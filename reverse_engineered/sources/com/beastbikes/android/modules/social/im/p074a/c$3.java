package com.beastbikes.android.modules.social.im.p074a;

import android.content.SharedPreferences.Editor;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$3 */
class c$3 extends ResultCallback<ConversationNotificationStatus> {
    /* renamed from: a */
    final /* synthetic */ String f11130a;
    /* renamed from: b */
    final /* synthetic */ C1434c f11131b;

    c$3(C1434c c1434c, String str) {
        this.f11131b = c1434c;
        this.f11130a = str;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        m11959a((ConversationNotificationStatus) obj);
    }

    /* renamed from: a */
    public void m11959a(ConversationNotificationStatus conversationNotificationStatus) {
        boolean z = false;
        int value = conversationNotificationStatus.getValue();
        if (C1434c.a(this.f11131b) == null) {
            C1434c.a(this.f11131b, C1434c.b(this.f11131b).getSharedPreferences(this.f11130a, 0));
        }
        Editor edit = C1434c.a(this.f11131b).edit();
        String str = "beast.club.group.msg.dnd.switch";
        if (value == 0) {
            z = true;
        }
        edit.putBoolean(str, z).apply();
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
    }
}
