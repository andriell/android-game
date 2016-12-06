package com.andriell.mygame.base;

import android.graphics.Canvas;

public class SpritePositioned implements InterfaceSpritePositioned {
    protected float x = 0;
    protected float y = 0;
    protected float width = 0;
    protected float height = 0;

    public SpritePositioned() {}

    public SpritePositioned(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public SpritePositioned(float x, float y, float width, float height) {
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

    @Override
    public float getCenterX() {
        return x + width / 2;
    }

    @Override
    public float getCenterY() {
        return y + height / 2;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setCenterX(float x) {
        this.x = x - width / 2;
    }

    @Override
    public void setCenterY(float y) {
        this.y = y - height / 2;
    }
}
