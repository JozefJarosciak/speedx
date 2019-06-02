package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzs implements Creator<PlacesParams> {
    static void zza(PlacesParams placesParams, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placesParams.agd, false);
        zzb.zza(parcel, 2, placesParams.age, false);
        zzb.zza(parcel, 3, placesParams.DP, false);
        zzb.zza(parcel, 4, placesParams.afc, false);
        zzb.zzc(parcel, 6, placesParams.agf);
        zzb.zzc(parcel, 7, placesParams.agg);
        zzb.zzc(parcel, 1000, placesParams.versionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzno(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzue(i);
    }

    public PlacesParams zzno(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzcl = zza.zzcl(parcel);
        int i2 = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    str4 = zza.zzq(parcel, zzck);
                    break;
                case 2:
                    str3 = zza.zzq(parcel, zzck);
                    break;
                case 3:
                    str2 = zza.zzq(parcel, zzck);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 6:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 7:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 1000:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new PlacesParams(i3, str4, str3, str2, str, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlacesParams[] zzue(int i) {
        return new PlacesParams[i];
    }
}
