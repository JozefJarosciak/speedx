package com.beastbikes.android.ble.protocol.v1;

public class SystimeConfigCharacteristic extends ConfigCharacteristic {
    private long systime;

    public long getSystime() {
        return this.systime;
    }

    public void setSystime(long j) {
        this.systime = j;
    }
}
