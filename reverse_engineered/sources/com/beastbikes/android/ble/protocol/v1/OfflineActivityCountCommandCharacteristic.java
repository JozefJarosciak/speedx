package com.beastbikes.android.ble.protocol.v1;

public class OfflineActivityCountCommandCharacteristic extends CommandCharacteristic {
    private int count;

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }
}
