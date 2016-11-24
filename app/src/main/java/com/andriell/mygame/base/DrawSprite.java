package com.andriell.mygame.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class DrawSprite extends DrawView {
    private List<InterfaceSprite>[] sprites;
    private List<InterfaceSprite>[] spritesNew;

    public DrawSprite(Context context, int zSize) {
        super(context);
        sprites = new ArrayList[zSize];
        spritesNew = new ArrayList[zSize];
        for (int i = 0; i < zSize; i++) {
            sprites[i] = new ArrayList<InterfaceSprite>();
            spritesNew[i] = new ArrayList<InterfaceSprite>();
        }
    }

    public void addSprite(int z, InterfaceSprite sprite) {
        if (z < 0 || z >= sprites.length) {
            return;
        }
        spritesNew[z].add(sprite);
    }

    @Override
    protected void myDraw(Canvas canvas) {
        Iterator<InterfaceSprite> iterator;
        InterfaceSprite s;

        synchronized (this) {
            for (int i = 0; i < sprites.length; i++) {
                try {
                    iterator = sprites[i].iterator();
                    while (iterator.hasNext()) {
                        s = iterator.next();
                        if (s instanceof InterfaceSpriteCollisionListener) {
                            testCollision((InterfaceSpriteCollisionListener) s);
                        }
                        if (!s.onDraw(canvas)) {
                            Log.i("SpriteView", "InterfaceSprite remove");
                            iterator.remove();
                        }
                    }
                } catch (Exception e) {
                    Log.e("SpriteView", "iterator error", e);
                }
            }

            for (int i = 0; i < sprites.length; i++) {
                sprites[i].addAll(spritesNew[i]);
                spritesNew[i].clear();
            }
        }
    }

    protected void testCollision(InterfaceSpriteCollisionListener listener) {
        Iterator<InterfaceSprite> iterator;
        InterfaceSprite s;
        InterfaceSpriteMaterial material;
        for (List<InterfaceSprite> list : sprites) {
            if (list == null) {
                continue;
            }
            iterator = list.iterator();
            while (iterator.hasNext()) {
                s = iterator.next();
                if (listener.equals(s)) {
                    continue;
                }
                if (s instanceof InterfaceSpriteMaterial) {
                    material = (InterfaceSpriteMaterial) s;
                    // 1x2 < 2x1 || 1x1 > 2x2
                    if ((listener.getX() + listener.getWidth() < material.getX() || listener.getX() > material.getX() + material.getWidth())) {
                        continue;
                    }
                    // 1y2 < 2y1 || 1y1 > 2y2
                    if ((listener.getY() + listener.getHeight() < material.getY() || listener.getY() > material.getY() + material.getHeight())) {
                        continue;
                    }
                    if (!listener.onCollision(material)) {
                        return;
                    }
                }
            }
        }
    }
}
