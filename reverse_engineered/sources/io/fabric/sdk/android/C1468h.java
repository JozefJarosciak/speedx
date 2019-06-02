package io.fabric.sdk.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.C4883l;
import io.fabric.sdk.android.services.concurrency.C1522b;
import java.io.File;

/* compiled from: Kit */
/* renamed from: io.fabric.sdk.android.h */
public abstract class C1468h<Result> implements Comparable<C1468h> {
    /* renamed from: g */
    C1520c f6861g;
    /* renamed from: h */
    C4846g<Result> f6862h = new C4846g(this);
    /* renamed from: i */
    Context f6863i;
    /* renamed from: j */
    C4837f<Result> f6864j;
    /* renamed from: k */
    C4883l f6865k;

    /* renamed from: c */
    public abstract String mo2810c();

    /* renamed from: g */
    public abstract String mo2812g();

    /* renamed from: n */
    protected abstract Result mo2813n();

    public /* synthetic */ int compareTo(Object obj) {
        return m8068a((C1468h) obj);
    }

    /* renamed from: a */
    void m8069a(Context context, C1520c c1520c, C4837f<Result> c4837f, C4883l c4883l) {
        this.f6861g = c1520c;
        this.f6863i = new C4838d(context, mo2812g(), m8081s());
        this.f6864j = c4837f;
        this.f6865k = c4883l;
    }

    /* renamed from: o */
    final void m8077o() {
        this.f6862h.a(this.f6861g.m8367e(), new Void[]{(Void) null});
    }

    /* renamed from: d */
    protected boolean mo2811d() {
        return true;
    }

    /* renamed from: a */
    protected void m8070a(Result result) {
    }

    /* renamed from: b */
    protected void m8071b(Result result) {
    }

    /* renamed from: p */
    protected C4883l m8078p() {
        return this.f6865k;
    }

    /* renamed from: q */
    public Context m8079q() {
        return this.f6863i;
    }

    /* renamed from: r */
    public C1520c m8080r() {
        return this.f6861g;
    }

    /* renamed from: s */
    public String m8081s() {
        return ".Fabric" + File.separator + mo2812g();
    }

    /* renamed from: a */
    public int m8068a(C1468h c1468h) {
        if (m8072b(c1468h)) {
            return 1;
        }
        if (c1468h.m8072b(this)) {
            return -1;
        }
        if (m8082t() && !c1468h.m8082t()) {
            return 1;
        }
        if (m8082t() || !c1468h.m8082t()) {
            return 0;
        }
        return -1;
    }

    /* renamed from: b */
    boolean m8072b(C1468h c1468h) {
        C1522b c1522b = (C1522b) getClass().getAnnotation(C1522b.class);
        if (c1522b != null) {
            for (Object equals : c1522b.m8373a()) {
                if (equals.equals(c1468h.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: t */
    boolean m8082t() {
        return ((C1522b) getClass().getAnnotation(C1522b.class)) != null;
    }
}
