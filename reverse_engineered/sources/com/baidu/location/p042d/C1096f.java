package com.baidu.location.p042d;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.baidu.location.d.f */
class C1096f extends Thread {
    /* renamed from: a */
    final /* synthetic */ C1041e f2696a;

    C1096f(C1041e c1041e) {
        this.f2696a = c1041e;
    }

    public void run() {
        Throwable th;
        this.f2696a.f2338h = C1100j.m4019c();
        this.f2696a.mo2603b();
        this.f2696a.mo2597a();
        HttpURLConnection httpURLConnection = null;
        int i = this.f2696a.f2339i;
        while (i > 0) {
            HttpURLConnection httpURLConnection2;
            try {
                httpURLConnection2 = (HttpURLConnection) new URL(this.f2696a.f2338h).openConnection();
                try {
                    httpURLConnection2.setRequestMethod("GET");
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setUseCaches(false);
                    httpURLConnection2.setConnectTimeout(C1090a.f2660b);
                    httpURLConnection2.setReadTimeout(C1090a.f2660b);
                    httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                    httpURLConnection2.setRequestProperty("Accept-Charset", "UTF-8");
                    if (httpURLConnection2.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection2.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        inputStream.close();
                        byteArrayOutputStream.close();
                        this.f2696a.f2340j = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                        this.f2696a.mo2598a(true);
                        httpURLConnection2.disconnect();
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (i > 0) {
                            C1041e.f2337o++;
                            this.f2696a.f2340j = null;
                            this.f2696a.mo2598a(false);
                            return;
                        }
                        C1041e.f2337o = 0;
                        return;
                    }
                    httpURLConnection2.disconnect();
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    i--;
                    httpURLConnection = httpURLConnection2;
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                httpURLConnection2 = httpURLConnection;
                try {
                    Log.d(C1090a.f2659a, "NetworkCommunicationException!");
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    i--;
                    httpURLConnection = httpURLConnection2;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    httpURLConnection = httpURLConnection2;
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
        if (i > 0) {
            C1041e.f2337o = 0;
            return;
        }
        C1041e.f2337o++;
        this.f2696a.f2340j = null;
        this.f2696a.mo2598a(false);
        return;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        throw th;
    }
}
