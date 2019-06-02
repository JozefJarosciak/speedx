package com.beastbikes.android.modules.cycling.sections.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;

@C1459b(a = 2130903184)
@C1460c(a = 2131820549)
public class SectionFiltersActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755978)
    /* renamed from: A */
    private ViewGroup f5729A;
    /* renamed from: B */
    private TextView f5730B;
    /* renamed from: C */
    private TextView f5731C;
    /* renamed from: D */
    private TextView f5732D;
    /* renamed from: E */
    private ImageView f5733E;
    /* renamed from: F */
    private TextView f5734F;
    /* renamed from: G */
    private SeekBar f5735G;
    /* renamed from: H */
    private SectionFiltersActivity$a f5736H;
    /* renamed from: I */
    private SectionFiltersActivity$b f5737I;
    /* renamed from: J */
    private SectionFiltersActivity$c f5738J;
    @C1458a(a = 2131755979)
    /* renamed from: K */
    private TextView f5739K;
    /* renamed from: L */
    private String f5740L = "";
    /* renamed from: M */
    private String f5741M = "";
    /* renamed from: N */
    private String f5742N = "";
    /* renamed from: O */
    private String f5743O = "";
    /* renamed from: P */
    private boolean f5744P = true;
    /* renamed from: Q */
    private ActionBar f5745Q;
    @C1458a(a = 2131755970)
    /* renamed from: a */
    private ViewGroup f5746a;
    /* renamed from: b */
    private TextView f5747b;
    @C1458a(a = 2131755971)
    /* renamed from: c */
    private ViewGroup f5748c;
    /* renamed from: d */
    private TextView f5749d;
    @C1458a(a = 2131755972)
    /* renamed from: e */
    private ViewGroup f5750e;
    /* renamed from: f */
    private TextView f5751f;
    @C1458a(a = 2131755973)
    /* renamed from: g */
    private ViewGroup f5752g;
    /* renamed from: h */
    private TextView f5753h;
    @C1458a(a = 2131755974)
    /* renamed from: i */
    private ViewGroup f5754i;
    /* renamed from: j */
    private TextView f5755j;
    @C1458a(a = 2131755975)
    /* renamed from: k */
    private ViewGroup f5756k;
    /* renamed from: l */
    private TextView f5757l;
    @C1458a(a = 2131755976)
    /* renamed from: m */
    private ViewGroup f5758m;
    /* renamed from: n */
    private TextView f5759n;
    /* renamed from: o */
    private TextView f5760o;
    /* renamed from: p */
    private TextView f5761p;
    /* renamed from: q */
    private ImageView f5762q;
    /* renamed from: r */
    private TextView f5763r;
    /* renamed from: s */
    private SeekBar f5764s;
    @C1458a(a = 2131755977)
    /* renamed from: t */
    private ViewGroup f5765t;
    /* renamed from: u */
    private TextView f5766u;
    /* renamed from: v */
    private TextView f5767v;
    /* renamed from: w */
    private TextView f5768w;
    /* renamed from: x */
    private ImageView f5769x;
    /* renamed from: y */
    private TextView f5770y;
    /* renamed from: z */
    private SeekBar f5771z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f5745Q = getSupportActionBar();
        if (this.f5745Q != null) {
            this.f5745Q.setDisplayHomeAsUpEnabled(true);
        }
        this.f5744P = C1849a.b(this);
        m6988a();
    }

    /* renamed from: a */
    private void m6988a() {
        this.f5747b = (TextView) this.f5746a.findViewById(C1373R.id.item_section_filter_difficulty_tv);
        this.f5749d = (TextView) this.f5748c.findViewById(C1373R.id.item_section_filter_difficulty_tv);
        this.f5751f = (TextView) this.f5750e.findViewById(C1373R.id.item_section_filter_difficulty_tv);
        this.f5753h = (TextView) this.f5752g.findViewById(C1373R.id.item_section_filter_difficulty_tv);
        this.f5755j = (TextView) this.f5754i.findViewById(C1373R.id.item_section_filter_difficulty_tv);
        this.f5757l = (TextView) this.f5756k.findViewById(C1373R.id.item_section_filter_difficulty_tv);
        this.f5747b.setText(getResources().getString(C1373R.string.speedx_one_star));
        this.f5749d.setText(getResources().getString(C1373R.string.speedx_two_star));
        this.f5751f.setText(getResources().getString(C1373R.string.speedx_three_star));
        this.f5753h.setText(getResources().getString(C1373R.string.speedx_four_star));
        this.f5755j.setText(getResources().getString(C1373R.string.speedx_five_star));
        this.f5757l.setText(getResources().getString(C1373R.string.speedx_six_star));
        this.f5747b.setOnClickListener(new SectionFiltersActivity$1(this));
        this.f5749d.setOnClickListener(new SectionFiltersActivity$2(this));
        this.f5751f.setOnClickListener(new SectionFiltersActivity$3(this));
        this.f5753h.setOnClickListener(new SectionFiltersActivity$4(this));
        this.f5755j.setOnClickListener(new SectionFiltersActivity$5(this));
        this.f5756k.setOnClickListener(new SectionFiltersActivity$6(this));
        this.f5759n = (TextView) this.f5758m.findViewById(C1373R.id.activity_section_filters_seekbar_title);
        this.f5766u = (TextView) this.f5765t.findViewById(C1373R.id.activity_section_filters_seekbar_title);
        this.f5730B = (TextView) this.f5729A.findViewById(C1373R.id.activity_section_filters_seekbar_title);
        this.f5760o = (TextView) this.f5758m.findViewById(C1373R.id.activity_section_filters_seekbar_unit);
        this.f5767v = (TextView) this.f5765t.findViewById(C1373R.id.activity_section_filters_seekbar_unit);
        this.f5731C = (TextView) this.f5729A.findViewById(C1373R.id.activity_section_filters_seekbar_unit);
        this.f5761p = (TextView) this.f5758m.findViewById(C1373R.id.activity_section_filters_seekbar_value);
        this.f5768w = (TextView) this.f5765t.findViewById(C1373R.id.activity_section_filters_seekbar_value);
        this.f5732D = (TextView) this.f5729A.findViewById(C1373R.id.activity_section_filters_seekbar_value);
        this.f5762q = (ImageView) this.f5758m.findViewById(C1373R.id.activity_section_filters_seekbar_icon);
        this.f5769x = (ImageView) this.f5765t.findViewById(C1373R.id.activity_section_filters_seekbar_icon);
        this.f5733E = (ImageView) this.f5729A.findViewById(C1373R.id.activity_section_filters_seekbar_icon);
        this.f5763r = (TextView) this.f5758m.findViewById(C1373R.id.section_filter_seekbar_graduation);
        this.f5770y = (TextView) this.f5765t.findViewById(C1373R.id.section_filter_seekbar_graduation);
        this.f5734F = (TextView) this.f5729A.findViewById(C1373R.id.section_filter_seekbar_graduation);
        this.f5764s = (SeekBar) this.f5758m.findViewById(C1373R.id.section_filter_seekbar);
        this.f5771z = (SeekBar) this.f5765t.findViewById(C1373R.id.section_filter_seekbar);
        this.f5735G = (SeekBar) this.f5729A.findViewById(C1373R.id.section_filter_seekbar);
        this.f5759n.setText(getResources().getString(C1373R.string.str_cycling_distance_with_unit_km));
        this.f5766u.setText(getResources().getString(C1373R.string.str_elevation_diff));
        this.f5730B.setText(getResources().getString(C1373R.string.str_slope));
        this.f5760o.setText(getResources().getString(C1373R.string.kilometre));
        this.f5767v.setText(getResources().getString(C1373R.string.metre));
        this.f5731C.setText(getResources().getString(C1373R.string.degree));
        this.f5763r.setText("500");
        this.f5770y.setText("300");
        this.f5734F.setText("20");
        this.f5762q.setImageResource(C1373R.drawable.ic_section_filter_distance);
        this.f5769x.setImageResource(C1373R.drawable.ic_section_filter_altitude_difference);
        this.f5733E.setImageResource(C1373R.drawable.ic_section_filter_slopes);
        this.f5736H = new SectionFiltersActivity$a(this);
        this.f5737I = new SectionFiltersActivity$b(this);
        this.f5738J = new SectionFiltersActivity$c(this);
        this.f5764s.setOnSeekBarChangeListener(this.f5736H);
        this.f5771z.setOnSeekBarChangeListener(this.f5737I);
        this.f5735G.setOnSeekBarChangeListener(this.f5738J);
        this.f5739K.setOnClickListener(this);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.layout_activity_section_filter_submit:
                Intent intent = getIntent();
                intent.putExtra("section_difficult", this.f5740L);
                intent.putExtra("section_distance", this.f5741M);
                intent.putExtra("section_altdiff", this.f5742N);
                intent.putExtra("section_slope", this.f5743O);
                setResult(-1, intent);
                finish();
                return;
            default:
                return;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.activity_section_filters_reset) {
            this.f5764s.setProgress(0);
            this.f5771z.setProgress(0);
            this.f5735G.setProgress(0);
            m6992b();
            this.f5740L = "";
            this.f5741M = "";
            this.f5742N = "";
            this.f5743O = "";
            Intent intent = getIntent();
            intent.putExtra("section_difficult", this.f5740L);
            intent.putExtra("section_distance", this.f5741M);
            intent.putExtra("section_altdiff", this.f5742N);
            intent.putExtra("section_slope", this.f5743O);
            setResult(-1, intent);
            finish();
        } else if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return true;
    }

    /* renamed from: b */
    private void m6992b() {
        this.f5747b.setSelected(false);
        this.f5749d.setSelected(false);
        this.f5751f.setSelected(false);
        this.f5753h.setSelected(false);
        this.f5755j.setSelected(false);
        this.f5757l.setSelected(false);
        this.f5747b.setTextColor(getResources().getColor(C1373R.color.text_default));
        this.f5749d.setTextColor(getResources().getColor(C1373R.color.text_default));
        this.f5751f.setTextColor(getResources().getColor(C1373R.color.text_default));
        this.f5753h.setTextColor(getResources().getColor(C1373R.color.text_default));
        this.f5755j.setTextColor(getResources().getColor(C1373R.color.text_default));
        this.f5757l.setTextColor(getResources().getColor(C1373R.color.text_default));
    }
}
