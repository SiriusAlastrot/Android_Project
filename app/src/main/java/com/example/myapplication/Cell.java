package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cell {
    static Bitmap background;
    static Context context;
    float x;
    float y;
    float sizeX;
    float sizeY;
    int state;
    Cell(float x, float y, float sizeX, float sizeY, int state, Context context)
    {
        this.x= x;
        this.y= y;
        this.sizeX= sizeX;
        this.sizeY= sizeY;
        this.state= state;
        background = BitmapFactory.decodeResource(context.getResources(),R.drawable.mur);
        background= Bitmap.createScaledBitmap(background, (int)sizeX, (int)sizeY, true);
    }
    void affiche(Canvas canvas, Paint paint)
    {
        if(state == 1)
        {
            canvas.drawBitmap(background,x,y,paint);
            //paint.setColor(Color.RED);
        }
        else
        {
            paint.setColor(Color.WHITE);
        }
        //canvas.drawRect(x,y, x + sizeX, y +sizeY, paint);
    }
}