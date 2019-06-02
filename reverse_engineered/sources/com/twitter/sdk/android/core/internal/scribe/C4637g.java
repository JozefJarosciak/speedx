package com.twitter.sdk.android.core.internal.scribe;

import java.util.List;

/* compiled from: ScribeEventFactory */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.g */
public class C4637g {
    /* renamed from: a */
    public static C1503f m18349a(C1502c c1502c, long j, String str, String str2, List<C1506j> list) {
        String str3 = c1502c.f7057a;
        Object obj = -1;
        switch (str3.hashCode()) {
            case 114757:
                if (str3.equals("tfw")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new C1510l(c1502c, j, str, str2, list);
            default:
                return new C1508k(c1502c, j, str, str2, list);
        }
    }
}
