package com.facebook.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.customtabs.CustomTabsService;
import com.facebook.AccessTokenSource;
import com.facebook.C1472c;
import com.facebook.internal.C3010d;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3048s.C3045b;
import com.facebook.internal.C3049t;
import com.facebook.login.LoginClient.Request;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomTabLoginMethodHandler extends WebLoginMethodHandler {
    public static final Creator<CustomTabLoginMethodHandler> CREATOR = new C30601();
    /* renamed from: c */
    private static final String[] f13672c = new String[]{"com.android.chrome", "com.chrome.beta", "com.chrome.dev"};
    /* renamed from: d */
    private C3010d f13673d;
    /* renamed from: e */
    private String f13674e;

    /* renamed from: com.facebook.login.CustomTabLoginMethodHandler$1 */
    static class C30601 implements Creator {
        C30601() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14820a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14821a(i);
        }

        /* renamed from: a */
        public CustomTabLoginMethodHandler m14820a(Parcel parcel) {
            return new CustomTabLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public CustomTabLoginMethodHandler[] m14821a(int i) {
            return new CustomTabLoginMethodHandler[i];
        }
    }

    CustomTabLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* renamed from: a */
    String mo3699a() {
        return "custom_tab";
    }

    AccessTokenSource f_() {
        return AccessTokenSource.CHROME_CUSTOM_TAB;
    }

    /* renamed from: c */
    protected String mo3702c() {
        return "chrome_custom_tab";
    }

    /* renamed from: a */
    boolean mo3701a(Request request) {
        if (!m14842e()) {
            return false;
        }
        Bundle a = m14838a(m14840b(request), request);
        Activity b = this.b.m14958b();
        this.f13673d = new C3010d("oauth", a);
        this.f13673d.m14560a(b, m14844g());
        return true;
    }

    /* renamed from: a */
    protected void mo3700a(JSONObject jSONObject) throws JSONException {
        if (this.b.m14951a() instanceof LoginFragment) {
            jSONObject.put("7_challenge", ((LoginFragment) this.b.m14951a()).m14977a());
        }
    }

    /* renamed from: e */
    private boolean m14842e() {
        return m14843f() && m14844g() != null && C3049t.m14799c(C1472c.f());
    }

    /* renamed from: f */
    private boolean m14843f() {
        C3045b c = C3048s.m14772c(C3048s.m14732a(this.b.m14958b()));
        return c != null && c.m14716b();
    }

    /* renamed from: g */
    private String m14844g() {
        if (this.f13674e != null) {
            return this.f13674e;
        }
        Context b = this.b.m14958b();
        List<ResolveInfo> queryIntentServices = b.getPackageManager().queryIntentServices(new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION), 0);
        if (queryIntentServices != null) {
            Set hashSet = new HashSet(Arrays.asList(f13672c));
            for (ResolveInfo resolveInfo : queryIntentServices) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null && hashSet.contains(serviceInfo.packageName)) {
                    this.f13674e = serviceInfo.packageName;
                    return this.f13674e;
                }
            }
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    CustomTabLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
