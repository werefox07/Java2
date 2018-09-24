package ru.geekbrains.lesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;
    private AuthService authServer;

    public Server () {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            clients = new Vector<>();
            authServer = new BaseAuthServer();
            System.out.println("Server started ... Waiting for clients");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthService getAuthServer() {
        return this.authServer;
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }
    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public void broadcastMsg (String msg) {
        for(ClientHandler o: clients){
            o.sendMsg(msg);
        }
    }

    public void privateMsg (String msg, String from, String to) {
        for(ClientHandler o: clients){
            if (o.getNick().equals(from) || o.getNick().equals(to)) {
                o.sendMsg(msg);
            }
        }
    }
}
