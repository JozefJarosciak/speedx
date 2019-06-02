package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.util.ac;
import com.j256.ormlite.stmt.query.SimpleComparison;

/* renamed from: cn.jpush.android.data.f */
public final class C0434f {
    /* renamed from: a */
    public static final String[] f652a;
    /* renamed from: z */
    private static final String[] f653z;
    /* renamed from: b */
    private Context f654b;
    /* renamed from: c */
    private C0435g f655c;
    /* renamed from: d */
    private SQLiteDatabase f656d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 17;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "gx]\u0017\ffyt\u0000";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x00e4;
            case 1: goto L_0x00e8;
            case 2: goto L_0x00ec;
            case 3: goto L_0x00ef;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00f3;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "gx]\u0006\u0006~xv";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "In\n\njzl\n\u001dbpk\u0006\bm\u000b";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "gx]\u0006\u0006~xv[Y";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "gx]\u0011\u0010{s";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "gx]\u0006\u0006~xv[Y+wl\u0001Igx]\u0011\u001bbqe\u0000\u001bTbk\b\f7";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "+wl\u0001Igx]\u0011\u001bbqe\u0000\u001bTbk\b\f";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "dfg\u000bIYsc\u0001\bizg!\bw@\u0004\u001an6d\u0004\u0000g6g_";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "gx]\f\r";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "gx]\u0004\roIv\f\u0004n";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "gx]\u0000\u0011dc";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "gx]\u0011\u001bbqe\u0000\u001bTbk\b\f";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "gx]\f\r6";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "gx]\u0006\u0006~xvX";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "dfg\u000bIdfg\u000b>yv\u0004\u000bgsF\u0004\u001djtc\u0016\f+pc\f\u0005+s8";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "6&";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "+wl\u0001Igx]\u0011\u0010{s";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        f653z = r4;
        r0 = 8;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "Tf";
        r0 = 16;
        r4 = r3;
        goto L_0x0009;
    L_0x00e4:
        r9 = 11;
        goto L_0x0020;
    L_0x00e8:
        r9 = 22;
        goto L_0x0020;
    L_0x00ec:
        r9 = 2;
        goto L_0x0020;
    L_0x00ef:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0020;
    L_0x00f3:
        r3[r2] = r1;
        r0 = 1;
        r1 = f653z;
        r2 = 8;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 2;
        r1 = f653z;
        r2 = 1;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 3;
        r1 = f653z;
        r2 = 0;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 4;
        r1 = f653z;
        r2 = 4;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 5;
        r1 = f653z;
        r2 = 10;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 6;
        r1 = f653z;
        r2 = 11;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 7;
        r1 = f653z;
        r2 = 9;
        r1 = r1[r2];
        r4[r0] = r1;
        f652a = r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.f.<clinit>():void");
    }

    public C0434f(Context context) {
        this.f654b = context;
        this.f655c = new C0435g(context);
    }

    /* renamed from: c */
    private synchronized boolean m1288c() {
        boolean z;
        z = false;
        try {
            this.f656d = this.f655c.getWritableDatabase();
            z = true;
        } catch (Exception e) {
            new StringBuilder(f653z[14]).append(e.getMessage());
            ac.m1588e();
        }
        return z;
    }

    /* renamed from: d */
    private synchronized boolean m1289d() {
        boolean z;
        z = false;
        try {
            this.f656d = this.f655c.getReadableDatabase();
            z = true;
        } catch (Exception e) {
            new StringBuilder(f653z[7]).append(e.getMessage());
            ac.m1588e();
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized long m1290a(long j, int i, int i2, int i3, String str, long j2, long j3) {
        long j4;
        ContentValues contentValues = new ContentValues();
        contentValues.put(f653z[8], Long.valueOf(j));
        contentValues.put(f653z[1], Integer.valueOf(1));
        contentValues.put(f653z[0], Integer.valueOf(0));
        contentValues.put(f653z[4], Integer.valueOf(0));
        contentValues.put(f653z[10], str);
        contentValues.put(f653z[11], Long.valueOf(j2));
        contentValues.put(f653z[9], Long.valueOf(j3));
        j4 = 0;
        try {
            j4 = this.f656d.insert(f653z[2], null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j4;
    }

    /* renamed from: a */
    public final synchronized Cursor m1291a(int i, long j) {
        Cursor query;
        Exception e;
        try {
            query = this.f656d.query(true, f653z[2], f652a, new StringBuilder(f653z[13]).append(1).append(f653z[6]).append(SimpleComparison.LESS_THAN_OPERATION).append(j).toString(), null, null, null, null, null);
            if (query != null) {
                try {
                    query.moveToFirst();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return query;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            e.printStackTrace();
            return query;
        }
        return query;
    }

    /* renamed from: a */
    public final synchronized Cursor m1292a(long j, int i) {
        Cursor query;
        try {
            query = this.f656d.query(true, f653z[2], f652a, new StringBuilder(f653z[12]).append(j).append(f653z[16]).append(f653z[15]).toString(), null, null, null, null, null);
            if (query != null) {
                try {
                    query.moveToFirst();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            query = null;
        } catch (Throwable th) {
        }
        return query;
    }

    /* renamed from: a */
    public final synchronized Cursor m1293a(long j, long j2) {
        Cursor query;
        try {
            query = this.f656d.query(true, f653z[2], f652a, new StringBuilder(f653z[5]).append(300000 + j).append(f653z[6]).append(SimpleComparison.GREATER_THAN_OPERATION).append(j).toString(), null, null, null, null, null);
            if (query != null) {
                try {
                    query.moveToFirst();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            query = null;
        }
        return query;
    }

    /* renamed from: a */
    public final synchronized void m1294a() {
        if (this.f656d != null) {
            this.f656d.close();
        }
    }

    /* renamed from: a */
    public final synchronized void m1295a(Cursor cursor, C0436h c0436h) {
        if (cursor != null) {
            if (cursor.getCount() != 0) {
                if (c0436h == null) {
                    c0436h = new C0436h();
                }
                try {
                    c0436h.m1301a(cursor.getLong(1));
                    c0436h.m1300a(cursor.getInt(2));
                    c0436h.m1304b(cursor.getInt(3));
                    c0436h.m1307c(cursor.getInt(4));
                    c0436h.m1302a(cursor.getString(5));
                    c0436h.m1308c(cursor.getLong(6));
                    c0436h.m1305b(cursor.getLong(7));
                } catch (Exception e) {
                    e.getStackTrace();
                }
                c0436h.toString();
                ac.m1584c();
            }
        }
        ac.m1581b();
    }

    /* renamed from: a */
    public final synchronized boolean m1296a(boolean z) {
        return z ? m1288c() : m1289d();
    }

    /* renamed from: b */
    public final synchronized boolean m1297b() {
        boolean z = true;
        synchronized (this) {
            try {
                String str = f653z[3];
                ContentValues contentValues = new ContentValues();
                contentValues.put(f653z[1], Integer.valueOf(0));
                contentValues.put(f653z[0], Integer.valueOf(1));
                contentValues.put(f653z[4], Integer.valueOf(0));
                if (this.f656d.update(f653z[2], contentValues, str, null) <= 0) {
                    z = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
        }
        return z;
    }

    /* renamed from: b */
    public final synchronized boolean m1298b(long j, int i, int i2, int i3, String str, long j2, long j3) {
        boolean z;
        try {
            String stringBuilder = new StringBuilder(f653z[12]).append(j).toString();
            ContentValues contentValues = new ContentValues();
            contentValues.put(f653z[8], Long.valueOf(j));
            contentValues.put(f653z[1], Integer.valueOf(i));
            contentValues.put(f653z[0], Integer.valueOf(i2));
            contentValues.put(f653z[4], Integer.valueOf(0));
            contentValues.put(f653z[10], str);
            contentValues.put(f653z[11], Long.valueOf(j2));
            contentValues.put(f653z[9], Long.valueOf(j3));
            z = this.f656d.update(f653z[2], contentValues, stringBuilder, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }
}
