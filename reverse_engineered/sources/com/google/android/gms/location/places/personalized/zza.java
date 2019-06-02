package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zza implements Creator<AliasedPlace> {
    static void zza(AliasedPlace aliasedPlace, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, aliasedPlace.getPlaceId(), false);
        zzb.zzb(parcel, 2, aliasedPlace.zzbpa(), false);
        zzb.zzc(parcel, 1000, aliasedPlace.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznq(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzug(i);
    }

    public AliasedPlace zznq(Parcel parcel) {
        List list = null;
        int zzcl = com.google.android.gms.common.internal.safeparcel.zza.zzcl(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = com.google.android.gms.common.internal.safeparcel.zza.zzck(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgi(zzck)) {
                case 1:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzck);
                    break;
                case 2:
                    list = com.google.android.gms.common.internal.safeparcel.zza.zzae(parcel, zzck);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzck);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new AliasedPlace(i, str, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public AliasedPlace[] zzug(int i) {
        return new AliasedPlace[i];
    }
}
