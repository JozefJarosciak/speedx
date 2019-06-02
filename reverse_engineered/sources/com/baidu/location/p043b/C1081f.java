package com.baidu.location.p043b;

import ch.qos.logback.core.CoreConstants;
import java.util.List;

/* renamed from: com.baidu.location.b.f */
class C1081f {
    /* renamed from: a */
    public static String f2626a = null;
    /* renamed from: b */
    public int f2627b = 0;
    /* renamed from: c */
    private boolean f2628c = false;
    /* renamed from: d */
    private String f2629d = "";
    /* renamed from: e */
    private boolean f2630e = false;
    /* renamed from: f */
    private double f2631f = 0.0d;
    /* renamed from: g */
    private double f2632g = 0.0d;

    public C1081f(List<String> list, String str, String str2, String str3) {
        this.f2629d = str3;
        m3939d();
    }

    /* renamed from: a */
    private boolean m3938a(String str) {
        if (str == null || str.length() <= 8) {
            return false;
        }
        int i = 0;
        for (int i2 = 1; i2 < str.length() - 3; i2++) {
            i ^= str.charAt(i2);
        }
        return Integer.toHexString(i).equalsIgnoreCase(str.substring(str.length() + -2, str.length()));
    }

    /* renamed from: d */
    private void m3939d() {
        int i = 0;
        if (m3938a(this.f2629d)) {
            String substring = this.f2629d.substring(0, this.f2629d.length() - 3);
            int i2 = 0;
            while (i < substring.length()) {
                if (substring.charAt(i) == CoreConstants.COMMA_CHAR) {
                    i2++;
                }
                i++;
            }
            String[] split = substring.split(",", i2 + 1);
            if (split.length >= 6) {
                if (!(split[2].equals("") || split[split.length - 3].equals("") || split[split.length - 2].equals("") || split[split.length - 1].equals(""))) {
                    try {
                        this.f2631f = Double.valueOf(split[split.length - 3]).doubleValue();
                        this.f2632g = Double.valueOf(split[split.length - 2]).doubleValue();
                    } catch (Exception e) {
                    }
                    this.f2630e = true;
                }
            } else {
                return;
            }
        }
        this.f2628c = this.f2630e;
    }

    /* renamed from: a */
    public boolean m3940a() {
        return this.f2628c;
    }

    /* renamed from: b */
    public double m3941b() {
        return this.f2631f;
    }

    /* renamed from: c */
    public double m3942c() {
        return this.f2632g;
    }
}
