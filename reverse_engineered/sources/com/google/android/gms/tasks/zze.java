package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private OnSuccessListener<? super TResult> aDJ;
    private final Executor avP;
    private final Object zzail = new Object();

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.avP = executor;
        this.aDJ = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.aDJ = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzail) {
                if (this.aDJ == null) {
                    return;
                }
                this.avP.execute(new Runnable(this) {
                    final /* synthetic */ zze aDK;

                    public void run() {
                        synchronized (this.aDK.zzail) {
                            if (this.aDK.aDJ != null) {
                                this.aDK.aDJ.onSuccess(task.getResult());
                            }
                        }
                    }
                });
            }
        }
    }
}
