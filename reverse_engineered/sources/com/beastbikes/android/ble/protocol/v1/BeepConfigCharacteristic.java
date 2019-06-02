package com.beastbikes.android.ble.protocol.v1;

public class BeepConfigCharacteristic extends ConfigCharacteristic {
    private int muted;

    public int getMuted() {
        return this.muted;
    }

    public void setMuted(int i) {
        this.muted = i;
    }
}
