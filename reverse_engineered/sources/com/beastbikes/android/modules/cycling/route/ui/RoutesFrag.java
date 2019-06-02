package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.route.dto.C2187a;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.user.ui.C2496a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@C1457a(a = "路线首页")
@C1459b(a = 2130903659)
public class RoutesFrag extends SessionFragment implements OnItemClickListener, BDLocationListener {
    @C1458a(a = 2131757498)
    /* renamed from: a */
    private ListView f5616a;
    @C1458a(a = 2131757499)
    /* renamed from: b */
    private ImageView f5617b;
    /* renamed from: c */
    private final BDLocation f5618c = new BDLocation();
    /* renamed from: d */
    private final Set<C2187a> f5619d = new LinkedHashSet();
    /* renamed from: e */
    private final List<RouteDTO> f5620e = new ArrayList();
    /* renamed from: f */
    private RoutesFrag$a f5621f;
    /* renamed from: g */
    private C2185a f5622g;
    /* renamed from: h */
    private LocationClient f5623h;
    /* renamed from: i */
    private LocationClientOption f5624i;
    /* renamed from: j */
    private C2496a f5625j;
    /* renamed from: k */
    private String f5626k = "131";
    /* renamed from: l */
    private boolean f5627l;
    /* renamed from: m */
    private boolean f5628m;

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RoutesFrag$b */
    private final class C1423b extends ViewHolder<RouteDTO> {
        /* renamed from: a */
        final /* synthetic */ RoutesFrag f5608a;
        @C1458a(a = 2131757544)
        /* renamed from: b */
        private ImageView f5609b;
        @C1458a(a = 2131757545)
        /* renamed from: c */
        private TextView f5610c;
        @C1458a(a = 2131757546)
        /* renamed from: d */
        private TextView f5611d;
        @C1458a(a = 2131757549)
        /* renamed from: e */
        private TextView f5612e;
        @C1458a(a = 2131757547)
        /* renamed from: f */
        private RatingBar f5613f;
        @C1458a(a = 2131757548)
        /* renamed from: g */
        private TextView f5614g;
        @C1458a(a = 2131757550)
        /* renamed from: h */
        private TextView f5615h;

        public /* synthetic */ void bind(Object obj) {
            m6881a((RouteDTO) obj);
        }

        protected C1423b(RoutesFrag routesFrag, View view) {
            this.f5608a = routesFrag;
            super(view);
        }

        /* renamed from: a */
        public void m6881a(RouteDTO routeDTO) {
            this.f5610c.setText(routeDTO.getName());
            this.f5611d.setText(routeDTO.getEnglishName());
            this.f5612e.setText(String.format(this.f5608a.getString(C1373R.string.routes_fragment_followed), new Object[]{Integer.valueOf(routeDTO.getNumberOfFollowers())}));
            this.f5613f.setRating((float) Math.round(routeDTO.getDifficultyCoefficient()));
            if (this.f5608a.f5628m) {
                this.f5614g.setText(String.format("%.0f km", new Object[]{Double.valueOf(routeDTO.getTotalDistance() / 1000.0d)}));
            } else {
                this.f5614g.setText(String.format("%.0f mi", new Object[]{Double.valueOf(C1849a.a(routeDTO.getTotalDistance()) / 1000.0d)}));
            }
            if (TextUtils.isEmpty(routeDTO.getCoverURL())) {
                this.f5609b.setImageResource(C1373R.drawable.transparent);
            } else {
                Picasso.with(getContext()).load(routeDTO.getCoverURL()).fit().error((int) C1373R.drawable.transparent).placeholder((int) C1373R.drawable.transparent).centerCrop().into(this.f5609b);
            }
            if (this.f5608a.f5618c != null && routeDTO.getOriginLatitude() != 0.0d) {
                double distance = DistanceUtil.getDistance(new LatLng(this.f5608a.f5618c.getLatitude(), this.f5608a.f5618c.getLongitude()), new LatLng(routeDTO.getOriginLatitude(), routeDTO.getOriginLongitude())) / 1000.0d;
                if (this.f5608a.f5628m) {
                    this.f5615h.setText(String.format("<%.0f km", new Object[]{Double.valueOf(distance)}));
                    return;
                }
                this.f5615h.setText(String.format("<%.0f mi", new Object[]{Double.valueOf(C1849a.a(distance))}));
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().setTitle(C1373R.string.discovery_fragment_good_route);
        this.f5622g = new C2185a(getActivity());
        C2187a c2187a = new C2187a();
        c2187a.a("131");
        c2187a.b(getString(C1373R.string.beijing));
        this.f5619d.add(c2187a);
        setHasOptionsMenu(true);
        this.f5628m = C1849a.b(getActivity());
        this.f5621f = new RoutesFrag$a(this);
        this.f5616a.setAdapter(this.f5621f);
        this.f5616a.setOnItemClickListener(this);
        if (!this.f5627l && this.f5616a != null) {
            if (TextUtils.isEmpty(this.f5626k)) {
                this.f5626k = "131";
            }
            m6885a(this.f5626k);
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            getActivity().setTitle(C1373R.string.discovery_fragment_good_route);
        }
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onStart() {
        super.onStart();
        this.f5624i = new LocationClientOption();
        this.f5624i.setAddrType("all");
        this.f5624i.setOpenGps(true);
        this.f5623h = new LocationClient(getActivity());
        this.f5623h.setLocOption(this.f5624i);
        this.f5623h.registerLocationListener(this);
        this.f5623h.start();
        this.f5623h.requestLocation();
    }

    public void onStop() {
        super.onStop();
        if (this.f5623h != null && this.f5623h.isStarted()) {
            this.f5623h.stop();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Context activity = getActivity();
        synchronized (this.f5619d) {
            for (C2187a c2187a : this.f5619d) {
                String a = c2187a.a();
                MenuItem add = menu.add(c2187a.b());
                String str = Build.FINGERPRINT;
                if (!VERSION.RELEASE.equals("4.2.2")) {
                    add.setCheckable(true);
                    add.setChecked(a.equals(this.f5626k));
                } else if (!str.contains("Xiaomi")) {
                    add.setCheckable(true);
                    add.setChecked(a.equals(this.f5626k));
                }
                add.setTitleCondensed("aa");
                add.setOnMenuItemClickListener(new RoutesFrag$1(this, activity, a));
            }
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C2580w.a(getActivity(), "点击路线总次数", null);
        RouteDTO routeDTO = (RouteDTO) adapterView.getItemAtPosition(i);
        if (routeDTO != null) {
            Intent intent = new Intent(getActivity(), RouteActivity.class);
            intent.putExtra("route", routeDTO);
            startActivity(intent);
        }
    }

    public void onReceiveLocation(BDLocation bDLocation) {
        if (bDLocation != null) {
            this.f5618c.setAddrStr(bDLocation.getAddrStr());
            this.f5618c.setAltitude(bDLocation.getAltitude());
            this.f5618c.setCoorType(bDLocation.getCoorType());
            this.f5618c.setLatitude(bDLocation.getLatitude());
            this.f5618c.setLongitude(bDLocation.getLongitude());
            this.f5618c.setLocType(bDLocation.getLocType());
            String cityCode = bDLocation.getCityCode();
            if (!"131".equals(cityCode)) {
                m6885a(cityCode);
            }
            if (this.f5623h != null) {
                this.f5623h.stop();
            }
        }
    }

    public void onConnectHotSpotMessage(String str, int i) {
    }

    /* renamed from: a */
    private void m6885a(String str) {
        getActivity();
        if (TextUtils.isEmpty(str)) {
            this.f5626k = "131";
        } else {
            this.f5626k = str;
        }
        getAsyncTaskQueue().a(new RoutesFrag$2(this), new String[]{str});
    }
}
