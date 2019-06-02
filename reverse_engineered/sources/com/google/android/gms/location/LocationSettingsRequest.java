package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public static final Creator<LocationSettingsRequest> CREATOR = new zzg();
    private final List<LocationRequest> PJ;
    private final boolean adk;
    private final boolean adl;
    private final int mVersionCode;

    public static final class Builder {
        private boolean adk = false;
        private boolean adl = false;
        private final ArrayList<LocationRequest> adm = new ArrayList();

        public Builder addAllLocationRequests(Collection<LocationRequest> collection) {
            this.adm.addAll(collection);
            return this;
        }

        public Builder addLocationRequest(LocationRequest locationRequest) {
            this.adm.add(locationRequest);
            return this;
        }

        public LocationSettingsRequest build() {
            return new LocationSettingsRequest(this.adm, this.adk, this.adl);
        }

        public Builder setAlwaysShow(boolean z) {
            this.adk = z;
            return this;
        }

        public Builder setNeedBle(boolean z) {
            this.adl = z;
            return this;
        }
    }

    LocationSettingsRequest(int i, List<LocationRequest> list, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.PJ = list;
        this.adk = z;
        this.adl = z2;
    }

    private LocationSettingsRequest(List<LocationRequest> list, boolean z, boolean z2) {
        this(3, (List) list, z, z2);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public List<LocationRequest> zzbew() {
        return Collections.unmodifiableList(this.PJ);
    }

    public boolean zzbnh() {
        return this.adk;
    }

    public boolean zzbni() {
        return this.adl;
    }
}
