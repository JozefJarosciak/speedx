package com.avos.avoscloud;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

abstract class IntervalTimer {
    private static final int MSG = 1;
    private static final int SKIP = -1;
    Looper looper;
    private boolean mCancelled;
    private final long mCountdownInterval;
    private Handler mHandler;
    private long mTriggerTimeInFuture;

    public abstract void onTrigger();

    public IntervalTimer(long j) {
        this(null, j);
    }

    public IntervalTimer(Looper looper, long j) {
        this.mCancelled = false;
        this.mCountdownInterval = j;
        this.looper = looper;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.mHandler = new Handler(looper) {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleMessage(android.os.Message r9) {
                /*
                r8 = this;
                r1 = com.avos.avoscloud.IntervalTimer.this;
                monitor-enter(r1);
                r0 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r0 = r0.mCancelled;	 Catch:{ all -> 0x0014 }
                if (r0 == 0) goto L_0x000d;
            L_0x000b:
                monitor-exit(r1);	 Catch:{ all -> 0x0014 }
            L_0x000c:
                return;
            L_0x000d:
                r0 = r9.what;	 Catch:{ all -> 0x0014 }
                switch(r0) {
                    case -1: goto L_0x0063;
                    case 0: goto L_0x0012;
                    case 1: goto L_0x0017;
                    default: goto L_0x0012;
                };	 Catch:{ all -> 0x0014 }
            L_0x0012:
                monitor-exit(r1);	 Catch:{ all -> 0x0014 }
                goto L_0x000c;
            L_0x0014:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x0014 }
                throw r0;
            L_0x0017:
                r0 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r2 = r0.mTriggerTimeInFuture;	 Catch:{ all -> 0x0014 }
                r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0014 }
                r2 = r2 - r4;
                r4 = 0;
                r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                if (r0 > 0) goto L_0x0050;
            L_0x0028:
                r0 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r0.onTrigger();	 Catch:{ all -> 0x0014 }
                r0 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r4 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r4 = r4.mTriggerTimeInFuture;	 Catch:{ all -> 0x0014 }
                r6 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r6 = r6.mCountdownInterval;	 Catch:{ all -> 0x0014 }
                r4 = r4 + r6;
                r2 = r4 - r2;
                r0.mTriggerTimeInFuture = r2;	 Catch:{ all -> 0x0014 }
                r0 = 1;
                r0 = r8.obtainMessage(r0);	 Catch:{ all -> 0x0014 }
                r2 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r2 = r2.mCountdownInterval;	 Catch:{ all -> 0x0014 }
                r8.sendMessageDelayed(r0, r2);	 Catch:{ all -> 0x0014 }
                goto L_0x0012;
            L_0x0050:
                r0 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r4 = r0.mCountdownInterval;	 Catch:{ all -> 0x0014 }
                r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                if (r0 > 0) goto L_0x0012;
            L_0x005a:
                r0 = 1;
                r0 = r8.obtainMessage(r0);	 Catch:{ all -> 0x0014 }
                r8.sendMessageDelayed(r0, r2);	 Catch:{ all -> 0x0014 }
                goto L_0x0012;
            L_0x0063:
                r0 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x0014 }
                r4 = com.avos.avoscloud.IntervalTimer.this;	 Catch:{ all -> 0x0014 }
                r4 = r4.mCountdownInterval;	 Catch:{ all -> 0x0014 }
                r2 = r2 + r4;
                r0.mTriggerTimeInFuture = r2;	 Catch:{ all -> 0x0014 }
                goto L_0x0012;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.avos.avoscloud.IntervalTimer.1.handleMessage(android.os.Message):void");
            }
        };
    }

    public void cancel() {
        this.mCancelled = true;
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(-1);
    }

    public final void skip() {
        this.mHandler.sendEmptyMessage(-1);
    }

    protected final synchronized IntervalTimer start() {
        this.mCancelled = false;
        this.mTriggerTimeInFuture = SystemClock.elapsedRealtime() + this.mCountdownInterval;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
        return this;
    }
}
