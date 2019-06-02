package com.beastbikes.android.modules.social.im.ui.conversation;

import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;

class GroupSettingActivity$3 extends ResultCallback<ConversationNotificationStatus> {
    /* renamed from: a */
    final /* synthetic */ GroupSettingActivity f11182a;

    GroupSettingActivity$3(GroupSettingActivity groupSettingActivity) {
        this.f11182a = groupSettingActivity;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        m11985a((ConversationNotificationStatus) obj);
    }

    /* renamed from: a */
    public void m11985a(ConversationNotificationStatus conversationNotificationStatus) {
        GroupSettingActivity.a(this.f11182a, conversationNotificationStatus.getValue());
        this.f11182a.f6174f.setChecked(GroupSettingActivity.c(this.f11182a) == 0);
        this.f11182a.f6174f.setEnabled(true);
        this.f11182a.f6174f.setClickable(true);
        this.f11182a.f6174f.setTag(Boolean.valueOf(true));
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
    }
}
