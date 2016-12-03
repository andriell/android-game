package com.andriell.mygame.base;

import android.graphics.Canvas;


public class SpriteAnimation extends SpritePositioned {
    Animation animation;

    public SpriteAnimation() {
        this(null, 0F, 0F);
    }

    public SpriteAnimation(Animation animation) {
        this(animation, 0F, 0F);
    }

    public SpriteAnimation(Animation animation, float x, float y) {
        super(x, y, animation.getWidth(), animation.getHeight());
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawBitmap(animation.getBitmap(), x, y, null);
        return !(width + x < 0 || height + y < 0 || x > c.getWidth() || y > c.getHeight());
    }
}
