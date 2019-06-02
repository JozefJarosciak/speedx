package cn.jpush.android.data;

import cn.jpush.android.util.an;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.data.e */
public final class C0433e {
    /* renamed from: z */
    private static final String[] f646z;
    /* renamed from: a */
    public int f647a;
    /* renamed from: b */
    public String f648b;
    /* renamed from: c */
    public String f649c;
    /* renamed from: d */
    public String f650d;
    /* renamed from: e */
    public String f651e;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 8;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = ">X";
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
            case 0: goto L_0x0079;
            case 1: goto L_0x007c;
            case 2: goto L_0x007f;
            case 3: goto L_0x0082;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 47;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "B\u0016";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "A\\\r`\\\n\u0015";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0016W\u001a";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u000eS\u000bpC";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u000eS\u000bpC\u0011B\u000f";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000fE\u001a";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0016_\u0010p";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        f646z = r4;
        return;
    L_0x0079:
        r9 = 98;
        goto L_0x0020;
    L_0x007c:
        r9 = 54;
        goto L_0x0020;
    L_0x007f:
        r9 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x0020;
    L_0x0082:
        r9 = 21;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.e.<clinit>():void");
    }

    public C0433e(int i, String str, String str2, String str3, String str4) {
        this.f647a = i;
        this.f651e = str;
        this.f649c = str3;
        this.f648b = str2;
        this.f650d = str4;
    }

    /* renamed from: a */
    public final int m1286a() {
        return toString().getBytes().length;
    }

    /* renamed from: b */
    public final JSONObject m1287b() {
        JSONObject jSONObject = new JSONObject();
        Object obj = (an.m1657a(this.f650d) || an.m1657a(this.f651e) || an.m1657a(this.f648b) || an.m1657a(this.f649c)) ? null : 1;
        if (obj == null) {
            return null;
        }
        try {
            jSONObject.put(f646z[4], this.f647a);
            jSONObject.put(f646z[5], this.f651e);
            jSONObject.put(f646z[7], this.f650d);
            jSONObject.put(f646z[3], this.f648b);
            jSONObject.put(f646z[6], this.f649c);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final String toString() {
        if (this.f649c != null && this.f649c.contains(f646z[0])) {
            this.f649c.replaceAll(f646z[0], f646z[2]);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f647a).append(f646z[1]);
        stringBuffer.append(this.f651e).append(f646z[1]);
        stringBuffer.append(this.f650d).append(f646z[1]);
        stringBuffer.append(this.f648b).append(f646z[1]);
        stringBuffer.append(this.f649c).append(f646z[1]);
        return stringBuffer.toString();
    }
}
