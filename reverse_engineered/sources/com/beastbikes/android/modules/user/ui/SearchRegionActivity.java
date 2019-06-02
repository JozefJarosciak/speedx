package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.dto.SearchRegionDTO;
import com.beastbikes.android.modules.user.p152b.C2405d;
import com.beastbikes.android.modules.user.ui.p160c.C2524a;
import com.beastbikes.android.modules.user.view.C2528e;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@C1457a(a = "地区搜索")
@C1459b(a = 2130903180)
public class SearchRegionActivity extends BaseUserInfoSettingsActivity implements LocationListener, OnClickListener, C2524a, C2528e {
    @C1458a(a = 2131755941)
    /* renamed from: d */
    private EditText f6573d;
    @C1458a(a = 2131755942)
    /* renamed from: e */
    private ImageView f6574e;
    @C1458a(a = 2131755943)
    /* renamed from: f */
    private RecyclerView f6575f;
    /* renamed from: g */
    private SearchRegionActivity$a f6576g;
    /* renamed from: h */
    private C2405d f6577h;
    /* renamed from: i */
    private LocationManager f6578i;
    /* renamed from: j */
    private Location f6579j;
    /* renamed from: k */
    private int f6580k;

    /* renamed from: a */
    public /* synthetic */ BaseUserInfoSettingsActivity mo2808a() {
        return mo2809i();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f6578i = (LocationManager) getSystemService(MapboxEvent.TYPE_LOCATION);
        this.f6580k = intent.getIntExtra("enter_type", 0);
        m7786k();
        m7787l();
        m7788m();
        this.f6577h = new C2405d(this);
    }

    public void onClick(View view) {
        this.f6573d.setText("");
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f6578i != null) {
            this.f6578i.removeUpdates(this);
            this.f6578i = null;
        }
        this.f6579j = null;
    }

    /* renamed from: a */
    public void m7791a(View view, SearchRegionDTO searchRegionDTO) {
        this.c = searchRegionDTO;
        if (this.f6580k == 0) {
            m7789n();
        } else {
            this.b.a();
        }
    }

    /* renamed from: k */
    private void m7786k() {
        this.f6575f.setLayoutManager(new LinearLayoutManager(this));
        this.f6575f.setHasFixedSize(true);
        this.f6576g = new SearchRegionActivity$a(this, this);
        this.f6575f.setAdapter(this.f6576g);
    }

    /* renamed from: l */
    private void m7787l() {
        this.f6573d.addTextChangedListener(new SearchRegionActivity$1(this));
        this.f6574e.setOnClickListener(this);
    }

    /* renamed from: m */
    private void m7788m() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setPowerRequirement(1);
        criteria.setAltitudeRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        String bestProvider = this.f6578i.getBestProvider(criteria, false);
        this.f6579j = this.f6578i.getLastKnownLocation(bestProvider);
        if (this.f6579j == null) {
            this.f6578i.requestLocationUpdates(bestProvider, 0, 0.0f, this);
        }
    }

    /* renamed from: i */
    public SearchRegionActivity mo2809i() {
        return (SearchRegionActivity) new WeakReference(this).get();
    }

    /* renamed from: d */
    public boolean m7794d() {
        return false;
    }

    /* renamed from: a */
    public void m7792a(ProfileDTO profileDTO) {
        m7789n();
    }

    /* renamed from: n */
    private void m7789n() {
        Intent intent = new Intent();
        intent.putExtra("key_region_dto", m7491e());
        setResult(-1, intent);
        finish();
    }

    /* renamed from: a */
    public void m7793a(ArrayList<SearchRegionDTO> arrayList) {
        if (this.f6576g != null) {
            this.f6576g.a(arrayList);
        }
    }

    /* renamed from: j */
    public String m7796j() {
        if (this.f6579j != null) {
            return this.f6579j.getLatitude() + "," + this.f6579j.getLongitude();
        }
        return null;
    }

    public void onLocationChanged(Location location) {
        this.f6579j = location;
        this.f6578i.removeUpdates(this);
        this.f6578i = null;
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }
}
