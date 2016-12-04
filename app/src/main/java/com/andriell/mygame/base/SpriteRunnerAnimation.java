package com.andriell.mygame.base;

import android.graphics.Canvas;

/**
 * Created by Андрей on 24.11.2016.
 */

public class SpriteRunnerAnimation extends SpriteAnimation implements InterfaceSpriteSetSpeed {
    protected float speedX = 0;
    protected float speedY = 0;

    public SpriteRunnerAnimation() {
        this(null, 0F, 0F, 0F, 0F);
    }

    public SpriteRunnerAnimation(Animation animation) {
        this(animation, 0F, 0F, 0F, 0F);
    }

    public SpriteRunnerAnimation(Animation animation, float speedX, float speedY) {
        this(animation, 0F, 0F, speedX, speedY);
    }
    public SpriteRunnerAnimation(Animation animation, float x, float y, float speedX, float speedY) {
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    @Override
    public boolean onDraw(Canvas c) {
        x += speedX;
        y += speedY;
        return super.onDraw(c);
    }
}
