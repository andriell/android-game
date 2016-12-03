package com.andriell.mygame.game2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;

import com.andriell.mygame.R;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.base.GameActivity;
import com.andriell.mygame.base.SpriteButton;
import com.andriell.mygame.base.SpriteColor;

public class StartActivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DrawSprite drawSprite = new DrawSprite(this, 2);
        drawSprite.addSprite(0, new SpriteColor(Color.WHITE));

        SpriteButton spriteButton = createButtonP(R.drawable.start1, R.drawable.start2, 0.2F);
        setPositionCenter(spriteButton);
        spriteButton.setUpListener(new SpriteButton.UpListener() {
            @Override
            public boolean onUp(MotionEvent e) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        });
        drawSprite.addSprite(1, spriteButton);

        setContentView(drawSprite);
    }
}
