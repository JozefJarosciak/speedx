package com.baidu.platform.comapi.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.storage.StorageManager;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.baidu.platform.comapi.util.d */
public final class C1280d {
    /* renamed from: a */
    private static volatile C1280d f3882a = null;
    /* renamed from: b */
    private boolean f3883b = false;
    /* renamed from: c */
    private boolean f3884c = true;
    /* renamed from: d */
    private final List<C1279c> f3885d = new ArrayList();
    /* renamed from: e */
    private C1279c f3886e = null;
    /* renamed from: f */
    private String f3887f;

    private C1280d() {
    }

    /* renamed from: a */
    public static C1280d m4838a() {
        if (f3882a == null) {
            synchronized (C1280d.class) {
                if (f3882a == null) {
                    f3882a = new C1280d();
                }
            }
        }
        return f3882a;
    }

    /* renamed from: a */
    private boolean m4839a(String str) {
        boolean createNewFile;
        Exception e;
        try {
            File file = new File(str + "/test.0");
            if (file.exists()) {
                file.delete();
            }
            createNewFile = file.createNewFile();
            try {
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return createNewFile;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            createNewFile = false;
            e = exception;
            e.printStackTrace();
            return createNewFile;
        }
        return createNewFile;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(14)
    /* renamed from: c */
    private void m4840c(Context context) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = storageManager.getClass().getMethod("getVolumeState", new Class[]{String.class});
            Class cls = Class.forName("android.os.storage.StorageVolume");
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Method method4 = cls.getMethod("getPath", new Class[0]);
            Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
            if (objArr != null) {
                for (Object obj : objArr) {
                    String str = (String) method4.invoke(obj, new Object[0]);
                    if (str != null && str.length() > 0) {
                        if ("mounted".equals(method2.invoke(storageManager, new Object[]{str}))) {
                            Object obj2 = !((Boolean) method3.invoke(obj, new Object[0])).booleanValue() ? 1 : null;
                            if (VERSION.SDK_INT <= 19 && m4839a(str)) {
                                this.f3885d.add(new C1279c(str, obj2 == null, obj2 != null ? "内置存储卡" : "外置存储卡", context));
                            } else if (VERSION.SDK_INT >= 19 && new File(str + File.separator + "BaiduMapSDKNew").exists() && str.equals(context.getSharedPreferences("map_pref", 0).getString("PREFFERED_SD_CARD", ""))) {
                                this.f3887f = str + File.separator + "BaiduMapSDKNew";
                            }
                        }
                    }
                }
                if (VERSION.SDK_INT >= 19) {
                    File[] externalFilesDirs = context.getExternalFilesDirs(null);
                    Collection arrayList = new ArrayList();
                    arrayList.addAll(this.f3885d);
                    int i = 0;
                    while (i < externalFilesDirs.length && externalFilesDirs[i] != null) {
                        Object obj3;
                        String absolutePath = externalFilesDirs[i].getAbsolutePath();
                        for (C1279c a : this.f3885d) {
                            if (absolutePath.startsWith(a.m4834a())) {
                                obj3 = 1;
                                break;
                            }
                        }
                        obj3 = null;
                        String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                        if (!(str2 == null || r0 != null || absolutePath.indexOf(str2) == -1)) {
                            arrayList.add(new C1279c(absolutePath, true, "外置存储卡", context));
                        }
                        i++;
                    }
                    this.f3885d.clear();
                    this.f3885d.addAll(arrayList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: d */
    private void m4841d(android.content.Context r10) {
        /*
        r9 = this;
        r2 = 0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r1 = "/proc/mounts";
        r0.<init>(r1);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r1 = r0.exists();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r1 == 0) goto L_0x0056;
    L_0x0018:
        r1 = new java.util.Scanner;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
    L_0x001d:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        if (r0 == 0) goto L_0x0053;
    L_0x0023:
        r0 = r1.nextLine();	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        r5 = "/dev/block/vold/";
        r5 = r0.startsWith(r5);	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        if (r5 == 0) goto L_0x001d;
    L_0x002f:
        r5 = 9;
        r6 = 32;
        r0 = r0.replace(r5, r6);	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        r5 = " ";
        r0 = r0.split(r5);	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        if (r0 == 0) goto L_0x001d;
    L_0x003f:
        r5 = r0.length;	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        if (r5 <= 0) goto L_0x001d;
    L_0x0042:
        r5 = 1;
        r0 = r0[r5];	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        r3.add(r0);	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
        goto L_0x001d;
    L_0x0049:
        r0 = move-exception;
    L_0x004a:
        r0.printStackTrace();	 Catch:{ all -> 0x0118 }
        if (r1 == 0) goto L_0x0052;
    L_0x004f:
        r1.close();
    L_0x0052:
        return;
    L_0x0053:
        r1.close();	 Catch:{ Exception -> 0x0049, all -> 0x0115 }
    L_0x0056:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r1 = "/system/etc/vold.fstab";
        r0.<init>(r1);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r1 = r0.exists();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r1 == 0) goto L_0x00b2;
    L_0x0063:
        r1 = new java.util.Scanner;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
    L_0x0068:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        if (r0 == 0) goto L_0x00af;
    L_0x006e:
        r0 = r1.nextLine();	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        r5 = "dev_mount";
        r5 = r0.startsWith(r5);	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        if (r5 == 0) goto L_0x0068;
    L_0x007a:
        r5 = 9;
        r6 = 32;
        r0 = r0.replace(r5, r6);	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        r5 = " ";
        r0 = r0.split(r5);	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        if (r0 == 0) goto L_0x0068;
    L_0x008a:
        r5 = r0.length;	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        if (r5 <= 0) goto L_0x0068;
    L_0x008d:
        r5 = 2;
        r0 = r0[r5];	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        r5 = ":";
        r5 = r0.contains(r5);	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        if (r5 == 0) goto L_0x00a3;
    L_0x0098:
        r5 = 0;
        r6 = ":";
        r6 = r0.indexOf(r6);	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        r0 = r0.substring(r5, r6);	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
    L_0x00a3:
        r4.add(r0);	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
        goto L_0x0068;
    L_0x00a7:
        r0 = move-exception;
        r2 = r1;
    L_0x00a9:
        if (r2 == 0) goto L_0x00ae;
    L_0x00ab:
        r2.close();
    L_0x00ae:
        throw r0;
    L_0x00af:
        r1.close();	 Catch:{ Exception -> 0x0049, all -> 0x00a7 }
    L_0x00b2:
        r0 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r1 = r0.getAbsolutePath();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r0 = r9.f3885d;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r5 = new com.baidu.platform.comapi.util.c;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r6 = 0;
        r7 = "Auto";
        r5.<init>(r1, r6, r7, r10);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r0.add(r5);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r3 = r3.iterator();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
    L_0x00cb:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r0 == 0) goto L_0x010c;
    L_0x00d1:
        r0 = r3.next();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r5 = r4.contains(r0);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r5 == 0) goto L_0x00cb;
    L_0x00dd:
        r5 = r0.equals(r1);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r5 != 0) goto L_0x00cb;
    L_0x00e3:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r5.<init>(r0);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r6 = r5.exists();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r6 == 0) goto L_0x00cb;
    L_0x00ee:
        r6 = r5.isDirectory();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r6 == 0) goto L_0x00cb;
    L_0x00f4:
        r5 = r5.canWrite();	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        if (r5 == 0) goto L_0x00cb;
    L_0x00fa:
        r5 = r9.f3885d;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r6 = new com.baidu.platform.comapi.util.c;	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r7 = 0;
        r8 = "Auto";
        r6.<init>(r0, r7, r8, r10);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        r5.add(r6);	 Catch:{ Exception -> 0x0108, all -> 0x0113 }
        goto L_0x00cb;
    L_0x0108:
        r0 = move-exception;
        r1 = r2;
        goto L_0x004a;
    L_0x010c:
        if (r2 == 0) goto L_0x0052;
    L_0x010e:
        r2.close();
        goto L_0x0052;
    L_0x0113:
        r0 = move-exception;
        goto L_0x00a9;
    L_0x0115:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00a9;
    L_0x0118:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00a9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.util.d.d(android.content.Context):void");
    }

    /* renamed from: a */
    public void m4842a(Context context) {
        int i = 0;
        if (!this.f3883b) {
            this.f3883b = true;
            try {
                if (VERSION.SDK_INT >= 14) {
                    m4840c(context);
                } else {
                    m4841d(context);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (this.f3885d.size() > 0) {
                    C1279c c1279c = null;
                    for (C1279c c1279c2 : this.f3885d) {
                        C1279c c1279c3;
                        int i2;
                        if (new File(c1279c2.m4835b()).exists()) {
                            int i3 = i + 1;
                            c1279c3 = c1279c2;
                            i2 = i3;
                        } else {
                            i2 = i;
                            c1279c3 = c1279c;
                        }
                        c1279c = c1279c3;
                        i = i2;
                    }
                    if (i == 0) {
                        this.f3886e = m4845b(context);
                        if (this.f3886e == null) {
                            for (C1279c c1279c22 : this.f3885d) {
                                if (m4843a(context, c1279c22)) {
                                    this.f3886e = c1279c22;
                                    break;
                                }
                            }
                        }
                    } else if (i != 1) {
                        this.f3886e = m4845b(context);
                    } else if (m4843a(context, c1279c)) {
                        this.f3886e = c1279c;
                    }
                    if (this.f3886e == null) {
                        this.f3886e = (C1279c) this.f3885d.get(0);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (this.f3886e == null || !m4839a(this.f3886e.m4834a())) {
                    this.f3884c = false;
                    this.f3886e = new C1279c(context);
                    this.f3885d.clear();
                    this.f3885d.add(this.f3886e);
                    return;
                }
                File file = new File(this.f3886e.m4835b());
                if (!file.exists()) {
                    file.mkdirs();
                }
                file = new File(this.f3886e.m4836c());
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, ".nomedia");
                if (!file2.exists()) {
                    file2.createNewFile();
                }
            } catch (Exception e22) {
                e22.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public boolean m4843a(Context context, C1279c c1279c) {
        String a = c1279c.m4834a();
        if (!m4839a(a)) {
            return false;
        }
        Editor edit = context.getSharedPreferences("map_pref", 0).edit();
        edit.putString("PREFFERED_SD_CARD", a);
        return edit.commit();
    }

    /* renamed from: b */
    public C1279c m4844b() {
        return this.f3886e;
    }

    /* renamed from: b */
    public C1279c m4845b(Context context) {
        String string = context.getSharedPreferences("map_pref", 0).getString("PREFFERED_SD_CARD", "");
        if (string != null && string.length() > 0) {
            for (C1279c c1279c : this.f3885d) {
                if (c1279c.m4834a().equals(string)) {
                    return c1279c;
                }
            }
        }
        return null;
    }
}
