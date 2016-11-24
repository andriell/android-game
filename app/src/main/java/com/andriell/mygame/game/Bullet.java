package com.andriell.mygame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.andriell.mygame.base.SpriteMaterial;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class Bullet implements SpriteMaterial {
    private Bitmap bitmap;
    private int[] hw = new int[2];
    private int[] xyxy = new int[4];
    private int speed = 25;
    private double angle;

    @Override
    public int[] getXYXY() {
        return xyxy;
    }

    /**Конструктор*/
    public Bullet(Bitmap bmp, int[] xy, int[] xyClick, int speed) {
        bitmap = bmp;
        hw[0] = bitmap.getHeight();
        hw[1] = bitmap.getWidth();
        xyxy[0] = xy[0];
        xyxy[1] = xy[1];
        angle = Math.atan((double)(xy[1] - xyClick[1]) / (xy[0] - xyClick[0]));
        this.speed = speed;
    }

    /**Перемещение объекта, его направление*/
    private void update() {
        xyxy[0] += speed * Math.cos(angle);
        xyxy[1] += speed * Math.sin(angle);
        xyxy[2] = xyxy[0] + hw[0];
        xyxy[3] = xyxy[1] + hw[1];
    }

    @Override
    public boolean onDraw(Canvas c) {
        update();
        c.drawBitmap(bitmap, xyxy[0], xyxy[1], null);
        return xyxy[0] < c.getHeight() && xyxy[1] < c.getWidth();
    }
}