package com.baidu.location.p042d;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import ch.qos.logback.core.joran.action.ActionConst;
import com.baidu.android.bbalbs.common.util.C1015b;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.location.C1102f;
import com.baidu.location.p041a.C1060k;
import io.rong.imlib.statistics.UserData;
import java.util.Locale;

/* renamed from: com.baidu.location.d.b */
public class C1091b {
    /* renamed from: d */
    public static String f2668d = null;
    /* renamed from: e */
    public static String f2669e = null;
    /* renamed from: f */
    public static String f2670f = null;
    /* renamed from: g */
    public static String f2671g = null;
    /* renamed from: h */
    private static C1091b f2672h = null;
    /* renamed from: a */
    public String f2673a = null;
    /* renamed from: b */
    public String f2674b = null;
    /* renamed from: c */
    public String f2675c = null;
    /* renamed from: i */
    private boolean f2676i = false;

    private C1091b() {
        if (C1102f.getServiceContext() != null) {
            m3992a(C1102f.getServiceContext());
        }
    }

    /* renamed from: a */
    public static C1091b m3989a() {
        if (f2672h == null) {
            f2672h = new C1091b();
        }
        return f2672h;
    }

    /* renamed from: a */
    public String m3990a(boolean z) {
        return m3991a(z, null);
    }

    /* renamed from: a */
    public String m3991a(boolean z, String str) {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.12f);
        if (z) {
            if (C1100j.f2736g.equals("all")) {
                stringBuffer.append("&addr=all");
            }
            if (C1100j.f2737h || C1100j.f2739j || C1100j.f2740k || C1100j.f2738i) {
                stringBuffer.append("&sema=");
                if (C1100j.f2737h) {
                    stringBuffer.append("aptag|");
                }
                if (C1100j.f2738i) {
                    stringBuffer.append("aptagd|");
                }
                if (C1100j.f2739j) {
                    stringBuffer.append("poiregion|");
                }
                if (C1100j.f2740k) {
                    stringBuffer.append("regular");
                }
            }
        }
        if (z) {
            if (str == null) {
                stringBuffer.append("&coor=gcj02");
            } else {
                stringBuffer.append("&coor=");
                stringBuffer.append(str);
            }
        }
        if (this.f2674b == null) {
            stringBuffer.append("&im=");
            stringBuffer.append(this.f2673a);
        } else {
            stringBuffer.append("&cu=");
            stringBuffer.append(this.f2674b);
            if (!(this.f2673a == null || this.f2673a.equals(ActionConst.NULL) || this.f2674b.contains(new StringBuffer(this.f2673a).reverse().toString()))) {
                stringBuffer.append("&Aim=");
                stringBuffer.append(this.f2673a);
            }
        }
        if (this.f2675c != null) {
            stringBuffer.append("&Aid=");
            stringBuffer.append(this.f2675c);
        }
        stringBuffer.append("&fw=");
        stringBuffer.append(C1102f.getFrameVersion());
        stringBuffer.append("&lt=1");
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        String b = C1100j.m4017b();
        if (b != null) {
            stringBuffer.append("&laip=");
            stringBuffer.append(b);
        }
        if (C1060k.m3789a().m3799e() != 0.0f) {
            stringBuffer.append("&altv=");
            stringBuffer.append(String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(r0)}));
        }
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        stringBuffer.append("&os=A");
        stringBuffer.append(VERSION.SDK);
        if (z) {
            stringBuffer.append("&sv=");
            b = VERSION.RELEASE;
            if (b != null && b.length() > 6) {
                b = b.substring(0, 6);
            }
            stringBuffer.append(b);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public void m3992a(Context context) {
        if (context != null && !this.f2676i) {
            try {
                this.f2673a = ((TelephonyManager) context.getSystemService(UserData.PHONE_KEY)).getDeviceId();
            } catch (Exception e) {
                this.f2673a = ActionConst.NULL;
            }
            try {
                this.f2674b = CommonParam.m3534a(context);
            } catch (Exception e2) {
                this.f2674b = null;
            }
            try {
                this.f2675c = C1015b.m3552b(context);
            } catch (Exception e3) {
                this.f2675c = null;
            }
            try {
                f2668d = context.getPackageName();
            } catch (Exception e4) {
                f2668d = null;
            }
            this.f2676i = true;
        }
    }

    /* renamed from: a */
    public void m3993a(String str, String str2) {
        f2669e = str;
        f2668d = str2;
    }

    /* renamed from: b */
    public String m3994b() {
        return this.f2674b != null ? "v7.12|" + this.f2674b + "|" + Build.MODEL : "v7.12|" + this.f2673a + "|" + Build.MODEL;
    }

    /* renamed from: c */
    public String m3995c() {
        return f2668d != null ? m3994b() + "|" + f2668d : m3994b();
    }
}
