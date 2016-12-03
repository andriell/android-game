package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Андрей on 24.11.2016.
 */

public class SpriteRunnerBitmap extends SpriteBitmap {
    protected float xSpeed = 0;
    protected float ySpeed = 0;

    public SpriteRunnerBitmap() {
        this(null, 0F, 0F, 0F, 0F);
    }

    public SpriteRunnerBitmap(Bitmap bitmap) {
        this(bitmap, 0F, 0F, 0F, 0F);
    }

    public SpriteRunnerBitmap(Bitmap bitmap, float speedX, float speedY) {
        this(bitmap, 0F, 0F, speedX, speedY);
    }

    public SpriteRunnerBitmap(Bitmap bitmap, float x, float y, float xSpeed, float ySpeed) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public boolean onDraw(Canvas c) {
        x += xSpeed;
        y += ySpeed;
        return super.onDraw(c);
    }
}
