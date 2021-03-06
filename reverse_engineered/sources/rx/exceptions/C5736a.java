package rx.exceptions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.C5719b;

/* compiled from: Exceptions */
/* renamed from: rx.exceptions.a */
public final class C5736a {
    /* renamed from: a */
    public static void m20858a(Throwable th) {
        if (th instanceof OnErrorNotImplementedException) {
            throw ((OnErrorNotImplementedException) th);
        } else if (th instanceof OnErrorFailedException) {
            throw ((OnErrorFailedException) th);
        } else if (th instanceof OnCompletedFailedException) {
            throw ((OnCompletedFailedException) th);
        } else if (th instanceof StackOverflowError) {
            throw ((StackOverflowError) th);
        } else if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        } else if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        } else if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }

    /* renamed from: a */
    public static void m20859a(Throwable th, Throwable th2) {
        Set hashSet = new HashSet();
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i < 25) {
                th = th.getCause();
                if (!hashSet.contains(th.getCause())) {
                    hashSet.add(th.getCause());
                    i = i2;
                }
            } else {
                return;
            }
        }
        try {
            th.initCause(th2);
        } catch (Throwable th3) {
        }
    }

    /* renamed from: b */
    public static Throwable m20863b(Throwable th) {
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i >= 25) {
                return new RuntimeException("Stack too deep to get final cause");
            }
            th = th.getCause();
            i = i2;
        }
        return th;
    }

    /* renamed from: a */
    public static void m20862a(List<? extends Throwable> list) {
        if (list != null && !list.isEmpty()) {
            if (list.size() == 1) {
                Throwable th = (Throwable) list.get(0);
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                } else if (th instanceof Error) {
                    throw ((Error) th);
                } else {
                    throw new RuntimeException(th);
                }
            }
            throw new CompositeException(list);
        }
    }

    /* renamed from: a */
    public static void m20861a(Throwable th, C5719b<?> c5719b, Object obj) {
        C5736a.m20858a(th);
        c5719b.mo7151a(OnErrorThrowable.addValueAsLastCause(th, obj));
    }

    /* renamed from: a */
    public static void m20860a(Throwable th, C5719b<?> c5719b) {
        C5736a.m20858a(th);
        c5719b.mo7151a(th);
    }
}
