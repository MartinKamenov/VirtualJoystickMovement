package com.kamenov.martin.virtualjoystickmovement.engine.joystickService;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.kamenov.martin.virtualjoystickmovement.engine.GamePanel;
import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.contracts.MovingDirection;
import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.contracts.Object3D;
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
    public float speedCoef = 0.1f;

    public JoystickPanel(Context context, DrawingService drawingService, float centerX, float centerY, float size) {
        super(context, drawingService);
        this.figures = new ArrayList<>();
        this.size = size;
        this.centerX = centerX;
        this.centerY = centerY;
        x1 = centerX;
        y1 = centerY;

        joystickModel = new JoystickModel(centerX,
                centerY, centerX, centerY, 50,
                PaintService.createEdgePaint("white"),
                PaintService.createWallPaint("red"));

        getHolder().addCallback(this);

        setFocusable(true);
    }

    public void addMovableObjects(ArrayList<Object3D> figures) {
        this.figures = figures;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if(isMotionOutOfBounds(x1, y1)) {
                    return true;
                }
                x1 = event.getX();
                y1 = event.getY();
                joystickModel.changePosition(x1, y1);
                draw();
                break;
            case MotionEvent.ACTION_UP:
                x1 = centerX;
                y1 = centerY;
                joystickModel.changePosition(x1, y1);
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
    public void update() {
        float diffX = centerX - x1;
        float diffY = centerY - y1;
        if(diffX == 0 && diffY == 0) {
            return;
        }

        move(speedCoef * diffX, MovingDirection.Left);
        move(speedCoef * diffY, MovingDirection.Up);
    }

    public void move(float pixels, MovingDirection movingDirection) {
        for(int i = 0; i < figures.size(); i++) {
            Object3D movingObject = figures.get(i);
            move(movingObject, pixels, movingDirection);
        }
    }

    public void move(Object3D movingObject, float pixels, MovingDirection movingDirection) {
        movingObject.move(pixels, movingDirection);
        movingObject.setDrawingParts();
    }

    private boolean isMotionOutOfBounds(float x, float y) {
        float diffX = Math.abs(x - centerX);
        float diffY = Math.abs(y - centerY);

        float distance = (float) Math.sqrt(diffX * diffX + diffY * diffY);
        return distance > this.size * 2;
    }
}
