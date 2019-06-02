package io.rong.imkit;

import io.rong.imlib.model.UserInfo;
import java.util.List;

public interface RongIM$IGroupMemberCallback {
    void onGetGroupMembersResult(List<UserInfo> list);
}
