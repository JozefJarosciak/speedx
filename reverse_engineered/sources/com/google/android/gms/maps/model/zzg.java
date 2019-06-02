package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzg implements Creator<PointOfInterest> {
    static void zza(PointOfInterest pointOfInterest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, pointOfInterest.getVersionCode());
        zzb.zza(parcel, 2, pointOfInterest.aiL, i, false);
        zzb.zza(parcel, 3, pointOfInterest.aiM, false);
        zzb.zza(parcel, 4, pointOfInterest.name, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzoc(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzut(i);
    }

    public PointOfInterest zzoc(Parcel parcel) {
        String str = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str2 = null;
        LatLng latLng = null;
        while (parcel.dataPosition() < zzcl) {
            LatLng latLng2;
            int zzg;
            String str3;
            int zzck = zza.zzck(parcel);
            String str4;
            switch (zza.zzgi(zzck)) {
                case 1:
                    str4 = str;
                    str = str2;
                    latLng2 = latLng;
                    zzg = zza.zzg(parcel, zzck);
                    str3 = str4;
                    break;
                case 2:
                    zzg = i;
                    str4 = str2;
                    latLng2 = (LatLng) zza.zza(parcel, zzck, LatLng.CREATOR);
                    str3 = str;
                    str = str4;
                    break;
                case 3:
                    latLng2 = latLng;
                    zzg = i;
                    str4 = str;
                    str = zza.zzq(parcel, zzck);
                    str3 = str4;
                    break;
                case 4:
                    str3 = zza.zzq(parcel, zzck);
                    str = str2;
                    latLng2 = latLng;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    str3 = str;
                    str = str2;
                    latLng2 = latLng;
                    zzg = i;
                    break;
            }
            i = zzg;
            latLng = latLng2;
            str2 = str;
            str = str3;
        }
        if (parcel.dataPosition() == zzcl) {
            return new PointOfInterest(i, latLng, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PointOfInterest[] zzut(int i) {
        return new PointOfInterest[i];
    }
}
