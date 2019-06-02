package com.tencent.mm.sdk.p198b;

import android.os.Debug;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.alipay.sdk.util.C0880h;
import com.tencent.mm.sdk.p198b.C4496g.C4493a;
import junit.framework.Assert;

/* renamed from: com.tencent.mm.sdk.b.e */
final class C4494e extends Handler implements C4493a {
    private Looper aN = getLooper();
    private Callback aO;
    C4491a aP;

    /* renamed from: com.tencent.mm.sdk.b.e$a */
    public interface C4491a {
        /* renamed from: a */
        void mo6083a(Runnable runnable, C4496g c4496g);

        /* renamed from: b */
        void mo6084b(Runnable runnable, C4496g c4496g);
    }

    C4494e(Looper looper, C4491a c4491a) {
        super(looper);
        this.aP = c4491a;
    }

    C4494e(C4491a c4491a) {
        this.aP = c4491a;
    }

    /* renamed from: c */
    public final void mo6085c(Runnable runnable, C4496g c4496g) {
        if (this.aP != null) {
            this.aP.mo6084b(runnable, c4496g);
        }
    }

    public final void dispatchMessage(Message message) {
        if (message.getCallback() == null && this.aO == null) {
            System.currentTimeMillis();
            Debug.threadCpuTimeNanos();
            handleMessage(message);
            if (this.aP != null) {
                this.aN.getThread();
                System.currentTimeMillis();
                Debug.threadCpuTimeNanos();
                return;
            }
            return;
        }
        super.dispatchMessage(message);
    }

    public final void handleMessage(Message message) {
    }

    public final boolean sendMessageAtTime(Message message, long j) {
        Assert.assertTrue("msg is null", message != null);
        Runnable callback = message.getCallback();
        if (callback == null) {
            return super.sendMessageAtTime(message, j);
        }
        long uptimeMillis = j - SystemClock.uptimeMillis();
        C4496g c4496g = new C4496g(this.aN.getThread(), message.getTarget() == null ? this : message.getTarget(), callback, message.obj, this);
        if (uptimeMillis > 0) {
            c4496g.aY = uptimeMillis;
        }
        Message obtain = Message.obtain(message.getTarget(), c4496g);
        obtain.what = message.what;
        obtain.arg1 = message.arg1;
        obtain.arg2 = message.arg2;
        obtain.obj = message.obj;
        obtain.replyTo = message.replyTo;
        obtain.setData(message.getData());
        message.recycle();
        if (this.aP != null) {
            this.aP.mo6083a(callback, c4496g);
        }
        boolean sendMessageAtTime = super.sendMessageAtTime(obtain, j);
        if (!(sendMessageAtTime || this.aP == null)) {
            this.aP.mo6084b(callback, c4496g);
        }
        return sendMessageAtTime;
    }

    public final String toString() {
        return "MMInnerHandler{listener = " + this.aP + C0880h.f2222d;
    }
}
