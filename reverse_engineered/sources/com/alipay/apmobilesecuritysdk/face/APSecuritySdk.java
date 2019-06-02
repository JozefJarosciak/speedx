package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p022a.C0764a;
import com.alipay.apmobilesecuritysdk.p023b.C0765a;
import com.alipay.apmobilesecuritysdk.p026e.C0772a;
import com.alipay.apmobilesecuritysdk.p027f.C0773a;
import com.alipay.apmobilesecuritysdk.p027f.C0776d;
import com.alipay.apmobilesecuritysdk.p027f.C0779g;
import com.alipay.apmobilesecuritysdk.p027f.C0780h;
import com.alipay.apmobilesecuritysdk.p027f.C0781i;
import com.alipay.apmobilesecuritysdk.p028g.C0784b;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.sdk.cons.C0845b;
import java.util.HashMap;
import java.util.Map;

public class APSecuritySdk {
    /* renamed from: a */
    private static APSecuritySdk f1844a;
    /* renamed from: c */
    private static Object f1845c = new Object();
    /* renamed from: b */
    private Context f1846b;

    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    public class TokenResult {
        /* renamed from: a */
        final /* synthetic */ APSecuritySdk f1843a;
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult(APSecuritySdk aPSecuritySdk) {
            this.f1843a = aPSecuritySdk;
        }
    }

    private APSecuritySdk(Context context) {
        this.f1846b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f1844a == null) {
            synchronized (f1845c) {
                if (f1844a == null) {
                    f1844a = new APSecuritySdk(context);
                }
            }
        }
        return f1844a;
    }

    public static String getUtdid(Context context) {
        return "";
    }

    public String getApdidToken() {
        return C0764a.m2915a(this.f1846b, "");
    }

    public String getSdkName() {
        return "security-sdk-token";
    }

    public String getSdkVersion() {
        return "3.2.0-20160621";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult(this);
        try {
            tokenResult.apdidToken = C0764a.m2915a(this.f1846b, "");
            tokenResult.clientKey = C0780h.m2982c(this.f1846b);
            tokenResult.apdid = C0764a.m2914a(this.f1846b);
            Context context = this.f1846b;
            tokenResult.umidToken = C0772a.m2936a();
        } catch (Throwable th) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        C0765a.m2920a().m2921a(i);
        String a = C0780h.m2975a(this.f1846b);
        String c = C0765a.m2920a().m2923c();
        if (C0789a.m3023b(a) && !C0789a.m3021a(a, c)) {
            C0773a.m2940a(this.f1846b);
            C0776d.m2955a(this.f1846b);
            C0779g.m2973a(this.f1846b);
            C0781i.m3000h();
        }
        if (!C0789a.m3021a(a, c)) {
            C0780h.m2976a(this.f1846b, c);
        }
        Object a2 = C0789a.m3019a(map, C0845b.f2066g, "");
        c = C0789a.m3019a(map, C0845b.f2062c, "");
        String a3 = C0789a.m3019a(map, "userId", "");
        if (C0789a.m3020a((String) a2)) {
            Context context = this.f1846b;
            a2 = "";
        }
        final Map hashMap = new HashMap();
        hashMap.put(C0845b.f2066g, a2);
        hashMap.put(C0845b.f2062c, c);
        hashMap.put("userId", a3);
        hashMap.put("appName", "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "3");
        C0784b.m3006a().m3009a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ APSecuritySdk f1842c;

            public void run() {
                new C0764a(this.f1842c.f1846b).m2919a(hashMap);
                if (initResultListener != null) {
                    initResultListener.onResult(this.f1842c.getTokenResult());
                }
            }
        });
    }
}
