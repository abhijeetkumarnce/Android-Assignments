package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class ProximitySensor extends AppCompatActivity {
    TextView tv;
    SensorManager sm;
    Sensor s;
    SensorEventListener sel=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x=sensorEvent.values[0];
            if(x<s.getMaximumRange())
            {
                tv.setText("NEAR");
            }
            else
            {
                tv.setText("FAR");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);
        sm=(SensorManager) getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        tv=findViewById(R.id.textView4);
        sm.registerListener(sel,s,SensorManager.SENSOR_DELAY_NORMAL);
    }
}