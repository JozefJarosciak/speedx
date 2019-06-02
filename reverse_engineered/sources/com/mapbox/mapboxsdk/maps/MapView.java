package com.mapbox.mapboxsdk.maps;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ZoomButtonsController;
import com.mapbox.mapboxsdk.C1485R;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.R$array;
import com.mapbox.mapboxsdk.R$string;
import com.mapbox.mapboxsdk.R$style;
import com.mapbox.mapboxsdk.annotations.Annotation;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.InfoWindow;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerView;
import com.mapbox.mapboxsdk.annotations.Polygon;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.exceptions.IconBitmapChangedException;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationListener;
import com.mapbox.mapboxsdk.location.LocationServices;
import com.mapbox.mapboxsdk.maps.MapboxMap.CancelableCallback;
import com.mapbox.mapboxsdk.maps.MapboxMap.OnMyBearingTrackingModeChangeListener;
import com.mapbox.mapboxsdk.maps.MapboxMap.OnMyLocationChangeListener;
import com.mapbox.mapboxsdk.maps.MapboxMap.OnMyLocationTrackingModeChangeListener;
import com.mapbox.mapboxsdk.maps.MapboxMap.SnapshotReadyCallback;
import com.mapbox.mapboxsdk.maps.widgets.CompassView;
import com.mapbox.mapboxsdk.maps.widgets.MyLocationView;
import com.mapbox.mapboxsdk.maps.widgets.MyLocationViewSettings;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.mapboxsdk.telemetry.MapboxEventManager;
import com.mapbox.mapboxsdk.utils.ColorUtils;
import com.p014a.p015a.p016a.p017a.C0707d;
import com.p014a.p015a.p016a.p017a.C0708b;
import com.p014a.p015a.p016a.p017a.C0708b.C0706b;
import com.p014a.p015a.p016a.p017a.C0710c;
import com.p014a.p015a.p016a.p017a.C0710c.C0709a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapView extends FrameLayout {
    public static final int DID_FAIL_LOADING_MAP = 7;
    public static final int DID_FINISH_LOADING_MAP = 6;
    public static final int DID_FINISH_LOADING_STYLE = 14;
    public static final int DID_FINISH_RENDERING_FRAME = 9;
    public static final int DID_FINISH_RENDERING_FRAME_FULLY_RENDERED = 10;
    public static final int DID_FINISH_RENDERING_MAP = 12;
    public static final int DID_FINISH_RENDERING_MAP_FULLY_RENDERED = 13;
    public static final int REGION_DID_CHANGE = 3;
    public static final int REGION_DID_CHANGE_ANIMATED = 4;
    public static final int REGION_IS_CHANGING = 2;
    public static final int REGION_WILL_CHANGE = 0;
    public static final int REGION_WILL_CHANGE_ANIMATED = 1;
    public static final int WILL_START_LOADING_MAP = 5;
    public static final int WILL_START_RENDERING_FRAME = 8;
    public static final int WILL_START_RENDERING_MAP = 11;
    private ImageView attributionsView;
    private int averageIconHeight;
    private int averageIconWidth;
    private CompassView compassView;
    private ConnectivityReceiver connectivityReceiver;
    private int contentPaddingBottom;
    private int contentPaddingLeft;
    private int contentPaddingRight;
    private int contentPaddingTop;
    private TrackballLongPressTimeOut currentTrackballLongPressTimeOut;
    private boolean destroyed;
    private boolean dragStarted = false;
    private PointF focalPoint;
    private GestureDetectorCompat gestureDetector;
    private boolean hasSurface = false;
    private List<Icon> icons;
    private boolean initialLoad;
    private ImageView logoView;
    private MapboxMap mapboxMap;
    private ViewGroup markerViewContainer;
    private LocationListener myLocationListener;
    private MyLocationView myLocationView;
    private NativeMapView nativeMapView;
    private CopyOnWriteArrayList<OnMapChangedListener> onMapChangedListener;
    private List<OnMapReadyCallback> onMapReadyCallbackList;
    private Projection projection;
    private boolean quickZoom = false;
    private C0708b rotateGestureDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private float screenDensity = 1.0f;
    private boolean scrollInProgress = false;
    private C0710c shoveGestureDetector;
    private SnapshotRequest snapshotRequest;
    private String styleUrl = Style.MAPBOX_STREETS;
    private boolean styleWasSet = false;
    private boolean twoTap = false;
    private ZoomButtonsController zoomButtonsController;
    private boolean zoomStarted = false;

    public interface OnMapChangedListener {
        void onMapChanged(int i);
    }

    /* renamed from: com.mapbox.mapboxsdk.maps.MapView$1 */
    class C14871 implements OnMapChangedListener {
        C14871() {
        }

        public void onMapChanged(int i) {
            Iterator it;
            if (i == 14 && MapView.this.initialLoad) {
                MapView.this.initialLoad = false;
                MapView.this.reloadIcons();
                MapView.this.reloadMarkers();
                MapView.this.adjustTopOffsetPixels();
                if (MapView.this.onMapReadyCallbackList.size() > 0) {
                    it = MapView.this.onMapReadyCallbackList.iterator();
                    while (it.hasNext()) {
                        ((OnMapReadyCallback) it.next()).onMapReady(MapView.this.mapboxMap);
                        it.remove();
                    }
                }
                MapView.this.invalidateCameraPosition();
            } else if (i == 2 || i == 3 || i == 6) {
                MapView.this.mapboxMap.getMarkerViewManager().scheduleViewMarkerInvalidation();
                MapView.this.compassView.update(MapView.this.getDirection());
                MapView.this.myLocationView.update();
                MapView.this.mapboxMap.getMarkerViewManager().update();
                for (InfoWindow update : MapView.this.mapboxMap.getInfoWindows()) {
                    update.update();
                }
            }
        }
    }

    private static class AttributionOnClickListener implements OnClickListener, View.OnClickListener {
        private static final int ATTRIBUTION_INDEX_IMPROVE_THIS_MAP = 2;
        private static final int ATTRIBUTION_INDEX_TELEMETRY_SETTINGS = 3;
        private MapView mapView;

        public AttributionOnClickListener(MapView mapView) {
            this.mapView = mapView;
        }

        public void onClick(View view) {
            Builder builder = new Builder(this.mapView.getContext(), R$style.TelemAlertDialogStyle);
            builder.setTitle(R$string.attributionsDialogTitle);
            builder.setAdapter(new ArrayAdapter(this.mapView.getContext(), C1485R.layout.attribution_list_item, this.mapView.getContext().getResources().getStringArray(R$array.attribution_names)), this);
            builder.show();
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Context context = ((Dialog) dialogInterface).getContext();
            if (i == 3) {
                Builder builder = new Builder(context, R$style.TelemAlertDialogStyle);
                builder.setTitle(R$string.attributionTelemetryTitle);
                builder.setMessage(R$string.attributionTelemetryMessage);
                builder.setPositiveButton(R$string.attributionTelemetryPositive, new MapView$AttributionOnClickListener$1(this));
                builder.setNeutralButton(R$string.attributionTelemetryNeutral, new MapView$AttributionOnClickListener$2(this, context));
                builder.setNegativeButton(R$string.attributionTelemetryNegative, new MapView$AttributionOnClickListener$3(this));
                AlertDialog show = builder.show();
                show.getButton(-1).setTextColor(this.mapView.getAttributionTintColor());
                show.getButton(-2).setTextColor(this.mapView.getAttributionTintColor());
                show.getButton(-3).setTextColor(this.mapView.getAttributionTintColor());
                return;
            }
            String str = context.getResources().getStringArray(R$array.attribution_links)[i];
            if (i == 2) {
                LatLng latLng = this.mapView.getMapboxMap().getCameraPosition().target;
                str = String.format(str, new Object[]{Double.valueOf(latLng.getLongitude()), Double.valueOf(latLng.getLatitude()), Integer.valueOf((int) this.mapView.getZoom())});
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        }
    }

    private class ConnectivityReceiver extends BroadcastReceiver {
        private ConnectivityReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = false;
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                MapView mapView = MapView.this;
                if (!booleanExtra) {
                    z = true;
                }
                mapView.onConnectivityChanged(z);
            }
        }
    }

    private class GestureListener extends SimpleOnGestureListener {
        private GestureListener() {
        }

        @SuppressLint({"ResourceType"})
        public boolean onDown(MotionEvent motionEvent) {
            if (MapView.this.mapboxMap.getUiSettings().isZoomControlsEnabled()) {
                MapView.this.zoomButtonsController.setVisible(true);
            }
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (MapView.this.destroyed || !MapView.this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                return false;
            }
            switch (motionEvent.getAction()) {
                case 1:
                    if (!MapView.this.quickZoom) {
                        if (MapView.this.focalPoint == null) {
                            MapView.this.zoom(true, motionEvent.getX(), motionEvent.getY());
                            break;
                        }
                        MapView.this.zoom(true, MapView.this.focalPoint.x, MapView.this.focalPoint.y);
                        break;
                    }
                    MapView.this.quickZoom = false;
                    break;
            }
            MapView.this.trackGestureEvent(MapboxEvent.GESTURE_DOUBLETAP, motionEvent.getX(), motionEvent.getY());
            return true;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (MapView.this.destroyed) {
                return false;
            }
            MapView.this.nativeMapView.cancelTransitions();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            int i = 0;
            List<Marker> selectedMarkers = MapView.this.mapboxMap.getSelectedMarkers();
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            float access$2300 = 4.0f * MapView.this.screenDensity;
            float access$23002 = 10.0f * MapView.this.screenDensity;
            List<Marker> markersInRect = MapView.this.getMarkersInRect(new RectF(((pointF.x - ((float) (MapView.this.averageIconWidth / 2))) - access$2300) / MapView.this.screenDensity, ((pointF.y - ((float) (MapView.this.averageIconHeight / 2))) - access$23002) / MapView.this.screenDensity, (access$2300 + (pointF.x + ((float) (MapView.this.averageIconWidth / 2)))) / MapView.this.screenDensity, (access$23002 + (pointF.y + ((float) (MapView.this.averageIconHeight / 2)))) / MapView.this.screenDensity));
            long j = -1;
            if (markersInRect != null && markersInRect.size() > 0) {
                Collections.sort(markersInRect);
                for (Marker marker : markersInRect) {
                    boolean z = false;
                    for (Marker equals : selectedMarkers) {
                        boolean z2;
                        if (equals.equals(marker)) {
                            z2 = true;
                        } else {
                            z2 = z;
                        }
                        z = z2;
                    }
                    if (!z) {
                        j = marker.getId();
                        break;
                    }
                }
            }
            if (j >= 0) {
                List annotations = MapView.this.mapboxMap.getAnnotations();
                int size = annotations.size();
                while (i < size) {
                    Annotation annotation = (Annotation) annotations.get(i);
                    if (!(annotation instanceof Marker) || annotation.getId() != j) {
                        i++;
                    } else if (selectedMarkers.isEmpty() || !selectedMarkers.contains(annotation)) {
                        if (annotation instanceof MarkerView) {
                            MapView.this.mapboxMap.getMarkerViewManager().onClickMarkerView((MarkerView) annotation);
                        } else {
                            MapView.this.mapboxMap.selectMarker((Marker) annotation);
                        }
                    }
                }
            } else {
                if (MapView.this.mapboxMap.getUiSettings().isDeselectMarkersOnTap()) {
                    MapView.this.mapboxMap.deselectMarkers();
                }
                MapboxMap$OnMapClickListener onMapClickListener = MapView.this.mapboxMap.getOnMapClickListener();
                if (onMapClickListener != null) {
                    onMapClickListener.onMapClick(MapView.this.projection.fromScreenLocation(pointF));
                }
            }
            MapView.this.trackGestureEvent(MapboxEvent.GESTURE_SINGLETAP, motionEvent.getX(), motionEvent.getY());
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            MapboxMap$OnMapLongClickListener onMapLongClickListener = MapView.this.mapboxMap.getOnMapLongClickListener();
            if (onMapLongClickListener != null && !MapView.this.quickZoom) {
                onMapLongClickListener.onMapLongClick(MapView.this.projection.fromScreenLocation(new PointF(motionEvent.getX(), motionEvent.getY())));
            }
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (MapView.this.destroyed || !MapView.this.mapboxMap.getTrackingSettings().isScrollGestureCurrentlyEnabled()) {
                return false;
            }
            MapView.this.resetTrackingModesIfRequired(true, false);
            float f3 = f * 0.25f;
            float f4 = f2 * 0.25f;
            double sqrt = Math.sqrt((double) ((f3 * f3) + (f4 * f4))) / (((double) 1048576000) * 2500.0d);
            MapView.this.nativeMapView.cancelTransitions();
            MapView.this.nativeMapView.moveBy(((((double) f3) * sqrt) / 2.0d) / ((double) MapView.this.screenDensity), ((((double) f4) * sqrt) / 2.0d) / ((double) MapView.this.screenDensity), (long) (sqrt * 1000.0d));
            MapboxMap$OnFlingListener onFlingListener = MapView.this.mapboxMap.getOnFlingListener();
            if (onFlingListener != null) {
                onFlingListener.onFling();
            }
            MapView.this.trackGestureEvent(MapboxEvent.GESTURE_PAN_START, motionEvent.getX(), motionEvent.getY());
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!MapView.this.scrollInProgress) {
                MapView.this.scrollInProgress = true;
            }
            if (MapView.this.destroyed || !MapView.this.mapboxMap.getTrackingSettings().isScrollGestureCurrentlyEnabled() || MapView.this.dragStarted) {
                return false;
            }
            MapView.this.requestDisallowInterceptTouchEvent(true);
            MapView.this.resetTrackingModesIfRequired(true, false);
            MapView.this.nativeMapView.cancelTransitions();
            MapView.this.nativeMapView.moveBy((double) ((-f) / MapView.this.screenDensity), (double) ((-f2) / MapView.this.screenDensity));
            MapboxMap$OnScrollListener onScrollListener = MapView.this.mapboxMap.getOnScrollListener();
            if (onScrollListener != null) {
                onScrollListener.onScroll();
            }
            return true;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MapChange {
    }

    private class OnZoomListener implements android.widget.ZoomButtonsController.OnZoomListener {
        private OnZoomListener() {
        }

        public void onVisibilityChanged(boolean z) {
        }

        public void onZoom(boolean z) {
            if (MapView.this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                MapView.this.zoom(z);
            }
        }
    }

    private class RotateGestureListener extends C0706b {
        long beginTime;
        boolean started;
        float totalAngle;

        private RotateGestureListener() {
            this.beginTime = 0;
            this.totalAngle = 0.0f;
            this.started = false;
        }

        public boolean onRotateBegin(C0708b c0708b) {
            if (MapView.this.destroyed || !MapView.this.mapboxMap.getTrackingSettings().isRotateGestureCurrentlyEnabled()) {
                return false;
            }
            this.beginTime = c0708b.m2767c();
            MapView.this.trackGestureEvent(MapboxEvent.GESTURE_ROTATION_START, c0708b.m2775e(), c0708b.m2776f());
            return true;
        }

        public void onRotateEnd(C0708b c0708b) {
            this.beginTime = 0;
            this.totalAngle = 0.0f;
            this.started = false;
        }

        public boolean onRotate(C0708b c0708b) {
            if (MapView.this.destroyed || !MapView.this.mapboxMap.getTrackingSettings().isRotateGestureCurrentlyEnabled() || MapView.this.dragStarted) {
                return false;
            }
            this.totalAngle += c0708b.m2780d();
            if (!MapView.this.zoomStarted && (this.totalAngle > 20.0f || this.totalAngle < -20.0f)) {
                this.started = true;
            }
            long c = c0708b.m2767c() - this.beginTime;
            if ((!this.started && c <= ((long) ViewConfiguration.getTapTimeout())) || !this.started) {
                return false;
            }
            MapView.this.nativeMapView.cancelTransitions();
            MapView.this.resetTrackingModesIfRequired(true, true);
            double bearing = MapView.this.nativeMapView.getBearing() + ((double) c0708b.m2780d());
            if (MapView.this.focalPoint != null) {
                MapView.this.setBearing(bearing, MapView.this.focalPoint.x / MapView.this.screenDensity, MapView.this.focalPoint.y / MapView.this.screenDensity);
            } else {
                MapView.this.setBearing(bearing, c0708b.m2775e() / MapView.this.screenDensity, c0708b.m2776f() / MapView.this.screenDensity);
            }
            return true;
        }
    }

    private class ScaleGestureListener extends SimpleOnScaleGestureListener {
        long beginTime;
        float scaleFactor;

        private ScaleGestureListener() {
            this.beginTime = 0;
            this.scaleFactor = 1.0f;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (MapView.this.destroyed || !MapView.this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                return false;
            }
            this.beginTime = scaleGestureDetector.getEventTime();
            MapView.this.trackGestureEvent(MapboxEvent.GESTURE_PINCH_START, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            this.beginTime = 0;
            this.scaleFactor = 1.0f;
            MapView.this.zoomStarted = false;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            UiSettings uiSettings = MapView.this.mapboxMap.getUiSettings();
            if (MapView.this.destroyed || !uiSettings.isZoomGesturesEnabled()) {
                return super.onScale(scaleGestureDetector);
            }
            this.scaleFactor *= scaleGestureDetector.getScaleFactor();
            if (this.scaleFactor > 1.05f || this.scaleFactor < 0.95f) {
                MapView.this.zoomStarted = true;
            }
            long eventTime = scaleGestureDetector.getEventTime() - this.beginTime;
            if ((!MapView.this.zoomStarted && eventTime <= ((long) ViewConfiguration.getTapTimeout())) || !MapView.this.zoomStarted || MapView.this.dragStarted) {
                return false;
            }
            boolean z;
            MapView.this.nativeMapView.cancelTransitions();
            MapView.this.quickZoom = !MapView.this.twoTap;
            MapView mapView = MapView.this;
            if (MapView.this.quickZoom) {
                z = false;
            } else {
                z = true;
            }
            mapView.resetTrackingModesIfRequired(z, false);
            if (MapView.this.focalPoint != null) {
                MapView.this.nativeMapView.scaleBy((double) scaleGestureDetector.getScaleFactor(), (double) (MapView.this.focalPoint.x / MapView.this.screenDensity), (double) (MapView.this.focalPoint.y / MapView.this.screenDensity));
            } else if (MapView.this.quickZoom) {
                MapView.this.nativeMapView.scaleBy((double) scaleGestureDetector.getScaleFactor(), (double) (((float) (MapView.this.getWidth() / 2)) / MapView.this.screenDensity), (double) (((float) (MapView.this.getHeight() / 2)) / MapView.this.screenDensity));
            } else {
                MapView.this.nativeMapView.scaleBy((double) scaleGestureDetector.getScaleFactor(), (double) (scaleGestureDetector.getFocusX() / MapView.this.screenDensity), (double) (scaleGestureDetector.getFocusY() / MapView.this.screenDensity));
            }
            return true;
        }
    }

    private class ShoveGestureListener implements C0709a {
        long beginTime;
        boolean started;
        float totalDelta;

        private ShoveGestureListener() {
            this.beginTime = 0;
            this.totalDelta = 0.0f;
            this.started = false;
        }

        public boolean onShoveBegin(C0710c c0710c) {
            if (!MapView.this.mapboxMap.getUiSettings().isTiltGesturesEnabled()) {
                return false;
            }
            this.beginTime = c0710c.m2767c();
            MapView.this.trackGestureEvent(MapboxEvent.GESTURE_PITCH_START, c0710c.m2775e(), c0710c.m2776f());
            return true;
        }

        public void onShoveEnd(C0710c c0710c) {
            this.beginTime = 0;
            this.totalDelta = 0.0f;
            this.started = false;
            MapView.this.dragStarted = false;
        }

        public boolean onShove(C0710c c0710c) {
            if (MapView.this.destroyed || !MapView.this.mapboxMap.getUiSettings().isTiltGesturesEnabled()) {
                return false;
            }
            this.totalDelta += c0710c.m2786d();
            if (!MapView.this.zoomStarted && (this.totalDelta > 10.0f || this.totalDelta < -10.0f)) {
                this.started = true;
            }
            long c = c0710c.m2767c() - this.beginTime;
            if ((!this.started && c <= ((long) ViewConfiguration.getTapTimeout())) || !this.started) {
                return false;
            }
            MapView.this.nativeMapView.cancelTransitions();
            MapView.this.mapboxMap.setTilt(Math.max(0.0d, Math.min(60.0d, MapView.this.getTilt() - (0.1d * ((double) c0710c.m2786d())))));
            MapView.this.dragStarted = true;
            return true;
        }
    }

    private class SnapshotRequest {
        private Bitmap bitmap;
        private SnapshotReadyCallback callback;

        public SnapshotRequest(Bitmap bitmap, SnapshotReadyCallback snapshotReadyCallback) {
            this.bitmap = bitmap;
            this.callback = snapshotReadyCallback;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public SnapshotReadyCallback getCallback() {
            return this.callback;
        }
    }

    private class SurfaceCallback implements Callback {
        private Surface surface;

        private SurfaceCallback() {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            NativeMapView access$1500 = MapView.this.nativeMapView;
            Surface surface = surfaceHolder.getSurface();
            this.surface = surface;
            access$1500.createSurface(surface);
            MapView.this.hasSurface = true;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (!MapView.this.destroyed) {
                MapView.this.nativeMapView.resizeFramebuffer(i2, i3);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            MapView.this.hasSurface = false;
            if (MapView.this.nativeMapView != null) {
                MapView.this.nativeMapView.destroySurface();
            }
            this.surface.release();
        }
    }

    private class SurfaceTextureListener implements android.view.TextureView.SurfaceTextureListener {
        private Surface surface;

        private SurfaceTextureListener() {
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            NativeMapView access$1500 = MapView.this.nativeMapView;
            Surface surface = new Surface(surfaceTexture);
            this.surface = surface;
            access$1500.createSurface(surface);
            MapView.this.nativeMapView.resizeFramebuffer(i, i2);
            MapView.this.hasSurface = true;
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            MapView.this.hasSurface = false;
            if (MapView.this.nativeMapView != null) {
                MapView.this.nativeMapView.destroySurface();
            }
            this.surface.release();
            return true;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            if (!MapView.this.destroyed) {
                MapView.this.nativeMapView.resizeFramebuffer(i, i2);
            }
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            if (!MapView.this.destroyed) {
                MapView.this.compassView.update(MapView.this.getDirection());
                MapView.this.myLocationView.update();
                MapView.this.mapboxMap.getMarkerViewManager().update();
                for (InfoWindow update : MapView.this.mapboxMap.getInfoWindows()) {
                    update.update();
                }
            }
        }
    }

    private class TrackballLongPressTimeOut implements Runnable {
        private boolean cancelled = false;

        public void cancel() {
            this.cancelled = true;
        }

        public void run() {
            if (!this.cancelled) {
                MapView.this.zoom(false);
                MapView.this.currentTrackballLongPressTimeOut = null;
            }
        }
    }

    private static class ZoomInvalidator implements Runnable {
        private MapboxMap mapboxMap;

        public ZoomInvalidator(MapboxMap mapboxMap) {
            this.mapboxMap = mapboxMap;
        }

        public void run() {
            this.mapboxMap.getCameraPosition();
        }
    }

    @UiThread
    public MapView(@NonNull Context context) {
        super(context);
        initialize(context, MapboxMapOptions.createFromAttributes(context, null));
    }

    @UiThread
    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, MapboxMapOptions.createFromAttributes(context, attributeSet));
    }

    @UiThread
    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, MapboxMapOptions.createFromAttributes(context, attributeSet));
    }

    @UiThread
    public MapView(@NonNull Context context, @Nullable MapboxMapOptions mapboxMapOptions) {
        super(context);
        initialize(context, mapboxMapOptions);
    }

    private void initialize(@NonNull Context context, @NonNull MapboxMapOptions mapboxMapOptions) {
        if (isInEditMode()) {
            LayoutInflater.from(context).inflate(C1485R.layout.mapview_preview, this);
            return;
        }
        this.initialLoad = true;
        this.onMapReadyCallbackList = new ArrayList();
        this.onMapChangedListener = new CopyOnWriteArrayList();
        this.mapboxMap = new MapboxMap(this);
        this.projection = this.mapboxMap.getProjection();
        this.icons = new ArrayList();
        View inflate = LayoutInflater.from(context).inflate(C1485R.layout.mapview_internal, this);
        setWillNotDraw(false);
        if (mapboxMapOptions.getTextureMode()) {
            View textureView = new TextureView(context);
            textureView.setSurfaceTextureListener(new SurfaceTextureListener());
            addView(textureView, 0);
        } else {
            SurfaceView surfaceView = (SurfaceView) findViewById(C1485R.id.surfaceView);
            surfaceView.getHolder().addCallback(new SurfaceCallback());
            surfaceView.setVisibility(0);
        }
        this.nativeMapView = new NativeMapView(this);
        loadIcon(IconFactory.recreate(IconFactory.ICON_MARKERVIEW_ID, IconFactory.ICON_MARKERVIEW_BITMAP));
        setClickable(true);
        setLongClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.gestureDetector = new GestureDetectorCompat(context, new GestureListener());
        this.gestureDetector.setIsLongpressEnabled(true);
        this.scaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureListener());
        ScaleGestureDetectorCompat.setQuickScaleEnabled(this.scaleGestureDetector, true);
        this.rotateGestureDetector = new C0708b(context, new RotateGestureListener());
        this.shoveGestureDetector = new C0710c(context, new ShoveGestureListener());
        this.zoomButtonsController = new ZoomButtonsController(this);
        this.zoomButtonsController.setZoomSpeed(300);
        this.zoomButtonsController.setOnZoomListener(new OnZoomListener());
        onConnectivityChanged(isConnected());
        this.markerViewContainer = (ViewGroup) inflate.findViewById(C1485R.id.markerViewContainer);
        this.myLocationView = (MyLocationView) inflate.findViewById(C1485R.id.userLocationView);
        this.myLocationView.setMapboxMap(this.mapboxMap);
        this.compassView = (CompassView) inflate.findViewById(C1485R.id.compassView);
        this.compassView.setMapboxMap(this.mapboxMap);
        this.logoView = (ImageView) inflate.findViewById(C1485R.id.logoView);
        this.attributionsView = (ImageView) inflate.findViewById(C1485R.id.attributionView);
        this.attributionsView.setOnClickListener(new AttributionOnClickListener(this));
        this.screenDensity = context.getResources().getDisplayMetrics().density;
        setInitialState(mapboxMapOptions);
        if (!context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
            this.mapboxMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }

    private void setInitialState(MapboxMapOptions mapboxMapOptions) {
        int dimension;
        this.mapboxMap.setDebugActive(mapboxMapOptions.getDebugActive());
        CameraPosition camera = mapboxMapOptions.getCamera();
        if (camera != null) {
            this.mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
            this.myLocationView.setTilt(camera.tilt);
        }
        Object apiBaseUrl = mapboxMapOptions.getApiBaseUrl();
        if (!TextUtils.isEmpty(apiBaseUrl)) {
            setApiBaseUrl(apiBaseUrl);
        }
        apiBaseUrl = mapboxMapOptions.getAccessToken();
        if (!TextUtils.isEmpty(apiBaseUrl)) {
            this.mapboxMap.setAccessToken(apiBaseUrl);
        }
        apiBaseUrl = mapboxMapOptions.getStyle();
        if (!TextUtils.isEmpty(apiBaseUrl)) {
            this.styleUrl = apiBaseUrl;
        }
        MyLocationViewSettings myLocationViewSettings = this.mapboxMap.getMyLocationViewSettings();
        myLocationViewSettings.setForegroundDrawable(mapboxMapOptions.getMyLocationForegroundDrawable(), mapboxMapOptions.getMyLocationForegroundBearingDrawable());
        myLocationViewSettings.setForegroundTintColor(mapboxMapOptions.getMyLocationForegroundTintColor());
        myLocationViewSettings.setBackgroundDrawable(mapboxMapOptions.getMyLocationBackgroundDrawable(), mapboxMapOptions.getMyLocationBackgroundPadding());
        myLocationViewSettings.setBackgroundTintColor(mapboxMapOptions.getMyLocationBackgroundTintColor());
        myLocationViewSettings.setAccuracyAlpha(mapboxMapOptions.getMyLocationAccuracyAlpha());
        myLocationViewSettings.setAccuracyTintColor(mapboxMapOptions.getMyLocationAccuracyTintColor());
        this.mapboxMap.setMyLocationEnabled(mapboxMapOptions.getLocationEnabled());
        UiSettings uiSettings = this.mapboxMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(mapboxMapOptions.getZoomGesturesEnabled());
        uiSettings.setZoomGestureChangeAllowed(mapboxMapOptions.getZoomGesturesEnabled());
        uiSettings.setScrollGesturesEnabled(mapboxMapOptions.getScrollGesturesEnabled());
        uiSettings.setScrollGestureChangeAllowed(mapboxMapOptions.getScrollGesturesEnabled());
        uiSettings.setRotateGesturesEnabled(mapboxMapOptions.getRotateGesturesEnabled());
        uiSettings.setRotateGestureChangeAllowed(mapboxMapOptions.getRotateGesturesEnabled());
        uiSettings.setTiltGesturesEnabled(mapboxMapOptions.getTiltGesturesEnabled());
        uiSettings.setTiltGestureChangeAllowed(mapboxMapOptions.getTiltGesturesEnabled());
        uiSettings.setZoomControlsEnabled(mapboxMapOptions.getZoomControlsEnabled());
        this.mapboxMap.setMaxZoom((double) mapboxMapOptions.getMaxZoom());
        this.mapboxMap.setMinZoom((double) mapboxMapOptions.getMinZoom());
        uiSettings.setCompassEnabled(mapboxMapOptions.getCompassEnabled());
        uiSettings.setCompassGravity(mapboxMapOptions.getCompassGravity());
        int[] compassMargins = mapboxMapOptions.getCompassMargins();
        if (compassMargins != null) {
            uiSettings.setCompassMargins(compassMargins[0], compassMargins[1], compassMargins[2], compassMargins[3]);
        } else {
            dimension = (int) getResources().getDimension(C1485R.dimen.ten_dp);
            uiSettings.setCompassMargins(dimension, dimension, dimension, dimension);
        }
        uiSettings.setCompassFadeFacingNorth(mapboxMapOptions.getCompassFadeFacingNorth());
        uiSettings.setLogoEnabled(mapboxMapOptions.getLogoEnabled());
        uiSettings.setLogoGravity(mapboxMapOptions.getLogoGravity());
        compassMargins = mapboxMapOptions.getLogoMargins();
        if (compassMargins != null) {
            uiSettings.setLogoMargins(compassMargins[0], compassMargins[1], compassMargins[2], compassMargins[3]);
        } else {
            dimension = (int) getResources().getDimension(C1485R.dimen.sixteen_dp);
            uiSettings.setLogoMargins(dimension, dimension, dimension, dimension);
        }
        uiSettings.setAttributionEnabled(mapboxMapOptions.getAttributionEnabled());
        uiSettings.setAttributionGravity(mapboxMapOptions.getAttributionGravity());
        compassMargins = mapboxMapOptions.getAttributionMargins();
        if (compassMargins != null) {
            uiSettings.setAttributionMargins(compassMargins[0], compassMargins[1], compassMargins[2], compassMargins[3]);
        } else {
            Resources resources = getResources();
            int dimension2 = (int) resources.getDimension(C1485R.dimen.seven_dp);
            uiSettings.setAttributionMargins((int) resources.getDimension(C1485R.dimen.seventy_six_dp), dimension2, dimension2, dimension2);
        }
        dimension = mapboxMapOptions.getAttributionTintColor();
        if (dimension == -1) {
            dimension = ColorUtils.getPrimaryColor(getContext());
        }
        uiSettings.setAttributionTintColor(dimension);
    }

    @UiThread
    public void onCreate(@Nullable Bundle bundle) {
        String accessToken = this.mapboxMap.getAccessToken();
        if (TextUtils.isEmpty(accessToken)) {
            accessToken = MapboxAccountManager.getInstance().getAccessToken();
            this.mapboxMap.setAccessToken(accessToken);
        } else {
            MapboxAccountManager.start(getContext(), accessToken);
        }
        MapboxAccountManager.validateAccessToken(accessToken);
        if (bundle != null && bundle.getBoolean(MapboxConstants.STATE_HAS_SAVED_STATE)) {
            CameraPosition cameraPosition = (CameraPosition) bundle.getParcelable(MapboxConstants.STATE_CAMERA_POSITION);
            if (cameraPosition != null) {
                this.mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder(cameraPosition).build()));
            }
            UiSettings uiSettings = this.mapboxMap.getUiSettings();
            uiSettings.setZoomGesturesEnabled(bundle.getBoolean(MapboxConstants.STATE_ZOOM_ENABLED));
            uiSettings.setZoomGestureChangeAllowed(bundle.getBoolean(MapboxConstants.STATE_ZOOM_ENABLED_CHANGE));
            uiSettings.setScrollGesturesEnabled(bundle.getBoolean(MapboxConstants.STATE_SCROLL_ENABLED));
            uiSettings.setScrollGestureChangeAllowed(bundle.getBoolean(MapboxConstants.STATE_SCROLL_ENABLED_CHANGE));
            uiSettings.setRotateGesturesEnabled(bundle.getBoolean(MapboxConstants.STATE_ROTATE_ENABLED));
            uiSettings.setRotateGestureChangeAllowed(bundle.getBoolean(MapboxConstants.STATE_ROTATE_ENABLED_CHANGE));
            uiSettings.setTiltGesturesEnabled(bundle.getBoolean(MapboxConstants.STATE_TILT_ENABLED));
            uiSettings.setTiltGestureChangeAllowed(bundle.getBoolean(MapboxConstants.STATE_TILT_ENABLED_CHANGE));
            uiSettings.setZoomControlsEnabled(bundle.getBoolean(MapboxConstants.STATE_ZOOM_CONTROLS_ENABLED));
            uiSettings.setCompassEnabled(bundle.getBoolean(MapboxConstants.STATE_COMPASS_ENABLED));
            uiSettings.setCompassGravity(bundle.getInt(MapboxConstants.STATE_COMPASS_GRAVITY));
            uiSettings.setCompassMargins(bundle.getInt(MapboxConstants.STATE_COMPASS_MARGIN_LEFT), bundle.getInt(MapboxConstants.STATE_COMPASS_MARGIN_TOP), bundle.getInt(MapboxConstants.STATE_COMPASS_MARGIN_RIGHT), bundle.getInt(MapboxConstants.STATE_COMPASS_MARGIN_BOTTOM));
            uiSettings.setCompassFadeFacingNorth(bundle.getBoolean(MapboxConstants.STATE_COMPASS_FADE_WHEN_FACING_NORTH));
            uiSettings.setLogoEnabled(bundle.getBoolean(MapboxConstants.STATE_LOGO_ENABLED));
            uiSettings.setLogoGravity(bundle.getInt(MapboxConstants.STATE_LOGO_GRAVITY));
            uiSettings.setLogoMargins(bundle.getInt(MapboxConstants.STATE_LOGO_MARGIN_LEFT), bundle.getInt(MapboxConstants.STATE_LOGO_MARGIN_TOP), bundle.getInt(MapboxConstants.STATE_LOGO_MARGIN_RIGHT), bundle.getInt(MapboxConstants.STATE_LOGO_MARGIN_BOTTOM));
            uiSettings.setAttributionEnabled(bundle.getBoolean(MapboxConstants.STATE_ATTRIBUTION_ENABLED));
            uiSettings.setAttributionGravity(bundle.getInt(MapboxConstants.STATE_ATTRIBUTION_GRAVITY));
            uiSettings.setAttributionMargins(bundle.getInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_LEFT), bundle.getInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_TOP), bundle.getInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_RIGHT), bundle.getInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_BOTTOM));
            this.mapboxMap.setDebugActive(bundle.getBoolean(MapboxConstants.STATE_DEBUG_ACTIVE));
            this.styleUrl = bundle.getString(MapboxConstants.STATE_STYLE_URL);
            try {
                this.mapboxMap.setMyLocationEnabled(bundle.getBoolean(MapboxConstants.STATE_MY_LOCATION_ENABLED));
            } catch (SecurityException e) {
            }
            TrackingSettings trackingSettings = this.mapboxMap.getTrackingSettings();
            trackingSettings.setMyLocationTrackingMode(bundle.getInt(MapboxConstants.STATE_MY_LOCATION_TRACKING_MODE, 0));
            trackingSettings.setMyBearingTrackingMode(bundle.getInt(MapboxConstants.STATE_MY_BEARING_TRACKING_MODE, 0));
            trackingSettings.setDismissLocationTrackingOnGesture(bundle.getBoolean(MapboxConstants.STATE_MY_LOCATION_TRACKING_DISMISS, true));
            trackingSettings.setDismissBearingTrackingOnGesture(bundle.getBoolean(MapboxConstants.STATE_MY_BEARING_TRACKING_DISMISS, true));
        } else if (bundle == null) {
            Log.i(MapView.class.getCanonicalName(), "MapView start Telemetry...");
            MapboxEventManager.getMapboxEventManager().initialize(getContext(), getAccessToken());
        }
        this.nativeMapView.initializeDisplay();
        this.nativeMapView.initializeContext();
        addOnMapChangedListener(new C14871());
        if (bundle == null) {
            Hashtable hashtable = new Hashtable();
            hashtable.put("event", MapboxEvent.TYPE_MAP_LOAD);
            hashtable.put(MapboxEvent.ATTRIBUTE_CREATED, MapboxEventManager.generateCreateDate());
            MapboxEventManager.getMapboxEventManager().pushEvent(hashtable);
        }
    }

    @UiThread
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putBoolean(MapboxConstants.STATE_HAS_SAVED_STATE, true);
        bundle.putParcelable(MapboxConstants.STATE_CAMERA_POSITION, this.mapboxMap.getCameraPosition());
        bundle.putBoolean(MapboxConstants.STATE_DEBUG_ACTIVE, this.mapboxMap.isDebugActive());
        bundle.putString(MapboxConstants.STATE_STYLE_URL, this.styleUrl);
        bundle.putBoolean(MapboxConstants.STATE_MY_LOCATION_ENABLED, this.mapboxMap.isMyLocationEnabled());
        TrackingSettings trackingSettings = this.mapboxMap.getTrackingSettings();
        bundle.putInt(MapboxConstants.STATE_MY_LOCATION_TRACKING_MODE, trackingSettings.getMyLocationTrackingMode());
        bundle.putInt(MapboxConstants.STATE_MY_BEARING_TRACKING_MODE, trackingSettings.getMyBearingTrackingMode());
        bundle.putBoolean(MapboxConstants.STATE_MY_LOCATION_TRACKING_DISMISS, trackingSettings.isDismissLocationTrackingOnGesture());
        bundle.putBoolean(MapboxConstants.STATE_MY_BEARING_TRACKING_DISMISS, trackingSettings.isDismissBearingTrackingOnGesture());
        UiSettings uiSettings = this.mapboxMap.getUiSettings();
        bundle.putBoolean(MapboxConstants.STATE_ZOOM_ENABLED, uiSettings.isZoomGesturesEnabled());
        bundle.putBoolean(MapboxConstants.STATE_ZOOM_ENABLED_CHANGE, uiSettings.isZoomGestureChangeAllowed());
        bundle.putBoolean(MapboxConstants.STATE_SCROLL_ENABLED, uiSettings.isScrollGesturesEnabled());
        bundle.putBoolean(MapboxConstants.STATE_SCROLL_ENABLED_CHANGE, uiSettings.isScrollGestureChangeAllowed());
        bundle.putBoolean(MapboxConstants.STATE_ROTATE_ENABLED, uiSettings.isRotateGesturesEnabled());
        bundle.putBoolean(MapboxConstants.STATE_ROTATE_ENABLED_CHANGE, uiSettings.isRotateGestureChangeAllowed());
        bundle.putBoolean(MapboxConstants.STATE_TILT_ENABLED, uiSettings.isTiltGesturesEnabled());
        bundle.putBoolean(MapboxConstants.STATE_TILT_ENABLED_CHANGE, uiSettings.isTiltGestureChangeAllowed());
        bundle.putBoolean(MapboxConstants.STATE_ZOOM_CONTROLS_ENABLED, uiSettings.isZoomControlsEnabled());
        bundle.putBoolean(MapboxConstants.STATE_COMPASS_ENABLED, uiSettings.isCompassEnabled());
        bundle.putInt(MapboxConstants.STATE_COMPASS_GRAVITY, uiSettings.getCompassGravity());
        bundle.putInt(MapboxConstants.STATE_COMPASS_MARGIN_LEFT, uiSettings.getCompassMarginLeft());
        bundle.putInt(MapboxConstants.STATE_COMPASS_MARGIN_TOP, uiSettings.getCompassMarginTop());
        bundle.putInt(MapboxConstants.STATE_COMPASS_MARGIN_BOTTOM, uiSettings.getCompassMarginBottom());
        bundle.putInt(MapboxConstants.STATE_COMPASS_MARGIN_RIGHT, uiSettings.getCompassMarginRight());
        bundle.putBoolean(MapboxConstants.STATE_COMPASS_FADE_WHEN_FACING_NORTH, uiSettings.isCompassFadeWhenFacingNorth());
        bundle.putInt(MapboxConstants.STATE_LOGO_GRAVITY, uiSettings.getLogoGravity());
        bundle.putInt(MapboxConstants.STATE_LOGO_MARGIN_LEFT, uiSettings.getLogoMarginLeft());
        bundle.putInt(MapboxConstants.STATE_LOGO_MARGIN_TOP, uiSettings.getLogoMarginTop());
        bundle.putInt(MapboxConstants.STATE_LOGO_MARGIN_RIGHT, uiSettings.getLogoMarginRight());
        bundle.putInt(MapboxConstants.STATE_LOGO_MARGIN_BOTTOM, uiSettings.getLogoMarginBottom());
        bundle.putBoolean(MapboxConstants.STATE_LOGO_ENABLED, uiSettings.isLogoEnabled());
        bundle.putInt(MapboxConstants.STATE_ATTRIBUTION_GRAVITY, uiSettings.getAttributionGravity());
        bundle.putInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_LEFT, uiSettings.getAttributionMarginLeft());
        bundle.putInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_TOP, uiSettings.getAttributionMarginTop());
        bundle.putInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_RIGHT, uiSettings.getAttributionMarginRight());
        bundle.putInt(MapboxConstants.STATE_ATTRIBUTION_MARGIN_BOTTOM, uiSettings.getAttributionMarginBottom());
        bundle.putBoolean(MapboxConstants.STATE_ATTRIBUTION_ENABLED, uiSettings.isAttributionEnabled());
    }

    @UiThread
    public void onDestroy() {
        this.destroyed = true;
        this.nativeMapView.terminateContext();
        this.nativeMapView.terminateDisplay();
        this.nativeMapView.destroySurface();
        this.nativeMapView.destroy();
        this.nativeMapView = null;
    }

    @UiThread
    public void onPause() {
        if (this.connectivityReceiver != null) {
            getContext().unregisterReceiver(this.connectivityReceiver);
            this.connectivityReceiver = null;
        }
        this.myLocationView.onPause();
    }

    @UiThread
    public void onResume() {
        this.connectivityReceiver = new ConnectivityReceiver();
        getContext().registerReceiver(this.connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.nativeMapView.update();
        this.myLocationView.onResume();
        if (!this.styleWasSet) {
            setStyleUrl(this.styleUrl);
        }
    }

    void setFocalPoint(PointF pointF) {
        if (pointF == null) {
            UiSettings uiSettings = this.mapboxMap.getUiSettings();
            if (uiSettings.getFocalPoint() != null) {
                pointF = uiSettings.getFocalPoint();
            }
        }
        this.focalPoint = pointF;
    }

    @UiThread
    public void onLowMemory() {
        this.nativeMapView.onLowMemory();
    }

    protected void onFpsChanged(final double d) {
        post(new Runnable() {
            public void run() {
                MapboxMap$OnFpsChangedListener onFpsChangedListener = MapView.this.mapboxMap.getOnFpsChangedListener();
                if (onFpsChangedListener != null) {
                    onFpsChangedListener.onFpsChanged(d);
                }
            }
        });
    }

    LatLng getLatLng() {
        return this.nativeMapView.getLatLng();
    }

    double getTilt() {
        return this.nativeMapView.getPitch();
    }

    void setTilt(Double d) {
        this.mapboxMap.getMarkerViewManager().setTilt(d.floatValue());
        this.myLocationView.setTilt(d.doubleValue());
        this.nativeMapView.setPitch(d.doubleValue(), 0);
    }

    double getDirection() {
        if (this.destroyed) {
            return 0.0d;
        }
        double d = -this.nativeMapView.getBearing();
        while (d > 360.0d) {
            d -= 360.0d;
        }
        while (d < 0.0d) {
            d += 360.0d;
        }
        return d;
    }

    void setDirection(@FloatRange(from = 0.0d, to = 360.0d) double d) {
        if (!this.destroyed) {
            setDirection(d, false);
        }
    }

    void setDirection(@FloatRange(from = 0.0d, to = 360.0d) double d, boolean z) {
        if (!this.destroyed) {
            long j = z ? 300 : 0;
            this.nativeMapView.cancelTransitions();
            this.nativeMapView.setBearing(-d, j);
        }
    }

    void resetNorth() {
        if (!this.destroyed) {
            this.myLocationView.setBearing(0.0d);
            this.nativeMapView.cancelTransitions();
            this.nativeMapView.resetNorth();
        }
    }

    int getContentPaddingLeft() {
        return this.contentPaddingLeft;
    }

    int getContentPaddingTop() {
        return this.contentPaddingTop;
    }

    int getContentPaddingRight() {
        return this.contentPaddingRight;
    }

    int getContentPaddingBottom() {
        return this.contentPaddingBottom;
    }

    int getContentWidth() {
        return (getWidth() - this.contentPaddingLeft) - this.contentPaddingRight;
    }

    int getContentHeight() {
        return (getHeight() - this.contentPaddingBottom) - this.contentPaddingTop;
    }

    double getZoom() {
        if (this.destroyed) {
            return 0.0d;
        }
        return this.nativeMapView.getZoom();
    }

    void setMinZoom(@FloatRange(from = 0.0d, to = 21.0d) double d) {
        if (!this.destroyed) {
            this.nativeMapView.setMinZoom(d);
        }
    }

    double getMinZoom() {
        if (this.destroyed) {
            return 0.0d;
        }
        return this.nativeMapView.getMinZoom();
    }

    void setMaxZoom(@FloatRange(from = 0.0d, to = 21.0d) double d) {
        if (!this.destroyed) {
            this.nativeMapView.setMaxZoom(d);
        }
    }

    double getMaxZoom() {
        if (this.destroyed) {
            return 0.0d;
        }
        return this.nativeMapView.getMaxZoom();
    }

    private void zoom(boolean z) {
        zoom(z, -1.0f, -1.0f);
    }

    private void zoom(boolean z, float f, float f2) {
        this.nativeMapView.cancelTransitions();
        if (z) {
            this.nativeMapView.scaleBy(2.0d, (double) (f / this.screenDensity), (double) (f2 / this.screenDensity), 300);
        } else {
            this.nativeMapView.scaleBy(0.5d, (double) (f / this.screenDensity), (double) (f2 / this.screenDensity), 300);
        }
        postDelayed(new ZoomInvalidator(this.mapboxMap), 300);
    }

    boolean isDebugActive() {
        if (this.destroyed) {
            return false;
        }
        return this.nativeMapView.getDebug();
    }

    void setDebugActive(boolean z) {
        if (!this.destroyed) {
            this.nativeMapView.setDebug(z);
        }
    }

    void cycleDebugOptions() {
        if (!this.destroyed) {
            this.nativeMapView.cycleDebugOptions();
        }
    }

    public void setStyleUrl(@NonNull String str) {
        if (!this.destroyed) {
            if (TextUtils.isEmpty(this.nativeMapView.getAccessToken())) {
                setAccessToken(MapboxAccountManager.getInstance().getAccessToken());
            }
            this.styleUrl = str;
            this.nativeMapView.setStyleUrl(str);
            this.styleWasSet = true;
        }
    }

    @UiThread
    public void setStyle(String str) {
        setStyleUrl(str);
    }

    @UiThread
    @NonNull
    public String getStyleUrl() {
        return this.styleUrl;
    }

    @UiThread
    void setApiBaseUrl(@NonNull String str) {
        this.nativeMapView.setApiBaseUrl(str);
    }

    @UiThread
    @Deprecated
    public void setAccessToken(@NonNull String str) {
        if (!this.destroyed) {
            if (!TextUtils.isEmpty(str)) {
                str = str.trim();
            }
            MapboxAccountManager.validateAccessToken(str);
            this.nativeMapView.setAccessToken(str);
        }
    }

    @Nullable
    @UiThread
    @Deprecated
    public String getAccessToken() {
        if (this.destroyed) {
            return "";
        }
        return this.nativeMapView.getAccessToken();
    }

    LatLng fromNativeScreenLocation(@NonNull PointF pointF) {
        if (this.destroyed) {
            return new LatLng();
        }
        return this.nativeMapView.latLngForPixel(pointF);
    }

    PointF toNativeScreenLocation(@NonNull LatLng latLng) {
        if (this.destroyed || latLng == null) {
            return new PointF();
        }
        return this.nativeMapView.pixelForLatLng(latLng);
    }

    Icon loadIconForMarker(Marker marker) {
        Icon icon;
        Icon icon2 = marker.getIcon();
        int size = this.icons.size() + 1;
        Bitmap bitmap;
        if (icon2 == null) {
            icon2 = IconFactory.getInstance(getContext()).defaultMarker();
            bitmap = icon2.getBitmap();
            this.averageIconHeight += ((bitmap.getHeight() / 2) - this.averageIconHeight) / size;
            this.averageIconWidth = ((bitmap.getWidth() - this.averageIconWidth) / size) + this.averageIconWidth;
            marker.setIcon(icon2);
            icon = icon2;
        } else {
            bitmap = icon2.getBitmap();
            this.averageIconHeight += (bitmap.getHeight() - this.averageIconHeight) / size;
            this.averageIconWidth = ((bitmap.getWidth() - this.averageIconWidth) / size) + this.averageIconWidth;
            icon = icon2;
        }
        if (!this.icons.contains(icon)) {
            this.icons.add(icon);
            loadIcon(icon);
        } else if (!((Icon) this.icons.get(this.icons.indexOf(icon))).getBitmap().sameAs(icon.getBitmap())) {
            throw new IconBitmapChangedException();
        }
        return icon;
    }

    Icon loadIconForMarkerView(MarkerView markerView) {
        Icon icon = markerView.getIcon();
        int size = this.icons.size() + 1;
        if (icon == null) {
            icon = IconFactory.getInstance(getContext()).defaultMarkerView();
            markerView.setIcon(icon);
        }
        Icon icon2 = icon;
        Bitmap bitmap = icon2.getBitmap();
        this.averageIconHeight += (bitmap.getHeight() - this.averageIconHeight) / size;
        this.averageIconWidth = ((bitmap.getWidth() - this.averageIconWidth) / size) + this.averageIconWidth;
        if (!this.icons.contains(icon2)) {
            this.icons.add(icon2);
        } else if (!((Icon) this.icons.get(this.icons.indexOf(icon2))).getBitmap().sameAs(icon2.getBitmap())) {
            throw new IconBitmapChangedException();
        }
        return icon2;
    }

    void loadIcon(Icon icon) {
        if (!this.destroyed) {
            Bitmap copy;
            Bitmap bitmap = icon.getBitmap();
            String id = icon.getId();
            if (bitmap.getConfig() != Config.ARGB_8888) {
                copy = bitmap.copy(Config.ARGB_8888, false);
            } else {
                copy = bitmap;
            }
            Buffer allocate = ByteBuffer.allocate(copy.getRowBytes() * copy.getHeight());
            copy.copyPixelsToBuffer(allocate);
            float density = (float) copy.getDensity();
            if (density == 0.0f) {
                density = 160.0f;
            }
            this.nativeMapView.addAnnotationIcon(id, copy.getWidth(), copy.getHeight(), density / 160.0f, allocate.array());
        }
    }

    void reloadIcons() {
        int size = this.icons.size();
        for (int i = 0; i < size; i++) {
            loadIcon((Icon) this.icons.get(i));
        }
    }

    void updateMarker(@NonNull Marker marker) {
        if (!this.destroyed) {
            if (marker == null) {
                Log.w(MapboxConstants.TAG, "marker was null, doing nothing");
            } else if (marker.getId() == -1) {
                Log.w(MapboxConstants.TAG, "marker has an id of -1, possibly was not added yet, doing nothing");
            } else {
                if (!(marker instanceof MarkerView)) {
                    ensureIconLoaded(marker);
                }
                this.nativeMapView.updateMarker(marker);
            }
        }
    }

    void updatePolygon(Polygon polygon) {
        if (!this.destroyed) {
            if (polygon == null) {
                Log.w(MapboxConstants.TAG, "polygon was null, doing nothing");
            } else if (polygon.getId() == -1) {
                Log.w(MapboxConstants.TAG, "polygon has an id of -1, indicating the polygon was not added to the map yet.");
            } else {
                this.nativeMapView.updatePolygon(polygon);
            }
        }
    }

    void updatePolyline(Polyline polyline) {
        if (!this.destroyed) {
            if (polyline == null) {
                Log.w(MapboxConstants.TAG, "polygon was null, doing nothing");
            } else if (polyline.getId() == -1) {
                Log.w(MapboxConstants.TAG, "polygon has an id of -1, indicating the polygon was not added to the map yet.");
            } else {
                this.nativeMapView.updatePolyline(polyline);
            }
        }
    }

    private void ensureIconLoaded(Marker marker) {
        Icon icon = marker.getIcon();
        if (icon == null) {
            icon = IconFactory.getInstance(getContext()).defaultMarker();
            marker.setIcon(icon);
        }
        Icon icon2 = icon;
        if (!this.icons.contains(icon2)) {
            this.icons.add(icon2);
            loadIcon(icon2);
        } else if (!((Icon) this.icons.get(this.icons.indexOf(icon2))).getBitmap().sameAs(icon2.getBitmap())) {
            throw new IconBitmapChangedException();
        }
        Marker marker2 = marker.getId() != -1 ? (Marker) this.mapboxMap.getAnnotation(marker.getId()) : null;
        if (marker2 == null || marker2.getIcon() == null || marker2.getIcon() != marker.getIcon()) {
            marker.setTopOffsetPixels(getTopOffsetPixelsForIcon(icon2));
        }
    }

    long addMarker(@NonNull Marker marker) {
        if (this.destroyed) {
            return 0;
        }
        return this.nativeMapView.addMarker(marker);
    }

    long[] addMarkers(@NonNull List<Marker> list) {
        if (this.destroyed) {
            return new long[0];
        }
        return this.nativeMapView.addMarkers(list);
    }

    long addPolyline(@NonNull Polyline polyline) {
        if (this.destroyed) {
            return 0;
        }
        return this.nativeMapView.addPolyline(polyline);
    }

    long[] addPolylines(@NonNull List<Polyline> list) {
        if (this.destroyed) {
            return new long[0];
        }
        return this.nativeMapView.addPolylines(list);
    }

    long addPolygon(@NonNull Polygon polygon) {
        if (this.destroyed) {
            return 0;
        }
        return this.nativeMapView.addPolygon(polygon);
    }

    long[] addPolygons(@NonNull List<Polygon> list) {
        if (this.destroyed) {
            return new long[0];
        }
        return this.nativeMapView.addPolygons(list);
    }

    void removeAnnotation(long j) {
        if (!this.destroyed) {
            this.nativeMapView.removeAnnotation(j);
        }
    }

    void removeAnnotations(@NonNull long[] jArr) {
        if (!this.destroyed) {
            this.nativeMapView.removeAnnotations(jArr);
        }
    }

    List<Marker> getMarkersInRect(@NonNull RectF rectF) {
        int i = 0;
        if (this.destroyed || rectF == null) {
            return new ArrayList();
        }
        long[] queryPointAnnotations = this.nativeMapView.queryPointAnnotations(rectF);
        List arrayList = new ArrayList(queryPointAnnotations.length);
        for (long valueOf : queryPointAnnotations) {
            arrayList.add(Long.valueOf(valueOf));
        }
        Collection arrayList2 = new ArrayList(queryPointAnnotations.length);
        List annotations = this.mapboxMap.getAnnotations();
        int size = annotations.size();
        while (i < size) {
            Annotation annotation = (Annotation) annotations.get(i);
            if ((annotation instanceof Marker) && arrayList.contains(Long.valueOf(annotation.getId()))) {
                arrayList2.add((Marker) annotation);
            }
            i++;
        }
        return new ArrayList(arrayList2);
    }

    public List<MarkerView> getMarkerViewsInRect(@NonNull RectF rectF) {
        int i = 0;
        if (this.destroyed || rectF == null) {
            return new ArrayList();
        }
        long[] queryPointAnnotations = this.nativeMapView.queryPointAnnotations(rectF);
        List arrayList = new ArrayList(queryPointAnnotations.length);
        for (long valueOf : queryPointAnnotations) {
            arrayList.add(Long.valueOf(valueOf));
        }
        Collection arrayList2 = new ArrayList(queryPointAnnotations.length);
        List annotations = this.mapboxMap.getAnnotations();
        int size = annotations.size();
        while (i < size) {
            Annotation annotation = (Annotation) annotations.get(i);
            if (annotation instanceof MarkerView) {
                arrayList2.add((MarkerView) annotation);
            }
            i++;
        }
        return new ArrayList(arrayList2);
    }

    public ViewGroup getMarkerViewContainer() {
        return this.markerViewContainer;
    }

    int getTopOffsetPixelsForIcon(Icon icon) {
        if (this.destroyed) {
            return 0;
        }
        return (int) (this.nativeMapView.getTopOffsetPixelsForAnnotationSymbol(icon.getId()) * ((double) this.screenDensity));
    }

    void setContentPadding(int i, int i2, int i3, int i4) {
        if (!this.destroyed) {
            this.contentPaddingLeft = i;
            this.contentPaddingTop = i2;
            this.contentPaddingRight = i3;
            this.contentPaddingBottom = i4;
            int[] padding = this.mapboxMap.getMyLocationViewSettings().getPadding();
            int i5 = i2 + padding[1];
            int i6 = i3 + padding[2];
            this.nativeMapView.setContentPadding((double) (((float) i5) / this.screenDensity), (double) (((float) (i + padding[0])) / this.screenDensity), (double) (((float) (padding[3] + i4)) / this.screenDensity), (double) (((float) i6) / this.screenDensity));
        }
    }

    public void invalidateContentPadding() {
        setContentPadding(this.contentPaddingLeft, this.contentPaddingTop, this.contentPaddingRight, this.contentPaddingBottom);
        if (this.mapboxMap.getTrackingSettings().isLocationTrackingDisabled()) {
            setFocalPoint(null);
        } else {
            setFocalPoint(new PointF(this.myLocationView.getCenterX(), this.myLocationView.getCenterY()));
        }
    }

    double getMetersPerPixelAtLatitude(@FloatRange(from = -180.0d, to = 180.0d) double d) {
        if (this.destroyed) {
            return 0.0d;
        }
        return this.nativeMapView.getMetersPerPixelAtLatitude(d, getZoom()) / ((double) this.screenDensity);
    }

    void jumpTo(double d, LatLng latLng, double d2, double d3) {
        if (!this.destroyed) {
            this.nativeMapView.cancelTransitions();
            this.nativeMapView.jumpTo(d, latLng, d2, d3);
        }
    }

    void easeTo(double d, LatLng latLng, long j, double d2, double d3, boolean z, @Nullable CancelableCallback cancelableCallback) {
        if (!this.destroyed) {
            this.nativeMapView.cancelTransitions();
            if (cancelableCallback != null) {
                final CancelableCallback cancelableCallback2 = cancelableCallback;
                addOnMapChangedListener(new OnMapChangedListener() {
                    public void onMapChanged(int i) {
                        if (i == 4) {
                            cancelableCallback2.onFinish();
                            MapView.this.removeOnMapChangedListener(this);
                        }
                    }
                });
            }
            this.nativeMapView.easeTo(d, latLng, j, d2, d3, z);
        }
    }

    void flyTo(double d, LatLng latLng, long j, double d2, double d3, @Nullable CancelableCallback cancelableCallback) {
        if (!this.destroyed) {
            this.nativeMapView.cancelTransitions();
            if (cancelableCallback != null) {
                final CancelableCallback cancelableCallback2 = cancelableCallback;
                addOnMapChangedListener(new OnMapChangedListener() {
                    public void onMapChanged(int i) {
                        if (i == 4) {
                            cancelableCallback2.onFinish();
                            MapView.this.removeOnMapChangedListener(this);
                        }
                    }
                });
            }
            this.nativeMapView.flyTo(d, latLng, j, d2, d3);
        }
    }

    private void adjustTopOffsetPixels() {
        List annotations = this.mapboxMap.getAnnotations();
        int size = annotations.size();
        for (int i = 0; i < size; i++) {
            Annotation annotation = (Annotation) annotations.get(i);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.setTopOffsetPixels(getTopOffsetPixelsForIcon(marker.getIcon()));
            }
        }
        for (Marker marker2 : this.mapboxMap.getSelectedMarkers()) {
            if (marker2.isInfoWindowShown()) {
                marker2.hideInfoWindow();
                marker2.showInfoWindow(this.mapboxMap, this);
            }
        }
    }

    private void reloadMarkers() {
        if (!this.destroyed) {
            List annotations = this.mapboxMap.getAnnotations();
            int size = annotations.size();
            for (int i = 0; i < size; i++) {
                Annotation annotation = (Annotation) annotations.get(i);
                if (annotation instanceof Marker) {
                    Marker marker = (Marker) annotation;
                    this.nativeMapView.removeAnnotation(annotation.getId());
                    marker.setId(this.nativeMapView.addMarker(marker));
                }
            }
        }
    }

    protected void onInvalidate() {
        postInvalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && !this.destroyed && this.hasSurface) {
            this.nativeMapView.render();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!this.destroyed && !isInEditMode()) {
            this.nativeMapView.resizeView((int) (((float) i) / this.screenDensity), (int) (((float) i2) / this.screenDensity));
        }
    }

    double getScale() {
        if (this.destroyed) {
            return 0.0d;
        }
        return this.nativeMapView.getScale();
    }

    CameraPosition invalidateCameraPosition() {
        if (this.destroyed) {
            return new CameraPosition.Builder().build();
        }
        CameraPosition build = new CameraPosition.Builder(this.nativeMapView.getCameraValues()).build();
        this.myLocationView.setCameraPosition(build);
        this.mapboxMap.getMarkerViewManager().setTilt((float) build.tilt);
        return build;
    }

    double getBearing() {
        if (this.destroyed) {
            return 0.0d;
        }
        double d = -this.nativeMapView.getBearing();
        while (d > 360.0d) {
            d -= 360.0d;
        }
        while (d < 0.0d) {
            d += 360.0d;
        }
        return d;
    }

    void setBearing(float f) {
        if (!this.destroyed) {
            this.myLocationView.setBearing((double) f);
            this.nativeMapView.setBearing((double) f);
        }
    }

    void setBearing(float f, long j) {
        if (!this.destroyed) {
            this.myLocationView.setBearing((double) f);
            this.nativeMapView.setBearing((double) f, j);
        }
    }

    void setBearing(double d, float f, float f2) {
        if (!this.destroyed) {
            this.myLocationView.setBearing(d);
            this.nativeMapView.setBearing(d, (double) f, (double) f2);
        }
    }

    @CallSuper
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mapboxMap.getUiSettings().isZoomControlsEnabled()) {
            this.zoomButtonsController.setVisible(false);
        }
        if (this.myLocationListener != null) {
            LocationServices.getLocationServices(getContext()).removeLocationListener(this.myLocationListener);
            this.myLocationListener = null;
        }
    }

    protected void onVisibilityChanged(@NonNull View view, int i) {
        if (!isInEditMode()) {
            if (i == 0) {
                if (this.mapboxMap != null && this.mapboxMap.getUiSettings().isZoomControlsEnabled()) {
                    this.zoomButtonsController.setVisible(true);
                }
            } else if (this.mapboxMap != null && this.mapboxMap.getUiSettings().isZoomControlsEnabled()) {
                this.zoomButtonsController.setVisible(false);
            }
        }
    }

    private void trackGestureEvent(@NonNull String str, @NonNull float f, @NonNull float f2) {
        LatLng fromScreenLocation = this.projection.fromScreenLocation(new PointF(f, f2));
        if (Double.isNaN(fromScreenLocation.getLatitude()) || Double.isNaN(fromScreenLocation.getLongitude())) {
            Log.d(MapView.class.getSimpleName(), "trackGestureEvent() has a NaN lat or lon.  Returning.");
        } else if (Double.isInfinite(fromScreenLocation.getLatitude()) || Double.isInfinite(fromScreenLocation.getLongitude())) {
            Log.d(MapView.class.getSimpleName(), "trackGestureEvent() has an Infinite lat or lon.  Returning.");
        } else {
            Hashtable hashtable = new Hashtable();
            hashtable.put("event", MapboxEvent.TYPE_MAP_CLICK);
            hashtable.put(MapboxEvent.ATTRIBUTE_CREATED, MapboxEventManager.generateCreateDate());
            hashtable.put(MapboxEvent.KEY_GESTURE_ID, str);
            hashtable.put(MapboxEvent.KEY_LATITUDE, Double.valueOf(fromScreenLocation.getLatitude()));
            hashtable.put(MapboxEvent.KEY_LONGITUDE, Double.valueOf(fromScreenLocation.getLongitude()));
            hashtable.put(MapboxEvent.KEY_ZOOM, Double.valueOf(this.mapboxMap.getCameraPosition().zoom));
            MapboxEventManager.getMapboxEventManager().pushEvent(hashtable);
        }
    }

    private void trackGestureDragEndEvent(@NonNull float f, @NonNull float f2) {
        LatLng fromScreenLocation = this.projection.fromScreenLocation(new PointF(f, f2));
        if (Double.isNaN(fromScreenLocation.getLatitude()) || Double.isNaN(fromScreenLocation.getLongitude())) {
            Log.d(MapView.class.getSimpleName(), "trackGestureDragEndEvent() has a NaN lat or lon.  Returning.");
        } else if (Double.isInfinite(fromScreenLocation.getLatitude()) || Double.isInfinite(fromScreenLocation.getLongitude())) {
            Log.d(MapView.class.getSimpleName(), "trackGestureDragEndEvent() has an Infinite lat or lon.  Returning.");
        } else {
            Hashtable hashtable = new Hashtable();
            hashtable.put("event", MapboxEvent.TYPE_MAP_DRAGEND);
            hashtable.put(MapboxEvent.ATTRIBUTE_CREATED, MapboxEventManager.generateCreateDate());
            hashtable.put(MapboxEvent.KEY_LATITUDE, Double.valueOf(fromScreenLocation.getLatitude()));
            hashtable.put(MapboxEvent.KEY_LONGITUDE, Double.valueOf(fromScreenLocation.getLongitude()));
            hashtable.put(MapboxEvent.KEY_ZOOM, Double.valueOf(this.mapboxMap.getCameraPosition().zoom));
            MapboxEventManager.getMapboxEventManager().pushEvent(hashtable);
        }
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (this.destroyed) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getButtonState() != 0 && motionEvent.getButtonState() != 1) {
            return false;
        }
        this.rotateGestureDetector.m2763a(motionEvent);
        this.scaleGestureDetector.onTouchEvent(motionEvent);
        this.shoveGestureDetector.m2763a(motionEvent);
        boolean z;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.nativeMapView.setGestureInProgress(true);
                break;
            case 1:
                z = motionEvent.getEventTime() - motionEvent.getDownTime() <= ((long) ViewConfiguration.getTapTimeout());
                boolean z2;
                if (this.rotateGestureDetector.m2766b() || this.scaleGestureDetector.isInProgress() || this.shoveGestureDetector.m2766b()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!this.twoTap || !z || r3) {
                    if (this.scrollInProgress) {
                        trackGestureDragEndEvent(motionEvent.getX(), motionEvent.getY());
                        this.scrollInProgress = false;
                    }
                    this.twoTap = false;
                    this.nativeMapView.setGestureInProgress(false);
                    break;
                }
                if (this.focalPoint != null) {
                    zoom(false, this.focalPoint.x, this.focalPoint.y);
                } else {
                    PointF d = C0707d.m2770d(motionEvent);
                    zoom(false, d.x, d.y);
                }
                this.twoTap = false;
                return true;
                break;
            case 3:
                this.twoTap = false;
                this.nativeMapView.setGestureInProgress(false);
                break;
            case 5:
                if (motionEvent.getPointerCount() == 2 && this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                    z = true;
                } else {
                    z = false;
                }
                this.twoTap = z;
                if (this.twoTap) {
                    trackGestureEvent(MapboxEvent.GESTURE_TWO_FINGER_SINGLETAP, motionEvent.getX(), motionEvent.getY());
                    break;
                }
                break;
        }
        if (this.gestureDetector.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        if (this.destroyed) {
            return super.onKeyDown(i, keyEvent);
        }
        double d = keyEvent.getRepeatCount() >= 5 ? 50.0d : 10.0d;
        switch (i) {
            case 19:
                if (!this.mapboxMap.getTrackingSettings().isScrollGestureCurrentlyEnabled()) {
                    return false;
                }
                this.nativeMapView.cancelTransitions();
                this.nativeMapView.moveBy(0.0d / ((double) this.screenDensity), d / ((double) this.screenDensity));
                return true;
            case 20:
                if (!this.mapboxMap.getTrackingSettings().isScrollGestureCurrentlyEnabled()) {
                    return false;
                }
                this.nativeMapView.cancelTransitions();
                this.nativeMapView.moveBy(0.0d / ((double) this.screenDensity), (-d) / ((double) this.screenDensity));
                return true;
            case 21:
                if (!this.mapboxMap.getTrackingSettings().isScrollGestureCurrentlyEnabled()) {
                    return false;
                }
                this.nativeMapView.cancelTransitions();
                this.nativeMapView.moveBy(d / ((double) this.screenDensity), 0.0d / ((double) this.screenDensity));
                return true;
            case 22:
                if (!this.mapboxMap.getTrackingSettings().isScrollGestureCurrentlyEnabled()) {
                    return false;
                }
                this.nativeMapView.cancelTransitions();
                this.nativeMapView.moveBy((-d) / ((double) this.screenDensity), 0.0d / ((double) this.screenDensity));
                return true;
            case 23:
            case 66:
                keyEvent.startTracking();
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        switch (i) {
            case 23:
            case 66:
                if (!this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                    return false;
                }
                zoom(false);
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (keyEvent.isCanceled()) {
            return super.onKeyUp(i, keyEvent);
        }
        switch (i) {
            case 23:
            case 66:
                if (!this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                    return false;
                }
                zoom(true);
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.destroyed) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                if (this.currentTrackballLongPressTimeOut != null) {
                    this.currentTrackballLongPressTimeOut.cancel();
                    this.currentTrackballLongPressTimeOut = null;
                }
                this.currentTrackballLongPressTimeOut = new TrackballLongPressTimeOut();
                postDelayed(this.currentTrackballLongPressTimeOut, (long) ViewConfiguration.getLongPressTimeout());
                return true;
            case 1:
                if (!this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                    return false;
                }
                if (this.currentTrackballLongPressTimeOut != null) {
                    zoom(true);
                }
                return true;
            case 2:
                if (!this.mapboxMap.getTrackingSettings().isScrollGestureCurrentlyEnabled()) {
                    return false;
                }
                this.nativeMapView.cancelTransitions();
                this.nativeMapView.moveBy((((double) motionEvent.getX()) * -10.0d) / ((double) this.screenDensity), (((double) motionEvent.getY()) * -10.0d) / ((double) this.screenDensity));
                return true;
            case 3:
                if (this.currentTrackballLongPressTimeOut != null) {
                    this.currentTrackballLongPressTimeOut.cancel();
                    this.currentTrackballLongPressTimeOut = null;
                }
                return true;
            default:
                return super.onTrackballEvent(motionEvent);
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.destroyed) {
            return false;
        }
        if ((motionEvent.getSource() & 2) != 2) {
            return super.onGenericMotionEvent(motionEvent);
        }
        switch (motionEvent.getActionMasked()) {
            case 8:
                if (!this.mapboxMap.getUiSettings().isZoomGesturesEnabled()) {
                    return false;
                }
                this.nativeMapView.cancelTransitions();
                this.nativeMapView.scaleBy(Math.pow(2.0d, (double) motionEvent.getAxisValue(9)), (double) (motionEvent.getX() / this.screenDensity), (double) (motionEvent.getY() / this.screenDensity));
                return true;
            default:
                return super.onGenericMotionEvent(motionEvent);
        }
    }

    public boolean onHoverEvent(@NonNull MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 7:
            case 9:
                if (!this.mapboxMap.getUiSettings().isZoomControlsEnabled()) {
                    return true;
                }
                this.zoomButtonsController.setVisible(true);
                return true;
            case 10:
                if (!this.mapboxMap.getUiSettings().isZoomControlsEnabled()) {
                    return true;
                }
                this.zoomButtonsController.setVisible(false);
                return true;
            default:
                return super.onHoverEvent(motionEvent);
        }
    }

    private boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getContext().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void onConnectivityChanged(boolean z) {
        this.nativeMapView.setReachability(z);
    }

    public void addOnMapChangedListener(@Nullable OnMapChangedListener onMapChangedListener) {
        if (onMapChangedListener != null) {
            this.onMapChangedListener.add(onMapChangedListener);
        }
    }

    public void removeOnMapChangedListener(@Nullable OnMapChangedListener onMapChangedListener) {
        if (onMapChangedListener != null) {
            this.onMapChangedListener.remove(onMapChangedListener);
        }
    }

    protected void onMapChanged(int i) {
        if (this.onMapChangedListener != null) {
            Iterator it = this.onMapChangedListener.iterator();
            while (it.hasNext()) {
                ((OnMapChangedListener) it.next()).onMapChanged(i);
            }
        }
    }

    void setMyLocationEnabled(boolean z) {
        this.myLocationView.setEnabled(z);
    }

    Location getMyLocation() {
        return this.myLocationView.getLocation();
    }

    void setOnMyLocationChangeListener(@Nullable final OnMyLocationChangeListener onMyLocationChangeListener) {
        if (onMyLocationChangeListener != null) {
            this.myLocationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    if (onMyLocationChangeListener != null) {
                        onMyLocationChangeListener.onMyLocationChange(location);
                    }
                }
            };
            LocationServices.getLocationServices(getContext()).addLocationListener(this.myLocationListener);
            return;
        }
        LocationServices.getLocationServices(getContext()).removeLocationListener(this.myLocationListener);
        this.myLocationListener = null;
    }

    void setMyLocationTrackingMode(int i) {
        if (!(i == 0 || this.mapboxMap.isMyLocationEnabled())) {
            this.mapboxMap.setMyLocationEnabled(true);
        }
        this.myLocationView.setMyLocationTrackingMode(i);
        if (i == 4) {
            setFocalPoint(new PointF(this.myLocationView.getCenterX(), this.myLocationView.getCenterY()));
        } else {
            setFocalPoint(null);
        }
        OnMyLocationTrackingModeChangeListener onMyLocationTrackingModeChangeListener = this.mapboxMap.getOnMyLocationTrackingModeChangeListener();
        if (onMyLocationTrackingModeChangeListener != null) {
            onMyLocationTrackingModeChangeListener.onMyLocationTrackingModeChange(i);
        }
    }

    void setMyBearingTrackingMode(int i) {
        if (!(i == 0 || this.mapboxMap.isMyLocationEnabled())) {
            this.mapboxMap.setMyLocationEnabled(true);
        }
        this.myLocationView.setMyBearingTrackingMode(i);
        OnMyBearingTrackingModeChangeListener onMyBearingTrackingModeChangeListener = this.mapboxMap.getOnMyBearingTrackingModeChangeListener();
        if (onMyBearingTrackingModeChangeListener != null) {
            onMyBearingTrackingModeChangeListener.onMyBearingTrackingModeChange(i);
        }
    }

    boolean isPermissionsAccepted() {
        return ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    void resetTrackingModesIfRequired(boolean z, boolean z2) {
        TrackingSettings trackingSettings = this.mapboxMap.getTrackingSettings();
        if (z && !trackingSettings.isLocationTrackingDisabled() && trackingSettings.isDismissLocationTrackingOnGesture()) {
            resetLocationTrackingMode();
        }
        if (z2 && !trackingSettings.isBearingTrackingDisabled() && trackingSettings.isDismissBearingTrackingOnGesture()) {
            resetBearingTrackingMode();
        }
    }

    void resetTrackingModesIfRequired(CameraPosition cameraPosition) {
        boolean z;
        boolean z2 = true;
        if (cameraPosition.target != null) {
            z = true;
        } else {
            z = false;
        }
        if (cameraPosition.bearing == -1.0d) {
            z2 = false;
        }
        resetTrackingModesIfRequired(z, z2);
    }

    private void resetLocationTrackingMode() {
        try {
            this.mapboxMap.getTrackingSettings().setMyLocationTrackingMode(0);
        } catch (SecurityException e) {
        }
    }

    private void resetBearingTrackingMode() {
        try {
            this.mapboxMap.getTrackingSettings().setMyBearingTrackingMode(0);
        } catch (SecurityException e) {
        }
    }

    void setCompassEnabled(boolean z) {
        this.compassView.setEnabled(z);
    }

    void setCompassGravity(int i) {
        setWidgetGravity(this.compassView, i);
    }

    void setCompassMargins(int i, int i2, int i3, int i4) {
        setWidgetMargins(this.compassView, i, i2, i3, i4);
    }

    void setCompassFadeFacingNorth(boolean z) {
        this.compassView.fadeCompassViewFacingNorth(z);
    }

    void setLogoGravity(int i) {
        setWidgetGravity(this.logoView, i);
    }

    void setLogoMargins(int i, int i2, int i3, int i4) {
        setWidgetMargins(this.logoView, i, i2, i3, i4);
    }

    void setLogoEnabled(boolean z) {
        this.logoView.setVisibility(z ? 0 : 8);
    }

    void setAttributionGravity(int i) {
        setWidgetGravity(this.attributionsView, i);
    }

    void setAttributionMargins(int i, int i2, int i3, int i4) {
        setWidgetMargins(this.attributionsView, i, i2, i3, i4);
    }

    void setAttributionEnabled(int i) {
        this.attributionsView.setVisibility(i);
    }

    void setAtttibutionTintColor(int i) {
        ColorUtils.setTintList(this.attributionsView, i);
    }

    int getAttributionTintColor() {
        return this.mapboxMap.getUiSettings().getAttributionTintColor();
    }

    @UiThread
    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        if (!this.initialLoad && onMapReadyCallback != null) {
            onMapReadyCallback.onMapReady(this.mapboxMap);
        } else if (onMapReadyCallback != null) {
            this.onMapReadyCallbackList.add(onMapReadyCallback);
        }
    }

    MapboxMap getMapboxMap() {
        return this.mapboxMap;
    }

    void setMapboxMap(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
    }

    MyLocationView getUserLocationView() {
        return this.myLocationView;
    }

    NativeMapView getNativeMapView() {
        return this.nativeMapView;
    }

    @UiThread
    void snapshot(@NonNull SnapshotReadyCallback snapshotReadyCallback, @Nullable Bitmap bitmap) {
        this.snapshotRequest = new SnapshotRequest(bitmap, snapshotReadyCallback);
        this.nativeMapView.scheduleTakeSnapshot();
        this.nativeMapView.render();
    }

    protected void onSnapshotReady(byte[] bArr) {
        if (this.snapshotRequest != null && bArr != null) {
            Options options = new Options();
            options.inBitmap = this.snapshotRequest.getBitmap();
            options.inMutable = true;
            options.inSampleSize = 1;
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            SnapshotReadyCallback callback = this.snapshotRequest.getCallback();
            if (callback != null) {
                callback.onSnapshotReady(decodeByteArray);
            }
        }
    }

    private void setWidgetGravity(@NonNull View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.gravity = i;
        view.setLayoutParams(layoutParams);
    }

    private void setWidgetMargins(@NonNull View view, int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(this.contentPaddingLeft + i, this.contentPaddingTop + i2, this.contentPaddingRight + i3, this.contentPaddingBottom + i4);
        view.setLayoutParams(layoutParams);
    }
}
