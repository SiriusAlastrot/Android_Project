package com.example.myapplication;
import java.util.*;
public class Ball {
    float size;
    float vX = 0;
    float vY = 0;
    float posX;
    float posY;
    public Ball(float posX, float posY, float size) {
        this.posX= posX;
        this.posY= posY;
        posX= 50;
        posY= 50;
        this.size= size;
    }
    void updatePosition()
    {
        vX = vX + (float)0.1;
        int seuilVitesse = 10;
        if(vX > seuilVitesse)
        {
            vX= seuilVitesse;
        }
        if(vY > seuilVitesse)
        {
            vY= seuilVitesse;
        }
        posY = posY + vY;
        posX = posX + vX;
    }
    void affiche()
    {
    }
    void collision(Maze maze)
    {
        /*int cellX= (int)(posX-(int)posX%(width/widthGrid))/(width/widthGrid);
        int cellY= (int)(posY-(int)posY%(height/heightGrid))/(height/heightGrid);
        if(maze.maze[cellY][cellX-1] == 1 && collisionRect((cellX-1)*(width/widthGrid),cellY*(height/heightGrid), width/widthGrid, height/heightGrid))
        {
            vX= -vX*0.5;
            posX= (cellX)*(width/widthGrid) +size/2;
        }
        if(maze.maze[cellY][cellX+1] == 1 && collisionRect((cellX+1)*(width/widthGrid),cellY*(height/heightGrid), width/widthGrid, height/heightGrid))
        {
            vX= -vX*0.5;
            posX= (cellX+1)*(width/widthGrid) -size/2;
        }
        if(maze.maze[cellY-1][cellX] == 1 && collisionRect((cellX)*(width/widthGrid),(cellY-1)*(height/heightGrid), width/widthGrid, height/heightGrid))
        {
            vY= -vY*0.5;
            posY= (cellY)*(height/heightGrid)+size/2;
        }
        if(maze.maze[cellY+1][cellX] == 1 && collisionRect((cellX)*(width/widthGrid),(cellY+1)*(height/heightGrid), width/widthGrid, height/heightGrid))
        {
            vY= -vY*0.5;
            posY= (cellY+1)*(height/heightGrid) - size/2;
        }
         */
    }
    boolean collisionRect(float x1, float y1, float w, float h)
    {
        float testX = posX;
        float testY = posY;
        if (posX+size/2 < x1)
        {
            testX = x1;
        }
        else if (posX+size/2 > x1+w)
        {
            testX = x1+w;
        }
        if (posY+size/2 < y1){
            testY = y1;
        }
        else if (posY+size/2 > y1+h){
            testY = y1+h;
        }
        float distX = posX-testX;
        float distY = posY-testY;
        double distance = Math.sqrt((distX*distX) + (distY*distY));
        if (distance <= size/2) {
            return true;
        }
        return false;
    }
}
