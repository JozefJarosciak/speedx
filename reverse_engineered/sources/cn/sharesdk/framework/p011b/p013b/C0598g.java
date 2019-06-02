package cn.sharesdk.framework.p011b.p013b;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.p011b.p012a.C0589e;

/* compiled from: StartEvent */
/* renamed from: cn.sharesdk.framework.b.b.g */
public class C0598g extends C0591c {
    /* renamed from: a */
    private static int f1315a;
    /* renamed from: b */
    private static long f1316b;

    /* renamed from: a */
    protected String mo2277a() {
        return "[RUN]";
    }

    /* renamed from: b */
    protected int mo2279b() {
        return 5000;
    }

    /* renamed from: c */
    protected int mo2280c() {
        return 5;
    }

    /* renamed from: a */
    public boolean mo2285a(Context context) {
        C0589e a = C0589e.m2039a(context);
        f1315a = a.m2059g("insertRunEventCount");
        f1316b = a.m2057f("lastInsertRunEventTime");
        return super.mo2285a(context);
    }

    /* renamed from: b */
    public void mo2286b(Context context) {
        super.mo2286b(context);
        C0589e a = C0589e.m2039a(context);
        a.m2044a("lastInsertRunEventTime", Long.valueOf(f1316b));
        a.m2043a("insertRunEventCount", f1315a);
    }

    /* renamed from: d */
    protected long mo2281d() {
        return (long) f1315a;
    }

    /* renamed from: e */
    protected long mo2282e() {
        return f1316b;
    }

    /* renamed from: f */
    protected void mo2283f() {
        f1315a++;
    }

    /* renamed from: a */
    protected void mo2278a(long j) {
        f1316b = j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        return stringBuilder.toString();
    }
}
