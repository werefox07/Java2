package ru.geekbrains.lesson1;

public class Course {
    Obstracle[] cross;

    public Course(Obstracle[] cross) {
        this.cross = new Obstracle[cross.length];
        for (int i = 0; i<cross.length; i++) {
            this.cross[i] = cross[i];
        }
    }

    public void doIt(Team team) {
        for (int i = 0; i < team.size(); i++) {
            Participant part = team.getPart(i);

            for (int j = 0; j < cross.length; j++) {
                if (part.isOnDistance()) {
                    cross[j].doIt(part);
                }
            }
        }
    }
}
