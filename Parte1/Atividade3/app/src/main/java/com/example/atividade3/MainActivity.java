package com.example.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private EditText x;
    private EditText y;
    private EditText z;
    private float lastSensorX = 0.0f;
    private float lastSensorY = 0.0f;
    private float lastSensorZ = 0.0f;
    private boolean firstTime = true;
    private final float MAX_DIFF = 10.0f;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (EditText) findViewById(R.id.editText1);
        y = (EditText) findViewById(R.id.editText2);
        z = (EditText) findViewById(R.id.editText3);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];
            x.setText("X " + String.valueOf(sensorX));
            y.setText("Y " + String.valueOf(sensorY));
            z.setText("Z " + String.valueOf(sensorZ));

            if (!firstTime && (Math.abs(sensorX - lastSensorX) > MAX_DIFF
                || Math.abs(sensorY - lastSensorY) > MAX_DIFF
                || Math.abs(sensorZ - lastSensorZ) > MAX_DIFF)) {

                Log.d("X info", "X: " + sensorX + " - "  + lastSensorX);
                Log.d("Y info", "Y: " + sensorY + " - "  + lastSensorY);
                Log.d("Z info", "Z: " + sensorZ + " - "  + lastSensorZ);
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }

            lastSensorX = sensorX;
            lastSensorY = sensorY;
            lastSensorZ = sensorZ;
            firstTime = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }
}