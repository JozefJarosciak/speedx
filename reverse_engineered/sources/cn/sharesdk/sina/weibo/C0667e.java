package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.utils.Data;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONObject;

/* compiled from: WeiboAppManager */
/* renamed from: cn.sharesdk.sina.weibo.e */
public class C0667e {
    /* renamed from: a */
    private static final Uri f1576a = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    /* renamed from: b */
    private static C0667e f1577b;
    /* renamed from: c */
    private static C0666a f1578c = null;
    /* renamed from: d */
    private Context f1579d;

    /* compiled from: WeiboAppManager */
    /* renamed from: cn.sharesdk.sina.weibo.e$a */
    public static class C0666a {
        /* renamed from: a */
        private String f1574a;
        /* renamed from: b */
        private int f1575b;

        /* renamed from: a */
        private void m2566a(String str) {
            this.f1574a = str;
        }

        /* renamed from: a */
        public String m2567a() {
            return this.f1574a;
        }

        /* renamed from: a */
        private void m2563a(int i) {
            this.f1575b = i;
        }

        /* renamed from: b */
        public int m2568b() {
            return this.f1575b;
        }

        public String toString() {
            return "WeiboInfo: PackageName = " + this.f1574a + ", supportApi = " + this.f1575b;
        }
    }

    private C0667e(Context context) {
        this.f1579d = context.getApplicationContext();
    }

    /* renamed from: a */
    public static synchronized C0667e m2569a(Context context) {
        C0667e c0667e;
        synchronized (C0667e.class) {
            if (f1577b == null) {
                f1577b = new C0667e(context);
            }
            c0667e = f1577b;
        }
        return c0667e;
    }

    /* renamed from: a */
    public synchronized String m2576a() {
        if (f1578c == null) {
            f1578c = m2572b(this.f1579d);
        }
        return f1578c.m2567a();
    }

    /* renamed from: b */
    private C0666a m2572b(Context context) {
        Object obj = 1;
        C0666a c = m2573c(context);
        C0666a d = m2574d(context);
        Object obj2 = c != null ? 1 : null;
        if (d == null) {
            obj = null;
        }
        if (obj2 == null || obj == null) {
            if (obj2 != null) {
                return c;
            }
            if (obj != null) {
                return d;
            }
            return null;
        } else if (c.m2568b() >= d.m2568b()) {
            return c;
        } else {
            return d;
        }
    }

    /* renamed from: c */
    private C0666a m2573c(Context context) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(f1576a, null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            try {
                int columnIndex = query.getColumnIndex("support_api");
                int columnIndex2 = query.getColumnIndex(OnlineConfigAgent.KEY_PACKAGE);
                if (query.moveToFirst()) {
                    int i = -1;
                    try {
                        columnIndex = Integer.parseInt(query.getString(columnIndex));
                    } catch (Throwable e2) {
                        C0621d.m2279a().d(e2);
                        columnIndex = i;
                    }
                    String string = query.getString(columnIndex2);
                    if (!TextUtils.isEmpty(string) && C0667e.m2570a(context, string)) {
                        C0666a c0666a = new C0666a();
                        c0666a.m2566a(string);
                        c0666a.m2563a(columnIndex);
                        if (query == null) {
                            return c0666a;
                        }
                        query.close();
                        return c0666a;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    C0621d.m2279a().e(e.getMessage(), new Object[0]);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            return null;
        } catch (Exception e4) {
            e = e4;
            query = null;
            C0621d.m2279a().e(e.getMessage(), new Object[0]);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* renamed from: d */
    private C0666a m2574d(Context context) {
        C0666a c0666a = null;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                C0666a a;
                if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName))) {
                    a = m2575a(resolveInfo.serviceInfo.applicationInfo.packageName);
                    if (a != null) {
                        if (c0666a != null) {
                            if (c0666a.m2568b() < a.m2568b()) {
                            }
                        }
                        c0666a = a;
                    }
                }
                a = c0666a;
                c0666a = a;
            }
        }
        return c0666a;
    }

    /* renamed from: a */
    public C0666a m2575a(String str) {
        InputStream open;
        NameNotFoundException e;
        Throwable th;
        Exception e2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            open = this.f1579d.createPackageContext(str, 2).getAssets().open("weibo_for_sdk.json");
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int read = open.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                if (!TextUtils.isEmpty(stringBuilder.toString()) && C0667e.m2570a(this.f1579d, str)) {
                    int optInt = new JSONObject(stringBuilder.toString()).optInt("support_api", -1);
                    C0666a c0666a = new C0666a();
                    c0666a.m2566a(str);
                    c0666a.m2563a(optInt);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e3) {
                            C0621d.m2279a().e(e3.getMessage(), new Object[0]);
                        }
                    }
                    return c0666a;
                } else if (open == null) {
                    return null;
                } else {
                    try {
                        open.close();
                        return null;
                    } catch (IOException e4) {
                        C0621d.m2279a().e(e4.getMessage(), new Object[0]);
                        return null;
                    }
                }
            } catch (NameNotFoundException e5) {
                e = e5;
                try {
                    C0621d.m2279a().e(e.getMessage(), new Object[0]);
                    if (open != null) {
                        return null;
                    }
                    try {
                        open.close();
                        return null;
                    } catch (IOException e42) {
                        C0621d.m2279a().e(e42.getMessage(), new Object[0]);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e422) {
                            C0621d.m2279a().e(e422.getMessage(), new Object[0]);
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e2 = e6;
                C0621d.m2279a().e(e2.getMessage(), new Object[0]);
                if (open != null) {
                    return null;
                }
                try {
                    open.close();
                    return null;
                } catch (IOException e4222) {
                    C0621d.m2279a().e(e4222.getMessage(), new Object[0]);
                    return null;
                }
            }
        } catch (NameNotFoundException e7) {
            e = e7;
            open = null;
            C0621d.m2279a().e(e.getMessage(), new Object[0]);
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Exception e8) {
            e2 = e8;
            open = null;
            C0621d.m2279a().e(e2.getMessage(), new Object[0]);
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Throwable th3) {
            open = null;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m2570a(Context context, String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return z;
        }
        try {
            return C0667e.m2571a(context.getPackageManager().getPackageInfo(str, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
        } catch (NameNotFoundException e) {
            return z;
        }
    }

    /* renamed from: a */
    private static boolean m2571a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature toByteArray : signatureArr) {
            if (str.equals(Data.MD5(toByteArray.toByteArray()))) {
                C0621d.m2279a().d("check pass", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
