package com.beastbikes.android.modules.social.im.p074a;

import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$b */
final class c$b extends ResultCallback<Integer> {
    /* renamed from: a */
    final /* synthetic */ C1434c f11139a;

    c$b(C1434c c1434c) {
        this.f11139a = c1434c;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        m11962a((Integer) obj);
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        C1434c.g().error("RongCloud getTotalUnreadCount Error =" + rongIMClient$ErrorCode.getMessage());
    }

    /* renamed from: a */
    public void m11962a(Integer num) {
        C1434c.g().info("RongCloud getTotalUnreadCount success unreadCount=[" + num + "]");
        if (C1434c.a(this.f11139a) != null) {
            C1434c.a(this.f11139a).edit().putInt("beast.rongcloud.new.message.count", num.intValue()).apply();
        }
    }
}
