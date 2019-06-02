package com.baidu.location.p042d;

import android.util.Log;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.loopj.android.http.AsyncHttpClient;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

/* renamed from: com.baidu.location.d.g */
class C1097g extends Thread {
    /* renamed from: a */
    final /* synthetic */ String f2697a;
    /* renamed from: b */
    final /* synthetic */ boolean f2698b;
    /* renamed from: c */
    final /* synthetic */ C1041e f2699c;

    C1097g(C1041e c1041e, String str, boolean z) {
        this.f2699c = c1041e;
        this.f2697a = str;
        this.f2698b = z;
    }

    public void run() {
        HttpURLConnection httpURLConnection;
        Throwable th;
        this.f2699c.f2338h = C1100j.m4019c();
        this.f2699c.mo2603b();
        this.f2699c.mo2597a();
        HttpURLConnection httpURLConnection2 = null;
        int i = this.f2699c.f2339i;
        while (i > 0) {
            try {
                URL url = new URL(this.f2699c.f2338h);
                StringBuffer stringBuffer = new StringBuffer();
                for (Entry entry : this.f2699c.f2341k.entrySet()) {
                    stringBuffer.append((String) entry.getKey());
                    stringBuffer.append(SimpleComparison.EQUAL_TO_OPERATION);
                    stringBuffer.append(entry.getValue());
                    stringBuffer.append(C0869a.f2161b);
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setConnectTimeout(C1090a.f2660b);
                    httpURLConnection.setReadTimeout(C1090a.f2660b);
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                    httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
                    httpURLConnection.setRequestProperty("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
                    httpURLConnection.setRequestProperty("Host", this.f2697a);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(stringBuffer.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        String contentEncoding = httpURLConnection.getContentEncoding();
                        InputStream gZIPInputStream = (contentEncoding == null || !contentEncoding.contains(AsyncHttpClient.ENCODING_GZIP)) ? inputStream : new GZIPInputStream(new BufferedInputStream(inputStream));
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = gZIPInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        gZIPInputStream.close();
                        byteArrayOutputStream.close();
                        this.f2699c.f2340j = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                        if (this.f2698b) {
                            this.f2699c.f2343m = byteArrayOutputStream.toByteArray();
                        }
                        this.f2699c.mo2598a(true);
                        httpURLConnection.disconnect();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (i > 0) {
                            C1041e.f2337o++;
                            this.f2699c.f2340j = null;
                            this.f2699c.mo2598a(false);
                            return;
                        }
                        C1041e.f2337o = 0;
                        return;
                    }
                    httpURLConnection.disconnect();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    httpURLConnection2 = httpURLConnection;
                    i--;
                } catch (Exception e) {
                    Log.d(C1090a.f2659a, "NetworkCommunicationException!");
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    httpURLConnection2 = httpURLConnection;
                    i--;
                } catch (Error e2) {
                    try {
                        Log.d(C1090a.f2659a, "NetworkCommunicationError!");
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        httpURLConnection2 = httpURLConnection;
                        i--;
                    } catch (Throwable th2) {
                        httpURLConnection2 = httpURLConnection;
                        th = th2;
                    }
                }
            } catch (Exception e3) {
                httpURLConnection = httpURLConnection2;
                Log.d(C1090a.f2659a, "NetworkCommunicationException!");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                httpURLConnection2 = httpURLConnection;
                i--;
            } catch (Error e4) {
                httpURLConnection = httpURLConnection2;
                Log.d(C1090a.f2659a, "NetworkCommunicationError!");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                httpURLConnection2 = httpURLConnection;
                i--;
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (i > 0) {
            C1041e.f2337o = 0;
            return;
        }
        C1041e.f2337o++;
        this.f2699c.f2340j = null;
        this.f2699c.mo2598a(false);
        return;
        if (httpURLConnection2 != null) {
            httpURLConnection2.disconnect();
        }
        throw th;
    }
}
