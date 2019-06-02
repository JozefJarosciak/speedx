package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.android.phone.mrpc.core.p021a.C0731f;
import com.alipay.android.phone.mrpc.core.p021a.C0733d;
import com.alipay.android.phone.mrpc.core.p021a.C0734e;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.alipay.android.phone.mrpc.core.z */
public final class C0763z {
    /* renamed from: a */
    private static final ThreadLocal<Object> f1806a = new ThreadLocal();
    /* renamed from: b */
    private static final ThreadLocal<Map<String, Object>> f1807b = new ThreadLocal();
    /* renamed from: c */
    private byte f1808c = (byte) 0;
    /* renamed from: d */
    private AtomicInteger f1809d;
    /* renamed from: e */
    private C0761x f1810e;

    public C0763z(C0761x c0761x) {
        this.f1810e = c0761x;
        this.f1809d = new AtomicInteger();
    }

    /* renamed from: a */
    public final Object m2913a(Method method, Object[] objArr) {
        boolean z = true;
        boolean z2 = Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper();
        if (z2) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        if (method.getAnnotation(ResetCookie.class) == null) {
            z = false;
        }
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        f1806a.set(null);
        f1807b.set(null);
        if (operationType == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String value = operationType.value();
        int incrementAndGet = this.f1809d.incrementAndGet();
        try {
            if (this.f1808c == (byte) 0) {
                C0731f c0734e = new C0734e(incrementAndGet, value, objArr);
                if (f1807b.get() != null) {
                    c0734e.mo2410a(f1807b.get());
                }
                byte[] bArr = (byte[]) new C0748j(this.f1810e.m2911a(), method, incrementAndGet, value, c0734e.mo2411a(), z).mo2417a();
                f1807b.set(null);
                Object a = new C0733d(genericReturnType, bArr).mo2409a();
                if (genericReturnType != Void.TYPE) {
                    f1806a.set(a);
                }
            }
            return f1806a.get();
        } catch (RpcException e) {
            e.setOperationType(value);
            throw e;
        }
    }
}
