package com.beastbikes.android.ble.dao.entity;

import android.os.Parcel;
import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ble_device")
public class BleDevice implements PersistentObject {
    @DatabaseField(columnName = "brandType")
    private int brandType;
    @DatabaseField(columnName = "cycle_time")
    private long cycleTime;
    @DatabaseField(columnName = "cycle_times")
    private int cycleTimes;
    @DatabaseField(columnName = "device_id")
    private String deviceId;
    @DatabaseField(columnName = "device_name")
    private String deviceName;
    @DatabaseField(columnName = "device_type")
    private int deviceType;
    @DatabaseField(columnName = "frame_id")
    private String frameId;
    @DatabaseField(columnName = "guarantee_time")
    private int guaranteeTime;
    @DatabaseField(columnName = "hardware_type")
    private int hardwareType;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "last_bind_time")
    private long lastBindTime;
    @DatabaseField(columnName = "mac_address")
    private String macAddress;
    @DatabaseField(columnName = "mileage")
    private double mileage;
    @DatabaseField(columnName = "object_id")
    private int objectId;
    @DatabaseField(columnName = "status")
    private int status;
    @DatabaseField(columnName = "device_url")
    private String url;
    @DatabaseField(columnName = "user_id")
    private String userId;

    public BleDevice(String str) {
        this.deviceName = str;
    }

    public BleDevice(Parcel parcel) {
        this.id = parcel.readString();
        this.userId = parcel.readString();
        this.deviceId = parcel.readString();
        this.deviceName = parcel.readString();
        this.lastBindTime = parcel.readLong();
        this.status = parcel.readInt();
        this.url = parcel.readString();
        this.hardwareType = parcel.readInt();
        this.brandType = parcel.readInt();
        this.macAddress = parcel.readString();
        this.guaranteeTime = parcel.readInt();
        this.frameId = parcel.readString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public long getLastBindTime() {
        return this.lastBindTime;
    }

    public void setLastBindTime(long j) {
        this.lastBindTime = j;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
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

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public int getGuaranteeTime() {
        return this.guaranteeTime;
    }

    public void setGuaranteeTime(int i) {
        this.guaranteeTime = i;
    }

    public String getFrameId() {
        return this.frameId;
    }

    public void setFrameId(String str) {
        this.frameId = str;
    }

    public int getObjectId() {
        return this.objectId;
    }

    public void setObjectId(int i) {
        this.objectId = i;
    }

    public double getMileage() {
        return this.mileage;
    }

    public void setMileage(double d) {
        this.mileage = d;
    }

    public long getCycleTime() {
        return this.cycleTime;
    }

    public void setCycleTime(long j) {
        this.cycleTime = j;
    }

    public int getCycleTimes() {
        return this.cycleTimes;
    }

    public void setCycleTimes(int i) {
        this.cycleTimes = i;
    }

    public static String brandType2String(int i) {
        String str = "SpeedForce";
        switch (i) {
            case 0:
                return "SpeedForce";
            case 1:
                return "Mustang";
            case 2:
                return "Leopard";
            case 3:
                return "Leopard_Pro";
            case 4:
                return "Giant_Customed";
            default:
                return "SpeedForce";
        }
    }

    public static String brandType2Name(int i) {
        String str = "SpeedForce";
        switch (i) {
            case 0:
                return "SpeedForce";
            case 1:
                return "Mustang";
            case 2:
                return "Leopard";
            case 3:
                return "Leopard Pro";
            case 4:
                return "Giant Customed";
            default:
                return "SpeedForce";
        }
    }

    public static int brandType2Int(String str) {
        if (TextUtils.equals(str, "SpeedForce")) {
            return 0;
        }
        if (TextUtils.equals(str, "Mustang")) {
            return 1;
        }
        if (TextUtils.equals(str, "Leopard")) {
            return 2;
        }
        if (TextUtils.equals(str, "Leopard Pro")) {
            return 3;
        }
        if (TextUtils.equals(str, "Giant Customed")) {
            return 4;
        }
        return TextUtils.equals(str, "SpeedForce") ? 0 : 0;
    }

    public boolean equals(Object obj) {
        return obj != null && TextUtils.equals(this.macAddress, ((BleDevice) obj).getMacAddress());
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "BleDevice{id='" + this.id + CoreConstants.SINGLE_QUOTE_CHAR + ", userId='" + this.userId + CoreConstants.SINGLE_QUOTE_CHAR + ", deviceId='" + this.deviceId + CoreConstants.SINGLE_QUOTE_CHAR + ", deviceName='" + this.deviceName + CoreConstants.SINGLE_QUOTE_CHAR + ", lastBindTime=" + this.lastBindTime + ", status=" + this.status + ", url='" + this.url + CoreConstants.SINGLE_QUOTE_CHAR + ", hardwareType=" + this.hardwareType + ", brandType=" + this.brandType + ", macAddress='" + this.macAddress + CoreConstants.SINGLE_QUOTE_CHAR + ", guaranteeTime=" + this.guaranteeTime + ", frameId='" + this.frameId + CoreConstants.SINGLE_QUOTE_CHAR + ", objectId=" + this.objectId + ", mileage=" + this.mileage + ", cycleTime=" + this.cycleTime + ", cycleTimes=" + this.cycleTimes + ", deviceType=" + this.deviceType + CoreConstants.CURLY_RIGHT;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }
}
