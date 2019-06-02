package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class PlacePhotoMetadataResult extends AbstractSafeParcelable implements Result {
    public static final Creator<PlacePhotoMetadataResult> CREATOR = new zzh();
    final DataHolder aeI;
    private final PlacePhotoMetadataBuffer aeJ;
    private final Status cc;
    final int mVersionCode;

    PlacePhotoMetadataResult(int i, Status status, DataHolder dataHolder) {
        this.mVersionCode = i;
        this.cc = status;
        this.aeI = dataHolder;
        if (dataHolder == null) {
            this.aeJ = null;
        } else {
            this.aeJ = new PlacePhotoMetadataBuffer(this.aeI);
        }
    }

    public PlacePhotoMetadataResult(Status status, DataHolder dataHolder) {
        this(0, status, dataHolder);
    }

    public PlacePhotoMetadataBuffer getPhotoMetadata() {
        return this.aeJ;
    }

    public Status getStatus() {
        return this.cc;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }
}
