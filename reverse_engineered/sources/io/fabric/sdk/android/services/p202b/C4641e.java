package io.fabric.sdk.android.services.p202b;

import android.content.Context;
import io.fabric.sdk.android.services.common.C4877i;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: EventsHandler */
/* renamed from: io.fabric.sdk.android.services.b.e */
public abstract class C4641e<T> implements C4640h {
    /* renamed from: a */
    protected final Context f16352a;
    /* renamed from: b */
    protected final ScheduledExecutorService f16353b;
    /* renamed from: c */
    protected C4632i<T> f16354c;

    /* compiled from: EventsHandler */
    /* renamed from: io.fabric.sdk.android.services.b.e$2 */
    class C48572 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4641e f17127a;

        C48572(C4641e c4641e) {
            this.f17127a = c4641e;
        }

        public void run() {
            try {
                this.f17127a.f16354c.mo6144b();
            } catch (Throwable e) {
                C4877i.m19158a(this.f17127a.f16352a, "Failed to send events files.", e);
            }
        }
    }

    public C4641e(Context context, C4632i<T> c4632i, C4638d c4638d, ScheduledExecutorService scheduledExecutorService) {
        this.f16352a = context.getApplicationContext();
        this.f16353b = scheduledExecutorService;
        this.f16354c = c4632i;
        c4638d.m18354a((C4640h) this);
    }

    /* renamed from: a */
    public void m18364a(final T t, final boolean z) {
        m18365a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C4641e f17126c;

            public void run() {
                try {
                    this.f17126c.f16354c.mo6143a(t);
                    if (z) {
                        this.f17126c.f16354c.mo6146d();
                    }
                } catch (Throwable e) {
                    C4877i.m19158a(this.f17126c.f16352a, "Failed to record event.", e);
                }
            }
        });
    }

    /* renamed from: a */
    public void mo6150a(String str) {
        m18365a(new C48572(this));
    }

    /* renamed from: a */
    protected void m18365a(Runnable runnable) {
        try {
            this.f16353b.submit(runnable);
        } catch (Throwable e) {
            C4877i.m19158a(this.f16352a, "Failed to submit events task", e);
        }
    }
}
