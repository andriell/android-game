package com.andriell.mygame.base;

import android.graphics.Canvas;


public class SpriteAnimation extends SpriteMaterial {
    Animation animation;

    public SpriteAnimation(Animation animation, float x, float y) {
        super(x, y, animation.getWidth(), animation.getHeight());
        this.animation = animation;
    }

    public SpriteAnimation(Animation animation) {
        super(0, 0, animation.getWidth(), animation.getHeight());
        this.animation = animation;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawBitmap(animation.getBitmap(), x, y, null);
        return !(width + x < 0 || height + y < 0 || x > c.getWidth() || y > c.getHeight());
    }
}
