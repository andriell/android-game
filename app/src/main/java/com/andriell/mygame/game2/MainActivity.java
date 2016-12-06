package com.andriell.mygame.game2;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.andriell.mygame.R;
import com.andriell.mygame.base.Animation;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.base.GameActivity;
import com.andriell.mygame.base.InterfaceSpriteButtonDownListener;
import com.andriell.mygame.base.InterfaceSpriteButtonUpListener;
import com.andriell.mygame.base.InterfaceSpriteCollisionListener;
import com.andriell.mygame.base.InterfaceSpriteCollisionTarget;
import com.andriell.mygame.base.SpriteAnimation;
import com.andriell.mygame.base.SpriteAnimationLimited;
import com.andriell.mygame.base.SpriteButtonClear;
import com.andriell.mygame.base.SpriteProgressBarRect;
import com.andriell.mygame.base.SpriteRunnerAnimation;
import com.andriell.mygame.base.SpriteSheetBitmap;

import java.util.Random;

public class MainActivity extends GameActivity {
    DrawSprite drawSprite;
    Player player;
    SpriteProgressBarRect liveProgressBar;
    SpriteProgressBarRect reloadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawSprite = new DrawSprite(this, 3);

        SpriteSheetBitmap stars = new SpriteSheetBitmap();
        setSizeP(stars, 1F, 1F);
        setPositionPTL(stars, 0, 0);
        stars.setBitmap(createBitmapP(R.drawable.stars1));
        stars.setSpeedY(yP(0.001F));
        drawSprite.addSprite(0, stars);

        Fly fly = new Fly();
        setPositionPTL(fly, 0.2F, 0.5F, ALIGN_CENTER, ALIGN_CENTER);
        drawSprite.addSprite(1, fly);

        player = new Player();
        setPositionPBL(player, 0.01F, 0.5F, ALIGN_CENTER, ALIGN_BOTTOM);
        drawSprite.addSprite(1, player);

        reloadProgressBar = new SpriteProgressBarRect();
        reloadProgressBar.setColorValue(Color.BLUE);
        reloadProgressBar.setValue(1F);
        setSizeP(reloadProgressBar, 0.8F, 0.01F);
        setPositionPTL(reloadProgressBar, 0.03F, 0.1F);
        drawSprite.addSprite(2, reloadProgressBar);

        liveProgressBar = new SpriteProgressBarRect();
        liveProgressBar.setValue(1F);
        setSizeP(liveProgressBar, 0.8F, 0.01F);
        setPositionPTL(liveProgressBar, 0.01F, 0.1F);
        drawSprite.addSprite(2, liveProgressBar);

        OnLeft toLeft = new OnLeft();
        SpriteButtonClear buttonLeft = new SpriteButtonClear();
        setSizeP(buttonLeft, 0.5F, 0.3F);
        setPositionPBL(buttonLeft, 0F, 0F);
        buttonLeft.setDownListener(toLeft);
        buttonLeft.setUpListener(toLeft);
        drawSprite.addSprite(2, buttonLeft);

        OnRight toRight = new OnRight();
        SpriteButtonClear buttonRight = new SpriteButtonClear();
        setSizeP(buttonRight, 0.5F, 0.3F);
        setPositionPBR(buttonRight, 0F, 0F);
        buttonRight.setDownListener(toRight);
        buttonRight.setUpListener(toRight);
        drawSprite.addSprite(2, buttonRight);

        OnFire onFire = new OnFire();
        SpriteButtonClear buttonFire = new SpriteButtonClear();
        setSizeP(buttonFire, 1F, 0.7F);
        setPositionPTL(buttonFire, 0F, 0F);
        buttonFire.setDownListener(onFire);
        drawSprite.addSprite(2, buttonFire);

        setContentView(drawSprite);
    }

    class Explosion extends SpriteAnimationLimited {
        private Animation a;

        public Explosion(float x, float y) {
            if (a == null) {
                a = createAnimationP(new int[] {R.drawable.explosion1, R.drawable.explosion2}, new int[] {100, 100}, 0F, 0.05F);
            }
            setAnimation(a);
            setTimeLimit(500);
            setCenterX(x);
            setCenterY(y);
        }
    }

    class OnFire implements InterfaceSpriteButtonUpListener, InterfaceSpriteButtonDownListener {
        @Override
        public boolean onDown(MotionEvent e) {
            if (reloadProgressBar.getValue() < 1) {
                return true;
            }
            reloadProgressBar.setValue(0F);
            int index = e.getActionIndex();
            Rocket rocket = new Rocket();
            rocket.setX(player.getCenterX());
            rocket.setY(player.getY() - rocket.getHeight());
            setSpeed(rocket, e.getX(index), e.getY(index), 10F);
            drawSprite.addSprite(1, rocket);
            return true;
        }

        @Override
        public boolean onUp(MotionEvent e) {
            return true;
        }
    }

    class OnRight implements InterfaceSpriteButtonUpListener, InterfaceSpriteButtonDownListener {
        @Override
        public boolean onDown(MotionEvent e) {
            player.toRight();
            return true;
        }

        @Override
        public boolean onUp(MotionEvent e) {
            player.toNormal();
            return true;
        }
    }

    class OnLeft implements InterfaceSpriteButtonUpListener, InterfaceSpriteButtonDownListener {
        @Override
        public boolean onDown(MotionEvent e) {
            player.toLeft();
            return true;
        }

        @Override
        public boolean onUp(MotionEvent e) {
            player.toNormal();
            return true;
        }
    }

    class Fireball extends SpriteRunnerAnimation implements InterfaceSpriteCollisionTarget {
        private float damage = 10F;
        private float live = 10F;

        public Fireball() {
            setAnimation(createAnimationP(new int[] {R.drawable.fireball1_0, R.drawable.fireball1_1}, new int[] {100, 100}, 0F, 0.05F));
        }

        public float getDamage() {
            return damage;
        }

        public void setDamage(float damage) {
            this.damage = damage;
        }

        public float getLive() {
            return live;
        }

        public void setLive(float live) {
            this.live = live;
        }

        @Override
        public boolean onDraw(Canvas c) {
            if (live <= 0) {
                return false;
            }
            return super.onDraw(c);
        }
    }

    class Rocket extends SpriteRunnerAnimation implements InterfaceSpriteCollisionTarget {
        private float damage = 10F;
        private float live = 10F;
        public Rocket() {
            setAnimation(createAnimationP(new int[] {R.drawable.rocket_1_0, R.drawable.rocket_1_1}, new int[] {100, 100}, 0F, 0.05F));
        }

        public float getDamage() {
            return damage;
        }

        public void setDamage(float damage) {
            this.damage = damage;
        }

        @Override
        public boolean onDraw(Canvas c) {
            if (live <= 0) {
                return false;
            }
            return super.onDraw(c);
        }

        public float getLive() {
            return live;
        }

        public void setLive(float live) {
            this.live = live;
        }
    }

    class Fly extends SpriteRunnerAnimation implements InterfaceSpriteCollisionListener {
        private float live = 1000;
        private Handler handler;
        private Random rnd;
        public Fly() {
            setAnimation(createAnimationP(new int[] {R.drawable.fly_1_0, R.drawable.fly_1_1}, new int[] {100, 100}, 0F, 0.1F));

            handler = new Handler();
            rnd = new Random();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (isRun && live > 0) {
                        Fireball fireball = new Fireball();
                        fireball.setCenterX(getX());
                        fireball.setY(getY() + getHeight());
                        setSpeed(fireball, player.getCenterX(), player.getCenterY(), 10F);
                        drawSprite.addSprite(1, fireball);
                    }
                    handler.postDelayed(this, rnd.nextInt(4000));
                }
            });
        }

        @Override
        public boolean onCollision(InterfaceSpriteCollisionTarget sprite) {
            if (sprite instanceof Rocket) {
                Rocket rocket = (Rocket) sprite;
                live -= rocket.getDamage();
                rocket.setLive(-live);
                drawSprite.addSprite(1, new Explosion(rocket.getCenterX(), rocket.getY()));
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.grenade);
                mediaPlayer.start();
            }
            return false;
        }

        @Override
        public boolean onDraw(Canvas c) {
            if (live <= 0) {
                return false;
            }
            return super.onDraw(c);
        }
    }

    class Player extends SpriteRunnerAnimation implements InterfaceSpriteCollisionListener {
        float live = 1000F;
        float liveMax = 1000F;
        Animation animationNormal;
        Animation animationLeft;
        Animation animationRight;

        public Player() {
            animationNormal = createAnimationP(new int[] {R.drawable.player_1_0}, null, 0F, 0.1F);
            animationLeft = createAnimationP(new int[] {R.drawable.player_1_l}, null, 0F, 0.1F);
            animationRight = createAnimationP(new int[] {R.drawable.player_1_r}, null, 0F, 0.1F);
            setAnimation(animationNormal);
        }

        public void toLeft() {
            setSpeedX(xP(-0.01F));
            setAnimation(animationLeft);
        }

        public void toRight() {
            setSpeedX(xP(0.01F));
            setAnimation(animationRight);
        }

        public void toNormal() {
            setSpeedX(0F);
            setAnimation(animationNormal);
        }

        @Override
        public boolean onDraw(Canvas c) {
            super.onDraw(c);
            float reload = reloadProgressBar.getValue();
            reload += 0.01F;
            if (reload > 1) {
                reload = 1F;
            }
            reloadProgressBar.setValue(reload);

            if (x <= 0) {
                setX(1F);
                setSpeedX(0F);
            } else if (x >= displaySize.x - getWidth()) {
                setX(displaySize.x - getWidth() - 1);
                setSpeedX(0F);
            }
            return true;
        }

        @Override
        public boolean onCollision(InterfaceSpriteCollisionTarget sprite) {
            if (sprite instanceof Fireball) {
                Fireball fireball = (Fireball) sprite;
                live -= fireball.getDamage();
                if (live < 0F) {
                    live = 0F;
                }
                liveProgressBar.setValue(live / liveMax);
                fireball.setLive(-live);
                drawSprite.addSprite(1, new Explosion(fireball.getCenterX(), fireball.getY()));
            }
            return false;
        }
    }
}
