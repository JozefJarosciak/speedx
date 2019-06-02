package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlacePicker$IntentBuilder extends zza {
    public PlacePicker$IntentBuilder() {
        super("com.google.android.gms.location.places.ui.PICK_PLACE");
        this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        return super.build(activity);
    }

    public PlacePicker$IntentBuilder setLatLngBounds(LatLngBounds latLngBounds) {
        zzab.zzaa(latLngBounds);
        zzc.zza((SafeParcelable) latLngBounds, this.mIntent, "latlng_bounds");
        return this;
    }
}
