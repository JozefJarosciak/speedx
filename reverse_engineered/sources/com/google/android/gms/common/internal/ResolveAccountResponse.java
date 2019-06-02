package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzq.zza;

public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Creator<ResolveAccountResponse> CREATOR = new zzad();
    final int mVersionCode;
    private ConnectionResult rv;
    private boolean tB;
    IBinder wY;
    private boolean yX;

    ResolveAccountResponse(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.wY = iBinder;
        this.rv = connectionResult;
        this.tB = z;
        this.yX = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.rv.equals(resolveAccountResponse.rv) && zzatc().equals(resolveAccountResponse.zzatc());
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzad.zza(this, parcel, i);
    }

    public zzq zzatc() {
        return zza.zzdp(this.wY);
    }

    public ConnectionResult zzatd() {
        return this.rv;
    }

    public boolean zzate() {
        return this.tB;
    }

    public boolean zzatf() {
        return this.yX;
    }
}
