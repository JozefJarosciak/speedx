package cn.jpush.android.util;

import android.content.Context;
import android.database.Cursor;
import cn.jpush.android.C0404a;
import cn.jpush.android.data.C0443o;
import cn.jpush.android.data.C0445q;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.util.u */
public final class C0507u {
    /* renamed from: z */
    private static final String[] f1050z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 23;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "e\u0011\u0006f}s";
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
            case 0: goto L_0x011b;
            case 1: goto L_0x011f;
            case 2: goto L_0x0123;
            case 3: goto L_0x0127;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 30;
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
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "u\u0011\u001dzA\u000e";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "x\u001b\u0007";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "b\u0017\u001eqm";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "z\u0011\u0010urI\u001a\u001dg";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "u\u0011\u0006zjIM,";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "u\u0011\u0006zjIN,%";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "e\u000b\u0010w{e\r,p{b\u001f\u001axm";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "u\u0011\u0006zjIO,'";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "p\u001f\u001ax{r!\u0007{n";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "z\u0011\u0014}pI\n\u001c`z";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "r\u001f\u0007q";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "e\u000b\u0010w{s\u001a,`qf";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "o\u0007\nm3[3^pz";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "z\u0011\u0014}pI\u0018\u0012}rs\u001a";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "b\u0007\u0003q";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "e\u001a\u0018Kwx\u001a\u0016l";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\n\u001ay{";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "6\u0017\u00004{n\u0017\u0000`m6DSanr\u001f\u0007q";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "6^S4ry\u0019\u001azAu\u0011\u0000`w{\u001bI4";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "6\u0010\u001c`>s\u0006\u001agje^I4wx\r\u0016fj";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "e\u0011\u0001`A}\u001b\n.";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "e\u0011\u0001`A}\u001b\n4$6";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        f1050z = r4;
        return;
    L_0x011b:
        r9 = 22;
        goto L_0x0020;
    L_0x011f:
        r9 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        goto L_0x0020;
    L_0x0123:
        r9 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        goto L_0x0020;
    L_0x0127:
        r9 = 20;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.u.<clinit>():void");
    }

    /* renamed from: a */
    private static JSONObject m1803a(C0445q c0445q) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f1050z[2], c0445q.m1335b());
            jSONObject.put(f1050z[1], c0445q.m1338c());
            jSONObject.put(f1050z[4], c0445q.m1341d());
            jSONObject.put(f1050z[0], c0445q.m1344e());
            jSONObject.put(f1050z[3], c0445q.m1349g());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(f1050z[6], c0445q.m1350h());
            jSONObject2.put(f1050z[8], c0445q.m1351i());
            jSONObject2.put(f1050z[5], c0445q.m1352j());
            jSONObject.put(f1050z[7], jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m1804a(Context context) {
        if (context != null) {
            C0507u.m1807b(context);
            C0507u.m1808c(context);
        }
    }

    /* renamed from: a */
    public static synchronized void m1805a(Context context, int i, long j, int i2) {
        Throwable th;
        synchronized (C0507u.class) {
            ac.m1584c();
            String r = C0490b.m1734r(context);
            String h = C0404a.m1109h() == null ? "" : C0404a.m1109h();
            String str = r + "_" + i + "_" + h + "_" + C0490b.m1699d();
            new StringBuilder(f1050z[22]).append(str).append(f1050z[19]).append(j);
            ac.m1581b();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            if (i2 == 0) {
                if (j <= 1000) {
                    i3 = 1;
                }
                if (j > 1000 && j <= 3000) {
                    i4 = 1;
                }
                if (j > 3000) {
                    i5 = 1;
                }
            }
            C0443o a = C0443o.m1318a(context);
            if (a != null) {
                if (a.m1325a(true)) {
                    if (a.m1324a(str)) {
                        new StringBuilder(f1050z[21]).append(str).append(f1050z[18]);
                        ac.m1584c();
                        Cursor b;
                        try {
                            b = a.m1327b(str);
                            if (b == null) {
                                try {
                                    ac.m1586d();
                                } catch (Exception e) {
                                    r3 = b;
                                    if (r3 != null) {
                                        r3.close();
                                    }
                                    a.m1323a();
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (b != null) {
                                        b.close();
                                    }
                                    throw th;
                                }
                            }
                            C0445q a2 = a.m1322a(b);
                            if (a2 != null) {
                                a.m1329b(str, r, h, C0490b.m1699d(), i, a2.m1347f() + i2, a2.m1349g() + 1, i3 + a2.m1350h(), i4 + a2.m1351i(), i5 + a2.m1352j(), a2.m1353k() + 0);
                            } else {
                                ac.m1586d();
                            }
                            if (b != null) {
                                b.close();
                            }
                        } catch (Exception e2) {
                            r3 = null;
                            Cursor cursor;
                            if (cursor != null) {
                                cursor.close();
                            }
                            a.m1323a();
                        } catch (Throwable th3) {
                            th = th3;
                            b = null;
                            if (b != null) {
                                b.close();
                            }
                            throw th;
                        }
                    }
                    new StringBuilder(f1050z[21]).append(str).append(f1050z[20]);
                    ac.m1584c();
                    a.m1321a(str, r, h, C0490b.m1699d(), i, i2, 1, i3, i4, i5, 0);
                    a.m1323a();
                } else {
                    ac.m1588e();
                }
            }
        }
    }

    /* renamed from: b */
    private static JSONObject m1806b(C0445q c0445q) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f1050z[2], c0445q.m1335b());
            jSONObject.put(f1050z[1], c0445q.m1338c());
            jSONObject.put(f1050z[4], c0445q.m1341d());
            jSONObject.put(f1050z[0], c0445q.m1344e());
            jSONObject.put(f1050z[3], c0445q.m1349g());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: b */
    private static synchronized void m1807b(Context context) {
        C0443o a;
        JSONException e;
        Exception e2;
        Cursor cursor = null;
        synchronized (C0507u.class) {
            if (C0404a.m1145y()) {
                JSONObject jSONObject = new JSONObject();
                a = C0443o.m1318a(context);
                try {
                    jSONObject.put(f1050z[17], C0404a.m1126m());
                    jSONObject.put(f1050z[15], f1050z[16]);
                    jSONObject.put(f1050z[11], new SimpleDateFormat(f1050z[13]).format(new Date(C0404a.m1105g())));
                    if (a.m1325a(false)) {
                        jSONObject.put(f1050z[10], a.m1326b(true));
                        jSONObject.put(f1050z[14], a.m1326b(false));
                        JSONArray jSONArray = new JSONArray();
                        Cursor c = a.m1330c();
                        if (c != null) {
                            do {
                                try {
                                    C0445q a2 = a.m1322a(c);
                                    if (a2 == null || an.m1657a(a2.m1332a())) {
                                        ac.m1581b();
                                    } else {
                                        jSONArray.put(C0507u.m1806b(a2));
                                    }
                                } catch (JSONException e3) {
                                    e = e3;
                                    cursor = c;
                                } catch (Exception e4) {
                                    e2 = e4;
                                    cursor = c;
                                } catch (Throwable th) {
                                    Throwable th2 = th;
                                    cursor = c;
                                }
                            } while (c.moveToNext());
                            c.close();
                        }
                        jSONObject.put(f1050z[9], jSONArray);
                        JSONArray jSONArray2 = new JSONArray();
                        cursor = a.m1331d();
                        if (cursor != null) {
                            do {
                                C0445q a3 = a.m1322a(cursor);
                                if (a3 == null || an.m1657a(a3.m1332a())) {
                                    ac.m1581b();
                                } else {
                                    jSONArray2.put(C0507u.m1803a(a3));
                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                        }
                        jSONObject.put(f1050z[12], jSONArray2);
                        a.m1323a();
                        ah.m1621a(context, jSONObject);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a != null) {
                            a.m1323a();
                        }
                    } else {
                        ac.m1588e();
                        if (a != null) {
                            a.m1323a();
                        }
                    }
                } catch (JSONException e5) {
                    e = e5;
                } catch (Exception e6) {
                    e2 = e6;
                }
            }
        }
        ac.m1588e();
        e2.printStackTrace();
        if (cursor != null) {
            cursor.close();
        }
        if (a != null) {
            a.m1323a();
        }
        try {
            ac.m1588e();
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
            if (a != null) {
                a.m1323a();
            }
        } catch (Throwable th3) {
            th2 = th3;
            if (cursor != null) {
                cursor.close();
            }
            if (a != null) {
                a.m1323a();
            }
            throw th2;
        }
    }

    /* renamed from: c */
    private static synchronized void m1808c(Context context) {
        synchronized (C0507u.class) {
            C0443o a = C0443o.m1318a(context);
            if (a != null) {
                a.m1325a(true);
                a.m1328b();
                a.m1323a();
            }
        }
    }
}
