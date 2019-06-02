package com.beastbikes.android.modules.shop.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData.Builder;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.ui.RouteMapSearchGeoActivity;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.p073a.C2327a;
import com.beastbikes.android.modules.shop.ui.p146b.C2340a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903077)
public class BikeShopMapFragment extends SessionFragment implements OnClickListener, BDLocationListener, OnMapClickListener, OnMapLoadedCallback, OnMapStatusChangeListener, OnMarkerClickListener, C1371a, C2340a {
    /* renamed from: A */
    private CoordinateConverter f6057A;
    /* renamed from: B */
    private int[] f6058B = new int[]{1, 20, 40, 60, 80, 100};
    /* renamed from: C */
    private int f6059C = this.f6058B[0];
    /* renamed from: D */
    private float f6060D = 16.0f;
    /* renamed from: E */
    private List<BikeShopListDTO> f6061E = new ArrayList();
    /* renamed from: F */
    private Marker f6062F;
    @C1458a(a = 2131755354)
    /* renamed from: a */
    private MapView f6063a;
    @C1458a(a = 2131755355)
    /* renamed from: b */
    private RelativeLayout f6064b;
    @C1458a(a = 2131755356)
    /* renamed from: c */
    private RelativeLayout f6065c;
    @C1458a(a = 2131755357)
    /* renamed from: d */
    private RelativeLayout f6066d;
    @C1458a(a = 2131755358)
    /* renamed from: e */
    private TextView f6067e;
    @C1458a(a = 2131755360)
    /* renamed from: f */
    private CardView f6068f;
    @C1458a(a = 2131755361)
    /* renamed from: g */
    private TextView f6069g;
    @C1458a(a = 2131755362)
    /* renamed from: h */
    private TextView f6070h;
    @C1458a(a = 2131755368)
    /* renamed from: i */
    private TextView f6071i;
    @C1458a(a = 2131755367)
    /* renamed from: j */
    private TextView f6072j;
    @C1458a(a = 2131755366)
    /* renamed from: k */
    private TextView f6073k;
    @C1458a(a = 2131755364)
    /* renamed from: l */
    private TextView f6074l;
    @C1458a(a = 2131755363)
    /* renamed from: m */
    private TextView f6075m;
    @C1458a(a = 2131755365)
    /* renamed from: n */
    private TextView f6076n;
    @C1458a(a = 2131755359)
    /* renamed from: o */
    private ImageView f6077o;
    /* renamed from: p */
    private final String f6078p = "speedx_shop_info";
    /* renamed from: q */
    private BaiduMap f6079q;
    /* renamed from: r */
    private LocationClient f6080r;
    /* renamed from: s */
    private float f6081s = 16.0f;
    /* renamed from: t */
    private double f6082t;
    /* renamed from: u */
    private double f6083u;
    /* renamed from: v */
    private double f6084v;
    /* renamed from: w */
    private double f6085w;
    /* renamed from: x */
    private C2327a f6086x;
    /* renamed from: y */
    private int f6087y;
    /* renamed from: z */
    private int f6088z;

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f6086x = new C2327a(getActivity());
        m7252e();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(C1848b.a().getClass().getName(), 0);
        this.f6082t = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
        this.f6083u = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
        this.f6084v = this.f6082t;
        this.f6085w = this.f6083u;
        this.f6064b.setOnClickListener(this);
        this.f6065c.setOnClickListener(this);
        this.f6068f.setOnClickListener(this);
        this.f6077o.setOnClickListener(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.f6087y = displayMetrics.widthPixels;
        this.f6088z = displayMetrics.heightPixels;
        if (this.f6082t != 0.0d && this.f6083u != 0.0d) {
            this.f6079q.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(new LatLng(this.f6082t, this.f6083u), 15.0f));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.f6063a.onDestroy();
        if (this.f6080r != null) {
            this.f6080r.stop();
            this.f6080r.unRegisterLocationListener(this);
        }
    }

    public void onResume() {
        super.onResume();
        this.f6063a.onResume();
    }

    public void onPause() {
        super.onPause();
        this.f6063a.onPause();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 80:
                    PoiInfoDTO poiInfoDTO = (PoiInfoDTO) intent.getSerializableExtra("poiinfo");
                    this.f6083u = poiInfoDTO.getLongitude();
                    this.f6082t = poiInfoDTO.getLatitude();
                    CoordinateConverter coordinateConverter = new CoordinateConverter();
                    coordinateConverter.from(CoordType.GPS);
                    coordinateConverter.coord(new LatLng(this.f6082t, this.f6083u));
                    this.f6079q.animateMapStatus(MapStatusUpdateFactory.newLatLng(coordinateConverter.convert()));
                    ((NearbyBikeShopActivity) getActivity()).m7266b();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m7254a(List<BikeShopListDTO> list) {
        m7251d();
    }

    /* renamed from: b */
    public void m7255b(List<BikeShopListDTO> list) {
        if (list != null && list.size() > 0) {
            this.f6061E.clear();
            this.f6061E.addAll(list);
            m7251d();
        }
    }

    /* renamed from: a */
    public SessionFragmentActivity m7253a() {
        return (SessionFragmentActivity) getActivity();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_bike_shop_search:
                RouteMapSearchGeoActivity.f5519a = false;
                startActivityForResult(new Intent(getActivity(), RouteMapSearchGeoActivity.class), 80);
                C2580w.a(getActivity(), "搜索车店", "search_bicycle");
                return;
            case C1373R.id.bike_shop_location_iv:
                m7256c();
                return;
            case C1373R.id.shop_baidu_map_search_result_info:
                C2580w.a(getActivity(), "查看车店详情", "open_bicycle_detail");
                if (this.f6068f.getTag() != null) {
                    long parseLong = Long.parseLong(this.f6068f.getTag().toString());
                    Intent intent = new Intent(getActivity(), BikeShopDetailActivity.class);
                    intent.putExtra("bike_shop_id", parseLong);
                    intent.putExtra("show_enter_club", true);
                    startActivity(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onMapLoaded() {
        ((NearbyBikeShopActivity) getActivity()).m7268c();
    }

    public void onMapStatusChangeStart(MapStatus mapStatus) {
    }

    public void onMapStatusChange(MapStatus mapStatus) {
    }

    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        this.f6060D = mapStatus.zoom;
    }

    public boolean onMarkerClick(Marker marker) {
        int i = 8;
        if (marker.getExtraInfo() != null) {
            BikeShopListDTO bikeShopListDTO = (BikeShopListDTO) marker.getExtraInfo().getSerializable("speedx_shop_info");
            if (bikeShopListDTO != null) {
                DecimalFormat decimalFormat;
                String str;
                double distance = bikeShopListDTO.getDistance() / 1000.0d;
                if (distance >= 10.0d) {
                    decimalFormat = new DecimalFormat("#");
                } else {
                    decimalFormat = new DecimalFormat("#.#");
                }
                if (C1849a.b(getActivity())) {
                    str = decimalFormat.format(distance) + getResources().getString(C1373R.string.str_mileage_unit_km);
                } else {
                    str = decimalFormat.format(C1849a.a(distance)) + getResources().getString(C1373R.string.str_mileage_unit_mile);
                }
                this.f6071i.setText(SimpleComparison.LESS_THAN_OPERATION + str);
                marker.setIcon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_speedx_shop_location));
                switch (bikeShopListDTO.getLevel()) {
                    case 0:
                        this.f6069g.setVisibility(8);
                        break;
                    case 1:
                        this.f6069g.setVisibility(0);
                        this.f6069g.setBackgroundResource(C1373R.drawable.bg_bike_shop_service_providers);
                        this.f6069g.setText(C1373R.string.str_bicycle_shop_service_level);
                        this.f6069g.setTextColor(Color.parseColor("#713500"));
                        break;
                    case 2:
                        this.f6069g.setVisibility(0);
                        this.f6069g.setBackgroundResource(C1373R.drawable.bg_bike_shop_dealers);
                        this.f6069g.setText(C1373R.string.str_bicycle_shop_dealer_level);
                        this.f6069g.setTextColor(Color.parseColor("#ffffff"));
                        break;
                }
                this.f6070h.setText(bikeShopListDTO.getName());
                this.f6068f.setTag(Long.valueOf(bikeShopListDTO.getShopId()));
                this.f6072j.setText(getString(C1373R.string.bike_shop_detail_address) + bikeShopListDTO.getAllAddress());
                this.f6066d.setVisibility(8);
                this.f6068f.setVisibility(0);
                if (bikeShopListDTO.getTagInfo() != null) {
                    int i2;
                    this.f6073k.setVisibility(bikeShopListDTO.getTagInfo().isRent() ? 0 : 8);
                    TextView textView = this.f6074l;
                    if (bikeShopListDTO.getTagInfo().isCare()) {
                        i2 = 0;
                    } else {
                        i2 = 8;
                    }
                    textView.setVisibility(i2);
                    textView = this.f6075m;
                    if (bikeShopListDTO.getTagInfo().isFix()) {
                        i2 = 0;
                    } else {
                        i2 = 8;
                    }
                    textView.setVisibility(i2);
                    TextView textView2 = this.f6076n;
                    if (bikeShopListDTO.getTagInfo().isOfficeExperience()) {
                        i = 0;
                    }
                    textView2.setVisibility(i);
                }
                if (this.f6062F != null) {
                    this.f6062F.setIcon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_speedx_shop_vip_icon));
                }
                this.f6062F = marker;
            }
        }
        return false;
    }

    public void onMapClick(LatLng latLng) {
        m7251d();
    }

    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    /* renamed from: d */
    private void m7251d() {
        this.f6079q.clear();
        if (this.f6061E != null && this.f6061E.size() != 0) {
            BikeShopListDTO bikeShopListDTO;
            for (int i = 0; i < this.f6061E.size(); i++) {
                bikeShopListDTO = (BikeShopListDTO) this.f6061E.get(i);
                this.f6057A.coord(new LatLng(bikeShopListDTO.getLatitude(), bikeShopListDTO.getLongitude()));
                LatLng convert = this.f6057A.convert();
                m7249a(bikeShopListDTO.getName(), convert);
                BitmapDescriptor fromResource = BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_speedx_shop_vip_icon);
                if (bikeShopListDTO.getLevel() == 0) {
                    fromResource = BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_speedx_shop_icon);
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("speedx_shop_info", bikeShopListDTO);
                this.f6079q.addOverlay(new MarkerOptions().position(convert).icon(fromResource).extraInfo(bundle));
            }
            List<BikeShopListDTO> d = ((NearbyBikeShopActivity) getActivity()).m7269d();
            if (d != null && d.size() > 0) {
                List arrayList = new ArrayList();
                for (BikeShopListDTO bikeShopListDTO2 : d) {
                    this.f6057A.coord(new LatLng(bikeShopListDTO2.getLatitude(), bikeShopListDTO2.getLongitude()));
                    arrayList.add(this.f6057A.convert());
                }
                m7250c(arrayList);
            }
            this.f6067e.setText(getResources().getString(C1373R.string.shop_search_result1) + this.f6061E.size() + getResources().getString(C1373R.string.shop_search_result2));
        }
    }

    /* renamed from: a */
    private void m7249a(String str, LatLng latLng) {
        if (this.f6079q != null && this.f6060D >= 18.0f) {
            this.f6079q.addOverlay(new TextOptions().fontSize(30).fontColor(ViewCompat.MEASURED_STATE_MASK).text(str).position(latLng));
        }
    }

    /* renamed from: e */
    private void m7252e() {
        this.f6057A = new CoordinateConverter();
        this.f6057A.from(CoordType.COMMON);
        this.f6063a.getChildAt(1).setVisibility(8);
        this.f6079q = this.f6063a.getMap();
        this.f6079q.getUiSettings().setOverlookingGesturesEnabled(false);
        this.f6063a.showZoomControls(false);
        this.f6063a.showScaleControl(false);
        this.f6079q.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, null));
        this.f6079q.setMyLocationEnabled(true);
        this.f6079q.animateMapStatus(MapStatusUpdateFactory.zoomTo(this.f6081s));
        this.f6079q.setOnMapLoadedCallback(this);
        this.f6079q.setOnMarkerClickListener(this);
        this.f6079q.setOnMapClickListener(this);
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if (bDLocation != null) {
            this.f6079q.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.FOLLOWING, true, BitmapDescriptorFactory.fromResource(C1373R.drawable.route_map_make_location)));
            this.f6079q.setMyLocationData(new Builder().accuracy(bDLocation.getRadius()).latitude(bDLocation.getLatitude()).longitude(bDLocation.getLongitude()).build());
            this.f6080r.stop();
            this.f6079q.animateMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(bDLocation.getLatitude(), bDLocation.getLongitude())));
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    /* renamed from: c */
    public void m7256c() {
        if (this.f6080r == null) {
            this.f6080r = new LocationClient(getActivity());
            this.f6080r.registerLocationListener(this);
        }
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setPriority(1);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(5000);
        locationClientOption.setAddrType("all");
        this.f6080r.setLocOption(locationClientOption);
        if (!this.f6080r.isStarted()) {
            this.f6080r.start();
        }
        this.f6080r.requestLocation();
        this.f6079q.setMyLocationEnabled(true);
        this.f6079q.setOnMapStatusChangeListener(this);
    }

    /* renamed from: c */
    private void m7250c(List<LatLng> list) {
        if (list != null && !list.isEmpty() && this.f6079q != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (int i = 0; i < list.size(); i++) {
                builder.include((LatLng) list.get(i));
            }
            int width = this.f6063a.getWidth();
            this.f6079q.animateMapStatus(MapStatusUpdateFactory.newLatLngBounds(builder.build(), (width * 2) / 3, this.f6063a.getHeight()), 1000);
        }
    }
}
