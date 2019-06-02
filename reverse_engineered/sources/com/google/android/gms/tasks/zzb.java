package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult> {
    private final Continuation<TResult, Task<TContinuationResult>> aDA;
    private final zzh<TContinuationResult> aDB;
    private final Executor avP;

    public zzb(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzh<TContinuationResult> zzh) {
        this.avP = executor;
        this.aDA = continuation;
        this.aDB = zzh;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.avP.execute(new Runnable(this) {
            final /* synthetic */ zzb aDE;

            public void run() {
                try {
                    Task task = (Task) this.aDE.aDA.then(task);
                    if (task == null) {
                        this.aDE.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.aDO, this.aDE);
                    task.addOnFailureListener(TaskExecutors.aDO, this.aDE);
                } catch (Exception e) {
                    if (e.getCause() instanceof Exception) {
                        this.aDE.aDB.setException((Exception) e.getCause());
                    } else {
                        this.aDE.aDB.setException(e);
                    }
                } catch (Exception e2) {
                    this.aDE.aDB.setException(e2);
                }
            }
        });
    }

    public void onFailure(@NonNull Exception exception) {
        this.aDB.setException(exception);
    }

    public void onSuccess(TContinuationResult tContinuationResult) {
        this.aDB.setResult(tContinuationResult);
    }
}
