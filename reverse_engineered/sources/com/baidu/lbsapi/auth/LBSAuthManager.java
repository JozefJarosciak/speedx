package com.baidu.lbsapi.auth;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0844a;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class LBSAuthManager {
    public static final int CODE_AUTHENTICATE_SUCC = 0;
    public static final int CODE_AUTHENTICATING = 602;
    public static final int CODE_INNER_ERROR = -1;
    public static final int CODE_KEY_NOT_EXIST = 101;
    public static final int CODE_NETWORK_FAILED = -11;
    public static final int CODE_NETWORK_INVALID = -10;
    public static final int CODE_UNAUTHENTICATE = 601;
    public static final String VERSION = "1.0.20";
    /* renamed from: a */
    private static Context f2265a;
    /* renamed from: d */
    private static C1031l f2266d = null;
    /* renamed from: e */
    private static int f2267e = 0;
    /* renamed from: f */
    private static Hashtable<String, LBSAuthManagerListener> f2268f = new Hashtable();
    /* renamed from: g */
    private static LBSAuthManager f2269g;
    /* renamed from: b */
    private C1021c f2270b = null;
    /* renamed from: c */
    private C1024e f2271c = null;
    /* renamed from: h */
    private final Handler f2272h = new C1027h(this, Looper.getMainLooper());

    private LBSAuthManager(Context context) {
        f2265a = context;
        if (!(f2266d == null || f2266d.isAlive())) {
            f2266d = null;
        }
        C1017a.m3590b("BaiduApiAuth SDK Version:1.0.20");
        m3586d();
    }

    /* renamed from: a */
    private int m3570a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
            int i = jSONObject.getInt("status");
            if (jSONObject.has("current") && i == 0) {
                long j = jSONObject.getLong("current");
                long currentTimeMillis = System.currentTimeMillis();
                if (((double) (currentTimeMillis - j)) / 3600000.0d >= 24.0d) {
                    i = CODE_UNAUTHENTICATE;
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (!simpleDateFormat.format(Long.valueOf(currentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(j)))) {
                        i = CODE_UNAUTHENTICATE;
                    }
                }
            }
            if (jSONObject.has("current") && i == CODE_AUTHENTICATING) {
                if (((double) ((System.currentTimeMillis() - jSONObject.getLong("current")) / 1000)) > 180.0d) {
                    return CODE_UNAUTHENTICATE;
                }
            }
            return i;
        } catch (JSONException e) {
            JSONException jSONException = e;
            int i2 = -1;
            jSONException.printStackTrace();
            return i2;
        }
    }

    /* renamed from: a */
    private String m3571a(int i) throws IOException {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        Object obj;
        Object obj2;
        BufferedReader bufferedReader;
        Throwable th;
        Throwable th2;
        String str = null;
        try {
            fileInputStream = new FileInputStream(new File("/proc/" + i + "/cmdline"));
            try {
                inputStreamReader = new InputStreamReader(fileInputStream);
            } catch (FileNotFoundException e) {
                obj = str;
                obj2 = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (IOException e2) {
                obj = str;
                obj2 = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (Throwable th3) {
                obj2 = str;
                String str2 = str;
                th = th3;
                obj = str2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                try {
                    str = bufferedReader.readLine();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (FileNotFoundException e3) {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                } catch (IOException e4) {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                } catch (Throwable th4) {
                    th = th4;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                obj = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (IOException e6) {
                obj = str;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            } catch (Throwable th32) {
                th2 = th32;
                obj = str;
                th = th2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e7) {
            bufferedReader = str;
            inputStreamReader = str;
            fileInputStream = str;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (IOException e8) {
            bufferedReader = str;
            inputStreamReader = str;
            fileInputStream = str;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable th322) {
            inputStreamReader = str;
            fileInputStream = str;
            th2 = th322;
            bufferedReader = str;
            th = th2;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    /* renamed from: a */
    private String m3572a(Context context) {
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        String str = null;
        try {
            str = m3571a(myPid);
        } catch (IOException e) {
        }
        return str == null ? f2265a.getPackageName() : str;
    }

    /* renamed from: a */
    private String m3573a(Context context, String str) {
        LBSAuthManagerListener lBSAuthManagerListener;
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                lBSAuthManagerListener = (LBSAuthManagerListener) f2268f.get(str);
                if (lBSAuthManagerListener != null) {
                    lBSAuthManagerListener.onAuthResult(101, ErrorMessage.m3568a(101, "AndroidManifest.xml的application中没有meta-data标签"));
                }
                return str2;
            }
            str2 = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
            if (str2 == null || str2.equals("")) {
                lBSAuthManagerListener = (LBSAuthManagerListener) f2268f.get(str);
                if (lBSAuthManagerListener != null) {
                    lBSAuthManagerListener.onAuthResult(101, ErrorMessage.m3568a(101, "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
                }
            }
            return str2;
        } catch (NameNotFoundException e) {
            lBSAuthManagerListener = (LBSAuthManagerListener) f2268f.get(str);
            if (lBSAuthManagerListener != null) {
                lBSAuthManagerListener.onAuthResult(101, ErrorMessage.m3568a(101, "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
            }
        }
    }

    /* renamed from: a */
    private synchronized void m3578a(String str, String str2) {
        if (str == null) {
            str = m3587e();
        }
        Message obtainMessage = this.f2272h.obtainMessage();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
            if (!jSONObject.has("current")) {
                jSONObject.put("current", System.currentTimeMillis());
            }
            m3585c(jSONObject.toString());
            if (jSONObject.has("current")) {
                jSONObject.remove("current");
            }
            obtainMessage.what = jSONObject.getInt("status");
            obtainMessage.obj = jSONObject.toString();
            Bundle bundle = new Bundle();
            bundle.putString("listenerKey", str2);
            obtainMessage.setData(bundle);
            this.f2272h.sendMessage(obtainMessage);
        } catch (JSONException e) {
            e.printStackTrace();
            obtainMessage.what = -1;
            obtainMessage.obj = new JSONObject();
            bundle = new Bundle();
            bundle.putString("listenerKey", str2);
            obtainMessage.setData(bundle);
            this.f2272h.sendMessage(obtainMessage);
        }
        f2266d.m3630c();
        f2267e--;
        if (C1017a.f2273a) {
            C1017a.m3589a("httpRequest called mAuthCounter-- = " + f2267e);
        }
        if (f2267e == 0) {
            f2266d.m3628a();
            if (f2266d != null) {
                f2266d = null;
            }
        }
    }

    /* renamed from: a */
    private void m3579a(boolean z, String str, Hashtable<String, String> hashtable, String str2) {
        String a = m3573a(f2265a, str2);
        if (a != null && !a.equals("")) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
            C1017a.m3589a("url:https://api.map.baidu.com/sdkcs/verify");
            hashMap.put("output", "json");
            hashMap.put("ak", a);
            C1017a.m3589a("ak:" + a);
            hashMap.put("mcode", C1019b.m3594a(f2265a));
            hashMap.put("from", "lbs_yunsdk");
            if (hashtable != null && hashtable.size() > 0) {
                for (Entry entry : hashtable.entrySet()) {
                    String str3 = (String) entry.getKey();
                    a = (String) entry.getValue();
                    if (!(TextUtils.isEmpty(str3) || TextUtils.isEmpty(a))) {
                        hashMap.put(str3, a);
                    }
                }
            }
            CharSequence charSequence = "";
            try {
                charSequence = CommonParam.m3534a(f2265a);
            } catch (Exception e) {
            }
            C1017a.m3589a("cuid:" + charSequence);
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("cuid", "");
            } else {
                hashMap.put("cuid", charSequence);
            }
            hashMap.put("pcn", f2265a.getPackageName());
            hashMap.put(MapboxEvent.ATTRIBUTE_VERSION, VERSION);
            charSequence = "";
            try {
                charSequence = C1019b.m3600c(f2265a);
            } catch (Exception e2) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("macaddr", "");
            } else {
                hashMap.put("macaddr", charSequence);
            }
            charSequence = "";
            try {
                charSequence = C1019b.m3593a();
            } catch (Exception e3) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("language", "");
            } else {
                hashMap.put("language", charSequence);
            }
            if (z) {
                hashMap.put("force", z ? C0844a.f2048d : "0");
            }
            if (str == null) {
                hashMap.put("from_service", "");
            } else {
                hashMap.put("from_service", str);
            }
            this.f2270b = new C1021c(f2265a);
            this.f2270b.m3609a(hashMap, new C1029j(this, str2));
        }
    }

    /* renamed from: a */
    private void m3580a(boolean z, String str, Hashtable<String, String> hashtable, String[] strArr, String str2) {
        String a = m3573a(f2265a, str2);
        if (a != null && !a.equals("")) {
            HashMap hashMap = new HashMap();
            hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
            hashMap.put("output", "json");
            hashMap.put("ak", a);
            hashMap.put("from", "lbs_yunsdk");
            if (hashtable != null && hashtable.size() > 0) {
                for (Entry entry : hashtable.entrySet()) {
                    String str3 = (String) entry.getKey();
                    a = (String) entry.getValue();
                    if (!(TextUtils.isEmpty(str3) || TextUtils.isEmpty(a))) {
                        hashMap.put(str3, a);
                    }
                }
            }
            CharSequence charSequence = "";
            try {
                charSequence = CommonParam.m3534a(f2265a);
            } catch (Exception e) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("cuid", "");
            } else {
                hashMap.put("cuid", charSequence);
            }
            hashMap.put("pcn", f2265a.getPackageName());
            hashMap.put(MapboxEvent.ATTRIBUTE_VERSION, VERSION);
            charSequence = "";
            try {
                charSequence = C1019b.m3600c(f2265a);
            } catch (Exception e2) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("macaddr", "");
            } else {
                hashMap.put("macaddr", charSequence);
            }
            charSequence = "";
            try {
                charSequence = C1019b.m3593a();
            } catch (Exception e3) {
            }
            if (TextUtils.isEmpty(charSequence)) {
                hashMap.put("language", "");
            } else {
                hashMap.put("language", charSequence);
            }
            if (z) {
                hashMap.put("force", z ? C0844a.f2048d : "0");
            }
            if (str == null) {
                hashMap.put("from_service", "");
            } else {
                hashMap.put("from_service", str);
            }
            this.f2271c = new C1024e(f2265a);
            this.f2271c.m3616a(hashMap, strArr, new C1030k(this, str2));
        }
    }

    /* renamed from: b */
    private boolean m3583b(String str) {
        String a = m3573a(f2265a, str);
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(m3587e());
            if (!jSONObject.has("ak")) {
                return true;
            }
            Object string = jSONObject.getString("ak");
            return (a == null || string == null || a.equals(string)) ? false : true;
        } catch (JSONException e) {
            e.printStackTrace();
            String str3 = str2;
        }
    }

    /* renamed from: c */
    private void m3585c(String str) {
        f2265a.getSharedPreferences("authStatus_" + m3572a(f2265a), 0).edit().putString("status", str).commit();
    }

    /* renamed from: d */
    private void m3586d() {
        synchronized (LBSAuthManager.class) {
            if (f2266d == null) {
                f2266d = new C1031l(C0825c.f1954d);
                f2266d.start();
                while (f2266d.f2298a == null) {
                    try {
                        if (C1017a.f2273a) {
                            C1017a.m3589a("wait for create auth thread.");
                        }
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: e */
    private String m3587e() {
        return f2265a.getSharedPreferences("authStatus_" + m3572a(f2265a), 0).getString("status", "{\"status\":601}");
    }

    public static LBSAuthManager getInstance(Context context) {
        if (f2269g == null) {
            synchronized (LBSAuthManager.class) {
                if (f2269g == null) {
                    f2269g = new LBSAuthManager(context);
                }
            }
        } else if (context != null) {
            f2265a = context;
        } else if (C1017a.f2273a) {
            C1017a.m3591c("input context is null");
            new RuntimeException("here").printStackTrace();
        }
        return f2269g;
    }

    public int authenticate(boolean z, String str, Hashtable<String, String> hashtable, LBSAuthManagerListener lBSAuthManagerListener) {
        int i;
        synchronized (LBSAuthManager.class) {
            String str2 = System.currentTimeMillis() + "";
            if (lBSAuthManagerListener != null) {
                f2268f.put(str2, lBSAuthManagerListener);
            }
            String a = m3573a(f2265a, str2);
            if (a == null || a.equals("")) {
                i = 101;
            } else {
                f2267e++;
                if (C1017a.f2273a) {
                    C1017a.m3589a(" mAuthCounter  ++ = " + f2267e);
                }
                a = m3587e();
                if (C1017a.f2273a) {
                    C1017a.m3589a("getAuthMessage from cache:" + a);
                }
                i = m3570a(a);
                if (i == CODE_UNAUTHENTICATE) {
                    try {
                        m3585c(new JSONObject().put("status", CODE_AUTHENTICATING).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                m3586d();
                if (C1017a.f2273a) {
                    C1017a.m3589a("mThreadLooper.mHandler = " + f2266d.f2298a);
                }
                if (f2266d == null || f2266d.f2298a == null) {
                    i = -1;
                } else {
                    f2266d.f2298a.post(new C1028i(this, i, z, str2, str, hashtable));
                }
            }
        }
        return i;
    }

    public String getCUID() {
        if (f2265a == null) {
            return "";
        }
        String str = "";
        try {
            return CommonParam.m3534a(f2265a);
        } catch (Exception e) {
            if (!C1017a.f2273a) {
                return str;
            }
            e.printStackTrace();
            return str;
        }
    }

    public String getKey() {
        if (f2265a == null) {
            return "";
        }
        try {
            return getPublicKey(f2265a);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getMCode() {
        return f2265a == null ? "" : C1019b.m3594a(f2265a);
    }

    public String getPublicKey(Context context) throws NameNotFoundException {
        String str = "";
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.baidu.lbsapi.API_KEY");
    }
}
