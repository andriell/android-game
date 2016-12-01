package com.andriell.mygame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.andriell.mygame.base.InterfaceSpriteCollisionListener;
import com.andriell.mygame.base.InterfaceSpriteMaterial;
import com.andriell.mygame.base.SpriteRunner;

public class Monster extends SpriteRunner implements InterfaceSpriteCollisionListener {
    boolean isDead = false;

    public Monster(Bitmap bitmap, float x, float y, float xSpeed, float ySpeed) {
        super(bitmap, x, y, xSpeed, ySpeed);
    }

    @Override
    public boolean onCollision(InterfaceSpriteMaterial sprite) {
        if (isDead) {
            return false;
        }

        if (sprite instanceof Bullet) {
            Bullet bullet = (Bullet) sprite;
            if (bullet.isDead()) {
                return true;
            } else {
                bullet.setDead();
                isDead = true;
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onDraw(Canvas c) {
        if (isDead) {
            return false;
        }
        return super.onDraw(c);
    }
}
