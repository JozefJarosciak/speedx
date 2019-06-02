package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.AccessTokenSource;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.Collections;
import java.util.Set;

/* compiled from: LoginManager */
/* renamed from: com.facebook.login.c */
public class C3085c {
    /* renamed from: a */
    private static final Set<String> f13740a = C3085c.m14997a();

    /* renamed from: a */
    static boolean m14999a(String str) {
        return str != null && (str.startsWith("publish") || str.startsWith("manage") || f13740a.contains(str));
    }

    /* renamed from: a */
    private static Set<String> m14997a() {
        return Collections.unmodifiableSet(new LoginManager$2());
    }

    /* renamed from: a */
    public static void m14998a(Intent intent, Bundle bundle) {
        Request request = (Request) intent.getBundleExtra("com.facebook.LoginFragment:Request").getParcelable("request");
        intent.putExtra("com.facebook.LoginFragment:Result", Result.m14934a(request, LoginMethodHandler.m14823a(request.m14922a(), bundle, AccessTokenSource.CHROME_CUSTOM_TAB, request.m14926d())));
    }
}
