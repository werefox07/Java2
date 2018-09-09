package ru.geekbrains.lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class Phonebook {
    private HashMap<String, ArrayList<String>> phoneMap = new HashMap<>();

    public ArrayList<String> get(String surname) {
        return phoneMap.get(surname);
    }

    public void add(String surname, String phoneNumber) {
        if(phoneMap.containsKey(surname)) {
            phoneMap.get(surname).add(phoneNumber);
        } else {
            ArrayList<String> l = new ArrayList<>();
            l.add(phoneNumber);
            phoneMap.put(surname, l);
        }

    }
}
