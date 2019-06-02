package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;
import java.util.Arrays;

public class PreviewDataCharacteristic extends SyncDataCharacteristic {
    private CyclingActivityCharacteristic activity;
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

    public CyclingActivityCharacteristic getActivity() {
        return this.activity;
    }

    public void setActivity(CyclingActivityCharacteristic cyclingActivityCharacteristic) {
        this.activity = cyclingActivityCharacteristic;
    }

    public String toString() {
        return "PreviewDataCharacteristic{totalPacketCount=" + this.totalPacketCount + ", currentPacketIndex=" + this.currentPacketIndex + ", activity=" + this.activity + ", samples=" + Arrays.toString(this.samples) + CoreConstants.CURLY_RIGHT;
    }
}
