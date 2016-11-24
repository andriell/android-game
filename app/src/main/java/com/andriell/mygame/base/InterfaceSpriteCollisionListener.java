package com.andriell.mygame.base;

/**
 * Created by Rybalko on 24.11.2016.
 */

public interface InterfaceSpriteCollisionListener extends InterfaceSpriteMaterial {
    boolean onCollision(InterfaceSpriteMaterial sprite);
}
