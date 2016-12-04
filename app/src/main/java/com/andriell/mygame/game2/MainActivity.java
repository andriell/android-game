package com.andriell.mygame.game2;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Bundle;
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
import com.andriell.mygame.base.SpriteButtonClear;
import com.andriell.mygame.base.SpriteRunnerAnimation;
import com.andriell.mygame.base.SpriteSheetBitmap;

public class MainActivity extends GameActivity {
    DrawSprite drawSprite;
    Player player;

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

        player = new Player();
        setPositionPBL(player, 0.01F, 0.5F, ALIGN_CENTER, ALIGN_BOTTOM);
        drawSprite.addSprite(1, player);

        OnLeft toLeft = new OnLeft();
        SpriteButtonClear buttonLeft = new SpriteButtonClear();
        setSizeP(buttonLeft, 0.5F, 0.2F);
        setPositionPBL(buttonLeft, 0F, 0F);
        buttonLeft.setDownListener(toLeft);
        buttonLeft.setUpListener(toLeft);
        drawSprite.addSprite(2, buttonLeft);

        OnRight toRight = new OnRight();
        SpriteButtonClear buttonRight = new SpriteButtonClear();
        setSizeP(buttonRight, 0.5F, 0.2F);
        setPositionPBR(buttonRight, 0F, 0F);
        buttonRight.setDownListener(toRight);
        buttonRight.setUpListener(toRight);
        drawSprite.addSprite(2, buttonRight);

        OnFire onFire = new OnFire();
        SpriteButtonClear buttonFire = new SpriteButtonClear();
        setSizeP(buttonFire, 1F, 0.8F);
        setPositionPTL(buttonFire, 0F, 0F);
        buttonFire.setDownListener(onFire);
        drawSprite.addSprite(2, buttonFire);

        setContentView(drawSprite);
    }

    class OnFire implements InterfaceSpriteButtonDownListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
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

    class Fireball extends SpriteAnimation implements InterfaceSpriteCollisionTarget {
        public Fireball() {
            setAnimation(createAnimationP(new int[] {R.drawable.fireball1_0, R.drawable.fireball1_1}, new int[] {100, 100}, 0F, 0.1F));
        }
    }

    class Rocket extends SpriteAnimation implements InterfaceSpriteCollisionTarget {
        public Rocket() {
            setAnimation(createAnimationP(new int[] {R.drawable.rocket_1_0, R.drawable.rocket_1_1}, new int[] {100, 100}, 0F, 0.1F));
        }
    }

    class Fly extends SpriteRunnerAnimation implements InterfaceSpriteCollisionListener {
        public Fly() {
            setAnimation(createAnimationP(new int[] {R.drawable.fly_1_0, R.drawable.fly_1_1}, new int[] {100, 100}, 0F, 0.1F));
        }

        @Override
        public boolean onCollision(InterfaceSpriteCollisionTarget sprite) {
            return false;
        }
    }

    class Player extends SpriteRunnerAnimation implements InterfaceSpriteCollisionListener {
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
            return false;
        }
    }
}
