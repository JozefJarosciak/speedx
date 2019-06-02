package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult> {
    private final zzh<TResult> aDN = new zzh();

    @NonNull
    public Task<TResult> getTask() {
        return this.aDN;
    }

    public void setException(@NonNull Exception exception) {
        this.aDN.setException(exception);
    }

    public void setResult(TResult tResult) {
        this.aDN.setResult(tResult);
    }
}
