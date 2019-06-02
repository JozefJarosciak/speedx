package com.beastbikes.android.ble.protocol.v1;

public class AutoLightConfigCharacteristic extends ConfigCharacteristic {
    private int enabled;

    public int getEnabled() {
        return this.enabled;
    }

    public void setEnabled(int i) {
        this.enabled = i;
    }
}
