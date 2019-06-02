package com.mapbox.mapboxsdk.annotations;

import android.content.res.Resources;
import android.graphics.PointF;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mapbox.mapboxsdk.C1485R;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMap$OnInfoWindowCloseListener;
import java.lang.ref.WeakReference;

public class InfoWindow {
    private WeakReference<Marker> mBoundMarker;
    private PointF mCoordinates;
    private boolean mIsVisible;
    @LayoutRes
    private int mLayoutRes;
    private WeakReference<MapboxMap> mMapboxMap;
    private float mMarkerHeightOffset;
    private float mMarkerWidthOffset;
    protected WeakReference<View> mView;
    private float mViewWidthOffset;

    InfoWindow(MapView mapView, int i, MapboxMap mapboxMap) {
        this.mLayoutRes = i;
        initialize(LayoutInflater.from(mapView.getContext()).inflate(i, mapView, false), mapboxMap);
    }

    InfoWindow(View view, MapboxMap mapboxMap) {
        initialize(view, mapboxMap);
    }

    private void initialize(View view, MapboxMap mapboxMap) {
        this.mMapboxMap = new WeakReference(mapboxMap);
        this.mIsVisible = false;
        this.mView = new WeakReference(view);
        view.setOnClickListener(new InfoWindow$1(this));
        view.setOnLongClickListener(new InfoWindow$2(this));
    }

    InfoWindow open(MapView mapView, Marker marker, LatLng latLng, int i, int i2) {
        setBoundMarker(marker);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        MapboxMap mapboxMap = (MapboxMap) this.mMapboxMap.get();
        View view = (View) this.mView.get();
        if (!(view == null || mapboxMap == null)) {
            view.measure(0, 0);
            this.mMarkerHeightOffset = (float) ((-view.getMeasuredHeight()) + i2);
            this.mMarkerWidthOffset = (float) (-i);
            this.mCoordinates = mapboxMap.getProjection().toScreenLocation(latLng);
            float measuredWidth = (this.mCoordinates.x - ((float) (view.getMeasuredWidth() / 2))) + ((float) i);
            float measuredHeight = (this.mCoordinates.y - ((float) view.getMeasuredHeight())) + ((float) i2);
            if (view instanceof InfoWindowView) {
                float f;
                float f2;
                Object obj;
                float f3;
                Object obj2;
                float f4;
                Resources resources = mapView.getContext().getResources();
                float measuredWidth2 = measuredWidth + ((float) view.getMeasuredWidth());
                float right = (float) mapView.getRight();
                float left = (float) mapView.getLeft();
                float dimension = resources.getDimension(C1485R.dimen.infowindow_margin);
                float dimension2 = resources.getDimension(C1485R.dimen.infowindow_tipview_width) / 2.0f;
                float measuredWidth3 = ((float) (view.getMeasuredWidth() / 2)) - dimension2;
                if (measuredWidth2 > right) {
                    f = measuredWidth - (measuredWidth2 - right);
                    f2 = measuredWidth3 + ((measuredWidth2 - right) + dimension2);
                    measuredWidth3 = f;
                    f = ((float) view.getMeasuredWidth()) + f;
                    obj = 1;
                } else {
                    f = measuredWidth2;
                    obj = null;
                    f2 = measuredWidth3;
                    measuredWidth3 = measuredWidth;
                }
                if (measuredWidth < left) {
                    measuredWidth3 += left - measuredWidth;
                    f2 -= (left - measuredWidth) + dimension2;
                    measuredWidth = measuredWidth3;
                    f3 = f2;
                    f2 = measuredWidth3;
                    obj2 = 1;
                    f4 = f3;
                } else {
                    f4 = f2;
                    f2 = measuredWidth;
                    measuredWidth = measuredWidth3;
                    obj2 = null;
                }
                if (obj == null || right - f >= dimension) {
                    f3 = f2;
                    f2 = measuredWidth;
                    measuredWidth = f3;
                } else {
                    f2 = measuredWidth - (dimension - (right - f));
                    f4 += (dimension - (right - f)) - dimension2;
                    measuredWidth = f2;
                }
                if (obj2 == null || measuredWidth - left >= dimension) {
                    measuredWidth = f2;
                    f2 = f4;
                } else {
                    f4 -= (dimension - (measuredWidth - left)) - dimension2;
                    measuredWidth = f2 + (dimension - (measuredWidth - left));
                    f2 = f4;
                }
                ((InfoWindowView) view).setTipViewMarginLeft((int) f2);
            }
            view.setX(measuredWidth);
            view.setY(measuredHeight);
            this.mViewWidthOffset = (measuredWidth - this.mCoordinates.x) - ((float) i);
            close();
            mapView.addView(view, layoutParams);
            this.mIsVisible = true;
        }
        return this;
    }

    InfoWindow close() {
        MapboxMap mapboxMap = (MapboxMap) this.mMapboxMap.get();
        if (this.mIsVisible && mapboxMap != null) {
            this.mIsVisible = false;
            View view = (View) this.mView.get();
            if (!(view == null || view.getParent() == null)) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            Marker boundMarker = getBoundMarker();
            MapboxMap$OnInfoWindowCloseListener onInfoWindowCloseListener = mapboxMap.getOnInfoWindowCloseListener();
            if (onInfoWindowCloseListener != null) {
                onInfoWindowCloseListener.onInfoWindowClose(boundMarker);
            }
            setBoundMarker(null);
        }
        return this;
    }

    void adaptDefaultMarker(Marker marker, MapboxMap mapboxMap, MapView mapView) {
        View view = (View) this.mView.get();
        if (view == null) {
            view = LayoutInflater.from(mapView.getContext()).inflate(this.mLayoutRes, mapView, false);
            initialize(view, mapboxMap);
        }
        View view2 = view;
        this.mMapboxMap = new WeakReference(mapboxMap);
        CharSequence title = marker.getTitle();
        TextView textView = (TextView) view2.findViewById(C1485R.id.infowindow_title);
        if (TextUtils.isEmpty(title)) {
            textView.setVisibility(8);
        } else {
            textView.setText(title);
            textView.setVisibility(0);
        }
        title = marker.getSnippet();
        textView = (TextView) view2.findViewById(C1485R.id.infowindow_description);
        if (TextUtils.isEmpty(title)) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(title);
        textView.setVisibility(0);
    }

    InfoWindow setBoundMarker(Marker marker) {
        this.mBoundMarker = new WeakReference(marker);
        return this;
    }

    Marker getBoundMarker() {
        if (this.mBoundMarker == null) {
            return null;
        }
        return (Marker) this.mBoundMarker.get();
    }

    public void update() {
        MapboxMap mapboxMap = (MapboxMap) this.mMapboxMap.get();
        Marker marker = (Marker) this.mBoundMarker.get();
        View view = (View) this.mView.get();
        if (mapboxMap != null && marker != null && view != null) {
            this.mCoordinates = mapboxMap.getProjection().toScreenLocation(marker.getPosition());
            if (view instanceof InfoWindowView) {
                view.setX((this.mCoordinates.x + this.mViewWidthOffset) - this.mMarkerWidthOffset);
            } else {
                view.setX((this.mCoordinates.x - ((float) (view.getMeasuredWidth() / 2))) - this.mMarkerWidthOffset);
            }
            view.setY(this.mCoordinates.y + this.mMarkerHeightOffset);
        }
    }

    public View getView() {
        return this.mView != null ? (View) this.mView.get() : null;
    }

    boolean isVisible() {
        return this.mIsVisible;
    }
}
