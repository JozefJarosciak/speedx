package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.places.internal.AutocompletePredictionEntity.SubstringEntity;

public class zzu implements Creator<SubstringEntity> {
    static void zza(SubstringEntity substringEntity, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, substringEntity.mOffset);
        zzb.zzc(parcel, 2, substringEntity.mLength);
        zzb.zzc(parcel, 1000, substringEntity.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzuf(i);
    }

    public SubstringEntity zznp(Parcel parcel) {
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
            return new SubstringEntity(i3, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public SubstringEntity[] zzuf(int i) {
        return new SubstringEntity[i];
    }
}
