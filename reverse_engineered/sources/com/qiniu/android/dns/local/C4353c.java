package com.qiniu.android.dns.local;

import com.qiniu.android.dns.C4346b;
import com.qiniu.android.dns.C4347c;
import com.qiniu.android.dns.C4349f;
import com.qiniu.android.dns.NetworkInfo;
import java.io.IOException;

/* compiled from: HijackingDetectWrapper */
/* renamed from: com.qiniu.android.dns.local.c */
public final class C4353c implements C4347c {
    /* renamed from: a */
    private final C4356e f15129a;

    public C4353c(C4356e c4356e) {
        this.f15129a = c4356e;
    }

    /* renamed from: a */
    public C4349f[] mo6027a(C4346b c4346b, NetworkInfo networkInfo) throws IOException {
        int i;
        int i2 = 0;
        C4349f[] a = this.f15129a.mo6027a(c4346b, networkInfo);
        if (c4346b.f15121b) {
            for (C4349f a2 : a) {
                if (a2.m17152a()) {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (i == 0) {
                throw new DnshijackingException(c4346b.f15120a, this.f15129a.f15134a.getHostAddress());
            }
        }
        if (c4346b.f15122c != 0) {
            i = a.length;
            while (i2 < i) {
                C4349f c4349f = a[i2];
                if (c4349f.m17152a() || c4349f.f15127c <= c4346b.f15122c) {
                    i2++;
                } else {
                    throw new DnshijackingException(c4346b.f15120a, this.f15129a.f15134a.getHostAddress(), c4349f.f15127c);
                }
            }
        }
        return a;
    }
}
