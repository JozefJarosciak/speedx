package p203u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.umeng.analytics.C4731a;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;

/* compiled from: MemoCache */
/* renamed from: u.aly.q */
public class C5953q {
    /* renamed from: a */
    private List<C5862n> f19088a = new ArrayList();
    /* renamed from: b */
    private Context f19089b = null;

    public C5953q(Context context) {
        this.f19089b = context;
    }

    /* renamed from: a */
    public synchronized int m22005a() {
        int size;
        size = this.f19088a.size();
        if (av.f18718c != 0) {
            size++;
        }
        return size;
    }

    /* renamed from: a */
    public synchronized void m22007a(C5862n c5862n) {
        this.f19088a.add(c5862n);
    }

    /* renamed from: a */
    public void m22006a(av avVar) {
        if (C5958w.m22034g(this.f19089b) != null) {
            m22008b(avVar);
            m22004c(avVar);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    private void m22004c(p203u.aly.av r7) {
        /*
        r6 = this;
        r5 = 1;
        r4 = 0;
        monitor-enter(r6);
        r0 = r6.f19088a;	 Catch:{ all -> 0x0019 }
        r1 = r0.iterator();	 Catch:{ all -> 0x0019 }
    L_0x0009:
        r0 = r1.hasNext();	 Catch:{ all -> 0x0019 }
        if (r0 == 0) goto L_0x001c;
    L_0x000f:
        r0 = r1.next();	 Catch:{ all -> 0x0019 }
        r0 = (p203u.aly.C5862n) r0;	 Catch:{ all -> 0x0019 }
        r0.mo7172a(r7);	 Catch:{ all -> 0x0019 }
        goto L_0x0009;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        throw r0;
    L_0x001c:
        r0 = r6.f19089b;	 Catch:{ all -> 0x0019 }
        r0 = p203u.aly.C5955u.m22014a(r0);	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x0026;
    L_0x0024:
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
    L_0x0025:
        return;
    L_0x0026:
        r1 = "userlevel";
        r2 = "";
        r0 = r0.getString(r1, r2);	 Catch:{ all -> 0x0019 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0019 }
        if (r1 != 0) goto L_0x0038;
    L_0x0034:
        r1 = r7.f18721b;	 Catch:{ all -> 0x0019 }
        r1.f18661j = r0;	 Catch:{ all -> 0x0019 }
    L_0x0038:
        r0 = r6.f19088a;	 Catch:{ all -> 0x0019 }
        r0.clear();	 Catch:{ all -> 0x0019 }
        monitor-exit(r6);	 Catch:{ all -> 0x0019 }
        r0 = p203u.aly.av.f18718c;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x004e;
    L_0x0046:
        r0 = r7.f18721b;
        r0 = r0.f18655d;
        r2 = p203u.aly.av.f18718c;
        r0.f18607a = r2;
    L_0x004e:
        r0 = r6.f19089b;
        r0 = p203u.aly.bq.m21732a(r0);
        r0.m21753a(r7);
        r0 = r6.f19089b;
        r0 = com.umeng.analytics.C4753g.m18669a(r0);
        if (r0 == 0) goto L_0x007f;
    L_0x005f:
        r1 = r0[r4];
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x007f;
    L_0x0067:
        r1 = r0[r5];
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x007f;
    L_0x006f:
        r1 = r7.f18721b;
        r1 = r1.f18658g;
        r2 = r0[r4];
        r1.f18609a = r2;
        r1 = r7.f18721b;
        r1 = r1.f18658g;
        r0 = r0[r5];
        r1.f18610b = r0;
    L_0x007f:
        r0 = r6.f19089b;
        r0 = p203u.aly.C5961z.m22062a(r0);
        r0.m22068a(r7);
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.q.c(u.aly.av):void");
    }

    /* renamed from: b */
    void m22008b(av avVar) {
        String[] h;
        avVar.f18720a.f18678a = C4731a.m18614a(this.f19089b);
        avVar.f18720a.f18679b = C4731a.m18619b(this.f19089b);
        avVar.f18720a.f18680c = ag.m21146a(C4731a.m18620c(this.f19089b));
        avVar.f18720a.f18690m = C4731a.m18621d(this.f19089b);
        avVar.f18720a.f18689l = C4731a.m18622e(this.f19089b);
        avVar.f18720a.f18682e = af.m21136r(this.f19089b);
        int parseInt = Integer.parseInt(af.m21111a(this.f19089b));
        String b = af.m21117b(this.f19089b);
        SharedPreferences a = C5955u.m22014a(this.f19089b);
        if (a == null) {
            avVar.f18720a.f18685h = parseInt;
            avVar.f18720a.f18681d = b;
        } else {
            avVar.f18720a.f18685h = a.getInt("versioncode", 0);
            avVar.f18720a.f18681d = a.getString("versionname", "");
        }
        avVar.f18720a.f18683f = af.m21137s(this.f19089b);
        avVar.f18720a.f18684g = af.m21139u(this.f19089b);
        if (!(C4731a.f16605a == null || C4731a.f16606b == null)) {
            avVar.f18720a.f18686i = C4731a.f16605a;
            avVar.f18720a.f18687j = C4731a.f16606b;
        }
        avVar.f18720a.f18697t = af.m21119c(this.f19089b);
        avVar.f18720a.f18691n = af.m21121d(this.f19089b);
        avVar.f18720a.f18696s = af.m21133o(this.f19089b);
        avVar.f18720a.f18664B = af.m21140v(this.f19089b);
        avVar.f18720a.f18665C = af.m21141w(this.f19089b);
        int[] p = af.m21134p(this.f19089b);
        if (p != null) {
            avVar.f18720a.f18695r = p[1] + Marker.ANY_MARKER + p[0];
        }
        if (C4731a.f16608d == null || C4731a.f16607c != null) {
            h = af.m21126h(this.f19089b);
        } else {
            h = af.m21126h(this.f19089b);
        }
        if ("Wi-Fi".equals(h[0])) {
            avVar.f18720a.f18670H = MapboxEvent.ATTRIBUTE_WIFI;
        } else if ("2G/3G".equals(h[0])) {
            avVar.f18720a.f18670H = "2G/3G";
        } else {
            avVar.f18720a.f18670H = "unknow";
        }
        if (!"".equals(h[1])) {
            avVar.f18720a.f18671I = h[1];
        }
        Object e = af.m21123e(this.f19089b);
        if (!TextUtils.isEmpty(e)) {
            avVar.f18720a.f18672J = e;
        }
        avVar.f18720a.f18669G = af.m21125g(this.f19089b);
        h = af.m21131m(this.f19089b);
        avVar.f18720a.f18668F = h[0];
        avVar.f18720a.f18667E = h[1];
        avVar.f18720a.f18666D = (long) af.m21129k(this.f19089b);
        C5959x.m22042a(this.f19089b, avVar);
        try {
            bp b2 = bz.m21801a(this.f19089b).m21807b();
            if (b2 != null) {
                byte[] a2 = new an().m21192a(b2);
                avVar.f18720a.f18677O = Base64.encodeToString(a2, 0);
                try {
                    b2 = cf.m21838a(this.f19089b).m21843a();
                    if (b2 == null) {
                        ah.m21165d("trans the imprint is null");
                        return;
                    }
                    a2 = new an().m21192a(b2);
                    avVar.f18720a.f18676N = Base64.encodeToString(a2, 0);
                } catch (Exception e2) {
                }
            }
        } catch (Exception e3) {
        }
    }
}
