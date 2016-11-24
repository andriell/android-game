package com.andriell.mygame.game;

import android.graphics.Bitmap;
import android.util.Log;

import com.andriell.mygame.base.InterfaceSpriteCollisionListener;
import com.andriell.mygame.base.InterfaceSpriteMaterial;
import com.andriell.mygame.base.SpriteRunner;

public class Monster extends SpriteRunner implements InterfaceSpriteCollisionListener {
    boolean live = true;

    public Monster(Bitmap bitmap, int x, int y, int xSpeed, int ySpeed) {
        super(bitmap, x, y, xSpeed, ySpeed);
    }

    @Override
    public boolean onCollision(InterfaceSpriteMaterial sprite) {
        live = ! (sprite instanceof Bullet);
        Log.i("Monster", "Collision");
        if (!live) {
            Log.i("Monster", "is dead");
        }
        return live;
    }
}
