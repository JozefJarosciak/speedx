package com.beastbikes.android.modules.cycling.sections.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.GoogleMapManager;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.sections.dto.C2221b;
import com.beastbikes.android.modules.cycling.sections.dto.C2223d;
import com.beastbikes.android.modules.cycling.sections.p069a.C2219a;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.polyline.C2571a;
import com.beastbikes.android.utils.polyline.Point;
import com.beastbikes.android.widget.PullRefreshListView4ScrollView;
import com.beastbikes.android.widget.ScrollView4CheckBottom;
import com.beastbikes.android.widget.ScrollView4CheckBottom.C2601a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903183)
public class SectionDetailActivity extends SessionFragmentActivity implements OnClickListener, OnItemClickListener, OnMapLoadedCallback, C1371a, C2601a, OnMapReadyCallback {
    /* renamed from: A */
    private long f5692A;
    /* renamed from: B */
    private float f5693B;
    /* renamed from: C */
    private float f5694C;
    /* renamed from: D */
    private boolean f5695D = true;
    /* renamed from: E */
    private int f5696E = 1;
    /* renamed from: F */
    private int f5697F = 10;
    /* renamed from: G */
    private boolean f5698G = false;
    /* renamed from: H */
    private Builder f5699H;
    /* renamed from: I */
    private int f5700I;
    /* renamed from: J */
    private int f5701J;
    /* renamed from: K */
    private boolean f5702K;
    @C1458a(a = 2131755950)
    /* renamed from: a */
    private MapView f5703a;
    @C1458a(a = 2131755948)
    /* renamed from: b */
    private ScrollView4CheckBottom f5704b;
    @C1458a(a = 2131755953)
    /* renamed from: c */
    private ImageView f5705c;
    @C1458a(a = 2131755949)
    /* renamed from: d */
    private RelativeLayout f5706d;
    @C1458a(a = 2131755952)
    /* renamed from: e */
    private RelativeLayout f5707e;
    /* renamed from: f */
    private BaiduMap f5708f;
    /* renamed from: g */
    private LocationClient f5709g;
    /* renamed from: h */
    private int f5710h;
    @C1458a(a = 2131755969)
    /* renamed from: i */
    private PullRefreshListView4ScrollView f5711i;
    /* renamed from: j */
    private MapFragment f5712j;
    /* renamed from: k */
    private GoogleMap f5713k;
    /* renamed from: l */
    private float f5714l = 16.0f;
    /* renamed from: m */
    private C2230b f5715m;
    /* renamed from: n */
    private List<C2223d> f5716n = new ArrayList();
    @C1458a(a = 2131755955)
    /* renamed from: o */
    private TextView f5717o;
    @C1458a(a = 2131755957)
    /* renamed from: p */
    private TextView f5718p;
    @C1458a(a = 2131755958)
    /* renamed from: q */
    private TextView f5719q;
    @C1458a(a = 2131755959)
    /* renamed from: r */
    private TextView f5720r;
    @C1458a(a = 2131755960)
    /* renamed from: s */
    private RatingBar f5721s;
    @C1458a(a = 2131755961)
    /* renamed from: t */
    private TextView f5722t;
    @C1458a(a = 2131755964)
    /* renamed from: u */
    private ImageView f5723u;
    @C1458a(a = 2131755965)
    /* renamed from: v */
    private TextView f5724v;
    /* renamed from: w */
    private C2219a f5725w;
    /* renamed from: x */
    private C1802i f5726x;
    /* renamed from: y */
    private List<LatLng> f5727y = new ArrayList();
    /* renamed from: z */
    private C2221b f5728z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.f5692A = intent.getLongExtra("speedx_section_id", -1);
            if (this.f5692A != -1) {
                this.f5695D = C1849a.b(this);
                if (C1849a.a()) {
                    m6969f();
                } else {
                    m6971g();
                }
                this.f5725w = new C2219a(this);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                this.f5710h = displayMetrics.widthPixels;
                LayoutParams layoutParams = this.f5706d.getLayoutParams();
                layoutParams.width = this.f5710h;
                layoutParams.height = (int) (((double) this.f5710h) * 0.5556d);
                this.f5706d.setLayoutParams(layoutParams);
                this.f5715m = new C2230b(this, this.f5716n);
                this.f5711i.setAdapter(this.f5715m);
                this.f5711i.b(C1373R.color.blackFive);
                this.f5711i.setPullRefreshEnable(false);
                this.f5711i.setOnItemClickListener(this);
                this.f5704b.smoothScrollTo(0, 20);
                this.f5704b.setScrollViewLoadMoreListener(this);
                this.f5723u.setOnClickListener(this);
                SharedPreferences sharedPreferences = getSharedPreferences(C1848b.a().getClass().getName(), 0);
                this.f5693B = Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
                this.f5694C = Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
                m6963c();
                m6967e();
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.section_detail_favourite_iv:
                this.f5726x = new C1802i(this, getString(C1373R.string.str_loading), true);
                this.f5723u.setEnabled(false);
                m6960b();
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.f5716n != null && this.f5716n.size() != 0) {
            C2223d c2223d = (C2223d) this.f5716n.get(i - 1);
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_id", c2223d.c());
            startActivity(intent);
        }
    }

    /* renamed from: a */
    public void m6986a() {
        if (!this.f5698G) {
            this.f5698G = true;
            this.f5696E++;
            m6967e();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.f5703a.onDestroy();
    }

    public void onResume() {
        super.onResume();
        this.f5703a.onResume();
    }

    public void onPause() {
        super.onPause();
        this.f5703a.onPause();
    }

    /* renamed from: b */
    private void m6960b() {
        getAsyncTaskQueue().a(new SectionDetailActivity$1(this), new Void[0]);
    }

    /* renamed from: c */
    private void m6963c() {
        getAsyncTaskQueue().a(new SectionDetailActivity$2(this), new Void[0]);
    }

    /* renamed from: d */
    private void m6965d() {
        if (this.f5728z != null && this.f5702K) {
            m6956a(new com.google.android.gms.maps.model.LatLng(this.f5728z.c(), this.f5728z.b()));
            m6957a(this.f5728z.e());
        }
    }

    /* renamed from: e */
    private void m6967e() {
        getAsyncTaskQueue().a(new SectionDetailActivity$3(this), new Void[0]);
    }

    /* renamed from: a */
    private void m6953a(LatLng latLng) {
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.from(CoordType.GPS);
        coordinateConverter.coord(latLng);
        LatLng convert = coordinateConverter.convert();
        BitmapDescriptor fromResource = BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect);
        this.f5708f.addOverlay(new MarkerOptions().position(convert).icon(fromResource).extraInfo(new Bundle()));
    }

    /* renamed from: a */
    private void m6956a(com.google.android.gms.maps.model.LatLng latLng) {
        if (this.f5713k != null) {
            this.f5713k.addMarker(new com.google.android.gms.maps.model.MarkerOptions().icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_section_map_marker_unselect)).position(latLng));
        }
    }

    /* renamed from: a */
    private void m6957a(String str) {
        List a = new C2571a().a(str);
        if (a != null && a.size() != 0 && this.f5713k != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            Iterable arrayList = new ArrayList();
            for (int i = 0; i < a.size(); i++) {
                com.google.android.gms.maps.model.LatLng latLng = new com.google.android.gms.maps.model.LatLng(((Point) a.get(i)).getLat(), ((Point) a.get(i)).getLng());
                arrayList.add(latLng);
                builder.include(latLng);
            }
            int parseColor = Color.parseColor("#ff102d");
            if (a.size() >= 2 && a.size() <= 10000) {
                PolylineOptions zIndex = new PolylineOptions().width(8.0f).color(parseColor).addAll(arrayList).visible(true).zIndex(50.0f);
                if (this.f5713k != null) {
                    this.f5713k.addPolyline(zIndex);
                } else {
                    return;
                }
            }
            this.f5713k.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100));
        }
    }

    /* renamed from: b */
    private void m6961b(String str) {
        List a = new C2571a().a(str);
        if (a != null && a.size() != 0 && this.f5708f != null) {
            CoordinateConverter coordinateConverter = new CoordinateConverter();
            coordinateConverter.from(CoordType.GPS);
            this.f5699H = new Builder();
            for (int i = 0; i < a.size(); i++) {
                coordinateConverter.coord(new LatLng(((Point) a.get(i)).getLat(), ((Point) a.get(i)).getLng()));
                LatLng convert = coordinateConverter.convert();
                this.f5727y.add(convert);
                this.f5699H.include(convert);
            }
            int parseColor = Color.parseColor("#ff102d");
            if (a.size() >= 2 && a.size() <= 10000) {
                this.f5708f.addOverlay(new com.baidu.mapapi.map.PolylineOptions().width(8).color(parseColor).points(this.f5727y).visible(true).zIndex(50));
            }
            this.f5700I = this.f5703a.getWidth();
            this.f5701J = this.f5703a.getHeight();
            this.f5708f.setMapStatus(MapStatusUpdateFactory.newLatLngBounds(this.f5699H.build(), this.f5700I, this.f5701J));
        }
    }

    /* renamed from: f */
    private void m6969f() {
        this.f5703a.getChildAt(1).setVisibility(8);
        this.f5708f = this.f5703a.getMap();
        this.f5708f.getUiSettings().setOverlookingGesturesEnabled(false);
        this.f5703a.showZoomControls(false);
        this.f5703a.showScaleControl(false);
        this.f5708f.setOnMapLoadedCallback(this);
        this.f5708f.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, null));
        this.f5708f.setMyLocationEnabled(true);
        this.f5708f.animateMapStatus(MapStatusUpdateFactory.zoomTo(this.f5714l));
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setPriority(1);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(5000);
        locationClientOption.setAddrType("all");
        this.f5709g = new LocationClient(this);
        this.f5709g.setLocOption(locationClientOption);
        this.f5709g.start();
        this.f5709g.requestLocation();
        this.f5703a.getChildAt(0).setOnTouchListener(new SectionDetailActivity$4(this));
    }

    /* renamed from: g */
    private void m6971g() {
        if (GoogleMapManager.a(this)) {
            GoogleMapOptions googleMapOptions = new GoogleMapOptions();
            googleMapOptions.mapType(1).compassEnabled(false).rotateGesturesEnabled(false).tiltGesturesEnabled(false).zoomControlsEnabled(false).zoomGesturesEnabled(true);
            this.f5712j = MapFragment.newInstance(googleMapOptions);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.add(C1373R.id.section_google_mapview, this.f5712j);
            beginTransaction.commit();
            this.f5712j.getMapAsync(this);
            this.f5705c.setOnTouchListener(new SectionDetailActivity$5(this));
            return;
        }
        this.f5707e.setVisibility(0);
    }

    /* renamed from: h */
    private void m6973h() {
        this.f5713k.setMyLocationEnabled(true);
        this.f5713k.getUiSettings().setMyLocationButtonEnabled(false);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.f5702K = true;
        this.f5713k = googleMap;
        m6973h();
        m6965d();
    }

    public void onMapLoaded() {
        if (this.f5703a != null) {
            this.f5700I = this.f5703a.getWidth();
            this.f5701J = this.f5703a.getHeight();
            if (this.f5699H != null) {
                this.f5708f.setMapStatus(MapStatusUpdateFactory.newLatLngBounds(this.f5699H.build(), this.f5700I, this.f5701J));
            }
        }
    }
}
