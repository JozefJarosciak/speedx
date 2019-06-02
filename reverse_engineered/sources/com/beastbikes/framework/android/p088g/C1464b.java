package com.beastbikes.framework.android.p088g;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: ChannelUtil */
/* renamed from: com.beastbikes.framework.android.g.b */
public class C1464b {
    /* renamed from: a */
    private static String f6858a;
    /* renamed from: b */
    private static String f6859b = "Google";

    /* renamed from: a */
    public static String m8055a(Context context) {
        return C1464b.m8056a(context, f6859b);
    }

    /* renamed from: a */
    public static String m8056a(Context context, String str) {
        if (!TextUtils.isEmpty(f6858a)) {
            return f6858a;
        }
        f6858a = C1464b.m8057b(context);
        if (!TextUtils.isEmpty(f6858a)) {
            return f6858a;
        }
        f6858a = C1464b.m8058b(context, "cztchannel");
        if (TextUtils.isEmpty(f6858a)) {
            return str;
        }
        C1464b.m8060c(context, f6858a);
        return f6858a;
    }

    /* renamed from: b */
    private static String m8058b(Context context, String str) {
        ZipFile zipFile;
        IOException e;
        String[] split;
        Throwable th;
        String str2 = context.getApplicationInfo().sourceDir;
        String str3 = "META-INF/" + str;
        String str4 = "";
        try {
            zipFile = new ZipFile(str2);
            try {
                Enumeration entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    str2 = ((ZipEntry) entries.nextElement()).getName();
                    if (str2.startsWith(str3)) {
                        break;
                    }
                }
                str2 = str4;
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (zipFile == null) {
                        str2 = str4;
                    } else {
                        try {
                            zipFile.close();
                            str2 = str4;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            str2 = str4;
                        }
                    }
                    split = str2.split("_");
                    str4 = "";
                    if (split != null) {
                    }
                    return str4;
                } catch (Throwable th2) {
                    th = th2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e4 = e5;
            zipFile = null;
            e4.printStackTrace();
            if (zipFile == null) {
                zipFile.close();
                str2 = str4;
            } else {
                str2 = str4;
            }
            split = str2.split("_");
            str4 = "";
            if (split != null) {
            }
            return str4;
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
            if (zipFile != null) {
                zipFile.close();
            }
            throw th;
        }
        split = str2.split("_");
        str4 = "";
        if (split != null || split.length < 2) {
            return str4;
        }
        return str2.substring(split[0].length() + 1);
    }

    /* renamed from: c */
    private static void m8060c(Context context, String str) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString("cztchannel", str);
        edit.putInt("cztchannel_version", C1464b.m8059c(context));
        edit.commit();
    }

    /* renamed from: b */
    private static String m8057b(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int c = C1464b.m8059c(context);
        if (c == -1) {
            return "";
        }
        int i = defaultSharedPreferences.getInt("cztchannel_version", -1);
        if (i == -1) {
            return "";
        }
        if (c != i) {
            return "";
        }
        return defaultSharedPreferences.getString("cztchannel", "");
    }

    /* renamed from: c */
    private static int m8059c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
