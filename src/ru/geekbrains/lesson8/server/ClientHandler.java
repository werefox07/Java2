package ru.geekbrains.lesson8.server;

import ru.geekbrains.lesson6.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nick;
    private Boolean isAuth;

    public String getNick() {
        return nick;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.isAuth = false;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(120000);
                        if (!isAuth) {
                            server.unsubscribe(ClientHandler.this);
                            socket.close();
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.startsWith("/auth ")) {
                                String[] data = msg.split("\\s");
                                String newNick = server.getAuthService().getNickByLoginAndPass(data[1], data[2]);
                                if (newNick != null) {
                                    if (!server.isNickBusy(newNick)) {
                                        nick = newNick;
                                        sendMsg("/authok " + newNick);
                                        isAuth = true;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        ClientHandler.this.sendMsg("Учетная запись уже занята");
                                    }
                                } else {
                                    ClientHandler.this.sendMsg("Неверный логин/пароль");
                                }
                            }
                        }
                        while (true) {
                            String msg = in.readUTF();
                            System.out.println(nick + ": " + msg);
                            if (msg.startsWith("/")) {
                                if (msg.equals("/end")) break;
                                if(msg.startsWith("/w ")){
                                    String[] data = msg.split("\\s", 3);
                                    server.sendPrivateMsg(ClientHandler.this, data[1], data[2]);
                                }
                            } else {
                                server.broadcastMsg(nick + ": " + msg);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        nick = null;
                        server.unsubscribe(ClientHandler.this);
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
