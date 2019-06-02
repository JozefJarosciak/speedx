package io.rong.imlib.navigation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.protocol.HTTP;

public class PCAuthConfig {
    private static final String NAVIGATION_HTTPS_URL = "https://private.cn.ronghub.com/check.json";
    private static final String TAG = "AuthConfig";
    private Future<?> connectionFuture;
    private ExecutorService executor;

    /* renamed from: io.rong.imlib.navigation.PCAuthConfig$2 */
    class C53962 implements X509TrustManager {
        C53962() {
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
        private static PCAuthConfig sIns = new PCAuthConfig();

        private SingletonHolder() {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void request(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0028 in list [B:7:0x0050]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r6 = this;
        r0 = "AuthConfig";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "request : ";
        r1 = r1.append(r2);
        r1 = r1.append(r7);
        r2 = "-";
        r1 = r1.append(r2);
        r1 = r1.append(r8);
        r1 = r1.toString();
        io.rong.common.RLog.m19422i(r0, r1);
        r0 = android.text.TextUtils.isEmpty(r9);
        if (r0 == 0) goto L_0x0029;
    L_0x0028:
        return;
    L_0x0029:
        r1 = 0;
        r2 = 0;
        r1 = r6.createConnection(r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x0054, all -> 0x0076 }
        r1.connect();	 Catch:{ Exception -> 0x0054, all -> 0x0076 }
        r0 = r1.getResponseCode();	 Catch:{ Exception -> 0x0054, all -> 0x0076 }
        r2 = "AuthConfig";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "request: ";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        io.rong.common.RLog.m19422i(r2, r0);
        if (r1 == 0) goto L_0x0028;
    L_0x0050:
        r1.disconnect();
        goto L_0x0028;
    L_0x0054:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x0054, all -> 0x0076 }
        r0 = "AuthConfig";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "request: ";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r2 = r2.toString();
        io.rong.common.RLog.m19422i(r0, r2);
        if (r1 == 0) goto L_0x0028;
    L_0x0072:
        r1.disconnect();
        goto L_0x0028;
    L_0x0076:
        r0 = move-exception;
        r3 = "AuthConfig";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "request: ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r2 = r2.toString();
        io.rong.common.RLog.m19422i(r3, r2);
        if (r1 == 0) goto L_0x0094;
    L_0x0091:
        r1.disconnect();
    L_0x0094:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.imlib.navigation.PCAuthConfig.request(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static PCAuthConfig getInstance() {
        return SingletonHolder.sIns;
    }

    public void postConfig(String str, String str2, String str3, String str4, String str5) {
        if (str5 != null && str4 != null && str3 != null) {
            if (!(this.connectionFuture == null || this.connectionFuture.isDone())) {
                this.connectionFuture.cancel(true);
            }
            ensureExecutor();
            final String str6 = str;
            final String str7 = str2;
            final String str8 = str3;
            final String str9 = str4;
            final String str10 = str5;
            this.connectionFuture = this.executor.submit(new Runnable() {
                public void run() {
                    PCAuthConfig.this.request(str6, str7, str8, str9, str10);
                }
            });
        }
    }

    private void ensureExecutor() {
        if (this.executor == null) {
            this.executor = Executors.newSingleThreadExecutor();
        }
    }

    private SSLContext initSSL() {
        try {
            TrustManager[] trustManagerArr = new TrustManager[]{new C53962()};
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, trustManagerArr, null);
            return instance;
        } catch (Throwable th) {
            IllegalStateException illegalStateException = new IllegalStateException(th);
        }
    }

    private HttpURLConnection createConnection(String str, String str2, String str3, String str4, String str5) throws IOException {
        URL url = new URL(NAVIGATION_HTTPS_URL);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setSSLSocketFactory(initSSL().getSocketFactory());
        httpsURLConnection.setConnectTimeout(10000);
        httpsURLConnection.setReadTimeout(10000);
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setRequestProperty("Connection", HTTP.CONN_CLOSE);
        String str6 = ((((("customId=" + URLEncoder.encode(str, "UTF-8")) + "&code=") + URLEncoder.encode(str2, "UTF-8")) + "&appKey=" + str3) + "&nip=" + str4) + "&ip=" + str5;
        httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(str6.length()));
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setDoInput(true);
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(str6);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        return httpsURLConnection;
    }
}
