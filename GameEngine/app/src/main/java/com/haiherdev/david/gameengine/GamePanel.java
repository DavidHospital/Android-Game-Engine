package com.haiherdev.david.gameengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.haiherdev.david.gameengine.testclasses.GradientRect;

/**
 * Created by David on 9/23/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {


    MainThread thread;

    GradientRect gr;

    public GamePanel(Context context) {
        super(context);

        //set the pixel format for the SurfaceHolder
        getHolder().setFormat(PixelFormat.RGBA_8888);

        //add the callback to the SurfaceHolder to interupt events
        getHolder().addCallback(this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);

        int[] colors = {
                0xFF99ceff,
                0xFFcce6ff
        };
        float[] positions = {
                0f, 1f
        };
        gr = new GradientRect(new RectF(0, 0, Game.WIDTH, Game.HEIGHT), 0, 0, 0, Game.HEIGHT,
                colors, positions);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //safely start a new thread
        thread = new MainThread(getHolder(), this);
        while (true) {
            try {
                thread.setRunning(true);
                thread.start();
                break;
            } catch (IllegalThreadStateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //safely stop the current thread
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        double x = e.getX();
        double y = e.getY();

        return super.onTouchEvent(e);
    }

    public void update(long elapsedMilli) {
        System.out.println(elapsedMilli);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        gr.draw(canvas);
    }
}
