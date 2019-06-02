package cn.sharesdk.framework.p011b.p013b;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;

/* compiled from: BaseEvent */
/* renamed from: cn.sharesdk.framework.b.b.c */
public abstract class C0591c {
    /* renamed from: e */
    public long f1273e;
    /* renamed from: f */
    public String f1274f;
    /* renamed from: g */
    public String f1275g;
    /* renamed from: h */
    public String f1276h;
    /* renamed from: i */
    public int f1277i;
    /* renamed from: j */
    public String f1278j;
    /* renamed from: k */
    public int f1279k;
    /* renamed from: l */
    public String f1280l;
    /* renamed from: m */
    public String f1281m;

    /* renamed from: a */
    protected abstract String mo2277a();

    /* renamed from: a */
    protected abstract void mo2278a(long j);

    /* renamed from: b */
    protected abstract int mo2279b();

    /* renamed from: c */
    protected abstract int mo2280c();

    /* renamed from: d */
    protected abstract long mo2281d();

    /* renamed from: e */
    protected abstract long mo2282e();

    /* renamed from: f */
    protected abstract void mo2283f();

    /* renamed from: a */
    public boolean mo2285a(Context context) {
        int b = mo2279b();
        int c = mo2280c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mo2282e() >= ((long) b)) {
            mo2278a(currentTimeMillis);
            return true;
        } else if (mo2281d() < ((long) c)) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: b */
    public void mo2286b(Context context) {
        mo2283f();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mo2277a()).append(CoreConstants.COLON_CHAR);
        stringBuilder.append(this.f1273e).append('|');
        stringBuilder.append(this.f1274f).append('|');
        stringBuilder.append(this.f1275g).append('|');
        stringBuilder.append(this.f1276h).append('|');
        stringBuilder.append(this.f1277i).append('|');
        stringBuilder.append(this.f1278j).append('|');
        stringBuilder.append(this.f1279k).append('|');
        stringBuilder.append(this.f1280l);
        return stringBuilder.toString();
    }
}
