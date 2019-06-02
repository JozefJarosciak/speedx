package com.tencent.bugly.proguard;

import com.tencent.bugly.crashreport.crash.jni.C4445b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class ao extends C4447j implements Cloneable {
    /* renamed from: m */
    private static an f15616m = new an();
    /* renamed from: n */
    private static Map<String, String> f15617n = new HashMap();
    /* renamed from: o */
    private static /* synthetic */ boolean f15618o;
    /* renamed from: a */
    public boolean f15619a = true;
    /* renamed from: b */
    public boolean f15620b = true;
    /* renamed from: c */
    public boolean f15621c = true;
    /* renamed from: d */
    public String f15622d = "";
    /* renamed from: e */
    public String f15623e = "";
    /* renamed from: f */
    public an f15624f = null;
    /* renamed from: g */
    public Map<String, String> f15625g = null;
    /* renamed from: h */
    public long f15626h = 0;
    /* renamed from: i */
    public int f15627i = 0;
    /* renamed from: j */
    private String f15628j = "";
    /* renamed from: k */
    private String f15629k = "";
    /* renamed from: l */
    private int f15630l = 0;

    static {
        boolean z;
        if (ao.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        f15618o = z;
        f15617n.put("", "");
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ao aoVar = (ao) obj;
        if (C4457k.m17646a(this.f15619a, aoVar.f15619a) && C4457k.m17646a(this.f15620b, aoVar.f15620b) && C4457k.m17646a(this.f15621c, aoVar.f15621c) && C4457k.m17645a(this.f15622d, aoVar.f15622d) && C4457k.m17645a(this.f15623e, aoVar.f15623e) && C4457k.m17645a(this.f15624f, aoVar.f15624f) && C4457k.m17645a(this.f15625g, aoVar.f15625g) && C4457k.m17644a(this.f15626h, aoVar.f15626h) && C4457k.m17645a(this.f15628j, aoVar.f15628j) && C4457k.m17645a(this.f15629k, aoVar.f15629k) && C4457k.m17643a(this.f15630l, aoVar.f15630l) && C4457k.m17643a(this.f15627i, aoVar.f15627i)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!f15618o) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17640a(this.f15619a, 0);
        c4456i.m17640a(this.f15620b, 1);
        c4456i.m17640a(this.f15621c, 2);
        if (this.f15622d != null) {
            c4456i.m17636a(this.f15622d, 3);
        }
        if (this.f15623e != null) {
            c4456i.m17636a(this.f15623e, 4);
        }
        if (this.f15624f != null) {
            c4456i.m17634a(this.f15624f, 5);
        }
        if (this.f15625g != null) {
            c4456i.m17638a(this.f15625g, 6);
        }
        c4456i.m17633a(this.f15626h, 7);
        if (this.f15628j != null) {
            c4456i.m17636a(this.f15628j, 8);
        }
        if (this.f15629k != null) {
            c4456i.m17636a(this.f15629k, 9);
        }
        c4456i.m17632a(this.f15630l, 10);
        c4456i.m17632a(this.f15627i, 11);
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        boolean z = this.f15619a;
        this.f15619a = c4455h.m17624a(0, true);
        z = this.f15620b;
        this.f15620b = c4455h.m17624a(1, true);
        z = this.f15621c;
        this.f15621c = c4455h.m17624a(2, true);
        this.f15622d = c4455h.m17625b(3, false);
        this.f15623e = c4455h.m17625b(4, false);
        this.f15624f = (an) c4455h.m17619a(f15616m, 5, false);
        this.f15625g = (Map) c4455h.m17620a(f15617n, 6, false);
        this.f15626h = c4455h.m17618a(this.f15626h, 7, false);
        this.f15628j = c4455h.m17625b(8, false);
        this.f15629k = c4455h.m17625b(9, false);
        this.f15630l = c4455h.m17616a(this.f15630l, 10, false);
        this.f15627i = c4455h.m17616a(this.f15627i, 11, false);
    }

    /* renamed from: a */
    public final void mo6072a(StringBuilder stringBuilder, int i) {
        C4445b c4445b = new C4445b(stringBuilder, i);
        c4445b.m17513a(this.f15619a, "enable");
        c4445b.m17513a(this.f15620b, "enableUserInfo");
        c4445b.m17513a(this.f15621c, "enableQuery");
        c4445b.m17521b(this.f15622d, "url");
        c4445b.m17521b(this.f15623e, "expUrl");
        c4445b.m17508a(this.f15624f, "security");
        c4445b.m17511a(this.f15625g, "valueMap");
        c4445b.m17507a(this.f15626h, "strategylastUpdateTime");
        c4445b.m17521b(this.f15628j, "httpsUrl");
        c4445b.m17521b(this.f15629k, "httpsExpUrl");
        c4445b.m17506a(this.f15630l, "eventRecordCount");
        c4445b.m17506a(this.f15627i, "eventTimeInterval");
    }
}
