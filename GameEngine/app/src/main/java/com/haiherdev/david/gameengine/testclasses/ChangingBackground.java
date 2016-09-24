package com.haiherdev.david.gameengine.testclasses;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by David on 9/23/2016.
 */
public class ChangingBackground {



    int[] color = {
            255, 0, 0
    };
    int strongest = 0;
    boolean inc = true;

    public void update(long elapsedMilli) {
        int before = (strongest - 1 + 3) % 3;
        int after = (strongest + 1 + 3) % 3;

        if (inc) {
            if (color[after] < 255) {
                color[after] ++;
            } else {
                strongest = after;
                inc = false;
            }
        } else {
            if (color[before] > 0) {
                color[before] --;
            } else {
                inc = true;
            }
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.argb(255, color[0], color[1], color[2]));
    }
}
