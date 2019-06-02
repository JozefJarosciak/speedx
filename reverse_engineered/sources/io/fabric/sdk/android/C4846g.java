package io.fabric.sdk.android;

import io.fabric.sdk.android.services.common.C4892q;
import io.fabric.sdk.android.services.concurrency.C4845c;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;

/* compiled from: InitializationTask */
/* renamed from: io.fabric.sdk.android.g */
class C4846g<Result> extends C4845c<Void, Void, Result> {
    /* renamed from: a */
    final C1468h<Result> f17104a;

    public C4846g(C1468h<Result> c1468h) {
        this.f17104a = c1468h;
    }

    /* renamed from: a */
    protected void mo6234a() {
        super.mo6234a();
        C4892q a = m19049a("onPreExecute");
        try {
            boolean d = this.f17104a.d();
            a.m19227b();
            if (!d) {
                m19029a(true);
            }
        } catch (UnmetDependencyException e) {
            throw e;
        } catch (Throwable e2) {
            C1520c.h().mo6222d("Fabric", "Failure onPreExecute()", e2);
            a.m19227b();
            m19029a(true);
        } catch (Throwable th) {
            a.m19227b();
            m19029a(true);
        }
    }

    /* renamed from: a */
    protected Result m19051a(Void... voidArr) {
        C4892q a = m19049a("doInBackground");
        Result result = null;
        if (!m19033e()) {
            result = this.f17104a.n();
        }
        a.m19227b();
        return result;
    }

    /* renamed from: a */
    protected void mo6235a(Result result) {
        this.f17104a.a(result);
        this.f17104a.f6864j.mo6226a((Object) result);
    }

    /* renamed from: b */
    protected void mo6236b(Result result) {
        this.f17104a.b(result);
        this.f17104a.f6864j.mo6225a(new InitializationException(this.f17104a.g() + " Initialization was cancelled"));
    }

    /* renamed from: b */
    public Priority mo6228b() {
        return Priority.HIGH;
    }

    /* renamed from: a */
    private C4892q m19049a(String str) {
        C4892q c4892q = new C4892q(this.f17104a.g() + "." + str, "KitInitialization");
        c4892q.m19226a();
        return c4892q;
    }
}
