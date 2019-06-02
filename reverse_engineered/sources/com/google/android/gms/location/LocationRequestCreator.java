package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class LocationRequestCreator implements Creator<LocationRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(LocationRequest locationRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, locationRequest.mPriority);
        zzb.zza(parcel, 2, locationRequest.add);
        zzb.zza(parcel, 3, locationRequest.ade);
        zzb.zza(parcel, 4, locationRequest.PO);
        zzb.zza(parcel, 5, locationRequest.acI);
        zzb.zzc(parcel, 6, locationRequest.adf);
        zzb.zza(parcel, 7, locationRequest.adg);
        zzb.zzc(parcel, 1000, locationRequest.getVersionCode());
        zzb.zza(parcel, 8, locationRequest.adh);
        zzb.zzaj(parcel, zzcm);
    }

    public LocationRequest createFromParcel(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        int i2 = 102;
        long j = 3600000;
        long j2 = 600000;
        boolean z = false;
        long j3 = Long.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        float f = 0.0f;
        long j4 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzck);
                    break;
                case 3:
                    j2 = zza.zzi(parcel, zzck);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 5:
                    j3 = zza.zzi(parcel, zzck);
                    break;
                case 6:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                case 7:
                    f = zza.zzl(parcel, zzck);
                    break;
                case 8:
                    j4 = zza.zzi(parcel, zzck);
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
            return new LocationRequest(i, i2, j, j2, z, j3, i3, f, j4);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LocationRequest[] newArray(int i) {
        return new LocationRequest[i];
    }
}
