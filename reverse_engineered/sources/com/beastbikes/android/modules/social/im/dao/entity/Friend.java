package com.beastbikes.android.modules.social.im.dao.entity;

import android.os.Parcel;
import com.beastbikes.android.p057b.C1572g;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "friend")
public class Friend implements C1572g, PersistentObject {
    private static final long serialVersionUID = -8227874145529936392L;
    @DatabaseField(columnName = "friend_avatar")
    private String avatar;
    @DatabaseField(columnName = "create_time")
    private long createTime;
    @DatabaseField(columnName = "friend_id")
    private String friendId;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "friend_name")
    private String nickname;
    @DatabaseField(columnName = "friend_remarks")
    private String remarks;
    @DatabaseField(columnName = "status")
    private int status;
    @DatabaseField(columnName = "user_id")
    private String userId;

    public Friend(Parcel parcel) {
        this.id = parcel.readString();
        this.userId = parcel.readString();
        this.friendId = parcel.readString();
        this.nickname = parcel.readString();
        this.avatar = parcel.readString();
        this.status = parcel.readInt();
        this.createTime = parcel.readLong();
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

    public String getFriendId() {
        return this.friendId;
    }

    public void setFriendId(String str) {
        this.friendId = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }
}
