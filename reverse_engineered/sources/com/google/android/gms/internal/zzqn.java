package com.google.android.gms.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzs;

public class zzqn {
    private final Object uZ;

    public zzqn(Activity activity) {
        zzab.zzb((Object) activity, (Object) "Activity must not be null");
        boolean z = zzs.zzavj() || (activity instanceof FragmentActivity);
        zzab.zzb(z, (Object) "This Activity is not supported before platform version 11 (3.0 Honeycomb). Please use FragmentActivity instead.");
        this.uZ = activity;
    }

    public boolean zzaqm() {
        return this.uZ instanceof FragmentActivity;
    }

    public Activity zzaqn() {
        return (Activity) this.uZ;
    }

    public FragmentActivity zzaqo() {
        return (FragmentActivity) this.uZ;
    }
}
