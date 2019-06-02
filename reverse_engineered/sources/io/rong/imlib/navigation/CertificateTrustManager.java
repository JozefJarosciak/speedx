package io.rong.imlib.navigation;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public final class CertificateTrustManager implements X509TrustManager {
    private final List<byte[]> keys;

    public CertificateTrustManager(List<String> list) throws CertificateException {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("You must specify non-empty keys list");
        }
        this.keys = new ArrayList();
        for (String decode : list) {
            this.keys.add(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(decode, 0))).getPublicKey().getEncoded());
        }
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null) {
            throw new IllegalArgumentException("PublicKeyManager: X509Certificate array is null");
        } else if (x509CertificateArr.length <= 0) {
            throw new IllegalArgumentException("PublicKeyManager: X509Certificate is empty");
        } else if (str == null || !str.equalsIgnoreCase("RSA")) {
            throw new CertificateException("PublicKeyManager: AuthType is not RSA");
        } else {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
                instance.init((KeyStore) null);
                for (TrustManager trustManager : instance.getTrustManagers()) {
                    ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
                }
                byte[] encoded = x509CertificateArr[0].getPublicKey().getEncoded();
                for (byte[] equals : this.keys) {
                    if (Arrays.equals(equals, encoded)) {
                        return;
                    }
                }
                throw new CertificateException("Public keys didn't pass checks");
            } catch (Throwable e) {
                throw new CertificateException(e);
            }
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
