package com.example.assignmentseven;

public class Ball extends Circle {
    private static final float GRAVITY = 0.4f;
    private static final int INTERVAL = 100;
    private boolean isStart = false;
    public Ball(float xB, float yB, int c, float b){
        super();
        x = xB;
        y = yB;
        color = c;
        radius = 60;
        boundary = b - radius;
    }

    @Override
    public void move() {
        if(isStart){
            float ySpeed = yVelocity;
            float yDistance = ySpeed + GRAVITY/2;
            yVelocity += GRAVITY;
            float xSpeed = xVelocity;
            float xDistance;
            if(x>(boundary*3/4)){
                xDistance = xSpeed - ACCELERATION/4;
                xVelocity -= ACCELERATION/2;
            }else if(x<(boundary/4)){
                xDistance = xSpeed + ACCELERATION/4;
                xVelocity += ACCELERATION/2;
            }else xDistance = xSpeed;


            x += xDistance;
            y += yDistance;

            rebound(x,y);
        }
    }
    public void turnOn(float xV, float yV){
        if(Math.abs(xV/INTERVAL)>40)
            xVelocity = 40*(xV/Math.abs(xV));
        else xVelocity = xV/INTERVAL;

        if(yV/INTERVAL>-40)
            yVelocity = yV/INTERVAL;
        else yVelocity = -40;
        isStart = true;
    }
    private void rebound(float xCurrent, float yCurrent){
        if(xCurrent < radius){
            x = radius;
            xVelocity = -xVelocity*9/10;
        }
        if (xCurrent > boundary){
            x = boundary;
            xVelocity = -xVelocity*9/10;
        }
        if (yCurrent < radius){
            y = radius;
            yVelocity = -yVelocity*9/10;
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
        yVelocity = 15;
    }
    /*public void touchBouncer(float xBouncer, float yBouncer, int r){
        double angle = Math.atan(Math.abs(yBouncer - y) / Math.abs(xBouncer - x));
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double vC = - (xVelocity * cos + yVelocity * sin);
        double vU = xVelocity * sin + yVelocity * cos;
        double absX = (r + radius) * cos;
        double absY = (r + radius) * sin;
        if (x >= xBouncer)
            x = (float) (xBouncer + absX +10);
        else x = (float) (xBouncer - absX -10);
        if (y >= yBouncer)
            y = (float) (yBouncer + absY +10);
        else y = (float) (yBouncer - absY -10);
        xVelocity = (float) (vC * cos + vU * sin);
        yVelocity = (float) (vU * cos + vC * sin);
        if(-100<xVelocity & xVelocity<100)
            xVelocity*=2;
        if(-100<yVelocity & yVelocity<100)
            yVelocity*=2;
    }*/
    public void touchBouncer (float xBouncer, float yBouncer, int r){
        double distance = Math.hypot((xBouncer - x),(yBouncer - y));
        double Sin = (yBouncer - y) / distance;
        double angle = Math.asin(Math.abs(Sin));
        double sin = Math.abs(Sin);
        double cos = Math.cos(angle);
        double absX = (r + radius) * cos;
        double absY = (r + radius) * sin;
        double vC,vU;
        if (x >= xBouncer)
            x = (float) (xBouncer + absX);
        else x = (float) (xBouncer - absX);
        if (y >= yBouncer)
            y = (float) (yBouncer + absY);
        else y = (float) (yBouncer - absY);
        if(Sin<0){
            if(xBouncer > x){
                if (yVelocity>=0 & xVelocity>0){
                    vC = Math.abs(xVelocity*cos - yVelocity*sin);
                    vU = xVelocity*sin + yVelocity*cos;
                    xVelocity = (float) (vU*sin - vC*cos);
                    yVelocity = (float) (vC*sin + vU*cos);
                }
                if(yVelocity<=0 & xVelocity>=0){
                    vC = xVelocity*cos - yVelocity*sin;
                    vU = Math.abs(yVelocity*cos + xVelocity*sin);
                    if(-yVelocity*cos > xVelocity*sin){
                        xVelocity = (float) -(vU*sin + vC*cos);
                        yVelocity = (float) (vC*sin - vU*cos);
                    }
                    else {
                        xVelocity = (float) (vU*sin - vC*cos);
                        yVelocity = (float) (vU*cos + vC*sin);
                    }
                }
                if (xVelocity<=0 & yVelocity<0){
                    vC = Math.abs(xVelocity*cos - yVelocity*sin);
                    vU = Math.abs(yVelocity*cos + xVelocity*sin);
                    xVelocity = (float) -(vU*sin + vC*cos);
                    yVelocity = (float) -(vU*cos - vC*sin);
                }
            }
            else {
                if(yVelocity<0 & xVelocity>=0){
                    vC = Math.abs(xVelocity*cos + yVelocity*sin);
                    vU = xVelocity*sin - yVelocity*cos;
                    xVelocity = (float) (vU*sin + vC*cos);
                    yVelocity = (float) -(vU*cos - vC*sin);
                }
                if(yVelocity<=0 & xVelocity<=0){
                    vC = -(yVelocity *sin + xVelocity*cos);
                    vU = Math.abs(yVelocity*cos- xVelocity*sin);
                    if(yVelocity*cos < xVelocity*sin){
                        xVelocity = (float) (vU*sin + vC*cos);
                        yVelocity = (float) (vC*sin -vU*cos);
                    }
                    else {
                        yVelocity = (float) (vU *cos + vC*sin);
                        xVelocity = (float) (vC*cos - vU*sin);
                    }
                }
                if(yVelocity>=0 & xVelocity<0){
                    vC = Math.abs(xVelocity*cos + yVelocity*sin);
                    vU = -(xVelocity*sin) + yVelocity*cos;
                    xVelocity = (float) -(vU*sin - vC*cos);
                    yVelocity = (float) (vU*cos + vC*sin);
                }
            }
        }else {
            if(xBouncer > x){
                if(yVelocity<=0 & xVelocity>0){
                    vU = xVelocity*sin - yVelocity*cos;
                    vC = Math.abs(yVelocity*sin + xVelocity*cos);
                    yVelocity = (float) -(vC*sin + vU*cos);
                    xVelocity = (float) (vU*sin - vC*cos);
                }
                if(xVelocity>=0 & yVelocity>=0){
                    vC = xVelocity*cos + yVelocity*sin;
                    vU = Math.abs(xVelocity*sin - yVelocity*cos);
                    if(xVelocity*sin > yVelocity*cos){
                        yVelocity = (float) -(vC*sin + vU*cos);
                        xVelocity = (float) (vU*sin - vC*cos);
                    }
                    else {
                        xVelocity = (float) -(vC*cos + vU*sin);
                        yVelocity = (float) (vU*cos - vC*sin);
                    }
                }
                if(yVelocity>0 &xVelocity<=0){
                    vU = yVelocity*cos - xVelocity*sin;
                    vC = Math.abs(yVelocity*sin + xVelocity*cos);
                    yVelocity = (float) (vU*cos - vC*sin);
                    xVelocity = (float) -(vU*sin + vC*cos);
                }
            }else {
                if(xVelocity<0 & yVelocity<=0){
                    vU = -(xVelocity*sin + yVelocity*cos);
                    vC = Math.abs(xVelocity*cos - yVelocity*sin);
                    yVelocity = (float) -(vC*sin + vU*cos);
                    xVelocity = (float) (vC*cos - vU*sin);
                }
                if(xVelocity<=0 & yVelocity>=0){
                    vC = yVelocity*sin - xVelocity*cos;
                    vU = Math.abs(yVelocity*cos + xVelocity*sin);
                    if(Math.abs(xVelocity*sin) > yVelocity*cos){
                        yVelocity = (float) -(vU*cos + vC*sin);
                        xVelocity = (float) (vC*cos - vU*sin);
                    }
                    else {
                        xVelocity = (float) (vU*sin + vC*cos);
                        yVelocity = (float) (vU*cos - vC*sin);
                    }
                }
                if(xVelocity>=0 & yVelocity>0){
                    vU = xVelocity*sin + yVelocity*cos;
                    vC = Math.abs(xVelocity*cos - yVelocity*sin);
                    xVelocity = (float) (vC*cos + vU*sin);
                    yVelocity = (float) (vU*cos - vC*sin);
                }
            }
        }
    }
}
