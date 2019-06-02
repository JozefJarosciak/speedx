package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzg implements Creator<PlaceFilter> {
    static void zza(PlaceFilter placeFilter, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placeFilter.aeo, false);
        zzb.zza(parcel, 3, placeFilter.aeE);
        zzb.zzc(parcel, 4, placeFilter.aep, false);
        zzb.zzb(parcel, 6, placeFilter.aen, false);
        zzb.zzc(parcel, 1000, placeFilter.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzne(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzts(i);
    }

    public PlaceFilter zzne(Parcel parcel) {
        boolean z = false;
        List list = null;
        int zzcl = zza.zzcl(parcel);
        List list2 = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    list3 = zza.zzad(parcel, zzck);
                    break;
                case 3:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 4:
                    list = zza.zzc(parcel, zzck, UserDataType.CREATOR);
                    break;
                case 6:
                    list2 = zza.zzae(parcel, zzck);
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
            return new PlaceFilter(i, list3, z, list2, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlaceFilter[] zzts(int i) {
        return new PlaceFilter[i];
    }
}
