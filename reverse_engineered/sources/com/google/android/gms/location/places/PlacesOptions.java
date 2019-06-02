package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public final class PlacesOptions implements Optional {
    @Nullable
    public final String afb;
    @Nullable
    public final String afc;
    public final int afd;

    public static class Builder {
        private int afd = 0;

        public PlacesOptions build() {
            return new PlacesOptions();
        }
    }

    private PlacesOptions(Builder builder) {
        this.afb = null;
        this.afc = null;
        this.afd = 0;
    }
}
