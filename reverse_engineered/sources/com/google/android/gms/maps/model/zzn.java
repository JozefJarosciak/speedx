package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzn implements Creator<Tile> {
    static void zza(Tile tile, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, tile.getVersionCode());
        zzb.zzc(parcel, 2, tile.width);
        zzb.zzc(parcel, 3, tile.height);
        zzb.zza(parcel, 4, tile.data, false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzoj(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzva(i);
    }

    public Tile zzoj(Parcel parcel) {
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 4:
                    bArr = zza.zzt(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new Tile(i3, i2, i, bArr);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public Tile[] zzva(int i) {
        return new Tile[i];
    }
}
