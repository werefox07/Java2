package ru.geekbrains.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8189);
            System.out.println("Клиент подключился");
            DataInputStream sc = new DataInputStream(socket.getInputStream());
            DataOutputStream pw = new DataOutputStream(socket.getOutputStream());
            Listener listener = new Listener(sc);
            Sender sender = new Sender(pw);
            listener.start();
            sender.start();
            listener.join();
            sender.join();
        } catch (IOException e) {
            System.out.println("Ошибка инициализации клиента");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
