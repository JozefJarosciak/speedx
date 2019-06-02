package com.tencent.mm.sdk.p198b;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.tencent.mm.sdk.p198b.C4489b.C4488a;

/* renamed from: com.tencent.mm.sdk.b.c */
final class C4490c implements C4488a {
    private Handler handler = new Handler(Looper.getMainLooper());

    C4490c() {
    }

    /* renamed from: f */
    public final void mo6078f(String str, String str2) {
        if (C4489b.level <= 2) {
            Log.i(str, str2);
        }
    }

    /* renamed from: g */
    public final void mo6079g(String str, String str2) {
        if (C4489b.level <= 1) {
            Log.d(str, str2);
        }
    }

    public final int getLogLevel() {
        return C4489b.level;
    }

    /* renamed from: h */
    public final void mo6081h(String str, String str2) {
        if (C4489b.level <= 3) {
            Log.w(str, str2);
        }
    }

    /* renamed from: i */
    public final void mo6082i(String str, String str2) {
        if (C4489b.level <= 4) {
            Log.e(str, str2);
        }
    }
}
