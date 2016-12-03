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
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public float getHeight() {
        if (animation == null) {
            return height;
        }
        return animation.getHeight();
    }

    @Override
    public float getWidth() {
        if (animation == null) {
            return width;
        }
        return animation.getWidth();
    }

    @Override
    public boolean onDraw(Canvas c) {
        if (animation != null) {
            c.drawBitmap(animation.getBitmap(), x, y, null);
        }
        return !(getWidth() + x < 0 || getHeight() + y < 0 || x > c.getWidth() || y > c.getHeight());
    }
}
