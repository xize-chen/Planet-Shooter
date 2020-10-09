package com.example.assignmentseven;

public class Bouncer extends Circle {

    private float xAcceleration;

    public Bouncer(float xB,float yB, int c, float width){
        super();
        x = xB;
        y = yB;
        color = c;
        radius = 200;
        boundary = width;
    }
    @Override
    public void move() {
        float interval = 0.4f;
        float xSpeed = xVelocity;
        float acceleration;
        if(x>(boundary*2/3))
            acceleration = xAcceleration + ACCELERATION;
        else if(x<(boundary/3))
            acceleration = xAcceleration - ACCELERATION;
        else
            acceleration = xAcceleration;
        float xDistance = (xSpeed + acceleration*interval/2)*interval;
        xVelocity -= acceleration*interval;
        //float xDistance = (xSpeed + xAcceleration*interval/2)*interval;
        x += xDistance;
        if(x<=radius){
            x=radius;
            xVelocity = -xVelocity/3;
        }
        if(x>=boundary-radius){
            x=boundary-radius;
            xVelocity = -xVelocity/3;
        }
    }

    public void update(float xAcc){
        xAcceleration = xAcc;
    }



}
