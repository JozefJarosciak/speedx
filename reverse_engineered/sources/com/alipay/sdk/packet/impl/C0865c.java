package com.alipay.sdk.packet.impl;

import android.content.Context;
import com.alipay.sdk.packet.C0859b;
import com.alipay.sdk.packet.C0861d;
import com.umeng.onlineconfig.C4766a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.packet.impl.c */
public final class C0865c extends C0861d {
    /* renamed from: t */
    public static final String f2151t = "log_v";

    /* renamed from: a */
    protected final List<Header> mo2430a(boolean z, String str) {
        List<Header> arrayList = new ArrayList();
        arrayList.add(new BasicHeader(C0861d.f2129a, String.valueOf(z)));
        arrayList.add(new BasicHeader(C0861d.f2132d, "application/octet-stream"));
        arrayList.add(new BasicHeader(C0861d.f2135g, "CBC"));
        return arrayList;
    }

    /* renamed from: c */
    protected final String mo2431c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put(C0861d.f2137i, "/sdk/log");
        hashMap.put(C0861d.f2138j, C4766a.f16699b);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(f2151t, "1.0");
        return C0861d.m3339a(hashMap, hashMap2);
    }

    /* renamed from: a */
    protected final JSONObject mo2426a() throws JSONException {
        return null;
    }

    /* renamed from: a */
    protected final String mo2429a(String str, JSONObject jSONObject) {
        return str;
    }

    /* renamed from: a */
    public final C0859b mo2428a(Context context, String str) throws Throwable {
        return m3347a(context, str, "http://mcgw.alipay.com/sdklog.do", true);
    }
}
