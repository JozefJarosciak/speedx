package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.locale.googlemaputils.C1859f;
import com.beastbikes.android.locale.googlemaputils.C1862g;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.C2188b;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.sections.ui.widget.CustomEditText;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@C1459b(a = 2130903666)
public class RouteMapSearchGeoActivity extends SessionFragmentActivity implements OnClickListener, OnItemClickListener, OnGetPoiSearchResultListener, C1859f {
    /* renamed from: a */
    public static boolean f5519a = true;
    @C1458a(a = 2131757533)
    /* renamed from: b */
    private ImageView f5520b;
    /* renamed from: c */
    private CustomEditText f5521c;
    @C1458a(a = 2131757534)
    /* renamed from: d */
    private ListView f5522d;
    /* renamed from: e */
    private PoiSearch f5523e;
    /* renamed from: f */
    private RouteMapSearchGeoActivity$d f5524f;
    /* renamed from: g */
    private List<PoiInfo> f5525g = new ArrayList();
    /* renamed from: h */
    private String f5526h;
    /* renamed from: i */
    private int f5527i = 0;
    /* renamed from: j */
    private C1862g f5528j;
    /* renamed from: k */
    private boolean f5529k = true;
    /* renamed from: l */
    private List<C2188b> f5530l = new ArrayList();
    /* renamed from: m */
    private RouteMapSearchGeoActivity$b f5531m;
    /* renamed from: n */
    private RouteMapSearchGeoActivity$a f5532n;

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RouteMapSearchGeoActivity$c */
    private final class C1419c extends ViewHolder<C2188b> {
        /* renamed from: a */
        final /* synthetic */ RouteMapSearchGeoActivity f5513a;
        @C1458a(a = 2131757535)
        /* renamed from: b */
        private TextView f5514b;
        @C1458a(a = 2131757536)
        /* renamed from: c */
        private TextView f5515c;

        public /* synthetic */ void bind(Object obj) {
            m6816a((C2188b) obj);
        }

        protected C1419c(RouteMapSearchGeoActivity routeMapSearchGeoActivity, View view) {
            this.f5513a = routeMapSearchGeoActivity;
            super(view);
        }

        /* renamed from: a */
        public void m6816a(C2188b c2188b) {
            if (c2188b != null) {
                this.f5514b.setText(c2188b.a());
                this.f5515c.setText(c2188b.b());
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RouteMapSearchGeoActivity$e */
    private final class C1420e extends ViewHolder<PoiInfo> {
        /* renamed from: a */
        final /* synthetic */ RouteMapSearchGeoActivity f5516a;
        @C1458a(a = 2131757535)
        /* renamed from: b */
        private TextView f5517b;
        @C1458a(a = 2131757536)
        /* renamed from: c */
        private TextView f5518c;

        public /* synthetic */ void bind(Object obj) {
            m6817a((PoiInfo) obj);
        }

        protected C1420e(RouteMapSearchGeoActivity routeMapSearchGeoActivity, View view) {
            this.f5516a = routeMapSearchGeoActivity;
            super(view);
        }

        /* renamed from: a */
        public void m6817a(PoiInfo poiInfo) {
            if (poiInfo != null) {
                this.f5517b.setText(poiInfo.name);
                this.f5518c.setText(poiInfo.address);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f5529k = C1849a.a();
        if (this.f5529k) {
            this.f5524f = new RouteMapSearchGeoActivity$d(this, this.f5525g);
            this.f5522d.setAdapter(this.f5524f);
            this.f5522d.setOnItemClickListener(this);
            this.f5523e = PoiSearch.newInstance();
            this.f5523e.setOnGetPoiSearchResultListener(this);
        } else {
            this.f5532n = new RouteMapSearchGeoActivity$a(this, null);
            this.f5531m = new RouteMapSearchGeoActivity$b(this, this.f5530l);
            this.f5522d.setAdapter(this.f5531m);
            this.f5522d.setOnItemClickListener(this.f5532n);
            this.f5528j = new C1862g(this, getAsyncTaskQueue(), getRequestQueueFactory(), this);
        }
        this.f5520b.setOnClickListener(this);
        this.f5521c = (CustomEditText) findViewById(C1373R.id.route_map_search_edittext);
        this.f5521c.addTextChangedListener(new RouteMapSearchGeoActivity$1(this));
        this.f5521c.setDrawableClickListener(new RouteMapSearchGeoActivity$2(this));
        this.f5521c.setFocusable(true);
        this.f5521c.setFocusableInTouchMode(true);
    }

    protected void onResume() {
        if (f5519a) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
        super.onResume();
    }

    /* renamed from: a */
    public void m6824a() {
    }

    /* renamed from: a */
    public void m6825a(Status status) {
        runOnUiThread(new RouteMapSearchGeoActivity$3(this));
    }

    /* renamed from: a */
    public void m6826a(List<C2188b> list) {
        if (list != null && list.size() != 0) {
            this.f5530l.clear();
            this.f5530l.addAll(list);
            if (this.f5531m != null) {
                this.f5531m.notifyDataSetChanged();
            }
            this.f5522d.setBackgroundResource(C1373R.drawable.bg_shadow);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.route_map_search_back:
                finish();
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        PoiInfo poiInfo = (PoiInfo) adapterView.getAdapter().getItem(i);
        if (poiInfo != null) {
            Intent intent = getIntent();
            intent.putExtra("poiinfo", new PoiInfoDTO(poiInfo));
            setResult(-1, intent);
            finish();
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult.error != ERRORNO.NO_ERROR) {
            Toast.makeText(this, "抱歉，未找到结果", 0).show();
        } else {
            Toast.makeText(this, poiDetailResult.getName() + ": " + poiDetailResult.getAddress(), 0).show();
        }
    }

    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
    }

    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null || poiResult.error == ERRORNO.RESULT_NOT_FOUND) {
            Toasts.show(this, getResources().getString(C1373R.string.get_poi_no_result));
        } else if (poiResult.error == ERRORNO.NO_ERROR) {
            this.f5527i = 0;
            Collection allPoi = poiResult.getAllPoi();
            if (!allPoi.isEmpty()) {
                this.f5525g.clear();
                this.f5525g.addAll(allPoi);
                this.f5524f.notifyDataSetChanged();
            }
            this.f5522d.setBackgroundResource(C1373R.drawable.bg_shadow);
        } else if (poiResult.error != ERRORNO.AMBIGUOUS_KEYWORD) {
        } else {
            if (this.f5527i > 2) {
                this.f5527i = 0;
                return;
            }
            if (poiResult.getSuggestCityList().size() > 0) {
                this.f5523e.searchInCity(new PoiCitySearchOption().city(((CityInfo) poiResult.getSuggestCityList().get(0)).city).keyword(this.f5526h));
            }
            this.f5527i++;
        }
    }
}
