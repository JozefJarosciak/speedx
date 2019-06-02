package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.data.o */
public final class C0443o {
    /* renamed from: a */
    public static final String[] f721a;
    /* renamed from: e */
    private static C0443o f722e;
    /* renamed from: f */
    private static Object f723f = new Object();
    /* renamed from: z */
    private static final String[] f724z;
    /* renamed from: b */
    private Context f725b;
    /* renamed from: c */
    private C0444p f726c;
    /* renamed from: d */
    private SQLiteDatabase f727d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 21;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001e>\u0002K)+=\u0003Y5\u001d=\u0003Q\"\u0007";
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
            case 0: goto L_0x0119;
            case 1: goto L_0x011d;
            case 2: goto L_0x0121;
            case 3: goto L_0x0125;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 65;
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
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x0129;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0007:(K.\u0006:(S$\rsP";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001b>\u0012Va\u001b>\u0012V\u0016\u0006'\u0003Y#\u0018+3Y5\u0015,\u0016K$T(\u0016Q-T+M";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0010+\u001b]5\u0011n\u0011J.\u0019n\u001dH4\u0007&(K5\u0015:\u001eK5\u001d-\u0004";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u001b>\u0012Va&+\u0016\\ \u0016\"\u0012| \u0000/5Y2\u0011n\u0011Y(\u0018n\u0012\u0002";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0007+\u001b]\"\u0000n4w\u0014:\u001a_K5+=\u0018J5+%\u0012AhT(\u0005W,T$\u0007M2\u001c\u0011\u0004L \u0000'\u0004L(\u0017=WO)\u0011<\u0012\u00182\u0000\u0011\u0004W3\u0000\u0011\u001c]8Ii";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "]n\u0011J.\u0019n\u001dH4\u0007&(K5\u0015:\u001eK5\u001d-\u0004";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0007:(L.\u0000/\u001b";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0007+\u001b]\"\u0000n$m\f\\";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0007:(^ \u001d\"\u0012\\";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0007+\u001b]\"\u0000n]\u0018'\u0006!\u001a\u0018+\u0004;\u0004P\u001e\u0007:\u0016L(\u0007:\u001e[2T9\u001f]3\u0011n\u0004L\u001e\u0000!\u0003Y-TpW\ba\u0015 \u0013\u00182\u0000\u0011\u0011Y(\u0018+\u0013\u0018|T~W\u0018.\u0006*\u0012Ja\u00167WK5+:\u0018L \u0018n\u0013]2\u0017n\u001bQ,\u001d:W\u000b";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0007+\u001b]\"\u0000n]\u0018'\u0006!\u001a\u0018+\u0004;\u0004P\u001e\u0007:\u0016L(\u0007:\u001e[2T9\u001f]3\u0011n\u0004L\u001e\u0012/\u001eT$\u0010nI\u0018qTn\u0018J%\u0011<WZ8T=\u0003g'\u0015'\u001b]%T*\u0012K\"T\"\u001eU(\u0000nD";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0007:([.\u0001 \u0003gp";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0007:([.\u001a (Q1";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0007:([.\u0001 \u0003gpD";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u0007:(T.\u0017/\u001bg%\u001a=";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0007:([.\u0001 \u0003gp+}";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0007:([.\u0001 \u0003gr+G";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0007:(K.\u0006:(S$\r";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0007:(K.\u0001<\u0014]";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\u0007:(V$\u0000";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        f724z = r4;
        r0 = 12;
        r3 = new java.lang.String[r0];
        r0 = 0;
        r1 = f724z;
        r2 = 18;
        r1 = r1[r2];
        r3[r0] = r1;
        r2 = 1;
        r1 = "+'\u0013";
        r0 = 20;
        r4 = r3;
        goto L_0x0009;
    L_0x0119:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0020;
    L_0x011d:
        r9 = 78;
        goto L_0x0020;
    L_0x0121:
        r9 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x0020;
    L_0x0125:
        r9 = 56;
        goto L_0x0020;
    L_0x0129:
        r3[r2] = r1;
        r0 = 2;
        r1 = f724z;
        r2 = 20;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 3;
        r1 = f724z;
        r2 = 13;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 4;
        r1 = f724z;
        r2 = 15;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 5;
        r1 = f724z;
        r2 = 19;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 6;
        r1 = f724z;
        r2 = 9;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 7;
        r1 = f724z;
        r2 = 7;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 8;
        r1 = f724z;
        r2 = 12;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 9;
        r1 = f724z;
        r2 = 16;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 10;
        r1 = f724z;
        r2 = 17;
        r1 = r1[r2];
        r4[r0] = r1;
        r0 = 11;
        r1 = f724z;
        r2 = 14;
        r1 = r1[r2];
        r4[r0] = r1;
        f721a = r4;
        r0 = new java.lang.Object;
        r0.<init>();
        f723f = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.o.<clinit>():void");
    }

    private C0443o(Context context) {
        this.f725b = context;
        this.f726c = new C0444p(context);
    }

    /* renamed from: a */
    public static C0443o m1318a(Context context) {
        synchronized (f723f) {
            if (f722e == null) {
                f722e = new C0443o(context);
            }
        }
        return f722e;
    }

    /* renamed from: e */
    private synchronized boolean m1319e() {
        boolean z;
        z = false;
        try {
            this.f727d = this.f726c.getWritableDatabase();
            z = true;
        } catch (Exception e) {
            new StringBuilder(f724z[2]).append(e.getMessage());
            ac.m1588e();
        }
        return z;
    }

    /* renamed from: f */
    private synchronized boolean m1320f() {
        boolean z;
        z = false;
        try {
            this.f727d = this.f726c.getReadableDatabase();
            z = true;
        } catch (Exception e) {
            new StringBuilder(f724z[4]).append(e.getMessage());
            ac.m1588e();
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized long m1321a(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        long j;
        j = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(f724z[18], str);
        contentValues.put(f724z[20], str2);
        contentValues.put(f724z[13], str3);
        contentValues.put(f724z[15], str4);
        contentValues.put(f724z[19], str5);
        contentValues.put(f724z[9], Integer.valueOf(i));
        contentValues.put(f724z[7], Integer.valueOf(1));
        contentValues.put(f724z[12], Integer.valueOf(i3));
        contentValues.put(f724z[16], Integer.valueOf(i4));
        contentValues.put(f724z[17], Integer.valueOf(i5));
        contentValues.put(f724z[14], Integer.valueOf(0));
        try {
            j = this.f727d.insert(f724z[0], null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    /* renamed from: a */
    public final synchronized C0445q m1322a(Cursor cursor) {
        C0445q c0445q;
        if (cursor != null) {
            if (cursor.getCount() != 0) {
                c0445q = new C0445q();
                try {
                    c0445q.m1334a(cursor.getString(1));
                    c0445q.m1337b(cursor.getString(2));
                    c0445q.m1340c(cursor.getString(3));
                    c0445q.m1343d(cursor.getString(4));
                    c0445q.m1346e(cursor.getString(5));
                    c0445q.m1333a(cursor.getInt(6));
                    c0445q.m1336b(cursor.getInt(7));
                    c0445q.m1339c(cursor.getInt(8));
                    c0445q.m1342d(cursor.getInt(9));
                    c0445q.m1345e(cursor.getInt(10));
                    c0445q.m1348f(cursor.getInt(11));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                c0445q.toString();
                ac.m1584c();
            }
        }
        ac.m1581b();
        c0445q = null;
        return c0445q;
    }

    /* renamed from: a */
    public final synchronized void m1323a() {
        if (this.f727d != null) {
            this.f727d.close();
        }
    }

    /* renamed from: a */
    public final synchronized boolean m1324a(String str) {
        Cursor cursor = null;
        boolean z = false;
        synchronized (this) {
            try {
                cursor = this.f727d.rawQuery(new StringBuilder(f724z[5]).append(str).append("'").toString(), null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    if (cursor.getInt(0) != 0) {
                        z = true;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized boolean m1325a(boolean z) {
        return z ? m1319e() : m1320f();
    }

    /* renamed from: b */
    public final synchronized int m1326b(boolean z) {
        Cursor cursor = null;
        int i = 0;
        synchronized (this) {
            String str = f724z[9];
            if (z) {
                str = f724z[7];
            }
            try {
                cursor = this.f727d.rawQuery(new StringBuilder(f724z[8]).append(str).append(f724z[6]).toString(), null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    i = cursor.getInt(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    /* renamed from: b */
    public final synchronized Cursor m1327b(String str) {
        Cursor query;
        Exception e;
        try {
            query = this.f727d.query(true, f724z[0], f721a, new StringBuilder(f724z[1]).append(str).append("'").toString(), null, null, null, null, null);
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

    /* renamed from: b */
    public final synchronized void m1328b() {
        try {
            this.f727d.execSQL(f724z[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public final synchronized boolean m1329b(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean z;
        String stringBuilder = new StringBuilder(f724z[1]).append(str).append("'").toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put(f724z[18], str);
        contentValues.put(f724z[20], str2);
        contentValues.put(f724z[13], str3);
        contentValues.put(f724z[15], str4);
        contentValues.put(f724z[19], str5);
        contentValues.put(f724z[9], Integer.valueOf(i));
        contentValues.put(f724z[7], Integer.valueOf(i2));
        contentValues.put(f724z[12], Integer.valueOf(i3));
        contentValues.put(f724z[16], Integer.valueOf(i4));
        contentValues.put(f724z[17], Integer.valueOf(i5));
        contentValues.put(f724z[14], Integer.valueOf(i6));
        z = false;
        try {
            z = this.f727d.update(f724z[0], contentValues, stringBuilder, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    /* renamed from: c */
    public final synchronized Cursor m1330c() {
        Cursor rawQuery;
        Exception e;
        try {
            rawQuery = this.f727d.rawQuery(f724z[11], null);
            if (rawQuery != null) {
                try {
                    rawQuery.moveToFirst();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return rawQuery;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            rawQuery = null;
            e = exception;
            e.printStackTrace();
            return rawQuery;
        }
        return rawQuery;
    }

    /* renamed from: d */
    public final synchronized Cursor m1331d() {
        Cursor rawQuery;
        Exception e;
        try {
            rawQuery = this.f727d.rawQuery(f724z[10], null);
            if (rawQuery != null) {
                try {
                    rawQuery.moveToFirst();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return rawQuery;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            rawQuery = null;
            e = exception;
            e.printStackTrace();
            return rawQuery;
        }
        return rawQuery;
    }
}
