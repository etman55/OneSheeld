package com.example.at_et.onesheeld.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.at_et.onesheeld.Adapters.DetailsAdapter;

import com.example.at_et.onesheeld.R;
import com.example.at_et.onesheeld.Utility.DetailsCallBack;


/**
 * Created by at-et on 10/30/16.
 */

public class DetailsFragment extends Fragment {

    RecyclerView selectedSensors;
    DetailsAdapter detailsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_fragment, container, false);
        selectedSensors = (RecyclerView) rootView.findViewById(R.id.selected_sensors);
        return rootView;
    }


    public void receiveSensorsArray(final String[] sensorsArray) {
        detailsAdapter = new DetailsAdapter(sensorsArray);
        selectedSensors.setAdapter(detailsAdapter);
        selectedSensors.setHasFixedSize(true);
        selectedSensors.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("subscribe array", "" + sensorsArray.length);
        detailsAdapter.setOnDetailsClickListener(new DetailsCallBack() {
            @Override
            public void onItemClick(View itemView, int position) {
                switch (sensorsArray[position]) {
                    case "Accelerometer": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            AccelerometerFragment fragment = new AccelerometerFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }
                        break;
                    }
                    case "Camera": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            CameraFragment fragment = new CameraFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }

                        break;
                    }

                    case "GPS": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            GPSFragment fragment = new GPSFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }
                        break;
                    }

                    case "Gyroscope": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            GyroscopeFragment fragment = new GyroscopeFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }

                        break;
                    }

                    case "Light": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            LightFragment fragment = new LightFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }
                        break;
                    }

                    case "Magnetometer": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            MagnetometerFragment fragment = new MagnetometerFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }

                        break;
                    }

                    case "Mic": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            MicFragment fragment = new MicFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }
                        break;
                    }


                    case "Pressure": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            PressureFragment fragment = new PressureFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }
                        break;
                    }
                    case "Proximity": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            ProximityFragment fragment = new ProximityFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }

                        break;
                    }
                    case "Temperature": {
                        if (getActivity().findViewById(R.id.sensor_fragment) != null) {
                            TemperatureFragment fragment = new TemperatureFragment();
                            fragment.setArguments(getActivity().getIntent().getExtras());
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.sensor_fragment, fragment).commit();
                        }
                        break;
                    }
                    default: {
                        break;
                    }

                }

            }
        });
    }
}
