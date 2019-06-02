package cn.jpush.android.data;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.C0448e;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0503p;
import cn.jpush.android.util.C0505r;
import cn.jpush.android.util.C0506s;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.data.c */
public abstract class C0429c implements Serializable {
    /* renamed from: E */
    private static final String[] f606E;
    /* renamed from: A */
    public String f607A;
    /* renamed from: B */
    public ArrayList<String> f608B = null;
    /* renamed from: C */
    public String f609C;
    /* renamed from: D */
    public String f610D;
    /* renamed from: a */
    private boolean f611a = false;
    /* renamed from: b */
    public int f612b;
    /* renamed from: c */
    public String f613c;
    /* renamed from: d */
    public String f614d;
    /* renamed from: e */
    public boolean f615e;
    /* renamed from: f */
    public int f616f;
    /* renamed from: g */
    public int f617g = 0;
    /* renamed from: h */
    public boolean f618h;
    /* renamed from: i */
    public String f619i;
    /* renamed from: j */
    public String f620j;
    /* renamed from: k */
    public String f621k;
    /* renamed from: l */
    public String f622l;
    /* renamed from: m */
    public String f623m;
    /* renamed from: n */
    public String f624n;
    /* renamed from: o */
    public int f625o;
    /* renamed from: p */
    public boolean f626p;
    /* renamed from: q */
    public List<String> f627q = null;
    /* renamed from: r */
    public int f628r;
    /* renamed from: s */
    public String f629s;
    /* renamed from: t */
    public String f630t;
    /* renamed from: u */
    public List<C0429c> f631u;
    /* renamed from: v */
    public boolean f632v = false;
    /* renamed from: w */
    public boolean f633w = false;
    /* renamed from: x */
    public boolean f634x = false;
    /* renamed from: y */
    public boolean f635y = false;
    /* renamed from: z */
    public int f636z = -1;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 12;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = " \u001f+9K/F3?E%4+=H\b\u0011>7A\u0013\u0019,?Q3\u001f:#\u0004l\\*\"H\u0011\u000e:6M9F";
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
            case 0: goto L_0x00a2;
            case 1: goto L_0x00a6;
            case 2: goto L_0x00aa;
            case 3: goto L_0x00ae;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 36;
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
        r1 = "\u0016\u000e6$Aa\u000f+?V \u001b:pA3\u000e0\"\ba\\<\"A \b:pM,\u001b6M-\u00196E(\u0010q";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0012\t<3A$\u0018$Ka\u001001@a\u001521C$\\rp";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = ")\b+ \u001enS";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0004\u0012+9P8";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = " \u001f+9K/F3?E%527v$\u000f}\u00044\u000e3j";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "/#:(P3\u001d,";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "/#9<E&";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = " \u0018\u00003K/\b:>P";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "'\t3<{2\u001f-5A/";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "/#<?J5\u00191$";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "/#+9P-\u0019";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        f606E = r4;
        return;
    L_0x00a2:
        r9 = 65;
        goto L_0x0020;
    L_0x00a6:
        r9 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        goto L_0x0020;
    L_0x00aa:
        r9 = 95;
        goto L_0x0020;
    L_0x00ae:
        r9 = 80;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.c.<clinit>():void");
    }

    /* renamed from: a */
    static String m1270a(String str, String str2, String str3, Context context) {
        new StringBuilder(f606E[5]).append(str);
        ac.m1576a();
        String str4 = "";
        if (!(!C0456f.m1402a(str) || context == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3))) {
            byte[] a = C0506s.m1801a(str, 5, 5000, 4);
            if (a != null) {
                try {
                    String str5 = C0503p.m1779a(context, str2) + str3;
                    C0505r.m1793a(str5, a);
                    new StringBuilder(f606E[2]).append(str5);
                    ac.m1576a();
                    return str5;
                } catch (IOException e) {
                    ac.m1593i();
                    return str4;
                }
            }
            C0459i.m1416a(str2, 1020, C0490b.m1685b(context, str), context);
        }
        return str4;
    }

    /* renamed from: a */
    static boolean m1271a(ArrayList<String> arrayList, Context context, String str, String str2, boolean z) {
        new StringBuilder(f606E[0]).append(str);
        ac.m1576a();
        if (!C0456f.m1402a(str) || context == null || arrayList.size() <= 0 || TextUtils.isEmpty(str2)) {
            return true;
        }
        Iterator it = arrayList.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            String str3 = (String) it.next();
            String str4 = (str3 == null || str3.startsWith(f606E[3])) ? str3 : str + str3;
            byte[] a = C0506s.m1801a(str4, 5, 5000, 4);
            if (a != null) {
                try {
                    if (str3.startsWith(f606E[3])) {
                        str3 = C0505r.m1795c(str3);
                    }
                    str3 = !z ? C0503p.m1779a(context, str2) + str3 : C0503p.m1783b(context, str2) + str3;
                    C0505r.m1793a(str3, a);
                    new StringBuilder(f606E[2]).append(str3);
                    ac.m1576a();
                } catch (Throwable e) {
                    ac.m1579a(f606E[4], f606E[1], e);
                    z2 = false;
                }
            } else {
                C0459i.m1416a(str2, 1020, C0490b.m1685b(context, str4), context);
                z2 = false;
            }
        }
        return z2;
    }

    /* renamed from: a */
    public abstract void mo2219a(Context context);

    /* renamed from: a */
    public final boolean m1273a() {
        return this.f625o == 3 || this.f625o == 1;
    }

    /* renamed from: a */
    protected abstract boolean mo2220a(Context context, JSONObject jSONObject);

    /* renamed from: b */
    public final boolean m1275b() {
        return this.f625o == 2;
    }

    /* renamed from: b */
    public final boolean m1276b(Context context, JSONObject jSONObject) {
        ac.m1576a();
        this.f626p = jSONObject.optInt(f606E[9], 0) > 0;
        this.f628r = jSONObject.optInt(f606E[7], 0);
        this.f629s = jSONObject.optString(f606E[11], "");
        this.f630t = jSONObject.optString(f606E[10], "");
        this.f622l = jSONObject.optString(f606E[6], "");
        if (an.m1657a(this.f629s)) {
            if (this.f618h) {
                ac.m1581b();
                this.f629s = C0448e.f752d;
            } else {
                ac.m1581b();
                C0459i.m1415a(this.f613c, 996, context);
                return false;
            }
        }
        JSONObject a = C0456f.m1399a(context, this.f613c, jSONObject, f606E[8]);
        if (a == null) {
            return this.f618h && this.f615e;
        } else {
            if (this.f618h && this.f615e) {
                this.f611a = true;
            }
            return mo2220a(context, a);
        }
    }

    /* renamed from: c */
    public final boolean m1277c() {
        return this.f625o == 3;
    }

    /* renamed from: d */
    public final String m1278d() {
        return m1273a() ? ((C0437i) this).f673K : m1275b() ? ((C0447s) this).f743E : this.f611a ? this.f610D : "";
    }

    /* renamed from: e */
    public final boolean m1279e() {
        return this.f611a;
    }
}
