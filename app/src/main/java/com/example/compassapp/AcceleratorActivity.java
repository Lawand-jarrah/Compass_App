package com.example.compassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import java.util.*;

public class AcceleratorActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerator);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Intent intent = getIntent();
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer,mSensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String[] values = new String[3];

        for (int i = 0; i < values.length; i++) {
            int temp = Math.round(sensorEvent.values[i]);
            values[i] = Integer.toString(temp);
        }

        TextView xAcceleration = findViewById(R.id.x_acceleration);
        TextView yAcceleration = findViewById(R.id.y_acceleration);
        TextView zAcceleration = findViewById(R.id.z_acceleration);

        xAcceleration.setText(values[0]);
        yAcceleration.setText(values[1]);
        zAcceleration.setText(values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}


