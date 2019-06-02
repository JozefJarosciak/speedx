package com.facebook.internal;

import android.app.Activity;
import android.util.Log;
import com.facebook.C1472c;
import com.facebook.FacebookException;
import java.util.List;

/* compiled from: FacebookDialogBase */
/* renamed from: com.facebook.internal.g */
public abstract class C3015g<CONTENT, RESULT> {
    /* renamed from: a */
    protected static final Object f13546a = new Object();
    /* renamed from: b */
    private final Activity f13547b;
    /* renamed from: c */
    private final C3021j f13548c = null;
    /* renamed from: d */
    private List<C3014a> f13549d;
    /* renamed from: e */
    private int f13550e;

    /* compiled from: FacebookDialogBase */
    /* renamed from: com.facebook.internal.g$a */
    protected abstract class C3014a {
        /* renamed from: a */
        final /* synthetic */ C3015g f13545a;

        /* renamed from: a */
        public abstract C2996a mo3725a(CONTENT content);

        /* renamed from: a */
        public abstract boolean mo3727a(CONTENT content, boolean z);

        protected C3014a(C3015g c3015g) {
            this.f13545a = c3015g;
        }

        /* renamed from: a */
        public Object mo3726a() {
            return C3015g.f13546a;
        }
    }

    /* renamed from: c */
    protected abstract List<C3014a> mo3730c();

    /* renamed from: d */
    protected abstract C2996a mo3731d();

    protected C3015g(Activity activity, int i) {
        C3049t.m14790a((Object) activity, "activity");
        this.f13547b = activity;
        this.f13550e = i;
    }

    /* renamed from: a */
    public int m14578a() {
        return this.f13550e;
    }

    /* renamed from: a */
    public void m14579a(CONTENT content) {
        m14580a(content, f13546a);
    }

    /* renamed from: a */
    protected void m14580a(CONTENT content, Object obj) {
        C2996a b = m14576b(content, obj);
        if (b == null) {
            String str = "No code path should ever result in a null appCall";
            Log.e("FacebookDialog", str);
            if (C1472c.b()) {
                throw new IllegalStateException(str);
            }
        } else if (this.f13548c != null) {
            C3013f.m14567a(b, this.f13548c);
        } else {
            C3013f.m14564a(b, this.f13547b);
        }
    }

    /* renamed from: b */
    protected Activity m14581b() {
        if (this.f13547b != null) {
            return this.f13547b;
        }
        if (this.f13548c != null) {
            return this.f13548c.m14611a();
        }
        return null;
    }

    /* renamed from: b */
    private C2996a m14576b(CONTENT content, Object obj) {
        boolean z;
        C2996a a;
        if (obj == f13546a) {
            z = true;
        } else {
            z = false;
        }
        for (C3014a c3014a : mo3732e()) {
            if ((z || C3048s.m14760a(c3014a.mo3726a(), obj)) && c3014a.mo3727a(content, true)) {
                try {
                    a = c3014a.mo3725a(content);
                    break;
                } catch (FacebookException e) {
                    a = mo3731d();
                    C3013f.m14565a(a, e);
                }
            }
        }
        a = null;
        if (a != null) {
            return a;
        }
        a = mo3731d();
        C3013f.m14563a(a);
        return a;
    }

    /* renamed from: e */
    private List<C3014a> mo3732e() {
        if (this.f13549d == null) {
            this.f13549d = mo3730c();
        }
        return this.f13549d;
    }
}
