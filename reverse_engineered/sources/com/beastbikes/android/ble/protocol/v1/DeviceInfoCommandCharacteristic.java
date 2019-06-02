package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;

public class DeviceInfoCommandCharacteristic extends CommandCharacteristic implements Serializable {
    private int autolight;
    private int backlight;
    private int battery;
    private int brandType;
    private int favouriteCadence;
    private int firmwareVersion;
    private int frequency;
    private int gpsService;
    private int hardwareType;
    private int locale;
    private int mileageUnit;
    private int mute;
    private int notification;
    private int shakeUp;
    private long systime;
    private int wheelType;

    public long getSystime() {
        return this.systime;
    }

    public void setSystime(long j) {
        this.systime = j;
    }

    public int getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public void setFirmwareVersion(int i) {
        this.firmwareVersion = i;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public int getLocale() {
        return this.locale;
    }

    public void setLocale(int i) {
        this.locale = i;
    }

    public int getBacklight() {
        return this.backlight;
    }

    public void setBacklight(int i) {
        this.backlight = i;
    }

    public int getBattery() {
        return this.battery;
    }

    public void setBattery(int i) {
        this.battery = i;
    }

    public int getAutolight() {
        return this.autolight;
    }

    public void setAutolight(int i) {
        this.autolight = i;
    }

    public int getMute() {
        return this.mute;
    }

    public void setMute(int i) {
        this.mute = i;
    }

    public int getGpsService() {
        return this.gpsService;
    }

    public void setGpsService(int i) {
        this.gpsService = i;
    }

    public int getMileageUnit() {
        return this.mileageUnit;
    }

    public void setMileageUnit(int i) {
        this.mileageUnit = i;
    }

    public int getHardwareType() {
        return this.hardwareType;
    }

    public void setHardwareType(int i) {
        this.hardwareType = i;
    }

    public int getBrandType() {
        return this.brandType;
    }

    public void setBrandType(int i) {
        this.brandType = i;
    }

    public int getWheelType() {
        return this.wheelType;
    }

    public void setWheelType(int i) {
        this.wheelType = i;
    }

    public int getNotification() {
        return this.notification;
    }

    public void setNotification(int i) {
        this.notification = i;
    }

    public int getShakeUp() {
        return this.shakeUp;
    }

    public void setShakeUp(int i) {
        this.shakeUp = i;
    }

    public int getFavouriteCadence() {
        return this.favouriteCadence;
    }

    public void setFavouriteCadence(int i) {
        this.favouriteCadence = i;
    }

    public String toString() {
        return "DeviceInfoCommandCharacteristic{systime=" + this.systime + ", firmwareVersion=" + this.firmwareVersion + ", frequency=" + this.frequency + ", locale=" + this.locale + ", backlight=" + this.backlight + ", battery=" + this.battery + ", autolight=" + this.autolight + ", mute=" + this.mute + ", gpsService=" + this.gpsService + ", mileageUnit=" + this.mileageUnit + ", hardwareType=" + this.hardwareType + ", brandType=" + this.brandType + ", wheelType=" + this.wheelType + ", notification=" + this.notification + ", favouriteCadence=" + this.favouriteCadence + ", shakeUp=" + this.shakeUp + CoreConstants.CURLY_RIGHT;
    }
}
