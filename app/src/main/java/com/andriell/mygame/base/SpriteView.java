package com.andriell.mygame.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import com.andriell.mygame.base.DrawView;
import com.andriell.mygame.base.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class SpriteView extends DrawView {
    private List<Sprite>[] sprites;

    public SpriteView(Context context, int zSize) {
        super(context);
        sprites = new ArrayList[zSize];
    }

    public void addSprite(int z, Sprite sprite) {
        if (z < 0 || z >= sprites.length) {
            return;
        }
        if (sprites[z] == null) {
            sprites[z] = new ArrayList<Sprite>();
        }
        sprites[z].add(sprite);
    }

    @Override
    protected void myDraw(Canvas canvas) {
        Iterator<Sprite> iterator;
        Sprite s;
        for (List<Sprite> list : sprites) {
            if (list == null) {
                continue;
            }
            try {
                iterator = list.iterator();
                while (iterator.hasNext()) {
                    s = iterator.next();
                    if (s instanceof SpriteCollisionListener) {
                        testCollision((SpriteCollisionListener) s);
                    }
                    if (!s.onDraw(canvas)) {
                        Log.i("SpriteView", "Sprite remove");
                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                Log.d("SpriteView", "list error", e);
            }
        }
    }

    protected void testCollision(SpriteCollisionListener listener) {
        Iterator<Sprite> iterator;
        Sprite s;
        SpriteMaterial material;
        int[] xyxy1 = listener.getXYXY(), xyxy2;
        for (List<Sprite> list : sprites) {
            if (list == null) {
                continue;
            }
            iterator = list.iterator();
            while (iterator.hasNext()) {
                s = iterator.next();
                if (s instanceof SpriteMaterial) {
                    material = (SpriteMaterial) s;
                    xyxy2 = material.getXYXY();
                    // !(1x2 < 2x1 || 1x1 > 2x2 || 1y2 < 2y1 || 1y1 > 2y2)
                    if (!(xyxy1[2] < xyxy2[0] || xyxy1[0] > xyxy2[2] || xyxy1[3] < xyxy2[1] || xyxy1[1] > xyxy2[3])) {
                        if (!listener.onCollision(material)) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
