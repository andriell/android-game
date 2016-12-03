package com.andriell.mygame.base;

import android.view.MotionEvent;

/**
 * Created by Андрей on 01.12.2016.
 */

public interface InterfaceSpriteTouchListener extends InterfaceSpritePositioned {
    boolean onTouchEvent(MotionEvent e);
}
