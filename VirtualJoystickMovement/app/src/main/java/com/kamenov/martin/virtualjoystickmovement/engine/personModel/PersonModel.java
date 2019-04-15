package com.kamenov.martin.virtualjoystickmovement.engine.personModel;

import android.graphics.Paint;

import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.contracts.Object3D;

public class PersonModel extends Object3D {
    private final float size;

    public PersonModel(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation,
                       float size) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.size = size;
    }
}
