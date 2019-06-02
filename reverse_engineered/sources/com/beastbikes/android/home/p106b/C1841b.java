package com.beastbikes.android.home.p106b;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import java.util.ArrayList;

/* compiled from: NavigationView */
/* renamed from: com.beastbikes.android.home.b.b */
public class C1841b implements OnItemClickListener {
    /* renamed from: a */
    private ListView f8297a;
    /* renamed from: b */
    private C1840c f8298b;
    /* renamed from: c */
    private DrawerLayout f8299c;
    /* renamed from: d */
    private Activity f8300d;
    /* renamed from: e */
    private C1839b f8301e;
    /* renamed from: f */
    private Toolbar f8302f;
    /* renamed from: g */
    private int f8303g;
    /* renamed from: h */
    private ArrayList<C1838a> f8304h = new ArrayList();

    /* compiled from: NavigationView */
    /* renamed from: com.beastbikes.android.home.b.b$a */
    public class C1838a {
        /* renamed from: a */
        final /* synthetic */ C1841b f8285a;
        /* renamed from: b */
        private int f8286b;
        /* renamed from: c */
        private String f8287c;
        /* renamed from: d */
        private int f8288d;
        /* renamed from: e */
        private int f8289e;
        /* renamed from: f */
        private int f8290f;
        /* renamed from: g */
        private LayoutParams f8291g;
        /* renamed from: h */
        private int f8292h;
        /* renamed from: i */
        private int f8293i;

        public C1838a(C1841b c1841b, int i, String str, int i2) {
            this.f8285a = c1841b;
            this.f8292h = 8;
            this.f8293i = 0;
            this.f8288d = i;
            this.f8287c = str;
            this.f8289e = i2;
            if (i == 0 && TextUtils.isEmpty(str)) {
                this.f8286b = 2;
            } else if (i == 0) {
                this.f8286b = 1;
            } else {
                this.f8286b = 0;
            }
            if (this.f8286b != 2 && TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("you need set a name for a item");
            }
        }

        public C1838a(C1841b c1841b, String str) {
            this(c1841b, 0, str, 0);
        }

        public C1838a(C1841b c1841b) {
            this(c1841b, null);
        }

        /* renamed from: a */
        public int m9610a() {
            return this.f8289e;
        }
    }

    /* compiled from: NavigationView */
    /* renamed from: com.beastbikes.android.home.b.b$b */
    public class C1839b extends BaseAdapter {
        /* renamed from: a */
        final /* synthetic */ C1841b f8294a;
        /* renamed from: b */
        private LayoutInflater f8295b;
        /* renamed from: c */
        private Context f8296c;

        public C1839b(C1841b c1841b, Context context) {
            this.f8294a = c1841b;
            this.f8295b = LayoutInflater.from(context);
            this.f8296c = context;
        }

        public int getCount() {
            return this.f8294a.f8304h.size();
        }

        /* renamed from: a */
        public Object m9611a(int i) {
            if (i > 0) {
                for (int i2 = 0; i2 < this.f8294a.f8304h.size(); i2++) {
                    C1838a c1838a = (C1838a) this.f8294a.f8304h.get(i2);
                    if (c1838a != null && c1838a.m9610a() == i) {
                        return c1838a;
                    }
                }
            }
            return null;
        }

        public Object getItem(int i) {
            if (i < 0 || i >= this.f8294a.f8304h.size()) {
                return null;
            }
            return this.f8294a.f8304h.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public int getItemViewType(int i) {
            if (((C1838a) this.f8294a.f8304h.get(i)) == null) {
                return -1;
            }
            return ((C1838a) this.f8294a.f8304h.get(i)).f8286b;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            C1838a c1838a = (C1838a) this.f8294a.f8304h.get(i);
            if (c1838a == null) {
                return this.f8295b.inflate(C1373R.layout.design_drawer_item_divider, viewGroup, false);
            }
            switch (c1838a.f8286b) {
                case 0:
                    view = this.f8295b.inflate(C1373R.layout.design_drawer_item, viewGroup, false);
                    ((ImageView) view.findViewById(C1373R.id.iv_icon)).setImageResource(c1838a.f8288d);
                    ((TextView) view.findViewById(C1373R.id.tv_title)).setText(c1838a.f8287c);
                    view.setBackgroundResource(this.f8294a.f8303g == c1838a.f8289e ? C1373R.drawable.bg_303030 : C1373R.drawable.transparent);
                    TextView textView = (TextView) view.findViewById(C1373R.id.tv_dot);
                    textView.setBackgroundResource(C1373R.drawable.bg_oval);
                    textView.setTextColor(this.f8294a.f8300d.getResources().getColor(C1373R.color.text_number_color));
                    if (c1838a.m9610a() == C1373R.id.nav_item_cycling) {
                        return view;
                    }
                    textView.setText(c1838a.f8290f > 99 ? "99+" : c1838a.f8290f + "");
                    if (c1838a.f8291g != null) {
                        textView.getLayoutParams().height = c1838a.f8291g.height;
                        textView.getLayoutParams().width = c1838a.f8291g.width;
                    }
                    textView.setVisibility(c1838a.f8292h);
                    return view;
                case 1:
                    View inflate;
                    if (view == null) {
                        inflate = this.f8295b.inflate(C1373R.layout.design_drawer_item_subheader, viewGroup, false);
                    } else {
                        inflate = view;
                    }
                    ((TextView) inflate).setText(c1838a.f8287c);
                    return inflate;
                case 2:
                    if (view == null) {
                        return this.f8295b.inflate(C1373R.layout.design_drawer_item_separator, viewGroup, false);
                    }
                    return view;
                default:
                    return view;
            }
        }
    }

    /* compiled from: NavigationView */
    /* renamed from: com.beastbikes.android.home.b.b$c */
    public interface C1840c {
        /* renamed from: a */
        boolean m9612a(int i);
    }

    public C1841b(Activity activity, ListView listView) {
        this.f8300d = activity;
        this.f8297a = listView;
    }

    /* renamed from: a */
    public void m9621a(C1840c c1840c) {
        this.f8298b = c1840c;
    }

    /* renamed from: a */
    public void m9619a(AppCompatActivity appCompatActivity) {
        if (appCompatActivity == null) {
            throw new RuntimeException("you must be set paramerter of Activity context!!");
        }
        C1398a c1398a = new C1398a(this.f8300d);
        ListView listView = this.f8297a;
        ListAdapter c1839b = new C1839b(this, this.f8300d);
        this.f8301e = c1839b;
        listView.setAdapter(c1839b);
        this.f8297a.setOnItemClickListener(this);
        m9616a();
        this.f8299c = (DrawerLayout) appCompatActivity.findViewById(C1373R.id.drawer_layout);
        this.f8302f = (Toolbar) appCompatActivity.findViewById(C1373R.id.toolbar);
        appCompatActivity.setSupportActionBar(this.f8302f);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        supportActionBar.setHomeAsUpIndicator(C1373R.drawable.ic_menu);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        Object actionBarDrawerToggle = new ActionBarDrawerToggle(appCompatActivity, this.f8299c, (Toolbar) appCompatActivity.findViewById(C1373R.id.toolbar), C1373R.string.navigation_drawer_open, C1373R.string.navigation_drawer_close);
        this.f8299c.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z = false;
        C1838a c1838a = (C1838a) this.f8301e.getItem((int) j);
        if (c1838a == null) {
            c1838a = new C1838a(this);
            c1838a.f8289e = -1;
        }
        if (this.f8298b != null) {
            z = this.f8298b.m9612a(c1838a.m9610a());
        }
        if (z) {
            if (this.f8299c != null) {
                this.f8299c.closeDrawer(GravityCompat.START);
            }
            this.f8303g = c1838a.m9610a();
            this.f8301e.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void m9620a(View view) {
        if (this.f8297a != null) {
            this.f8297a.addHeaderView(view);
        }
    }

    /* renamed from: b */
    public void m9622b(View view) {
        this.f8297a.addFooterView(view);
    }

    /* renamed from: a */
    public void m9617a(int i) {
        if (this.f8298b != null) {
            this.f8303g = i;
            this.f8298b.m9612a(i);
        }
    }

    /* renamed from: a */
    public void m9618a(int i, int i2, LayoutParams layoutParams, int i3) {
        Object a = this.f8301e.m9611a(i);
        if (a != null) {
            C1838a c1838a = (C1838a) a;
            c1838a.f8290f = i2;
            c1838a.f8291g = layoutParams;
            c1838a.f8292h = i3;
        }
        this.f8301e.notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m9616a() {
        this.f8304h.clear();
        this.f8304h.add(new C1838a(this, C1373R.drawable.ic_nav_ranking1, this.f8300d.getString(C1373R.string.ranking_fragment_title), C1373R.id.nav_item_ranking));
        this.f8304h.add(new C1838a(this, C1373R.drawable.ic_nav_club, this.f8300d.getString(C1373R.string.club_info_title), C1373R.id.nav_item_club));
        this.f8304h.add(new C1838a(this, C1373R.drawable.ic_nav_activity, this.f8300d.getString(C1373R.string.str_cycling_events), C1373R.id.nav_item_activity));
        if (C1849a.m9641a()) {
            this.f8304h.add(new C1838a(this, C1373R.drawable.ic_nav_route, this.f8300d.getString(C1373R.string.feedback_issue_7), C1373R.id.nav_item_route));
        }
        this.f8304h.add(new C1838a(this, C1373R.drawable.ic_train_course, this.f8300d.getString(C1373R.string.str_train_course_title), C1373R.id.nav_item_train_course));
        this.f8304h.add(null);
        this.f8304h.add(new C1838a(this, C1373R.drawable.ic_settings, this.f8300d.getString(C1373R.string.str_settings), C1373R.id.nav_item_setting));
        this.f8301e.notifyDataSetChanged();
    }
}
