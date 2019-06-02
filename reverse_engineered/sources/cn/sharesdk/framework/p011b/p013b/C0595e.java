package cn.sharesdk.framework.p011b.p013b;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.p011b.p012a.C0589e;

/* compiled from: ExitEvent */
/* renamed from: cn.sharesdk.framework.b.b.e */
public class C0595e extends C0591c {
    /* renamed from: b */
    private static int f1297b;
    /* renamed from: c */
    private static long f1298c;
    /* renamed from: a */
    public long f1299a;

    /* renamed from: a */
    protected String mo2277a() {
        return "[EXT]";
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
        f1297b = a.m2059g("insertExitEventCount");
        f1298c = a.m2057f("lastInsertExitEventTime");
        return super.mo2285a(context);
    }

    /* renamed from: b */
    public void mo2286b(Context context) {
        super.mo2286b(context);
        C0589e a = C0589e.m2039a(context);
        a.m2044a("lastInsertExitEventTime", Long.valueOf(f1298c));
        a.m2043a("insertExitEventCount", f1297b);
    }

    /* renamed from: d */
    protected long mo2281d() {
        return (long) f1297b;
    }

    /* renamed from: e */
    protected long mo2282e() {
        return f1298c;
    }

    /* renamed from: f */
    protected void mo2283f() {
        f1297b++;
    }

    /* renamed from: a */
    protected void mo2278a(long j) {
        f1298c = j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|').append(Math.round(((float) this.f1299a) / 1000.0f));
        return stringBuilder.toString();
    }
}
