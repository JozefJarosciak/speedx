package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;

public class OTARequestCommandCharacteristic extends CommandCharacteristic {
    private int flags;
    private int processType;
    private int requestPacketIndex;

    public int getProcessType() {
        return this.processType;
    }

    public void setProcessType(int i) {
        this.processType = i;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public int getRequestPacketIndex() {
        return this.requestPacketIndex;
    }

    public void setRequestPacketIndex(int i) {
        this.requestPacketIndex = i;
    }

    public String toString() {
        return "OTARequestCommandCharacteristic{processType=" + this.processType + ", flags=" + this.flags + ", requestPacketIndex=" + this.requestPacketIndex + CoreConstants.CURLY_RIGHT;
    }
}
