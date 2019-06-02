package com.beastbikes.android.modules.map;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.GoogleMapManager;
import com.beastbikes.android.modules.map.C2296d.C1950e;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.android.utils.C2558g;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap$CancelableCallback;
import com.google.android.gms.maps.GoogleMap$OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap$OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap$OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap$OnPolylineClickListener;
import com.google.android.gms.maps.GoogleMap$SnapshotReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: Map4Google */
/* renamed from: com.beastbikes.android.modules.map.b */
public class C2302b extends C2296d<LatLng, BitmapDescriptor> implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, GoogleMap$CancelableCallback, GoogleMap$OnCameraChangeListener, GoogleMap$OnMarkerDragListener, OnMapReadyCallback {
    /* renamed from: m */
    private static final Logger f10889m = LoggerFactory.getLogger("Map4Google");
    /* renamed from: n */
    private MapFragment f10890n;
    /* renamed from: o */
    private TextView f10891o;
    /* renamed from: p */
    private GoogleMap f10892p;
    /* renamed from: q */
    private FrameLayout f10893q;
    /* renamed from: r */
    private ImageView f10894r;
    /* renamed from: s */
    private boolean f10895s;
    /* renamed from: t */
    private Location f10896t;
    /* renamed from: u */
    private boolean f10897u = false;
    /* renamed from: v */
    private float f10898v = 0.33f;
    /* renamed from: w */
    private GoogleApiClient f10899w;
    /* renamed from: x */
    private DecimalFormat f10900x;
    /* renamed from: y */
    private LocationRequest f10901y;

    /* compiled from: Map4Google */
    /* renamed from: com.beastbikes.android.modules.map.b$2 */
    class C22992 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2302b f10886a;

        C22992(C2302b c2302b) {
            this.f10886a = c2302b;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (this.f10886a.d == null) {
                return false;
            }
            switch (action) {
                case 0:
                    this.f10886a.d.requestDisallowInterceptTouchEvent(true);
                    return false;
                case 1:
                    this.f10886a.d.requestDisallowInterceptTouchEvent(false);
                    return true;
                case 2:
                    this.f10886a.d.requestDisallowInterceptTouchEvent(true);
                    return false;
                default:
                    return true;
            }
        }
    }

    /* compiled from: Map4Google */
    /* renamed from: com.beastbikes.android.modules.map.b$3 */
    class C23003 implements GoogleMap$OnMarkerClickListener {
        /* renamed from: a */
        final /* synthetic */ C2302b f10887a;

        C23003(C2302b c2302b) {
            this.f10887a = c2302b;
        }

        public boolean onMarkerClick(Marker marker) {
            if (this.f10887a.j != null) {
                this.f10887a.j.m11802a(marker);
            }
            return true;
        }
    }

    /* compiled from: Map4Google */
    /* renamed from: com.beastbikes.android.modules.map.b$4 */
    class C23014 implements GoogleMap$OnPolylineClickListener {
        /* renamed from: a */
        final /* synthetic */ C2302b f10888a;

        C23014(C2302b c2302b) {
            this.f10888a = c2302b;
        }

        public void onPolylineClick(Polyline polyline) {
            if (this.f10888a.k != null) {
                this.f10888a.k.m11803b(polyline);
            }
        }
    }

    /* renamed from: b */
    protected /* synthetic */ Object mo3451b(Point point) {
        return m11744a(point);
    }

    /* renamed from: b */
    public /* synthetic */ void mo3453b(Object obj, Object obj2) {
        m11756a((LatLng) obj, (LatLng) obj2);
    }

    /* renamed from: c */
    public /* synthetic */ Object mo3455c(List list, int i, int i2) {
        return m11745a(list, i, i2);
    }

    /* renamed from: d */
    public /* synthetic */ Object mo3458d(List list) {
        return m11757b(list);
    }

    public C2302b(Context context) {
        super(context);
        C2302b.inflate(context, C1373R.layout.speedx_map_with_google, this);
        this.f10900x = new DecimalFormat("0");
    }

    /* renamed from: d */
    protected void mo3459d() {
        this.f10891o = (TextView) findViewById(C1373R.id.noGooglePlayServiceTV);
        this.f10893q = (FrameLayout) findViewById(C1373R.id.googleMap_View_FL);
        this.f10894r = (ImageView) findViewById(C1373R.id.transparent_image);
    }

    /* renamed from: a */
    protected LatLng m11744a(Point point) {
        return this.f10892p.getProjection().fromScreenLocation(point);
    }

    /* renamed from: a */
    protected String mo3444a(List<LatLng> list) {
        if (list == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            LatLng latLng = (LatLng) it.next();
            stringBuilder.append(latLng.latitude).append(",").append(latLng.longitude);
            if (it.hasNext()) {
                stringBuilder.append('|');
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: e */
    public void mo3460e() {
        c_();
    }

    /* renamed from: f */
    public void mo3461f() {
        if (this.f10899w == null || !this.f10899w.isConnected()) {
            f10889m.info("GoogleApiClient is not connected");
        } else {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.f10899w, this);
        }
    }

    /* renamed from: g */
    public void mo3462g() {
        if (!this.f10897u) {
            if (this.f10896t != null) {
                this.f10892p.addMarker(new MarkerOptions().position(new LatLng(this.f10896t.getLatitude(), this.f10896t.getLongitude())).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.route_map_make_location)));
            }
            this.f10897u = true;
        }
    }

    /* renamed from: a */
    public void mo3447a(float f) {
        if (this.f10892p != null) {
            float minZoomLevel = this.f10892p.getMinZoomLevel();
            float maxZoomLevel = this.f10892p.getMaxZoomLevel();
            if (f >= minZoomLevel) {
                minZoomLevel = f;
            }
            if (minZoomLevel <= maxZoomLevel) {
                maxZoomLevel = minZoomLevel;
            }
            this.f10892p.animateCamera(CameraUpdateFactory.zoomTo(maxZoomLevel));
            this.g = maxZoomLevel;
        }
    }

    /* renamed from: a */
    public void mo3449a(Activity activity, C2286e c2286e, boolean z, ScrollView scrollView) {
        m11697b(activity, c2286e, z, scrollView);
        FragmentManager fragmentManager = activity.getFragmentManager();
        DisplayMetrics dm = getDm();
        if (GoogleMapManager.m9659a((Context) activity)) {
            this.f10890n = new MapFragment();
            this.a.getFragmentManager().beginTransaction().add(C1373R.id.googleMap_View_FL, this.f10890n, "map").commitAllowingStateLoss();
            this.f10890n.getMapAsync(this);
            this.f10899w = new Builder(getContext()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
            m11743k();
            return;
        }
        this.f10891o.setVisibility(0);
        LayoutParams layoutParams = this.f10891o.getLayoutParams();
        layoutParams.height = dm.widthPixels;
        layoutParams.width = dm.widthPixels;
        this.f10891o.setLayoutParams(layoutParams);
        if (this.f10890n != null) {
            fragmentManager.beginTransaction().remove(this.f10890n).commit();
        }
    }

    /* renamed from: a */
    public void m11756a(LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null && this.f10892p != null) {
            this.f10892p.clear();
            this.f10892p.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_detail_start)).position(latLng));
            this.f10892p.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_finish_end)).position(latLng2));
        }
    }

    /* renamed from: a */
    protected Object m11746a(LatLng latLng, int i) {
        if (latLng == null || this.f10892p == null) {
            return null;
        }
        Object addMarker = this.f10892p.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(i)).zIndex(1.0f).position(latLng));
        if (addMarker == null) {
            return addMarker;
        }
        m11699b(addMarker);
        return addMarker;
    }

    /* renamed from: a */
    protected Object m11747a(LatLng latLng, BitmapDescriptor bitmapDescriptor) {
        if (latLng == null || this.f10892p == null || bitmapDescriptor == null) {
            return null;
        }
        Object addMarker = this.f10892p.addMarker(new MarkerOptions().icon(bitmapDescriptor).zIndex(1.0f).position(latLng));
        if (addMarker == null) {
            return addMarker;
        }
        m11699b(addMarker);
        return addMarker;
    }

    /* renamed from: a */
    public Polyline m11745a(List<LatLng> list, int i, int i2) {
        if (list == null || list.size() < 1 || this.f10892p == null) {
            return null;
        }
        Polyline addPolyline = this.f10892p.addPolyline(new PolylineOptions().width((float) i).color(i2).addAll(list).visible(true).zIndex(50.0f));
        m11694a((Object) addPolyline);
        return addPolyline;
    }

    /* renamed from: b */
    public Polyline m11757b(List<LatLng> list) {
        Polyline polyline = null;
        if (!(list == null || list.size() < 1 || this.f10892p == null)) {
            int parseColor = Color.parseColor("#ff102d");
            if (list.size() <= 5) {
                polyline = this.f10892p.addPolyline(new PolylineOptions().width(8.0f).color(parseColor).addAll(list).visible(true).zIndex(50.0f));
            } else {
                List a = C2553b.m12783a(list.size());
                Polyline polyline2 = null;
                int i = 5;
                while (i < list.size()) {
                    int intValue;
                    if (i < a.size()) {
                        intValue = ((Integer) a.get(i)).intValue();
                    } else {
                        intValue = parseColor;
                    }
                    Iterable arrayList = new ArrayList();
                    for (parseColor = 0; parseColor <= 5; parseColor++) {
                        arrayList.add(list.get(i - parseColor));
                    }
                    Polyline addPolyline = this.f10892p.addPolyline(new PolylineOptions().width(8.0f).color(intValue).addAll(arrayList).visible(true).zIndex(50.0f));
                    i++;
                    parseColor = intValue;
                    polyline2 = addPolyline;
                }
                polyline = polyline2;
            }
            if (polyline != null) {
                m11694a((Object) polyline);
            }
        }
        return polyline;
    }

    /* renamed from: a */
    protected void mo3446a(double d, double d2) {
        this.f10892p.animateCamera(CameraUpdateFactory.newLatLng(C2558g.m12841a(d, d2)));
    }

    /* renamed from: c */
    public void mo3457c(List<LatLng> list) {
        if (list != null && !list.isEmpty() && this.f10892p != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (LatLng include : list) {
                builder.include(include);
            }
            try {
                this.f10892p.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100), this);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    public void mo3454b(List<LatLng> list, int i, int i2) {
        if (list != null && !list.isEmpty() && this.f10892p != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (LatLng include : list) {
                builder.include(include);
            }
            try {
                this.f10892p.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), i, i2, 100), this);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    protected void mo3448a(int i, int i2) {
        this.f10892p.animateCamera(CameraUpdateFactory.scrollBy((float) i, (float) i2), this);
    }

    /* renamed from: a */
    public void mo3450a(final C1950e c1950e) {
        if (c1950e != null && this.f10892p != null) {
            this.f10892p.snapshot(new GoogleMap$SnapshotReadyCallback(this) {
                /* renamed from: b */
                final /* synthetic */ C2302b f10885b;

                public void onSnapshotReady(Bitmap bitmap) {
                    c1950e.mo3296a(bitmap);
                }
            });
        }
    }

    /* renamed from: h */
    public void mo3463h() {
        super.mo3463h();
        DisplayMetrics dm = getDm();
        LayoutParams layoutParams = this.f10893q.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        this.f10893q.setLayoutParams(layoutParams);
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.f10896t = location;
            mo3462g();
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude);
            List arrayList = new ArrayList();
            arrayList.add(latLng);
            mo3457c(arrayList);
            if (this.i != null) {
                com.mapbox.mapboxsdk.geometry.LatLng f = C2558g.m12850f(latitude, longitude);
                this.i.mo3198c(f.getLatitude(), f.getLongitude());
            }
        }
    }

    public void onMarkerDragStart(Marker marker) {
        if (this.h != null) {
            this.h.mo3200a(marker.getPosition().latitude, marker.getPosition().longitude);
        }
    }

    public void onMarkerDrag(Marker marker) {
    }

    public void onMarkerDragEnd(Marker marker) {
        if (this.c != null) {
            this.c.mo3441b();
        }
        if (this.h != null) {
            this.h.mo3204b(marker.getPosition().latitude, marker.getPosition().longitude);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.f10892p = googleMap;
        this.f10895s = true;
        if (this.c != null) {
            this.c.mo3440a();
        }
        this.f10892p.getUiSettings().setZoomControlsEnabled(false);
        this.f10892p.getUiSettings().setCompassEnabled(false);
        this.f10892p.getUiSettings().setRotateGesturesEnabled(false);
        this.f10894r.setOnTouchListener(new C22992(this));
        this.f10892p.setOnMarkerDragListener(this);
        this.f10892p.setOnCameraChangeListener(this);
        this.f10892p.setOnMarkerClickListener(new C23003(this));
        this.f10892p.setOnPolylineClickListener(new C23014(this));
        this.e = this.f10892p.getMaxZoomLevel();
        this.f = this.f10892p.getMinZoomLevel();
        this.f10899w.connect();
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        if (cameraPosition != null) {
            float f;
            String string;
            if (this.c != null) {
                this.c.mo3441b();
            }
            if (this.h != null) {
                this.h.mo3204b(cameraPosition.target.latitude, cameraPosition.target.longitude);
            }
            if (C1849a.m9645b(getContext())) {
                f = 1000.0f;
            } else {
                f = 1609.344f;
            }
            LatLngBounds latLngBounds = this.f10892p.getProjection().getVisibleRegion().latLngBounds;
            float[] fArr = new float[1];
            Location.distanceBetween(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, latLngBounds.southwest.latitude, latLngBounds.northeast.longitude, fArr);
            float f2 = (fArr[0] / f) * this.f10898v;
            float pow = (float) Math.pow(10.0d, (double) ((float) Math.floor(Math.log10((double) f2))));
            f2 = (float) ((int) (((double) ((f2 / pow) / 2.0f)) + 0.5d));
            if (f2 > 5.0f) {
                f2 = 10.0f;
            } else if (f2 > 2.0f) {
                f2 = 5.0f;
            } else if (f2 > 1.0f) {
                f2 = 2.0f;
            }
            pow *= f2;
            if (pow < 1.0f) {
                pow *= 1000.0f;
                if (C1849a.m9645b(getContext())) {
                    string = getResources().getString(C1373R.string.metre);
                } else {
                    string = "feet";
                }
            } else if (C1849a.m9645b(getContext())) {
                string = getResources().getString(C1373R.string.kilometre);
            } else {
                string = "mile";
            }
            String format = this.f10900x.format((double) pow);
            if (this.h != null) {
                this.h.mo3196a(format, string);
            }
        }
    }

    /* renamed from: i */
    protected void mo3464i() {
        if (this.f10892p != null) {
            this.f10892p.clear();
        }
    }

    public void onConnected(@Nullable Bundle bundle) {
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.f10899w);
        if (lastLocation != null) {
            this.f10896t = lastLocation;
            mo3462g();
            LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            List arrayList = new ArrayList();
            arrayList.add(latLng);
            mo3457c(arrayList);
        }
    }

    /* renamed from: k */
    private void m11743k() {
        this.f10901y = new LocationRequest();
        this.f10901y.setInterval(AbstractComponentTracker.LINGERING_TIMEOUT);
        this.f10901y.setFastestInterval(5000);
        this.f10901y.setPriority(100);
    }

    public void c_() {
        if (this.f10899w == null || !this.f10899w.isConnected()) {
            f10889m.error("GoogleApiClient is not connected");
        } else {
            LocationServices.FusedLocationApi.requestLocationUpdates(this.f10899w, this.f10901y, this);
        }
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void onFinish() {
        if (this.l != null) {
            this.l.mo3327i();
        }
    }

    public void onCancel() {
    }
}
