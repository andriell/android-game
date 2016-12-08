package com.andriell.mygame.base;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

    public void addRight(int i, int j, SpritePositioned sprite, float x, float y) {
        if (sprites == null || i < 0 || i > sprites.length || sprites[i] == null) {
            return;
        }
        add(j, sprite, spriteX[i] + sprites[i].getWidth() + x, y);
    }

    public void addBottom(int i, int j, SpritePositioned sprite, float x, float y) {
        if (sprites == null || i < 0 || i > sprites.length || sprites[i] == null) {
            return;
        }
        add(j, sprite, x, spriteY[i] + sprites[i].getHeight() + y);
    }

    public void add(int i, SpritePositioned sprite) {
        add(i, sprite, 0F, 0F);
    }

    public void add(int i, SpritePositioned sprite, float x, float y) {
        if (sprites == null || i < 0 || i > sprites.length) {
            return;
        }
        sprites[i] = sprite;
        spriteX[i] = x;
        spriteY[i] = y;
        if (width < spriteX[i] + sprite.getWidth()) {
            width = spriteX[i] + sprite.getWidth();
        }
        if (height < spriteY[i] + sprite.getHeight()) {
            height = spriteY[i] + sprite.getHeight();
        }
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
        // Debug
        /*Paint paint = new Paint();
        paint.setColor(Color.argb(128, 128, 128, 128));
        c.drawRect(x, y, x + width, y + height, paint);*/
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] == null) {
                continue;
            }
            sprites[i].setX(x + spriteX[i]);
            sprites[i].setY(y + spriteY[i]);
            sprites[i].onDraw(c);
        }
        return !(x < -width || y < -height || x > c.getWidth() || y > c.getHeight());
    }

    @Override
    public InterfaceSpritePositioned[] getSprites() {
        return sprites;
    }
}
