package com.beastbikes.android.ble.protocol.v1;

import com.beastbikes.android.ble.protocol.CharacteristicValue;

public abstract class AbstractCharacteristicValue implements CharacteristicValue {
    private int crc;
    private int protocol;

    public int getProtocol() {
        return this.protocol;
    }

    public void setProtocol(int i) {
        this.protocol = i;
    }

    public int getCrc() {
        return this.crc;
    }

    public void setCrc(int i) {
        this.crc = i;
    }
}
