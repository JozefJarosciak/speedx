package com.beastbikes.android.modules.social.im.p074a;

import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$6 */
class c$6 extends RongIMClient$OperationCallback {
    /* renamed from: a */
    final /* synthetic */ C1434c f11134a;

    c$6(C1434c c1434c) {
        this.f11134a = c1434c;
    }

    public void onSuccess() {
        C1434c.c(this.f11134a, true);
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        C1434c.c(this.f11134a, true);
    }
}
