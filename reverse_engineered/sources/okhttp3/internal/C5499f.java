package okhttp3.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;

/* compiled from: Jdk9Platform */
/* renamed from: okhttp3.internal.f */
final class C5499f extends C5481j {
    /* renamed from: a */
    final Method f17703a;
    /* renamed from: b */
    final Method f17704b;

    public C5499f(Method method, Method method2) {
        this.f17703a = method;
        this.f17704b = method2;
    }

    /* renamed from: a */
    public void mo6702a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List a = C5481j.m19767a((List) list);
            this.f17703a.invoke(sSLParameters, new Object[]{a.toArray(new String[a.size()])});
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        } catch (InvocationTargetException e2) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public String mo6699a(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f17704b.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        } catch (InvocationTargetException e2) {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    public static C5499f m19850b() {
        try {
            return new C5499f(SSLParameters.class.getMethod("setApplicationProtocols", new Class[]{String[].class}), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
