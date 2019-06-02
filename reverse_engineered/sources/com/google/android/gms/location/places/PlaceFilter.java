package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter extends zza implements SafeParcelable {
    public static final zzg CREATOR = new zzg();
    private static final PlaceFilter aeD = new PlaceFilter();
    final boolean aeE;
    final List<String> aen;
    final List<Integer> aeo;
    final List<UserDataType> aep;
    private final Set<String> aes;
    private final Set<Integer> aet;
    private final Set<UserDataType> aeu;
    final int mVersionCode;

    @Deprecated
    public static final class zza {
        private boolean aeE;
        private Collection<Integer> aeF;
        private Collection<UserDataType> aeG;
        private String[] aeH;

        private zza() {
            this.aeF = null;
            this.aeE = false;
            this.aeG = null;
            this.aeH = null;
        }

        public PlaceFilter zzbod() {
            return new PlaceFilter(null, false, null, null);
        }
    }

    public PlaceFilter() {
        this(false, null);
    }

    PlaceFilter(int i, @Nullable List<Integer> list, boolean z, @Nullable List<String> list2, @Nullable List<UserDataType> list3) {
        this.mVersionCode = i;
        this.aeo = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.aeE = z;
        this.aep = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.aen = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.aet = zzz(this.aeo);
        this.aeu = zzz(this.aep);
        this.aes = zzz(this.aen);
    }

    public PlaceFilter(@Nullable Collection<Integer> collection, boolean z, @Nullable Collection<String> collection2, @Nullable Collection<UserDataType> collection3) {
        this(0, zzk(collection), z, zzk(collection2), zzk(collection3));
    }

    public PlaceFilter(boolean z, @Nullable Collection<String> collection) {
        this(null, z, collection, null);
    }

    @Deprecated
    public static PlaceFilter zzboc() {
        return new zza().zzbod();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) obj;
        return this.aet.equals(placeFilter.aet) && this.aeE == placeFilter.aeE && this.aeu.equals(placeFilter.aeu) && this.aes.equals(placeFilter.aes);
    }

    public Set<String> getPlaceIds() {
        return this.aes;
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{this.aet, Boolean.valueOf(this.aeE), this.aeu, this.aes});
    }

    public boolean isRestrictedToPlacesOpenNow() {
        return this.aeE;
    }

    public String toString() {
        com.google.android.gms.common.internal.zzaa.zza zzz = zzaa.zzz(this);
        if (!this.aet.isEmpty()) {
            zzz.zzg("types", this.aet);
        }
        zzz.zzg("requireOpenNow", Boolean.valueOf(this.aeE));
        if (!this.aes.isEmpty()) {
            zzz.zzg("placeIds", this.aes);
        }
        if (!this.aeu.isEmpty()) {
            zzz.zzg("requestedUserDataTypes", this.aeu);
        }
        return zzz.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public Set<Integer> zzbob() {
        return this.aet;
    }
}
