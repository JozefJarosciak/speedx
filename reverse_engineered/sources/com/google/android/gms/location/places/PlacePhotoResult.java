package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public class PlacePhotoResult extends AbstractSafeParcelable implements Result {
    public static final Creator<PlacePhotoResult> CREATOR = new zzi();
    final BitmapTeleporter aeK;
    private final Status cc;
    private final Bitmap mBitmap;
    final int mVersionCode;

    PlacePhotoResult(int i, Status status, BitmapTeleporter bitmapTeleporter) {
        this.mVersionCode = i;
        this.cc = status;
        this.aeK = bitmapTeleporter;
        if (this.aeK != null) {
            this.mBitmap = bitmapTeleporter.zzaqz();
        } else {
            this.mBitmap = null;
        }
    }

    public PlacePhotoResult(Status status, @Nullable BitmapTeleporter bitmapTeleporter) {
        this(0, status, bitmapTeleporter);
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public Status getStatus() {
        return this.cc;
    }

    public String toString() {
        return zzaa.zzz(this).zzg("status", this.cc).zzg("bitmap", this.mBitmap).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}
