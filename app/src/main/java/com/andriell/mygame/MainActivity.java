package com.andriell.mygame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.andriell.mygame.base.Sprite;
import com.andriell.mygame.base.SpriteView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // если хотим, чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SpriteView spriteView = new SpriteView(this, 3);
        spriteView.addSprite(0, new Sprite() {
            @Override
            public boolean onDraw(Canvas c) {
                c.drawColor(Color.WHITE);
                return true;
            }
        });
        spriteView.addSprite(0, new Sprite() {
            @Override
            public boolean onDraw(Canvas c) {
                c.drawColor(Color.BLACK);
                return true;
            }
        });
        setContentView(spriteView);
    }
}
