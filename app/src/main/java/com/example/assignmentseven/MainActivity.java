package com.example.assignmentseven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Target target;
    private Ball ball;
    private Bouncer bouncer;
    private Orbiter[] orbiters = new Orbiter[2];
    private ArrayList<Circle> objects;
    private float width, height;
    private float xTarget, yTarget;
    private float xStart, yStart;
    private boolean flag = true;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private int counter;
    private int rounds;
    private TextView scorePanel;
    private boolean isStart = true;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            bouncer.update(sensorEvent.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) { }
    };


    public class GraphicView extends View{
        Paint paint = new Paint();
        GestureDetector detector;

        public GraphicView(Context context) {
            super(context);
            detector = new GestureDetector(context, new myGestureListener());
            width = (float) (this.getResources().getDisplayMetrics().widthPixels);
            height = (float) (this.getResources().getDisplayMetrics().heightPixels);
            xTarget = width/2;
            yTarget = height/6;
            xStart = width/2;
            yStart = height*19/20;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(Circle object : objects){
                paint.setColor(getColor(object.color));
                canvas.drawCircle(object.x, object.y, object.radius, paint);
                object.move();
                checkState();
            }
            if(isStart)
                invalidate();
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if(detector.onTouchEvent(event)){
                return true;
            }
            return super.onTouchEvent(event);

        }
        class myGestureListener extends GestureDetector.SimpleOnGestureListener{
            @Override
            public boolean onDown(MotionEvent e) {
                return isBallClicked(e.getX(), e.getY());
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(velocityY < -250){
                    ball.x = e1.getX();
                    ball.turnOn(velocityX,velocityY);
                    flag = false;
                }
                return true;
            }
            @Override
            public boolean onScroll (MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
                if(flag){
                    if(isBallClicked(e2.getX(),e2.getY()))
                        ball.x -= distanceX;
                }
                return true;
            }
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        ConstraintLayout layout = findViewById(R.id.cl_main);
        layout.addView(new GraphicView(this));
        scorePanel = (TextView) findViewById(R.id.scorePanel);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setup();

    }
    @Override
    protected void onResume() {
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        super.onResume();
        sensorManager.registerListener(sensorEventListener,accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener,accelerometer);
    }
    private void setup() {
        objects = new ArrayList<>();
        target = new Target(xTarget, yTarget,R.color.color3);
        objects.add(target);
        orbiters[0] = new Orbiter(xTarget, yTarget,R.color.color1);
        objects.add(orbiters[0]);
        orbiters[1] = new Orbiter(xTarget, yTarget,R.color.color1);
        objects.add(orbiters[1]);
        bouncer = new Bouncer(xTarget,yTarget*4,R.color.color2,width);
        objects.add(bouncer);
        ball = new Ball(xStart, yStart, R.color.color3,width);
        objects.add(ball);
        counter = 0;
        scorePanel.setText(Integer.toString(counter));
        rounds = 10;
    }
    private boolean isBallClicked(float x, float y){
        float yD = Math.abs(y - ball.y);
        float xD = Math.abs(x - ball.x);
        return (Math.hypot(yD, xD) < (ball.radius +60));
    }
    private boolean isCollision(Circle object){
        float yD = Math.abs(object.y - ball.y);
        float xD = Math.abs(object.x - ball.x);
        return (Math.hypot(yD, xD) <= (ball.radius + object.radius));
    }
    private void checkState(){
        if(ball.y > height*2){
            if(--rounds == 0){
                isStart = false;
                Scoreboard scoreboard = Scoreboard.getInstance();
                scoreboard.update(counter);
                Intent startScore = new Intent(this, ScoreActivity.class);
                startActivity(startScore);
            }else
                reStart();
        }

        if(isCollision(orbiters[0]))
            ball.moveOut(height);
        if(isCollision(orbiters[1]))
            ball.moveOut(height);
        if(isCollision(bouncer))
            ball.touchBouncer(bouncer.x,bouncer.y, bouncer.radius);
        if(isCollision(target)){
            counter++;
            scorePanel.setText(Integer.toString(counter));
            ball.moveOut(height);
        }
    }
    private void reStart(){
        ball.delete();
        objects.remove(ball);
        ball = new Ball(xStart, yStart, R.color.color3,width);
        objects.add(ball);
        flag = true;
    }
}