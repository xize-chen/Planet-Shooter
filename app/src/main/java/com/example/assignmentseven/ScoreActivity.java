package com.example.assignmentseven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        String[] output = Scoreboard.getInstance().printout();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_text, R.id.lise_content,output);
        ListView listView = (ListView) findViewById(R.id.ranking);
        listView.setAdapter(arrayAdapter);
    }
    public void onclickBack(View v){
        Intent startBack = new Intent(this, StartActivity.class);
        startActivity(startBack);
    }
}