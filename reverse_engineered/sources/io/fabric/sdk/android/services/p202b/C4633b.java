package io.fabric.sdk.android.services.p202b;

import android.content.Context;
import io.fabric.sdk.android.services.common.C4877i;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: EnabledEventsStrategy */
/* renamed from: io.fabric.sdk.android.services.b.b */
public abstract class C4633b<T> implements C4632i<T> {
    /* renamed from: a */
    protected final Context f16324a;
    /* renamed from: b */
    protected final C4638d<T> f16325b;
    /* renamed from: c */
    final ScheduledExecutorService f16326c;
    /* renamed from: d */
    final AtomicReference<ScheduledFuture<?>> f16327d;
    /* renamed from: e */
    volatile int f16328e = -1;

    public C4633b(Context context, ScheduledExecutorService scheduledExecutorService, C4638d<T> c4638d) {
        this.f16324a = context;
        this.f16326c = scheduledExecutorService;
        this.f16325b = c4638d;
        this.f16327d = new AtomicReference();
    }

    /* renamed from: e */
    public void m18336e() {
        if ((this.f16328e != -1 ? 1 : null) != null) {
            m18331a((long) this.f16328e, (long) this.f16328e);
        }
    }

    /* renamed from: b */
    public void mo6144b() {
        m18337f();
    }

    /* renamed from: c */
    public void mo6145c() {
        if (this.f16327d.get() != null) {
            C4877i.m19157a(this.f16324a, "Cancelling time-based rollover because no events are currently being generated.");
            ((ScheduledFuture) this.f16327d.get()).cancel(false);
            this.f16327d.set(null);
        }
    }

    /* renamed from: a */
    public void mo6143a(T t) {
        C4877i.m19157a(this.f16324a, t.toString());
        try {
            this.f16325b.m18355a((Object) t);
        } catch (Throwable e) {
            C4877i.m19158a(this.f16324a, "Failed to write event.", e);
        }
        m18336e();
    }

    /* renamed from: d */
    public boolean mo6146d() {
        try {
            return this.f16325b.m18357b();
        } catch (Throwable e) {
            C4877i.m19158a(this.f16324a, "Failed to roll file over.", e);
            return false;
        }
    }

    /* renamed from: a */
    protected void m18330a(int i) {
        this.f16328e = i;
        m18331a(0, (long) this.f16328e);
    }

    /* renamed from: a */
    void m18331a(long j, long j2) {
        if ((this.f16327d.get() == null ? 1 : null) != null) {
            Runnable c4860m = new C4860m(this.f16324a, this);
            C4877i.m19157a(this.f16324a, "Scheduling time based file roll over every " + j2 + " seconds");
            try {
                this.f16327d.set(this.f16326c.scheduleAtFixedRate(c4860m, j, j2, TimeUnit.SECONDS));
            } catch (Throwable e) {
                C4877i.m19158a(this.f16324a, "Failed to schedule time based file roll over", e);
            }
        }
    }

    /* renamed from: f */
    void m18337f() {
        C4627k a = mo6147a();
        if (a == null) {
            C4877i.m19157a(this.f16324a, "skipping files send because we don't yet know the target endpoint");
            return;
        }
        C4877i.m19157a(this.f16324a, "Sending all files");
        List e = this.f16325b.m18360e();
        int i = 0;
        while (e.size() > 0) {
            int size;
            C4877i.m19157a(this.f16324a, String.format(Locale.US, "attempt to send batch of %d files", new Object[]{Integer.valueOf(e.size())}));
            boolean a2 = a.mo6142a(e);
            if (a2) {
                size = e.size() + i;
                try {
                    this.f16325b.m18356a(e);
                    i = size;
                } catch (Exception e2) {
                    Throwable e3 = e2;
                }
            }
            if (!a2) {
                break;
            }
            try {
                e = this.f16325b.m18360e();
            } catch (Throwable e4) {
                Throwable th = e4;
                size = i;
                e3 = th;
            }
        }
        if (i == 0) {
            this.f16325b.m18361f();
        }
        C4877i.m19158a(this.f16324a, "Failed to send batch of analytics files to server: " + e3.getMessage(), e3);
        i = size;
        if (i == 0) {
            this.f16325b.m18361f();
        }
    }
}
