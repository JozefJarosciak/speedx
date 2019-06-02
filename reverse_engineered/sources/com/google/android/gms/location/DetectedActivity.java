package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Comparator;

public class DetectedActivity extends AbstractSafeParcelable {
    public static final DetectedActivityCreator CREATOR = new DetectedActivityCreator();
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    public static final Comparator<DetectedActivity> acC = new C34191();
    public static final int[] acD = new int[]{9, 10};
    public static final int[] acE = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14};
    int acF;
    int acG;
    private final int mVersionCode;

    /* renamed from: com.google.android.gms.location.DetectedActivity$1 */
    class C34191 implements Comparator<DetectedActivity> {
        C34191() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return zza((DetectedActivity) obj, (DetectedActivity) obj2);
        }

        public int zza(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
            return compareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
        }
    }

    public DetectedActivity(int i, int i2) {
        this.mVersionCode = 1;
        this.acF = i;
        this.acG = i2;
    }

    public DetectedActivity(int i, int i2, int i3) {
        this.mVersionCode = i;
        this.acF = i2;
        this.acG = i3;
    }

    private int zzst(int i) {
        return i > 15 ? 4 : i;
    }

    public static String zzsu(int i) {
        switch (i) {
            case 0:
                return "IN_VEHICLE";
            case 1:
                return "ON_BICYCLE";
            case 2:
                return "ON_FOOT";
            case 3:
                return "STILL";
            case 4:
                return "UNKNOWN";
            case 5:
                return "TILTING";
            case 7:
                return "WALKING";
            case 8:
                return "RUNNING";
            default:
                return Integer.toString(i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.acF == detectedActivity.acF && this.acG == detectedActivity.acG;
    }

    public int getConfidence() {
        return this.acG;
    }

    public int getType() {
        return zzst(this.acF);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.acF), Integer.valueOf(this.acG));
    }

    public String toString() {
        String valueOf = String.valueOf(zzsu(getType()));
        return new StringBuilder(String.valueOf(valueOf).length() + 48).append("DetectedActivity [type=").append(valueOf).append(", confidence=").append(this.acG).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        DetectedActivityCreator.zza(this, parcel, i);
    }
}
