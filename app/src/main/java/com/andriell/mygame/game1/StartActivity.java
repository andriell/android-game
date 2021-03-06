package com.andriell.mygame.game1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;

import com.andriell.mygame.base.DrawView;
import com.andriell.mygame.base.InterfaceSpriteButtonDownListener;
import com.andriell.mygame.base.InterfaceSpriteButtonUpListener;
import com.andriell.mygame.game2.MainActivity;
import com.andriell.mygame.R;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.base.GameActivity;
import com.andriell.mygame.base.SpriteButtonBitmap;
import com.andriell.mygame.base.SpriteColor;

public class StartActivity extends GameActivity {

    @Override
    protected void init() {

    }

    @Override
    protected DrawView preload() {
        return null;
    }

    @Override
    protected DrawView game() {
        DrawSprite drawSprite = new DrawSprite(this, 2);
        drawSprite.addSprite(0, new SpriteColor(Color.WHITE));

        SpriteButtonBitmap spriteButtonBitmap = createButtonP(R.drawable.start1, R.drawable.start2, 0.2F);
        setPositionCenter(spriteButtonBitmap);
        spriteButtonBitmap.setUpListener(new InterfaceSpriteButtonUpListener() {
            @Override
            public boolean onUp(MotionEvent e) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        });
        drawSprite.addSprite(1, spriteButtonBitmap);
        return drawSprite;
    }
}
