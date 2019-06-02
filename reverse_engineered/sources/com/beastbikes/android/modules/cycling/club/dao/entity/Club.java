package com.beastbikes.android.modules.cycling.club.dao.entity;

import android.os.Parcel;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "club")
public class Club implements PersistentObject {
    private static final long serialVersionUID = -4797455584982225068L;
    @DatabaseField(columnName = "activities")
    private int activities;
    @DatabaseField(columnName = "city")
    private String clubCity;
    @DatabaseField(columnName = "desc")
    private String clubDesc;
    @DatabaseField(columnName = "club_id")
    private String clubId;
    @DatabaseField(columnName = "clubLevel")
    private int clubLevel;
    @DatabaseField(columnName = "logo")
    private String clubLogo;
    @DatabaseField(columnName = "manager_id")
    private String clubManagerId;
    @DatabaseField(columnName = "members")
    private int clubMembers;
    @DatabaseField(columnName = "milestone")
    private double clubMilestone;
    @DatabaseField(columnName = "name")
    private String clubName;
    @DatabaseField(columnName = "notice")
    private String clubNotice;
    @DatabaseField(columnName = "province")
    private String clubProvince;
    @DatabaseField(columnName = "score")
    private double clubScore;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "isPrivate")
    private int isPrivate;
    @DatabaseField(columnName = "level")
    private int level;
    @DatabaseField(columnName = "linkTo")
    private long linkTo;
    @DatabaseField(columnName = "max_members")
    private int maxMembers;
    @DatabaseField(columnName = "rank")
    private int rank;
    @DatabaseField(columnName = "status")
    private int status;
    @DatabaseField(columnName = "type")
    private int type;
    @DatabaseField(columnName = "user_id")
    private String userId;

    public Club(Parcel parcel) {
        this.id = parcel.readString();
        this.clubId = parcel.readString();
        this.clubName = parcel.readString();
        this.clubDesc = parcel.readString();
        this.clubManagerId = parcel.readString();
        this.maxMembers = parcel.readInt();
        this.clubMembers = parcel.readInt();
        this.clubScore = parcel.readDouble();
        this.clubMilestone = parcel.readDouble();
        this.clubLogo = parcel.readString();
        this.clubCity = parcel.readString();
        this.userId = parcel.readString();
        this.level = parcel.readInt();
        this.status = parcel.readInt();
        this.clubNotice = parcel.readString();
        this.activities = parcel.readInt();
        this.clubProvince = parcel.readString();
        this.rank = parcel.readInt();
        this.isPrivate = parcel.readInt();
        this.clubLevel = parcel.readInt();
        this.type = parcel.readInt();
        this.linkTo = parcel.readLong();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String str) {
        this.clubName = str;
    }

    public String getClubDesc() {
        return this.clubDesc;
    }

    public void setClubDesc(String str) {
        this.clubDesc = str;
    }

    public String getClubManagerId() {
        return this.clubManagerId;
    }

    public void setClubManagerId(String str) {
        this.clubManagerId = str;
    }

    public int getClubMembers() {
        return this.clubMembers;
    }

    public void setClubMembers(int i) {
        this.clubMembers = i;
    }

    public double getClubScore() {
        return this.clubScore;
    }

    public void setClubScore(double d) {
        this.clubScore = d;
    }

    public double getClubMilestone() {
        return this.clubMilestone;
    }

    public void setClubMilestone(double d) {
        this.clubMilestone = d;
    }

    public String getClubLogo() {
        return this.clubLogo;
    }

    public void setClubLogo(String str) {
        this.clubLogo = str;
    }

    public String getClubCity() {
        return this.clubCity;
    }

    public void setClubCity(String str) {
        this.clubCity = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getClubProvince() {
        return this.clubProvince;
    }

    public void setClubProvince(String str) {
        this.clubProvince = str;
    }

    public int getActivities() {
        return this.activities;
    }

    public void setActivities(int i) {
        this.activities = i;
    }

    public String getClubNotice() {
        return this.clubNotice;
    }

    public void setClubNotice(String str) {
        this.clubNotice = str;
    }

    public int getMaxMembers() {
        return this.maxMembers;
    }

    public void setMaxMembers(int i) {
        this.maxMembers = i;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public int getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(int i) {
        this.isPrivate = i;
    }

    public int getClubLevel() {
        return this.clubLevel;
    }

    public void setClubLevel(int i) {
        this.clubLevel = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public long getLinkTo() {
        return this.linkTo;
    }

    public void setLinkTo(long j) {
        this.linkTo = j;
    }
}
