package com.pingplusplus.android;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PingppDataCollection {
    /* renamed from: a */
    private static String[] f14946a = new String[]{"app_id", "device_id", "enter_time", "sdk_type", OnlineConfigAgent.KEY_SDK_VERSION, "system", "system_version"};
    public String appId;
    public List<String> chIds;
    public Map<String, Integer> channels;
    public String deviceId;
    public Long enterTime;
    public Map<String, String> extra;
    public String firstChannel;
    public Object gps;
    public String ip;
    public String lastChannel;
    public List<Object> nocard;
    public Long quitTime;
    public Integer sdkType;
    public String sdkVersion;
    public String system = "Android";
    public String systemVersion = (Build.MODEL + "," + VERSION.RELEASE);

    public PingppDataCollection(Context context) {
        PingppLog.m16961a("systemVersion=" + this.systemVersion);
        this.sdkVersion = Pingpp.VERSION;
        this.deviceId = C4295l.m17000a(context).m17008b();
        PingppLog.m16961a(this.deviceId);
        this.chIds = new ArrayList();
        this.channels = new HashMap();
        this.enterTime = Long.valueOf(m16952f());
        this.appId = "app_id";
        this.extra = new HashMap();
        this.extra.put("module", "SDK");
        try {
            if (Class.forName("com.jianmi.uexpingpp.EUExPingpp") != null) {
                this.extra.put("module", "AppCan");
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            if (Class.forName("com.pingplusplus.apicloud.ModulePingpp") != null) {
                this.extra.put("module", "APICloud");
            }
        } catch (ClassNotFoundException e2) {
        }
        try {
            if (Class.forName("com.justep.cordova.plugin.pingpp.PingppPlugin") != null) {
                this.extra.put("module", "WeX5");
            }
        } catch (ClassNotFoundException e3) {
        }
    }

    /* renamed from: a */
    private void m16948a(String str) {
        this.chIds.add(str);
    }

    /* renamed from: b */
    private void m16949b(String str) {
        if (this.firstChannel == null) {
            this.firstChannel = str;
        }
        this.lastChannel = str;
        Object valueOf = Integer.valueOf(1);
        if (this.channels.containsKey(str)) {
            valueOf = Integer.valueOf(((Integer) this.channels.get(str)).intValue() + valueOf.intValue());
        }
        this.channels.put(str, valueOf);
    }

    /* renamed from: c */
    private Object m16950c(String str) {
        try {
            return getClass().getDeclaredField(str).get(this);
        } catch (Exception e) {
            PingppLog.m16960a(e);
            return null;
        }
    }

    /* renamed from: e */
    private String m16951e() {
        PingppLog.m16961a(m16958c());
        PingppLog.m16961a(m16957b());
        return C4295l.m17003b(m16958c());
    }

    /* renamed from: f */
    private static long m16952f() {
        return new Date().getTime() / 1000;
    }

    /* renamed from: g */
    private String[] m16953g() {
        Field[] declaredFields = getClass().getDeclaredFields();
        List arrayList = new ArrayList();
        for (Field field : declaredFields) {
            if (Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field.getName());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: a */
    public Map<String, Object> m16954a() {
        Map<String, Object> hashMap = new HashMap();
        for (String str : m16953g()) {
            Object c = m16950c(str);
            if (c != null) {
                hashMap.put(C4295l.m17002a(str), c);
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public void m16955a(C4289f c4289f) {
        this.sdkType = c4289f.f14967c;
    }

    /* renamed from: a */
    public void m16956a(JSONObject jSONObject) {
        String string;
        try {
            m16948a(jSONObject.getString("id"));
            m16949b(jSONObject.getString(OnlineConfigAgent.KEY_CHANNEL));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = null;
        try {
            str = jSONObject.getString("app");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (str == null) {
            try {
                string = jSONObject.getJSONObject("app").getString("id");
            } catch (JSONException e22) {
                e22.printStackTrace();
            }
            if (string != null) {
                this.appId = string;
            }
        }
        string = str;
        if (string != null) {
            this.appId = string;
        }
    }

    /* renamed from: b */
    public String m16957b() {
        return new JSONObject(m16954a()).toString();
    }

    /* renamed from: c */
    public String m16958c() {
        Map a = m16954a();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : f14946a) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(C0869a.f2161b);
            }
            stringBuilder.append(str).append(SimpleComparison.EQUAL_TO_OPERATION).append(a.get(str) == null ? "" : a.get(str));
        }
        return stringBuilder.toString();
    }

    /* renamed from: d */
    public void m16959d() {
        this.quitTime = Long.valueOf(m16952f());
    }

    public void sendToServer() {
        if (this.quitTime == null) {
            m16959d();
        }
        Map hashMap = new HashMap();
        if (m16951e() != null) {
            hashMap.put("X-Pingpp-Report-Token", m16951e());
        }
        List arrayList = new ArrayList();
        arrayList.add(m16954a());
        C4295l.m16999a().m17006a("https://statistics.pingxx.com/report", arrayList, hashMap);
    }
}
