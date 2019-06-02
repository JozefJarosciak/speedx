package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class DetectedActivityCreator implements Creator<DetectedActivity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(DetectedActivity detectedActivity, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, detectedActivity.acF);
        zzb.zzc(parcel, 2, detectedActivity.acG);
        zzb.zzc(parcel, 1000, detectedActivity.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public DetectedActivity createFromParcel(Parcel parcel) {
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 1000:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new DetectedActivity(i3, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public DetectedActivity[] newArray(int i) {
        return new DetectedActivity[i];
    }
}
