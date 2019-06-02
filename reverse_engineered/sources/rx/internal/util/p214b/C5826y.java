package rx.internal.util.p214b;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* compiled from: UnsafeAccess */
/* renamed from: rx.internal.util.b.y */
public final class C5826y {
    /* renamed from: a */
    public static final Unsafe f18506a;
    /* renamed from: b */
    private static final boolean f18507b;

    static {
        Unsafe unsafe;
        boolean z = true;
        if (System.getProperty("rx.unsafe-disable") == null) {
            z = false;
        }
        f18507b = z;
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (Throwable th) {
            unsafe = null;
        }
        f18506a = unsafe;
    }

    /* renamed from: a */
    public static boolean m21015a() {
        return (f18506a == null || f18507b) ? false : true;
    }

    /* renamed from: a */
    public static long m21014a(Class<?> cls, String str) {
        try {
            return f18506a.objectFieldOffset(cls.getDeclaredField(str));
        } catch (Throwable e) {
            InternalError internalError = new InternalError();
            internalError.initCause(e);
            throw internalError;
        }
    }
}
