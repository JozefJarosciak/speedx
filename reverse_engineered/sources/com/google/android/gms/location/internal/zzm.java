package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public class zzm implements Creator<LocationRequestInternal> {
    static void zza(LocationRequestInternal locationRequestInternal, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, locationRequestInternal.PM, i, false);
        zzb.zza(parcel, 4, locationRequestInternal.acu);
        zzb.zzc(parcel, 5, locationRequestInternal.adU, false);
        zzb.zza(parcel, 6, locationRequestInternal.mTag, false);
        zzb.zza(parcel, 7, locationRequestInternal.adV);
        zzb.zzc(parcel, 1000, locationRequestInternal.getVersionCode());
        zzb.zza(parcel, 8, locationRequestInternal.adW);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmx(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztj(i);
    }

    public LocationRequestInternal zzmx(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzcl = zza.zzcl(parcel);
        boolean z2 = true;
        List list = LocationRequestInternal.adT;
        boolean z3 = false;
        LocationRequest locationRequest = null;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    locationRequest = (LocationRequest) zza.zza(parcel, zzck, LocationRequest.CREATOR);
                    break;
                case 4:
                    z2 = zza.zzc(parcel, zzck);
                    break;
                case 5:
                    list = zza.zzc(parcel, zzck, ClientIdentity.CREATOR);
                    break;
                case 6:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 7:
                    z3 = zza.zzc(parcel, zzck);
                    break;
                case 8:
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
            return new LocationRequestInternal(i, locationRequest, z2, list, str, z3, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LocationRequestInternal[] zztj(int i) {
        return new LocationRequestInternal[i];
    }
}
