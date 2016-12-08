package com.andriell.mygame.base;

import android.graphics.Canvas;

/**
 * Created by Андрей on 07.12.2016.
 */

public class SpriteGroup implements InterfaceSpriteGroup {
    protected float x = 0;
    protected float y = 0;
    protected float width = 0;
    protected float height = 0;
    InterfaceSpritePositioned[] sprites;
    float[] spriteX;
    float[] spriteY;

    public SpriteGroup(int size) {
        sprites = new InterfaceSpritePositioned[size];
        spriteX = new float[size];
        spriteY = new float[size];
    }

    public void addRight(int j, int i, SpritePositioned sprite, float x, float y) {
        if (i < 0 || i > sprites.length) {
            return;
        }
        add(i, sprite, spriteX[j] + sprites[j].getWidth() + x, spriteY[j] + y);
    }

    public void addBottom(int j, int i, SpritePositioned sprite, float x, float y) {
        if (i < 0 || i > sprites.length) {
            return;
        }
        add(i, sprite, spriteX[j] + x, spriteY[j] + sprites[j].getHeight() + y);
    }

    public void add(int i, SpritePositioned sprite) {
        add(i, sprite, 0, 0);
    }

    public void add(int i, SpritePositioned sprite, float x, float y) {
        if (i < 0 || i > sprites.length) {
            return;
        }
        sprites[i] = sprite;
        spriteX[i] = x;
        spriteY[i] = y;
        width += x + sprite.getWidth();
        height += y + sprite.getHeight();
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
    public float getHeight() {
        return height;
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

    @Override
    public boolean onDraw(Canvas c) {
        if (sprites == null) {
            return false;
        }
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] == null) {
                continue;
            }
            sprites[i].setX(x + spriteX[i]);
            sprites[i].setY(y + spriteY[i]);
            sprites[i].onDraw(c);
        }
        if (x < -width || y < -height || x > c.getWidth() || y > c.getHeight()) {
            destroy();
            return false;
        }
        return true;
    }

    public void destroy() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = null;
        }
        sprites = null;
    }

    @Override
    public InterfaceSpritePositioned[] getSprites() {
        return sprites;
    }
}
