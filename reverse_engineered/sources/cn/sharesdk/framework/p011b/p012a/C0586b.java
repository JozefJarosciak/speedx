package cn.sharesdk.framework.p011b.p012a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.sharesdk.framework.utils.C0621d;

/* compiled from: DBProvider */
/* renamed from: cn.sharesdk.framework.b.a.b */
public class C0586b {
    /* renamed from: c */
    private static C0586b f1257c = null;
    /* renamed from: a */
    private Context f1258a;
    /* renamed from: b */
    private C0585a f1259b = new C0585a(this.f1258a);

    private C0586b(Context context) {
        this.f1258a = context.getApplicationContext();
    }

    /* renamed from: a */
    public static synchronized C0586b m2030a(Context context) {
        C0586b c0586b;
        synchronized (C0586b.class) {
            if (f1257c == null) {
                f1257c = new C0586b(context);
            }
            c0586b = f1257c;
        }
        return c0586b;
    }

    /* renamed from: a */
    public Cursor m2034a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        SQLiteDatabase writableDatabase = this.f1259b.getWritableDatabase();
        C0621d.m2279a().d("Query table: %s", new Object[]{str});
        try {
            return writableDatabase.query(str, strArr, str2, strArr2, null, null, str3);
        } catch (Throwable e) {
            C0621d.m2279a().w(e, "when query database occur error table:%s,", new Object[]{str});
            return null;
        }
    }

    /* renamed from: a */
    public long m2033a(String str, ContentValues contentValues) {
        long j = -1;
        try {
            j = this.f1259b.getWritableDatabase().replace(str, null, contentValues);
        } catch (Throwable e) {
            C0621d.m2279a().w(e, "when insert database occur error table:%s,", new Object[]{str});
        }
        return j;
    }

    /* renamed from: a */
    public int m2032a(String str, String str2, String[] strArr) {
        int delete;
        Throwable e;
        try {
            delete = this.f1259b.getWritableDatabase().delete(str, str2, strArr);
            try {
                C0621d.m2279a().d("Deleted %d rows from table: %s", new Object[]{Integer.valueOf(delete), str});
            } catch (Exception e2) {
                e = e2;
                C0621d.m2279a().w(e, "when delete database occur error table:%s,", new Object[]{str});
                return delete;
            }
        } catch (Throwable e3) {
            e = e3;
            delete = 0;
            C0621d.m2279a().w(e, "when delete database occur error table:%s,", new Object[]{str});
            return delete;
        }
        return delete;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public int m2031a(java.lang.String r6) {
        /*
        r5 = this;
        r2 = 0;
        r0 = 0;
        r1 = r5.f1259b;
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
        r3 = cn.sharesdk.framework.utils.C0621d.m2279a();	 Catch:{ all -> 0x003b }
        r3.w(r1);	 Catch:{ all -> 0x003b }
        r2.close();
        goto L_0x002e;
    L_0x003b:
        r0 = move-exception;
        r2.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.b.a.b.a(java.lang.String):int");
    }
}
