package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class aj extends C4447j {
    /* renamed from: A */
    private static ArrayList<ai> f15547A = new ArrayList();
    /* renamed from: B */
    private static Map<String, String> f15548B = new HashMap();
    /* renamed from: C */
    private static Map<String, String> f15549C = new HashMap();
    /* renamed from: v */
    private static Map<String, String> f15550v = new HashMap();
    /* renamed from: w */
    private static ah f15551w = new ah();
    /* renamed from: x */
    private static ag f15552x = new ag();
    /* renamed from: y */
    private static ArrayList<ag> f15553y = new ArrayList();
    /* renamed from: z */
    private static ArrayList<ag> f15554z = new ArrayList();
    /* renamed from: a */
    public String f15555a = "";
    /* renamed from: b */
    public long f15556b = 0;
    /* renamed from: c */
    public String f15557c = "";
    /* renamed from: d */
    public String f15558d = "";
    /* renamed from: e */
    public String f15559e = "";
    /* renamed from: f */
    public String f15560f = "";
    /* renamed from: g */
    public String f15561g = "";
    /* renamed from: h */
    public Map<String, String> f15562h = null;
    /* renamed from: i */
    public String f15563i = "";
    /* renamed from: j */
    public ah f15564j = null;
    /* renamed from: k */
    public int f15565k = 0;
    /* renamed from: l */
    public String f15566l = "";
    /* renamed from: m */
    public String f15567m = "";
    /* renamed from: n */
    public ag f15568n = null;
    /* renamed from: o */
    public ArrayList<ag> f15569o = null;
    /* renamed from: p */
    public ArrayList<ag> f15570p = null;
    /* renamed from: q */
    public ArrayList<ai> f15571q = null;
    /* renamed from: r */
    public Map<String, String> f15572r = null;
    /* renamed from: s */
    public Map<String, String> f15573s = null;
    /* renamed from: t */
    public String f15574t = "";
    /* renamed from: u */
    private boolean f15575u = true;

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17636a(this.f15555a, 0);
        c4456i.m17633a(this.f15556b, 1);
        c4456i.m17636a(this.f15557c, 2);
        if (this.f15558d != null) {
            c4456i.m17636a(this.f15558d, 3);
        }
        if (this.f15559e != null) {
            c4456i.m17636a(this.f15559e, 4);
        }
        if (this.f15560f != null) {
            c4456i.m17636a(this.f15560f, 5);
        }
        if (this.f15561g != null) {
            c4456i.m17636a(this.f15561g, 6);
        }
        if (this.f15562h != null) {
            c4456i.m17638a(this.f15562h, 7);
        }
        if (this.f15563i != null) {
            c4456i.m17636a(this.f15563i, 8);
        }
        if (this.f15564j != null) {
            c4456i.m17634a(this.f15564j, 9);
        }
        c4456i.m17632a(this.f15565k, 10);
        if (this.f15566l != null) {
            c4456i.m17636a(this.f15566l, 11);
        }
        if (this.f15567m != null) {
            c4456i.m17636a(this.f15567m, 12);
        }
        if (this.f15568n != null) {
            c4456i.m17634a(this.f15568n, 13);
        }
        if (this.f15569o != null) {
            c4456i.m17637a(this.f15569o, 14);
        }
        if (this.f15570p != null) {
            c4456i.m17637a(this.f15570p, 15);
        }
        if (this.f15571q != null) {
            c4456i.m17637a(this.f15571q, 16);
        }
        if (this.f15572r != null) {
            c4456i.m17638a(this.f15572r, 17);
        }
        if (this.f15573s != null) {
            c4456i.m17638a(this.f15573s, 18);
        }
        if (this.f15574t != null) {
            c4456i.m17636a(this.f15574t, 19);
        }
        c4456i.m17640a(this.f15575u, 20);
    }

    static {
        f15550v.put("", "");
        f15553y.add(new ag());
        f15554z.add(new ag());
        f15547A.add(new ai());
        f15548B.put("", "");
        f15549C.put("", "");
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        this.f15555a = c4455h.m17625b(0, true);
        this.f15556b = c4455h.m17618a(this.f15556b, 1, true);
        this.f15557c = c4455h.m17625b(2, true);
        this.f15558d = c4455h.m17625b(3, false);
        this.f15559e = c4455h.m17625b(4, false);
        this.f15560f = c4455h.m17625b(5, false);
        this.f15561g = c4455h.m17625b(6, false);
        this.f15562h = (Map) c4455h.m17620a(f15550v, 7, false);
        this.f15563i = c4455h.m17625b(8, false);
        this.f15564j = (ah) c4455h.m17619a(f15551w, 9, false);
        this.f15565k = c4455h.m17616a(this.f15565k, 10, false);
        this.f15566l = c4455h.m17625b(11, false);
        this.f15567m = c4455h.m17625b(12, false);
        this.f15568n = (ag) c4455h.m17619a(f15552x, 13, false);
        this.f15569o = (ArrayList) c4455h.m17620a(f15553y, 14, false);
        this.f15570p = (ArrayList) c4455h.m17620a(f15554z, 15, false);
        this.f15571q = (ArrayList) c4455h.m17620a(f15547A, 16, false);
        this.f15572r = (Map) c4455h.m17620a(f15548B, 17, false);
        this.f15573s = (Map) c4455h.m17620a(f15549C, 18, false);
        this.f15574t = c4455h.m17625b(19, false);
        boolean z = this.f15575u;
        this.f15575u = c4455h.m17624a(20, false);
    }
}
