package cn.jpush.android.p003b.p004a;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import cn.jpush.android.util.ac;
import com.google.gson.jpush.C1483y;
import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.C4042k;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.b.a.d */
public final class C0423d {
    /* renamed from: z */
    private static final String[] f575z;
    /* renamed from: a */
    private HashMap<String, Method> f576a;
    /* renamed from: b */
    private String f577b;
    /* renamed from: c */
    private String f578c;
    /* renamed from: d */
    private C4042k f579d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 35;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "+D";
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
            case 0: goto L_0x019f;
            case 1: goto L_0x01a3;
            case 2: goto L_0x01a7;
            case 3: goto L_0x01ab;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 45;
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
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            case 27: goto L_0x0158;
            case 28: goto L_0x0163;
            case 29: goto L_0x016e;
            case 30: goto L_0x0179;
            case 31: goto L_0x0184;
            case 32: goto L_0x018f;
            case 33: goto L_0x019a;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0019r*{B\u0010?";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = ">d\u001drA\u0018]?eL";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "+X";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "+U";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "+G";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "+Y";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "]73f^\u00007+`HT`;q[\u001dr)3Y\u001b7<v\r\u0012~,`YTg?aL\u0019r*v_X7)zA\u00187<v\r\u0004v-`";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u000f5=|I\u00115d3\b\u0010;~1_\u0011d+YV-~6^\t";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "(5";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "Te;`X\u0018cd";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "Tt?AT}-|CN7";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u001ab2";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u001ab3qH\u0006";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0019r*{B\u0010";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u0015e9`";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0017v2\r\u0010v*r\r\u0011z.gT";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0000n.v^";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u001ax*3K\u001bb0w\r\u0019r*{B\u0010?";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "]7)zY\u001c7(rA\u001ds~cL\u0006v3vY\u0011e-";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\u0019r*{B\u00107;kH\u0017b*v\r\u0011e,|_N";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u001bu4vN\u0000";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0016x1H\u0015y";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0007c,zC\u0013";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "Tt?ATr,aB\u0006;~~H\u0007d?tHNz7`^Tz;gE\u001bs~}L\u0019r|n[\u0015e~v\u0010/JeuB\u0006?(r_Tc\"\u0016\u001c+8=A\u0011y9gEOu8\u0004\u000fa?a\r\u0017*8HE),(r_T}cgT\u0004r1u\r\u0017,;HHZ{;}J\u0000\u0003.GO~8;GI*|uX\u001at*zB\u001a5wh[\u0015e~w\u0010\u00159/fH\u0001rpH\u001ap*{\u0016\u00159/fH\u0001r\u0005wpIteuv\u001cJcwP\ta?a\r\u0013*\u0014@b:9.r_\u0007rvc_\u001bz.g\u0005>D\u0011]\u0003\u0007c,zC\u0013~8j\u0005\u000fz;gE\u001bsdu\u0003\u00077uY\\>rgT\u0004r-)HXv,t^Nq#:\u0004],7u\u0005\u00139=|I\u00116c!\u001dD>%gE\u0006x)1";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u001ev(r^\u0017e7cYN?8fC\u0017c7|C\\uwhN\u001by-|A\u001192|J\\5";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\u001dy7g\r\u001ed~v_\u0006x,)";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "T~0zY\u001dv2zW\u0015c7|CTr0w\u000f]jw;Z\u001dy:|Z],";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "T~0zY\u001dv2zW\u0015c7|CTu;tD\u001a5w([\u0015e~r\u0010\u000ff+vX\u0011-\u0005N\u0001\u0017v2O\u0015t5)K\u0001y=gD\u001byv:V\u0002v,3IIV,aL\r9.aB\u0000x*j]\u00119-D\u0017rppL\u0018{vr_\u0013b3vC\u0000dr#\u0004Oa?a\r\u0017*:=^\u001c~8g\u0005],(r_Trcw\u0003\u00077uY\\>egE\u001ddpbX\u0011b;HN)9?c]\u0018nvgE\u001ddrw\u0004O~8;\f\u0011>%wH\u0018r*v\r\u00007`\u0003\u0005b;fH/t\u0003nP\t,";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "Tt?ATr,aB\u0006;~pB\u0010rd1\u0006\u00139=|I\u0011<|?\r\u0019r-`L\u0013rd1\u0006\u00139,v^\u0001{*n_\u0011c+aCTppaH\u0007b2gPOX<yH\u0017cptH\u0000X)}}\u0006x.v_\u0000n\u0010r@\u0011dvr\u0004Zq1ah\u0015t6;K\u0001y=gD\u001byvw\u0004\u000fa?a\r\u0017*?HI),7u\u0005\u0000n.vB\u00127=.\u0010I58fC\u0017c7|CV1xw\fI*|pL\u0018{<rN\u001f5whL/s\u0003.K\u0001y=gD\u001byv:V\u0006r*f_\u001a7==L\u0004g2j\u0005\u0015;\u0005wpZt1}N\u0015cvR_\u0006v'=]\u0006x*|Y\rg;=^\u0018~=v\u0003\u0017v2\u0005\u0015e9f@\u0011y*`\u0001D>w:P\tjw(OZ";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\u00159{`\u0010";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "IvepB\u001ad1HZ{1t\u0005V";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\u0012b0pY\u001dx0;\u0004\u000fa?a\r\u0012*\u001fa_\u0015npc_\u001bc1gT\u0004rp`A\u001dt;=N\u0015{2;L\u0006p+~H\u001ac-?\u001d],7u\u0005\u001292vC\u0013c6/\u001c]l*{_\u001b`|";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "Y:s>\u0000Y:s>\u0000";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "\u001dy4vN\u0000r:3C\u0015z;3N\u0015y~}B\u00007<v\r\u001ab2";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        f575z = r4;
        return;
    L_0x019f:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0020;
    L_0x01a3:
        r9 = 23;
        goto L_0x0020;
    L_0x01a7:
        r9 = 94;
        goto L_0x0020;
    L_0x01ab:
        r9 = 19;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.d.<clinit>():void");
    }

    public C0423d(String str, Class cls) {
        try {
            if (TextUtils.isEmpty(str)) {
                throw new Exception(f575z[34]);
            }
            this.f577b = str;
            this.f576a = new HashMap();
            Method[] declaredMethods = cls.getDeclaredMethods();
            StringBuilder stringBuilder = new StringBuilder(f575z[25]);
            stringBuilder.append(this.f577b);
            stringBuilder.append(f575z[28]);
            for (Method method : declaredMethods) {
                if (method.getModifiers() == 9) {
                    String a = C0423d.m1224a(method);
                    if (a != null) {
                        this.f576a.put(a, method);
                        stringBuilder.append(String.format(f575z[30], new Object[]{method.getName()}));
                    }
                }
            }
            stringBuilder.append(f575z[32]);
            stringBuilder.append(this.f577b);
            stringBuilder.append(f575z[24]);
            stringBuilder.append(this.f577b);
            stringBuilder.append(f575z[29]);
            stringBuilder.append(this.f577b);
            stringBuilder.append(f575z[31]);
            stringBuilder.append(this.f577b);
            stringBuilder.append(f575z[27]);
            this.f578c = stringBuilder.toString();
            new StringBuilder(f575z[33]).append(stringBuilder.toString());
            ac.m1581b();
        } catch (Exception e) {
            Log.e(f575z[2], new StringBuilder(f575z[26]).append(e.getMessage()).toString());
        }
    }

    /* renamed from: a */
    private String m1223a(String str, int i, Object obj) {
        String str2;
        if (obj == null) {
            str2 = f575z[12];
        } else if (obj instanceof String) {
            str2 = "\"" + ((String) obj).replace("\"", f575z[9]) + "\"";
        } else if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof JSONObject)) {
            str2 = String.valueOf(obj);
        } else {
            if (this.f579d == null) {
                this.f579d = new C4042k();
            }
            C4042k c4042k = this.f579d;
            Object stringWriter;
            if (obj == null) {
                C3975w c3975w = C1483y.f7007a;
                stringWriter = new StringWriter();
                c4042k.a(c3975w, stringWriter);
                str2 = stringWriter.toString();
            } else {
                Type type = obj.getClass();
                stringWriter = new StringWriter();
                c4042k.a(obj, type, stringWriter);
                str2 = stringWriter.toString();
            }
        }
        str2 = String.format(f575z[8], new Object[]{Integer.valueOf(i), str2});
        Log.d(f575z[2], this.f577b + f575z[11] + str + f575z[10] + str2);
        return str2;
    }

    /* renamed from: a */
    private static String m1224a(Method method) {
        String name = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        int length = parameterTypes.length;
        if (length <= 0 || parameterTypes[0] != WebView.class) {
            Log.w(f575z[2], new StringBuilder(f575z[1]).append(name).append(f575z[7]).toString());
            return null;
        }
        String str = name;
        for (int i = 1; i < length; i++) {
            Class cls = parameterTypes[i];
            str = cls == String.class ? str + f575z[0] : (cls == Integer.TYPE || cls == Long.TYPE || cls == Float.TYPE || cls == Double.TYPE) ? str + f575z[6] : cls == Boolean.TYPE ? str + f575z[4] : cls == JSONObject.class ? str + f575z[3] : str + f575z[5];
        }
        return str;
    }

    /* renamed from: a */
    public final String m1225a() {
        return this.f578c;
    }

    /* renamed from: a */
    public final String m1226a(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            return m1223a(str, 500, f575z[16]);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(f575z[14]);
            JSONArray jSONArray = jSONObject.getJSONArray(f575z[17]);
            JSONArray jSONArray2 = jSONObject.getJSONArray(f575z[15]);
            int length = jSONArray.length();
            Object[] objArr = new Object[(length + 1)];
            int i = 0;
            objArr[0] = webView;
            int i2 = 0;
            while (i2 < length) {
                String str2;
                int i3;
                String optString = jSONArray.optString(i2);
                int i4;
                if (f575z[23].equals(optString)) {
                    optString = string + f575z[0];
                    objArr[i2 + 1] = jSONArray2.isNull(i2) ? null : jSONArray2.getString(i2);
                    i4 = i;
                    str2 = optString;
                    i3 = i4;
                } else if (f575z[13].equals(optString)) {
                    string = string + f575z[6];
                    i3 = ((i * 10) + i2) + 1;
                    str2 = string;
                } else if (f575z[22].equals(optString)) {
                    optString = string + f575z[4];
                    objArr[i2 + 1] = Boolean.valueOf(jSONArray2.getBoolean(i2));
                    i4 = i;
                    str2 = optString;
                    i3 = i4;
                } else if (f575z[21].equals(optString)) {
                    optString = string + f575z[3];
                    objArr[i2 + 1] = jSONArray2.isNull(i2) ? null : jSONArray2.getJSONObject(i2);
                    i4 = i;
                    str2 = optString;
                    i3 = i4;
                } else {
                    i4 = i;
                    str2 = string + f575z[5];
                    i3 = i4;
                }
                i2++;
                string = str2;
                i = i3;
            }
            Method method = (Method) this.f576a.get(string);
            if (method == null) {
                return m1223a(str, 500, new StringBuilder(f575z[18]).append(string).append(f575z[19]).toString());
            }
            if (i > 0) {
                Class[] parameterTypes = method.getParameterTypes();
                while (i > 0) {
                    i2 = i - ((i / 10) * 10);
                    Class cls = parameterTypes[i2];
                    if (cls == Integer.TYPE) {
                        objArr[i2] = Integer.valueOf(jSONArray2.getInt(i2 - 1));
                    } else if (cls == Long.TYPE) {
                        objArr[i2] = Long.valueOf(Long.parseLong(jSONArray2.getString(i2 - 1)));
                    } else {
                        objArr[i2] = Double.valueOf(jSONArray2.getDouble(i2 - 1));
                    }
                    i /= 10;
                }
            }
            return m1223a(str, 200, method.invoke(null, objArr));
        } catch (Exception e) {
            return e.getCause() != null ? m1223a(str, 500, new StringBuilder(f575z[20]).append(e.getCause().getMessage()).toString()) : m1223a(str, 500, new StringBuilder(f575z[20]).append(e.getMessage()).toString());
        }
    }
}
