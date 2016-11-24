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

public class SpriteView extends DrawView {
    private List<InterfaceSprite>[] sprites;

    public SpriteView(Context context, int zSize) {
        super(context);
        sprites = new ArrayList[zSize];
    }

    public void addSprite(int z, InterfaceSprite sprite) {
        if (z < 0 || z >= sprites.length) {
            return;
        }
        if (sprites[z] == null) {
            sprites[z] = new ArrayList<InterfaceSprite>();
        }
        sprites[z].add(sprite);
    }

    @Override
    protected void myDraw(Canvas canvas) {
        Iterator<InterfaceSprite> iterator;
        InterfaceSprite s;
        for (List<InterfaceSprite> list : sprites) {
            if (list == null) {
                continue;
            }
            try {
                iterator = list.iterator();
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
                Log.d("SpriteView", "list error", e);
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
