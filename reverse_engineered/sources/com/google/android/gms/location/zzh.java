package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<LocationSettingsResult> {
    static void zza(LocationSettingsResult locationSettingsResult, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, locationSettingsResult.getStatus(), i, false);
        zzb.zza(parcel, 2, locationSettingsResult.getLocationSettingsStates(), i, false);
        zzb.zzc(parcel, 1000, locationSettingsResult.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmt(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztc(i);
    }

    public LocationSettingsResult zzmt(Parcel parcel) {
        LocationSettingsStates locationSettingsStates = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzcl) {
            int i2;
            LocationSettingsStates locationSettingsStates2;
            Status status2;
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzck, Status.CREATOR);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status3;
                    break;
                case 2:
                    locationSettingsStates2 = (LocationSettingsStates) zza.zza(parcel, zzck, LocationSettingsStates.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    LocationSettingsStates locationSettingsStates3 = locationSettingsStates;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzck);
                    locationSettingsStates2 = locationSettingsStates3;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            locationSettingsStates = locationSettingsStates2;
        }
        if (parcel.dataPosition() == zzcl) {
            return new LocationSettingsResult(i, status, locationSettingsStates);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LocationSettingsResult[] zztc(int i) {
        return new LocationSettingsResult[i];
    }
}
