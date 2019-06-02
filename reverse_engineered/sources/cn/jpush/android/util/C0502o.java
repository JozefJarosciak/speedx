package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0407c;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* renamed from: cn.jpush.android.util.o */
public class C0502o {
    /* renamed from: a */
    private static final String f1042a = C0502o.class.getSimpleName();
    /* renamed from: b */
    private static Map<String, String> f1043b = null;
    /* renamed from: z */
    private static final String[] f1044z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 19;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "FC!!osW1$nOV\u000b;iJ\\";
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
        r9 = 7;
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
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "_W?\rqIA';hB";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "O[5<iI_";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "^V'=kYG==i";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "MC$\rqIA';hB]5?b";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "@R:5rMT1";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "C@\u000b$b^@==i";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006b:
        r3[r2] = r1;
        r2 = 7;
        r1 = "XZ97}C]1";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0073:
        r3[r2] = r1;
        r2 = 8;
        r1 = "XJ$7";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007c:
        r3[r2] = r1;
        r2 = 9;
        r1 = "HV\";dIl=<aC";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0086:
        r3[r2] = r1;
        r2 = 10;
        r1 = "EG=?b";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0091:
        r3[r2] = r1;
        r2 = 11;
        r1 = "OC!\rnBU;";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009c:
        r3[r2] = r1;
        r2 = 12;
        r1 = "A\\07k";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a7:
        r3[r2] = r1;
        r2 = 13;
        r1 = "MC$\rqIA';hBP;6b";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b2:
        r3[r2] = r1;
        r2 = 14;
        r1 = "MC$\rlIJ";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00bd:
        r3[r2] = r1;
        r2 = 15;
        r1 = "wm5}m\u001e\u000eb*\u0015lz\u000f";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c8:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u000f\u001dd";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d3:
        r3[r2] = r1;
        r2 = 17;
        r1 = "_P&7bB@=(b";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00de:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u001e\u001de|0";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00e9:
        r3[r2] = r1;
        f1044z = r4;
        r0 = cn.jpush.android.util.C0502o.class;
        r0 = r0.getSimpleName();
        f1042a = r0;
        r0 = 0;
        f1043b = r0;
        return;
    L_0x00f9:
        r9 = 44;
        goto L_0x001f;
    L_0x00fd:
        r9 = 51;
        goto L_0x001f;
    L_0x0101:
        r9 = 84;
        goto L_0x001f;
    L_0x0105:
        r9 = 82;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.o.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1773a(Context context) {
        return context.getSharedPreferences(f1044z[0], 0).getString(f1044z[1], null);
    }

    /* renamed from: a */
    public static void m1774a(Context context, String str) {
        Editor edit = context.getSharedPreferences(f1044z[0], 0).edit();
        edit.putString(f1044z[1], str);
        edit.commit();
    }

    /* renamed from: a */
    private static void m1775a(Context context, Map<String, String> map) {
        if (map != null) {
            Set<String> keySet = map.keySet();
            if (keySet != null && keySet.size() > 0) {
                Editor edit = context.getSharedPreferences(f1044z[0], 0).edit();
                for (String str : keySet) {
                    edit.putString(str, (String) map.get(str));
                }
                edit.commit();
            }
        }
    }

    /* renamed from: b */
    public static void m1776b(Context context) {
        ac.m1581b();
        if (C0404a.m1145y()) {
            Map c = C0502o.m1777c(context);
            if (c != null && !c.isEmpty()) {
                Map hashMap;
                JSONObject jSONObject;
                if (f1043b == null) {
                    hashMap = new HashMap();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(f1044z[0], 0);
                    String string = sharedPreferences.getString(f1044z[11], null);
                    String string2 = sharedPreferences.getString(f1044z[3], null);
                    String string3 = sharedPreferences.getString(f1044z[2], null);
                    String string4 = sharedPreferences.getString(f1044z[14], null);
                    String string5 = sharedPreferences.getString(f1044z[13], null);
                    String string6 = sharedPreferences.getString(f1044z[4], null);
                    String string7 = sharedPreferences.getString(f1044z[5], null);
                    String string8 = sharedPreferences.getString(f1044z[7], null);
                    String string9 = sharedPreferences.getString(f1044z[1], null);
                    String string10 = sharedPreferences.getString(f1044z[12], null);
                    if (!an.m1657a(string)) {
                        hashMap.put(f1044z[11], string);
                    }
                    if (!an.m1657a(string2)) {
                        hashMap.put(f1044z[3], string2);
                    }
                    if (!an.m1657a(string3)) {
                        hashMap.put(f1044z[2], string3);
                    }
                    if (!an.m1657a(string4)) {
                        hashMap.put(f1044z[14], string4);
                    }
                    if (!an.m1657a(string5)) {
                        hashMap.put(f1044z[13], string5);
                    }
                    if (!an.m1657a(string6)) {
                        hashMap.put(f1044z[4], string6);
                    }
                    if (!an.m1657a(string7)) {
                        hashMap.put(f1044z[6], string7);
                    }
                    if (!an.m1657a(string7)) {
                        hashMap.put(f1044z[5], string7);
                    }
                    if (!an.m1657a(string8)) {
                        hashMap.put(f1044z[7], string8);
                    }
                    if (!an.m1657a(string9)) {
                        hashMap.put(f1044z[1], string9);
                    }
                    if (!an.m1657a(string10)) {
                        hashMap.put(f1044z[12], string10);
                    }
                    f1043b = hashMap;
                }
                hashMap = f1043b;
                Object obj = (hashMap == null || hashMap.isEmpty()) ? 1 : !c.equals(hashMap) ? 1 : null;
                if (obj != null) {
                    f1043b = c;
                    C0502o.m1775a(context, c);
                    try {
                        jSONObject = new JSONObject(c);
                        jSONObject.put(f1044z[10], C0404a.m1126m());
                        jSONObject.put(f1044z[8], f1044z[9]);
                        ah.m1621a(context, jSONObject);
                    } catch (JSONException e) {
                        e.getMessage();
                        ac.m1588e();
                        return;
                    }
                }
                jSONObject = C0407c.m1169a().m1178c(context);
                if (jSONObject != null) {
                    JSONObject jSONObject2 = new JSONObject(c);
                    if (jSONObject2 != null && jSONObject2.length() > 0) {
                        try {
                            jSONObject.put(f1044z[9], jSONObject2);
                        } catch (JSONException e2) {
                        }
                    }
                    ah.m1621a(context, jSONObject);
                    C0407c.m1174b(context);
                }
            }
        }
    }

    /* renamed from: c */
    private static Map<String, String> m1777c(Context context) {
        String format;
        String str;
        Exception exception;
        String str2;
        Exception exception2;
        context.getPackageManager();
        context.getPackageName();
        Map<String, String> hashMap = new HashMap();
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        try {
            str3 = C0490b.m1695c();
            str4 = C0490b.m1669a(context);
            format = new DecimalFormat(f1044z[16]).format(C0490b.m1739v(context));
            try {
                str5 = C0404a.m1051F();
                str6 = C0404a.m1050E();
                str7 = C0448e.f757i;
                str8 = C0448e.f758j.replaceAll(f1044z[15], "_");
                str9 = VERSION.RELEASE;
                str11 = f1044z[18];
                str = Build.MODEL;
            } catch (Exception e) {
                exception = e;
                str = null;
                str2 = null;
                exception2 = exception;
                exception2.getMessage();
                ac.m1588e();
                if (!an.m1657a(str3)) {
                    hashMap.put(f1044z[11], str3);
                }
                if (!an.m1657a(str4)) {
                    hashMap.put(f1044z[3], str4);
                }
                if (!an.m1657a(str5)) {
                    hashMap.put(f1044z[2], str5);
                }
                if (!an.m1657a(str6)) {
                    hashMap.put(f1044z[14], str6);
                }
                if (!an.m1657a(str7)) {
                    hashMap.put(f1044z[13], str7);
                }
                if (!an.m1657a(str8)) {
                    hashMap.put(f1044z[4], str8);
                }
                if (!an.m1657a(str9)) {
                    hashMap.put(f1044z[6], str9);
                }
                if (!an.m1657a(str2)) {
                    hashMap.put(f1044z[5], str2);
                }
                if (!an.m1657a(str10)) {
                    hashMap.put(f1044z[7], str10);
                }
                if (!an.m1657a(str11)) {
                    hashMap.put(f1044z[1], str11);
                }
                if (!an.m1657a(str)) {
                    hashMap.put(f1044z[12], str);
                }
                if (!an.m1657a(format)) {
                    hashMap.put(f1044z[17], format);
                }
                return hashMap;
            }
            try {
                str2 = context.getResources().getConfiguration().locale.toString();
                try {
                    long rawOffset = ((long) TimeZone.getDefault().getRawOffset()) / 3600000;
                    str10 = rawOffset > 0 ? new StringBuilder(Marker.ANY_NON_NULL_MARKER).append(rawOffset).toString() : rawOffset < 0 ? new StringBuilder(HelpFormatter.DEFAULT_OPT_PREFIX).append(rawOffset).toString() : rawOffset;
                } catch (Exception e2) {
                    exception2 = e2;
                    exception2.getMessage();
                    ac.m1588e();
                    if (an.m1657a(str3)) {
                        hashMap.put(f1044z[11], str3);
                    }
                    if (an.m1657a(str4)) {
                        hashMap.put(f1044z[3], str4);
                    }
                    if (an.m1657a(str5)) {
                        hashMap.put(f1044z[2], str5);
                    }
                    if (an.m1657a(str6)) {
                        hashMap.put(f1044z[14], str6);
                    }
                    if (an.m1657a(str7)) {
                        hashMap.put(f1044z[13], str7);
                    }
                    if (an.m1657a(str8)) {
                        hashMap.put(f1044z[4], str8);
                    }
                    if (an.m1657a(str9)) {
                        hashMap.put(f1044z[6], str9);
                    }
                    if (an.m1657a(str2)) {
                        hashMap.put(f1044z[5], str2);
                    }
                    if (an.m1657a(str10)) {
                        hashMap.put(f1044z[7], str10);
                    }
                    if (an.m1657a(str11)) {
                        hashMap.put(f1044z[1], str11);
                    }
                    if (an.m1657a(str)) {
                        hashMap.put(f1044z[12], str);
                    }
                    if (an.m1657a(format)) {
                        hashMap.put(f1044z[17], format);
                    }
                    return hashMap;
                }
            } catch (Exception e3) {
                exception = e3;
                str2 = null;
                exception2 = exception;
                exception2.getMessage();
                ac.m1588e();
                if (an.m1657a(str3)) {
                    hashMap.put(f1044z[11], str3);
                }
                if (an.m1657a(str4)) {
                    hashMap.put(f1044z[3], str4);
                }
                if (an.m1657a(str5)) {
                    hashMap.put(f1044z[2], str5);
                }
                if (an.m1657a(str6)) {
                    hashMap.put(f1044z[14], str6);
                }
                if (an.m1657a(str7)) {
                    hashMap.put(f1044z[13], str7);
                }
                if (an.m1657a(str8)) {
                    hashMap.put(f1044z[4], str8);
                }
                if (an.m1657a(str9)) {
                    hashMap.put(f1044z[6], str9);
                }
                if (an.m1657a(str2)) {
                    hashMap.put(f1044z[5], str2);
                }
                if (an.m1657a(str10)) {
                    hashMap.put(f1044z[7], str10);
                }
                if (an.m1657a(str11)) {
                    hashMap.put(f1044z[1], str11);
                }
                if (an.m1657a(str)) {
                    hashMap.put(f1044z[12], str);
                }
                if (an.m1657a(format)) {
                    hashMap.put(f1044z[17], format);
                }
                return hashMap;
            }
        } catch (Exception e4) {
            exception = e4;
            format = null;
            str = null;
            str2 = null;
            exception2 = exception;
            exception2.getMessage();
            ac.m1588e();
            if (an.m1657a(str3)) {
                hashMap.put(f1044z[11], str3);
            }
            if (an.m1657a(str4)) {
                hashMap.put(f1044z[3], str4);
            }
            if (an.m1657a(str5)) {
                hashMap.put(f1044z[2], str5);
            }
            if (an.m1657a(str6)) {
                hashMap.put(f1044z[14], str6);
            }
            if (an.m1657a(str7)) {
                hashMap.put(f1044z[13], str7);
            }
            if (an.m1657a(str8)) {
                hashMap.put(f1044z[4], str8);
            }
            if (an.m1657a(str9)) {
                hashMap.put(f1044z[6], str9);
            }
            if (an.m1657a(str2)) {
                hashMap.put(f1044z[5], str2);
            }
            if (an.m1657a(str10)) {
                hashMap.put(f1044z[7], str10);
            }
            if (an.m1657a(str11)) {
                hashMap.put(f1044z[1], str11);
            }
            if (an.m1657a(str)) {
                hashMap.put(f1044z[12], str);
            }
            if (an.m1657a(format)) {
                hashMap.put(f1044z[17], format);
            }
            return hashMap;
        }
        if (an.m1657a(str3)) {
            hashMap.put(f1044z[11], str3);
        }
        if (an.m1657a(str4)) {
            hashMap.put(f1044z[3], str4);
        }
        if (an.m1657a(str5)) {
            hashMap.put(f1044z[2], str5);
        }
        if (an.m1657a(str6)) {
            hashMap.put(f1044z[14], str6);
        }
        if (an.m1657a(str7)) {
            hashMap.put(f1044z[13], str7);
        }
        if (an.m1657a(str8)) {
            hashMap.put(f1044z[4], str8);
        }
        if (an.m1657a(str9)) {
            hashMap.put(f1044z[6], str9);
        }
        if (an.m1657a(str2)) {
            hashMap.put(f1044z[5], str2);
        }
        if (an.m1657a(str10)) {
            hashMap.put(f1044z[7], str10);
        }
        if (an.m1657a(str11)) {
            hashMap.put(f1044z[1], str11);
        }
        if (an.m1657a(str)) {
            hashMap.put(f1044z[12], str);
        }
        if (an.m1657a(format)) {
            hashMap.put(f1044z[17], format);
        }
        return hashMap;
    }
}
