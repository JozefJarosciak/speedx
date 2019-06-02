package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.c */
public class C4449c extends C4446a {
    /* renamed from: d */
    protected HashMap<String, byte[]> f15647d = null;
    /* renamed from: e */
    private HashMap<String, Object> f15648e = new HashMap();
    /* renamed from: f */
    private C4455h f15649f = new C4455h();

    /* renamed from: b */
    public void mo6077b() {
        this.f15647d = new HashMap();
    }

    /* renamed from: a */
    public <T> void mo6074a(String str, T t) {
        if (this.f15647d == null) {
            super.mo6074a(str, (Object) t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            C4456i c4456i = new C4456i();
            c4456i.m17629a(this.b);
            c4456i.m17635a((Object) t, 0);
            this.f15647d.put(str, C4457k.m17647a(c4456i.m17630a()));
        }
    }

    /* renamed from: b */
    public final <T> T m17587b(String str, T t) throws C4448b {
        T a;
        if (this.f15647d != null) {
            if (!this.f15647d.containsKey(str)) {
                return null;
            }
            if (this.f15648e.containsKey(str)) {
                return this.f15648e.get(str);
            }
            try {
                this.f15649f.m17623a((byte[]) this.f15647d.get(str));
                this.f15649f.m17617a(this.b);
                a = this.f15649f.m17620a((Object) t, 0, true);
                if (a == null) {
                    return a;
                }
                this.f15648e.put(str, a);
                return a;
            } catch (Exception e) {
                throw new C4448b(e);
            }
        } else if (!this.a.containsKey(str)) {
            return null;
        } else {
            if (this.f15648e.containsKey(str)) {
                return this.f15648e.get(str);
            }
            byte[] bArr;
            byte[] bArr2 = new byte[0];
            Iterator it = ((HashMap) this.a.get(str)).entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                bArr = (byte[]) entry.getValue();
            } else {
                bArr = bArr2;
            }
            try {
                this.f15649f.m17623a(bArr);
                this.f15649f.m17617a(this.b);
                a = this.f15649f.m17620a((Object) t, 0, true);
                this.f15648e.put(str, a);
                return a;
            } catch (Exception e2) {
                throw new C4448b(e2);
            }
        }
    }

    /* renamed from: a */
    public byte[] mo6076a() {
        if (this.f15647d == null) {
            return super.mo6076a();
        }
        C4456i c4456i = new C4456i(0);
        c4456i.m17629a(this.b);
        c4456i.m17638a(this.f15647d, 0);
        return C4457k.m17647a(c4456i.m17630a());
    }

    /* renamed from: a */
    public void mo6075a(byte[] bArr) {
        try {
            super.mo6075a(bArr);
        } catch (Exception e) {
            this.f15649f.m17623a(bArr);
            this.f15649f.m17617a(this.b);
            Map hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.f15647d = this.f15649f.m17621a(hashMap, 0, false);
        }
    }
}
