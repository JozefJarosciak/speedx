package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.C0404a;
import cn.jpush.android.data.C0433e;
import com.avos.avoscloud.AVException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.util.w */
public final class C0509w {
    /* renamed from: z */
    private static final String[] f1051z;
    /* renamed from: a */
    protected int f1052a = 0;
    /* renamed from: b */
    protected boolean f1053b = false;
    /* renamed from: c */
    private int f1054c = 2;
    /* renamed from: d */
    private int f1055d = 0;
    /* renamed from: e */
    private long f1056e = 0;
    /* renamed from: f */
    private long f1057f = 0;
    /* renamed from: g */
    private long f1058g = 0;
    /* renamed from: h */
    private long f1059h = 0;
    /* renamed from: i */
    private Context f1060i;
    /* renamed from: j */
    private ArrayList<C0433e> f1061j = new ArrayList();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 20;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "剖伅旉闣；";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
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
            case 0: goto L_0x00f9;
            case 1: goto L_0x00fd;
            case 2: goto L_0x0101;
            case 3: goto L_0x0105;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 1;
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
        goto L_0x0017;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0012;
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
            case 5: goto L_0x006b;
            case 6: goto L_0x0073;
            case 7: goto L_0x007c;
            case 8: goto L_0x0086;
            case 9: goto L_0x0091;
            case 10: goto L_0x009c;
            case 11: goto L_0x00a7;
            case 12: goto L_0x00b2;
            case 13: goto L_0x00bd;
            case 14: goto L_0x00c8;
            case 15: goto L_0x00d3;
            case 16: goto L_0x00de;
            case 17: goto L_0x00e9;
            case 18: goto L_0x00f4;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "u\u0010PpfZ.mrqP.K_dS,Ze";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "旚律夘尘；";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "U,Jdi`0PpfZ.";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "V(Vzd";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "K%Or";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\\3QcdQ(";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006b:
        r3[r2] = r1;
        r2 = 7;
        r1 = "丵拹樞弘；";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0073:
        r3[r2] = r1;
        r2 = 8;
        r1 = "O9M~n[";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007c:
        r3[r2] = r1;
        r2 = 9;
        r1 = "O=\\|dK";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0086:
        r3[r2] = r1;
        r2 = 10;
        r1 = "攉剬朲劶上叮捛仛－";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0091:
        r3[r2] = r1;
        r2 = 11;
        r1 = "K5Rr";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009c:
        r3[r2] = r1;
        r2 = 12;
        r1 = "旚律筶纰；";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a7:
        r3[r2] = r1;
        r2 = 13;
        r1 = "丵拹呗月；";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b2:
        r3[r2] = r1;
        r2 = 14;
        r1 = "S9IrmL";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00bd:
        r3[r2] = r1;
        r2 = 15;
        r1 = "觜柌夎贲；";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c8:
        r3[r2] = r1;
        r2 = 16;
        r1 = "弿妗觜枇/\u0011r";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d3:
        r3[r2] = r1;
        r2 = 17;
        r1 = "觜柌戯劈";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00de:
        r3[r2] = r1;
        r2 = 18;
        r1 = "黧诸区夰導Ｅm\u000f%5\u000f";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00e9:
        r3[r2] = r1;
        r2 = 19;
        r1 = "R3[r";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f4:
        r3[r2] = r1;
        f1051z = r4;
        return;
    L_0x00f9:
        r9 = 63;
        goto L_0x001f;
    L_0x00fd:
        r9 = 92;
        goto L_0x001f;
    L_0x0101:
        r9 = 63;
        goto L_0x001f;
    L_0x0105:
        r9 = 23;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.w.<clinit>():void");
    }

    protected C0509w() {
    }

    /* renamed from: a */
    private void m1811a(String str) {
        int length = str.length();
        while (length > 0) {
            length--;
            switch (str.charAt(length)) {
                case 'd':
                    this.f1052a |= 2;
                    break;
                case 'e':
                    this.f1052a |= 16;
                    break;
                case 'i':
                    this.f1052a |= 4;
                    break;
                case 'v':
                    this.f1052a |= 1;
                    break;
                case AVException.OPERATION_FORBIDDEN /*119*/:
                    this.f1052a |= 8;
                    break;
                default:
                    break;
            }
        }
    }

    /* renamed from: b */
    private void m1812b() {
        JSONObject d = m1814d();
        if (d != null) {
            ac.m1582b(f1051z[1], d.toString());
            ah.m1630b(this.f1060i, d);
        }
    }

    /* renamed from: c */
    private void m1813c() {
        this.f1053b = false;
        this.f1055d = 0;
        this.f1059h = 0;
        this.f1056e = 0;
        this.f1057f = 0;
        this.f1052a = 0;
        this.f1058g = 0;
        this.f1054c = 2;
        this.f1060i = null;
        this.f1061j.clear();
    }

    /* renamed from: d */
    private JSONObject m1814d() {
        if (this.f1061j == null) {
            return null;
        }
        int size = this.f1061j.size();
        if (size <= 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < size; i++) {
            jSONArray.put(((C0433e) this.f1061j.get(i)).m1287b());
        }
        if (jSONArray.length() <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f1051z[6], jSONArray);
            jSONObject.put(f1051z[5], f1051z[3]);
            jSONObject.put(f1051z[4], C0404a.m1126m());
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected final void m1815a() {
        if (!this.f1053b) {
            return;
        }
        if (System.currentTimeMillis() - this.f1058g >= this.f1059h) {
            m1812b();
            m1813c();
        } else if (this.f1054c == 1 && System.currentTimeMillis() - this.f1057f >= this.f1056e) {
            synchronized (this.f1061j) {
                m1812b();
                this.f1061j.clear();
                this.f1055d = 0;
                this.f1057f = System.currentTimeMillis();
            }
        }
    }

    /* renamed from: a */
    protected final void m1816a(Context context, String str) {
        ac.m1582b(f1051z[1], new StringBuilder(f1051z[10]).append(str).toString());
        ac.m1582b(f1051z[1], f1051z[16]);
        try {
            if (this.f1053b) {
                this.f1053b = false;
                m1812b();
                m1813c();
            }
            this.f1060i = context;
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(f1051z[19]);
            String string2 = jSONObject.getString(f1051z[14]);
            if (string != null) {
                if (string.equals(f1051z[9])) {
                    this.f1054c = 2;
                } else if (string.equals(f1051z[8])) {
                    this.f1054c = 1;
                }
            }
            m1811a(string2);
            this.f1059h = jSONObject.getLong(f1051z[11]) * 1000;
            ac.m1582b(f1051z[1], new StringBuilder(f1051z[7]).append(string).toString());
            ac.m1582b(f1051z[1], new StringBuilder(f1051z[12]).append(string2).toString());
            if (this.f1054c == 1) {
                this.f1056e = jSONObject.getLong(f1051z[8]) * 1000;
                this.f1057f = System.currentTimeMillis();
                ac.m1582b(f1051z[1], new StringBuilder(f1051z[13]).append(this.f1056e / 1000).append("s").toString());
                if (this.f1059h < 300000) {
                    this.f1054c = 2;
                }
            } else {
                ac.m1582b(f1051z[1], f1051z[18]);
            }
            this.f1058g = System.currentTimeMillis();
            this.f1053b = true;
            ac.m1582b(f1051z[1], f1051z[17]);
        } catch (JSONException e) {
            m1813c();
            ac.m1582b(f1051z[1], new StringBuilder(f1051z[15]).append(e.getMessage()).toString());
        }
    }

    /* renamed from: a */
    protected final void m1817a(C0433e c0433e) {
        if (this.f1053b) {
            synchronized (this.f1061j) {
                this.f1055d += c0433e.m1286a();
                ac.m1582b(f1051z[1], new StringBuilder(f1051z[2]).append(c0433e.m1286a()).toString());
                ac.m1582b(f1051z[1], new StringBuilder(f1051z[0]).append((this.f1059h - (System.currentTimeMillis() - this.f1058g)) / 1000).append("s").toString());
                switch (this.f1054c) {
                    case 1:
                        if (System.currentTimeMillis() - this.f1058g < this.f1059h) {
                            if (System.currentTimeMillis() - this.f1057f > this.f1056e) {
                                synchronized (this.f1061j) {
                                    m1812b();
                                    this.f1061j.clear();
                                    this.f1055d = 0;
                                    this.f1057f = System.currentTimeMillis();
                                }
                            }
                            this.f1061j.add(c0433e);
                            break;
                        }
                        m1812b();
                        m1813c();
                        break;
                    case 2:
                        if (System.currentTimeMillis() - this.f1058g < this.f1059h) {
                            if (((long) this.f1055d) >= 10240) {
                                m1812b();
                                this.f1061j.clear();
                                this.f1055d = c0433e.m1286a();
                            }
                            this.f1061j.add(c0433e);
                            break;
                        }
                        m1812b();
                        m1813c();
                        break;
                }
            }
        }
    }
}
