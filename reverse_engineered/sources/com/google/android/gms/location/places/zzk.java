package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk implements Creator<PlaceRequest> {
    static void zza(PlaceRequest placeRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 2, placeRequest.zzbnx(), i, false);
        zzb.zza(parcel, 3, placeRequest.getInterval());
        zzb.zzc(parcel, 4, placeRequest.getPriority());
        zzb.zza(parcel, 5, placeRequest.getExpirationTime());
        zzb.zzc(parcel, 1000, placeRequest.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzni(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztx(i);
    }

    public PlaceRequest zzni(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        PlaceFilter placeFilter = null;
        long j = PlaceRequest.aeQ;
        int i2 = 102;
        long j2 = Long.MAX_VALUE;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 2:
                    placeFilter = (PlaceFilter) zza.zza(parcel, zzck, PlaceFilter.CREATOR);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzck);
                    break;
                case 4:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 5:
                    j2 = zza.zzi(parcel, zzck);
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
            return new PlaceRequest(i, placeFilter, j, i2, j2);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlaceRequest[] zztx(int i) {
        return new PlaceRequest[i];
    }
}
