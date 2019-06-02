package com.beastbikes.android.ble.protocol.v1;

public class FrequencyConfigCharacteristic extends ConfigCharacteristic {
    private int frequency;

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }
}
