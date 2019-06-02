package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzab;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzpr$zza<R extends Result, A extends zzb> extends zzpt<R> implements zzpr.zzb<R> {
    private final Api<?> pD;
    private AtomicReference<zzrd.zzb> sA = new AtomicReference();
    private final zzc<A> sz;

    @Deprecated
    protected zzpr$zza(zzc<A> zzc, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzab.zzb(googleApiClient, "GoogleApiClient must not be null"));
        this.sz = (zzc) zzab.zzaa(zzc);
        this.pD = null;
    }

    protected zzpr$zza(Api<?> api, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzab.zzb(googleApiClient, "GoogleApiClient must not be null"));
        this.sz = api.zzanp();
        this.pD = api;
    }

    private void zza(RemoteException remoteException) {
        zzz(new Status(8, remoteException.getLocalizedMessage(), null));
    }

    public /* synthetic */ void setResult(Object obj) {
        super.zzc((Result) obj);
    }

    protected abstract void zza(A a) throws RemoteException;

    public void zza(zzrd.zzb zzb) {
        this.sA.set(zzb);
    }

    public final zzc<A> zzanp() {
        return this.sz;
    }

    public final Api<?> zzanw() {
        return this.pD;
    }

    public void zzaoo() {
        setResultCallback(null);
    }

    protected void zzaop() {
        zzrd.zzb zzb = (zzrd.zzb) this.sA.getAndSet(null);
        if (zzb != null) {
            zzb.zzh(this);
        }
    }

    public final void zzb(A a) throws DeadObjectException {
        try {
            zza((zzb) a);
        } catch (RemoteException e) {
            zza(e);
            throw e;
        } catch (RemoteException e2) {
            zza(e2);
        }
    }

    protected void zzb(R r) {
    }

    public final void zzz(Status status) {
        zzab.zzb(!status.isSuccess(), "Failed result must not be success");
        Result zzc = zzc(status);
        zzc(zzc);
        zzb(zzc);
    }
}
