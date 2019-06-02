package com.beastbikes.android.modules.social.im.p074a;

import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$8 */
class c$8 extends RongIMClient$OperationCallback {
    /* renamed from: a */
    final /* synthetic */ C1434c f11136a;

    c$8(C1434c c1434c) {
        this.f11136a = c1434c;
    }

    public void onSuccess() {
        C1434c.c(this.f11136a, false);
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        C1434c.c(this.f11136a, false);
    }
}
