package ru.geekbrains.lesson1;

public class Main {
    public static void main(String[] args) {

        Participant[] part = {
                new Cat("Barsik"),
                new Dog("Bobik"),
                new Cat("Tom"),
                new Human("Bruce")
        };

        Obstracle[] cross = {new Wall(80), new Cross(800)};
        Course c = new Course(cross); // Создаем полосу препятствий
        Team team = new Team("Дружба", part); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        System.out.println();
        System.out.println("Прошли дистанцию:");
        team.showResults(); // Показываем результаты

    }
}
