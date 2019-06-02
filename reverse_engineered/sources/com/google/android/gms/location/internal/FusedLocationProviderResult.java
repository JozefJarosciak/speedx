package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class FusedLocationProviderResult extends AbstractSafeParcelable implements Result {
    public static final Creator<FusedLocationProviderResult> CREATOR = new zze();
    public static final FusedLocationProviderResult adH = new FusedLocationProviderResult(Status.sg);
    private final Status cc;
    private final int mVersionCode;

    FusedLocationProviderResult(int i, Status status) {
        this.mVersionCode = i;
        this.cc = status;
    }

    public FusedLocationProviderResult(Status status) {
        this(1, status);
    }

    public Status getStatus() {
        return this.cc;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }
}
