package com.alipay.android.phone.mrpc.core.p021a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.p018a.p019a.C0720e;
import com.alipay.sdk.util.C0882j;
import java.lang.reflect.Type;
import org.json.JSONObject;

/* renamed from: com.alipay.android.phone.mrpc.core.a.d */
public final class C0733d extends C0730a {
    public C0733d(Type type, byte[] bArr) {
        super(type, bArr);
    }

    /* renamed from: a */
    public final Object mo2409a() {
        try {
            String str = new String(this.b);
            new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; rpc response:  ").append(str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(C0882j.f2227a);
            if (i == 1000) {
                return this.a == String.class ? jSONObject.optString(C0882j.f2229c) : C0720e.m2805a(jSONObject.optString(C0882j.f2229c), this.a);
            } else {
                throw new RpcException(Integer.valueOf(i), jSONObject.optString("tips"));
            }
        } catch (Exception e) {
            throw new RpcException(Integer.valueOf(10), new StringBuilder("response  =").append(new String(this.b)).append(":").append(e).toString() == null ? "" : e.getMessage());
        }
    }
}
