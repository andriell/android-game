package com.andriell.mygame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.andriell.mygame.base.InterfaceSpriteCollisionListener;
import com.andriell.mygame.base.InterfaceSpriteMaterial;
import com.andriell.mygame.base.SpriteRunner;

public class Monster extends SpriteRunner implements InterfaceSpriteCollisionListener {
    boolean isDead = false;

    public Monster(Bitmap bitmap, int x, int y, int xSpeed, int ySpeed) {
        super(bitmap, x, y, xSpeed, ySpeed);
    }

    @Override
    public boolean onCollision(InterfaceSpriteMaterial sprite) {
        if (isDead) {
            return false;
        }
        isDead = sprite instanceof Bullet;
        Log.i("Monster", "Collision");
        if (isDead) {
            ((Bullet) sprite).setDead();
            Log.i("Monster", "is dead");
        }
        return isDead;
    }

    @Override
    public boolean onDraw(Canvas c) {
        if (isDead) {
            return false;
        }
        return super.onDraw(c);
    }
}
