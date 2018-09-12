package ru.geekbrains.lesson4;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
    private JTextField jT = new JTextField(20);
    private JTextArea jA = new JTextArea(20,30);
    private JButton bj1 = new JButton("Send message");
    private JPanel jP1 = new JPanel(new BorderLayout());
    private JScrollPane sP = new JScrollPane(jA);
    public MyWindow() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(800, 400, 400, 400);
        setLayout(new FlowLayout());
        setResizable(false);
        jA.setEditable(false);
        jA.setLineWrap(true);
        jT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { sendMessage(); }
        });
        bj1.setBackground(new Color(186, 172, 199));
        JPanel jP2 = new JPanel();
        bj1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            sendMessage();
            }
        });

        jP1.add(jT, BorderLayout.WEST);
        jP1.add(bj1, BorderLayout.EAST);
        add(sP);
        add(jP1);
        setVisible(true);
    }

    public void sendMessage() {
        jA.append(jT.getText() + "\n");
        jT.setText("");
        jT.grabFocus();
    }
}