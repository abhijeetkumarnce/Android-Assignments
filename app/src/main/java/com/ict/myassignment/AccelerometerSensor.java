package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerSensor extends AppCompatActivity {
    TextView tv;
    SensorManager sm;
    Sensor s;
    SensorEventListener sel=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x=sensorEvent.values[0];
            float y=sensorEvent.values[1];
            float z=sensorEvent.values[2];
            tv.setText("AZIMUTH : "+x+"\n PITCH : "+y+"\n ROLL : "+z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_sensor);
        sm=(SensorManager) getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        tv=findViewById(R.id.textView);
        sm.registerListener(sel,s,SensorManager.SENSOR_DELAY_NORMAL);
    }
}