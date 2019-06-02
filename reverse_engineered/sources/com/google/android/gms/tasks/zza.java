package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult> {
    private final Continuation<TResult, TContinuationResult> aDA;
    private final zzh<TContinuationResult> aDB;
    private final Executor avP;

    public zza(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzh<TContinuationResult> zzh) {
        this.avP = executor;
        this.aDA = continuation;
        this.aDB = zzh;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.avP.execute(new Runnable(this) {
            final /* synthetic */ zza aDD;

            public void run() {
                try {
                    this.aDD.aDB.setResult(this.aDD.aDA.then(task));
                } catch (Exception e) {
                    if (e.getCause() instanceof Exception) {
                        this.aDD.aDB.setException((Exception) e.getCause());
                    } else {
                        this.aDD.aDB.setException(e);
                    }
                } catch (Exception e2) {
                    this.aDD.aDB.setException(e2);
                }
            }
        });
    }
}
