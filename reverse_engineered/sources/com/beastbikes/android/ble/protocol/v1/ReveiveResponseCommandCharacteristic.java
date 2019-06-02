package com.beastbikes.android.ble.protocol.v1;

public class ReveiveResponseCommandCharacteristic extends CommandCharacteristic {
    private int flags;

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }
}
