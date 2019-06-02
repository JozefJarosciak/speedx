package com.beastbikes.android.ble.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.google.gson.Gson;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import java.util.ArrayList;

@C1459b(a = 2130903179)
public class SearchLocationActivity extends SessionFragmentActivity implements OnClickListener, C1371a {
    /* renamed from: a */
    private static final Logger f4199a = LoggerFactory.getLogger("SearchLocationActivity");
    @C1458a(a = 2131755934)
    /* renamed from: b */
    private ImageView f4200b;
    @C1458a(a = 2131755937)
    /* renamed from: c */
    private EditText f4201c;
    @C1458a(a = 2131755936)
    /* renamed from: d */
    private ImageView f4202d;
    @C1458a(a = 2131755938)
    /* renamed from: e */
    private LinearLayout f4203e;
    @C1458a(a = 2131755939)
    /* renamed from: f */
    private LinearLayout f4204f;
    @C1458a(a = 2131755940)
    /* renamed from: g */
    private ListView f4205g;
    /* renamed from: h */
    private SearchLocationActivity$a f4206h;
    /* renamed from: i */
    private int f4207i = 6;
    /* renamed from: j */
    private int f4208j;
    /* renamed from: k */
    private ArrayList<NavigationLocation> f4209k;
    /* renamed from: l */
    private ArrayList<NavigationLocation> f4210l;
    /* renamed from: m */
    private C1651c f4211m;
    /* renamed from: n */
    private AsyncTask<String, Void, ArrayList<NavigationLocation>> f4212n;
    /* renamed from: o */
    private C1454a f4213o;
    /* renamed from: p */
    private C1802i f4214p;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(Color.parseColor("#eeeeee"));
        }
        this.f4211m = new C1651c(this);
        this.f4209k = new ArrayList();
        this.f4210l = new ArrayList();
        this.f4213o = C1454a.m7990a();
        m5517b();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.img_search_location_back:
                setResult(0);
                finish();
                return;
            case C1373R.id.img_search_location_delete:
                this.f4201c.setText("");
                if (!this.f4210l.isEmpty()) {
                    this.f4209k.clear();
                    this.f4209k.addAll(this.f4210l);
                    this.f4209k.add(null);
                    this.f4206h.notifyDataSetChanged();
                    return;
                }
                return;
            case C1373R.id.linear_search_location_mine:
                m5329c(this.f4207i, null);
                finish();
                return;
            case C1373R.id.linear_search_location_on_map:
                Intent intent = new Intent(this, SelectOnMapActivity.class);
                intent.putExtra("search_event_id", this.f4207i);
                intent.putExtra("way_point_position", this.f4208j);
                m5325a(intent, this.f4207i);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    protected void mo2739b(int i, Object obj) {
        if (i == 6 || i == 7 || i == 8) {
            finish();
        }
    }

    /* renamed from: b */
    private void m5517b() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f4207i = intent.getIntExtra("search_event_id", 6);
        this.f4208j = intent.getIntExtra("way_point_position", -1);
        if (this.f4207i == 7) {
            this.f4201c.setHint(C1373R.string.str_navigation_select_destination);
        } else if (this.f4207i == 6) {
            this.f4201c.setHint(C1373R.string.str_navigation_select_start);
        } else {
            this.f4201c.setHint(C1373R.string.str_navigation_select_transit_point);
        }
        Object a = this.f4213o.m7994a((Context) this, "searchHistory", null);
        if (!TextUtils.isEmpty(a)) {
            ArrayList arrayList = (ArrayList) new Gson().fromJson(a, new SearchLocationActivity$1(this).getType());
            this.f4210l.addAll(arrayList);
            this.f4209k.addAll(arrayList);
            this.f4209k.add(null);
        }
        this.f4206h = new SearchLocationActivity$a(this);
        this.f4205g.setAdapter(this.f4206h);
        m5519c();
    }

    /* renamed from: c */
    private void m5519c() {
        this.f4200b.setOnClickListener(this);
        this.f4202d.setOnClickListener(this);
        this.f4203e.setOnClickListener(this);
        this.f4204f.setOnClickListener(this);
        this.f4201c.addTextChangedListener(new SearchLocationActivity$2(this));
        this.f4201c.setOnEditorActionListener(new SearchLocationActivity$3(this));
        this.f4205g.setOnItemClickListener(new SearchLocationActivity$4(this));
    }

    /* renamed from: a */
    private void m5513a(String str) {
        if (this.f4212n != null) {
            this.f4212n.cancel(true);
        }
        this.f4212n = new SearchLocationActivity$5(this);
        getAsyncTaskQueue().a(this.f4212n, new String[]{str});
    }

    /* renamed from: a */
    private void m5509a(NavigationLocation navigationLocation) {
        if (this.f4210l.isEmpty() || navigationLocation == null) {
            this.f4213o.m7992a((Context) this, "searchHistory").apply();
        } else if (C1849a.a()) {
            this.f4213o.m7993a((Context) this, "searchHistory", new Gson().toJson(this.f4210l)).apply();
            Intent intent = getIntent();
            intent.putExtra("mapboxlocation", navigationLocation);
            intent.putExtra("way_point_position", this.f4208j);
            m5329c(this.f4207i, intent);
            finish();
        } else {
            m5515b(navigationLocation);
        }
    }

    /* renamed from: b */
    private NavigationLocation m5515b(NavigationLocation navigationLocation) {
        m5514a(false, getResources().getString(C1373R.string.str_loading));
        getAsyncTaskQueue().a(new SearchLocationActivity$6(this, navigationLocation), new Void[0]);
        return navigationLocation;
    }

    /* renamed from: a */
    private void m5514a(boolean z, String str) {
        if (getWindow() != null && !isFinishing()) {
            if (this.f4214p == null) {
                this.f4214p = new C1802i(this, str, z);
            }
            if (!this.f4214p.isShowing()) {
                this.f4214p.show();
            }
        }
    }

    /* renamed from: d */
    private void m5521d() {
        if (this.f4214p != null && !isFinishing() && getWindow() != null) {
            if (this.f4214p.isShowing()) {
                this.f4214p.dismiss();
            }
            this.f4214p = null;
        }
    }
}
