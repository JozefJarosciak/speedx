package okhttp3.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;

/* compiled from: JdkWithJettyBootPlatform */
/* renamed from: okhttp3.internal.g */
class C5550g extends C5481j {
    /* renamed from: a */
    private final Method f17879a;
    /* renamed from: b */
    private final Method f17880b;
    /* renamed from: c */
    private final Method f17881c;
    /* renamed from: d */
    private final Class<?> f17882d;
    /* renamed from: e */
    private final Class<?> f17883e;

    /* compiled from: JdkWithJettyBootPlatform */
    /* renamed from: okhttp3.internal.g$a */
    private static class C5549a implements InvocationHandler {
        /* renamed from: a */
        private final List<String> f17876a;
        /* renamed from: b */
        private boolean f17877b;
        /* renamed from: c */
        private String f17878c;

        public C5549a(List<String> list) {
            this.f17876a = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class returnType = method.getReturnType();
            if (objArr == null) {
                objArr = C5586l.f18007b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.valueOf(true);
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f17877b = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.f17876a;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                    List list = (List) objArr[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.f17876a.contains(list.get(i))) {
                            name = (String) list.get(i);
                            this.f17878c = name;
                            return name;
                        }
                    }
                    name = (String) this.f17876a.get(0);
                    this.f17878c = name;
                    return name;
                } else if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                } else {
                    this.f17878c = (String) objArr[0];
                    return null;
                }
            }
        }
    }

    public C5550g(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f17879a = method;
        this.f17880b = method2;
        this.f17881c = method3;
        this.f17882d = cls;
        this.f17883e = cls2;
    }

    /* renamed from: a */
    public void mo6702a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        List a = C5481j.m19767a((List) list);
        Object newProxyInstance;
        try {
            newProxyInstance = Proxy.newProxyInstance(C5481j.class.getClassLoader(), new Class[]{this.f17882d, this.f17883e}, new C5549a(a));
            this.f17879a.invoke(null, new Object[]{sSLSocket, newProxyInstance});
        } catch (InvocationTargetException e) {
            newProxyInstance = e;
            throw new AssertionError(newProxyInstance);
        } catch (IllegalAccessException e2) {
            newProxyInstance = e2;
            throw new AssertionError(newProxyInstance);
        }
    }

    /* renamed from: b */
    public void mo6746b(SSLSocket sSLSocket) {
        try {
            this.f17881c.invoke(null, new Object[]{sSLSocket});
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        } catch (InvocationTargetException e2) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public String mo6699a(SSLSocket sSLSocket) {
        try {
            C5549a c5549a = (C5549a) Proxy.getInvocationHandler(this.f17880b.invoke(null, new Object[]{sSLSocket}));
            if (c5549a.f17877b || c5549a.f17878c != null) {
                return c5549a.f17877b ? null : c5549a.f17878c;
            }
            C5481j.m19770c().mo6700a(4, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?", null);
            return null;
        } catch (InvocationTargetException e) {
            throw new AssertionError();
        } catch (IllegalAccessException e2) {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    public static C5481j m20157b() {
        try {
            String str = "org.eclipse.jetty.alpn.ALPN";
            Class cls = Class.forName(str);
            Class cls2 = Class.forName(str + "$Provider");
            Class cls3 = Class.forName(str + "$ClientProvider");
            Class cls4 = Class.forName(str + "$ServerProvider");
            return new C5550g(cls.getMethod("put", new Class[]{SSLSocket.class, cls2}), cls.getMethod("get", new Class[]{SSLSocket.class}), cls.getMethod("remove", new Class[]{SSLSocket.class}), cls3, cls4);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        }
        return null;
    }
}
