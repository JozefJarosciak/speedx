package com.beastbikes.android.modules.cycling.club.ui;

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
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.cycling.route.ui.RoutePlanActivity;
import com.beastbikes.android.modules.preferences.ui.BaseEditTextActivity;
import com.beastbikes.android.utils.C2580w;
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
@C1457a(a = "路书选择页面")
public class ClubActRouteSelfActivity extends SessionFragmentActivity implements OnSharedPreferenceChangeListener, OnClickListener, OnItemClickListener, OnItemLongClickListener {
    @C1458a(a = 2131756666)
    /* renamed from: a */
    private Button f4831a;
    @C1458a(a = 2131756667)
    /* renamed from: b */
    private ListView f4832b;
    @C1458a(a = 2131756665)
    /* renamed from: c */
    private ViewGroup f4833c;
    /* renamed from: d */
    private List<RouteDTO> f4834d = new ArrayList();
    /* renamed from: e */
    private ClubActRouteSelfActivity$a f4835e;
    /* renamed from: f */
    private C2185a f4836f;
    /* renamed from: g */
    private SharedPreferences f4837g;
    /* renamed from: h */
    private RouteDTO f4838h;
    /* renamed from: i */
    private C1802i f4839i;
    /* renamed from: j */
    private String f4840j;
    /* renamed from: k */
    private int f4841k;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubActRouteSelfActivity$b */
    private final class C1406b extends ViewHolder<RouteDTO> {
        /* renamed from: a */
        final /* synthetic */ ClubActRouteSelfActivity f4825a;
        @C1458a(a = 2131757537)
        /* renamed from: b */
        private TextView f4826b;
        @C1458a(a = 2131757539)
        /* renamed from: c */
        private AsyncImageView f4827c;
        @C1458a(a = 2131757540)
        /* renamed from: d */
        private TextView f4828d;
        @C1458a(a = 2131757542)
        /* renamed from: e */
        private TextView f4829e;
        @C1458a(a = 2131757538)
        /* renamed from: f */
        private ImageView f4830f;

        public /* synthetic */ void bind(Object obj) {
            m6210a((RouteDTO) obj);
        }

        protected C1406b(ClubActRouteSelfActivity clubActRouteSelfActivity, View view) {
            this.f4825a = clubActRouteSelfActivity;
            super(view);
        }

        /* renamed from: a */
        public void m6210a(RouteDTO routeDTO) {
            if (routeDTO != null) {
                this.f4826b.setText(routeDTO.getName());
                this.f4828d.setText(String.format("%.0f", new Object[]{Double.valueOf(routeDTO.getTotalDistance() / 1000.0d)}));
                ImageCache a = C2790a.a();
                Object mapURL = routeDTO.getMapURL();
                if (TextUtils.isEmpty(mapURL)) {
                    this.f4827c.setScaleType(ScaleType.CENTER);
                    this.f4830f.setVisibility(0);
                } else {
                    this.f4827c.setImageUrl(mapURL, new ClubActRouteSelfActivity$b$1(this, this.f4825a.getRequestQueueFactory().b(this.f4825a), a));
                }
                if (routeDTO.isUse()) {
                    this.f4829e.setText(C1373R.string.route_self_activity_used);
                    this.f4829e.setTextColor(this.f4825a.getResources().getColor(C1373R.color.route_self_used));
                    this.f4829e.setBackgroundResource(C1373R.drawable.route_map_used_bg);
                } else {
                    this.f4829e.setText(C1373R.string.route_self_activity_use);
                    this.f4829e.setTextColor(this.f4825a.getResources().getColor(C1373R.color.route_self_use));
                    this.f4829e.setBackgroundResource(C1373R.drawable.route_map_use_bg);
                    this.f4829e.setOnClickListener(new ClubActRouteSelfActivity$b$2(this, routeDTO));
                }
                this.f4829e.setVisibility(8);
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
        this.f4836f = new C2185a(this);
        this.f4831a.setOnClickListener(this);
        this.f4835e = new ClubActRouteSelfActivity$a(this, this, this.f4834d);
        this.f4832b.setAdapter(this.f4835e);
        this.f4832b.setSelector(new ColorDrawable(0));
        this.f4832b.setOnItemClickListener(this);
        this.f4837g = getSharedPreferences(getPackageName(), 0);
        this.f4837g.registerOnSharedPreferenceChangeListener(this);
    }

    protected void onResume() {
        super.onResume();
        m6212a();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f4837g.unregisterOnSharedPreferenceChangeListener(this);
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
            Intent intent = getIntent();
            intent.putExtra("route_id", routeDTO.getId());
            intent.putExtra("route_name", routeDTO.getName());
            intent.putExtra("route_map", routeDTO.getMapURL());
            setResult(-1, intent);
            finish();
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
        this.f4841k = ((AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        if (this.f4841k >= this.f4835e.getCount()) {
            return true;
        }
        this.f4838h = (RouteDTO) this.f4835e.getItem(this.f4841k);
        if (this.f4838h == null) {
            return true;
        }
        Intent intent;
        switch (menuItem.getItemId()) {
            case C1373R.id.route_self_route_edit:
                intent = new Intent(this, RoutePlanActivity.class);
                intent.putExtra("route_id", String.valueOf(this.f4838h.getId()));
                startActivity(intent);
                C2580w.a(this, "编辑我的路线", null);
                break;
            case C1373R.id.route_self_route_rename:
                this.f4840j = this.f4838h.getName();
                intent = new Intent(this, BaseEditTextActivity.class);
                intent.putExtra("value", this.f4840j);
                startActivityForResult(intent, 8);
                C2580w.a(this, "重命名我的路线", null);
                break;
            case C1373R.id.route_self_route_delete:
                Object id = this.f4838h.getId();
                if (!TextUtils.isEmpty(id)) {
                    m6214a(id, this.f4841k);
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
                startActivity(new Intent(this, RoutePlanActivity.class));
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str != null && str.equals("use_route_id")) {
            String string = this.f4837g.getString("use_route_id", "");
            for (RouteDTO routeDTO : this.f4834d) {
                if (string.equals(routeDTO.getId())) {
                    routeDTO.setUse(true);
                } else {
                    routeDTO.setUse(false);
                }
            }
            this.f4835e.notifyDataSetChanged();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 8:
                if (-1 == i2) {
                    String string = intent.getExtras().getString("value");
                    this.f4838h.setName(string);
                    m6213a(string);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m6212a() {
        this.f4839i = new C1802i(this, getString(C1373R.string.str_loading), true);
        getAsyncTaskQueue().a(new ClubActRouteSelfActivity$1(this), new String[0]);
    }

    /* renamed from: a */
    private void m6214a(String str, int i) {
        this.f4839i = new C1802i(this, getString(C1373R.string.loading_msg_deleted), false);
        getAsyncTaskQueue().a(new ClubActRouteSelfActivity$2(this, i), new String[]{str});
    }

    /* renamed from: a */
    private void m6213a(String str) {
        if (!TextUtils.isEmpty(str) && !this.f4840j.equals(str)) {
            getAsyncTaskQueue().a(new ClubActRouteSelfActivity$3(this), new String[]{str});
        }
    }
}
