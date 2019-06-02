package okhttp3.internal.p207b;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* compiled from: TrustRootIndex */
/* renamed from: okhttp3.internal.b.d */
public abstract class C5488d {

    /* compiled from: TrustRootIndex */
    /* renamed from: okhttp3.internal.b.d$a */
    static final class C5489a extends C5488d {
        /* renamed from: a */
        private final X509TrustManager f17668a;
        /* renamed from: b */
        private final Method f17669b;

        C5489a(X509TrustManager x509TrustManager, Method method) {
            this.f17669b = method;
            this.f17668a = x509TrustManager;
        }

        /* renamed from: a */
        public X509Certificate mo6705a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.f17669b.invoke(this.f17668a, new Object[]{x509Certificate});
                return trustAnchor != null ? trustAnchor.getTrustedCert() : null;
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                return null;
            }
        }
    }

    /* compiled from: TrustRootIndex */
    /* renamed from: okhttp3.internal.b.d$b */
    static final class C5490b extends C5488d {
        /* renamed from: a */
        private final Map<X500Principal, List<X509Certificate>> f17670a = new LinkedHashMap();

        public C5490b(X509Certificate... x509CertificateArr) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
                List list = (List) this.f17670a.get(subjectX500Principal);
                if (list == null) {
                    list = new ArrayList(1);
                    this.f17670a.put(subjectX500Principal, list);
                }
                list.add(x509Certificate);
            }
        }

        /* renamed from: a */
        public X509Certificate mo6705a(X509Certificate x509Certificate) {
            List<X509Certificate> list = (List) this.f17670a.get(x509Certificate.getIssuerX500Principal());
            if (list == null) {
                return null;
            }
            for (X509Certificate x509Certificate2 : list) {
                try {
                    x509Certificate.verify(x509Certificate2.getPublicKey());
                    return x509Certificate2;
                } catch (Exception e) {
                }
            }
            return null;
        }
    }

    /* renamed from: a */
    abstract X509Certificate mo6705a(X509Certificate x509Certificate);

    /* renamed from: a */
    public static C5488d m19803a(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[]{X509Certificate.class});
            declaredMethod.setAccessible(true);
            return new C5489a(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException e) {
            return C5488d.m19804a(x509TrustManager.getAcceptedIssuers());
        }
    }

    /* renamed from: a */
    public static C5488d m19804a(X509Certificate... x509CertificateArr) {
        return new C5490b(x509CertificateArr);
    }
}
