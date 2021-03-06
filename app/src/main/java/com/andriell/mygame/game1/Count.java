package com.andriell.mygame.game1;

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

        paint = new Paint(paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeMiter(10);
        paint.setColor(Color.WHITE);
        setPaintStroke(paint);
        setText("0");
    }

    public void count() {
        count++;
        setText(Integer.toString(count));
    }
}
