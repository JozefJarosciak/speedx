package com.baidu.location.p042d;

import android.util.Log;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.loopj.android.http.AsyncHttpClient;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;

/* renamed from: com.baidu.location.d.h */
class C1098h extends Thread {
    /* renamed from: a */
    final /* synthetic */ String f2700a;
    /* renamed from: b */
    final /* synthetic */ C1041e f2701b;

    C1098h(C1041e c1041e, String str) {
        this.f2701b = c1041e;
        this.f2700a = str;
    }

    public void run() {
        URL url;
        HttpsURLConnection httpsURLConnection;
        Exception e;
        HttpsURLConnection httpsURLConnection2;
        Throwable th;
        HttpsURLConnection httpsURLConnection3 = null;
        this.f2701b.mo2597a();
        this.f2701b.mo2603b();
        this.f2701b.f2338h = this.f2700a;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            url = new URL(this.f2701b.f2338h);
            try {
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
            } catch (Exception e2) {
                e = e2;
                httpsURLConnection2 = null;
                Object obj = url;
                try {
                    e.printStackTrace();
                    Log.i(C1090a.f2659a, "https NetworkCommunicationException!");
                    this.f2701b.f2340j = null;
                    this.f2701b.mo2598a(false);
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                    if (httpsURLConnection3 == null) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    Object obj2 = httpsURLConnection3;
                    httpsURLConnection3 = httpsURLConnection2;
                    if (httpsURLConnection3 != null) {
                        httpsURLConnection3.disconnect();
                    }
                    if (url == null) {
                        throw th;
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (httpsURLConnection3 != null) {
                    httpsURLConnection3.disconnect();
                }
                if (url == null) {
                    throw th;
                }
                throw th;
            }
            try {
                httpsURLConnection.setInstanceFollowRedirects(false);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setConnectTimeout(C1090a.f2660b);
                httpsURLConnection.setReadTimeout(C1090a.f2661c);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                httpsURLConnection.setRequestProperty("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
                httpsURLConnection.connect();
                for (Entry entry : this.f2701b.f2341k.entrySet()) {
                    stringBuffer.append((String) entry.getKey());
                    stringBuffer.append(SimpleComparison.EQUAL_TO_OPERATION);
                    stringBuffer.append(entry.getValue());
                    stringBuffer.append(C0869a.f2161b);
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
                httpsURLConnection.getOutputStream().write(stringBuffer.toString().getBytes());
                httpsURLConnection.getOutputStream().flush();
                httpsURLConnection.getOutputStream().close();
                if (httpsURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    String contentEncoding = httpsURLConnection.getContentEncoding();
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
                    this.f2701b.f2340j = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                    this.f2701b.mo2598a(true);
                } else {
                    this.f2701b.f2340j = null;
                    this.f2701b.mo2598a(false);
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                if (url == null) {
                }
            } catch (Exception e3) {
                httpsURLConnection2 = httpsURLConnection;
                e = e3;
                httpsURLConnection3 = url;
                e.printStackTrace();
                Log.i(C1090a.f2659a, "https NetworkCommunicationException!");
                this.f2701b.f2340j = null;
                this.f2701b.mo2598a(false);
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                if (httpsURLConnection3 == null) {
                }
            } catch (Throwable th4) {
                Throwable th5 = th4;
                httpsURLConnection3 = httpsURLConnection;
                th = th5;
                if (httpsURLConnection3 != null) {
                    httpsURLConnection3.disconnect();
                }
                if (url == null) {
                    throw th;
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            httpsURLConnection2 = null;
            e.printStackTrace();
            Log.i(C1090a.f2659a, "https NetworkCommunicationException!");
            this.f2701b.f2340j = null;
            this.f2701b.mo2598a(false);
            if (httpsURLConnection2 != null) {
                httpsURLConnection2.disconnect();
            }
            if (httpsURLConnection3 == null) {
            }
        } catch (Throwable th6) {
            th = th6;
            url = null;
            if (httpsURLConnection3 != null) {
                httpsURLConnection3.disconnect();
            }
            if (url == null) {
                throw th;
            }
            throw th;
        }
    }
}
