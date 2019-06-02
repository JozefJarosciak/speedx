package cn.jpush.android.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import cn.jpush.android.C0404a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JRecorder {
    /* renamed from: a */
    private static ExecutorService f927a = Executors.newSingleThreadExecutor();
    /* renamed from: c */
    private static Context f928c;
    /* renamed from: d */
    private static Handler f929d = null;
    /* renamed from: e */
    private static ArrayList<aa> f930e = new ArrayList();
    /* renamed from: f */
    private static volatile boolean f931f = false;
    /* renamed from: z */
    private static final String[] f932z;
    /* renamed from: b */
    private ArrayList<ab> f933b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 12;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\n\u000ec\u0014\u0016\fKv\u0015\u0005\u001a\u0007v\u001fDB";
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
            case 0: goto L_0x00b5;
            case 1: goto L_0x00b9;
            case 2: goto L_0x00bd;
            case 3: goto L_0x00c1;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 100;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\n\u000ec\u0014\u0016\fKw\u0012\u0017\u0019\t\u001e\u0000X\tj[^";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\n\u000ep\u0014\u0016\u001c\u001bv\t\r\u0017\u000f";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\n\n}\u001c\u0001";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\n\u000ep\u0014\u0016\u001c4g\u0002\u0014\u001d";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001c\u001ea\u001a\u0010\u0011\u0004}";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0011\u0005p\t\u0001\u0019\u0006v\u0015\u0010";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\n\u000ep\u0014\u0016\u001c\u001e}\u0012\u0010\u000b";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0017\u001dv\t\b\u0019\u0012";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\f\u0012c\u001e";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0012\u0019v\u0018\u000b\n\u000fv\t";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0011\u001fz\u0016\u0001";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        f932z = r4;
        r0 = java.util.concurrent.Executors.newSingleThreadExecutor();
        f927a = r0;
        r0 = 0;
        f929d = r0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        f930e = r0;
        r0 = 0;
        f931f = r0;
        return;
    L_0x00b5:
        r9 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x0020;
    L_0x00b9:
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0020;
    L_0x00bd:
        r9 = 19;
        goto L_0x0020;
    L_0x00c1:
        r9 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.JRecorder.<clinit>():void");
    }

    private JRecorder() {
        if (f929d == null) {
            f929d = new Handler(Looper.getMainLooper());
        }
    }

    private JRecorder(int i, Context context) {
        this();
        f928c = context.getApplicationContext();
        this.f933b = new ArrayList();
        aa aaVar = new aa();
        aaVar.f936a = i;
        aaVar.f937b = this.f933b;
        f930e.add(aaVar);
    }

    /* renamed from: a */
    private static JSONObject m1553a(ArrayList<ab> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        long j = ((ab) arrayList.get(size - 1)).f939a - ((ab) arrayList.get(0)).f939a;
        int i = 0;
        int i2 = 0;
        while (i < size) {
            i++;
            i2 = (int) (((double) i2) + ((ab) arrayList.get(i)).f940b);
        }
        jSONObject.put(f932z[4], f932z[6]);
        jSONObject.put(f932z[5], j);
        jSONObject.put(f932z[3], ((double) i2) - ((ab) arrayList.get(0)).f940b);
        return jSONObject;
    }

    /* renamed from: a */
    static /* synthetic */ void m1554a(Context context) {
        try {
            JSONArray jSONArray;
            if (f930e == null || f930e.size() <= 0) {
                jSONArray = null;
            } else {
                JSONArray jSONArray2 = new JSONArray();
                Iterator it = f930e.iterator();
                while (it.hasNext()) {
                    aa aaVar = (aa) it.next();
                    if (aaVar.f936a == 0) {
                        jSONArray2.put(m1553a(aaVar.f937b));
                    } else if (aaVar.f936a == 1) {
                        Object obj;
                        ArrayList arrayList = aaVar.f937b;
                        if (arrayList == null) {
                            obj = null;
                        } else {
                            int size = arrayList.size();
                            if (size <= 0) {
                                obj = null;
                            } else {
                                long j = ((ab) arrayList.get(size - 1)).f939a - ((ab) arrayList.get(0)).f939a;
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put(f932z[4], f932z[8]);
                                jSONObject.put(f932z[5], j);
                                jSONObject.put(f932z[3], ((ab) arrayList.get(size - 1)).f940b - ((ab) arrayList.get(0)).f940b);
                                JSONObject jSONObject2 = jSONObject;
                            }
                        }
                        jSONArray2.put(obj);
                    }
                }
                m1557b();
                jSONArray = jSONArray2;
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(f932z[9], f932z[10]);
                jSONObject3.put(f932z[11], C0404a.m1126m());
                jSONObject3.put(f932z[7], jSONArray);
                f927a.execute(new C0512z(jSONObject3));
            }
        } catch (JSONException e) {
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m1555a(JRecorder jRecorder, double d) {
        synchronized (jRecorder.f933b) {
            ab abVar = new ab(jRecorder);
            abVar.f940b = d;
            abVar.f939a = System.currentTimeMillis();
            jRecorder.f933b.add(abVar);
        }
    }

    /* renamed from: b */
    private static void m1557b() {
        Iterator it = f930e.iterator();
        while (it.hasNext()) {
            ((aa) it.next()).f937b.clear();
        }
        f930e.clear();
    }

    public static JRecorder getIncreamentsRecorder(Context context) {
        return new JRecorder(0, context);
    }

    public static JRecorder getSuperpositionRecorder(Context context) {
        return new JRecorder(1, context);
    }

    public static void parseRecordCommand(String str) {
        if (f931f) {
            ac.m1581b();
            return;
        }
        try {
            int i = new JSONObject(str).getInt(f932z[2]);
            f931f = true;
            new StringBuilder(f932z[0]).append(i).append("s");
            ac.m1581b();
            if (f929d == null) {
                f929d = new Handler(Looper.getMainLooper());
            }
            f929d.postDelayed(new C0511y(), (long) (i * 1000));
        } catch (JSONException e) {
            f931f = false;
            new StringBuilder(f932z[1]).append(e.getMessage());
            ac.m1581b();
        }
    }

    public void record(int i) {
        if (f931f) {
            f927a.execute(new C0510x(this, i));
        } else {
            ac.m1581b();
        }
    }
}
