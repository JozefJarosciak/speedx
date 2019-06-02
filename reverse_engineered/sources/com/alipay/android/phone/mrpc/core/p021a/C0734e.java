package com.alipay.android.phone.mrpc.core.p021a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.p018a.p019a.C0721f;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.alipay.android.phone.mrpc.core.a.e */
public final class C0734e extends C0732b {
    /* renamed from: c */
    private int f1721c;
    /* renamed from: d */
    private Object f1722d;

    public C0734e(int i, String str, Object obj) {
        super(str, obj);
        this.f1721c = i;
    }

    /* renamed from: a */
    public final void mo2410a(Object obj) {
        this.f1722d = obj;
    }

    /* renamed from: a */
    public final byte[] mo2411a() {
        try {
            List arrayList = new ArrayList();
            if (this.f1722d != null) {
                arrayList.add(new BasicNameValuePair("extParam", C0721f.m2806a(this.f1722d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.a));
            arrayList.add(new BasicNameValuePair("id", this.f1721c));
            new StringBuilder("mParams is:").append(this.b);
            arrayList.add(new BasicNameValuePair("requestData", this.b == null ? "[]" : C0721f.m2806a(this.b)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Throwable e) {
            Throwable th = e;
            throw new RpcException(Integer.valueOf(9), new StringBuilder("request  =").append(this.b).append(":").append(th).toString() == null ? "" : th.getMessage(), th);
        }
    }
}
