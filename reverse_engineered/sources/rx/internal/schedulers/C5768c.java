package rx.internal.schedulers;

import rx.C5720f;
import rx.C5733d;
import rx.C5733d.C5726a;
import rx.p208a.C5711a;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

/* compiled from: ImmediateScheduler */
/* renamed from: rx.internal.schedulers.c */
public final class C5768c extends C5733d {
    /* renamed from: b */
    public static final C5768c f18429b = new C5768c();

    /* compiled from: ImmediateScheduler */
    /* renamed from: rx.internal.schedulers.c$a */
    private class C5767a extends C5726a implements C5720f {
        /* renamed from: a */
        final BooleanSubscription f18427a = new BooleanSubscription();
        /* renamed from: b */
        final /* synthetic */ C5768c f18428b;

        C5767a(C5768c c5768c) {
            this.f18428b = c5768c;
        }

        /* renamed from: a */
        public C5720f mo7166a(C5711a c5711a) {
            c5711a.call();
            return Subscriptions.unsubscribed();
        }

        public void unsubscribe() {
            this.f18427a.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.f18427a.isUnsubscribed();
        }
    }

    private C5768c() {
    }

    /* renamed from: a */
    public C5726a mo7167a() {
        return new C5767a(this);
    }
}
