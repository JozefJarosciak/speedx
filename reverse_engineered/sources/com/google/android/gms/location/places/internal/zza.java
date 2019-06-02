package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.places.internal.AutocompletePredictionEntity.SubstringEntity;
import java.util.List;

public class zza implements Creator<AutocompletePredictionEntity> {
    static void zza(AutocompletePredictionEntity autocompletePredictionEntity, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, autocompletePredictionEntity.afk, false);
        zzb.zza(parcel, 2, autocompletePredictionEntity.aeL, false);
        zzb.zza(parcel, 3, autocompletePredictionEntity.aeh, false);
        zzb.zzc(parcel, 4, autocompletePredictionEntity.afl, false);
        zzb.zzc(parcel, 5, autocompletePredictionEntity.afm);
        zzb.zza(parcel, 6, autocompletePredictionEntity.afn, false);
        zzb.zzc(parcel, 7, autocompletePredictionEntity.afo, false);
        zzb.zzc(parcel, 1000, autocompletePredictionEntity.mVersionCode);
        zzb.zza(parcel, 8, autocompletePredictionEntity.afp, false);
        zzb.zzc(parcel, 9, autocompletePredictionEntity.afq, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznk(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztz(i);
    }

    public AutocompletePredictionEntity zznk(Parcel parcel) {
        int i = 0;
        List list = null;
        int zzcl = com.google.android.gms.common.internal.safeparcel.zza.zzcl(parcel);
        String str = null;
        List list2 = null;
        String str2 = null;
        List list3 = null;
        String str3 = null;
        List list4 = null;
        String str4 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = com.google.android.gms.common.internal.safeparcel.zza.zzck(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgi(zzck)) {
                case 1:
                    str3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzck);
                    break;
                case 2:
                    str4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzck);
                    break;
                case 3:
                    list4 = com.google.android.gms.common.internal.safeparcel.zza.zzad(parcel, zzck);
                    break;
                case 4:
                    list3 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzck, SubstringEntity.CREATOR);
                    break;
                case 5:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzck);
                    break;
                case 6:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzck);
                    break;
                case 7:
                    list2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzck, SubstringEntity.CREATOR);
                    break;
                case 8:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzck);
                    break;
                case 9:
                    list = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzck, SubstringEntity.CREATOR);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzck);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new AutocompletePredictionEntity(i2, str4, list4, i, str3, list3, str2, list2, str, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public AutocompletePredictionEntity[] zztz(int i) {
        return new AutocompletePredictionEntity[i];
    }
}
