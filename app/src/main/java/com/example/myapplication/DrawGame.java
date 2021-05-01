package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawGame extends View {

    static Ball ball= new Ball(0,0, 30);
    static Level currentLevel;
    static Boolean isInit = false;
    static Boolean win = false;
    public DrawGame(Context context,  AttributeSet attrs) {
        super(context, attrs);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit)
        {
            currentLevel.mazeLevel.initGrid(getWidth(), getHeight(), getContext());
            isInit = true;
        }
        Paint paint = new Paint();
        currentLevel.mazeLevel.affiche(canvas, paint, this.getContext());
        ball.size= getHeight()/currentLevel.h/3;
        Ball.affiche(paint, canvas,this.getContext());
        paint.setStyle(Paint.Style.FILL);
        Ball.updatePosition( (ActivityGame.gravity[1]),  (ActivityGame.gravity[2]), getWidth(), getHeight(), currentLevel.w, currentLevel.h);
        Ball.collision(paint, canvas, currentLevel.mazeLevel);
        win = Ball.isWinned(currentLevel.mazeLevel, getContext(), canvas, paint);
        super.onDraw(canvas);
    }

}
