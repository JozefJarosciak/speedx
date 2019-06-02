package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzab;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final Status cc;
    private final PendingResult<?>[] rA;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.cc = status;
        this.rA = pendingResultArr;
    }

    public Status getStatus() {
        return this.cc;
    }

    public <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        zzab.zzb(batchResultToken.mId < this.rA.length, (Object) "The result token does not belong to this batch");
        return this.rA[batchResultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
