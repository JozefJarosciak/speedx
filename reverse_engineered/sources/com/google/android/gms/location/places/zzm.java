package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm implements Creator<UserDataType> {
    static void zza(UserDataType userDataType, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, userDataType.zzcgd, false);
        zzb.zzc(parcel, 2, userDataType.afi);
        zzb.zzc(parcel, 1000, userDataType.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznj(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzty(i);
    }

    public UserDataType zznj(Parcel parcel) {
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzck);
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
            return new UserDataType(i2, str, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public UserDataType[] zzty(int i) {
        return new UserDataType[i];
    }
}
