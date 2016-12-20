package com.example.at_et.onesheeld.Utility;


import java.util.ArrayList;

/**
 * Created by at-et on 10/27/16.
 */

public class Utility {

    public static final int ACCELEROMETER = 0;
    public static final int CAMERA = 1;
    public static final int GPS = 2;
    public static final int GYROSCOPE = 3;
    public static final int LIGHT = 4;
    public static final int MAGNETOMETER = 5;
    public static final int MIC = 6;
    public static final int PRESSURE = 7;
    public static final int PROXIMITY = 8;
    public static final int TEMPERATURE = 9;

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public static final int MY_PERMISSIONS_CAMERA = 1;
    public static final int MY_PERMISSIONS_MIC = 1;

    public static String[] getSelectedSensors(ArrayList<SensorsModel> models) {
        ArrayList<String> setModels = new ArrayList<String>();
        for (SensorsModel m : models) {
            if (m.isSelected()) {
                setModels.add(m.getSensorName());
            }
        }
        return setModels.toArray(new String[0]);
    }

   public static void setData(ArrayList<SensorsModel> recyclerModels){
       recyclerModels.add(new SensorsModel("Accelerometer", false));
       recyclerModels.add(new SensorsModel("Camera", false));
       recyclerModels.add(new SensorsModel("GPS", false));
       recyclerModels.add(new SensorsModel("Gyroscope", false));
       recyclerModels.add(new SensorsModel("Light", false));
       recyclerModels.add(new SensorsModel("Magnetometer", false));
       recyclerModels.add(new SensorsModel("Mic", false));
       recyclerModels.add(new SensorsModel("Pressure", false));
       recyclerModels.add(new SensorsModel("Proximity", false));
   }



}
