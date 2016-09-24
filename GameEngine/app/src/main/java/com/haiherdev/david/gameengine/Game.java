package com.haiherdev.david.gameengine;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class Game extends Activity {

    // WIDTH and HEIGHT of the screen
    public static final int WIDTH;
    public static final int HEIGHT;

    static {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view to new GamePanel
        setContentView(new GamePanel(this));
    }
}
