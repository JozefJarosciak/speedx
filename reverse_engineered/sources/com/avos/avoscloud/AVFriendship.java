package com.avos.avoscloud;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AVFriendship<T extends AVUser> {
    List<T> followees = new LinkedList();
    List<T> followers = new LinkedList();
    AVUser user;

    static class AVFriendshipResponse {
        List<Map<String, Object>> followees;
        List<Map<String, Object>> followers;

        AVFriendshipResponse() {
        }

        public List<Map<String, Object>> getFollowers() {
            return this.followers;
        }

        public void setFollowers(List<Map<String, Object>> list) {
            this.followers = list;
        }

        public List<Map<String, Object>> getFollowees() {
            return this.followees;
        }

        public void setFollowees(List<Map<String, Object>> list) {
            this.followees = list;
        }
    }

    public List<T> getFollowers() {
        return this.followers;
    }

    protected void setFollowers(List<T> list) {
        this.followers = list;
    }

    public List<T> getFollowees() {
        return this.followees;
    }

    protected void setFollowees(List<T> list) {
        this.followees = list;
    }

    public AVUser getUser() {
        return this.user;
    }

    protected void setUser(AVUser aVUser) {
        this.user = aVUser;
    }
}
