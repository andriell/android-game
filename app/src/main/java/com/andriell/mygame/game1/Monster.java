package com.andriell.mygame.game1;

import android.graphics.Canvas;

import com.andriell.mygame.base.Animation;
import com.andriell.mygame.base.InterfaceSpriteCollisionListener;
import com.andriell.mygame.base.InterfaceSpriteCollisionTarget;
import com.andriell.mygame.base.SpriteRunnerAnimation;

public class Monster extends SpriteRunnerAnimation implements InterfaceSpriteCollisionListener {
    boolean isDead = false;

    public Monster(Animation animation, float xSpeed, float ySpeed) {
        super(animation, xSpeed, ySpeed);
    }

    @Override
    public boolean onCollision(InterfaceSpriteCollisionTarget sprite) {
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
