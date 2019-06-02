package com.beastbikes.android.modules.cycling.grid.dao.entity;

import android.os.Parcel;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "grid")
public class Grid implements PersistentObject {
    @DatabaseField(columnName = "count")
    private int count;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "unlock_at")
    private String unlockAt;
    @DatabaseField(columnName = "user_id")
    private String userId;

    public Grid(Parcel parcel) {
        this.id = parcel.readString();
        this.count = parcel.readInt();
        this.unlockAt = parcel.readString();
        this.userId = parcel.readString();
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

    public String getUnlockAt() {
        return this.unlockAt;
    }

    public void setUnlockAt(String str) {
        this.unlockAt = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }
}
