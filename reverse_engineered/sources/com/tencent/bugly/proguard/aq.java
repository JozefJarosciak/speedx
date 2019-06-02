package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class aq extends C4447j implements Cloneable {
    /* renamed from: f */
    private static ArrayList<ap> f15640f;
    /* renamed from: g */
    private static Map<String, String> f15641g;
    /* renamed from: a */
    public byte f15642a = (byte) 0;
    /* renamed from: b */
    public String f15643b = "";
    /* renamed from: c */
    public String f15644c = "";
    /* renamed from: d */
    public ArrayList<ap> f15645d = null;
    /* renamed from: e */
    public Map<String, String> f15646e = null;

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17631a(this.f15642a, 0);
        if (this.f15643b != null) {
            c4456i.m17636a(this.f15643b, 1);
        }
        if (this.f15644c != null) {
            c4456i.m17636a(this.f15644c, 2);
        }
        if (this.f15645d != null) {
            c4456i.m17637a(this.f15645d, 3);
        }
        if (this.f15646e != null) {
            c4456i.m17638a(this.f15646e, 4);
        }
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        this.f15642a = c4455h.m17615a(this.f15642a, 0, true);
        this.f15643b = c4455h.m17625b(1, false);
        this.f15644c = c4455h.m17625b(2, false);
        if (f15640f == null) {
            f15640f = new ArrayList();
            f15640f.add(new ap());
        }
        this.f15645d = (ArrayList) c4455h.m17620a(f15640f, 3, false);
        if (f15641g == null) {
            f15641g = new HashMap();
            f15641g.put("", "");
        }
        this.f15646e = (Map) c4455h.m17620a(f15641g, 4, false);
    }

    /* renamed from: a */
    public final void mo6072a(StringBuilder stringBuilder, int i) {
    }
}
