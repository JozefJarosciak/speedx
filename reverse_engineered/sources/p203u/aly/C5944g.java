package p203u.aly;

import com.umeng.analytics.C4731a;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: CrashHandler */
/* renamed from: u.aly.g */
public class C5944g implements UncaughtExceptionHandler {
    /* renamed from: a */
    private UncaughtExceptionHandler f19056a;
    /* renamed from: b */
    private C4751s f19057b;

    public C5944g() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.f19056a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    /* renamed from: a */
    public void m21939a(C4751s c4751s) {
        this.f19057b = c4751s;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        m21938a(th);
        if (this.f19056a != null && this.f19056a != Thread.getDefaultUncaughtExceptionHandler()) {
            this.f19056a.uncaughtException(thread, th);
        }
    }

    /* renamed from: a */
    private void m21938a(Throwable th) {
        if (C4731a.f16612h) {
            this.f19057b.mo6181a(th);
        } else {
            this.f19057b.mo6181a(null);
        }
    }
}
