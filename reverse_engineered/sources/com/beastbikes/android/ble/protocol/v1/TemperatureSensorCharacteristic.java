package com.beastbikes.android.ble.protocol.v1;

public class TemperatureSensorCharacteristic extends SensorCharacteristic {
    private float degree;

    public float getDegree() {
        return this.degree;
    }

    public void setDegree(float f) {
        this.degree = f;
    }
}
