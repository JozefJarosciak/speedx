package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<PlacePhotoMetadataResult> {
    static void zza(PlacePhotoMetadataResult placePhotoMetadataResult, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placePhotoMetadataResult.getStatus(), i, false);
        zzb.zza(parcel, 2, placePhotoMetadataResult.aeI, i, false);
        zzb.zzc(parcel, 1000, placePhotoMetadataResult.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznf(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztu(i);
    }

    public PlacePhotoMetadataResult zznf(Parcel parcel) {
        DataHolder dataHolder = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzcl) {
            int i2;
            DataHolder dataHolder2;
            Status status2;
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzck, Status.CREATOR);
                    dataHolder2 = dataHolder;
                    status2 = status3;
                    break;
                case 2:
                    dataHolder2 = (DataHolder) zza.zza(parcel, zzck, DataHolder.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    DataHolder dataHolder3 = dataHolder;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzck);
                    dataHolder2 = dataHolder3;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    dataHolder2 = dataHolder;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            dataHolder = dataHolder2;
        }
        if (parcel.dataPosition() == zzcl) {
            return new PlacePhotoMetadataResult(i, status, dataHolder);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlacePhotoMetadataResult[] zztu(int i) {
        return new PlacePhotoMetadataResult[i];
    }
}
