package com.beastbikes.android.ble.protocol.v1;

public abstract class SyncDataCharacteristic extends AbstractCharacteristicValue {
    private int cyclingDataType;

    public int getCyclingDataType() {
        return this.cyclingDataType;
    }

    public void setCyclingDataType(int i) {
        this.cyclingDataType = i;
    }
}
