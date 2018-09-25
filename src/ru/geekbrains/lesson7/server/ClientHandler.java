package ru.geekbrains.lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String nick;

    public String getNick() {
        return this.nick;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {

                public void handleAuth() throws IOException {
                    while (true) {
                        String msg = in.readUTF();
                        if(msg.startsWith("/auth")) {
                            String[] data = msg.split(" ");
                            AuthService authService = ClientHandler.this.server.getAuthServer();
                            String nick = authService.getNickByLoginPass(data[1], data[2]);
                            if(nick != null) {
                                sendMsg("/authok");
                                server.subscribe(ClientHandler.this);
                                ClientHandler.this.nick = nick;
                                break;
                            }
                        }
                    }
                }

                public void handleMsg() throws IOException {
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/w")) {
                            String[] data = msg.split(" ");
                            String nick = data[1];
                            String[] msgData = new String[data.length - 2];
                            System.arraycopy(data, 2, msgData, 0, msgData.length);
                            msg = String.join(" ", msgData);
                            msg = ClientHandler.this.nick + ": " + msg;
                            server.privateMsg(msg, ClientHandler.this.nick, nick);
                        } else {
                            System.out.println("from client: " + msg);
                            msg = ClientHandler.this.nick + ": " + msg;
                            server.broadcastMsg(msg);
                            if (msg.equals("/end")) break;
                        }
                    }
                }

                @Override
                public void run() {
                    try {
                        handleAuth();
                        handleMsg();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            server.unsubscribe(ClientHandler.this);
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

    public void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
