package ru.geekbrains.lesson6a;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

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
