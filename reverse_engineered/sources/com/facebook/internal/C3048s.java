package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.StatFs;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.packet.C0861d;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.util.C0880h;
import com.facebook.AccessToken;
import com.facebook.C1472c;
import com.facebook.C2987f;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.C2942b;
import com.facebook.HttpMethod;
import com.facebook.appevents.p178a.C2964b;
import com.j256.ormlite.stmt.query.SimpleComparison;
import io.rong.imlib.statistics.UserData;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* compiled from: Utility */
/* renamed from: com.facebook.internal.s */
public final class C3048s {
    /* renamed from: a */
    private static final String[] f13632a = new String[]{"supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "gdpv4_chrome_custom_tabs_enabled", "android_dialog_configs", "android_sdk_error_categories", "app_events_session_timeout"};
    /* renamed from: b */
    private static Map<String, C3045b> f13633b = new ConcurrentHashMap();
    /* renamed from: c */
    private static AtomicBoolean f13634c = new AtomicBoolean(false);
    /* renamed from: d */
    private static int f13635d = 0;
    /* renamed from: e */
    private static long f13636e = -1;
    /* renamed from: f */
    private static long f13637f = -1;
    /* renamed from: g */
    private static long f13638g = -1;
    /* renamed from: h */
    private static String f13639h = "";
    /* renamed from: i */
    private static String f13640i = "";
    /* renamed from: j */
    private static String f13641j = "NoCarrier";

    /* compiled from: Utility */
    /* renamed from: com.facebook.internal.s$c */
    public interface C2953c {
        /* renamed from: a */
        void mo3689a(FacebookException facebookException);

        /* renamed from: a */
        void mo3690a(JSONObject jSONObject);
    }

    /* compiled from: Utility */
    /* renamed from: com.facebook.internal.s$3 */
    static class C30433 implements FilenameFilter {
        C30433() {
        }

        public boolean accept(File file, String str) {
            return Pattern.matches("cpu[0-9]+", str);
        }
    }

    /* compiled from: Utility */
    /* renamed from: com.facebook.internal.s$a */
    public static class C3044a {
        /* renamed from: a */
        private String f13619a;
        /* renamed from: b */
        private String f13620b;
        /* renamed from: c */
        private Uri f13621c;
        /* renamed from: d */
        private int[] f13622d;

        /* renamed from: b */
        private static C3044a m14711b(JSONObject jSONObject) {
            Uri uri = null;
            String optString = jSONObject.optString("name");
            if (C3048s.m14761a(optString)) {
                return null;
            }
            String[] split = optString.split("\\|");
            if (split.length != 2) {
                return null;
            }
            String str = split[0];
            String str2 = split[1];
            if (C3048s.m14761a(str) || C3048s.m14761a(str2)) {
                return null;
            }
            optString = jSONObject.optString("url");
            if (!C3048s.m14761a(optString)) {
                uri = Uri.parse(optString);
            }
            return new C3044a(str, str2, uri, C3044a.m14710a(jSONObject.optJSONArray("versions")));
        }

        /* renamed from: a */
        private static int[] m14710a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                int optInt = jSONArray.optInt(i, -1);
                if (optInt == -1) {
                    String optString = jSONArray.optString(i);
                    if (!C3048s.m14761a(optString)) {
                        try {
                            optInt = Integer.parseInt(optString);
                        } catch (Exception e) {
                            C3048s.m14753a("FacebookSDK", e);
                            optInt = -1;
                        }
                    }
                }
                iArr[i] = optInt;
            }
            return iArr;
        }

        private C3044a(String str, String str2, Uri uri, int[] iArr) {
            this.f13619a = str;
            this.f13620b = str2;
            this.f13621c = uri;
            this.f13622d = iArr;
        }

        /* renamed from: a */
        public String m14712a() {
            return this.f13619a;
        }

        /* renamed from: b */
        public String m14713b() {
            return this.f13620b;
        }

        /* renamed from: c */
        public int[] m14714c() {
            return this.f13622d;
        }
    }

    /* compiled from: Utility */
    /* renamed from: com.facebook.internal.s$b */
    public static class C3045b {
        /* renamed from: a */
        private boolean f13623a;
        /* renamed from: b */
        private String f13624b;
        /* renamed from: c */
        private boolean f13625c;
        /* renamed from: d */
        private boolean f13626d;
        /* renamed from: e */
        private int f13627e;
        /* renamed from: f */
        private Map<String, Map<String, C3044a>> f13628f;
        /* renamed from: g */
        private C3017h f13629g;

        private C3045b(boolean z, String str, boolean z2, boolean z3, int i, Map<String, Map<String, C3044a>> map, C3017h c3017h) {
            this.f13623a = z;
            this.f13624b = str;
            this.f13625c = z2;
            this.f13626d = z3;
            this.f13628f = map;
            this.f13629g = c3017h;
            this.f13627e = i;
        }

        /* renamed from: a */
        public boolean m14715a() {
            return this.f13623a;
        }

        /* renamed from: b */
        public boolean m14716b() {
            return this.f13626d;
        }

        /* renamed from: c */
        public Map<String, Map<String, C3044a>> m14717c() {
            return this.f13628f;
        }

        /* renamed from: d */
        public C3017h m14718d() {
            return this.f13629g;
        }
    }

    /* compiled from: Utility */
    /* renamed from: com.facebook.internal.s$d */
    public interface C3046d<T, K> {
        /* renamed from: a */
        K mo3722a(T t);
    }

    /* compiled from: Utility */
    /* renamed from: com.facebook.internal.s$e */
    public static class C3047e {
        /* renamed from: a */
        List<String> f13630a;
        /* renamed from: b */
        List<String> f13631b;

        public C3047e(List<String> list, List<String> list2) {
            this.f13630a = list;
            this.f13631b = list2;
        }

        /* renamed from: a */
        public List<String> m14720a() {
            return this.f13630a;
        }

        /* renamed from: b */
        public List<String> m14721b() {
            return this.f13631b;
        }
    }

    /* renamed from: a */
    public static <T> boolean m14762a(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    /* renamed from: a */
    public static boolean m14761a(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: a */
    public static <T> Collection<T> m14740a(T... tArr) {
        return Collections.unmodifiableCollection(Arrays.asList(tArr));
    }

    /* renamed from: a */
    public static String m14737a(byte[] bArr) {
        return C3048s.m14735a("SHA-1", bArr);
    }

    /* renamed from: a */
    private static String m14735a(String str, byte[] bArr) {
        try {
            return C3048s.m14736a(MessageDigest.getInstance(str), bArr);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m14736a(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString((b >> 4) & 15));
            stringBuilder.append(Integer.toHexString((b >> 0) & 15));
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static Uri m14724a(String str, String str2, Bundle bundle) {
        Builder builder = new Builder();
        builder.scheme(C0845b.f2060a);
        builder.authority(str);
        builder.path(str2);
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof String) {
                    builder.appendQueryParameter(str3, (String) obj);
                }
            }
        }
        return builder.build();
    }

    /* renamed from: b */
    public static Bundle m14764b(String str) {
        Bundle bundle = new Bundle();
        if (!C3048s.m14761a(str)) {
            for (String split : str.split(C0869a.f2161b)) {
                String[] split2 = split.split(SimpleComparison.EQUAL_TO_OPERATION);
                try {
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                    } else if (split2.length == 1) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), "");
                    }
                } catch (Exception e) {
                    C3048s.m14753a("FacebookSDK", e);
                }
            }
        }
        return bundle;
    }

    /* renamed from: a */
    public static void m14748a(Bundle bundle, String str, String str2) {
        if (!C3048s.m14761a(str2)) {
            bundle.putString(str, str2);
        }
    }

    /* renamed from: a */
    public static void m14747a(Bundle bundle, String str, Uri uri) {
        if (uri != null) {
            C3048s.m14748a(bundle, str, uri.toString());
        }
    }

    /* renamed from: a */
    public static void m14750a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public static void m14756a(URLConnection uRLConnection) {
        if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    /* renamed from: a */
    public static String m14732a(Context context) {
        C3049t.m14790a((Object) context, CoreConstants.CONTEXT_SCOPE_VALUE);
        C1472c.a(context);
        return C1472c.i();
    }

    /* renamed from: a */
    public static Object m14730a(JSONObject jSONObject, String str, String str2) throws JSONException {
        Object obj;
        Object opt = jSONObject.opt(str);
        if (opt == null || !(opt instanceof String)) {
            obj = opt;
        } else {
            obj = new JSONTokener((String) opt).nextValue();
        }
        if (obj == null || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj;
        }
        if (str2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(str2, obj);
            return jSONObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }

    /* renamed from: a */
    public static String m14734a(InputStream inputStream) throws IOException {
        Throwable th;
        Closeable closeable = null;
        Closeable inputStreamReader;
        try {
            Closeable bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    char[] cArr = new char[2048];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            stringBuilder.append(cArr, 0, read);
                        } else {
                            String stringBuilder2 = stringBuilder.toString();
                            C3048s.m14750a(bufferedInputStream);
                            C3048s.m14750a(inputStreamReader);
                            return stringBuilder2;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = bufferedInputStream;
                    C3048s.m14750a(closeable);
                    C3048s.m14750a(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                closeable = bufferedInputStream;
                C3048s.m14750a(closeable);
                C3048s.m14750a(inputStreamReader);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            C3048s.m14750a(closeable);
            C3048s.m14750a(inputStreamReader);
            throw th;
        }
    }

    /* renamed from: a */
    public static int m14722a(InputStream inputStream, OutputStream outputStream) throws IOException {
        Throwable th;
        int i = 0;
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    i += read;
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    private static void m14770b(Context context, String str) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        if (cookie != null) {
            for (String split : cookie.split(C0880h.f2220b)) {
                String[] split2 = split.split(SimpleComparison.EQUAL_TO_OPERATION);
                if (split2.length > 0) {
                    instance.setCookie(str, split2[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
                }
            }
            instance.removeExpiredCookie();
        }
    }

    /* renamed from: b */
    public static void m14769b(Context context) {
        C3048s.m14770b(context, "facebook.com");
        C3048s.m14770b(context, ".facebook.com");
        C3048s.m14770b(context, "https://facebook.com");
        C3048s.m14770b(context, "https://.facebook.com");
    }

    /* renamed from: a */
    public static void m14753a(String str, Exception exception) {
        if (C1472c.b() && str != null && exception != null) {
            Log.d(str, exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
    }

    /* renamed from: a */
    public static void m14754a(String str, String str2) {
        if (C1472c.b() && str != null && str2 != null) {
            Log.d(str, str2);
        }
    }

    /* renamed from: a */
    public static void m14755a(String str, String str2, Throwable th) {
        if (C1472c.b() && !C3048s.m14761a(str)) {
            Log.d(str, str2, th);
        }
    }

    /* renamed from: a */
    public static <T> boolean m14760a(T t, T t2) {
        if (t == null) {
            return t2 == null;
        } else {
            return t.equals(t2);
        }
    }

    /* renamed from: a */
    public static void m14746a(final Context context, final String str) {
        boolean compareAndSet = f13634c.compareAndSet(false, true);
        if (!C3048s.m14761a(str) && !f13633b.containsKey(str) && compareAndSet) {
            final String format = String.format("com.facebook.internal.APP_SETTINGS.%s", new Object[]{str});
            C1472c.d().execute(new Runnable() {
                public void run() {
                    JSONObject jSONObject;
                    SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
                    String string = sharedPreferences.getString(format, null);
                    if (!C3048s.m14761a(string)) {
                        try {
                            jSONObject = new JSONObject(string);
                        } catch (Exception e) {
                            C3048s.m14753a("FacebookSDK", e);
                            jSONObject = null;
                        }
                        if (jSONObject != null) {
                            C3048s.m14765b(str, jSONObject);
                        }
                    }
                    jSONObject = C3048s.m14784f(str);
                    if (jSONObject != null) {
                        C3048s.m14765b(str, jSONObject);
                        sharedPreferences.edit().putString(format, jSONObject.toString()).apply();
                    }
                    C3048s.f13634c.set(false);
                }
            });
        }
    }

    /* renamed from: c */
    public static C3045b m14772c(String str) {
        return str != null ? (C3045b) f13633b.get(str) : null;
    }

    /* renamed from: a */
    public static C3045b m14727a(String str, boolean z) {
        if (!z && f13633b.containsKey(str)) {
            return (C3045b) f13633b.get(str);
        }
        JSONObject f = C3048s.m14784f(str);
        if (f == null) {
            return null;
        }
        return C3048s.m14765b(str, f);
    }

    /* renamed from: b */
    private static C3045b m14765b(String str, JSONObject jSONObject) {
        C3017h a;
        JSONArray optJSONArray = jSONObject.optJSONArray("android_sdk_error_categories");
        if (optJSONArray == null) {
            a = C3017h.m14584a();
        } else {
            a = C3017h.m14585a(optJSONArray);
        }
        C3045b c3045b = new C3045b(jSONObject.optBoolean("supports_implicit_sdk_logging", false), jSONObject.optString("gdpv4_nux_content", ""), jSONObject.optBoolean("gdpv4_nux_enabled", false), jSONObject.optBoolean("gdpv4_chrome_custom_tabs_enabled", false), jSONObject.optInt("app_events_session_timeout", C2964b.m14419a()), C3048s.m14767b(jSONObject.optJSONObject("android_dialog_configs")), a);
        f13633b.put(str, c3045b);
        return c3045b;
    }

    /* renamed from: f */
    private static JSONObject m14784f(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", f13632a));
        GraphRequest a = GraphRequest.m14332a(null, str, null);
        a.m14376a(true);
        a.m14371a(bundle);
        return a.m14384i().m14500b();
    }

    /* renamed from: a */
    public static C3044a m14725a(String str, String str2, String str3) {
        if (C3048s.m14761a(str2) || C3048s.m14761a(str3)) {
            return null;
        }
        C3045b c3045b = (C3045b) f13633b.get(str);
        if (c3045b != null) {
            Map map = (Map) c3045b.m14717c().get(str2);
            if (map != null) {
                return (C3044a) map.get(str3);
            }
        }
        return null;
    }

    /* renamed from: b */
    private static Map<String, Map<String, C3044a>> m14767b(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray(C0861d.f2139k);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    C3044a a = C3044a.m14711b(optJSONArray.optJSONObject(i));
                    if (a != null) {
                        String a2 = a.m14712a();
                        Map map = (Map) hashMap.get(a2);
                        if (map == null) {
                            map = new HashMap();
                            hashMap.put(a2, map);
                        }
                        map.put(a.m14713b(), a);
                    }
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static void m14751a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File a : listFiles) {
                        C3048s.m14751a(a);
                    }
                }
            }
            file.delete();
        }
    }

    /* renamed from: b */
    public static <T> List<T> m14766b(T... tArr) {
        List arrayList = new ArrayList();
        for (Object obj : tArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List<String> m14743a(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    /* renamed from: b */
    public static Set<String> m14768b(JSONArray jSONArray) throws JSONException {
        Set<String> hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        return hashSet;
    }

    /* renamed from: a */
    public static void m14758a(JSONObject jSONObject, C3000b c3000b, String str, boolean z) throws JSONException {
        boolean z2 = true;
        if (!(c3000b == null || c3000b.m14547a() == null)) {
            jSONObject.put("attribution", c3000b.m14547a());
        }
        if (!(c3000b == null || c3000b.m14548b() == null)) {
            jSONObject.put("advertiser_id", c3000b.m14548b());
            jSONObject.put("advertiser_tracking_enabled", !c3000b.m14550d());
        }
        if (!(c3000b == null || c3000b.m14549c() == null)) {
            jSONObject.put("installer_package", c3000b.m14549c());
        }
        jSONObject.put("anon_id", str);
        String str2 = "application_tracking_enabled";
        if (z) {
            z2 = false;
        }
        jSONObject.put(str2, z2);
    }

    /* renamed from: a */
    public static void m14757a(JSONObject jSONObject, Context context) throws JSONException {
        Locale locale;
        double d;
        int i;
        int i2;
        int i3;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("a2");
        C3048s.m14777d(context);
        String packageName = context.getPackageName();
        int i4 = -1;
        Object obj = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            i4 = packageInfo.versionCode;
            obj = packageInfo.versionName;
        } catch (NameNotFoundException e) {
        }
        jSONArray.put(packageName);
        jSONArray.put(i4);
        jSONArray.put(obj);
        jSONArray.put(VERSION.RELEASE);
        jSONArray.put(Build.MODEL);
        try {
            locale = context.getResources().getConfiguration().locale;
        } catch (Exception e2) {
            locale = Locale.getDefault();
        }
        jSONArray.put(locale.getLanguage() + "_" + locale.getCountry());
        jSONArray.put(f13639h);
        jSONArray.put(f13641j);
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics);
                int i5 = displayMetrics.widthPixels;
                try {
                    int i6 = displayMetrics.heightPixels;
                    try {
                        d = (double) displayMetrics.density;
                        i = i6;
                        i2 = i5;
                    } catch (Exception e3) {
                        i3 = i6;
                        i4 = i5;
                        i = i3;
                        i2 = i4;
                        d = 0.0d;
                        jSONArray.put(i2);
                        jSONArray.put(i);
                        jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
                        jSONArray.put(C3048s.m14763b());
                        jSONArray.put(f13637f);
                        jSONArray.put(f13638g);
                        jSONArray.put(f13640i);
                        jSONObject.put("extinfo", jSONArray.toString());
                    }
                } catch (Exception e4) {
                    i3 = 0;
                    i4 = i5;
                    i = i3;
                    i2 = i4;
                    d = 0.0d;
                    jSONArray.put(i2);
                    jSONArray.put(i);
                    jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
                    jSONArray.put(C3048s.m14763b());
                    jSONArray.put(f13637f);
                    jSONArray.put(f13638g);
                    jSONArray.put(f13640i);
                    jSONObject.put("extinfo", jSONArray.toString());
                }
            }
            d = 0.0d;
            i = 0;
            i2 = 0;
        } catch (Exception e5) {
            i3 = 0;
            i4 = 0;
            i = i3;
            i2 = i4;
            d = 0.0d;
            jSONArray.put(i2);
            jSONArray.put(i);
            jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
            jSONArray.put(C3048s.m14763b());
            jSONArray.put(f13637f);
            jSONArray.put(f13638g);
            jSONArray.put(f13640i);
            jSONObject.put("extinfo", jSONArray.toString());
        }
        jSONArray.put(i2);
        jSONArray.put(i);
        jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
        jSONArray.put(C3048s.m14763b());
        jSONArray.put(f13637f);
        jSONArray.put(f13638g);
        jSONArray.put(f13640i);
        jSONObject.put("extinfo", jSONArray.toString());
    }

    /* renamed from: a */
    public static Method m14738a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static Method m14739a(String str, String str2, Class<?>... clsArr) {
        try {
            return C3048s.m14738a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static Object m14729a(Object obj, Method method, Object... objArr) {
        Object obj2 = null;
        try {
            obj2 = method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return obj2;
    }

    /* renamed from: c */
    public static String m14773c(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return context.getClass().getSimpleName();
    }

    /* renamed from: a */
    public static <T, K> List<K> m14742a(List<T> list, C3046d<T, K> c3046d) {
        if (list == null) {
            return null;
        }
        List<K> arrayList = new ArrayList();
        for (T a : list) {
            Object a2 = c3046d.mo3722a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        if (arrayList.size() != 0) {
            return arrayList;
        }
        return null;
    }

    /* renamed from: a */
    public static String m14733a(Uri uri) {
        return uri == null ? null : uri.toString();
    }

    /* renamed from: b */
    public static boolean m14771b(Uri uri) {
        return uri != null && (HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(uri.getScheme()) || C0845b.f2060a.equalsIgnoreCase(uri.getScheme()));
    }

    /* renamed from: c */
    public static boolean m14775c(Uri uri) {
        return uri != null && "content".equalsIgnoreCase(uri.getScheme());
    }

    /* renamed from: d */
    public static boolean m14779d(Uri uri) {
        return uri != null && Action.FILE_ATTRIBUTE.equalsIgnoreCase(uri.getScheme());
    }

    /* renamed from: e */
    public static long m14780e(Uri uri) {
        Throwable th;
        Cursor query;
        try {
            query = C1472c.f().getContentResolver().query(uri, null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("_size");
                query.moveToFirst();
                long j = query.getLong(columnIndex);
                if (query != null) {
                    query.close();
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static Date m14741a(Bundle bundle, String str, Date date) {
        if (bundle == null) {
            return null;
        }
        long longValue;
        Object obj = bundle.get(str);
        if (obj instanceof Long) {
            longValue = ((Long) obj).longValue();
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            try {
                longValue = Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        if (longValue == 0) {
            return new Date(Long.MAX_VALUE);
        }
        return new Date((longValue * 1000) + date.getTime());
    }

    /* renamed from: a */
    public static void m14749a(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    /* renamed from: a */
    public static Map<String, String> m14744a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return hashMap;
    }

    /* renamed from: a */
    public static boolean m14759a(AccessToken accessToken) {
        return accessToken != null ? accessToken.equals(AccessToken.m14270a()) : false;
    }

    /* renamed from: a */
    public static void m14752a(final String str, final C2953c c2953c) {
        JSONObject a = C3039q.m14702a(str);
        if (a != null) {
            c2953c.mo3690a(a);
            return;
        }
        C2942b c30422 = new C2942b() {
            /* renamed from: a */
            public void mo3687a(C2987f c2987f) {
                if (c2987f.m14499a() != null) {
                    c2953c.mo3689a(c2987f.m14499a().m14306f());
                    return;
                }
                C3039q.m14703a(str, c2987f.m14500b());
                c2953c.mo3690a(c2987f.m14500b());
            }
        };
        GraphRequest g = C3048s.m14786g(str);
        g.m14372a(c30422);
        g.m14385j();
    }

    /* renamed from: d */
    public static JSONObject m14776d(String str) {
        JSONObject a = C3039q.m14702a(str);
        if (a != null) {
            return a;
        }
        C2987f i = C3048s.m14786g(str).m14384i();
        if (i.m14499a() != null) {
            return null;
        }
        return i.m14500b();
    }

    /* renamed from: g */
    private static GraphRequest m14786g(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString("access_token", str);
        return new GraphRequest(null, "me", bundle, HttpMethod.GET, null);
    }

    /* renamed from: b */
    private static int m14763b() {
        if (f13635d > 0) {
            return f13635d;
        }
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new C30433());
            if (listFiles != null) {
                f13635d = listFiles.length;
            }
        } catch (Exception e) {
        }
        if (f13635d <= 0) {
            f13635d = Math.max(Runtime.getRuntime().availableProcessors(), 1);
        }
        return f13635d;
    }

    /* renamed from: d */
    private static void m14777d(Context context) {
        if (f13636e == -1 || System.currentTimeMillis() - f13636e >= 1800000) {
            f13636e = System.currentTimeMillis();
            C3048s.m14774c();
            C3048s.m14783e(context);
            C3048s.m14785f();
            C3048s.m14782e();
        }
    }

    /* renamed from: c */
    private static void m14774c() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            f13639h = timeZone.getDisplayName(timeZone.inDaylightTime(new Date()), 0);
            f13640i = timeZone.getID();
        } catch (Exception e) {
        }
    }

    /* renamed from: e */
    private static void m14783e(Context context) {
        if (f13641j.equals("NoCarrier")) {
            try {
                f13641j = ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getNetworkOperatorName();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: d */
    private static boolean m14778d() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* renamed from: e */
    private static void m14782e() {
        try {
            if (C3048s.m14778d()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                f13638g = ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
            }
            f13638g = C3048s.m14723a((double) f13638g);
        } catch (Exception e) {
        }
    }

    /* renamed from: f */
    private static void m14785f() {
        try {
            if (C3048s.m14778d()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                f13637f = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
            }
            f13637f = C3048s.m14723a((double) f13637f);
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static long m14723a(double d) {
        return Math.round(d / 1.073741824E9d);
    }

    /* renamed from: a */
    public static C3047e m14728a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONObject("permissions").getJSONArray(C0861d.f2139k);
        List arrayList = new ArrayList(jSONArray.length());
        List arrayList2 = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("permission");
            if (!(optString == null || optString.equals("installed"))) {
                String optString2 = optJSONObject.optString("status");
                if (optString2 != null) {
                    if (optString2.equals("granted")) {
                        arrayList.add(optString);
                    } else if (optString2.equals("declined")) {
                        arrayList2.add(optString);
                    }
                }
            }
        }
        return new C3047e(arrayList, arrayList2);
    }

    /* renamed from: a */
    public static String m14731a(int i) {
        return new BigInteger(i * 5, new Random()).toString(32);
    }
}
