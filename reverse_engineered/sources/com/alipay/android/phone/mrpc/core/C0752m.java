package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* renamed from: com.alipay.android.phone.mrpc.core.m */
final class C0752m extends FutureTask<C0756u> {
    /* renamed from: a */
    final /* synthetic */ C0758q f1764a;
    /* renamed from: b */
    final /* synthetic */ C0751l f1765b;

    C0752m(C0751l c0751l, Callable callable, C0758q c0758q) {
        this.f1765b = c0751l;
        this.f1764a = c0758q;
        super(callable);
    }

    protected final void done() {
        C0754t a = this.f1764a.m2908a();
        if (a.m2874f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a.m2876h()) {
                a.m2875g();
                if (!isCancelled() || !isDone()) {
                    cancel(false);
                }
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (CancellationException e3) {
            a.m2875g();
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException("An error occured while executing http request", th);
        }
    }
}
