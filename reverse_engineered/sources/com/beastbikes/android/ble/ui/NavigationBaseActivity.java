package com.beastbikes.android.ble.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.map.C2296d.C1686a;
import com.beastbikes.android.modules.map.MapType;
import com.beastbikes.android.modules.map.SpeedxMap;
import com.beastbikes.android.modules.map.SpeedxMap.C1685b;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NavigationBaseActivity extends SessionFragmentActivity implements OnClickListener, BDLocationListener, C1371a, C1623g, C1685b, C1686a {
    /* renamed from: d */
    private static final Logger f7604d = LoggerFactory.getLogger("NavigationBaseActivity");
    /* renamed from: a */
    protected SpeedxMap f7605a;
    /* renamed from: b */
    protected NavigationLocation f7606b;
    /* renamed from: c */
    protected C1602a f7607c;
    /* renamed from: e */
    private Toolbar f7608e;
    /* renamed from: f */
    private Button f7609f;
    /* renamed from: g */
    private ImageView f7610g;
    /* renamed from: h */
    private ImageView f7611h;
    /* renamed from: i */
    private TextView f7612i;
    /* renamed from: j */
    private C1802i f7613j;
    /* renamed from: k */
    private boolean f7614k;

    /* renamed from: com.beastbikes.android.ble.ui.NavigationBaseActivity$1 */
    class C16881 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ NavigationBaseActivity f7635a;

        C16881(NavigationBaseActivity navigationBaseActivity) {
            this.f7635a = navigationBaseActivity;
        }

        public void run() {
            Toasts.show(this.f7635a, this.f7635a.getString(C1373R.string.str_sync_success));
            this.f7635a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.activity_navigation_base);
        MapboxAccountManager.start(getApplicationContext(), BeastBikes.getMapBoxAccessToken());
        mo3201a(bundle);
    }

    public void onResume() {
        super.onResume();
        this.f7605a.m11675c();
    }

    public void onPause() {
        super.onPause();
        this.f7605a.m11676d();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f7605a.m11673b(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f7605a.m11678f();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    protected void onDestroy() {
        this.f7605a.m11677e();
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_move_to_current_location:
                m9102a(true);
                return;
            case C1373R.id.img_navigation_zoom_in:
                m9098b(true);
                return;
            case C1373R.id.img_navigation_zoom_out:
                m9098b(false);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void mo3195a(int i) {
    }

    /* renamed from: b */
    public void mo3197b(int i) {
        runOnUiThread(new C16881(this));
    }

    /* renamed from: a */
    protected void mo3201a(Bundle bundle) {
        this.f7608e = (Toolbar) findViewById(C1373R.id.toolbar);
        setSupportActionBar(this.f7608e);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(C1373R.drawable.ic_navigation_back);
        }
        this.f7605a = (SpeedxMap) findViewById(C1373R.id.mapView_navigation);
        this.f7609f = (Button) findViewById(C1373R.id.btn_move_to_current_location);
        this.f7611h = (ImageView) findViewById(C1373R.id.img_navigation_zoom_in);
        this.f7610g = (ImageView) findViewById(C1373R.id.img_navigation_zoom_out);
        this.f7612i = (TextView) findViewById(C1373R.id.textView_navigation_scale);
        m9105b(bundle);
        mo3199a();
        m9102a(false);
    }

    /* renamed from: b */
    protected void m9105b(Bundle bundle) {
        MapType mapType;
        if (C1849a.m9641a()) {
            mapType = MapType.BaiDu;
        } else {
            mapType = MapType.Google;
        }
        this.f7605a.m11662a(mapType, (Activity) this, (C1686a) this);
        this.f7605a.m11660a(null);
    }

    /* renamed from: a */
    private void mo3199a() {
        this.f7609f.setOnClickListener(this);
        this.f7611h.setOnClickListener(this);
        this.f7610g.setOnClickListener(this);
    }

    /* renamed from: b */
    private void m9098b(boolean z) {
        if (z) {
            this.f7605a.m11659a(Math.min(this.f7605a.getZoomLevel() + 1.0f, this.f7605a.getMaxZoomLevel()));
            return;
        }
        this.f7605a.m11659a(Math.max(this.f7605a.getZoomLevel() - 1.0f, this.f7605a.getMinZoomLevel()));
    }

    /* renamed from: a */
    public void m9102a(boolean z) {
        this.f7614k = z;
        this.f7605a.m11681i();
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        this.f7605a.m11682j();
        LatLng b = C2558g.m12845b(new com.baidu.mapapi.model.LatLng(bDLocation.getLatitude(), bDLocation.getLongitude()));
        this.f7606b = new NavigationLocation();
        this.f7606b.setLatitude(b.getLatitude());
        this.f7606b.setLongitude(b.getLongitude());
        this.f7606b.setName(getResources().getString(C1373R.string.str_navigation_my_location));
        if (!this.f7614k) {
            a_(this.f7606b);
        }
    }

    /* renamed from: a */
    public void mo3196a(String str, String str2) {
        this.f7612i.setText(String.format(Locale.US, "%s%s", new Object[]{str, str2}));
    }

    protected void a_(NavigationLocation navigationLocation) {
    }

    /* renamed from: c */
    public void mo3198c(double d, double d2) {
        this.f7605a.m11682j();
        this.f7606b = new NavigationLocation();
        this.f7606b.setLatitude(d);
        this.f7606b.setLongitude(d2);
        this.f7606b.setName(getResources().getString(C1373R.string.str_navigation_my_location));
        if (!this.f7614k) {
            a_(this.f7606b);
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    /* renamed from: a */
    protected void m9103a(boolean z, String str) {
        if (getWindow() != null && !isFinishing()) {
            if (this.f7613j == null) {
                this.f7613j = new C1802i((Context) this, str, z);
            }
            if (!this.f7613j.isShowing()) {
                this.f7613j.show();
            }
        }
    }

    /* renamed from: c */
    protected void m9106c() {
        if (this.f7613j != null && !isFinishing() && getWindow() != null) {
            if (this.f7613j.isShowing()) {
                this.f7613j.dismiss();
            }
            this.f7613j = null;
        }
    }
}
