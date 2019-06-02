package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzh;

public final class GoogleMap {
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate agw;
    private UiSettings agx;

    @Deprecated
    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    protected GoogleMap(IGoogleMapDelegate iGoogleMapDelegate) {
        this.agw = (IGoogleMapDelegate) zzab.zzaa(iGoogleMapDelegate);
    }

    public final Circle addCircle(CircleOptions circleOptions) {
        try {
            return new Circle(this.agw.addCircle(circleOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        try {
            zzc addGroundOverlay = this.agw.addGroundOverlay(groundOverlayOptions);
            return addGroundOverlay != null ? new GroundOverlay(addGroundOverlay) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        try {
            zzf addMarker = this.agw.addMarker(markerOptions);
            return addMarker != null ? new Marker(addMarker) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            return new Polygon(this.agw.addPolygon(polygonOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polyline addPolyline(PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.agw.addPolyline(polylineOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        try {
            zzh addTileOverlay = this.agw.addTileOverlay(tileOverlayOptions);
            return addTileOverlay != null ? new TileOverlay(addTileOverlay) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        try {
            this.agw.animateCamera(cameraUpdate.zzbpg());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, int i, GoogleMap$CancelableCallback googleMap$CancelableCallback) {
        try {
            this.agw.animateCameraWithDurationAndCallback(cameraUpdate.zzbpg(), i, googleMap$CancelableCallback == null ? null : new GoogleMap$zza(googleMap$CancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, GoogleMap$CancelableCallback googleMap$CancelableCallback) {
        try {
            this.agw.animateCameraWithCallback(cameraUpdate.zzbpg(), googleMap$CancelableCallback == null ? null : new GoogleMap$zza(googleMap$CancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void clear() {
        try {
            this.agw.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final CameraPosition getCameraPosition() {
        try {
            return this.agw.getCameraPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public IndoorBuilding getFocusedBuilding() {
        try {
            zzd focusedBuilding = this.agw.getFocusedBuilding();
            return focusedBuilding != null ? new IndoorBuilding(focusedBuilding) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getMapType() {
        try {
            return this.agw.getMapType();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMaxZoomLevel() {
        try {
            return this.agw.getMaxZoomLevel();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMinZoomLevel() {
        try {
            return this.agw.getMinZoomLevel();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @Deprecated
    public final Location getMyLocation() {
        try {
            return this.agw.getMyLocation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Projection getProjection() {
        try {
            return new Projection(this.agw.getProjection());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings getUiSettings() {
        try {
            if (this.agx == null) {
                this.agx = new UiSettings(this.agw.getUiSettings());
            }
            return this.agx;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isBuildingsEnabled() {
        try {
            return this.agw.isBuildingsEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isIndoorEnabled() {
        try {
            return this.agw.isIndoorEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isMyLocationEnabled() {
        try {
            return this.agw.isMyLocationEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isTrafficEnabled() {
        try {
            return this.agw.isTrafficEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        try {
            this.agw.moveCamera(cameraUpdate.zzbpg());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setBuildingsEnabled(boolean z) {
        try {
            this.agw.setBuildingsEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setContentDescription(String str) {
        try {
            this.agw.setContentDescription(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean setIndoorEnabled(boolean z) {
        try {
            return this.agw.setIndoorEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setInfoWindowAdapter(GoogleMap$InfoWindowAdapter googleMap$InfoWindowAdapter) {
        if (googleMap$InfoWindowAdapter == null) {
            try {
                this.agw.setInfoWindowAdapter(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setInfoWindowAdapter(new GoogleMap$3(this, googleMap$InfoWindowAdapter));
    }

    public final void setLocationSource(LocationSource locationSource) {
        if (locationSource == null) {
            try {
                this.agw.setLocationSource(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setLocationSource(new GoogleMap$12(this, locationSource));
    }

    public final void setMapType(int i) {
        try {
            this.agw.setMapType(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final void setMyLocationEnabled(boolean z) {
        try {
            this.agw.setMyLocationEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnCameraChangeListener(GoogleMap$OnCameraChangeListener googleMap$OnCameraChangeListener) {
        if (googleMap$OnCameraChangeListener == null) {
            try {
                this.agw.setOnCameraChangeListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnCameraChangeListener(new GoogleMap$13(this, googleMap$OnCameraChangeListener));
    }

    public final void setOnCircleClickListener(GoogleMap$OnCircleClickListener googleMap$OnCircleClickListener) {
        if (googleMap$OnCircleClickListener == null) {
            try {
                this.agw.setOnCircleClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnCircleClickListener(new GoogleMap$8(this, googleMap$OnCircleClickListener));
    }

    public final void setOnGroundOverlayClickListener(GoogleMap$OnGroundOverlayClickListener googleMap$OnGroundOverlayClickListener) {
        if (googleMap$OnGroundOverlayClickListener == null) {
            try {
                this.agw.setOnGroundOverlayClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnGroundOverlayClickListener(new GoogleMap$7(this, googleMap$OnGroundOverlayClickListener));
    }

    public final void setOnIndoorStateChangeListener(GoogleMap$OnIndoorStateChangeListener googleMap$OnIndoorStateChangeListener) {
        if (googleMap$OnIndoorStateChangeListener == null) {
            try {
                this.agw.setOnIndoorStateChangeListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnIndoorStateChangeListener(new GoogleMap$1(this, googleMap$OnIndoorStateChangeListener));
    }

    public final void setOnInfoWindowClickListener(GoogleMap$OnInfoWindowClickListener googleMap$OnInfoWindowClickListener) {
        if (googleMap$OnInfoWindowClickListener == null) {
            try {
                this.agw.setOnInfoWindowClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnInfoWindowClickListener(new GoogleMap$18(this, googleMap$OnInfoWindowClickListener));
    }

    public final void setOnInfoWindowCloseListener(GoogleMap$OnInfoWindowCloseListener googleMap$OnInfoWindowCloseListener) {
        if (googleMap$OnInfoWindowCloseListener == null) {
            try {
                this.agw.setOnInfoWindowCloseListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnInfoWindowCloseListener(new GoogleMap$2(this, googleMap$OnInfoWindowCloseListener));
    }

    public final void setOnInfoWindowLongClickListener(GoogleMap$OnInfoWindowLongClickListener googleMap$OnInfoWindowLongClickListener) {
        if (googleMap$OnInfoWindowLongClickListener == null) {
            try {
                this.agw.setOnInfoWindowLongClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnInfoWindowLongClickListener(new GoogleMap$19(this, googleMap$OnInfoWindowLongClickListener));
    }

    public final void setOnMapClickListener(GoogleMap$OnMapClickListener googleMap$OnMapClickListener) {
        if (googleMap$OnMapClickListener == null) {
            try {
                this.agw.setOnMapClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnMapClickListener(new GoogleMap$14(this, googleMap$OnMapClickListener));
    }

    public void setOnMapLoadedCallback(GoogleMap$OnMapLoadedCallback googleMap$OnMapLoadedCallback) {
        if (googleMap$OnMapLoadedCallback == null) {
            try {
                this.agw.setOnMapLoadedCallback(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnMapLoadedCallback(new GoogleMap$6(this, googleMap$OnMapLoadedCallback));
    }

    public final void setOnMapLongClickListener(GoogleMap$OnMapLongClickListener googleMap$OnMapLongClickListener) {
        if (googleMap$OnMapLongClickListener == null) {
            try {
                this.agw.setOnMapLongClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnMapLongClickListener(new GoogleMap$15(this, googleMap$OnMapLongClickListener));
    }

    public final void setOnMarkerClickListener(GoogleMap$OnMarkerClickListener googleMap$OnMarkerClickListener) {
        if (googleMap$OnMarkerClickListener == null) {
            try {
                this.agw.setOnMarkerClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnMarkerClickListener(new GoogleMap$16(this, googleMap$OnMarkerClickListener));
    }

    public final void setOnMarkerDragListener(GoogleMap$OnMarkerDragListener googleMap$OnMarkerDragListener) {
        if (googleMap$OnMarkerDragListener == null) {
            try {
                this.agw.setOnMarkerDragListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnMarkerDragListener(new GoogleMap$17(this, googleMap$OnMarkerDragListener));
    }

    public final void setOnMyLocationButtonClickListener(GoogleMap$OnMyLocationButtonClickListener googleMap$OnMyLocationButtonClickListener) {
        if (googleMap$OnMyLocationButtonClickListener == null) {
            try {
                this.agw.setOnMyLocationButtonClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnMyLocationButtonClickListener(new GoogleMap$5(this, googleMap$OnMyLocationButtonClickListener));
    }

    @Deprecated
    public final void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        if (onMyLocationChangeListener == null) {
            try {
                this.agw.setOnMyLocationChangeListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnMyLocationChangeListener(new GoogleMap$4(this, onMyLocationChangeListener));
    }

    public final void setOnPolygonClickListener(GoogleMap$OnPolygonClickListener googleMap$OnPolygonClickListener) {
        if (googleMap$OnPolygonClickListener == null) {
            try {
                this.agw.setOnPolygonClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnPolygonClickListener(new GoogleMap$9(this, googleMap$OnPolygonClickListener));
    }

    public final void setOnPolylineClickListener(GoogleMap$OnPolylineClickListener googleMap$OnPolylineClickListener) {
        if (googleMap$OnPolylineClickListener == null) {
            try {
                this.agw.setOnPolylineClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.agw.setOnPolylineClickListener(new GoogleMap$10(this, googleMap$OnPolylineClickListener));
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        try {
            this.agw.setPadding(i, i2, i3, i4);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setTrafficEnabled(boolean z) {
        try {
            this.agw.setTrafficEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void snapshot(GoogleMap$SnapshotReadyCallback googleMap$SnapshotReadyCallback) {
        snapshot(googleMap$SnapshotReadyCallback, null);
    }

    public final void snapshot(GoogleMap$SnapshotReadyCallback googleMap$SnapshotReadyCallback, Bitmap bitmap) {
        try {
            this.agw.snapshot(new GoogleMap$11(this, googleMap$SnapshotReadyCallback), (zze) (bitmap != null ? zze.zzae(bitmap) : null));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void stopAnimation() {
        try {
            this.agw.stopAnimation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
