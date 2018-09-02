package ru.geekbrains.lesson1;

public class Water extends Obstracle {
    private int distance;

    public Water(int distance) {
        this.distance = distance;
    }

    @Override
    public void doIt(Participant a) {
        a.swim(distance);

    }


}
