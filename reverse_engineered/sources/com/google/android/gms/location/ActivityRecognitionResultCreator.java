package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class ActivityRecognitionResultCreator implements Creator<ActivityRecognitionResult> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, activityRecognitionResult.acy, false);
        zzb.zza(parcel, 2, activityRecognitionResult.acz);
        zzb.zza(parcel, 3, activityRecognitionResult.acA);
        zzb.zzc(parcel, 4, activityRecognitionResult.acB);
        zzb.zza(parcel, 5, activityRecognitionResult.extras, false);
        zzb.zzc(parcel, 1000, activityRecognitionResult.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public ActivityRecognitionResult createFromParcel(Parcel parcel) {
        long j = 0;
        Bundle bundle = null;
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        long j2 = 0;
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    list = zza.zzc(parcel, zzck, DetectedActivity.CREATOR);
                    break;
                case 2:
                    j2 = zza.zzi(parcel, zzck);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzck);
                    break;
                case 4:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 5:
                    bundle = zza.zzs(parcel, zzck);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new ActivityRecognitionResult(i2, list, j2, j, i, bundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public ActivityRecognitionResult[] newArray(int i) {
        return new ActivityRecognitionResult[i];
    }
}
