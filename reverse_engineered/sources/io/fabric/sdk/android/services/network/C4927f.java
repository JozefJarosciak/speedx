package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.C1520c;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* compiled from: PinningTrustManager */
/* renamed from: io.fabric.sdk.android.services.network.f */
class C4927f implements X509TrustManager {
    /* renamed from: a */
    private final TrustManager[] f17269a;
    /* renamed from: b */
    private final C4928g f17270b;
    /* renamed from: c */
    private final long f17271c;
    /* renamed from: d */
    private final List<byte[]> f17272d = new LinkedList();
    /* renamed from: e */
    private final Set<X509Certificate> f17273e = Collections.synchronizedSet(new HashSet());

    public C4927f(C4928g c4928g, C4656e c4656e) {
        this.f17269a = m19361a(c4928g);
        this.f17270b = c4928g;
        this.f17271c = c4656e.mo6154d();
        for (String a : c4656e.mo6153c()) {
            this.f17272d.add(m19360a(a));
        }
    }

    /* renamed from: a */
    private TrustManager[] m19361a(C4928g c4928g) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(c4928g.f17274a);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        }
    }

    /* renamed from: a */
    private boolean m19359a(X509Certificate x509Certificate) throws CertificateException {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] equals : this.f17272d) {
                if (Arrays.equals(equals, digest)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            throw new CertificateException(e);
        }
    }

    /* renamed from: a */
    private void m19358a(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        for (TrustManager trustManager : this.f17269a) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    /* renamed from: a */
    private void m19357a(X509Certificate[] x509CertificateArr) throws CertificateException {
        if (this.f17271c == -1 || System.currentTimeMillis() - this.f17271c <= 15552000000L) {
            X509Certificate[] a = C4922a.m19350a(x509CertificateArr, this.f17270b);
            int length = a.length;
            int i = 0;
            while (i < length) {
                if (!m19359a(a[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        C1520c.h().mo6219c("Fabric", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.f17271c) + " millis vs " + 15552000000L + " millis) " + "falling back to system trust.");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (!this.f17273e.contains(x509CertificateArr[0])) {
            m19358a(x509CertificateArr, str);
            m19357a(x509CertificateArr);
            this.f17273e.add(x509CertificateArr[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    /* renamed from: a */
    private byte[] m19360a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
