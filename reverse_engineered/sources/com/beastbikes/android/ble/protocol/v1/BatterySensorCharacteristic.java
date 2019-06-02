package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;

public class BatterySensorCharacteristic extends SensorCharacteristic {
    private int chargeState;
    private int percentage;

    public int getPercentage() {
        return this.percentage;
    }

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getChargeState() {
        return this.chargeState;
    }

    public void setChargeState(int i) {
        this.chargeState = i;
    }

    public String toString() {
        return "BatterySensorCharacteristic{percentage=" + this.percentage + ", chargeState=" + this.chargeState + CoreConstants.CURLY_RIGHT;
    }
}
