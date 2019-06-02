package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public class zzl implements Creator<PlaceEntity> {
    static void zza(PlaceEntity placeEntity, Parcel parcel, int i) {
        int zzcm = zzb.zzcm(parcel);
        zzb.zza(parcel, 1, placeEntity.getId(), false);
        zzb.zza(parcel, 2, placeEntity.zzbos(), false);
        zzb.zza(parcel, 3, placeEntity.zzbou(), i, false);
        zzb.zza(parcel, 4, placeEntity.getLatLng(), i, false);
        zzb.zza(parcel, 5, placeEntity.zzbon());
        zzb.zza(parcel, 6, placeEntity.getViewport(), i, false);
        zzb.zza(parcel, 7, placeEntity.zzbot(), false);
        zzb.zzc(parcel, 1000, placeEntity.mVersionCode);
        zzb.zza(parcel, 8, placeEntity.getWebsiteUri(), i, false);
        zzb.zza(parcel, 9, placeEntity.zzboq());
        zzb.zza(parcel, 10, placeEntity.getRating());
        zzb.zzc(parcel, 11, placeEntity.getPriceLevel());
        zzb.zza(parcel, 12, placeEntity.zzbor());
        zzb.zza(parcel, 13, placeEntity.zzbom(), false);
        zzb.zza(parcel, 14, (String) placeEntity.getAddress(), false);
        zzb.zza(parcel, 15, (String) placeEntity.getPhoneNumber(), false);
        zzb.zza(parcel, 16, placeEntity.zzboo(), false);
        zzb.zzb(parcel, 17, placeEntity.zzbop(), false);
        zzb.zza(parcel, 19, (String) placeEntity.getName(), false);
        zzb.zza(parcel, 20, placeEntity.getPlaceTypes(), false);
        zzb.zzaj(parcel, zzcm);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zznl(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzub(i);
    }

    public PlaceEntity zznl(Parcel parcel) {
        int zzcl = zza.zzcl(parcel);
        int i = 0;
        String str = null;
        List list = null;
        List list2 = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        List list3 = null;
        LatLng latLng = null;
        float f = 0.0f;
        LatLngBounds latLngBounds = null;
        String str6 = null;
        Uri uri = null;
        boolean z = false;
        float f2 = 0.0f;
        int i2 = 0;
        long j = 0;
        PlaceLocalization placeLocalization = null;
        while (parcel.dataPosition() < zzcl) {
            int zzck = zza.zzck(parcel);
            switch (zza.zzgi(zzck)) {
                case 1:
                    str = zza.zzq(parcel, zzck);
                    break;
                case 2:
                    bundle = zza.zzs(parcel, zzck);
                    break;
                case 3:
                    placeLocalization = (PlaceLocalization) zza.zza(parcel, zzck, (Creator) PlaceLocalization.CREATOR);
                    break;
                case 4:
                    latLng = (LatLng) zza.zza(parcel, zzck, LatLng.CREATOR);
                    break;
                case 5:
                    f = zza.zzl(parcel, zzck);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zza.zza(parcel, zzck, (Creator) LatLngBounds.CREATOR);
                    break;
                case 7:
                    str6 = zza.zzq(parcel, zzck);
                    break;
                case 8:
                    uri = (Uri) zza.zza(parcel, zzck, Uri.CREATOR);
                    break;
                case 9:
                    z = zza.zzc(parcel, zzck);
                    break;
                case 10:
                    f2 = zza.zzl(parcel, zzck);
                    break;
                case 11:
                    i2 = zza.zzg(parcel, zzck);
                    break;
                case 12:
                    j = zza.zzi(parcel, zzck);
                    break;
                case 13:
                    list2 = zza.zzad(parcel, zzck);
                    break;
                case 14:
                    str3 = zza.zzq(parcel, zzck);
                    break;
                case 15:
                    str4 = zza.zzq(parcel, zzck);
                    break;
                case 16:
                    str5 = zza.zzq(parcel, zzck);
                    break;
                case 17:
                    list3 = zza.zzae(parcel, zzck);
                    break;
                case 19:
                    str2 = zza.zzq(parcel, zzck);
                    break;
                case 20:
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
            return new PlaceEntity(i, str, list, list2, bundle, str2, str3, str4, str5, list3, latLng, f, latLngBounds, str6, uri, z, f2, i2, j, placeLocalization);
        }
        throw new zza.zza("Overread allowed size end=" + zzcl, parcel);
    }

    public PlaceEntity[] zzub(int i) {
        return new PlaceEntity[i];
    }
}
