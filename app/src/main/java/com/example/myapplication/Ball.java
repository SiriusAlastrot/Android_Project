package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Ball {
    static Bitmap background;
    static float size;
    static float vX= 0;
    static float vY= 0;
    static float posX= 500;
    static float posY= 500;
    static int cellX;
    static int cellY;
    public Ball(float posX, float posY, float size) {
        this.posX= posX;
        this.posY= posY;
        this.size= size;
    }
    static void updatePosition(float accX, float accY, float width, float height, float widthGrid, float heightGrid)
    {
        vX = vX - (float)(accY*0.3);
        vY = vY - (float)(accX*0.3);
        float seuilVitesse = size/2;
        if(vX > seuilVitesse)
        {
            vX= seuilVitesse;
        }
        if(vX < -seuilVitesse)
        {
            vX= -seuilVitesse;
        }
        if(vY > seuilVitesse)
        {
            vY= seuilVitesse;
        }
        if(vY < -seuilVitesse)
        {
            vY= -seuilVitesse;
        }

        posY = posY + vY;
        posX = posX + vX;
        cellX= (int) ((int)(posX-posX%(width/widthGrid))/(width/widthGrid));
        cellY= (int) ((int)(posY-posY%(height/heightGrid))/(height/heightGrid));
    }
    static void affiche(Paint paint, Canvas canvas,Context context)
    {
        background = BitmapFactory.decodeResource(context.getResources(),R.drawable.ball);
        //paint.setColor(Color.WHITE);
        //canvas.drawCircle(posX ,posY,  size, paint);
        //background.setWidth((int) size);
        //background.setHeight((int) size);
        Bitmap bm = Bitmap.createScaledBitmap(background, (int)(size*2), (int)(size*2), true);
        canvas.drawBitmap(bm,posX- size,posY-size,paint);
    }
    static void collision(Paint paint, Canvas canvas, Maze maze)
    {
        Cell haut= maze.cellGrid[cellX+1][cellY+2];
        Cell bas= maze.cellGrid[cellX+1][cellY];
        Cell gauche= maze.cellGrid[cellX][cellY+1];
        Cell droite= maze.cellGrid[cellX+2][cellY+1];
        if (bas.state == 1 && collisionRect(bas)) {
            vY= (float) (-vY*0.5);
            posY = bas.y+ bas.sizeY + size;
        }
        if (haut.state == 1 && collisionRect(haut)) {
            vY= (float) (-vY*0.5);
            posY = haut.y - size;
        }
        if (gauche.state == 1 && collisionRect(gauche)) {
            vX= (float) (-vX*0.5);
            posX = gauche.x+ gauche.sizeX + size;
        }
        if (droite.state == 1 && collisionRect(droite)) {
            vX= (float) (-vX*0.5);
            posX = droite.x - size;
        }
    }
    static boolean collisionRect(Cell collisionCell)
    {
        float testX = posX;
        float testY = posY;
        if(collisionCell != null) {
            if (posX + size < collisionCell.x) {
                testX = collisionCell.y;
            } else if (posX - size > collisionCell.x + collisionCell.sizeX) {
                testX = collisionCell.x + collisionCell.sizeX;
            }
            if (posY + size < collisionCell.y) {
                testY = collisionCell.y;
            } else if (posY - size > collisionCell.y + collisionCell.sizeY) {
                testY = collisionCell.y + collisionCell.sizeY;
            }
            float distX = posX - testX;
            float distY = posY - testY;
            double distance = Math.sqrt((distX * distX) + (distY * distY));
            if (distance <= size) {
                return true;
            }
        }
        return false;

    }
    static boolean isWinned(Maze maze, Context context, Canvas canvas, Paint paint)
    {
        Cell winCell = maze.cellGrid[(int) (maze.w- 2)][(int) (maze.h - 2)];
        background = BitmapFactory.decodeResource(context.getResources(),R.drawable.win);
        Bitmap.createScaledBitmap(background, (int)(winCell.sizeX*10), (int)(winCell.sizeY*10), true);
        canvas.drawBitmap(background,winCell.x+(int)winCell.sizeX/2 -16*2,winCell.y+(int)winCell.sizeY/2 - 16*2,paint);
        if(collisionRect(winCell))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void setPosX(float posX) {
        Ball.posX = posX;
    }
}

