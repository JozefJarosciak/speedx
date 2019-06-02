package com.baidu.mapapi.map;

import android.view.View;
import com.baidu.mapapi.model.LatLng;

public class InfoWindow {
    /* renamed from: a */
    BitmapDescriptor f2954a;
    /* renamed from: b */
    View f2955b;
    /* renamed from: c */
    LatLng f2956c;
    /* renamed from: d */
    OnInfoWindowClickListener f2957d;
    /* renamed from: e */
    int f2958e;

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick();
    }

    public InfoWindow(View view, LatLng latLng, int i) {
        if (view == null || latLng == null) {
            throw new IllegalArgumentException("view and position can not be null");
        }
        this.f2955b = view;
        this.f2956c = latLng;
        this.f2958e = i;
    }

    public InfoWindow(BitmapDescriptor bitmapDescriptor, LatLng latLng, int i, OnInfoWindowClickListener onInfoWindowClickListener) {
        if (bitmapDescriptor == null || latLng == null) {
            throw new IllegalArgumentException("bitmapDescriptor and position can not be null");
        }
        this.f2954a = bitmapDescriptor;
        this.f2956c = latLng;
        this.f2957d = onInfoWindowClickListener;
        this.f2958e = i;
    }
}
