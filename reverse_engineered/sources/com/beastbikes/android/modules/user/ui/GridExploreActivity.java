package com.beastbikes.android.modules.user.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.grid.dto.GridDTO;
import com.beastbikes.android.modules.cycling.grid.p066a.C2170a;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.utils.C2565m;
import com.beastbikes.android.utils.C2568n;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.google.android.gms.common.Scopes;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapView.OnMapChangedListener;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMap$OnMarkerClickListener;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

@C1459b(a = 2130903387)
public class GridExploreActivity extends SessionFragmentActivity implements OnMapChangedListener, MapboxMap$OnMarkerClickListener, OnMapReadyCallback {
    @C1458a(a = 2131756707)
    /* renamed from: a */
    private RelativeLayout f6404a;
    /* renamed from: b */
    private MapView f6405b;
    /* renamed from: c */
    private MapboxMap f6406c;
    /* renamed from: d */
    private C2565m f6407d;
    @C1458a(a = 2131756708)
    /* renamed from: e */
    private CircleImageView f6408e;
    @C1458a(a = 2131756709)
    /* renamed from: f */
    private TextView f6409f;
    @C1458a(a = 2131756710)
    /* renamed from: g */
    private TextView f6410g;
    @C1458a(a = 2131756711)
    /* renamed from: h */
    private TextView f6411h;
    @C1458a(a = 2131756712)
    /* renamed from: i */
    private TextView f6412i;
    /* renamed from: j */
    private C2170a f6413j;
    /* renamed from: k */
    private List<GridDTO> f6414k = new ArrayList();
    /* renamed from: l */
    private ArrayList<MarkerOptions> f6415l = new ArrayList();
    /* renamed from: m */
    private double f6416m;
    /* renamed from: n */
    private boolean f6417n;
    /* renamed from: o */
    private boolean f6418o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6407d = new C2565m();
        this.f6405b = this.f6407d.a(this);
        this.f6404a.addView(this.f6405b);
        this.f6405b.getMapAsync(this);
        this.f6405b.onCreate(bundle);
        this.f6405b.addOnMapChangedListener(this);
        ProfileDTO profileDTO = (ProfileDTO) getIntent().getSerializableExtra(Scopes.PROFILE);
        if (profileDTO == null) {
            finish();
            return;
        }
        this.f6413j = new C2170a(this);
        m7633a(profileDTO);
    }

    public void onResume() {
        super.onResume();
        this.f6405b.onResume();
    }

    public void onPause() {
        super.onPause();
        this.f6405b.onPause();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f6405b.onLowMemory();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f6405b.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f6405b.onSaveInstanceState(bundle);
    }

    public void onMapReady(MapboxMap mapboxMap) {
        if (mapboxMap != null && !this.f6418o) {
            this.f6406c = mapboxMap;
            this.f6406c.setOnMarkerClickListener(this);
            m7634a(m5331p());
        }
    }

    public void finish() {
        this.f6418o = true;
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m7633a(ProfileDTO profileDTO) {
        LayoutParams layoutParams = this.f6405b.getLayoutParams();
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1373R.dimen.grid_explore_avatar_width);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        layoutParams.width = i;
        layoutParams.height = i;
        layoutParams2.setMargins((i - dimensionPixelSize) / 2, displayMetrics.widthPixels - (dimensionPixelSize / 2), 0, 0);
        this.f6408e.setLayoutParams(layoutParams2);
        this.f6405b.setLayoutParams(layoutParams);
        if (TextUtils.isEmpty(profileDTO.getAvatar())) {
            this.f6408e.setImageResource(C1373R.drawable.ic_avatar);
        } else {
            Picasso.with(this).load(profileDTO.getAvatar()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f6408e);
        }
        this.f6409f.setText(profileDTO.getNickname());
        this.f6410g.setText(String.format(Locale.getDefault(), "%.2f", new Object[]{Double.valueOf(profileDTO.getTotalDistance() / 1000.0d)}));
        this.f6411h.setText(String.valueOf(profileDTO.getGridNum()));
        if (profileDTO.getTotalElapsedTime() > 0) {
            if (profileDTO.getTotalElapsedTime() <= 0) {
                this.f6412i.setText("0");
                return;
            }
            float totalElapsedTime = (((float) profileDTO.getTotalElapsedTime()) * 1.0f) / 3600.0f;
            this.f6412i.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(totalElapsedTime)}));
        } else if (profileDTO.getTotalDistance() > 0.0d) {
            m7636b(m5331p());
        } else {
            this.f6412i.setText("0");
        }
    }

    /* renamed from: a */
    private void m7634a(String str) {
        getAsyncTaskQueue().a(new GridExploreActivity$1(this, str), new String[0]);
    }

    /* renamed from: b */
    private void m7636b(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new GridExploreActivity$2(this), new String[]{str});
        }
    }

    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    public void onMapChanged(int i) {
        if (this.f6406c != null) {
            double a = this.f6407d.a(this.f6405b);
            if (a <= 10.0d && !this.f6417n) {
                m7632a();
            } else if (a > 10.0d && this.f6417n) {
                m7635b();
            } else if (a <= 10.0d && Math.abs(a - this.f6416m) >= 1.0d) {
                try {
                    C2568n.a(this.f6406c, this.f6415l);
                    this.f6416m = a;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private void m7632a() {
        Exception e;
        if (!this.f6418o) {
            this.f6406c.removeAnnotations();
            this.f6417n = true;
            Icon fromDrawable = IconFactory.getInstance(this).fromDrawable(getResources().getDrawable(C1373R.drawable.ic_grid_oval_icon));
            for (GridDTO latLng1 : this.f6414k) {
                this.f6415l.add((MarkerOptions) ((MarkerOptions) new MarkerOptions().icon(fromDrawable)).position(latLng1.getLatLng1()));
            }
            try {
                C2568n.a(this.f6406c, this.f6415l);
            } catch (ExecutionException e2) {
                e = e2;
                e.printStackTrace();
            } catch (InterruptedException e3) {
                e = e3;
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    private void m7635b() {
        if (!this.f6418o) {
            this.f6417n = false;
            this.f6405b.clearAnimation();
            this.f6406c.removeAnnotations();
            List arrayList = new ArrayList();
            for (GridDTO polygons : this.f6414k) {
                arrayList.add(new PolygonOptions().addAll(polygons.getPolygons()).fillColor(Color.parseColor("#00bcd4")).strokeColor(-16728876).alpha(0.5f));
            }
            this.f6406c.addPolygons(arrayList);
            this.f6417n = false;
        }
    }
}
