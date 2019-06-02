package com.pingplusplus.android;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.alipay.sdk.cons.C0845b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.pingplusplus.android.crypto.CryptoUtils;
import io.rong.imlib.statistics.UserData;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.pingplusplus.android.l */
public class C4295l {
    /* renamed from: e */
    private static Context f14977e;
    /* renamed from: a */
    String f14978a;
    /* renamed from: b */
    String f14979b;
    /* renamed from: c */
    String f14980c;
    /* renamed from: d */
    String f14981d;

    public C4295l(Context context) {
        if (context != null) {
            m17009b(context);
        }
    }

    /* renamed from: a */
    public static C4294k m16998a(C4297n c4297n) {
        URL url;
        OutputStream outputStream;
        Throwable th;
        String str = c4297n.f14982a;
        Object obj = c4297n.f14983b;
        String str2 = c4297n.f14984c;
        Map map = c4297n.f14985d;
        try {
            url = new URL(str);
        } catch (Exception e) {
            PingppLog.m16960a(e);
            url = null;
        }
        if (url == null) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection = url.getProtocol().equals(C0845b.f2060a) ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();
        } catch (Exception e2) {
            PingppLog.m16960a(e2);
            httpURLConnection = null;
        }
        if (httpURLConnection == null) {
            return null;
        }
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setUseCaches(false);
        for (Entry entry : C4295l.m17005d().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        if (map != null) {
            for (Entry entry2 : map.entrySet()) {
                httpURLConnection.setRequestProperty((String) entry2.getKey(), (String) entry2.getValue());
            }
        }
        C4294k c4294k;
        try {
            httpURLConnection.setRequestMethod(str2);
            if (str2.equals("POST")) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                String jSONObject = obj instanceof String ? (String) obj : obj instanceof Map ? new JSONObject((Map) obj).toString() : obj instanceof List ? new JSONArray((List) obj).toString() : "{}";
                try {
                    outputStream = httpURLConnection.getOutputStream();
                    try {
                        outputStream.write(jSONObject.getBytes("UTF-8"));
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            }
            int responseCode = httpURLConnection.getResponseCode();
            str = (responseCode < 200 || responseCode >= 300) ? C4295l.m17001a(httpURLConnection.getErrorStream()) : C4295l.m17001a(httpURLConnection.getInputStream());
            c4294k = new C4294k(responseCode, str, httpURLConnection.getHeaderFields(), c4297n.f14986e);
            return c4294k;
        } catch (IOException e3) {
            c4294k = e3;
            PingppLog.m16960a((Exception) c4294k);
            return null;
        } finally {
            httpURLConnection.disconnect();
        }
    }

    /* renamed from: a */
    public static C4295l m16999a() {
        return C4299p.f14989a;
    }

    /* renamed from: a */
    public static C4295l m17000a(Context context) {
        C4295l a = C4299p.f14989a;
        if (f14977e == null) {
            f14977e = context.getApplicationContext();
            a.m17009b(f14977e);
        }
        return a;
    }

    /* renamed from: a */
    private static String m17001a(InputStream inputStream) {
        String next = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
        inputStream.close();
        return next;
    }

    /* renamed from: a */
    public static String m17002a(String str) {
        int i = 1;
        if (str == null || str.equals("")) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (str.length() > 0) {
            stringBuilder.append(str.substring(0, 1).toLowerCase());
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (Character.isUpperCase(charAt)) {
                    stringBuilder.append("_");
                    stringBuilder.append(Character.toLowerCase(charAt));
                } else {
                    stringBuilder.append(charAt);
                }
                i++;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static String m17003b(String str) {
        String str2 = null;
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(i));
            }
            str2 = stringBuilder.toString();
        } catch (Exception e) {
            PingppLog.m16960a(e);
        } catch (Exception e2) {
            PingppLog.m16960a(e2);
        }
        return str2;
    }

    /* renamed from: d */
    private static Map<String, String> m17005d() {
        Map<String, String> hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        hashMap2.put("bindings_version", Pingpp.VERSION);
        hashMap2.put("lang", "Java");
        hashMap2.put("os_version", VERSION.RELEASE);
        hashMap2.put(MapboxEvent.ATTRIBUTE_MODEL, Build.MODEL);
        hashMap.put("X-Pingpp-User-Agent", new JSONObject(hashMap2).toString());
        return hashMap;
    }

    /* renamed from: a */
    public void m17006a(String str, Object obj, Map<String, String> map) {
        m17007a(str, obj, map, null);
    }

    /* renamed from: a */
    public void m17007a(String str, Object obj, Map<String, String> map, C4290g c4290g) {
        C4298o c4298o = new C4298o();
        C4297n[] c4297nArr = new C4297n[1];
        c4297nArr[0] = new C4297n(this, str, obj, "POST", map, c4290g);
        c4298o.execute(c4297nArr);
    }

    /* renamed from: b */
    public String m17008b() {
        String str = "6_";
        String str2 = "7_";
        String str3 = "8_";
        String str4 = "9_";
        String str5 = "10_";
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f14978a != null) {
            stringBuilder.append(str).append(this.f14978a);
        }
        if (this.f14979b != null) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("|");
            }
            stringBuilder.append(str2).append(this.f14979b);
        }
        if (this.f14980c != null) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("|");
            }
            stringBuilder.append(str3).append(this.f14980c);
        }
        if (this.f14981d != null) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("|");
            }
            stringBuilder.append(str4).append(this.f14981d);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        str2 = CryptoUtils.getRandomString(16);
        str3 = CryptoUtils.getRandomString(16);
        stringBuilder2.append(str5).append("PE_v2").append("$").append(str2 + str3).append(Pingpp.encryptData(str2, str3, stringBuilder.toString()));
        return stringBuilder2.toString();
    }

    /* renamed from: b */
    public void m17009b(Context context) {
        String simSerialNumber;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
        try {
            String deviceId = telephonyManager.getDeviceId();
            if (deviceId != null) {
                this.f14978a = deviceId;
            }
        } catch (Exception e) {
            PingppLog.m16961a(e.getMessage());
        }
        try {
            simSerialNumber = telephonyManager.getSimSerialNumber();
            if (simSerialNumber != null) {
                this.f14980c = simSerialNumber;
            }
        } catch (Exception e2) {
            PingppLog.m16961a(e2.getMessage());
        }
        try {
            simSerialNumber = Secure.getString(context.getContentResolver(), "android_id");
            if (!(simSerialNumber == null || "9774d56d682e549c".equals(simSerialNumber))) {
                this.f14979b = simSerialNumber;
            }
        } catch (Exception e22) {
            PingppLog.m16961a(e22.getMessage());
        }
        try {
            simSerialNumber = Build.SERIAL;
            if (simSerialNumber != null) {
                this.f14981d = simSerialNumber;
            }
        } catch (Exception e222) {
            PingppLog.m16961a(e222.getMessage());
        }
    }
}
