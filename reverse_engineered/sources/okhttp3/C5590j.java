package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.C5586l;

/* compiled from: ConnectionSpec */
/* renamed from: okhttp3.j */
public final class C5590j {
    /* renamed from: a */
    public static final C5590j f18015a = new C5589a(true).m20350a(f18018d).m20351a(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).m20348a(true).m20352a();
    /* renamed from: b */
    public static final C5590j f18016b = new C5589a(f18015a).m20351a(TlsVersion.TLS_1_0).m20348a(true).m20352a();
    /* renamed from: c */
    public static final C5590j f18017c = new C5589a(false).m20352a();
    /* renamed from: d */
    private static final CipherSuite[] f18018d = new CipherSuite[]{CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
    /* renamed from: e */
    private final boolean f18019e;
    /* renamed from: f */
    private final boolean f18020f;
    /* renamed from: g */
    private final String[] f18021g;
    /* renamed from: h */
    private final String[] f18022h;

    /* compiled from: ConnectionSpec */
    /* renamed from: okhttp3.j$a */
    public static final class C5589a {
        /* renamed from: a */
        private boolean f18011a;
        /* renamed from: b */
        private String[] f18012b;
        /* renamed from: c */
        private String[] f18013c;
        /* renamed from: d */
        private boolean f18014d;

        C5589a(boolean z) {
            this.f18011a = z;
        }

        public C5589a(C5590j c5590j) {
            this.f18011a = c5590j.f18019e;
            this.f18012b = c5590j.f18021g;
            this.f18013c = c5590j.f18022h;
            this.f18014d = c5590j.f18020f;
        }

        /* renamed from: a */
        public C5589a m20350a(CipherSuite... cipherSuiteArr) {
            if (this.f18011a) {
                String[] strArr = new String[cipherSuiteArr.length];
                for (int i = 0; i < cipherSuiteArr.length; i++) {
                    strArr[i] = cipherSuiteArr[i].f17560a;
                }
                return m20349a(strArr);
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        /* renamed from: a */
        public C5589a m20349a(String... strArr) {
            if (!this.f18011a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            } else {
                this.f18012b = (String[]) strArr.clone();
                return this;
            }
        }

        /* renamed from: a */
        public C5589a m20351a(TlsVersion... tlsVersionArr) {
            if (this.f18011a) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].f17580a;
                }
                return m20353b(strArr);
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        /* renamed from: b */
        public C5589a m20353b(String... strArr) {
            if (!this.f18011a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            } else {
                this.f18013c = (String[]) strArr.clone();
                return this;
            }
        }

        /* renamed from: a */
        public C5589a m20348a(boolean z) {
            if (this.f18011a) {
                this.f18014d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        /* renamed from: a */
        public C5590j m20352a() {
            return new C5590j();
        }
    }

    private C5590j(C5589a c5589a) {
        this.f18019e = c5589a.f18011a;
        this.f18021g = c5589a.f18012b;
        this.f18022h = c5589a.f18013c;
        this.f18020f = c5589a.f18014d;
    }

    /* renamed from: a */
    public boolean m20361a() {
        return this.f18019e;
    }

    /* renamed from: b */
    public List<CipherSuite> m20363b() {
        if (this.f18021g == null) {
            return null;
        }
        Object[] objArr = new CipherSuite[this.f18021g.length];
        for (int i = 0; i < this.f18021g.length; i++) {
            objArr[i] = CipherSuite.forJavaName(this.f18021g[i]);
        }
        return C5586l.m20322a(objArr);
    }

    /* renamed from: c */
    public List<TlsVersion> m20364c() {
        if (this.f18022h == null) {
            return null;
        }
        Object[] objArr = new TlsVersion[this.f18022h.length];
        for (int i = 0; i < this.f18022h.length; i++) {
            objArr[i] = TlsVersion.forJavaName(this.f18022h[i]);
        }
        return C5586l.m20322a(objArr);
    }

    /* renamed from: d */
    public boolean m20365d() {
        return this.f18020f;
    }

    /* renamed from: a */
    void m20360a(SSLSocket sSLSocket, boolean z) {
        C5590j b = m20356b(sSLSocket, z);
        if (b.f18022h != null) {
            sSLSocket.setEnabledProtocols(b.f18022h);
        }
        if (b.f18021g != null) {
            sSLSocket.setEnabledCipherSuites(b.f18021g);
        }
    }

    /* renamed from: b */
    private C5590j m20356b(SSLSocket sSLSocket, boolean z) {
        String[] strArr;
        String[] strArr2;
        if (this.f18021g != null) {
            strArr = (String[]) C5586l.m20334a(String.class, this.f18021g, sSLSocket.getEnabledCipherSuites());
        } else {
            strArr = sSLSocket.getEnabledCipherSuites();
        }
        if (this.f18022h != null) {
            strArr2 = (String[]) C5586l.m20334a(String.class, this.f18022h, sSLSocket.getEnabledProtocols());
        } else {
            strArr2 = sSLSocket.getEnabledProtocols();
        }
        if (z && C5586l.m20333a(sSLSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV")) {
            strArr = C5586l.m20339b(strArr, "TLS_FALLBACK_SCSV");
        }
        return new C5589a(this).m20349a(strArr).m20353b(strArr2).m20352a();
    }

    /* renamed from: a */
    public boolean m20362a(SSLSocket sSLSocket) {
        if (!this.f18019e) {
            return false;
        }
        if (this.f18022h != null && !C5590j.m20355a(this.f18022h, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        if (this.f18021g == null || C5590j.m20355a(this.f18021g, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m20355a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr2.length == 0) {
            return false;
        }
        for (String a : strArr) {
            if (C5586l.m20333a(strArr2, a)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5590j)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        C5590j c5590j = (C5590j) obj;
        if (this.f18019e != c5590j.f18019e) {
            return false;
        }
        if (!this.f18019e || (Arrays.equals(this.f18021g, c5590j.f18021g) && Arrays.equals(this.f18022h, c5590j.f18022h) && this.f18020f == c5590j.f18020f)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (!this.f18019e) {
            return 17;
        }
        return (this.f18020f ? 0 : 1) + ((((Arrays.hashCode(this.f18021g) + 527) * 31) + Arrays.hashCode(this.f18022h)) * 31);
    }

    public String toString() {
        if (!this.f18019e) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f18021g != null ? m20363b().toString() : "[all enabled]") + ", tlsVersions=" + (this.f18022h != null ? m20364c().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f18020f + ")";
    }
}
