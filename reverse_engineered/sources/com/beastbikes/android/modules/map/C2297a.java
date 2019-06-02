package com.beastbikes.android.modules.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapDrawFrameCallback;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.BaiduMap.OnMapRenderCallback;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnPolylineClickListener;
import com.baidu.mapapi.map.BaiduMap.SnapshotReadyCallback;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.map.C2296d.C1950e;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.android.utils.C2558g;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: Map4Baidu */
/* renamed from: com.beastbikes.android.modules.map.a */
public class C2297a extends C2296d<LatLng, BitmapDescriptor> implements BDLocationListener, OnMapStatusChangeListener {
    /* renamed from: m */
    private final DecimalFormat f10880m = new DecimalFormat("0");
    /* renamed from: n */
    private MapView f10881n;
    /* renamed from: o */
    private BaiduMap f10882o;
    /* renamed from: p */
    private LocationClient f10883p;

    /* compiled from: Map4Baidu */
    /* renamed from: com.beastbikes.android.modules.map.a$1 */
    class C22871 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2297a f10852a;

        C22871(C2297a c2297a) {
            this.f10852a = c2297a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f10852a.d != null) {
                if (motionEvent.getAction() == 1) {
                    this.f10852a.d.requestDisallowInterceptTouchEvent(false);
                } else {
                    this.f10852a.d.requestDisallowInterceptTouchEvent(true);
                }
            }
            return false;
        }
    }

    /* compiled from: Map4Baidu */
    /* renamed from: com.beastbikes.android.modules.map.a$3 */
    class C22893 implements OnMapRenderCallback {
        /* renamed from: a */
        final /* synthetic */ C2297a f10855a;

        C22893(C2297a c2297a) {
            this.f10855a = c2297a;
        }

        public void onMapRenderFinished() {
            if (this.f10855a.l != null) {
                this.f10855a.l.mo3327i();
            }
        }
    }

    /* compiled from: Map4Baidu */
    /* renamed from: com.beastbikes.android.modules.map.a$5 */
    class C22925 implements OnMarkerClickListener {
        /* renamed from: a */
        final /* synthetic */ C2297a f10861a;

        C22925(C2297a c2297a) {
            this.f10861a = c2297a;
        }

        public boolean onMarkerClick(Marker marker) {
            if (this.f10861a.j != null) {
                this.f10861a.j.m11802a(marker);
            }
            return true;
        }
    }

    /* compiled from: Map4Baidu */
    /* renamed from: com.beastbikes.android.modules.map.a$6 */
    class C22936 implements OnPolylineClickListener {
        /* renamed from: a */
        final /* synthetic */ C2297a f10862a;

        C22936(C2297a c2297a) {
            this.f10862a = c2297a;
        }

        public boolean onPolylineClick(Polyline polyline) {
            if (this.f10862a.k != null) {
                this.f10862a.k.m11803b(polyline);
            }
            return true;
        }
    }

    /* compiled from: Map4Baidu */
    /* renamed from: com.beastbikes.android.modules.map.a$8 */
    class C22958 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2297a f10865a;

        C22958(C2297a c2297a) {
            this.f10865a = c2297a;
        }

        public void run() {
            this.f10865a.mo3447a(this.f10865a.getZoomLevel());
        }
    }

    /* renamed from: b */
    protected /* synthetic */ Object mo3451b(Point point) {
        return m11715a(point);
    }

    /* renamed from: b */
    public /* synthetic */ void mo3453b(Object obj, Object obj2) {
        m11726a((LatLng) obj, (LatLng) obj2);
    }

    /* renamed from: c */
    public /* synthetic */ Object mo3455c(List list, int i, int i2) {
        return m11714a(list, i, i2);
    }

    /* renamed from: d */
    public /* synthetic */ Object mo3458d(List list) {
        return m11728b(list);
    }

    public C2297a(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(C1373R.layout.speedx_map_with_baidu, this);
    }

    /* renamed from: a */
    protected void mo3445a() {
        super.mo3445a();
        this.f10881n.onResume();
    }

    /* renamed from: b */
    protected void mo3452b() {
        super.mo3452b();
        this.f10881n.onPause();
    }

    /* renamed from: c */
    public void mo3456c() {
        super.mo3456c();
        if (this.f10883p != null) {
            this.f10883p.stop();
            this.f10883p.unRegisterLocationListener(this);
        }
        this.f10881n.onDestroy();
    }

    /* renamed from: d */
    protected void mo3459d() {
        this.f10881n = (MapView) findViewById(C1373R.id.speedx_map_with_baidu_view);
    }

    /* renamed from: a */
    protected LatLng m11715a(Point point) {
        return this.f10882o.getProjection().fromScreenLocation(point);
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
        if (this.f10883p == null) {
            this.f10883p = new LocationClient(this.a);
            this.f10883p.registerLocationListener(this);
        }
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setLocationMode(LocationMode.Hight_Accuracy);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(5000);
        locationClientOption.setIsNeedAddress(true);
        this.f10883p.setLocOption(locationClientOption);
        if (!this.f10883p.isStarted()) {
            this.f10883p.start();
        }
        this.f10883p.requestLocation();
        this.f10882o.setMyLocationEnabled(true);
        this.f10882o.setOnMapStatusChangeListener(this);
    }

    /* renamed from: f */
    public void mo3461f() {
        if (this.f10883p != null) {
            this.f10883p.stop();
        }
    }

    /* renamed from: g */
    public void mo3462g() {
        this.f10882o.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, BitmapDescriptorFactory.fromResource(C1373R.drawable.route_map_make_location)));
    }

    /* renamed from: a */
    public void mo3447a(float f) {
        super.mo3447a(f);
        try {
            float minZoomLevel = this.f10882o.getMinZoomLevel();
            float maxZoomLevel = this.f10882o.getMaxZoomLevel();
            if (f >= minZoomLevel) {
                minZoomLevel = f;
            }
            if (minZoomLevel <= maxZoomLevel) {
                maxZoomLevel = minZoomLevel;
            }
            this.f10882o.animateMapStatus(MapStatusUpdateFactory.zoomTo(maxZoomLevel));
            this.g = maxZoomLevel;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo3449a(final Activity activity, final C2286e c2286e, final boolean z, ScrollView scrollView) {
        m11697b(activity, c2286e, z, scrollView);
        this.f10881n.setVisibility(0);
        this.f10881n.showZoomControls(false);
        this.f10881n.getChildAt(1).setVisibility(8);
        View childAt = this.f10881n.getChildAt(3);
        if (childAt != null) {
            childAt.setVisibility(8);
        }
        this.f10881n.getChildAt(0).setOnTouchListener(new C22871(this));
        this.f10882o = this.f10881n.getMap();
        if (this.f10882o != null) {
            this.f10882o.setOnMapLoadedCallback(new OnMapLoadedCallback(this) {
                /* renamed from: b */
                final /* synthetic */ C2297a f10854b;

                public void onMapLoaded() {
                    if (c2286e != null) {
                        c2286e.mo3440a();
                    }
                }
            });
            this.f10882o.setOnMapRenderCallbadk(new C22893(this));
            MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null);
            myLocationConfiguration.accuracyCircleStrokeColor = 17170445;
            myLocationConfiguration.accuracyCircleFillColor = 17170445;
            this.f10882o.setMyLocationConfigeration(myLocationConfiguration);
            this.f10882o.setMyLocationEnabled(true);
            this.f10882o.getUiSettings().setCompassEnabled(false);
            this.f10882o.getUiSettings().setRotateGesturesEnabled(false);
            this.f10882o.getUiSettings().setOverlookingGesturesEnabled(false);
            this.f10882o.setOnMapDrawFrameCallback(new OnMapDrawFrameCallback(this) {
                /* renamed from: d */
                final /* synthetic */ C2297a f10860d;

                /* compiled from: Map4Baidu */
                /* renamed from: com.beastbikes.android.modules.map.a$4$1 */
                class C22901 implements Runnable {
                    /* renamed from: a */
                    final /* synthetic */ C22914 f10856a;

                    C22901(C22914 c22914) {
                        this.f10856a = c22914;
                    }

                    public void run() {
                        if (this.f10856a.f10860d.f10882o.getProjection() != null && c2286e != null) {
                            c2286e.mo3441b();
                        }
                    }
                }

                public void onMapDrawFrame(GL10 gl10, MapStatus mapStatus) {
                    if (z) {
                        activity.runOnUiThread(new C22901(this));
                    }
                }

                public void onMapDrawFrame(MapStatus mapStatus) {
                }
            });
            this.f10882o.setOnMarkerClickListener(new C22925(this));
            this.f10882o.setOnPolylineClickListener(new C22936(this));
            this.e = this.f10882o.getMaxZoomLevel();
            this.f = this.f10882o.getMinZoomLevel();
        }
    }

    /* renamed from: a */
    public void m11726a(LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null && this.f10882o != null) {
            this.f10882o.clear();
            this.f10882o.addOverlay(new MarkerOptions().position(latLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_detail_start)));
            this.f10882o.addOverlay(new MarkerOptions().position(latLng2).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_finish_end)));
        }
    }

    /* renamed from: a */
    protected Object m11716a(LatLng latLng, int i) {
        if (latLng == null || this.f10882o == null) {
            return null;
        }
        Marker marker = (Marker) this.f10882o.addOverlay(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(i)).zIndex(1));
        if (marker == null) {
            return marker;
        }
        m11699b((Object) marker);
        return marker;
    }

    /* renamed from: a */
    protected Object m11717a(LatLng latLng, BitmapDescriptor bitmapDescriptor) {
        if (latLng == null || this.f10882o == null || bitmapDescriptor == null) {
            return null;
        }
        Marker marker = (Marker) this.f10882o.addOverlay(new MarkerOptions().position(latLng).icon(bitmapDescriptor).zIndex(1));
        if (marker == null) {
            return marker;
        }
        m11699b((Object) marker);
        return marker;
    }

    /* renamed from: a */
    public Polyline m11714a(List<LatLng> list, int i, int i2) {
        if (list == null || list.size() < 2) {
            return null;
        }
        Polyline polyline = (Polyline) this.f10882o.addOverlay(new PolylineOptions().width(i).color(i2).points(list).visible(true).zIndex(50));
        m11694a((Object) polyline);
        return polyline;
    }

    /* renamed from: b */
    public Polyline m11728b(List<LatLng> list) {
        if (list == null || list.size() < 2) {
            return null;
        }
        Polyline polyline = (Polyline) this.f10882o.addOverlay(new PolylineOptions().width(8).colorsValues(C2553b.m12783a(list.size())).points(list).visible(true).zIndex(50));
        m11694a((Object) list);
        return polyline;
    }

    /* renamed from: a */
    protected void mo3446a(double d, double d2) {
        this.f10882o.animateMapStatus(MapStatusUpdateFactory.newLatLng(C2558g.m12851g(d, d2)));
    }

    /* renamed from: c */
    public void mo3457c(List<LatLng> list) {
        if (list != null && list.size() >= 1 && this.f10882o != null) {
            Builder builder = new Builder();
            for (int i = 0; i < list.size(); i++) {
                builder.include((LatLng) list.get(i));
            }
            int width = this.f10881n.getWidth();
            this.f10882o.animateMapStatus(MapStatusUpdateFactory.newLatLngBounds(builder.build(), width, width), 1000);
        }
    }

    /* renamed from: b */
    public void mo3454b(List<LatLng> list, int i, int i2) {
        if (list != null && !list.isEmpty() && this.f10882o != null) {
            Builder builder = new Builder();
            for (int i3 = 0; i3 < list.size(); i3++) {
                builder.include((LatLng) list.get(i3));
            }
            MapStatusUpdate newLatLngBounds = MapStatusUpdateFactory.newLatLngBounds(builder.build(), i, i2);
            if (this.f10882o != null && newLatLngBounds != null) {
                this.f10882o.animateMapStatus(newLatLngBounds);
            }
        }
    }

    /* renamed from: a */
    protected void mo3448a(int i, int i2) {
        this.f10882o.animateMapStatus(MapStatusUpdateFactory.scrollBy(i, i2));
    }

    /* renamed from: a */
    public void mo3450a(final C1950e c1950e) {
        if (c1950e != null && this.f10882o != null) {
            this.f10882o.snapshot(new SnapshotReadyCallback(this) {
                /* renamed from: b */
                final /* synthetic */ C2297a f10864b;

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
        LayoutParams layoutParams = this.f10881n.getLayoutParams();
        layoutParams.width = dm.widthPixels;
        layoutParams.height = dm.heightPixels;
        this.f10881n.setLayoutParams(layoutParams);
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        LatLng latLng = new LatLng(bDLocation.getLatitude(), bDLocation.getLongitude());
        this.f10882o.setMyLocationData(new MyLocationData.Builder().accuracy(bDLocation.getRadius()).latitude(latLng.latitude).longitude(latLng.longitude).build());
        this.f10883p.stop();
        this.f10882o.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
        this.f10881n.postDelayed(new C22958(this), 1000);
        if (this.i != null) {
            com.mapbox.mapboxsdk.geometry.LatLng b = C2558g.m12845b(latLng);
            this.i.mo3198c(b.getLatitude(), b.getLongitude());
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    public void onMapStatusChangeStart(MapStatus mapStatus) {
        if (this.h != null) {
            this.h.mo3200a(mapStatus.target.latitude, mapStatus.target.longitude);
        }
    }

    public void onMapStatusChange(MapStatus mapStatus) {
    }

    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        if (this.h != null) {
            this.h.mo3204b(mapStatus.target.latitude, mapStatus.target.longitude);
            int mapLevel = this.f10881n.getMapLevel();
            String string = getResources().getString(C1373R.string.metre);
            if (mapLevel > 1000) {
                mapLevel /= 1000;
                string = getResources().getString(C1373R.string.kilometre);
            }
            this.h.mo3196a(String.valueOf(mapLevel), string);
        }
    }

    /* renamed from: i */
    protected void mo3464i() {
        if (this.f10882o != null) {
            this.f10882o.clear();
        }
    }
}
