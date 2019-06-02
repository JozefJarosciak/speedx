package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo implements Creator<TileOverlayOptions> {
    static void zza(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, tileOverlayOptions.getVersionCode());
        zzb.zza(parcel, 2, tileOverlayOptions.zzbqj(), false);
        zzb.zza(parcel, 3, tileOverlayOptions.isVisible());
        zzb.zza(parcel, 4, tileOverlayOptions.getZIndex());
        zzb.zza(parcel, 5, tileOverlayOptions.getFadeIn());
        zzb.zza(parcel, 6, tileOverlayOptions.getTransparency());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzok(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzvb(i);
    }

    public TileOverlayOptions zzok(Parcel parcel) {
        boolean z = false;
        float f = 0.0f;
        int zzcl = zza.zzcl(parcel);
        IBinder iBinder = null;
        boolean z2 = true;
        float f2 = 0.0f;
        int i = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    iBinder = zza.zzr(parcel, zzck);
                    break;
                case 3:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 4:
                    f2 = zza.zzl(parcel, zzck);
                    break;
                case 5:
                    z2 = zza.zzc(parcel, zzck);
                    break;
                case 6:
                    f = zza.zzl(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new TileOverlayOptions(i, iBinder, z, f2, z2, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public TileOverlayOptions[] zzvb(int i) {
        return new TileOverlayOptions[i];
    }
}
