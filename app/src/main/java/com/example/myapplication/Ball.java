package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
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
        vX = vX - (float)(accY*0.1);
        vY = vY - (float)(accX*0.1);
        float seuilVitesse = 10;
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
        if(posX-size <= width/widthGrid)
        {
            posX = 0+size + width/widthGrid;
        }
        if(posX+size >= width)
        {
            posX = width-size;
        }
        if(posY-size <= height/heightGrid)
        {
            posY = 0+size + height/heightGrid;
        }
        if(posY+size >= height)
        {
            posY = height-size;
        }
        cellX= (int) ((int)(posX-posX%(width/widthGrid))/(width/widthGrid));
        cellY= (int) ((int)(posY-posY%(height/heightGrid))/(height/heightGrid));
    }
    static void affiche(Paint paint, Canvas canvas)
    {
        paint.setColor(Color.WHITE);
        canvas.drawCircle(posX ,posY,  size, paint);
    }
    static void collision(Paint paint, Canvas canvas, Maze maze)
    {
        Cell haut= maze.cellGrid[cellX+1][cellY+2];
        Cell bas= maze.cellGrid[cellX+1][cellY];
        Cell gauche= maze.cellGrid[cellX][cellY+1];
        Cell droite= maze.cellGrid[cellX+2][cellY+1];
        if (bas.state == 1 && collisionRect(bas)) {
            vY= (float) (-vY*0.9);
            posY = bas.y+ bas.sizeY + size;
        }
        if (haut.state == 1 && collisionRect(haut)) {
            vY= (float) (-vY*0.9);
            posY = haut.y - size;
        }
        if (gauche.state == 1 && collisionRect(gauche)) {
            vX= (float) (-vX*0.9);
            posX = gauche.x+ gauche.sizeX + size;
        }
        if (droite.state == 1 && collisionRect(droite)) {
            vX= (float) (-vX*0.9);
            posX = droite.x - size;
        }
    }
    static boolean collisionRect(Cell collisionCell)
    {
        float testX = posX;
        float testY = posY;
        if (posX+size< collisionCell.x)
        {
            testX = collisionCell.y;
        }
        else if (posX-size > collisionCell.x+collisionCell.sizeX)
        {
            testX = collisionCell.x+collisionCell.sizeX;
        }
        if (posY+size < collisionCell.y){
            testY = collisionCell.y;
        }
        else if (posY-size > collisionCell.y+collisionCell.sizeY){
            testY = collisionCell.y+collisionCell.sizeY;
        }
        float distX = posX-testX;
        float distY = posY-testY;
        double distance = Math.sqrt((distX*distX) + (distY*distY));
        if (distance <= size) {
            return true;
        }
        return false;
    }
    static boolean isWinned(Paint paint, Canvas canvas, Maze maze)
    {
        Cell winCell = maze.cellGrid[(int) (maze.w- 2)][(int) (maze.h - 2)];
        float bottom = winCell.y + winCell.sizeY;
        float right = winCell.x + winCell.sizeX;
        if(collisionRect(winCell))
        {
            paint.setColor(Color.BLUE);
            canvas.drawRect(winCell.x,winCell.y, right, bottom, paint);
            return true;
        }
        else
        {
            paint.setColor(Color.GREEN);
            canvas.drawRect(winCell.x,winCell.y, right, bottom, paint);
            return false;
        }
    }
    static void calVelocity(Cell rect){
        float mirrorX = 1;
        float mirrorY = 1;
        float x = posX;
        float y = posY;
        float sdX = vX;
        float sdy = vY;
        x -= vX;
        y -= vY;
        float batW2 = rect.sizeX / 2;
        float batH2 = rect.sizeY / 2;

        float batH = batH2 + size;
        float batW = batW2 + size;

        x -= rect.x;
        x -= rect.y;
        if(x < 0){
            mirrorX = -1;
            x = -x;
            sdX = -sdX;
        }
        if(y < 0){
            mirrorY = -1;
            y = -y;
            sdy = -sdy;
        }

        float distY = (batH - y);
        float distX = (batW - x);

        if(sdX > 0 && sdy > 0){
            return;
        }


        float ballSpeed = (float) Math.sqrt(sdX * sdX + sdy * sdy);

        float bottomX = x +(sdX / sdy) * distY;


        float rightY = y +(sdy / sdX) * distX;


        float distB = (float) Math.hypot(bottomX - x, batH - y);
        float distR = (float) Math.hypot(batW - x, rightY - y);
        boolean hit = false;

        if(sdy < 0 && bottomX <= batW2 && distB <= ballSpeed && distB < distR){
            hit = true;
            y = batH - sdy * ((ballSpeed - distB) / ballSpeed);
            sdy = -sdy;
        }
        if(! hit && sdX < 0 && rightY <= batH2 && distR <= ballSpeed && distR <= distB){
            hit = true;
            x =  batW  - sdX * ((ballSpeed - distR) / ballSpeed);;
            sdX = -sdX;
        }
        if(!hit){
            float u = ((batW2 - x) * sdX + (batH2 - y) * sdy)/(ballSpeed * ballSpeed);

            float cpx = x + sdX * u;
            float cpy = y + sdy * u;

            float radSqr = size*size;

            float dist  = (cpx - batW2) * (cpx - batW2) + (cpy - batH2) * (cpy - batH2);

            if(dist > radSqr){
                return;
            }

            float d = (float) (Math.sqrt(radSqr - dist) / ballSpeed);


            cpx -= sdX * d;
            cpy -= sdy * d;

            d = (float) Math.hypot(cpx - x,cpy - y);

            if(d > ballSpeed){
                return;
            }

            x = cpx;
            y = cpy;


            float ty = (cpx - batW2) / size;
            float tx = -(cpy - batH2) / size;


            float bsx = sdX / ballSpeed;
            float bsy = sdy / ballSpeed;
            float dot = (bsx * tx + bsy * ty) * 2;


            d = ballSpeed - d;

            sdX = (tx * dot - bsx);
            sdy = (ty * dot - bsy);


            x += sdX * d;
            y += sdy * d;


            sdX *= ballSpeed;
            sdy *= ballSpeed;
            hit = true;
        }

        if(hit){
            x *= mirrorX;
            sdX *= mirrorX;
            y *= mirrorY;
            sdy *= mirrorY;

            x += rect.x;
            y += rect.y;

            posX = x;
            posY = y;
            vX = sdX;
            vY = sdy;
        }
    }

    public static void setPosX(float posX) {
        Ball.posX = posX;
    }
}

