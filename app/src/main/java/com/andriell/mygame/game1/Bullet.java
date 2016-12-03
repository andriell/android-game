package com.andriell.mygame.game1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.andriell.mygame.base.SpriteRunnerBitmap;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class Bullet extends SpriteRunnerBitmap {
    private boolean isDead = false;

    public Bullet(Bitmap bitmap, float x, float y, float xSpeed, float ySpeed) {
        super(bitmap, x, y, xSpeed, ySpeed);
    }

    public void setDead() {
        isDead = true;
    }

    @Override
    public boolean onDraw(Canvas c) {
        if (isDead) {
            return false;
        }
        return super.onDraw(c);
    }

    public boolean isDead() {
        return isDead;
    }
}