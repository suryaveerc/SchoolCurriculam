package com.conneckto.schoolcurriculam;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.conneckto.schoolcurriculam.Utils.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.buttonStart);
        start.setOnClickListener(new CustomListener(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        }

}
