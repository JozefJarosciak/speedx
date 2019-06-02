package com.umeng.onlineconfig;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.util.Iterator;
import org.json.JSONObject;

public class OnlineConfigAgent {
    public static final String KEY_APPKEY = "appkey";
    public static final String KEY_CHANNEL = "channel";
    public static final String KEY_ID = "idmd5";
    public static final String KEY_PACKAGE = "package";
    public static final String KEY_SDK_VERSION = "sdk_version";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VERSION_CODE = "version_code";
    /* renamed from: a */
    private static final String f7156a = "last_config_time";
    /* renamed from: b */
    private static final String f7157b = "online_config";
    /* renamed from: c */
    private static final long f7158c = 600000;
    /* renamed from: e */
    private static OnlineConfigAgent f7159e = null;
    /* renamed from: d */
    private UmengOnlineConfigureListener f7160d = null;
    /* renamed from: f */
    private String f7161f;
    /* renamed from: g */
    private String f7162g;

    private OnlineConfigAgent() {
    }

    public static synchronized OnlineConfigAgent getInstance() {
        OnlineConfigAgent onlineConfigAgent;
        synchronized (OnlineConfigAgent.class) {
            if (f7159e == null) {
                f7159e = new OnlineConfigAgent();
            }
            onlineConfigAgent = f7159e;
        }
        return onlineConfigAgent;
    }

    public void updateOnlineConfig(Context context) {
        if (context == null) {
            try {
                OnlineConfigLog.e(C4766a.f16698a, "unexpected null context in updateOnlineConfig");
                return;
            } catch (Exception e) {
                OnlineConfigLog.e(C4766a.f16698a, "exception in updateOnlineConfig");
                return;
            }
        }
        new Thread(new OnlineConfigAgent$b(this, context.getApplicationContext())).start();
    }

    public void updateOnlineConfig(Context context, String str, String str2) {
        if (context == null) {
            try {
                OnlineConfigLog.e(C4766a.f16698a, "unexpected null context in updateOnlineConfig");
                return;
            } catch (Exception e) {
                OnlineConfigLog.e(C4766a.f16698a, "exception in updateOnlineConfig");
                return;
            }
        }
        this.f7161f = str;
        this.f7162g = str2;
        new Thread(new OnlineConfigAgent$b(this, context.getApplicationContext())).start();
    }

    public void setOnlineConfigListener(UmengOnlineConfigureListener umengOnlineConfigureListener) {
        this.f7160d = umengOnlineConfigureListener;
    }

    public void removeOnlineConfigListener() {
        this.f7160d = null;
    }

    /* renamed from: a */
    private void m8344a(JSONObject jSONObject) {
        if (this.f7160d != null) {
            this.f7160d.onDataReceived(jSONObject);
        }
    }

    /* renamed from: a */
    private long m8338a(Context context) {
        return C4769d.a(context).a().getLong("oc_mdf_told", 0);
    }

    /* renamed from: a */
    private void m8341a(Context context, C4768c c4768c) {
        if (c4768c.f16708a != null && c4768c.f16708a.length() != 0) {
            Editor edit = C4769d.a(context).a().edit();
            try {
                JSONObject jSONObject = c4768c.f16708a;
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    edit.putString(str, jSONObject.getString(str));
                }
                edit.commit();
                OnlineConfigLog.i(C4766a.f16698a, "get online setting params: " + jSONObject);
            } catch (Exception e) {
                OnlineConfigLog.d(C4766a.f16698a, "save online config params", e);
            }
        }
    }

    public void setDebugMode(boolean z) {
        OnlineConfigLog.LOG = z;
    }

    public String getConfigParams(Context context, String str) {
        return C4769d.a(context).a().getString(str, "");
    }
}
