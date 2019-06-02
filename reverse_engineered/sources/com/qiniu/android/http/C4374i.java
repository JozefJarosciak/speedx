package com.qiniu.android.http;

import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.avos.avoscloud.AVException;
import com.qiniu.android.p189c.C4338d;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: UserAgent */
/* renamed from: com.qiniu.android.http.i */
public final class C4374i {
    /* renamed from: c */
    private static C4374i f15182c = new C4374i();
    /* renamed from: a */
    public final String f15183a = C4374i.m17211b();
    /* renamed from: b */
    public final String f15184b = C4374i.m17209a(this.f15183a);

    private C4374i() {
    }

    /* renamed from: a */
    public static C4374i m17208a() {
        return f15182c;
    }

    /* renamed from: b */
    private static String m17211b() {
        return System.currentTimeMillis() + "" + new Random().nextInt(AVException.UNKNOWN);
    }

    /* renamed from: a */
    private static String m17209a(String str) {
        return String.format("QiniuAndroid/%s (%s; %s; %s)", new Object[]{"7.2.0", C4374i.m17212c(), C4374i.m17213d(), str});
    }

    /* renamed from: c */
    private static String m17212c() {
        String str = VERSION.RELEASE;
        if (str == null) {
            return "";
        }
        return C4338d.m17128b(str.trim());
    }

    /* renamed from: d */
    private static String m17213d() {
        String trim = Build.MODEL.trim();
        String a = C4374i.m17210a(Build.MANUFACTURER.trim(), trim);
        if (TextUtils.isEmpty(a)) {
            a = C4374i.m17210a(Build.BRAND.trim(), trim);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (a == null) {
            a = "";
        }
        return C4338d.m17128b(stringBuilder.append(a).append(HelpFormatter.DEFAULT_OPT_PREFIX).append(trim).toString());
    }

    /* renamed from: a */
    private static String m17210a(String str, String str2) {
        CharSequence toLowerCase = str.toLowerCase(Locale.getDefault());
        if (toLowerCase.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || toLowerCase.startsWith("alps") || toLowerCase.startsWith("android") || toLowerCase.startsWith("sprd") || toLowerCase.startsWith("spreadtrum") || toLowerCase.startsWith("rockchip") || toLowerCase.startsWith("wondermedia") || toLowerCase.startsWith("mtk") || toLowerCase.startsWith("mt65") || toLowerCase.startsWith("nvidia") || toLowerCase.startsWith("brcm") || toLowerCase.startsWith("marvell") || str2.toLowerCase(Locale.getDefault()).contains(toLowerCase)) {
            return null;
        }
        return str;
    }

    public String toString() {
        return this.f15184b;
    }
}
