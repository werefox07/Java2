package ru.geekbrains.lesson6a;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sender extends Thread{
    private DataOutputStream pw;

    public Sender(DataOutputStream pw) {
        super();
        this.pw = pw;
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String str = in.nextLine();
            try {
                pw.writeUTF(str);
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
