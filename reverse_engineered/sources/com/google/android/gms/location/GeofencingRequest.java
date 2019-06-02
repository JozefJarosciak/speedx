package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.ArrayList;
import java.util.List;

public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Creator<GeofencingRequest> CREATOR = new zzb();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    private final List<ParcelableGeofence> acS;
    private final int acT;
    private final int mVersionCode;

    public static final class Builder {
        private final List<ParcelableGeofence> acS = new ArrayList();
        private int acT = 5;

        public static int zzsv(int i) {
            return i & 7;
        }

        public Builder addGeofence(Geofence geofence) {
            zzab.zzb((Object) geofence, (Object) "geofence can't be null.");
            zzab.zzb(geofence instanceof ParcelableGeofence, (Object) "Geofence must be created using Geofence.Builder.");
            this.acS.add((ParcelableGeofence) geofence);
            return this;
        }

        public Builder addGeofences(List<Geofence> list) {
            if (!(list == null || list.isEmpty())) {
                for (Geofence geofence : list) {
                    if (geofence != null) {
                        addGeofence(geofence);
                    }
                }
            }
            return this;
        }

        public GeofencingRequest build() {
            zzab.zzb(!this.acS.isEmpty(), (Object) "No geofence has been added to this request.");
            return new GeofencingRequest(this.acS, this.acT);
        }

        public Builder setInitialTrigger(int i) {
            this.acT = zzsv(i);
            return this;
        }
    }

    GeofencingRequest(int i, List<ParcelableGeofence> list, int i2) {
        this.mVersionCode = i;
        this.acS = list;
        this.acT = i2;
    }

    private GeofencingRequest(List<ParcelableGeofence> list, int i) {
        this(1, (List) list, i);
    }

    public List<Geofence> getGeofences() {
        List<Geofence> arrayList = new ArrayList();
        arrayList.addAll(this.acS);
        return arrayList;
    }

    public int getInitialTrigger() {
        return this.acT;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public List<ParcelableGeofence> zzbnf() {
        return this.acS;
    }
}
