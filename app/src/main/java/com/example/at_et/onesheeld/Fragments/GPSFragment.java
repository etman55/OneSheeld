package com.example.at_et.onesheeld.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.at_et.onesheeld.R;
import com.example.at_et.onesheeld.Sensors.GPS;

/**
 * Created by at-et on 10/27/16.
 */

public class GPSFragment extends Fragment {
    double lat, lng;
    TextView latText, lngText;
    GPS tracker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gps_ui, container, false);
        latText = (TextView) rootView.findViewById(R.id.lat);
        lngText = (TextView) rootView.findViewById(R.id.lng);
        tracker = new GPS(getContext());
        if (tracker.isCanGetLocation()) {
            double lat = tracker.getLat();
            double lng = tracker.getLng();
            latText.setText("Latitude \n" + lat);
            lngText.setText("Longitude \n" + lng);
            Log.d("Your location is", "Lat: " + lat + " Log:" + lng);

        } else {
            tracker.showSettingAlert();

        }

        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}