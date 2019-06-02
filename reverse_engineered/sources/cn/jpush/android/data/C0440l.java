package cn.jpush.android.data;

import cn.jpush.android.util.C0499l;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.data.l */
public final class C0440l implements Serializable {
    /* renamed from: z */
    private static final String[] f691z;
    /* renamed from: a */
    public String f692a;
    /* renamed from: b */
    public String f693b;
    /* renamed from: c */
    public String f694c;
    /* renamed from: d */
    public String f695d;
    /* renamed from: e */
    public boolean f696e;
    /* renamed from: f */
    public String f697f;
    /* renamed from: g */
    public String f698g;
    /* renamed from: h */
    public String f699h;
    /* renamed from: i */
    public boolean f700i;
    /* renamed from: j */
    public String f701j;
    /* renamed from: k */
    public ArrayList<String> f702k = new ArrayList();
    /* renamed from: l */
    public String f703l;
    /* renamed from: m */
    public String f704m;
    /* renamed from: n */
    public String f705n;
    /* renamed from: o */
    final /* synthetic */ C0437i f706o;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 14;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "IH\u001ey[";
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
            case 0: goto L_0x00b8;
            case 1: goto L_0x00bc;
            case 2: goto L_0x00c0;
            case 3: goto L_0x00c4;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 28;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "IHi\u000fsZr";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "IHn\u0015lM";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "IHl\tn";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "IHs\u0001}OrE\u0019nD";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "IHs\u0002zG";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "IH\u0019nD";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "IHi\u0005fM";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "IHn\u0005hDr";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "IHh\to";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "IHs\u000fsFHo\u001ep";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "w~w\r{MG{\u0018t";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "w~y\u0003rxvn\u0004";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "w`\u000eLIp<}\\";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        f691z = r4;
        return;
    L_0x00b8:
        r9 = 40;
        goto L_0x0020;
    L_0x00bc:
        r9 = 23;
        goto L_0x0020;
    L_0x00c0:
        r9 = 26;
        goto L_0x0020;
    L_0x00c4:
        r9 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.l.<clinit>():void");
    }

    public C0440l(C0437i c0437i) {
        this.f706o = c0437i;
    }

    /* renamed from: a */
    private JSONObject m1315a() {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f691z[8], this.f692a);
            jSONObject.put(f691z[10], this.f693b);
            jSONObject.put(f691z[3], this.f694c);
            jSONObject.put(f691z[2], this.f695d);
            jSONObject.put(f691z[1], this.f696e ? 0 : 1);
            jSONObject.put(f691z[7], this.f697f);
            jSONObject.put(f691z[5], this.f698g);
            jSONObject.put(f691z[4], this.f699h);
            jSONObject.put(f691z[6], this.f701j);
            String str = f691z[9];
            if (!this.f700i) {
                i = 1;
            }
            jSONObject.put(str, i);
            jSONObject.put(f691z[0], C0499l.m1763a(this.f702k));
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public final String toString() {
        JSONObject a = m1315a();
        try {
            a.put(f691z[12], this.f703l);
            a.put(f691z[11], this.f704m);
            a.put(f691z[13], this.f705n);
        } catch (JSONException e) {
        }
        return a.toString();
    }
}
