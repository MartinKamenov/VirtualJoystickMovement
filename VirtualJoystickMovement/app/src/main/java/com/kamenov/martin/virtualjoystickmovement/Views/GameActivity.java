package com.kamenov.martin.virtualjoystickmovement.Views;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kamenov.martin.virtualjoystickmovement.R;
import com.kamenov.martin.virtualjoystickmovement.constants.Constants;
import com.kamenov.martin.virtualjoystickmovement.engine.GamePanel;
import com.kamenov.martin.virtualjoystickmovement.engine.joystickService.JoystickPanel;
import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.contracts.Object3D;
import com.kamenov.martin.virtualjoystickmovement.engine.personModel.PersonModel;
import com.kamenov.martin.virtualjoystickmovement.engine.services.DrawingService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.PaintService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.SortingService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.factories.FigureFactory;

import java.util.ArrayList;

public class GameActivity extends Activity {

    private DrawingService drawingService;
    private GamePanel gamePanel;
    private JoystickPanel joystickPanel;

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
        RelativeLayout gameContainer = findViewById(R.id.container);
        drawingService = DrawingService.getInstance(SortingService.getInstance());
        FigureFactory figureFactory = FigureFactory.getInstance();
        ArrayList<Object3D> objects = new ArrayList<>();
        PersonModel person = new PersonModel(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2,
                0,
                PaintService.createEdgePaint("red"),
                PaintService.createWallPaint("white"),
                1,
                100);
        objects.add(person);

        figureFactory.setFigures(objects);
        //gamePanel = new GamePanel(this, drawingService);
        //gameContainer.addView(gamePanel);

        joystickPanel = new JoystickPanel(
                this,
                drawingService,
                Constants.SCREEN_WIDTH / 4,
                Constants.SCREEN_WIDTH / 4,
                50);
        gameContainer.addView(joystickPanel);

        joystickPanel.getLayoutParams().height = Constants.SCREEN_WIDTH / 2;
        joystickPanel.getLayoutParams().width = Constants.SCREEN_WIDTH / 2;
        RelativeLayout.LayoutParams joystickParams = (RelativeLayout.LayoutParams) joystickPanel.getLayoutParams();
        joystickParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        joystickParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        joystickPanel.setLayoutParams(joystickParams);
        joystickPanel.bringToFront();

    }
}
