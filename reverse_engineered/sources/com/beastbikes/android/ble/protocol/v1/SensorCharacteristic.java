package com.beastbikes.android.ble.protocol.v1;

public abstract class SensorCharacteristic extends AbstractCharacteristicValue {
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
