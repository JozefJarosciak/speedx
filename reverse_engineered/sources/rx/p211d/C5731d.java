package rx.p211d;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: RxJavaPlugins */
/* renamed from: rx.d.d */
public class C5731d {
    /* renamed from: a */
    static final C5727a f18286a = new C57301();
    /* renamed from: b */
    private static final C5731d f18287b = new C5731d();
    /* renamed from: c */
    private final AtomicReference<C5727a> f18288c = new AtomicReference();
    /* renamed from: d */
    private final AtomicReference<C5728b> f18289d = new AtomicReference();
    /* renamed from: e */
    private final AtomicReference<Object> f18290e = new AtomicReference();
    /* renamed from: f */
    private final AtomicReference<Object> f18291f = new AtomicReference();
    /* renamed from: g */
    private final AtomicReference<C5732e> f18292g = new AtomicReference();

    /* compiled from: RxJavaPlugins */
    /* renamed from: rx.d.d$1 */
    static class C57301 extends C5727a {
        C57301() {
        }
    }

    /* renamed from: a */
    public static C5731d m20840a() {
        return f18287b;
    }

    C5731d() {
    }

    /* renamed from: b */
    public C5727a m20841b() {
        if (this.f18288c.get() == null) {
            Object a = C5731d.m20839a(C5727a.class, System.getProperties());
            if (a == null) {
                this.f18288c.compareAndSet(null, f18286a);
            } else {
                this.f18288c.compareAndSet(null, (C5727a) a);
            }
        }
        return (C5727a) this.f18288c.get();
    }

    /* renamed from: c */
    public C5728b m20842c() {
        if (this.f18289d.get() == null) {
            Object a = C5731d.m20839a(C5728b.class, System.getProperties());
            if (a == null) {
                this.f18289d.compareAndSet(null, C5729c.m20838a());
            } else {
                this.f18289d.compareAndSet(null, (C5728b) a);
            }
        }
        return (C5728b) this.f18289d.get();
    }

    /* renamed from: a */
    static Object m20839a(Class<?> cls, Properties properties) {
        Properties properties2 = (Properties) properties.clone();
        String simpleName = cls.getSimpleName();
        String str = "rxjava.plugin.";
        String property = properties2.getProperty("rxjava.plugin." + simpleName + ".implementation");
        if (property == null) {
            str = ".class";
            str = ".impl";
            for (Entry entry : properties2.entrySet()) {
                String obj = entry.getKey().toString();
                if (obj.startsWith("rxjava.plugin.") && obj.endsWith(".class") && simpleName.equals(entry.getValue().toString())) {
                    str = "rxjava.plugin." + obj.substring(0, obj.length() - ".class".length()).substring("rxjava.plugin.".length()) + ".impl";
                    String property2 = properties2.getProperty(str);
                    if (property2 == null) {
                        throw new RuntimeException("Implementing class declaration for " + simpleName + " missing: " + str);
                    }
                    str = property2;
                    if (str != null) {
                        return null;
                    }
                    try {
                        return Class.forName(str).asSubclass(cls).newInstance();
                    } catch (ClassCastException e) {
                        throw new RuntimeException(simpleName + " implementation is not an instance of " + simpleName + ": " + str);
                    } catch (Throwable e2) {
                        throw new RuntimeException(simpleName + " implementation class not found: " + str, e2);
                    } catch (Throwable e22) {
                        throw new RuntimeException(simpleName + " implementation not able to be instantiated: " + str, e22);
                    } catch (Throwable e222) {
                        throw new RuntimeException(simpleName + " implementation not able to be accessed: " + str, e222);
                    }
                }
            }
        }
        str = property;
        if (str != null) {
            return null;
        }
        return Class.forName(str).asSubclass(cls).newInstance();
    }

    /* renamed from: d */
    public C5732e m20843d() {
        if (this.f18292g.get() == null) {
            Object a = C5731d.m20839a(C5732e.class, System.getProperties());
            if (a == null) {
                this.f18292g.compareAndSet(null, C5732e.m20844a());
            } else {
                this.f18292g.compareAndSet(null, (C5732e) a);
            }
        }
        return (C5732e) this.f18292g.get();
    }
}
