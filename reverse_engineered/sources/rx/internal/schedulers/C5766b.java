package rx.internal.schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.util.RxThreadFactory;

/* compiled from: GenericScheduledExecutorService */
/* renamed from: rx.internal.schedulers.b */
public final class C5766b {
    /* renamed from: a */
    public static final C5766b f18421a = new C5766b();
    /* renamed from: b */
    private static final RxThreadFactory f18422b = new RxThreadFactory("RxScheduledExecutorPool-");
    /* renamed from: c */
    private static final ScheduledExecutorService[] f18423c = new ScheduledExecutorService[0];
    /* renamed from: d */
    private static final ScheduledExecutorService f18424d = Executors.newScheduledThreadPool(0);
    /* renamed from: f */
    private static int f18425f;
    /* renamed from: e */
    private final AtomicReference<ScheduledExecutorService[]> f18426e = new AtomicReference(f18423c);

    static {
        f18424d.shutdown();
    }

    private C5766b() {
        m20949a();
    }

    /* renamed from: a */
    public void m20949a() {
        int i = 8;
        int i2 = 0;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors > 4) {
            availableProcessors /= 2;
        }
        if (availableProcessors <= 8) {
            i = availableProcessors;
        }
        Object obj = new ScheduledExecutorService[i];
        for (availableProcessors = 0; availableProcessors < i; availableProcessors++) {
            obj[availableProcessors] = Executors.newScheduledThreadPool(1, f18422b);
        }
        if (this.f18426e.compareAndSet(f18423c, obj)) {
            availableProcessors = obj.length;
            while (i2 < availableProcessors) {
                ScheduledExecutorService scheduledExecutorService = obj[i2];
                if (!C5763d.m20940b(scheduledExecutorService) && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
                    C5763d.m20938a((ScheduledThreadPoolExecutor) scheduledExecutorService);
                }
                i2++;
            }
            return;
        }
        for (ScheduledExecutorService shutdownNow : obj) {
            shutdownNow.shutdownNow();
        }
    }

    /* renamed from: b */
    public static ScheduledExecutorService m20948b() {
        ScheduledExecutorService[] scheduledExecutorServiceArr = (ScheduledExecutorService[]) f18421a.f18426e.get();
        if (scheduledExecutorServiceArr == f18423c) {
            return f18424d;
        }
        int i = f18425f + 1;
        if (i >= scheduledExecutorServiceArr.length) {
            i = 0;
        }
        f18425f = i;
        return scheduledExecutorServiceArr[i];
    }
}
