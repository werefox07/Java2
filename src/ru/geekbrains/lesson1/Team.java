package ru.geekbrains.lesson1;

public class Team {
    private String nameTeam;
    private Participant[] part;

    public Team(String nameTeam, Participant[] part) {
        this.nameTeam = nameTeam;
        this.part = new Participant[part.length];
        for (int i = 0; i<part.length; i++) {
            this.part[i] = part[i];
        }
    }

    public Participant getPart(int i) {
        return part[i];
    }

    public int size() {
        return part.length;
    }

    public void infoAll() {
        for (Participant a: part) {
            a.info();
        }
    }

    public void showResults(){
        for (Participant a: part) {
            if (a.isOnDistance()) {
                a.info();
            }
        }
    }
}


