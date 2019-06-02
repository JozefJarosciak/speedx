package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<NearbyAlertRequest> {
    static void zza(NearbyAlertRequest nearbyAlertRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, nearbyAlertRequest.zzbns());
        zzb.zzc(parcel, 2, nearbyAlertRequest.zzbnw());
        zzb.zza(parcel, 3, nearbyAlertRequest.zzbnx(), i, false);
        zzb.zza(parcel, 4, nearbyAlertRequest.zzbny(), i, false);
        zzb.zza(parcel, 5, nearbyAlertRequest.zzbnz());
        zzb.zzc(parcel, 6, nearbyAlertRequest.zzboa());
        zzb.zzc(parcel, 7, nearbyAlertRequest.getPriority());
        zzb.zzc(parcel, 1000, nearbyAlertRequest.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznd(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztr(i);
    }

    public NearbyAlertRequest zznd(Parcel parcel) {
        NearbyAlertFilter nearbyAlertFilter = null;
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        int i2 = -1;
        int i3 = 110;
        boolean z = false;
        PlaceFilter placeFilter = null;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i4 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 3:
                    placeFilter = (PlaceFilter) zza.zza(parcel, zzck, PlaceFilter.CREATOR);
                    break;
                case 4:
                    nearbyAlertFilter = (NearbyAlertFilter) zza.zza(parcel, zzck, NearbyAlertFilter.CREATOR);
                    break;
                case 5:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 6:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 7:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                case 1000:
                    i5 = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new NearbyAlertRequest(i5, i4, i2, placeFilter, nearbyAlertFilter, z, i, i3);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public NearbyAlertRequest[] zztr(int i) {
        return new NearbyAlertRequest[i];
    }
}
