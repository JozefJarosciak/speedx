package com.beastbikes.android.modules.cycling.club.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClubFeedBase implements Serializable {
    protected String clubId;
    private int commentCount;
    private List<ClubFeedComment> commentList;
    private Date date;
    private int feedType;
    private int fid;
    private boolean hasLiked = false;
    private int likeCount;
    private List<ClubUser> likeList;
    private int status;
    private String text;
    private ClubUser user;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getFid() {
        return this.fid;
    }

    public void setFid(int i) {
        this.fid = i;
    }

    public int getFeedType() {
        return this.feedType;
    }

    public void setFeedType(int i) {
        this.feedType = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClubUser getUser() {
        return this.user;
    }

    public void setUser(ClubUser clubUser) {
        this.user = clubUser;
    }

    public int getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(int i) {
        this.likeCount = i;
    }

    public List<ClubUser> getLikeList() {
        return this.likeList;
    }

    public void setLikeList(List<ClubUser> list) {
        this.likeList = list;
    }

    public int getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(int i) {
        this.commentCount = i;
    }

    public List<ClubFeedComment> getCommentList() {
        return this.commentList;
    }

    public void setCommentList(List<ClubFeedComment> list) {
        this.commentList = list;
    }

    public void addComment(ClubFeedComment clubFeedComment) {
        if (this.commentList == null) {
            this.commentList = new ArrayList();
        }
        this.commentList.add(clubFeedComment);
        this.commentCount++;
    }

    public boolean isHasLiked() {
        return this.hasLiked;
    }

    public void addHasLiked(ClubUser clubUser) {
        if (this.likeList == null) {
            this.likeList = new ArrayList();
        }
        if (!this.likeList.contains(clubUser)) {
            if (this.likeList.size() >= 6) {
                this.likeList.remove(0);
            }
            this.likeList.add(0, clubUser);
            this.likeCount++;
        }
    }

    public void removeLiked(String str) {
        int i = 0;
        while (this.likeList != null && i < this.likeList.size()) {
            if (((ClubUser) this.likeList.get(i)).getUserId().equals(str)) {
                this.likeList.remove(i);
                this.likeCount--;
                return;
            }
            i++;
        }
    }

    public void removeComment(int i) {
        int i2 = 0;
        while (this.commentList != null && i2 < this.commentList.size()) {
            if (((ClubFeedComment) this.commentList.get(i2)).getCid() == i) {
                this.commentList.remove(i2);
                this.commentCount = this.commentList.size();
                return;
            }
            i2++;
        }
    }

    public void setHasLiked(boolean z) {
        this.hasLiked = z;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }
}
