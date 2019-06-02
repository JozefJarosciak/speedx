package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.cycling.route.ui.RoutePlanActivity;
import com.beastbikes.android.modules.preferences.ui.BaseEditTextActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p176b.C2790a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.AsyncImageView;
import java.util.ArrayList;
import java.util.List;

@C1457a(a = "我的路书")
@C1459b(a = 2130903375)
public class RoutesSelfFrag extends SessionFragment implements OnSharedPreferenceChangeListener, OnClickListener, OnItemClickListener, OnItemLongClickListener {
    @C1458a(a = 2131756666)
    /* renamed from: a */
    private Button f5682a;
    @C1458a(a = 2131756667)
    /* renamed from: b */
    private ListView f5683b;
    @C1458a(a = 2131756665)
    /* renamed from: c */
    private ViewGroup f5684c;
    /* renamed from: d */
    private List<RouteDTO> f5685d = new ArrayList();
    /* renamed from: e */
    private RoutesSelfFrag$a f5686e;
    /* renamed from: f */
    private C2185a f5687f;
    /* renamed from: g */
    private SharedPreferences f5688g;
    /* renamed from: h */
    private RouteDTO f5689h;
    /* renamed from: i */
    private String f5690i;
    /* renamed from: j */
    private int f5691j;

    /* renamed from: com.beastbikes.android.modules.cycling.sections.ui.RoutesSelfFrag$b */
    private final class C1425b extends ViewHolder<RouteDTO> {
        /* renamed from: a */
        final /* synthetic */ RoutesSelfFrag f5675a;
        @C1458a(a = 2131757537)
        /* renamed from: b */
        private TextView f5676b;
        @C1458a(a = 2131757539)
        /* renamed from: c */
        private AsyncImageView f5677c;
        @C1458a(a = 2131757540)
        /* renamed from: d */
        private TextView f5678d;
        @C1458a(a = 2131757541)
        /* renamed from: e */
        private TextView f5679e;
        @C1458a(a = 2131757542)
        /* renamed from: f */
        private TextView f5680f;
        @C1458a(a = 2131757538)
        /* renamed from: g */
        private ImageView f5681g;

        public /* synthetic */ void bind(Object obj) {
            m6939a((RouteDTO) obj);
        }

        protected C1425b(RoutesSelfFrag routesSelfFrag, View view) {
            this.f5675a = routesSelfFrag;
            super(view);
        }

        /* renamed from: a */
        public void m6939a(RouteDTO routeDTO) {
            if (routeDTO != null) {
                this.f5676b.setText(routeDTO.getName());
                double totalDistance = routeDTO.getTotalDistance() / 1000.0d;
                CharSequence charSequence = "km";
                if (!C1849a.b(getContext())) {
                    totalDistance = C1849a.a(totalDistance);
                    charSequence = "mi";
                }
                this.f5678d.setText(String.format("%.0f", new Object[]{Double.valueOf(totalDistance)}));
                this.f5679e.setText(charSequence);
                ImageCache a = C2790a.a();
                Object mapURL = routeDTO.getMapURL();
                if (TextUtils.isEmpty(mapURL)) {
                    this.f5677c.setScaleType(ScaleType.CENTER);
                    this.f5681g.setVisibility(0);
                } else {
                    this.f5677c.setImageUrl(mapURL, new RoutesSelfFrag$b$1(this, this.f5675a.getRequestQueueFactory().b(this.f5675a.getActivity()), a));
                }
                if (routeDTO.isUse()) {
                    this.f5680f.setText(C1373R.string.route_self_activity_used);
                    this.f5680f.setTextColor(this.f5675a.getResources().getColor(C1373R.color.route_self_used));
                    this.f5680f.setBackgroundResource(C1373R.drawable.route_map_used_bg);
                    return;
                }
                this.f5680f.setText(C1373R.string.route_self_activity_use);
                this.f5680f.setTextColor(this.f5675a.getResources().getColor(C1373R.color.route_self_use));
                this.f5680f.setBackgroundResource(C1373R.drawable.route_map_use_bg);
                this.f5680f.setOnClickListener(new RoutesSelfFrag$b$2(this, routeDTO));
            }
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5687f = new C2185a(getActivity());
        this.f5682a.setOnClickListener(this);
        this.f5686e = new RoutesSelfFrag$a(this, this.f5685d);
        this.f5683b.setAdapter(this.f5686e);
        this.f5683b.setSelector(new ColorDrawable(0));
        this.f5683b.setOnItemClickListener(this);
        registerForContextMenu(this.f5683b);
        this.f5683b.setOnItemLongClickListener(this);
        this.f5688g = getActivity().getSharedPreferences(getActivity().getPackageName(), 0);
        this.f5688g.registerOnSharedPreferenceChangeListener(this);
    }

    public void onResume() {
        super.onResume();
        m6941a();
    }

    public void onDestroy() {
        super.onDestroy();
        this.f5688g.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.route_make_first_btn:
                startActivity(new Intent(getActivity(), RoutePlanActivity.class));
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        RouteDTO routeDTO = (RouteDTO) adapterView.getAdapter().getItem(i);
        if (routeDTO != null) {
            Intent intent = new Intent(getActivity(), RoutePlanActivity.class);
            intent.putExtra("route_id", String.valueOf(routeDTO.getId()));
            intent.putExtra("show_list", false);
            startActivity(intent);
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (((RouteDTO) adapterView.getAdapter().getItem(i)) == null) {
            return true;
        }
        return false;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        this.f5691j = ((AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        if (this.f5691j >= this.f5686e.getCount()) {
            return true;
        }
        this.f5689h = (RouteDTO) this.f5686e.getItem(this.f5691j);
        if (this.f5689h == null) {
            return true;
        }
        Intent intent;
        switch (menuItem.getItemId()) {
            case C1373R.id.route_self_route_edit:
                intent = new Intent(getActivity(), RoutePlanActivity.class);
                intent.putExtra("route_id", String.valueOf(this.f5689h.getId()));
                startActivity(intent);
                AVAnalytics.onEvent(getActivity(), "编辑我的路线");
                break;
            case C1373R.id.route_self_route_rename:
                this.f5690i = this.f5689h.getName();
                intent = new Intent(getActivity(), BaseEditTextActivity.class);
                intent.putExtra("value", this.f5690i);
                startActivityForResult(intent, 8);
                C2580w.a(getActivity(), "重命名我的路线", null);
                break;
            case C1373R.id.route_self_route_delete:
                Object id = this.f5689h.getId();
                if (!TextUtils.isEmpty(id)) {
                    m6943a(id, this.f5691j);
                    C2580w.a(getActivity(), "删除我的路线", null);
                    break;
                }
                return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str != null && str.equals("use_route_id")) {
            String string = this.f5688g.getString("use_route_id", "");
            for (RouteDTO routeDTO : this.f5685d) {
                if (string.equals(routeDTO.getId())) {
                    routeDTO.setUse(true);
                } else {
                    routeDTO.setUse(false);
                }
            }
            this.f5686e.notifyDataSetChanged();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 8:
                if (-1 == i2) {
                    String string = intent.getExtras().getString("value");
                    this.f5689h.setName(string);
                    m6942a(string);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6941a() {
        getAsyncTaskQueue().a(new RoutesSelfFrag$1(this), new String[0]);
    }

    /* renamed from: a */
    private void m6943a(String str, int i) {
        C1802i c1802i = new C1802i(getActivity(), getString(C1373R.string.loading_msg_deleted), false);
        getAsyncTaskQueue().a(new RoutesSelfFrag$2(this, c1802i, i), new String[]{str});
    }

    /* renamed from: a */
    private void m6942a(String str) {
        if (!TextUtils.isEmpty(str) && !this.f5690i.equals(str)) {
            getAsyncTaskQueue().a(new RoutesSelfFrag$3(this), new String[]{str});
        }
    }
}
