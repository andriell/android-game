package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Андрей on 24.11.2016.
 */

public class SpriteRunnerBitmap extends SpriteBitmap implements InterfaceSpriteSetSpeed {
    protected float speedX = 0;
    protected float speedY = 0;

    public SpriteRunnerBitmap() {
        this(null, 0F, 0F, 0F, 0F);
    }

    public SpriteRunnerBitmap(Bitmap bitmap) {
        this(bitmap, 0F, 0F, 0F, 0F);
    }

    public SpriteRunnerBitmap(Bitmap bitmap, float speedX, float speedY) {
        this(bitmap, 0F, 0F, speedX, speedY);
    }

    public SpriteRunnerBitmap(Bitmap bitmap, float x, float y, float speedX, float speedY) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Override
    public boolean onDraw(Canvas c) {
        x += speedX;
        y += speedY;
        return super.onDraw(c);
    }

    public float getSpeedX() {
        return speedX;
    }

    @Override
    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    @Override
    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
