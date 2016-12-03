package com.andriell.mygame.base;

import android.graphics.Canvas;

public class SpriteResize extends SpritePositioned implements InterfaceSpriteResize {
    public SpriteResize() {}

    public SpriteResize(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public SpriteResize(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }
}
