package com.tencent.mm.sdk.diffdev.p200a;

import com.tencent.mm.sdk.diffdev.OAuthListener;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tencent.mm.sdk.diffdev.a.c */
final class C4503c implements Runnable {
    final /* synthetic */ C4502b ah;

    C4503c(C4502b c4502b) {
        this.ah = c4502b;
    }

    public final void run() {
        List<OAuthListener> arrayList = new ArrayList();
        arrayList.addAll(this.ah.ag.ad);
        for (OAuthListener onQrcodeScanned : arrayList) {
            onQrcodeScanned.onQrcodeScanned();
        }
    }
}
