package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1808e;
import com.beastbikes.android.C1808e.C1807a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.RecordActionBar.C1952a;
import com.beastbikes.android.modules.cycling.activity.ui.record.RecordSideBar.C1953a;
import com.beastbikes.android.modules.cycling.activity.ui.record.RecordSummary.C1954a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p118d.C1955a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p120a.C1965a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p123c.C1978a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p125f.C1987a;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2024b;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2024b.C1956a;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2027d;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2030e;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.VerticalViewPager;
import com.beastbikes.android.modules.map.MapType;
import com.beastbikes.android.modules.map.SpeedxMap;
import com.beastbikes.android.modules.map.SpeedxMap.C1959a;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2411a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyclingCompletedBase extends SessionFragmentActivity implements OnPageChangeListener, C1807a, C1952a, C1953a, C1954a, C1955a, C1956a {
    /* renamed from: a */
    protected static final Logger f8753a = LoggerFactory.getLogger("CyclingCompletedBase");
    /* renamed from: b */
    protected LinearLayout f8754b;
    /* renamed from: c */
    protected SpeedxMap f8755c;
    /* renamed from: d */
    protected VerticalViewPager f8756d;
    /* renamed from: e */
    protected C1965a f8757e;
    /* renamed from: f */
    protected ArrayList<View> f8758f;
    /* renamed from: g */
    protected boolean f8759g = false;
    /* renamed from: h */
    protected MapType f8760h;
    /* renamed from: i */
    protected SharedPreferences f8761i;
    /* renamed from: j */
    private RecordSideBar f8762j;
    /* renamed from: k */
    private RecordActionBar f8763k;
    /* renamed from: l */
    private C1967a f8764l;
    /* renamed from: m */
    private C1802i f8765m;
    /* renamed from: n */
    private C1808e f8766n;
    /* renamed from: o */
    private PopupWindow f8767o;
    /* renamed from: p */
    private C1963a f8768p;
    /* renamed from: q */
    private ArrayList<C1987a> f8769q = new ArrayList();
    /* renamed from: r */
    private int f8770r;
    /* renamed from: s */
    private ObjectAnimator f8771s;
    /* renamed from: t */
    private ObjectAnimator f8772t;
    /* renamed from: u */
    private boolean f8773u = false;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedBase$1 */
    class C19601 implements C1959a {
        /* renamed from: a */
        final /* synthetic */ CyclingCompletedBase f8790a;

        C19601(CyclingCompletedBase cyclingCompletedBase) {
            this.f8790a = cyclingCompletedBase;
        }

        /* renamed from: a */
        public void mo3330a() {
            Collection a = this.f8790a.mo3303a();
            if (a != null && a.size() > 0) {
                List arrayList = new ArrayList();
                arrayList.addAll(a);
                this.f8790a.f8755c.m11675c();
                this.f8790a.f8755c.m11668a(arrayList);
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedBase$2 */
    class C19612 implements OnDismissListener {
        /* renamed from: a */
        final /* synthetic */ CyclingCompletedBase f8791a;

        C19612(CyclingCompletedBase cyclingCompletedBase) {
            this.f8791a = cyclingCompletedBase;
        }

        public void onDismiss() {
            this.f8791a.f8764l.m10141a();
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedBase$a */
    class C1963a extends Adapter<C1962a> implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ CyclingCompletedBase f8794a;
        /* renamed from: b */
        private ArrayList<C1987a> f8795b;
        /* renamed from: c */
        private C1955a f8796c;

        /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedBase$a$a */
        class C1962a extends ViewHolder {
            /* renamed from: a */
            TextView f8792a;
            /* renamed from: b */
            final /* synthetic */ C1963a f8793b;

            public C1962a(C1963a c1963a, View view) {
                this.f8793b = c1963a;
                super(view);
                this.f8792a = (TextView) view.findViewById(C1373R.id.tv_cycling_report_select_menu_name);
            }
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            m10109a((C1962a) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return m10108a(viewGroup, i);
        }

        public C1963a(CyclingCompletedBase cyclingCompletedBase, ArrayList<C1987a> arrayList) {
            this.f8794a = cyclingCompletedBase;
            this.f8795b = arrayList;
        }

        /* renamed from: a */
        public C1962a m10108a(ViewGroup viewGroup, int i) {
            return new C1962a(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_cycling_report_select_menu_pop, viewGroup, false));
        }

        public int getItemCount() {
            return this.f8795b.size();
        }

        /* renamed from: a */
        public void m10109a(C1962a c1962a, int i) {
            C1987a a = m10107a(i);
            c1962a.f8792a.setText(a.m10230b());
            c1962a.f8792a.setCompoundDrawablesWithIntrinsicBounds(0, a.m10228a(), 0, 0);
            c1962a.f8792a.setEnabled(a.m10232d());
            c1962a.itemView.setTag(Integer.valueOf(a.m10231c()));
            c1962a.itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (this.f8796c != null) {
                this.f8796c.mo3302d(((Integer) view.getTag()).intValue());
            }
        }

        /* renamed from: a */
        private C1987a m10107a(int i) {
            return (C1987a) this.f8795b.get(i);
        }

        /* renamed from: a */
        public void m10110a(C1955a c1955a) {
            this.f8796c = c1955a;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.activity_cycling_completed_base);
        RecordSummary recordSummary = (RecordSummary) findViewById(C1373R.id.record_summary_temp);
        recordSummary.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        this.f8770r = recordSummary.getMeasuredHeight();
        this.f8756d = (VerticalViewPager) findViewById(C1373R.id.vertical_viewpager_cycling_completed);
        this.f8756d.setPageMargin(0);
        this.f8758f = new ArrayList();
        this.f8764l = new C1967a(this, this);
        C2030e c2030e = new C2030e(this);
        c2030e.m10476a(this.f8770r, this.f8756d);
        c2030e.setOnSummaryItemClickListener(this);
        C2027d c2027d = new C2027d(this);
        c2027d.setAdapter(this.f8764l);
        this.f8758f.add(c2030e);
        this.f8758f.add(c2027d);
        this.f8757e = new C1965a(this.f8758f);
        this.f8756d.setAdapter(this.f8757e);
        this.f8762j = (RecordSideBar) findViewById(C1373R.id.record_side_bar);
        this.f8763k = (RecordActionBar) findViewById(C1373R.id.record_action_bar);
        this.f8755c = (SpeedxMap) findViewById(C1373R.id.speedx_map_cycling_completed);
        this.f8754b = (LinearLayout) findViewById(C1373R.id.record_share_view);
        this.f8756d.setOnPageChangeListener(this);
        this.f8762j.setOnSideBarItemClickListener(this);
        this.f8763k.setItemClickListener(this);
        this.f8761i = PreferenceManager.getDefaultSharedPreferences(this);
        this.f8766n = new C1808e(new Handler(getMainLooper()), this);
        this.f8766n.m9516a((C1807a) this);
        this.f8769q.add(new C1987a(C1373R.drawable.ic_chart_speed_in_pop, getString(C1373R.string.str_speed), 1));
        this.f8769q.add(new C1987a(C1373R.drawable.ic_chart_elevation_in_pop, getString(C1373R.string.str_elevation), 2));
        this.f8769q.add(new C1987a(C1373R.drawable.selector_cycling_report_menu_power, getString(C1373R.string.str_power), 6));
        this.f8769q.add(new C1987a(C1373R.drawable.selector_cycling_report_menu_power, getString(C1373R.string.str_power_distribution), 7));
        this.f8769q.add(new C1987a(C1373R.drawable.selector_cycling_report_menu_heart_rate, getString(C1373R.string.str_heart_rate), 4));
        this.f8769q.add(new C1987a(C1373R.drawable.selector_cycling_report_menu_heart_rate, getString(C1373R.string.str_heart_rate_zone), 5));
        this.f8769q.add(new C1987a(C1373R.drawable.selector_cycling_report_menu_cadence, getString(C1373R.string.str_cadence), 3));
    }

    /* renamed from: a */
    public List<C2411a> mo3303a() {
        return null;
    }

    /* renamed from: j */
    protected void m10022j() {
        if (((BeastBikes) getApplication()).f() || this.f8759g) {
            this.f8760h = MapType.MapBox;
        } else if (C1849a.m9641a()) {
            this.f8760h = MapType.BaiDu;
        } else {
            this.f8760h = MapType.Google;
        }
        this.f8755c.m11664a(this.f8760h, this, this.f8759g, null, new C19601(this));
        this.f8755c.m11660a(null);
    }

    protected void onResume() {
        super.onResume();
        this.f8755c.m11675c();
    }

    protected void onPause() {
        super.onPause();
        this.f8755c.m11676d();
    }

    public void onDestroy() {
        this.f8755c.m11682j();
        this.f8755c.m11677e();
        this.f8766n.m9515a();
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

    /* renamed from: a */
    public void mo3297a(int i) {
        switch (i) {
            case C1373R.id.record_side_btn_zoom:
                C2030e c2030e = (C2030e) this.f8758f.get(0);
                if (this.f8773u) {
                    c2030e.m10478b();
                    m10025m();
                    this.f8773u = false;
                    return;
                }
                c2030e.m10475a();
                m10024l();
                this.f8773u = true;
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    public void mo3300b(int i) {
        switch (i) {
            case C1373R.id.action_bar_back:
                finish();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void mo3299a(String str) {
    }

    /* renamed from: a */
    protected void m10011a(boolean z, ActivityDTO activityDTO, boolean z2) {
        if (this.f8764l != null) {
            this.f8764l.m10148a(z, activityDTO, z2);
        }
        if (activityDTO.getMaxPower() <= 0.0d) {
            ((C1987a) this.f8769q.get(2)).m10229a(false);
            ((C1987a) this.f8769q.get(3)).m10229a(false);
        } else {
            ((C1987a) this.f8769q.get(2)).m10229a(true);
            ((C1987a) this.f8769q.get(3)).m10229a(true);
        }
        if (activityDTO.getMaxCardiacRate() <= 0.0d) {
            ((C1987a) this.f8769q.get(4)).m10229a(false);
            ((C1987a) this.f8769q.get(5)).m10229a(false);
        } else {
            ((C1987a) this.f8769q.get(4)).m10229a(true);
            ((C1987a) this.f8769q.get(5)).m10229a(true);
        }
        if (activityDTO.getMaxCadence() <= 0.0d) {
            ((C1987a) this.f8769q.get(6)).m10229a(false);
        } else {
            ((C1987a) this.f8769q.get(6)).m10229a(true);
        }
        if (this.f8768p != null) {
            this.f8768p.notifyDataSetChanged();
        }
        ((C2027d) this.f8758f.get(1)).m10471a(activityDTO);
        m10018c(activityDTO);
        m10020d(activityDTO);
        m10021e(activityDTO);
    }

    /* renamed from: c */
    protected void m10018c(ActivityDTO activityDTO) {
        ((C2030e) this.f8758f.get(0)).m10477a(activityDTO);
    }

    /* renamed from: d */
    protected void m10020d(ActivityDTO activityDTO) {
        if (this.f8763k != null) {
            this.f8763k.m10119a(activityDTO);
        }
    }

    /* renamed from: e */
    protected void m10021e(ActivityDTO activityDTO) {
        if (this.f8762j != null) {
            this.f8762j.m10131a(activityDTO);
        }
    }

    /* renamed from: a */
    protected void m10005a(double d, List<Double> list) {
        if (this.f8764l != null) {
            this.f8764l.m10142a(d, (List) list);
        }
    }

    /* renamed from: a */
    protected void m10010a(List<Double> list, double d, double d2) {
        if (this.f8764l != null) {
            this.f8764l.m10147a((List) list, d, d2);
        }
    }

    /* renamed from: a */
    protected void m10008a(ActivityDTO activityDTO, int i, List<Double> list) {
        if (this.f8764l != null) {
            this.f8764l.m10143a(activityDTO, i, (List) list);
        }
    }

    /* renamed from: b */
    protected void m10016b(List<Double> list) {
        if (this.f8764l != null) {
            this.f8764l.m10146a((List) list);
        }
    }

    /* renamed from: b */
    protected void m10014b(ActivityDTO activityDTO, ArrayList<Double> arrayList) {
        if (this.f8764l != null) {
            this.f8764l.m10144a(activityDTO, (ArrayList) arrayList);
        }
    }

    /* renamed from: b */
    protected void m10015b(ActivityDTO activityDTO, List<C2411a> list, ArrayList<C2411a> arrayList) {
        if (this.f8764l != null) {
            this.f8764l.m10145a(activityDTO, (List) list, (ArrayList) arrayList);
        }
    }

    /* renamed from: a */
    protected void m10012a(boolean z, String str) {
        if (getWindow() != null && !isFinishing()) {
            if (this.f8765m == null) {
                this.f8765m = new C1802i((Context) this, str, z);
            }
            if (!this.f8765m.isShowing()) {
                this.f8765m.show();
            }
        }
    }

    /* renamed from: k */
    protected void m10023k() {
        if (this.f8765m != null && !isFinishing() && getWindow() != null) {
            if (this.f8765m.isShowing()) {
                this.f8765m.dismiss();
            }
            this.f8765m = null;
        }
    }

    /* renamed from: a */
    public void mo3298a(View view, View view2) {
        if (this.f8767o == null) {
            View inflate = LayoutInflater.from(this).inflate(C1373R.layout.layout_cycling_report_select_pop, null);
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C1373R.id.recyclerView_cycling_report_select_pop);
            LayoutManager gridLayoutManager = new GridLayoutManager(this, 3, 1, false);
            gridLayoutManager.setSpanCount(3);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(new C1978a(getResources().getDimensionPixelSize(C1373R.dimen.dimen_1), Color.parseColor("#171717")));
            recyclerView.setHasFixedSize(true);
            this.f8768p = new C1963a(this, this.f8769q);
            recyclerView.setAdapter(this.f8768p);
            this.f8768p.m10110a((C1955a) this);
            this.f8767o = new PopupWindow(inflate, -1, -2);
            this.f8767o.setTouchable(true);
            this.f8767o.setFocusable(true);
            this.f8767o.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#222222")));
            this.f8767o.setOutsideTouchable(true);
            this.f8767o.setOnDismissListener(new C19612(this));
        }
        if (this.f8767o.isShowing()) {
            this.f8767o.dismiss();
        } else {
            this.f8767o.showAsDropDown(view, 0, 0);
        }
    }

    /* renamed from: d */
    public void mo3302d(int i) {
        this.f8767o.dismiss();
        ((C2027d) this.f8758f.get(1)).setCurrentPosition(this.f8764l.m10140a(i));
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.f8763k.m10118a(0, i);
        this.f8762j.m10130a(0, i);
    }

    public void onPageScrollStateChanged(int i) {
        this.f8763k.m10118a(0, this.f8756d.getCurrentItem());
        this.f8762j.m10130a(0, this.f8756d.getCurrentItem());
    }

    /* renamed from: c */
    public void mo3301c(int i) {
    }

    /* renamed from: l */
    public void m10024l() {
        if (this.f8772t == null) {
            this.f8772t = ObjectAnimator.ofFloat(this.f8762j, "translationY", new float[]{(float) (this.f8770r - 50)}).setDuration(500);
        }
        this.f8772t.start();
    }

    /* renamed from: m */
    public void m10025m() {
        if (this.f8771s == null) {
            this.f8771s = ObjectAnimator.ofFloat(this.f8762j, "translationY", new float[]{0.0f}).setDuration(500);
        }
        this.f8771s.start();
    }

    /* renamed from: n */
    public ArrayList<C2024b> m10026n() {
        if (this.f8764l == null) {
            return null;
        }
        return this.f8764l.m10149b();
    }

    /* renamed from: o */
    public void m10027o() {
        if (this.f8764l != null) {
            this.f8764l.m10150c();
        }
    }
}
