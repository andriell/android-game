package com.andriell.mygame.base;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Андрей on 30.11.2016.
 */

public class SpriteText extends SpriteMaterial {
    private Paint paint;
    private String text = "";

    public SpriteText(Paint paint, int x, int y) {
        super(x, y);
        this.paint = paint;
    }

    public SpriteText(int x, int y) {
        super(x, y);
        paint = new Paint();
    }


    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawText(text, x, x, paint);
        return true;
    }
}
