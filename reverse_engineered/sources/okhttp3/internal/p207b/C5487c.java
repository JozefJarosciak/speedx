package okhttp3.internal.p207b;

import ch.qos.logback.core.CoreConstants;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.C5586l;
import org.slf4j.Marker;

/* compiled from: OkHostnameVerifier */
/* renamed from: okhttp3.internal.b.c */
public final class C5487c implements HostnameVerifier {
    /* renamed from: a */
    public static final C5487c f17667a = new C5487c();

    private C5487c() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return m19802a(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException e) {
            return false;
        }
    }

    /* renamed from: a */
    public boolean m19802a(String str, X509Certificate x509Certificate) {
        if (C5586l.m20341c(str)) {
            return m19800b(str, x509Certificate);
        }
        return m19801c(str, x509Certificate);
    }

    /* renamed from: b */
    private boolean m19800b(String str, X509Certificate x509Certificate) {
        List a = C5487c.m19798a(x509Certificate, 7);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase((String) a.get(i))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    private boolean m19801c(String str, X509Certificate x509Certificate) {
        String toLowerCase = str.toLowerCase(Locale.US);
        List a = C5487c.m19798a(x509Certificate, 2);
        int size = a.size();
        int i = 0;
        Object obj = null;
        while (i < size) {
            if (m19799a(toLowerCase, (String) a.get(i))) {
                return true;
            }
            i++;
            int i2 = 1;
        }
        if (obj == null) {
            String a2 = new C5486b(x509Certificate.getSubjectX500Principal()).m19796a("cn");
            if (a2 != null) {
                return m19799a(toLowerCase, a2);
            }
        }
        return false;
    }

    /* renamed from: a */
    public static List<String> m19797a(X509Certificate x509Certificate) {
        Collection a = C5487c.m19798a(x509Certificate, 7);
        Collection a2 = C5487c.m19798a(x509Certificate, 2);
        List<String> arrayList = new ArrayList(a.size() + a2.size());
        arrayList.addAll(a);
        arrayList.addAll(a2);
        return arrayList;
    }

    /* renamed from: a */
    private static List<String> m19798a(X509Certificate x509Certificate, int i) {
        List<String> arrayList = new ArrayList();
        try {
            Collection<List> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2) {
                    Integer num = (Integer) list.get(0);
                    if (num != null && num.intValue() == i) {
                        String str = (String) list.get(1);
                        if (str != null) {
                            arrayList.add(str);
                        }
                    }
                }
            }
            return arrayList;
        } catch (CertificateParsingException e) {
            return Collections.emptyList();
        }
    }

    /* renamed from: a */
    private boolean m19799a(String str, String str2) {
        if (str == null || str.length() == 0 || str.startsWith(".") || str.endsWith("..") || str2 == null || str2.length() == 0 || str2.startsWith(".") || str2.endsWith("..")) {
            return false;
        }
        if (!str.endsWith(".")) {
            str = str + CoreConstants.DOT;
        }
        if (!str2.endsWith(".")) {
            str2 = str2 + CoreConstants.DOT;
        }
        String toLowerCase = str2.toLowerCase(Locale.US);
        if (!toLowerCase.contains(Marker.ANY_MARKER)) {
            return str.equals(toLowerCase);
        }
        if (!toLowerCase.startsWith("*.") || toLowerCase.indexOf(42, 1) != -1 || str.length() < toLowerCase.length() || "*.".equals(toLowerCase)) {
            return false;
        }
        toLowerCase = toLowerCase.substring(1);
        if (!str.endsWith(toLowerCase)) {
            return false;
        }
        int length = str.length() - toLowerCase.length();
        if (length <= 0 || str.lastIndexOf(46, length - 1) == -1) {
            return true;
        }
        return false;
    }
}
