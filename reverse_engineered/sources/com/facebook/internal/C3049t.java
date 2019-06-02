package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import com.facebook.C1472c;
import com.facebook.CustomTabActivity;
import com.facebook.FacebookActivity;
import com.facebook.FacebookSdkNotInitializedException;
import java.util.Collection;
import java.util.List;

/* compiled from: Validate */
/* renamed from: com.facebook.internal.t */
public final class C3049t {
    /* renamed from: a */
    private static final String f13642a = C3049t.class.getName();

    /* renamed from: a */
    public static void m14790a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException("Argument '" + str + "' cannot be null");
        }
    }

    /* renamed from: a */
    public static <T> void m14792a(Collection<T> collection, String str) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Container '" + str + "' cannot be empty");
        }
    }

    /* renamed from: b */
    public static <T> void m14796b(Collection<T> collection, String str) {
        C3049t.m14790a((Object) collection, str);
        for (T t : collection) {
            if (t == null) {
                throw new NullPointerException("Container '" + str + "' cannot contain null values");
            }
        }
    }

    /* renamed from: c */
    public static <T> void m14798c(Collection<T> collection, String str) {
        C3049t.m14796b((Collection) collection, str);
        C3049t.m14792a((Collection) collection, str);
    }

    /* renamed from: a */
    public static void m14791a(String str, String str2) {
        if (C3048s.m14761a(str)) {
            throw new IllegalArgumentException("Argument '" + str2 + "' cannot be null or empty");
        }
    }

    /* renamed from: a */
    public static void m14787a() {
        if (!C1472c.a()) {
            throw new FacebookSdkNotInitializedException("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
        }
    }

    /* renamed from: b */
    public static String m14793b() {
        String i = C1472c.i();
        if (i != null) {
            return i;
        }
        throw new IllegalStateException("No App ID found, please set the App ID.");
    }

    /* renamed from: c */
    public static String m14797c() {
        String k = C1472c.k();
        if (k != null) {
            return k;
        }
        throw new IllegalStateException("No Client Token found, please set the Client Token.");
    }

    /* renamed from: a */
    public static void m14788a(Context context) {
        C3049t.m14789a(context, true);
    }

    /* renamed from: a */
    public static void m14789a(Context context, boolean z) {
        C3049t.m14790a((Object) context, CoreConstants.CONTEXT_SCOPE_VALUE);
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != -1) {
            return;
        }
        if (z) {
            throw new IllegalStateException("No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
        }
        Log.w(f13642a, "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
    }

    /* renamed from: b */
    public static void m14794b(Context context) {
        C3049t.m14795b(context, true);
    }

    /* renamed from: b */
    public static void m14795b(Context context, boolean z) {
        C3049t.m14790a((Object) context, CoreConstants.CONTEXT_SCOPE_VALUE);
        PackageManager packageManager = context.getPackageManager();
        ActivityInfo activityInfo = null;
        if (packageManager != null) {
            try {
                activityInfo = packageManager.getActivityInfo(new ComponentName(context, FacebookActivity.class), 1);
            } catch (NameNotFoundException e) {
            }
        }
        if (activityInfo != null) {
            return;
        }
        if (z) {
            throw new IllegalStateException("FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
        }
        Log.w(f13642a, "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
    }

    /* renamed from: c */
    public static boolean m14799c(Context context) {
        boolean z;
        C3049t.m14790a((Object) context, CoreConstants.CONTEXT_SCOPE_VALUE);
        PackageManager packageManager = context.getPackageManager();
        List list = null;
        if (packageManager != null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(Uri.parse("fb" + C1472c.i() + "://authorize"));
            list = packageManager.queryIntentActivities(intent, 64);
        }
        if (r0 != null) {
            z = false;
            for (ResolveInfo resolveInfo : r0) {
                if (!resolveInfo.activityInfo.name.equals(CustomTabActivity.class.getName())) {
                    return false;
                }
                z = true;
            }
        } else {
            z = false;
        }
        return z;
    }

    /* renamed from: d */
    public static void m14800d(Context context) {
        C3049t.m14790a((Object) context, CoreConstants.CONTEXT_SCOPE_VALUE);
        String b = C3049t.m14793b();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            if (packageManager.resolveContentProvider("com.facebook.app.FacebookContentProvider" + b, 0) == null) {
                throw new IllegalStateException(String.format("A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.", new Object[]{"com.facebook.app.FacebookContentProvider" + b}));
            }
        }
    }
}
