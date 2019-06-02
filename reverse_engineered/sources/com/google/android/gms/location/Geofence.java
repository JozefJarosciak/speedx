package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.location.internal.ParcelableGeofence;

public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    public static final class Builder {
        private int acH = 0;
        private long acI = Long.MIN_VALUE;
        private short acJ = (short) -1;
        private double acK;
        private double acL;
        private float acM;
        private int acN = 0;
        private int acO = -1;
        private String zzbvu = null;

        public Geofence build() {
            if (this.zzbvu == null) {
                throw new IllegalArgumentException("Request ID not set.");
            } else if (this.acH == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            } else if ((this.acH & 4) != 0 && this.acO < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            } else if (this.acI == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            } else if (this.acJ == (short) -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            } else if (this.acN >= 0) {
                return new ParcelableGeofence(this.zzbvu, this.acH, (short) 1, this.acK, this.acL, this.acM, this.acI, this.acN, this.acO);
            } else {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
        }

        public Builder setCircularRegion(double d, double d2, float f) {
            this.acJ = (short) 1;
            this.acK = d;
            this.acL = d2;
            this.acM = f;
            return this;
        }

        public Builder setExpirationDuration(long j) {
            if (j < 0) {
                this.acI = -1;
            } else {
                this.acI = SystemClock.elapsedRealtime() + j;
            }
            return this;
        }

        public Builder setLoiteringDelay(int i) {
            this.acO = i;
            return this;
        }

        public Builder setNotificationResponsiveness(int i) {
            this.acN = i;
            return this;
        }

        public Builder setRequestId(String str) {
            this.zzbvu = str;
            return this;
        }

        public Builder setTransitionTypes(int i) {
            this.acH = i;
            return this;
        }
    }

    String getRequestId();
}
