package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class NearbyAlertFilter extends zza implements SafeParcelable {
    public static final zzd CREATOR = new zzd();
    final List<String> aen;
    final List<Integer> aeo;
    final List<UserDataType> aep;
    final String aeq;
    final boolean aer;
    private final Set<String> aes;
    private final Set<Integer> aet;
    private final Set<UserDataType> aeu;
    final int mVersionCode;

    NearbyAlertFilter(int i, @Nullable List<String> list, @Nullable List<Integer> list2, @Nullable List<UserDataType> list3, @Nullable String str, boolean z) {
        this.mVersionCode = i;
        this.aeo = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.aep = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.aen = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.aet = zza.zzz(this.aeo);
        this.aeu = zza.zzz(this.aep);
        this.aes = zza.zzz(this.aen);
        this.aeq = str;
        this.aer = z;
    }

    public static NearbyAlertFilter zzm(Collection<String> collection) {
        if (collection != null && !collection.isEmpty()) {
            return new NearbyAlertFilter(0, zza.zzk(collection), null, null, null, false);
        }
        throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace ID to match results with.");
    }

    public static NearbyAlertFilter zzn(Collection<Integer> collection) {
        if (collection != null && !collection.isEmpty()) {
            return new NearbyAlertFilter(0, null, zza.zzk(collection), null, null, false);
        }
        throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace type to match results with.");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NearbyAlertFilter)) {
            return false;
        }
        NearbyAlertFilter nearbyAlertFilter = (NearbyAlertFilter) obj;
        return (this.aeq != null || nearbyAlertFilter.aeq == null) ? this.aet.equals(nearbyAlertFilter.aet) && this.aeu.equals(nearbyAlertFilter.aeu) && this.aes.equals(nearbyAlertFilter.aes) && ((this.aeq == null || this.aeq.equals(nearbyAlertFilter.aeq)) && this.aer == nearbyAlertFilter.zzbnv()) : false;
    }

    public Set<String> getPlaceIds() {
        return this.aes;
    }

    public int hashCode() {
        return zzaa.hashCode(this.aet, this.aeu, this.aes, this.aeq, Boolean.valueOf(this.aer));
    }

    public String toString() {
        zza zzz = zzaa.zzz(this);
        if (!this.aet.isEmpty()) {
            zzz.zzg("types", this.aet);
        }
        if (!this.aes.isEmpty()) {
            zzz.zzg("placeIds", this.aes);
        }
        if (!this.aeu.isEmpty()) {
            zzz.zzg("requestedUserDataTypes", this.aeu);
        }
        if (this.aeq != null) {
            zzz.zzg("chainName", this.aeq);
        }
        zzz.zzg("Beacon required: ", Boolean.valueOf(this.aer));
        return zzz.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public boolean zzbnv() {
        return this.aer;
    }
}
