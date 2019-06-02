package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair;

public class zzb implements Creator<FieldMapPair> {
    static void zza(FieldMapPair fieldMapPair, Parcel parcel, int i) {
        int zzcm = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, fieldMapPair.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, fieldMapPair.zzcb, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, fieldMapPair.zH, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcs(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgp(i);
    }

    public FieldMapPair zzcs(Parcel parcel) {
        Field field = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 3:
                    field = (Field) zza.zza(parcel, zzck, Field.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new FieldMapPair(i, str, field);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public FieldMapPair[] zzgp(int i) {
        return new FieldMapPair[i];
    }
}
