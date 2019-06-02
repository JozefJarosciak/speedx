package com.baidu.vi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class VMsg {
    /* renamed from: a */
    private static final String f3982a = VMsg.class.getSimpleName();
    /* renamed from: b */
    private static Handler f3983b;
    /* renamed from: c */
    private static HandlerThread f3984c;

    /* renamed from: com.baidu.vi.VMsg$a */
    static class C1365a extends Handler {
        public C1365a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            VMsg.OnUserCommand1(message.what, message.arg1, message.arg2, message.obj == null ? 0 : ((Long) message.obj).longValue());
        }
    }

    private static native void OnUserCommand1(int i, int i2, int i3, long j);

    public static void destroy() {
        f3984c.quit();
        f3984c = null;
        f3983b.removeCallbacksAndMessages(null);
        f3983b = null;
    }

    public static void init() {
        f3984c = new HandlerThread("VIMsgThread");
        f3984c.start();
        f3983b = new C1365a(f3984c.getLooper());
    }

    private static void postMessage(int i, int i2, int i3, long j) {
        if (f3983b != null) {
            Message.obtain(f3983b, i, i2, i3, Long.valueOf(j)).sendToTarget();
        }
    }
}
