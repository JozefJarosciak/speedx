package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<Scope> {
    static void zza(Scope scope, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, scope.mVersionCode);
        zzb.zza(parcel, 2, scope.zzaoh(), false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzby(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzfd(i);
    }

    public Scope zzby(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new Scope(i, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public Scope[] zzfd(int i) {
        return new Scope[i];
    }
}
