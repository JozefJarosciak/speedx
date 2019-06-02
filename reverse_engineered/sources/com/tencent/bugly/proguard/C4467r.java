package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.tencent.bugly.crashreport.common.info.C4418b;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.apache.http.HttpStatus;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.r */
public final class C4467r {
    /* renamed from: b */
    private static C4467r f15722b;
    /* renamed from: a */
    public Map<String, String> f15723a = null;
    /* renamed from: c */
    private Context f15724c;

    private C4467r(Context context) {
        this.f15724c = context;
    }

    /* renamed from: a */
    public static C4467r m17697a(Context context) {
        if (f15722b == null) {
            f15722b = new C4467r(context);
        }
        return f15722b;
    }

    /* renamed from: a */
    public final byte[] m17702a(String str, byte[] bArr, C4472u c4472u, Map<String, String> map) {
        Throwable th;
        int i;
        if (str == null) {
            C4475w.m17753e("Failed for no URL.", new Object[0]);
            return null;
        }
        long j;
        int i2 = 0;
        int i3 = 0;
        if (bArr == null) {
            j = 0;
        } else {
            j = (long) bArr.length;
        }
        C4475w.m17751c("request: %s, send: %d (pid=%d | tid=%d)", str, Long.valueOf(j), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Object obj = null;
        String str2 = str;
        while (i2 <= 0 && i3 <= 0) {
            if (obj != null) {
                obj = null;
            } else {
                i2++;
                if (i2 > 1) {
                    C4475w.m17751c("try time: " + i2, new Object[0]);
                    SystemClock.sleep(((long) new Random(System.currentTimeMillis()).nextInt(10000)) + AbstractComponentTracker.LINGERING_TIMEOUT);
                }
            }
            String e = C4418b.m17368e(this.f15724c);
            if (e == null) {
                C4475w.m17752d("Failed to request for network not avail", new Object[0]);
            } else {
                int i4;
                c4472u.m17738a(j);
                HttpURLConnection a = m17699a(str2, bArr, e, (Map) map);
                if (a != null) {
                    try {
                        int responseCode = a.getResponseCode();
                        if (responseCode == 200) {
                            long j2;
                            this.f15723a = C4467r.m17700a(a);
                            byte[] b = C4467r.m17701b(a);
                            if (b == null) {
                                j2 = 0;
                            } else {
                                j2 = (long) b.length;
                            }
                            c4472u.m17739b(j2);
                            try {
                                a.disconnect();
                            } catch (Throwable th2) {
                                if (!C4475w.m17748a(th2)) {
                                    th2.printStackTrace();
                                }
                            }
                            return b;
                        }
                        Object obj2 = (responseCode == HttpStatus.SC_MOVED_PERMANENTLY || responseCode == HttpStatus.SC_MOVED_TEMPORARILY || responseCode == HttpStatus.SC_SEE_OTHER || responseCode == HttpStatus.SC_TEMPORARY_REDIRECT) ? 1 : null;
                        if (obj2 != null) {
                            try {
                                String headerField = a.getHeaderField("Location");
                                if (headerField == null) {
                                    C4475w.m17753e("Failed to redirect: %d" + responseCode, new Object[0]);
                                    try {
                                        a.disconnect();
                                    } catch (Throwable th22) {
                                        if (!C4475w.m17748a(th22)) {
                                            th22.printStackTrace();
                                        }
                                    }
                                    return null;
                                }
                                i3++;
                                try {
                                    C4475w.m17751c("redirect code: %d ,to:%s", Integer.valueOf(responseCode), headerField);
                                    obj = 1;
                                    str2 = headerField;
                                    i2 = i3;
                                    i3 = 0;
                                } catch (Throwable e2) {
                                    str2 = headerField;
                                    th = e2;
                                    i = 1;
                                    i2 = i3;
                                    i3 = 0;
                                    try {
                                        if (!C4475w.m17748a(th)) {
                                            th.printStackTrace();
                                        }
                                        try {
                                            a.disconnect();
                                        } catch (Throwable th3) {
                                            if (!C4475w.m17748a(th3)) {
                                                th3.printStackTrace();
                                            }
                                        }
                                        i4 = i2;
                                        i2 = i3;
                                        i3 = i4;
                                    } catch (Throwable th4) {
                                        if (!C4475w.m17748a(th4)) {
                                            th4.printStackTrace();
                                        }
                                    }
                                }
                            } catch (Throwable e22) {
                                Throwable th5 = e22;
                                i = 1;
                                th3 = th5;
                                int i5 = i3;
                                i3 = i2;
                                i2 = i5;
                                if (C4475w.m17748a(th3)) {
                                    th3.printStackTrace();
                                }
                                a.disconnect();
                                i4 = i2;
                                i2 = i3;
                                i3 = i4;
                            }
                        } else {
                            i4 = i3;
                            i3 = i2;
                            i2 = i4;
                        }
                        try {
                            C4475w.m17752d("response code " + responseCode, new Object[0]);
                            long contentLength = (long) a.getContentLength();
                            if (contentLength < 0) {
                                contentLength = 0;
                            }
                            c4472u.m17739b(contentLength);
                            try {
                                a.disconnect();
                            } catch (Throwable th32) {
                                if (!C4475w.m17748a(th32)) {
                                    th32.printStackTrace();
                                }
                            }
                        } catch (IOException e3) {
                            th32 = e3;
                            if (C4475w.m17748a(th32)) {
                                th32.printStackTrace();
                            }
                            a.disconnect();
                            i4 = i2;
                            i2 = i3;
                            i3 = i4;
                        }
                    } catch (IOException e4) {
                        th32 = e4;
                        i4 = i3;
                        i3 = i2;
                        i2 = i4;
                    }
                } else {
                    C4475w.m17751c("Failed to execute post.", new Object[0]);
                    c4472u.m17739b(0);
                    i4 = i3;
                    i3 = i2;
                    i2 = i4;
                }
                i4 = i2;
                i2 = i3;
                i3 = i4;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static Map<String, String> m17700a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* renamed from: b */
    private static byte[] m17701b(HttpURLConnection httpURLConnection) {
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        if (httpURLConnection != null) {
            BufferedInputStream bufferedInputStream;
            try {
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr2);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    byteArrayOutputStream.flush();
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                } catch (Throwable th4) {
                    th3 = th4;
                    try {
                        if (!C4475w.m17748a(th3)) {
                            th3.printStackTrace();
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable th32) {
                                th32.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th5) {
                        th2 = th5;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable th322) {
                                th322.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3222) {
                bufferedInputStream = bArr;
                th2 = th3222;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th2;
            }
        }
        return bArr;
    }

    /* renamed from: a */
    private HttpURLConnection m17699a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            C4475w.m17753e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection a = C4467r.m17698a(str2, str);
        if (a == null) {
            C4475w.m17753e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    a.setRequestProperty((String) entry.getKey(), URLEncoder.encode((String) entry.getValue(), "utf-8"));
                }
            }
            a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            a.connect();
            OutputStream outputStream = a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a;
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            C4475w.m17753e("Failed to upload crash, please check your network.", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m17698a(String str, String str2) {
        try {
            HttpURLConnection httpURLConnection;
            URL url = new URL(str2);
            if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
