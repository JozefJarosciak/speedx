package com.beastbikes.android.ble.protocol.v1;

public class AntPlusCommandCharacteristic extends CommandCharacteristic {
    private int manufacturerId;

    public int getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(int i) {
        this.manufacturerId = i;
    }
}
