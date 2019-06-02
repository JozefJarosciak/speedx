package com.journeyapps.barcodescanner;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

/* compiled from: DecoderResultPointCallback */
/* renamed from: com.journeyapps.barcodescanner.g */
public class C4161g implements ResultPointCallback {
    /* renamed from: a */
    private C4159e f14806a;

    /* renamed from: a */
    public void m16673a(C4159e c4159e) {
        this.f14806a = c4159e;
    }

    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        if (this.f14806a != null) {
            this.f14806a.foundPossibleResultPoint(resultPoint);
        }
    }
}
