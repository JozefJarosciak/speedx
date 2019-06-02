package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzqu extends zzps {
    private TaskCompletionSource<Void> sq = new TaskCompletionSource();

    private zzqu(zzqp zzqp) {
        super(zzqp);
        this.va.zza("GmsAvailabilityHelper", (zzqo) this);
    }

    public static zzqu zzu(Activity activity) {
        zzqp zzs = zzqo.zzs(activity);
        zzqu zzqu = (zzqu) zzs.zza("GmsAvailabilityHelper", zzqu.class);
        if (zzqu == null) {
            return new zzqu(zzs);
        }
        if (!zzqu.sq.getTask().isComplete()) {
            return zzqu;
        }
        zzqu.sq = new TaskCompletionSource();
        return zzqu;
    }

    public Task<Void> getTask() {
        return this.sq.getTask();
    }

    public void onStop() {
        super.onStop();
        this.sq.setException(new CancellationException());
    }

    protected void zza(ConnectionResult connectionResult, int i) {
        this.sq.setException(new Exception());
    }

    protected void zzaol() {
        int isGooglePlayServicesAvailable = this.rX.isGooglePlayServicesAvailable(this.va.zzaqp());
        if (isGooglePlayServicesAvailable == 0) {
            this.sq.setResult(null);
        } else {
            zzk(new ConnectionResult(isGooglePlayServicesAvailable, null));
        }
    }

    public void zzk(ConnectionResult connectionResult) {
        zzb(connectionResult, 0);
    }
}
