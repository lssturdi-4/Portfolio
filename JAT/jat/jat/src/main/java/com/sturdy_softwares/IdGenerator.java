package com.sturdy_softwares;

import java.util.ArrayList;

public class IdGenerator {
    static ArrayList<Integer> generatedIds;
    int id;

    public IdGenerator() {
        this.id = 0;
        generatedIds = new ArrayList<>();
    }

    public int generateId() {
        id = (int)(Math.random() * (999999999 - 100000000 + 1)) + 100000000;
        while (generatedIds.contains(id)) {
            id = (int)(Math.random() * (999999999 - 100000000 + 1)) + 100000000;
        }
        generatedIds.add(id);
        return id;
    }
}
