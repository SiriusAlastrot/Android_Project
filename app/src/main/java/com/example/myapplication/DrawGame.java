package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawGame extends View {

    public static Ball ball= new Ball(0,0, 30);
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
        paint.setStyle(Paint.Style.FILL);
        ball.affiche(paint, canvas);
        ball.updatePosition( (ActivityGame.gravity[1]),  (ActivityGame.gravity[2]), getWidth(), getHeight(), currentLevel.w, currentLevel.h);
        ball.collision(paint, canvas, currentLevel.mazeLevel);
        ball.isWinned(paint, canvas, currentLevel.mazeLevel);
        super.onDraw(canvas);
    }

}
