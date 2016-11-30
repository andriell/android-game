package com.andriell.mygame.base;

import android.graphics.Canvas;

public class SpriteMaterial implements InterfaceSpriteMaterial {
    protected int x;
    protected int y;
    protected int width;
    protected int height;


    public SpriteMaterial(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SpriteMaterial(int x, int y, int width, int height) {
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
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
