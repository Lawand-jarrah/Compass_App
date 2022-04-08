package com.example.compassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class    CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mOrientation;
    private Vibrator vib;
    private ImageView compassImage;
    private float degreeStart = 0f;
    private TextView DegreeTV;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        compassImage = (ImageView) findViewById(R.id.compass_image);
        DegreeTV = (TextView) findViewById(R.id.DegreeTV);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Intent intent = getIntent();
        mp = MediaPlayer.create(this, R.raw.eagle_cry_sound);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mOrientation, mSensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        vib.cancel();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent != null) {
            // get angle around the z-axis rotated
            float degree = Math.round(sensorEvent.values[0]);
            if(degree >= 345.0f || degree <= 15.0f) {
                mp.start();
                vib.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                DegreeTV.setTextColor(Color.CYAN);
            }
            DegreeTV.setText("Heading: " + Float.toString(degree) + " degrees");
            // rotation animation - reverse turn degree degrees
            RotateAnimation ra = new RotateAnimation(
                    degreeStart,
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            // set the compass animation after the end of the reservation status
            ra.setFillAfter(true);
            // set how long the animation for the compass image will take place
            ra.setDuration(210);
            // Start animation of compass image
            compassImage.startAnimation(ra);
            degreeStart = -degree;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}