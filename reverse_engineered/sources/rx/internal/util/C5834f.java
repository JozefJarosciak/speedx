package rx.internal.util;

import java.util.Queue;
import rx.C5720f;
import rx.internal.operators.NotificationLite;
import rx.internal.util.p214b.C5818j;
import rx.internal.util.p214b.C5825r;

/* compiled from: RxRingBuffer */
/* renamed from: rx.internal.util.f */
public class C5834f implements C5720f {
    /* renamed from: a */
    static int f18520a;
    /* renamed from: b */
    public static final int f18521b = f18520a;
    /* renamed from: c */
    public static C5827b<Queue<Object>> f18522c = new C58321();
    /* renamed from: d */
    public static C5827b<Queue<Object>> f18523d = new C58332();
    /* renamed from: e */
    private static final NotificationLite<Object> f18524e = NotificationLite.m20864a();
    /* renamed from: f */
    private Queue<Object> f18525f;
    /* renamed from: g */
    private final C5827b<Queue<Object>> f18526g;

    /* compiled from: RxRingBuffer */
    /* renamed from: rx.internal.util.f$1 */
    static class C58321 extends C5827b<Queue<Object>> {
        C58321() {
        }

        /* renamed from: b */
        protected /* synthetic */ Object mo7170b() {
            return m21035c();
        }

        /* renamed from: c */
        protected C5825r<Object> m21035c() {
            return new C5825r(C5834f.f18521b);
        }
    }

    /* compiled from: RxRingBuffer */
    /* renamed from: rx.internal.util.f$2 */
    static class C58332 extends C5827b<Queue<Object>> {
        C58332() {
        }

        /* renamed from: b */
        protected /* synthetic */ Object mo7170b() {
            return m21037c();
        }

        /* renamed from: c */
        protected C5818j<Object> m21037c() {
            return new C5818j(C5834f.f18521b);
        }
    }

    static {
        f18520a = 128;
        if (C5830d.m21028a()) {
            f18520a = 16;
        }
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                f18520a = Integer.parseInt(property);
            } catch (Exception e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
    }

    /* renamed from: a */
    public synchronized void m21038a() {
        Object obj = this.f18525f;
        C5827b c5827b = this.f18526g;
        if (!(c5827b == null || obj == null)) {
            obj.clear();
            this.f18525f = null;
            c5827b.m21018a(obj);
        }
    }

    public void unsubscribe() {
        m21038a();
    }

    public boolean isUnsubscribed() {
        return this.f18525f == null;
    }
}
