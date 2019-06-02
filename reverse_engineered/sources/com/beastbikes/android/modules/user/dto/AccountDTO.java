package com.beastbikes.android.modules.user.dto;

import ch.qos.logback.core.CoreConstants;
import com.beastbikes.android.modules.user.dao.entity.LocalAccounts;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import java.io.Serializable;
import org.json.JSONObject;

public class AccountDTO implements Serializable {
    public static final int STATUS_BOUND = 1;
    private String accessToken;
    private String accountName;
    private String authKey;
    private int authType;
    private String nickname;
    private String password;
    private int resID;
    private int status;
    private String thirdNick;
    private String username;

    public AccountDTO(String str, int i, int i2) {
        this.resID = i;
        this.accountName = str;
        this.authType = i2;
    }

    public AccountDTO(JSONObject jSONObject) {
        this.status = jSONObject.optInt("status");
        this.accessToken = jSONObject.optString("auth_token");
        this.authKey = jSONObject.optString("auth_key");
        this.authType = jSONObject.optInt("auth_type");
        this.thirdNick = jSONObject.optString("third_nick");
    }

    public AccountDTO(LocalUser localUser, int i) {
        this.accessToken = localUser.getUsername();
        this.authKey = localUser.getPassword();
        this.authType = i;
        this.thirdNick = localUser.getNickname();
    }

    public AccountDTO(LocalAccounts localAccounts) {
        this.status = (int) localAccounts.getStatus();
        this.accessToken = localAccounts.getAuthToken();
        this.authKey = localAccounts.getAuthkey();
        this.authType = (int) localAccounts.getAuthType();
        this.thirdNick = localAccounts.getThirdNick();
    }

    public void clone(AccountDTO accountDTO) {
        this.status = accountDTO.getStatus();
        this.accessToken = accountDTO.getAccessToken();
        this.authKey = accountDTO.getAuthKey();
        this.authType = accountDTO.getAuthType();
        this.thirdNick = accountDTO.getThirdNick();
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    public void setAuthKey(String str) {
        this.authKey = str;
    }

    public int getAuthType() {
        return this.authType;
    }

    public void setAuthType(int i) {
        this.authType = i;
    }

    public String getThirdNick() {
        return this.thirdNick;
    }

    public void setThirdNick(String str) {
        this.thirdNick = str;
    }

    public int getResID() {
        return this.resID;
    }

    public void setResID(int i) {
        this.resID = i;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String str) {
        this.accountName = str;
    }

    public String toString() {
        return "AccountDTO{authType='" + this.authType + CoreConstants.SINGLE_QUOTE_CHAR + ", status=" + this.status + ", accessToken='" + this.accessToken + CoreConstants.SINGLE_QUOTE_CHAR + ", authKey='" + this.authKey + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
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
}
