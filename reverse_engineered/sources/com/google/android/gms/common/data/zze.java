package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<DataHolder> {
    static void zza(DataHolder dataHolder, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, dataHolder.zzare(), false);
        zzb.zza(parcel, 2, dataHolder.zzarf(), i, false);
        zzb.zzc(parcel, 3, dataHolder.getStatusCode());
        zzb.zza(parcel, 4, dataHolder.zzaqy(), false);
        zzb.zzc(parcel, 1000, dataHolder.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcb(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzfr(i);
    }

    public DataHolder zzcb(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int zzcl = zza.zzcl(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    strArr = zza.zzac(parcel, zzck);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) zza.zzb(parcel, zzck, CursorWindow.CREATOR);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 4:
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
        if (parcel.dataPosition() != zzcl) {
            throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
        }
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.zzard();
        return dataHolder;
    }

    public DataHolder[] zzfr(int i) {
        return new DataHolder[i];
    }
}
