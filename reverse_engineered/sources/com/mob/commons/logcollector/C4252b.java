package com.mob.commons.logcollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.mob.tools.MobLog;

/* compiled from: DBProvider */
/* renamed from: com.mob.commons.logcollector.b */
public class C4252b {
    /* renamed from: c */
    private static C4252b f14931c = null;
    /* renamed from: a */
    private Context f14932a;
    /* renamed from: b */
    private C4251a f14933b = new C4251a(this.f14932a);

    private C4252b(Context context) {
        this.f14932a = context.getApplicationContext();
    }

    /* renamed from: a */
    public static synchronized C4252b m16896a(Context context) {
        C4252b c4252b;
        synchronized (C4252b.class) {
            if (f14931c == null) {
                f14931c = new C4252b(context);
            }
            c4252b = f14931c;
        }
        return c4252b;
    }

    /* renamed from: a */
    public long m16899a(String str, ContentValues contentValues) {
        long j = -1;
        try {
            j = this.f14933b.getWritableDatabase().replace(str, null, contentValues);
        } catch (Throwable e) {
            MobLog.getInstance().m16947w(e, "when insert database occur error table:%s,", str);
        }
        return j;
    }

    /* renamed from: a */
    public int m16898a(String str, String str2, String[] strArr) {
        int delete;
        Throwable e;
        try {
            delete = this.f14933b.getWritableDatabase().delete(str, str2, strArr);
            try {
                MobLog.getInstance().m16933d("Deleted %d rows from table: %s", Integer.valueOf(delete), str);
            } catch (Exception e2) {
                e = e2;
                MobLog.getInstance().m16947w(e, "when delete database occur error table:%s,", str);
                return delete;
            }
        } catch (Throwable e3) {
            e = e3;
            delete = 0;
            MobLog.getInstance().m16947w(e, "when delete database occur error table:%s,", str);
            return delete;
        }
        return delete;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public int m16897a(java.lang.String r6) {
        /*
        r5 = this;
        r2 = 0;
        r0 = 0;
        r1 = r5.f14933b;
        r1 = r1.getWritableDatabase();
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002f }
        r3.<init>();	 Catch:{ Exception -> 0x002f }
        r4 = "select count(*) from ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x002f }
        r3 = r3.append(r6);	 Catch:{ Exception -> 0x002f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x002f }
        r4 = 0;
        r2 = r1.rawQuery(r3, r4);	 Catch:{ Exception -> 0x002f }
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x002f }
        if (r1 == 0) goto L_0x002b;
    L_0x0026:
        r1 = 0;
        r0 = r2.getInt(r1);	 Catch:{ Exception -> 0x002f }
    L_0x002b:
        r2.close();
    L_0x002e:
        return r0;
    L_0x002f:
        r1 = move-exception;
        r3 = com.mob.tools.MobLog.getInstance();	 Catch:{ all -> 0x003b }
        r3.m16946w(r1);	 Catch:{ all -> 0x003b }
        r2.close();
        goto L_0x002e;
    L_0x003b:
        r0 = move-exception;
        r2.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.logcollector.b.a(java.lang.String):int");
    }

    /* renamed from: a */
    public Cursor m16900a(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = this.f14933b.getWritableDatabase().rawQuery(str, strArr);
        } catch (Throwable e) {
            MobLog.getInstance().m16946w(e);
        }
        return cursor;
    }
}
