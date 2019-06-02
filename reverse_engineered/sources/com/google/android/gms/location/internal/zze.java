package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<FusedLocationProviderResult> {
    static void zza(FusedLocationProviderResult fusedLocationProviderResult, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, fusedLocationProviderResult.getStatus(), i, false);
        zzb.zzc(parcel, 1000, fusedLocationProviderResult.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmw(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzth(i);
    }

    public FusedLocationProviderResult zzmw(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    status = (Status) zza.zza(parcel, zzck, Status.CREATOR);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new FusedLocationProviderResult(i, status);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public FusedLocationProviderResult[] zzth(int i) {
        return new FusedLocationProviderResult[i];
    }
}
