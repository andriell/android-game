package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class SpriteBitmap extends SpriteMaterial {
    Bitmap bitmap;

    public SpriteBitmap(Bitmap bitmap, int x, int y) {
        super(x, y);
        this.bitmap = bitmap;
    }

    public SpriteBitmap(Bitmap bitmap) {
        super(0, 0, bitmap.getWidth(), bitmap.getHeight());
        this.bitmap = bitmap;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawBitmap(bitmap, x, y, null);
        return super.onDraw(c);
    }
}
