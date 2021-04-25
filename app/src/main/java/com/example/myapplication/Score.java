package com.example.myapplication;

public class Score {
    String pseudo;
    String temps;
    String niveau;


    public Score(String pseudo, String temps, String niveau) {
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


