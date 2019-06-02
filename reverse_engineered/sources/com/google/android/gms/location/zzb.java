package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.List;

public class zzb implements Creator<GeofencingRequest> {
    static void zza(GeofencingRequest geofencingRequest, Parcel parcel, int i) {
        int zzcm = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, geofencingRequest.zzbnf(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, geofencingRequest.getInitialTrigger());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, geofencingRequest.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzsw(i);
    }

    public GeofencingRequest zzmp(Parcel parcel) {
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    list = zza.zzc(parcel, zzck, ParcelableGeofence.CREATOR);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new GeofencingRequest(i2, list, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public GeofencingRequest[] zzsw(int i) {
        return new GeofencingRequest[i];
    }
}
