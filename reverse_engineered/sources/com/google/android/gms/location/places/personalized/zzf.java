package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzf implements Creator<PlaceUserData> {
    static void zza(PlaceUserData placeUserData, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placeUserData.zzbpd(), false);
        zzb.zza(parcel, 2, placeUserData.getPlaceId(), false);
        zzb.zzc(parcel, 6, placeUserData.zzbpa(), false);
        zzb.zzc(parcel, 1000, placeUserData.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznt(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzuj(i);
    }

    public PlaceUserData zznt(Parcel parcel) {
        List list = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    str2 = zza.zzq(parcel, zzck);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 6:
                    list = zza.zzc(parcel, zzck, PlaceAlias.CREATOR);
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
            return new PlaceUserData(i, str2, str, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlaceUserData[] zzuj(int i) {
        return new PlaceUserData[i];
    }
}
