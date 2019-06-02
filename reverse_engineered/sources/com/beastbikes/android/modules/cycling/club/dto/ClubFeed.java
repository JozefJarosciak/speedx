package com.beastbikes.android.modules.cycling.club.dto;

import com.beastbikes.android.utils.C2563k;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClubFeed implements Serializable {
    public static final int FEED_TYPE_ACTIVITY = 2;
    public static final int FEED_TYPE_MAX = 4;
    public static final int FEED_TYPE_NOTICE = 3;
    public static final int FEED_TYPE_TEXT_IMAGE_RECORD = 1;
    public static final int STATE_DOING = 1;
    public static final int STATE_DONE = 0;
    private ClubFeedActivity activity;
    private int feedType;
    private int fid;
    private ClubFeedImageTxtRecord imageTxt;
    private ClubFeedNotice notice;
    private ClubFeedPost post;
    private long stamp;
    private int state = 0;

    public ClubFeed(int i) {
        this.fid = i;
    }

    public ClubFeed(JSONObject jSONObject) {
        int i = 0;
        ClubFeedBase clubFeedBase = null;
        if (jSONObject != null) {
            ClubUser clubUser;
            List list;
            List arrayList;
            JSONObject optJSONObject;
            List list2;
            this.feedType = jSONObject.optInt("feedType");
            this.fid = jSONObject.optInt("fid");
            this.stamp = jSONObject.optLong("stamp");
            this.state = jSONObject.optInt("state");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("feedInfo");
            JSONObject optJSONObject3 = jSONObject.optJSONObject("user");
            if (C2563k.m12869a(optJSONObject3)) {
                clubUser = null;
            } else {
                clubUser = new ClubUser(optJSONObject3);
            }
            int optInt = jSONObject.optInt("likeCount");
            int optInt2 = jSONObject.optInt("commentCount");
            JSONArray optJSONArray = jSONObject.optJSONArray("likeList");
            if (C2563k.m12868a(optJSONArray)) {
                list = null;
            } else {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        arrayList.add(new ClubUser(optJSONObject));
                    }
                }
                list = arrayList;
            }
            optJSONArray = jSONObject.optJSONArray("commentList");
            if (C2563k.m12868a(optJSONArray)) {
                list2 = null;
            } else {
                arrayList = new ArrayList();
                while (i < optJSONArray.length()) {
                    optJSONObject = optJSONArray.optJSONObject(i);
                    if (!C2563k.m12869a(optJSONObject)) {
                        arrayList.add(new ClubFeedComment(optJSONObject));
                    }
                    i++;
                }
                list2 = arrayList;
            }
            switch (this.feedType) {
                case 1:
                    this.imageTxt = new ClubFeedImageTxtRecord(optJSONObject2);
                    clubFeedBase = this.imageTxt;
                    break;
                case 2:
                    this.activity = new ClubFeedActivity(optJSONObject2);
                    clubFeedBase = this.activity;
                    break;
                case 3:
                    this.notice = new ClubFeedNotice(optJSONObject2);
                    clubFeedBase = this.notice;
                    break;
            }
            if (clubFeedBase != null) {
                clubFeedBase.setLikeCount(optInt);
                clubFeedBase.setCommentCount(optInt2);
                clubFeedBase.setLikeList(list);
                clubFeedBase.setCommentList(list2);
                clubFeedBase.setUser(clubUser);
                clubFeedBase.setFid(this.fid);
                clubFeedBase.setFeedType(this.feedType);
                clubFeedBase.setHasLiked(jSONObject.optBoolean("hasLiked"));
                clubFeedBase.setDate(new Date((this.stamp * 1000) + ((long) TimeZone.getDefault().getRawOffset())));
            }
        }
    }

    public ClubFeedImageTxtRecord getImageTxt() {
        return this.imageTxt;
    }

    public void setImageTxt(ClubFeedImageTxtRecord clubFeedImageTxtRecord) {
        this.imageTxt = clubFeedImageTxtRecord;
    }

    public int getFeedType() {
        return this.feedType;
    }

    public void setFeedType(int i) {
        this.feedType = i;
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int i) {
        this.fid = i;
    }

    public long getStamp() {
        return this.stamp;
    }

    public void setStamp(long j) {
        this.stamp = j;
    }

    public ClubFeedNotice getNotice() {
        return this.notice;
    }

    public void setNotice(ClubFeedNotice clubFeedNotice) {
        this.notice = clubFeedNotice;
    }

    public ClubFeedActivity getActivity() {
        return this.activity;
    }

    public void setActivity(ClubFeedActivity clubFeedActivity) {
        this.activity = clubFeedActivity;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public ClubFeedPost getPost() {
        return this.post;
    }

    public void setPost(ClubFeedPost clubFeedPost) {
        this.post = clubFeedPost;
    }
}
