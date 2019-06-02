package com.baidu.android.bbalbs.common.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.system.Os;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.android.bbalbs.common.p040a.C1007a;
import com.baidu.android.bbalbs.common.p040a.C1008b;
import com.baidu.android.bbalbs.common.p040a.C1009c;
import com.baidu.android.bbalbs.common.p040a.C1010d;
import com.facebook.stetho.dumpapp.Framer;
import com.j256.ormlite.stmt.query.SimpleComparison;
import io.rong.imlib.statistics.UserData;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.android.bbalbs.common.util.b */
public final class C1015b {
    /* renamed from: a */
    private static final String f2259a;
    /* renamed from: e */
    private static C1013b f2260e;
    /* renamed from: b */
    private final Context f2261b;
    /* renamed from: c */
    private int f2262c = 0;
    /* renamed from: d */
    private PublicKey f2263d;

    /* renamed from: com.baidu.android.bbalbs.common.util.b$a */
    private static class C1012a {
        /* renamed from: a */
        public ApplicationInfo f2252a;
        /* renamed from: b */
        public int f2253b;
        /* renamed from: c */
        public boolean f2254c;
        /* renamed from: d */
        public boolean f2255d;

        private C1012a() {
            this.f2253b = 0;
            this.f2254c = false;
            this.f2255d = false;
        }
    }

    /* renamed from: com.baidu.android.bbalbs.common.util.b$b */
    private static class C1013b {
        /* renamed from: a */
        public String f2256a;
        /* renamed from: b */
        public String f2257b;
        /* renamed from: c */
        public int f2258c;

        private C1013b() {
            this.f2258c = 2;
        }

        /* renamed from: a */
        public static C1013b m3536a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                Object string = jSONObject.getString("deviceid");
                String string2 = jSONObject.getString("imei");
                int i = jSONObject.getInt("ver");
                if (TextUtils.isEmpty(string) || string2 == null) {
                    return null;
                }
                C1013b c1013b = new C1013b();
                c1013b.f2256a = string;
                c1013b.f2257b = string2;
                c1013b.f2258c = i;
                return c1013b;
            } catch (Throwable e) {
                C1015b.m3555b(e);
                return null;
            }
        }

        /* renamed from: a */
        public String m3537a() {
            try {
                return new JSONObject().put("deviceid", this.f2256a).put("imei", this.f2257b).put("ver", this.f2258c).toString();
            } catch (Throwable e) {
                C1015b.m3555b(e);
                return null;
            }
        }

        /* renamed from: b */
        public String m3538b() {
            String str = this.f2257b;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.f2256a + "|" + new StringBuffer(str).reverse().toString();
        }
    }

    /* renamed from: com.baidu.android.bbalbs.common.util.b$c */
    static class C1014c {
        /* renamed from: a */
        static boolean m3539a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (Throwable e) {
                C1015b.m3555b(e);
                return false;
            }
        }
    }

    static {
        String str = new String(C1008b.m3529a(new byte[]{(byte) 77, (byte) 122, (byte) 65, (byte) 121, (byte) 77, (byte) 84, (byte) 73, Framer.EXIT_FRAME_PREFIX, (byte) 77, (byte) 68, (byte) 73, (byte) 61}));
        f2259a = str + new String(C1008b.m3529a(new byte[]{(byte) 90, (byte) 71, (byte) 108, (byte) 106, (byte) 100, (byte) 87, (byte) 82, (byte) 112, (byte) 89, (byte) 87, (byte) 73, (byte) 61}));
    }

    private C1015b(Context context) {
        this.f2261b = context.getApplicationContext();
        m3544a();
    }

    /* renamed from: a */
    public static String m3540a(Context context) {
        return C1015b.m3556c(context).m3538b();
    }

    /* renamed from: a */
    private static String m3541a(File file) {
        FileReader fileReader;
        Throwable e;
        Throwable th;
        String str = null;
        try {
            fileReader = new FileReader(file);
            try {
                char[] cArr = new char[8192];
                CharArrayWriter charArrayWriter = new CharArrayWriter();
                while (true) {
                    int read = fileReader.read(cArr);
                    if (read <= 0) {
                        break;
                    }
                    charArrayWriter.write(cArr, 0, read);
                }
                str = charArrayWriter.toString();
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable e2) {
                        C1015b.m3555b(e2);
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    C1015b.m3555b(e2);
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable e22) {
                            C1015b.m3555b(e22);
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable e222) {
                            C1015b.m3555b(e222);
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e222 = e4;
            Object obj = str;
            C1015b.m3555b(e222);
            if (fileReader != null) {
                fileReader.close();
            }
            return str;
        } catch (Throwable e2222) {
            fileReader = str;
            th = e2222;
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
        return str;
    }

    /* renamed from: a */
    private static String m3542a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String str = "";
        str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            str = toHexString.length() == 1 ? str + "0" + toHexString : str + toHexString;
        }
        return str.toLowerCase();
    }

    /* renamed from: a */
    private List<C1012a> m3543a(Intent intent, boolean z) {
        List<C1012a> arrayList = new ArrayList();
        PackageManager packageManager = this.f2261b.getPackageManager();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (!(resolveInfo.activityInfo == null || resolveInfo.activityInfo.applicationInfo == null)) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            Object string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] a = C1008b.m3529a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(a));
                                C1012a c1012a = new C1012a();
                                c1012a.f2253b = jSONObject.getInt("priority");
                                c1012a.f2252a = resolveInfo.activityInfo.applicationInfo;
                                if (this.f2261b.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    c1012a.f2255d = true;
                                }
                                if (z) {
                                    Object string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        int i;
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (m3548a(strArr, m3550a(packageInfo.signatures))) {
                                            byte[] a2 = C1015b.m3549a(C1008b.m3529a(string2.getBytes()), this.f2263d);
                                            i = (a2 == null || !Arrays.equals(a2, C1010d.m3533a(a))) ? 0 : 1;
                                            if (i != 0) {
                                                c1012a.f2254c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(c1012a);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new C1016c(this));
        return arrayList;
    }

    /* renamed from: a */
    private void m3544a() {
        ByteArrayInputStream byteArrayInputStream;
        Throwable e;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(C1011a.m3535a());
            try {
                this.f2263d = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e2) {
                        C1015b.m3555b(e2);
                    }
                }
            } catch (Exception e3) {
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e22) {
                        C1015b.m3555b(e22);
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                byteArrayInputStream2 = byteArrayInputStream;
                e22 = th2;
                if (byteArrayInputStream2 != null) {
                    try {
                        byteArrayInputStream2.close();
                    } catch (Throwable th3) {
                        C1015b.m3555b(th3);
                    }
                }
                throw e22;
            }
        } catch (Exception e4) {
            byteArrayInputStream = null;
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
        } catch (Throwable th4) {
            e22 = th4;
            if (byteArrayInputStream2 != null) {
                byteArrayInputStream2.close();
            }
            throw e22;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    private boolean m3546a(java.lang.String r7) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        r3 = 0;
        r0 = android.os.Build.VERSION.SDK_INT;
        r4 = 24;
        if (r0 < r4) goto L_0x003b;
    L_0x0009:
        r0 = r1;
    L_0x000a:
        r4 = r6.f2261b;	 Catch:{ Exception -> 0x0042, all -> 0x0053 }
        r5 = "libcuid.so";
        r3 = r4.openFileOutput(r5, r0);	 Catch:{ Exception -> 0x0042, all -> 0x0053 }
        r4 = r7.getBytes();	 Catch:{ Exception -> 0x0062, all -> 0x0053 }
        r3.write(r4);	 Catch:{ Exception -> 0x0062, all -> 0x0053 }
        r3.flush();	 Catch:{ Exception -> 0x0062, all -> 0x0053 }
        if (r3 == 0) goto L_0x0021;
    L_0x001e:
        r3.close();	 Catch:{ Exception -> 0x003d }
    L_0x0021:
        if (r0 != 0) goto L_0x003a;
    L_0x0023:
        r0 = 436; // 0x1b4 float:6.11E-43 double:2.154E-321;
        r1 = new java.io.File;
        r2 = r6.f2261b;
        r2 = r2.getFilesDir();
        r3 = "libcuid.so";
        r1.<init>(r2, r3);
        r1 = r1.getAbsolutePath();
        r2 = com.baidu.android.bbalbs.common.util.C1015b.C1014c.m3539a(r1, r0);
    L_0x003a:
        return r2;
    L_0x003b:
        r0 = r2;
        goto L_0x000a;
    L_0x003d:
        r1 = move-exception;
        com.baidu.android.bbalbs.common.util.C1015b.m3555b(r1);
        goto L_0x0021;
    L_0x0042:
        r0 = move-exception;
        r2 = r3;
    L_0x0044:
        com.baidu.android.bbalbs.common.util.C1015b.m3555b(r0);	 Catch:{ all -> 0x005f }
        if (r2 == 0) goto L_0x004c;
    L_0x0049:
        r2.close();	 Catch:{ Exception -> 0x004e }
    L_0x004c:
        r2 = r1;
        goto L_0x003a;
    L_0x004e:
        r0 = move-exception;
        com.baidu.android.bbalbs.common.util.C1015b.m3555b(r0);
        goto L_0x004c;
    L_0x0053:
        r0 = move-exception;
    L_0x0054:
        if (r3 == 0) goto L_0x0059;
    L_0x0056:
        r3.close();	 Catch:{ Exception -> 0x005a }
    L_0x0059:
        throw r0;
    L_0x005a:
        r1 = move-exception;
        com.baidu.android.bbalbs.common.util.C1015b.m3555b(r1);
        goto L_0x0059;
    L_0x005f:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0054;
    L_0x0062:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.bbalbs.common.util.b.a(java.lang.String):boolean");
    }

    /* renamed from: a */
    private boolean m3547a(String str, String str2) {
        try {
            return System.putString(this.f2261b.getContentResolver(), str, str2);
        } catch (Throwable e) {
            C1015b.m3555b(e);
            return false;
        }
    }

    /* renamed from: a */
    private boolean m3548a(String[] strArr, String[] strArr2) {
        int i = 0;
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (Object add : strArr) {
            hashSet.add(add);
        }
        HashSet hashSet2 = new HashSet();
        int length = strArr2.length;
        while (i < length) {
            hashSet2.add(strArr2[i]);
            i++;
        }
        return hashSet.equals(hashSet2);
    }

    /* renamed from: a */
    private static byte[] m3549a(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(2, publicKey);
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    private String[] m3550a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = C1015b.m3542a(C1010d.m3533a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    /* renamed from: b */
    private C1013b m3551b() {
        boolean z;
        C1012a c1012a;
        boolean z2;
        String str;
        String str2;
        C1013b a;
        C1013b e;
        String str3 = null;
        int i = 0;
        List a2 = m3543a(new Intent("com.baidu.intent.action.GALAXY").setPackage(this.f2261b.getPackageName()), true);
        int i2;
        if (a2 == null || a2.size() == 0) {
            for (i2 = 0; i2 < 3; i2++) {
                Log.w("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = false;
        } else {
            c1012a = (C1012a) a2.get(0);
            z2 = c1012a.f2254c;
            if (!c1012a.f2254c) {
                for (i2 = 0; i2 < 3; i2++) {
                    Log.w("DeviceId", "galaxy config err, In the release version of the signature should be matched");
                }
            }
            z = z2;
        }
        File file = new File(this.f2261b.getFilesDir(), "libcuid.so");
        C1013b a3 = file.exists() ? C1013b.m3536a(C1015b.m3563f(C1015b.m3541a(file))) : null;
        if (a3 == null) {
            this.f2262c |= 16;
            List<C1012a> a4 = m3543a(new Intent("com.baidu.intent.action.GALAXY"), z);
            if (a4 != null) {
                str = "files";
                file = this.f2261b.getFilesDir();
                if (str.equals(file.getName())) {
                    str2 = str;
                } else {
                    Log.e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + file.getAbsolutePath());
                    str2 = file.getName();
                }
                for (C1012a c1012a2 : a4) {
                    if (!c1012a2.f2255d) {
                        File file2 = new File(new File(c1012a2.f2252a.dataDir, str2), "libcuid.so");
                        if (file2.exists()) {
                            a = C1013b.m3536a(C1015b.m3563f(C1015b.m3541a(file2)));
                            if (a != null) {
                                break;
                            }
                        }
                        a = a3;
                        a3 = a;
                    }
                }
            }
        }
        a = a3;
        if (a == null) {
            a = C1013b.m3536a(C1015b.m3563f(m3553b("com.baidu.deviceid.v2")));
        }
        boolean c = m3558c("android.permission.READ_EXTERNAL_STORAGE");
        if (a == null && c) {
            this.f2262c |= 2;
            e = m3561e();
        } else {
            e = a;
        }
        if (e == null) {
            this.f2262c |= 8;
            e = m3559d();
        }
        if (e == null && c) {
            this.f2262c |= 1;
            str = m3565h("");
            e = m3560d(str);
            i = 1;
        } else {
            str = null;
        }
        if (e == null) {
            this.f2262c |= 4;
            if (i == 0) {
                str = m3565h("");
            }
            C1013b c1013b = new C1013b();
            str2 = C1015b.m3552b(this.f2261b);
            c1013b.f2256a = C1009c.m3532a((VERSION.SDK_INT < 23 ? str + str2 + UUID.randomUUID().toString() : "com.baidu" + str2).getBytes(), true);
            c1013b.f2257b = str;
            a = c1013b;
        } else {
            a = e;
        }
        file = new File(this.f2261b.getFilesDir(), "libcuid.so");
        if (!((this.f2262c & 16) == 0 && file.exists())) {
            str2 = TextUtils.isEmpty(null) ? C1015b.m3562e(a.m3537a()) : null;
            m3546a(str2);
            str3 = str2;
        }
        z2 = m3557c();
        if (z2 && ((this.f2262c & 2) != 0 || TextUtils.isEmpty(m3553b("com.baidu.deviceid.v2")))) {
            if (TextUtils.isEmpty(str3)) {
                str3 = C1015b.m3562e(a.m3537a());
            }
            m3547a("com.baidu.deviceid.v2", str3);
        }
        if (m3558c("android.permission.WRITE_EXTERNAL_STORAGE")) {
            File file3 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
            if (!((this.f2262c & 8) == 0 && file3.exists())) {
                if (TextUtils.isEmpty(str3)) {
                    str3 = C1015b.m3562e(a.m3537a());
                }
                C1015b.m3564g(str3);
            }
        }
        if (z2 && ((this.f2262c & 1) != 0 || TextUtils.isEmpty(m3553b("com.baidu.deviceid")))) {
            m3547a("com.baidu.deviceid", a.f2256a);
            m3547a("bd_setting_i", a.f2257b);
        }
        if (z2 && !TextUtils.isEmpty(a.f2257b)) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            if (!((this.f2262c & 2) == 0 && file.exists())) {
                C1015b.m3554b(a.f2257b, a.f2256a);
            }
        }
        return a;
    }

    /* renamed from: b */
    public static String m3552b(Context context) {
        String str = "";
        Object string = Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    /* renamed from: b */
    private String m3553b(String str) {
        try {
            return System.getString(this.f2261b.getContentResolver(), str);
        } catch (Throwable e) {
            C1015b.m3555b(e);
            return null;
        }
    }

    /* renamed from: b */
    private static void m3554b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(str2);
            File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
            File file2 = new File(file, ".cuid");
            try {
                if (file.exists() && !file.isDirectory()) {
                    File file3;
                    Random random = new Random();
                    File parentFile = file.getParentFile();
                    String name = file.getName();
                    do {
                        file3 = new File(parentFile, name + random.nextInt() + ".tmp");
                    } while (file3.exists());
                    file.renameTo(file3);
                    file3.delete();
                }
                file.mkdirs();
                FileWriter fileWriter = new FileWriter(file2, false);
                fileWriter.write(C1008b.m3528a(C1007a.m3526a(f2259a, f2259a, stringBuilder.toString().getBytes()), "utf-8"));
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: b */
    private static void m3555b(Throwable th) {
    }

    /* renamed from: c */
    private static C1013b m3556c(Context context) {
        if (f2260e == null) {
            synchronized (C1013b.class) {
                if (f2260e == null) {
                    SystemClock.uptimeMillis();
                    f2260e = new C1015b(context).m3551b();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return f2260e;
    }

    /* renamed from: c */
    private boolean m3557c() {
        return m3558c("android.permission.WRITE_SETTINGS");
    }

    /* renamed from: c */
    private boolean m3558c(String str) {
        return this.f2261b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* renamed from: d */
    private C1013b m3559d() {
        Object b = m3553b("com.baidu.deviceid");
        String b2 = m3553b("bd_setting_i");
        if (TextUtils.isEmpty(b2)) {
            b2 = m3565h("");
            if (!TextUtils.isEmpty(b2)) {
                m3547a("bd_setting_i", b2);
            }
        }
        if (TextUtils.isEmpty(b)) {
            b = m3553b(C1009c.m3532a(("com.baidu" + b2 + C1015b.m3552b(this.f2261b)).getBytes(), true));
        }
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        C1013b c1013b = new C1013b();
        c1013b.f2256a = b;
        c1013b.f2257b = b2;
        return c1013b;
    }

    /* renamed from: d */
    private C1013b m3560d(String str) {
        Object obj = null;
        Object obj2 = VERSION.SDK_INT < 23 ? 1 : null;
        if (obj2 != null && TextUtils.isEmpty(str)) {
            return null;
        }
        String str2;
        C1013b c1013b;
        Object obj3 = "";
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            int i = 1;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append("\r\n");
            }
            bufferedReader.close();
            String[] split = new String(C1007a.m3527b(f2259a, f2259a, C1008b.m3529a(stringBuilder.toString().getBytes()))).split(SimpleComparison.EQUAL_TO_OPERATION);
            if (split != null && split.length == 2) {
                if (obj2 != null && str.equals(split[0])) {
                    obj3 = split[1];
                    str2 = str;
                    if (obj == null) {
                        C1015b.m3554b(str2, obj3);
                    }
                    if (!TextUtils.isEmpty(obj3)) {
                        return null;
                    }
                    c1013b = new C1013b();
                    c1013b.f2256a = obj3;
                    c1013b.f2257b = str2;
                    return c1013b;
                } else if (obj2 == null) {
                    if (TextUtils.isEmpty(str)) {
                        str = split[1];
                    }
                    obj3 = split[1];
                    str2 = str;
                    if (obj == null) {
                        try {
                            C1015b.m3554b(str2, obj3);
                        } catch (FileNotFoundException e) {
                        } catch (IOException e2) {
                        } catch (Exception e3) {
                        }
                    }
                    if (!TextUtils.isEmpty(obj3)) {
                        return null;
                    }
                    c1013b = new C1013b();
                    c1013b.f2256a = obj3;
                    c1013b.f2257b = str2;
                    return c1013b;
                }
            }
            str2 = str;
            if (obj == null) {
                C1015b.m3554b(str2, obj3);
            }
        } catch (FileNotFoundException e4) {
            str2 = str;
        } catch (IOException e5) {
            str2 = str;
        } catch (Exception e6) {
            str2 = str;
        }
        if (!TextUtils.isEmpty(obj3)) {
            return null;
        }
        c1013b = new C1013b();
        c1013b.f2256a = obj3;
        c1013b.f2257b = str2;
        return c1013b;
    }

    /* renamed from: e */
    private C1013b m3561e() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            Object a = C1015b.m3541a(file);
            if (!TextUtils.isEmpty(a)) {
                try {
                    return C1013b.m3536a(new String(C1007a.m3527b(f2259a, f2259a, C1008b.m3529a(a.getBytes()))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: e */
    private static String m3562e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return C1008b.m3528a(C1007a.m3526a(f2259a, f2259a, str.getBytes()), "utf-8");
        } catch (Throwable e) {
            C1015b.m3555b(e);
            return "";
        } catch (Throwable e2) {
            C1015b.m3555b(e2);
            return "";
        }
    }

    /* renamed from: f */
    private static String m3563f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(C1007a.m3527b(f2259a, f2259a, C1008b.m3529a(str.getBytes())));
        } catch (Throwable e) {
            C1015b.m3555b(e);
            return "";
        }
    }

    /* renamed from: g */
    private static void m3564g(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file2 = new File(file, ".cuid2");
        try {
            if (file.exists() && !file.isDirectory()) {
                File file3;
                Random random = new Random();
                File parentFile = file.getParentFile();
                String name = file.getName();
                do {
                    file3 = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file3.exists());
                file.renameTo(file3);
                file3.delete();
            }
            file.mkdirs();
            FileWriter fileWriter = new FileWriter(file2, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
        } catch (Exception e2) {
        }
    }

    /* renamed from: h */
    private String m3565h(String str) {
        String deviceId;
        CharSequence i;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f2261b.getSystemService(UserData.PHONE_KEY);
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
                i = C1015b.m3566i(deviceId);
                return TextUtils.isEmpty(i) ? str : i;
            }
        } catch (Throwable e) {
            Log.e("DeviceId", "Read IMEI failed", e);
        }
        deviceId = null;
        i = C1015b.m3566i(deviceId);
        if (TextUtils.isEmpty(i)) {
        }
    }

    /* renamed from: i */
    private static String m3566i(String str) {
        return (str == null || !str.contains(":")) ? str : "";
    }
}
