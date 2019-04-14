package com.kamenov.martin.virtualjoystickmovement.Views;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.kamenov.martin.virtualjoystickmovement.R;
import com.kamenov.martin.virtualjoystickmovement.constants.Constants;
import com.kamenov.martin.virtualjoystickmovement.engine.GamePanel;
import com.kamenov.martin.virtualjoystickmovement.engine.services.DrawingService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.SortingService;

public class GameActivity extends Activity {

    private DrawingService drawingService;
    private GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        RelativeLayout relativeLayout = findViewById(R.id.container);
        drawingService = DrawingService.getInstance(SortingService.getInstance());
        gamePanel = new GamePanel(this, drawingService);
        relativeLayout.addView(gamePanel);
    }
}
