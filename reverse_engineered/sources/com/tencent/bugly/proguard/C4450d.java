package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.d */
public final class C4450d extends C4449c {
    /* renamed from: f */
    private static HashMap<String, byte[]> f15650f = null;
    /* renamed from: g */
    private static HashMap<String, HashMap<String, byte[]>> f15651g = null;
    /* renamed from: e */
    private C4452f f15652e = new C4452f();

    /* renamed from: a */
    public final <T> void mo6074a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.mo6074a(str, t);
    }

    /* renamed from: b */
    public final void mo6077b() {
        super.mo6077b();
        this.f15652e.f15657a = (short) 3;
    }

    /* renamed from: a */
    public final byte[] mo6076a() {
        if (this.f15652e.f15657a != (short) 2) {
            if (this.f15652e.f15659c == null) {
                this.f15652e.f15659c = "";
            }
            if (this.f15652e.f15660d == null) {
                this.f15652e.f15660d = "";
            }
        } else if (this.f15652e.f15659c.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.f15652e.f15660d.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        C4456i c4456i = new C4456i(0);
        c4456i.m17629a(this.b);
        if (this.f15652e.f15657a == (short) 2) {
            c4456i.m17638a(this.a, 0);
        } else {
            c4456i.m17638a(this.d, 0);
        }
        this.f15652e.f15661e = C4457k.m17647a(c4456i.m17630a());
        c4456i = new C4456i(0);
        c4456i.m17629a(this.b);
        this.f15652e.mo6071a(c4456i);
        byte[] a = C4457k.m17647a(c4456i.m17630a());
        int length = a.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(a).flip();
        return allocate.array();
    }

    /* renamed from: a */
    public final void mo6075a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            C4455h c4455h = new C4455h(bArr, 4);
            c4455h.m17617a(this.b);
            this.f15652e.mo6070a(c4455h);
            HashMap hashMap;
            if (this.f15652e.f15657a == (short) 3) {
                c4455h = new C4455h(this.f15652e.f15661e);
                c4455h.m17617a(this.b);
                if (f15650f == null) {
                    hashMap = new HashMap();
                    f15650f = hashMap;
                    hashMap.put("", new byte[0]);
                }
                this.d = c4455h.m17621a(f15650f, 0, false);
                return;
            }
            c4455h = new C4455h(this.f15652e.f15661e);
            c4455h.m17617a(this.b);
            if (f15651g == null) {
                f15651g = new HashMap();
                hashMap = new HashMap();
                hashMap.put("", new byte[0]);
                f15651g.put("", hashMap);
            }
            this.a = c4455h.m17621a(f15651g, 0, false);
            HashMap hashMap2 = new HashMap();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public final void m17594b(String str) {
        this.f15652e.f15659c = str;
    }

    /* renamed from: c */
    public final void m17595c(String str) {
        this.f15652e.f15660d = str;
    }

    /* renamed from: b */
    public final void m17593b(int i) {
        this.f15652e.f15658b = 1;
    }
}
