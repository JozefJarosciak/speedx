package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult> {
    private OnCompleteListener<TResult> aDF;
    private final Executor avP;
    private final Object zzail = new Object();

    public zzc(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.avP = executor;
        this.aDF = onCompleteListener;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.aDF = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.zzail) {
            if (this.aDF == null) {
                return;
            }
            this.avP.execute(new Runnable(this) {
                final /* synthetic */ zzc aDG;

                public void run() {
                    synchronized (this.aDG.zzail) {
                        if (this.aDG.aDF != null) {
                            this.aDG.aDF.onComplete(task);
                        }
                    }
                }
            });
        }
    }
}
