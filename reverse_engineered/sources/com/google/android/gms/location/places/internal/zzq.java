package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;

public class zzq extends zzt implements PlacePhotoMetadata {
    private final String afX = getString("photo_fife_url");

    public zzq(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public /* synthetic */ Object freeze() {
        return zzboy();
    }

    public CharSequence getAttributions() {
        return zzao("photo_attributions", null);
    }

    public int getMaxHeight() {
        return zzx("photo_max_height", 0);
    }

    public int getMaxWidth() {
        return zzx("photo_max_width", 0);
    }

    public PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    public PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        return ((PlacePhotoMetadata) freeze()).getScaledPhoto(googleApiClient, i, i2);
    }

    public PlacePhotoMetadata zzboy() {
        return new zzp(this.afX, getMaxWidth(), getMaxHeight(), getAttributions(), this.vK);
    }
}
