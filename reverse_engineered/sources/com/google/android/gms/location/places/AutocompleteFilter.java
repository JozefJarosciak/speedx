package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AutocompleteFilter extends AbstractSafeParcelable {
    public static final zzc CREATOR = new zzc();
    public static final int TYPE_FILTER_ADDRESS = 2;
    public static final int TYPE_FILTER_CITIES = 5;
    public static final int TYPE_FILTER_ESTABLISHMENT = 34;
    public static final int TYPE_FILTER_GEOCODE = 1007;
    public static final int TYPE_FILTER_NONE = 0;
    public static final int TYPE_FILTER_REGIONS = 4;
    final boolean aek;
    final List<Integer> ael;
    final int aem;
    final int mVersionCode;

    public static final class Builder {
        private boolean aek = false;
        private int aem = 0;

        public AutocompleteFilter build() {
            return new AutocompleteFilter(1, false, Arrays.asList(new Integer[]{Integer.valueOf(this.aem)}));
        }

        public Builder setTypeFilter(int i) {
            this.aem = i;
            return this;
        }
    }

    AutocompleteFilter(int i, boolean z, List<Integer> list) {
        boolean z2 = true;
        this.mVersionCode = i;
        this.ael = list;
        this.aem = zzl(list);
        if (this.mVersionCode < 1) {
            if (z) {
                z2 = false;
            }
            this.aek = z2;
            return;
        }
        this.aek = z;
    }

    private static int zzl(@Nullable Collection<Integer> collection) {
        return (collection == null || collection.isEmpty()) ? 0 : ((Integer) collection.iterator().next()).intValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompleteFilter)) {
            return false;
        }
        return this.aem == this.aem && this.aek == ((AutocompleteFilter) obj).aek;
    }

    public int getTypeFilter() {
        return this.aem;
    }

    public int hashCode() {
        return zzaa.hashCode(Boolean.valueOf(this.aek), Integer.valueOf(this.aem));
    }

    public String toString() {
        return zzaa.zzz(this).zzg("includeQueryPredictions", Boolean.valueOf(this.aek)).zzg("typeFilter", Integer.valueOf(this.aem)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }
}
