package cn.jpush.android.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import cn.jpush.android.C0404a;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0500m;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.al;
import cn.jpush.android.util.an;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.api.e */
public class C0409e {
    /* renamed from: a */
    public static boolean f526a = false;
    /* renamed from: b */
    public static boolean f527b = false;
    /* renamed from: c */
    private static C0409e f528c = null;
    /* renamed from: z */
    private static final String[] f529z;
    /* renamed from: d */
    private ExecutorService f530d = Executors.newSingleThreadExecutor();
    /* renamed from: e */
    private String f531e = null;
    /* renamed from: f */
    private String f532f = null;
    /* renamed from: g */
    private ArrayList<C0405a> f533g = new ArrayList();
    /* renamed from: h */
    private long f534h = 30;
    /* renamed from: i */
    private long f535i = 0;
    /* renamed from: j */
    private long f536j = 0;
    /* renamed from: k */
    private boolean f537k = false;
    /* renamed from: l */
    private boolean f538l = true;
    /* renamed from: m */
    private boolean f539m = true;
    /* renamed from: n */
    private boolean f540n = false;
    /* renamed from: o */
    private boolean f541o = true;
    /* renamed from: p */
    private long f542p = 0;
    /* renamed from: q */
    private WeakReference<JSONObject> f543q = null;
    /* renamed from: r */
    private JSONObject f544r = null;
    /* renamed from: s */
    private Object f545s = new Object();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 24;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0011G>V\u001d\rL\u0012L\u0010";
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
            case 0: goto L_0x012f;
            case 1: goto L_0x0133;
            case 2: goto L_0x0137;
            case 3: goto L_0x013b;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
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
            case 22: goto L_0x0121;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u000bV$H\u0011";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0003A9L\u0002\u0007}9@\u0006\u000fK#D\u0000\u0007";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\bR8V\u001c=Q9D\u0000=A,F\u001c\u0007\f'V\u001b\f";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0016[=@";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u000eC>Q+\u0012C8V\u0011";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "(r8V\u001c1ccJ\u001a0G>P\u0019\u0007\n\u000eJ\u001a\u0016G5Q]BO8V\u0000B@(\u0005\u001d\fT\"N\u0011\u0006\u0002$KT#A9L\u0002\u000bV4\u000b\u001b\fp(V\u0001\u000fGe\f";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "!M#Q\u0011\u001aVmV\u001c\rW!AT\u0000GmD\u001aBc.Q\u001d\u0014K9\\T\rLmQ\u001c\u000bQmH\u0011\u0016J\"ATX\u0002";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "(r8V\u001c1c";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0003A9L\u0002\u0007}!D\u0001\fA%";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0001W?z\u0007\u0007Q>L\u001b\f}>Q\u0015\u0010V";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0003A9L\u0002\u000bV$@\u0007";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0017V+\bL";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0006W?D\u0000\u000bM#";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0001W?z\u0007\u0007G>L\u001b\f}(K\u0010";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "(r8V\u001c+L9@\u0006\u0004C.@Z\rL\u001dD\u0001\u0011Ge\fT\u000fW>QT\u0000GmF\u0015\u000eN(AT\u0003D9@\u0006BA,I\u0018\u0007Fmo$\u0017Q%l\u001a\u0016G?C\u0015\u0001GcJ\u001a0G>P\u0019\u0007\nd\u0005\u0015\fFmo$\u0017Q%l\u001a\u0016G?C\u0015\u0001GcJ\u001a2C8V\u0011BQ%J\u0001\u000eFmK\u001b\u0016\u0002/@T\u0001C!I\u0011\u0006\u0002 J\u0006\u0007\u00029L\u0019\u0007\u0002$KT\u0016J$VT#A9L\u0002\u000bV4\u0005\u001b\u0010\u0002\u000bW\u0015\u0005O(K\u0000B\u0019m";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\rL\u001dD\u0001\u0011G";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0006C9@";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0016K @";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0012C*@T\fC @T\u0006K)KS\u0016\u0002 D\u0000\u0001JmQ\u001c\u0007\u0002!D\u0007\u0016\u0002\"K\u0011BR,V\u0007\u0007FmG\rBM#w\u0011\u0011W @";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "(r8V\u001c+L9@\u0006\u0004C.@Z\rL\u001dD\u0001\u0011Ge\fT\u000fW>QT\u0000GmF\u0015\u000eN(AT\u0003D9@\u0006BA,I\u0018\u0007Fmo$\u0017Q%l\u001a\u0016G?C\u0015\u0001GcJ\u001a0G>P\u0019\u0007\nd\u0005\u001d\f\u00029M\u001d\u0011\u0002\fF\u0000\u000bT$Q\rBM?\u00052\u0010C*H\u0011\fV";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "(r8V\u001c+L9@\u0006\u0004C.@Z\rL\u001f@\u0007\u0017O(\r]BO8V\u0000B@(\u0005\u0017\u0003N!@\u0010BC+Q\u0011\u0010\u0002.D\u0018\u000eG)\u0005>2W>M=\fV(W\u0012\u0003A(\u000b\u001b\fr,P\u0007\u0007\nd\u0005\u001d\f\u0002!D\u0007\u0016\u0002\fF\u0000\u000bT$Q\rBM?\u00052\u0010C*H\u0011\fV";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "(r8V\u001c+L9@\u0006\u0004C.@Z\rL\u001f@\u0007\u0017O(\r]BO8V\u0000B@(\u0005\u0017\u0003N!@\u0010BC+Q\u0011\u0010\u0002.D\u0018\u000eG)\u0005>2W>M=\fV(W\u0012\u0003A(\u000b\u001b\fr,P\u0007\u0007\nd\u0005\u0015\fFmo$\u0017Q%l\u001a\u0016G?C\u0015\u0001GcJ\u001a0G>P\u0019\u0007\u0002>M\u001b\u0017N)\u0005\u001a\rVmG\u0011BA,I\u0018\u0007FmH\u001b\u0010GmQ\u001d\u000fGmL\u001aBN,V\u0000Bc.Q\u001d\u0014K9\\T\rPmc\u0006\u0003E @\u001a\u0016\u0002m";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\rL\u001f@\u0007\u0017O(";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        f529z = r4;
        r0 = 0;
        f528c = r0;
        r0 = 0;
        f526a = r0;
        r0 = 0;
        f527b = r0;
        return;
    L_0x012f:
        r9 = 98;
        goto L_0x0020;
    L_0x0133:
        r9 = 34;
        goto L_0x0020;
    L_0x0137:
        r9 = 77;
        goto L_0x0020;
    L_0x013b:
        r9 = 37;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.e.<clinit>():void");
    }

    private C0409e() {
    }

    /* renamed from: a */
    private JSONObject m1179a(Context context, long j) {
        al.m1651a().m1654b(context, f529z[10], this.f535i);
        StringBuilder stringBuilder = new StringBuilder();
        String q = C0490b.m1732q(context);
        if (!an.m1657a(q)) {
            stringBuilder.append(q);
        }
        q = C0490b.m1718j(context);
        if (!an.m1657a(q)) {
            stringBuilder.append(q);
        }
        stringBuilder.append(j);
        this.f532f = C0490b.m1671a(stringBuilder.toString());
        al.m1651a().m1655b(context, f529z[0], this.f532f);
        JSONObject jSONObject = new JSONObject();
        try {
            if (C0404a.m1145y()) {
                C0409e.m1181a(jSONObject);
                jSONObject.put(f529z[1], C0404a.m1126m());
                jSONObject.put(f529z[0], this.f532f);
                jSONObject.put(f529z[4], f529z[9]);
                return jSONObject;
            }
            ac.m1586d();
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m1180a(C0409e c0409e, Context context) {
        boolean z = true;
        if (C0404a.m1145y()) {
            if (c0409e.f538l) {
                c0409e.f538l = false;
                ac.m1581b();
                long a = al.m1651a().m1652a(context, f529z[5], -1);
                long j = c0409e.f535i - a;
                if (a != -1 && j <= c0409e.f534h * 1000) {
                    z = false;
                }
            } else if (c0409e.f535i - c0409e.f536j <= c0409e.f534h * 1000) {
                z = false;
            }
            c0409e.f537k = z;
            if (c0409e.f537k) {
                JSONObject d;
                ac.m1581b();
                JSONArray jSONArray = new JSONArray();
                JSONObject a2 = c0409e.m1179a(context, c0409e.f535i);
                if (a2 != null) {
                    jSONArray.put(a2);
                }
                synchronized (c0409e.f545s) {
                    d = c0409e.m1186d(context);
                    if (d != null && d.length() > 0) {
                        try {
                            d.put(f529z[4], f529z[2]);
                            d.put(f529z[1], C0404a.m1126m());
                        } catch (Exception e) {
                        }
                        ah.m1625a(context, f529z[3], null);
                        c0409e.f544r = null;
                        c0409e.f533g = new ArrayList();
                    }
                }
                if (d != null && d.length() > 0) {
                    jSONArray.put(d);
                }
                ah.m1620a(context, jSONArray);
                return;
            }
            c0409e.f532f = al.m1651a().m1653a(context, f529z[0], null);
            return;
        }
        ac.m1586d();
    }

    /* renamed from: a */
    private static void m1181a(JSONObject jSONObject) {
        String a = C0500m.m1764a();
        Object obj = a.split("_")[0];
        Object obj2 = a.split("_")[1];
        jSONObject.put(f529z[17], obj);
        jSONObject.put(f529z[18], obj2);
    }

    /* renamed from: a */
    private static boolean m1182a(String str) {
        boolean z = false;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length >= 2) {
            int i = 0;
            while (i < stackTrace.length) {
                try {
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (stackTraceElement.getMethodName().equals(str)) {
                        for (Class cls = Class.forName(stackTraceElement.getClassName()); cls.getSuperclass() != null; cls = cls.getSuperclass()) {
                            if (cls.getSuperclass() == Activity.class) {
                                z = true;
                                break;
                            }
                        }
                    }
                    i++;
                } catch (Exception e) {
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    public static C0409e m1183b() {
        if (f528c == null) {
            synchronized (C0409e.class) {
                f528c = new C0409e();
            }
        }
        return f528c;
    }

    /* renamed from: b */
    static /* synthetic */ void m1184b(C0409e c0409e, Context context) {
        int i = 0;
        if (context != null) {
            synchronized (c0409e.f545s) {
                JSONArray optJSONArray;
                al.m1651a().m1654b(context, f529z[5], c0409e.f536j);
                al.m1651a().m1654b(context, f529z[14], c0409e.f536j);
                if (c0409e.f539m) {
                    c0409e.f539m = false;
                    if (!(c0409e.f537k || c0409e.m1186d(context) == null)) {
                        optJSONArray = c0409e.m1186d(context).optJSONArray(f529z[11]);
                        if (optJSONArray != null) {
                            Collection arrayList = new ArrayList();
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                try {
                                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                                    if (optJSONObject != null) {
                                        Iterator keys = optJSONObject.keys();
                                        if (keys.hasNext()) {
                                            String str = (String) keys.next();
                                            arrayList.add(new C0405a(str, optJSONObject.getLong(str)));
                                        }
                                    }
                                } catch (Exception e) {
                                    e.getMessage();
                                    ac.m1588e();
                                }
                            }
                            arrayList.addAll(c0409e.f533g);
                            c0409e.f533g = new ArrayList();
                            c0409e.f533g.addAll(arrayList);
                        }
                    }
                }
                JSONObject d = c0409e.m1186d(context);
                JSONObject jSONObject = d == null ? new JSONObject() : d;
                optJSONArray = new JSONArray();
                while (i < c0409e.f533g.size()) {
                    C0405a c0405a = (C0405a) c0409e.f533g.get(i);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(c0405a.f504a, c0405a.f505b);
                        optJSONArray.put(jSONObject2);
                    } catch (JSONException e2) {
                    }
                    i++;
                }
                if (optJSONArray.length() > 0) {
                    try {
                        jSONObject.put(f529z[11], optJSONArray);
                    } catch (JSONException e3) {
                    }
                }
                if (jSONObject.length() > 0) {
                    try {
                        long a = al.m1651a().m1652a(context, f529z[10], 0);
                        long j = 10;
                        if (a == 0) {
                            a = c0409e.f536j - c0409e.f542p;
                            if (a > 0) {
                                j = a / 1000;
                            }
                            al.m1651a().m1654b(context, f529z[10], c0409e.f542p);
                        } else {
                            j = (c0409e.f536j - a) / 1000;
                        }
                        jSONObject.put(f529z[13], j);
                        jSONObject.put(f529z[1], C0404a.m1126m());
                        jSONObject.put(f529z[0], c0409e.f532f);
                        C0409e.m1181a(jSONObject);
                    } catch (Exception e4) {
                    }
                    c0409e.f544r = jSONObject;
                    if (c0409e.f544r != null) {
                        try {
                            ah.m1619a(context, c0409e.f544r.toString().getBytes(f529z[12]).length);
                        } catch (UnsupportedEncodingException e5) {
                        } catch (Exception e6) {
                            e6.getMessage();
                            ac.m1588e();
                        }
                    }
                    ah.m1625a(context, f529z[3], jSONObject);
                }
            }
        }
    }

    /* renamed from: c */
    private boolean m1185c(Context context, String str) {
        if (!this.f541o) {
            ac.m1584c();
            return false;
        } else if (context == null) {
            ac.m1584c();
            return false;
        } else if (context instanceof Application) {
            ac.m1589e(f529z[8], new StringBuilder(f529z[7]).append(str).toString());
            return false;
        } else if (C0409e.m1182a(str)) {
            return true;
        } else {
            throw new SecurityException(f529z[6]);
        }
    }

    /* renamed from: d */
    private JSONObject m1186d(Context context) {
        if (this.f544r == null) {
            this.f544r = ah.m1617a(context, f529z[3]);
        }
        return this.f544r;
    }

    /* renamed from: a */
    public final void m1187a(long j) {
        this.f534h = j;
    }

    /* renamed from: a */
    public final void m1188a(Context context) {
        if (m1185c(context, f529z[23])) {
            f526a = true;
            try {
                this.f540n = false;
            } catch (ClassCastException e) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.f540n) {
                ac.m1589e(f529z[8], f529z[22]);
                return;
            }
            this.f540n = true;
            this.f535i = System.currentTimeMillis();
            this.f531e = context.getClass().getName();
            try {
                this.f530d.execute(new C0412h(this, context));
            } catch (Exception e3) {
            }
        }
    }

    /* renamed from: a */
    public final void m1189a(Context context, String str) {
        if (this.f540n) {
            ac.m1589e(f529z[8], f529z[21]);
            return;
        }
        this.f540n = true;
        this.f531e = str;
        this.f535i = System.currentTimeMillis();
        try {
            this.f530d.execute(new C0410f(this, context));
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public final void m1190a(boolean z) {
        this.f541o = z;
    }

    /* renamed from: a */
    public final boolean m1191a() {
        return this.f541o;
    }

    /* renamed from: b */
    public final void m1192b(Context context) {
        if (m1185c(context, f529z[16])) {
            f527b = true;
            try {
                this.f540n = true;
            } catch (ClassCastException e) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.f540n) {
                this.f540n = false;
                if (this.f531e == null || !this.f531e.equals(context.getClass().getName())) {
                    ac.m1584c();
                    return;
                }
                this.f536j = System.currentTimeMillis();
                this.f533g.add(new C0405a(this.f531e, (this.f536j - this.f535i) / 1000));
                this.f542p = this.f535i;
                try {
                    this.f530d.execute(new C0413i(this, context));
                    return;
                } catch (Exception e3) {
                    return;
                }
            }
            ac.m1589e(f529z[8], f529z[15]);
        }
    }

    /* renamed from: b */
    public final void m1193b(Context context, String str) {
        if (this.f540n) {
            this.f540n = false;
            if (this.f531e == null || !this.f531e.equals(str)) {
                ac.m1589e(f529z[8], f529z[19]);
                return;
            }
            this.f536j = System.currentTimeMillis();
            this.f533g.add(new C0405a(this.f531e, (this.f536j - this.f535i) / 1000));
            try {
                this.f530d.execute(new C0411g(this, context));
                return;
            } catch (Exception e) {
                return;
            }
        }
        ac.m1589e(f529z[8], f529z[20]);
    }

    /* renamed from: c */
    public final void m1194c(Context context) {
        try {
            if (this.f531e != null && this.f540n) {
                this.f536j = System.currentTimeMillis();
                this.f533g.add(new C0405a(this.f531e, (this.f536j - this.f535i) / 1000));
                try {
                    this.f530d.execute(new C0414j(this, context));
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
        }
    }
}
