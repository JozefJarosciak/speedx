package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Pair;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzpr$zza;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqi;
import com.google.android.gms.internal.zzqt;
import com.google.android.gms.internal.zzrb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzc<O extends ApiOptions> {
    private final Context mContext;
    private final int mId;
    private final Api<O> pD;
    private final zzqt rE;
    private final O rF;
    private final zzpo<O> rG;
    private final zzqh rH;
    private final GoogleApiClient rI;
    private final AtomicBoolean rJ;
    private final AtomicInteger rK;
    private final Looper zzahv;

    public zzc(@NonNull Context context, Api<O> api, O o) {
        this(context, api, o, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
    }

    public zzc(@NonNull Context context, Api<O> api, O o, Looper looper) {
        this.rJ = new AtomicBoolean(false);
        this.rK = new AtomicInteger(0);
        zzab.zzb((Object) context, (Object) "Null context is not permitted.");
        zzab.zzb((Object) api, (Object) "Api must not be null.");
        zzab.zzb((Object) looper, (Object) "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.pD = api;
        this.rF = o;
        this.zzahv = looper;
        this.rE = new zzqt();
        this.rG = new zzpo(this.pD, this.rF);
        this.rI = new zzqi(this);
        Pair zza = zzqh.zza(this.mContext, this);
        this.rH = (zzqh) zza.first;
        this.mId = ((Integer) zza.second).intValue();
    }

    private <A extends zzb, T extends zzpr$zza<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzaot();
        this.rH.zza(this, i, t);
        return t;
    }

    private <TResult, A extends zzb> Task<TResult> zza(int i, @NonNull zzrb<A, TResult> zzrb) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.rH.zza(this, i, zzrb, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    public int getInstanceId() {
        return this.mId;
    }

    public Looper getLooper() {
        return this.zzahv;
    }

    public void release() {
        boolean z = true;
        if (!this.rJ.getAndSet(true)) {
            this.rE.release();
            zzqh zzqh = this.rH;
            int i = this.mId;
            if (this.rK.get() <= 0) {
                z = false;
            }
            zzqh.zzd(i, z);
        }
    }

    public <A extends zzb, T extends zzpr$zza<? extends Result, A>> T zza(@NonNull T t) {
        return zza(0, (zzpr$zza) t);
    }

    public <TResult, A extends zzb> Task<TResult> zza(zzrb<A, TResult> zzrb) {
        return zza(0, (zzrb) zzrb);
    }

    public void zzanu() {
        this.rK.incrementAndGet();
    }

    public void zzanv() {
        if (this.rK.decrementAndGet() == 0 && this.rJ.get()) {
            this.rH.zzd(this.mId, false);
        }
    }

    public Api<O> zzanw() {
        return this.pD;
    }

    public O zzanx() {
        return this.rF;
    }

    public zzpo<O> zzany() {
        return this.rG;
    }

    public GoogleApiClient zzanz() {
        return this.rI;
    }

    public <A extends zzb, T extends zzpr$zza<? extends Result, A>> T zzb(@NonNull T t) {
        return zza(1, (zzpr$zza) t);
    }

    public <TResult, A extends zzb> Task<TResult> zzb(zzrb<A, TResult> zzrb) {
        return zza(1, (zzrb) zzrb);
    }
}
