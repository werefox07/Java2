package ru.geekbrains.lesson6;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            DataInputStream sc = new DataInputStream(sock.getInputStream());
            DataOutputStream pw = new DataOutputStream(sock.getOutputStream());
            Listener listener = new Listener(sc);
            Sender sender = new Sender(pw);
            listener.start();
            sender.start();
            listener.join();
            sender.join();
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
