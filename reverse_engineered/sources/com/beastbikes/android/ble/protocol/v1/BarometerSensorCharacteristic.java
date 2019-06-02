package com.beastbikes.android.ble.protocol.v1;

public class BarometerSensorCharacteristic extends SensorCharacteristic {
    private float altitude;
    private int diff;

    public int getDifference() {
        return this.diff;
    }

    public void setDifference(int i) {
        this.diff = i;
    }

    public float getAltitude() {
        return this.altitude;
    }

    public void setAltitude(float f) {
        this.altitude = f;
    }
}
