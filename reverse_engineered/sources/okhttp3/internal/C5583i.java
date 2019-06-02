package okhttp3.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: OptionalMethod */
/* renamed from: okhttp3.internal.i */
class C5583i<T> {
    /* renamed from: a */
    private final Class<?> f18000a;
    /* renamed from: b */
    private final String f18001b;
    /* renamed from: c */
    private final Class[] f18002c;

    public C5583i(Class<?> cls, String str, Class... clsArr) {
        this.f18000a = cls;
        this.f18001b = str;
        this.f18002c = clsArr;
    }

    /* renamed from: a */
    public boolean m20308a(T t) {
        return m20305a(t.getClass()) != null;
    }

    /* renamed from: a */
    public Object m20307a(T t, Object... objArr) throws InvocationTargetException {
        Object obj = null;
        Method a = m20305a(t.getClass());
        if (a != null) {
            try {
                obj = a.invoke(t, objArr);
            } catch (IllegalAccessException e) {
            }
        }
        return obj;
    }

    /* renamed from: b */
    public Object m20309b(T t, Object... objArr) {
        try {
            return m20307a(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* renamed from: c */
    public Object m20310c(T t, Object... objArr) throws InvocationTargetException {
        Object a = m20305a(t.getClass());
        if (a == null) {
            throw new AssertionError("Method " + this.f18001b + " not supported for object " + t);
        }
        try {
            return a.invoke(t, objArr);
        } catch (Throwable e) {
            AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + a);
            assertionError.initCause(e);
            throw assertionError;
        }
    }

    /* renamed from: d */
    public Object m20311d(T t, Object... objArr) {
        try {
            return m20310c(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* renamed from: a */
    private Method m20305a(Class<?> cls) {
        if (this.f18001b == null) {
            return null;
        }
        Method a = C5583i.m20306a(cls, this.f18001b, this.f18002c);
        if (a == null || this.f18000a == null || this.f18000a.isAssignableFrom(a.getReturnType())) {
            return a;
        }
        return null;
    }

    /* renamed from: a */
    private static Method m20306a(Class<?> cls, String str, Class[] clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) == 0) {
                    return null;
                }
                return method;
            } catch (NoSuchMethodException e) {
                return method;
            }
        } catch (NoSuchMethodException e2) {
            return null;
        }
    }
}
