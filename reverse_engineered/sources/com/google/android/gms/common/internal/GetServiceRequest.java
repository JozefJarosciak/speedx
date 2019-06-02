package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzq.zza;
import com.google.android.gms.common.zzc;
import java.util.Collection;

public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Creator<GetServiceRequest> CREATOR = new zzj();
    final int version;
    final int yi;
    int yj;
    String yk;
    IBinder yl;
    Scope[] ym;
    Bundle yn;
    Account yo;
    long yp;

    public GetServiceRequest(int i) {
        this.version = 3;
        this.yj = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.yi = i;
    }

    GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, long j) {
        this.version = i;
        this.yi = i2;
        this.yj = i3;
        this.yk = str;
        if (i < 2) {
            this.yo = zzdo(iBinder);
        } else {
            this.yl = iBinder;
            this.yo = account;
        }
        this.ym = scopeArr;
        this.yn = bundle;
        this.yp = j;
    }

    private Account zzdo(IBinder iBinder) {
        return iBinder != null ? zza.zza(zza.zzdp(iBinder)) : null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }

    public GetServiceRequest zzb(zzq zzq) {
        if (zzq != null) {
            this.yl = zzq.asBinder();
        }
        return this;
    }

    public GetServiceRequest zzd(Account account) {
        this.yo = account;
        return this;
    }

    public GetServiceRequest zzf(Collection<Scope> collection) {
        this.ym = (Scope[]) collection.toArray(new Scope[collection.size()]);
        return this;
    }

    public GetServiceRequest zzhm(String str) {
        this.yk = str;
        return this;
    }

    public GetServiceRequest zzn(Bundle bundle) {
        this.yn = bundle;
        return this;
    }
}
