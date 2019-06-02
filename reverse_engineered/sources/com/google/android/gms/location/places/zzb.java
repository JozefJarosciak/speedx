package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class zzb implements Creator<AddPlaceRequest> {
    static void zza(AddPlaceRequest addPlaceRequest, Parcel parcel, int i) {
        int zzcm = com.google.android.gms.common.internal.safeparcel.zzb.zzcm(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, addPlaceRequest.getName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, addPlaceRequest.getLatLng(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, addPlaceRequest.getAddress(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, addPlaceRequest.getPlaceTypes(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, addPlaceRequest.getPhoneNumber(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, addPlaceRequest.getWebsiteUri(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, addPlaceRequest.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzna(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzto(i);
    }

    public AddPlaceRequest zzna(Parcel parcel) {
        Uri uri = null;
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        List list = null;
        String str2 = null;
        LatLng latLng = null;
        String str3 = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    str3 = zza.zzq(parcel, zzck);
                    break;
                case 2:
                    latLng = (LatLng) zza.zza(parcel, zzck, LatLng.CREATOR);
                    break;
                case 3:
                    str2 = zza.zzq(parcel, zzck);
                    break;
                case 4:
                    list = zza.zzad(parcel, zzck);
                    break;
                case 5:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 6:
                    uri = (Uri) zza.zza(parcel, zzck, Uri.CREATOR);
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
            return new AddPlaceRequest(i, str3, latLng, str2, list, str, uri);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public AddPlaceRequest[] zzto(int i) {
        return new AddPlaceRequest[i];
    }
}
