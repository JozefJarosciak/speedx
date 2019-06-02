package rx.p210c;

import java.util.Arrays;
import rx.C5719b;
import rx.C5721e;
import rx.exceptions.C5736a;
import rx.exceptions.CompositeException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.UnsubscribeFailedException;
import rx.internal.util.C5831e;

/* compiled from: SafeSubscriber */
/* renamed from: rx.c.a */
public class C5722a<T> extends C5721e<T> {
    /* renamed from: a */
    boolean f18276a = false;
    /* renamed from: b */
    private final C5721e<? super T> f18277b;

    public C5722a(C5721e<? super T> c5721e) {
        super(c5721e);
        this.f18277b = c5721e;
    }

    /* renamed from: a */
    public void mo7149a() {
        UnsubscribeFailedException unsubscribeFailedException;
        if (!this.f18276a) {
            this.f18276a = true;
            try {
                this.f18277b.mo7149a();
                try {
                    unsubscribe();
                } catch (Throwable th) {
                    C5831e.m21032a(th);
                    unsubscribeFailedException = new UnsubscribeFailedException(th.getMessage(), th);
                }
            } catch (Throwable th2) {
                try {
                    unsubscribe();
                } catch (Throwable th3) {
                    C5831e.m21032a(th3);
                    unsubscribeFailedException = new UnsubscribeFailedException(th3.getMessage(), th3);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo7151a(Throwable th) {
        C5736a.m20858a(th);
        if (!this.f18276a) {
            this.f18276a = true;
            m20823b(th);
        }
    }

    /* renamed from: a */
    public void mo7150a(T t) {
        try {
            if (!this.f18276a) {
                this.f18277b.mo7150a((Object) t);
            }
        } catch (Throwable th) {
            C5736a.m20860a(th, (C5719b) this);
        }
    }

    /* renamed from: b */
    protected void m20823b(Throwable th) {
        C5831e.m21032a(th);
        try {
            this.f18277b.mo7151a(th);
            try {
                unsubscribe();
            } catch (Throwable e) {
                C5831e.m21032a(e);
                throw new OnErrorFailedException(e);
            }
        } catch (Throwable th2) {
            C5831e.m21032a(th2);
            OnErrorFailedException onErrorFailedException = new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(new Throwable[]{th, e, th2})));
        }
    }
}
