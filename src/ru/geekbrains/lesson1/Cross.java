package ru.geekbrains.lesson1;

public class Cross extends Obstracle {
    private int length;

    public Cross(int lenght) {
        this.length = lenght;
    }


    @Override
    public void doIt(Participant a) {
        a.run(length);
    }
}
