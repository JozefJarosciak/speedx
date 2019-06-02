package com.beastbikes.android.modules.cycling.club.dto;

import ch.qos.logback.core.CoreConstants;
import com.beastbikes.android.utils.C2555d;
import com.mapbox.mapboxsdk.style.layers.Property;
import java.io.Serializable;
import java.util.List;
import org.json.JSONObject;

public class ClubPhotoDTO implements Serializable {
    private String avatar;
    private String clubId;
    private List<ClubFeedComment> commentList;
    private int commentNum;
    private String content;
    private String createDate;
    private boolean hasLiked;
    private int headerCount;
    private long headerId;
    private int height;
    private String imageUrl;
    private boolean isEdit;
    private int likeNum;
    private List<ClubUser> likeUserList;
    private String nickName;
    private int photoId;
    private String remarks;
    private String userId;
    private int width;

    public ClubPhotoDTO(JSONObject jSONObject) {
        this.photoId = jSONObject.optInt("photoId");
        this.userId = jSONObject.optString("userId");
        this.clubId = jSONObject.optString("clubId");
        if (jSONObject.has("imageMeta")) {
            JSONObject optJSONObject = jSONObject.optJSONObject("imageMeta");
            this.imageUrl = optJSONObject.optString("url");
            this.width = optJSONObject.optInt(Property.ICON_TEXT_FIT_WIDTH);
            this.height = optJSONObject.optInt(Property.ICON_TEXT_FIT_HEIGHT);
        }
        this.likeNum = jSONObject.optInt("likeNum");
        this.hasLiked = jSONObject.optBoolean("hasLiked");
        this.commentNum = jSONObject.optInt("commentNum");
        this.createDate = jSONObject.optString("createdAt");
        this.content = jSONObject.optString("content");
        if (jSONObject.has("user")) {
            optJSONObject = jSONObject.optJSONObject("user");
            this.nickName = optJSONObject.optString("nickname");
            this.remarks = optJSONObject.optString("remarks");
            this.avatar = optJSONObject.optString("avatar");
        }
        this.headerId = C2555d.m12817g(this.createDate);
    }

    public ClubPhotoDTO(String str, int i, int i2, String str2) {
        this.imageUrl = str;
        this.width = i;
        this.height = i2;
        this.headerId = C2555d.m12805c(str2);
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public boolean isEdit() {
        return this.isEdit;
    }

    public void setEdit(boolean z) {
        this.isEdit = z;
    }

    public int getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(int i) {
        this.photoId = i;
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

    public void setIsEdit(boolean z) {
        this.isEdit = z;
    }

    public int getLikeNum() {
        return this.likeNum;
    }

    public void setLikeNum(int i) {
        this.likeNum = i;
    }

    public boolean isHasLiked() {
        return this.hasLiked;
    }

    public void setHasLiked(boolean z) {
        this.hasLiked = z;
    }

    public int getCommentNum() {
        return this.commentNum;
    }

    public void setCommentNum(int i) {
        this.commentNum = i;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setCreateDate(String str) {
        this.createDate = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public long getHeaderId() {
        return this.headerId;
    }

    public void setHeaderId(long j) {
        this.headerId = j;
    }

    public int getHeaderCount() {
        return this.headerCount;
    }

    public void setHeaderCount(int i) {
        this.headerCount = i;
    }

    public List<ClubUser> getLikeUserList() {
        return this.likeUserList;
    }

    public void setLikeUserList(List<ClubUser> list) {
        this.likeUserList = list;
    }

    public List<ClubFeedComment> getCommentList() {
        return this.commentList;
    }

    public void setCommentList(List<ClubFeedComment> list) {
        this.commentList = list;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }

    public String toString() {
        return "ClubPhotoDTO{photoId=" + this.photoId + ", userId='" + this.userId + CoreConstants.SINGLE_QUOTE_CHAR + ", clubId='" + this.clubId + CoreConstants.SINGLE_QUOTE_CHAR + ", content='" + this.content + CoreConstants.SINGLE_QUOTE_CHAR + ", imageUrl='" + this.imageUrl + CoreConstants.SINGLE_QUOTE_CHAR + ", width=" + this.width + ", height=" + this.height + ", isEdit=" + this.isEdit + ", likeNum=" + this.likeNum + ", hasLiked=" + this.hasLiked + ", commentNum=" + this.commentNum + ", createDate='" + this.createDate + CoreConstants.SINGLE_QUOTE_CHAR + ", nickName='" + this.nickName + CoreConstants.SINGLE_QUOTE_CHAR + ", avatar='" + this.avatar + CoreConstants.SINGLE_QUOTE_CHAR + ", headerId=" + this.headerId + ", headerCount=" + this.headerCount + CoreConstants.CURLY_RIGHT;
    }
}
