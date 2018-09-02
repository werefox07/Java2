package ru.geekbrains.lesson1;

public class Wall extends Obstracle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }


    @Override
    public void doIt(Participant a) {
        a.jump(height);
    }
}
