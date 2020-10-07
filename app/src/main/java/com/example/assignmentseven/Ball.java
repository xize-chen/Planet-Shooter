package com.example.assignmentseven;

public class Ball extends Circle {
    private static final float GRAVITY = 10;
    private static final int INTERVAL = 100;
    private boolean isStart = false;
    private float boundary;
    public Ball(float xB, float yB, int c, float b){
        super();
        x = xB;
        y = yB;
        color = c;
        radius = 50;
        boundary = b - radius;
    }

    @Override
    public void move() {
        if(isStart){
            x += xVelocity/INTERVAL;
            y += yVelocity/INTERVAL;
            yVelocity += GRAVITY;
            rebound(x,y);
        }
    }
    public void turnOn(float xV, float yV){
        xVelocity = xV;
        if(yV>-3000)
            yVelocity = yV;
        else yVelocity = -3000;
        isStart = true;
    }
    private void rebound(float xCurrent, float yCurrent){
        if(xCurrent < radius){
            x = radius;
            xVelocity = -xVelocity*8/10;
        }
        if (xCurrent > boundary){
            x = boundary;
            xVelocity = -xVelocity*8/10;
        }
        if (yCurrent < radius){
            y = radius;
            yVelocity = -yVelocity*8/10;
        }

    }
    public void delete(){
        x = -500;
        y = -500;
        xVelocity = 0;
        yVelocity = 0;
    }
    public void moveOut(float height){
        y = (float) (1.5*height);
        yVelocity = 400;
    }
    public void touchBouncer(float xBouncer, float yBouncer){
        double angle = Math.atan(Math.abs(yBouncer - y) / Math.abs(xBouncer - x));
        double vC = - (xVelocity * Math.cos(angle) + yVelocity * Math.sin(angle));
        double vU = xVelocity * Math.sin(angle) + yVelocity * Math.cos(angle);
        xVelocity = (float) (vC * Math.cos(angle) + vU*Math.sin(angle));
        yVelocity = (float) (vU * Math.cos(angle) + vC*Math.sin(angle));
    }
}
