package com.beastbikes.android.modules.preferences.ui.offlineMap.p142c;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p143d.p144a.C2325b;
import java.util.Comparator;

/* compiled from: OfflineMapItem */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.c.a */
public class C2322a {
    /* renamed from: a */
    public static final Comparator<C2322a> f11043a = new C23201();
    /* renamed from: b */
    public static Comparator<C2322a> f11044b = new C23212();
    /* renamed from: c */
    private volatile MKOLUpdateElement f11045c;
    /* renamed from: d */
    private volatile MKOLSearchRecord f11046d;
    /* renamed from: e */
    private String f11047e;
    /* renamed from: f */
    private long f11048f;

    /* compiled from: OfflineMapItem */
    /* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.c.a$1 */
    static class C23201 implements Comparator<C2322a> {
        C23201() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m11864a((C2322a) obj, (C2322a) obj2);
        }

        /* renamed from: a */
        public int m11864a(C2322a c2322a, C2322a c2322a2) {
            int i = 1;
            int i2 = 0;
            int i3 = c2322a.m11872c() == 4 ? 1 : 0;
            if (c2322a2.m11872c() != 4) {
                i = 0;
            }
            if (i3 != i) {
                return i3 - i;
            }
            String f = c2322a.m11875f();
            String f2 = c2322a2.m11875f();
            int length = f.length();
            int length2 = f2.length();
            i3 = Math.min(length, length2);
            char[] toCharArray = f.toCharArray();
            char[] toCharArray2 = f2.toCharArray();
            while (true) {
                i = i3 - 1;
                if (i3 == 0) {
                    return length - length2;
                }
                char c = toCharArray[i2];
                char c2 = toCharArray2[i2];
                if (c != c2) {
                    return c - c2;
                }
                i2++;
                i3 = i;
            }
        }
    }

    /* compiled from: OfflineMapItem */
    /* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.c.a$2 */
    static class C23212 implements Comparator<C2322a> {
        C23212() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m11865a((C2322a) obj, (C2322a) obj2);
        }

        /* renamed from: a */
        public int m11865a(C2322a c2322a, C2322a c2322a2) {
            long a = c2322a.m11866a() - c2322a2.m11866a();
            if (a > 0) {
                return -1;
            }
            return a < 0 ? 1 : 0;
        }
    }

    /* renamed from: a */
    public long m11866a() {
        return this.f11048f;
    }

    /* renamed from: a */
    public void m11868a(long j) {
        this.f11048f = j;
    }

    /* renamed from: b */
    public int m11871b() {
        if (this.f11045c != null) {
            return this.f11045c.ratio;
        }
        return 0;
    }

    /* renamed from: c */
    public int m11872c() {
        if (this.f11045c != null) {
            return this.f11045c.status;
        }
        return 0;
    }

    /* renamed from: a */
    public void m11867a(int i) {
        if (this.f11045c != null) {
            this.f11045c.status = i;
        }
    }

    /* renamed from: d */
    public boolean m11873d() {
        if (this.f11045c != null) {
            return this.f11045c.update;
        }
        return false;
    }

    /* renamed from: e */
    public String m11874e() {
        if (this.f11046d != null) {
            return this.f11046d.cityName;
        }
        return "";
    }

    /* renamed from: f */
    public String m11875f() {
        return this.f11047e;
    }

    /* renamed from: g */
    public int m11876g() {
        if (this.f11046d != null) {
            return this.f11046d.cityID;
        }
        return 0;
    }

    /* renamed from: a */
    public void m11869a(MKOLSearchRecord mKOLSearchRecord) {
        this.f11046d = mKOLSearchRecord;
        this.f11047e = C2325b.m11883a(mKOLSearchRecord.cityName);
    }

    /* renamed from: h */
    public MKOLUpdateElement m11877h() {
        return this.f11045c;
    }

    /* renamed from: a */
    public void m11870a(MKOLUpdateElement mKOLUpdateElement) {
        this.f11045c = mKOLUpdateElement;
    }

    /* renamed from: i */
    public int m11878i() {
        if (this.f11046d != null) {
            return this.f11046d.size;
        }
        return 0;
    }
}
