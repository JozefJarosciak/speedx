package com.beastbikes.android.modules.preferences.ui.offlineMap;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p072a.C2316b;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p072a.C2317c;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p072a.C2318d;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p141b.C2319a;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;
import com.beastbikes.android.widget.AutoExpandableListView;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@C1457a(a = "离线地图")
@C1459b(a = 2130903157)
public class OfflineMapActivity extends BaseFragmentActivity implements MKOfflineMapListener, C2319a {
    /* renamed from: a */
    private static final String f5969a = OfflineMapActivity.class.getName();
    /* renamed from: b */
    private SharedPreferences f5970b;
    @C1458a(a = 2131755836)
    /* renamed from: c */
    private ViewPager f5971c;
    @C1458a(a = 2131755833)
    /* renamed from: d */
    private TextView f5972d;
    @C1458a(a = 2131755834)
    /* renamed from: e */
    private TextView f5973e;
    @C1458a(a = 2131755835)
    /* renamed from: f */
    private ImageView f5974f;
    /* renamed from: g */
    private AutoExpandableListView f5975g;
    /* renamed from: h */
    private ListView f5976h;
    /* renamed from: i */
    private ListView f5977i;
    /* renamed from: j */
    private TextView f5978j;
    /* renamed from: k */
    private final MKOfflineMap f5979k = new MKOfflineMap();
    /* renamed from: l */
    private final List<C2322a> f5980l = Collections.synchronizedList(new ArrayList());
    /* renamed from: m */
    private final List<C2322a> f5981m = Collections.synchronizedList(new ArrayList());
    /* renamed from: n */
    private final List<View> f5982n = new ArrayList(2);
    /* renamed from: o */
    private final List<C2322a> f5983o = Collections.synchronizedList(new ArrayList());
    /* renamed from: p */
    private final List<C2322a> f5984p = Collections.synchronizedList(new ArrayList());
    /* renamed from: q */
    private final List<List<C2322a>> f5985q = Collections.synchronizedList(new ArrayList());
    /* renamed from: r */
    private C2318d f5986r;
    /* renamed from: s */
    private C2317c f5987s;
    /* renamed from: t */
    private C2316b f5988t;
    /* renamed from: u */
    private boolean f5989u = false;
    /* renamed from: v */
    private boolean f5990v = false;
    /* renamed from: w */
    private int f5991w = 0;
    /* renamed from: x */
    private int f5992x = 0;
    /* renamed from: y */
    private int f5993y;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5970b = getSharedPreferences(f5969a, 0);
        m7206d();
    }

    protected void onResume() {
        super.onResume();
        if (!this.f5989u) {
            this.f5989u = true;
            m7202b();
        }
    }

    protected void onDestroy() {
        this.f5979k.destroy();
        super.onDestroy();
    }

    protected void onPause() {
        this.f5970b.edit().putInt("tab_index", this.f5992x).commit();
        super.onPause();
    }

    /* renamed from: b */
    private void m7202b() {
        getAsyncTaskQueue().a(new OfflineMapActivity$1(this), new Void[0]);
    }

    /* renamed from: a */
    private C2322a m7199a(MKOLUpdateElement mKOLUpdateElement, boolean z) {
        C2322a c2322a = null;
        if (!(mKOLUpdateElement == null || this.f5981m == null)) {
            if (mKOLUpdateElement.status == 4 || mKOLUpdateElement.ratio == 100) {
                mKOLUpdateElement.status = 4;
                this.f5970b.edit().putLong(String.valueOf(mKOLUpdateElement.cityID), System.currentTimeMillis()).commit();
            }
            for (C2322a c2322a2 : this.f5981m) {
                if (mKOLUpdateElement.cityID == c2322a2.g()) {
                    c2322a2.a(mKOLUpdateElement);
                    c2322a2.a(this.f5970b.getLong(String.valueOf(mKOLUpdateElement.cityID), 0));
                    if (c2322a2.c() != 0) {
                        if (z) {
                            if (!this.f5980l.contains(c2322a2) && c2322a2.c() == 4 && this.f5980l.add(c2322a2)) {
                                c2322a = c2322a2;
                            }
                        } else if (c2322a2.c() == 4 && this.f5980l.add(c2322a2)) {
                            c2322a = c2322a2;
                        }
                    }
                    if (!this.f5990v && c2322a2.c() == 2) {
                        int g = c2322a2.g();
                        if (g > 0) {
                            this.f5979k.start(g);
                        }
                        this.f5990v = true;
                    }
                }
            }
        }
        return c2322a;
    }

    /* renamed from: c */
    private void m7204c() {
        boolean z = this.f5980l == null || this.f5980l.isEmpty();
        runOnUiThread(new OfflineMapActivity$2(this, z));
    }

    @SuppressLint({"InflateParams"})
    /* renamed from: d */
    private void m7206d() {
        this.f5979k.init(this);
        this.f5993y = BitmapFactory.decodeResource(getResources(), C1373R.drawable.offline_map_tab_cursor).getWidth();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f5991w = ((displayMetrics.widthPixels / 2) - this.f5993y) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate((float) this.f5991w, 0.0f);
        this.f5974f.setImageMatrix(matrix);
        LayoutInflater from = LayoutInflater.from(this);
        View inflate = from.inflate(C1373R.layout.activity_offline_map_cities, null, false);
        this.f5975g = (AutoExpandableListView) inflate.findViewById(C1373R.id.offlinemap_fragment_cities_lvWholeCountry);
        this.f5976h = (ListView) inflate.findViewById(C1373R.id.offlinemap_fragment_cities_lvHotCities);
        this.f5982n.add(inflate);
        View inflate2 = from.inflate(C1373R.layout.activity_offline_map_download, null, false);
        this.f5977i = (ListView) inflate2.findViewById(C1373R.id.offlinemap_fragment_download_lvDown);
        this.f5982n.add(inflate2);
        OnPageChangeListener offlineMapActivity$b = new OfflineMapActivity$b(this);
        this.f5971c.setAdapter(new OfflineMapActivity$c(this, this.f5982n));
        this.f5971c.setOnPageChangeListener(offlineMapActivity$b);
        this.f5992x = this.f5970b.getInt("tab_index", 0);
        this.f5971c.setCurrentItem(this.f5992x);
        offlineMapActivity$b.onPageSelected(this.f5992x);
        this.f5986r = new C2318d(this, this.f5979k, this);
        this.f5977i.setAdapter(this.f5986r);
        this.f5987s = new C2317c(this, this.f5979k, this);
        this.f5976h.setAdapter(this.f5987s);
        this.f5988t = new C2316b(this, this.f5979k, this);
        this.f5975g.setAdapter(this.f5988t);
        this.f5975g.setGroupIndicator(null);
        this.f5978j = (TextView) inflate2.findViewById(C1373R.id.offlinemap_fragment_download_no_download);
        this.f5972d.setOnClickListener(new OfflineMapActivity$a(this, 0));
        this.f5973e.setOnClickListener(new OfflineMapActivity$a(this, 1));
    }

    /* renamed from: a */
    public void m7222a(C2322a c2322a, boolean z) {
        if (z) {
            for (C2322a c2322a2 : this.f5981m) {
                if (c2322a2.g() == c2322a.g()) {
                    c2322a2.a(null);
                    break;
                }
            }
            for (int size = this.f5980l.size() - 1; size >= 0; size--) {
                if (((C2322a) this.f5980l.get(size)).g() == c2322a.g()) {
                    this.f5980l.remove(size);
                    this.f5970b.edit().remove(String.valueOf(c2322a.g())).apply();
                    m7199a(this.f5979k.getUpdateInfo(c2322a.g()), false);
                }
            }
            m7204c();
        } else {
            this.f5986r.notifyDataSetChanged();
        }
        this.f5987s.notifyDataSetChanged();
        this.f5988t.notifyDataSetChanged();
    }

    public void onGetOfflineMapState(int i, int i2) {
        MKOLUpdateElement updateInfo = this.f5979k.getUpdateInfo(i2);
        if (updateInfo != null) {
            switch (i) {
                case 0:
                    if (!(m7199a(updateInfo, true) == null || this.f5980l == null || this.f5980l.size() <= 1)) {
                        Collections.sort(this.f5980l, C2322a.f11044b);
                    }
                    m7204c();
                    return;
                case 6:
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m7221a() {
        this.f5971c.setCurrentItem(1);
    }
}
