package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
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
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.preferences.ui.BaseEditTextActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p056e.C1376d;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.android.p176b.C2790a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.AsyncImageView;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903668)
@C1460c(a = 2131820584)
@C1457a(a = "我的路书")
public class RouteSelfActivity extends SessionFragmentActivity implements OnSharedPreferenceChangeListener, OnClickListener, OnItemClickListener, OnItemLongClickListener {
    @C1458a(a = 2131756666)
    /* renamed from: a */
    private Button f5597a;
    @C1458a(a = 2131756667)
    /* renamed from: b */
    private ListView f5598b;
    @C1458a(a = 2131756665)
    /* renamed from: c */
    private ViewGroup f5599c;
    /* renamed from: d */
    private List<RouteDTO> f5600d = new ArrayList();
    /* renamed from: e */
    private RouteSelfActivity$a f5601e;
    /* renamed from: f */
    private C2185a f5602f;
    /* renamed from: g */
    private SharedPreferences f5603g;
    /* renamed from: h */
    private RouteDTO f5604h;
    /* renamed from: i */
    private C1802i f5605i;
    /* renamed from: j */
    private String f5606j;
    /* renamed from: k */
    private int f5607k;

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RouteSelfActivity$b */
    private final class C1422b extends ViewHolder<RouteDTO> {
        /* renamed from: a */
        C1376d f5589a;
        /* renamed from: b */
        final /* synthetic */ RouteSelfActivity f5590b;
        @C1458a(a = 2131757537)
        /* renamed from: c */
        private TextView f5591c;
        @C1458a(a = 2131757539)
        /* renamed from: d */
        private AsyncImageView f5592d;
        @C1458a(a = 2131757540)
        /* renamed from: e */
        private TextView f5593e;
        @C1458a(a = 2131757541)
        /* renamed from: f */
        private TextView f5594f;
        @C1458a(a = 2131757542)
        /* renamed from: g */
        private TextView f5595g;
        @C1458a(a = 2131757538)
        /* renamed from: h */
        private ImageView f5596h;

        public /* synthetic */ void bind(Object obj) {
            m6868a((RouteDTO) obj);
        }

        protected C1422b(RouteSelfActivity routeSelfActivity, View view, C1376d c1376d) {
            this.f5590b = routeSelfActivity;
            super(view);
            this.f5589a = c1376d;
        }

        /* renamed from: a */
        public void m6868a(RouteDTO routeDTO) {
            if (routeDTO != null) {
                this.f5591c.setText(routeDTO.getName());
                double totalDistance = routeDTO.getTotalDistance() / 1000.0d;
                CharSequence charSequence = "km";
                if (!C1849a.b(getContext())) {
                    totalDistance = C1849a.a(totalDistance);
                    charSequence = "mi";
                }
                this.f5593e.setText(String.format("%.0f", new Object[]{Double.valueOf(totalDistance)}));
                this.f5594f.setText(charSequence);
                ImageCache a = C2790a.a();
                Object mapURL = routeDTO.getMapURL();
                if (TextUtils.isEmpty(mapURL)) {
                    this.f5592d.setScaleType(ScaleType.CENTER);
                    this.f5596h.setVisibility(0);
                } else {
                    this.f5592d.setImageUrl(mapURL, new RouteSelfActivity$b$1(this, this.f5590b.getRequestQueueFactory().b(this.f5590b), a));
                }
                if (routeDTO.isUse()) {
                    this.f5595g.setText(C1373R.string.route_self_activity_used);
                    this.f5595g.setTextColor(this.f5590b.getResources().getColor(C1373R.color.route_self_used));
                    this.f5595g.setBackgroundResource(C1373R.drawable.route_map_used_bg);
                    return;
                }
                this.f5595g.setText(C1373R.string.route_self_activity_use);
                this.f5595g.setTextColor(this.f5590b.getResources().getColor(C1373R.color.route_self_use));
                this.f5595g.setBackgroundResource(C1373R.drawable.route_map_use_bg);
                this.f5595g.setOnClickListener(new RouteSelfActivity$b$2(this, routeDTO));
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5602f = new C2185a(this);
        this.f5597a.setOnClickListener(this);
        this.f5601e = new RouteSelfActivity$a(this, this, this.f5600d);
        this.f5598b.setAdapter(this.f5601e);
        this.f5598b.setSelector(new ColorDrawable(0));
        this.f5598b.setOnItemClickListener(this);
        registerForContextMenu(this.f5598b);
        this.f5598b.setOnItemLongClickListener(this);
        this.f5603g = getSharedPreferences(getPackageName(), 0);
        this.f5603g.registerOnSharedPreferenceChangeListener(this);
    }

    protected void onResume() {
        super.onResume();
        m6870a();
    }

    protected void onDestroy() {
        this.f5603g.unregisterOnSharedPreferenceChangeListener(this);
        if (this.f5605i != null) {
            if (this.f5605i.isShowing()) {
                this.f5605i.dismiss();
            }
            this.f5605i = null;
        }
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.route_make_first_btn:
                startActivity(new Intent(this, RoutePlanActivity.class));
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        RouteDTO routeDTO = (RouteDTO) adapterView.getAdapter().getItem(i);
        if (routeDTO != null) {
            Intent intent = new Intent(this, RoutePlanActivity.class);
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

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        new MenuInflater(view.getContext()).inflate(C1373R.menu.route_self_delete_menu, contextMenu);
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        this.f5607k = ((AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        if (this.f5607k >= this.f5601e.getCount()) {
            return true;
        }
        this.f5604h = (RouteDTO) this.f5601e.getItem(this.f5607k);
        if (this.f5604h == null) {
            return true;
        }
        Intent intent;
        switch (menuItem.getItemId()) {
            case C1373R.id.route_self_route_edit:
                intent = new Intent(this, RoutePlanActivity.class);
                intent.putExtra("route_id", String.valueOf(this.f5604h.getId()));
                startActivity(intent);
                AVAnalytics.onEvent(this, "编辑我的路线");
                break;
            case C1373R.id.route_self_route_rename:
                this.f5606j = this.f5604h.getName();
                intent = new Intent(this, BaseEditTextActivity.class);
                intent.putExtra("value", this.f5606j);
                startActivityForResult(intent, 8);
                C2580w.a(this, "重命名我的路线", null);
                break;
            case C1373R.id.route_self_route_delete:
                Object id = this.f5604h.getId();
                if (!TextUtils.isEmpty(id)) {
                    m6872a(id, this.f5607k);
                    C2580w.a(this, "删除我的路线", null);
                    break;
                }
                return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.route_make_line_menu_item:
                C2580w.a(this, "", "click_my_page_my_road_book_new");
                startActivity(new Intent(this, RoutePlanActivity.class));
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str != null && str.equals("use_route_id")) {
            String string = this.f5603g.getString("use_route_id", "");
            for (RouteDTO routeDTO : this.f5600d) {
                if (string.equals(routeDTO.getId())) {
                    routeDTO.setUse(true);
                } else {
                    routeDTO.setUse(false);
                }
            }
            this.f5601e.notifyDataSetChanged();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 8:
                if (-1 == i2) {
                    String string = intent.getExtras().getString("value");
                    this.f5604h.setName(string);
                    m6871a(string);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6870a() {
        this.f5605i = new C1802i(this, getString(C1373R.string.str_loading), true);
        getAsyncTaskQueue().a(new RouteSelfActivity$1(this), new String[0]);
    }

    /* renamed from: a */
    private void m6872a(String str, int i) {
        this.f5605i = new C1802i(this, getString(C1373R.string.loading_msg_deleted), false);
        getAsyncTaskQueue().a(new RouteSelfActivity$2(this, i), new String[]{str});
    }

    /* renamed from: a */
    private void m6871a(String str) {
        if (!TextUtils.isEmpty(str) && !this.f5606j.equals(str)) {
            getAsyncTaskQueue().a(new RouteSelfActivity$3(this), new String[]{str});
        }
    }
}
