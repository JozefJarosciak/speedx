package com.digits.sdk.p177a;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.util.C0880h;
import com.digits.sdk.p177a.C2870e.C2868a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* compiled from: VCardBuilder */
/* renamed from: com.digits.sdk.a.b */
public class C2865b {
    /* renamed from: a */
    private static final Set<String> f13043a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"vnd.android.cursor.item/nickname", "vnd.android.cursor.item/contact_event", "vnd.android.cursor.item/relation"})));
    /* renamed from: b */
    private final int f13044b;
    /* renamed from: c */
    private final boolean f13045c;
    /* renamed from: d */
    private final boolean f13046d;
    /* renamed from: e */
    private final boolean f13047e;
    /* renamed from: f */
    private final boolean f13048f;
    /* renamed from: g */
    private final boolean f13049g;
    /* renamed from: h */
    private final boolean f13050h;
    /* renamed from: i */
    private final boolean f13051i;
    /* renamed from: j */
    private final boolean f13052j;
    /* renamed from: k */
    private final boolean f13053k;
    /* renamed from: l */
    private final String f13054l;
    /* renamed from: m */
    private final String f13055m;
    /* renamed from: n */
    private StringBuilder f13056n;
    /* renamed from: o */
    private boolean f13057o;

    public C2865b(int i, String str) {
        boolean z = false;
        this.f13044b = i;
        if (C2866c.m13798c(i)) {
            Log.w("vCard", "Should not use vCard 4.0 when building vCard. It is not officially published yet.");
        }
        boolean z2 = C2866c.m13797b(i) || C2866c.m13798c(i);
        this.f13045c = z2;
        this.f13048f = C2866c.m13799d(i);
        this.f13047e = C2866c.m13807l(i);
        this.f13046d = C2866c.m13806k(i);
        this.f13049g = C2866c.m13801f(i);
        this.f13051i = C2866c.m13802g(i);
        this.f13050h = C2866c.m13803h(i);
        this.f13052j = C2866c.m13806k(i);
        if (!(C2866c.m13797b(i) && "UTF-8".equalsIgnoreCase(str))) {
            z = true;
        }
        this.f13053k = z;
        if (C2866c.m13807l(i)) {
            if ("SHIFT_JIS".equalsIgnoreCase(str)) {
                this.f13054l = str;
            } else if (TextUtils.isEmpty(str)) {
                this.f13054l = "SHIFT_JIS";
            } else {
                this.f13054l = str;
            }
            this.f13055m = "CHARSET=SHIFT_JIS";
        } else if (TextUtils.isEmpty(str)) {
            Log.i("vCard", "Use the charset \"UTF-8\" for export.");
            this.f13054l = "UTF-8";
            this.f13055m = "CHARSET=UTF-8";
        } else {
            this.f13054l = str;
            this.f13055m = "CHARSET=" + str;
        }
        m13788a();
    }

    /* renamed from: a */
    public void m13788a() {
        this.f13056n = new StringBuilder();
        this.f13057o = false;
        m13791a("BEGIN", "VCARD");
        if (C2866c.m13798c(this.f13044b)) {
            m13791a("VERSION", "4.0");
        } else if (C2866c.m13797b(this.f13044b)) {
            m13791a("VERSION", "3.0");
        } else {
            if (!C2866c.m13796a(this.f13044b)) {
                Log.w("vCard", "Unknown vCard version detected.");
            }
            m13791a("VERSION", "2.1");
        }
    }

    /* renamed from: a */
    private boolean m13776a(ContentValues contentValues) {
        return (TextUtils.isEmpty(contentValues.getAsString("data3")) && TextUtils.isEmpty(contentValues.getAsString("data5")) && TextUtils.isEmpty(contentValues.getAsString("data2")) && TextUtils.isEmpty(contentValues.getAsString("data4")) && TextUtils.isEmpty(contentValues.getAsString("data6")) && TextUtils.isEmpty(contentValues.getAsString("data9")) && TextUtils.isEmpty(contentValues.getAsString("data8")) && TextUtils.isEmpty(contentValues.getAsString("data7")) && TextUtils.isEmpty(contentValues.getAsString("data1"))) ? false : true;
    }

    /* renamed from: c */
    private ContentValues m13781c(List<ContentValues> list) {
        ContentValues contentValues;
        ContentValues contentValues2 = null;
        ContentValues contentValues3 = null;
        for (ContentValues contentValues4 : list) {
            if (contentValues4 != null) {
                Integer asInteger = contentValues4.getAsInteger("is_super_primary");
                if (asInteger != null && asInteger.intValue() > 0) {
                    break;
                }
                if (contentValues3 == null) {
                    asInteger = contentValues4.getAsInteger("is_primary");
                    if (asInteger != null && asInteger.intValue() > 0 && m13776a(contentValues4)) {
                        ContentValues contentValues5 = contentValues2;
                        contentValues2 = contentValues4;
                        contentValues4 = contentValues5;
                        contentValues3 = contentValues2;
                        contentValues2 = contentValues4;
                    } else if (contentValues2 == null && m13776a(contentValues4)) {
                        contentValues2 = contentValues3;
                        contentValues3 = contentValues2;
                        contentValues2 = contentValues4;
                    }
                }
                contentValues4 = contentValues2;
                contentValues2 = contentValues3;
                contentValues3 = contentValues2;
                contentValues2 = contentValues4;
            }
        }
        contentValues4 = contentValues3;
        if (contentValues4 != null) {
            return contentValues4;
        }
        if (contentValues2 != null) {
            return contentValues2;
        }
        return new ContentValues();
    }

    /* renamed from: d */
    private C2865b m13783d(List<ContentValues> list) {
        if (this.f13047e || this.f13052j) {
            Log.w("vCard", "Invalid flag is used in vCard 4.0 construction. Ignored.");
        }
        if (list == null || list.isEmpty()) {
            m13791a("FN", "");
        } else {
            ContentValues c = m13781c((List) list);
            String asString = c.getAsString("data3");
            String asString2 = c.getAsString("data5");
            String asString3 = c.getAsString("data2");
            String asString4 = c.getAsString("data4");
            String asString5 = c.getAsString("data6");
            String asString6 = c.getAsString("data1");
            if (TextUtils.isEmpty(asString) && TextUtils.isEmpty(asString3) && TextUtils.isEmpty(asString2) && TextUtils.isEmpty(asString4) && TextUtils.isEmpty(asString5)) {
                if (TextUtils.isEmpty(asString6)) {
                    m13791a("FN", "");
                } else {
                    asString = asString6;
                }
            }
            String asString7 = c.getAsString("data9");
            String asString8 = c.getAsString("data8");
            String asString9 = c.getAsString("data7");
            String d = m13784d(asString);
            String d2 = m13784d(asString3);
            String d3 = m13784d(asString2);
            String d4 = m13784d(asString4);
            String d5 = m13784d(asString5);
            this.f13056n.append("N");
            if (!(TextUtils.isEmpty(asString7) && TextUtils.isEmpty(asString8) && TextUtils.isEmpty(asString9))) {
                this.f13056n.append(C0880h.f2220b);
                this.f13056n.append("SORT-AS=").append(C2870e.m13830e(m13784d(asString7) + ';' + m13784d(asString9) + ';' + m13784d(asString8)));
            }
            this.f13056n.append(":");
            this.f13056n.append(d);
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append(d2);
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append(d3);
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append(d4);
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append(d5);
            this.f13056n.append("\r\n");
            if (TextUtils.isEmpty(asString6)) {
                Log.w("vCard", "DISPLAY_NAME is empty.");
                m13791a("FN", m13784d(C2870e.m13813a(C2866c.m13800e(this.f13044b), asString, asString2, asString3, asString4, asString5)));
            } else {
                asString6 = m13784d(asString6);
                this.f13056n.append("FN");
                this.f13056n.append(":");
                this.f13056n.append(asString6);
                this.f13056n.append("\r\n");
            }
            m13778b(c);
        }
        return this;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public com.digits.sdk.p177a.C2865b m13786a(java.util.List<android.content.ContentValues> r15) {
        /*
        r14 = this;
        r7 = 1;
        r8 = 0;
        r0 = r14.f13044b;
        r0 = com.digits.sdk.p177a.C2866c.m13798c(r0);
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        r14 = r14.m13783d(r15);
    L_0x000e:
        return r14;
    L_0x000f:
        if (r15 == 0) goto L_0x0017;
    L_0x0011:
        r0 = r15.isEmpty();
        if (r0 == 0) goto L_0x003a;
    L_0x0017:
        r0 = r14.f13044b;
        r0 = com.digits.sdk.p177a.C2866c.m13797b(r0);
        if (r0 == 0) goto L_0x002e;
    L_0x001f:
        r0 = "N";
        r1 = "";
        r14.m13791a(r0, r1);
        r0 = "FN";
        r1 = "";
        r14.m13791a(r0, r1);
        goto L_0x000e;
    L_0x002e:
        r0 = r14.f13047e;
        if (r0 == 0) goto L_0x000e;
    L_0x0032:
        r0 = "N";
        r1 = "";
        r14.m13791a(r0, r1);
        goto L_0x000e;
    L_0x003a:
        r10 = r14.m13781c(r15);
        r0 = "data3";
        r1 = r10.getAsString(r0);
        r0 = "data5";
        r2 = r10.getAsString(r0);
        r0 = "data2";
        r3 = r10.getAsString(r0);
        r0 = "data4";
        r4 = r10.getAsString(r0);
        r0 = "data6";
        r5 = r10.getAsString(r0);
        r0 = "data1";
        r0 = r10.getAsString(r0);
        r6 = android.text.TextUtils.isEmpty(r1);
        if (r6 == 0) goto L_0x006e;
    L_0x0068:
        r6 = android.text.TextUtils.isEmpty(r3);
        if (r6 != 0) goto L_0x0221;
    L_0x006e:
        r6 = 5;
        r6 = new java.lang.String[r6];
        r6[r8] = r1;
        r6[r7] = r3;
        r9 = 2;
        r6[r9] = r2;
        r9 = 3;
        r6[r9] = r4;
        r9 = 4;
        r6[r9] = r5;
        r11 = r14.m13777a(r6);
        r6 = r14.f13051i;
        if (r6 != 0) goto L_0x0190;
    L_0x0086:
        r6 = new java.lang.String[r7];
        r6[r8] = r1;
        r6 = com.digits.sdk.p177a.C2870e.m13823b(r6);
        if (r6 == 0) goto L_0x00b8;
    L_0x0090:
        r6 = new java.lang.String[r7];
        r6[r8] = r3;
        r6 = com.digits.sdk.p177a.C2870e.m13823b(r6);
        if (r6 == 0) goto L_0x00b8;
    L_0x009a:
        r6 = new java.lang.String[r7];
        r6[r8] = r2;
        r6 = com.digits.sdk.p177a.C2870e.m13823b(r6);
        if (r6 == 0) goto L_0x00b8;
    L_0x00a4:
        r6 = new java.lang.String[r7];
        r6[r8] = r4;
        r6 = com.digits.sdk.p177a.C2870e.m13823b(r6);
        if (r6 == 0) goto L_0x00b8;
    L_0x00ae:
        r6 = new java.lang.String[r7];
        r6[r8] = r5;
        r6 = com.digits.sdk.p177a.C2870e.m13823b(r6);
        if (r6 != 0) goto L_0x0190;
    L_0x00b8:
        r6 = r7;
    L_0x00b9:
        r9 = android.text.TextUtils.isEmpty(r0);
        if (r9 != 0) goto L_0x0193;
    L_0x00bf:
        r9 = new java.lang.String[r7];
        r9[r8] = r0;
        r12 = r14.m13777a(r9);
        r9 = r14.f13051i;
        if (r9 != 0) goto L_0x019f;
    L_0x00cb:
        r9 = new java.lang.String[r7];
        r9[r8] = r0;
        r9 = com.digits.sdk.p177a.C2870e.m13823b(r9);
        if (r9 != 0) goto L_0x019f;
    L_0x00d5:
        r9 = r7;
    L_0x00d6:
        if (r6 == 0) goto L_0x01a2;
    L_0x00d8:
        r8 = r14.m13782c(r1);
        r7 = r14.m13782c(r3);
        r3 = r14.m13782c(r2);
        r2 = r14.m13782c(r4);
        r1 = r14.m13782c(r5);
        r4 = r3;
        r5 = r7;
        r3 = r2;
        r7 = r8;
        r2 = r1;
    L_0x00f1:
        if (r9 == 0) goto L_0x01bd;
    L_0x00f3:
        r1 = r14.m13782c(r0);
    L_0x00f7:
        r8 = r14.f13056n;
        r13 = "N";
        r8.append(r13);
        r8 = r14.f13047e;
        if (r8 == 0) goto L_0x01c3;
    L_0x0102:
        if (r11 == 0) goto L_0x0112;
    L_0x0104:
        r2 = r14.f13056n;
        r3 = ";";
        r2.append(r3);
        r2 = r14.f13056n;
        r3 = r14.f13055m;
        r2.append(r3);
    L_0x0112:
        if (r6 == 0) goto L_0x0122;
    L_0x0114:
        r2 = r14.f13056n;
        r3 = ";";
        r2.append(r3);
        r2 = r14.f13056n;
        r3 = "ENCODING=QUOTED-PRINTABLE";
        r2.append(r3);
    L_0x0122:
        r2 = r14.f13056n;
        r3 = ":";
        r2.append(r3);
        r2 = r14.f13056n;
        r2.append(r0);
        r0 = r14.f13056n;
        r2 = ";";
        r0.append(r2);
        r0 = r14.f13056n;
        r2 = ";";
        r0.append(r2);
        r0 = r14.f13056n;
        r2 = ";";
        r0.append(r2);
        r0 = r14.f13056n;
        r2 = ";";
        r0.append(r2);
    L_0x014a:
        r0 = r14.f13056n;
        r2 = "\r\n";
        r0.append(r2);
        r0 = r14.f13056n;
        r2 = "FN";
        r0.append(r2);
        if (r12 == 0) goto L_0x0168;
    L_0x015a:
        r0 = r14.f13056n;
        r2 = ";";
        r0.append(r2);
        r0 = r14.f13056n;
        r2 = r14.f13055m;
        r0.append(r2);
    L_0x0168:
        if (r9 == 0) goto L_0x0178;
    L_0x016a:
        r0 = r14.f13056n;
        r2 = ";";
        r0.append(r2);
        r0 = r14.f13056n;
        r2 = "ENCODING=QUOTED-PRINTABLE";
        r0.append(r2);
    L_0x0178:
        r0 = r14.f13056n;
        r2 = ":";
        r0.append(r2);
        r0 = r14.f13056n;
        r0.append(r1);
        r0 = r14.f13056n;
        r1 = "\r\n";
        r0.append(r1);
    L_0x018b:
        r14.m13778b(r10);
        goto L_0x000e;
    L_0x0190:
        r6 = r8;
        goto L_0x00b9;
    L_0x0193:
        r0 = r14.f13044b;
        r0 = com.digits.sdk.p177a.C2866c.m13800e(r0);
        r0 = com.digits.sdk.p177a.C2870e.m13813a(r0, r1, r2, r3, r4, r5);
        goto L_0x00bf;
    L_0x019f:
        r9 = r8;
        goto L_0x00d6;
    L_0x01a2:
        r8 = r14.m13784d(r1);
        r7 = r14.m13784d(r3);
        r3 = r14.m13784d(r2);
        r2 = r14.m13784d(r4);
        r1 = r14.m13784d(r5);
        r4 = r3;
        r5 = r7;
        r3 = r2;
        r7 = r8;
        r2 = r1;
        goto L_0x00f1;
    L_0x01bd:
        r1 = r14.m13784d(r0);
        goto L_0x00f7;
    L_0x01c3:
        if (r11 == 0) goto L_0x01d3;
    L_0x01c5:
        r0 = r14.f13056n;
        r8 = ";";
        r0.append(r8);
        r0 = r14.f13056n;
        r8 = r14.f13055m;
        r0.append(r8);
    L_0x01d3:
        if (r6 == 0) goto L_0x01e3;
    L_0x01d5:
        r0 = r14.f13056n;
        r6 = ";";
        r0.append(r6);
        r0 = r14.f13056n;
        r6 = "ENCODING=QUOTED-PRINTABLE";
        r0.append(r6);
    L_0x01e3:
        r0 = r14.f13056n;
        r6 = ":";
        r0.append(r6);
        r0 = r14.f13056n;
        r0.append(r7);
        r0 = r14.f13056n;
        r6 = ";";
        r0.append(r6);
        r0 = r14.f13056n;
        r0.append(r5);
        r0 = r14.f13056n;
        r5 = ";";
        r0.append(r5);
        r0 = r14.f13056n;
        r0.append(r4);
        r0 = r14.f13056n;
        r4 = ";";
        r0.append(r4);
        r0 = r14.f13056n;
        r0.append(r3);
        r0 = r14.f13056n;
        r3 = ";";
        r0.append(r3);
        r0 = r14.f13056n;
        r0.append(r2);
        goto L_0x014a;
    L_0x0221:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x025d;
    L_0x0227:
        r1 = "N";
        r14.m13780b(r1, r0);
        r1 = r14.f13056n;
        r2 = ";";
        r1.append(r2);
        r1 = r14.f13056n;
        r2 = ";";
        r1.append(r2);
        r1 = r14.f13056n;
        r2 = ";";
        r1.append(r2);
        r1 = r14.f13056n;
        r2 = ";";
        r1.append(r2);
        r1 = r14.f13056n;
        r2 = "\r\n";
        r1.append(r2);
        r1 = "FN";
        r14.m13780b(r1, r0);
        r0 = r14.f13056n;
        r1 = "\r\n";
        r0.append(r1);
        goto L_0x018b;
    L_0x025d:
        r0 = r14.f13044b;
        r0 = com.digits.sdk.p177a.C2866c.m13797b(r0);
        if (r0 == 0) goto L_0x0275;
    L_0x0265:
        r0 = "N";
        r1 = "";
        r14.m13791a(r0, r1);
        r0 = "FN";
        r1 = "";
        r14.m13791a(r0, r1);
        goto L_0x018b;
    L_0x0275:
        r0 = r14.f13047e;
        if (r0 == 0) goto L_0x018b;
    L_0x0279:
        r0 = "N";
        r1 = "";
        r14.m13791a(r0, r1);
        goto L_0x018b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digits.sdk.a.b.a(java.util.List):com.digits.sdk.a.b");
    }

    /* renamed from: b */
    private void m13780b(String str, String str2) {
        int i;
        String c;
        if (!this.f13051i) {
            if (!C2870e.m13823b(str2)) {
                i = 1;
                c = i == 0 ? m13782c(str2) : m13784d(str2);
                this.f13056n.append(str);
                if (m13777a(str2)) {
                    this.f13056n.append(C0880h.f2220b);
                    this.f13056n.append(this.f13055m);
                }
                if (i != 0) {
                    this.f13056n.append(C0880h.f2220b);
                    this.f13056n.append("ENCODING=QUOTED-PRINTABLE");
                }
                this.f13056n.append(":");
                this.f13056n.append(c);
            }
        }
        i = 0;
        if (i == 0) {
        }
        this.f13056n.append(str);
        if (m13777a(str2)) {
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append(this.f13055m);
        }
        if (i != 0) {
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append("ENCODING=QUOTED-PRINTABLE");
        }
        this.f13056n.append(":");
        this.f13056n.append(c);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    private void m13778b(android.content.ContentValues r12) {
        /*
        r11 = this;
        r10 = 32;
        r7 = 1;
        r6 = 0;
        r0 = "data9";
        r2 = r12.getAsString(r0);
        r0 = "data8";
        r1 = r12.getAsString(r0);
        r0 = "data7";
        r0 = r12.getAsString(r0);
        r3 = r11.f13052j;
        if (r3 == 0) goto L_0x0026;
    L_0x001a:
        r2 = com.digits.sdk.p177a.C2870e.m13831f(r2);
        r1 = com.digits.sdk.p177a.C2870e.m13831f(r1);
        r0 = com.digits.sdk.p177a.C2870e.m13831f(r0);
    L_0x0026:
        r3 = android.text.TextUtils.isEmpty(r2);
        if (r3 == 0) goto L_0x007c;
    L_0x002c:
        r3 = android.text.TextUtils.isEmpty(r1);
        if (r3 == 0) goto L_0x007c;
    L_0x0032:
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 == 0) goto L_0x007c;
    L_0x0038:
        r0 = r11.f13047e;
        if (r0 == 0) goto L_0x007b;
    L_0x003c:
        r0 = r11.f13056n;
        r1 = "SOUND";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = ";";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = "X-IRMC-N";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = ":";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = ";";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = ";";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = ";";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = ";";
        r0.append(r1);
        r0 = r11.f13056n;
        r1 = "\r\n";
        r0.append(r1);
    L_0x007b:
        return;
    L_0x007c:
        r3 = r11.f13044b;
        r3 = com.digits.sdk.p177a.C2866c.m13798c(r3);
        if (r3 == 0) goto L_0x01a1;
    L_0x0084:
        r3 = r11.f13049g;
        if (r3 == 0) goto L_0x007b;
    L_0x0088:
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x00e5;
    L_0x008e:
        r3 = r11.f13048f;
        if (r3 == 0) goto L_0x02c6;
    L_0x0092:
        r3 = new java.lang.String[r7];
        r3[r6] = r0;
        r3 = com.digits.sdk.p177a.C2870e.m13823b(r3);
        if (r3 != 0) goto L_0x02c6;
    L_0x009c:
        r4 = r7;
    L_0x009d:
        if (r4 == 0) goto L_0x02c9;
    L_0x009f:
        r3 = r11.m13782c(r0);
    L_0x00a3:
        r5 = r11.f13056n;
        r8 = "X-PHONETIC-FIRST-NAME";
        r5.append(r8);
        r5 = new java.lang.String[r7];
        r5[r6] = r0;
        r0 = r11.m13777a(r5);
        if (r0 == 0) goto L_0x00c2;
    L_0x00b4:
        r0 = r11.f13056n;
        r5 = ";";
        r0.append(r5);
        r0 = r11.f13056n;
        r5 = r11.f13055m;
        r0.append(r5);
    L_0x00c2:
        if (r4 == 0) goto L_0x00d2;
    L_0x00c4:
        r0 = r11.f13056n;
        r4 = ";";
        r0.append(r4);
        r0 = r11.f13056n;
        r4 = "ENCODING=QUOTED-PRINTABLE";
        r0.append(r4);
    L_0x00d2:
        r0 = r11.f13056n;
        r4 = ":";
        r0.append(r4);
        r0 = r11.f13056n;
        r0.append(r3);
        r0 = r11.f13056n;
        r3 = "\r\n";
        r0.append(r3);
    L_0x00e5:
        r0 = android.text.TextUtils.isEmpty(r1);
        if (r0 != 0) goto L_0x0142;
    L_0x00eb:
        r0 = r11.f13048f;
        if (r0 == 0) goto L_0x02cf;
    L_0x00ef:
        r0 = new java.lang.String[r7];
        r0[r6] = r1;
        r0 = com.digits.sdk.p177a.C2870e.m13823b(r0);
        if (r0 != 0) goto L_0x02cf;
    L_0x00f9:
        r3 = r7;
    L_0x00fa:
        if (r3 == 0) goto L_0x02d2;
    L_0x00fc:
        r0 = r11.m13782c(r1);
    L_0x0100:
        r4 = r11.f13056n;
        r5 = "X-PHONETIC-MIDDLE-NAME";
        r4.append(r5);
        r4 = new java.lang.String[r7];
        r4[r6] = r1;
        r1 = r11.m13777a(r4);
        if (r1 == 0) goto L_0x011f;
    L_0x0111:
        r1 = r11.f13056n;
        r4 = ";";
        r1.append(r4);
        r1 = r11.f13056n;
        r4 = r11.f13055m;
        r1.append(r4);
    L_0x011f:
        if (r3 == 0) goto L_0x012f;
    L_0x0121:
        r1 = r11.f13056n;
        r3 = ";";
        r1.append(r3);
        r1 = r11.f13056n;
        r3 = "ENCODING=QUOTED-PRINTABLE";
        r1.append(r3);
    L_0x012f:
        r1 = r11.f13056n;
        r3 = ":";
        r1.append(r3);
        r1 = r11.f13056n;
        r1.append(r0);
        r0 = r11.f13056n;
        r1 = "\r\n";
        r0.append(r1);
    L_0x0142:
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 != 0) goto L_0x007b;
    L_0x0148:
        r0 = r11.f13048f;
        if (r0 == 0) goto L_0x02d8;
    L_0x014c:
        r0 = new java.lang.String[r7];
        r0[r6] = r2;
        r0 = com.digits.sdk.p177a.C2870e.m13823b(r0);
        if (r0 != 0) goto L_0x02d8;
    L_0x0156:
        r1 = r7;
    L_0x0157:
        if (r1 == 0) goto L_0x02db;
    L_0x0159:
        r0 = r11.m13782c(r2);
    L_0x015d:
        r3 = r11.f13056n;
        r4 = "X-PHONETIC-LAST-NAME";
        r3.append(r4);
        r3 = new java.lang.String[r7];
        r3[r6] = r2;
        r2 = r11.m13777a(r3);
        if (r2 == 0) goto L_0x017c;
    L_0x016e:
        r2 = r11.f13056n;
        r3 = ";";
        r2.append(r3);
        r2 = r11.f13056n;
        r3 = r11.f13055m;
        r2.append(r3);
    L_0x017c:
        if (r1 == 0) goto L_0x018c;
    L_0x017e:
        r1 = r11.f13056n;
        r2 = ";";
        r1.append(r2);
        r1 = r11.f13056n;
        r2 = "ENCODING=QUOTED-PRINTABLE";
        r1.append(r2);
    L_0x018c:
        r1 = r11.f13056n;
        r2 = ":";
        r1.append(r2);
        r1 = r11.f13056n;
        r1.append(r0);
        r0 = r11.f13056n;
        r1 = "\r\n";
        r0.append(r1);
        goto L_0x007b;
    L_0x01a1:
        r3 = r11.f13044b;
        r3 = com.digits.sdk.p177a.C2866c.m13797b(r3);
        if (r3 == 0) goto L_0x01ef;
    L_0x01a9:
        r3 = r11.f13044b;
        r3 = com.digits.sdk.p177a.C2870e.m13820b(r3, r2, r1, r0);
        r4 = r11.f13056n;
        r5 = "SORT-STRING";
        r4.append(r5);
        r4 = r11.f13044b;
        r4 = com.digits.sdk.p177a.C2866c.m13797b(r4);
        if (r4 == 0) goto L_0x01d6;
    L_0x01be:
        r4 = new java.lang.String[r7];
        r4[r6] = r3;
        r4 = r11.m13777a(r4);
        if (r4 == 0) goto L_0x01d6;
    L_0x01c8:
        r4 = r11.f13056n;
        r5 = ";";
        r4.append(r5);
        r4 = r11.f13056n;
        r5 = r11.f13055m;
        r4.append(r5);
    L_0x01d6:
        r4 = r11.f13056n;
        r5 = ":";
        r4.append(r5);
        r4 = r11.f13056n;
        r3 = r11.m13784d(r3);
        r4.append(r3);
        r3 = r11.f13056n;
        r4 = "\r\n";
        r3.append(r4);
        goto L_0x0084;
    L_0x01ef:
        r3 = r11.f13046d;
        if (r3 == 0) goto L_0x0084;
    L_0x01f3:
        r3 = r11.f13056n;
        r4 = "SOUND";
        r3.append(r4);
        r3 = r11.f13056n;
        r4 = ";";
        r3.append(r4);
        r3 = r11.f13056n;
        r4 = "X-IRMC-N";
        r3.append(r4);
        r3 = r11.f13051i;
        if (r3 != 0) goto L_0x02af;
    L_0x020c:
        r3 = new java.lang.String[r7];
        r3[r6] = r2;
        r3 = com.digits.sdk.p177a.C2870e.m13823b(r3);
        if (r3 == 0) goto L_0x022a;
    L_0x0216:
        r3 = new java.lang.String[r7];
        r3[r6] = r1;
        r3 = com.digits.sdk.p177a.C2870e.m13823b(r3);
        if (r3 == 0) goto L_0x022a;
    L_0x0220:
        r3 = new java.lang.String[r7];
        r3[r6] = r0;
        r3 = com.digits.sdk.p177a.C2870e.m13823b(r3);
        if (r3 != 0) goto L_0x02af;
    L_0x022a:
        r3 = r7;
    L_0x022b:
        if (r3 == 0) goto L_0x02b2;
    L_0x022d:
        r5 = r11.m13782c(r2);
        r4 = r11.m13782c(r1);
        r3 = r11.m13782c(r0);
    L_0x0239:
        r8 = 3;
        r8 = new java.lang.String[r8];
        r8[r6] = r5;
        r8[r7] = r4;
        r9 = 2;
        r8[r9] = r3;
        r8 = r11.m13777a(r8);
        if (r8 == 0) goto L_0x0257;
    L_0x0249:
        r8 = r11.f13056n;
        r9 = ";";
        r8.append(r9);
        r8 = r11.f13056n;
        r9 = r11.f13055m;
        r8.append(r9);
    L_0x0257:
        r8 = r11.f13056n;
        r9 = ":";
        r8.append(r9);
        r8 = android.text.TextUtils.isEmpty(r5);
        if (r8 != 0) goto L_0x02e1;
    L_0x0264:
        r8 = r11.f13056n;
        r8.append(r5);
        r5 = r6;
    L_0x026a:
        r8 = android.text.TextUtils.isEmpty(r4);
        if (r8 != 0) goto L_0x0278;
    L_0x0270:
        if (r5 == 0) goto L_0x02c0;
    L_0x0272:
        r5 = r6;
    L_0x0273:
        r8 = r11.f13056n;
        r8.append(r4);
    L_0x0278:
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x028a;
    L_0x027e:
        if (r5 != 0) goto L_0x0285;
    L_0x0280:
        r4 = r11.f13056n;
        r4.append(r10);
    L_0x0285:
        r4 = r11.f13056n;
        r4.append(r3);
    L_0x028a:
        r3 = r11.f13056n;
        r4 = ";";
        r3.append(r4);
        r3 = r11.f13056n;
        r4 = ";";
        r3.append(r4);
        r3 = r11.f13056n;
        r4 = ";";
        r3.append(r4);
        r3 = r11.f13056n;
        r4 = ";";
        r3.append(r4);
        r3 = r11.f13056n;
        r4 = "\r\n";
        r3.append(r4);
        goto L_0x0084;
    L_0x02af:
        r3 = r6;
        goto L_0x022b;
    L_0x02b2:
        r5 = r11.m13784d(r2);
        r4 = r11.m13784d(r1);
        r3 = r11.m13784d(r0);
        goto L_0x0239;
    L_0x02c0:
        r8 = r11.f13056n;
        r8.append(r10);
        goto L_0x0273;
    L_0x02c6:
        r4 = r6;
        goto L_0x009d;
    L_0x02c9:
        r3 = r11.m13784d(r0);
        goto L_0x00a3;
    L_0x02cf:
        r3 = r6;
        goto L_0x00fa;
    L_0x02d2:
        r0 = r11.m13784d(r1);
        goto L_0x0100;
    L_0x02d8:
        r1 = r6;
        goto L_0x0157;
    L_0x02db:
        r0 = r11.m13784d(r2);
        goto L_0x015d;
    L_0x02e1:
        r5 = r7;
        goto L_0x026a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digits.sdk.a.b.b(android.content.ContentValues):void");
    }

    /* renamed from: a */
    public C2865b m13787a(List<ContentValues> list, C2867d c2867d) {
        Object obj;
        if (list != null) {
            Set hashSet = new HashSet();
            obj = null;
            for (ContentValues contentValues : list) {
                Integer asInteger = contentValues.getAsInteger("data2");
                String asString = contentValues.getAsString("data3");
                Integer asInteger2 = contentValues.getAsInteger("is_primary");
                boolean z = asInteger2 != null ? asInteger2.intValue() > 0 : false;
                String asString2 = contentValues.getAsString("data1");
                if (asString2 != null) {
                    asString2 = asString2.trim();
                }
                if (!TextUtils.isEmpty(asString2)) {
                    int intValue;
                    Object obj2;
                    if (asInteger != null) {
                        intValue = asInteger.intValue();
                    } else {
                        intValue = 1;
                    }
                    if (c2867d != null) {
                        asString2 = c2867d.m13808a(asString2, intValue, asString, z);
                        if (!hashSet.contains(asString2)) {
                            hashSet.add(asString2);
                            m13790a(Integer.valueOf(intValue), asString, asString2, z);
                            obj2 = obj;
                        }
                        obj2 = obj;
                    } else if (intValue == 6 || C2866c.m13805j(this.f13044b)) {
                        obj = 1;
                        if (!hashSet.contains(asString2)) {
                            hashSet.add(asString2);
                            m13790a(Integer.valueOf(intValue), asString, asString2, z);
                            r0 = 1;
                        }
                        obj2 = obj;
                    } else {
                        List<String> a = m13773a(asString2);
                        if (!a.isEmpty()) {
                            for (String asString22 : a) {
                                if (!hashSet.contains(asString22)) {
                                    String replace = asString22.replace(CoreConstants.COMMA_CHAR, 'p').replace(';', 'w');
                                    if (TextUtils.equals(replace, asString22)) {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        int length = asString22.length();
                                        for (int i = 0; i < length; i++) {
                                            char charAt = asString22.charAt(i);
                                            if (Character.isDigit(charAt) || charAt == '+') {
                                                stringBuilder.append(charAt);
                                            }
                                        }
                                        replace = C2868a.m13809a(stringBuilder.toString(), C2870e.m13812a(this.f13044b));
                                    }
                                    if (!(!C2866c.m13798c(this.f13044b) || TextUtils.isEmpty(replace) || replace.startsWith("tel:"))) {
                                        replace = "tel:" + replace;
                                    }
                                    hashSet.add(asString22);
                                    m13790a(Integer.valueOf(intValue), asString, replace, z);
                                }
                            }
                            r0 = 1;
                        }
                    }
                    obj = obj2;
                }
            }
        } else {
            obj = null;
        }
        if (obj == null && this.f13047e) {
            m13790a(Integer.valueOf(1), "", "", false);
        }
        return this;
    }

    /* renamed from: a */
    private List<String> m13773a(String str) {
        List<String> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        StringBuilder stringBuilder2 = stringBuilder;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '\n' || stringBuilder2.length() <= 0) {
                stringBuilder2.append(charAt);
            } else {
                arrayList.add(stringBuilder2.toString());
                stringBuilder2 = new StringBuilder();
            }
        }
        if (stringBuilder2.length() > 0) {
            arrayList.add(stringBuilder2.toString());
        }
        return arrayList;
    }

    /* renamed from: b */
    public C2865b m13795b(List<ContentValues> list) {
        boolean z;
        if (list != null) {
            Set hashSet = new HashSet();
            z = false;
            for (ContentValues contentValues : list) {
                String asString = contentValues.getAsString("data1");
                if (asString != null) {
                    asString = asString.trim();
                }
                if (!TextUtils.isEmpty(asString)) {
                    Integer asInteger = contentValues.getAsInteger("data2");
                    int intValue = asInteger != null ? asInteger.intValue() : 3;
                    String asString2 = contentValues.getAsString("data3");
                    Integer asInteger2 = contentValues.getAsInteger("is_primary");
                    boolean z2 = asInteger2 != null ? asInteger2.intValue() > 0 : false;
                    if (!hashSet.contains(asString)) {
                        hashSet.add(asString);
                        m13789a(intValue, asString2, asString, z2);
                    }
                    z = true;
                }
            }
        } else {
            z = false;
        }
        if (!z && this.f13047e) {
            m13789a(1, "", "", false);
        }
        return this;
    }

    /* renamed from: a */
    public void m13789a(int i, String str, String str2, boolean z) {
        CharSequence charSequence = null;
        switch (i) {
            case 0:
                if (!C2870e.m13816a(str)) {
                    if (!TextUtils.isEmpty(str)) {
                        if (C2870e.m13826c(str)) {
                            charSequence = "X-" + str;
                            break;
                        }
                    }
                }
                charSequence = "CELL";
                break;
                break;
            case 1:
                charSequence = "HOME";
                break;
            case 2:
                charSequence = "WORK";
                break;
            case 3:
                break;
            case 4:
                charSequence = "CELL";
                break;
            default:
                Log.e("vCard", "Unknown Email type: " + i);
                break;
        }
        List arrayList = new ArrayList();
        if (z) {
            arrayList.add("PREF");
        }
        if (!TextUtils.isEmpty(charSequence)) {
            arrayList.add(charSequence);
        }
        m13793a("EMAIL", arrayList, str2);
    }

    /* renamed from: a */
    public void m13790a(Integer num, String str, String str2, boolean z) {
        int i;
        this.f13056n.append("TEL");
        this.f13056n.append(C0880h.f2220b);
        if (num == null) {
            i = 7;
        } else {
            i = num.intValue();
        }
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case 0:
                if (!TextUtils.isEmpty(str)) {
                    if (!C2870e.m13816a(str)) {
                        if (!this.f13045c) {
                            String toUpperCase = str.toUpperCase(Locale.getDefault());
                            if (!C2870e.m13821b(toUpperCase)) {
                                if (C2870e.m13826c(str)) {
                                    arrayList.add("X-" + str);
                                    break;
                                }
                            }
                            arrayList.add(toUpperCase);
                            break;
                        }
                        arrayList.add(str);
                        break;
                    }
                    arrayList.add("CELL");
                    break;
                }
                arrayList.add("VOICE");
                break;
                break;
            case 1:
                arrayList.addAll(Arrays.asList(new String[]{"HOME"}));
                break;
            case 2:
                arrayList.add("CELL");
                break;
            case 3:
                arrayList.addAll(Arrays.asList(new String[]{"WORK"}));
                break;
            case 4:
                arrayList.addAll(Arrays.asList(new String[]{"WORK", "FAX"}));
                break;
            case 5:
                arrayList.addAll(Arrays.asList(new String[]{"HOME", "FAX"}));
                break;
            case 6:
                if (!this.f13047e) {
                    arrayList.add("PAGER");
                    break;
                } else {
                    arrayList.add("VOICE");
                    break;
                }
            case 7:
                arrayList.add("VOICE");
                break;
            case 9:
                arrayList.add("CAR");
                break;
            case 10:
                arrayList.add("WORK");
                z = true;
                break;
            case 11:
                arrayList.add("ISDN");
                break;
            case 12:
                z = true;
                break;
            case 13:
                arrayList.add("FAX");
                break;
            case 15:
                arrayList.add("TLX");
                break;
            case 17:
                arrayList.addAll(Arrays.asList(new String[]{"WORK", "CELL"}));
                break;
            case 18:
                arrayList.add("WORK");
                if (!this.f13047e) {
                    arrayList.add("PAGER");
                    break;
                } else {
                    arrayList.add("VOICE");
                    break;
                }
            case 20:
                arrayList.add("MSG");
                break;
        }
        if (z) {
            arrayList.add("PREF");
        }
        if (arrayList.isEmpty()) {
            m13774a(this.f13056n, Integer.valueOf(i));
        } else {
            m13785e(arrayList);
        }
        this.f13056n.append(":");
        this.f13056n.append(str2);
        this.f13056n.append("\r\n");
    }

    /* renamed from: a */
    private void m13774a(StringBuilder stringBuilder, Integer num) {
        if (this.f13047e) {
            stringBuilder.append("VOICE");
            return;
        }
        String a = C2870e.m13814a(num);
        if (a != null) {
            m13779b(a);
        } else {
            Log.e("vCard", "Unknown or unsupported (by vCard) Phone type: " + num);
        }
    }

    /* renamed from: a */
    public void m13793a(String str, List<String> list, String str2) {
        boolean z;
        boolean z2 = !C2870e.m13818a(str2);
        if (this.f13048f) {
            if (!C2870e.m13823b(str2)) {
                z = true;
                m13794a(str, list, str2, z2, z);
            }
        }
        z = false;
        m13794a(str, list, str2, z2, z);
    }

    /* renamed from: a */
    public void m13791a(String str, String str2) {
        m13792a(str, str2, false, false);
    }

    /* renamed from: a */
    public void m13792a(String str, String str2, boolean z, boolean z2) {
        m13794a(str, null, str2, z, z2);
    }

    /* renamed from: a */
    public void m13794a(String str, List<String> list, String str2, boolean z, boolean z2) {
        String c;
        this.f13056n.append(str);
        if (list != null && list.size() > 0) {
            this.f13056n.append(C0880h.f2220b);
            m13785e(list);
        }
        if (z) {
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append(this.f13055m);
        }
        if (z2) {
            this.f13056n.append(C0880h.f2220b);
            this.f13056n.append("ENCODING=QUOTED-PRINTABLE");
            c = m13782c(str2);
        } else {
            c = m13784d(str2);
        }
        this.f13056n.append(":");
        this.f13056n.append(c);
        this.f13056n.append("\r\n");
    }

    /* renamed from: e */
    private void m13785e(List<String> list) {
        Object obj = 1;
        for (String str : list) {
            Object obj2;
            String str2;
            if (C2866c.m13797b(this.f13044b) || C2866c.m13798c(this.f13044b)) {
                str2 = C2866c.m13798c(this.f13044b) ? C2870e.m13830e(str2) : C2870e.m13827d(str2);
                if (!TextUtils.isEmpty(str2)) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        this.f13056n.append(C0880h.f2220b);
                    }
                    m13779b(str2);
                    obj2 = obj;
                }
            } else if (C2870e.m13824c(str2)) {
                if (obj != null) {
                    obj = null;
                } else {
                    this.f13056n.append(C0880h.f2220b);
                }
                m13779b(str2);
                obj2 = obj;
            }
            obj = obj2;
        }
    }

    /* renamed from: b */
    private void m13779b(String str) {
        m13775a(this.f13056n, str);
    }

    /* renamed from: a */
    private void m13775a(StringBuilder stringBuilder, String str) {
        if (C2866c.m13798c(this.f13044b) || ((C2866c.m13797b(this.f13044b) || this.f13050h) && !this.f13047e)) {
            stringBuilder.append("TYPE").append(SimpleComparison.EQUAL_TO_OPERATION);
        }
        stringBuilder.append(str);
    }

    /* renamed from: a */
    private boolean m13777a(String... strArr) {
        if (!this.f13053k) {
            return false;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (!C2870e.m13818a(strArr[i])) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    private String m13782c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte[] bytes;
        int i;
        int i2;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bytes = str.getBytes(this.f13054l);
            i = 0;
            i2 = 0;
        } catch (UnsupportedEncodingException e) {
            Log.e("vCard", "Charset " + this.f13054l + " cannot be used. " + "Try default charset");
            bytes = str.getBytes();
            i = 0;
            i2 = 0;
        }
        while (i2 < bytes.length) {
            stringBuilder.append(String.format("=%02X", new Object[]{Byte.valueOf(bytes[i2])}));
            i2++;
            i += 3;
            if (i >= 67) {
                stringBuilder.append("=\r\n");
                i = 0;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: d */
    private String m13784d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\r':
                    if (i + 1 < length && str.charAt(i) == '\n') {
                        break;
                    }
                case '\n':
                    stringBuilder.append("\\n");
                    break;
                case ',':
                    if (!this.f13045c) {
                        stringBuilder.append(charAt);
                        break;
                    }
                    stringBuilder.append("\\,");
                    break;
                case ';':
                    stringBuilder.append(CoreConstants.ESCAPE_CHAR);
                    stringBuilder.append(';');
                    break;
                case '\\':
                    if (this.f13045c) {
                        stringBuilder.append("\\\\");
                        break;
                    }
                case '<':
                case '>':
                    if (!this.f13047e) {
                        stringBuilder.append(charAt);
                        break;
                    }
                    stringBuilder.append(CoreConstants.ESCAPE_CHAR);
                    stringBuilder.append(charAt);
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public String toString() {
        if (!this.f13057o) {
            if (this.f13047e) {
                m13791a("X-CLASS", "PUBLIC");
                m13791a("X-REDUCTION", "");
                m13791a("X-NO", "");
                m13791a("X-DCM-HMN-MODE", "");
            }
            m13791a("END", "VCARD");
            this.f13057o = true;
        }
        return this.f13056n.toString();
    }
}
