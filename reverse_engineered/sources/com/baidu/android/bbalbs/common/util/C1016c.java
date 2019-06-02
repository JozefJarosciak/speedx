package com.baidu.android.bbalbs.common.util;

import com.baidu.android.bbalbs.common.util.C1015b.C1012a;
import java.util.Comparator;

/* renamed from: com.baidu.android.bbalbs.common.util.c */
class C1016c implements Comparator<C1012a> {
    /* renamed from: a */
    final /* synthetic */ C1015b f2264a;

    C1016c(C1015b c1015b) {
        this.f2264a = c1015b;
    }

    /* renamed from: a */
    public int m3567a(C1012a c1012a, C1012a c1012a2) {
        int i = c1012a2.f2253b - c1012a.f2253b;
        return i == 0 ? (c1012a.f2255d && c1012a2.f2255d) ? 0 : c1012a.f2255d ? -1 : c1012a2.f2255d ? 1 : i : i;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m3567a((C1012a) obj, (C1012a) obj2);
    }
}
