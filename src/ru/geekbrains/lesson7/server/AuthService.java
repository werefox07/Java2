package ru.geekbrains.lesson7.server;

interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}
