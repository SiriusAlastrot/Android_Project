package com.example.myapplication;

public class Tache {
    String title;
    int priority;
    int nbDay;
    boolean autoInc;

    Tache(String title, int priority, int nbDay, boolean autoInc) {
        this.priority = priority;
        this.title = title;
        this.nbDay = nbDay;
        this.autoInc = autoInc;
    }
}
