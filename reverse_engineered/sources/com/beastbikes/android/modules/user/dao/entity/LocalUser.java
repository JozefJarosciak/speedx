package com.beastbikes.android.modules.user.dao.entity;

import android.text.TextUtils;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@DatabaseTable(tableName = "user")
public class LocalUser implements PersistentObject {
    private static final long serialVersionUID = 4490749315232538948L;
    @DatabaseField(columnName = "achievement_num")
    private int achievementNum;
    @DatabaseField(columnName = "avatar")
    private String avatar;
    @DatabaseField(columnName = "birthday")
    private String birthday;
    @DatabaseField(columnName = "city")
    private String city;
    @DatabaseField(columnName = "clubId")
    private String clubId;
    @DatabaseField(columnName = "clubName")
    private String clubName;
    @DatabaseField(columnName = "createdAt")
    private String createdAt;
    @DatabaseField(columnName = "district")
    private String district;
    @DatabaseField(columnName = "edited")
    private long edited;
    @DatabaseField(columnName = "email")
    private String email;
    @DatabaseField(columnName = "fans_num")
    private int fansNum;
    @DatabaseField(columnName = "follow_status")
    private int followStatus;
    @DatabaseField(columnName = "follower_num")
    private int followerNum;
    @DatabaseField(columnName = "gender")
    private int gender = 1;
    @DatabaseField(columnName = "gridNum")
    private long gridNum;
    @DatabaseField(columnName = "height")
    private double height;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "isOk")
    private long isOk;
    @DatabaseField(columnName = "latest_activity_time")
    private long latestActivityTime;
    @DatabaseField(columnName = "max_heart_rate")
    private int maxHeartRate;
    @DatabaseField(columnName = "medal_num")
    private int medalNum;
    @DatabaseField(columnName = "mobile")
    private String mobile;
    @DatabaseField(columnName = "monthlyDistance")
    private double monthlyDistance;
    @DatabaseField(columnName = "nickname")
    private String nickname;
    @DatabaseField(columnName = "objectId")
    private String objectId;
    private transient String password;
    @DatabaseField(columnName = "province")
    private String province;
    @DatabaseField(columnName = "sameGrid")
    private long sameGrid;
    @DatabaseField(columnName = "speedx_id")
    private int speedxId;
    @DatabaseField(columnName = "total_distance")
    private double totalDistance;
    @DatabaseField(columnName = "total_elapsed_time")
    private double totalElapsedTime;
    @DatabaseField(columnName = "updatedAt")
    private String updatedAt;
    @DatabaseField(columnName = "userId")
    private String userId;
    @DatabaseField(columnName = "userIntId")
    private long userIntId;
    @DatabaseField(canBeNull = false, columnName = "username")
    private String username;
    @DatabaseField(columnName = "weeklyDistance")
    private double weeklyDistance;
    @DatabaseField(columnName = "weight")
    private double weight;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d) {
        this.weight = d;
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
        List arrayList = new ArrayList();
        CharSequence province = getProvince();
        if (!TextUtils.isEmpty(province)) {
            arrayList.add(province);
        }
        province = getCity();
        if (!TextUtils.isEmpty(province)) {
            arrayList.add(province);
        }
        province = getDistrict();
        if (!TextUtils.isEmpty(province)) {
            arrayList.add(province);
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuilder.append((String) it.next());
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
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

    public long getLatestActivityTime() {
        return this.latestActivityTime;
    }

    public void setLatestActivityTime(long j) {
        this.latestActivityTime = j;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public long getUserIntId() {
        return this.userIntId;
    }

    public void setUserIntId(long j) {
        this.userIntId = j;
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

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public long getIsOk() {
        return this.isOk;
    }

    public void setIsOk(long j) {
        this.isOk = j;
    }

    public long getGridNum() {
        return this.gridNum;
    }

    public void setGridNum(long j) {
        this.gridNum = j;
    }

    public long getEdited() {
        return this.edited;
    }

    public void setEdited(long j) {
        this.edited = j;
    }

    public double getWeeklyDistance() {
        return this.weeklyDistance;
    }

    public void setWeeklyDistance(double d) {
        this.weeklyDistance = d;
    }

    public long getSameGrid() {
        return this.sameGrid;
    }

    public void setSameGrid(long j) {
        this.sameGrid = j;
    }

    public double getMonthlyDistance() {
        return this.monthlyDistance;
    }

    public void setMonthlyDistance(double d) {
        this.monthlyDistance = d;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String str) {
        this.clubName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public int getFansNum() {
        return this.fansNum;
    }

    public void setFansNum(int i) {
        this.fansNum = i;
    }

    public int getFollowerNum() {
        return this.followerNum;
    }

    public void setFollowerNum(int i) {
        this.followerNum = i;
    }

    public int getFollowStatus() {
        return this.followStatus;
    }

    public void setFollowStatus(int i) {
        this.followStatus = i;
    }

    public int getMedalNum() {
        return this.medalNum;
    }

    public void setMedalNum(int i) {
        this.medalNum = i;
    }

    public int getSpeedxId() {
        return this.speedxId;
    }

    public void setSpeedxId(int i) {
        this.speedxId = i;
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
}
