package com.example.myapplication;

import java.util.ArrayList;

public class Level {
    String levelName;
    Maze mazeLevel;
    int size;
    ArrayList<Score> listScore= new ArrayList<Score>();
    Level(String levelName, int size)
    {
        this.size= size;
        this.levelName= levelName;
        mazeLevel= new Maze(16* size+1, 9*size+1);
    }
}
