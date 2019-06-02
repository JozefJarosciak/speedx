package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzn implements Creator<LocationRequestUpdateData> {
    static void zza(LocationRequestUpdateData locationRequestUpdateData, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, locationRequestUpdateData.adX);
        zzb.zza(parcel, 2, locationRequestUpdateData.adY, i, false);
        zzb.zza(parcel, 3, locationRequestUpdateData.zzbnn(), false);
        zzb.zza(parcel, 4, locationRequestUpdateData.mPendingIntent, i, false);
        zzb.zza(parcel, 5, locationRequestUpdateData.zzbno(), false);
        zzb.zza(parcel, 6, locationRequestUpdateData.zzbnp(), false);
        zzb.zzc(parcel, 1000, locationRequestUpdateData.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmy(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztk(i);
    }

    public LocationRequestUpdateData zzmy(Parcel parcel) {
        IBinder iBinder = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        int i2 = 1;
        IBinder iBinder2 = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder3 = null;
        LocationRequestInternal locationRequestInternal = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    locationRequestInternal = (LocationRequestInternal) zza.zza(parcel, zzck, LocationRequestInternal.CREATOR);
                    break;
                case 3:
                    iBinder3 = zza.zzr(parcel, zzck);
                    break;
                case 4:
                    pendingIntent = (PendingIntent) zza.zza(parcel, zzck, PendingIntent.CREATOR);
                    break;
                case 5:
                    iBinder2 = zza.zzr(parcel, zzck);
                    break;
                case 6:
                    iBinder = zza.zzr(parcel, zzck);
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
            return new LocationRequestUpdateData(i, i2, locationRequestInternal, iBinder3, pendingIntent, iBinder2, iBinder);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public LocationRequestUpdateData[] zztk(int i) {
        return new LocationRequestUpdateData[i];
    }
}
