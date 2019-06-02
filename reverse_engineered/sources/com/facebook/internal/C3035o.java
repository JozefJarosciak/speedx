package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import ch.qos.logback.core.joran.action.Action;
import com.facebook.C1472c;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.login.DefaultAudience;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: NativeProtocol */
/* renamed from: com.facebook.internal.o */
public final class C3035o {
    /* renamed from: a */
    private static final String f13595a = C3035o.class.getName();
    /* renamed from: b */
    private static List<C3030d> f13596b = C3035o.m14691e();
    /* renamed from: c */
    private static Map<String, List<C3030d>> f13597c = C3035o.m14693f();
    /* renamed from: d */
    private static AtomicBoolean f13598d = new AtomicBoolean(false);
    /* renamed from: e */
    private static final List<Integer> f13599e = Arrays.asList(new Integer[]{Integer.valueOf(20160327), Integer.valueOf(20141218), Integer.valueOf(20141107), Integer.valueOf(20141028), Integer.valueOf(20141001), Integer.valueOf(20140701), Integer.valueOf(20140324), Integer.valueOf(20140204), Integer.valueOf(20131107), Integer.valueOf(20130618), Integer.valueOf(20130502), Integer.valueOf(20121101)});

    /* compiled from: NativeProtocol */
    /* renamed from: com.facebook.internal.o$1 */
    static class C30291 implements Runnable {
        C30291() {
        }

        public void run() {
            try {
                for (C3030d a : C3035o.f13596b) {
                    a.m14649a(true);
                }
            } finally {
                C3035o.f13598d.set(false);
            }
        }
    }

    /* compiled from: NativeProtocol */
    /* renamed from: com.facebook.internal.o$d */
    private static abstract class C3030d {
        /* renamed from: a */
        private static final HashSet<String> f13593a = C3030d.m14650d();
        /* renamed from: b */
        private TreeSet<Integer> f13594b;

        /* renamed from: a */
        protected abstract String mo3696a();

        /* renamed from: b */
        protected abstract String mo3697b();

        private C3030d() {
        }

        /* renamed from: d */
        private static HashSet<String> m14650d() {
            HashSet<String> hashSet = new HashSet();
            hashSet.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
            hashSet.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
            hashSet.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
            return hashSet;
        }

        /* renamed from: a */
        public boolean m14652a(Context context, String str) {
            String str2 = Build.BRAND;
            int i = context.getApplicationInfo().flags;
            if (str2.startsWith("generic") && (i & 2) != 0) {
                return true;
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
                if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                    return false;
                }
                for (Signature toByteArray : packageInfo.signatures) {
                    if (!f13593a.contains(C3048s.m14737a(toByteArray.toByteArray()))) {
                        return false;
                    }
                }
                return true;
            } catch (NameNotFoundException e) {
                return false;
            }
        }

        /* renamed from: c */
        public TreeSet<Integer> m14654c() {
            if (this.f13594b == null) {
                m14649a(false);
            }
            return this.f13594b;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: a */
        private synchronized void m14649a(boolean r2) {
            /*
            r1 = this;
            monitor-enter(r1);
            if (r2 != 0) goto L_0x0007;
        L_0x0003:
            r0 = r1.f13594b;	 Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x000d;
        L_0x0007:
            r0 = com.facebook.internal.C3035o.m14683b(r1);	 Catch:{ all -> 0x000f }
            r1.f13594b = r0;	 Catch:{ all -> 0x000f }
        L_0x000d:
            monitor-exit(r1);
            return;
        L_0x000f:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.o.d.a(boolean):void");
        }
    }

    /* compiled from: NativeProtocol */
    /* renamed from: com.facebook.internal.o$a */
    private static class C3031a extends C3030d {
        private C3031a() {
            super();
        }

        /* renamed from: a */
        protected String mo3696a() {
            return "com.facebook.lite";
        }

        /* renamed from: b */
        protected String mo3697b() {
            return "com.facebook.lite.platform.LoginGDPDialogActivity";
        }
    }

    /* compiled from: NativeProtocol */
    /* renamed from: com.facebook.internal.o$b */
    private static class C3032b extends C3030d {
        private C3032b() {
            super();
        }

        /* renamed from: a */
        protected String mo3696a() {
            return "com.facebook.katana";
        }

        /* renamed from: b */
        protected String mo3697b() {
            return "com.facebook.katana.ProxyAuth";
        }
    }

    /* compiled from: NativeProtocol */
    /* renamed from: com.facebook.internal.o$c */
    private static class C3033c extends C3030d {
        private C3033c() {
            super();
        }

        /* renamed from: a */
        protected String mo3696a() {
            return "com.facebook.orca";
        }

        /* renamed from: b */
        protected String mo3697b() {
            return null;
        }
    }

    /* compiled from: NativeProtocol */
    /* renamed from: com.facebook.internal.o$e */
    private static class C3034e extends C3030d {
        private C3034e() {
            super();
        }

        /* renamed from: a */
        protected String mo3696a() {
            return "com.facebook.wakizashi";
        }

        /* renamed from: b */
        protected String mo3697b() {
            return "com.facebook.katana.ProxyAuth";
        }
    }

    /* renamed from: e */
    private static List<C3030d> m14691e() {
        List<C3030d> arrayList = new ArrayList();
        arrayList.add(new C3032b());
        arrayList.add(new C3034e());
        return arrayList;
    }

    /* renamed from: f */
    private static Map<String, List<C3030d>> m14693f() {
        Map<String, List<C3030d>> hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C3033c());
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", f13596b);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", f13596b);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", f13596b);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", f13596b);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", arrayList);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", arrayList);
        return hashMap;
    }

    /* renamed from: a */
    static Intent m14669a(Context context, Intent intent, C3030d c3030d) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return null;
        }
        if (c3030d.m14652a(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    /* renamed from: b */
    static Intent m14681b(Context context, Intent intent, C3030d c3030d) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null) {
            return null;
        }
        if (c3030d.m14652a(context, resolveService.serviceInfo.packageName)) {
            return intent;
        }
        return null;
    }

    /* renamed from: a */
    public static Intent m14672a(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3) {
        C3030d c3031a = new C3031a();
        return C3035o.m14669a(context, C3035o.m14674a(c3031a, str, (Collection) collection, str2, z, z2, defaultAudience, str3), c3031a);
    }

    /* renamed from: a */
    private static Intent m14674a(C3030d c3030d, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3) {
        String b = c3030d.mo3697b();
        if (b == null) {
            return null;
        }
        Intent putExtra = new Intent().setClassName(c3030d.mo3696a(), b).putExtra("client_id", str);
        if (!C3048s.m14762a((Collection) collection)) {
            putExtra.putExtra(Action.SCOPE_ATTRIBUTE, TextUtils.join(",", collection));
        }
        if (!C3048s.m14761a(str2)) {
            putExtra.putExtra("e2e", str2);
        }
        putExtra.putExtra("state", str3);
        putExtra.putExtra("response_type", "token,signed_request");
        putExtra.putExtra("return_scopes", "true");
        if (z2) {
            putExtra.putExtra("default_audience", defaultAudience.getNativeProtocolAudience());
        }
        putExtra.putExtra("legacy_override", "v2.7");
        putExtra.putExtra("auth_type", "rerequest");
        return putExtra;
    }

    /* renamed from: b */
    public static Intent m14682b(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, DefaultAudience defaultAudience, String str3) {
        for (C3030d c3030d : f13596b) {
            Intent a = C3035o.m14669a(context, C3035o.m14674a(c3030d, str, (Collection) collection, str2, z, z2, defaultAudience, str3), c3030d);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static final int m14663a() {
        return ((Integer) f13599e.get(0)).intValue();
    }

    /* renamed from: a */
    private static Intent m14670a(Context context, String str, String str2) {
        List<C3030d> list = (List) f13597c.get(str2);
        if (list == null) {
            return null;
        }
        Intent intent = null;
        for (C3030d c3030d : list) {
            intent = C3035o.m14669a(context, new Intent().setAction(str).setPackage(c3030d.mo3696a()).addCategory("android.intent.category.DEFAULT"), c3030d);
            if (intent != null) {
                return intent;
            }
        }
        return intent;
    }

    /* renamed from: a */
    public static boolean m14679a(int i) {
        return f13599e.contains(Integer.valueOf(i)) && i >= 20140701;
    }

    /* renamed from: a */
    public static Intent m14671a(Context context, String str, String str2, int i, Bundle bundle) {
        Intent a = C3035o.m14670a(context, "com.facebook.platform.PLATFORM_ACTIVITY", str2);
        if (a == null) {
            return null;
        }
        C3035o.m14678a(a, str, str2, i, bundle);
        return a;
    }

    /* renamed from: a */
    public static void m14678a(Intent intent, String str, String str2, int i, Bundle bundle) {
        String i2 = C1472c.i();
        String j = C1472c.j();
        intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", i).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", str2).putExtra("com.facebook.platform.extra.APPLICATION_ID", i2);
        if (C3035o.m14679a(i)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", str);
            C3048s.m14748a(bundle2, "app_name", j);
            intent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            intent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle);
            return;
        }
        intent.putExtra("com.facebook.platform.protocol.CALL_ID", str);
        if (!C3048s.m14761a(j)) {
            intent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", j);
        }
        intent.putExtras(bundle);
    }

    /* renamed from: a */
    public static Intent m14673a(Intent intent, Bundle bundle, FacebookException facebookException) {
        UUID b = C3035o.m14684b(intent);
        if (b == null) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", C3035o.m14664a(intent));
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", b.toString());
        if (facebookException != null) {
            bundle2.putBundle("error", C3035o.m14675a(facebookException));
        }
        intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
        if (bundle == null) {
            return intent2;
        }
        intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
        return intent2;
    }

    /* renamed from: a */
    public static Intent m14668a(Context context) {
        for (C3030d c3030d : f13596b) {
            Intent b = C3035o.m14681b(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(c3030d.mo3696a()).addCategory("android.intent.category.DEFAULT"), c3030d);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static int m14664a(Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }

    /* renamed from: b */
    public static UUID m14684b(Intent intent) {
        UUID uuid = null;
        if (intent != null) {
            String string;
            if (C3035o.m14679a(C3035o.m14664a(intent))) {
                Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
                if (bundleExtra != null) {
                    string = bundleExtra.getString("action_id");
                } else {
                    Object obj = uuid;
                }
            } else {
                string = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
            }
            if (string != null) {
                try {
                    uuid = UUID.fromString(string);
                } catch (IllegalArgumentException e) {
                }
            }
        }
        return uuid;
    }

    /* renamed from: c */
    public static Bundle m14687c(Intent intent) {
        if (C3035o.m14679a(C3035o.m14664a(intent))) {
            return intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
        }
        return null;
    }

    /* renamed from: d */
    public static Bundle m14689d(Intent intent) {
        if (C3035o.m14679a(C3035o.m14664a(intent))) {
            return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
        }
        return intent.getExtras();
    }

    /* renamed from: e */
    public static boolean m14692e(Intent intent) {
        Bundle c = C3035o.m14687c(intent);
        if (c != null) {
            return c.containsKey("error");
        }
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }

    /* renamed from: a */
    public static FacebookException m14676a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("error_type");
        if (string == null) {
            string = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String string2 = bundle.getString("error_description");
        if (string2 == null) {
            string2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (string == null || !string.equalsIgnoreCase("UserCanceled")) {
            return new FacebookException(string2);
        }
        return new FacebookOperationCanceledException(string2);
    }

    /* renamed from: a */
    public static Bundle m14675a(FacebookException facebookException) {
        if (facebookException == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("error_description", facebookException.toString());
        if (!(facebookException instanceof FacebookOperationCanceledException)) {
            return bundle;
        }
        bundle.putString("error_type", "UserCanceled");
        return bundle;
    }

    /* renamed from: b */
    public static int m14680b(int i) {
        return C3035o.m14666a(f13596b, new int[]{i});
    }

    /* renamed from: a */
    public static int m14665a(String str, int[] iArr) {
        return C3035o.m14666a((List) f13597c.get(str), iArr);
    }

    /* renamed from: a */
    private static int m14666a(List<C3030d> list, int[] iArr) {
        C3035o.m14685b();
        if (list == null) {
            return -1;
        }
        for (C3030d c : list) {
            int a = C3035o.m14667a(c.m14654c(), C3035o.m14663a(), iArr);
            if (a != -1) {
                return a;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public static void m14685b() {
        if (f13598d.compareAndSet(false, true)) {
            C1472c.d().execute(new C30291());
        }
    }

    /* renamed from: b */
    private static TreeSet<Integer> m14683b(C3030d c3030d) {
        Cursor query;
        Throwable th;
        TreeSet<Integer> treeSet = new TreeSet();
        ContentResolver contentResolver = C1472c.f().getContentResolver();
        String[] strArr = new String[]{MapboxEvent.ATTRIBUTE_VERSION};
        Uri c = C3035o.m14686c(c3030d);
        try {
            if (C1472c.f().getPackageManager().resolveContentProvider(c3030d.mo3696a() + ".provider.PlatformProvider", 0) != null) {
                try {
                    query = contentResolver.query(c, strArr, null, null, null);
                } catch (NullPointerException e) {
                    Log.e(f13595a, "Failed to query content resolver.");
                    query = null;
                    if (query != null) {
                        while (query.moveToNext()) {
                            try {
                                treeSet.add(Integer.valueOf(query.getInt(query.getColumnIndex(MapboxEvent.ATTRIBUTE_VERSION))));
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    return treeSet;
                } catch (SecurityException e2) {
                    Log.e(f13595a, "Failed to query content resolver.");
                    query = null;
                    if (query != null) {
                        while (query.moveToNext()) {
                            treeSet.add(Integer.valueOf(query.getInt(query.getColumnIndex(MapboxEvent.ATTRIBUTE_VERSION))));
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    return treeSet;
                }
                if (query != null) {
                    while (query.moveToNext()) {
                        treeSet.add(Integer.valueOf(query.getInt(query.getColumnIndex(MapboxEvent.ATTRIBUTE_VERSION))));
                    }
                }
            } else {
                query = null;
            }
            if (query != null) {
                query.close();
            }
            return treeSet;
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
    public static int m14667a(TreeSet<Integer> treeSet, int i, int[] iArr) {
        int length = iArr.length - 1;
        Iterator descendingIterator = treeSet.descendingIterator();
        int i2 = -1;
        int i3 = length;
        while (descendingIterator.hasNext()) {
            int intValue = ((Integer) descendingIterator.next()).intValue();
            length = Math.max(i2, intValue);
            i2 = i3;
            while (i2 >= 0 && iArr[i2] > intValue) {
                i2--;
            }
            if (i2 < 0) {
                return -1;
            }
            if (iArr[i2] != intValue) {
                i3 = i2;
                i2 = length;
            } else if (i2 % 2 == 0) {
                return Math.min(length, i);
            } else {
                return -1;
            }
        }
        return -1;
    }

    /* renamed from: c */
    private static Uri m14686c(C3030d c3030d) {
        return Uri.parse("content://" + c3030d.mo3696a() + ".provider.PlatformProvider/versions");
    }
}
