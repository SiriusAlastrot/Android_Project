package com.example.myapplication;

import java.util.ArrayList;

public class Level {
    int id;
    String levelName;
    Maze mazeLevel;
    int w;
    int h;
    int size;
    ArrayList<Score> listScore= new ArrayList<Score>();
   public Level(int id, String levelName, int size)
    {
        this.id=id;
        this.w= 9*size;
        this.h= 16* size+1;
        this.size= size;
        this.levelName= levelName;
        mazeLevel= new Maze(this.h, this.w);
    }
    public String getLevelName() {
        return levelName;
    }

}
