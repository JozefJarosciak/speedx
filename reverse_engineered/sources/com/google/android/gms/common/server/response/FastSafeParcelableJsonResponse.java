package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    public final int describeContents() {
        return 0;
    }

    public Object zzht(String str) {
        return null;
    }

    public boolean zzhu(String str) {
        return false;
    }
}
