package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzb implements Creator<Flag> {
    static void zza(Flag flag, Parcel parcel, int i) {
        int zzcm = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, flag.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, flag.name, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, flag.asc);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, flag.abu);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, flag.abw);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, flag.zr, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, flag.asd, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, flag.ase);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 9, flag.asf);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzpv(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzwy(i);
    }

    public Flag zzpv(Parcel parcel) {
        byte[] bArr = null;
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        long j = 0;
        double d = 0.0d;
        int i2 = 0;
        String str = null;
        boolean z = false;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    str2 = zza.zzq(parcel, zzck);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzck);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 5:
                    d = zza.zzn(parcel, zzck);
                    break;
                case 6:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 7:
                    bArr = zza.zzt(parcel, zzck);
                    break;
                case 8:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 9:
                    i = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new Flag(i3, str2, j, z, d, str, bArr, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public Flag[] zzwy(int i) {
        return new Flag[i];
    }
}
