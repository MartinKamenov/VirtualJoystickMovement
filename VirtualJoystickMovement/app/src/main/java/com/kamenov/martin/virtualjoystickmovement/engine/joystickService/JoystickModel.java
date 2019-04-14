package com.kamenov.martin.virtualjoystickmovement.engine.joystickService;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.contracts.GameObject;

public class JoystickModel implements GameObject {
    private final Paint edgePaint;
    private final Paint wallPaint;
    private final float centerX;
    private final float centery;
    private float x;
    private float y;
    private float size;

    public JoystickModel(float x, float y,
                         float centerX, float centerY,
                         float size, Paint edgePaint, Paint wallPaint) {
        this.x = x;
        this.y = y;
        this.centerX = centerX;
        this.centery = centerY;
        this.size = size;
        this.edgePaint = edgePaint;
        this.wallPaint = wallPaint;
    }

    @Override
    public void update() {

    }

    public void changePosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(centerX, centery, size * 3 / 2,wallPaint);
        canvas.drawCircle(x, y, size, wallPaint);
        canvas.drawCircle(x, y, size, edgePaint);
    }
}
