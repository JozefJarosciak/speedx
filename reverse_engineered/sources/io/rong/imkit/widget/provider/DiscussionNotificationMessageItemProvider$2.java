package io.rong.imkit.widget.provider;

import io.rong.imkit.utils.MessageProviderUserInfoHelper;
import io.rong.imlib.model.UserInfo;

class DiscussionNotificationMessageItemProvider$2 implements Runnable {
    final /* synthetic */ DiscussionNotificationMessageItemProvider this$0;
    final /* synthetic */ UserInfo val$userInfo;

    DiscussionNotificationMessageItemProvider$2(DiscussionNotificationMessageItemProvider discussionNotificationMessageItemProvider, UserInfo userInfo) {
        this.this$0 = discussionNotificationMessageItemProvider;
        this.val$userInfo = userInfo;
    }

    public void run() {
        if (MessageProviderUserInfoHelper.getInstance().isCacheUserId(this.val$userInfo.getUserId())) {
            MessageProviderUserInfoHelper.getInstance().notifyMessageUpdate(this.val$userInfo.getUserId());
        }
    }
}
