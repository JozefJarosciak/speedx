package com.mapbox.mapboxsdk.maps;

import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.geometry.LatLng;

public interface MapboxMap$OnMapClickListener {
    void onMapClick(@NonNull LatLng latLng);
}
