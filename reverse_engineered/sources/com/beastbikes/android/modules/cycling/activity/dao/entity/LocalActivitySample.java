package com.beastbikes.android.modules.cycling.activity.dao.entity;

import ch.qos.logback.core.CoreConstants;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONException;
import org.json.JSONObject;

@DatabaseTable(tableName = "activity_sample")
public class LocalActivitySample implements PersistentObject {
    private static final long serialVersionUID = -8566336474374023212L;
    private String AVG_POWER_10S = "avg_power_10s";
    private String AVG_POWER_30S = "avg_power_30s";
    private String AVG_POWER_3S = "avg_power_3s";
    private String AVG_RESERVE_HR = "avg_reserve_hr";
    private String GRADE_PERCENTAGE = "grade_percentage";
    private String HR_ZONE = "hr_zone";
    private String MAX_HR_PERCENTAGE = "max_hr_percentage";
    private String POWER_FTP = "power_ftp";
    private String POWER_IF = "power_if";
    private String POWER_KG = "power_kg";
    private String POWER_TSS = "power_tts";
    private String POWER_ZONE = "power_zone";
    private String RESERVE_HR = "reserve_hr";
    private String VERTICAL_SPEED = "vertical_speed";
    private String VERTICAL_SPEED_30S = "vertical_speed_30s";
    @DatabaseField(canBeNull = false, columnName = "activity_id")
    private String activityId;
    @DatabaseField(columnName = "altitude")
    private String altitude;
    @DatabaseField(columnName = "cadence")
    private double cadence;
    @DatabaseField(columnName = "calorie")
    private double calorie;
    @DatabaseField(columnName = "cardiac_rate")
    private double cardiacRate;
    @DatabaseField(columnName = "curr_time")
    private long currTime;
    @DatabaseField(columnName = "cycling_status")
    private int cyclingStatus;
    @DatabaseField(columnName = "distance")
    private double distance;
    @DatabaseField(canBeNull = false, columnName = "elapsed_time")
    private long elapsedTime;
    @DatabaseField(columnName = "extend")
    private String extend;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "has_repair")
    private int isRepair;
    @DatabaseField(columnName = "latitude_0")
    private String latitude0;
    @DatabaseField(columnName = "latitude_1")
    private String latitude1;
    @DatabaseField(columnName = "longitude_0")
    private String longitude0;
    @DatabaseField(columnName = "longitude_1")
    private String longitude1;
    @DatabaseField(columnName = "max_cadence")
    private double maxCadence;
    @DatabaseField(columnName = "max_cardiac_rate")
    private double maxCardiacRate;
    @DatabaseField(columnName = "max_speed")
    private double maxSpeed;
    @DatabaseField(columnName = "ordinal")
    private int ordinal;
    @DatabaseField(columnName = "power")
    private double power;
    @DatabaseField(columnName = "remote_id")
    private String remoteId;
    @DatabaseField(columnName = "sync_time")
    private long syncTime;
    @DatabaseField(columnName = "synced")
    private boolean synced;
    @DatabaseField(canBeNull = false, columnName = "time")
    private double time;
    @DatabaseField(canBeNull = false, columnName = "user_id")
    private String userId;
    @DatabaseField(columnName = "velocity")
    private double velocity;
    private double verticalSpeed;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(int i) {
        this.ordinal = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getLongitude0() {
        return this.longitude0;
    }

    public void setLongitude0(String str) {
        this.longitude0 = str;
    }

    public String getLatitude0() {
        return this.latitude0;
    }

    public void setLatitude0(String str) {
        this.latitude0 = str;
    }

    public String getLongitude1() {
        return this.longitude1;
    }

    public void setLongitude1(String str) {
        this.longitude1 = str;
    }

    public String getLatitude1() {
        return this.latitude1;
    }

    public void setLatitude1(String str) {
        this.latitude1 = str;
    }

    public String getAltitude() {
        return this.altitude;
    }

    public void setAltitude(String str) {
        this.altitude = str;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double d) {
        this.time = d;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public void setElapsedTime(long j) {
        this.elapsedTime = j;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public void setVelocity(double d) {
        this.velocity = d;
    }

    public double getCalorie() {
        return this.calorie;
    }

    public void setCalorie(double d) {
        this.calorie = d;
    }

    public double getCardiacRate() {
        return this.cardiacRate;
    }

    public void setCardiacRate(double d) {
        this.cardiacRate = d;
    }

    public boolean isSynced() {
        return this.synced;
    }

    public void setSynced(boolean z) {
        this.synced = z;
    }

    public long getSyncTime() {
        return this.syncTime;
    }

    public void setSyncTime(long j) {
        this.syncTime = j;
    }

    public String getRemoteId() {
        return this.remoteId;
    }

    public void setRemoteId(String str) {
        this.remoteId = str;
    }

    public long getCurrTime() {
        return this.currTime;
    }

    public void setCurrTime(long j) {
        this.currTime = j;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(double d) {
        this.maxSpeed = d;
    }

    public double getMaxCardiacRate() {
        return this.maxCardiacRate;
    }

    public void setMaxCardiacRate(double d) {
        this.maxCardiacRate = d;
    }

    public double getCadence() {
        return this.cadence;
    }

    public void setCadence(double d) {
        this.cadence = d;
    }

    public double getMaxCadence() {
        return this.maxCadence;
    }

    public void setMaxCadence(double d) {
        this.maxCadence = d;
    }

    public int getCyclingStatus() {
        return this.cyclingStatus;
    }

    public void setCyclingStatus(int i) {
        this.cyclingStatus = i;
    }

    public int getIsRepair() {
        return this.isRepair;
    }

    public void setIsRepair(int i) {
        this.isRepair = i;
    }

    public double getPower() {
        return this.power;
    }

    public void setPower(double d) {
        this.power = d;
    }

    public double getVerticalSpeed() {
        return this.verticalSpeed;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public void setExtend(LocalActivity localActivity) {
        int i = 1;
        double d = 0.0d;
        JSONObject jSONObject = new JSONObject();
        try {
            double currentSlope = localActivity.getCurrentSlope();
            if (Double.isNaN(currentSlope)) {
                currentSlope = 0.0d;
            }
            jSONObject.put(this.GRADE_PERCENTAGE, currentSlope);
            currentSlope = localActivity.getVerticalSpeed();
            if (Double.isNaN(localActivity.getVerticalSpeed())) {
                currentSlope = 0.0d;
            }
            jSONObject.put(this.VERTICAL_SPEED, currentSlope);
            currentSlope = localActivity.getVerticalSpeedPer30s();
            if (Double.isNaN(currentSlope)) {
                currentSlope = 0.0d;
            }
            jSONObject.put(this.VERTICAL_SPEED_30S, currentSlope);
            currentSlope = localActivity.getReserveHeartRate();
            if (currentSlope < 0.0d || Double.isNaN(currentSlope)) {
                currentSlope = 0.0d;
            }
            jSONObject.put(this.RESERVE_HR, currentSlope);
            currentSlope = localActivity.getMaxHeartRatePercent();
            if (currentSlope < 0.0d || Double.isNaN(currentSlope)) {
                currentSlope = 0.0d;
            }
            jSONObject.put(this.MAX_HR_PERCENTAGE, currentSlope);
            currentSlope = localActivity.getAvgReserveHeartRate();
            if (currentSlope < 0.0d || Double.isNaN(currentSlope)) {
                currentSlope = 0.0d;
            }
            jSONObject.put(this.AVG_RESERVE_HR, currentSlope);
            int heartRateZone = localActivity.getHeartRateZone();
            if (heartRateZone >= 1) {
                i = heartRateZone;
            }
            jSONObject.put(this.HR_ZONE, i);
            jSONObject.put(this.POWER_FTP, localActivity.getPowerFTP());
            jSONObject.put(this.AVG_POWER_3S, localActivity.getAvgPowerPer3s());
            jSONObject.put(this.AVG_POWER_10S, localActivity.getAvgPowerPer10s());
            jSONObject.put(this.AVG_POWER_30S, localActivity.getAvgPowerPer30s());
            double powerWattsPerKG = localActivity.getPowerWattsPerKG();
            if (Double.isNaN(powerWattsPerKG)) {
                powerWattsPerKG = 0.0d;
            }
            jSONObject.put(this.POWER_KG, powerWattsPerKG);
            powerWattsPerKG = localActivity.getPowerIF();
            if (Double.isNaN(powerWattsPerKG)) {
                powerWattsPerKG = 0.0d;
            }
            jSONObject.put(this.POWER_IF, powerWattsPerKG);
            powerWattsPerKG = (double) localActivity.getPowerTSS();
            if (!Double.isNaN(powerWattsPerKG)) {
                d = powerWattsPerKG;
            }
            jSONObject.put(this.POWER_TSS, d);
            jSONObject.put(this.POWER_ZONE, localActivity.getPowerZone());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.extend = jSONObject.toString();
    }

    public void setVerticalSpeed(double d) {
        this.verticalSpeed = d;
    }

    public String toString() {
        return "LocalActivitySample{id='" + this.id + CoreConstants.SINGLE_QUOTE_CHAR + ", activityId='" + this.activityId + CoreConstants.SINGLE_QUOTE_CHAR + ", ordinal=" + this.ordinal + ", userId='" + this.userId + CoreConstants.SINGLE_QUOTE_CHAR + ", longitude0='" + this.longitude0 + CoreConstants.SINGLE_QUOTE_CHAR + ", latitude0='" + this.latitude0 + CoreConstants.SINGLE_QUOTE_CHAR + ", longitude1='" + this.longitude1 + CoreConstants.SINGLE_QUOTE_CHAR + ", latitude1='" + this.latitude1 + CoreConstants.SINGLE_QUOTE_CHAR + ", altitude='" + this.altitude + CoreConstants.SINGLE_QUOTE_CHAR + ", time=" + this.time + ", elapsedTime=" + this.elapsedTime + ", distance=" + this.distance + ", velocity=" + this.velocity + ", calorie=" + this.calorie + ", cardiacRate=" + this.cardiacRate + ", synced=" + this.synced + ", syncTime=" + this.syncTime + ", remoteId='" + this.remoteId + CoreConstants.SINGLE_QUOTE_CHAR + ", currTime=" + this.currTime + ", maxSpeed=" + this.maxSpeed + ", maxCardiacRate=" + this.maxCardiacRate + ", cadence=" + this.cadence + ", maxCadence=" + this.maxCadence + ", cyclingStatus=" + this.cyclingStatus + ", isRepair=" + this.isRepair + ", power=" + this.power + CoreConstants.CURLY_RIGHT;
    }
}
