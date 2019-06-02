package com.beastbikes.android.modules.user.dao.entity;

import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.framework.persistence.PersistentObject;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "accounts")
public class LocalAccounts implements PersistentObject {
    @DatabaseField(columnName = "authToken")
    private String authToken;
    @DatabaseField(canBeNull = false, columnName = "authType")
    private long authType;
    @DatabaseField(columnName = "authkey")
    private String authkey;
    @DatabaseField(canBeNull = false, columnName = "_id", id = true)
    private String id;
    @DatabaseField(columnName = "status")
    private long status;
    @DatabaseField(columnName = "thirdNick")
    private String thirdNick;
    @DatabaseField(canBeNull = false, columnName = "user_id")
    private String userId;

    public LocalAccounts(AccountDTO accountDTO) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.id = currentUser.getObjectId() + accountDTO.getAuthType();
            this.authType = (long) accountDTO.getAuthType();
            this.status = (long) accountDTO.getStatus();
            this.authToken = accountDTO.getAccessToken();
            this.thirdNick = accountDTO.getThirdNick();
            this.authkey = accountDTO.getAuthKey();
            this.userId = currentUser.getObjectId();
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public long getAuthType() {
        return this.authType;
    }

    public void setAuthType(long j) {
        this.authType = j;
    }

    public long getStatus() {
        return this.status;
    }

    public void setStatus(long j) {
        this.status = j;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String str) {
        this.authToken = str;
    }

    public String getThirdNick() {
        return this.thirdNick;
    }

    public void setThirdNick(String str) {
        this.thirdNick = str;
    }

    public String getAuthkey() {
        return this.authkey;
    }

    public void setAuthkey(String str) {
        this.authkey = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
