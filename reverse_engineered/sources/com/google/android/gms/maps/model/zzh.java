package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;
import java.util.List;

public class zzh implements Creator<PolygonOptions> {
    static void zza(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zzc(parcel, 1, polygonOptions.getVersionCode());
        zzb.zzc(parcel, 2, polygonOptions.getPoints(), false);
        zzb.zzd(parcel, 3, polygonOptions.zzbqi(), false);
        zzb.zza(parcel, 4, polygonOptions.getStrokeWidth());
        zzb.zzc(parcel, 5, polygonOptions.getStrokeColor());
        zzb.zzc(parcel, 6, polygonOptions.getFillColor());
        zzb.zza(parcel, 7, polygonOptions.getZIndex());
        zzb.zza(parcel, 8, polygonOptions.isVisible());
        zzb.zza(parcel, 9, polygonOptions.isGeodesic());
        zzb.zza(parcel, 10, polygonOptions.isClickable());
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzod(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzuu(i);
    }

    public PolygonOptions zzod(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int zzcl = zza.zzcl(parcel);
        List list = null;
        List arrayList = new ArrayList();
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    i3 = zza.zzg(parcel, zzck);
                    break;
                case 2:
                    list = zza.zzc(parcel, zzck, LatLng.CREATOR);
                    break;
                case 3:
                    zza.zza(parcel, zzck, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    f2 = zza.zzl(parcel, zzck);
                    break;
                case 5:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 6:
                    i = zza.zzg(parcel, zzck);
                    break;
                case 7:
                    f = zza.zzl(parcel, zzck);
                    break;
                case 8:
                    z3 = zza.zzc(parcel, zzck);
                    break;
                case 9:
                    z2 = zza.zzc(parcel, zzck);
                    break;
                case 10:
                    z = zza.zzc(parcel, zzck);
                    break;
                default:
                    zza.zzb(parcel, zzck);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcl) {
            return new PolygonOptions(i3, list, arrayList, f2, i2, i, f, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PolygonOptions[] zzuu(int i) {
        return new PolygonOptions[i];
    }
}
