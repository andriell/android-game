package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class SpriteBitmap extends SpritePositioned {
    protected Bitmap bitmap;

    public SpriteBitmap() {
        this(null, 0F, 0F);
    }

    public SpriteBitmap(Bitmap bitmap) {
        this(bitmap, 0F, 0F);
    }

    public SpriteBitmap(Bitmap bitmap, float x, float y) {
        super(x, y, bitmap.getWidth(), bitmap.getHeight());
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public boolean onDraw(Canvas c) {
        if (bitmap != null) {
            c.drawBitmap(bitmap, x, y, null);
        }
        return !(width + x < 0 || height + y < 0 || x > c.getWidth() || y > c.getHeight());
    }
}
