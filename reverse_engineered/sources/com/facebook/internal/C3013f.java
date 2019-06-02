package com.facebook.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.alipay.sdk.packet.C0861d;
import com.facebook.C1472c;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.internal.C3048s.C3044a;

/* compiled from: DialogPresenter */
/* renamed from: com.facebook.internal.f */
public class C3013f {

    /* compiled from: DialogPresenter */
    /* renamed from: com.facebook.internal.f$a */
    public interface C3012a {
        /* renamed from: a */
        Bundle mo3728a();

        /* renamed from: b */
        Bundle mo3729b();
    }

    /* renamed from: a */
    public static void m14563a(C2996a c2996a) {
        C3013f.m14565a(c2996a, new FacebookException("Unable to show the provided content via the web or the installed version of the Facebook app. Some dialogs are only supported starting API 14."));
    }

    /* renamed from: a */
    public static void m14565a(C2996a c2996a, FacebookException facebookException) {
        C3013f.m14572b(c2996a, facebookException);
    }

    /* renamed from: a */
    public static void m14564a(C2996a c2996a, Activity activity) {
        activity.startActivityForResult(c2996a.m14534b(), c2996a.m14536d());
        c2996a.m14537e();
    }

    /* renamed from: a */
    public static void m14567a(C2996a c2996a, C3021j c3021j) {
        c3021j.m14612a(c2996a.m14534b(), c2996a.m14536d());
        c2996a.m14537e();
    }

    /* renamed from: a */
    public static boolean m14569a(C3011e c3011e) {
        return C3013f.m14571b(c3011e) != -1;
    }

    /* renamed from: b */
    public static void m14572b(C2996a c2996a, FacebookException facebookException) {
        if (facebookException != null) {
            C3049t.m14794b(C1472c.f());
            Intent intent = new Intent();
            intent.setClass(C1472c.f(), FacebookActivity.class);
            intent.setAction(FacebookActivity.f13369a);
            C3035o.m14678a(intent, c2996a.m14535c().toString(), null, C3035o.m14663a(), C3035o.m14675a(facebookException));
            c2996a.m14533a(intent);
        }
    }

    /* renamed from: a */
    public static void m14568a(C2996a c2996a, String str, Bundle bundle) {
        C3049t.m14794b(C1472c.f());
        C3049t.m14788a(C1472c.f());
        Bundle bundle2 = new Bundle();
        bundle2.putString(C0861d.f2143o, str);
        bundle2.putBundle("params", bundle);
        Intent intent = new Intent();
        C3035o.m14678a(intent, c2996a.m14535c().toString(), str, C3035o.m14663a(), bundle2);
        intent.setClass(C1472c.f(), FacebookActivity.class);
        intent.setAction("FacebookDialogFragment");
        c2996a.m14533a(intent);
    }

    /* renamed from: a */
    public static void m14566a(C2996a c2996a, C3012a c3012a, C3011e c3011e) {
        Context f = C1472c.f();
        String action = c3011e.getAction();
        int b = C3013f.m14571b(c3011e);
        if (b == -1) {
            throw new FacebookException("Cannot present this dialog. This likely means that the Facebook app is not installed.");
        }
        Bundle a;
        if (C3035o.m14679a(b)) {
            a = c3012a.mo3728a();
        } else {
            a = c3012a.mo3729b();
        }
        if (a == null) {
            a = new Bundle();
        }
        Intent a2 = C3035o.m14671a(f, c2996a.m14535c().toString(), action, b, a);
        if (a2 == null) {
            throw new FacebookException("Unable to create Intent; this likely means theFacebook app is not installed.");
        }
        c2996a.m14533a(a2);
    }

    /* renamed from: b */
    public static int m14571b(C3011e c3011e) {
        String i = C1472c.i();
        String action = c3011e.getAction();
        return C3035o.m14665a(action, C3013f.m14570a(i, action, c3011e));
    }

    /* renamed from: a */
    private static int[] m14570a(String str, String str2, C3011e c3011e) {
        C3044a a = C3048s.m14725a(str, str2, c3011e.name());
        if (a != null) {
            return a.m14714c();
        }
        return new int[]{c3011e.getMinVersion()};
    }
}
