package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;
import java.util.Arrays;

public class SynchronizationDataCharacteristic extends SyncDataCharacteristic {
    private int currentPacketIndex;
    private CyclingSampleCharacteristic[] samples;
    private int totalPacketCount;

    public int getTotalPacketCount() {
        return this.totalPacketCount;
    }

    public void setTotalPacketCount(int i) {
        this.totalPacketCount = i;
    }

    public int getCurrentPacketIndex() {
        return this.currentPacketIndex;
    }

    public void setCurrentPacketIndex(int i) {
        this.currentPacketIndex = i;
    }

    public CyclingSampleCharacteristic[] getSamples() {
        return this.samples;
    }

    public void setSamples(CyclingSampleCharacteristic[] cyclingSampleCharacteristicArr) {
        this.samples = cyclingSampleCharacteristicArr;
    }

    public String toString() {
        return "SynchronizationDataCharacteristic{totalPacketCount=" + this.totalPacketCount + ", currentPacketIndex=" + this.currentPacketIndex + ", samples=" + Arrays.toString(this.samples) + CoreConstants.CURLY_RIGHT;
    }
}
