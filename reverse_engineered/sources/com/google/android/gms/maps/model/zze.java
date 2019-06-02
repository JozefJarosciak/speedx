package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<LatLng> {
    static void zza(LatLng latLng, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, latLng.getVersionCode());
        zzb.zza(parcel, 2, latLng.latitude);
        zzb.zza(parcel, 3, latLng.longitude);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzoa(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzur(i);
    }

    public LatLng zzoa(Parcel parcel) {
        double d = 0.0d;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    d2 = zza.zzn(parcel, zzck);
                    break;
                case 3:
                    d = zza.zzn(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new LatLng(i, d2, d);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LatLng[] zzur(int i) {
        return new LatLng[i];
    }
}
