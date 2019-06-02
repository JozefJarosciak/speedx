package rx.internal.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class RxThreadFactory extends AtomicLong implements ThreadFactory {
    public static final ThreadFactory NONE = new C57921();
    private static final long serialVersionUID = -8841098858898482335L;
    /* renamed from: a */
    final String f18461a;

    /* renamed from: rx.internal.util.RxThreadFactory$1 */
    static class C57921 implements ThreadFactory {
        C57921() {
        }

        public Thread newThread(Runnable runnable) {
            throw new AssertionError("No threads allowed.");
        }
    }

    public RxThreadFactory(String str) {
        this.f18461a = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f18461a + incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }
}
