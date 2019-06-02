package com.beastbikes.android.modules.cycling.club.dto;

import com.beastbikes.android.utils.C2563k;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClubFeedImageTxtRecord extends ClubFeedBase {
    private List<ImageInfo> imageList;
    private int postId;
    private RecordInfo recordInfo;
    private String text;
    private String userId;

    public ClubFeedImageTxtRecord(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.postId = jSONObject.optInt("postId");
            this.clubId = jSONObject.optString("clubId");
            this.userId = jSONObject.optString("userId");
            JSONObject optJSONObject = jSONObject.optJSONObject("content");
            if (optJSONObject != null) {
                this.text = optJSONObject.optString(DirectionsCriteria.INSTRUCTIONS_TEXT);
                JSONArray optJSONArray = optJSONObject.optJSONArray("imageList");
                if (optJSONArray != null) {
                    this.imageList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null) {
                            this.imageList.add(new ImageInfo(optJSONObject2));
                        }
                    }
                }
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("recordInfo");
                if (!C2563k.m12869a(optJSONObject3)) {
                    this.recordInfo = new RecordInfo(optJSONObject3);
                }
            }
        }
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public List<ImageInfo> getImageList() {
        return this.imageList;
    }

    public void setImageList(List<ImageInfo> list) {
        this.imageList = list;
    }

    public RecordInfo getRecordInfo() {
        return this.recordInfo;
    }

    public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }

    public int getPostId() {
        return this.postId;
    }

    public void setPostId(int i) {
        this.postId = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }
}
