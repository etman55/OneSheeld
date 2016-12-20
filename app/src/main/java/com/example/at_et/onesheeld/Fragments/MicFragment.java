package com.example.at_et.onesheeld.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.at_et.onesheeld.R;
import com.example.at_et.onesheeld.Sensors.MicObj;

/**
 * Created by at-et on 10/27/16.
 */

public class MicFragment extends Fragment {
    private MicObj mSensor;
    private Handler handler;
    TextView titleX, titleY, titleZ, valueY, sensorName;
    double volume;

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
        sensorName.setText("Mic");
        titleY.setText("Noise");
        mSensor = new MicObj();
        try {
            mSensor.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        final Runnable r = new Runnable() {

            public void run() {
                Log.d("Amplify", "HERE");
                // Get the volume from 0 to 255 in 'int'
                volume = 10 * mSensor.getTheAmplitude() / 32768;
                Log.d("volume", "" + volume);
                valueY.setText(Double.toString(volume));
                handler.postDelayed(this, 50); // amount of delay between every cycle of volume level detection + sending the data  out

            }
        };
        handler = new Handler();
        handler.postDelayed(r, 50);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        mSensor.stop();
    }
}
