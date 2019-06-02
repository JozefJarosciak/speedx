package cn.jpush.android.p002a;

import android.os.Handler;
import android.os.Message;
import cn.jpush.android.util.ac;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* renamed from: cn.jpush.android.a.f */
final class C0397f extends Handler {
    /* renamed from: z */
    private static final String[] f461z;
    /* renamed from: a */
    final /* synthetic */ C0395d f462a;
    /* renamed from: b */
    private float f463b;
    /* renamed from: c */
    private JSONArray f464c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "?\u001f#\u000f7=\u0014J\u00013#\u0001J\u0001>.\u0003-\u00072";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "8\u0004,\u000b\"\u0000:\u0004'\u0004%\u001e%\fLO";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = ",(\u0006.\"\u0000:\u0004'\u0004%\u001e%\fLOm";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "?\"\u00196V\u0005>\u0005,";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "OsJ";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "OfJ";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f461z = r4;
        return;
    L_0x0068:
        r9 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x001f;
    L_0x006b:
        r9 = 77;
        goto L_0x001f;
    L_0x006e:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x001f;
    L_0x0071:
        r9 = 66;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.f.<clinit>():void");
    }

    public final void handleMessage(Message message) {
        int i = 1;
        int i2 = 0;
        if (this.f462a.f444l == this) {
            switch (message.what) {
                case 1:
                    this.f464c = null;
                    this.f463b = Float.MIN_VALUE;
                    break;
                case 2:
                    break;
                case 5:
                    if (this.f462a.f448p == 2) {
                        removeMessages(2);
                        removeMessages(10);
                        this.f462a.f448p = 3;
                        if (this.f462a.f440h.m989f()) {
                            this.f462a.f440h.m987d();
                        }
                        JSONArray[] jSONArrayArr = new JSONArray[2];
                        jSONArrayArr[0] = this.f464c;
                        if (this.f462a.f451s.m1028a()) {
                            jSONArrayArr[1] = this.f462a.f451s.m1030c();
                        }
                        if (this.f464c != null) {
                            new StringBuilder(f461z[2]).append(this.f464c.toString());
                            ac.m1584c();
                            this.f462a.m1010a(this.f464c);
                        }
                        if (jSONArrayArr[1] != null) {
                            new StringBuilder(f461z[1]).append(jSONArrayArr[1].toString());
                            ac.m1584c();
                            this.f462a.m1012b(jSONArrayArr[1]);
                        }
                        C0395d.m994a(this.f462a, f461z[3]);
                        return;
                    }
                    return;
                case 10:
                    if (this.f462a.f448p == 1 && !this.f462a.f445m) {
                        if (!this.f462a.f440h.m989f()) {
                            if (this.f462a.f439g != this.f462a.f440h.m984a()) {
                                i = 0;
                            }
                            if (i == 0) {
                                sendEmptyMessageDelayed(10, (long) C0395d.f432b);
                                return;
                            }
                        } else if (this.f462a.f443k != null && this.f462a.f443k.length != 0) {
                            int[] d = this.f462a.f440h.m987d();
                            if (d.length != 0) {
                                if (this.f462a.f443k[0] == d[0]) {
                                    int i3;
                                    List<Object> arrayList = new ArrayList(this.f462a.f443k.length / 2);
                                    List arrayList2 = new ArrayList(d.length / 2);
                                    int length = this.f462a.f443k.length;
                                    for (i3 = 0; i3 < length; i3 += 2) {
                                        arrayList.add(Integer.valueOf(this.f462a.f443k[i3]));
                                    }
                                    for (i3 = 0; i3 < d.length; i3 += 2) {
                                        arrayList2.add(Integer.valueOf(d[i3]));
                                    }
                                    i3 = 0;
                                    for (Object contains : arrayList) {
                                        if (arrayList2.contains(contains)) {
                                            i3++;
                                        }
                                    }
                                    int size = arrayList.size() - i3;
                                    int size2 = arrayList2.size() - i3;
                                    if (size + size2 > i3) {
                                        i2 = 1;
                                    }
                                    if (i2 != 0) {
                                        StringBuilder append = new StringBuilder(size).append(f461z[5]);
                                        append.append(size2).append(f461z[4]);
                                        append.append(i3);
                                        C0395d.m994a(this.f462a, append.toString());
                                        return;
                                    }
                                    return;
                                }
                                C0395d.m994a(this.f462a, f461z[0]);
                            }
                        } else {
                            return;
                        }
                        this.f462a.m1015e();
                        return;
                    }
                    return;
                default:
                    return;
            }
            if (this.f462a.f448p == 2) {
                JSONArray c = this.f462a.f440h.m986c();
                this.f462a.f440h.m990g();
                if (c != null && 1.06535322E9f > this.f463b) {
                    this.f464c = c;
                    this.f463b = 1.06535322E9f;
                }
                sendEmptyMessageDelayed(2, 600);
            }
        }
    }
}
