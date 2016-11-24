package com.andriell.mygame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.andriell.mygame.base.InterfaceSpriteMaterial;
import com.andriell.mygame.base.SpriteRunner;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class Bullet extends SpriteRunner {
    private boolean isDead = false;

    public Bullet(Bitmap bitmap, int x, int y, int xSpeed, int ySpeed) {
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
}