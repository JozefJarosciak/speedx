package io.rong.imkit.utils;

import android.content.Context;
import ch.qos.logback.core.net.ssl.SSL;
import io.rong.imageloader.core.assist.ContentLengthInputStream;
import io.rong.imageloader.core.download.BaseImageDownloader;
import io.rong.imageloader.utils.IoUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class RongAuthImageDownloader extends BaseImageDownloader {
    final HostnameVerifier DO_NOT_VERIFY = new C51401();
    private SSLSocketFactory mSSLSocketFactory = sslContextForTrustedCertificates().getSocketFactory();

    /* renamed from: io.rong.imkit.utils.RongAuthImageDownloader$1 */
    class C51401 implements HostnameVerifier {
        C51401() {
        }

        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    class miTM implements TrustManager, X509TrustManager {
        miTM() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] x509CertificateArr) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] x509CertificateArr) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }
    }

    public RongAuthImageDownloader(Context context) {
        super(context);
    }

    public RongAuthImageDownloader(Context context, int i, int i2) {
        super(context, i, i2);
    }

    protected InputStream getStreamFromNetwork(String str, Object obj) throws IOException {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.connectTimeout);
        httpURLConnection.setReadTimeout(this.readTimeout);
        if (httpURLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.mSSLSocketFactory);
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.DO_NOT_VERIFY);
        }
        try {
            Object inputStream = httpURLConnection.getInputStream();
            if (shouldBeProcessed(httpURLConnection)) {
                return new ContentLengthInputStream(new BufferedInputStream(inputStream, 32768), httpURLConnection.getContentLength());
            }
            IoUtils.closeSilently(inputStream);
            throw new IOException("Image request failed with response code " + httpURLConnection.getResponseCode());
        } catch (IOException e2) {
            IoUtils.readAndCloseStream(httpURLConnection.getErrorStream());
            throw e2;
        }
    }

    private SSLContext sslContextForTrustedCertificates() {
        NoSuchAlgorithmException e;
        KeyManagementException e2;
        TrustManager[] trustManagerArr = new TrustManager[1];
        SSLContext mitm = new miTM();
        trustManagerArr[0] = mitm;
        try {
            mitm = SSLContext.getInstance(SSL.DEFAULT_PROTOCOL);
            try {
                mitm.init(null, trustManagerArr, null);
                return mitm;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
            } catch (KeyManagementException e4) {
                e2 = e4;
                e2.printStackTrace();
                return mitm;
            }
        } catch (NoSuchAlgorithmException e5) {
            NoSuchAlgorithmException noSuchAlgorithmException = e5;
            mitm = null;
            e = noSuchAlgorithmException;
            e.printStackTrace();
            return mitm;
        } catch (KeyManagementException e6) {
            KeyManagementException keyManagementException = e6;
            mitm = null;
            e2 = keyManagementException;
            e2.printStackTrace();
            return mitm;
        } catch (Throwable th) {
            return mitm;
        }
    }
}
