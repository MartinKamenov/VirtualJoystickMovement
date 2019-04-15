package com.kamenov.martin.virtualjoystickmovement.engine.personModel;

import android.graphics.Paint;

import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.ComplexObject;
import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.Cube;
import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.Parallelepiped;
import com.kamenov.martin.virtualjoystickmovement.engine.models.game_objects.contracts.Object3D;
import com.kamenov.martin.virtualjoystickmovement.engine.services.factories.FigureFactory;

import java.util.ArrayList;

public class PersonModel extends Object3D {
    private final float size;

    public PersonModel(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation,
                       float size) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.size = size;

        float bodyWidth = size / 3;
        float bodyHeight = size / 2;
        float bodyDepth = size / 5;
        float armHeight = size / 3 * 2;
        float armWidth = bodyWidth / 5;
        float armDepth = bodyDepth / 3;
        float legHeight = bodyHeight;
        float legWidth = bodyWidth / 3;
        float legDepth = bodyDepth / 3 * 2;
        float headLength = bodyHeight / 4;
        ArrayList<Object3D> objects = new ArrayList<>();
        Parallelepiped body = new Parallelepiped(x, y, z,
                bodyWidth,
                bodyHeight,
                bodyDepth,
                edgePaint, wallPaint,
                rotation);
        Parallelepiped leftArm = new Parallelepiped(
                x - bodyWidth / 2 - armWidth / 2,
                y + (Math.abs(armHeight - bodyHeight) / 2),
                z,
                armWidth,
                armHeight,
                armDepth,
                edgePaint, wallPaint,
                rotation);
        Parallelepiped rightArm = new Parallelepiped(
                x + bodyWidth / 2 + armWidth / 2,
                y + (Math.abs(armHeight - bodyHeight) / 2),
                z,
                armWidth,
                armHeight,
                armDepth,
                edgePaint, wallPaint,
                rotation);
        Parallelepiped leftLeg = new Parallelepiped(
                x - legWidth / 2,
                y + bodyHeight / 2 + legHeight / 2,
                z,
                legWidth,
                legHeight,
                legDepth,
                edgePaint, wallPaint,
                rotation);
        Parallelepiped rightLeg = new Parallelepiped(
                x + legWidth / 2,
                y + bodyHeight / 2 + legHeight / 2,
                z,
                legWidth,
                legHeight,
                legDepth,
                edgePaint, wallPaint,
                rotation);
        Cube head = new Cube(
                x,
                y - bodyHeight / 2 - headLength / 2,
                z,
                headLength,
                edgePaint, wallPaint,
                rotation);
        objects.add(body);
        objects.add(leftArm);
        objects.add(rightArm);
        objects.add(leftLeg);
        objects.add(rightLeg);
        objects.add(head);

        ComplexObject overallObject = new ComplexObject(x, y, z, edgePaint,
                wallPaint, rotation, objects);

        this.points = overallObject.points;
        this.parts = overallObject.parts;
        setDrawingParts();
    }
}
