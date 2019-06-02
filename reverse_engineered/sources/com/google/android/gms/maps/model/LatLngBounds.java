package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;

public final class LatLngBounds implements SafeParcelable {
    public static final zzd CREATOR = new zzd();
    private final int mVersionCode;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double aiA = Double.NEGATIVE_INFINITY;
        private double aiB = Double.NaN;
        private double aiC = Double.NaN;
        private double aiz = Double.POSITIVE_INFINITY;

        private boolean zzi(double d) {
            boolean z = false;
            if (this.aiB <= this.aiC) {
                return this.aiB <= d && d <= this.aiC;
            } else {
                if (this.aiB <= d || d <= this.aiC) {
                    z = true;
                }
                return z;
            }
        }

        public LatLngBounds build() {
            zzab.zza(!Double.isNaN(this.aiB), (Object) "no included points");
            return new LatLngBounds(new LatLng(this.aiz, this.aiB), new LatLng(this.aiA, this.aiC));
        }

        public Builder include(LatLng latLng) {
            this.aiz = Math.min(this.aiz, latLng.latitude);
            this.aiA = Math.max(this.aiA, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.aiB)) {
                this.aiB = d;
                this.aiC = d;
            } else if (!zzi(d)) {
                if (LatLngBounds.zzb(this.aiB, d) < LatLngBounds.zzc(this.aiC, d)) {
                    this.aiB = d;
                } else {
                    this.aiC = d;
                }
            }
            return this;
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        zzab.zzb((Object) latLng, (Object) "null southwest");
        zzab.zzb((Object) latLng2, (Object) "null northeast");
        zzab.zzb(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        this.mVersionCode = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    public static Builder builder() {
        return new Builder();
    }

    private static double zzb(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private static double zzc(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private boolean zzh(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean zzi(double d) {
        boolean z = false;
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= d && d <= this.northeast.longitude;
        } else {
            if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
                z = true;
            }
            return z;
        }
    }

    public boolean contains(LatLng latLng) {
        return zzh(latLng.latitude) && zzi(latLng.longitude);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public LatLng getCenter() {
        double d = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        return new LatLng(d, d3 <= d2 ? (d2 + d3) / 2.0d : ((d2 + 360.0d) + d3) / 2.0d);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(this.southwest, this.northeast);
    }

    public LatLngBounds including(LatLng latLng) {
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng.longitude;
        if (zzi(d3)) {
            d3 = d2;
            d2 = d;
        } else if (zzb(d2, d3) < zzc(d, d3)) {
            d2 = d;
        } else {
            double d4 = d2;
            d2 = d3;
            d3 = d4;
        }
        return new LatLngBounds(new LatLng(min, d3), new LatLng(max, d2));
    }

    public String toString() {
        return zzaa.zzz(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
