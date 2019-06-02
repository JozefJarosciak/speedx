package com.beastbikes.android.ble.protocol.v1;

public class WheelConfigCharacteristic extends ConfigCharacteristic {
    private int size;

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }
}
