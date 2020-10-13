package com.example.assignmentseven;

public class Orbiter extends Circle {
    private float xCenter, yCenter;
    private int degree;
    private static int index = 0;
    public Orbiter(float xC, float yC, int c){
        super();
        radius = 30;
        color = c;
        xCenter = xC;
        yCenter = yC;
        if (index == 0){
            degree = 0;
            index++;
        }else {
            degree = 180;
            index--;
        }
        x = (float) (Math.cos(angle(degree)) * 140 + xCenter);
        y = (float) (Math.sin(angle(degree)) * 140 + yCenter);
    }
    @Override
    public void move(){
        degree++;
        x = (float) (Math.cos(angle(degree)) * 140 + xCenter);
        y = (float) (Math.sin(angle(degree)) * 140 + yCenter);
        if(degree == 360)
            degree = 0;
    }
    private double angle (double xC){
        double PERIOD = 360;
        double portion = (xC % PERIOD) / PERIOD;
        return (portion * 2 * Math.PI);
    }
}
