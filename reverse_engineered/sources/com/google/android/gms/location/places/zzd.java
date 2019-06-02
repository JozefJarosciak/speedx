package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzd implements Creator<NearbyAlertFilter> {
    static void zza(NearbyAlertFilter nearbyAlertFilter, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzb(parcel, 1, nearbyAlertFilter.aen, false);
        zzb.zza(parcel, 2, nearbyAlertFilter.aeo, false);
        zzb.zzc(parcel, 3, nearbyAlertFilter.aep, false);
        zzb.zza(parcel, 4, nearbyAlertFilter.aeq, false);
        zzb.zza(parcel, 5, nearbyAlertFilter.aer);
        zzb.zzc(parcel, 1000, nearbyAlertFilter.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznc(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztq(i);
    }

    public NearbyAlertFilter zznc(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzcl = zza.zzcl(parcel);
        List list = null;
        List list2 = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    list3 = zza.zzae(parcel, zzck);
                    break;
                case 2:
                    list2 = zza.zzad(parcel, zzck);
                    break;
                case 3:
                    list = zza.zzc(parcel, zzck, UserDataType.CREATOR);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 5:
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
            return new NearbyAlertFilter(i, list3, list2, list, str, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public NearbyAlertFilter[] zztq(int i) {
        return new NearbyAlertFilter[i];
    }
}
