package com.andriell.mygame.game;

import android.graphics.Color;
import android.graphics.Paint;

import com.andriell.mygame.base.SpriteText;

/**
 * Created by Андрей on 30.11.2016.
 */

public class Count extends SpriteText {
    private int count = 0;

    public Count() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.LEFT);
        setPaint(paint);
        setText("0");
    }

    public void count() {
        count++;
        setText(Integer.toString(count));
    }
}
