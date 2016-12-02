package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class SpriteBitmap extends SpriteMaterial {
    protected Bitmap bitmap;

    public SpriteBitmap(Bitmap bitmap) {
        this(bitmap, 0F, 0F);
    }

    public SpriteBitmap(Bitmap bitmap, float x, float y) {
        super(x, y, bitmap.getWidth(), bitmap.getHeight());
        this.bitmap = bitmap;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawBitmap(bitmap, x, y, null);
        return !(width + x < 0 || height + y < 0 || x > c.getWidth() || y > c.getHeight());
    }
}
