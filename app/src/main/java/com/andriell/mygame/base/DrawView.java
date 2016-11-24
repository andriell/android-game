package com.andriell.mygame.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class DrawView extends SurfaceView {

    private DrawThread drawThread;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                drawThread = new DrawThread(getHolder());
                drawThread.setRunning(true);
                drawThread.start();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                drawThread.setRunning(false);
                while (retry) {
                    try {
                        drawThread.join();
                        retry = false;
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        });
    }

    class DrawThread extends Thread {

        private boolean running = false;
        private SurfaceHolder surfaceHolder;

        DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            while (running) {
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null) {
                        Log.e("GameView", "onDraw canvas == null");
                        return;
                    }
                    synchronized (surfaceHolder) {
                        myDraw(canvas);
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    abstract protected void myDraw(Canvas canvas);
}
