package io.fabric.sdk.android.services.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import io.fabric.sdk.android.C1520c;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: CommonUtils */
/* renamed from: io.fabric.sdk.android.services.common.i */
public class C4877i {
    /* renamed from: a */
    public static final Comparator<File> f17159a = new C48761();
    /* renamed from: b */
    private static Boolean f17160b = null;
    /* renamed from: c */
    private static final char[] f17161c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: d */
    private static long f17162d = -1;

    /* compiled from: CommonUtils */
    /* renamed from: io.fabric.sdk.android.services.common.i$1 */
    static class C48761 implements Comparator<File> {
        C48761() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m19143a((File) obj, (File) obj2);
        }

        /* renamed from: a */
        public int m19143a(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    }

    /* renamed from: a */
    public static SharedPreferences m19145a(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    /* renamed from: a */
    public static String m19146a(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    /* renamed from: a */
    public static String m19148a(String str) {
        return C4877i.m19149a(str, "SHA-1");
    }

    /* renamed from: b */
    public static String m19164b(InputStream inputStream) {
        return C4877i.m19147a(inputStream, "SHA-1");
    }

    /* renamed from: a */
    private static String m19147a(InputStream inputStream, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return C4877i.m19150a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Throwable e) {
            C1520c.h().mo6222d("Fabric", "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    /* renamed from: a */
    private static String m19151a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return C4877i.m19150a(instance.digest());
        } catch (Throwable e) {
            C1520c.h().mo6222d("Fabric", "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    /* renamed from: a */
    private static String m19149a(String str, String str2) {
        return C4877i.m19151a(str.getBytes(), str2);
    }

    /* renamed from: a */
    public static String m19152a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace(HelpFormatter.DEFAULT_OPT_PREFIX, "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : arrayList) {
            stringBuilder.append(append);
        }
        String append2 = stringBuilder.toString();
        return append2.length() > 0 ? C4877i.m19148a(append2) : null;
    }

    /* renamed from: a */
    public static void m19157a(Context context, String str) {
        if (C4877i.m19166b(context)) {
            C1520c.h().mo6215a("Fabric", str);
        }
    }

    /* renamed from: a */
    public static void m19158a(Context context, String str, Throwable th) {
        if (C4877i.m19166b(context)) {
            C1520c.h().mo6221d("Fabric", str);
        }
    }

    /* renamed from: a */
    public static void m19155a(Context context, int i, String str, String str2) {
        if (C4877i.m19166b(context)) {
            C1520c.h().mo6214a(i, "Fabric", str2);
        }
    }

    /* renamed from: b */
    public static boolean m19166b(Context context) {
        if (f17160b == null) {
            f17160b = Boolean.valueOf(C4877i.m19162a(context, "com.crashlytics.Trace", false));
        }
        return f17160b.booleanValue();
    }

    /* renamed from: a */
    public static boolean m19162a(Context context, String str, boolean z) {
        if (context == null) {
            return z;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return z;
        }
        int a = C4877i.m19144a(context, str, "bool");
        if (a > 0) {
            return resources.getBoolean(a);
        }
        int a2 = C4877i.m19144a(context, str, "string");
        if (a2 > 0) {
            return Boolean.parseBoolean(context.getString(a2));
        }
        return z;
    }

    /* renamed from: a */
    public static int m19144a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, C4877i.m19170d(context));
    }

    /* renamed from: a */
    public static String m19150a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = f17161c[i2 >>> 4];
            cArr[(i * 2) + 1] = f17161c[i2 & 15];
        }
        return new String(cArr);
    }

    /* renamed from: c */
    public static boolean m19168c(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: b */
    public static String m19163b(Context context, String str) {
        int a = C4877i.m19144a(context, str, "string");
        if (a > 0) {
            return context.getString(a);
        }
        return "";
    }

    /* renamed from: a */
    public static void m19160a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                C1520c.h().mo6222d("Fabric", str, e);
            }
        }
    }

    /* renamed from: b */
    public static boolean m19167b(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: d */
    public static String m19170d(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

    /* renamed from: a */
    public static void m19161a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    /* renamed from: e */
    public static String m19171e(Context context) {
        Closeable openRawResource;
        Throwable e;
        Throwable th;
        String str = null;
        try {
            openRawResource = context.getResources().openRawResource(C4877i.m19172f(context));
            try {
                String b = C4877i.m19164b((InputStream) openRawResource);
                if (!C4877i.m19167b(b)) {
                    str = b;
                }
                C4877i.m19160a(openRawResource, "Failed to close icon input stream.");
            } catch (Exception e2) {
                e = e2;
                try {
                    C1520c.h().mo6222d("Fabric", "Could not calculate hash for app icon.", e);
                    C4877i.m19160a(openRawResource, "Failed to close icon input stream.");
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    C4877i.m19160a(openRawResource, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            openRawResource = null;
            C1520c.h().mo6222d("Fabric", "Could not calculate hash for app icon.", e);
            C4877i.m19160a(openRawResource, "Failed to close icon input stream.");
            return str;
        } catch (Throwable e4) {
            openRawResource = null;
            th = e4;
            C4877i.m19160a(openRawResource, "Failed to close icon input stream.");
            throw th;
        }
        return str;
    }

    /* renamed from: f */
    public static int m19172f(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    /* renamed from: g */
    public static String m19173g(Context context) {
        int a = C4877i.m19144a(context, "io.fabric.android.build_id", "string");
        if (a == 0) {
            a = C4877i.m19144a(context, "com.crashlytics.android.build_id", "string");
        }
        if (a == 0) {
            return null;
        }
        String string = context.getResources().getString(a);
        C1520c.h().mo6215a("Fabric", "Build ID is: " + string);
        return string;
    }

    /* renamed from: a */
    public static void m19159a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: c */
    public static boolean m19169c(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: a */
    public static void m19156a(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /* renamed from: b */
    public static void m19165b(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    @TargetApi(16)
    /* renamed from: a */
    public static void m19154a(Context context, int i) {
        if (context instanceof Activity) {
            C4877i.m19153a((Activity) context, i);
        }
    }

    @TargetApi(16)
    /* renamed from: a */
    public static void m19153a(Activity activity, int i) {
        if (activity != null) {
            if (VERSION.SDK_INT >= 16) {
                activity.finishAffinity();
                return;
            }
            activity.setResult(i);
            activity.finish();
        }
    }
}
