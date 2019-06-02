package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzc implements Creator<GestureRequest> {
    static void zza(GestureRequest gestureRequest, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, gestureRequest.zzbng(), false);
        zzb.zzc(parcel, 1000, gestureRequest.getVersionCode());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmq(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzsx(i);
    }

    public GestureRequest zzmq(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    list = zza.zzad(parcel, zzck);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new GestureRequest(i, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public GestureRequest[] zzsx(int i) {
        return new GestureRequest[i];
    }
}
