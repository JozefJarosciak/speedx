package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;
import java.util.Arrays;

public class DeviceInfoExtensionCharacteristic extends CommandCharacteristic {
    private int guaranteeTime;
    private String macAddr;
    private byte[] macAddress;
    private int totalDistance;
    private int totalTime;

    public byte[] getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(byte[] bArr) {
        this.macAddress = bArr;
        this.macAddr = String.format("%02X%02X%02X%02X%02X%02X", new Object[]{Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5])});
    }

    public int getGuaranteeTime() {
        return this.guaranteeTime;
    }

    public void setGuaranteeTime(int i) {
        this.guaranteeTime = i;
    }

    public String getMacAddr() {
        String format = String.format("%02X02X02X02X02X02X", new Object[]{Byte.valueOf(this.macAddress[0]), Byte.valueOf(this.macAddress[1]), Byte.valueOf(this.macAddress[2]), Byte.valueOf(this.macAddress[3]), Byte.valueOf(this.macAddress[4]), Byte.valueOf(this.macAddress[5])});
        this.macAddr = format;
        return format;
    }

    public void setMacAddr(String str) {
        this.macAddr = str;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int i) {
        this.totalTime = i;
    }

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public String toString() {
        return "DeviceInfoExtensionCharacteristic{macAddress=" + Arrays.toString(this.macAddress) + ", guaranteeTime=" + this.guaranteeTime + ", macAddr='" + this.macAddr + CoreConstants.SINGLE_QUOTE_CHAR + ", totalDistance=" + this.totalDistance + ", totalTime=" + this.totalTime + CoreConstants.CURLY_RIGHT;
    }
}
