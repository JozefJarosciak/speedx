package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.route.dto.C2189c;
import com.beastbikes.android.modules.cycling.route.dto.C2190d;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.NonScrollListView;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "路线详情")
@C1459b(a = 2130903656)
public class RouteActivity extends SessionFragmentActivity implements OnPageChangeListener, OnClickListener, OnItemClickListener {
    /* renamed from: a */
    public static final Logger f5440a = LoggerFactory.getLogger(RouteActivity.class);
    /* renamed from: A */
    private List<View> f5441A = new ArrayList();
    /* renamed from: B */
    private C2189c f5442B;
    /* renamed from: C */
    private List<C2190d> f5443C = new ArrayList();
    /* renamed from: D */
    private C2185a f5444D;
    /* renamed from: E */
    private RequestQueue f5445E;
    /* renamed from: F */
    private RouteDTO f5446F;
    /* renamed from: G */
    private int f5447G = 0;
    /* renamed from: H */
    private List<String> f5448H = new ArrayList();
    @C1458a(a = 2131757471)
    /* renamed from: b */
    private ViewGroup f5449b;
    @C1458a(a = 2131757482)
    /* renamed from: c */
    private ViewGroup f5450c;
    @C1458a(a = 2131757469)
    /* renamed from: d */
    private ViewPager f5451d;
    @C1458a(a = 2131757468)
    /* renamed from: e */
    private ViewGroup f5452e;
    @C1458a(a = 2131757470)
    /* renamed from: f */
    private ViewGroup f5453f;
    @C1458a(a = 2131757483)
    /* renamed from: g */
    private ImageView f5454g;
    @C1458a(a = 2131757475)
    /* renamed from: h */
    private RatingBar f5455h;
    @C1458a(a = 2131757476)
    /* renamed from: i */
    private RatingBar f5456i;
    @C1458a(a = 2131757477)
    /* renamed from: j */
    private RatingBar f5457j;
    @C1458a(a = 2131757478)
    /* renamed from: k */
    private TextView f5458k;
    @C1458a(a = 2131757479)
    /* renamed from: l */
    private TextView f5459l;
    @C1458a(a = 2131757472)
    /* renamed from: m */
    private LinearLayout f5460m;
    @C1458a(a = 2131757473)
    /* renamed from: n */
    private TextView f5461n;
    @C1458a(a = 2131757474)
    /* renamed from: o */
    private TextView f5462o;
    @C1458a(a = 2131757481)
    /* renamed from: p */
    private TextView f5463p;
    @C1458a(a = 2131757485)
    /* renamed from: q */
    private TextView f5464q;
    @C1458a(a = 2131757484)
    /* renamed from: r */
    private TextView f5465r;
    @C1458a(a = 2131757486)
    /* renamed from: s */
    private NonScrollListView f5466s;
    @C1458a(a = 2131757487)
    /* renamed from: t */
    private EditText f5467t;
    @C1458a(a = 2131757488)
    /* renamed from: u */
    private Button f5468u;
    @C1458a(a = 2131757472)
    /* renamed from: v */
    private ViewGroup f5469v;
    /* renamed from: w */
    private ImageView[] f5470w;
    /* renamed from: x */
    private ImageView f5471x;
    /* renamed from: y */
    private ImageView f5472y;
    /* renamed from: z */
    private RouteActivity$a f5473z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5444D = new C2185a(this);
        this.f5445E = Volley.newRequestQueue(this);
        this.f5445E.start();
        Intent intent = getIntent();
        this.f5446F = (RouteDTO) intent.getSerializableExtra("route");
        String stringExtra = intent.getStringExtra("route_id");
        this.f5450c.setVisibility(0);
        if (this.f5446F != null) {
            setTitle(this.f5446F.getName());
            m6759d(this.f5446F.getId());
            m6751a(this.f5446F.getId());
            if (TextUtils.isEmpty(this.f5446F.getMapURL())) {
                this.f5449b.setVisibility(8);
            } else {
                Picasso.with(this).load(this.f5446F.getMapURL()).fit().centerCrop().error((int) C1373R.drawable.transparent).placeholder((int) C1373R.drawable.transparent).into(this.f5454g, new RouteActivity$1(this));
            }
            this.f5455h.setRating((float) this.f5446F.getDifficultyCoefficient());
            this.f5456i.setRating((float) this.f5446F.getViewCoefficient());
            this.f5457j.setRating((float) this.f5446F.getTrafficCoefficient());
            if (C1849a.b(this)) {
                this.f5458k.setText(String.format("%.0f", new Object[]{Double.valueOf(this.f5446F.getTotalDistance() / 1000.0d)}));
            } else {
                this.f5458k.setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.a(this.f5446F.getTotalDistance()) / 1000.0d)}));
                this.f5459l.setText(getResources().getString(C1373R.string.str_mileage_unit_mile));
            }
            this.f5462o.setText("(" + this.f5446F.getNumberOfFollowers() + ")");
            this.f5463p.setText("      " + Html.fromHtml(this.f5446F.getDescription()));
        }
        if (!TextUtils.isEmpty(stringExtra)) {
            m6759d(stringExtra);
            m6751a(stringExtra);
            m6757c(stringExtra);
        }
        this.f5451d.removeAllViews();
        this.f5453f.removeAllViews();
        this.f5454g.setOnClickListener(this);
        this.f5469v.setOnClickListener(this);
        this.f5464q.setOnClickListener(this);
        this.f5468u.setOnClickListener(this);
        this.f5442B = new C2189c(this.f5443C);
        this.f5466s.setAdapter(this.f5442B);
        this.f5466s.setOnItemClickListener(this);
        this.f5449b.setVisibility(0);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        LayoutParams layoutParams = this.f5452e.getLayoutParams();
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.height = ((int) (((float) displayMetrics.widthPixels) / 1.6842105f)) + 1;
        this.f5452e.setPadding(0, 0, 0, 0);
        this.f5452e.setLayoutParams(layoutParams);
    }

    protected void onResume() {
        super.onResume();
        if (this.f5446F != null) {
            m6757c(this.f5446F.getId());
        }
    }

    /* renamed from: a */
    private void m6749a() {
        if (this.f5446F.isFollowed()) {
            this.f5469v.setBackgroundResource(C1373R.drawable.route_want_bg);
            this.f5461n.setTextColor(getResources().getColor(C1373R.color.route_activity_want_go));
            this.f5461n.setText(C1373R.string.routes_activity_want_go);
            this.f5461n.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(C1373R.drawable.ic_route_activity_want), null, null, null);
            this.f5462o.setTextColor(getResources().getColor(C1373R.color.route_activity_want_go));
        } else {
            this.f5469v.setBackgroundResource(C1373R.drawable.route_wanted_bg);
            this.f5461n.setText(C1373R.string.routes_activity_wanted_go);
            this.f5461n.setTextColor(getResources().getColor(C1373R.color.route_activity_wanted_go));
            this.f5461n.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(C1373R.drawable.ic_route_activity_wanted), null, null, null);
            this.f5462o.setTextColor(getResources().getColor(C1373R.color.route_activity_wanted_go));
        }
        this.f5460m.setVisibility(0);
    }

    protected void onDestroy() {
        this.f5445E.stop();
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < this.f5470w.length; i2++) {
            this.f5470w[i].setBackgroundResource(C1373R.drawable.route_activity_indicator_1);
            if (i != i2) {
                this.f5470w[i2].setBackgroundResource(C1373R.drawable.route_activity_indicator_0);
            }
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.route_activity_want:
                if (this.f5446F.isFollowed()) {
                    Toasts.show(this, C1373R.string.route_activity_comment_followed_msg);
                    return;
                }
                m6754b(this.f5446F.getId());
                C2580w.a(this, "路线想去总次数", null);
                return;
            case C1373R.id.route_activity_line_map:
                intent = new Intent(this, RouteMapActivity.class);
                intent.putExtra("route_id", this.f5446F.getId());
                intent.putExtra("route_distance", this.f5446F.getTotalDistance());
                startActivity(intent);
                C2580w.a(this, "查看精品路线地图详情", null);
                return;
            case C1373R.id.route_activity_comment_all:
                intent = new Intent(this, RouteCommentActivity.class);
                intent.putExtra("route_id", this.f5446F.getId());
                intent.putExtra("route_comment_count", this.f5447G);
                startActivity(intent);
                return;
            case C1373R.id.route_activity_send_comment:
                String trim = this.f5467t.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toasts.show(this, C1373R.string.route_activity_comment_empty_msg);
                    return;
                }
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.f5467t.getWindowToken(), 2);
                this.f5468u.setClickable(false);
                m6761e(trim);
                C2580w.a(this, "路线评论总次数", null);
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C2190d c2190d = (C2190d) adapterView.getAdapter().getItem(i);
        if (c2190d != null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_id", c2190d.a());
            intent.putExtra("avatar", c2190d.f());
            intent.putExtra("nick_name", c2190d.b());
            intent.putExtra("remarks", c2190d.g());
            startActivity(intent);
        }
    }

    /* renamed from: b */
    private void m6753b() {
        this.f5441A = new ArrayList();
        for (int i = 0; i < this.f5448H.size(); i++) {
            this.f5471x = new ImageView(this);
            this.f5471x.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.f5471x.setScaleType(ScaleType.CENTER_CROP);
            this.f5441A.add(this.f5471x);
        }
        this.f5473z = new RouteActivity$a(this, this.f5441A);
        this.f5451d.setAdapter(this.f5473z);
        this.f5451d.setOnPageChangeListener(this);
    }

    /* renamed from: c */
    private void m6756c() {
        this.f5470w = new ImageView[this.f5448H.size()];
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(4, 4, 4, 4);
        for (int i = 0; i < this.f5441A.size(); i++) {
            this.f5472y = new ImageView(this);
            this.f5472y.setLayoutParams(layoutParams);
            this.f5470w[i] = this.f5472y;
            this.f5470w[i].setTag(Integer.valueOf(i));
            this.f5470w[i].setOnClickListener(this);
            if (i == 0) {
                this.f5470w[i].setBackgroundResource(C1373R.drawable.route_activity_indicator_1);
            } else {
                this.f5470w[i].setBackgroundResource(C1373R.drawable.route_activity_indicator_0);
            }
            this.f5453f.addView(this.f5470w[i]);
        }
    }

    /* renamed from: a */
    private void m6751a(String str) {
        getAsyncTaskQueue().a(new RouteActivity$2(this), new String[]{str});
    }

    /* renamed from: b */
    private void m6754b(String str) {
        getAsyncTaskQueue().a(new RouteActivity$3(this), new String[]{str});
    }

    /* renamed from: c */
    private void m6757c(String str) {
        getAsyncTaskQueue().a(new RouteActivity$4(this), new String[]{str});
    }

    /* renamed from: d */
    private void m6759d(String str) {
        getAsyncTaskQueue().a(new RouteActivity$5(this), new String[]{str});
    }

    /* renamed from: e */
    private void m6761e(String str) {
        getAsyncTaskQueue().a(new RouteActivity$6(this), new String[]{this.f5446F.getId(), str});
    }
}
