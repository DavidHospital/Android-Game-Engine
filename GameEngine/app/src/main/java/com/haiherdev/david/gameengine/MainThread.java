package com.haiherdev.david.gameengine;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by David on 9/23/2016.
 */
public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder holder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = holder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run()
    {
        long lastTimeMilli = System.nanoTime();
        long deltaMilli;

        while(running) {
            deltaMilli = (System.nanoTime() - lastTimeMilli) / 1000000;
            lastTimeMilli = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update(deltaMilli);
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                if(canvas != null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }
            gamePanel.postInvalidate();
        }
    }

    public void setRunning(boolean b) {
        this.running = b;
    }
}
