package com.beastbikes.android.ble.dto;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;

public class S605FirmwareInfo implements Serializable {
    public static final int VERSION_LEN = 4;
    public static final int VERSION_NAME_LEN = 3;
    private String macAddress;
    private String mid;
    private String romCurrVersion;
    private String romNewVersion;

    public String getRomCurrVersion() {
        return this.romCurrVersion;
    }

    public void setRomCurrVersion(String str) {
        this.romCurrVersion = str;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String str) {
        this.mid = str;
    }

    public String getRomNewVersion() {
        return this.romNewVersion;
    }

    public void setRomNewVersion(String str) {
        this.romNewVersion = str;
    }

    public String toString() {
        return "S605FirmwareInfo{romCurrVersion='" + this.romCurrVersion + CoreConstants.SINGLE_QUOTE_CHAR + ", macAddress='" + this.macAddress + CoreConstants.SINGLE_QUOTE_CHAR + ", mid='" + this.mid + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }
}
