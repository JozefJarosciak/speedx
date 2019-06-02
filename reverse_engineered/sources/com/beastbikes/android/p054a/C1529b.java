package com.beastbikes.android.p054a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import com.beastbikes.android.C1373R;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: EasyPermissions */
/* renamed from: com.beastbikes.android.a.b */
public class C1529b {

    /* compiled from: EasyPermissions */
    /* renamed from: com.beastbikes.android.a.b$a */
    public interface C1528a extends OnRequestPermissionsResultCallback {
        /* renamed from: a */
        void m8390a(int i, List<String> list);

        /* renamed from: b */
        void m8391b(int i, List<String> list);
    }

    /* renamed from: a */
    public static boolean m8399a(Context context, String... strArr) {
        if (VERSION.SDK_INT < 23) {
            Log.w("EasyPermissions", "hasPermissions: API version < M, returning true by default");
            return true;
        }
        for (String checkSelfPermission : strArr) {
            boolean z;
            if (ContextCompat.checkSelfPermission(context, checkSelfPermission) == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static void m8397a(Object obj, String str, int i, String... strArr) {
        C1529b.m8396a(obj, str, 17039370, 17039360, i, strArr);
    }

    /* renamed from: a */
    public static void m8396a(final Object obj, String str, @StringRes int i, @StringRes int i2, final int i3, final String... strArr) {
        C1529b.m8403b(obj);
        Object obj2 = null;
        for (String str2 : strArr) {
            if (obj2 != null || C1529b.m8400a(obj, str2)) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
        }
        if (obj2 != null) {
            Context a = C1529b.m8392a(obj);
            if (a != null) {
                new Builder(a).setMessage(str).setPositiveButton(i, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        C1529b.m8405b(obj, strArr, i3);
                    }
                }).setNegativeButton(i2, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (obj instanceof C1528a) {
                            ((C1528a) obj).m8391b(i3, Arrays.asList(strArr));
                        }
                    }
                }).create().show();
                return;
            }
            return;
        }
        obj2 = new ArrayList();
        Collections.addAll(obj2, strArr);
        C1529b.m8402a(obj, str, C1373R.string.activity_alert_dialog_text_ok, C1373R.string.activity_alert_dialog_text_cancel, obj2);
    }

    /* renamed from: a */
    public static void m8393a(int i, String[] strArr, int[] iArr, Object obj) {
        C1529b.m8403b(obj);
        Object arrayList = new ArrayList();
        Object arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            Object obj2 = strArr[i2];
            if (iArr[i2] == 0) {
                arrayList.add(obj2);
            } else {
                arrayList2.add(obj2);
            }
        }
        if (!arrayList.isEmpty() && (obj instanceof C1528a)) {
            ((C1528a) obj).m8390a(i, arrayList);
        }
        if (!arrayList2.isEmpty() && (obj instanceof C1528a)) {
            ((C1528a) obj).m8391b(i, arrayList2);
        }
        if (!arrayList.isEmpty() && arrayList2.isEmpty()) {
            C1529b.m8394a(obj, i);
        }
    }

    /* renamed from: a */
    public static boolean m8402a(Object obj, String str, @StringRes int i, @StringRes int i2, List<String> list) {
        return C1529b.m8401a(obj, str, i, i2, null, (List) list);
    }

    /* renamed from: a */
    public static boolean m8401a(final Object obj, String str, @StringRes int i, @StringRes int i2, @Nullable OnClickListener onClickListener, List<String> list) {
        for (String a : list) {
            if (!C1529b.m8400a(obj, a)) {
                final Context a2 = C1529b.m8392a(obj);
                if (a2 == null) {
                    return true;
                }
                new Builder(a2).setMessage(str).setPositiveButton(i, new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts(OnlineConfigAgent.KEY_PACKAGE, a2.getPackageName(), null));
                        C1529b.m8404b(obj, intent);
                    }
                }).setNegativeButton(i2, onClickListener).create().show();
                return true;
            }
        }
        return false;
    }

    @TargetApi(23)
    /* renamed from: a */
    private static boolean m8400a(Object obj, String str) {
        if (obj instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) obj, str);
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).shouldShowRequestPermissionRationale(str);
        }
        if (obj instanceof android.app.Fragment) {
            return ((android.app.Fragment) obj).shouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    @TargetApi(23)
    /* renamed from: b */
    private static void m8405b(Object obj, String[] strArr, int i) {
        C1529b.m8403b(obj);
        if (obj instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) obj, strArr, i);
        } else if (obj instanceof Fragment) {
            ((Fragment) obj).requestPermissions(strArr, i);
        } else if (obj instanceof android.app.Fragment) {
            ((android.app.Fragment) obj).requestPermissions(strArr, i);
        }
    }

    @TargetApi(11)
    /* renamed from: a */
    private static Activity m8392a(Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        }
        if (obj instanceof Fragment) {
            return ((Fragment) obj).getActivity();
        }
        if (obj instanceof android.app.Fragment) {
            return ((android.app.Fragment) obj).getActivity();
        }
        return null;
    }

    @TargetApi(11)
    /* renamed from: b */
    private static void m8404b(Object obj, Intent intent) {
        if (obj instanceof Activity) {
            ((Activity) obj).startActivityForResult(intent, 16061);
        } else if (obj instanceof Fragment) {
            ((Fragment) obj).startActivityForResult(intent, 16061);
        } else if (obj instanceof android.app.Fragment) {
            ((android.app.Fragment) obj).startActivityForResult(intent, 16061);
        }
    }

    /* renamed from: a */
    private static void m8394a(Object obj, int i) {
        Class cls = obj.getClass();
        if (C1529b.m8406c(obj)) {
            cls = cls.getSuperclass();
        }
        for (Method method : r0.getDeclaredMethods()) {
            if (method.isAnnotationPresent(C1374a.class) && ((C1374a) method.getAnnotation(C1374a.class)).a() == i) {
                if (method.getParameterTypes().length > 0) {
                    throw new RuntimeException("Cannot execute non-void method " + method.getName());
                }
                try {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    method.invoke(obj, new Object[0]);
                } catch (Throwable e) {
                    Log.e("EasyPermissions", "runDefaultMethod:IllegalAccessException", e);
                } catch (Throwable e2) {
                    Log.e("EasyPermissions", "runDefaultMethod:InvocationTargetException", e2);
                }
            }
        }
    }

    /* renamed from: b */
    private static void m8403b(Object obj) {
        boolean z = obj instanceof Activity;
        boolean z2 = obj instanceof Fragment;
        boolean z3 = obj instanceof android.app.Fragment;
        Object obj2 = VERSION.SDK_INT >= 23 ? 1 : null;
        if (!z2 && !z) {
            if (z3 && obj2 != null) {
                return;
            }
            if (z3) {
                throw new IllegalArgumentException("Target SDK needs to be greater than 23 if caller is android.app.Fragment");
            }
            throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
        }
    }

    /* renamed from: c */
    private static boolean m8406c(Object obj) {
        boolean z = false;
        if (obj.getClass().getSimpleName().endsWith("_")) {
            try {
                z = Class.forName("org.androidannotations.api.view.HasViews").isInstance(obj);
            } catch (ClassNotFoundException e) {
            }
        }
        return z;
    }
}
