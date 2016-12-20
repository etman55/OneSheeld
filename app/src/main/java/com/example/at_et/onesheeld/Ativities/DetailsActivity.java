package com.example.at_et.onesheeld.Ativities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.at_et.onesheeld.Fragments.AccelerometerFragment;
import com.example.at_et.onesheeld.Fragments.CameraFragment;
import com.example.at_et.onesheeld.Fragments.DetailsFragment;
import com.example.at_et.onesheeld.Fragments.GPSFragment;
import com.example.at_et.onesheeld.Fragments.GyroscopeFragment;
import com.example.at_et.onesheeld.Fragments.LightFragment;
import com.example.at_et.onesheeld.Fragments.MagnetometerFragment;
import com.example.at_et.onesheeld.Fragments.MicFragment;
import com.example.at_et.onesheeld.Fragments.PressureFragment;
import com.example.at_et.onesheeld.Fragments.ProximityFragment;
import com.example.at_et.onesheeld.Fragments.TemperatureFragment;
import com.example.at_et.onesheeld.R;

import com.example.at_et.onesheeld.Utility.DetailsCallBack;

public class DetailsActivity extends AppCompatActivity implements DetailsCallBack {
    String[] selectedSensorsArray = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle received = getIntent().getExtras();
        if (received != null) {
            selectedSensorsArray = received.getStringArray("checked");
            DetailsFragment fragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_list_fragment);
            fragment.receiveSensorsArray(selectedSensorsArray);

        }
    }

    @Override
    public void onItemClick(View itemView, int position) {

    }
}
