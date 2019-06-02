package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.cons.C0844a;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteLine.BikingStep;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.p111a.C1892g;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteNodeDTO;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.cycling.route.ui.widget.C2216a;
import com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView;
import com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView.C2192h;
import com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView.C2193m;
import com.beastbikes.android.modules.user.ui.C2496a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903662)
public class RoutePlanActivity extends SessionFragmentActivity implements OnClickListener, BDLocationListener, OnMapStatusChangeListener, OnGetGeoCoderResultListener, OnGetRoutePlanResultListener {
    /* renamed from: c */
    private static final Logger f5540c = LoggerFactory.getLogger(RoutePlanActivity.class);
    /* renamed from: A */
    private GeoCoder f5541A;
    /* renamed from: B */
    private List<PoiInfoDTO> f5542B = new ArrayList();
    /* renamed from: C */
    private List<PoiInfoDTO> f5543C = new ArrayList();
    /* renamed from: D */
    private RoutePlanActivity$a f5544D;
    /* renamed from: E */
    private PoiInfoDTO f5545E;
    /* renamed from: F */
    private boolean f5546F;
    /* renamed from: G */
    private C2185a f5547G;
    /* renamed from: H */
    private double f5548H = 0.0d;
    /* renamed from: I */
    private int f5549I = 0;
    /* renamed from: J */
    private boolean f5550J;
    /* renamed from: K */
    private boolean f5551K;
    /* renamed from: L */
    private boolean f5552L = false;
    /* renamed from: M */
    private LatLng f5553M;
    /* renamed from: N */
    private TranslateAnimation f5554N;
    /* renamed from: O */
    private AlphaAnimation f5555O;
    /* renamed from: P */
    private String f5556P;
    /* renamed from: Q */
    private C2496a f5557Q;
    /* renamed from: R */
    private boolean f5558R;
    /* renamed from: S */
    private String f5559S;
    /* renamed from: T */
    private SharedPreferences f5560T;
    /* renamed from: U */
    private List<List<LatLng>> f5561U = new ArrayList();
    /* renamed from: V */
    private C2192h f5562V = new RoutePlanActivity$1(this);
    /* renamed from: W */
    private C2193m f5563W = new RoutePlanActivity$3(this);
    /* renamed from: a */
    List<RouteNodeDTO> f5564a = new ArrayList();
    /* renamed from: b */
    String f5565b = null;
    @C1458a(a = 2131757510)
    /* renamed from: d */
    private MapView f5566d;
    @C1458a(a = 2131757511)
    /* renamed from: e */
    private ImageView f5567e;
    @C1458a(a = 2131757520)
    /* renamed from: f */
    private TextView f5568f;
    @C1458a(a = 2131757512)
    /* renamed from: g */
    private ViewGroup f5569g;
    @C1458a(a = 2131755574)
    /* renamed from: h */
    private ViewGroup f5570h;
    @C1458a(a = 2131757513)
    /* renamed from: i */
    private ImageView f5571i;
    @C1458a(a = 2131757514)
    /* renamed from: j */
    private ImageView f5572j;
    @C1458a(a = 2131757515)
    /* renamed from: k */
    private ImageView f5573k;
    @C1458a(a = 2131757522)
    /* renamed from: l */
    private DragSortListView f5574l;
    @C1458a(a = 2131757516)
    /* renamed from: m */
    private ViewGroup f5575m;
    @C1458a(a = 2131757517)
    /* renamed from: n */
    private TextView f5576n;
    @C1458a(a = 2131757518)
    /* renamed from: o */
    private TextView f5577o;
    @C1458a(a = 2131757144)
    /* renamed from: p */
    private ViewGroup f5578p;
    @C1458a(a = 2131757145)
    /* renamed from: q */
    private Button f5579q;
    @C1458a(a = 2131757523)
    /* renamed from: r */
    private TextView f5580r;
    @C1458a(a = 2131757521)
    /* renamed from: s */
    private TextView f5581s;
    @C1458a(a = 2131757519)
    /* renamed from: t */
    private ImageView f5582t;
    @C1458a(a = 2131757524)
    /* renamed from: u */
    private RelativeLayout f5583u;
    /* renamed from: v */
    private LocationClient f5584v;
    /* renamed from: w */
    private BaiduMap f5585w;
    /* renamed from: x */
    private boolean f5586x = true;
    /* renamed from: y */
    private float f5587y = 16.0f;
    /* renamed from: z */
    private RoutePlanSearch f5588z;

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RoutePlanActivity$b */
    private final class C1421b extends ViewHolder<PoiInfoDTO> {
        /* renamed from: a */
        final /* synthetic */ RoutePlanActivity f5533a;
        @C1458a(a = 2131757530)
        /* renamed from: b */
        private TextView f5534b;
        @C1458a(a = 2131757528)
        /* renamed from: c */
        private TextView f5535c;
        @C1458a(a = 2131757527)
        /* renamed from: d */
        private ImageView f5536d;
        @C1458a(a = 2131757525)
        /* renamed from: e */
        private ImageView f5537e;
        @C1458a(a = 2131757529)
        /* renamed from: f */
        private ImageView f5538f;
        /* renamed from: g */
        private List<PoiInfoDTO> f5539g;

        public /* synthetic */ void bind(Object obj) {
            m6827a((PoiInfoDTO) obj);
        }

        protected C1421b(RoutePlanActivity routePlanActivity, View view, List<PoiInfoDTO> list) {
            this.f5533a = routePlanActivity;
            super(view);
            this.f5539g = list;
        }

        /* renamed from: a */
        public void m6827a(PoiInfoDTO poiInfoDTO) {
            int i = 0;
            if (poiInfoDTO != null) {
                this.f5537e.setVisibility(this.f5533a.f5552L ? 0 : 8);
                ImageView imageView = this.f5538f;
                if (!this.f5533a.f5552L) {
                    i = 8;
                }
                imageView.setVisibility(i);
                int index = poiInfoDTO.getIndex();
                i = this.f5539g.size();
                if (index == 0) {
                    this.f5536d.setImageResource(C1373R.drawable.route_map_make_list_start_icon);
                    this.f5535c.setTextColor(this.f5533a.getResources().getColor(17170445));
                } else if (index != i - 1 || i <= 1) {
                    this.f5536d.setImageResource(C1373R.drawable.route_map_make_list_pass_icon);
                    this.f5535c.setTextColor(this.f5533a.getResources().getColor(17170443));
                } else {
                    this.f5536d.setImageResource(C1373R.drawable.route_map_make_list_end_icon);
                    this.f5535c.setTextColor(this.f5533a.getResources().getColor(17170445));
                }
                this.f5535c.setText(String.valueOf(index));
                this.f5534b.setText(poiInfoDTO.getName());
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f5567e.setOnClickListener(this);
        this.f5569g.setOnClickListener(this);
        this.f5579q.setOnClickListener(this);
        this.f5580r.setOnClickListener(this);
        this.f5547G = new C2185a(this);
        this.f5560T = getSharedPreferences(getPackageName(), 0);
        this.f5584v = new LocationClient(this);
        this.f5584v.registerLocationListener(this);
        this.f5566d.showZoomControls(false);
        if (this.f5566d.getChildAt(1) != null) {
            this.f5566d.getChildAt(1).setVisibility(8);
        }
        if (this.f5566d.getChildAt(3) != null) {
            this.f5566d.getChildAt(3).setVisibility(8);
        }
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setPriority(1);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setAddrType("all");
        this.f5585w = this.f5566d.getMap();
        this.f5585w.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.FOLLOWING, true, BitmapDescriptorFactory.fromResource(C1373R.drawable.route_map_make_location)));
        this.f5585w.setMyLocationEnabled(true);
        this.f5585w.animateMapStatus(MapStatusUpdateFactory.zoomTo(this.f5587y));
        this.f5585w.setOnMapStatusChangeListener(this);
        this.f5585w.getUiSettings().setCompassEnabled(false);
        this.f5571i.setOnClickListener(this);
        this.f5573k.setOnClickListener(this);
        this.f5572j.setOnClickListener(this);
        this.f5581s.setOnClickListener(this);
        this.f5582t.setOnClickListener(this);
        this.f5584v.setLocOption(locationClientOption);
        this.f5588z = RoutePlanSearch.newInstance();
        this.f5588z.setOnGetRoutePlanResultListener(this);
        this.f5541A = GeoCoder.newInstance();
        this.f5541A.setOnGetGeoCodeResultListener(this);
        this.f5544D = new RoutePlanActivity$a(this, this.f5543C);
        this.f5557Q = new C2496a(this);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(C1373R.layout.route_map_make_list_footer, null).findViewById(C1373R.id.route_map_make_add_point);
        viewGroup.setOnClickListener(this);
        this.f5574l.addFooterView(viewGroup, null, false);
        this.f5574l.setAdapter(this.f5544D);
        Object c2216a = new C2216a(this.f5574l);
        c2216a.c(C1373R.id.route_map_make_item_drag);
        c2216a.d(C1373R.id.route_map_make_item_delete);
        c2216a.b(true);
        c2216a.a(true);
        c2216a.a(0);
        c2216a.b(0);
        this.f5574l.setFloatViewManager(c2216a);
        this.f5574l.setOnTouchListener(c2216a);
        this.f5574l.setDragEnabled(true);
        this.f5574l.setRemoveListener(this.f5563W);
        this.f5574l.setDropListener(this.f5562V);
        this.f5555O = new AlphaAnimation(0.0f, 1.0f);
        this.f5555O.setDuration(500);
        this.f5554N = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -0.5f);
        this.f5554N.setRepeatCount(1);
        this.f5554N.setDuration(300);
        this.f5554N.setRepeatMode(2);
        this.f5554N.setAnimationListener(new RoutePlanActivity$4(this));
        Intent intent = getIntent();
        if (intent != null) {
            this.f5559S = intent.getStringExtra("route_id");
            if (!intent.getBooleanExtra("show_list", true)) {
                this.f5570h.setVisibility(8);
                this.f5569g.setVisibility(8);
                this.f5578p.setVisibility(8);
                this.f5585w.setMyLocationEnabled(false);
            }
            if (TextUtils.isEmpty(this.f5559S)) {
                this.f5584v.start();
                this.f5584v.requestLocation();
                return;
            }
            m6835a(this.f5559S);
        }
    }

    protected void onResume() {
        this.f5566d.onResume();
        super.onResume();
    }

    protected void onPause() {
        this.f5566d.onPause();
        super.onPause();
    }

    protected void onDestroy() {
        if (this.f5584v.isStarted()) {
            this.f5584v.stop();
        }
        if (this.f5557Q != null) {
            this.f5557Q = null;
        }
        this.f5585w.clear();
        this.f5588z.destroy();
        this.f5585w.setMyLocationEnabled(false);
        this.f5584v.unRegisterLocationListener(this);
        System.gc();
        super.onDestroy();
    }

    public void finish() {
        super.finish();
    }

    public void onClick(View view) {
        float min;
        switch (view.getId()) {
            case C1373R.id.route_map_make_select_start_point:
                LatLng latLng = this.f5585w.getMapStatus().target;
                this.f5553M = latLng;
                if (this.f5546F) {
                    this.f5541A.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
                    return;
                } else if (this.f5545E != null) {
                    this.f5545E.setLatitude(this.f5553M.latitude);
                    this.f5545E.setLongitude(this.f5553M.longitude);
                    this.f5543C.add(this.f5545E);
                    this.f5544D.notifyDataSetChanged();
                    return;
                } else {
                    return;
                }
            case C1373R.id.route_map_make_back:
                m6839b();
                return;
            case C1373R.id.route_map_make_search:
                RouteMapSearchGeoActivity.f5519a = true;
                startActivityForResult(new Intent(this, RouteMapSearchGeoActivity.class), 80);
                return;
            case C1373R.id.route_map_make_activity_map_button_location:
                this.f5586x = true;
                if (!this.f5584v.isStarted()) {
                    this.f5584v.start();
                }
                this.f5584v.requestLocation();
                this.f5585w.setMyLocationEnabled(true);
                return;
            case C1373R.id.route_map_make_activity_map_button_zoom_out:
                min = Math.min(this.f5587y + 1.0f, this.f5585w.getMaxZoomLevel());
                this.f5585w.animateMapStatus(MapStatusUpdateFactory.zoomTo(min));
                this.f5587y = min;
                return;
            case C1373R.id.route_map_make_activity_map_button_zoom_in:
                min = Math.max(this.f5587y - 1.0f, this.f5585w.getMinZoomLevel());
                this.f5585w.animateMapStatus(MapStatusUpdateFactory.zoomTo(min));
                this.f5587y = min;
                return;
            case C1373R.id.route_map_make_activity_elevation:
                C2580w.a(this, "查看路线制作海拔趋势图", null);
                StringBuilder stringBuilder = new StringBuilder();
                if (!this.f5543C.isEmpty()) {
                    Iterator it = this.f5543C.iterator();
                    while (it.hasNext()) {
                        PoiInfoDTO poiInfoDTO = (PoiInfoDTO) it.next();
                        stringBuilder.append(poiInfoDTO.getLatitude()).append(",").append(poiInfoDTO.getLongitude());
                        if (it.hasNext()) {
                            stringBuilder.append('|');
                        }
                    }
                }
                Intent intent = new Intent(this, RouteElevationActivity.class);
                intent.putExtra("nodes", stringBuilder.toString());
                intent.putExtra("distance", this.f5548H / 1000.0d);
                startActivity(intent);
                return;
            case C1373R.id.route_map_make_point_list_edit:
                boolean z;
                if (this.f5552L) {
                    z = false;
                } else {
                    z = true;
                }
                this.f5552L = z;
                if (this.f5544D.getCount() > 0) {
                    ((PoiInfoDTO) this.f5544D.getItem(0)).setEdit(true);
                }
                this.f5544D.notifyDataSetChanged();
                return;
            case C1373R.id.route_map_make_save_points:
                this.f5550J = true;
                this.f5580r.setClickable(false);
                m6833a();
                C2580w.a(this, getString(C1373R.string.route_map_activity_event_save_route), "click_my_page_my_road_book_save");
                return;
            case C1373R.id.route_map_make_add_point:
                startActivityForResult(new Intent(this, RouteMapSearchGeoActivity.class), 80);
                return;
            default:
                return;
        }
    }

    public void onMapStatusChange(MapStatus mapStatus) {
    }

    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        runOnUiThread(new RoutePlanActivity$5(this));
    }

    public void onMapStatusChangeStart(MapStatus mapStatus) {
        this.f5546F = true;
        runOnUiThread(new RoutePlanActivity$6(this));
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if (bDLocation != null) {
            runOnUiThread(new RoutePlanActivity$7(this, bDLocation));
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }

    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        LatLng latLng = this.f5585w.getMapStatus().target;
        if (latLng == null) {
            Toasts.show(this, C1373R.string.route_map_make_activity_select_err);
            return;
        }
        this.f5553M = latLng;
        String str = "";
        this.f5545E = new PoiInfoDTO();
        if (reverseGeoCodeResult != null && reverseGeoCodeResult.error == ERRORNO.NO_ERROR) {
            String str2 = reverseGeoCodeResult.getAddressDetail().city;
            List poiList = reverseGeoCodeResult.getPoiList();
            if (!(poiList == null || poiList.isEmpty())) {
                PoiInfo poiInfo = (PoiInfo) reverseGeoCodeResult.getPoiList().get(0);
                if (poiInfo != null) {
                    this.f5545E.setName(poiInfo.name);
                    this.f5545E.setAddress(poiInfo.address);
                }
            }
            str = str2;
        }
        if (TextUtils.isEmpty(this.f5545E.getName())) {
            str = String.valueOf(this.f5553M.latitude) + ", " + String.valueOf(this.f5553M.longitude);
            this.f5545E.setName(str);
        }
        this.f5545E.setLatitude(this.f5553M.latitude);
        this.f5545E.setLongitude(this.f5553M.longitude);
        this.f5545E.setCity(str);
        this.f5543C.add(this.f5545E);
        this.f5544D.notifyDataSetChanged();
        this.f5545E = null;
    }

    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
    }

    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
    }

    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
    }

    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
    }

    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
        if (bikingRouteResult != null) {
            if (bikingRouteResult.error != ERRORNO.NO_ERROR) {
                Toasts.show(this, C1373R.string.route_map_make_activity_plain_err_msg);
                if (this.f5557Q != null) {
                    this.f5557Q.dismiss();
                }
                this.f5543C.remove(this.f5543C.size() - 1);
                this.f5544D.notifyDataSetChanged();
            }
            if (bikingRouteResult.error == ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                Toasts.show(this, C1373R.string.route_map_make_activity_plain_err_msg);
                if (this.f5557Q != null) {
                    this.f5557Q.dismiss();
                }
                this.f5543C.remove(this.f5543C.size() - 1);
                this.f5544D.notifyDataSetChanged();
            }
            if (bikingRouteResult.error == ERRORNO.NO_ERROR && bikingRouteResult.getRouteLines() != null && bikingRouteResult.getRouteLines().get(0) != null) {
                this.f5548H = ((double) ((BikingRouteLine) bikingRouteResult.getRouteLines().get(0)).getDistance()) + this.f5548H;
                List arrayList = new ArrayList();
                for (BikingStep wayPoints : ((BikingRouteLine) bikingRouteResult.getRouteLines().get(0)).getAllStep()) {
                    arrayList.addAll(wayPoints.getWayPoints());
                }
                this.f5561U.add(this.f5549I, arrayList);
                m6833a();
                this.f5549I++;
                if (this.f5549I >= this.f5543C.size() || this.f5549I <= 0 || !this.f5551K) {
                    this.f5551K = false;
                    if (this.f5557Q != null) {
                        this.f5557Q.dismiss();
                    }
                } else if (this.f5549I + 1 < this.f5543C.size()) {
                    PoiInfoDTO poiInfoDTO = (PoiInfoDTO) this.f5543C.get(this.f5549I);
                    PlanNode withLocation = PlanNode.withLocation(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()));
                    poiInfoDTO = (PoiInfoDTO) this.f5543C.get(this.f5549I + 1);
                    PlanNode withLocation2 = PlanNode.withLocation(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()));
                    if (this.f5588z != null) {
                        this.f5588z.bikingSearch(new BikingRoutePlanOption().from(withLocation).to(withLocation2));
                    }
                } else if (this.f5557Q != null) {
                    this.f5557Q.dismiss();
                }
            }
        }
    }

    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 80:
                    this.f5545E = (PoiInfoDTO) intent.getSerializableExtra("poiinfo");
                    this.f5585w.animateMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(this.f5545E.getLatitude(), this.f5545E.getLongitude())));
                    this.f5546F = false;
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m6835a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new RoutePlanActivity$8(this), new String[]{str});
        }
    }

    /* renamed from: a */
    private void m6836a(List<PoiInfoDTO> list) {
        if (list == null || list.isEmpty() || isFinishing()) {
            this.f5585w.clear();
        } else if (list.size() == 1) {
            this.f5585w.clear();
            LatLng latLng = new LatLng(((PoiInfoDTO) list.get(0)).getLatitude(), ((PoiInfoDTO) list.get(0)).getLongitude());
            TextView textView = (TextView) LayoutInflater.from(this).inflate(C1373R.layout.route_map_make_plan_ordinal, null);
            textView.setText(C0844a.f2048d);
            textView.setBackgroundResource(C1373R.drawable.route_map_line_start_icon);
            textView.setTextColor(getResources().getColor(17170445));
            this.f5585w.addOverlay(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromView(textView)));
        } else if (this.f5549I + 1 < list.size()) {
            if (this.f5557Q != null) {
                this.f5557Q.setCancelable(true);
                this.f5557Q.a(C1373R.string.route_map_make_activity_plain);
                this.f5557Q.show();
            }
            PoiInfoDTO poiInfoDTO = (PoiInfoDTO) list.get(this.f5549I);
            PlanNode withLocation = PlanNode.withLocation(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()));
            poiInfoDTO = (PoiInfoDTO) list.get(this.f5549I + 1);
            PlanNode withLocation2 = PlanNode.withLocation(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()));
            if (this.f5588z != null) {
                this.f5588z.bikingSearch(new BikingRoutePlanOption().from(withLocation).to(withLocation2));
            }
        }
    }

    /* renamed from: a */
    private void m6833a() {
        if (this.f5543C != null && this.f5543C.size() >= 2) {
            this.f5585w.clear();
            this.f5575m.setVisibility(0);
            double d = this.f5548H / 1000.0d;
            CharSequence charSequence = "km";
            if (!C1849a.b(this)) {
                d = C1849a.a(d);
                charSequence = "mi";
            }
            this.f5576n.setText(String.format("%.0f", new Object[]{Double.valueOf(d)}));
            this.f5577o.setText(charSequence);
            for (int i = 0; i < this.f5543C.size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(this).inflate(C1373R.layout.route_map_make_plan_ordinal, null);
                textView.setText(String.valueOf(i));
                if (i == 0) {
                    textView.setBackgroundResource(C1373R.drawable.route_map_line_start_icon);
                    textView.setTextColor(getResources().getColor(17170445));
                } else if (i == this.f5543C.size() - 1) {
                    textView.setBackgroundResource(C1373R.drawable.route_map_line_end_icon);
                    textView.setTextColor(getResources().getColor(17170445));
                } else if (this.f5550J || this.f5586x) {
                    textView.setTextColor(getResources().getColor(17170445));
                } else {
                    textView.setBackgroundResource(C1373R.drawable.route_map_make_center_icon);
                    textView.setTextColor(getResources().getColor(17170443));
                }
                this.f5585w.addOverlay(new MarkerOptions().position(new LatLng(((PoiInfoDTO) this.f5543C.get(i)).getLatitude(), ((PoiInfoDTO) this.f5543C.get(i)).getLongitude())).icon(BitmapDescriptorFactory.fromView(textView)));
            }
            for (List list : this.f5561U) {
                if (list != null) {
                    f5540c.trace("Rotue line source value size = " + list.size());
                    List list2 = list;
                    float f = 1.0E-4f;
                    while (list2.size() >= 1000) {
                        try {
                            list2 = C1892g.a(f, list2);
                            f += 3.0E-4f;
                        } catch (BusinessException e) {
                            e.printStackTrace();
                        }
                    }
                    f5540c.trace("Rotue line new value size = " + list2.size());
                    this.f5585w.addOverlay(new PolylineOptions().width(8).color(-10583834).points(list2));
                }
            }
            if (this.f5586x) {
                m6841b(this.f5543C);
            }
            if (this.f5550J) {
                this.f5583u.setVisibility(0);
                m6841b(this.f5543C);
                m6844c(this.f5543C);
            }
        }
    }

    /* renamed from: b */
    private void m6841b(List<PoiInfoDTO> list) {
        if (list != null && !list.isEmpty()) {
            Builder builder = new Builder();
            for (PoiInfoDTO poiInfoDTO : list) {
                builder.include(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()));
            }
            int i = (getResources().getDisplayMetrics().heightPixels * 7) / 10;
            this.f5585w.setMapStatus(MapStatusUpdateFactory.newLatLngBounds(builder.build(), i, (((getResources().getDisplayMetrics().widthPixels * 2) / 3) * 6) / 10));
        }
    }

    /* renamed from: c */
    private void m6844c(List<PoiInfoDTO> list) {
        if (list != null && list.size() >= 1) {
            this.f5585w.setMyLocationEnabled(false);
            double d = this.f5548H;
            RouteDTO routeDTO = new RouteDTO();
            PoiInfoDTO poiInfoDTO = (PoiInfoDTO) list.get(0);
            PoiInfoDTO poiInfoDTO2 = (PoiInfoDTO) list.get(list.size() - 1);
            routeDTO.setName(poiInfoDTO.getName() + HelpFormatter.DEFAULT_OPT_PREFIX + poiInfoDTO2.getName());
            routeDTO.setTotalDistance(d);
            routeDTO.setOriginAltitude(0.0d);
            routeDTO.setOriginLatitude(poiInfoDTO.getLatitude());
            routeDTO.setOriginLongitude(poiInfoDTO.getLongitude());
            routeDTO.setDestinationAltitude(0.0d);
            routeDTO.setDestinationLatitude(poiInfoDTO2.getLatitude());
            routeDTO.setDestinationLongitude(poiInfoDTO2.getLongitude());
            routeDTO.setUserId(m5331p());
            HandlerThread handlerThread = new HandlerThread("Upload Route");
            handlerThread.start();
            new Handler(handlerThread.getLooper()).postDelayed(new RoutePlanActivity$9(this, routeDTO, list), 800);
        }
    }

    /* renamed from: b */
    private void m6839b() {
        if (!this.f5558R) {
            finish();
        }
        if (this.f5544D == null || 2 > this.f5544D.getCount()) {
            finish();
            return;
        }
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.route_map_activity_exit_dialog_msg);
        c2621c.a(C1373R.string.route_map_activity_exit_dialog_ok, new RoutePlanActivity$2(this, c2621c)).b(C1373R.string.route_map_activity_exit_dialog_cancel, new RoutePlanActivity$10(this, c2621c)).a();
    }

    /* renamed from: d */
    private void m6846d(List<PoiInfoDTO> list) {
        boolean z = true;
        if (list != null) {
            boolean z2;
            this.f5558R = false;
            if (this.f5558R) {
                z2 = false;
            } else {
                z2 = true;
            }
            this.f5586x = z2;
            if (list.size() != this.f5542B.size()) {
                this.f5558R = true;
                if (this.f5558R) {
                    z = false;
                }
                this.f5586x = z;
                return;
            }
            for (int i = 0; i < this.f5542B.size(); i++) {
                if (((PoiInfoDTO) this.f5542B.get(i)).hashCode() != ((PoiInfoDTO) list.get(i)).hashCode()) {
                    this.f5558R = true;
                    if (this.f5558R) {
                        z = false;
                    }
                    this.f5586x = z;
                    return;
                }
            }
        }
    }

    public void onBackPressed() {
        m6839b();
    }
}
