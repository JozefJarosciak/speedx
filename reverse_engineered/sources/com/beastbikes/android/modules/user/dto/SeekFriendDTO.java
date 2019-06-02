package com.beastbikes.android.modules.user.dto;

import java.io.Serializable;

public class SeekFriendDTO implements Serializable {
    private String nickName;
    private String seekValue;

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getSeekValue() {
        return this.seekValue;
    }

    public void setSeekValue(String str) {
        this.seekValue = str;
    }
}
