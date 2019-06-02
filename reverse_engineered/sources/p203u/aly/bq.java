package p203u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.umeng.analytics.C4747i;
import com.umeng.analytics.C4754h;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import p203u.aly.av.C5858e;
import p203u.aly.av.C5859f;

/* compiled from: UMCCAggregatedManager */
/* renamed from: u.aly.bq */
public class bq {
    /* renamed from: i */
    private static Context f18917i;
    /* renamed from: a */
    private C5946h f18918a;
    /* renamed from: b */
    private bs f18919b;
    /* renamed from: c */
    private C5952p f18920c;
    /* renamed from: d */
    private boolean f18921d;
    /* renamed from: e */
    private boolean f18922e;
    /* renamed from: f */
    private long f18923f;
    /* renamed from: g */
    private final String f18924g;
    /* renamed from: h */
    private final String f18925h;
    /* renamed from: j */
    private List<String> f18926j;
    /* renamed from: k */
    private C5924a f18927k;
    /* renamed from: l */
    private final Thread f18928l;

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$1 */
    class C59141 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ bq f18904a;

        C59141(bq bqVar) {
            this.f18904a = bqVar;
        }

        public void run() {
            Looper.prepare();
            if (this.f18904a.f18927k == null) {
                this.f18904a.f18927k = new C5924a(this.f18904a);
            }
            this.f18904a.m21737d();
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$2 */
    class C59152 extends bo {
        /* renamed from: a */
        final /* synthetic */ bq f18905a;

        C59152(bq bqVar) {
            this.f18905a = bqVar;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
            if (obj instanceof String) {
                this.f18905a.f18920c.m22003b();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$3 */
    class C59163 extends bo {
        /* renamed from: a */
        final /* synthetic */ bq f18906a;

        C59163(bq bqVar) {
            this.f18906a = bqVar;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
            if (obj.equals("success")) {
                this.f18906a.m21743g();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$4 */
    class C59174 extends bo {
        /* renamed from: a */
        final /* synthetic */ bq f18907a;

        C59174(bq bqVar) {
            this.f18907a = bqVar;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
            if (!obj.equals("success")) {
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$5 */
    class C59185 extends bo {
        /* renamed from: a */
        final /* synthetic */ bq f18908a;

        C59185(bq bqVar) {
            this.f18908a = bqVar;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
            if (obj instanceof String) {
                this.f18908a.f18918a.m21956d();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$6 */
    class C59196 extends bo {
        /* renamed from: a */
        final /* synthetic */ bq f18909a;

        C59196(bq bqVar) {
            this.f18909a = bqVar;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
            if (obj instanceof String) {
                this.f18909a.f18920c.m22003b();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$7 */
    class C59207 extends bo {
        /* renamed from: a */
        final /* synthetic */ bq f18910a;

        C59207(bq bqVar) {
            this.f18910a = bqVar;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$8 */
    class C59218 extends bo {
        /* renamed from: a */
        final /* synthetic */ bq f18911a;

        C59218(bq bqVar) {
            this.f18911a = bqVar;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
            if (obj instanceof String) {
                this.f18911a.f18920c.m22003b();
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$a */
    private static class C5924a extends Handler {
        /* renamed from: a */
        private final WeakReference<bq> f18915a;

        public C5924a(bq bqVar) {
            this.f18915a = new WeakReference(bqVar);
        }

        public void handleMessage(Message message) {
            if (this.f18915a != null) {
                switch (message.what) {
                    case 48:
                        sendEmptyMessageDelayed(48, bt.m21778b(System.currentTimeMillis()));
                        bq.m21732a(bq.f18917i).m21748i();
                        return;
                    case 49:
                        sendEmptyMessageDelayed(49, bt.m21779c(System.currentTimeMillis()));
                        bq.m21732a(bq.f18917i).m21745h();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* compiled from: UMCCAggregatedManager */
    /* renamed from: u.aly.bq$b */
    private static class C5925b {
        /* renamed from: a */
        private static final bq f18916a = new bq();
    }

    private bq() {
        this.f18918a = null;
        this.f18919b = null;
        this.f18920c = null;
        this.f18921d = false;
        this.f18922e = false;
        this.f18923f = 0;
        this.f18924g = "main_fest_mode";
        this.f18925h = "main_fest_timestamp";
        this.f18926j = new ArrayList();
        this.f18927k = null;
        this.f18928l = new Thread(new C59141(this));
        if (f18917i != null) {
            if (this.f18918a == null) {
                this.f18918a = new C5946h();
            }
            if (this.f18919b == null) {
                this.f18919b = bs.m21764a(f18917i);
            }
            if (this.f18920c == null) {
                this.f18920c = new C5952p();
            }
        }
        this.f18928l.start();
    }

    /* renamed from: d */
    private void m21737d() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f18927k.sendEmptyMessageDelayed(48, bt.m21778b(currentTimeMillis));
        this.f18927k.sendEmptyMessageDelayed(49, bt.m21779c(currentTimeMillis));
    }

    /* renamed from: a */
    public static final bq m21732a(Context context) {
        f18917i = context;
        return C5925b.f18916a;
    }

    /* renamed from: a */
    public void m21754a(final bo boVar) {
        if (!this.f18921d) {
            C4754h.m18673b(new C4747i(this) {
                /* renamed from: b */
                final /* synthetic */ bq f18914b;

                /* compiled from: UMCCAggregatedManager */
                /* renamed from: u.aly.bq$9$1 */
                class C59221 extends bo {
                    /* renamed from: a */
                    final /* synthetic */ C59239 f18912a;

                    C59221(C59239 c59239) {
                        this.f18912a = c59239;
                    }

                    /* renamed from: a */
                    public void mo6179a(Object obj, boolean z) {
                        if (obj instanceof Map) {
                            this.f18912a.f18914b.f18918a.m21947a((Map) obj);
                        } else if (!(obj instanceof String) && (obj instanceof Boolean)) {
                        }
                        this.f18912a.f18914b.f18921d = true;
                    }
                }

                /* renamed from: a */
                public void mo6180a() {
                    try {
                        this.f18914b.f18919b.m21766a(new C59221(this));
                        this.f18914b.m21742f();
                        this.f18914b.m21749j();
                        boVar.mo6179a("success", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* renamed from: e */
    private void m21740e() {
        Editor edit = C5955u.m22014a(f18917i).edit();
        edit.putBoolean("main_fest_mode", false);
        edit.putLong("main_fest_timestamp", 0);
        edit.commit();
        this.f18922e = false;
    }

    /* renamed from: f */
    private void m21742f() {
        SharedPreferences a = C5955u.m22014a(f18917i);
        this.f18922e = a.getBoolean("main_fest_mode", false);
        this.f18923f = a.getLong("main_fest_timestamp", 0);
    }

    /* renamed from: a */
    public void m21753a(av avVar) {
        if (avVar.f18721b.f18659h != null) {
            avVar.f18721b.f18659h.f18612a = m21755b(new bo());
            avVar.f18721b.f18659h.f18613b = m21757c(new bo());
        }
    }

    /* renamed from: b */
    public Map<String, List<C5858e>> m21755b(bo boVar) {
        Map a = this.f18919b.m21765a();
        Map<String, List<C5858e>> hashMap = new HashMap();
        if (a == null || a.size() <= 0) {
            return null;
        }
        for (String str : this.f18926j) {
            if (a.containsKey(str)) {
                hashMap.put(str, a.get(str));
            }
        }
        return hashMap;
    }

    /* renamed from: c */
    public Map<String, List<C5859f>> m21757c(bo boVar) {
        if (this.f18920c.m21998a().size() > 0) {
            this.f18919b.m21773b(new C59152(this), this.f18920c.m21998a());
        }
        return this.f18919b.m21772b(new bo());
    }

    /* renamed from: d */
    public void m21758d(bo boVar) {
        boolean z = false;
        if (this.f18922e) {
            if (this.f18923f == 0) {
                m21742f();
            }
            z = bt.m21777a(System.currentTimeMillis(), this.f18923f);
        }
        if (!z) {
            m21740e();
            this.f18926j.clear();
        }
        this.f18920c.m22003b();
        this.f18919b.m21770a(new C59163(this), z);
    }

    /* renamed from: g */
    private void m21743g() {
        for (Entry key : this.f18918a.m21945a().entrySet()) {
            List list = (List) key.getKey();
            if (!this.f18926j.contains(list)) {
                this.f18926j.add(bj.m21699a(list));
            }
        }
        if (this.f18926j.size() > 0) {
            this.f18919b.m21768a(new bo(), this.f18926j);
        }
    }

    /* renamed from: a */
    public void m21752a(long j, long j2, String str) {
        this.f18919b.m21767a(new C59174(this), str, j, j2);
    }

    /* renamed from: h */
    private void m21745h() {
        try {
            if (this.f18918a.m21945a().size() > 0) {
                this.f18919b.m21774c(new C59185(this), this.f18918a.m21945a());
            }
            if (this.f18920c.m21998a().size() > 0) {
                this.f18919b.m21773b(new C59196(this), this.f18920c.m21998a());
            }
            if (this.f18926j.size() > 0) {
                this.f18919b.m21768a(new bo(), this.f18926j);
            }
        } catch (Throwable th) {
            ah.m21153a("converyMemoryToDataTable happen error: " + th.toString());
        }
    }

    /* renamed from: i */
    private void m21748i() {
        try {
            if (this.f18918a.m21945a().size() > 0) {
                this.f18919b.m21769a(new C59207(this), this.f18918a.m21945a());
            }
            if (this.f18920c.m21998a().size() > 0) {
                this.f18919b.m21773b(new C59218(this), this.f18920c.m21998a());
            }
            if (this.f18926j.size() > 0) {
                this.f18919b.m21768a(new bo(), this.f18926j);
            }
        } catch (Throwable th) {
            ah.m21153a("convertMemoryToCacheTable happen error: " + th.toString());
        }
    }

    /* renamed from: j */
    private void m21749j() {
        List b = this.f18919b.m21771b();
        if (b != null) {
            this.f18926j = b;
        }
    }

    /* renamed from: a */
    public void m21751a() {
        m21748i();
    }

    /* renamed from: b */
    public void m21756b() {
        m21748i();
    }
}
