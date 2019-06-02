package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<ActivityRecognitionRequest> {
    static void zza(ActivityRecognitionRequest activityRecognitionRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, activityRecognitionRequest.getIntervalMillis());
        zzb.zza(parcel, 2, activityRecognitionRequest.zzbnb());
        zzb.zza(parcel, 3, activityRecognitionRequest.zzbnc(), i, false);
        zzb.zza(parcel, 4, activityRecognitionRequest.getTag(), false);
        zzb.zza(parcel, 5, activityRecognitionRequest.zzbnd(), false);
        zzb.zza(parcel, 6, activityRecognitionRequest.zzbne());
        zzb.zza(parcel, 7, activityRecognitionRequest.getAccountName(), false);
        zzb.zzc(parcel, 1000, activityRecognitionRequest.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmo(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzss(i);
    }

    public ActivityRecognitionRequest zzmo(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzcl = com.google.android.gms.common.internal.safeparcel.zza.zzcl(parcel);
        long j = 0;
        int[] iArr = null;
        String str2 = null;
        WorkSource workSource = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = com.google.android.gms.common.internal.safeparcel.zza.zzck(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgi(zzck)) {
                case 1:
                    j = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzck);
                    break;
                case 2:
                    z2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzck);
                    break;
                case 3:
                    workSource = (WorkSource) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzck, WorkSource.CREATOR);
                    break;
                case 4:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzck);
                    break;
                case 5:
                    iArr = com.google.android.gms.common.internal.safeparcel.zza.zzw(parcel, zzck);
                    break;
                case 6:
                    z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzck);
                    break;
                case 7:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzck);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzck);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new ActivityRecognitionRequest(i, j, z2, workSource, str2, iArr, z, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public ActivityRecognitionRequest[] zzss(int i) {
        return new ActivityRecognitionRequest[i];
    }
}
