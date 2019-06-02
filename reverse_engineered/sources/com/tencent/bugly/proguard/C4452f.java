package com.tencent.bugly.proguard;

import ch.qos.logback.core.CoreConstants;
import com.tencent.bugly.crashreport.crash.jni.C4445b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.f */
public final class C4452f extends C4447j {
    /* renamed from: k */
    private static byte[] f15654k = null;
    /* renamed from: l */
    private static Map<String, String> f15655l = null;
    /* renamed from: m */
    private static /* synthetic */ boolean f15656m;
    /* renamed from: a */
    public short f15657a = (short) 0;
    /* renamed from: b */
    public int f15658b = 0;
    /* renamed from: c */
    public String f15659c = null;
    /* renamed from: d */
    public String f15660d = null;
    /* renamed from: e */
    public byte[] f15661e;
    /* renamed from: f */
    private byte f15662f = (byte) 0;
    /* renamed from: g */
    private int f15663g = 0;
    /* renamed from: h */
    private int f15664h = 0;
    /* renamed from: i */
    private Map<String, String> f15665i;
    /* renamed from: j */
    private Map<String, String> f15666j;

    static {
        boolean z;
        if (C4452f.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        f15656m = z;
    }

    public final boolean equals(Object obj) {
        C4452f c4452f = (C4452f) obj;
        if (C4457k.m17643a(1, c4452f.f15657a) && C4457k.m17643a(1, c4452f.f15662f) && C4457k.m17643a(1, c4452f.f15663g) && C4457k.m17643a(1, c4452f.f15658b) && C4457k.m17645a(Integer.valueOf(1), c4452f.f15659c) && C4457k.m17645a(Integer.valueOf(1), c4452f.f15660d) && C4457k.m17645a(Integer.valueOf(1), c4452f.f15661e) && C4457k.m17643a(1, c4452f.f15664h) && C4457k.m17645a(Integer.valueOf(1), c4452f.f15665i) && C4457k.m17645a(Integer.valueOf(1), c4452f.f15666j)) {
            return true;
        }
        return false;
    }

    public final Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!f15656m) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17639a(this.f15657a, 1);
        c4456i.m17631a(this.f15662f, 2);
        c4456i.m17632a(this.f15663g, 3);
        c4456i.m17632a(this.f15658b, 4);
        c4456i.m17636a(this.f15659c, 5);
        c4456i.m17636a(this.f15660d, 6);
        c4456i.m17641a(this.f15661e, 7);
        c4456i.m17632a(this.f15664h, 8);
        c4456i.m17638a(this.f15665i, 9);
        c4456i.m17638a(this.f15666j, 10);
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        try {
            Map hashMap;
            this.f15657a = c4455h.m17622a(this.f15657a, 1, true);
            this.f15662f = c4455h.m17615a(this.f15662f, 2, true);
            this.f15663g = c4455h.m17616a(this.f15663g, 3, true);
            this.f15658b = c4455h.m17616a(this.f15658b, 4, true);
            this.f15659c = c4455h.m17625b(5, true);
            this.f15660d = c4455h.m17625b(6, true);
            if (f15654k == null) {
                f15654k = new byte[]{(byte) 0};
            }
            byte[] bArr = f15654k;
            this.f15661e = c4455h.m17626c(7, true);
            this.f15664h = c4455h.m17616a(this.f15664h, 8, true);
            if (f15655l == null) {
                hashMap = new HashMap();
                f15655l = hashMap;
                hashMap.put("", "");
            }
            this.f15665i = (Map) c4455h.m17620a(f15655l, 9, true);
            if (f15655l == null) {
                hashMap = new HashMap();
                f15655l = hashMap;
                hashMap.put("", "");
            }
            this.f15666j = (Map) c4455h.m17620a(f15655l, 10, true);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + C4451e.m17596a(this.f15661e));
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public final void mo6072a(StringBuilder stringBuilder, int i) {
        C4445b c4445b = new C4445b(stringBuilder, i);
        c4445b.m17512a(this.f15657a, "iVersion");
        c4445b.m17502a(this.f15662f, "cPacketType");
        c4445b.m17506a(this.f15663g, "iMessageType");
        c4445b.m17506a(this.f15658b, "iRequestId");
        c4445b.m17521b(this.f15659c, "sServantName");
        c4445b.m17521b(this.f15660d, "sFuncName");
        c4445b.m17514a(this.f15661e, "sBuffer");
        c4445b.m17506a(this.f15664h, "iTimeout");
        c4445b.m17511a(this.f15665i, CoreConstants.CONTEXT_SCOPE_VALUE);
        c4445b.m17511a(this.f15666j, "status");
    }
}
