package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.internal.zzpt;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzpt<BatchResult> {
    private final PendingResult<?>[] rA;
    private int rx;
    private boolean ry;
    private boolean rz;
    private final Object zzail;

    /* renamed from: com.google.android.gms.common.api.Batch$1 */
    class C32921 implements zza {
        final /* synthetic */ Batch rB;

        C32921(Batch batch) {
            this.rB = batch;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void zzv(com.google.android.gms.common.api.Status r6) {
            /*
            r5 = this;
            r0 = r5.rB;
            r1 = r0.zzail;
            monitor-enter(r1);
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            r0 = r0.isCanceled();	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0011;
        L_0x000f:
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        L_0x0010:
            return;
        L_0x0011:
            r0 = r6.isCanceled();	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x003c;
        L_0x0017:
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            r2 = 1;
            r0.rz = r2;	 Catch:{ all -> 0x0039 }
        L_0x001d:
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            r0.rx = r0.rx - 1;	 Catch:{ all -> 0x0039 }
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            r0 = r0.rx;	 Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x0037;
        L_0x002a:
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            r0 = r0.rz;	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0049;
        L_0x0032:
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            super.cancel();	 Catch:{ all -> 0x0039 }
        L_0x0037:
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
            goto L_0x0010;
        L_0x0039:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
            throw r0;
        L_0x003c:
            r0 = r6.isSuccess();	 Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x001d;
        L_0x0042:
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            r2 = 1;
            r0.ry = r2;	 Catch:{ all -> 0x0039 }
            goto L_0x001d;
        L_0x0049:
            r0 = r5.rB;	 Catch:{ all -> 0x0039 }
            r0 = r0.ry;	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0069;
        L_0x0051:
            r0 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x0039 }
            r2 = 13;
            r0.<init>(r2);	 Catch:{ all -> 0x0039 }
        L_0x0058:
            r2 = r5.rB;	 Catch:{ all -> 0x0039 }
            r3 = new com.google.android.gms.common.api.BatchResult;	 Catch:{ all -> 0x0039 }
            r4 = r5.rB;	 Catch:{ all -> 0x0039 }
            r4 = r4.rA;	 Catch:{ all -> 0x0039 }
            r3.<init>(r0, r4);	 Catch:{ all -> 0x0039 }
            r2.zzc(r3);	 Catch:{ all -> 0x0039 }
            goto L_0x0037;
        L_0x0069:
            r0 = com.google.android.gms.common.api.Status.sg;	 Catch:{ all -> 0x0039 }
            goto L_0x0058;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Batch.1.zzv(com.google.android.gms.common.api.Status):void");
        }
    }

    public static final class Builder {
        private GoogleApiClient hb;
        private List<PendingResult<?>> rC = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.hb = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken(this.rC.size());
            this.rC.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.rC, this.hb);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzail = new Object();
        this.rx = list.size();
        this.rA = new PendingResult[this.rx];
        if (list.isEmpty()) {
            zzc(new BatchResult(Status.sg, this.rA));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult pendingResult = (PendingResult) list.get(i);
            this.rA[i] = pendingResult;
            pendingResult.zza(new C32921(this));
        }
    }

    public void cancel() {
        super.cancel();
        for (PendingResult cancel : this.rA) {
            cancel.cancel();
        }
    }

    public BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.rA);
    }

    public /* synthetic */ Result zzc(Status status) {
        return createFailedResult(status);
    }
}
