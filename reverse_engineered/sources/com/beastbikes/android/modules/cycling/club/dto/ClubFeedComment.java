package com.beastbikes.android.modules.cycling.club.dto;

import android.text.TextUtils;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2563k;
import java.io.Serializable;
import org.json.JSONObject;

public class ClubFeedComment implements Serializable {
    private int cid;
    private String content;
    private String createdAt;
    private int fid;
    private int replyId;
    private ClubUser replyUser;
    private ClubUser user;

    public ClubFeedComment(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.cid = jSONObject.optInt("cid");
            this.fid = jSONObject.optInt("fid");
            if (jSONObject.has("photoId")) {
                this.fid = jSONObject.optInt("photoId");
            }
            this.createdAt = jSONObject.optString("createdAt");
            if (!TextUtils.isEmpty(this.createdAt)) {
                this.createdAt = C2555d.m12816f(this.createdAt);
            }
            this.replyId = jSONObject.optInt("replyId");
            this.content = jSONObject.optString("content");
            this.user = new ClubUser(jSONObject.optJSONObject("user"));
            if (!C2563k.m12869a(jSONObject.optJSONObject("replyUser"))) {
                this.replyUser = new ClubUser(jSONObject.optJSONObject("replyUser"));
            }
        }
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int i) {
        this.cid = i;
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int i) {
        this.fid = i;
    }

    public int getReplyId() {
        return this.replyId;
    }

    public void setReplyId(int i) {
        this.replyId = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public ClubUser getUser() {
        return this.user;
    }

    public void setUser(ClubUser clubUser) {
        this.user = clubUser;
    }

    public ClubUser getReplyUser() {
        return this.replyUser;
    }

    public void setReplyUser(ClubUser clubUser) {
        this.replyUser = clubUser;
    }

    public String getCreateAt() {
        return this.createdAt;
    }

    public void setCreateAt(String str) {
        this.createdAt = str;
    }
}
