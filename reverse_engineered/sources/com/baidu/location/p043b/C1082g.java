package com.baidu.location.p043b;

import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.alipay.sdk.util.C0880h;
import com.baidu.location.p042d.C1100j;
import java.util.List;
import java.util.regex.Pattern;

/* renamed from: com.baidu.location.b.g */
public class C1082g {
    /* renamed from: a */
    public List<ScanResult> f2633a = null;
    /* renamed from: b */
    private long f2634b = 0;
    /* renamed from: c */
    private long f2635c = 0;
    /* renamed from: d */
    private boolean f2636d = false;
    /* renamed from: e */
    private boolean f2637e;

    public C1082g(List<ScanResult> list, long j) {
        this.f2634b = j;
        this.f2633a = list;
        this.f2635c = System.currentTimeMillis();
        m3944i();
    }

    /* renamed from: a */
    private boolean m3943a(String str) {
        return TextUtils.isEmpty(str) ? false : Pattern.compile("wpa|wep", 2).matcher(str).find();
    }

    /* renamed from: i */
    private void m3944i() {
        if (m3945a() >= 1) {
            Object obj = 1;
            for (int size = this.f2633a.size() - 1; size >= 1 && r2 != null; size--) {
                int i = 0;
                obj = null;
                while (i < size) {
                    Object obj2;
                    if (((ScanResult) this.f2633a.get(i)).level < ((ScanResult) this.f2633a.get(i + 1)).level) {
                        ScanResult scanResult = (ScanResult) this.f2633a.get(i + 1);
                        this.f2633a.set(i + 1, this.f2633a.get(i));
                        this.f2633a.set(i, scanResult);
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    i++;
                    obj = obj2;
                }
            }
        }
    }

    /* renamed from: a */
    public int m3945a() {
        return this.f2633a == null ? 0 : this.f2633a.size();
    }

    /* renamed from: a */
    public String m3946a(int i) {
        return m3947a(i, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    public java.lang.String m3947a(int r26, boolean r27) {
        /*
        r25 = this;
        r2 = r25.m3945a();
        r3 = 1;
        if (r2 >= r3) goto L_0x0009;
    L_0x0007:
        r2 = 0;
    L_0x0008:
        return r2;
    L_0x0009:
        r3 = 0;
        r19 = new java.util.Random;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r19.<init>();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r20 = new java.lang.StringBuffer;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r0 = r20;
        r0.<init>(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r21 = new java.util.ArrayList;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r21.<init>();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = com.baidu.location.p043b.C1085h.m3959a();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r6 = r2.m3971i();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r5 = 0;
        r4 = 0;
        r2 = -1;
        if (r6 == 0) goto L_0x034e;
    L_0x002a:
        r7 = r6.getBSSID();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r7 == 0) goto L_0x034e;
    L_0x0030:
        r2 = r6.getBSSID();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r4 = ":";
        r5 = "";
        r5 = r2.replace(r4, r5);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r6.getRssi();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r4 = com.baidu.location.p043b.C1085h.m3959a();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r4 = r4.m3973k();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r2 >= 0) goto L_0x0346;
    L_0x004a:
        r2 = -r2;
        r16 = r2;
        r17 = r4;
        r18 = r5;
    L_0x0051:
        r4 = 0;
        r8 = 0;
        r2 = 0;
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r7 = 17;
        if (r6 < r7) goto L_0x0343;
    L_0x005c:
        r4 = android.os.SystemClock.elapsedRealtimeNanos();	 Catch:{ Error -> 0x00aa, Exception -> 0x0335 }
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 / r6;
    L_0x0063:
        r6 = 0;
        r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r6 <= 0) goto L_0x0343;
    L_0x0069:
        r2 = 1;
        r14 = r4;
    L_0x006b:
        if (r2 == 0) goto L_0x0340;
    L_0x006d:
        if (r2 == 0) goto L_0x00ae;
    L_0x006f:
        if (r27 == 0) goto L_0x00ae;
    L_0x0071:
        r2 = 1;
    L_0x0072:
        r13 = r2;
    L_0x0073:
        r6 = 0;
        r5 = 0;
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.size();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r4 = 1;
        r0 = r26;
        if (r2 <= r0) goto L_0x033c;
    L_0x0082:
        r2 = 0;
        r12 = r2;
    L_0x0084:
        r0 = r26;
        if (r12 >= r0) goto L_0x0210;
    L_0x0088:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.get(r12);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.level;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r2 != 0) goto L_0x00b0;
    L_0x0096:
        r2 = r4;
        r4 = r6;
        r6 = r8;
        r24 = r5;
        r5 = r3;
        r3 = r24;
    L_0x009e:
        r8 = r12 + 1;
        r12 = r8;
        r8 = r6;
        r6 = r4;
        r4 = r2;
        r24 = r3;
        r3 = r5;
        r5 = r24;
        goto L_0x0084;
    L_0x00aa:
        r4 = move-exception;
        r4 = 0;
        goto L_0x0063;
    L_0x00ae:
        r2 = 0;
        goto L_0x0072;
    L_0x00b0:
        if (r13 == 0) goto L_0x00d3;
    L_0x00b2:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Exception -> 0x0195, Error -> 0x01a3 }
        r2 = r2.get(r12);	 Catch:{ Exception -> 0x0195, Error -> 0x01a3 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Exception -> 0x0195, Error -> 0x01a3 }
        r10 = r2.timestamp;	 Catch:{ Exception -> 0x0195, Error -> 0x01a3 }
        r10 = r14 - r10;
        r22 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r10 = r10 / r22;
    L_0x00c5:
        r2 = java.lang.Long.valueOf(r10);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r21;
        r0.add(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d3;
    L_0x00d2:
        r8 = r10;
    L_0x00d3:
        if (r4 == 0) goto L_0x019a;
    L_0x00d5:
        r4 = 0;
        r2 = "&wf=";
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x00dd:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.get(r12);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.BSSID;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r2 == 0) goto L_0x0206;
    L_0x00eb:
        r7 = ":";
        r10 = "";
        r7 = r2.replace(r7, r10);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r7);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.get(r12);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.level;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r2 >= 0) goto L_0x0107;
    L_0x0106:
        r2 = -r2;
    L_0x0107:
        r10 = java.util.Locale.CHINA;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r11 = ";%d;";
        r22 = 1;
        r0 = r22;
        r0 = new java.lang.Object[r0];	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r22 = r0;
        r23 = 0;
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r22[r23] = r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r22;
        r2 = java.lang.String.format(r10, r11, r0);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r6 = r6 + 1;
        r2 = 0;
        if (r18 == 0) goto L_0x014b;
    L_0x012b:
        r0 = r18;
        r7 = r0.equals(r7);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r7 == 0) goto L_0x014b;
    L_0x0133:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.get(r12);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.capabilities;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r25;
        r2 = r0.m3943a(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r25;
        r0.f2637e = r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = 1;
        r5 = r6;
    L_0x014b:
        if (r2 != 0) goto L_0x01f5;
    L_0x014d:
        if (r3 != 0) goto L_0x01a7;
    L_0x014f:
        r2 = 10;
        r0 = r19;
        r2 = r0.nextInt(r2);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r7 = 2;
        if (r2 != r7) goto L_0x0339;
    L_0x015a:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.get(r12);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.SSID;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        if (r2 == 0) goto L_0x0339;
    L_0x0168:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.get(r12);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.SSID;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.length();	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r7 = 30;
        if (r2 >= r7) goto L_0x0339;
    L_0x017c:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.get(r12);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.SSID;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = 1;
    L_0x018e:
        r3 = r5;
        r5 = r2;
        r2 = r4;
        r4 = r6;
        r6 = r8;
        goto L_0x009e;
    L_0x0195:
        r2 = move-exception;
        r10 = 0;
        goto L_0x00c5;
    L_0x019a:
        r2 = "|";
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        goto L_0x00dd;
    L_0x01a3:
        r2 = move-exception;
        r2 = 0;
        goto L_0x0008;
    L_0x01a7:
        r2 = 1;
        if (r3 != r2) goto L_0x0339;
    L_0x01aa:
        r2 = 20;
        r0 = r19;
        r2 = r0.nextInt(r2);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r7 = 1;
        if (r2 != r7) goto L_0x0339;
    L_0x01b5:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.get(r12);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.SSID;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        if (r2 == 0) goto L_0x0339;
    L_0x01c3:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.get(r12);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.SSID;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.length();	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r7 = 30;
        if (r2 >= r7) goto L_0x0339;
    L_0x01d7:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.get(r12);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = r2.SSID;	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Exception -> 0x01ea, Error -> 0x01a3 }
        r2 = 2;
        goto L_0x018e;
    L_0x01ea:
        r2 = move-exception;
        r2 = r4;
        r4 = r6;
        r6 = r8;
        r24 = r5;
        r5 = r3;
        r3 = r24;
        goto L_0x009e;
    L_0x01f5:
        r0 = r25;
        r2 = r0.f2633a;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.get(r12);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (android.net.wifi.ScanResult) r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.SSID;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x0206:
        r2 = r4;
        r4 = r6;
        r6 = r8;
        r24 = r5;
        r5 = r3;
        r3 = r24;
        goto L_0x009e;
    L_0x0210:
        if (r4 != 0) goto L_0x0332;
    L_0x0212:
        r2 = new java.lang.StringBuilder;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2.<init>();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r3 = "&wf_n=";
        r2 = r2.append(r3);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.append(r5);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.toString();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r18 == 0) goto L_0x024b;
    L_0x022c:
        r2 = -1;
        r0 = r16;
        if (r0 == r2) goto L_0x024b;
    L_0x0231:
        r2 = new java.lang.StringBuilder;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2.<init>();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r3 = "&wf_rs=";
        r2 = r2.append(r3);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r16;
        r2 = r2.append(r0);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.toString();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x024b:
        r2 = 10;
        r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x02d2;
    L_0x0251:
        r2 = r21.size();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r2 <= 0) goto L_0x02d2;
    L_0x0257:
        r2 = 0;
        r0 = r21;
        r2 = r0.get(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (java.lang.Long) r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = r2.longValue();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r6 = 0;
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x02d2;
    L_0x026a:
        r6 = new java.lang.StringBuffer;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r6.<init>(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = "&wf_ut=";
        r6.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r4 = 1;
        r2 = 0;
        r0 = r21;
        r2 = r0.get(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = (java.lang.Long) r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r7 = r21.iterator();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x0284:
        r3 = r7.hasNext();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r3 == 0) goto L_0x02c9;
    L_0x028a:
        r3 = r7.next();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r3 = (java.lang.Long) r3;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r4 == 0) goto L_0x02a2;
    L_0x0292:
        r4 = 0;
        r8 = r3.longValue();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r6.append(r8);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r3 = r4;
    L_0x029b:
        r4 = "|";
        r6.append(r4);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r4 = r3;
        goto L_0x0284;
    L_0x02a2:
        r8 = r3.longValue();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r10 = r2.longValue();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r8 = r8 - r10;
        r10 = 0;
        r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r3 == 0) goto L_0x02c7;
    L_0x02b1:
        r3 = new java.lang.StringBuilder;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r3.<init>();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r10 = "";
        r3 = r3.append(r10);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r3 = r3.append(r8);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r3 = r3.toString();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r6.append(r3);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x02c7:
        r3 = r4;
        goto L_0x029b;
    L_0x02c9:
        r2 = r6.toString();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x02d2:
        r2 = "&wf_st=";
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r25;
        r2 = r0.f2634b;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = "&wf_et=";
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r25;
        r2 = r0.f2635c;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = "&wf_vt=";
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = com.baidu.location.p043b.C1085h.f2641a;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r5 <= 0) goto L_0x031a;
    L_0x0302:
        r2 = 1;
        r0 = r25;
        r0.f2636d = r2;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r2 = "&wf_en=";
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r25;
        r2 = r0.f2637e;	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        if (r2 == 0) goto L_0x0330;
    L_0x0314:
        r2 = 1;
    L_0x0315:
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x031a:
        if (r17 == 0) goto L_0x032a;
    L_0x031c:
        r2 = "&wf_gw=";
        r0 = r20;
        r0.append(r2);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        r0 = r20;
        r1 = r17;
        r0.append(r1);	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
    L_0x032a:
        r2 = r20.toString();	 Catch:{ Error -> 0x01a3, Exception -> 0x0335 }
        goto L_0x0008;
    L_0x0330:
        r2 = 0;
        goto L_0x0315;
    L_0x0332:
        r2 = 0;
        goto L_0x0008;
    L_0x0335:
        r2 = move-exception;
        r2 = 0;
        goto L_0x0008;
    L_0x0339:
        r2 = r3;
        goto L_0x018e;
    L_0x033c:
        r26 = r2;
        goto L_0x0082;
    L_0x0340:
        r13 = r2;
        goto L_0x0073;
    L_0x0343:
        r14 = r4;
        goto L_0x006b;
    L_0x0346:
        r16 = r2;
        r17 = r4;
        r18 = r5;
        goto L_0x0051;
    L_0x034e:
        r16 = r2;
        r17 = r4;
        r18 = r5;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.g.a(int, boolean):java.lang.String");
    }

    /* renamed from: a */
    public boolean m3948a(C1082g c1082g) {
        if (this.f2633a == null || c1082g == null || c1082g.f2633a == null) {
            return false;
        }
        int size = this.f2633a.size() < c1082g.f2633a.size() ? this.f2633a.size() : c1082g.f2633a.size();
        for (int i = 0; i < size; i++) {
            if (!((ScanResult) this.f2633a.get(i)).BSSID.equals(((ScanResult) c1082g.f2633a.get(i)).BSSID)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public String m3949b() {
        try {
            return m3947a(C1100j.f2716M, true);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public String m3950b(int i) {
        int i2 = 0;
        if (i == 0 || m3945a() < 1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        int size = this.f2633a.size();
        int i3 = size > C1100j.f2716M ? C1100j.f2716M : size;
        int i4 = 1;
        int i5 = 0;
        while (i5 < i3) {
            if ((i4 & i) == 0 || ((ScanResult) this.f2633a.get(i5)).BSSID == null) {
                size = i2;
            } else {
                if (i2 == 0) {
                    stringBuffer.append("&ssid=");
                } else {
                    stringBuffer.append("|");
                }
                stringBuffer.append(((ScanResult) this.f2633a.get(i5)).BSSID.replace(":", ""));
                stringBuffer.append(C0880h.f2220b);
                stringBuffer.append(((ScanResult) this.f2633a.get(i5)).SSID);
                size = i2 + 1;
            }
            i4 <<= 1;
            i5++;
            i2 = size;
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public boolean m3951b(C1082g c1082g) {
        if (this.f2633a == null || c1082g == null || c1082g.f2633a == null) {
            return false;
        }
        int size = this.f2633a.size() < c1082g.f2633a.size() ? this.f2633a.size() : c1082g.f2633a.size();
        for (int i = 0; i < size; i++) {
            String str = ((ScanResult) this.f2633a.get(i)).BSSID;
            int i2 = ((ScanResult) this.f2633a.get(i)).level;
            String str2 = ((ScanResult) c1082g.f2633a.get(i)).BSSID;
            int i3 = ((ScanResult) c1082g.f2633a.get(i)).level;
            if (!str.equals(str2) || i2 != i3) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public String m3952c() {
        try {
            return m3946a(15);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: c */
    public boolean m3953c(C1082g c1082g) {
        return C1085h.m3962a(c1082g, this, C1100j.f2719P);
    }

    /* renamed from: d */
    public int m3954d() {
        for (int i = 0; i < m3945a(); i++) {
            int i2 = -((ScanResult) this.f2633a.get(i)).level;
            if (i2 > 0) {
                return i2;
            }
        }
        return 0;
    }

    /* renamed from: e */
    public boolean m3955e() {
        return this.f2636d;
    }

    /* renamed from: f */
    public boolean m3956f() {
        return System.currentTimeMillis() - this.f2635c > 0 && System.currentTimeMillis() - this.f2635c < 5000;
    }

    /* renamed from: g */
    public boolean m3957g() {
        return System.currentTimeMillis() - this.f2635c > 0 && System.currentTimeMillis() - this.f2635c < 5000;
    }

    /* renamed from: h */
    public boolean m3958h() {
        return System.currentTimeMillis() - this.f2635c > 0 && System.currentTimeMillis() - this.f2634b < 5000;
    }
}
