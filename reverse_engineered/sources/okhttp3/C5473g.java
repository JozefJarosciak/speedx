package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.C5586l;
import okhttp3.internal.p207b.C5483a;
import okio.ByteString;

/* compiled from: CertificatePinner */
/* renamed from: okhttp3.g */
public final class C5473g {
    /* renamed from: a */
    public static final C5473g f17623a = new C5471a().m19724a();
    /* renamed from: b */
    private final List<C5472b> f17624b;
    /* renamed from: c */
    private final C5483a f17625c;

    /* compiled from: CertificatePinner */
    /* renamed from: okhttp3.g$a */
    public static final class C5471a {
        /* renamed from: a */
        private final List<C5472b> f17619a = new ArrayList();

        /* renamed from: a */
        public C5471a m19723a(String str, String... strArr) {
            if (str == null) {
                throw new NullPointerException("pattern == null");
            }
            for (String c5472b : strArr) {
                this.f17619a.add(new C5472b(str, c5472b));
            }
            return this;
        }

        /* renamed from: a */
        public C5473g m19724a() {
            return new C5473g(C5586l.m20321a(this.f17619a), null);
        }
    }

    /* compiled from: CertificatePinner */
    /* renamed from: okhttp3.g$b */
    static final class C5472b {
        /* renamed from: a */
        final String f17620a;
        /* renamed from: b */
        final String f17621b;
        /* renamed from: c */
        final ByteString f17622c;

        C5472b(String str, String str2) {
            this.f17620a = str;
            if (str2.startsWith("sha1/")) {
                this.f17621b = "sha1/";
                this.f17622c = ByteString.decodeBase64(str2.substring("sha1/".length()));
            } else if (str2.startsWith("sha256/")) {
                this.f17621b = "sha256/";
                this.f17622c = ByteString.decodeBase64(str2.substring("sha256/".length()));
            } else {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
            }
            if (this.f17622c == null) {
                throw new IllegalArgumentException("pins must be base64: " + str2);
            }
        }

        /* renamed from: a */
        boolean m19725a(String str) {
            boolean z = false;
            if (this.f17620a.equals(str)) {
                return true;
            }
            int indexOf = str.indexOf(46);
            if (this.f17620a.startsWith("*.")) {
                if (str.regionMatches(false, indexOf + 1, this.f17620a, 2, this.f17620a.length() - 2)) {
                    z = true;
                }
            }
            return z;
        }

        public boolean equals(Object obj) {
            return (obj instanceof C5472b) && this.f17620a.equals(((C5472b) obj).f17620a) && this.f17621b.equals(((C5472b) obj).f17621b) && this.f17622c.equals(((C5472b) obj).f17622c);
        }

        public int hashCode() {
            return ((((this.f17620a.hashCode() + 527) * 31) + this.f17621b.hashCode()) * 31) + this.f17622c.hashCode();
        }

        public String toString() {
            return this.f17621b + this.f17622c.base64();
        }
    }

    private C5473g(List<C5472b> list, C5483a c5483a) {
        this.f17624b = list;
        this.f17625c = c5483a;
    }

    /* renamed from: a */
    public void m19731a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List a = m19729a(str);
        if (!a.isEmpty()) {
            int i;
            if (this.f17625c != null) {
                list = this.f17625c.mo6704a(list, str);
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i2);
                int size2 = a.size();
                int i3 = 0;
                Object obj = null;
                Object obj2 = null;
                while (i3 < size2) {
                    C5472b c5472b = (C5472b) a.get(i3);
                    if (c5472b.f17621b.equals("sha256/")) {
                        if (obj == null) {
                            obj = C5473g.m19728b(x509Certificate);
                        }
                        if (c5472b.f17622c.equals(obj)) {
                            return;
                        }
                    } else if (c5472b.f17621b.equals("sha1/")) {
                        if (obj2 == null) {
                            obj2 = C5473g.m19727a(x509Certificate);
                        }
                        if (c5472b.f17622c.equals(obj2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError();
                    }
                    Object obj3 = obj;
                    i3++;
                    obj2 = obj2;
                    obj = obj3;
                }
            }
            StringBuilder append = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (i = 0; i < size3; i++) {
                Certificate certificate = (X509Certificate) list.get(i);
                append.append("\n    ").append(C5473g.m19726a(certificate)).append(": ").append(certificate.getSubjectDN().getName());
            }
            append.append("\n  Pinned certificates for ").append(str).append(":");
            size3 = a.size();
            for (i = 0; i < size3; i++) {
                append.append("\n    ").append((C5472b) a.get(i));
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }

    /* renamed from: a */
    List<C5472b> m19729a(String str) {
        List<C5472b> emptyList = Collections.emptyList();
        for (C5472b c5472b : this.f17624b) {
            if (c5472b.m19725a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(c5472b);
            }
        }
        return emptyList;
    }

    /* renamed from: a */
    C5473g m19730a(C5483a c5483a) {
        return this.f17625c != c5483a ? new C5473g(this.f17624b, c5483a) : this;
    }

    /* renamed from: a */
    public static String m19726a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + C5473g.m19728b((X509Certificate) certificate).base64();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    /* renamed from: a */
    static ByteString m19727a(X509Certificate x509Certificate) {
        return C5586l.m20325a(ByteString.of(x509Certificate.getPublicKey().getEncoded()));
    }

    /* renamed from: b */
    static ByteString m19728b(X509Certificate x509Certificate) {
        return C5586l.m20337b(ByteString.of(x509Certificate.getPublicKey().getEncoded()));
    }
}
