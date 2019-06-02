package com.qiniu.android.p188a;

import com.qiniu.android.dns.C4345a;
import java.net.URI;
import java.net.URISyntaxException;

/* compiled from: ServiceAddress */
/* renamed from: com.qiniu.android.a.a */
public final class C4308a {
    /* renamed from: a */
    public final URI f15015a;
    /* renamed from: b */
    public final String[] f15016b;

    public C4308a(String str, String[] strArr) {
        this.f15015a = C4308a.m17043a(str);
        if (strArr == null) {
            strArr = new String[0];
        }
        this.f15016b = strArr;
    }

    /* renamed from: a */
    private static URI m17043a(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m17044a(C4345a c4345a) {
        for (String a : this.f15016b) {
            c4345a.m17145a(this.f15015a.getHost(), a);
        }
    }
}
