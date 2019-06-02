package com.beastbikes.android.modules.social.im.p074a;

import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.OnReceiveUnreadCountChangedListener;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$1 */
class c$1 implements OnReceiveUnreadCountChangedListener {
    /* renamed from: a */
    final /* synthetic */ C1434c f11128a;

    c$1(C1434c c1434c) {
        this.f11128a = c1434c;
    }

    public void onMessageIncreased(int i) {
        C1434c.a(this.f11128a).edit().putInt("beast.club.dot.group.chat", C1434c.a(this.f11128a).getInt("beast.club.dot.group.chat", 0) + 1).apply();
        RongIM.getInstance().getTotalUnreadCount(C1434c.c(this.f11128a));
    }
}
