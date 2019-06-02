package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.List;

public class zzb implements Creator<AliasedPlacesResult> {
    static void zza(AliasedPlacesResult aliasedPlacesResult, Parcel parcel, int i) {
        int zzcm = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, aliasedPlacesResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, aliasedPlacesResult.zzbpb(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, aliasedPlacesResult.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznr(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzuh(i);
    }

    public AliasedPlacesResult zznr(Parcel parcel) {
        List list = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzcl) {
            int i2;
            Status status2;
            ArrayList zzc;
            int zzck = zza.zzck(parcel);
            List list2;
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzck, Status.CREATOR);
                    list2 = list;
                    status2 = status3;
                    break;
                case 2:
                    zzc = zza.zzc(parcel, zzck, AliasedPlace.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    List list3 = list;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzck);
                    list2 = list3;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    zzc = list;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            Object obj = zzc;
        }
        if (parcel.dataPosition() == zzcl) {
            return new AliasedPlacesResult(i, status, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public AliasedPlacesResult[] zzuh(int i) {
        return new AliasedPlacesResult[i];
    }
}
