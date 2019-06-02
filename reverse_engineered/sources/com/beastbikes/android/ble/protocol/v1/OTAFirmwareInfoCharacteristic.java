package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;

public class OTAFirmwareInfoCharacteristic extends SyncDataCharacteristic implements Serializable {
    private int bleCheckSum;
    private int bleFirmwareVersion;
    private String bleVersion;
    private int fontCheckSum;
    private int fontFirmwareVersion;
    private String fontVersion;
    private ProtocolParserImpl mParser = new ProtocolParserImpl();
    private int mcuCheckSum;
    private int mcuFirmwareVersion;
    private String mcuVersion;
    private int overallFirmwareVersion;
    private String overallVersion;
    private int powerCheckSum;
    private int powerFirmwareVersion;
    private String powerVersion;
    private int uiCheckSum;
    private int uiFirmwareVersion;
    private String uiVersion;

    public int getOverallFirmwareVersion() {
        return this.overallFirmwareVersion;
    }

    public void setOverallFirmwareVersion(int i) {
        this.overallFirmwareVersion = i;
        int i2 = i >>> 16;
        int i3 = (i >>> 8) & 255;
        int i4 = i & 255;
        if (this.mParser == null) {
            this.mParser = new ProtocolParserImpl();
        }
        byte bcdToByte = this.mParser.bcdToByte((byte) i2);
        byte bcdToByte2 = this.mParser.bcdToByte((byte) i3);
        this.overallVersion = bcdToByte + "." + bcdToByte2 + "." + this.mParser.bcdToByte((byte) i4);
    }

    public int getMcuFirmwareVersion() {
        return this.mcuFirmwareVersion;
    }

    public void setMcuFirmwareVersion(int i) {
        this.mcuFirmwareVersion = i;
        int i2 = i >>> 16;
        int i3 = (i >>> 8) & 255;
        int i4 = i & 255;
        if (this.mParser == null) {
            this.mParser = new ProtocolParserImpl();
        }
        byte bcdToByte = this.mParser.bcdToByte((byte) i2);
        byte bcdToByte2 = this.mParser.bcdToByte((byte) i3);
        this.mcuVersion = bcdToByte + "." + bcdToByte2 + "." + this.mParser.bcdToByte((byte) i4);
    }

    public int getMcuCheckSum() {
        return this.mcuCheckSum;
    }

    public void setMcuCheckSum(int i) {
        this.mcuCheckSum = i;
    }

    public int getBleFirmwareVersion() {
        return this.bleFirmwareVersion;
    }

    public void setBleFirmwareVersion(int i) {
        this.bleFirmwareVersion = i;
        int i2 = i >>> 16;
        int i3 = (i >>> 8) & 255;
        int i4 = i & 255;
        if (this.mParser == null) {
            this.mParser = new ProtocolParserImpl();
        }
        byte bcdToByte = this.mParser.bcdToByte((byte) i2);
        byte bcdToByte2 = this.mParser.bcdToByte((byte) i3);
        this.bleVersion = bcdToByte + "." + bcdToByte2 + "." + this.mParser.bcdToByte((byte) i4);
    }

    public int getBleCheckSum() {
        return this.bleCheckSum;
    }

    public void setBleCheckSum(int i) {
        this.bleCheckSum = i;
    }

    public int getUiFirmwareVersion() {
        return this.uiFirmwareVersion;
    }

    public void setUiFirmwareVersion(int i) {
        this.uiFirmwareVersion = i;
        int i2 = i >>> 16;
        int i3 = (i >>> 8) & 255;
        int i4 = i & 255;
        if (this.mParser == null) {
            this.mParser = new ProtocolParserImpl();
        }
        byte bcdToByte = this.mParser.bcdToByte((byte) i2);
        byte bcdToByte2 = this.mParser.bcdToByte((byte) i3);
        this.uiVersion = bcdToByte + "." + bcdToByte2 + "." + this.mParser.bcdToByte((byte) i4);
    }

    public int getUiCheckSum() {
        return this.uiCheckSum;
    }

    public void setUiCheckSum(int i) {
        this.uiCheckSum = i;
    }

    public int getFontFirmwareVersion() {
        return this.fontFirmwareVersion;
    }

    public void setFontFirmwareVersion(int i) {
        this.fontFirmwareVersion = i;
        int i2 = i >>> 16;
        int i3 = (i >>> 8) & 255;
        int i4 = i & 255;
        if (this.mParser == null) {
            this.mParser = new ProtocolParserImpl();
        }
        byte bcdToByte = this.mParser.bcdToByte((byte) i2);
        byte bcdToByte2 = this.mParser.bcdToByte((byte) i3);
        this.fontVersion = bcdToByte + "." + bcdToByte2 + "." + this.mParser.bcdToByte((byte) i4);
    }

    public int getFontCheckSum() {
        return this.fontCheckSum;
    }

    public void setFontCheckSum(int i) {
        this.fontCheckSum = i;
    }

    public int getPowerFirmwareVersion() {
        return this.powerFirmwareVersion;
    }

    public void setPowerFirmwareVersion(int i) {
        this.powerFirmwareVersion = i;
        int i2 = i >>> 16;
        int i3 = (i >>> 8) & 255;
        int i4 = i & 255;
        if (this.mParser == null) {
            this.mParser = new ProtocolParserImpl();
        }
        byte bcdToByte = this.mParser.bcdToByte((byte) i2);
        byte bcdToByte2 = this.mParser.bcdToByte((byte) i3);
        this.powerVersion = bcdToByte + "." + bcdToByte2 + "." + this.mParser.bcdToByte((byte) i4);
    }

    public int getPowerCheckSum() {
        return this.powerCheckSum;
    }

    public void setPowerCheckSum(int i) {
        this.powerCheckSum = i;
    }

    public String getOverallVersion() {
        return this.overallVersion;
    }

    public void setOverallVersion(String str) {
        this.overallVersion = str;
    }

    public String getMcuVersion() {
        return this.mcuVersion;
    }

    public void setMcuVersion(String str) {
        this.mcuVersion = str;
    }

    public String getBleVersion() {
        return this.bleVersion;
    }

    public void setBleVersion(String str) {
        this.bleVersion = str;
    }

    public String getUiVersion() {
        return this.uiVersion;
    }

    public void setUiVersion(String str) {
        this.uiVersion = str;
    }

    public String getFontVersion() {
        return this.fontVersion;
    }

    public void setFontVersion(String str) {
        this.fontVersion = str;
    }

    public String getPowerVersion() {
        return this.powerVersion;
    }

    public void setPowerVersion(String str) {
        this.powerVersion = str;
    }

    public String toString() {
        return "OTAFirmwareInfoCharacteristic{overallFirmwareVersion=" + this.overallFirmwareVersion + ", overallVersion='" + this.overallVersion + CoreConstants.SINGLE_QUOTE_CHAR + ", mcuFirmwareVersion=" + this.mcuFirmwareVersion + ", mcuVersion='" + this.mcuVersion + CoreConstants.SINGLE_QUOTE_CHAR + ", mcuCheckSum=" + this.mcuCheckSum + ", bleFirmwareVersion=" + this.bleFirmwareVersion + ", bleVersion='" + this.bleVersion + CoreConstants.SINGLE_QUOTE_CHAR + ", bleCheckSum=" + this.bleCheckSum + ", uiFirmwareVersion=" + this.uiFirmwareVersion + ", uiVersion='" + this.uiVersion + CoreConstants.SINGLE_QUOTE_CHAR + ", uiCheckSum=" + this.uiCheckSum + ", fontFirmwareVersion=" + this.fontFirmwareVersion + ", fontVersion='" + this.fontVersion + CoreConstants.SINGLE_QUOTE_CHAR + ", fontCheckSum=" + this.fontCheckSum + ", powerFirmwareVersion=" + this.powerFirmwareVersion + ", powerVersion='" + this.powerVersion + CoreConstants.SINGLE_QUOTE_CHAR + ", powerCheckSum=" + this.powerCheckSum + CoreConstants.CURLY_RIGHT;
    }
}
