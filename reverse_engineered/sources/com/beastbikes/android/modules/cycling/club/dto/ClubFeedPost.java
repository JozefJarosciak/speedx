package com.beastbikes.android.modules.cycling.club.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class ClubFeedPost implements Serializable {
    public static final int TYPE_ABLUM = 1;
    public static final int TYPE_FEED = 0;
    private String clubId;
    private String content;
    private int needSync;
    private int postError;
    private List<ImageInfo> postImageList;
    private String sportIdentify;
    private int type;

    public ClubFeedPost(int i, String str, String str2, String str3, int i2) {
        this.needSync = i;
        this.clubId = str;
        this.content = str2;
        this.sportIdentify = str3;
        this.type = i2;
    }

    public int isNeedSync() {
        return this.needSync;
    }

    public void setNeedSync(int i) {
        this.needSync = i;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getSportIdentify() {
        return this.sportIdentify;
    }

    public void setSportIdentify(String str) {
        this.sportIdentify = str;
    }

    public int getPostError() {
        return this.postError;
    }

    public void setPostError(int i) {
        this.postError = i;
    }

    public List<ImageInfo> getCompletedList() {
        return this.postImageList;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void addInComplete(ImageInfo imageInfo) {
        if (this.postImageList == null) {
            this.postImageList = new ArrayList();
        }
        this.postImageList.add(imageInfo);
    }

    public void setPostImageList(List<ImageInfo> list) {
        this.postImageList = list;
    }

    public String getCompleteListJsonStr() {
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (this.postImageList != null && i < this.postImageList.size()) {
            try {
                jSONArray.put(i, ((ImageInfo) this.postImageList.get(i)).obj2Json());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }
        return jSONArray.toString();
    }
}
