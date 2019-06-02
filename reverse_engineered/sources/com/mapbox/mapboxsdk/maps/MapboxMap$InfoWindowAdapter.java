package com.mapbox.mapboxsdk.maps;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mapbox.mapboxsdk.annotations.Marker;

public interface MapboxMap$InfoWindowAdapter {
    @Nullable
    View getInfoWindow(@NonNull Marker marker);
}
