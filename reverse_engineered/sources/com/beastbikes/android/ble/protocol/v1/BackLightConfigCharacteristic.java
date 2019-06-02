package com.beastbikes.android.ble.protocol.v1;

public class BackLightConfigCharacteristic extends ConfigCharacteristic {
    private int duration;

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }
}
