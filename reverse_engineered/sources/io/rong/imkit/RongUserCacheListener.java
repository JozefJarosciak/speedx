package io.rong.imkit;

import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.userInfoCache.IRongCacheListener;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;

public class RongUserCacheListener implements IRongCacheListener {
    public void onUserInfoUpdated(UserInfo userInfo) {
        if (userInfo != null) {
            RongContext.getInstance().getEventBus().post(userInfo);
        }
    }

    public void onGroupUserInfoUpdated(GroupUserInfo groupUserInfo) {
        if (groupUserInfo != null) {
            RongContext.getInstance().getEventBus().post(groupUserInfo);
        }
    }

    public void onGroupUpdated(Group group) {
        if (group != null) {
            RongContext.getInstance().getEventBus().post(group);
        }
    }

    public void onDiscussionUpdated(Discussion discussion) {
        if (discussion != null) {
            RongContext.getInstance().getEventBus().post(discussion);
        }
    }

    public void onPublicServiceProfileUpdated(PublicServiceProfile publicServiceProfile) {
        if (publicServiceProfile != null) {
            RongContext.getInstance().getEventBus().post(publicServiceProfile);
        }
    }

    public UserInfo getUserInfo(String str) {
        if (RongContext.getInstance().getUserInfoProvider() != null) {
            return RongContext.getInstance().getUserInfoProvider().getUserInfo(str);
        }
        return null;
    }

    public GroupUserInfo getGroupUserInfo(String str, String str2) {
        if (RongContext.getInstance().getGroupUserInfoProvider() != null) {
            return RongContext.getInstance().getGroupUserInfoProvider().getGroupUserInfo(str, str2);
        }
        return null;
    }

    public Group getGroupInfo(String str) {
        if (RongContext.getInstance().getGroupInfoProvider() != null) {
            return RongContext.getInstance().getGroupInfoProvider().getGroupInfo(str);
        }
        return null;
    }
}
