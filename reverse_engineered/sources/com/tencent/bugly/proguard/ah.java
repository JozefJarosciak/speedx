package com.tencent.bugly.proguard;

import java.util.ArrayList;

/* compiled from: BUGLY */
public final class ah extends C4447j implements Cloneable {
    /* renamed from: c */
    private static ArrayList<String> f15540c;
    /* renamed from: a */
    private String f15541a = "";
    /* renamed from: b */
    private ArrayList<String> f15542b = null;

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17636a(this.f15541a, 0);
        if (this.f15542b != null) {
            c4456i.m17637a(this.f15542b, 1);
        }
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        this.f15541a = c4455h.m17625b(0, true);
        if (f15540c == null) {
            f15540c = new ArrayList();
            f15540c.add("");
        }
        this.f15542b = (ArrayList) c4455h.m17620a(f15540c, 1, false);
    }

    /* renamed from: a */
    public final void mo6072a(StringBuilder stringBuilder, int i) {
    }
}
