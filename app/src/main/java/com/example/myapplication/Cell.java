package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cell {
    float x;
    float y;
    float sizeX;
    float sizeY;
    int state;
    Cell(float x, float y, float sizeX, float sizeY, int state)
    {
        this.x= x;
        this.y= y;
        this.sizeX= sizeX;
        this.sizeY= sizeY;
        this.state= state;
    }
    void affiche(Canvas canvas, Paint paint)
    {
        if(state == 1)
        {
            paint.setColor(Color.RED);
        }
        else if(state == 2)
        {
            paint.setColor(Color.YELLOW);
        }
        else
        {
            paint.setColor(Color.BLACK);
        }
        canvas.drawRect(x,y, x + sizeX, y +sizeY, paint);
    }
}