package com.beastbikes.android.ble.protocol.v2;

import com.beastbikes.android.ble.protocol.v1.CommandCharacteristic;

public class CyclingTargetCharacteristic extends CommandCharacteristic {
    private int currentValue;
    private int targetType;
    private int targetValue;

    public int getTargetType() {
        return this.targetType;
    }

    public void setTargetType(int i) {
        this.targetType = i;
    }

    public int getTargetValue() {
        return this.targetValue;
    }

    public void setTargetValue(int i) {
        this.targetValue = i;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public void setCurrentValue(int i) {
        this.currentValue = i;
    }
}
