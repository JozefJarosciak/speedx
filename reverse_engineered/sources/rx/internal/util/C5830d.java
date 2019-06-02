package rx.internal.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* compiled from: PlatformDependent */
/* renamed from: rx.internal.util.d */
public final class C5830d {
    /* renamed from: a */
    private static final int f18518a = C5830d.m21031d();
    /* renamed from: b */
    private static final boolean f18519b = (f18518a != 0);

    /* compiled from: PlatformDependent */
    /* renamed from: rx.internal.util.d$1 */
    static class C58291 implements PrivilegedAction<ClassLoader> {
        C58291() {
        }

        public /* synthetic */ Object run() {
            return m21027a();
        }

        /* renamed from: a */
        public ClassLoader m21027a() {
            return ClassLoader.getSystemClassLoader();
        }
    }

    /* renamed from: a */
    public static boolean m21028a() {
        return f18519b;
    }

    /* renamed from: b */
    public static int m21029b() {
        return f18518a;
    }

    /* renamed from: d */
    private static int m21031d() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION", true, C5830d.m21030c()).getField("SDK_INT").get(null)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /* renamed from: c */
    static ClassLoader m21030c() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new C58291());
    }
}
