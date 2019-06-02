package p203u.aly;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.C4731a;
import com.umeng.analytics.C4743c;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;

/* compiled from: NetworkHelper */
/* renamed from: u.aly.r */
public class C5954r {
    /* renamed from: a */
    private String f19090a;
    /* renamed from: b */
    private String f19091b = "10.0.0.172";
    /* renamed from: c */
    private int f19092c = 80;
    /* renamed from: d */
    private Context f19093d;
    /* renamed from: e */
    private C5951o f19094e;

    public C5954r(Context context) {
        this.f19093d = context;
        this.f19090a = m22009a(context);
    }

    /* renamed from: a */
    public void m22012a(C5951o c5951o) {
        this.f19094e = c5951o;
    }

    /* renamed from: a */
    public byte[] m22013a(byte[] bArr) {
        byte[] bArr2 = null;
        for (String a : C4743c.f16639d) {
            bArr2 = m22011a(bArr, a);
            if (bArr2 != null) {
                if (this.f19094e != null) {
                    this.f19094e.mo7232c();
                }
                return bArr2;
            }
            if (this.f19094e != null) {
                this.f19094e.mo7233d();
            }
        }
        return bArr2;
    }

    /* renamed from: a */
    private boolean m22010a() {
        if (this.f19093d.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f19093d.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f19093d.getSystemService("connectivity");
            if (!af.m21115a(this.f19093d, "android.permission.ACCESS_NETWORK_STATE")) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private byte[] m22011a(byte[] bArr, String str) {
        InputStream inputStream;
        Throwable e;
        HttpURLConnection httpURLConnection;
        try {
            if (this.f19094e != null) {
                this.f19094e.mo7230a();
            }
            if (m22010a()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Type.HTTP, new InetSocketAddress(this.f19091b, this.f19092c)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", this.f19090a);
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setRequestProperty("Content-Type", "envelope/json");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                if (Integer.parseInt(VERSION.SDK) < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.f19094e != null) {
                    this.f19094e.mo7231b();
                }
                int responseCode = httpURLConnection.getResponseCode();
                Object headerField = httpURLConnection.getHeaderField("Content-Type");
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    headerField = null;
                } else {
                    headerField = 1;
                }
                if (responseCode != 200 || r0 == null) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
                ah.m21158b("Send message to " + str);
                inputStream = httpURLConnection.getInputStream();
                byte[] b = ag.m21151b(inputStream);
                ag.m21152c(inputStream);
                if (httpURLConnection == null) {
                    return b;
                }
                httpURLConnection.disconnect();
                return b;
            } catch (Exception e2) {
                e = e2;
                try {
                    ah.m21160b("IOException,Failed to send message.", e);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable th) {
                    e = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                ag.m21152c(inputStream);
            }
        } catch (Exception e3) {
            e = e3;
            httpURLConnection = null;
            ah.m21160b("IOException,Failed to send message.", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th3) {
            e = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw e;
        }
    }

    /* renamed from: a */
    private String m22009a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append("/");
        stringBuffer.append("6.0.0");
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(af.m21138t(context));
            stringBuffer2.append("/");
            stringBuffer2.append(af.m21117b(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append("/");
            stringBuffer2.append(VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(ag.m21146a(C4731a.m18614a(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
