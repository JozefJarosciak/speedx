package com.alipay.p029b.p030a.p031a.p039e;

import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* renamed from: com.alipay.b.a.a.e.a */
public final class C0807a {
    /* renamed from: a */
    private String f1886a;
    /* renamed from: b */
    private String f1887b;
    /* renamed from: c */
    private String f1888c;
    /* renamed from: d */
    private String f1889d;
    /* renamed from: e */
    private String f1890e;
    /* renamed from: f */
    private String f1891f;
    /* renamed from: g */
    private String f1892g;

    public C0807a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f1886a = str;
        this.f1887b = str2;
        this.f1888c = str3;
        this.f1889d = str4;
        this.f1890e = str5;
        this.f1891f = str6;
        this.f1892g = str7;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + this.f1886a);
        stringBuffer.append("," + this.f1887b);
        stringBuffer.append("," + this.f1888c);
        stringBuffer.append("," + this.f1889d);
        if (C0789a.m3020a(this.f1890e) || this.f1890e.length() < 20) {
            stringBuffer.append("," + this.f1890e);
        } else {
            stringBuffer.append("," + this.f1890e.substring(0, 20));
        }
        if (C0789a.m3020a(this.f1891f) || this.f1891f.length() < 20) {
            stringBuffer.append("," + this.f1891f);
        } else {
            stringBuffer.append("," + this.f1891f.substring(0, 20));
        }
        if (C0789a.m3020a(this.f1892g) || this.f1892g.length() < 20) {
            stringBuffer.append("," + this.f1892g);
        } else {
            stringBuffer.append("," + this.f1892g.substring(0, 20));
        }
        return stringBuffer.toString();
    }
}
