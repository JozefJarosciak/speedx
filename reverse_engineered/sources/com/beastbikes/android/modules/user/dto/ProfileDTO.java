package com.beastbikes.android.modules.user.dto;

import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0844a;
import com.avos.avoscloud.AVUtils;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.imlib.statistics.UserData;
import java.io.Serializable;
import org.json.JSONObject;

public class ProfileDTO implements Serializable {
    private static final long serialVersionUID = -878422186334908806L;
    private int achievementNum;
    private String avatar;
    private String birthday;
    private String city;
    private String clubId;
    private int clubLevel;
    private String clubName;
    private String country;
    private String createdAt;
    private String district;
    private boolean edited;
    private String email;
    private int fansNum;
    private int followNum;
    private int followStatu;
    private int ftp;
    private int gender;
    private int gridNum;
    private double height;
    private boolean isFriend = false;
    private int isOk;
    private long latestActivityTime;
    private int maxHeartRate;
    private int medalNum;
    private String mobile;
    private double monthlyDistance;
    private String nickname;
    private String objectId;
    private String province;
    private String remarks;
    private int sameNum;
    private int speedxId;
    private double totalDistance;
    private long totalElapsedTime;
    private int trainingId;
    private int trainingType;
    private String updatedAt;
    private String userId;
    private int userIntId;
    private String username;
    private double weeklyDistance;
    private double weight;

    public int getSpeedxId() {
        return this.speedxId;
    }

    public void setSpeedxId(int i) {
        this.speedxId = i;
    }

    public ProfileDTO(JSONObject jSONObject) {
        this.userId = jSONObject.optString("userId");
        this.username = jSONObject.optString(UserData.USERNAME_KEY);
        this.nickname = jSONObject.optString("nickname");
        this.email = jSONObject.optString("email");
        this.gender = Integer.parseInt(jSONObject.optString("sex", C0844a.f2048d));
        this.weight = jSONObject.optDouble("weight");
        this.height = jSONObject.optDouble(Property.ICON_TEXT_FIT_HEIGHT);
        this.country = jSONObject.optString(GeocodingCriteria.TYPE_COUNTRY);
        this.province = jSONObject.optString("province");
        this.city = jSONObject.optString("city");
        this.district = jSONObject.optString("area");
        this.totalDistance = jSONObject.optDouble("totalDistance", 0.0d);
        this.monthlyDistance = jSONObject.optDouble("monthlyDistance", 0.0d);
        this.weeklyDistance = jSONObject.optDouble("weeklyDistance", 0.0d);
        this.totalElapsedTime = jSONObject.optLong("totalTime", 0);
        this.latestActivityTime = jSONObject.optLong("lastestCyclingTime", 0);
        this.avatar = jSONObject.optString("avatar");
        this.edited = jSONObject.optBoolean("edited");
        this.objectId = jSONObject.optString(AVUtils.objectIdTag);
        this.gridNum = jSONObject.optInt("gridNum");
        this.sameNum = jSONObject.optInt("sameGrid");
        this.clubName = jSONObject.optString("clubName");
        this.userIntId = jSONObject.optInt("userIntId");
        this.updatedAt = jSONObject.optString("updatedAt");
        this.createdAt = jSONObject.optString("createdAt");
        this.clubId = jSONObject.optString("clubId");
        this.isOk = jSONObject.optInt("isOk");
        this.birthday = jSONObject.optString("birthday");
        this.remarks = jSONObject.optString("remarks");
        this.followNum = jSONObject.optInt("followNum");
        this.followStatu = jSONObject.optInt("followStatu");
        this.isFriend = jSONObject.optBoolean("isFriend");
        this.fansNum = jSONObject.optInt("fansNum");
        this.medalNum = jSONObject.optInt("badgeNum");
        this.speedxId = jSONObject.optInt("speedxId");
        this.maxHeartRate = jSONObject.optInt("cardiacRate");
        this.mobile = jSONObject.optString("mobile");
        this.achievementNum = jSONObject.optInt("achievement_count");
        this.trainingId = jSONObject.optInt("training_id");
        this.trainingType = jSONObject.optInt("training_type");
        this.clubLevel = jSONObject.optInt("level");
        this.ftp = jSONObject.optInt("ftp");
    }

    public ProfileDTO(String str, String str2, String str3) {
        this.userId = str;
        this.nickname = str2;
        this.avatar = str3;
    }

    public boolean isEdited() {
        return this.edited;
    }

    public void setEdited(boolean z) {
        this.edited = z;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
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

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public String getLocation() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!(TextUtils.isEmpty(this.country) || this.country.equals("null") || this.country.equals(EnvironmentCompat.MEDIA_UNKNOWN))) {
            stringBuilder.append(this.country);
        }
        if (!(TextUtils.isEmpty(this.province) || this.province.equals("null") || this.province.equals(EnvironmentCompat.MEDIA_UNKNOWN))) {
            stringBuilder.append(this.province);
        }
        if (!(TextUtils.isEmpty(this.city) || this.city.equals("null") || this.province.equals(this.city) || this.city.equals(EnvironmentCompat.MEDIA_UNKNOWN))) {
            stringBuilder.append(",").append(this.city);
        }
        return stringBuilder.toString();
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public long getTotalElapsedTime() {
        return this.totalElapsedTime;
    }

    public void setTotalElapsedTime(long j) {
        this.totalElapsedTime = j;
    }

    public long getLatestActivityTime() {
        return this.latestActivityTime;
    }

    public void setLatestActivityTime(long j) {
        this.latestActivityTime = j;
    }

    public double getWeeklyDistance() {
        return this.weeklyDistance;
    }

    public void setWeeklyDistance(double d) {
        this.weeklyDistance = d;
    }

    public double getMonthlyDistance() {
        return this.monthlyDistance;
    }

    public void setMonthlyDistance(double d) {
        this.monthlyDistance = d;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public int getGridNum() {
        return this.gridNum;
    }

    public void setGridNum(int i) {
        this.gridNum = i;
    }

    public int getSameNum() {
        return this.sameNum;
    }

    public void setSameNum(int i) {
        this.sameNum = i;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String str) {
        this.clubName = str;
    }

    public int getUserIntId() {
        return this.userIntId;
    }

    public void setUserIntId(int i) {
        this.userIntId = i;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }

    public int getIsOk() {
        return this.isOk;
    }

    public void setIsOk(int i) {
        this.isOk = i;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }

    public int getFollowNum() {
        return this.followNum;
    }

    public void setFollowNum(int i) {
        this.followNum = i;
    }

    public int getFollowStatu() {
        return this.followStatu;
    }

    public void setFollowStatu(int i) {
        this.followStatu = i;
    }

    public boolean isFriend() {
        return this.isFriend;
    }

    public void setIsFriend(boolean z) {
        this.isFriend = z;
    }

    public int getFansNum() {
        return this.fansNum;
    }

    public void setFansNum(int i) {
        this.fansNum = i;
    }

    public int getMedalNum() {
        return this.medalNum;
    }

    public void setMedalNum(int i) {
        this.medalNum = i;
    }

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public int getAchievementNum() {
        return this.achievementNum;
    }

    public void setAchievementNum(int i) {
        this.achievementNum = i;
    }

    public int getTrainingId() {
        return this.trainingId;
    }

    public void setTrainingId(int i) {
        this.trainingId = i;
    }

    public int getTrainingType() {
        return this.trainingType;
    }

    public void setTrainingType(int i) {
        this.trainingType = i;
    }

    public int getClubLevel() {
        return this.clubLevel;
    }

    public void setClubLevel(int i) {
        this.clubLevel = i;
    }

    public int getFtp() {
        return this.ftp;
    }

    public void setFtp(int i) {
        this.ftp = i;
    }
}
