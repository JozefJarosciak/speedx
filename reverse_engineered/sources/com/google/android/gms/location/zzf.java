package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzf implements Creator<LocationResult> {
    static void zza(LocationResult locationResult, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, locationResult.getLocations(), false);
        zzb.zzc(parcel, 1000, locationResult.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmr(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzta(i);
    }

    public LocationResult zzmr(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        List list = LocationResult.adi;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    list = zza.zzc(parcel, zzck, Location.CREATOR);
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
            return new LocationResult(i, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LocationResult[] zzta(int i) {
        return new LocationResult[i];
    }
}
