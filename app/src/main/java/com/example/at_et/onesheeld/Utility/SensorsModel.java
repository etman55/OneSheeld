package com.example.at_et.onesheeld.Utility;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by at-et on 12/2/16.
 */

public class SensorsModel implements Parcelable {
    private String sensorName;
    private boolean isSelected;

    private SensorsModel(Parcel in) {
        sensorName = in.readString();
        isSelected = in.readInt() == 1;
    }

    public SensorsModel(String sensorName, boolean isSelected) {
        this.sensorName = sensorName;
        this.isSelected = isSelected;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sensorName);
        parcel.writeInt(isSelected ? 1 : 0);
    }

    public static final Creator<SensorsModel> CREATOR = new Creator<SensorsModel>() {
        @Override
        public SensorsModel createFromParcel(Parcel in) {
            return new SensorsModel(in);
        }

        @Override
        public SensorsModel[] newArray(int size) {
            return new SensorsModel[size];
        }
    };

}


