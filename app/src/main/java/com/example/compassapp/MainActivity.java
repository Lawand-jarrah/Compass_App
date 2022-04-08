package com.example.compassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAccelerationValues (View view) {
    Intent intent = new Intent(this, AcceleratorActivity.class);
    startActivity(intent);
    }

    public void showCompassDirection(View view) {
        Intent intent = new Intent(this, CompassActivity.class);
        startActivity(intent);
    }
}

