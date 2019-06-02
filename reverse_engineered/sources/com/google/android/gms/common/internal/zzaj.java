package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzaj implements Creator<ValidateAccountRequest> {
    static void zza(ValidateAccountRequest validateAccountRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.mVersionCode);
        zzb.zzc(parcel, 2, validateAccountRequest.zzatk());
        zzb.zza(parcel, 3, validateAccountRequest.wY, false);
        zzb.zza(parcel, 4, validateAccountRequest.zzati(), i, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzatl(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcj(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgh(i);
    }

    public ValidateAccountRequest zzcj(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzcl = zza.zzcl(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 3:
                    iBinder = zza.zzr(parcel, zzck);
                    break;
                case 4:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzck, Scope.CREATOR);
                    break;
                case 5:
                    bundle = zza.zzs(parcel, zzck);
                    break;
                case 6:
                    str = zza.zzq(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new ValidateAccountRequest(i2, i, iBinder, scopeArr, bundle, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public ValidateAccountRequest[] zzgh(int i) {
        return new ValidateAccountRequest[i];
    }
}
