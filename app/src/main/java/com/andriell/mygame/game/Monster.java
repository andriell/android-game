package com.andriell.mygame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.andriell.mygame.base.SpriteCollisionListener;
import com.andriell.mygame.base.SpriteMaterial;

public class Monster implements SpriteCollisionListener {
    private Bitmap bitmap;
    private int[] hw = new int[2];
    private int[] xyxy = new int[4];
    private int speed;
    private boolean live = true;

    @Override
    public int[] getXYXY() {
        return xyxy;
    }

    /**Конструктор*/
    public Monster(Bitmap bmp, int[] xy, int speed) {
        bitmap = bmp;
        hw[0] = bitmap.getHeight();
        hw[1] = bitmap.getWidth();
        xyxy[0] = xy[0];
        xyxy[1] = xy[1];

        this.speed = speed;
    }

    /**Перемещение объекта, его направление*/
    private void update() {
        xyxy[0] -= speed;
        xyxy[2] = xyxy[0] + hw[0];
        xyxy[3] = xyxy[1] + hw[1];
    }

    @Override
    public boolean onDraw(Canvas c) {
        //update();
        c.drawBitmap(bitmap, xyxy[0], xyxy[1], null);
        return live && xyxy[0] < c.getHeight() && xyxy[1] < c.getWidth();
    }

    @Override
    public boolean onCollision(SpriteMaterial sprite) {
        live = ! (sprite instanceof Bullet);
        if (!live) {
            Log.i("Monster", "is dead");
        }
        return live;
    }
}
