package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd implements Creator<PlaceAlias> {
    static void zza(PlaceAlias placeAlias, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placeAlias.zzbpc(), false);
        zzb.zzc(parcel, 1000, placeAlias.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzns(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzui(i);
    }

    public PlaceAlias zzns(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    str = zza.zzq(parcel, zzck);
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
            return new PlaceAlias(i, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlaceAlias[] zzui(int i) {
        return new PlaceAlias[i];
    }
}
