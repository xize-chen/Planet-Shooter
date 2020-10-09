package com.example.assignmentseven;

public class Bouncer extends Circle {

    private float xAcceleration;
    private float h,k;

    public Bouncer(float xB,float yB, int c, float width){
        super();
        x = xB;
        y = yB;
        h = xB;
        k = yB;
        color = c;
        radius = 200;
        boundary = width;
    }
    @Override
    public void move() {
        float interval = 0.4f;
        float xSpeed = xVelocity;
        float acceleration;
        if(x>(boundary*4/7)){
            acceleration = xAcceleration - 2*ACCELERATION;
            if(x>(boundary*5/7))
                acceleration -= 2*ACCELERATION;
        }
        else if(x<(boundary*3/7)){
            acceleration = xAcceleration + 2*ACCELERATION;
            if(x<(boundary*2/7))
                acceleration += 2*ACCELERATION;
        }
        else
            acceleration = xAcceleration;
        float xDistance = (xSpeed + acceleration*interval/2)*interval;
        xVelocity += acceleration*interval;

        x += xDistance;
        y = k - (x-h)*(x-h)/2000;
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

        xAcceleration = -xAcc;
    }



}
