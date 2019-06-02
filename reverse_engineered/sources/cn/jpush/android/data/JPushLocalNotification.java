package cn.jpush.android.data;

import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class JPushLocalNotification implements Serializable {
    /* renamed from: z */
    private static final String[] f591z;
    /* renamed from: a */
    private int f592a = 1;
    /* renamed from: b */
    private String f593b = "";
    /* renamed from: c */
    private String f594c = f591z[0];
    /* renamed from: d */
    private String f595d = f591z[0];
    /* renamed from: e */
    private long f596e = 0;
    /* renamed from: f */
    private String f597f;
    /* renamed from: g */
    private String f598g;
    /* renamed from: h */
    private String f599h;
    /* renamed from: i */
    private long f600i;
    /* renamed from: j */
    private long f601j = 1;
    /* renamed from: k */
    private int f602k = 1;
    /* renamed from: l */
    private String f603l = "";
    /* renamed from: m */
    private String f604m = "";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 15;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "XU";
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
            case 0: goto L_0x00c3;
            case 1: goto L_0x00c7;
            case 2: goto L_0x00cb;
            case 3: goto L_0x00cf;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 76;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u000b\n\u0019?)\u0006\u0011(?5\u0018\u0000";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0006:\u0018% \u0011";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0006\n\u0003\"*\u0001\u0006\u0016\"#\u0006:\u00032<\r";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u001b\r\u0018<\u0013\u001c\u001c\u0007.";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\t\u0001(?";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0006:\u001238\u001a\u0004\u0004";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0007\u0013\u00129>\u0001\u0001\u0012\u0014!\u001b\u0002(\"(";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0006:\u0003\"8\u0004\u0000";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0005:\u0014$\"\u001c\u0000\u0019?";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0006:\u0015>%\u0004\u0001\u00129\u0013\u0001\u0001";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0006:\u0014$\"\u001c\u0000\u0019?";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0005\u0016\u0010\u0014%\f";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = ";\u0000\u0003k8\u0001\b\u0012k*\t\f\u001bjl8\t\u0012*?\rE\u0014#)\u000b\u000eW2#\u001d\u0017W*>\u000f\u0016V";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\"5\u00028$$\n\u0014* &\n\u0003\"*\u0001\u0006\u0016?%\u0007\u000b";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        f591z = r4;
        return;
    L_0x00c3:
        r9 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x0020;
    L_0x00c7:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0020;
    L_0x00cb:
        r9 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x0020;
    L_0x00cf:
        r9 = 75;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.JPushLocalNotification.<clinit>():void");
    }

    /* renamed from: a */
    private static void m1269a(String str, String str2, JSONObject jSONObject) {
        if (!an.m1657a(str2)) {
            jSONObject.put(str, str2);
        }
    }

    public boolean equals(Object obj) {
        return this.f601j == ((JPushLocalNotification) obj).f601j;
    }

    public long getBroadcastTime() {
        return this.f596e;
    }

    public long getBuilderId() {
        return this.f600i;
    }

    public String getContent() {
        return this.f597f;
    }

    public String getExtras() {
        return this.f599h;
    }

    public long getNotificationId() {
        return this.f601j;
    }

    public String getTitle() {
        return this.f598g;
    }

    public int hashCode() {
        return (this.f601j).hashCode();
    }

    public void setBroadcastTime(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i < 0 || i2 <= 0 || i2 > 12 || i3 <= 0 || i3 > 31 || i4 < 0 || i4 > 23 || i5 < 0 || i5 > 59 || i6 < 0 || i6 > 59) {
            ac.m1589e(f591z[14], f591z[13]);
            return;
        }
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2 - 1, i3, i4, i5, i6);
        Date time = instance.getTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (time.getTime() < currentTimeMillis) {
            this.f596e = currentTimeMillis;
        } else {
            this.f596e = time.getTime();
        }
    }

    public void setBroadcastTime(long j) {
        this.f596e = j;
    }

    public void setBroadcastTime(Date date) {
        this.f596e = date.getTime();
    }

    public void setBuilderId(long j) {
        this.f600i = j;
    }

    public void setContent(String str) {
        this.f597f = str;
    }

    public void setExtras(String str) {
        this.f599h = str;
    }

    public void setNotificationId(long j) {
        this.f601j = j;
    }

    public void setTitle(String str) {
        this.f598g = str;
    }

    public String toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (!an.m1657a(this.f599h)) {
                jSONObject2.put(f591z[6], new JSONObject(this.f599h));
            }
            m1269a(f591z[11], this.f597f, jSONObject2);
            m1269a(f591z[8], this.f598g, jSONObject2);
            m1269a(f591z[11], this.f597f, jSONObject2);
            jSONObject2.put(f591z[5], 0);
            jSONObject.put(f591z[9], jSONObject2);
            m1269a(f591z[12], this.f601j, jSONObject);
            m1269a(f591z[1], this.f604m, jSONObject);
            m1269a(f591z[7], this.f603l, jSONObject);
            jSONObject.put(f591z[2], this.f602k);
            jSONObject.put(f591z[10], this.f600i);
            jSONObject.put(f591z[4], 3);
            jSONObject.put(f591z[3], 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
