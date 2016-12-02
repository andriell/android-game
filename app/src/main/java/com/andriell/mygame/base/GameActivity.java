package com.andriell.mygame.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Андрей on 02.12.2016.
 */
public class GameActivity extends Activity {
    public final Point displaySize = new Point();
    public boolean isRun = false;

    @Override
    protected void onStart() {
        super.onStart();
        isRun = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRun = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRun = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // если хотим, чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindowManager().getDefaultDisplay().getSize(displaySize);
    }

    public Bitmap createBitmapP(int id, float height) {
        return createBitmapP(id, 0F, height);
    }

    public Bitmap createBitmapP(int id, float width, float height) {
        Bitmap bitmapPlayer = BitmapFactory.decodeResource(getResources(), id);
        if (width > 0 && height > 0) {
            bitmapPlayer = Bitmap.createScaledBitmap(bitmapPlayer, (int) (displaySize.x * width), (int) (displaySize.y * height), true);
        } else if (width > 0) {
            bitmapPlayer = Bitmap.createScaledBitmap(bitmapPlayer, (int) (displaySize.x * width), (int) (bitmapPlayer.getHeight() * width), true);
        } else if (height > 0) {
            bitmapPlayer = Bitmap.createScaledBitmap(bitmapPlayer, (int) ((bitmapPlayer.getWidth() * displaySize.y * height) / bitmapPlayer.getHeight()), (int) (displaySize.y * height), true);
        }
        return bitmapPlayer;
    }

    public Animation createAnimationP(int[] id, int[] timeMap, float height) {
        return createAnimationP(id, timeMap, 0, height, 1F);
    }

    public Animation createAnimationP(int[] id, int[] timeMap) {
        return createAnimationP(id, timeMap, 0, 0, 1F);
    }

    public Animation createAnimationP(int[] id, int[] timeMap, float width, float height) {
        return createAnimationP(id, timeMap, width, height, 1F);
    }

    public Animation createAnimationP(int[] id, int[] timeMap, float width, float height, float speed) {
        Bitmap[] bitmaps = new Bitmap[id.length];
        for (int i = 0; i < id.length; i++) {
            bitmaps[i] = createBitmapP(id[i], width, height);
        }
        return new Animation(bitmaps, timeMap, speed);
    }
}
