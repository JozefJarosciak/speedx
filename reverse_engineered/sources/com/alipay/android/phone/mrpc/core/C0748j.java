package com.alipay.android.phone.mrpc.core;

import com.loopj.android.http.AsyncHttpClient;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/* renamed from: com.alipay.android.phone.mrpc.core.j */
public final class C0748j extends C0736a {
    /* renamed from: g */
    private C0744g f1749g;

    public C0748j(C0744g c0744g, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.f1749g = c0744g;
    }

    /* renamed from: a */
    public final Object mo2417a() {
        Throwable e;
        C0754t c0755o = new C0755o(this.f1749g.mo2413a());
        c0755o.m2882a(this.b);
        c0755o.m2878a(this.e);
        c0755o.m2881a(this.f);
        c0755o.m2879a("id", String.valueOf(this.d));
        c0755o.m2879a("operationType", this.c);
        c0755o.m2879a(AsyncHttpClient.ENCODING_GZIP, String.valueOf(this.f1749g.mo2416d()));
        c0755o.m2880a(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> b = this.f1749g.mo2415c().m2834b();
        if (!(b == null || b.isEmpty())) {
            for (Header a : b) {
                c0755o.m2880a(a);
            }
        }
        new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; ").append(c0755o.toString());
        try {
            C0756u c0756u = (C0756u) this.f1749g.mo2414b().mo2418a(c0755o).get();
            if (c0756u != null) {
                return c0756u.m2889b();
            }
            throw new RpcException(Integer.valueOf(9), "response is null");
        } catch (Throwable e2) {
            throw new RpcException(Integer.valueOf(13), "", e2);
        } catch (Throwable e22) {
            Throwable th = e22;
            e22 = th.getCause();
            if (e22 == null || !(e22 instanceof HttpException)) {
                throw new RpcException(Integer.valueOf(9), "", th);
            }
            HttpException httpException = (HttpException) e22;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        } catch (Throwable e222) {
            throw new RpcException(Integer.valueOf(13), "", e222);
        }
    }
}
