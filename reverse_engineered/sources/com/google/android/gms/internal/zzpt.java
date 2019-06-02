package com.google.android.gms.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class zzpt<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> sI = new zzpt$1();
    private final Object sJ;
    protected final zzpt$zza<R> sK;
    protected final WeakReference<GoogleApiClient> sL;
    private final ArrayList<zza> sM;
    private ResultCallback<? super R> sN;
    private zzpt$zzb sO;
    private volatile boolean sP;
    private boolean sQ;
    private zzr sR;
    private volatile zzrc<R> sS;
    private boolean sT;
    private R sc;
    private boolean zzak;
    private final CountDownLatch zzalc;

    @Deprecated
    zzpt() {
        this.sJ = new Object();
        this.zzalc = new CountDownLatch(1);
        this.sM = new ArrayList();
        this.sT = false;
        this.sK = new zzpt$zza(Looper.getMainLooper());
        this.sL = new WeakReference(null);
    }

    @Deprecated
    protected zzpt(Looper looper) {
        this.sJ = new Object();
        this.zzalc = new CountDownLatch(1);
        this.sM = new ArrayList();
        this.sT = false;
        this.sK = new zzpt$zza(looper);
        this.sL = new WeakReference(null);
    }

    protected zzpt(GoogleApiClient googleApiClient) {
        this.sJ = new Object();
        this.zzalc = new CountDownLatch(1);
        this.sM = new ArrayList();
        this.sT = false;
        this.sK = new zzpt$zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.sL = new WeakReference(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.sJ) {
            if (this.sP) {
                z = false;
            }
            zzab.zza(z, "Result has already been consumed.");
            zzab.zza(isReady(), "Result is not ready.");
            r = this.sc;
            this.sc = null;
            this.sN = null;
            this.sP = true;
        }
        zzaop();
        return r;
    }

    private void zzd(R r) {
        this.sc = r;
        this.sR = null;
        this.zzalc.countDown();
        Status status = this.sc.getStatus();
        if (this.zzak) {
            this.sN = null;
        } else if (this.sN != null) {
            this.sK.zzaow();
            this.sK.zza(this.sN, get());
        } else if (this.sc instanceof Releasable) {
            this.sO = new zzpt$zzb(this, null);
        }
        Iterator it = this.sM.iterator();
        while (it.hasNext()) {
            ((zza) it.next()).zzv(status);
        }
        this.sM.clear();
    }

    public static void zze(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzab.zza(Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread");
        zzab.zza(!this.sP, "Result has already been consumed");
        if (this.sS != null) {
            z = false;
        }
        zzab.zza(z, "Cannot await if then() has been called.");
        try {
            this.zzalc.await();
        } catch (InterruptedException e) {
            zzaa(Status.sh);
        }
        zzab.zza(isReady(), "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        boolean z2 = j <= 0 || Looper.myLooper() != Looper.getMainLooper();
        zzab.zza(z2, "await must not be called on the UI thread when time is greater than zero.");
        zzab.zza(!this.sP, "Result has already been consumed.");
        if (this.sS != null) {
            z = false;
        }
        zzab.zza(z, "Cannot await if then() has been called.");
        try {
            if (!this.zzalc.await(j, timeUnit)) {
                zzaa(Status.sj);
            }
        } catch (InterruptedException e) {
            zzaa(Status.sh);
        }
        zzab.zza(isReady(), "Result is not ready.");
        return get();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
        r2 = this;
        r1 = r2.sJ;
        monitor-enter(r1);
        r0 = r2.zzak;	 Catch:{ all -> 0x0029 }
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.sP;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.sR;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r2.sR;	 Catch:{ RemoteException -> 0x002c }
        r0.cancel();	 Catch:{ RemoteException -> 0x002c }
    L_0x0016:
        r0 = r2.sc;	 Catch:{ all -> 0x0029 }
        zze(r0);	 Catch:{ all -> 0x0029 }
        r0 = 1;
        r2.zzak = r0;	 Catch:{ all -> 0x0029 }
        r0 = com.google.android.gms.common.api.Status.sk;	 Catch:{ all -> 0x0029 }
        r0 = r2.zzc(r0);	 Catch:{ all -> 0x0029 }
        r2.zzd(r0);	 Catch:{ all -> 0x0029 }
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        goto L_0x000c;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        throw r0;
    L_0x002c:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpt.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.sJ) {
            z = this.zzak;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzalc.getCount() == 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
        r5 = this;
        r0 = 1;
        r1 = 0;
        r3 = r5.sJ;
        monitor-enter(r3);
        if (r6 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r5.sN = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r5.sP;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzab.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r5.sS;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzab.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r5.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r5.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r5.sK;	 Catch:{ all -> 0x0027 }
        r1 = r5.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r6, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r5.sN = r6;	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpt.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r3 = r6.sJ;
        monitor-enter(r3);
        if (r7 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r6.sN = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r6.sP;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzab.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r6.sS;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzab.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r6.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r6.sK;	 Catch:{ all -> 0x0027 }
        r1 = r6.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r7, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r6.sN = r7;	 Catch:{ all -> 0x0027 }
        r0 = r6.sK;	 Catch:{ all -> 0x0027 }
        r4 = r10.toMillis(r8);	 Catch:{ all -> 0x0027 }
        r0.zza(r6, r4);	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpt.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzab.zza(!this.sP, "Result has already been consumed.");
        synchronized (this.sJ) {
            zzab.zza(this.sS == null, "Cannot call then() twice.");
            if (this.sN != null) {
                z = false;
            }
            zzab.zza(z, "Cannot call then() if callbacks are set.");
            this.sT = true;
            this.sS = new zzrc(this.sL);
            then = this.sS.then(resultTransform);
            if (isReady()) {
                this.sK.zza(this.sS, get());
            } else {
                this.sN = this.sS;
            }
        }
        return then;
    }

    public final void zza(zza zza) {
        boolean z = true;
        zzab.zza(!this.sP, "Result has already been consumed.");
        if (zza == null) {
            z = false;
        }
        zzab.zzb(z, "Callback cannot be null.");
        synchronized (this.sJ) {
            if (isReady()) {
                zza.zzv(this.sc.getStatus());
            } else {
                this.sM.add(zza);
            }
        }
    }

    protected final void zza(zzr zzr) {
        synchronized (this.sJ) {
            this.sR = zzr;
        }
    }

    public final void zzaa(Status status) {
        synchronized (this.sJ) {
            if (!isReady()) {
                zzc(zzc(status));
                this.sQ = true;
            }
        }
    }

    public Integer zzaog() {
        return null;
    }

    protected void zzaop() {
    }

    public boolean zzaos() {
        boolean isCanceled;
        synchronized (this.sJ) {
            if (((GoogleApiClient) this.sL.get()) == null || !this.sT) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzaot() {
        boolean z = this.sT || ((Boolean) sI.get()).booleanValue();
        this.sT = z;
    }

    boolean zzaou() {
        return false;
    }

    protected abstract R zzc(Status status);

    public final void zzc(R r) {
        boolean z = true;
        synchronized (this.sJ) {
            if (this.sQ || this.zzak || (isReady() && zzaou())) {
                zze(r);
                return;
            }
            zzab.zza(!isReady(), "Results have already been set");
            if (this.sP) {
                z = false;
            }
            zzab.zza(z, "Result has already been consumed");
            zzd(r);
        }
    }
}
