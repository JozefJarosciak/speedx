package p203u.aly;

import android.content.Context;
import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: IdTracker */
/* renamed from: u.aly.bz */
public class bz {
    /* renamed from: a */
    public static bz f18951a;
    /* renamed from: b */
    private final String f18952b = "umeng_it.cache";
    /* renamed from: c */
    private File f18953c;
    /* renamed from: d */
    private bc f18954d = null;
    /* renamed from: e */
    private long f18955e;
    /* renamed from: f */
    private long f18956f;
    /* renamed from: g */
    private Set<bu> f18957g = new HashSet();
    /* renamed from: h */
    private C5930a f18958h = null;

    /* compiled from: IdTracker */
    /* renamed from: u.aly.bz$a */
    public static class C5930a {
        /* renamed from: a */
        private Context f18949a;
        /* renamed from: b */
        private Set<String> f18950b = new HashSet();

        public C5930a(Context context) {
            this.f18949a = context;
        }

        /* renamed from: a */
        public boolean m21798a(String str) {
            return !this.f18950b.contains(str);
        }

        /* renamed from: b */
        public void m21800b(String str) {
            this.f18950b.add(str);
        }

        /* renamed from: a */
        public void m21797a() {
            if (!this.f18950b.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : this.f18950b) {
                    stringBuilder.append(append);
                    stringBuilder.append(CoreConstants.COMMA_CHAR);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                C5955u.m22014a(this.f18949a).edit().putString("invld_id", stringBuilder.toString()).commit();
            }
        }

        /* renamed from: b */
        public void m21799b() {
            Object string = C5955u.m22014a(this.f18949a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                if (split != null) {
                    for (CharSequence charSequence : split) {
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.f18950b.add(charSequence);
                        }
                    }
                }
            }
        }
    }

    bz(Context context) {
        this.f18953c = new File(context.getFilesDir(), "umeng_it.cache");
        this.f18956f = 86400000;
        this.f18958h = new C5930a(context);
        this.f18958h.m21799b();
    }

    /* renamed from: a */
    public static synchronized bz m21801a(Context context) {
        bz bzVar;
        synchronized (bz.class) {
            if (f18951a == null) {
                f18951a = new bz(context);
                f18951a.m21806a(new ca(context));
                f18951a.m21806a(new bw(context));
                f18951a.m21806a(new C5931c(context));
                f18951a.m21806a(new C5871b(context));
                f18951a.m21806a(new by(context));
                f18951a.m21806a(new cj(context));
                f18951a.m21806a(new ck());
                f18951a.m21806a(new C5935d(context));
                f18951a.m21809d();
            }
            bzVar = f18951a;
        }
        return bzVar;
    }

    /* renamed from: a */
    public boolean m21806a(bu buVar) {
        if (this.f18958h.m21798a(buVar.m21309c())) {
            return this.f18957g.add(buVar);
        }
        return false;
    }

    /* renamed from: a */
    public void m21805a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f18955e >= this.f18956f) {
            Object obj = null;
            for (bu buVar : this.f18957g) {
                if (buVar.m21310d()) {
                    if (buVar.m21308b()) {
                        obj = 1;
                        if (!buVar.m21310d()) {
                            this.f18958h.m21800b(buVar.m21309c());
                        }
                    }
                    obj = obj;
                }
            }
            if (obj != null) {
                m21803f();
                this.f18958h.m21797a();
                m21810e();
            }
            this.f18955e = currentTimeMillis;
        }
    }

    /* renamed from: b */
    public bc m21807b() {
        return this.f18954d;
    }

    /* renamed from: f */
    private void m21803f() {
        bc bcVar = new bc();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (bu buVar : this.f18957g) {
            if (buVar.m21310d()) {
                if (buVar.m21311e() != null) {
                    hashMap.put(buVar.m21309c(), buVar.m21311e());
                }
                if (!(buVar.m21312f() == null || buVar.m21312f().isEmpty())) {
                    arrayList.addAll(buVar.m21312f());
                }
            }
        }
        bcVar.m21443a(arrayList);
        bcVar.m21444a(hashMap);
        synchronized (this) {
            this.f18954d = bcVar;
        }
    }

    /* renamed from: c */
    public void m21808c() {
        boolean z = false;
        for (bu buVar : this.f18957g) {
            if (buVar.m21310d()) {
                boolean z2;
                if (buVar.m21312f() == null || buVar.m21312f().isEmpty()) {
                    z2 = z;
                } else {
                    buVar.m21306a(null);
                    z2 = true;
                }
                z = z2;
            }
        }
        if (z) {
            this.f18954d.m21452b(false);
            m21810e();
        }
    }

    /* renamed from: d */
    public void m21809d() {
        bc g = m21804g();
        if (g != null) {
            List<bu> arrayList = new ArrayList(this.f18957g.size());
            synchronized (this) {
                this.f18954d = g;
                for (bu buVar : this.f18957g) {
                    buVar.m21307a(this.f18954d);
                    if (!buVar.m21310d()) {
                        arrayList.add(buVar);
                    }
                }
                for (bu buVar2 : arrayList) {
                    this.f18957g.remove(buVar2);
                }
            }
            m21803f();
        }
    }

    /* renamed from: e */
    public void m21810e() {
        if (this.f18954d != null) {
            m21802a(this.f18954d);
        }
    }

    /* renamed from: g */
    private bc m21804g() {
        Exception e;
        Throwable th;
        if (!this.f18953c.exists()) {
            return null;
        }
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.f18953c);
            try {
                byte[] b = ag.m21151b(fileInputStream);
                bp bcVar = new bc();
                new al().m21191a(bcVar, b);
                ag.m21152c(fileInputStream);
                return bcVar;
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    ag.m21152c(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    ag.m21152c(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            e.printStackTrace();
            ag.m21152c(fileInputStream);
            return null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            ag.m21152c(fileInputStream);
            throw th;
        }
    }

    /* renamed from: a */
    private void m21802a(bc bcVar) {
        if (bcVar != null) {
            try {
                byte[] a;
                synchronized (this) {
                    a = new an().m21192a(bcVar);
                }
                if (a != null) {
                    ag.m21148a(this.f18953c, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
