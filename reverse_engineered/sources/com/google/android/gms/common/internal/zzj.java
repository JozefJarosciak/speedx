package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj implements Creator<GetServiceRequest> {
    static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.yi);
        zzb.zzc(parcel, 3, getServiceRequest.yj);
        zzb.zza(parcel, 4, getServiceRequest.yk, false);
        zzb.zza(parcel, 5, getServiceRequest.yl, false);
        zzb.zza(parcel, 6, getServiceRequest.ym, i, false);
        zzb.zza(parcel, 7, getServiceRequest.yn, false);
        zzb.zza(parcel, 8, getServiceRequest.yo, i, false);
        zzb.zza(parcel, 9, getServiceRequest.yp);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcf(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzga(i);
    }

    public GetServiceRequest zzcf(Parcel parcel) {
        int i = 0;
        Account account = null;
        int zzcl = zza.zzcl(parcel);
        long j = 0;
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 5:
                    iBinder = zza.zzr(parcel, zzck);
                    break;
                case 6:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzck, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zza.zzs(parcel, zzck);
                    break;
                case 8:
                    account = (Account) zza.zza(parcel, zzck, Account.CREATOR);
                    break;
                case 9:
                    j = zza.zzi(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new GetServiceRequest(i3, i2, i, str, iBinder, scopeArr, bundle, account, j);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public GetServiceRequest[] zzga(int i) {
        return new GetServiceRequest[i];
    }
}
