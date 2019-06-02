package cn.jpush.android.p002a;

import cn.jpush.android.util.ac;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.a.a */
public class C0392a {
    /* renamed from: z */
    private static final String f401z;
    /* renamed from: a */
    private int f402a;
    /* renamed from: b */
    private int f403b;
    /* renamed from: c */
    private int f404c;
    /* renamed from: d */
    private int f405d;
    /* renamed from: e */
    private String f406e;
    /* renamed from: f */
    private double f407f;
    /* renamed from: g */
    private double f408g;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "M\r\u0005\u0000\u001eZf\u0002GH\u0013KJG\u001fYM\u000f\t\u0017u@\u0013\u000b\u0006DV%\n\u0016S\r\\@\u0016\u001a\r\n\n\u0011W[\u000f\n\u001cw]\u0003\u00041YK\u0003GH\u0013KJG\u001fYM\u000f\t\u0017xJ\u0012\u0012\u001dDD%\n\u0016S\r\\@\u0016\u001a\r\u0014\u0004\u0016_@2\u001c\u0002S\r\\GWE\rJG\u001eW[D_WP\u0003D\t\u001cQ\r\\@\u0014K";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f401z = r0;
        return;
    L_0x0035:
        r5 = 54;
        goto L_0x0019;
    L_0x0038:
        r5 = 47;
        goto L_0x0019;
    L_0x003b:
        r5 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x0019;
    L_0x003e:
        r5 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.a.<clinit>():void");
    }

    /* renamed from: a */
    public final int m975a() {
        return this.f402a;
    }

    /* renamed from: a */
    public final void m976a(int i) {
        this.f402a = i;
    }

    /* renamed from: a */
    public final void m977a(String str) {
        this.f406e = str;
    }

    /* renamed from: b */
    public final JSONArray m978b() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(new JSONObject(toString()));
            return jSONArray;
        } catch (JSONException e) {
            C0392a.class.getSimpleName();
            e.getMessage();
            ac.m1588e();
            return null;
        }
    }

    /* renamed from: b */
    public final void m979b(int i) {
        this.f403b = i;
    }

    /* renamed from: c */
    public final void m980c(int i) {
        this.f404c = i;
    }

    /* renamed from: d */
    public final void m981d(int i) {
        this.f405d = i;
    }

    public String toString() {
        try {
            return String.format(f401z, new Object[]{Integer.valueOf(this.f402a), Integer.valueOf(this.f403b), Integer.valueOf(this.f405d), Integer.valueOf(this.f404c), this.f406e, Double.valueOf(this.f407f), Double.valueOf(this.f408g)});
        } catch (Exception e) {
            return "";
        }
    }
}
