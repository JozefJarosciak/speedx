package com.beastbikes.android.ble.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.Polyline;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.C1662i;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.dto.C1667b;
import com.beastbikes.android.ble.dto.C1668c;
import com.beastbikes.android.ble.dto.C1669d;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.android.ble.ui.p098a.C1740p;
import com.beastbikes.android.ble.ui.widget.WayPointLinearLayout;
import com.beastbikes.android.ble.ui.widget.WayPointLinearLayout.C1687a;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.commons.models.Position;
import com.mapbox.services.directions.v5.DirectionsCriteria;
import com.mapbox.services.directions.v5.MapboxDirections;
import com.mapbox.services.directions.v5.MapboxDirections.Builder;
import com.mapbox.services.directions.v5.models.DirectionsResponse;
import com.mapbox.services.directions.v5.models.DirectionsRoute;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@TargetApi(18)
public class NavigationActivity extends NavigationBaseActivity implements C1687a {
    /* renamed from: d */
    private static final Logger f7615d = LoggerFactory.getLogger("NavigationActivity");
    /* renamed from: e */
    private TextView f7616e;
    /* renamed from: f */
    private TextView f7617f;
    /* renamed from: g */
    private Button f7618g;
    /* renamed from: h */
    private RelativeLayout f7619h;
    /* renamed from: i */
    private TextView f7620i;
    /* renamed from: j */
    private TextView f7621j;
    /* renamed from: k */
    private TextView f7622k;
    /* renamed from: l */
    private WayPointLinearLayout f7623l;
    /* renamed from: m */
    private LatLng f7624m;
    /* renamed from: n */
    private LatLng f7625n;
    /* renamed from: o */
    private SparseArray<LatLng> f7626o;
    /* renamed from: p */
    private C1668c f7627p;
    /* renamed from: q */
    private ArrayList<Position> f7628q;
    /* renamed from: r */
    private C1454a f7629r;
    /* renamed from: s */
    private BleDevice f7630s;
    /* renamed from: t */
    private C1662i f7631t;
    /* renamed from: u */
    private boolean f7632u = true;
    /* renamed from: v */
    private boolean f7633v = true;
    /* renamed from: w */
    private ServiceConnection f7634w = new C16845(this);

    /* renamed from: com.beastbikes.android.ble.ui.NavigationActivity$2 */
    class C16812 implements Callback<DirectionsResponse> {
        /* renamed from: a */
        final /* synthetic */ NavigationActivity f7597a;

        C16812(NavigationActivity navigationActivity) {
            this.f7597a = navigationActivity;
        }

        public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
            NavigationActivity.f7615d.error("Response code: " + response.code() + "  message: " + response.message());
            if (response.body() == null) {
                NavigationActivity.f7615d.error("No routes found, make sure you set the right user and access token.");
                Toasts.show(this.f7597a, this.f7597a.getString(C1373R.string.str_navigation_calcuate_route_failed_and_retry_later));
            } else if (((DirectionsResponse) response.body()).getRoutes().size() < 1) {
                NavigationActivity.f7615d.error("No routes found");
                Toasts.show(this.f7597a, this.f7597a.getString(C1373R.string.str_navigation_calcuate_route_failed_and_retry_later));
            } else {
                DirectionsRoute directionsRoute = (DirectionsRoute) ((DirectionsResponse) response.body()).getRoutes().get(0);
                if (directionsRoute == null) {
                    Toasts.show(this.f7597a, this.f7597a.getString(C1373R.string.str_navigation_calcuate_route_failed_and_retry_later));
                    return;
                }
                this.f7597a.m9116a(new C1668c(directionsRoute), false);
                this.f7597a.m9106c();
            }
        }

        public void onFailure(Call<DirectionsResponse> call, Throwable th) {
            Toasts.show(this.f7597a, this.f7597a.getString(C1373R.string.str_navigation_calcuate_route_failed_and_retry_later));
            this.f7597a.m9106c();
        }
    }

    /* renamed from: com.beastbikes.android.ble.ui.NavigationActivity$4 */
    class C16834 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ NavigationActivity f7602a;

        C16834(NavigationActivity navigationActivity) {
            this.f7602a = navigationActivity;
        }

        public void run() {
            Toasts.show(this.f7602a, this.f7602a.getString(C1373R.string.str_sync_success));
            this.f7602a.finish();
        }
    }

    /* renamed from: com.beastbikes.android.ble.ui.NavigationActivity$5 */
    class C16845 implements ServiceConnection {
        /* renamed from: a */
        final /* synthetic */ NavigationActivity f7603a;

        C16845(NavigationActivity navigationActivity) {
            this.f7603a = navigationActivity;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            NavigationActivity.f7615d.info("onServiceConnected");
            CentralService a = ((C1596c) iBinder).m8563a();
            this.f7603a.c = a.m8582b();
            this.f7603a.c.mo3145a(this.f7603a);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            NavigationActivity.f7615d.info("onServiceDisconnected");
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        startService(intent);
        bindService(intent, this.f7634w, 1);
    }

    /* renamed from: a */
    protected void mo3201a(Bundle bundle) {
        super.mo3201a(bundle);
        if (getIntent() != null) {
            this.f7630s = (BleDevice) getIntent().getSerializableExtra("extraBleDevice");
        }
        if (this.f7630s == null) {
            Toasts.show((Context) this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
            finish();
            return;
        }
        this.f7629r = C1454a.a();
        m9127d();
        this.a.setOnMapStatusChangeListener(this);
    }

    public void onClick(View view) {
        super.onClick(view);
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.activity_navigation_tv_my_location:
                intent = new Intent(this, SearchLocationActivity.class);
                intent.putExtra("search_event_id", 6);
                a(intent, 6);
                return;
            case C1373R.id.activity_navigation_tv_destination_location:
                intent = new Intent(this, SearchLocationActivity.class);
                intent.putExtra("search_event_id", 7);
                a(intent, 7);
                return;
            case C1373R.id.btn_calculate_route:
                if (this.f7632u) {
                    if (this.f7624m != null && this.f7625n != null) {
                        try {
                            m9118a(this.f7624m, this.f7625n, this.f7626o);
                            return;
                        } catch (ServicesException e) {
                            e.printStackTrace();
                            f7615d.error("getRoute error e: " + e);
                            return;
                        }
                    }
                    return;
                } else if (this.f7629r.a(this, "show_disclaimer", false)) {
                    m9115a(this.f7627p, this.f7628q);
                    return;
                } else {
                    startActivityForResult(new Intent(this, DisclaimerActivity.class), 90);
                    return;
                }
            case C1373R.id.tv_navigation_how_to_use_nav:
                startActivity(new Intent(this, NavigationDescActivity.class));
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 90) {
            this.f7629r.a(this, "show_disclaimer", Boolean.valueOf(true)).apply();
            m9115a(this.f7627p, this.f7628q);
        }
    }

    /* renamed from: b */
    protected void m9139b(int i, Object obj) {
        switch (i) {
            case 6:
                m9121a(true, obj);
                break;
            case 7:
                m9121a(false, obj);
                break;
            case 8:
                m9120a(obj);
                break;
        }
        if (this.f7624m == null || this.f7625n == null) {
            this.f7618g.setBackground(ContextCompat.getDrawable(this, C1373R.drawable.bg_navigation_btn_gray));
        } else {
            this.f7618g.setBackground(ContextCompat.getDrawable(this, C1373R.drawable.bg_navigation_btn_black));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f7634w != null) {
            unbindService(this.f7634w);
        }
    }

    /* renamed from: a */
    public void mo3200a(double d, double d2) {
    }

    /* renamed from: b */
    public void mo3204b(double d, double d2) {
    }

    protected void a_(final NavigationLocation navigationLocation) {
        runOnUiThread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ NavigationActivity f7596b;

            public void run() {
                if (this.f7596b.f7616e != null) {
                    this.f7596b.f7616e.setText(navigationLocation.getName());
                    this.f7596b.f7616e.setCompoundDrawablesWithIntrinsicBounds(C1373R.drawable.ic_select_location_my_location, 0, 0, 0);
                    this.f7596b.f7616e.setTextColor(Color.parseColor("#111111"));
                    this.f7596b.f7624m = new LatLng(navigationLocation.getLatitude(), navigationLocation.getLongitude());
                    this.f7596b.f7633v = true;
                }
            }
        });
    }

    /* renamed from: c */
    public void mo3205c(int i) {
        Intent intent = new Intent(this, SearchLocationActivity.class);
        intent.putExtra("search_event_id", 8);
        intent.putExtra("way_point_position", i);
        a(intent, 8);
    }

    /* renamed from: a */
    public void mo3202a(NavigationLocation navigationLocation, int i) {
        int i2 = 0;
        if (this.f7626o != null && this.f7626o.size() > 0 && navigationLocation != null) {
            int i3;
            this.f7626o.remove(i);
            ArrayList arrayList = new ArrayList();
            int size = this.f7626o.size();
            for (i3 = 0; i3 < size; i3++) {
                LatLng latLng = (LatLng) this.f7626o.get(this.f7626o.keyAt(i3));
                if (latLng != null) {
                    arrayList.add(latLng);
                }
            }
            this.f7626o.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i3 = i2 + 1;
                this.f7626o.put(i2, (LatLng) it.next());
                i2 = i3;
            }
            this.f7632u = true;
            this.f7618g.setText(C1373R.string.str_navigation_calculate_route);
            if (this.f7619h != null) {
                this.f7619h.setVisibility(8);
            }
            m9131h();
            m9129f();
        }
    }

    /* renamed from: a */
    public void mo3199a() {
        this.f7623l.m9379a();
    }

    /* renamed from: a */
    private void m9121a(boolean z, Object obj) {
        NavigationLocation navigationLocation;
        if (obj != null) {
            navigationLocation = (NavigationLocation) ((Intent) obj).getSerializableExtra("mapboxlocation");
        } else if (this.b == null) {
            Toasts.show((Context) this, getString(C1373R.string.str_get_location_info_failed));
            f7615d.error("获取位置信息失败");
            return;
        } else {
            navigationLocation = this.b;
            if (this.f7633v && !z) {
                this.f7633v = false;
                this.f7624m = null;
                this.f7616e.setText(C1373R.string.str_navigation_select_start);
                this.f7616e.setCompoundDrawablesWithIntrinsicBounds(C1373R.drawable.ic_select_location_my_location_gray, 0, 0, 0);
                this.f7616e.setTextColor(Color.parseColor("#999999"));
            }
            if (!this.f7633v && z) {
                this.f7633v = true;
                this.f7625n = null;
                this.f7617f.setText(C1373R.string.str_navigation_select_destination);
                this.f7617f.setCompoundDrawablesWithIntrinsicBounds(C1373R.drawable.ic_select_location_destination_gray, 0, 0, 0);
                this.f7617f.setTextColor(Color.parseColor("#999999"));
            }
        }
        if (navigationLocation != null) {
            if (C1849a.m9641a()) {
                com.baidu.mapapi.model.LatLng g = C2558g.m12851g(navigationLocation.getLatitude(), navigationLocation.getLongitude());
                this.a.m11658a(g.latitude, g.longitude);
            } else {
                com.google.android.gms.maps.model.LatLng a = C2558g.m12841a(navigationLocation.getLatitude(), navigationLocation.getLongitude());
                this.a.m11658a(a.latitude, a.longitude);
            }
            if (z) {
                this.f7624m = new LatLng(navigationLocation.getLatitude(), navigationLocation.getLongitude());
                this.f7616e.setText(navigationLocation.getName());
                this.f7616e.setCompoundDrawablesWithIntrinsicBounds(C1373R.drawable.ic_select_location_my_location, 0, 0, 0);
                this.f7616e.setTextColor(Color.parseColor("#111111"));
                f7615d.info("start: " + navigationLocation.getName());
            } else {
                this.f7625n = new LatLng(navigationLocation.getLatitude(), navigationLocation.getLongitude());
                this.f7617f.setText(navigationLocation.getName());
                this.f7617f.setCompoundDrawablesWithIntrinsicBounds(C1373R.drawable.ic_location_red, 0, 0, 0);
                this.f7617f.setTextColor(Color.parseColor("#111111"));
                f7615d.info("destination: " + navigationLocation.getName());
            }
            this.f7632u = true;
            this.f7618g.setText(C1373R.string.str_navigation_calculate_route);
            if (this.f7619h != null) {
                this.f7619h.setVisibility(8);
            }
            m9129f();
            m9131h();
        }
    }

    /* renamed from: a */
    private void m9120a(Object obj) {
        if (obj != null) {
            Intent intent = (Intent) obj;
            NavigationLocation navigationLocation = (NavigationLocation) intent.getSerializableExtra("mapboxlocation");
            int intExtra = intent.getIntExtra("way_point_position", -1);
            if (navigationLocation != null && intExtra != -1) {
                f7615d.info("wayPoint: position: " + intExtra + "  location: " + navigationLocation);
                if (this.f7626o == null) {
                    this.f7626o = new SparseArray(3);
                }
                this.f7626o.put(intExtra, new LatLng(navigationLocation.getLatitude(), navigationLocation.getLongitude()));
                this.f7623l.m9381a(intExtra, navigationLocation);
                m9129f();
                if (C1849a.m9641a()) {
                    com.baidu.mapapi.model.LatLng g = C2558g.m12851g(navigationLocation.getLatitude(), navigationLocation.getLongitude());
                    this.a.m11658a(g.latitude, g.longitude);
                } else {
                    com.google.android.gms.maps.model.LatLng a = C2558g.m12841a(navigationLocation.getLatitude(), navigationLocation.getLongitude());
                    this.a.m11658a(a.latitude, a.longitude);
                }
                this.f7632u = true;
                this.f7618g.setText(C1373R.string.str_navigation_calculate_route);
                if (this.f7619h != null) {
                    this.f7619h.setVisibility(8);
                }
                m9131h();
            }
        }
    }

    /* renamed from: d */
    private void m9127d() {
        ((ViewStub) findViewById(C1373R.id.viewStub_before_select_location)).inflate();
        this.f7616e = (TextView) findViewById(C1373R.id.activity_navigation_tv_my_location);
        this.f7617f = (TextView) findViewById(C1373R.id.activity_navigation_tv_destination_location);
        this.f7618g = (Button) findViewById(C1373R.id.btn_calculate_route);
        TextView textView = (TextView) findViewById(C1373R.id.tv_navigation_how_to_use_nav);
        this.f7623l = (WayPointLinearLayout) findViewById(C1373R.id.wayPointLinear);
        this.f7616e.setOnClickListener(this);
        this.f7617f.setOnClickListener(this);
        this.f7618g.setOnClickListener(this);
        textView.setOnClickListener(this);
        this.f7623l.setOnClickWayPointListener(this);
    }

    /* renamed from: e */
    private void m9128e() {
        if (this.f7619h == null) {
            ((ViewStub) findViewById(C1373R.id.viewStub_route_info)).inflate();
            this.f7619h = (RelativeLayout) findViewById(C1373R.id.relative_navigation_route_info);
            this.f7620i = (TextView) findViewById(C1373R.id.activity_navigation_tv_distance);
            this.f7621j = (TextView) findViewById(C1373R.id.activity_navigation_tv_distance_unit);
            this.f7622k = (TextView) findViewById(C1373R.id.activity_navigation_tv_total_time);
        }
        this.f7619h.setVisibility(0);
    }

    /* renamed from: f */
    private void m9129f() {
        m9130g();
        if (this.f7624m != null) {
            this.a.m11667a(this.f7624m, (int) C1373R.drawable.ic_navigation_start_pos);
        }
        if (this.f7625n != null) {
            this.a.m11667a(this.f7625n, (int) C1373R.drawable.ic_navigation_destination_pos);
        }
        if (this.f7626o != null) {
            int size = this.f7626o.size();
            for (int i = 0; i < size; i++) {
                LatLng latLng = (LatLng) this.f7626o.get(this.f7626o.keyAt(i));
                if (latLng != null) {
                    this.a.m11667a(latLng, (int) C1373R.drawable.ic_navigation_way_point_pos);
                }
            }
        }
    }

    /* renamed from: g */
    private void m9130g() {
        if (this.a != null) {
            boolean a = C1849a.m9641a();
            ArrayList markers = this.a.getMarkers();
            if (markers != null && !markers.isEmpty()) {
                Iterator it = markers.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (a) {
                        ((Marker) next).remove();
                    } else {
                        ((com.google.android.gms.maps.model.Marker) next).remove();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m9118a(LatLng latLng, LatLng latLng2, SparseArray<LatLng> sparseArray) throws ServicesException {
        if (latLng == null || latLng2 == null) {
            f7615d.info("Origin or destination is null");
            return;
        }
        if (this.f7628q == null) {
            this.f7628q = new ArrayList();
        }
        this.f7628q.clear();
        ArrayList arrayList = new ArrayList();
        if (sparseArray != null && sparseArray.size() > 0) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                LatLng latLng3 = (LatLng) sparseArray.get(sparseArray.keyAt(i));
                if (latLng3 != null) {
                    this.f7628q.add(Position.fromCoordinates(latLng3.getLongitude(), latLng3.getLatitude()));
                    arrayList.add(latLng3);
                }
            }
        }
        f7615d.info("Way points size: " + this.f7628q.size());
        if (C1849a.m9641a()) {
            m9119a(latLng, latLng2, arrayList);
        } else {
            m9126b(latLng, latLng2, arrayList);
        }
    }

    /* renamed from: a */
    private void m9116a(C1668c c1668c, boolean z) {
        m9125b(c1668c, z);
        m9128e();
        Iterator it = c1668c.m9050c().iterator();
        double d = 0.0d;
        double d2 = 0.0d;
        while (it.hasNext()) {
            C1667b c1667b = (C1667b) it.next();
            d += c1667b.m9045a();
            d2 = c1667b.m9046b() + d2;
        }
        if (d < 1000.0d) {
            this.f7620i.setText(String.valueOf(d));
            if (C1849a.m9645b((Context) this)) {
                this.f7621j.setText(C1373R.string.metre);
            } else {
                d = C1849a.m9646c(d);
                this.f7621j.setText("feet");
            }
        } else {
            d /= 1000.0d;
            if (C1849a.m9645b((Context) this)) {
                this.f7621j.setText(C1373R.string.kilometre);
            } else {
                d = C1849a.m9638a(d);
                this.f7621j.setText("mile");
            }
        }
        this.f7620i.setText(String.format("%.1f", new Object[]{Double.valueOf(d)}));
        f7615d.info("route total distance: " + d);
        f7615d.info("route total time: " + d);
        if (d2 < 3600.0d) {
            this.f7622k.setText(getString(C1373R.string.str_approximately) + ((((long) d2) % 3600) / 60) + getString(C1373R.string.str_minute));
        } else {
            this.f7622k.setText(getString(C1373R.string.str_approximately) + (((long) d2) / 3600) + getString(C1373R.string.str_hour) + ((((long) d2) % 3600) / 60) + getString(C1373R.string.str_minute));
        }
        this.f7632u = false;
        this.f7618g.setText(C1373R.string.str_navigation_async_to_central);
    }

    /* renamed from: b */
    private void m9125b(C1668c c1668c, boolean z) {
        if (c1668c == null) {
            f7615d.info("method drawRoute NavigationRouteDTO is null");
            return;
        }
        this.f7627p = c1668c;
        ArrayList arrayList = new ArrayList();
        Iterator it = c1668c.m9050c().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((C1667b) it.next()).m9047c().iterator();
            while (it2.hasNext()) {
                arrayList.addAll(((C1669d) it2.next()).m9051a());
            }
        }
        int size = arrayList.size();
        LatLng[] latLngArr = new LatLng[size];
        int i;
        Position position;
        if (z) {
            for (i = 0; i < size; i++) {
                position = (Position) arrayList.get(i);
                latLngArr[i] = C2558g.m12850f(position.getLatitude(), position.getLongitude());
            }
        } else {
            for (i = 0; i < size; i++) {
                position = (Position) arrayList.get(i);
                latLngArr[i] = new LatLng(position.getLatitude(), position.getLongitude());
            }
        }
        this.f7624m = latLngArr[0];
        this.f7625n = latLngArr[size - 1];
        m9129f();
        m9131h();
        this.a.m11671a(latLngArr, 8, Color.parseColor("#ff584f"));
    }

    /* renamed from: h */
    private void m9131h() {
        boolean a = C1849a.m9641a();
        ArrayList polylines = this.a.getPolylines();
        if (polylines != null && !polylines.isEmpty()) {
            Iterator it = polylines.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (a) {
                    ((Polyline) next).remove();
                } else {
                    ((com.google.android.gms.maps.model.Polyline) next).remove();
                }
            }
        }
    }

    /* renamed from: a */
    private void m9115a(C1668c c1668c, ArrayList<Position> arrayList) {
        if (c1668c != null) {
            if (m9132i()) {
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("SYNC_DATA");
                if (findFragmentByTag != null) {
                    beginTransaction.remove(findFragmentByTag);
                    return;
                }
                findFragmentByTag = new C1740p();
                Bundle bundle = new Bundle();
                bundle.putInt("sync_type", 3);
                bundle.putString("central_id", this.f7630s.getMacAddress());
                findFragmentByTag.m9298a(this.c);
                findFragmentByTag.setArguments(bundle);
                if (this.c == null) {
                    f7615d.error("mManager is null");
                    return;
                } else if (1 == this.c.mo3134a(16, "1.0.1", c1668c, arrayList)) {
                    Toasts.show((Context) this, getString(C1373R.string.str_navigation_route_is_too_long));
                    return;
                } else {
                    beginTransaction.add(findFragmentByTag, "SYNC_DATA").commitAllowingStateLoss();
                    return;
                }
            }
            Toasts.show((Context) this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
        }
    }

    /* renamed from: i */
    private boolean m9132i() {
        return C1661h.m8999a().m9004b() != null;
    }

    /* renamed from: a */
    private void m9119a(LatLng latLng, LatLng latLng2, ArrayList<LatLng> arrayList) throws ServicesException {
        f7615d.info("使用 Mapbox Direction服务进行路线规划, 起点 = " + latLng.toString() + ", 终点：" + latLng2.toString() + ", 途经点数： " + arrayList);
        Builder builder = new Builder();
        if (!(arrayList == null || arrayList.isEmpty())) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                LatLng latLng3 = (LatLng) it.next();
                arrayList2.add(Position.fromCoordinates(latLng3.getLongitude(), latLng3.getLatitude()));
            }
            builder.setCoordinates(arrayList2);
        }
        MapboxDirections build = builder.setOrigin(Position.fromCoordinates(latLng.getLongitude(), latLng.getLatitude())).setDestination(Position.fromCoordinates(latLng2.getLongitude(), latLng2.getLatitude())).setProfile(DirectionsCriteria.PROFILE_CYCLING).setAccessToken(MapboxAccountManager.getInstance().getAccessToken()).setSteps(Boolean.valueOf(true)).build();
        m9103a(true, getString(C1373R.string.str_loading));
        build.enqueueCall(new C16812(this));
    }

    /* renamed from: b */
    private void m9126b(final LatLng latLng, final LatLng latLng2, final ArrayList<LatLng> arrayList) {
        f7615d.info("使用Google Map Direction服务进行路线规划, 起点 = " + latLng.toString() + ", 终点：" + latLng2.toString() + ", 途经点数： " + arrayList);
        if (this.f7631t == null) {
            this.f7631t = new C1662i(this);
        }
        getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, C1668c>(this) {
            /* renamed from: d */
            final /* synthetic */ NavigationActivity f7601d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m9091a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m9092a((C1668c) obj);
            }

            protected void onPreExecute() {
                this.f7601d.m9103a(true, this.f7601d.getString(C1373R.string.str_loading));
            }

            /* renamed from: a */
            protected C1668c m9091a(Void... voidArr) {
                Exception exception;
                C1668c c1668c;
                com.google.android.gms.maps.model.LatLng a = C2558g.m12841a(latLng.getLatitude(), latLng.getLongitude());
                com.google.android.gms.maps.model.LatLng a2 = C2558g.m12841a(latLng2.getLatitude(), latLng2.getLongitude());
                String a3 = this.f7601d.m9114a(a);
                String a4 = this.f7601d.m9114a(a2);
                String str = "";
                if (arrayList == null || arrayList.size() <= 0) {
                    NavigationActivity.f7615d.info("Way points is null or empty");
                } else {
                    NavigationActivity.f7615d.info("Way points size: " + arrayList.size());
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        LatLng latLng = (LatLng) it.next();
                        stringBuilder.append(this.f7601d.m9114a(C2558g.m12841a(latLng.getLatitude(), latLng.getLongitude())));
                        stringBuilder.append("|");
                    }
                    str = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
                C1668c c1668c2 = null;
                try {
                    c1668c2 = this.f7601d.f7631t.m9013a("bicycling", a3, a4, str, "");
                    try {
                        if (!TextUtils.equals(c1668c2.m9049b(), "ZERO_RESULTS")) {
                            return c1668c2;
                        }
                        NavigationActivity.f7615d.info("Get direction with bicycling failed, and try with driving");
                        return this.f7601d.f7631t.m9013a(DirectionsCriteria.PROFILE_DRIVING, a3, a4, str, "tolls|highways|ferries");
                    } catch (Exception e) {
                        exception = e;
                        c1668c = c1668c2;
                        exception.printStackTrace();
                        return c1668c;
                    }
                } catch (Exception e2) {
                    exception = e2;
                    c1668c = c1668c2;
                    exception.printStackTrace();
                    return c1668c;
                }
            }

            /* renamed from: a */
            protected void m9092a(C1668c c1668c) {
                this.f7601d.m9106c();
                if (c1668c == null || c1668c.m9050c() == null) {
                    Toasts.show(this.f7601d, this.f7601d.getString(C1373R.string.str_navigation_calcuate_route_failed_and_retry_later));
                    NavigationActivity.f7615d.error("Calculate route failed with NavigationRouteDTO is null");
                    return;
                }
                if (TextUtils.equals(c1668c.m9048a(), DirectionsCriteria.PROFILE_DRIVING)) {
                    Toasts.show(this.f7601d, this.f7601d.getString(C1373R.string.str_navigation_change_to_driving_modes_decs));
                }
                this.f7601d.m9116a(c1668c, true);
                NavigationActivity.f7615d.info("NavigationRouteDTO", c1668c);
            }
        }, new Void[0]);
    }

    /* renamed from: a */
    private String m9114a(com.google.android.gms.maps.model.LatLng latLng) {
        if (latLng == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(latLng.latitude);
        stringBuilder.append(",");
        stringBuilder.append(latLng.longitude);
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public void mo3197b(int i) {
        runOnUiThread(new C16834(this));
    }
}
