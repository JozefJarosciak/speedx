package io.rong.imkit.userInfoCache;

import io.rong.imkit.model.GroupUserInfo;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;

public interface IRongCacheListener {
    Group getGroupInfo(String str);

    GroupUserInfo getGroupUserInfo(String str, String str2);

    UserInfo getUserInfo(String str);

    void onDiscussionUpdated(Discussion discussion);

    void onGroupUpdated(Group group);

    void onGroupUserInfoUpdated(GroupUserInfo groupUserInfo);

    void onPublicServiceProfileUpdated(PublicServiceProfile publicServiceProfile);

    void onUserInfoUpdated(UserInfo userInfo);
}
