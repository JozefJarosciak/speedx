package com.beastbikes.android.modules.social.im.ui.conversation;

import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;

class GroupSettingActivity$2 extends ResultCallback<ConversationNotificationStatus> {
    /* renamed from: a */
    final /* synthetic */ ConversationNotificationStatus f11180a;
    /* renamed from: b */
    final /* synthetic */ GroupSettingActivity f11181b;

    GroupSettingActivity$2(GroupSettingActivity groupSettingActivity, ConversationNotificationStatus conversationNotificationStatus) {
        this.f11181b = groupSettingActivity;
        this.f11180a = conversationNotificationStatus;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        m11984a((ConversationNotificationStatus) obj);
    }

    /* renamed from: a */
    public void m11984a(ConversationNotificationStatus conversationNotificationStatus) {
        GroupSettingActivity.b(this.f11181b).edit().putBoolean("beast.club.group.msg.dnd.switch", this.f11180a == ConversationNotificationStatus.DO_NOT_DISTURB).apply();
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
    }
}
