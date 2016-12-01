package com.andriell.mygame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.base.SpriteButton;
import com.andriell.mygame.base.SpriteColor;

public class StartActivity extends Activity {

    Point displaySize = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // если хотим, чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.start1);
        bitmap1 = Bitmap.createScaledBitmap(bitmap1, bitmap1.getWidth() / 2, bitmap1.getHeight() / 2, true);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.start2);
        bitmap2 = Bitmap.createScaledBitmap(bitmap2, bitmap2.getWidth() / 2, bitmap2.getHeight() / 2, true);

        getWindowManager().getDefaultDisplay().getSize(displaySize);

        DrawSprite drawSprite = new DrawSprite(this, 2);
        drawSprite.addSprite(0, new SpriteColor(Color.WHITE));

        drawSprite.addSprite(1, new SpriteButton(bitmap1, bitmap2, (displaySize.x - bitmap1.getWidth()) / 2, (displaySize.y - bitmap1.getHeight()) / 2) {
            @Override
            public boolean onDown(MotionEvent e) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        });

        setContentView(drawSprite);
    }
}
