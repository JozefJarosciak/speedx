package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.avos.avoscloud.AVException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import java.util.Collections;
import java.util.List;

public class ActivityRecognitionResult extends AbstractSafeParcelable {
    public static final ActivityRecognitionResultCreator CREATOR = new ActivityRecognitionResultCreator();
    long acA;
    int acB;
    List<DetectedActivity> acy;
    long acz;
    Bundle extras;
    private final int mVersionCode;

    public ActivityRecognitionResult(int i, List<DetectedActivity> list, long j, long j2, int i2, Bundle bundle) {
        this.mVersionCode = i;
        this.acy = list;
        this.acz = j;
        this.acA = j2;
        this.acB = i2;
        this.extras = bundle;
    }

    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2) {
        this(detectedActivity, j, j2, 0, null);
    }

    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2, int i, Bundle bundle) {
        this(Collections.singletonList(detectedActivity), j, j2, i, bundle);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2) {
        this((List) list, j, j2, 0, null);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2, int i, Bundle bundle) {
        boolean z = true;
        boolean z2 = list != null && list.size() > 0;
        zzab.zzb(z2, (Object) "Must have at least 1 detected activity");
        if (j <= 0 || j2 <= 0) {
            z = false;
        }
        zzab.zzb(z, (Object) "Must set times");
        this.mVersionCode = 2;
        this.acy = list;
        this.acz = j;
        this.acA = j2;
        this.acB = i;
        this.extras = bundle;
    }

    public static ActivityRecognitionResult extractResult(Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        Object obj = intent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
        return obj instanceof byte[] ? (ActivityRecognitionResult) zzc.zza((byte[]) obj, CREATOR) : obj instanceof ActivityRecognitionResult ? (ActivityRecognitionResult) obj : null;
    }

    public static boolean hasResult(Intent intent) {
        return intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
    }

    private static boolean zzc(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return true;
        }
        if ((bundle == null && bundle2 != null) || (bundle != null && bundle2 == null)) {
            return false;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (!bundle2.containsKey(str)) {
                return false;
            }
            if (bundle.get(str) == null) {
                if (bundle2.get(str) != null) {
                    return false;
                }
            } else if (bundle.get(str) instanceof Bundle) {
                if (!zzc(bundle.getBundle(str), bundle2.getBundle(str))) {
                    return false;
                }
            } else if (!bundle.get(str).equals(bundle2.get(str))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
        return this.acz == activityRecognitionResult.acz && this.acA == activityRecognitionResult.acA && this.acB == activityRecognitionResult.acB && zzaa.equal(this.acy, activityRecognitionResult.acy) && zzc(this.extras, activityRecognitionResult.extras);
    }

    public int getActivityConfidence(int i) {
        for (DetectedActivity detectedActivity : this.acy) {
            if (detectedActivity.getType() == i) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.acA;
    }

    public DetectedActivity getMostProbableActivity() {
        return (DetectedActivity) this.acy.get(0);
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.acy;
    }

    public long getTime() {
        return this.acz;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(Long.valueOf(this.acz), Long.valueOf(this.acA), Integer.valueOf(this.acB), this.acy, this.extras);
    }

    public String toString() {
        String valueOf = String.valueOf(this.acy);
        long j = this.acz;
        return new StringBuilder(String.valueOf(valueOf).length() + AVException.TIMEOUT).append("ActivityRecognitionResult [probableActivities=").append(valueOf).append(", timeMillis=").append(j).append(", elapsedRealtimeMillis=").append(this.acA).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        ActivityRecognitionResultCreator.zza(this, parcel, i);
    }
}
