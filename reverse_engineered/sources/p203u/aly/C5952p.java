package p203u.aly;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: UMCCSystemBufferManager */
/* renamed from: u.aly.p */
public class C5952p implements Serializable {
    /* renamed from: a */
    private static final long f19086a = 1;
    /* renamed from: b */
    private Map<String, C5949k> f19087b = new HashMap();

    /* renamed from: a */
    public void m22000a(bo boVar, String str) {
        if (this.f19087b.containsKey(str)) {
            m21997c(str);
        } else {
            m21995b(str);
        }
        boVar.mo6179a(this, false);
    }

    /* renamed from: a */
    public Map<String, C5949k> m21998a() {
        return this.f19087b;
    }

    /* renamed from: b */
    public void m22003b() {
        this.f19087b.clear();
    }

    /* renamed from: a */
    public void m21999a(Map<String, C5949k> map) {
        this.f19087b = map;
    }

    /* renamed from: a */
    public boolean m22002a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (Entry key : this.f19087b.entrySet()) {
            if (((String) key.getKey()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private void m21995b(String str) {
        this.f19087b.put(str, new C5949k(str, System.currentTimeMillis(), 1));
    }

    /* renamed from: c */
    private void m21997c(String str) {
        this.f19087b.put(str, ((C5949k) this.f19087b.get(str)).m21977a());
    }

    /* renamed from: a */
    public void m22001a(C5949k c5949k) {
        if (m22002a(c5949k.m21982c())) {
            m21996b(c5949k);
        } else {
            this.f19087b.put(c5949k.m21982c(), c5949k);
        }
    }

    /* renamed from: b */
    private void m21996b(C5949k c5949k) {
        this.f19087b.put(c5949k.m21982c(), ((C5949k) this.f19087b.get(c5949k.m21982c())).m21978a(c5949k));
    }
}
