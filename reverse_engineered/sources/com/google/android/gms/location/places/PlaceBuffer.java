package com.google.android.gms.location.places;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzr;

public class PlaceBuffer extends AbstractDataBuffer<Place> implements Result {
    private final String aeC;
    private final Status cc;
    private final Context mContext;

    public PlaceBuffer(DataHolder dataHolder, Context context) {
        super(dataHolder);
        this.mContext = context;
        this.cc = PlacesStatusCodes.zzgz(dataHolder.getStatusCode());
        if (dataHolder == null || dataHolder.zzaqy() == null) {
            this.aeC = null;
        } else {
            this.aeC = dataHolder.zzaqy().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
        }
    }

    public Place get(int i) {
        return new zzr(this.tk, i, this.mContext);
    }

    @Nullable
    public CharSequence getAttributions() {
        return this.aeC;
    }

    public Status getStatus() {
        return this.cc;
    }
}
