package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.classic.spi.CallerData;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.cons.C0846c;
import com.alipay.sdk.data.C0847a;
import com.alipay.sdk.data.C0849c;
import com.alipay.sdk.packet.impl.C0866d;
import com.alipay.sdk.protocol.C0867a;
import com.alipay.sdk.protocol.C0868b;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.tid.C0871a;
import com.alipay.sdk.tid.C0872b;
import com.alipay.sdk.util.C0873a;
import com.alipay.sdk.util.C0877e;
import com.alipay.sdk.util.C0877e.C0813a;
import com.alipay.sdk.util.C0880h;
import com.alipay.sdk.util.C0881i;
import com.alipay.sdk.util.C0882j;
import com.alipay.sdk.util.C0885l;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.widget.C0889a;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PayTask {
    /* renamed from: a */
    static final Object f1909a = C0877e.class;
    /* renamed from: b */
    private Activity f1910b;
    /* renamed from: c */
    private C0889a f1911c;
    /* renamed from: d */
    private String f1912d = "wappaygw.alipay.com/service/rest.htm";
    /* renamed from: e */
    private String f1913e = "mclient.alipay.com/service/rest.htm";
    /* renamed from: f */
    private String f1914f = "mclient.alipay.com/home/exterfaceAssign.htm";
    /* renamed from: g */
    private Map<String, C0812a> f1915g = new HashMap();

    /* renamed from: com.alipay.sdk.app.PayTask$a */
    private class C0812a {
        /* renamed from: a */
        String f1906a;
        /* renamed from: b */
        String f1907b;
        /* renamed from: c */
        final /* synthetic */ PayTask f1908c;

        private C0812a(PayTask payTask) {
            this.f1908c = payTask;
            this.f1906a = "";
            this.f1907b = "";
        }

        /* renamed from: a */
        private String m3149a() {
            return this.f1906a;
        }

        /* renamed from: a */
        private void m3150a(String str) {
            this.f1906a = str;
        }

        /* renamed from: b */
        private String m3151b() {
            return this.f1907b;
        }

        /* renamed from: b */
        private void m3152b(String str) {
            this.f1907b = str;
        }
    }

    public PayTask(Activity activity) {
        this.f1910b = activity;
        C0870b a = C0870b.m3386a();
        Context context = this.f1910b;
        C0849c.m3265a();
        a.m3391a(context);
        C0823a.m3182a(activity);
        this.f1911c = new C0889a(activity, C0889a.f2241b);
    }

    public synchronized String pay(String str, boolean z) {
        String str2;
        Object obj = null;
        synchronized (this) {
            String a;
            if (z) {
                m3160b();
            }
            try {
                Context context;
                String[] split;
                int i;
                String[] split2;
                int i2;
                String a2 = new C0869a(this.f1910b).m3384a(str);
                if (!a2.contains("paymethod=\"expressGateway\"") && C0885l.m3472b(this.f1910b)) {
                    C0877e c0877e = new C0877e(this.f1910b, new C0820g(this));
                    a = c0877e.m3444a(a2);
                    c0877e.f2210a = null;
                    if (!TextUtils.equals(a, C0877e.f2209b)) {
                        if (TextUtils.isEmpty(a)) {
                            a = C0821h.m3171a();
                        }
                        context = this.f1910b;
                        if (!TextUtils.isEmpty(a)) {
                            split = a.split(C0880h.f2220b);
                            i = 0;
                            while (i < split.length) {
                                if (split[i].startsWith(C0880h.f2221c) && split[i].endsWith(C0880h.f2222d)) {
                                    split2 = split[i].substring(8, split[i].length() - 1).split(C0869a.f2161b);
                                    i2 = 0;
                                    while (i2 < split2.length) {
                                        if (!split2[i2].startsWith(C0880h.f2223e) && split2[i2].endsWith("\"")) {
                                            obj = split2[i2].substring(13, split2[i2].length() - 1);
                                            break;
                                        } else if (split2[i2].startsWith(C0880h.f2225g)) {
                                            obj = split2[i2].substring(12);
                                            break;
                                        } else {
                                            i2++;
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        if (!TextUtils.isEmpty(obj)) {
                            C0881i.m3450a(context, C0880h.f2219a, obj);
                        }
                        C0847a.m3258b().m3264a(this.f1910b);
                        m3161c();
                        C0823a.m3183a(this.f1910b, str);
                        str2 = a;
                    }
                }
                a = m3159b(a2);
                context = this.f1910b;
                if (TextUtils.isEmpty(a)) {
                    split = a.split(C0880h.f2220b);
                    i = 0;
                    while (i < split.length) {
                        split2 = split[i].substring(8, split[i].length() - 1).split(C0869a.f2161b);
                        i2 = 0;
                        while (i2 < split2.length) {
                            if (!split2[i2].startsWith(C0880h.f2223e)) {
                            }
                            if (split2[i2].startsWith(C0880h.f2225g)) {
                                obj = split2[i2].substring(12);
                                break;
                            }
                            i2++;
                        }
                        i++;
                    }
                }
                if (TextUtils.isEmpty(obj)) {
                    C0881i.m3450a(context, C0880h.f2219a, obj);
                }
            } catch (Throwable th) {
                try {
                    str2 = C0821h.m3171a();
                    C0847a.m3258b().m3264a(this.f1910b);
                    m3161c();
                    C0823a.m3183a(this.f1910b, str);
                } catch (Throwable th2) {
                    C0847a.m3258b().m3264a(this.f1910b);
                    m3161c();
                    C0823a.m3183a(this.f1910b, str);
                }
            }
            C0847a.m3258b().m3264a(this.f1910b);
            m3161c();
            C0823a.m3183a(this.f1910b, str);
            str2 = a;
        }
        return str2;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return C0882j.m3455a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return C0881i.m3451b(this.f1910b, C0880h.f2219a, "");
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        String trim;
        if (!TextUtils.isEmpty(str)) {
            String trim2;
            trim = str.trim();
            if (trim.startsWith("https://" + this.f1912d) || trim.startsWith("http://" + this.f1912d)) {
                trim2 = trim.replaceFirst("(http|https)://" + this.f1912d + "\\?", "").trim();
                if (!TextUtils.isEmpty(trim2)) {
                    trim = "_input_charset=\"utf-8\"&ordertoken=\"" + C0885l.m3463a("<request_token>", "</request_token>", (String) C0885l.m3465a(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new C0869a(this.f1910b).m3385a("sc", "h5tonative") + "\"";
                }
            }
            try {
                if (trim.startsWith("https://" + this.f1913e) || trim.startsWith("http://" + this.f1913e)) {
                    trim2 = trim.replaceFirst("(http|https)://" + this.f1913e + "\\?", "").trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        trim = "_input_charset=\"utf-8\"&ordertoken=\"" + C0885l.m3463a("<request_token>", "</request_token>", (String) C0885l.m3465a(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new C0869a(this.f1910b).m3385a("sc", "h5tonative") + "\"";
                    }
                }
                if ((trim.startsWith("https://" + this.f1914f) || trim.startsWith("http://" + this.f1914f)) && trim.contains("alipay.wap.create.direct.pay.by.user") && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://" + this.f1914f + "\\?", "").trim())) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("url", str);
                        jSONObject.put("bizcontext", new C0869a(this.f1910b).m3385a("sc", "h5tonative"));
                        trim = "new_external_info==" + jSONObject.toString();
                    } catch (Throwable th) {
                    }
                }
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)").matcher(str).find()) {
                    trim = C0885l.m3463a(CallerData.NA, "", str);
                    if (!TextUtils.isEmpty(trim)) {
                        Map a = C0885l.m3465a(trim);
                        StringBuilder stringBuilder = new StringBuilder();
                        if (m3158a(false, true, C0825c.f1950G, stringBuilder, a, C0825c.f1950G, "alipay_trade_no")) {
                            m3158a(true, false, "pay_phase_id", stringBuilder, a, "payPhaseId", "pay_phase_id", "out_relation_id");
                            stringBuilder.append("&biz_sub_type=\"TRADE\"");
                            stringBuilder.append("&biz_type=\"trade\"");
                            trim = (String) a.get("app_name");
                            if (TextUtils.isEmpty(trim) && !TextUtils.isEmpty((CharSequence) a.get("cid"))) {
                                trim = "ali1688";
                            } else if (TextUtils.isEmpty(trim) && !(TextUtils.isEmpty((CharSequence) a.get("sid")) && TextUtils.isEmpty((CharSequence) a.get("s_id")))) {
                                trim = "tb";
                            }
                            stringBuilder.append("&app_name=\"" + trim + "\"");
                            if (m3158a(true, true, "extern_token", stringBuilder, a, "extern_token", "cid", "sid", "s_id")) {
                                m3158a(true, false, "appenv", stringBuilder, a, "appenv");
                                stringBuilder.append("&pay_channel_id=\"alipay_sdk\"");
                                C0812a c0812a = new C0812a();
                                c0812a.f1906a = (String) a.get("return_url");
                                c0812a.f1907b = (String) a.get("pay_order_id");
                                trim = stringBuilder.toString() + "&bizcontext=\"" + new C0869a(this.f1910b).m3385a("sc", "h5tonative") + "\"";
                                this.f1915g.put(trim, c0812a);
                            } else {
                                trim = "";
                            }
                        }
                    }
                }
            } catch (Throwable th2) {
            }
        }
        trim = "";
        return trim;
    }

    /* renamed from: a */
    private static boolean m3158a(boolean z, boolean z2, String str, StringBuilder stringBuilder, Map<String, String> map, String... strArr) {
        String str2;
        String str3 = "";
        for (Object obj : strArr) {
            if (!TextUtils.isEmpty((CharSequence) map.get(obj))) {
                str2 = (String) map.get(obj);
                break;
            }
        }
        str2 = str3;
        if (TextUtils.isEmpty(str2)) {
            if (z2) {
                return false;
            }
        } else if (z) {
            stringBuilder.append(C0869a.f2161b).append(str).append("=\"").append(str2).append("\"");
        } else {
            stringBuilder.append(str).append("=\"").append(str2).append("\"");
        }
        return true;
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        synchronized (this) {
            H5PayResultModel h5PayResultModel2 = new H5PayResultModel();
            try {
                str.trim();
                String[] split = pay(str, z).split(C0880h.f2220b);
                Map hashMap = new HashMap();
                for (String str2 : split) {
                    String substring = str2.substring(0, str2.indexOf("={"));
                    String str3 = substring + "={";
                    hashMap.put(substring, str2.substring(str3.length() + str2.indexOf(str3), str2.lastIndexOf(C0880h.f2222d)));
                }
                if (hashMap.containsKey(C0882j.f2227a)) {
                    h5PayResultModel2.setResultCode((String) hashMap.get(C0882j.f2227a));
                }
                if (hashMap.containsKey("callBackUrl")) {
                    h5PayResultModel2.setReturnUrl((String) hashMap.get("callBackUrl"));
                } else if (hashMap.containsKey(C0882j.f2229c)) {
                    try {
                        String str4 = (String) hashMap.get(C0882j.f2229c);
                        if (str4.length() > 15) {
                            C0812a c0812a = (C0812a) this.f1915g.get(str);
                            if (c0812a != null) {
                                if (TextUtils.isEmpty(c0812a.f1907b)) {
                                    h5PayResultModel2.setReturnUrl(c0812a.f1906a);
                                } else {
                                    h5PayResultModel2.setReturnUrl(C0847a.m3258b().f2094j.replace("$OrderId$", c0812a.f1907b));
                                }
                                this.f1915g.remove(str);
                                h5PayResultModel = h5PayResultModel2;
                            } else {
                                CharSequence a = C0885l.m3463a("&callBackUrl=\"", "\"", str4);
                                if (TextUtils.isEmpty(a)) {
                                    a = C0885l.m3463a("&call_back_url=\"", "\"", str4);
                                    if (TextUtils.isEmpty(a)) {
                                        a = C0885l.m3463a(C0844a.f2058n, "\"", str4);
                                        if (TextUtils.isEmpty(a)) {
                                            a = URLDecoder.decode(C0885l.m3463a(C0844a.f2059o, C0869a.f2161b, str4), "utf-8");
                                            if (TextUtils.isEmpty(a)) {
                                                str4 = URLDecoder.decode(C0885l.m3463a("&callBackUrl=", C0869a.f2161b, str4), "utf-8");
                                                if (TextUtils.isEmpty(str4)) {
                                                    str4 = C0847a.m3258b().f2094j;
                                                }
                                                h5PayResultModel2.setReturnUrl(URLDecoder.decode(str4, "utf-8"));
                                            }
                                        }
                                    }
                                }
                                CharSequence charSequence = a;
                                if (TextUtils.isEmpty(str4)) {
                                    str4 = C0847a.m3258b().f2094j;
                                }
                                h5PayResultModel2.setReturnUrl(URLDecoder.decode(str4, "utf-8"));
                            }
                        } else {
                            C0812a c0812a2 = (C0812a) this.f1915g.get(str);
                            if (c0812a2 != null) {
                                h5PayResultModel2.setReturnUrl(c0812a2.f1906a);
                                this.f1915g.remove(str);
                                h5PayResultModel = h5PayResultModel2;
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
            }
            h5PayResultModel = h5PayResultModel2;
        }
        return h5PayResultModel;
    }

    /* renamed from: a */
    private static String m3156a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(C0880h.f2222d));
    }

    /* renamed from: a */
    private C0813a m3153a() {
        return new C0820g(this);
    }

    /* renamed from: b */
    private void m3160b() {
        if (this.f1911c != null) {
            this.f1911c.m3498a();
        }
    }

    /* renamed from: c */
    private void m3161c() {
        if (this.f1911c != null) {
            this.f1911c.m3499b();
        }
    }

    /* renamed from: a */
    private String m3155a(String str) {
        String a = new C0869a(this.f1910b).m3384a(str);
        if (a.contains("paymethod=\"expressGateway\"")) {
            return m3159b(a);
        }
        if (!C0885l.m3472b(this.f1910b)) {
            return m3159b(a);
        }
        C0877e c0877e = new C0877e(this.f1910b, new C0820g(this));
        String a2 = c0877e.m3444a(a);
        c0877e.f2210a = null;
        if (TextUtils.equals(a2, C0877e.f2209b)) {
            return m3159b(a);
        }
        if (TextUtils.isEmpty(a2)) {
            return C0821h.m3171a();
        }
        return a2;
    }

    public String getVersion() {
        return C0844a.f2049e;
    }

    /* renamed from: b */
    private String m3159b(String str) {
        C0871a c0871a;
        C0822i c0822i;
        int i = 0;
        m3160b();
        try {
            List a = C0868b.m3372a(new C0866d().mo2428a(this.f1910b, str).m3332a().optJSONObject(C0846c.f2072c).optJSONObject(C0846c.f2073d));
            for (int i2 = 0; i2 < a.size(); i2++) {
                if (((C0868b) a.get(i2)).f2157a == C0867a.Update) {
                    String[] strArr = ((C0868b) a.get(i2)).f2158b;
                    if (strArr.length == 3 && TextUtils.equals(C0845b.f2062c, strArr[0])) {
                        Context context = C0870b.m3386a().f2177a;
                        C0872b a2 = C0872b.m3405a();
                        if (!(TextUtils.isEmpty(strArr[1]) || TextUtils.isEmpty(strArr[2]))) {
                            a2.f2182a = strArr[1];
                            a2.f2183b = strArr[2];
                            c0871a = new C0871a(context);
                            c0871a.m3403a(C0873a.m3414a(context).m3422a(), C0873a.m3414a(context).m3423b(), a2.f2182a, a2.f2183b);
                            c0871a.close();
                        }
                    }
                }
            }
            m3161c();
            while (i < a.size()) {
                if (((C0868b) a.get(i)).f2157a == C0867a.WapPay) {
                    String a3 = m3154a((C0868b) a.get(i));
                    m3161c();
                    return a3;
                }
                i++;
            }
            m3161c();
            c0822i = null;
        } catch (Exception e) {
            c0871a.close();
        } catch (Throwable e2) {
            C0822i a4 = C0822i.m3178a(C0822i.NETWORK_ERROR.f1938h);
            C0823a.m3187a(C0825c.f1951a, e2);
            m3161c();
            c0822i = a4;
        } catch (Throwable th) {
            m3161c();
        }
        if (c0822i == null) {
            c0822i = C0822i.m3178a(C0822i.FAILED.f1938h);
        }
        return C0821h.m3172a(c0822i.f1938h, c0822i.f1939i, "");
    }

    /* renamed from: a */
    private String m3154a(C0868b c0868b) {
        String[] strArr = c0868b.f2158b;
        Intent intent = new Intent(this.f1910b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        if (strArr.length == 2) {
            bundle.putString("cookie", strArr[1]);
        }
        intent.putExtras(bundle);
        this.f1910b.startActivity(intent);
        synchronized (f1909a) {
            try {
                f1909a.wait();
            } catch (InterruptedException e) {
                return C0821h.m3171a();
            }
        }
        String str = C0821h.f1929a;
        if (TextUtils.isEmpty(str)) {
            return C0821h.m3171a();
        }
        return str;
    }
}
