package ru.geekbrains.lesson1;

public class Human implements Participant{
    String name;
    int maxRunDit;
    int maxJumpDist;
    int maxSwimDist;

    boolean active;

    public Human(String name) {
        this.name = name;
        this.maxRunDit = 1000;
        this.maxJumpDist = 2500;
        this.maxSwimDist = 1000;
        this.active = true;
    }

    public void run(int distance){
        if(distance <= maxRunDit){
            System.out.println("Человек " + name + " Успешно справился с кроссом");

        }else {
            System.out.println("Человек " + name + " Не справился с кроссом");
            active = false;
        }

    }

    public void jump(int distance){
        if(distance <= maxJumpDist){
            System.out.println("Человек " + name + " Успешно справился с прыжком");

        }else {
            System.out.println("Человек " + name + " Не справился с прыжком");
            active = false;
        }

    }
    public void swim(int distance){
        if(distance <= maxSwimDist){
            System.out.println("Человек " + name + " Успешно справился с заплывом");

        }else {
            System.out.println("Человек " + name + " Не справился с заплывом");
            active = false;
        }

    }

    @Override
    public boolean isOnDistance() {
        return active;
    }

    @Override
    public void info() {
        System.out.println("Человек " + name + " ");
    }

}
