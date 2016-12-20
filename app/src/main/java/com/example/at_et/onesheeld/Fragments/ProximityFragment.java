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

public class ProximityFragment extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;
    TextView titleX, titleY, titleZ, valueY, sensorName;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sensor_ui, container, false);
        titleY = (TextView) rootView.findViewById(R.id.y_title);
        titleX = (TextView) rootView.findViewById(R.id.x_title);
        valueY = (TextView) rootView.findViewById(R.id.value_y);
        titleZ = (TextView) rootView.findViewById(R.id.z_title);
        sensorName = (TextView) rootView.findViewById(R.id.sensor_title);
        titleX.setVisibility(View.INVISIBLE);
        titleZ.setVisibility(View.INVISIBLE);
        titleY.setText("Distance");
        sensorName.setText("Proximity Sensor");
        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float distance = event.values[0];
            valueY.setText(Float.toString(distance));


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
