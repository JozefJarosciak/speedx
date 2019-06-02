package com.google.android.gms.location.places.ui;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlacePicker extends zza {
    public static final int RESULT_ERROR = 2;

    private PlacePicker() {
    }

    @Deprecated
    public static String getAttributions(Intent intent) {
        return intent.getStringExtra("third_party_attributions");
    }

    public static LatLngBounds getLatLngBounds(Intent intent) {
        return (LatLngBounds) zzc.zza(intent, "final_latlng_bounds", LatLngBounds.CREATOR);
    }

    public static Place getPlace(Context context, Intent intent) {
        return zza.getPlace(context, intent);
    }

    @Deprecated
    public static Place getPlace(Intent intent, Context context) {
        return zza.getPlace(context, intent);
    }
}
