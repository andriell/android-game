package com.andriell.mygame.base;

import android.graphics.Canvas;

/**
 * Created by Андрей on 03.12.2016.
 */

public class SpriteSheetBitmap extends SpriteBitmap implements InterfaceSpriteResize {
    private float speedX = 0F;
    private float speedY = 0F;


    @Override
    public boolean onDraw(Canvas c) {
        x += speedX;
        if (x >= width) {
            x -= width;
        }
        y += speedY;
        if (y >= height) {
            y -= height;
        }
        if (bitmap == null) {
            return true;
        }
        for (float left = x; left < width; left += bitmap.getWidth()) {
            for (float top = y; top < height; top += bitmap.getHeight()) {
                c.drawBitmap(bitmap, left, top, null);
            }
        }
        return true;
    }


    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
