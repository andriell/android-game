package com.andriell.mygame.base;

/**
 * Created by Андрей on 02.12.2016.
 */

public interface InterfaceSpriteSetPosition extends InterfaceSprite {
    void setX(float x);
    void setY(float y);
    float getX();
    float getY();
    float getHeight();
    float getWidth();
}
