package com.kamenov.martin.virtualjoystickmovement.engine.joystickService;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.kamenov.martin.virtualjoystickmovement.engine.GamePanel;
import com.kamenov.martin.virtualjoystickmovement.engine.services.DrawingService;
import com.kamenov.martin.virtualjoystickmovement.engine.services.PaintService;

import java.util.ArrayList;

public class JoystickPanel extends GamePanel {
    private final JoystickModel joystickModel;
    private final float size;
    private float centerX;
    private float centerY;
    private float x1;
    private float y1;

    public JoystickPanel(Context context, DrawingService drawingService, float centerX, float centerY, float size) {
        super(context, drawingService);
        this.figures = new ArrayList<>();
        x1 = -1;
        y1 = -1;
        this.size = size;
        this.centerX = centerX;
        this.centerY = centerY;
        joystickModel = new JoystickModel(centerX,
                centerY, centerX, centerY, 50,
                PaintService.createEdgePaint("white"),
                PaintService.createWallPaint("red"));

        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                x1 = event.getX();
                y1 = event.getY();
                if(isMotionOutOfBounds(x1, y1)) {
                    return true;
                }
                joystickModel.changePosition(x1, y1);
                draw();
                break;
            case MotionEvent.ACTION_UP:
                joystickModel.changePosition(centerX, centerY);
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

    @Override
    public void update() {}

    private boolean isMotionOutOfBounds(float x, float y) {
        float diffX = Math.abs(x - centerX);
        float diffY = Math.abs(y - centerY);

        float distance = (float) Math.sqrt(diffX * diffX + diffY * diffY);
        return distance > this.size * 2;
    }
}
