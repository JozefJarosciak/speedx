package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm implements Creator<PlaceLikelihoodEntity> {
    static void zza(PlaceLikelihoodEntity placeLikelihoodEntity, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placeLikelihoodEntity.afS, i, false);
        zzb.zza(parcel, 2, placeLikelihoodEntity.afT);
        zzb.zzc(parcel, 1000, placeLikelihoodEntity.mVersionCode);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznm(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzuc(i);
    }

    public PlaceLikelihoodEntity zznm(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        PlaceEntity placeEntity = null;
        float f = 0.0f;
        while (parcel.dataPosition() < zzcl) {
            int i2;
            float f2;
            PlaceEntity placeEntity2;
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i2 = i;
                    PlaceEntity placeEntity3 = (PlaceEntity) zza.zza(parcel, zzck, PlaceEntity.CREATOR);
                    f2 = f;
                    placeEntity2 = placeEntity3;
                    break;
                case 2:
                    f2 = zza.zzl(parcel, zzck);
                    placeEntity2 = placeEntity;
                    i2 = i;
                    break;
                case 1000:
                    float f3 = f;
                    placeEntity2 = placeEntity;
                    i2 = zza.zzg(parcel, zzck);
                    f2 = f3;
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    f2 = f;
                    placeEntity2 = placeEntity;
                    i2 = i;
                    break;
            }
            i = i2;
            placeEntity = placeEntity2;
            f = f2;
        }
        if (parcel.dataPosition() == zzcl) {
            return new PlaceLikelihoodEntity(i, placeEntity, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlaceLikelihoodEntity[] zzuc(int i) {
        return new PlaceLikelihoodEntity[i];
    }
}
