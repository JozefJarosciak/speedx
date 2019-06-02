package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent.C1519a;
import p203u.aly.C4751s;
import p203u.aly.C5862n;
import p203u.aly.C5943f;
import p203u.aly.C5944g;
import p203u.aly.C5948j;
import p203u.aly.C5955u;
import p203u.aly.C5958w;
import p203u.aly.C5960y;
import p203u.aly.ah;
import p203u.aly.av.C5863i;
import p203u.aly.bo;
import p203u.aly.bq;

/* compiled from: InternalAgent */
/* renamed from: com.umeng.analytics.f */
public class C4752f implements C4751s {
    /* renamed from: a */
    private Context f16649a = null;
    /* renamed from: b */
    private C4745e f16650b;
    /* renamed from: c */
    private C5944g f16651c = new C5944g();
    /* renamed from: d */
    private C5960y f16652d = new C5960y();
    /* renamed from: e */
    private C5958w f16653e = new C5958w();
    /* renamed from: f */
    private C5948j f16654f;
    /* renamed from: g */
    private C5943f f16655g;
    /* renamed from: h */
    private bq f16656h = null;
    /* renamed from: i */
    private boolean f16657i = false;
    /* renamed from: j */
    private boolean f16658j = false;

    /* compiled from: InternalAgent */
    /* renamed from: com.umeng.analytics.f$1 */
    class C47481 extends C4747i {
        /* renamed from: a */
        final /* synthetic */ C4752f f16644a;

        /* compiled from: InternalAgent */
        /* renamed from: com.umeng.analytics.f$1$1 */
        class C47461 extends bo {
            /* renamed from: a */
            final /* synthetic */ C47481 f16643a;

            C47461(C47481 c47481) {
                this.f16643a = c47481;
            }

            /* renamed from: a */
            public void mo6179a(Object obj, boolean z) {
                this.f16643a.f16644a.f16658j = true;
            }
        }

        C47481(C4752f c4752f) {
            this.f16644a = c4752f;
        }

        /* renamed from: a */
        public void mo6180a() {
            this.f16644a.f16656h.m21754a(new C47461(this));
        }
    }

    C4752f() {
        this.f16651c.m21939a((C4751s) this);
    }

    /* renamed from: c */
    private void m18659c(Context context) {
        if (!this.f16657i) {
            this.f16649a = context.getApplicationContext();
            this.f16654f = new C5948j(this.f16649a);
            this.f16655g = C5943f.m21932a(this.f16649a);
            this.f16657i = true;
            if (this.f16656h == null) {
                this.f16656h = bq.m21732a(this.f16649a);
            }
            if (!this.f16658j) {
                C4754h.m18673b(new C47481(this));
            }
        }
    }

    /* renamed from: a */
    public void m18663a(Context context, int i) {
        C4731a.m18615a(context, i);
    }

    /* renamed from: a */
    void m18662a(final Context context) {
        if (context == null) {
            ah.m21165d("unexpected null context in onResume");
            return;
        }
        if (C4731a.f16609e) {
            this.f16652d.m22060a(context.getClass().getName());
        }
        try {
            if (!this.f16657i) {
                m18659c(context);
            }
            C4754h.m18671a(new C4747i(this) {
                /* renamed from: b */
                final /* synthetic */ C4752f f16646b;

                /* renamed from: a */
                public void mo6180a() {
                    this.f16646b.m18660d(context.getApplicationContext());
                }
            });
        } catch (Throwable e) {
            ah.m21160b("Exception occurred in Mobclick.onResume(). ", e);
        }
    }

    /* renamed from: b */
    void m18668b(final Context context) {
        if (context == null) {
            ah.m21165d("unexpected null context in onPause");
            return;
        }
        if (C4731a.f16609e) {
            this.f16652d.m22061b(context.getClass().getName());
        }
        try {
            if (!this.f16657i) {
                m18659c(context);
            }
            C4754h.m18671a(new C4747i(this) {
                /* renamed from: b */
                final /* synthetic */ C4752f f16648b;

                /* renamed from: a */
                public void mo6180a() {
                    this.f16648b.m18661e(context.getApplicationContext());
                    this.f16648b.f16656h.m21756b();
                }
            });
        } catch (Throwable e) {
            ah.m21160b("Exception occurred in Mobclick.onRause(). ", e);
        }
    }

    /* renamed from: d */
    private void m18660d(Context context) {
        this.f16653e.m22037c(context);
        if (this.f16650b != null) {
            this.f16650b.m18645a();
        }
    }

    /* renamed from: e */
    private void m18661e(Context context) {
        this.f16653e.m22038d(context);
        this.f16652d.m22059a(context);
        if (this.f16650b != null) {
            this.f16650b.m18646b();
        }
        this.f16655g.mo7224b();
    }

    /* renamed from: a */
    public void m18665a(Context context, String str, String str2, long j, int i) {
        try {
            if (!this.f16657i) {
                m18659c(context);
            }
            this.f16654f.m21976a(str, str2, j, i);
        } catch (Throwable e) {
            ah.m21157a(e);
        }
    }

    /* renamed from: a */
    public void mo6181a(Throwable th) {
        try {
            this.f16652d.m22058a();
            if (this.f16649a != null) {
                if (!(th == null || this.f16655g == null)) {
                    C5862n c5863i = new C5863i();
                    c5863i.f18632a = System.currentTimeMillis();
                    c5863i.f18633b = 1;
                    c5863i.f18634c = C4744d.m18639a(th);
                    this.f16655g.mo7223a(c5863i);
                }
                this.f16656h.m21751a();
                m18661e(this.f16649a);
                C5955u.m22014a(this.f16649a).edit().commit();
            }
            C4754h.m18670a();
        } catch (Throwable e) {
            ah.m21160b("Exception in onAppCrash", e);
        }
    }

    /* renamed from: a */
    void m18664a(Context context, MobclickAgent$EScenarioType mobclickAgent$EScenarioType) {
        if (context != null) {
            this.f16649a = context.getApplicationContext();
        }
        if (mobclickAgent$EScenarioType != null) {
            m18663a(context, mobclickAgent$EScenarioType.toValue());
        }
    }

    /* renamed from: a */
    void m18666a(C1519a c1519a) {
        if (c1519a.f7154e != null) {
            this.f16649a = c1519a.f7154e.getApplicationContext();
        }
        if (TextUtils.isEmpty(c1519a.f7150a)) {
            ah.m21165d("the appkey is null!");
            return;
        }
        C4731a.m18616a(c1519a.f7154e, c1519a.f7150a);
        if (!TextUtils.isEmpty(c1519a.f7151b)) {
            C4731a.m18617a(c1519a.f7151b);
        }
        C4731a.f16612h = c1519a.f7152c;
        m18664a(this.f16649a, c1519a.f7153d);
    }
}
