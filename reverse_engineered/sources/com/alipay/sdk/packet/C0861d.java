package com.alipay.sdk.packet;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.widget.TextView;
import cn.sharesdk.BuildConfig;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.data.C0849c;
import com.alipay.sdk.net.C0856a;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.tid.C0872b;
import com.alipay.sdk.util.C0873a;
import com.alipay.sdk.util.C0874b;
import com.alipay.sdk.util.C0880h;
import com.alipay.sdk.util.C0883k;
import com.alipay.sdk.util.C0885l;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.packet.d */
public abstract class C0861d {
    /* renamed from: a */
    public static final String f2129a = "msp-gzip";
    /* renamed from: b */
    public static final String f2130b = "Msp-Param";
    /* renamed from: c */
    public static final String f2131c = "Operation-Type";
    /* renamed from: d */
    public static final String f2132d = "content-type";
    /* renamed from: e */
    public static final String f2133e = "Version";
    /* renamed from: f */
    public static final String f2134f = "AppId";
    /* renamed from: g */
    public static final String f2135g = "des-mode";
    /* renamed from: h */
    public static final String f2136h = "namespace";
    /* renamed from: i */
    public static final String f2137i = "api_name";
    /* renamed from: j */
    public static final String f2138j = "api_version";
    /* renamed from: k */
    public static final String f2139k = "data";
    /* renamed from: l */
    public static final String f2140l = "params";
    /* renamed from: m */
    public static final String f2141m = "public_key";
    /* renamed from: n */
    public static final String f2142n = "device";
    /* renamed from: o */
    public static final String f2143o = "action";
    /* renamed from: p */
    public static final String f2144p = "type";
    /* renamed from: q */
    public static final String f2145q = "method";
    /* renamed from: t */
    private static C0856a f2146t;
    /* renamed from: r */
    protected boolean f2147r = true;
    /* renamed from: s */
    protected boolean f2148s = true;

    /* renamed from: a */
    public abstract JSONObject mo2426a() throws JSONException;

    /* renamed from: a */
    public List<Header> mo2430a(boolean z, String str) {
        List<Header> arrayList = new ArrayList();
        arrayList.add(new BasicHeader(f2129a, String.valueOf(z)));
        arrayList.add(new BasicHeader(f2131c, "alipay.msp.cashier.dispatch.bytes"));
        arrayList.add(new BasicHeader(f2132d, "application/octet-stream"));
        arrayList.add(new BasicHeader(f2133e, BuildConfig.VERSION_NAME));
        arrayList.add(new BasicHeader(f2134f, "TAOBAO"));
        arrayList.add(new BasicHeader(f2130b, C0858a.m3322a(str)));
        arrayList.add(new BasicHeader(f2135g, "CBC"));
        return arrayList;
    }

    /* renamed from: b */
    public String mo2427b() {
        return "4.9.0";
    }

    /* renamed from: c */
    public String mo2431c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put(f2142n, Build.MODEL);
        hashMap.put("namespace", "com.alipay.mobilecashier");
        hashMap.put(f2137i, "com.alipay.mcpay");
        hashMap.put(f2138j, mo2427b());
        return C0861d.m3339a(hashMap, new HashMap());
    }

    /* renamed from: a */
    public static JSONObject m3341a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put(f2145q, str2);
        jSONObject.put(f2143o, jSONObject2);
        return jSONObject;
    }

    /* renamed from: a */
    public String mo2429a(String str, JSONObject jSONObject) {
        C0870b a = C0870b.m3386a();
        C0872b a2 = C0872b.m3405a();
        JSONObject a3 = C0874b.m3424a(new JSONObject(), jSONObject);
        try {
            String e;
            String a4;
            Object c;
            a3.put(C0845b.f2062c, a2.f2182a);
            String str2 = C0845b.f2061b;
            C0849c a5 = C0849c.m3265a();
            Context context = C0870b.m3386a().f2177a;
            C0873a a6 = C0873a.m3414a(context);
            if (TextUtils.isEmpty(a5.f2101a)) {
                String a7 = C0885l.m3462a();
                String b = C0885l.m3471b();
                e = C0885l.m3481e(context);
                a4 = C0883k.m3457a(context);
                a5.f2101a = "Msp/15.2.2" + " (" + a7 + C0880h.f2220b + b + C0880h.f2220b + e + C0880h.f2220b + a4.substring(0, a4.indexOf("://")) + C0880h.f2220b + C0885l.m3482f(context) + C0880h.f2220b + Float.toString(new TextView(context).getTextSize());
            }
            e = C0873a.m3416b(context).f2207p;
            a4 = "-1;-1";
            String str3 = C0844a.f2048d;
            String a8 = a6.m3422a();
            String b2 = a6.m3423b();
            Context context2 = C0870b.m3386a().f2177a;
            SharedPreferences sharedPreferences = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            CharSequence string = sharedPreferences.getString("virtual_imsi", null);
            if (TextUtils.isEmpty(string)) {
                if (TextUtils.isEmpty(C0872b.m3405a().f2182a)) {
                    c = C0870b.m3386a().m3392c();
                    string = TextUtils.isEmpty(c) ? C0849c.m3269b() : c.substring(3, 18);
                } else {
                    string = C0873a.m3414a(context2).m3422a();
                }
                sharedPreferences.edit().putString("virtual_imsi", string).commit();
            }
            CharSequence charSequence = string;
            context2 = C0870b.m3386a().f2177a;
            SharedPreferences sharedPreferences2 = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            string = sharedPreferences2.getString("virtual_imei", null);
            if (TextUtils.isEmpty(string)) {
                string = TextUtils.isEmpty(C0872b.m3405a().f2182a) ? C0849c.m3269b() : C0873a.m3414a(context2).m3423b();
                sharedPreferences2.edit().putString("virtual_imei", string).commit();
            }
            CharSequence charSequence2 = string;
            if (a2 != null) {
                a5.f2103c = a2.f2183b;
            }
            String replace = Build.MANUFACTURER.replace(C0880h.f2220b, " ");
            String replace2 = Build.MODEL.replace(C0880h.f2220b, " ");
            boolean b3 = C0870b.m3388b();
            String str4 = a6.f2186a;
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo();
            String ssid = connectionInfo != null ? connectionInfo.getSSID() : "-1";
            connectionInfo = ((WifiManager) context.getSystemService(MapboxEvent.ATTRIBUTE_WIFI)).getConnectionInfo();
            String bssid = connectionInfo != null ? connectionInfo.getBSSID() : "00";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a5.f2101a).append(C0880h.f2220b).append(e).append(C0880h.f2220b).append(a4).append(C0880h.f2220b).append(str3).append(C0880h.f2220b).append(a8).append(C0880h.f2220b).append(b2).append(C0880h.f2220b).append(a5.f2103c).append(C0880h.f2220b).append(replace).append(C0880h.f2220b).append(replace2).append(C0880h.f2220b).append(b3).append(C0880h.f2220b).append(str4).append(";-1;-1;").append(a5.f2102b).append(C0880h.f2220b).append(charSequence).append(C0880h.f2220b).append(charSequence2).append(C0880h.f2220b).append(ssid).append(C0880h.f2220b).append(bssid);
            if (a2 != null) {
                HashMap hashMap = new HashMap();
                hashMap.put(C0845b.f2062c, a2.f2182a);
                hashMap.put(C0845b.f2066g, C0870b.m3386a().m3392c());
                c = a5.m3279b(context, hashMap);
                if (!TextUtils.isEmpty(c)) {
                    stringBuilder.append(C0880h.f2220b).append(c);
                }
            }
            stringBuilder.append(")");
            a3.put(str2, stringBuilder.toString());
            a3.put(C0845b.f2064e, C0885l.m3472b(a.f2177a));
            a3.put(C0845b.f2065f, C0885l.m3466a(a.f2177a));
            a3.put(C0845b.f2063d, str);
            a3.put(C0845b.f2067h, C0844a.f2047c);
            a3.put(C0845b.f2066g, a.m3392c());
            a3.put(C0845b.f2069j, a2.f2183b);
        } catch (Throwable th) {
        }
        return a3.toString();
    }

    /* renamed from: a */
    private static boolean m3343a(HttpResponse httpResponse) {
        String str = null;
        String str2 = f2129a;
        if (httpResponse != null) {
            Header[] allHeaders = httpResponse.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                for (Header header : allHeaders) {
                    if (header != null) {
                        String name = header.getName();
                        if (name != null && name.equalsIgnoreCase(str2)) {
                            str = header.getValue();
                            break;
                        }
                    }
                }
            }
        }
        return Boolean.valueOf(str).booleanValue();
    }

    /* renamed from: a */
    private static String m3340a(HttpResponse httpResponse, String str) {
        if (httpResponse == null) {
            return null;
        }
        Header[] allHeaders = httpResponse.getAllHeaders();
        if (allHeaders == null || allHeaders.length <= 0) {
            return null;
        }
        for (Header header : allHeaders) {
            if (header != null) {
                String name = header.getName();
                if (name != null && name.equalsIgnoreCase(str)) {
                    return header.getValue();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m3339a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Entry entry : hashMap.entrySet()) {
            jSONObject2.put((String) entry.getKey(), entry.getValue());
        }
        JSONObject jSONObject3 = new JSONObject();
        for (Entry entry2 : hashMap2.entrySet()) {
            jSONObject3.put((String) entry2.getKey(), entry2.getValue());
        }
        jSONObject2.put("params", jSONObject3);
        jSONObject.put(f2139k, jSONObject2);
        return jSONObject.toString();
    }

    /* renamed from: a */
    private static boolean m3342a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(f2139k);
            if (!jSONObject.has("params")) {
                return false;
            }
            String optString = jSONObject.getJSONObject("params").optString(f2141m, null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            C0870b.m3386a();
            C0849c.m3265a().m3278a(optString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    /* renamed from: b */
    private static C0856a m3344b(Context context, String str) {
        if (f2146t == null) {
            f2146t = new C0856a(context, str);
        } else if (!TextUtils.equals(str, f2146t.f2120b)) {
            f2146t.f2120b = str;
        }
        return f2146t;
    }

    /* renamed from: a */
    private C0859b m3337a(Context context) throws Throwable {
        return m3347a(context, "", C0883k.m3457a(context), true);
    }

    /* renamed from: a */
    public C0859b mo2428a(Context context, String str) throws Throwable {
        return m3347a(context, str, C0883k.m3457a(context), true);
    }

    /* renamed from: a */
    private C0859b m3338a(Context context, String str, String str2) throws Throwable {
        return m3347a(context, str, str2, true);
    }

    /* renamed from: a */
    public final C0859b m3347a(Context context, String str, String str2, boolean z) throws Throwable {
        String str3 = null;
        C0862e c0862e = new C0862e(this.f2148s);
        C0860c a = c0862e.m3360a(new C0859b(mo2431c(), mo2429a(str, mo2426a())), this.f2147r);
        if (f2146t == null) {
            f2146t = new C0856a(context, str2);
        } else if (!TextUtils.equals(str2, f2146t.f2120b)) {
            f2146t.f2120b = str2;
        }
        HttpResponse a2 = f2146t.m3306a(a.f2128b, mo2430a(a.f2127a, str));
        String str4 = f2129a;
        if (a2 != null) {
            Header[] allHeaders = a2.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                for (Header header : allHeaders) {
                    if (header != null) {
                        String name = header.getName();
                        if (name != null && name.equalsIgnoreCase(str4)) {
                            str3 = header.getValue();
                            break;
                        }
                    }
                }
            }
        }
        C0859b a3 = c0862e.m3359a(new C0860c(Boolean.valueOf(str3).booleanValue(), C0861d.m3345b(a2)));
        if (a3 != null && C0861d.m3342a(a3.f2125a) && z) {
            return m3347a(context, str, str2, false);
        }
        return a3;
    }

    /* renamed from: b */
    private static byte[] m3345b(HttpResponse httpResponse) throws IllegalStateException, IOException {
        InputStream content;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bArr = new byte[1024];
        try {
            content = httpResponse.getEntity().getContent();
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = content.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, read);
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream = byteArrayOutputStream2;
                    }
                }
                bArr = byteArrayOutputStream2.toByteArray();
                if (content != null) {
                    try {
                        content.close();
                    } catch (Exception e) {
                    }
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception e2) {
                }
                return bArr;
            } catch (Throwable th3) {
                th = th3;
                if (content != null) {
                    try {
                        content.close();
                    } catch (Exception e3) {
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            content = null;
            if (content != null) {
                content.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }
}
