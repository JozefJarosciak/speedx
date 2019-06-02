package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzc implements Creator<CheckServerAuthResult> {
    static void zza(CheckServerAuthResult checkServerAuthResult, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.mVersionCode);
        zzb.zza(parcel, 2, checkServerAuthResult.aus);
        zzb.zzc(parcel, 3, checkServerAuthResult.aut, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzqn(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzxr(i);
    }

    public CheckServerAuthResult zzqn(Parcel parcel) {
        boolean z = false;
        int zzcl = zza.zzcl(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 3:
                    list = zza.zzc(parcel, zzck, Scope.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new CheckServerAuthResult(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public CheckServerAuthResult[] zzxr(int i) {
        return new CheckServerAuthResult[i];
    }
}
