package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawGame extends View {

    Ball ball= new Ball(0,0, 30);
    public static Level currentLevel;
    public DrawGame(Context context,  AttributeSet attrs) {
        super(context, attrs);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        currentLevel.mazeLevel.initGrid(getWidth(), getHeight());
        ball.size= getHeight()/currentLevel.h/3;
        Paint paint = new Paint();
        currentLevel.mazeLevel.affiche(canvas, paint);
        Ball.affiche(paint, canvas);
        paint.setStyle(Paint.Style.FILL);
        Ball.updatePosition( (ActivityGame.gravity[1]),  (ActivityGame.gravity[2]), getWidth(), getHeight(), currentLevel.w, currentLevel.h);
        Ball.collision(paint, canvas, currentLevel.mazeLevel);
        Ball.isWinned(paint, canvas, currentLevel.mazeLevel);
        super.onDraw(canvas);
    }

}
