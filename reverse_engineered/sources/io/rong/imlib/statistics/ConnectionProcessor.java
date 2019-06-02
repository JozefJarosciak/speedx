package io.rong.imlib.statistics;

import android.os.Build.VERSION;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.commons.cli.HelpFormatter;

public class ConnectionProcessor implements Runnable {
    private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 30000;
    private static final int READ_TIMEOUT_IN_MILLISECONDS = 30000;
    private final DeviceId deviceId_;
    private final String serverURL_;
    private final SSLContext sslContext_;
    private final StatisticsStore store_;

    ConnectionProcessor(String str, StatisticsStore statisticsStore, DeviceId deviceId, SSLContext sSLContext) {
        this.serverURL_ = str;
        this.store_ = statisticsStore;
        this.deviceId_ = deviceId;
        this.sslContext_ = sSLContext;
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    URLConnection urlConnectionForEventData(String str) throws IOException {
        URLConnection uRLConnection;
        URL url = new URL(this.serverURL_);
        if (Statistics.publicKeyPinCertificates == null) {
            uRLConnection = (HttpURLConnection) url.openConnection();
        } else {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(this.sslContext_.getSocketFactory());
        }
        uRLConnection.setConnectTimeout(30000);
        uRLConnection.setReadTimeout(30000);
        uRLConnection.setUseCaches(false);
        uRLConnection.setDoInput(true);
        String picturePathFromQuery = UserData.getPicturePathFromQuery(url);
        if (Statistics.sharedInstance().isLoggingEnabled()) {
            Log.d("Statistics", "Got picturePath: " + picturePathFromQuery);
        }
        if (!picturePathFromQuery.equals("")) {
            File file = new File(picturePathFromQuery);
            uRLConnection.setDoOutput(true);
            picturePathFromQuery = Long.toHexString(System.currentTimeMillis());
            CharSequence charSequence = "\r\n";
            uRLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + picturePathFromQuery);
            OutputStream outputStream = uRLConnection.getOutputStream();
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            printWriter.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX + picturePathFromQuery).append(charSequence);
            printWriter.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + file.getName() + "\"").append(charSequence);
            printWriter.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(charSequence);
            printWriter.append("Content-Transfer-Encoding: binary").append(charSequence);
            printWriter.append(charSequence).flush();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
            printWriter.append(charSequence).flush();
            fileInputStream.close();
            printWriter.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX + picturePathFromQuery + HelpFormatter.DEFAULT_LONG_OPT_PREFIX).append(charSequence).flush();
        } else if (str.contains("&crash=")) {
            if (Statistics.sharedInstance().isLoggingEnabled()) {
                Log.d("Statistics", "Using post because of crash");
            }
            uRLConnection.setDoOutput(true);
            uRLConnection.setRequestMethod("POST");
            r1 = uRLConnection.getOutputStream();
            r2 = new BufferedWriter(new OutputStreamWriter(r1, "UTF-8"));
            r2.write(str);
            r2.flush();
            r2.close();
            r1.close();
        } else {
            uRLConnection.setDoOutput(true);
            uRLConnection.setRequestMethod("POST");
            uRLConnection.setRequestProperty("Connection", "close");
            r1 = uRLConnection.getOutputStream();
            r2 = new BufferedWriter(new OutputStreamWriter(r1, "UTF-8"));
            r2.write(str);
            r2.flush();
            r2.close();
            r1.close();
        }
        return uRLConnection;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r14 = this;
        r4 = 0;
        r5 = 1;
        r6 = 0;
    L_0x0003:
        r1 = r14.store_;
        r7 = r1.connections();
        if (r7 == 0) goto L_0x000e;
    L_0x000b:
        r1 = r7.length;
        if (r1 != 0) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r1 = r14.deviceId_;
        r1 = r1.getId();
        if (r1 != 0) goto L_0x003c;
    L_0x0017:
        r1 = io.rong.imlib.statistics.Statistics.sharedInstance();
        r1 = r1.isLoggingEnabled();
        if (r1 == 0) goto L_0x000e;
    L_0x0021:
        r1 = "Statistics";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "No Device ID available yet, skipping request ";
        r2 = r2.append(r3);
        r3 = r7[r6];
        r2 = r2.append(r3);
        r2 = r2.toString();
        android.util.Log.i(r1, r2);
        goto L_0x000e;
    L_0x003c:
        r8 = r7[r6];
        r2 = r14.urlConnectionForEventData(r8);	 Catch:{ Exception -> 0x0171, all -> 0x0146 }
        r2.connect();	 Catch:{ Exception -> 0x0177, all -> 0x0164 }
        r3 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x0177, all -> 0x0164 }
        r1 = r2.getInputStream();	 Catch:{ Exception -> 0x0177, all -> 0x0164 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x0177, all -> 0x0164 }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r1.<init>(r9);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
    L_0x0055:
        r9 = r3.read();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r10 = -1;
        if (r9 == r10) goto L_0x0098;
    L_0x005c:
        r1.write(r9);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        goto L_0x0055;
    L_0x0060:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r13;
    L_0x0064:
        r4 = io.rong.imlib.statistics.Statistics.sharedInstance();	 Catch:{ all -> 0x016f }
        r4 = r4.isLoggingEnabled();	 Catch:{ all -> 0x016f }
        if (r4 == 0) goto L_0x0086;
    L_0x006e:
        r4 = "Statistics";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x016f }
        r5.<init>();	 Catch:{ all -> 0x016f }
        r6 = "Got exception while trying to submit event data: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x016f }
        r5 = r5.append(r8);	 Catch:{ all -> 0x016f }
        r5 = r5.toString();	 Catch:{ all -> 0x016f }
        android.util.Log.w(r4, r5, r2);	 Catch:{ all -> 0x016f }
    L_0x0086:
        if (r3 == 0) goto L_0x008b;
    L_0x0088:
        r3.close();	 Catch:{ IOException -> 0x015f }
    L_0x008b:
        if (r1 == 0) goto L_0x000e;
    L_0x008d:
        r2 = r1 instanceof java.net.HttpURLConnection;
        if (r2 == 0) goto L_0x000e;
    L_0x0091:
        r1 = (java.net.HttpURLConnection) r1;
        r1.disconnect();
        goto L_0x000e;
    L_0x0098:
        r1 = r2 instanceof java.net.HttpURLConnection;	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        if (r1 == 0) goto L_0x017e;
    L_0x009c:
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r1 = r0;
        r9 = r1.getResponseCode();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 < r1) goto L_0x0131;
    L_0x00a8:
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 >= r1) goto L_0x0131;
    L_0x00ac:
        r1 = r5;
    L_0x00ad:
        r10 = "Statistics";
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r11.<init>();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r12 = "code=";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r11 = r11.append(r9);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r11 = r11.toString();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        android.util.Log.d(r10, r11);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        if (r1 != 0) goto L_0x00f3;
    L_0x00c7:
        r10 = io.rong.imlib.statistics.Statistics.sharedInstance();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r10 = r10.isLoggingEnabled();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        if (r10 == 0) goto L_0x00f3;
    L_0x00d1:
        r10 = "Statistics";
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r11.<init>();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r12 = "HTTP error response code was ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9 = r11.append(r9);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r11 = " from submitting event data: ";
        r9 = r9.append(r11);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9 = r9.append(r8);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9 = r9.toString();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        android.util.Log.w(r10, r9);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
    L_0x00f3:
        if (r1 == 0) goto L_0x0134;
    L_0x00f5:
        r1 = io.rong.imlib.statistics.Statistics.sharedInstance();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r1 = r1.isLoggingEnabled();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        if (r1 == 0) goto L_0x0117;
    L_0x00ff:
        r1 = "Statistics";
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9.<init>();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r10 = "ok ->";
        r9 = r9.append(r10);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9 = r9.append(r8);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9 = r9.toString();	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        android.util.Log.d(r1, r9);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
    L_0x0117:
        r1 = r14.store_;	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r9 = 0;
        r7 = r7[r9];	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        r1.removeConnection(r7);	 Catch:{ Exception -> 0x0060, all -> 0x016a }
        if (r3 == 0) goto L_0x0124;
    L_0x0121:
        r3.close();	 Catch:{ IOException -> 0x015d }
    L_0x0124:
        if (r2 == 0) goto L_0x0003;
    L_0x0126:
        r1 = r2 instanceof java.net.HttpURLConnection;
        if (r1 == 0) goto L_0x0003;
    L_0x012a:
        r2 = (java.net.HttpURLConnection) r2;
        r2.disconnect();
        goto L_0x0003;
    L_0x0131:
        r1 = r6;
        goto L_0x00ad;
    L_0x0134:
        if (r3 == 0) goto L_0x0139;
    L_0x0136:
        r3.close();	 Catch:{ IOException -> 0x015b }
    L_0x0139:
        if (r2 == 0) goto L_0x000e;
    L_0x013b:
        r1 = r2 instanceof java.net.HttpURLConnection;
        if (r1 == 0) goto L_0x000e;
    L_0x013f:
        r2 = (java.net.HttpURLConnection) r2;
        r2.disconnect();
        goto L_0x000e;
    L_0x0146:
        r1 = move-exception;
        r2 = r1;
        r3 = r4;
        r1 = r4;
    L_0x014a:
        if (r3 == 0) goto L_0x014f;
    L_0x014c:
        r3.close();	 Catch:{ IOException -> 0x0162 }
    L_0x014f:
        if (r1 == 0) goto L_0x015a;
    L_0x0151:
        r3 = r1 instanceof java.net.HttpURLConnection;
        if (r3 == 0) goto L_0x015a;
    L_0x0155:
        r1 = (java.net.HttpURLConnection) r1;
        r1.disconnect();
    L_0x015a:
        throw r2;
    L_0x015b:
        r1 = move-exception;
        goto L_0x0139;
    L_0x015d:
        r1 = move-exception;
        goto L_0x0124;
    L_0x015f:
        r2 = move-exception;
        goto L_0x008b;
    L_0x0162:
        r3 = move-exception;
        goto L_0x014f;
    L_0x0164:
        r1 = move-exception;
        r3 = r4;
        r13 = r1;
        r1 = r2;
        r2 = r13;
        goto L_0x014a;
    L_0x016a:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r13;
        goto L_0x014a;
    L_0x016f:
        r2 = move-exception;
        goto L_0x014a;
    L_0x0171:
        r1 = move-exception;
        r2 = r1;
        r3 = r4;
        r1 = r4;
        goto L_0x0064;
    L_0x0177:
        r1 = move-exception;
        r3 = r4;
        r13 = r1;
        r1 = r2;
        r2 = r13;
        goto L_0x0064;
    L_0x017e:
        r1 = r5;
        goto L_0x00f3;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.imlib.statistics.ConnectionProcessor.run():void");
    }

    String getServerURL() {
        return this.serverURL_;
    }

    StatisticsStore getCountlyStore() {
        return this.store_;
    }

    DeviceId getDeviceId() {
        return this.deviceId_;
    }
}
