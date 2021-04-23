package com.example.myapplication;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityGame extends AppCompatActivity implements SensorEventListener {
    private Handler mHandler = new Handler();
    public static int i = 0;
    private View a;
    public SensorManager sensorManager;
    public Sensor sensor;
    public static float[] gravity = new float[3];
    public static float[] linear_acceleration = new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_game);
        a= (View) findViewById(R.id.viewGame);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                Sensor.TYPE_ACCELEROMETER);

        DrawGame.currentLevel= ActivityListLevel.listLevel.get(ActivityListLevel.currentLevel);
    }
    public void onSensorChanged(SensorEvent event){
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        double alpha = 0.1;
        i = i+1;
        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = (float)(alpha) * gravity[0] + (1 - (float)(alpha)) * event.values[0];
        gravity[1] = (float)(alpha) * gravity[1] + (1 - (float)(alpha)) * event.values[1];
        gravity[2] = (float)(alpha) * gravity[2] + (1 - (float)(alpha)) * event.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];
        a.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}