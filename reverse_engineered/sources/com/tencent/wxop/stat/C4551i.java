package com.tencent.wxop.stat;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.tencent.p191a.p192a.p193a.p194a.C4399g;
import com.tencent.p191a.p192a.p193a.p194a.C4400h;
import com.tencent.wxop.stat.common.C4533e;
import com.tencent.wxop.stat.common.C4534f;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.p201a.C4513e;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.i */
class C4551i {
    /* renamed from: d */
    private static StatLogger f16126d = C4539k.m18052b();
    /* renamed from: e */
    private static C4551i f16127e = null;
    /* renamed from: f */
    private static Context f16128f = null;
    /* renamed from: a */
    DefaultHttpClient f16129a = null;
    /* renamed from: b */
    C4533e f16130b = null;
    /* renamed from: c */
    StringBuilder f16131c = new StringBuilder(4096);
    /* renamed from: g */
    private long f16132g = 0;

    private C4551i(Context context) {
        try {
            f16128f = context.getApplicationContext();
            this.f16132g = System.currentTimeMillis() / 1000;
            this.f16130b = new C4533e();
            if (StatConfig.isDebugEnable()) {
                try {
                    Logger.getLogger("org.apache.http.wire").setLevel(Level.FINER);
                    Logger.getLogger("org.apache.http.headers").setLevel(Level.FINER);
                    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
                    System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
                    System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
                } catch (Throwable th) {
                }
            }
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            this.f16129a = new DefaultHttpClient(basicHttpParams);
            this.f16129a.setKeepAliveStrategy(new C4552j(this));
        } catch (Throwable th2) {
            f16126d.m18011e(th2);
        }
    }

    /* renamed from: a */
    static Context m18119a() {
        return f16128f;
    }

    /* renamed from: a */
    static void m18120a(Context context) {
        f16128f = context.getApplicationContext();
    }

    /* renamed from: a */
    private void m18121a(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("mid");
            if (C4400h.m17243c(optString)) {
                if (StatConfig.isDebugEnable()) {
                    f16126d.m18012i("update mid:" + optString);
                }
                C4399g.m17233E(f16128f).m17235a(optString);
            }
            if (!jSONObject.isNull("cfg")) {
                StatConfig.m17863a(f16128f, jSONObject.getJSONObject("cfg"));
            }
            if (!jSONObject.isNull("ncts")) {
                int i = jSONObject.getInt("ncts");
                int currentTimeMillis = (int) (((long) i) - (System.currentTimeMillis() / 1000));
                if (StatConfig.isDebugEnable()) {
                    f16126d.m18012i("server time:" + i + ", diff time:" + currentTimeMillis);
                }
                C4539k.m18084z(f16128f);
                C4539k.m18048a(f16128f, currentTimeMillis);
            }
        } catch (Throwable th) {
            f16126d.m18014w(th);
        }
    }

    /* renamed from: b */
    static C4551i m18122b(Context context) {
        if (f16127e == null) {
            synchronized (C4551i.class) {
                if (f16127e == null) {
                    f16127e = new C4551i(context);
                }
            }
        }
        return f16127e;
    }

    /* renamed from: a */
    void m18123a(C4513e c4513e, C4526h c4526h) {
        m18125b(Arrays.asList(new String[]{c4513e.m17909g()}), c4526h);
    }

    /* renamed from: a */
    void m18124a(List<?> list, C4526h c4526h) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            list.get(0);
            Throwable th;
            try {
                String str;
                this.f16131c.delete(0, this.f16131c.length());
                this.f16131c.append("[");
                String str2 = "rc4";
                for (int i2 = 0; i2 < size; i2++) {
                    this.f16131c.append(list.get(i2).toString());
                    if (i2 != size - 1) {
                        this.f16131c.append(",");
                    }
                }
                this.f16131c.append("]");
                String stringBuilder = this.f16131c.toString();
                size = stringBuilder.length();
                String str3 = StatConfig.getStatReportUrl() + "/?index=" + this.f16132g;
                this.f16132g++;
                if (StatConfig.isDebugEnable()) {
                    f16126d.m18012i("[" + str3 + "]Send request(" + size + "bytes), content:" + stringBuilder);
                }
                HttpPost httpPost = new HttpPost(str3);
                httpPost.addHeader("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
                httpPost.setHeader("Connection", HTTP.CONN_KEEP_ALIVE);
                httpPost.removeHeaders("Cache-Control");
                HttpHost a = C4525a.m17934a(f16128f).m17941a();
                httpPost.addHeader("Content-Encoding", str2);
                if (a == null) {
                    this.f16129a.getParams().removeParameter("http.route.default-proxy");
                } else {
                    if (StatConfig.isDebugEnable()) {
                        f16126d.m18009d("proxy:" + a.toHostString());
                    }
                    httpPost.addHeader("X-Content-Encoding", str2);
                    this.f16129a.getParams().setParameter("http.route.default-proxy", a);
                    httpPost.addHeader("X-Online-Host", StatConfig.f15849k);
                    httpPost.addHeader("Accept", "*/*");
                    httpPost.addHeader("Content-Type", "json");
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(size);
                byte[] bytes = stringBuilder.getBytes("UTF-8");
                int length = bytes.length;
                if (size > StatConfig.f15853o) {
                    i = 1;
                }
                if (i != 0) {
                    httpPost.removeHeaders("Content-Encoding");
                    str = str2 + ",gzip";
                    httpPost.addHeader("Content-Encoding", str);
                    if (a != null) {
                        httpPost.removeHeaders("X-Content-Encoding");
                        httpPost.addHeader("X-Content-Encoding", str);
                    }
                    byteArrayOutputStream.write(new byte[4]);
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    gZIPOutputStream.write(bytes);
                    gZIPOutputStream.close();
                    bytes = byteArrayOutputStream.toByteArray();
                    ByteBuffer.wrap(bytes, 0, 4).putInt(length);
                    if (StatConfig.isDebugEnable()) {
                        f16126d.m18009d("before Gzip:" + length + " bytes, after Gzip:" + bytes.length + " bytes");
                    }
                }
                httpPost.setEntity(new ByteArrayEntity(C4534f.m18026a(bytes)));
                HttpResponse execute = this.f16129a.execute(httpPost);
                HttpEntity entity = execute.getEntity();
                size = execute.getStatusLine().getStatusCode();
                long contentLength = entity.getContentLength();
                if (StatConfig.isDebugEnable()) {
                    f16126d.m18012i("http recv response status code:" + size + ", content length:" + contentLength);
                }
                if (contentLength <= 0) {
                    f16126d.m18010e((Object) "Server response no data.");
                    if (c4526h != null) {
                        c4526h.mo6120b();
                    }
                    EntityUtils.toString(entity);
                    return;
                }
                if (contentLength > 0) {
                    InputStream content = entity.getContent();
                    DataInputStream dataInputStream = new DataInputStream(content);
                    bytes = new byte[((int) entity.getContentLength())];
                    dataInputStream.readFully(bytes);
                    content.close();
                    dataInputStream.close();
                    Header firstHeader = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader != null) {
                        if (firstHeader.getValue().equalsIgnoreCase("gzip,rc4")) {
                            bytes = C4534f.m18028b(C4539k.m18050a(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4,gzip")) {
                            bytes = C4539k.m18050a(C4534f.m18028b(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                            bytes = C4539k.m18050a(bytes);
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4")) {
                            bytes = C4534f.m18028b(bytes);
                        }
                    }
                    str = new String(bytes, "UTF-8");
                    if (StatConfig.isDebugEnable()) {
                        f16126d.m18012i("http get response data:" + str);
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    if (size == 200) {
                        m18121a(jSONObject);
                        if (c4526h != null) {
                            if (jSONObject.optInt("ret") == 0) {
                                c4526h.mo6119a();
                            } else {
                                f16126d.error((Object) "response error data.");
                                c4526h.mo6120b();
                            }
                        }
                    } else {
                        f16126d.error("Server response error code:" + size + ", error:" + new String(bytes, "UTF-8"));
                        if (c4526h != null) {
                            c4526h.mo6120b();
                        }
                    }
                    content.close();
                } else {
                    EntityUtils.toString(entity);
                }
                byteArrayOutputStream.close();
                th = null;
                if (th != null) {
                    f16126d.error(th);
                    if (c4526h != null) {
                        try {
                            c4526h.mo6120b();
                        } catch (Throwable th2) {
                            f16126d.m18011e(th2);
                        }
                    }
                    if (th instanceof OutOfMemoryError) {
                        System.gc();
                        this.f16131c = null;
                        this.f16131c = new StringBuilder(2048);
                    }
                    C4525a.m17934a(f16128f).m17945d();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* renamed from: b */
    void m18125b(List<?> list, C4526h c4526h) {
        if (this.f16130b != null) {
            this.f16130b.m18024a(new C4553k(this, list, c4526h));
        }
    }
}
