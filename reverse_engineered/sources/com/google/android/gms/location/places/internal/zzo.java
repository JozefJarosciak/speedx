package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzo implements Creator<PlaceLocalization> {
    static void zza(PlaceLocalization placeLocalization, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placeLocalization.name, false);
        zzb.zza(parcel, 2, placeLocalization.address, false);
        zzb.zza(parcel, 3, placeLocalization.afU, false);
        zzb.zza(parcel, 4, placeLocalization.afV, false);
        zzb.zzb(parcel, 5, placeLocalization.afW, false);
        zzb.zzc(parcel, 1000, placeLocalization.versionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznn(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzud(i);
    }

    public PlaceLocalization zznn(Parcel parcel) {
        List list = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
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
                case 5:
                    list = zza.zzae(parcel, zzck);
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
            return new PlaceLocalization(i, str4, str3, str2, str, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlaceLocalization[] zzud(int i) {
        return new PlaceLocalization[i];
    }
}
