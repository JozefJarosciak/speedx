package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import ch.qos.logback.core.joran.action.Action;
import com.alipay.sdk.cons.C0844a;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.C1472c;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookServiceException;
import com.facebook.internal.C3048s;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.Locale;

abstract class WebLoginMethodHandler extends LoginMethodHandler {
    /* renamed from: c */
    private String f13671c;

    abstract AccessTokenSource f_();

    /* renamed from: e */
    private static final String m14836e() {
        return "fb" + C1472c.i() + "://authorize";
    }

    WebLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    WebLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    /* renamed from: c */
    protected String mo3702c() {
        return null;
    }

    /* renamed from: b */
    protected Bundle m14840b(Request request) {
        String join;
        Bundle bundle = new Bundle();
        if (!C3048s.m14762a(request.m14922a())) {
            join = TextUtils.join(",", request.m14922a());
            bundle.putString(Action.SCOPE_ATTRIBUTE, join);
            m14828a(Action.SCOPE_ATTRIBUTE, join);
        }
        bundle.putString("default_audience", request.m14925c().getNativeProtocolAudience());
        bundle.putString("state", m14826a(request.m14927e()));
        AccessToken a = AccessToken.m14270a();
        join = a != null ? a.m14277b() : null;
        if (join == null || !join.equals(m14837f())) {
            C3048s.m14769b(this.b.m14958b());
            m14828a("access_token", "0");
        } else {
            bundle.putString("access_token", join);
            m14828a("access_token", C0844a.f2048d);
        }
        return bundle;
    }

    /* renamed from: a */
    protected Bundle m14838a(Bundle bundle, Request request) {
        bundle.putString("redirect_uri", m14836e());
        bundle.putString("client_id", request.m14926d());
        LoginClient loginClient = this.b;
        bundle.putString("e2e", LoginClient.m14947m());
        bundle.putString("response_type", "token,signed_request");
        bundle.putString("return_scopes", "true");
        bundle.putString("auth_type", "rerequest");
        if (mo3702c() != null) {
            bundle.putString("sso", mo3702c());
        }
        return bundle;
    }

    /* renamed from: a */
    protected void m14839a(Request request, Bundle bundle, FacebookException facebookException) {
        Result a;
        this.f13671c = null;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                this.f13671c = bundle.getString("e2e");
            }
            try {
                AccessToken a2 = LoginMethodHandler.m14823a(request.m14922a(), bundle, f_(), request.m14926d());
                a = Result.m14934a(this.b.m14961c(), a2);
                CookieSyncManager.createInstance(this.b.m14958b()).sync();
                m14835c(a2.m14277b());
            } catch (FacebookException e) {
                a = Result.m14936a(this.b.m14961c(), null, e.getMessage());
            }
        } else if (facebookException instanceof FacebookOperationCanceledException) {
            a = Result.m14935a(this.b.m14961c(), "User canceled log in.");
        } else {
            String format;
            this.f13671c = null;
            String message = facebookException.getMessage();
            if (facebookException instanceof FacebookServiceException) {
                format = String.format(Locale.ROOT, "%d", new Object[]{Integer.valueOf(((FacebookServiceException) facebookException).getRequestError().m14302b())});
                message = r0.toString();
            } else {
                format = null;
            }
            a = Result.m14937a(this.b.m14961c(), null, message, format);
        }
        if (!C3048s.m14761a(this.f13671c)) {
            m14833b(this.f13671c);
        }
        this.b.m14954a(a);
    }

    /* renamed from: f */
    private String m14837f() {
        return this.b.m14958b().getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).getString("TOKEN", "");
    }

    /* renamed from: c */
    private void m14835c(String str) {
        this.b.m14958b().getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", str).apply();
    }
}
