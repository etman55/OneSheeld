package com.example.at_et.onesheeld.Fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.at_et.onesheeld.R;


/**
 * Created by at-et on 10/30/16.
 */

public class GyroscopeFragment extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;
    TextView valueX, valueY, valueZ, sensorName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sensor_ui, container, false);

        valueX = (TextView) rootView.findViewById(R.id.value_x);
        valueY = (TextView) rootView.findViewById(R.id.value_y);
        valueZ = (TextView) rootView.findViewById(R.id.value_z);
        sensorName = (TextView) rootView.findViewById(R.id.sensor_title);
        sensorName.setText("Gyroscope Sensor");
        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE_UNCALIBRATED) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            valueX.setText(Float.toString(x));
            valueY.setText(Float.toString(y));
            valueZ.setText(Float.toString(z));
        }


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
