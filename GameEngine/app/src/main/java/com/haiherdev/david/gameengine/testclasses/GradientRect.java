package com.haiherdev.david.gameengine.testclasses;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;

/**
 * Created by David on 9/23/2016.
 */
public class GradientRect {

    private Paint p;
    private RectF rect;

    public GradientRect (RectF rect, int x1, int y1, int x2, int y2,
                         int[] colors, @Nullable float[] positions) {
        this.rect = rect;
        p = new Paint();
        p.setDither(true);
        p.setShader(new LinearGradient(x1, y1, x2, y2,
                colors, positions, Shader.TileMode.CLAMP));
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, p);
    }
}
