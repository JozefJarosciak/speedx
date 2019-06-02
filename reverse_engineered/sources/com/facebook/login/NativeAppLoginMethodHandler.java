package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.C3040r;
import com.facebook.internal.C3048s;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;

abstract class NativeAppLoginMethodHandler extends LoginMethodHandler {
    /* renamed from: a */
    abstract boolean mo3701a(Request request);

    NativeAppLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    NativeAppLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    /* renamed from: a */
    boolean mo3705a(int i, int i2, Intent intent) {
        Result a;
        Request c = this.b.m14961c();
        if (intent == null) {
            a = Result.m14935a(c, "Operation canceled");
        } else if (i2 == 0) {
            a = m14891b(c, intent);
        } else if (i2 != -1) {
            a = Result.m14936a(c, "Unexpected resultCode from authorization.", null);
        } else {
            a = m14889a(c, intent);
        }
        if (a != null) {
            this.b.m14954a(a);
        } else {
            this.b.m14967i();
        }
        return true;
    }

    /* renamed from: a */
    private Result m14889a(Request request, Intent intent) {
        String str = null;
        Bundle extras = intent.getExtras();
        String a = m14890a(extras);
        String string = extras.getString(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
        String b = m14892b(extras);
        String string2 = extras.getString("e2e");
        if (!C3048s.m14761a(string2)) {
            m14833b(string2);
        }
        if (a == null && string == null && b == null) {
            try {
                return Result.m14934a(request, LoginMethodHandler.m14823a(request.m14922a(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.m14926d()));
            } catch (FacebookException e) {
                return Result.m14936a(request, str, e.getMessage());
            }
        } else if (C3040r.f13611a.contains(a)) {
            return str;
        } else {
            if (C3040r.f13612b.contains(a)) {
                return Result.m14935a(request, str);
            }
            return Result.m14937a(request, a, b, string);
        }
    }

    /* renamed from: b */
    private Result m14891b(Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String a = m14890a(extras);
        String string = extras.getString(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
        if ("CONNECTION_FAILURE".equals(string)) {
            return Result.m14937a(request, a, m14892b(extras), string);
        }
        return Result.m14935a(request, a);
    }

    /* renamed from: a */
    private String m14890a(Bundle bundle) {
        String string = bundle.getString("error");
        if (string == null) {
            return bundle.getString("error_type");
        }
        return string;
    }

    /* renamed from: b */
    private String m14892b(Bundle bundle) {
        String string = bundle.getString("error_message");
        if (string == null) {
            return bundle.getString("error_description");
        }
        return string;
    }

    /* renamed from: a */
    protected boolean m14894a(Intent intent, int i) {
        if (intent == null) {
            return false;
        }
        try {
            this.b.m14951a().startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }
}
