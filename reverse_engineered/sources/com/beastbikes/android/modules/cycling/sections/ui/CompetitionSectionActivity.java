package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.p104a.C1823a;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.ui.RouteMapSearchGeoActivity;
import com.beastbikes.android.modules.cycling.sections.dto.C2222c;
import com.beastbikes.android.modules.cycling.sections.p069a.C2219a;
import com.beastbikes.android.modules.cycling.sections.ui.widget.CustomEditText;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@C1459b(a = 2130903103)
public class CompetitionSectionActivity extends SessionFragmentActivity implements OnClickListener, OnEditorActionListener, BDLocationListener, C1371a, C1823a {
    /* renamed from: A */
    private boolean f5629A = true;
    /* renamed from: B */
    private Timer f5630B;
    /* renamed from: C */
    private C1802i f5631C;
    /* renamed from: a */
    TextWatcher f5632a = new CompetitionSectionActivity$6(this);
    /* renamed from: b */
    private Toolbar f5633b;
    @C1458a(a = 2131755558)
    /* renamed from: c */
    private CustomEditText f5634c;
    @C1458a(a = 2131755559)
    /* renamed from: d */
    private ImageView f5635d;
    @C1458a(a = 2131755561)
    /* renamed from: e */
    private ImageView f5636e;
    @C1458a(a = 2131755560)
    /* renamed from: f */
    private ImageView f5637f;
    @C1458a(a = 2131756607)
    /* renamed from: g */
    private ImageView f5638g;
    /* renamed from: h */
    private FragmentManager f5639h;
    /* renamed from: i */
    private List<C2222c> f5640i = new ArrayList();
    /* renamed from: j */
    private int f5641j = 1;
    /* renamed from: k */
    private int f5642k = 1;
    /* renamed from: l */
    private int f5643l = 2;
    /* renamed from: m */
    private PoiInfoDTO f5644m;
    /* renamed from: n */
    private SectionBaseFragment f5645n;
    /* renamed from: o */
    private C2219a f5646o;
    /* renamed from: p */
    private double f5647p;
    /* renamed from: q */
    private double f5648q;
    /* renamed from: r */
    private boolean f5649r;
    /* renamed from: s */
    private String f5650s = "";
    /* renamed from: t */
    private String f5651t = "";
    /* renamed from: u */
    private String f5652u = "";
    /* renamed from: v */
    private String f5653v = "";
    @C1458a(a = 2131755563)
    /* renamed from: w */
    private RelativeLayout f5654w;
    @C1458a(a = 2131756605)
    /* renamed from: x */
    private MapView f5655x;
    /* renamed from: y */
    private C2231c f5656y;
    /* renamed from: z */
    private LocationClient f5657z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f5633b = (Toolbar) findViewById(C1373R.id.activity_competition_section_toolbar);
        this.f5633b.setNavigationOnClickListener(new CompetitionSectionActivity$1(this));
        this.f5629A = C1849a.a();
        this.f5634c.setOnEditorActionListener(this);
        this.f5634c.addTextChangedListener(this.f5632a);
        this.f5635d.setOnClickListener(this);
        this.f5636e.setOnClickListener(this);
        this.f5637f.setOnClickListener(this);
        this.f5638g.setOnClickListener(this);
        this.f5634c.setDrawableClickListener(new CompetitionSectionActivity$2(this));
        this.f5634c.setOnFocusChangeListener(new CompetitionSectionActivity$3(this));
        this.f5639h = getSupportFragmentManager();
        this.f5645n = new SectionListFragment();
        this.f5639h.beginTransaction().add((int) C1373R.id.activity_competition_section_content, this.f5645n).commitAllowingStateLoss();
        this.f5656y = new C2231c(this);
        this.f5641j = this.f5642k;
        m6903a();
        if (this.f5629A) {
            m6906b();
        }
    }

    /* renamed from: a */
    private void m6903a() {
        SharedPreferences sharedPreferences = getSharedPreferences(C1848b.a().getClass().getName(), 0);
        this.f5647p = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
        this.f5648q = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
        if (this.f5647p == 0.0d && this.f5648q == 0.0d) {
            this.f5645n.a(getResources().getString(C1373R.string.str_locating_failed));
            return;
        }
        this.f5646o = new C2219a(this);
        this.f5640i.clear();
        m6904a(this.f5648q, this.f5647p, 300.0f, this.f5650s, this.f5651t, this.f5652u, this.f5653v, "");
    }

    public void onResume() {
        super.onResume();
        if (this.f5655x != null) {
            this.f5655x.onResume();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f5655x != null) {
            this.f5655x.onPause();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f5657z != null) {
            this.f5657z.unRegisterLocationListener(this);
            this.f5657z.stop();
        }
        if (this.f5655x.getMap() != null) {
            this.f5655x.getMap().setMyLocationEnabled(false);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_competition_section_search:
                RouteMapSearchGeoActivity.f5519a = false;
                startActivityForResult(new Intent(this, RouteMapSearchGeoActivity.class), 80);
                return;
            case C1373R.id.activity_competition_section_switch:
                this.f5637f.setEnabled(false);
                if (this.f5641j == this.f5642k) {
                    m6913a(this.f5643l);
                    this.f5641j = this.f5643l;
                    return;
                }
                m6913a(this.f5642k);
                this.f5641j = this.f5642k;
                return;
            case C1373R.id.activity_competition_section_filter:
                startActivityForResult(new Intent(this, SectionFiltersActivity.class), 99);
                return;
            case C1373R.id.section_activity_location:
                this.f5638g.setEnabled(false);
                C1848b.a().a(this, this);
                this.f5630B = new Timer();
                this.f5630B.schedule(new CompetitionSectionActivity$4(this), AbstractComponentTracker.LINGERING_TIMEOUT);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m6914a(Location location) {
        if (location != null) {
            if (this.f5630B != null) {
                this.f5630B.cancel();
            }
            this.f5638g.setEnabled(true);
            this.f5651t = "0,100";
            this.f5656y.a(location);
            m6903a();
        }
    }

    public void e_() {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 80:
                    this.f5644m = (PoiInfoDTO) intent.getSerializableExtra("poiinfo");
                    this.f5648q = this.f5644m.getLongitude();
                    this.f5647p = this.f5644m.getLatitude();
                    this.f5650s = "";
                    this.f5651t = "";
                    this.f5652u = "";
                    this.f5653v = "";
                    m6904a(this.f5648q, this.f5647p, 300.0f, this.f5650s, this.f5651t, this.f5652u, this.f5653v, "");
                    return;
                case 99:
                    this.f5649r = true;
                    this.f5650s = intent.getStringExtra("section_difficult");
                    this.f5651t = intent.getStringExtra("section_distance");
                    this.f5652u = intent.getStringExtra("section_altdiff");
                    this.f5653v = intent.getStringExtra("section_slope");
                    m6904a(this.f5648q, this.f5647p, 300.0f, this.f5650s, this.f5651t, this.f5652u, this.f5653v, "");
                    return;
                default:
                    return;
            }
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 4 && (keyEvent == null || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        Toasts.showOnUiThread(this, this.f5634c.getText().toString());
        return true;
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: b */
    private void m6906b() {
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setPriority(1);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(5000);
        locationClientOption.setAddrType("all");
        this.f5657z = new LocationClient(this);
        this.f5657z.registerLocationListener(this);
        this.f5657z.setLocOption(locationClientOption);
        this.f5657z.start();
        this.f5657z.requestLocation();
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if (this.f5656y != null) {
            this.f5656y.a(bDLocation);
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    /* renamed from: a */
    private void m6904a(double d, double d2, float f, String str, String str2, String str3, String str4, String str5) {
        getAsyncTaskQueue().a(new CompetitionSectionActivity$5(this, d, d2, f, str, str2, str3, str4, str5), new Void[0]);
    }

    /* renamed from: a */
    public void m6913a(int i) {
        FragmentTransaction beginTransaction = this.f5639h.beginTransaction();
        switch (i) {
            case 1:
                this.f5637f.setImageResource(C1373R.drawable.ic_section_location);
                if (this.f5645n != null) {
                    beginTransaction.show(this.f5645n);
                } else {
                    this.f5645n = new SectionListFragment();
                    beginTransaction.add((int) C1373R.id.activity_competition_section_content, this.f5645n);
                }
                this.f5654w.setVisibility(8);
                break;
            case 2:
                this.f5637f.setImageResource(C1373R.drawable.ic_section_search_list);
                this.f5654w.setVisibility(0);
                if (this.f5645n != null) {
                    beginTransaction.hide(this.f5645n);
                    break;
                }
                break;
        }
        beginTransaction.setTransition(8194).commitAllowingStateLoss();
        this.f5637f.setEnabled(true);
    }
}
