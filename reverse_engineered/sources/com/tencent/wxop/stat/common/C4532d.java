package com.tencent.wxop.stat.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.alipay.sdk.sys.C0869a;
import com.avos.avoscloud.AVUser.AVThirdPartyUserAuth;
import com.tencent.wxop.stat.C4525a;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.au;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Marker;

/* renamed from: com.tencent.wxop.stat.common.d */
class C4532d {
    /* renamed from: a */
    String f16053a;
    /* renamed from: b */
    String f16054b;
    /* renamed from: c */
    DisplayMetrics f16055c;
    /* renamed from: d */
    int f16056d;
    /* renamed from: e */
    String f16057e;
    /* renamed from: f */
    String f16058f;
    /* renamed from: g */
    String f16059g;
    /* renamed from: h */
    String f16060h;
    /* renamed from: i */
    String f16061i;
    /* renamed from: j */
    String f16062j;
    /* renamed from: k */
    String f16063k;
    /* renamed from: l */
    int f16064l;
    /* renamed from: m */
    String f16065m;
    /* renamed from: n */
    String f16066n;
    /* renamed from: o */
    Context f16067o;
    /* renamed from: p */
    private String f16068p;
    /* renamed from: q */
    private String f16069q;
    /* renamed from: r */
    private String f16070r;
    /* renamed from: s */
    private String f16071s;

    private C4532d(Context context) {
        this.f16054b = StatConstants.VERSION;
        this.f16056d = VERSION.SDK_INT;
        this.f16057e = Build.MODEL;
        this.f16058f = Build.MANUFACTURER;
        this.f16059g = Locale.getDefault().getLanguage();
        this.f16064l = 0;
        this.f16065m = null;
        this.f16066n = null;
        this.f16067o = null;
        this.f16068p = null;
        this.f16069q = null;
        this.f16070r = null;
        this.f16071s = null;
        this.f16067o = context.getApplicationContext();
        this.f16055c = C4539k.m18057d(this.f16067o);
        this.f16053a = C4539k.m18068j(this.f16067o);
        this.f16060h = StatConfig.getInstallChannel(this.f16067o);
        this.f16061i = C4539k.m18067i(this.f16067o);
        this.f16062j = TimeZone.getDefault().getID();
        this.f16064l = C4539k.m18073o(this.f16067o);
        this.f16063k = C4539k.m18074p(this.f16067o);
        this.f16065m = this.f16067o.getPackageName();
        if (this.f16056d >= 14) {
            this.f16068p = C4539k.m18080v(this.f16067o);
        }
        this.f16069q = C4539k.m18079u(this.f16067o).toString();
        this.f16070r = C4539k.m18078t(this.f16067o);
        this.f16071s = C4539k.m18058d();
        this.f16066n = C4539k.m18038C(this.f16067o);
    }

    /* renamed from: a */
    void m18023a(JSONObject jSONObject, Thread thread) {
        if (thread == null) {
            if (this.f16055c != null) {
                jSONObject.put("sr", this.f16055c.widthPixels + Marker.ANY_MARKER + this.f16055c.heightPixels);
                jSONObject.put("dpi", this.f16055c.xdpi + Marker.ANY_MARKER + this.f16055c.ydpi);
            }
            if (C4525a.m17934a(this.f16067o).m17946e()) {
                JSONObject jSONObject2 = new JSONObject();
                C4545q.m18100a(jSONObject2, "bs", C4545q.m18105d(this.f16067o));
                C4545q.m18100a(jSONObject2, "ss", C4545q.m18106e(this.f16067o));
                if (jSONObject2.length() > 0) {
                    C4545q.m18100a(jSONObject, "wf", jSONObject2.toString());
                }
            }
            JSONArray a = C4545q.m18099a(this.f16067o, 10);
            if (a != null && a.length() > 0) {
                C4545q.m18100a(jSONObject, "wflist", a.toString());
            }
            C4545q.m18100a(jSONObject, "sen", this.f16068p);
        } else {
            C4545q.m18100a(jSONObject, "thn", thread.getName());
            C4545q.m18100a(jSONObject, AVThirdPartyUserAuth.SNS_TENCENT_WEIBO, StatConfig.getQQ(this.f16067o));
            C4545q.m18100a(jSONObject, "cui", StatConfig.getCustomUserId(this.f16067o));
            if (C4539k.m18056c(this.f16070r) && this.f16070r.split("/").length == 2) {
                C4545q.m18100a(jSONObject, "fram", this.f16070r.split("/")[0]);
            }
            if (C4539k.m18056c(this.f16071s) && this.f16071s.split("/").length == 2) {
                C4545q.m18100a(jSONObject, "from", this.f16071s.split("/")[0]);
            }
            if (au.m17968a(this.f16067o).m18001b(this.f16067o) != null) {
                jSONObject.put("ui", au.m17968a(this.f16067o).m18001b(this.f16067o).m18017b());
            }
            C4545q.m18100a(jSONObject, "mid", StatConfig.getLocalMidOnly(this.f16067o));
        }
        C4545q.m18100a(jSONObject, "pcn", C4539k.m18075q(this.f16067o));
        C4545q.m18100a(jSONObject, "osn", VERSION.RELEASE);
        C4545q.m18100a(jSONObject, C0869a.f2170k, this.f16053a);
        C4545q.m18100a(jSONObject, "ch", this.f16060h);
        C4545q.m18100a(jSONObject, "mf", this.f16058f);
        C4545q.m18100a(jSONObject, C0869a.f2167h, this.f16054b);
        C4545q.m18100a(jSONObject, "osd", Build.DISPLAY);
        C4545q.m18100a(jSONObject, "prod", Build.PRODUCT);
        C4545q.m18100a(jSONObject, "tags", Build.TAGS);
        C4545q.m18100a(jSONObject, "id", Build.ID);
        C4545q.m18100a(jSONObject, "fng", Build.FINGERPRINT);
        C4545q.m18100a(jSONObject, "lch", this.f16066n);
        C4545q.m18100a(jSONObject, "ov", Integer.toString(this.f16056d));
        jSONObject.put("os", 1);
        C4545q.m18100a(jSONObject, "op", this.f16061i);
        C4545q.m18100a(jSONObject, "lg", this.f16059g);
        C4545q.m18100a(jSONObject, "md", this.f16057e);
        C4545q.m18100a(jSONObject, "tz", this.f16062j);
        if (this.f16064l != 0) {
            jSONObject.put("jb", this.f16064l);
        }
        C4545q.m18100a(jSONObject, "sd", this.f16063k);
        C4545q.m18100a(jSONObject, "apn", this.f16065m);
        C4545q.m18100a(jSONObject, "cpu", this.f16069q);
        C4545q.m18100a(jSONObject, "abi", Build.CPU_ABI);
        C4545q.m18100a(jSONObject, "abi2", Build.CPU_ABI2);
        C4545q.m18100a(jSONObject, "ram", this.f16070r);
        C4545q.m18100a(jSONObject, "rom", this.f16071s);
    }
}
