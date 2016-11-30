package com.andriell.mygame.base;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Андрей on 30.11.2016.
 */

public class SpriteText implements InterfaceSprite  {
    private Paint paint;
    private String text = "";
    private int x;
    private int y;

    public SpriteText(Paint paint, int x, int y) {
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    public SpriteText(int x, int y) {
        this.x = x;
        this.y = y;
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
        c.drawText(text, x, y, paint);
        return true;
    }
}
