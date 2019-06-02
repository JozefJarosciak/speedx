package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<PlacePhotoResult> {
    static void zza(PlacePhotoResult placePhotoResult, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placePhotoResult.getStatus(), i, false);
        zzb.zza(parcel, 2, placePhotoResult.aeK, i, false);
        zzb.zzc(parcel, 1000, placePhotoResult.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzng(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztv(i);
    }

    public PlacePhotoResult zzng(Parcel parcel) {
        BitmapTeleporter bitmapTeleporter = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzcl) {
            int i2;
            BitmapTeleporter bitmapTeleporter2;
            Status status2;
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzck, Status.CREATOR);
                    bitmapTeleporter2 = bitmapTeleporter;
                    status2 = status3;
                    break;
                case 2:
                    bitmapTeleporter2 = (BitmapTeleporter) zza.zza(parcel, zzck, BitmapTeleporter.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    BitmapTeleporter bitmapTeleporter3 = bitmapTeleporter;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzck);
                    bitmapTeleporter2 = bitmapTeleporter3;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    bitmapTeleporter2 = bitmapTeleporter;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            bitmapTeleporter = bitmapTeleporter2;
        }
        if (parcel.dataPosition() == zzcl) {
            return new PlacePhotoResult(i, status, bitmapTeleporter);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlacePhotoResult[] zztv(int i) {
        return new PlacePhotoResult[i];
    }
}
