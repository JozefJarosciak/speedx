package com.baidu.platform.util;

import com.alipay.sdk.sys.C0869a;
import com.baidu.platform.comjni.util.AppMD5;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.baidu.platform.util.a */
public class C1364a implements ParamBuilder<C1364a> {
    /* renamed from: a */
    protected Map<String, String> f3979a;

    /* renamed from: a */
    public C1364a m5234a(String str, String str2) {
        if (this.f3979a == null) {
            this.f3979a = new LinkedHashMap();
        }
        this.f3979a.put(str, str2);
        return this;
    }

    /* renamed from: a */
    public String m5235a() {
        if (this.f3979a == null || this.f3979a.isEmpty()) {
            return null;
        }
        String str = new String();
        int i = 0;
        String str2 = str;
        for (String str3 : this.f3979a.keySet()) {
            str = AppMD5.encodeUrlParamsValue((String) this.f3979a.get(str3));
            str = i == 0 ? str2 + str3 + SimpleComparison.EQUAL_TO_OPERATION + str : str2 + C0869a.f2161b + str3 + SimpleComparison.EQUAL_TO_OPERATION + str;
            i++;
            str2 = str;
        }
        return str2;
    }
}
