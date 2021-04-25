package com.example.myapplication;

public class Score {
    String pseudo;
    String temps;
    String niveau;
    long id;

    public Score(long id, String pseudo, String temps, String niveau) {
        this.id = id;
        this.pseudo = pseudo;
        this.temps = temps;
        this.niveau = niveau;


    }
    public String getPseudo() {
        return pseudo;
    }
    public String gettemps() {
        return temps;
    }
    public String getniveau() {
        return niveau;
    }
}


