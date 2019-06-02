package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo implements Creator<ParcelableGeofence> {
    static void zza(ParcelableGeofence parcelableGeofence, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, parcelableGeofence.getRequestId(), false);
        zzb.zza(parcel, 2, parcelableGeofence.getExpirationTime());
        zzb.zza(parcel, 3, parcelableGeofence.zzbnq());
        zzb.zza(parcel, 4, parcelableGeofence.getLatitude());
        zzb.zza(parcel, 5, parcelableGeofence.getLongitude());
        zzb.zza(parcel, 6, parcelableGeofence.zzbnr());
        zzb.zzc(parcel, 7, parcelableGeofence.zzbns());
        zzb.zzc(parcel, 1000, parcelableGeofence.getVersionCode());
        zzb.zzc(parcel, 8, parcelableGeofence.zzbnt());
        zzb.zzc(parcel, 9, parcelableGeofence.zzbnu());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmz(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztn(i);
    }

    public ParcelableGeofence zzmz(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzck);
                    break;
                case 3:
                    s = zza.zzf(parcel, zzck);
                    break;
                case 4:
                    d = zza.zzn(parcel, zzck);
                    break;
                case 5:
                    d2 = zza.zzn(parcel, zzck);
                    break;
                case 6:
                    f = zza.zzl(parcel, zzck);
                    break;
                case 7:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 8:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                case 9:
                    i4 = zza.zzg(parcel, zzck);
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
            return new ParcelableGeofence(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public ParcelableGeofence[] zztn(int i) {
        return new ParcelableGeofence[i];
    }
}
