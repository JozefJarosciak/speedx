package com.tencent.bugly.proguard;

import java.util.ArrayList;

/* compiled from: BUGLY */
public final class ak extends C4447j implements Cloneable {
    /* renamed from: b */
    private static ArrayList<aj> f15576b;
    /* renamed from: a */
    public ArrayList<aj> f15577a = null;

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17637a(this.f15577a, 0);
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        if (f15576b == null) {
            f15576b = new ArrayList();
            f15576b.add(new aj());
        }
        this.f15577a = (ArrayList) c4455h.m17620a(f15576b, 0, true);
    }

    /* renamed from: a */
    public final void mo6072a(StringBuilder stringBuilder, int i) {
    }
}
