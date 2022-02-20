package com.example.parte2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor light;
    private Sensor gyro;
    private TextView lightSensorData;
    private TextView gyroTitle, gyroX, gyroY, gyroZ;
    private GPSTracker gpsTracker;
    private Button fetchCoordinatesBtn;
    private TextView latText, longText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightSensorData = (TextView) findViewById(R.id.lightTextView);
        gyroTitle = (TextView) findViewById(R.id.gyroTextView);
        gyroX = (TextView) findViewById(R.id.gyroX);
        gyroY = (TextView) findViewById(R.id.gyroY);
        gyroZ = (TextView) findViewById(R.id.gyroZ);
        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        gpsTracker = new GPSTracker(getApplicationContext());
        fetchCoordinatesBtn = (Button) findViewById(R.id.showCoords);
        latText = (TextView) findViewById(R.id.latText);
        longText = (TextView) findViewById(R.id.longText);

        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        fetchCoordinatesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location l = gpsTracker.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double longi = l.getLongitude();

                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "\nLONG: " + longi, Toast.LENGTH_LONG).show();
                    longText.setText("Longitude: " + String.valueOf(longi));
                    latText.setText("Latitude: " + String.valueOf(lat));
                }
            }
        });

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null)
        {
            sensorManager.registerListener(MainActivity.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else
        {
            lightSensorData.setText("Light sensor not supported");
        }

        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyro != null) {
            sensorManager.registerListener(MainActivity.this, gyro,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            gyroTitle.setText("Gyroscope not supported");
        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                lightSensorData.setText("Light Intensity: " + event.values[0]);
                break;

            case Sensor.TYPE_GYROSCOPE:
                gyroX.setText("X " + event.values[0]);
                gyroY.setText("Y " + event.values[1]);
                gyroZ.setText("Z " + event.values[2]);
                break;
        }
    }
}