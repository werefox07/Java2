package ru.geekbrains.lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> col = new ArrayList<String>();
        col.add("cat");
        col.add("apple");
        col.add("muse");
        col.add("cat");
        col.add("green");
        col.add("java");
        col.add("python");
        col.add("alpaca");
        col.add("ruby");
        col.add("php");
        col.add("muse");
        col.add("magic");
        col.add("cat");
        col.add("muse");
        col.add("java");
        col.add("muse");
        col.add("muse");

        ArrayList<String> notDup = removeDup(col);
        ArrayList<Integer> countEl = countEl(col, notDup);
        for (int i = 0; i < notDup.size(); i++) {
            System.out.print(notDup.get(i) + " ");
            System.out.println("Повторений: " + countEl.get(i));
        }

        Phonebook book = new Phonebook();
        book.add("Kotov", "8926456276");
        book.add("Li", "892654267");
        book.add("Ivanov", "897643275");
        book.add("Ivanov", "897364263");
        book.add("Alexanrova", "892576347");

        System.out.println();
        System.out.println("Список номеров для: Kotov");
        for(String phone: book.get("Kotov")) {
            System.out.println(phone);
        }

        System.out.println("Список номеров для: Ivanov");
        for(String phone: book.get("Ivanov")) {
            System.out.println(phone);
        }

    }
    public static ArrayList<String> removeDup (ArrayList<String> col) {
        ArrayList<String> notDup = new ArrayList<String> ();
        for(String el: col){
            if (!(notDup.contains(el))) {
                notDup.add(el);
            }
        }
        return notDup;

    }
    public static ArrayList<Integer> countEl (ArrayList<String> col, ArrayList<String> notDup) {
        ArrayList<Integer> countEl = new ArrayList<Integer> ();
        for(String l: notDup) {
            Integer count = 0;
            for (String c: col) {
                if (l.equals(c)) {
                    count++;
                }
            }
            countEl.add(count);
        }
        return countEl;
    }
}
