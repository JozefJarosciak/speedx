package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzg implements Creator<LocationSettingsRequest> {
    static void zza(LocationSettingsRequest locationSettingsRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, locationSettingsRequest.zzbew(), false);
        zzb.zza(parcel, 2, locationSettingsRequest.zzbnh());
        zzb.zza(parcel, 3, locationSettingsRequest.zzbni());
        zzb.zzc(parcel, 1000, locationSettingsRequest.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzms(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztb(i);
    }

    public LocationSettingsRequest zzms(Parcel parcel) {
        boolean z = false;
        int zzcl = zza.zzcl(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    list = zza.zzc(parcel, zzck, LocationRequest.CREATOR);
                    break;
                case 2:
                    z2 = zza.zzc(parcel, zzck);
                    break;
                case 3:
                    z = zza.zzc(parcel, zzck);
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
            return new LocationSettingsRequest(i, list, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LocationSettingsRequest[] zztb(int i) {
        return new LocationSettingsRequest[i];
    }
}
