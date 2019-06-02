package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult> implements zzf<TResult> {
    private OnFailureListener aDH;
    private final Executor avP;
    private final Object zzail = new Object();

    public zzd(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.avP = executor;
        this.aDH = onFailureListener;
    }

    public void cancel() {
        synchronized (this.zzail) {
            this.aDH = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzail) {
                if (this.aDH == null) {
                    return;
                }
                this.avP.execute(new Runnable(this) {
                    final /* synthetic */ zzd aDI;

                    public void run() {
                        synchronized (this.aDI.zzail) {
                            if (this.aDI.aDH != null) {
                                this.aDI.aDH.onFailure(task.getException());
                            }
                        }
                    }
                });
            }
        }
    }
}
