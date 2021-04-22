package com.example.myapplication;

public class Level {
    String levelName;
    Maze mazeLevel;
    Level(String levelName, int size)
    {
        mazeLevel= new Maze(16* size+1, 9*size+1);
    }
}
