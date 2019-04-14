package com.kamenov.martin.virtualjoystickmovement.engine.joystickService;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.kamenov.martin.virtualjoystickmovement.constants.Constants;
import com.kamenov.martin.virtualjoystickmovement.engine.GamePanel;
import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.contracts.Object3D;
import com.kamenov.martin.virtualjoystickmovement.engine.services.CanvasService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.DrawingService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.PaintService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.factories.FigureFactory;

import java.util.ArrayList;

public class JoystickPanel extends GamePanel {
    private final JoystickModel joystickModel;
    private float centerX;
    private float centerY;
    private float x1;
    private float y1;
    private float x2;
    private float y2;

    public JoystickPanel(Context context, DrawingService drawingService, float centerX, float centerY) {
        super(context, drawingService);
        x1 = -1;
        x2 = -1;
        this.centerX = centerX;
        this.centerY = centerY;
        joystickModel = new JoystickModel(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2, 50,
                PaintService.createEdgePaint("white"),
                PaintService.createWallPaint("red"));

        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                joystickModel.changePosition(x1, y1);
                draw();
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = event.getX();
                y2 = event.getY();
                joystickModel.changePosition(x2, y2);
                draw();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                joystickModel.changePosition(x2, y2);
                draw();
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        joystickModel.draw(canvas);
    }
}
