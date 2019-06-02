package com.alipay.sdk.tid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.util.C0873a;

/* renamed from: com.alipay.sdk.tid.b */
public final class C0872b {
    /* renamed from: c */
    private static C0872b f2181c;
    /* renamed from: a */
    public String f2182a;
    /* renamed from: b */
    public String f2183b;

    private C0872b() {
    }

    /* renamed from: b */
    private String m3408b() {
        return this.f2182a;
    }

    /* renamed from: a */
    private void m3407a(String str) {
        this.f2182a = str;
    }

    /* renamed from: c */
    private String m3410c() {
        return this.f2183b;
    }

    /* renamed from: b */
    private void m3409b(String str) {
        this.f2183b = str;
    }

    /* renamed from: a */
    private void m3406a(Context context) {
        C0871a c0871a = new C0871a(context);
        try {
            c0871a.m3403a(C0873a.m3414a(context).m3422a(), C0873a.m3414a(context).m3423b(), this.f2182a, this.f2183b);
        } catch (Exception e) {
        } finally {
            c0871a.close();
        }
    }

    /* renamed from: d */
    private boolean m3411d() {
        return TextUtils.isEmpty(this.f2182a);
    }

    /* renamed from: a */
    public static synchronized C0872b m3405a() {
        C0872b c0872b;
        synchronized (C0872b.class) {
            if (f2181c == null) {
                f2181c = new C0872b();
                Context context = C0870b.m3386a().f2177a;
                C0871a c0871a = new C0871a(context);
                String a = C0873a.m3414a(context).m3422a();
                String b = C0873a.m3414a(context).m3423b();
                f2181c.f2182a = c0871a.m3401a(a, b);
                f2181c.f2183b = c0871a.m3404b(a, b);
                if (TextUtils.isEmpty(f2181c.f2183b)) {
                    C0872b c0872b2 = f2181c;
                    String toHexString = Long.toHexString(System.currentTimeMillis());
                    if (toHexString.length() > 10) {
                        toHexString = toHexString.substring(toHexString.length() - 10);
                    }
                    c0872b2.f2183b = toHexString;
                }
                c0871a.m3403a(a, b, f2181c.f2182a, f2181c.f2183b);
            }
            c0872b = f2181c;
        }
        return c0872b;
    }

    /* renamed from: e */
    private static void m3412e() {
        Context context = C0870b.m3386a().f2177a;
        String a = C0873a.m3414a(context).m3422a();
        String b = C0873a.m3414a(context).m3423b();
        C0871a c0871a = new C0871a(context);
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c0871a.getWritableDatabase();
            c0871a.m3402a(sQLiteDatabase, a, b, "", "");
            C0871a.m3395a(sQLiteDatabase, C0871a.m3398c(a, b));
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Exception e) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.close();
            }
        }
        c0871a.close();
    }

    /* renamed from: f */
    private static String m3413f() {
        String toHexString = Long.toHexString(System.currentTimeMillis());
        if (toHexString.length() > 10) {
            return toHexString.substring(toHexString.length() - 10);
        }
        return toHexString;
    }
}
