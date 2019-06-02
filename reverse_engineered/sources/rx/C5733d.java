package rx;

import java.util.concurrent.TimeUnit;
import rx.p208a.C5711a;

/* compiled from: Scheduler */
/* renamed from: rx.d */
public abstract class C5733d {
    /* renamed from: a */
    static final long f18294a = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* compiled from: Scheduler */
    /* renamed from: rx.d$a */
    public static abstract class C5726a implements C5720f {
        /* renamed from: a */
        public abstract C5720f mo7166a(C5711a c5711a);

        /* renamed from: a */
        public long m20828a() {
            return System.currentTimeMillis();
        }
    }

    /* renamed from: a */
    public abstract C5726a mo7167a();

    /* renamed from: b */
    public long m20847b() {
        return System.currentTimeMillis();
    }
}
