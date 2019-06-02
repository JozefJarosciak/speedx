package com.alipay.sdk.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.util.C0885l;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.sys.a */
public final class C0869a {
    /* renamed from: a */
    public static final String f2160a = "\"&";
    /* renamed from: b */
    public static final String f2161b = "&";
    /* renamed from: c */
    public static final String f2162c = "bizcontext=\"";
    /* renamed from: d */
    public static final String f2163d = "bizcontext=";
    /* renamed from: e */
    public static final String f2164e = "\"";
    /* renamed from: f */
    public static final String f2165f = "appkey";
    /* renamed from: g */
    public static final String f2166g = "ty";
    /* renamed from: h */
    public static final String f2167h = "sv";
    /* renamed from: i */
    public static final String f2168i = "an";
    /* renamed from: j */
    public static final String f2169j = "setting";
    /* renamed from: k */
    public static final String f2170k = "av";
    /* renamed from: l */
    public static final String f2171l = "sdk_start_time";
    /* renamed from: m */
    public static final String f2172m = "UTF-8";
    /* renamed from: n */
    private String f2173n = "";
    /* renamed from: o */
    private String f2174o = "";
    /* renamed from: p */
    private Context f2175p = null;

    public C0869a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f2173n = packageInfo.versionName;
            this.f2174o = packageInfo.packageName;
            this.f2175p = context.getApplicationContext();
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public final String m3384a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if ((!str.contains(f2160a) ? 1 : null) != null) {
            return m3382c(str);
        }
        return m3383d(str);
    }

    /* renamed from: b */
    private static boolean m3381b(String str) {
        return !str.contains(f2160a);
    }

    /* renamed from: c */
    private String m3382c(String str) {
        try {
            String a = C0869a.m3378a(str, f2161b, f2163d);
            if (TextUtils.isEmpty(a)) {
                return str + f2161b + m3379b(f2163d, "");
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + m3380b(a, f2163d, "") + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    /* renamed from: d */
    private String m3383d(String str) {
        try {
            String a = C0869a.m3378a(str, f2160a, f2162c);
            if (TextUtils.isEmpty(a)) {
                return str + f2161b + m3379b(f2162c, "\"");
            }
            if (!a.endsWith("\"")) {
                a = a + "\"";
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + m3380b(a, f2162c, "\"") + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    /* renamed from: a */
    private static String m3378a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str4;
        String[] split = str.split(str2);
        int i = 0;
        while (i < split.length) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                str4 = split[i];
                break;
            }
            i++;
        }
        str4 = null;
        return str4;
    }

    /* renamed from: b */
    private String m3379b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + m3385a("", "") + str2;
    }

    /* renamed from: a */
    public final String m3385a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", C0844a.f2047c);
            jSONObject.put(f2166g, "and_lite");
            jSONObject.put(f2167h, C0844a.f2050f);
            if (!(this.f2174o.contains(f2169j) && C0885l.m3476c(this.f2175p))) {
                jSONObject.put(f2168i, this.f2174o);
            }
            jSONObject.put(f2170k, this.f2173n);
            jSONObject.put(f2171l, System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: b */
    private String m3380b(String str, String str2, String str3) throws JSONException, UnsupportedEncodingException {
        String substring = str.substring(str2.length());
        JSONObject jSONObject = new JSONObject(substring.substring(0, substring.length() - str3.length()));
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", C0844a.f2047c);
        }
        if (!jSONObject.has(f2166g)) {
            jSONObject.put(f2166g, "and_lite");
        }
        if (!jSONObject.has(f2167h)) {
            jSONObject.put(f2167h, C0844a.f2050f);
        }
        if (!(jSONObject.has(f2168i) || (this.f2174o.contains(f2169j) && C0885l.m3476c(this.f2175p)))) {
            jSONObject.put(f2168i, this.f2174o);
        }
        if (!jSONObject.has(f2170k)) {
            jSONObject.put(f2170k, this.f2173n);
        }
        if (!jSONObject.has(f2171l)) {
            jSONObject.put(f2171l, System.currentTimeMillis());
        }
        return str2 + jSONObject.toString() + str3;
    }
}
