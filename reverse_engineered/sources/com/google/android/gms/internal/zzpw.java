package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.zzc;
import java.util.Iterator;

public class zzpw extends zzps {
    public void onStop() {
        Object obj = null;
        super.onStop();
        Iterator it = obj.iterator();
        while (it.hasNext()) {
            ((zzc) it.next()).release();
        }
        obj.clear();
        obj.zza(this);
    }

    protected void zza(ConnectionResult connectionResult, int i) {
        zzqh zzqh = null;
        zzqh.zza(connectionResult, i);
    }

    protected void zzaol() {
        zzqh zzqh = null;
        zzqh.zzaol();
    }
}
