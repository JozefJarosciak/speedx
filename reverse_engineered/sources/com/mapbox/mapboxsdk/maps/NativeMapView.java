package com.mapbox.mapboxsdk.maps;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Surface;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.Polygon;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.ProjectedMeters;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.NoSuchLayerException;
import com.mapbox.mapboxsdk.style.sources.NoSuchSourceException;
import com.mapbox.mapboxsdk.style.sources.Source;
import com.mapbox.services.commons.geojson.Feature;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class NativeMapView {
    boolean destroyed = false;
    private MapView mapView;
    private long nativeMapViewPtr = 0;
    private final float pixelRatio;

    private native void nativeAddAnnotationIcon(long j, String str, int i, int i2, float f, byte[] bArr);

    private native void nativeAddClass(long j, String str);

    private native void nativeAddImage(long j, String str, int i, int i2, float f, byte[] bArr);

    private native void nativeAddLayer(long j, long j2, String str);

    private native long[] nativeAddMarkers(long j, Marker[] markerArr);

    private native long[] nativeAddPolygons(long j, Polygon[] polygonArr);

    private native long[] nativeAddPolylines(long j, Polyline[] polylineArr);

    private native void nativeAddSource(long j, long j2);

    private native void nativeCancelTransitions(long j);

    private native long nativeCreate(String str, String str2, String str3, float f, int i, long j);

    private native void nativeCreateSurface(long j, Surface surface);

    private native void nativeDestroy(long j);

    private native void nativeDestroySurface(long j);

    private native void nativeEaseTo(long j, double d, double d2, double d3, long j2, double d4, double d5, boolean z);

    private native void nativeFlyTo(long j, double d, double d2, double d3, long j2, double d4, double d5);

    private native void nativeFramebufferResize(long j, int i, int i2);

    private native String nativeGetAccessToken(long j);

    private native double nativeGetBearing(long j);

    private native double[] nativeGetCameraValues(long j);

    private native List<String> nativeGetClasses(long j);

    private native boolean nativeGetDebug(long j);

    private native LatLng nativeGetLatLng(long j);

    private native Layer nativeGetLayer(long j, String str);

    private native double nativeGetMaxZoom(long j);

    private native double nativeGetMetersPerPixelAtLatitude(long j, double d, double d2);

    private native double nativeGetMinZoom(long j);

    private native double nativeGetPitch(long j);

    private native double nativeGetScale(long j);

    private native Source nativeGetSource(long j, String str);

    private native String nativeGetStyleJson(long j);

    private native double nativeGetTopOffsetPixelsForAnnotationSymbol(long j, String str);

    private native double nativeGetZoom(long j);

    private native boolean nativeHasClass(long j, String str);

    private native void nativeInitializeContext(long j);

    private native void nativeInitializeDisplay(long j);

    private native boolean nativeIsFullyLoaded(long j);

    private native void nativeJumpTo(long j, double d, double d2, double d3, double d4, double d5);

    private native LatLng nativeLatLngForPixel(long j, float f, float f2);

    private native LatLng nativeLatLngForProjectedMeters(long j, double d, double d2);

    private native void nativeMoveBy(long j, double d, double d2, long j2);

    private native void nativeOnLowMemory(long j);

    private native PointF nativePixelForLatLng(long j, double d, double d2);

    private native ProjectedMeters nativeProjectedMetersForLatLng(long j, double d, double d2);

    private native long[] nativeQueryPointAnnotations(long j, RectF rectF);

    private native Feature[] nativeQueryRenderedFeaturesForBox(long j, float f, float f2, float f3, float f4, String[] strArr);

    private native Feature[] nativeQueryRenderedFeaturesForPoint(long j, float f, float f2, String[] strArr);

    private native void nativeRemoveAnnotations(long j, long[] jArr);

    private native void nativeRemoveClass(long j, String str);

    private native void nativeRemoveImage(long j, String str);

    private native void nativeRemoveLayer(long j, String str) throws NoSuchLayerException;

    private native void nativeRemoveSource(long j, String str) throws NoSuchSourceException;

    private native void nativeRender(long j);

    private native void nativeResetNorth(long j);

    private native void nativeResetPosition(long j);

    private native void nativeResetZoom(long j);

    private native void nativeRotateBy(long j, double d, double d2, double d3, double d4, long j2);

    private native void nativeScaleBy(long j, double d, double d2, double d3, long j2);

    private native void nativeScheduleTakeSnapshot(long j);

    private native void nativeSetAPIBaseURL(long j, String str);

    private native void nativeSetAccessToken(long j, String str);

    private native void nativeSetBearing(long j, double d, long j2);

    private native void nativeSetBearingXY(long j, double d, double d2, double d3);

    private native void nativeSetClasses(long j, List<String> list);

    private native void nativeSetContentPadding(long j, double d, double d2, double d3, double d4);

    private native void nativeSetDebug(long j, boolean z);

    private native void nativeSetGestureInProgress(long j, boolean z);

    private native void nativeSetLatLng(long j, double d, double d2, long j2);

    private native void nativeSetMaxZoom(long j, double d);

    private native void nativeSetMinZoom(long j, double d);

    private native void nativeSetPitch(long j, double d, long j2);

    private native void nativeSetReachability(long j, boolean z);

    private native void nativeSetScale(long j, double d, double d2, double d3, long j2);

    private native void nativeSetStyleJson(long j, String str);

    private native void nativeSetStyleUrl(long j, String str);

    private native void nativeSetVisibleCoordinateBounds(long j, LatLng[] latLngArr, RectF rectF, double d, long j2);

    private native void nativeSetZoom(long j, double d, long j2);

    private native void nativeTerminateContext(long j);

    private native void nativeTerminateDisplay(long j);

    private native void nativeToggleDebug(long j);

    private native void nativeUpdate(long j);

    private native void nativeUpdateMarker(long j, long j2, double d, double d2, String str);

    private native void nativeUpdatePolygon(long j, long j2, Polygon polygon);

    private native void nativeUpdatePolyline(long j, long j2, Polyline polyline);

    private native void nativeViewResize(long j, int i, int i2);

    static {
        System.loadLibrary("mapbox-gl");
    }

    public NativeMapView(MapView mapView) {
        Context context = mapView.getContext();
        String databasePath = OfflineManager.getDatabasePath(context);
        this.pixelRatio = context.getResources().getDisplayMetrics().density;
        String packageCodePath = context.getPackageCodePath();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        long j = memoryInfo.availMem;
        if (VERSION.SDK_INT >= 16) {
            j = memoryInfo.totalMem;
        }
        if (availableProcessors < 0) {
            throw new IllegalArgumentException("availableProcessors cannot be negative.");
        } else if (j < 0) {
            throw new IllegalArgumentException("totalMemory cannot be negative.");
        } else {
            this.mapView = mapView;
            this.nativeMapViewPtr = nativeCreate(databasePath, databasePath, packageCodePath, this.pixelRatio, availableProcessors, j);
        }
    }

    public void destroy() {
        nativeDestroy(this.nativeMapViewPtr);
        this.nativeMapViewPtr = 0;
        this.mapView = null;
        this.destroyed = true;
    }

    public boolean wasDestroyed() {
        return this.destroyed;
    }

    public void initializeDisplay() {
        nativeInitializeDisplay(this.nativeMapViewPtr);
    }

    public void terminateDisplay() {
        nativeTerminateDisplay(this.nativeMapViewPtr);
    }

    public void initializeContext() {
        nativeInitializeContext(this.nativeMapViewPtr);
    }

    public void terminateContext() {
        nativeTerminateContext(this.nativeMapViewPtr);
    }

    public void createSurface(Surface surface) {
        nativeCreateSurface(this.nativeMapViewPtr, surface);
    }

    public void destroySurface() {
        nativeDestroySurface(this.nativeMapViewPtr);
    }

    public void update() {
        nativeUpdate(this.nativeMapViewPtr);
    }

    public void render() {
        nativeRender(this.nativeMapViewPtr);
    }

    public void resizeView(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("width cannot be negative.");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("height cannot be negative.");
        } else {
            if (i > 65535) {
                Log.e(MapboxConstants.TAG, "Device returned an out of range width size, capping value at 65535 instead of " + i);
                i = 65535;
            }
            if (i2 > 65535) {
                Log.e(MapboxConstants.TAG, "Device returned an out of range height size, capping value at 65535 instead of " + i2);
                i2 = 65535;
            }
            nativeViewResize(this.nativeMapViewPtr, i, i2);
        }
    }

    public void resizeFramebuffer(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("fbWidth cannot be negative.");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("fbHeight cannot be negative.");
        } else if (i > 65535) {
            throw new IllegalArgumentException("fbWidth cannot be greater than 65535.");
        } else if (i2 > 65535) {
            throw new IllegalArgumentException("fbHeight cannot be greater than 65535.");
        } else {
            nativeFramebufferResize(this.nativeMapViewPtr, i, i2);
        }
    }

    public void addClass(String str) {
        nativeAddClass(this.nativeMapViewPtr, str);
    }

    public void removeClass(String str) {
        nativeRemoveClass(this.nativeMapViewPtr, str);
    }

    public boolean hasClass(String str) {
        return nativeHasClass(this.nativeMapViewPtr, str);
    }

    public void setClasses(List<String> list) {
        nativeSetClasses(this.nativeMapViewPtr, list);
    }

    public List<String> getClasses() {
        return nativeGetClasses(this.nativeMapViewPtr);
    }

    public void setStyleUrl(String str) {
        nativeSetStyleUrl(this.nativeMapViewPtr, str);
    }

    public void setStyleJson(String str) {
        nativeSetStyleJson(this.nativeMapViewPtr, str);
    }

    public String getStyleJson() {
        return nativeGetStyleJson(this.nativeMapViewPtr);
    }

    public void setAccessToken(String str) {
        nativeSetAccessToken(this.nativeMapViewPtr, str);
    }

    public String getAccessToken() {
        return nativeGetAccessToken(this.nativeMapViewPtr);
    }

    public void cancelTransitions() {
        nativeCancelTransitions(this.nativeMapViewPtr);
    }

    public void setGestureInProgress(boolean z) {
        nativeSetGestureInProgress(this.nativeMapViewPtr, z);
    }

    public void moveBy(double d, double d2) {
        moveBy(d, d2, 0);
    }

    public void moveBy(double d, double d2, long j) {
        nativeMoveBy(this.nativeMapViewPtr, d, d2, j);
    }

    public void setLatLng(LatLng latLng) {
        setLatLng(latLng, 0);
    }

    public void setLatLng(LatLng latLng, long j) {
        nativeSetLatLng(this.nativeMapViewPtr, latLng.getLatitude(), latLng.getLongitude(), j);
    }

    public LatLng getLatLng() {
        return nativeGetLatLng(this.nativeMapViewPtr);
    }

    public void resetPosition() {
        nativeResetPosition(this.nativeMapViewPtr);
    }

    public double getPitch() {
        return nativeGetPitch(this.nativeMapViewPtr);
    }

    public void setPitch(double d, long j) {
        nativeSetPitch(this.nativeMapViewPtr, d, j);
    }

    public void scaleBy(double d) {
        scaleBy(d, Double.NaN, Double.NaN);
    }

    public void scaleBy(double d, double d2, double d3) {
        scaleBy(d, d2, d3, 0);
    }

    public void scaleBy(double d, double d2, double d3, long j) {
        nativeScaleBy(this.nativeMapViewPtr, d, d2, d3, j);
    }

    public void setScale(double d) {
        setScale(d, Double.NaN, Double.NaN);
    }

    public void setScale(double d, double d2, double d3) {
        setScale(d, d2, d3, 0);
    }

    public void setScale(double d, double d2, double d3, long j) {
        nativeSetScale(this.nativeMapViewPtr, d, d2, d3, j);
    }

    public double getScale() {
        return nativeGetScale(this.nativeMapViewPtr);
    }

    public void setZoom(double d) {
        setZoom(d, 0);
    }

    public void setZoom(double d, long j) {
        nativeSetZoom(this.nativeMapViewPtr, d, j);
    }

    public double getZoom() {
        return nativeGetZoom(this.nativeMapViewPtr);
    }

    public void resetZoom() {
        nativeResetZoom(this.nativeMapViewPtr);
    }

    public void setMinZoom(double d) {
        nativeSetMinZoom(this.nativeMapViewPtr, d);
    }

    public double getMinZoom() {
        return nativeGetMinZoom(this.nativeMapViewPtr);
    }

    public void setMaxZoom(double d) {
        nativeSetMaxZoom(this.nativeMapViewPtr, d);
    }

    public double getMaxZoom() {
        return nativeGetMaxZoom(this.nativeMapViewPtr);
    }

    public void rotateBy(double d, double d2, double d3, double d4) {
        rotateBy(d, d2, d3, d4, 0);
    }

    public void rotateBy(double d, double d2, double d3, double d4, long j) {
        nativeRotateBy(this.nativeMapViewPtr, d, d2, d3, d4, j);
    }

    public void setContentPadding(double d, double d2, double d3, double d4) {
        nativeSetContentPadding(this.nativeMapViewPtr, d, d2, d3, d4);
    }

    public void setBearing(double d) {
        setBearing(d, 0);
    }

    public void setBearing(double d, long j) {
        nativeSetBearing(this.nativeMapViewPtr, d, j);
    }

    public void setBearing(double d, double d2, double d3) {
        nativeSetBearingXY(this.nativeMapViewPtr, d, d2, d3);
    }

    public double getBearing() {
        return nativeGetBearing(this.nativeMapViewPtr);
    }

    public void resetNorth() {
        nativeResetNorth(this.nativeMapViewPtr);
    }

    public long addMarker(Marker marker) {
        return nativeAddMarkers(this.nativeMapViewPtr, new Marker[]{marker})[0];
    }

    public long[] addMarkers(List<Marker> list) {
        return nativeAddMarkers(this.nativeMapViewPtr, (Marker[]) list.toArray(new Marker[list.size()]));
    }

    public long addPolyline(Polyline polyline) {
        return nativeAddPolylines(this.nativeMapViewPtr, new Polyline[]{polyline})[0];
    }

    public long[] addPolylines(List<Polyline> list) {
        return nativeAddPolylines(this.nativeMapViewPtr, (Polyline[]) list.toArray(new Polyline[list.size()]));
    }

    public long addPolygon(Polygon polygon) {
        return nativeAddPolygons(this.nativeMapViewPtr, new Polygon[]{polygon})[0];
    }

    public long[] addPolygons(List<Polygon> list) {
        return nativeAddPolygons(this.nativeMapViewPtr, (Polygon[]) list.toArray(new Polygon[list.size()]));
    }

    public void updateMarker(Marker marker) {
        LatLng position = marker.getPosition();
        Icon icon = marker.getIcon();
        nativeUpdateMarker(this.nativeMapViewPtr, marker.getId(), position.getLatitude(), position.getLongitude(), icon.getId());
    }

    public void updatePolygon(Polygon polygon) {
        nativeUpdatePolygon(this.nativeMapViewPtr, polygon.getId(), polygon);
    }

    public void updatePolyline(Polyline polyline) {
        nativeUpdatePolyline(this.nativeMapViewPtr, polyline.getId(), polyline);
    }

    public void removeAnnotation(long j) {
        removeAnnotations(new long[]{j});
    }

    public void removeAnnotations(long[] jArr) {
        nativeRemoveAnnotations(this.nativeMapViewPtr, jArr);
    }

    public long[] queryPointAnnotations(RectF rectF) {
        return nativeQueryPointAnnotations(this.nativeMapViewPtr, rectF);
    }

    public void addAnnotationIcon(String str, int i, int i2, float f, byte[] bArr) {
        nativeAddAnnotationIcon(this.nativeMapViewPtr, str, i, i2, f, bArr);
    }

    public void setVisibleCoordinateBounds(LatLng[] latLngArr, RectF rectF, double d, long j) {
        nativeSetVisibleCoordinateBounds(this.nativeMapViewPtr, latLngArr, rectF, d, j);
    }

    public void onLowMemory() {
        nativeOnLowMemory(this.nativeMapViewPtr);
    }

    public void setDebug(boolean z) {
        nativeSetDebug(this.nativeMapViewPtr, z);
    }

    public void cycleDebugOptions() {
        nativeToggleDebug(this.nativeMapViewPtr);
    }

    public boolean getDebug() {
        return nativeGetDebug(this.nativeMapViewPtr);
    }

    public boolean isFullyLoaded() {
        return nativeIsFullyLoaded(this.nativeMapViewPtr);
    }

    public void setReachability(boolean z) {
        nativeSetReachability(this.nativeMapViewPtr, z);
    }

    public double getMetersPerPixelAtLatitude(double d, double d2) {
        return nativeGetMetersPerPixelAtLatitude(this.nativeMapViewPtr, d, d2);
    }

    public ProjectedMeters projectedMetersForLatLng(LatLng latLng) {
        return nativeProjectedMetersForLatLng(this.nativeMapViewPtr, latLng.getLatitude(), latLng.getLongitude());
    }

    public LatLng latLngForProjectedMeters(ProjectedMeters projectedMeters) {
        return nativeLatLngForProjectedMeters(this.nativeMapViewPtr, projectedMeters.getNorthing(), projectedMeters.getEasting());
    }

    public PointF pixelForLatLng(LatLng latLng) {
        return nativePixelForLatLng(this.nativeMapViewPtr, latLng.getLatitude(), latLng.getLongitude());
    }

    public LatLng latLngForPixel(PointF pointF) {
        return nativeLatLngForPixel(this.nativeMapViewPtr, pointF.x, pointF.y);
    }

    public double getTopOffsetPixelsForAnnotationSymbol(String str) {
        return nativeGetTopOffsetPixelsForAnnotationSymbol(this.nativeMapViewPtr, str);
    }

    public void jumpTo(double d, LatLng latLng, double d2, double d3) {
        nativeJumpTo(this.nativeMapViewPtr, d, latLng.getLatitude(), latLng.getLongitude(), d2, d3);
    }

    public void easeTo(double d, LatLng latLng, long j, double d2, double d3, boolean z) {
        nativeEaseTo(this.nativeMapViewPtr, d, latLng.getLatitude(), latLng.getLongitude(), j, d2, d3, z);
    }

    public void flyTo(double d, LatLng latLng, long j, double d2, double d3) {
        nativeFlyTo(this.nativeMapViewPtr, d, latLng.getLatitude(), latLng.getLongitude(), j, d2, d3);
    }

    public double[] getCameraValues() {
        return nativeGetCameraValues(this.nativeMapViewPtr);
    }

    public Layer getLayer(String str) {
        return nativeGetLayer(this.nativeMapViewPtr, str);
    }

    public void addLayer(@NonNull Layer layer, @Nullable String str) {
        nativeAddLayer(this.nativeMapViewPtr, layer.getNativePtr(), str);
        layer.invalidate();
    }

    public void removeLayer(@NonNull String str) throws NoSuchLayerException {
        nativeRemoveLayer(this.nativeMapViewPtr, str);
    }

    public Source getSource(@NonNull String str) {
        return nativeGetSource(this.nativeMapViewPtr, str);
    }

    public void addSource(@NonNull Source source) {
        nativeAddSource(this.nativeMapViewPtr, source.getNativePtr());
    }

    public void removeSource(@NonNull String str) throws NoSuchSourceException {
        nativeRemoveSource(this.nativeMapViewPtr, str);
    }

    public void addImage(@NonNull String str, @NonNull Bitmap bitmap) {
        if (bitmap.getConfig() != Config.ARGB_8888) {
            bitmap = bitmap.copy(Config.ARGB_8888, false);
        }
        Buffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        nativeAddImage(this.nativeMapViewPtr, str, bitmap.getWidth(), bitmap.getHeight(), (bitmap.getDensity() == 0 ? 0.0f : (float) bitmap.getDensity()) / 160.0f, allocate.array());
    }

    public void removeImage(String str) {
        nativeRemoveImage(this.nativeMapViewPtr, str);
    }

    @NonNull
    public List<Feature> queryRenderedFeatures(PointF pointF, String... strArr) {
        Feature[] nativeQueryRenderedFeaturesForPoint = nativeQueryRenderedFeaturesForPoint(this.nativeMapViewPtr, pointF.x / this.pixelRatio, pointF.y / this.pixelRatio, strArr);
        return nativeQueryRenderedFeaturesForPoint != null ? Arrays.asList(nativeQueryRenderedFeaturesForPoint) : new ArrayList();
    }

    @NonNull
    public List<Feature> queryRenderedFeatures(RectF rectF, String... strArr) {
        Feature[] nativeQueryRenderedFeaturesForBox = nativeQueryRenderedFeaturesForBox(this.nativeMapViewPtr, rectF.left / this.pixelRatio, rectF.top / this.pixelRatio, rectF.right / this.pixelRatio, rectF.bottom / this.pixelRatio, strArr);
        return nativeQueryRenderedFeaturesForBox != null ? Arrays.asList(nativeQueryRenderedFeaturesForBox) : new ArrayList();
    }

    public void scheduleTakeSnapshot() {
        nativeScheduleTakeSnapshot(this.nativeMapViewPtr);
    }

    public void setApiBaseUrl(String str) {
        nativeSetAPIBaseURL(this.nativeMapViewPtr, str);
    }

    protected void onInvalidate() {
        this.mapView.onInvalidate();
    }

    protected void onMapChanged(int i) {
        this.mapView.onMapChanged(i);
    }

    protected void onFpsChanged(double d) {
        this.mapView.onFpsChanged(d);
    }

    protected void onSnapshotReady(byte[] bArr) {
        this.mapView.onSnapshotReady(bArr);
    }
}
