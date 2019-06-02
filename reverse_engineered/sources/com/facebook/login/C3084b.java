package com.facebook.login;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: LoginLogger */
/* renamed from: com.facebook.login.b */
class C3084b {
    /* renamed from: a */
    private final AppEventsLogger f13737a;
    /* renamed from: b */
    private String f13738b;
    /* renamed from: c */
    private String f13739c;

    C3084b(Context context, String str) {
        this.f13738b = str;
        this.f13737a = AppEventsLogger.m14409a(context, str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                PackageInfo packageInfo = packageManager.getPackageInfo("com.facebook.katana", 0);
                if (packageInfo != null) {
                    this.f13739c = packageInfo.versionName;
                }
            }
        } catch (NameNotFoundException e) {
        }
    }

    /* renamed from: a */
    public String m14993a() {
        return this.f13738b;
    }

    /* renamed from: a */
    static Bundle m14992a(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", str);
        bundle.putString("3_method", "");
        bundle.putString("2_result", "");
        bundle.putString("5_error_message", "");
        bundle.putString("4_error_code", "");
        bundle.putString("6_extras", "");
        return bundle;
    }

    /* renamed from: a */
    public void m14994a(String str, String str2) {
        Bundle a = C3084b.m14992a(str);
        a.putString("3_method", str2);
        this.f13737a.m14416a("fb_mobile_login_method_start", null, a);
    }

    /* renamed from: a */
    public void m14996a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        Bundle a = C3084b.m14992a(str);
        if (str3 != null) {
            a.putString("2_result", str3);
        }
        if (str4 != null) {
            a.putString("5_error_message", str4);
        }
        if (str5 != null) {
            a.putString("4_error_code", str5);
        }
        if (!(map == null || map.isEmpty())) {
            a.putString("6_extras", new JSONObject(map).toString());
        }
        a.putString("3_method", str2);
        this.f13737a.m14416a("fb_mobile_login_method_complete", null, a);
    }

    /* renamed from: a */
    public void m14995a(String str, String str2, String str3) {
        Bundle a = C3084b.m14992a("");
        a.putString("2_result", Code.ERROR.m14933a());
        a.putString("5_error_message", str2);
        a.putString("3_method", str3);
        this.f13737a.m14416a(str, null, a);
    }
}
