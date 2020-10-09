package com.example.assignmentseven;

public abstract class Circle {
    protected static final float ACCELERATION = 0.2f;
    protected float x, y;
    protected float xVelocity, yVelocity;
    protected int radius;
    protected int color;
    protected float boundary;

    protected Circle () {
        xVelocity = 0;
        yVelocity = 0;
        boundary = 0;
    }
    public abstract void move();
}
