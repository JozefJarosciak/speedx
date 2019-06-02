package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import io.fabric.sdk.android.services.common.C4883l;
import io.fabric.sdk.android.services.concurrency.C1522b;
import io.fabric.sdk.android.services.concurrency.C4907g;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Fabric */
/* renamed from: io.fabric.sdk.android.c */
public class C1520c {
    /* renamed from: a */
    static volatile C1520c f7163a;
    /* renamed from: b */
    static final C4835k f7164b = new C4836b();
    /* renamed from: c */
    final C4835k f7165c;
    /* renamed from: d */
    final boolean f7166d;
    /* renamed from: e */
    private final Context f7167e;
    /* renamed from: f */
    private final Map<Class<? extends C1468h>, C1468h> f7168f;
    /* renamed from: g */
    private final ExecutorService f7169g;
    /* renamed from: h */
    private final Handler f7170h;
    /* renamed from: i */
    private final C4837f<C1520c> f7171i;
    /* renamed from: j */
    private final C4837f<?> f7172j;
    /* renamed from: k */
    private final C4883l f7173k;
    /* renamed from: l */
    private C4834a f7174l;
    /* renamed from: m */
    private WeakReference<Activity> f7175m;
    /* renamed from: n */
    private AtomicBoolean f7176n = new AtomicBoolean(false);

    /* renamed from: a */
    static C1520c m8346a() {
        if (f7163a != null) {
            return f7163a;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    C1520c(Context context, Map<Class<? extends C1468h>, C1468h> map, C4907g c4907g, Handler handler, C4835k c4835k, boolean z, C4837f c4837f, C4883l c4883l) {
        this.f7167e = context;
        this.f7168f = map;
        this.f7169g = c4907g;
        this.f7170h = handler;
        this.f7165c = c4835k;
        this.f7166d = z;
        this.f7171i = c4837f;
        this.f7172j = m8360a(map.size());
        this.f7173k = c4883l;
    }

    /* renamed from: a */
    public static C1520c m8347a(Context context, C1468h... c1468hArr) {
        if (f7163a == null) {
            synchronized (C1520c.class) {
                if (f7163a == null) {
                    C1520c.m8355c(new c$a(context).a(c1468hArr).a());
                }
            }
        }
        return f7163a;
    }

    /* renamed from: c */
    private static void m8355c(C1520c c1520c) {
        f7163a = c1520c;
        c1520c.m8358j();
    }

    /* renamed from: a */
    public C1520c m8359a(Activity activity) {
        this.f7175m = new WeakReference(activity);
        return this;
    }

    /* renamed from: j */
    private void m8358j() {
        m8359a(m8354c(this.f7167e));
        this.f7174l = new C4834a(this.f7167e);
        this.f7174l.a(new c$1(this));
        m8361a(this.f7167e);
    }

    /* renamed from: b */
    public String m8363b() {
        return "1.3.10.97";
    }

    /* renamed from: c */
    public String m8365c() {
        return "io.fabric.sdk.android:fabric";
    }

    /* renamed from: a */
    void m8361a(Context context) {
        StringBuilder append;
        Future b = m8364b(context);
        Collection g = m8369g();
        C4848l c4848l = new C4848l(b, g);
        List<C1468h> arrayList = new ArrayList(g);
        Collections.sort(arrayList);
        c4848l.a(context, this, C4837f.f17073d, this.f7173k);
        for (C1468h a : arrayList) {
            a.m8069a(context, this, this.f7172j, this.f7173k);
        }
        c4848l.o();
        if (C1520c.m8356h().a("Fabric", 3)) {
            append = new StringBuilder("Initializing ").append(m8365c()).append(" [Version: ").append(m8363b()).append("], with the following kits:\n");
        } else {
            append = null;
        }
        for (C1468h a2 : arrayList) {
            a2.f6862h.a(c4848l.h);
            m8362a(this.f7168f, a2);
            a2.m8077o();
            if (append != null) {
                append.append(a2.mo2812g()).append(" [Version: ").append(a2.mo2810c()).append("]\n");
            }
        }
        if (append != null) {
            C1520c.m8356h().a("Fabric", append.toString());
        }
    }

    /* renamed from: a */
    void m8362a(Map<Class<? extends C1468h>, C1468h> map, C1468h c1468h) {
        C1522b c1522b = (C1522b) c1468h.getClass().getAnnotation(C1522b.class);
        if (c1522b != null) {
            for (Class cls : c1522b.m8373a()) {
                if (cls.isInterface()) {
                    for (C1468h c1468h2 : map.values()) {
                        if (cls.isAssignableFrom(c1468h2.getClass())) {
                            c1468h.f6862h.a(c1468h2.f6862h);
                        }
                    }
                } else if (((C1468h) map.get(cls)) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                } else {
                    c1468h.f6862h.a(((C1468h) map.get(cls)).f6862h);
                }
            }
        }
    }

    /* renamed from: c */
    private Activity m8354c(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    /* renamed from: d */
    public C4834a m8366d() {
        return this.f7174l;
    }

    /* renamed from: e */
    public ExecutorService m8367e() {
        return this.f7169g;
    }

    /* renamed from: f */
    public Handler m8368f() {
        return this.f7170h;
    }

    /* renamed from: g */
    public Collection<C1468h> m8369g() {
        return this.f7168f.values();
    }

    /* renamed from: a */
    public static <T extends C1468h> T m8348a(Class<T> cls) {
        return (C1468h) C1520c.m8346a().f7168f.get(cls);
    }

    /* renamed from: h */
    public static C4835k m8356h() {
        if (f7163a == null) {
            return f7164b;
        }
        return f7163a.f7165c;
    }

    /* renamed from: i */
    public static boolean m8357i() {
        if (f7163a == null) {
            return false;
        }
        return f7163a.f7166d;
    }

    /* renamed from: b */
    private static Map<Class<? extends C1468h>, C1468h> m8353b(Collection<? extends C1468h> collection) {
        Map hashMap = new HashMap(collection.size());
        C1520c.m8351a(hashMap, (Collection) collection);
        return hashMap;
    }

    /* renamed from: a */
    private static void m8351a(Map<Class<? extends C1468h>, C1468h> map, Collection<? extends C1468h> collection) {
        for (C1468h c1468h : collection) {
            map.put(c1468h.getClass(), c1468h);
            if (c1468h instanceof C1498i) {
                C1520c.m8351a((Map) map, ((C1498i) c1468h).mo2981a());
            }
        }
    }

    /* renamed from: a */
    C4837f<?> m8360a(int i) {
        return new c$2(this, i);
    }

    /* renamed from: b */
    Future<Map<String, C4847j>> m8364b(Context context) {
        return m8367e().submit(new C4839e(context.getPackageCodePath()));
    }
}
