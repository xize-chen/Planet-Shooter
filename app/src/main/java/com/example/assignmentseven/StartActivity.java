package com.example.assignmentseven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
    }

    public void onclickStart(View v){
        Intent startGame = new Intent(this,MainActivity.class);
        startActivity(startGame);
    }
    public void onclickScore(View v){
        Intent startScore = new Intent(this, ScoreActivity.class);
        startActivity(startScore);
    }
}