package com.beastbikes.android.modules.cycling.activity.dao.entity;

import android.os.Parcel;
import com.baidu.platform.comapi.location.CoordinateType;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.ActivityDTO.Leg;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "activity")
public class LocalActivity implements PersistentObject {
    private static final long serialVersionUID = -4088024331689607983L;
    @DatabaseField(columnName = "achievement")
    private int achievement;
    @DatabaseField(columnName = "achievement_name")
    private String achievementName;
    @DatabaseField(columnName = "activity_url")
    private String activityUrl;
    private double altitude;
    @DatabaseField(columnName = "avg_power")
    private double avgPower;
    private int avgPowerPer10s;
    private int avgPowerPer30s;
    private int avgPowerPer3s;
    private double avgReserveHeartRate = -1.0d;
    @DatabaseField(columnName = "ble_data_type")
    private int bleDataType;
    @DatabaseField(columnName = "cadence")
    private double cadence;
    @DatabaseField(columnName = "cardiac_rate")
    private double cardiacRate;
    @DatabaseField(columnName = "central_id")
    private String centralId;
    @DatabaseField(columnName = "central_name")
    private String centralName;
    @DatabaseField(canBeNull = false, columnName = "coordinate_system")
    private String coordinate;
    @DatabaseField(columnName = "course_en_name")
    private String courseEnName;
    @DatabaseField(columnName = "course_id")
    private int courseId;
    @DatabaseField(columnName = "course_if")
    private int courseIf;
    @DatabaseField(columnName = "course_name")
    private String courseName;
    @DatabaseField(columnName = "course_time")
    private long courseTime;
    @DatabaseField(columnName = "course_tss")
    private int courseTss;
    private double currentSlope;
    @DatabaseField(columnName = "description")
    private String description;
    @DatabaseField(columnName = "device_id")
    private String deviceId;
    @DatabaseField(columnName = "email")
    private String email;
    @DatabaseField(columnName = "fake")
    private int fake;
    @DatabaseField(canBeNull = false, columnName = "finish_time")
    private long finishTime;
    private int heartRateZone = -1;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "instantaneous_velocity")
    private double instantaneousVelocity;
    @DatabaseField(columnName = "is_new_samples")
    private boolean isNewSamples;
    @DatabaseField(columnName = "is_private")
    private int isPrivate;
    @DatabaseField(columnName = "is_repair")
    private int isRepair;
    @DatabaseField(columnName = "is_virtual_watts")
    private boolean isVirtualPower = true;
    @DatabaseField(columnName = "is_join_leg")
    private boolean joinLeg;
    @DatabaseField(columnName = "leg_name")
    private String legName;
    @DatabaseField(columnName = "max_20_minutes_power")
    private int max20MinutesPower;
    @DatabaseField(columnName = "max_altitude")
    private double maxAltitude;
    @DatabaseField(columnName = "cadence_max")
    private double maxCadence;
    @DatabaseField(columnName = "max_cardiac_rate")
    private double maxCardiacRate;
    private double maxHeartRatePercent = -1.0d;
    @DatabaseField(columnName = "max_power")
    private double maxPower;
    @DatabaseField(columnName = "max_velocity")
    private double maxVelocity;
    @DatabaseField(columnName = "other_leg_count")
    private int otherLegCount;
    @DatabaseField(columnName = "power_ftp")
    private int powerFTP;
    @DatabaseField(columnName = "power_if")
    private double powerIF;
    @DatabaseField(columnName = "power_output")
    private double powerOutput;
    @DatabaseField(columnName = "power_tss")
    private int powerTSS;
    private double powerWattsPerKG;
    private int powerZone = 1;
    private int progress;
    private int realTimeCadence = -1;
    private int realTimeHeartRate = -1;
    private int realTimePower;
    @DatabaseField(columnName = "remote_id")
    private String remoteId;
    @DatabaseField(columnName = "repair_distance")
    private double repairDistance;
    private double reserveHeartRate = -1.0d;
    @DatabaseField(columnName = "sample_count")
    private int sampleCount;
    @DatabaseField(columnName = "sample_rate")
    private int sampleRate;
    @DatabaseField(columnName = "show_status")
    private int showStatus;
    @DatabaseField(columnName = "source")
    private String source;
    @DatabaseField(columnName = "speed")
    private double speed;
    @DatabaseField(columnName = "standard_power")
    private int standardPower;
    @DatabaseField(canBeNull = false, columnName = "start_time")
    private long startTime;
    @DatabaseField(canBeNull = false, columnName = "state")
    private int state;
    @DatabaseField(columnName = "sync_time")
    private long syncTime;
    @DatabaseField(columnName = "synced")
    private boolean synced;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "total_avg_power_30s")
    private double totalAvgPower30s;
    @DatabaseField(columnName = "total_calorie")
    private double totalCalorie;
    @DatabaseField(columnName = "total_decline")
    private double totalDescent;
    @DatabaseField(canBeNull = false, columnName = "total_distance")
    private double totalDistance;
    @DatabaseField(canBeNull = false, columnName = "total_elapsed_time")
    private double totalElapsedTime;
    private double totalMileage;
    @DatabaseField(columnName = "total_risen_altitude")
    private double totalRisenAltitude;
    private long totalTime;
    @DatabaseField(columnName = "total_uphill_distance")
    private double totalUphillDistance;
    @DatabaseField(columnName = "train_course_date")
    private long trainCourseDate;
    @DatabaseField(columnName = "train_id")
    private int trainId;
    @DatabaseField(canBeNull = false, columnName = "type")
    private int type;
    @DatabaseField(canBeNull = false, columnName = "user_id")
    private String userId;
    @DatabaseField(canBeNull = false, columnName = "username")
    private String username;
    @DatabaseField(columnName = "version")
    private String version;
    private double verticalSpeed;
    private double verticalSpeedPer30s;

    public LocalActivity(Parcel parcel) {
        boolean z = true;
        this.id = parcel.readString();
        this.userId = parcel.readString();
        this.username = parcel.readString();
        this.email = parcel.readString();
        this.type = parcel.readInt();
        this.state = parcel.readInt();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.startTime = parcel.readLong();
        this.finishTime = parcel.readLong();
        this.coordinate = parcel.readString();
        this.totalDistance = parcel.readDouble();
        this.totalElapsedTime = parcel.readDouble();
        this.totalCalorie = parcel.readDouble();
        this.totalRisenAltitude = parcel.readDouble();
        this.totalUphillDistance = parcel.readDouble();
        this.maxAltitude = parcel.readDouble();
        this.maxVelocity = parcel.readDouble();
        this.maxCardiacRate = parcel.readDouble();
        this.synced = parcel.readByte() != (byte) 0;
        this.syncTime = parcel.readLong();
        this.remoteId = parcel.readString();
        this.activityUrl = parcel.readString();
        this.fake = parcel.readInt();
        this.speed = parcel.readDouble();
        this.source = parcel.readString();
        this.deviceId = parcel.readString();
        this.sampleCount = parcel.readInt();
        this.sampleRate = parcel.readInt();
        this.isPrivate = parcel.readInt();
        this.cadence = (double) parcel.readFloat();
        this.maxCadence = (double) parcel.readFloat();
        this.cardiacRate = (double) parcel.readFloat();
        this.centralName = parcel.readString();
        this.centralId = parcel.readString();
        this.repairDistance = parcel.readDouble();
        this.showStatus = parcel.readInt();
        this.isRepair = parcel.readInt();
        this.version = parcel.readString();
        this.joinLeg = parcel.readByte() != (byte) 0;
        this.achievement = parcel.readInt();
        this.legName = parcel.readString();
        this.otherLegCount = parcel.readInt();
        this.achievementName = parcel.readString();
        this.maxPower = parcel.readDouble();
        this.avgPower = parcel.readDouble();
        this.powerOutput = parcel.readDouble();
        this.max20MinutesPower = parcel.readInt();
        this.standardPower = parcel.readInt();
        this.powerIF = parcel.readDouble();
        this.powerTSS = parcel.readInt();
        this.powerFTP = parcel.readInt();
        this.courseId = parcel.readInt();
        this.courseIf = parcel.readInt();
        this.courseName = parcel.readString();
        this.courseEnName = parcel.readString();
        this.courseTime = parcel.readLong();
        this.courseTss = parcel.readInt();
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.isVirtualPower = z;
    }

    public LocalActivity(ActivityDTO activityDTO) {
        int i = 1;
        this.id = activityDTO.getActivityIdentifier();
        this.remoteId = activityDTO.getActivityId();
        this.username = activityDTO.getUsername();
        this.userId = activityDTO.getUserId();
        this.email = activityDTO.getEmail();
        this.title = activityDTO.getTitle();
        this.startTime = activityDTO.getStartTime();
        this.finishTime = activityDTO.getStopTime();
        this.totalDistance = activityDTO.getTotalDistance();
        this.totalElapsedTime = activityDTO.getElapsedTime();
        this.totalCalorie = activityDTO.getCalories();
        this.totalRisenAltitude = activityDTO.getRiseTotal();
        this.totalUphillDistance = activityDTO.getUphillDistance();
        this.maxAltitude = activityDTO.getMaxAltitude();
        this.maxVelocity = activityDTO.getMaxVelocity();
        this.isPrivate = activityDTO.getIsPrivate();
        this.speed = activityDTO.getVelocity();
        if (this.speed <= 0.0d && this.totalElapsedTime > 0.0d && this.totalDistance > 0.0d) {
            this.speed = (this.totalDistance / this.totalElapsedTime) * 3.6d;
        }
        this.syncTime = System.currentTimeMillis();
        this.coordinate = CoordinateType.GCJ02;
        this.synced = true;
        this.type = 2;
        this.state = 4;
        this.activityUrl = activityDTO.getActivityUrl();
        if (activityDTO.isFake()) {
            this.fake = 1;
        } else {
            this.fake = 0;
        }
        this.deviceId = activityDTO.getDeviceId();
        this.bleDataType = activityDTO.getBleDataType();
        this.source = activityDTO.getSource();
        this.cardiacRate = activityDTO.getCardiacRate();
        this.maxCardiacRate = activityDTO.getMaxCardiacRate();
        this.cadence = activityDTO.getCadence();
        this.maxCadence = activityDTO.getMaxCadence();
        this.centralName = activityDTO.getCentralName();
        this.centralId = activityDTO.getCentralId();
        this.repairDistance = activityDTO.getRepairDistance();
        this.showStatus = activityDTO.getShowRepair();
        if (!activityDTO.isRepair()) {
            i = 0;
        }
        this.isRepair = i;
        this.isNewSamples = activityDTO.isNewSamples();
        this.version = activityDTO.getVersion();
        this.joinLeg = activityDTO.isJoinLeg();
        Leg leg = activityDTO.getLeg();
        if (leg != null) {
            this.achievement = leg.getAchievement();
            this.legName = leg.getLegName();
            this.otherLegCount = leg.getOtherLegCount();
            this.achievementName = leg.getAchievementName();
        }
        this.maxPower = activityDTO.getMaxPower();
        this.avgPower = activityDTO.getAvgPower();
        this.powerOutput = activityDTO.getPowerOutput();
        this.max20MinutesPower = activityDTO.getMax20MinutesPower();
        this.standardPower = activityDTO.getStandardPower();
        this.powerIF = activityDTO.getPowerIF();
        this.powerTSS = activityDTO.getPowerTSS();
        this.powerFTP = activityDTO.getPowerFTP();
        this.courseId = activityDTO.getCourseId();
        this.courseIf = (int) Math.round(activityDTO.getCourseIf());
        this.courseName = activityDTO.getCourseName();
        this.courseEnName = activityDTO.getCourseEnName();
        this.courseTime = activityDTO.getCourseTime();
        this.courseTss = activityDTO.getCourseTss();
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(long j) {
        this.finishTime = j;
    }

    public String getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(String str) {
        this.coordinate = str;
    }

    public double getInstantaneousVelocity() {
        return this.instantaneousVelocity;
    }

    public void setInstantaneousVelocity(double d) {
        this.instantaneousVelocity = d;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public double getTotalElapsedTime() {
        return this.totalElapsedTime;
    }

    public void setTotalElapsedTime(double d) {
        this.totalElapsedTime = d;
    }

    public double getTotalCalorie() {
        return this.totalCalorie;
    }

    public void setTotalCalorie(double d) {
        this.totalCalorie = d;
    }

    public double getTotalRisenAltitude() {
        return this.totalRisenAltitude;
    }

    public void setTotalRisenAltitude(double d) {
        this.totalRisenAltitude = d;
    }

    public double getTotalUphillDistance() {
        return this.totalUphillDistance;
    }

    public void setTotalUphillDistance(double d) {
        this.totalUphillDistance = d;
    }

    public double getMaxAltitude() {
        return this.maxAltitude;
    }

    public void setMaxAltitude(double d) {
        this.maxAltitude = d;
    }

    public double getMaxVelocity() {
        return this.maxVelocity;
    }

    public void setMaxVelocity(double d) {
        this.maxVelocity = d;
    }

    public double getMaxCardiacRate() {
        return this.maxCardiacRate;
    }

    public void setMaxCardiacRate(double d) {
        this.maxCardiacRate = d;
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

    public String getActivityUrl() {
        return this.activityUrl;
    }

    public void setActivityUrl(String str) {
        this.activityUrl = str;
    }

    public int getFake() {
        return this.fake;
    }

    public void setFake(int i) {
        this.fake = i;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double d) {
        this.speed = d;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public void setSampleCount(int i) {
        this.sampleCount = i;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(int i) {
        this.sampleRate = i;
    }

    public int getBleDataType() {
        return this.bleDataType;
    }

    public void setBleDataType(int i) {
        this.bleDataType = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(int i) {
        this.isPrivate = i;
    }

    public double getCardiacRate() {
        return this.cardiacRate;
    }

    public void setCardiacRate(double d) {
        this.cardiacRate = d;
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

    public String getCentralId() {
        return this.centralId;
    }

    public void setCentralId(String str) {
        this.centralId = str;
    }

    public double getRepairDistance() {
        return this.repairDistance;
    }

    public void setRepairDistance(double d) {
        this.repairDistance = d;
    }

    public int getShowStatus() {
        return this.showStatus;
    }

    public void setShowStatus(int i) {
        this.showStatus = i;
    }

    public String getCentralName() {
        return this.centralName;
    }

    public void setCentralName(String str) {
        this.centralName = str;
    }

    public int getIsRepair() {
        return this.isRepair;
    }

    public void setIsRepair(int i) {
        this.isRepair = i;
    }

    public boolean isNewSamples() {
        return this.isNewSamples;
    }

    public void setNewSamples(boolean z) {
        this.isNewSamples = z;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public boolean isJoinLeg() {
        return this.joinLeg;
    }

    public void setJoinLeg(boolean z) {
        this.joinLeg = z;
    }

    public int getAchievement() {
        return this.achievement;
    }

    public void setAchievement(int i) {
        this.achievement = i;
    }

    public String getLegName() {
        return this.legName;
    }

    public void setLegName(String str) {
        this.legName = str;
    }

    public int getOtherLegCount() {
        return this.otherLegCount;
    }

    public void setOtherLegCount(int i) {
        this.otherLegCount = i;
    }

    public String getAchievementName() {
        return this.achievementName;
    }

    public void setAchievementName(String str) {
        this.achievementName = str;
    }

    public double getMaxPower() {
        return this.maxPower;
    }

    public void setMaxPower(double d) {
        this.maxPower = d;
    }

    public double getAvgPower() {
        return this.avgPower;
    }

    public void setAvgPower(double d) {
        this.avgPower = d;
    }

    public double getPowerOutput() {
        return this.powerOutput;
    }

    public void setPowerOutput(double d) {
        this.powerOutput = d;
    }

    public int getMax20MinutesPower() {
        return this.max20MinutesPower;
    }

    public void setMax20MinutesPower(int i) {
        this.max20MinutesPower = i;
    }

    public int getStandardPower() {
        return this.standardPower;
    }

    public void setStandardPower(int i) {
        this.standardPower = i;
    }

    public double getPowerIF() {
        return this.powerIF;
    }

    public void setPowerIF(double d) {
        this.powerIF = d;
    }

    public int getPowerTSS() {
        return this.powerTSS;
    }

    public void setPowerTSS(int i) {
        this.powerTSS = i;
    }

    public int getPowerFTP() {
        return this.powerFTP;
    }

    public void setPowerFTP(int i) {
        this.powerFTP = i;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int i) {
        this.courseId = i;
    }

    public int getCourseIf() {
        return this.courseIf;
    }

    public void setCourseIf(int i) {
        this.courseIf = i;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String str) {
        this.courseName = str;
    }

    public String getCourseEnName() {
        return this.courseEnName;
    }

    public void setCourseEnName(String str) {
        this.courseEnName = str;
    }

    public long getCourseTime() {
        return this.courseTime;
    }

    public void setCourseTime(long j) {
        this.courseTime = j;
    }

    public int getCourseTss() {
        return this.courseTss;
    }

    public void setCourseTss(int i) {
        this.courseTss = i;
    }

    public int getRealTimePower() {
        return this.realTimePower;
    }

    public void setRealTimePower(int i) {
        this.realTimePower = i;
    }

    public int getRealTimeCadence() {
        return this.realTimeCadence;
    }

    public void setRealTimeCadence(int i) {
        this.realTimeCadence = i;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public double getCurrentSlope() {
        return this.currentSlope;
    }

    public void setCurrentSlope(double d) {
        this.currentSlope = d;
    }

    public double getTotalDescent() {
        return this.totalDescent;
    }

    public void setTotalDescent(double d) {
        this.totalDescent = d;
    }

    public double getVerticalSpeed() {
        return this.verticalSpeed;
    }

    public void setVerticalSpeed(double d) {
        this.verticalSpeed = d;
    }

    public double getVerticalSpeedPer30s() {
        return this.verticalSpeedPer30s;
    }

    public void setVerticalSpeedPer30s(double d) {
        this.verticalSpeedPer30s = d;
    }

    public int getRealTimeHeartRate() {
        return this.realTimeHeartRate;
    }

    public void setRealTimeHeartRate(int i) {
        this.realTimeHeartRate = i;
    }

    public double getReserveHeartRate() {
        return this.reserveHeartRate;
    }

    public void setReserveHeartRate(double d) {
        this.reserveHeartRate = d;
    }

    public double getAvgReserveHeartRate() {
        return this.avgReserveHeartRate;
    }

    public void setAvgReserveHeartRate(double d) {
        this.avgReserveHeartRate = d;
    }

    public double getMaxHeartRatePercent() {
        return this.maxHeartRatePercent;
    }

    public void setMaxHeartRatePercent(double d) {
        this.maxHeartRatePercent = d;
    }

    public int getAvgPowerPer30s() {
        return this.avgPowerPer30s;
    }

    public void setAvgPowerPer30s(int i) {
        this.avgPowerPer30s = i;
    }

    public int getAvgPowerPer3s() {
        return this.avgPowerPer3s;
    }

    public void setAvgPowerPer3s(int i) {
        this.avgPowerPer3s = i;
    }

    public int getAvgPowerPer10s() {
        return this.avgPowerPer10s;
    }

    public void setAvgPowerPer10s(int i) {
        this.avgPowerPer10s = i;
    }

    public double getPowerWattsPerKG() {
        return this.powerWattsPerKG;
    }

    public void setPowerWattsPerKG(double d) {
        this.powerWattsPerKG = d;
    }

    public int getHeartRateZone() {
        return this.heartRateZone;
    }

    public void setHeartRateZone(int i) {
        this.heartRateZone = i;
    }

    public int getPowerZone() {
        return this.powerZone;
    }

    public void setPowerZone(int i) {
        this.powerZone = i;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long j) {
        this.totalTime = j;
    }

    public double getTotalMileage() {
        return this.totalMileage;
    }

    public void setTotalMileage(double d) {
        this.totalMileage = d;
    }

    public boolean isVirtualPower() {
        return this.isVirtualPower;
    }

    public void setVirtualPower(boolean z) {
        this.isVirtualPower = z;
    }

    public long getTrainCourseDate() {
        return this.trainCourseDate;
    }

    public void setTrainCourseDate(long j) {
        this.trainCourseDate = j;
    }

    public int getTrainId() {
        return this.trainId;
    }

    public void setTrainId(int i) {
        this.trainId = i;
    }

    public double getTotalAvgPower30s() {
        return this.totalAvgPower30s;
    }

    public void setTotalAvgPower30s(double d) {
        this.totalAvgPower30s = d;
    }
}
