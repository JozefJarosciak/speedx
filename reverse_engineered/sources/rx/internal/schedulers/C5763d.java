package rx.internal.schedulers;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.C5720f;
import rx.C5733d.C5726a;
import rx.exceptions.C5736a;
import rx.internal.util.C5830d;
import rx.internal.util.C5835g;
import rx.internal.util.RxThreadFactory;
import rx.p208a.C5711a;
import rx.p211d.C5731d;
import rx.p211d.C5732e;
import rx.subscriptions.Subscriptions;

/* compiled from: NewThreadWorker */
/* renamed from: rx.internal.schedulers.d */
public class C5763d extends C5726a implements C5720f {
    /* renamed from: b */
    public static final int f18408b = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();
    /* renamed from: e */
    private static final boolean f18409e;
    /* renamed from: f */
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> f18410f = new ConcurrentHashMap();
    /* renamed from: g */
    private static final AtomicReference<ScheduledExecutorService> f18411g = new AtomicReference();
    /* renamed from: h */
    private static volatile Object f18412h;
    /* renamed from: i */
    private static final Object f18413i = new Object();
    /* renamed from: a */
    volatile boolean f18414a;
    /* renamed from: c */
    private final ScheduledExecutorService f18415c;
    /* renamed from: d */
    private final C5732e f18416d;

    /* compiled from: NewThreadWorker */
    /* renamed from: rx.internal.schedulers.d$1 */
    static class C57691 implements Runnable {
        C57691() {
        }

        public void run() {
            C5763d.m20939b();
        }
    }

    static {
        boolean z = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int b = C5830d.m21029b();
        if (z || (b != 0 && b < 21)) {
            z = false;
        } else {
            z = true;
        }
        f18409e = z;
    }

    /* renamed from: a */
    public static void m20938a(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (((ScheduledExecutorService) f18411g.get()) == null) {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
            if (f18411g.compareAndSet(null, newScheduledThreadPool)) {
                newScheduledThreadPool.scheduleAtFixedRate(new C57691(), (long) f18408b, (long) f18408b, TimeUnit.MILLISECONDS);
                break;
            }
            newScheduledThreadPool.shutdownNow();
        }
        f18410f.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    /* renamed from: a */
    public static void m20937a(ScheduledExecutorService scheduledExecutorService) {
        f18410f.remove(scheduledExecutorService);
    }

    /* renamed from: b */
    static void m20939b() {
        try {
            Iterator it = f18410f.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    it.remove();
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        } catch (Throwable th) {
            C5736a.m20858a(th);
            C5731d.m20840a().m20841b().m20831a(th);
        }
    }

    /* renamed from: b */
    public static boolean m20940b(ScheduledExecutorService scheduledExecutorService) {
        if (f18409e) {
            Method c;
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = f18412h;
                if (obj == f18413i) {
                    return false;
                }
                if (obj == null) {
                    c = C5763d.m20941c(scheduledExecutorService);
                    if (c != null) {
                        obj = c;
                    } else {
                        obj = f18413i;
                    }
                    f18412h = obj;
                } else {
                    c = (Method) obj;
                }
            } else {
                c = C5763d.m20941c(scheduledExecutorService);
            }
            if (c != null) {
                try {
                    c.invoke(scheduledExecutorService, new Object[]{Boolean.valueOf(true)});
                    return true;
                } catch (Throwable e) {
                    C5731d.m20840a().m20841b().m20831a(e);
                }
            }
        }
        return false;
    }

    /* renamed from: c */
    static Method m20941c(ScheduledExecutorService scheduledExecutorService) {
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public C5763d(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!C5763d.m20940b(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            C5763d.m20938a((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
        this.f18416d = C5731d.m20840a().m20843d();
        this.f18415c = newScheduledThreadPool;
    }

    /* renamed from: a */
    public C5720f mo7166a(C5711a c5711a) {
        return m20943a(c5711a, 0, null);
    }

    /* renamed from: a */
    public C5720f m20943a(C5711a c5711a, long j, TimeUnit timeUnit) {
        if (this.f18414a) {
            return Subscriptions.unsubscribed();
        }
        return m20945b(c5711a, j, timeUnit);
    }

    /* renamed from: b */
    public ScheduledAction m20945b(C5711a c5711a, long j, TimeUnit timeUnit) {
        Future submit;
        Runnable scheduledAction = new ScheduledAction(this.f18416d.m20845a(c5711a));
        if (j <= 0) {
            submit = this.f18415c.submit(scheduledAction);
        } else {
            submit = this.f18415c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    /* renamed from: a */
    public ScheduledAction m20944a(C5711a c5711a, long j, TimeUnit timeUnit, C5835g c5835g) {
        Future submit;
        C5720f scheduledAction = new ScheduledAction(this.f18416d.m20845a(c5711a), c5835g);
        c5835g.m21040a(scheduledAction);
        if (j <= 0) {
            submit = this.f18415c.submit(scheduledAction);
        } else {
            submit = this.f18415c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    public void unsubscribe() {
        this.f18414a = true;
        this.f18415c.shutdownNow();
        C5763d.m20937a(this.f18415c);
    }

    public boolean isUnsubscribed() {
        return this.f18414a;
    }
}
