package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd implements Creator<LatLngBounds> {
    static void zza(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, latLngBounds.getVersionCode());
        zzb.zza(parcel, 2, latLngBounds.southwest, i, false);
        zzb.zza(parcel, 3, latLngBounds.northeast, i, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznz(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzuq(i);
    }

    public LatLngBounds zznz(Parcel parcel) {
        LatLng latLng = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < zzcl) {
            int zzg;
            LatLng latLng3;
            int zzck = zza.zzck(parcel);
            LatLng latLng4;
            switch (zza.zzgi(zzck)) {
                case 1:
                    latLng4 = latLng;
                    latLng = latLng2;
                    zzg = zza.zzg(parcel, zzck);
                    latLng3 = latLng4;
                    break;
                case 2:
                    zzg = i;
                    latLng4 = (LatLng) zza.zza(parcel, zzck, LatLng.CREATOR);
                    latLng3 = latLng;
                    latLng = latLng4;
                    break;
                case 3:
                    latLng3 = (LatLng) zza.zza(parcel, zzck, LatLng.CREATOR);
                    latLng = latLng2;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    latLng3 = latLng;
                    latLng = latLng2;
                    zzg = i;
                    break;
            }
            i = zzg;
            latLng2 = latLng;
            latLng = latLng3;
        }
        if (parcel.dataPosition() == zzcl) {
            return new LatLngBounds(i, latLng2, latLng);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LatLngBounds[] zzuq(int i) {
        return new LatLngBounds[i];
    }
}
