package com.beastbikes.android.modules.social.im.ui.conversation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903106)
public class ConversationMapView extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755569)
    /* renamed from: a */
    private MapView f6160a;
    @C1458a(a = 2131755572)
    /* renamed from: b */
    private ImageView f6161b;
    @C1458a(a = 2131755573)
    /* renamed from: c */
    private ImageView f6162c;
    @C1458a(a = 2131755576)
    /* renamed from: d */
    private TextView f6163d;
    /* renamed from: e */
    private BaiduMap f6164e;
    /* renamed from: f */
    private double f6165f;
    /* renamed from: g */
    private double f6166g;
    /* renamed from: h */
    private String f6167h;
    /* renamed from: i */
    private float f6168i = 16.0f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6164e = this.f6160a.getMap();
        this.f6160a.showZoomControls(false);
        this.f6164e.getUiSettings().setCompassEnabled(false);
        this.f6160a.getChildAt(1).setVisibility(8);
        this.f6160a.getChildAt(3).setVisibility(8);
        this.f6162c.setOnClickListener(this);
        this.f6161b.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.f6165f = intent.getDoubleExtra("LATLNGLATTAG", 0.0d);
            this.f6166g = intent.getDoubleExtra("LATLNGLONTAG", 0.0d);
            CoordinateConverter coordinateConverter = new CoordinateConverter();
            coordinateConverter.from(CoordType.COMMON);
            coordinateConverter.coord(new LatLng(this.f6165f, this.f6166g));
            LatLng convert = coordinateConverter.convert();
            this.f6165f = convert.latitude;
            this.f6166g = convert.longitude;
            this.f6167h = intent.getStringExtra("LATLNGADDRESS");
        }
        if (!(this.f6165f == 0.0d || this.f6166g == 0.0d)) {
            m7353a();
        }
        if (!TextUtils.isEmpty(this.f6167h)) {
            this.f6163d.setText(this.f6167h);
        }
    }

    protected void onResume() {
        this.f6160a.onResume();
        super.onResume();
    }

    protected void onPause() {
        this.f6160a.onPause();
        super.onPause();
    }

    protected void onDestroy() {
        this.f6164e.clear();
        System.gc();
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m7353a() {
        LatLng latLng = new LatLng(this.f6165f, this.f6166g);
        this.f6164e.addOverlay(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(C1373R.drawable.route_map_make_start_icon)));
        this.f6164e.animateMapStatus(MapStatusUpdateFactory.zoomTo(this.f6168i));
        this.f6164e.animateMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(this.f6165f, this.f6166g)));
    }

    public void onClick(View view) {
        float min;
        switch (view.getId()) {
            case C1373R.id.localtion_selsect_activity_button_zoom_out:
                min = Math.min(this.f6168i + 1.0f, this.f6164e.getMaxZoomLevel());
                this.f6164e.animateMapStatus(MapStatusUpdateFactory.zoomTo(min));
                this.f6168i = min;
                return;
            case C1373R.id.localtion_selsect_activity_button_zoom_in:
                min = Math.max(this.f6168i - 1.0f, this.f6164e.getMinZoomLevel());
                this.f6164e.animateMapStatus(MapStatusUpdateFactory.zoomTo(min));
                this.f6168i = min;
                return;
            default:
                return;
        }
    }
}
