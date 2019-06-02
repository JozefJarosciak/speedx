package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.p111a.C1892g;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.business.BusinessException;
import com.mapbox.mapboxsdk.style.layers.Property;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "查看精品路线地图详情")
@C1459b(a = 2130903660)
public class RouteMapActivity extends SessionFragmentActivity implements SensorEventListener, OnClickListener, BDLocationListener {
    /* renamed from: a */
    private static final Logger f5491a = LoggerFactory.getLogger(RouteMapActivity.class);
    @C1458a(a = 2131757500)
    /* renamed from: b */
    private MapView f5492b;
    @C1458a(a = 2131757501)
    /* renamed from: c */
    private ImageView f5493c;
    @C1458a(a = 2131757504)
    /* renamed from: d */
    private ImageView f5494d;
    @C1458a(a = 2131757505)
    /* renamed from: e */
    private ImageView f5495e;
    @C1458a(a = 2131757502)
    /* renamed from: f */
    private ImageView f5496f;
    /* renamed from: g */
    private BaiduMap f5497g;
    /* renamed from: h */
    private LocationClient f5498h;
    /* renamed from: i */
    private float f5499i = 16.0f;
    /* renamed from: j */
    private final float[] f5500j = new float[3];
    /* renamed from: k */
    private final float[] f5501k = new float[3];
    /* renamed from: l */
    private float f5502l = 0.0f;
    /* renamed from: m */
    private Sensor f5503m;
    /* renamed from: n */
    private Sensor f5504n;
    /* renamed from: o */
    private SensorManager f5505o;
    /* renamed from: p */
    private boolean f5506p = true;
    /* renamed from: q */
    private C2185a f5507q;
    /* renamed from: r */
    private RouteMapActivity$a f5508r;
    /* renamed from: s */
    private int f5509s = 5;
    /* renamed from: t */
    private List<LatLng> f5510t = new ArrayList();
    /* renamed from: u */
    private boolean f5511u = false;
    /* renamed from: v */
    private double f5512v;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5505o = (SensorManager) getSystemService("sensor");
        this.f5503m = this.f5505o.getDefaultSensor(1);
        this.f5504n = this.f5505o.getDefaultSensor(2);
        this.f5505o.registerListener(this, this.f5503m, 2);
        this.f5505o.registerListener(this, this.f5504n, 2);
        this.f5508r = new RouteMapActivity$a(this, null);
        this.f5507q = new C2185a(this);
        this.f5498h = new LocationClient(this);
        this.f5498h.registerLocationListener(this);
        this.f5492b.showZoomControls(false);
        this.f5492b.getChildAt(1).setVisibility(8);
        this.f5492b.getChildAt(3).setVisibility(8);
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setPriority(1);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(5000);
        locationClientOption.setAddrType("all");
        this.f5497g = this.f5492b.getMap();
        this.f5497g.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.FOLLOWING, true, null));
        this.f5497g.setMyLocationEnabled(true);
        this.f5497g.animateMapStatus(MapStatusUpdateFactory.zoomTo(this.f5499i));
        this.f5493c.setOnClickListener(this);
        this.f5495e.setOnClickListener(this);
        this.f5494d.setOnClickListener(this);
        this.f5496f.setOnClickListener(this);
        this.f5496f.setVisibility(8);
        this.f5498h.setLocOption(locationClientOption);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("route_id");
            this.f5512v = intent.getDoubleExtra("route_distance", 0.0d);
            if (!TextUtils.isEmpty(stringExtra)) {
                m6804a(stringExtra);
            }
            String stringExtra2 = intent.getStringExtra(Property.SYMBOL_PLACEMENT_POINT);
            if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.length() > 2) {
                String[] split = stringExtra2.substring(1, stringExtra2.length() - 2).split(",");
                if (split != null && split.length == 2) {
                    LatLng latLng = new LatLng(Double.valueOf(split[0]).doubleValue(), Double.valueOf(split[1]).doubleValue());
                    this.f5497g.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
                    if (this.f5498h.isStarted()) {
                        this.f5498h.stop();
                    }
                    this.f5497g.clear();
                    this.f5497g.addOverlay(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.ic_muster_icon)));
                }
            }
            C2580w.a(this, "查看精品路线地图", null);
        }
    }

    protected void onResume() {
        this.f5492b.onResume();
        super.onResume();
    }

    protected void onPause() {
        this.f5492b.onPause();
        super.onPause();
    }

    protected void onDestroy() {
        if (this.f5498h.isStarted()) {
            this.f5498h.stop();
        }
        this.f5497g.setMyLocationEnabled(false);
        this.f5498h.unRegisterLocationListener(this);
        super.onDestroy();
    }

    public void onClick(View view) {
        float min;
        switch (view.getId()) {
            case C1373R.id.route_map_activity_map_button_location:
                this.f5506p = true;
                this.f5498h.requestLocation();
                this.f5498h.start();
                return;
            case C1373R.id.route_map_activity_elevation:
                C2580w.a(this, "查看精品路线海拔趋势图", null);
                m6805a(this.f5510t);
                return;
            case C1373R.id.route_map_activity_map_button_zoom_out:
                min = Math.min(this.f5499i + 1.0f, this.f5497g.getMaxZoomLevel());
                this.f5497g.animateMapStatus(MapStatusUpdateFactory.zoomTo(min));
                this.f5499i = min;
                this.f5509s++;
                m6808b(this.f5510t);
                return;
            case C1373R.id.route_map_activity_map_button_zoom_in:
                min = Math.max(this.f5499i - 1.0f, this.f5497g.getMinZoomLevel());
                this.f5497g.animateMapStatus(MapStatusUpdateFactory.zoomTo(min));
                this.f5499i = min;
                this.f5509s--;
                m6808b(this.f5510t);
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if (bDLocation != null) {
            runOnUiThread(new RouteMapActivity$1(this, bDLocation));
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 1:
                System.arraycopy(sensorEvent.values, 0, this.f5500j, 0, 3);
                break;
            case 2:
                System.arraycopy(sensorEvent.values, 0, this.f5501k, 0, 3);
                break;
            default:
                return;
        }
        float[] fArr = new float[9];
        float[] fArr2 = new float[3];
        if (SensorManager.getRotationMatrix(fArr, new float[9], this.f5500j, this.f5501k)) {
            SensorManager.getOrientation(fArr, fArr2);
            for (int i = 0; i < fArr2.length; i++) {
                fArr2[i] = (float) Math.toDegrees((double) fArr2[i]);
            }
            this.f5502l = fArr2[0];
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* renamed from: a */
    private void m6804a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new RouteMapActivity$2(this), new String[]{str});
        }
    }

    /* renamed from: a */
    private void m6805a(List<LatLng> list) {
        if (list != null && list.size() >= 1) {
            f5491a.trace("big map activity source sample line size = " + list.size());
            float f = 1.0E-4f;
            while (list.size() > 80) {
                try {
                    list = C1892g.a(f, list);
                    f += 3.0E-4f;
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
            }
            f5491a.trace("big map activity source sample line size = " + list.size());
            StringBuilder stringBuilder = new StringBuilder();
            for (LatLng latLng : list) {
                stringBuilder.append(latLng.latitude + "," + latLng.longitude + "|");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            Intent intent = new Intent(this, RouteElevationActivity.class);
            intent.putExtra("nodes", stringBuilder.toString());
            intent.putExtra("distance", this.f5512v / 1000.0d);
            startActivity(intent);
        }
    }

    /* renamed from: b */
    private void m6808b(List<LatLng> list) {
        if (list != null && list.size() >= 2 && this.f5497g != null) {
            List a;
            this.f5497g.clear();
            this.f5497g.addOverlay(new MarkerOptions().position((LatLng) list.get(0)).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.route_map_line_start_icon)));
            this.f5497g.addOverlay(new MarkerOptions().position((LatLng) list.get(list.size() - 1)).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.route_map_line_end_icon)));
            f5491a.trace("big route map source nodes line size = " + list.size());
            try {
                if (this.f5509s < 9) {
                    a = C1892g.a(this.f5509s, true, list);
                }
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            f5491a.trace("big route map source nodes line size = " + a.size());
            while (a.size() > 10000) {
                try {
                    a = C1892g.a(a);
                } catch (BusinessException e2) {
                    e2.printStackTrace();
                }
            }
            int size = a.size();
            if (size > 2 && size < 10000) {
                this.f5497g.addOverlay(new PolylineOptions().width(6).color(-1426128896).points(a));
            }
            if (!this.f5511u) {
                m6810c(a);
                this.f5511u = true;
            }
        }
    }

    /* renamed from: c */
    private void m6810c(List<LatLng> list) {
        if (list != null && !list.isEmpty()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            Builder builder = new Builder();
            for (LatLng include : list) {
                builder.include(include);
            }
            int width = this.f5492b.getWidth();
            int height = this.f5492b.getHeight();
            if (width > 0) {
                i = width;
            }
            if (height <= 0) {
                height = displayMetrics.heightPixels - 100;
            }
            this.f5497g.setMapStatus(MapStatusUpdateFactory.newLatLngBounds(builder.build(), i, height));
            this.f5499i = this.f5497g.getMapStatus().zoom;
        }
    }
}
