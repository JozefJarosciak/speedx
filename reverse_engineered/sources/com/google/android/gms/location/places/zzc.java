package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzc implements Creator<AutocompleteFilter> {
    static void zza(AutocompleteFilter autocompleteFilter, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, autocompleteFilter.aek);
        zzb.zza(parcel, 2, autocompleteFilter.ael, false);
        zzb.zzc(parcel, 1000, autocompleteFilter.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznb(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztp(i);
    }

    public AutocompleteFilter zznb(Parcel parcel) {
        boolean z = false;
        int zzcl = zza.zzcl(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 2:
                    list = zza.zzad(parcel, zzck);
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
            return new AutocompleteFilter(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public AutocompleteFilter[] zztp(int i) {
        return new AutocompleteFilter[i];
    }
}
