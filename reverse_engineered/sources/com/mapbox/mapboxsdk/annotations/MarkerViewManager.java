package com.mapbox.mapboxsdk.annotations;

import android.graphics.PointF;
import android.graphics.RectF;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMap$MarkerViewAdapter;
import com.mapbox.mapboxsdk.maps.MapboxMap$OnMarkerViewClickListener;
import com.mapbox.mapboxsdk.utils.AnimatorUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MarkerViewManager {
    private MarkerViewManager$ImageMarkerViewAdapter defaultMarkerViewAdapter;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private List<MapboxMap$MarkerViewAdapter> markerViewAdapters = new ArrayList();
    private Map<MarkerView, View> markerViewMap;
    private MapboxMap$OnMarkerViewClickListener onMarkerViewClickListener;
    private long viewMarkerBoundsUpdateTime;

    public MarkerViewManager(@NonNull MapboxMap mapboxMap, @NonNull MapView mapView) {
        this.mapboxMap = mapboxMap;
        this.mapView = mapView;
        this.markerViewMap = new HashMap();
        this.defaultMarkerViewAdapter = new MarkerViewManager$ImageMarkerViewAdapter(mapView.getContext());
        this.markerViewAdapters.add(this.defaultMarkerViewAdapter);
    }

    public void animateRotation(@NonNull MarkerView markerView, float f) {
        View view = (View) this.markerViewMap.get(markerView);
        if (view != null) {
            AnimatorUtils.rotate(view, f);
        }
    }

    public void animateRotationBy(@NonNull MarkerView markerView, float f) {
        View view = (View) this.markerViewMap.get(markerView);
        if (view != null) {
            AnimatorUtils.rotateBy(view, f);
        }
    }

    public void animateAlpha(@NonNull MarkerView markerView, float f) {
        View view = (View) this.markerViewMap.get(markerView);
        if (view != null) {
            AnimatorUtils.alpha(view, f);
        }
    }

    public void animateVisible(@NonNull MarkerView markerView, boolean z) {
        View view = (View) this.markerViewMap.get(markerView);
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    public void update() {
        for (MarkerView markerView : this.markerViewMap.keySet()) {
            View view = (View) this.markerViewMap.get(markerView);
            if (view != null) {
                PointF toScreenLocation = this.mapboxMap.getProjection().toScreenLocation(markerView.getPosition());
                if (markerView.getOffsetX() == -1.0f && markerView.getWidth() == 0.0f) {
                    view.measure(0, 0);
                    if (view.getMeasuredWidth() != 0) {
                        markerView.setWidth((float) view.getMeasuredWidth());
                        markerView.setHeight((float) view.getMeasuredHeight());
                    }
                }
                if (markerView.getWidth() != 0.0f) {
                    markerView.setOffset((float) ((int) (markerView.getAnchorU() * markerView.getWidth())), (float) ((int) (markerView.getAnchorV() * markerView.getHeight())));
                }
                view.setX(toScreenLocation.x - markerView.getOffsetX());
                view.setY(toScreenLocation.y - markerView.getOffsetY());
                if (markerView.isVisible() && view.getVisibility() == 8) {
                    animateVisible(markerView, true);
                }
            }
        }
    }

    public void setTilt(float f) {
        for (MarkerView markerView : this.markerViewMap.keySet()) {
            if (markerView.isFlat()) {
                View view = (View) this.markerViewMap.get(markerView);
                if (view != null) {
                    markerView.setTilt(f);
                    view.setRotationX(f);
                }
            }
        }
    }

    public void updateIcon(@NonNull MarkerView markerView) {
        View view = (View) this.markerViewMap.get(markerView);
        if (view != null && (view instanceof ImageView)) {
            ((ImageView) view).setImageBitmap(markerView.getIcon().getBitmap());
        }
    }

    public void deselect(@NonNull MarkerView markerView) {
        deselect(markerView, true);
    }

    public void deselect(@NonNull MarkerView markerView, boolean z) {
        View view = (View) this.markerViewMap.get(markerView);
        if (view != null) {
            for (MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter : this.markerViewAdapters) {
                if (mapboxMap$MarkerViewAdapter.getMarkerClass().equals(markerView.getClass())) {
                    mapboxMap$MarkerViewAdapter.onDeselect(markerView, view);
                }
            }
            if (z) {
                this.mapboxMap.deselectMarker(markerView);
            }
            markerView.setSelected(false);
        }
    }

    public void select(@NonNull MarkerView markerView) {
        select(markerView, true);
    }

    public void select(@NonNull MarkerView markerView, boolean z) {
        View view = (View) this.markerViewMap.get(markerView);
        for (MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter : this.markerViewAdapters) {
            if (mapboxMap$MarkerViewAdapter.getMarkerClass().equals(markerView.getClass())) {
                select(markerView, view, mapboxMap$MarkerViewAdapter, z);
            }
        }
    }

    public void select(@NonNull MarkerView markerView, View view, MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter) {
        select(markerView, view, mapboxMap$MarkerViewAdapter, true);
    }

    public void select(@NonNull MarkerView markerView, View view, MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter, boolean z) {
        if (view != null) {
            if (mapboxMap$MarkerViewAdapter.onSelect(markerView, view, false) && z) {
                this.mapboxMap.selectMarker(markerView);
            }
            markerView.setSelected(true);
            view.bringToFront();
        }
    }

    @Nullable
    public View getView(MarkerView markerView) {
        return (View) this.markerViewMap.get(markerView);
    }

    @Nullable
    public MapboxMap$MarkerViewAdapter getViewAdapter(MarkerView markerView) {
        MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter = null;
        for (MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter2 : this.markerViewAdapters) {
            MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter22;
            if (!mapboxMap$MarkerViewAdapter22.getMarkerClass().equals(markerView.getClass())) {
                mapboxMap$MarkerViewAdapter22 = mapboxMap$MarkerViewAdapter;
            }
            mapboxMap$MarkerViewAdapter = mapboxMap$MarkerViewAdapter22;
        }
        return mapboxMap$MarkerViewAdapter;
    }

    public void removeMarkerView(MarkerView markerView) {
        View view = (View) this.markerViewMap.get(markerView);
        if (!(view == null || markerView == null)) {
            for (MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter : this.markerViewAdapters) {
                if (mapboxMap$MarkerViewAdapter.getMarkerClass().equals(markerView.getClass()) && mapboxMap$MarkerViewAdapter.prepareViewForReuse(markerView, view)) {
                    markerView.setOffset(-1.0f, -1.0f);
                    mapboxMap$MarkerViewAdapter.releaseView(view);
                }
            }
        }
        markerView.setMapboxMap(null);
        this.markerViewMap.remove(markerView);
    }

    public void addMarkerViewAdapter(MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter) {
        if (mapboxMap$MarkerViewAdapter.getMarkerClass().equals(MarkerView.class)) {
            throw new RuntimeException("Providing a custom MarkerViewAdapter requires subclassing MarkerView");
        } else if (!this.markerViewAdapters.contains(mapboxMap$MarkerViewAdapter)) {
            this.markerViewAdapters.add(mapboxMap$MarkerViewAdapter);
            invalidateViewMarkersInVisibleRegion();
        }
    }

    public List<MapboxMap$MarkerViewAdapter> getMarkerViewAdapters() {
        return this.markerViewAdapters;
    }

    public void setOnMarkerViewClickListener(@Nullable MapboxMap$OnMarkerViewClickListener mapboxMap$OnMarkerViewClickListener) {
        this.onMarkerViewClickListener = mapboxMap$OnMarkerViewClickListener;
    }

    public void scheduleViewMarkerInvalidation() {
        if (!this.markerViewAdapters.isEmpty()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime >= this.viewMarkerBoundsUpdateTime) {
                invalidateViewMarkersInVisibleRegion();
                this.viewMarkerBoundsUpdateTime = elapsedRealtime + 250;
            }
        }
    }

    public void invalidateViewMarkersInVisibleRegion() {
        List<MarkerView> markerViewsInRect = this.mapView.getMarkerViewsInRect(new RectF(0.0f, 0.0f, (float) this.mapView.getWidth(), (float) this.mapView.getHeight()));
        Iterator it = this.markerViewMap.keySet().iterator();
        while (it.hasNext()) {
            MarkerView markerView = (MarkerView) it.next();
            if (!markerViewsInRect.contains(markerView)) {
                View view = (View) this.markerViewMap.get(markerView);
                for (MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter : this.markerViewAdapters) {
                    if (mapboxMap$MarkerViewAdapter.getMarkerClass().equals(markerView.getClass())) {
                        mapboxMap$MarkerViewAdapter.prepareViewForReuse(markerView, view);
                        mapboxMap$MarkerViewAdapter.releaseView(view);
                        markerView.setMapboxMap(null);
                        it.remove();
                    }
                }
            }
        }
        for (MarkerView markerView2 : markerViewsInRect) {
            if (!this.markerViewMap.containsKey(markerView2)) {
                for (MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter2 : this.markerViewAdapters) {
                    if (mapboxMap$MarkerViewAdapter2.getMarkerClass().equals(markerView2.getClass())) {
                        View view2 = (View) mapboxMap$MarkerViewAdapter2.getViewReusePool().acquire();
                        View view3 = mapboxMap$MarkerViewAdapter2.getView(markerView2, view2, this.mapView);
                        if (view3 != null) {
                            view3.setRotationX(markerView2.getTilt());
                            view3.setRotation(markerView2.getRotation());
                            view3.setAlpha(markerView2.getAlpha());
                            view3.setVisibility(8);
                            if (this.mapboxMap.getSelectedMarkers().contains(markerView2) && mapboxMap$MarkerViewAdapter2.onSelect(markerView2, view3, true)) {
                                this.mapboxMap.selectMarker(markerView2);
                            }
                            markerView2.setMapboxMap(this.mapboxMap);
                            this.markerViewMap.put(markerView2, view3);
                            if (view2 == null) {
                                view3.setVisibility(8);
                                this.mapView.getMarkerViewContainer().addView(view3);
                            }
                        }
                    }
                }
            }
        }
        update();
    }

    public void onClickMarkerView(MarkerView markerView) {
        boolean z = false;
        MapboxMap$MarkerViewAdapter viewAdapter = getViewAdapter(markerView);
        View view = getView(markerView);
        if (viewAdapter != null && view != null) {
            if (this.onMarkerViewClickListener != null) {
                z = this.onMarkerViewClickListener.onMarkerClick(markerView, view, viewAdapter);
            }
            if (!z) {
                ensureInfoWindowOffset(markerView);
                select(markerView, view, viewAdapter);
            }
        }
    }

    public void ensureInfoWindowOffset(MarkerView markerView) {
        View view;
        if (this.markerViewMap.containsKey(markerView)) {
            view = (View) this.markerViewMap.get(markerView);
        } else {
            for (MapboxMap$MarkerViewAdapter mapboxMap$MarkerViewAdapter : this.markerViewAdapters) {
                if (mapboxMap$MarkerViewAdapter.getMarkerClass().equals(markerView.getClass())) {
                    view = mapboxMap$MarkerViewAdapter.getView(markerView, (View) mapboxMap$MarkerViewAdapter.getViewReusePool().acquire(), this.mapView);
                    break;
                }
            }
            view = null;
        }
        if (view != null) {
            if (markerView.getWidth() == 0.0f) {
                if (view.getMeasuredWidth() == 0) {
                    view.measure(0, 0);
                }
                markerView.setWidth((float) view.getMeasuredWidth());
                markerView.setHeight((float) view.getMeasuredHeight());
            }
            if (markerView.getOffsetX() == -1.0f) {
                markerView.setOffset((float) ((int) (markerView.getAnchorU() * markerView.getWidth())), (float) ((int) (markerView.getAnchorV() * markerView.getHeight())));
            }
            int measuredWidth = (int) ((((float) view.getMeasuredWidth()) * markerView.getInfoWindowAnchorU()) - markerView.getOffsetX());
            markerView.setTopOffsetPixels((int) ((((float) view.getMeasuredHeight()) * markerView.getInfoWindowAnchorV()) - markerView.getOffsetY()));
            markerView.setRightOffsetPixels(measuredWidth);
        }
    }
}
