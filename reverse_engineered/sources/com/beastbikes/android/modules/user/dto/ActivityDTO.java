package com.beastbikes.android.modules.user.dto;

import android.text.TextUtils;
import com.avos.avoscloud.AVUtils;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.p081b.C2409c;
import com.beastbikes.framework.ui.android.WebActivity;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.directions.v5.DirectionsCriteria;
import io.rong.imlib.statistics.UserData;
import java.io.Serializable;
import java.util.Date;
import org.json.JSONObject;

public class ActivityDTO extends C2409c implements Serializable {
    private static final long serialVersionUID = 6214355107311371136L;
    private String activityId;
    private String activityIdentifier;
    private String activityUrl;
    private boolean automotive;
    private String avatarUrl;
    private double avgPower;
    private boolean baiduMap;
    private int bleDataType;
    private double cadence;
    private double calories;
    private double cardiacRate;
    private String centralId;
    private String centralName;
    private String cityName;
    private String courseEnName;
    private int courseId;
    private double courseIf;
    private String courseName;
    private long courseTime;
    private int courseTss;
    private String createdAt;
    private boolean cycling;
    private String deviceId;
    private double elapsedTime;
    private String email;
    private boolean fake;
    private boolean hasReport;
    private boolean isChecked;
    private boolean isNewSamples;
    private int isPrivate;
    private boolean isRepair;
    private boolean isTraining;
    private boolean isVirtualPower = true;
    private boolean joinLeg;
    private Leg leg;
    private int max20MinutesPower;
    private double maxAltitude;
    private double maxCadence;
    private double maxCardiacRate;
    private double maxPower;
    private double maxVelocity;
    private String nickname;
    private String note;
    private double np;
    private boolean nuked = false;
    private String objectId;
    private int powerFTP;
    private double powerIF;
    private double powerOutput;
    private int powerTSS;
    private double repairDistance;
    private double riseTotal;
    private boolean running;
    private int sampleStatus;
    private boolean samples;
    private int showRepair;
    private String source;
    private int standardPower;
    private long startTime;
    private long stopTime;
    private boolean synced = true;
    private String title;
    private double totalDecline;
    private double totalDistance;
    private String updateAt;
    private double uphillDistance;
    private String userId;
    private String username;
    private double velocity;
    private String version;
    private int versionCode;
    private boolean walking;

    public static class Leg implements Serializable {
        public static final int ACHIEVEMENT_TYPE_1 = 1;
        public static final int ACHIEVEMENT_TYPE_2 = 2;
        public static final int ACHIEVEMENT_TYPE_3 = 3;
        private int achievement;
        private String achievementName;
        private String legName;
        private int otherLegCount;

        public Leg(LocalActivity localActivity) {
            this.achievement = localActivity.getAchievement();
            this.legName = localActivity.getLegName();
            this.otherLegCount = localActivity.getOtherLegCount();
            this.achievementName = localActivity.getAchievementName();
        }

        public Leg(int i, String str, int i2, String str2) {
            this.achievement = i;
            this.legName = str;
            this.otherLegCount = i2;
            this.achievementName = str2;
        }

        public Leg(JSONObject jSONObject) {
            this.achievement = jSONObject.optInt("achievement");
            this.legName = jSONObject.optString("leg_name");
            this.otherLegCount = jSONObject.optInt("other_leg_count");
            this.achievementName = jSONObject.optString("achievement_name");
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
    }

    public ActivityDTO(LocalActivity localActivity) {
        boolean z = true;
        if (localActivity != null) {
            this.userId = localActivity.getUserId();
            this.activityId = localActivity.getRemoteId();
            this.activityIdentifier = localActivity.getId();
            this.email = localActivity.getEmail();
            this.username = localActivity.getUsername();
            this.totalDistance = localActivity.getTotalDistance();
            this.maxVelocity = localActivity.getMaxVelocity();
            this.elapsedTime = localActivity.getTotalElapsedTime();
            this.calories = localActivity.getTotalCalorie();
            Date h = C2555d.m12820h(C2555d.m12796a(localActivity.getStartTime()));
            Date h2 = C2555d.m12820h(C2555d.m12796a(localActivity.getStartTime()));
            if (h != null) {
                this.startTime = h.getTime();
            }
            if (h2 != null) {
                this.stopTime = h2.getTime();
            }
            this.title = localActivity.getTitle();
            this.uphillDistance = localActivity.getTotalUphillDistance();
            this.riseTotal = localActivity.getTotalRisenAltitude();
            this.activityUrl = localActivity.getActivityUrl();
            this.fake = localActivity.getFake() == 1;
            this.velocity = localActivity.getSpeed();
            if (this.velocity <= 0.0d && this.elapsedTime > 0.0d && this.totalDistance > 0.0d) {
                this.velocity = (this.totalDistance / this.elapsedTime) * 3.6d;
            }
            this.synced = localActivity.isSynced();
            this.isPrivate = localActivity.getIsPrivate();
            this.deviceId = localActivity.getDeviceId();
            this.bleDataType = localActivity.getBleDataType();
            this.source = localActivity.getSource();
            this.cadence = localActivity.getCadence();
            this.maxCadence = localActivity.getMaxCadence();
            this.cardiacRate = localActivity.getCardiacRate();
            this.maxCardiacRate = localActivity.getMaxCardiacRate();
            this.centralName = localActivity.getCentralName();
            this.repairDistance = localActivity.getRepairDistance();
            this.showRepair = localActivity.getShowStatus();
            if (localActivity.getIsRepair() != 1) {
                z = false;
            }
            this.isRepair = z;
            this.isNewSamples = localActivity.isNewSamples();
            this.version = localActivity.getVersion();
            this.versionCode = version2VersionCode(this.version);
            this.joinLeg = localActivity.isJoinLeg();
            this.leg = new Leg(localActivity);
            this.maxPower = localActivity.getMaxPower();
            this.avgPower = localActivity.getAvgPower();
            this.powerOutput = localActivity.getPowerOutput();
            this.max20MinutesPower = localActivity.getMax20MinutesPower();
            this.standardPower = localActivity.getStandardPower();
            this.powerIF = localActivity.getPowerIF();
            this.powerTSS = localActivity.getPowerTSS();
            this.powerFTP = localActivity.getPowerFTP();
            this.courseId = localActivity.getCourseId();
            this.courseIf = (double) localActivity.getCourseIf();
            this.courseName = localActivity.getCourseName();
            this.courseEnName = localActivity.getCourseEnName();
            this.courseTime = localActivity.getCourseTime();
            this.courseTss = localActivity.getCourseTss();
            this.isVirtualPower = localActivity.isVirtualPower();
            this.totalDecline = localActivity.getTotalDescent();
            this.np = (double) localActivity.getStandardPower();
        }
    }

    public ActivityDTO(JSONObject jSONObject) {
        int i = 1;
        this.activityId = jSONObject.optString("id");
        this.userId = jSONObject.optString("userId");
        this.email = jSONObject.optString("email");
        this.username = jSONObject.optString(UserData.USERNAME_KEY);
        this.activityIdentifier = jSONObject.optString("sportIdentify");
        this.totalDistance = jSONObject.optDouble("distance", 0.0d);
        this.velocity = jSONObject.optDouble(MapboxEvent.KEY_SPEED, 0.0d);
        this.maxVelocity = jSONObject.optDouble("speedMax", 0.0d);
        this.elapsedTime = jSONObject.optDouble("time", 0.0d);
        this.calories = jSONObject.optDouble("calories", 0.0d);
        Date h = C2555d.m12820h(jSONObject.optString("startDate"));
        Date h2 = C2555d.m12820h(jSONObject.optString("stopDate"));
        if (h != null) {
            this.startTime = h.getTime();
        }
        if (h2 != null) {
            this.stopTime = h2.getTime();
        }
        this.title = jSONObject.optString(WebActivity.EXTRA_TITLE);
        this.uphillDistance = jSONObject.optDouble("uphillDistance", 0.0d);
        this.riseTotal = jSONObject.optDouble("riseTotal", 0.0d);
        this.activityUrl = jSONObject.optString("cyclingImage");
        this.fake = jSONObject.optBoolean("fake");
        this.hasReport = jSONObject.optBoolean("hasReport");
        this.nuked = jSONObject.optBoolean("nuked", false);
        if (!jSONObject.optBoolean("isPrivate")) {
            i = 0;
        }
        this.isPrivate = i;
        this.source = jSONObject.optString(MapboxEvent.ATTRIBUTE_SOURCE);
        this.cardiacRate = jSONObject.optDouble("cardiacRate", 0.0d);
        this.maxCardiacRate = jSONObject.optDouble("cardiacRateMax", 0.0d);
        this.cadence = jSONObject.optDouble("cadence");
        this.maxCadence = jSONObject.optDouble("cadenceMax");
        this.centralName = jSONObject.optString("centralName");
        this.note = jSONObject.optString("note");
        this.updateAt = jSONObject.optString("updateAt");
        this.objectId = jSONObject.optString(AVUtils.objectIdTag);
        this.centralId = jSONObject.optString("centralId");
        this.samples = jSONObject.optBoolean("samples");
        this.cycling = jSONObject.optBoolean(DirectionsCriteria.PROFILE_CYCLING);
        this.baiduMap = jSONObject.optBoolean("baiduMap");
        this.automotive = jSONObject.optBoolean("automotive");
        this.walking = jSONObject.optBoolean(DirectionsCriteria.PROFILE_WALKING);
        this.createdAt = jSONObject.optString("createdAt");
        this.running = jSONObject.optBoolean("running");
        this.repairDistance = jSONObject.optDouble("repair_distance", 0.0d);
        this.isRepair = jSONObject.optBoolean("is_repair");
        this.showRepair = jSONObject.optInt("show_status");
        this.version = jSONObject.optString(MapboxEvent.ATTRIBUTE_VERSION);
        this.versionCode = version2VersionCode(this.version);
        this.joinLeg = jSONObject.optBoolean("is_join_leg");
        JSONObject optJSONObject = jSONObject.optJSONObject("leg");
        if (optJSONObject != null) {
            this.leg = new Leg(optJSONObject);
        }
        this.maxPower = jSONObject.optDouble("max_watts");
        this.avgPower = jSONObject.optDouble("avg_watts");
        this.powerOutput = jSONObject.optDouble("kilojoules");
        this.max20MinutesPower = jSONObject.optInt("twenty_avg_watts");
        this.standardPower = jSONObject.optInt("np");
        this.powerIF = jSONObject.optDouble("watts_if");
        this.powerTSS = jSONObject.optInt("tss");
        this.powerFTP = jSONObject.optInt("ftp");
        this.courseId = jSONObject.optInt("course_id");
        optJSONObject = jSONObject.optJSONObject(MapboxEvent.KEY_COURSE);
        if (optJSONObject != null) {
            this.courseIf = optJSONObject.optDouble("course_if", 0.0d);
            this.courseName = optJSONObject.optString("course_name");
            this.courseEnName = optJSONObject.optString("course_en_name");
            this.courseTime = optJSONObject.optLong("course_time");
            this.courseTss = optJSONObject.optInt("course_tss");
        }
        this.isVirtualPower = jSONObject.optBoolean("is_virtual_watts");
        this.totalDecline = jSONObject.optDouble("total_decline");
        this.np = jSONObject.optDouble("np");
        this.sampleStatus = jSONObject.optInt("sample_status");
        this.isTraining = jSONObject.optBoolean("is_training");
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public String getActivityIdentifier() {
        return this.activityIdentifier;
    }

    public void setActivityIdentifier(String str) {
        this.activityIdentifier = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public double getVelocity() {
        return this.velocity;
    }

    public void setVelocity(double d) {
        this.velocity = d;
    }

    public double getElapsedTime() {
        return this.elapsedTime;
    }

    public void setElapsedTime(double d) {
        this.elapsedTime = d;
    }

    public double getCalories() {
        return this.calories;
    }

    public void setCalories(double d) {
        this.calories = d;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(long j) {
        this.stopTime = j;
    }

    public double getUphillDistance() {
        return this.uphillDistance;
    }

    public void setUphillDistance(double d) {
        this.uphillDistance = d;
    }

    public double getRiseTotal() {
        return this.riseTotal;
    }

    public void setRiseTotal(double d) {
        this.riseTotal = d;
    }

    public double getMaxVelocity() {
        return this.maxVelocity;
    }

    public void setMaxVelocity(double d) {
        this.maxVelocity = d;
    }

    public double getMaxAltitude() {
        return this.maxAltitude;
    }

    public void setMaxAltitude(double d) {
        this.maxAltitude = d;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.avatarUrl = str;
    }

    public String getActivityUrl() {
        return this.activityUrl;
    }

    public void setActivityUrl(String str) {
        this.activityUrl = str;
    }

    public boolean isFake() {
        return this.fake;
    }

    public void setFake(boolean z) {
        this.fake = z;
    }

    public boolean isSynced() {
        return this.synced;
    }

    public void setSynced(boolean z) {
        this.synced = z;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public boolean isHasReport() {
        return this.hasReport;
    }

    public void setHasReport(boolean z) {
        this.hasReport = z;
    }

    public void setNuked(boolean z) {
        this.nuked = z;
    }

    public boolean isNuked() {
        return this.nuked;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public int getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(int i) {
        this.isPrivate = i;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public int getBleDataType() {
        return this.bleDataType;
    }

    public void setBleDataType(int i) {
        this.bleDataType = i;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public double getCardiacRate() {
        return this.cardiacRate;
    }

    public void setCardiacRate(float f) {
        this.cardiacRate = (double) f;
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

    public String getCentralName() {
        return this.centralName;
    }

    public void setCentralName(String str) {
        this.centralName = str;
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

    public int getShowRepair() {
        return this.showRepair;
    }

    public void setShowRepair(int i) {
        this.showRepair = i;
    }

    public boolean isRepair() {
        return this.isRepair;
    }

    public void setRepair(boolean z) {
        this.isRepair = z;
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

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public boolean isJoinLeg() {
        return this.joinLeg;
    }

    public void setJoinLeg(boolean z) {
        this.joinLeg = z;
    }

    public Leg getLeg() {
        return this.leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
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

    public double getCourseIf() {
        return this.courseIf;
    }

    public void setCourseIf(double d) {
        this.courseIf = d;
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

    public boolean isVirtualPower() {
        return this.isVirtualPower;
    }

    public void setVirtualPower(boolean z) {
        this.isVirtualPower = z;
    }

    public double getTotalDecline() {
        return this.totalDecline;
    }

    public void setTotalDecline(double d) {
        this.totalDecline = d;
    }

    public double getNp() {
        return this.np;
    }

    public void setNp(double d) {
        this.np = d;
    }

    public boolean isTraining() {
        return this.isTraining;
    }

    public void setTraining(boolean z) {
        this.isTraining = z;
    }

    public int getSampleStatus() {
        return this.sampleStatus;
    }

    public void setSampleStatus(int i) {
        this.sampleStatus = i;
    }

    private int version2VersionCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split("\\.");
        if (split.length != 3) {
            return 0;
        }
        return ((Integer.parseInt(split[0]) << 16) + (Integer.parseInt(split[1]) << 8)) + Integer.parseInt(split[2]);
    }
}
