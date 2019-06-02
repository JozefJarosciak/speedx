package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.C1603b;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p097b.C1620d;
import com.beastbikes.android.ble.dto.C1670e;
import com.beastbikes.android.ble.ui.p098a.C1718g;
import com.beastbikes.android.ble.ui.p098a.C1718g.C1717a;
import com.beastbikes.android.ble.ui.p098a.C1723k;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.geometry.LatLngBounds.Builder;
import com.mapbox.mapboxsdk.location.LocationServices;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMap$OnCameraChangeListener;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903163)
public class OfflineMapSelectActivity extends SessionFragmentActivity implements ServiceConnection, OnGestureListener, OnClickListener, OnTouchListener, C1620d, C1717a, MapboxMap$OnCameraChangeListener {
    /* renamed from: a */
    private static final Logger f4168a = LoggerFactory.getLogger(OfflineMapSelectActivity.class);
    @C1458a(a = 2131755786)
    /* renamed from: b */
    private Toolbar f4169b;
    @C1458a(a = 2131755849)
    /* renamed from: c */
    private MapView f4170c;
    @C1458a(a = 2131755855)
    /* renamed from: d */
    private View f4171d;
    @C1458a(a = 2131755850)
    /* renamed from: e */
    private Button f4172e;
    @C1458a(a = 2131755854)
    /* renamed from: f */
    private TextView f4173f;
    /* renamed from: g */
    private CentralService f4174g;
    /* renamed from: h */
    private C1802i f4175h;
    /* renamed from: i */
    private LocationServices f4176i;
    /* renamed from: j */
    private MapboxMap f4177j;
    /* renamed from: k */
    private float f4178k = 0.33f;
    /* renamed from: l */
    private LatLng f4179l;
    /* renamed from: m */
    private LatLng f4180m;
    /* renamed from: n */
    private LatLng f4181n;
    /* renamed from: o */
    private LatLng f4182o;
    /* renamed from: p */
    private String f4183p;
    /* renamed from: q */
    private SharedPreferences f4184q;
    /* renamed from: r */
    private JSONArray f4185r;
    /* renamed from: s */
    private boolean f4186s;
    /* renamed from: t */
    private boolean f4187t;
    /* renamed from: u */
    private double f4188u = 7.0d;
    /* renamed from: v */
    private OfflineManager f4189v;
    /* renamed from: w */
    private GestureDetector f4190w;
    /* renamed from: x */
    private boolean f4191x;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        MapboxAccountManager.start(this, BeastBikes.getMapBoxAccessToken());
        setSupportActionBar(this.f4169b);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m5503j();
        this.f4173f.setText(String.format(getString(C1373R.string.str_offline_map_choose_description), new Object[]{Double.valueOf(0.0d)}));
        this.f4184q = getSharedPreferences(m5331p(), 0);
        C1614a b = C1661h.a().b();
        if (b != null) {
            this.f4183p = b.a();
        }
        if (TextUtils.isEmpty(this.f4183p)) {
            this.f4185r = new JSONArray();
        } else {
            Object string = this.f4184q.getString(this.f4183p, "");
            f4168a.info("Cached offline region json: " + string);
            if (TextUtils.isEmpty(string)) {
                this.f4185r = new JSONArray();
            } else {
                try {
                    this.f4185r = new JSONArray(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.f4185r = new JSONArray();
                }
            }
        }
        this.f4176i = LocationServices.getLocationServices(this);
        this.f4176i.toggleGPS(true);
        this.f4190w = new GestureDetector(this, this);
        m5490a(bundle);
        m5500g();
    }

    protected void onResume() {
        super.onResume();
        this.f4170c.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.f4170c.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
        this.f4170c.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_select_offline_area_sync:
                new C1718g(this, this).show();
                return;
            default:
                return;
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f4174g = ((C1596c) iBinder).a();
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        LatLngBounds latLngBounds = this.f4177j.getProjection().getVisibleRegion().latLngBounds;
        float[] fArr = new float[1];
        Location.distanceBetween(latLngBounds.getLatSouth(), latLngBounds.getLonEast(), latLngBounds.getLatSouth(), latLngBounds.getLonWest(), fArr);
        float f = (fArr[0] / 1000.0f) * this.f4178k;
        float pow = (float) Math.pow(10.0d, (double) ((float) Math.floor(Math.log10((double) f))));
        f = (float) ((int) (((double) ((f / pow) / 2.0f)) + 0.5d));
        if (f > 5.0f) {
            f = 10.0f;
        } else if (f > 2.0f) {
            f = 5.0f;
        } else if (f > 1.0f) {
            f = 2.0f;
        }
        f *= pow;
        if (((double) f) >= 20.0d && !this.f4186s) {
            this.f4186s = true;
            double d = this.f4177j.getCameraPosition().zoom;
            f4168a.info("Set min zoom: " + d);
            this.f4177j.setMinZoom(d);
            this.f4188u = d;
        }
        if (((double) f) <= 2.0d && !this.f4187t) {
            this.f4187t = true;
            double d2 = this.f4177j.getCameraPosition().zoom;
            f4168a.info("Set max zoom: " + d2);
            this.f4177j.setMaxZoom(d2);
        }
    }

    /* renamed from: a */
    public void m5504a() {
        if (this.f4175h != null) {
            this.f4175h.dismiss();
        }
        C1614a b = C1661h.a().b();
        if (b != null && this.f4179l != null && this.f4180m != null && this.f4181n != null && this.f4182o != null) {
            String a = b.a();
            Editor edit = this.f4184q.edit();
            try {
                this.f4185r.put(new C1670e(this.f4179l, this.f4180m, this.f4181n, this.f4182o).e());
                edit.putString(a, this.f4185r.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            edit.apply();
            PolygonOptions polygonOptions = new PolygonOptions();
            polygonOptions.add(new LatLng[]{this.f4179l, this.f4180m, this.f4182o, this.f4181n});
            this.f4177j.addPolygon(polygonOptions);
        }
    }

    /* renamed from: b */
    public void m5505b() {
        if (this.f4175h != null) {
            this.f4175h.dismiss();
        }
        new C1723k(this).show();
    }

    /* renamed from: c */
    public void m5506c() {
        m5502i();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && this.f4191x) {
            this.f4191x = false;
            m5501h();
        }
        return this.f4190w.onTouchEvent(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f4191x = true;
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    /* renamed from: a */
    private void m5490a(Bundle bundle) {
        this.f4170c.onCreate(bundle);
        this.f4170c.getMapAsync(new OfflineMapSelectActivity$1(this));
    }

    /* renamed from: e */
    private void m5495e() {
        Location lastLocation = this.f4176i.getLastLocation();
        if (lastLocation != null) {
            this.f4177j.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation), 11.0f));
        }
        this.f4176i.addLocationListener(new OfflineMapSelectActivity$2(this));
    }

    /* renamed from: f */
    private void m5498f() {
        int length = this.f4185r.length();
        f4168a.info("Cache region size: " + length);
        if (length > 0) {
            List arrayList = new ArrayList();
            for (int i = 0; i < length; i++) {
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.fillColor(-872415232);
                C1670e c1670e = new C1670e(this.f4185r.optJSONObject(i));
                polygonOptions.add(c1670e.a());
                polygonOptions.add(c1670e.b());
                polygonOptions.add(c1670e.d());
                polygonOptions.add(c1670e.c());
                arrayList.add(polygonOptions);
            }
            this.f4177j.addPolygons(arrayList);
        }
    }

    /* renamed from: g */
    private void m5500g() {
        this.f4172e.setOnClickListener(this);
    }

    /* renamed from: h */
    private void m5501h() {
        if (this.f4189v == null) {
            this.f4189v = OfflineManager.getInstance(this);
        }
        int top = this.f4171d.getTop();
        int right = this.f4171d.getRight();
        int left = this.f4171d.getLeft();
        int bottom = this.f4171d.getBottom();
        this.f4179l = m5487a(left, top);
        this.f4180m = m5487a(right, top);
        this.f4181n = m5487a(left, bottom);
        this.f4182o = m5487a(right, bottom);
        if (this.f4180m != null && this.f4181n != null) {
            this.f4189v.createOfflineRegion(new OfflineTilePyramidRegionDefinition(this.f4177j.getStyleUrl(), new Builder().include(this.f4180m).include(this.f4181n).build(), this.f4188u, 20.0d, getResources().getDisplayMetrics().density), null, new OfflineMapSelectActivity$3(this));
        }
    }

    /* renamed from: i */
    private void m5502i() {
        if (this.f4174g == null) {
            f4168a.error("getSelectedArea(), CentralService is null");
            new C1723k(this).show();
            return;
        }
        C1603b c = this.f4174g.c();
        if (c == null) {
            f4168a.error("getSelectedArea(), S605Invocation is null");
            new C1723k(this).show();
            return;
        }
        c.a(this);
        c.a(this.f4180m, this.f4181n);
        if (this.f4175h == null) {
            this.f4175h = new C1802i(this, C1373R.string.str_ble_syncing, true);
        }
        this.f4175h.show();
    }

    /* renamed from: a */
    private LatLng m5487a(int i, int i2) {
        return this.f4177j.getProjection().fromScreenLocation(new PointF((float) i, (float) i2));
    }

    /* renamed from: j */
    private void m5503j() {
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        bindService(intent, this, 1);
    }
}
