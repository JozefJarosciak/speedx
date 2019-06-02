package com.beastbikes.android.utils;

import android.content.Context;
import com.beastbikes.android.BeastBikes;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds.Builder;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: MapBoxManagerUtils */
/* renamed from: com.beastbikes.android.utils.m */
public class C2565m {
    /* renamed from: a */
    private MapView f12046a;

    /* renamed from: a */
    public MapView m12873a(Context context) {
        MapboxMapOptions mapboxMapOptions = new MapboxMapOptions();
        mapboxMapOptions.accessToken(BeastBikes.getMapBoxAccessToken());
        mapboxMapOptions.attributionEnabled(false);
        mapboxMapOptions.logoEnabled(false);
        mapboxMapOptions.zoomControlsEnabled(false);
        mapboxMapOptions.rotateGesturesEnabled(false);
        mapboxMapOptions.zoomGesturesEnabled(true);
        mapboxMapOptions.compassEnabled(false);
        mapboxMapOptions.scrollGesturesEnabled(true);
        mapboxMapOptions.styleUrl(Style.DARK);
        this.f12046a = new MapView(context, mapboxMapOptions);
        return this.f12046a;
    }

    /* renamed from: a */
    public double m12872a(MapView mapView) {
        if (mapView == null) {
            return 11.0d;
        }
        try {
            Method declaredMethod = mapView.getClass().getDeclaredMethod("getZoom", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Double) declaredMethod.invoke(mapView, new Object[0])).doubleValue();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return 11.0d;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return 11.0d;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return 11.0d;
        }
    }

    /* renamed from: a */
    public void m12874a(MapboxMap mapboxMap, List<LatLng> list) {
        if (mapboxMap != null && list != null && list.size() >= 2) {
            Builder builder = new Builder();
            for (int i = 0; i < list.size(); i++) {
                builder.include((LatLng) list.get(i));
            }
            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 20), 1000);
        }
    }
}
