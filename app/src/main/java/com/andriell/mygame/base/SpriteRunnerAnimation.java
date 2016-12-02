package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Андрей on 24.11.2016.
 */

public class SpriteRunnerAnimation extends SpriteAnimation {
    protected float xSpeed = 0;
    protected float ySpeed = 0;

    public SpriteRunnerAnimation(Animation animation, float xSpeed, float ySpeed) {
        this(animation, 0F, 0F, xSpeed, ySpeed);
    }
    public SpriteRunnerAnimation(Animation animation, float x, float y, float xSpeed, float ySpeed) {
        super(animation, x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public boolean onDraw(Canvas c) {
        x += xSpeed;
        y += ySpeed;
        return super.onDraw(c);
    }
}
