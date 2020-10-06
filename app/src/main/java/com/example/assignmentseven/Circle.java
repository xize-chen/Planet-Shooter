package com.example.assignmentseven;

public abstract class Circle {
    protected float x, y;
    protected float xVelocity, yVelocity;
    protected int radius;
    protected int color;

    protected Circle () {
        xVelocity = 0;
        yVelocity = 0;
    }
    public abstract void move();
}
