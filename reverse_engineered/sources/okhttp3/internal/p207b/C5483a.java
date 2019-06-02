package okhttp3.internal.p207b;

import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* compiled from: CertificateChainCleaner */
/* renamed from: okhttp3.internal.b.a */
public abstract class C5483a {

    /* compiled from: CertificateChainCleaner */
    /* renamed from: okhttp3.internal.b.a$a */
    static final class C5484a extends C5483a {
        /* renamed from: a */
        private final Object f17657a;
        /* renamed from: b */
        private final Method f17658b;

        C5484a(Object obj, Method method) {
            this.f17657a = obj;
            this.f17658b = method;
        }

        /* renamed from: a */
        public List<Certificate> mo6704a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
                return (List) this.f17658b.invoke(this.f17657a, new Object[]{x509CertificateArr, "RSA", str});
            } catch (Throwable e) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
                sSLPeerUnverifiedException.initCause(e);
                throw sSLPeerUnverifiedException;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* compiled from: CertificateChainCleaner */
    /* renamed from: okhttp3.internal.b.a$b */
    static final class C5485b extends C5483a {
        /* renamed from: a */
        private final C5488d f17659a;

        public C5485b(C5488d c5488d) {
            this.f17659a = c5488d;
        }

        /* renamed from: a */
        public List<Certificate> mo6704a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            Deque arrayDeque = new ArrayDeque(list);
            List<Certificate> arrayList = new ArrayList();
            arrayList.add(arrayDeque.removeFirst());
            int i = 0;
            Object obj = null;
            while (i < 9) {
                Object obj2;
                X509Certificate x509Certificate = (X509Certificate) arrayList.get(arrayList.size() - 1);
                X509Certificate a = this.f17659a.mo6705a(x509Certificate);
                if (a != null) {
                    if (arrayList.size() > 1 || !x509Certificate.equals(a)) {
                        arrayList.add(a);
                    }
                    if (m19787a(a, a)) {
                        return arrayList;
                    }
                    obj2 = 1;
                } else {
                    Iterator it = arrayDeque.iterator();
                    while (it.hasNext()) {
                        a = (X509Certificate) it.next();
                        if (m19787a(x509Certificate, a)) {
                            it.remove();
                            arrayList.add(a);
                            obj2 = obj;
                        }
                    }
                    if (obj != null) {
                        return arrayList;
                    }
                    throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate);
                }
                i++;
                obj = obj2;
            }
            throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
        }

        /* renamed from: a */
        private boolean m19787a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
            if (!x509Certificate.getIssuerDN().equals(x509Certificate2.getSubjectDN())) {
                return false;
            }
            try {
                x509Certificate.verify(x509Certificate2.getPublicKey());
                return true;
            } catch (GeneralSecurityException e) {
                return false;
            }
        }
    }

    /* renamed from: a */
    public abstract List<Certificate> mo6704a(List<Certificate> list, String str) throws SSLPeerUnverifiedException;

    /* renamed from: a */
    public static C5483a m19784a(X509TrustManager x509TrustManager) {
        try {
            Class cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C5484a(cls.getConstructor(new Class[]{X509TrustManager.class}).newInstance(new Object[]{x509TrustManager}), cls.getMethod("checkServerTrusted", new Class[]{X509Certificate[].class, String.class, String.class}));
        } catch (Exception e) {
            return new C5485b(C5488d.m19803a(x509TrustManager));
        }
    }
}
