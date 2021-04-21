package com.example.myapplication;
import java.util.*;
public class Maze {
    int[][] maze;
    int h;
    int w;
    Maze(int h, int w)
    {
        maze = new int[h][w];
        this.h= h;
        this.w= w;
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                maze[i][j] = 1;
            }
        }
        Random rand = new Random();
        // r for row、c for column
        // Generate random r
        int r = rand.nextInt(h);
        while (r % 2 == 0) {
            r = rand.nextInt(h);
        }
        // Generate random c
        int c = rand.nextInt(w);
        while (c % 2 == 0) {
            c = rand.nextInt(w);
        }
        // Starting cell
        maze[r][c] = 0;

        //　Allocate the maze with recursive method
        recursion(r, c);
    }
    public Integer[] generateRandomDirections() {
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++)
        {
            randoms.add(i + 1);
        }
        Collections.shuffle(randoms);
        return randoms.toArray(new Integer[4]);
    }
    public void recursion(int r, int c) {
        // 4 random directions
        Integer[] randDirs = generateRandomDirections();
        // Examine each direction
        for (int i = 0; i < randDirs.length; i++) {

            switch(randDirs[i]){
                case 1: // Up
                    //　Whether 2 cells up is out or not
                    if (r - 2 <= 0)
                    {
                        continue;
                    }
                    if (maze[r - 2][c] != 0) {
                        maze[r-2][c] = 0;
                        maze[r-1][c] = 0;
                        recursion(r - 2, c);
                    }
                    break;
                case 2: // Right
                    // Whether 2 cells to the right is out or not
                    if (c + 2 >= w - 1)
                    {
                        continue;
                    }
                    if (maze[r][c + 2] != 0) {
                        maze[r][c + 2] = 0;
                        maze[r][c + 1] = 0;
                        recursion(r, c + 2);
                    }
                    break;
                case 3: // Down
                    // Whether 2 cells down is out or not
                    if (r + 2 >= h - 1)
                    {
                        continue;
                    }
                    if (maze[r + 2][c] != 0) {
                        maze[r+2][c] = 0;
                        maze[r+1][c] = 0;
                        recursion(r + 2, c);
                    }
                    break;
                case 4: // Left
                    // Whether 2 cells to the left is out or not
                    if (c - 2 <= 0)
                    {
                        continue;
                    }
                    if (maze[r][c - 2] != 0) {
                        maze[r][c - 2] = 0;
                        maze[r][c - 1] = 0;
                        recursion(r, c - 2);
                    }
                    break;
            }
        }
    }
    void affiche()
    {
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                if(maze[i][j] == 1)
                {
                    //fill(255,0,0);
                    //noStroke();
                    //rect(j*(width/w),i*(height/h), width/w, height/h);
                }
            }
        }
    }
}
