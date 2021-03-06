package ru.geekbrains.lesson6;

import java.io.DataInputStream;
import java.io.IOException;

public class Listener extends Thread {
    private DataInputStream sc;

    public Listener(DataInputStream sc) {
        super();
        this.sc = sc;
    }

    @Override
    public void run() {
        while (true) {
            String str = null;
            try {
                str = sc.readUTF();
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
