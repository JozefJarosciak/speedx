package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;

public class PlaceLikelihoodEntity extends AbstractSafeParcelable implements PlaceLikelihood {
    public static final Creator<PlaceLikelihoodEntity> CREATOR = new zzm();
    final PlaceEntity afS;
    final float afT;
    final int mVersionCode;

    PlaceLikelihoodEntity(int i, PlaceEntity placeEntity, float f) {
        this.mVersionCode = i;
        this.afS = placeEntity;
        this.afT = f;
    }

    public static PlaceLikelihoodEntity zza(PlaceEntity placeEntity, float f) {
        return new PlaceLikelihoodEntity(0, (PlaceEntity) zzab.zzaa(placeEntity), f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceLikelihoodEntity)) {
            return false;
        }
        PlaceLikelihoodEntity placeLikelihoodEntity = (PlaceLikelihoodEntity) obj;
        return this.afS.equals(placeLikelihoodEntity.afS) && this.afT == placeLikelihoodEntity.afT;
    }

    public /* synthetic */ Object freeze() {
        return zzbox();
    }

    public float getLikelihood() {
        return this.afT;
    }

    public Place getPlace() {
        return this.afS;
    }

    public int hashCode() {
        return zzaa.hashCode(this.afS, Float.valueOf(this.afT));
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return zzaa.zzz(this).zzg(GeocodingCriteria.TYPE_PLACE, this.afS).zzg("likelihood", Float.valueOf(this.afT)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }

    public PlaceLikelihood zzbox() {
        return this;
    }
}
