package com.mapbox.mapboxsdk.maps;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.location.Location;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.util.LongSparseArray;
import android.text.TextUtils;
import android.util.Log;
import com.mapbox.mapboxsdk.annotations.Annotation;
import com.mapbox.mapboxsdk.annotations.BaseMarkerOptions;
import com.mapbox.mapboxsdk.annotations.BaseMarkerViewOptions;
import com.mapbox.mapboxsdk.annotations.InfoWindow;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.MarkerView;
import com.mapbox.mapboxsdk.annotations.MarkerViewManager;
import com.mapbox.mapboxsdk.annotations.Polygon;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdate;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import com.mapbox.mapboxsdk.maps.widgets.MyLocationViewSettings;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.NoSuchLayerException;
import com.mapbox.mapboxsdk.style.sources.NoSuchSourceException;
import com.mapbox.mapboxsdk.style.sources.Source;
import com.mapbox.services.commons.geojson.Feature;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MapboxMap {
    private static final String TAG = MapboxMap.class.getSimpleName();
    private boolean allowConcurrentMultipleInfoWindows;
    private LongSparseArray<Annotation> annotations;
    private CameraPosition cameraPosition;
    private MapboxMap$InfoWindowAdapter infoWindowAdapter;
    private List<InfoWindow> infoWindows;
    private boolean invalidCameraPosition;
    private MapView mapView;
    private MarkerViewManager markerViewManager;
    private double maxZoomLevel = -1.0d;
    private double minZoomLevel = -1.0d;
    private boolean myLocationEnabled;
    private MyLocationViewSettings myLocationViewSettings;
    private MapboxMap$OnCameraChangeListener onCameraChangeListener;
    private MapboxMap$OnFlingListener onFlingListener;
    private MapboxMap$OnFpsChangedListener onFpsChangedListener;
    private MapboxMap$OnInfoWindowClickListener onInfoWindowClickListener;
    private MapboxMap$OnInfoWindowCloseListener onInfoWindowCloseListener;
    private MapboxMap$OnInfoWindowLongClickListener onInfoWindowLongClickListener;
    private MapboxMap$OnMapClickListener onMapClickListener;
    private MapboxMap$OnMapLongClickListener onMapLongClickListener;
    private MapboxMap$OnMarkerClickListener onMarkerClickListener;
    private OnMyBearingTrackingModeChangeListener onMyBearingTrackingModeChangeListener;
    private OnMyLocationTrackingModeChangeListener onMyLocationTrackingModeChangeListener;
    private MapboxMap$OnScrollListener onScrollListener;
    private Projection projection;
    private List<Marker> selectedMarkers;
    private TrackingSettings trackingSettings;
    private UiSettings uiSettings;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface OnMyBearingTrackingModeChangeListener {
        void onMyBearingTrackingModeChange(int i);
    }

    public interface OnMyLocationChangeListener {
        void onMyLocationChange(@Nullable Location location);
    }

    public interface OnMyLocationTrackingModeChangeListener {
        void onMyLocationTrackingModeChange(int i);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    MapboxMap(@NonNull MapView mapView) {
        this.mapView = mapView;
        this.mapView.addOnMapChangedListener(new MapboxMap$MapChangeCameraPositionListener(this, null));
        this.uiSettings = new UiSettings(mapView);
        this.trackingSettings = new TrackingSettings(this.mapView, this.uiSettings);
        this.projection = new Projection(mapView);
        this.annotations = new LongSparseArray();
        this.selectedMarkers = new ArrayList();
        this.infoWindows = new ArrayList();
        this.markerViewManager = new MarkerViewManager(this, mapView);
    }

    @Nullable
    @UiThread
    public Layer getLayer(@NonNull String str) {
        return getMapView().getNativeMapView().getLayer(str);
    }

    @Nullable
    @UiThread
    public <T extends Layer> T getLayerAs(@NonNull String str) {
        try {
            return getMapView().getNativeMapView().getLayer(str);
        } catch (ClassCastException e) {
            Log.e(TAG, String.format("Layer: %s is a different type: %s", new Object[]{str, e.getMessage()}));
            return null;
        }
    }

    @UiThread
    public void addLayer(@NonNull Layer layer) {
        addLayer(layer, null);
    }

    @UiThread
    public void addLayer(@NonNull Layer layer, String str) {
        getMapView().getNativeMapView().addLayer(layer, str);
    }

    @UiThread
    public void removeLayer(@NonNull String str) throws NoSuchLayerException {
        getMapView().getNativeMapView().removeLayer(str);
    }

    @Nullable
    @UiThread
    public Source getSource(@NonNull String str) {
        return getMapView().getNativeMapView().getSource(str);
    }

    @Nullable
    @UiThread
    public <T extends Source> T getSourceAs(@NonNull String str) {
        try {
            return getMapView().getNativeMapView().getSource(str);
        } catch (ClassCastException e) {
            Log.e(TAG, String.format("Source: %s is a different type: %s", new Object[]{str, e.getMessage()}));
            return null;
        }
    }

    @UiThread
    public void addSource(@NonNull Source source) {
        getMapView().getNativeMapView().addSource(source);
    }

    @UiThread
    public void removeSource(@NonNull String str) throws NoSuchSourceException {
        getMapView().getNativeMapView().removeSource(str);
    }

    @UiThread
    public void addImage(@NonNull String str, @NonNull Bitmap bitmap) {
        getMapView().getNativeMapView().addImage(str, bitmap);
    }

    @UiThread
    public void removeImage(String str) {
        getMapView().getNativeMapView().removeImage(str);
    }

    @UiThread
    public void setMinZoom(@FloatRange(from = 0.0d, to = 21.0d) double d) {
        if (d < 0.0d || d > 21.0d) {
            Log.e(MapboxConstants.TAG, "Not setting minZoom, value is in unsupported range: " + d);
            return;
        }
        this.minZoomLevel = d;
        this.mapView.setMinZoom(d);
    }

    @UiThread
    public double getMinZoom() {
        if (this.minZoomLevel != -1.0d) {
            return this.minZoomLevel;
        }
        double minZoom = this.mapView.getMinZoom();
        this.minZoomLevel = minZoom;
        return minZoom;
    }

    @UiThread
    public void setMaxZoom(@FloatRange(from = 0.0d, to = 21.0d) double d) {
        if (d < 0.0d || d > 21.0d) {
            Log.e(MapboxConstants.TAG, "Not setting maxZoom, value is in unsupported range: " + d);
            return;
        }
        this.maxZoomLevel = d;
        this.mapView.setMaxZoom(d);
    }

    @UiThread
    public double getMaxZoom() {
        if (this.maxZoomLevel != -1.0d) {
            return this.maxZoomLevel;
        }
        double maxZoom = this.mapView.getMaxZoom();
        this.maxZoomLevel = maxZoom;
        return maxZoom;
    }

    public UiSettings getUiSettings() {
        return this.uiSettings;
    }

    public TrackingSettings getTrackingSettings() {
        return this.trackingSettings;
    }

    public MyLocationViewSettings getMyLocationViewSettings() {
        if (this.myLocationViewSettings == null) {
            this.myLocationViewSettings = new MyLocationViewSettings(this.mapView, this.mapView.getUserLocationView());
        }
        return this.myLocationViewSettings;
    }

    public Projection getProjection() {
        return this.projection;
    }

    public final CameraPosition getCameraPosition() {
        if (this.invalidCameraPosition) {
            invalidateCameraPosition();
        }
        return this.cameraPosition;
    }

    public void setCameraPosition(@NonNull CameraPosition cameraPosition) {
        moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @UiThread
    public final void moveCamera(CameraUpdate cameraUpdate) {
        moveCamera(cameraUpdate, null);
    }

    @UiThread
    public final void moveCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        this.cameraPosition = cameraUpdate.getCameraPosition(this);
        this.mapView.resetTrackingModesIfRequired(this.cameraPosition);
        this.mapView.jumpTo(this.cameraPosition.bearing, this.cameraPosition.target, this.cameraPosition.tilt, this.cameraPosition.zoom);
        if (cancelableCallback != null) {
            cancelableCallback.onFinish();
        }
        if (this.onCameraChangeListener != null) {
            this.onCameraChangeListener.onCameraChange(this.cameraPosition);
        }
    }

    @UiThread
    public final void easeCamera(CameraUpdate cameraUpdate) {
        easeCamera(cameraUpdate, 300);
    }

    @UiThread
    public final void easeCamera(CameraUpdate cameraUpdate, int i) {
        easeCamera(cameraUpdate, i, null);
    }

    @UiThread
    public final void easeCamera(CameraUpdate cameraUpdate, int i, CancelableCallback cancelableCallback) {
        easeCamera(cameraUpdate, i, true, cancelableCallback);
    }

    @UiThread
    public final void easeCamera(CameraUpdate cameraUpdate, int i, boolean z) {
        easeCamera(cameraUpdate, i, z, null);
    }

    @UiThread
    public final void easeCamera(CameraUpdate cameraUpdate, int i, boolean z, CancelableCallback cancelableCallback) {
        easeCamera(cameraUpdate, i, z, true, cancelableCallback);
    }

    @UiThread
    public final void easeCamera(CameraUpdate cameraUpdate, int i, boolean z, boolean z2, CancelableCallback cancelableCallback) {
        this.cameraPosition = cameraUpdate.getCameraPosition(this);
        if (z2) {
            this.mapView.resetTrackingModesIfRequired(this.cameraPosition);
        }
        this.mapView.easeTo(this.cameraPosition.bearing, this.cameraPosition.target, getDurationNano((long) i), this.cameraPosition.tilt, this.cameraPosition.zoom, z, new MapboxMap$1(this, cancelableCallback));
    }

    @UiThread
    public final void animateCamera(CameraUpdate cameraUpdate) {
        animateCamera(cameraUpdate, 300, null);
    }

    @UiThread
    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        animateCamera(cameraUpdate, 300, cancelableCallback);
    }

    @UiThread
    public final void animateCamera(CameraUpdate cameraUpdate, int i) {
        animateCamera(cameraUpdate, i, null);
    }

    @UiThread
    public final void animateCamera(CameraUpdate cameraUpdate, int i, CancelableCallback cancelableCallback) {
        this.cameraPosition = cameraUpdate.getCameraPosition(this);
        this.mapView.resetTrackingModesIfRequired(this.cameraPosition);
        this.mapView.flyTo(this.cameraPosition.bearing, this.cameraPosition.target, getDurationNano((long) i), this.cameraPosition.tilt, this.cameraPosition.zoom, new MapboxMap$2(this, cancelableCallback));
    }

    private long getDurationNano(long j) {
        return j > 0 ? TimeUnit.NANOSECONDS.convert(j, TimeUnit.MILLISECONDS) : 0;
    }

    private void invalidateCameraPosition() {
        if (this.invalidCameraPosition) {
            this.invalidCameraPosition = false;
            CameraPosition invalidateCameraPosition = this.mapView.invalidateCameraPosition();
            if (invalidateCameraPosition != null) {
                this.cameraPosition = invalidateCameraPosition;
            }
            if (this.onCameraChangeListener != null) {
                this.onCameraChangeListener.onCameraChange(this.cameraPosition);
            }
        }
    }

    public void resetNorth() {
        this.mapView.resetNorth();
    }

    @UiThread
    public boolean isDebugActive() {
        return this.mapView.isDebugActive();
    }

    @UiThread
    public void setDebugActive(boolean z) {
        this.mapView.setDebugActive(z);
    }

    @UiThread
    public void cycleDebugOptions() {
        this.mapView.cycleDebugOptions();
    }

    @UiThread
    public void setStyleUrl(@NonNull String str) {
        this.mapView.setStyleUrl(str);
    }

    @UiThread
    @Deprecated
    public void setStyle(String str) {
        setStyleUrl(str);
    }

    @UiThread
    @NonNull
    public String getStyleUrl() {
        return this.mapView.getStyleUrl();
    }

    @UiThread
    @Deprecated
    public void setAccessToken(@NonNull String str) {
        this.mapView.setAccessToken(str);
    }

    @Nullable
    @UiThread
    @Deprecated
    public String getAccessToken() {
        return this.mapView.getAccessToken();
    }

    void setTilt(double d) {
        this.mapView.setTilt(Double.valueOf(d));
    }

    @UiThread
    @NonNull
    public Marker addMarker(@NonNull MarkerOptions markerOptions) {
        return addMarker((BaseMarkerOptions) markerOptions);
    }

    @UiThread
    @NonNull
    public Marker addMarker(@NonNull BaseMarkerOptions baseMarkerOptions) {
        Marker prepareMarker = prepareMarker(baseMarkerOptions);
        long addMarker = this.mapView.addMarker(prepareMarker);
        prepareMarker.setMapboxMap(this);
        prepareMarker.setId(addMarker);
        this.annotations.put(addMarker, prepareMarker);
        return prepareMarker;
    }

    @UiThread
    @NonNull
    public MarkerView addMarker(@NonNull BaseMarkerViewOptions baseMarkerViewOptions) {
        Marker prepareViewMarker = prepareViewMarker(baseMarkerViewOptions);
        prepareViewMarker.setMapboxMap(this);
        long addMarker = this.mapView.addMarker(prepareViewMarker);
        prepareViewMarker.setId(addMarker);
        this.annotations.put(addMarker, prepareViewMarker);
        this.markerViewManager.invalidateViewMarkersInVisibleRegion();
        return prepareViewMarker;
    }

    @UiThread
    @NonNull
    public List<MarkerView> addMarkerViews(@NonNull List<? extends BaseMarkerViewOptions> list) {
        List<MarkerView> arrayList = new ArrayList();
        for (BaseMarkerViewOptions prepareViewMarker : list) {
            Marker prepareViewMarker2 = prepareViewMarker(prepareViewMarker);
            prepareViewMarker2.setMapboxMap(this);
            long addMarker = this.mapView.addMarker(prepareViewMarker2);
            prepareViewMarker2.setId(addMarker);
            this.annotations.put(addMarker, prepareViewMarker2);
            arrayList.add(prepareViewMarker2);
        }
        this.markerViewManager.invalidateViewMarkersInVisibleRegion();
        return arrayList;
    }

    @UiThread
    @NonNull
    public List<Marker> addMarkers(@NonNull List<? extends BaseMarkerOptions> list) {
        int i = 0;
        int size = list.size();
        List<Marker> arrayList = new ArrayList(size);
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(prepareMarker((BaseMarkerOptions) list.get(i2)));
            }
            if (arrayList.size() > 0) {
                long[] addMarkers = this.mapView.addMarkers(arrayList);
                if (addMarkers == null || addMarkers.length == arrayList.size()) {
                    long j = 0;
                    while (i < arrayList.size()) {
                        Marker marker = (Marker) arrayList.get(i);
                        marker.setMapboxMap(this);
                        if (addMarkers != null) {
                            j = addMarkers[i];
                        } else {
                            j++;
                        }
                        marker.setId(j);
                        this.annotations.put(j, marker);
                        i++;
                    }
                }
            }
        }
        return arrayList;
    }

    @UiThread
    public void updateMarker(@NonNull Marker marker) {
        this.mapView.updateMarker(marker);
        int indexOfKey = this.annotations.indexOfKey(marker.getId());
        if (indexOfKey > -1) {
            this.annotations.setValueAt(indexOfKey, marker);
        }
    }

    @UiThread
    public void updatePolygon(Polygon polygon) {
        this.mapView.updatePolygon(polygon);
        int indexOfKey = this.annotations.indexOfKey(polygon.getId());
        if (indexOfKey > -1) {
            this.annotations.setValueAt(indexOfKey, polygon);
        }
    }

    @UiThread
    public void updatePolyline(Polyline polyline) {
        this.mapView.updatePolyline(polyline);
        int indexOfKey = this.annotations.indexOfKey(polyline.getId());
        if (indexOfKey > -1) {
            this.annotations.setValueAt(indexOfKey, polyline);
        }
    }

    @UiThread
    @NonNull
    public Polyline addPolyline(@NonNull PolylineOptions polylineOptions) {
        Polyline polyline = polylineOptions.getPolyline();
        if (!polyline.getPoints().isEmpty()) {
            long addPolyline = this.mapView.addPolyline(polyline);
            polyline.setMapboxMap(this);
            polyline.setId(addPolyline);
            this.annotations.put(addPolyline, polyline);
        }
        return polyline;
    }

    @UiThread
    @NonNull
    public List<Polyline> addPolylines(@NonNull List<PolylineOptions> list) {
        int size = list.size();
        List<Polyline> arrayList = new ArrayList(size);
        if (size > 0) {
            Polyline polyline;
            for (PolylineOptions polyline2 : list) {
                polyline = polyline2.getPolyline();
                if (!polyline.getPoints().isEmpty()) {
                    arrayList.add(polyline);
                }
            }
            long[] addPolylines = this.mapView.addPolylines(arrayList);
            if (addPolylines == null || addPolylines.length == arrayList.size()) {
                long j = 0;
                for (int i = 0; i < arrayList.size(); i++) {
                    polyline = (Polyline) arrayList.get(i);
                    polyline.setMapboxMap(this);
                    if (addPolylines != null) {
                        j = addPolylines[i];
                    } else {
                        j++;
                    }
                    polyline.setId(j);
                    this.annotations.put(j, polyline);
                }
            }
        }
        return arrayList;
    }

    @UiThread
    @NonNull
    public Polygon addPolygon(@NonNull PolygonOptions polygonOptions) {
        Polygon polygon = polygonOptions.getPolygon();
        if (!polygon.getPoints().isEmpty()) {
            long addPolygon = this.mapView.addPolygon(polygon);
            polygon.setId(addPolygon);
            polygon.setMapboxMap(this);
            this.annotations.put(addPolygon, polygon);
        }
        return polygon;
    }

    @UiThread
    @NonNull
    public List<Polygon> addPolygons(@NonNull List<PolygonOptions> list) {
        int size = list.size();
        List<Polygon> arrayList = new ArrayList(size);
        if (size > 0) {
            Polygon polygon;
            for (PolygonOptions polygon2 : list) {
                polygon = polygon2.getPolygon();
                if (!polygon.getPoints().isEmpty()) {
                    arrayList.add(polygon);
                }
            }
            long[] addPolygons = this.mapView.addPolygons(arrayList);
            if (addPolygons == null || addPolygons.length == arrayList.size()) {
                long j = 0;
                for (int i = 0; i < arrayList.size(); i++) {
                    polygon = (Polygon) arrayList.get(i);
                    polygon.setMapboxMap(this);
                    if (addPolygons != null) {
                        j = addPolygons[i];
                    } else {
                        j++;
                    }
                    polygon.setId(j);
                    this.annotations.put(j, polygon);
                }
            }
        }
        return arrayList;
    }

    @UiThread
    public void removeMarker(@NonNull Marker marker) {
        removeAnnotation((Annotation) marker);
    }

    @UiThread
    public void removePolyline(@NonNull Polyline polyline) {
        removeAnnotation((Annotation) polyline);
    }

    @UiThread
    public void removePolygon(@NonNull Polygon polygon) {
        removeAnnotation((Annotation) polygon);
    }

    @UiThread
    public void removeAnnotation(@NonNull Annotation annotation) {
        if (annotation instanceof Marker) {
            Marker marker = (Marker) annotation;
            marker.hideInfoWindow();
            if (marker instanceof MarkerView) {
                this.markerViewManager.removeMarkerView((MarkerView) marker);
            }
        }
        long id = annotation.getId();
        this.mapView.removeAnnotation(id);
        this.annotations.remove(id);
    }

    @UiThread
    public void removeAnnotation(long j) {
        this.mapView.removeAnnotation(j);
        this.annotations.remove(j);
    }

    @UiThread
    public void removeAnnotations(@NonNull List<? extends Annotation> list) {
        int size = list.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            Annotation annotation = (Annotation) list.get(i);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.hideInfoWindow();
                if (marker instanceof MarkerView) {
                    this.markerViewManager.removeMarkerView((MarkerView) marker);
                }
            }
            jArr[i] = ((Annotation) list.get(i)).getId();
        }
        this.mapView.removeAnnotations(jArr);
        for (long remove : jArr) {
            this.annotations.remove(remove);
        }
    }

    @UiThread
    public void removeAnnotations() {
        int size = this.annotations.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = this.annotations.keyAt(i);
            Annotation annotation = (Annotation) this.annotations.get(jArr[i]);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.hideInfoWindow();
                if (marker instanceof MarkerView) {
                    this.markerViewManager.removeMarkerView((MarkerView) marker);
                }
            }
        }
        this.mapView.removeAnnotations(jArr);
        this.annotations.clear();
    }

    @UiThread
    public void clear() {
        removeAnnotations();
    }

    @Nullable
    public Annotation getAnnotation(long j) {
        return (Annotation) this.annotations.get(j);
    }

    @NonNull
    public List<Annotation> getAnnotations() {
        List<Annotation> arrayList = new ArrayList();
        for (int i = 0; i < this.annotations.size(); i++) {
            arrayList.add(this.annotations.get(this.annotations.keyAt(i)));
        }
        return arrayList;
    }

    @NonNull
    public List<Marker> getMarkers() {
        List<Marker> arrayList = new ArrayList();
        for (int i = 0; i < this.annotations.size(); i++) {
            Annotation annotation = (Annotation) this.annotations.get(this.annotations.keyAt(i));
            if (annotation instanceof Marker) {
                arrayList.add((Marker) annotation);
            }
        }
        return arrayList;
    }

    @NonNull
    public List<Polygon> getPolygons() {
        List<Polygon> arrayList = new ArrayList();
        for (int i = 0; i < this.annotations.size(); i++) {
            Annotation annotation = (Annotation) this.annotations.get(this.annotations.keyAt(i));
            if (annotation instanceof Polygon) {
                arrayList.add((Polygon) annotation);
            }
        }
        return arrayList;
    }

    @NonNull
    public List<Polyline> getPolylines() {
        List<Polyline> arrayList = new ArrayList();
        for (int i = 0; i < this.annotations.size(); i++) {
            Annotation annotation = (Annotation) this.annotations.get(this.annotations.keyAt(i));
            if (annotation instanceof Polyline) {
                arrayList.add((Polyline) annotation);
            }
        }
        return arrayList;
    }

    @UiThread
    public void selectMarker(@NonNull Marker marker) {
        if (marker == null) {
            Log.w(MapboxConstants.TAG, "marker was null, so just returning");
        } else if (!this.selectedMarkers.contains(marker)) {
            boolean onMarkerClick;
            if (!isAllowConcurrentMultipleOpenInfoWindows()) {
                deselectMarkers();
            }
            if (this.onMarkerClickListener != null) {
                onMarkerClick = this.onMarkerClickListener.onMarkerClick(marker);
            } else {
                onMarkerClick = false;
            }
            if (!onMarkerClick) {
                if (marker instanceof MarkerView) {
                    this.markerViewManager.select((MarkerView) marker, false);
                    this.markerViewManager.ensureInfoWindowOffset((MarkerView) marker);
                }
                if (isInfoWindowValidForMarker(marker) || getInfoWindowAdapter() != null) {
                    this.infoWindows.add(marker.showInfoWindow(this, this.mapView));
                }
            }
            this.selectedMarkers.add(marker);
        }
    }

    @UiThread
    public void deselectMarkers() {
        if (!this.selectedMarkers.isEmpty()) {
            for (Marker marker : this.selectedMarkers) {
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                }
                if (marker instanceof MarkerView) {
                    this.markerViewManager.deselect((MarkerView) marker, false);
                }
            }
            this.selectedMarkers.clear();
        }
    }

    @UiThread
    public void deselectMarker(@NonNull Marker marker) {
        if (this.selectedMarkers.contains(marker)) {
            if (marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            }
            if (marker instanceof MarkerView) {
                this.markerViewManager.deselect((MarkerView) marker, false);
            }
            this.selectedMarkers.remove(marker);
        }
    }

    @UiThread
    public List<Marker> getSelectedMarkers() {
        return this.selectedMarkers;
    }

    private Marker prepareMarker(BaseMarkerOptions baseMarkerOptions) {
        Marker marker = baseMarkerOptions.getMarker();
        marker.setTopOffsetPixels(this.mapView.getTopOffsetPixelsForIcon(this.mapView.loadIconForMarker(marker)));
        return marker;
    }

    private MarkerView prepareViewMarker(BaseMarkerViewOptions baseMarkerViewOptions) {
        MarkerView marker = baseMarkerViewOptions.getMarker();
        this.mapView.loadIconForMarkerView(marker);
        return marker;
    }

    public MarkerViewManager getMarkerViewManager() {
        return this.markerViewManager;
    }

    @UiThread
    public void setInfoWindowAdapter(@Nullable MapboxMap$InfoWindowAdapter mapboxMap$InfoWindowAdapter) {
        this.infoWindowAdapter = mapboxMap$InfoWindowAdapter;
    }

    @Nullable
    @UiThread
    public MapboxMap$InfoWindowAdapter getInfoWindowAdapter() {
        return this.infoWindowAdapter;
    }

    @UiThread
    public void setAllowConcurrentMultipleOpenInfoWindows(boolean z) {
        this.allowConcurrentMultipleInfoWindows = z;
    }

    @UiThread
    public boolean isAllowConcurrentMultipleOpenInfoWindows() {
        return this.allowConcurrentMultipleInfoWindows;
    }

    List<InfoWindow> getInfoWindows() {
        return this.infoWindows;
    }

    private boolean isInfoWindowValidForMarker(@NonNull Marker marker) {
        return (TextUtils.isEmpty(marker.getTitle()) && TextUtils.isEmpty(marker.getSnippet())) ? false : true;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mapView.setContentPadding(i, i2, i3, i4);
        this.uiSettings.invalidate();
    }

    public int[] getPadding() {
        return new int[]{this.mapView.getContentPaddingLeft(), this.mapView.getContentPaddingTop(), this.mapView.getContentPaddingRight(), this.mapView.getContentPaddingBottom()};
    }

    @UiThread
    public void setOnCameraChangeListener(@Nullable MapboxMap$OnCameraChangeListener mapboxMap$OnCameraChangeListener) {
        this.onCameraChangeListener = mapboxMap$OnCameraChangeListener;
    }

    @UiThread
    public void setOnFpsChangedListener(@Nullable MapboxMap$OnFpsChangedListener mapboxMap$OnFpsChangedListener) {
        this.onFpsChangedListener = mapboxMap$OnFpsChangedListener;
    }

    MapboxMap$OnFpsChangedListener getOnFpsChangedListener() {
        return this.onFpsChangedListener;
    }

    @UiThread
    public void setOnScrollListener(@Nullable MapboxMap$OnScrollListener mapboxMap$OnScrollListener) {
        this.onScrollListener = mapboxMap$OnScrollListener;
    }

    MapboxMap$OnScrollListener getOnScrollListener() {
        return this.onScrollListener;
    }

    @UiThread
    public void setOnFlingListener(@Nullable MapboxMap$OnFlingListener mapboxMap$OnFlingListener) {
        this.onFlingListener = mapboxMap$OnFlingListener;
    }

    MapboxMap$OnFlingListener getOnFlingListener() {
        return this.onFlingListener;
    }

    @UiThread
    public void setOnMapClickListener(@Nullable MapboxMap$OnMapClickListener mapboxMap$OnMapClickListener) {
        this.onMapClickListener = mapboxMap$OnMapClickListener;
    }

    MapboxMap$OnMapClickListener getOnMapClickListener() {
        return this.onMapClickListener;
    }

    @UiThread
    public void setOnMapLongClickListener(@Nullable MapboxMap$OnMapLongClickListener mapboxMap$OnMapLongClickListener) {
        this.onMapLongClickListener = mapboxMap$OnMapLongClickListener;
    }

    MapboxMap$OnMapLongClickListener getOnMapLongClickListener() {
        return this.onMapLongClickListener;
    }

    @UiThread
    public void setOnMarkerClickListener(@Nullable MapboxMap$OnMarkerClickListener mapboxMap$OnMarkerClickListener) {
        this.onMarkerClickListener = mapboxMap$OnMarkerClickListener;
    }

    @UiThread
    public void setOnInfoWindowClickListener(@Nullable MapboxMap$OnInfoWindowClickListener mapboxMap$OnInfoWindowClickListener) {
        this.onInfoWindowClickListener = mapboxMap$OnInfoWindowClickListener;
    }

    @UiThread
    public MapboxMap$OnInfoWindowClickListener getOnInfoWindowClickListener() {
        return this.onInfoWindowClickListener;
    }

    @UiThread
    public void setOnInfoWindowLongClickListener(@Nullable MapboxMap$OnInfoWindowLongClickListener mapboxMap$OnInfoWindowLongClickListener) {
        this.onInfoWindowLongClickListener = mapboxMap$OnInfoWindowLongClickListener;
    }

    public MapboxMap$OnInfoWindowLongClickListener getOnInfoWindowLongClickListener() {
        return this.onInfoWindowLongClickListener;
    }

    public void setOnInfoWindowCloseListener(@Nullable MapboxMap$OnInfoWindowCloseListener mapboxMap$OnInfoWindowCloseListener) {
        this.onInfoWindowCloseListener = mapboxMap$OnInfoWindowCloseListener;
    }

    @UiThread
    public MapboxMap$OnInfoWindowCloseListener getOnInfoWindowCloseListener() {
        return this.onInfoWindowCloseListener;
    }

    @UiThread
    public boolean isMyLocationEnabled() {
        return this.myLocationEnabled;
    }

    @UiThread
    public void setMyLocationEnabled(boolean z) {
        if (this.mapView.isPermissionsAccepted()) {
            this.myLocationEnabled = z;
            this.mapView.setMyLocationEnabled(z);
            return;
        }
        Log.e(MapboxConstants.TAG, "Could not activate user location tracking: user did not accept the permission or permissions were not requested.");
    }

    @Nullable
    @UiThread
    public Location getMyLocation() {
        return this.mapView.getMyLocation();
    }

    @UiThread
    public void setOnMyLocationChangeListener(@Nullable OnMyLocationChangeListener onMyLocationChangeListener) {
        this.mapView.setOnMyLocationChangeListener(onMyLocationChangeListener);
    }

    @UiThread
    public void setOnMyLocationTrackingModeChangeListener(@Nullable OnMyLocationTrackingModeChangeListener onMyLocationTrackingModeChangeListener) {
        this.onMyLocationTrackingModeChangeListener = onMyLocationTrackingModeChangeListener;
    }

    OnMyLocationTrackingModeChangeListener getOnMyLocationTrackingModeChangeListener() {
        return this.onMyLocationTrackingModeChangeListener;
    }

    @UiThread
    public void setOnMyBearingTrackingModeChangeListener(@Nullable OnMyBearingTrackingModeChangeListener onMyBearingTrackingModeChangeListener) {
        this.onMyBearingTrackingModeChangeListener = onMyBearingTrackingModeChangeListener;
    }

    OnMyBearingTrackingModeChangeListener getOnMyBearingTrackingModeChangeListener() {
        return this.onMyBearingTrackingModeChangeListener;
    }

    MapView getMapView() {
        return this.mapView;
    }

    void setUiSettings(UiSettings uiSettings) {
        this.uiSettings = uiSettings;
    }

    void setProjection(Projection projection) {
        this.projection = projection;
    }

    public void invalidate() {
        this.mapView.invalidate();
    }

    @UiThread
    public void snapshot(@NonNull SnapshotReadyCallback snapshotReadyCallback, @Nullable Bitmap bitmap) {
        this.mapView.snapshot(snapshotReadyCallback, bitmap);
    }

    @UiThread
    public void snapshot(@NonNull SnapshotReadyCallback snapshotReadyCallback) {
        this.mapView.snapshot(snapshotReadyCallback, null);
    }

    @UiThread
    @NonNull
    public List<Feature> queryRenderedFeatures(@NonNull PointF pointF, @Nullable String... strArr) {
        return this.mapView.getNativeMapView().queryRenderedFeatures(pointF, strArr);
    }

    @UiThread
    @NonNull
    public List<Feature> queryRenderedFeatures(@NonNull RectF rectF, @Nullable String... strArr) {
        return this.mapView.getNativeMapView().queryRenderedFeatures(rectF, strArr);
    }
}
