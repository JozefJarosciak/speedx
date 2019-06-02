package com.beastbikes.android.ble.protocol.v1;

public class PhotoSensitiveSensorCharacteristic extends SensorCharacteristic {
    private int photoSensitive;

    public int getPhotoSensitive() {
        return this.photoSensitive;
    }

    public void setPhotoSensitive(int i) {
        this.photoSensitive = i;
    }
}
