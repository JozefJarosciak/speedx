package com.beastbikes.android.ble.protocol.v1;

public abstract class ConfigCharacteristic extends AbstractCharacteristicValue {
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
