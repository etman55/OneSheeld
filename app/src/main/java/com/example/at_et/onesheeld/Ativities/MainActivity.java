package com.example.at_et.onesheeld.Ativities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.at_et.onesheeld.Adapters.ContentAdapter;
import com.example.at_et.onesheeld.Utility.OnItemClickListener;
import com.example.at_et.onesheeld.R;
import com.example.at_et.onesheeld.Utility.SensorsModel;
import com.example.at_et.onesheeld.Utility.Utility;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    RecyclerView recyclerView;
    private CheckBox selected;
    private ArrayList<SensorsModel> modelArrayList = new ArrayList<>();
    private ContentAdapter contentAdapter;
    private String[] sensorsArray = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button detailsActivity = (Button) findViewById(R.id.details_activity);
        recyclerView = (RecyclerView) findViewById(R.id.sensors_list);
        if (savedInstanceState != null) {
            modelArrayList = savedInstanceState.getParcelableArrayList("model list");
            recyclerView.setHasFixedSize(true);
            // Attach the layout manager to the recycler view
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            contentAdapter = new ContentAdapter(this, modelArrayList);
            recyclerView.setAdapter(contentAdapter);
        } else {
            Utility.setData(modelArrayList);
            recyclerView.setHasFixedSize(true);
            // Attach the layout manager to the recycler view
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            contentAdapter = new ContentAdapter(this, modelArrayList);
            recyclerView.setAdapter(contentAdapter);
        }
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        contentAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                selected = (CheckBox) itemView.findViewById(R.id.chk_item);
                if (selected.isChecked()) {
                    selected.setChecked(false);
                } else {
                    selected.setChecked(true);
                }
                switch (position) {
                    case Utility.ACCELEROMETER: {
                        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                        if (mSensor == null) {
                            Toast.makeText(MainActivity.this, "Device Doesn't support this sensor", Toast.LENGTH_SHORT).show();
                            selected.setChecked(false);
                        }
                        break;
                    }
                    case Utility.CAMERA: {
                        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED
                                || ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    Utility.MY_PERMISSIONS_CAMERA);

                            return;
                        }
                        break;
                    }

                    case Utility.GPS: {
                        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    Utility.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                            return;
                        }
                        break;
                    }

                    case Utility.GYROSCOPE: {
                        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
                        if (mSensor == null) {
                            Toast.makeText(MainActivity.this, "Device Doesn't support this sensor", Toast.LENGTH_SHORT).show();
                            selected.setChecked(false);
                        }
                        break;
                    }

                    case Utility.LIGHT: {
                        break;
                    }

                    case Utility.MAGNETOMETER: {
                        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                        if (mSensor == null) {
                            Toast.makeText(MainActivity.this, "Device Doesn't support this sensor", Toast.LENGTH_SHORT).show();
                            selected.setChecked(false);
                        }
                        break;
                    }

                    case Utility.MIC: {

                        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.RECORD_AUDIO)
                                != PackageManager.PERMISSION_GRANTED
                                || ActivityCompat.checkSelfPermission(MainActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    Utility.MY_PERMISSIONS_MIC);

                            return;
                        }
                        break;
                    }


                    case Utility.PRESSURE: {
                        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
                        if (mSensor == null) {
                            Toast.makeText(MainActivity.this, "Device Doesn't support this sensor", Toast.LENGTH_SHORT).show();
                            selected.setChecked(false);

                        }
                        break;
                    }
                    case Utility.PROXIMITY: {
                        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                        if (mSensor == null) {
                            Toast.makeText(MainActivity.this, "Device Doesn't support this sensor", Toast.LENGTH_SHORT).show();
                            selected.setChecked(false);
                        }
                        break;
                    }
                    case Utility.TEMPERATURE: {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                            if (mSensor == null) {
                                Toast.makeText(MainActivity.this, "Device Doesn't support this sensor", Toast.LENGTH_SHORT).show();
                                selected.setChecked(false);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Device Doesn't support this sensor", Toast.LENGTH_SHORT).show();
                            selected.setChecked(false);
                        }
                        break;
                    }
                    default: {
                        break;
                    }

                }
            }

        });

        detailsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorsArray = Utility.getSelectedSensors(modelArrayList);
                if (sensorsArray.length > 0) {
                    Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
//                   sendSelectedSensors.sendSensorsArray(sensorsArray);
                    detailsIntent.putExtra("checked", sensorsArray);
                    startActivity(detailsIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Check at least one sensor", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == Utility.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read GPS Location", Toast.LENGTH_LONG).show();
            } else {
                selected.setChecked(false);
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "this sensor required permission", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("model list", modelArrayList);

    }
}
