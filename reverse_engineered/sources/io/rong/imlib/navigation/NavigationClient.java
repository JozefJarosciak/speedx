package io.rong.imlib.navigation;

import android.content.Context;
import android.text.TextUtils;
import io.rong.common.RFLog;
import io.rong.common.RLog;
import io.rong.imlib.common.BuildVar;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.protocol.HTTP;

public class NavigationClient {
    private static final String NAVIGATION_HTTPS_URL = "https://nav.cn.ronghub.com/navi.xml";
    private static String NAVIGATION_HTTP_URL = "http://nav.cn.ronghub.com/navi.xml";
    private static final String TAG = "NavigationClient";
    private Context context;
    private ExecutorService executor;
    private NavigationObserver navigationObserver;
    private boolean verifyCertificate;

    /* renamed from: io.rong.imlib.navigation.NavigationClient$4 */
    class C53944 implements X509TrustManager {
        C53944() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class SingletonHolder {
        private static NavigationClient sIns = new NavigationClient();

        private SingletonHolder() {
        }
    }

    private NavigationClient() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    public static NavigationClient getInstance() {
        return SingletonHolder.sIns;
    }

    public void enablePublicKeyPinning() {
        this.verifyCertificate = true;
    }

    public void setNaviDomain(String str) {
        NAVIGATION_HTTP_URL = String.format("http://%s/navi.xml", new Object[]{str});
    }

    public void addObserver(NavigationObserver navigationObserver) {
        this.navigationObserver = navigationObserver;
    }

    public void clearObserver() {
        this.navigationObserver = null;
    }

    public String getCMPServer() {
        return NavigationCacheHelper.getCMPServer(this.context);
    }

    public long getLastCachedTime() {
        return NavigationCacheHelper.getCachedTime();
    }

    public void getCMPServer(Context context, final String str, final String str2) {
        this.context = context;
        if (!NavigationCacheHelper.isCacheValid(context, str, str2)) {
            NavigationCacheHelper.clearCache(context);
            this.executor.submit(new Runnable() {
                public void run() {
                    NavigationClient.this.request(str, str2, false);
                }
            });
        } else if (this.navigationObserver != null) {
            this.navigationObserver.onSuccess(NavigationCacheHelper.getCMPServer(context));
        }
    }

    public boolean needUpdateCMP(Context context, final String str, final String str2) {
        if (!NavigationCacheHelper.isCacheTimeout(context)) {
            return false;
        }
        this.executor.submit(new Runnable() {
            public void run() {
                NavigationClient.this.request(str, str2, true);
            }
        });
        return true;
    }

    public void clearCache(Context context) {
        NavigationCacheHelper.clearCache(context);
    }

    public void updateCacheTime(Context context) {
        NavigationCacheHelper.updateTime(context, 0);
    }

    public String getVoIPCallInfo(Context context) {
        return NavigationCacheHelper.getVoIPCallInfo(context);
    }

    public String getMediaServer(Context context) {
        return NavigationCacheHelper.getMediaServer(context);
    }

    public LocationConfig getLocationConfig(Context context) {
        return NavigationCacheHelper.getLocationConfig(context);
    }

    private void request(final String str, final String str2, boolean z) {
        HttpURLConnection createConnection;
        BufferedInputStream bufferedInputStream;
        Exception e;
        HttpURLConnection httpURLConnection;
        Throwable th;
        BufferedInputStream bufferedInputStream2 = null;
        RLog.m19422i(TAG, "request start: " + str2);
        int i = 0;
        try {
            createConnection = createConnection(str, str2);
            try {
                InputStream errorStream;
                createConnection.connect();
                i = createConnection.getResponseCode();
                if (i != 200) {
                    errorStream = createConnection.getErrorStream();
                } else {
                    errorStream = createConnection.getInputStream();
                }
                bufferedInputStream = new BufferedInputStream(errorStream);
            } catch (Exception e2) {
                e = e2;
                httpURLConnection = createConnection;
                try {
                    if (this.navigationObserver != null) {
                        this.navigationObserver.onError(NavigationCacheHelper.getCMPServer(this.context), 30004);
                    }
                    e.printStackTrace();
                    RFLog.write(TAG, e);
                    RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (httpURLConnection == null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    createConnection = httpURLConnection;
                    bufferedInputStream = bufferedInputStream2;
                    RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (createConnection != null) {
                        createConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (createConnection != null) {
                    createConnection.disconnect();
                }
                throw th;
            }
            try {
                int read;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
                while (true) {
                    read = bufferedInputStream.read();
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(read);
                }
                final String trim = new String(byteArrayOutputStream.toByteArray(), "utf-8").trim();
                if (z) {
                    String decode2cmp = NavigationCacheHelper.decode2cmp(this.context, trim, i);
                    String cMPServer = NavigationCacheHelper.getCMPServer(this.context);
                    if (TextUtils.isEmpty(decode2cmp) || decode2cmp.equals(cMPServer)) {
                        NavigationCacheHelper.decode2File(this.context, trim, i);
                        NavigationCacheHelper.cacheRequest(this.context, str, str2);
                    } else if (this.navigationObserver != null) {
                        this.navigationObserver.onReconnect(decode2cmp, new NavigationCallback() {
                            public void onSuccess() {
                                NavigationCacheHelper.decode2File(NavigationClient.this.context, trim, 200);
                                NavigationCacheHelper.cacheRequest(NavigationClient.this.context, str, str2);
                            }

                            public void onError() {
                                NavigationCacheHelper.updateTime(NavigationClient.this.context, System.currentTimeMillis() - ((long) TimeZone.getDefault().getRawOffset()));
                            }
                        });
                    }
                } else {
                    read = NavigationCacheHelper.decode2File(this.context, trim, i);
                    if (read == 0) {
                        if (this.navigationObserver != null) {
                            this.navigationObserver.onSuccess(NavigationCacheHelper.getCMPServer(this.context));
                        }
                        NavigationCacheHelper.cacheRequest(this.context, str, str2);
                    } else {
                        if (this.navigationObserver != null) {
                            this.navigationObserver.onError(null, read);
                        }
                        RLog.m19420e(TAG, "request failure : " + read + ", data = " + trim);
                    }
                }
                RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                if (createConnection != null) {
                    createConnection.disconnect();
                }
            } catch (Exception e5) {
                e = e5;
                bufferedInputStream2 = bufferedInputStream;
                httpURLConnection = createConnection;
                if (this.navigationObserver != null) {
                    this.navigationObserver.onError(NavigationCacheHelper.getCMPServer(this.context), 30004);
                }
                e.printStackTrace();
                RFLog.write(TAG, e);
                RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (httpURLConnection == null) {
                    httpURLConnection.disconnect();
                }
            } catch (Throwable th4) {
                th = th4;
                RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (createConnection != null) {
                    createConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            httpURLConnection = null;
            if (this.navigationObserver != null) {
                this.navigationObserver.onError(NavigationCacheHelper.getCMPServer(this.context), 30004);
            }
            e.printStackTrace();
            RFLog.write(TAG, e);
            RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            if (httpURLConnection == null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedInputStream = null;
            createConnection = null;
            RLog.m19422i(TAG, "request end: " + i + ", force = " + z);
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (createConnection != null) {
                createConnection.disconnect();
            }
            throw th;
        }
    }

    private SSLContext initSSL() {
        try {
            TrustManager[] trustManagerArr = new TrustManager[]{new C53944()};
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, trustManagerArr, null);
            return instance;
        } catch (Throwable th) {
            IllegalStateException illegalStateException = new IllegalStateException(th);
        }
    }

    private HttpURLConnection createConnection(String str, String str2) throws IOException {
        HttpURLConnection httpURLConnection;
        if (this.verifyCertificate) {
            URL url = new URL(NAVIGATION_HTTPS_URL);
            httpURLConnection = (HttpsURLConnection) url.openConnection();
            httpURLConnection.setSSLSocketFactory(initSSL().getSocketFactory());
        } else {
            httpURLConnection = (HttpURLConnection) new URL(NAVIGATION_HTTP_URL).openConnection();
        }
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Connection", HTTP.CONN_CLOSE);
        httpURLConnection.setRequestProperty("User-Agent", "RongCloud");
        String str3 = ("token=" + URLEncoder.encode(str2, "UTF-8")) + "&v=" + BuildVar.SDK_VERSION;
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(str3.length()));
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("appId", str);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(str3);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        return httpURLConnection;
    }
}
