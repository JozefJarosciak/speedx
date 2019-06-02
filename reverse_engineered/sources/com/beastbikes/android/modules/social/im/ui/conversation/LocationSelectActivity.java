package com.beastbikes.android.modules.social.im.ui.conversation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.C1821c;
import com.beastbikes.android.locale.googlemaputils.C1855a;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.ui.RouteMapSearchGeoActivity;
import com.beastbikes.android.modules.map.MapType;
import com.beastbikes.android.modules.map.SpeedxMap;
import com.beastbikes.android.modules.map.SpeedxMap.C1685b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.Serializable;

@C1459b(a = 2130903502)
public class LocationSelectActivity extends SessionFragmentActivity implements OnClickListener, C1821c, C1685b {
    @C1458a(a = 2131755569)
    /* renamed from: a */
    private SpeedxMap f6184a;
    @C1458a(a = 2131757147)
    /* renamed from: b */
    private ImageView f6185b;
    @C1458a(a = 2131755572)
    /* renamed from: c */
    private ImageView f6186c;
    @C1458a(a = 2131755573)
    /* renamed from: d */
    private ImageView f6187d;
    @C1458a(a = 2131757144)
    /* renamed from: e */
    private RelativeLayout f6188e;
    @C1458a(a = 2131757149)
    /* renamed from: f */
    private RelativeLayout f6189f;
    @C1458a(a = 2131757148)
    /* renamed from: g */
    private LinearLayout f6190g;
    @C1458a(a = 2131757145)
    /* renamed from: h */
    private TextView f6191h;
    /* renamed from: i */
    private C1855a f6192i;
    /* renamed from: j */
    private boolean f6193j = true;
    /* renamed from: k */
    private float f6194k = 16.0f;
    /* renamed from: l */
    private boolean f6195l = true;
    /* renamed from: m */
    private LatLng f6196m;
    /* renamed from: n */
    private PoiInfoDTO f6197n;
    /* renamed from: o */
    private AlphaAnimation f6198o;
    /* renamed from: p */
    private TranslateAnimation f6199p;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f6192i = new C1855a();
        this.f6184a.a(C1849a.a() ? MapType.BaiDu : MapType.Google, this);
        this.f6184a.g();
        this.f6184a.k();
        this.f6184a.setOnMapStatusChangeListener(this);
        this.f6198o = new AlphaAnimation(0.0f, 1.0f);
        this.f6198o.setDuration(500);
        this.f6199p = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -0.5f);
        this.f6199p.setRepeatCount(1);
        this.f6199p.setDuration(300);
        this.f6199p.setRepeatMode(2);
        this.f6199p.setAnimationListener(new LocationSelectActivity$1(this));
        this.f6185b.setOnClickListener(this);
        this.f6187d.setOnClickListener(this);
        this.f6186c.setOnClickListener(this);
        this.f6191h.setOnClickListener(this);
        this.f6189f.setOnClickListener(this);
        this.f6190g.setOnClickListener(this);
        m7367a();
    }

    protected void onResume() {
        this.f6184a.c();
        super.onResume();
    }

    protected void onPause() {
        this.f6184a.d();
        super.onPause();
    }

    protected void onDestroy() {
        this.f6184a.e();
        super.onDestroy();
    }

    /* renamed from: a */
    private void m7367a() {
        this.f6193j = true;
        this.f6184a.i();
    }

    public void onClick(View view) {
        float max;
        switch (view.getId()) {
            case C1373R.id.localtion_selsect_activity_button_zoom_out:
                max = Math.max(this.f6194k + 1.0f, this.f6184a.getZoomLevel());
                this.f6184a.a(max);
                this.f6194k = max;
                return;
            case C1373R.id.localtion_selsect_activity_button_zoom_in:
                max = Math.max(this.f6194k - 1.0f, this.f6184a.getZoomLevel());
                this.f6184a.a(max);
                this.f6194k = max;
                return;
            case C1373R.id.route_map_make_select_start_point:
                if (this.f6197n != null) {
                    Intent intent = getIntent();
                    intent.putExtra("extra_lat", this.f6197n.getLatitude());
                    intent.putExtra("extra_lng", this.f6197n.getLongitude());
                    intent.putExtra("extra_addr", this.f6197n.getAddress());
                    intent.putExtra("city_name", this.f6197n.getCity());
                    intent.putExtra("province_name", this.f6197n.getProvince());
                    intent.putExtra("area_name", this.f6197n.getArea());
                    setResult(-1, intent);
                    finish();
                    return;
                }
                Toasts.show(this, C1373R.string.route_map_make_activity_select_err);
                return;
            case C1373R.id.localtion_selsect_activity_button_location:
                m7367a();
                return;
            case C1373R.id.location_route_map_make_search:
                RouteMapSearchGeoActivity.f5519a = false;
                startActivityForResult(new Intent(this, RouteMapSearchGeoActivity.class), 80);
                return;
            case C1373R.id.location_route_map_make_back:
                finish();
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    public void m7380b(double d, double d2) {
        if ((d > 0.0d || d2 > 0.0d) && d != Double.MIN_VALUE && d2 != Double.MIN_VALUE) {
            runOnUiThread(new LocationSelectActivity$2(this, d, d2));
        }
    }

    /* renamed from: a */
    public void m7379a(String str, String str2) {
    }

    /* renamed from: a */
    public void m7376a(double d, double d2) {
        runOnUiThread(new LocationSelectActivity$3(this));
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 80:
                    if (intent != null) {
                        Serializable serializableExtra = intent.getSerializableExtra("poiinfo");
                        if (serializableExtra != null) {
                            this.f6197n = (PoiInfoDTO) serializableExtra;
                            this.f6184a.a(this.f6197n.getLatitude(), this.f6197n.getLongitude());
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m7378a(C1856b c1856b) {
        if (this.f6197n != null && c1856b != null) {
            this.f6197n.setCity(c1856b.d());
            this.f6197n.setProvince(c1856b.c());
            if (!TextUtils.isEmpty(c1856b.a()) && c1856b.a().contains(",")) {
                String[] split = c1856b.a().split(",");
                if (split != null && split.length > 1) {
                    this.f6197n.setArea(split[1]);
                }
            }
            if (!TextUtils.isEmpty(c1856b.a())) {
                if (c1856b.a().contains("Unnamed Road, ")) {
                    this.f6197n.setAddress(c1856b.a().substring(0, 14));
                } else {
                    this.f6197n.setAddress(c1856b.a());
                }
            }
            if (!TextUtils.isEmpty(c1856b.b())) {
                if (c1856b.b().contains("Unnamed Road, ")) {
                    String b = c1856b.b();
                    this.f6197n.setAddress(b.substring(14, b.length()));
                    return;
                }
                this.f6197n.setAddress(c1856b.b());
            }
        }
    }

    /* renamed from: a */
    public void m7377a(VolleyError volleyError) {
    }
}
