package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;

public class AGpsInfoCharacteristic extends CommandCharacteristic {
    private int updateTime;

    public int getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(int i) {
        this.updateTime = i;
    }

    public String toString() {
        return "AGpsInfoCharacteristic{updateTime=" + this.updateTime + CoreConstants.CURLY_RIGHT;
    }
}
