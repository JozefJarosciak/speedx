package com.beastbikes.android.modules.cycling.activity.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.sdk.util.C0880h;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
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
import com.beastbikes.android.locale.googlemaputils.C1857d;
import com.beastbikes.android.locale.googlemaputils.GoogleMapManager;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.p115b.C1903a;
import com.beastbikes.android.modules.cycling.activity.view.C2046a;
import com.beastbikes.android.modules.cycling.p111a.C1892g;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.modules.cycling.stage.dto.StagePointDTO;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p056e.C2794a;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap$CancelableCallback;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "骑行页地图页")
@C1459b(a = 2130903135)
public class MapFragment extends SessionFragment implements OnSharedPreferenceChangeListener, SensorEventListener, OnClickListener, BDLocationListener, OnGetRoutePlanResultListener, C1857d, C2046a, LocationListener, GoogleMap$CancelableCallback, OnMapReadyCallback {
    /* renamed from: a */
    private static final Logger f4684a = LoggerFactory.getLogger(MapFragment.class);
    /* renamed from: A */
    private boolean f4685A;
    /* renamed from: B */
    private int f4686B;
    /* renamed from: C */
    private String f4687C;
    /* renamed from: D */
    private List<List<LatLng>> f4688D = new ArrayList();
    /* renamed from: E */
    private GoogleMap f4689E;
    /* renamed from: F */
    private com.google.android.gms.maps.MapFragment f4690F;
    /* renamed from: G */
    private boolean f4691G = false;
    /* renamed from: H */
    private LocationRequest f4692H;
    /* renamed from: I */
    private GoogleMapManager f4693I;
    /* renamed from: J */
    private com.google.android.gms.maps.model.LatLng f4694J;
    /* renamed from: K */
    private boolean f4695K = true;
    /* renamed from: L */
    private LocationManager f4696L;
    /* renamed from: M */
    private MapFragment$a f4697M;
    /* renamed from: N */
    private MapFragment$b f4698N;
    /* renamed from: O */
    private boolean f4699O = false;
    /* renamed from: P */
    private C1903a f4700P;
    /* renamed from: Q */
    private StagePointDTO f4701Q;
    /* renamed from: R */
    private StagePointDTO f4702R;
    /* renamed from: S */
    private String f4703S;
    /* renamed from: T */
    private SharedPreferences f4704T;
    /* renamed from: U */
    private List<GpsSatellite> f4705U = new ArrayList();
    /* renamed from: b */
    private MapView f4706b;
    @C1458a(a = 2131755751)
    /* renamed from: c */
    private ImageView f4707c;
    @C1458a(a = 2131755753)
    /* renamed from: d */
    private ImageView f4708d;
    @C1458a(a = 2131755754)
    /* renamed from: e */
    private ImageView f4709e;
    @C1458a(a = 2131755755)
    /* renamed from: f */
    private ImageView f4710f;
    @C1458a(a = 2131755752)
    /* renamed from: g */
    private ImageView f4711g;
    @C1458a(a = 2131755748)
    /* renamed from: h */
    private RelativeLayout f4712h;
    @C1458a(a = 2131755750)
    /* renamed from: i */
    private RatingBar f4713i;
    @C1458a(a = 2131755758)
    /* renamed from: j */
    private CardView f4714j;
    @C1458a(a = 2131755759)
    /* renamed from: k */
    private TextView f4715k;
    /* renamed from: l */
    private final float[] f4716l = new float[3];
    /* renamed from: m */
    private final float[] f4717m = new float[3];
    /* renamed from: n */
    private float f4718n = 0.0f;
    /* renamed from: o */
    private float f4719o = 16.0f;
    /* renamed from: p */
    private boolean f4720p = true;
    /* renamed from: q */
    private BaiduMap f4721q;
    /* renamed from: r */
    private LocationClient f4722r;
    /* renamed from: s */
    private C1398a f4723s;
    /* renamed from: t */
    private Sensor f4724t;
    /* renamed from: u */
    private Sensor f4725u;
    /* renamed from: v */
    private SensorManager f4726v;
    /* renamed from: w */
    private SharedPreferences f4727w;
    /* renamed from: x */
    private C2185a f4728x;
    /* renamed from: y */
    private RoutePlanSearch f4729y;
    /* renamed from: z */
    private List<PoiInfoDTO> f4730z = new ArrayList();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f4699O = C1849a.a();
        this.f4723s = new C1398a(activity);
        this.f4726v = (SensorManager) activity.getSystemService("sensor");
        this.f4724t = this.f4726v.getDefaultSensor(1);
        this.f4725u = this.f4726v.getDefaultSensor(2);
        this.f4726v.registerListener(this, this.f4724t, 2);
        this.f4726v.registerListener(this, this.f4725u, 2);
        if (this.f4699O) {
            this.f4722r = new LocationClient(activity);
            this.f4722r.registerLocationListener(this);
        }
        this.f4698N = (MapActivity) activity;
    }

    public void onDetach() {
        this.f4726v.unregisterListener(this, this.f4724t);
        this.f4726v.unregisterListener(this, this.f4725u);
        if (this.f4699O) {
            this.f4722r.unRegisterLocationListener(this);
        }
        super.onDetach();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f4699O = C1849a.a();
        this.f4727w = getActivity().getSharedPreferences(getActivity().getPackageName(), 0);
        this.f4727w.registerOnSharedPreferenceChangeListener(this);
        this.f4728x = new C2185a(getActivity());
        this.f4729y = RoutePlanSearch.newInstance();
        this.f4729y.setOnGetRoutePlanResultListener(this);
        this.f4710f.setOnClickListener(this);
        this.f4708d.setOnClickListener(this);
        this.f4709e.setOnClickListener(this);
        this.f4711g.setOnClickListener(this);
        if (!this.f4727w.contains("route_display")) {
            this.f4710f.setVisibility(8);
        }
        if (this.f4699O) {
            m6042a(viewGroup2);
        } else {
            m6053b(viewGroup2);
        }
        return viewGroup2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        getActivity();
        this.f4696L = (LocationManager) activity.getSystemService(MapboxEvent.TYPE_LOCATION);
        this.f4697M = new MapFragment$a(this, null);
        this.f4696L.addGpsStatusListener(this.f4697M);
        m6068g();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.f4699O) {
            m6073j();
        }
        this.f4707c.setOnClickListener(this);
        this.f4704T = getActivity().getSharedPreferences(getActivity().getPackageName(), 0);
        this.f4700P = new C1903a(this);
        m6072i();
    }

    /* renamed from: a */
    public MapFragment m6083a() {
        return this;
    }

    /* renamed from: a */
    public void m6086a(StageDTO stageDTO) {
        if (stageDTO != null) {
            this.f4701Q = stageDTO.getStartPoint();
            this.f4703S = stageDTO.getStageName();
            m6055b(stageDTO);
        }
    }

    public void onClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case C1373R.id.activity_fragment_tab_map_fragment_button_location:
                if (this.f4699O) {
                    this.f4720p = true;
                    this.f4722r.requestLocation();
                    return;
                } else if (this.f4689E != null && this.f4694J != null) {
                    this.f4689E.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(this.f4694J).zoom(16.0f).build()));
                    return;
                } else {
                    return;
                }
            case C1373R.id.activity_fragment_tab_map_fragment_button_exit:
                C2580w.a(getActivity(), "", "click_ridding_map_close");
                this.f4698N.a();
                return;
            case C1373R.id.activity_fragment_tab_map_fragment_route_display:
                if (C1849a.a()) {
                    boolean z2 = this.f4727w.getBoolean("route_display", true);
                    Editor edit = this.f4727w.edit();
                    String str = "route_display";
                    if (z2) {
                        z = false;
                    }
                    edit.putBoolean(str, z).commit();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if (bDLocation != null && this.f4706b != null) {
            getActivity().runOnUiThread(new MapFragment$1(this, bDLocation));
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 1:
                System.arraycopy(sensorEvent.values, 0, this.f4716l, 0, 3);
                break;
            case 2:
                System.arraycopy(sensorEvent.values, 0, this.f4717m, 0, 3);
                break;
            default:
                return;
        }
        float[] fArr = new float[9];
        float[] fArr2 = new float[3];
        if (SensorManager.getRotationMatrix(fArr, new float[9], this.f4716l, this.f4717m)) {
            SensorManager.getOrientation(fArr, fArr2);
            for (int i = 0; i < fArr2.length; i++) {
                fArr2[i] = (float) Math.toDegrees((double) fArr2[i]);
            }
            this.f4718n = fArr2[0];
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (this.f4699O) {
            if (str != null && "use_route_id".equals(str)) {
                this.f4687C = this.f4727w.getString(str, "");
                if (!TextUtils.isEmpty(this.f4687C)) {
                    this.f4730z.clear();
                    this.f4688D.clear();
                    this.f4710f.setVisibility(0);
                    m6049a(this.f4687C);
                    this.f4721q.clear();
                    m6068g();
                } else {
                    return;
                }
            }
            if (str != null && "route_display".equals(str)) {
                if (this.f4727w.getBoolean("route_display", true)) {
                    this.f4710f.setImageResource(C1373R.drawable.map_fragment_route_display_icon);
                    this.f4687C = this.f4727w.getString("use_route_id", "");
                    m6049a(this.f4687C);
                    return;
                }
                this.f4710f.setImageResource(C1373R.drawable.map_fragment_route_undisplay_icon);
                this.f4721q.clear();
                m6068g();
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onPause() {
        if (!this.f4699O) {
            m6090d();
        } else if (this.f4706b != null) {
            this.f4706b.onPause();
        }
        super.onPause();
    }

    public void onResume() {
        this.f4699O = C1849a.a();
        if (this.f4699O) {
            if (this.f4706b != null) {
                this.f4706b.onResume();
            }
        } else if (this.f4693I != null) {
            if (!this.f4693I.c().isConnected()) {
                this.f4693I.a();
            } else if (!this.f4691G) {
                m6089c();
            }
        }
        super.onResume();
        m6068g();
    }

    public void onStop() {
        if (!(this.f4699O || this.f4693I == null || !this.f4693I.c().isConnected())) {
            this.f4693I.b();
        }
        super.onStop();
    }

    public void onDestroy() {
        if (this.f4727w != null) {
            this.f4727w.unregisterOnSharedPreferenceChangeListener(this);
        }
        if (this.f4699O) {
            if (!(this.f4722r == null || this.f4729y == null || this.f4721q == null)) {
                this.f4722r.stop();
                this.f4729y.destroy();
                this.f4721q.setMyLocationEnabled(false);
            }
            if (this.f4706b != null) {
                this.f4706b.onDestroy();
            }
        }
        this.f4696L.removeGpsStatusListener(this.f4697M);
        super.onDestroy();
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
                Toasts.show(getActivity(), C1373R.string.route_map_make_activity_select_err);
            }
            if (bikingRouteResult.error == ERRORNO.NO_ERROR && this.f4721q != null && bikingRouteResult.getRouteLines() != null && bikingRouteResult.getRouteLines().get(0) != null) {
                List arrayList = new ArrayList();
                for (BikingStep wayPoints : ((BikingRouteLine) bikingRouteResult.getRouteLines().get(0)).getAllStep()) {
                    arrayList.addAll(wayPoints.getWayPoints());
                }
                this.f4688D.add(this.f4686B, arrayList);
                m6069h();
                this.f4686B++;
                if (this.f4686B < this.f4730z.size() && this.f4686B > 0 && this.f4686B + 1 < this.f4730z.size()) {
                    PoiInfoDTO poiInfoDTO = (PoiInfoDTO) this.f4730z.get(this.f4686B);
                    PlanNode withLocation = PlanNode.withLocation(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()));
                    poiInfoDTO = (PoiInfoDTO) this.f4730z.get(this.f4686B + 1);
                    this.f4729y.bikingSearch(new BikingRoutePlanOption().from(withLocation).to(PlanNode.withLocation(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()))));
                }
            }
        }
    }

    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
    }

    /* renamed from: a */
    private void m6049a(String str) {
        if (this.f4728x != null && !TextUtils.isEmpty(str)) {
            if (!this.f4730z.isEmpty() && !this.f4688D.isEmpty()) {
                m6069h();
            } else if (this.f4727w.contains(str)) {
                m6057b(str);
            } else {
                C2794a asyncTaskQueue = getAsyncTaskQueue();
                if (asyncTaskQueue != null) {
                    asyncTaskQueue.a(new MapFragment$2(this), new String[]{str});
                }
            }
        }
    }

    /* renamed from: f */
    private void m6066f() {
        if (this.f4730z != null && !this.f4730z.isEmpty()) {
            this.f4721q.clear();
            this.f4688D.clear();
            this.f4686B = 0;
            if (this.f4730z.size() == 1) {
                m6043a(new LatLng(((PoiInfoDTO) this.f4730z.get(0)).getLatitude(), ((PoiInfoDTO) this.f4730z.get(0)).getLongitude()), null);
            } else if (!this.f4730z.isEmpty() && this.f4730z.size() >= 1) {
                PoiInfoDTO poiInfoDTO = (PoiInfoDTO) this.f4730z.get(0);
                LatLng latLng = new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude());
                com.mapbox.mapboxsdk.geometry.LatLng b = C2558g.b(latLng);
                this.f4701Q = new StagePointDTO(b.getLongitude(), b.getLatitude());
                PlanNode withLocation = PlanNode.withLocation(latLng);
                poiInfoDTO = (PoiInfoDTO) this.f4730z.get(1);
                this.f4729y.bikingSearch(new BikingRoutePlanOption().from(withLocation).to(PlanNode.withLocation(new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()))));
            }
        }
    }

    /* renamed from: g */
    private void m6068g() {
        f4684a.info("drawLine");
        if (this.f4723s != null) {
            LocalActivity a = this.f4723s.m5861a();
            if (a == null || a.getState() == 4) {
                this.f4685A = false;
                return;
            }
            String id = a.getId();
            if (!TextUtils.isEmpty(id)) {
                C2794a asyncTaskQueue = getAsyncTaskQueue();
                if (asyncTaskQueue != null) {
                    f4684a.info("mapfragment drawline");
                    if (this.f4699O) {
                        m6047a(asyncTaskQueue, id);
                    } else {
                        m6056b(asyncTaskQueue, id);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m6047a(C2794a c2794a, String str) {
        c2794a.a(new MapFragment$3(this), new String[]{str});
    }

    /* renamed from: b */
    private void m6056b(C2794a c2794a, String str) {
        f4684a.error("googleTask");
        c2794a.a(new MapFragment$4(this), new String[]{str});
    }

    /* renamed from: b */
    private void m6057b(String str) {
        Object string = this.f4727w.getString(str, null);
        if (!TextUtils.isEmpty(string)) {
            List arrayList = new ArrayList();
            for (String split : string.split(C0880h.f2220b)) {
                String[] split2 = split.split(",");
                arrayList.add(new LatLng(Double.valueOf(split2[0]).doubleValue(), Double.valueOf(split2[1]).doubleValue()));
            }
            if (arrayList.size() >= 2) {
                if (this.f4721q != null) {
                    this.f4721q.clear();
                }
                m6043a((LatLng) arrayList.get(0), (LatLng) arrayList.get(arrayList.size() - 1));
                m6050a(arrayList, -10583834);
                m6060c(arrayList);
            }
        }
    }

    /* renamed from: h */
    private void m6069h() {
        if (this.f4730z != null && this.f4730z.size() >= 2) {
            this.f4721q.clear();
            PoiInfoDTO poiInfoDTO = (PoiInfoDTO) this.f4730z.get(0);
            LatLng latLng = new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude());
            poiInfoDTO = (PoiInfoDTO) this.f4730z.get(this.f4730z.size() - 1);
            m6043a(latLng, new LatLng(poiInfoDTO.getLatitude(), poiInfoDTO.getLongitude()));
            StringBuilder stringBuilder = new StringBuilder();
            for (List<LatLng> list : this.f4688D) {
                if (list != null) {
                    m6050a((List) list, -10583834);
                    m6060c((List) list);
                    for (LatLng latLng2 : list) {
                        stringBuilder.append(String.valueOf(latLng2.latitude)).append(",").append(String.valueOf(latLng2.longitude)).append(C0880h.f2220b);
                    }
                }
            }
            this.f4727w.edit().putString(this.f4687C, stringBuilder.toString()).commit();
        }
    }

    /* renamed from: a */
    private void m6050a(List<LatLng> list, int i) {
        if (list != null && list.size() >= 2) {
            float f = 1.0E-4f;
            while (list.size() >= 1000) {
                try {
                    list = C1892g.a(f, list);
                    f += 3.0E-4f;
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
            this.f4721q.addOverlay(new PolylineOptions().width(8).color(i).points(list));
        }
    }

    /* renamed from: a */
    private void m6043a(LatLng latLng, LatLng latLng2) {
        if (latLng != null) {
            this.f4721q.addOverlay(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_detail_start)));
        }
        if (latLng2 != null) {
            this.f4721q.addOverlay(new MarkerOptions().position(latLng2).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_finish_end)));
        }
    }

    /* renamed from: b */
    private void m6058b(List<com.google.android.gms.maps.model.LatLng> list) {
        f4684a.info("Google Activity source sample size = " + list.size());
        float f = 1.0E-4f;
        while (list.size() >= 500) {
            try {
                list = C1892g.b(f, list);
                f += 3.0E-4f;
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
        f4684a.info("Google Activity new sample size = " + list.size());
        int size = list.size();
        if (size >= 2 && size <= 10000) {
            this.f4689E.addPolyline(new com.google.android.gms.maps.model.PolylineOptions().width(6.0f).color(-1426128896).addAll(list));
        }
    }

    /* renamed from: a */
    private void m6048a(com.google.android.gms.maps.model.LatLng latLng, com.google.android.gms.maps.model.LatLng latLng2) {
        if (latLng != null) {
            this.f4689E.addMarker(new com.google.android.gms.maps.model.MarkerOptions().icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_detail_start)).position(latLng));
        }
        if (latLng2 != null) {
            this.f4689E.addMarker(new com.google.android.gms.maps.model.MarkerOptions().icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_activity_finish_end)).position(latLng2));
        }
    }

    /* renamed from: b */
    private void m6055b(StageDTO stageDTO) {
        if (stageDTO != null) {
            List points = stageDTO.getPoints();
            if (points != null && points.size() >= 2) {
                List a;
                if (C1849a.a()) {
                    a = this.f4700P.a(points);
                    m6050a(a, -10583834);
                    m6043a((LatLng) a.get(0), (LatLng) a.get(a.size() - 1));
                    m6060c(a);
                    return;
                }
                a = this.f4700P.b(points);
                m6058b(a);
                m6048a((com.google.android.gms.maps.model.LatLng) a.get(0), (com.google.android.gms.maps.model.LatLng) a.get(a.size() - 1));
                m6088a(a);
            }
        }
    }

    /* renamed from: i */
    private void m6072i() {
        if (this.f4727w == null) {
            this.f4727w = getActivity().getSharedPreferences(getActivity().getPackageName(), 0);
        }
        long j = this.f4727w.getLong("use_stage_route_id", -1);
        if (j > 0) {
            this.f4700P.a(j);
            return;
        }
        String string = this.f4727w.getString("use_route_id", "");
        if (!TextUtils.isEmpty(string)) {
            m6049a(string);
        }
    }

    /* renamed from: a */
    private void m6042a(ViewGroup viewGroup) {
        try {
            BaiduMapOptions baiduMapOptions = new BaiduMapOptions();
            baiduMapOptions.scaleControlEnabled(false);
            baiduMapOptions.zoomControlsEnabled(false);
            baiduMapOptions.zoomGesturesEnabled(true);
            this.f4706b = new MapView(getActivity(), baiduMapOptions);
            this.f4706b.getChildAt(1).setVisibility(8);
            viewGroup.addView(this.f4706b, 0, new LayoutParams(-1, -1));
        } catch (Throwable e) {
            f4684a.error("baiduMapInit error", e);
        }
    }

    /* renamed from: b */
    private void m6053b(ViewGroup viewGroup) {
        if (GoogleMapManager.a(getActivity())) {
            GoogleMapOptions googleMapOptions = new GoogleMapOptions();
            googleMapOptions.mapType(1).compassEnabled(false).rotateGesturesEnabled(false).tiltGesturesEnabled(false).zoomControlsEnabled(false).zoomGesturesEnabled(true);
            this.f4690F = com.google.android.gms.maps.MapFragment.newInstance(googleMapOptions);
            FragmentTransaction beginTransaction = getActivity().getFragmentManager().beginTransaction();
            beginTransaction.add(C1373R.id.mapViewFrameLayout, this.f4690F);
            beginTransaction.commit();
            this.f4690F.getMapAsync(this);
            return;
        }
        m6078l();
    }

    /* renamed from: j */
    private void m6073j() {
        if (this.f4706b != null) {
            LocationClientOption locationClientOption = new LocationClientOption();
            locationClientOption.setOpenGps(true);
            locationClientOption.setPriority(1);
            locationClientOption.setCoorType("bd09ll");
            locationClientOption.setScanSpan(10000);
            locationClientOption.setAddrType("all");
            this.f4721q = this.f4706b.getMap();
            UiSettings uiSettings = this.f4721q.getUiSettings();
            uiSettings.setCompassEnabled(false);
            uiSettings.setOverlookingGesturesEnabled(false);
            this.f4721q.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, null));
            this.f4721q.setMyLocationEnabled(true);
            this.f4721q.animateMapStatus(MapStatusUpdateFactory.zoomTo(this.f4719o));
            this.f4722r.setLocOption(locationClientOption);
            this.f4722r.start();
            this.f4722r.requestLocation();
        }
    }

    /* renamed from: k */
    private void m6075k() {
        this.f4690F.getMapAsync(new MapFragment$5(this));
    }

    public void onMapReady(GoogleMap googleMap) {
        m6075k();
        this.f4693I = new GoogleMapManager();
        this.f4693I.a(getActivity(), this);
        this.f4693I.a();
    }

    /* renamed from: a */
    public void m6085a(Location location, Bundle bundle) {
        if (!this.f4691G) {
            m6089c();
        }
    }

    /* renamed from: a */
    public void m6087a(ConnectionResult connectionResult) {
    }

    /* renamed from: a */
    public void m6084a(int i) {
    }

    /* renamed from: c */
    protected void m6089c() {
        if (this.f4693I != null && this.f4692H != null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(this.f4693I.c(), this.f4692H, (LocationListener) this);
        }
    }

    /* renamed from: d */
    protected void m6090d() {
        if (this.f4693I != null && this.f4693I.c().isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.f4693I.c(), (LocationListener) this);
        }
    }

    public void onLocationChanged(Location location) {
        if (location == null) {
            f4684a.info("google onLocationChanged null");
            return;
        }
        this.f4694J = new com.google.android.gms.maps.model.LatLng(location.getLatitude(), location.getLongitude());
        if (this.f4695K) {
            this.f4695K = false;
            this.f4689E.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(this.f4694J).zoom(16.0f).build()));
        }
        m6068g();
        com.mapbox.mapboxsdk.geometry.LatLng a = C2558g.a(this.f4694J);
        this.f4702R = new StagePointDTO(a.getLongitude(), a.getLatitude());
        m6080m();
    }

    /* renamed from: l */
    private void m6078l() {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(C1373R.layout.layout_nogoogleplayservice1, null);
        this.f4712h.addView(relativeLayout, new LayoutParams(-1, -1));
    }

    /* renamed from: a */
    private String m6038a(int i, GpsStatus gpsStatus) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (gpsStatus == null) {
            this.f4713i.setRating(0.0f);
        } else if (i == 4) {
            gpsStatus.getMaxSatellites();
            this.f4705U.clear();
            int i2 = 0;
            for (GpsSatellite gpsSatellite : gpsStatus.getSatellites()) {
                int snr;
                this.f4705U.add(gpsSatellite);
                if (((double) gpsSatellite.getSnr()) > 0.0d) {
                    snr = (int) (gpsSatellite.getSnr() + ((float) i2));
                } else {
                    snr = i2;
                }
                i2 = snr;
            }
            if (i2 > 240) {
                this.f4713i.setRating(5.0f);
            } else if (i2 > Opcodes.GETFIELD) {
                this.f4713i.setRating(4.0f);
            } else if (i2 > 120) {
                this.f4713i.setRating(3.0f);
            } else if (i2 > 60) {
                this.f4713i.setRating(2.0f);
            } else if (i2 > 0) {
                this.f4713i.setRating(1.0f);
            } else {
                this.f4713i.setRating(0.0f);
            }
            stringBuilder.append("搜索到卫星个数：" + this.f4705U.size());
        }
        return stringBuilder.toString();
    }

    /* renamed from: m */
    private void m6080m() {
        if (this.f4701Q == null) {
            f4684a.info("使用路段的起点坐标为空");
        } else if (this.f4702R == null) {
            f4684a.info("当前位置为空");
        } else {
            float[] fArr = new float[1];
            Location.distanceBetween(this.f4701Q.getLatitude(), this.f4701Q.getLongitude(), this.f4702R.getLatitude(), this.f4702R.getLongitude(), fArr);
            float f = fArr[0];
            boolean z = this.f4704T.contains("use_stage_route_hint") && this.f4704T.getBoolean("use_stage_route_hint", false);
            f4684a.trace("两点距离 ＝ " + f + ", isHint = " + z);
            if (f < 50.0f && !z) {
                this.f4714j.setVisibility(0);
                this.f4715k.setText(this.f4703S);
                this.f4704T.edit().putBoolean("use_stage_route_hint", true).commit();
                new Handler(Looper.getMainLooper()).postDelayed(new MapFragment$6(this), 3000);
            }
        }
    }

    /* renamed from: c */
    private void m6060c(List<LatLng> list) {
        if (list != null && !list.isEmpty() && this.f4721q != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (int i = 0; i < list.size(); i++) {
                builder.include((LatLng) list.get(i));
            }
            this.f4721q.animateMapStatus(MapStatusUpdateFactory.newLatLngBounds(builder.build(), this.f4706b.getWidth(), this.f4706b.getHeight()), 1000);
        }
    }

    /* renamed from: a */
    public void m6088a(List<com.google.android.gms.maps.model.LatLng> list) {
        if (list != null && !list.isEmpty() && this.f4689E != null) {
            com.google.android.gms.maps.model.LatLngBounds.Builder builder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
            for (com.google.android.gms.maps.model.LatLng include : list) {
                builder.include(include);
            }
            try {
                this.f4689E.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onFinish() {
    }

    public void onCancel() {
    }
}
