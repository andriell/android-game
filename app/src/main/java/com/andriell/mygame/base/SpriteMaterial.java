package com.andriell.mygame.base;

import android.graphics.Canvas;

public class SpriteMaterial implements InterfaceSpriteMaterial {
    protected float x = 0;
    protected float y = 0;
    protected float width = 0;
    protected float height = 0;

    public SpriteMaterial(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public SpriteMaterial(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean onDraw(Canvas c) {
        return !(x < 0 || y < 0 || x > c.getWidth() || y > c.getHeight());
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }
}
