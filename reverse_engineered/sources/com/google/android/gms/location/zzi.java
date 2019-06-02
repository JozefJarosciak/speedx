package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<LocationSettingsStates> {
    static void zza(LocationSettingsStates locationSettingsStates, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, locationSettingsStates.isGpsUsable());
        zzb.zza(parcel, 2, locationSettingsStates.isNetworkLocationUsable());
        zzb.zza(parcel, 3, locationSettingsStates.isBleUsable());
        zzb.zza(parcel, 4, locationSettingsStates.isGpsPresent());
        zzb.zza(parcel, 5, locationSettingsStates.isNetworkLocationPresent());
        zzb.zza(parcel, 6, locationSettingsStates.isBlePresent());
        zzb.zzc(parcel, 1000, locationSettingsStates.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmu(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztd(i);
    }

    public LocationSettingsStates zzmu(Parcel parcel) {
        boolean z = false;
        int zzcl = zza.zzcl(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    z6 = zza.zzc(parcel, zzck);
                    break;
                case 2:
                    z5 = zza.zzc(parcel, zzck);
                    break;
                case 3:
                    z4 = zza.zzc(parcel, zzck);
                    break;
                case 4:
                    z3 = zza.zzc(parcel, zzck);
                    break;
                case 5:
                    z2 = zza.zzc(parcel, zzck);
                    break;
                case 6:
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
            return new LocationSettingsStates(i, z6, z5, z4, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LocationSettingsStates[] zztd(int i) {
        return new LocationSettingsStates[i];
    }
}
