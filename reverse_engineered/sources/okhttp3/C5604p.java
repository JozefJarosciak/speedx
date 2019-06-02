package okhttp3;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.C5586l;

/* compiled from: Handshake */
/* renamed from: okhttp3.p */
public final class C5604p {
    /* renamed from: a */
    private final TlsVersion f18053a;
    /* renamed from: b */
    private final CipherSuite f18054b;
    /* renamed from: c */
    private final List<Certificate> f18055c;
    /* renamed from: d */
    private final List<Certificate> f18056d;

    private C5604p(TlsVersion tlsVersion, CipherSuite cipherSuite, List<Certificate> list, List<Certificate> list2) {
        this.f18053a = tlsVersion;
        this.f18054b = cipherSuite;
        this.f18055c = list;
        this.f18056d = list2;
    }

    /* renamed from: a */
    public static C5604p m20398a(SSLSession sSLSession) {
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        CipherSuite forJavaName = CipherSuite.forJavaName(cipherSuite);
        cipherSuite = sSLSession.getProtocol();
        if (cipherSuite == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        Object[] peerCertificates;
        List a;
        List a2;
        TlsVersion forJavaName2 = TlsVersion.forJavaName(cipherSuite);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            peerCertificates = null;
        }
        if (peerCertificates != null) {
            a = C5586l.m20322a(peerCertificates);
        } else {
            a = Collections.emptyList();
        }
        Object[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            a2 = C5586l.m20322a(localCertificates);
        } else {
            a2 = Collections.emptyList();
        }
        return new C5604p(forJavaName2, forJavaName, a, a2);
    }

    /* renamed from: a */
    public CipherSuite m20399a() {
        return this.f18054b;
    }

    /* renamed from: b */
    public List<Certificate> m20400b() {
        return this.f18055c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5604p)) {
            return false;
        }
        C5604p c5604p = (C5604p) obj;
        if (C5586l.m20331a(this.f18054b, c5604p.f18054b) && this.f18054b.equals(c5604p.f18054b) && this.f18055c.equals(c5604p.f18055c) && this.f18056d.equals(c5604p.f18056d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.f18053a != null ? this.f18053a.hashCode() : 0) + 527) * 31) + this.f18054b.hashCode()) * 31) + this.f18055c.hashCode()) * 31) + this.f18056d.hashCode();
    }
}
