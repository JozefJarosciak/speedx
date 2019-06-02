package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.alipay.sdk.data.C0849c;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.widget.C0889a;

/* renamed from: com.alipay.sdk.auth.h */
public final class C0836h {
    /* renamed from: a */
    private static final String f2011a = "com.eg.android.AlipayGphone";
    /* renamed from: b */
    private static final int f2012b = 65;
    /* renamed from: c */
    private static C0889a f2013c = null;
    /* renamed from: d */
    private static String f2014d = null;

    /* renamed from: a */
    private static boolean m3226a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
            if (packageInfo != null && packageInfo.versionCode >= 65) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: a */
    public static void m3224a(Activity activity, APAuthInfo aPAuthInfo) {
        C0870b a = C0870b.m3386a();
        C0849c.m3265a();
        a.m3391a((Context) activity);
        if (C0836h.m3226a((Context) activity)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("alipayauth://platformapi/startapp");
            stringBuilder.append("?appId=20000122");
            stringBuilder.append("&approveType=005");
            stringBuilder.append("&scope=kuaijie");
            stringBuilder.append("&productId=");
            stringBuilder.append(aPAuthInfo.getProductId());
            stringBuilder.append("&thirdpartyId=");
            stringBuilder.append(aPAuthInfo.getAppId());
            stringBuilder.append("&redirectUri=");
            stringBuilder.append(aPAuthInfo.getRedirectUri());
            C0836h.m3225a(activity, stringBuilder.toString());
            return;
        }
        if (activity != null) {
            try {
                if (!activity.isFinishing()) {
                    C0889a c0889a = new C0889a(activity, C0889a.f2240a);
                    f2013c = c0889a;
                    c0889a.m3498a();
                }
            } catch (Exception e) {
                f2013c = null;
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("app_id=");
        stringBuilder.append(aPAuthInfo.getAppId());
        stringBuilder.append("&partner=");
        stringBuilder.append(aPAuthInfo.getPid());
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&login_goal=auth");
        stringBuilder.append("&redirect_url=");
        stringBuilder.append(aPAuthInfo.getRedirectUri());
        stringBuilder.append("&view=wap");
        stringBuilder.append("&prod_code=");
        stringBuilder.append(aPAuthInfo.getProductId());
        new Thread(new C0837i(activity, stringBuilder, aPAuthInfo)).start();
    }

    /* renamed from: b */
    private static void m3228b(Activity activity, APAuthInfo aPAuthInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("alipayauth://platformapi/startapp");
        stringBuilder.append("?appId=20000122");
        stringBuilder.append("&approveType=005");
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&productId=");
        stringBuilder.append(aPAuthInfo.getProductId());
        stringBuilder.append("&thirdpartyId=");
        stringBuilder.append(aPAuthInfo.getAppId());
        stringBuilder.append("&redirectUri=");
        stringBuilder.append(aPAuthInfo.getRedirectUri());
        C0836h.m3225a(activity, stringBuilder.toString());
    }

    /* renamed from: c */
    private static void m3230c(Activity activity, APAuthInfo aPAuthInfo) {
        if (activity != null) {
            try {
                if (!activity.isFinishing()) {
                    C0889a c0889a = new C0889a(activity, C0889a.f2240a);
                    f2013c = c0889a;
                    c0889a.m3498a();
                }
            } catch (Exception e) {
                f2013c = null;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("app_id=");
        stringBuilder.append(aPAuthInfo.getAppId());
        stringBuilder.append("&partner=");
        stringBuilder.append(aPAuthInfo.getPid());
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&login_goal=auth");
        stringBuilder.append("&redirect_url=");
        stringBuilder.append(aPAuthInfo.getRedirectUri());
        stringBuilder.append("&view=wap");
        stringBuilder.append("&prod_code=");
        stringBuilder.append(aPAuthInfo.getProductId());
        new Thread(new C0837i(activity, stringBuilder, aPAuthInfo)).start();
    }

    /* renamed from: a */
    public static void m3225a(Activity activity, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }
}
