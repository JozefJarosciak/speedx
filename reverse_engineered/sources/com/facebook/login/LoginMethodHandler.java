package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.C3048s;
import com.facebook.login.LoginClient.Request;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

abstract class LoginMethodHandler implements Parcelable {
    /* renamed from: a */
    Map<String, String> f13669a;
    /* renamed from: b */
    protected LoginClient f13670b;

    /* renamed from: a */
    abstract String mo3699a();

    /* renamed from: a */
    abstract boolean mo3701a(Request request);

    LoginMethodHandler(LoginClient loginClient) {
        this.f13670b = loginClient;
    }

    LoginMethodHandler(Parcel parcel) {
        this.f13669a = C3048s.m14744a(parcel);
    }

    /* renamed from: a */
    void m14827a(LoginClient loginClient) {
        if (this.f13670b != null) {
            throw new FacebookException("Can't set LoginClient if it is already set.");
        }
        this.f13670b = loginClient;
    }

    /* renamed from: a */
    boolean mo3705a(int i, int i2, Intent intent) {
        return false;
    }

    /* renamed from: d */
    boolean mo3712d() {
        return false;
    }

    /* renamed from: b */
    void mo3707b() {
    }

    /* renamed from: a */
    void mo3700a(JSONObject jSONObject) throws JSONException {
    }

    /* renamed from: a */
    protected String m14826a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0_auth_logger_id", str);
            jSONObject.put("3_method", mo3699a());
            mo3700a(jSONObject);
        } catch (JSONException e) {
            Log.w("LoginMethodHandler", "Error creating client state json: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    protected void m14828a(String str, Object obj) {
        if (this.f13669a == null) {
            this.f13669a = new HashMap();
        }
        this.f13669a.put(str, obj == null ? null : obj.toString());
    }

    /* renamed from: b */
    protected void m14833b(String str) {
        String d = this.f13670b.m14961c().m14926d();
        AppEventsLogger a = AppEventsLogger.m14409a(this.f13670b.m14958b(), d);
        Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", str);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", d);
        a.m14416a("fb_dialogs_web_login_dialog_complete", null, bundle);
    }

    /* renamed from: a */
    static AccessToken m14822a(Bundle bundle, AccessTokenSource accessTokenSource, String str) {
        Date a = C3048s.m14741a(bundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0));
        Collection stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
        String string = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
        if (C3048s.m14761a(string)) {
            return null;
        }
        return new AccessToken(string, str, bundle.getString("com.facebook.platform.extra.USER_ID"), stringArrayList, null, accessTokenSource, a, new Date());
    }

    /* renamed from: a */
    public static AccessToken m14823a(Collection<String> collection, Bundle bundle, AccessTokenSource accessTokenSource, String str) throws FacebookException {
        Collection collection2;
        Date a = C3048s.m14741a(bundle, "expires_in", new Date());
        String string = bundle.getString("access_token");
        String string2 = bundle.getString("granted_scopes");
        if (C3048s.m14761a(string2)) {
            Collection<String> collection3 = collection;
        } else {
            Collection arrayList = new ArrayList(Arrays.asList(string2.split(",")));
        }
        string2 = bundle.getString("denied_scopes");
        if (C3048s.m14761a(string2)) {
            collection2 = null;
        } else {
            collection2 = new ArrayList(Arrays.asList(string2.split(",")));
        }
        if (C3048s.m14761a(string)) {
            return null;
        }
        return new AccessToken(string, str, m14824c(bundle.getString("signed_request")), arrayList, collection2, accessTokenSource, a, new Date());
    }

    /* renamed from: c */
    private static String m14824c(String str) throws FacebookException {
        if (str == null || str.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            String[] split = str.split("\\.");
            if (split.length == 2) {
                return new JSONObject(new String(Base64.decode(split[1], 0), "UTF-8")).getString("user_id");
            }
        } catch (UnsupportedEncodingException e) {
        } catch (JSONException e2) {
        }
        throw new FacebookException("Failed to retrieve user_id from signed_request");
    }

    public void writeToParcel(Parcel parcel, int i) {
        C3048s.m14749a(parcel, this.f13669a);
    }
}
