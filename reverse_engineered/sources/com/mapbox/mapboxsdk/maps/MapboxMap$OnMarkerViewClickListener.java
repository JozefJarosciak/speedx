package com.mapbox.mapboxsdk.maps;

import android.support.annotation.NonNull;
import android.view.View;
import com.mapbox.mapboxsdk.annotations.Marker;

public interface MapboxMap$OnMarkerViewClickListener {
    boolean onMarkerClick(@NonNull Marker marker, @NonNull View view, @NonNull MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter);
}
