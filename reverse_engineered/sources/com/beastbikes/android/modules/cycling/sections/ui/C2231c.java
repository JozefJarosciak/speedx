package com.beastbikes.android.modules.cycling.sections.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.C1857d;
import com.beastbikes.android.locale.googlemaputils.GoogleMapManager;
import com.beastbikes.android.modules.cycling.sections.dto.C2222c;
import com.beastbikes.android.utils.C2579v;
import com.beastbikes.android.utils.polyline.C2571a;
import com.beastbikes.android.utils.polyline.Point;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap$OnMapClickListener;
import com.google.android.gms.maps.GoogleMap$OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: SectionMapManager */
/* renamed from: com.beastbikes.android.modules.cycling.sections.ui.c */
public class C2231c implements OnClickListener, OnMapClickListener, OnMapLoadedCallback, OnMarkerClickListener, C1857d, LocationListener, GoogleMap$OnMapClickListener, GoogleMap$OnMarkerClickListener, OnMapReadyCallback {
    /* renamed from: C */
    private static final Logger f10612C = LoggerFactory.getLogger(C2231c.class);
    /* renamed from: A */
    private float f10613A = 0.0f;
    /* renamed from: B */
    private boolean f10614B = true;
    /* renamed from: D */
    private float f10615D = 16.0f;
    /* renamed from: a */
    private Activity f10616a;
    /* renamed from: b */
    private MapView f10617b;
    /* renamed from: c */
    private boolean f10618c = C1849a.m9641a();
    /* renamed from: d */
    private MapView f10619d;
    /* renamed from: e */
    private BaiduMap f10620e;
    /* renamed from: f */
    private Marker f10621f;
    /* renamed from: g */
    private final String f10622g = "baidu_Marker_Position";
    /* renamed from: h */
    private GoogleMap f10623h;
    /* renamed from: i */
    private GoogleMapManager f10624i;
    /* renamed from: j */
    private LocationRequest f10625j;
    /* renamed from: k */
    private MapFragment f10626k;
    /* renamed from: l */
    private boolean f10627l = false;
    /* renamed from: m */
    private LatLng f10628m;
    /* renamed from: n */
    private int f10629n;
    /* renamed from: o */
    private com.google.android.gms.maps.model.Marker f10630o;
    /* renamed from: p */
    private List<C2222c> f10631p;
    /* renamed from: q */
    private RelativeLayout f10632q;
    /* renamed from: r */
    private CircleImageView f10633r;
    /* renamed from: s */
    private TextView f10634s;
    /* renamed from: t */
    private TextView f10635t;
    /* renamed from: u */
    private TextView f10636u;
    /* renamed from: v */
    private RatingBar f10637v;
    /* renamed from: w */
    private TextView f10638w;
    /* renamed from: x */
    private TextView f10639x;
    /* renamed from: y */
    private TextView f10640y;
    /* renamed from: z */
    private long f10641z;

    public C2231c(Activity activity) {
        this.f10616a = activity;
        this.f10617b = (MapView) this.f10616a.findViewById(C1373R.id.activity_competition_section_mapview);
        this.f10632q = (RelativeLayout) this.f10616a.findViewById(C1373R.id.frag_section_map_info_rl);
        this.f10633r = (CircleImageView) this.f10616a.findViewById(C1373R.id.item_competition_section_avater);
        this.f10634s = (TextView) this.f10616a.findViewById(C1373R.id.item_competition_section_title);
        this.f10635t = (TextView) this.f10616a.findViewById(C1373R.id.item_competition_section_diatance);
        this.f10636u = (TextView) this.f10616a.findViewById(C1373R.id.item_competition_section_owner);
        this.f10637v = (RatingBar) this.f10616a.findViewById(C1373R.id.section_ratingbar);
        this.f10638w = (TextView) this.f10616a.findViewById(C1373R.id.section_elevation);
        this.f10639x = (TextView) this.f10616a.findViewById(C1373R.id.item_competition_section_total_distance);
        this.f10640y = (TextView) this.f10616a.findViewById(C1373R.id.item_competition_section_total_distance_unit);
        this.f10632q.setOnClickListener(this);
        if (this.f10618c) {
            m11468a(this.f10617b);
            return;
        }
        this.f10617b.setVisibility(8);
        m11473c();
    }

    /* renamed from: a */
    private void m11468a(MapView mapView) {
        this.f10619d = mapView;
        if (this.f10619d != null) {
            this.f10619d.getChildAt(1).setVisibility(8);
            this.f10620e = this.f10619d.getMap();
            this.f10620e.getUiSettings().setOverlookingGesturesEnabled(false);
            this.f10619d.showZoomControls(false);
            this.f10619d.showScaleControl(false);
            this.f10620e.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, null));
            this.f10620e.setMyLocationEnabled(true);
            this.f10620e.animateMapStatus(MapStatusUpdateFactory.zoomTo(this.f10615D));
            this.f10620e.setOnMarkerClickListener(this);
            this.f10620e.setOnMapClickListener(this);
            this.f10620e.setOnMapLoadedCallback(this);
        }
    }

    /* renamed from: c */
    private void m11473c() {
        if (GoogleMapManager.m9659a(this.f10616a)) {
            GoogleMapOptions googleMapOptions = new GoogleMapOptions();
            googleMapOptions.mapType(1).compassEnabled(false).rotateGesturesEnabled(false).tiltGesturesEnabled(false).zoomControlsEnabled(false).zoomGesturesEnabled(true);
            this.f10626k = MapFragment.newInstance(googleMapOptions);
            FragmentTransaction beginTransaction = this.f10616a.getFragmentManager().beginTransaction();
            beginTransaction.add(C1373R.id.section_google_mapview, this.f10626k);
            beginTransaction.commit();
            this.f10626k.getMapAsync(this);
        }
    }

    public void onMapClick(com.baidu.mapapi.model.LatLng latLng) {
        if (this.f10632q.getVisibility() == 0) {
            this.f10632q.setVisibility(8);
        }
        if (this.f10621f != null) {
            this.f10621f.setIcon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect));
        }
    }

    public void onMapClick(LatLng latLng) {
        if (this.f10632q.getVisibility() == 0) {
            this.f10632q.setVisibility(8);
        }
        if (this.f10630o != null) {
            this.f10630o.setIcon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.frag_section_map_info_rl:
                Intent intent = new Intent(this.f10616a, SectionDetailActivity.class);
                intent.putExtra("speedx_section_id", this.f10641z);
                this.f10616a.startActivity(intent);
                return;
            default:
                return;
        }
    }

    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    public void onMapLoaded() {
        if (this.f10631p != null && this.f10631p.size() != 0) {
            m11472b(this.f10631p);
        }
    }

    public boolean onMarkerClick(Marker marker) {
        if (!(this.f10631p == null || this.f10631p.size() == 0)) {
            int i = marker.getExtraInfo().getInt("baidu_Marker_Position", -1);
            if (i != -1) {
                marker.setIcon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_selected));
                m11470a((C2222c) this.f10631p.get(i));
                if (this.f10621f != null) {
                    this.f10621f.setIcon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect));
                }
                this.f10621f = marker;
            }
        }
        return false;
    }

    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
        if (!(this.f10631p == null || this.f10631p.size() == 0)) {
            int parseInt = Integer.parseInt(marker.getTitle());
            marker.setIcon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_selected));
            m11470a((C2222c) this.f10631p.get(parseInt));
            if (this.f10630o != null) {
                this.f10630o.setIcon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect));
            }
            this.f10630o = marker;
        }
        return true;
    }

    /* renamed from: a */
    private void m11470a(C2222c c2222c) {
        Object k = c2222c.m11428k();
        if (TextUtils.isEmpty(k)) {
            Picasso.with(this.f10616a).load(C1373R.drawable.ic_launcher).fit().placeholder(C1373R.drawable.ic_launcher).error(C1373R.drawable.ic_launcher).centerCrop().into(this.f10633r);
        } else {
            Picasso.with(this.f10616a).load(k).fit().placeholder(C1373R.drawable.ic_launcher).error(C1373R.drawable.ic_launcher).centerCrop().into(this.f10633r);
        }
        this.f10634s.setText(c2222c.m11422e());
        if (TextUtils.isEmpty(c2222c.m11424g())) {
            this.f10636u.setText(this.f10616a.getResources().getString(C1373R.string.section_no_lord));
        } else {
            this.f10636u.setText(c2222c.m11424g() + this.f10616a.getResources().getString(C1373R.string.occupy));
        }
        this.f10637v.setRating((float) c2222c.m11427j());
        this.f10641z = c2222c.m11421d();
        this.f10632q.setVisibility(0);
        if (C1849a.m9645b(this.f10616a)) {
            this.f10635t.setText(this.f10616a.getResources().getString(C1373R.string.distance_less_than) + C2579v.m12904a(c2222c.m11419b() / 1000.0d) + this.f10616a.getResources().getString(C1373R.string.str_mileage_unit_km));
            this.f10638w.setText(this.f10616a.getResources().getString(C1373R.string.str_elevation_diff) + " " + ((int) c2222c.m11423f()) + ANSIConstants.ESC_END);
            this.f10639x.setText("" + C2579v.m12904a(c2222c.m11420c() / 1000.0d));
            this.f10640y.setText(this.f10616a.getResources().getString(C1373R.string.kilometre));
            return;
        }
        this.f10635t.setText(this.f10616a.getResources().getString(C1373R.string.distance_less_than) + C2579v.m12904a(C1849a.m9638a(c2222c.m11419b() / 1000.0d)) + this.f10616a.getResources().getString(C1373R.string.str_mileage_unit_mile));
        this.f10638w.setText(this.f10616a.getResources().getString(C1373R.string.str_elevation_diff) + " " + ((int) C1849a.m9646c(c2222c.m11423f())) + "feet");
        this.f10639x.setText("" + C2579v.m12904a(C1849a.m9638a(c2222c.m11420c() / 1000.0d)));
        this.f10640y.setText(this.f10616a.getResources().getString(C1373R.string.miles));
    }

    public void onMapReady(GoogleMap googleMap) {
        this.f10623h = googleMap;
        m11475d();
        this.f10624i = new GoogleMapManager();
        this.f10624i.m9663a(this.f10616a, this);
        this.f10624i.m9662a();
        m11474c(this.f10631p);
    }

    /* renamed from: d */
    private void m11475d() {
        this.f10623h.setMyLocationEnabled(true);
        this.f10623h.getUiSettings().setMyLocationButtonEnabled(false);
        this.f10623h.setOnMarkerClickListener(this);
        this.f10623h.setOnMapClickListener(this);
        this.f10625j = new LocationRequest();
        this.f10625j.setInterval(5000);
        this.f10625j.setFastestInterval(5000);
        this.f10625j.setPriority(100);
    }

    /* renamed from: a */
    public void m11480a(Location location) {
        if (this.f10618c) {
            if (this.f10620e != null) {
                this.f10620e.animateMapStatus(MapStatusUpdateFactory.newLatLng(new com.baidu.mapapi.model.LatLng(location.getLatitude(), location.getLongitude())));
            }
        } else if (this.f10623h != null) {
            this.f10623h.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(16.0f).build()));
        }
    }

    /* renamed from: a */
    public void mo3432a(Location location, Bundle bundle) {
        if (!this.f10627l) {
            m11478a();
        }
    }

    /* renamed from: a */
    protected void m11478a() {
        if (this.f10624i != null && this.f10625j != null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(this.f10624i.m9665c(), this.f10625j, this);
        }
    }

    /* renamed from: a */
    public void mo3433a(ConnectionResult connectionResult) {
    }

    /* renamed from: a */
    public void mo3431a(int i) {
    }

    public void onLocationChanged(Location location) {
        if (location == null) {
            f10612C.info("google onLocationChanged null");
            return;
        }
        this.f10628m = new LatLng(location.getLatitude(), location.getLongitude());
        if (this.f10614B) {
            this.f10614B = false;
            this.f10623h.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(this.f10628m).zoom(16.0f).build()));
        }
    }

    /* renamed from: b */
    private void m11472b(List<C2222c> list) {
        if (this.f10620e != null) {
            this.f10620e.clear();
            for (int i = 0; i < list.size(); i++) {
                m11469a(new com.baidu.mapapi.model.LatLng(((C2222c) list.get(i)).m11426i(), ((C2222c) list.get(i)).m11425h()), i);
                m11476d(new C2571a().m12884a(((C2222c) list.get(i)).m11418a()));
            }
        }
    }

    /* renamed from: c */
    private void m11474c(List<C2222c> list) {
        if (this.f10623h != null && list != null && list.size() != 0) {
            this.f10623h.clear();
            for (int i = 0; i < list.size(); i++) {
                m11471a(new LatLng(((C2222c) list.get(i)).m11426i(), ((C2222c) list.get(i)).m11425h()), i);
                m11477e(new C2571a().m12884a(((C2222c) list.get(i)).m11418a()));
            }
        }
    }

    /* renamed from: a */
    private void m11469a(com.baidu.mapapi.model.LatLng latLng, int i) {
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.from(CoordType.GPS);
        coordinateConverter.coord(latLng);
        com.baidu.mapapi.model.LatLng convert = coordinateConverter.convert();
        BitmapDescriptor fromResource = BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect);
        Bundle bundle = new Bundle();
        bundle.putInt("baidu_Marker_Position", i);
        this.f10620e.addOverlay(new MarkerOptions().position(convert).icon(fromResource).extraInfo(bundle));
    }

    /* renamed from: a */
    private void m11471a(LatLng latLng, int i) {
        this.f10629n = i;
        this.f10623h.addMarker(new com.google.android.gms.maps.model.MarkerOptions().icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect)).position(latLng).title("" + i));
    }

    /* renamed from: d */
    private void m11476d(List<Point> list) {
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.from(CoordType.GPS);
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            coordinateConverter.coord(new com.baidu.mapapi.model.LatLng(((Point) list.get(i)).getLat(), ((Point) list.get(i)).getLng()));
            arrayList.add(coordinateConverter.convert());
        }
        int parseColor = Color.parseColor("#ff102d");
        if (list.size() >= 2 && list.size() <= 10000) {
            this.f10620e.addOverlay(new PolylineOptions().width(8).color(parseColor).points(arrayList).visible(true).zIndex(50));
        }
    }

    /* renamed from: e */
    private void m11477e(List<Point> list) {
        Iterable arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new LatLng(((Point) list.get(i)).getLat(), ((Point) list.get(i)).getLng()));
        }
        int parseColor = Color.parseColor("#ff102d");
        if (list.size() >= 2 && list.size() <= 10000) {
            com.google.android.gms.maps.model.PolylineOptions zIndex = new com.google.android.gms.maps.model.PolylineOptions().width(8.0f).color(parseColor).addAll(arrayList).visible(true).zIndex(50.0f);
            if (this.f10623h != null) {
                this.f10623h.addPolyline(zIndex);
            }
        }
    }

    /* renamed from: b */
    public void m11485b() {
        if (this.f10620e != null) {
            this.f10620e.clear();
        }
        if (this.f10623h != null) {
            this.f10623h.clear();
        }
    }

    /* renamed from: a */
    public void m11484a(List<C2222c> list) {
        if (list != null && list.size() != 0) {
            this.f10631p = list;
            if (this.f10618c) {
                m11472b(this.f10631p);
            } else {
                m11474c(this.f10631p);
            }
        }
    }

    /* renamed from: a */
    public void m11482a(BDLocation bDLocation) {
        if (bDLocation != null && this.f10619d != null) {
            try {
                double latitude = bDLocation.getLatitude();
                double longitude = bDLocation.getLongitude();
                com.baidu.mapapi.model.LatLng latLng = new com.baidu.mapapi.model.LatLng(latitude, longitude);
                this.f10620e.setMyLocationData(new MyLocationData.Builder().direction(this.f10613A).accuracy(bDLocation.getRadius()).latitude(latitude).longitude(longitude).build());
                if (this.f10614B) {
                    this.f10620e.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
                }
                this.f10614B = false;
            } catch (Throwable e) {
                f10612C.error("Unexpected error", e);
            }
        }
    }
}
