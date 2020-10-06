package com.example.assignmentseven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Target target;
    private Orbiter[] orbiters = new Orbiter[2];
    private ArrayList<Circle> objects;
    private float x,y;

    public class GraphicView extends View{
        Paint paint = new Paint();

        public GraphicView(Context context) {
            super(context);
            x = (float) (this.getResources().getDisplayMetrics().widthPixels/2.0);
            y = (float) (this.getResources().getDisplayMetrics().heightPixels/5.0);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(Circle object : objects){
                paint.setColor(getColor(object.color));
                canvas.drawCircle(object.x, object.y, object.radius, paint);
                object.move();
            }
            invalidate();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.cl_main);
        layout.addView(new GraphicView(this));
        setup();

    }
    private void setup() {
        objects = new ArrayList<Circle>();
        target = new Target(x,y,R.color.colorPrimary);
        objects.add(target);
        orbiters[0] = new Orbiter(x,y,R.color.colorAccent);
        objects.add(orbiters[0]);
        orbiters[1] = new Orbiter(x,y,R.color.colorAccent);
        objects.add(orbiters[1]);
    }
}