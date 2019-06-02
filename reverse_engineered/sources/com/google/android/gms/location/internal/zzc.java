package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<ClientIdentity> {
    static void zza(ClientIdentity clientIdentity, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, clientIdentity.uid);
        zzb.zza(parcel, 2, clientIdentity.packageName, false);
        zzb.zzc(parcel, 1000, clientIdentity.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmv(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztg(i);
    }

    public ClientIdentity zzmv(Parcel parcel) {
        int i = 0;
        int zzcl = zza.zzcl(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzck);
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
            return new ClientIdentity(i2, i, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public ClientIdentity[] zztg(int i) {
        return new ClientIdentity[i];
    }
}
