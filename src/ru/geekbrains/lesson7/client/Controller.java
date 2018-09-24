package ru.geekbrains.lesson7.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    @FXML
    HBox authPanel;
    @FXML
    HBox msgPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passField;

    private boolean authorized;

    public void setAutorized(boolean autorized) {
        this.authorized = autorized;
        if(authorized) {
            msgPanel.setVisible(true);
            msgPanel.setManaged(true);
            authPanel.setVisible(false);
            authPanel.setVisible(false);
        } else {
            msgPanel.setVisible(false);
            msgPanel.setManaged(false);
            authPanel.setVisible(true);
            authPanel.setVisible(true);
        }
    }

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    final String SERVER_IP = "localhost";
    final int SERVER_PORT = 8189;


    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendAuthMsg() {
        try {
            out.writeUTF("/auth " + loginField.getText()+ " " + passField.getText());
            loginField.clear();
            passField.clear();
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            authorized = false;
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            setAutorized(false);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String s = in.readUTF();
                            if(s.equals("/authok")) {
                                setAutorized(true);
                                break;
                            } else {
                                textArea.appendText(s + "\n");
                            }
                            textArea.appendText(s + "\n");
                        }
                        while (true) {
                            String s = in.readUTF();
                            textArea.appendText(s + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.setDaemon(true);
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
