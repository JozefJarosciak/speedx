package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.dto.ClubRankBean;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import org.apache.commons.cli.HelpFormatter;

@C1457a(a = "新俱乐部排行")
@C1459b(a = 2130903102)
public class ClubRankActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755544)
    /* renamed from: a */
    private ImageView f5270a;
    @C1458a(a = 2131755546)
    /* renamed from: b */
    private TextView f5271b;
    @C1458a(a = 2131755547)
    /* renamed from: c */
    private TextView f5272c;
    @C1458a(a = 2131755548)
    /* renamed from: d */
    private TextView f5273d;
    @C1458a(a = 2131755545)
    /* renamed from: e */
    private RelativeLayout f5274e;
    @C1458a(a = 2131755549)
    /* renamed from: f */
    private CircleImageView f5275f;
    @C1458a(a = 2131755550)
    /* renamed from: g */
    private TextView f5276g;
    @C1458a(a = 2131755551)
    /* renamed from: h */
    private TextView f5277h;
    @C1458a(a = 2131755554)
    /* renamed from: i */
    private TextView f5278i;
    @C1458a(a = 2131755556)
    /* renamed from: j */
    private TextView f5279j;
    /* renamed from: k */
    private FragmentManager f5280k;
    /* renamed from: l */
    private ClubRankFrag f5281l;
    /* renamed from: m */
    private ClubRankFrag f5282m;
    /* renamed from: n */
    private ClubRankFrag f5283n;
    /* renamed from: o */
    private ClubInfoCompact f5284o;
    /* renamed from: p */
    private ClubManager f5285p;
    /* renamed from: q */
    private boolean f5286q = false;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubRankActivity$b */
    private static class C1411b extends ViewHolder<ClubRankBean> {
        @C1458a(a = 2131757718)
        /* renamed from: a */
        private TextView f5264a;
        @C1458a(a = 2131757719)
        /* renamed from: b */
        private CircleImageView f5265b;
        @C1458a(a = 2131757720)
        /* renamed from: c */
        private TextView f5266c;
        @C1458a(a = 2131757721)
        /* renamed from: d */
        private TextView f5267d;
        @C1458a(a = 2131757722)
        /* renamed from: e */
        private TextView f5268e;
        @C1458a(a = 2131757723)
        /* renamed from: f */
        private TextView f5269f;

        public /* synthetic */ void bind(Object obj) {
            m6580a((ClubRankBean) obj);
        }

        protected C1411b(View view) {
            super(view);
        }

        /* renamed from: a */
        public void m6580a(ClubRankBean clubRankBean) {
            if (clubRankBean != null) {
                switch (clubRankBean.getOrdinal()) {
                    case 1:
                        this.f5264a.setBackgroundResource(C1373R.drawable.ordinal_bg_1);
                        break;
                    case 2:
                        this.f5264a.setBackgroundResource(C1373R.drawable.ordinal_bg_2);
                        break;
                    case 3:
                        this.f5264a.setBackgroundResource(C1373R.drawable.ordinal_bg_3);
                        break;
                    default:
                        this.f5264a.setBackgroundResource(C1373R.drawable.transparent);
                        break;
                }
                this.f5264a.setText(String.valueOf(clubRankBean.getOrdinal()));
                this.f5266c.setText(clubRankBean.getName());
                if (TextUtils.isEmpty(clubRankBean.getLogo())) {
                    this.f5265b.setImageResource(C1373R.drawable.ic_avatar_club);
                } else {
                    Picasso.with(getContext()).load(clubRankBean.getLogo()).fit().error((int) C1373R.drawable.ic_avatar_club).placeholder((int) C1373R.drawable.ic_avatar_club).centerCrop().into(this.f5265b);
                }
                StringBuilder stringBuilder = new StringBuilder("");
                if (!TextUtils.isEmpty(clubRankBean.getCity())) {
                    stringBuilder.append(clubRankBean.getCity());
                }
                CharSequence stringBuilder2 = stringBuilder.toString();
                if (TextUtils.isEmpty(stringBuilder2) || stringBuilder2.equals("null")) {
                    this.f5267d.setVisibility(4);
                } else {
                    this.f5267d.setText(stringBuilder2);
                }
                this.f5268e.setVisibility(0);
                if (C1849a.b(getContext())) {
                    this.f5269f.setText(getContext().getResources().getText(C1373R.string.kilometre));
                    this.f5268e.setText(Math.round(clubRankBean.getMilestone() / 1000.0d) + "");
                } else {
                    this.f5269f.setText(getContext().getResources().getText(C1373R.string.str_mileage_unit_mile));
                    this.f5268e.setText(Math.round(C1849a.a(clubRankBean.getMilestone() / 1000.0d)) + "");
                }
                this.f5269f.setVisibility(0);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        if (getIntent() != null) {
            this.f5284o = (ClubInfoCompact) getIntent().getSerializableExtra("club_info");
            if (this.f5284o != null) {
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser == null || TextUtils.isEmpty(currentUser.getClubId())) {
                    m6584b();
                } else {
                    this.f5286q = currentUser.getClubId().equals(this.f5284o.getObjectId());
                    m6581a();
                }
            }
        }
        this.f5270a.setImageResource(C1373R.drawable.bg_clubrankingtitle);
        this.f5285p = new ClubManager(this);
        m6585b(1);
        this.f5280k = getSupportFragmentManager();
        this.f5271b.setSelected(true);
        m6588a(1);
        this.f5271b.setOnClickListener(this);
        this.f5272c.setOnClickListener(this);
        this.f5273d.setOnClickListener(this);
        this.f5274e.setOnClickListener(this);
        this.f5279j.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_back:
                finish();
                return;
            case C1373R.id.tv_club_rank_month_rank_list:
                m6588a(1);
                m6585b(1);
                return;
            case C1373R.id.tv_club_rank_year_rank_list:
                m6588a(3);
                m6585b(2);
                return;
            case C1373R.id.tv_club_rank_total_rank_list:
                m6588a(2);
                m6585b(0);
                return;
            case C1373R.id.activity_club_rank_club_rank_view_all_tv:
                Intent intent = new Intent(this, ClubDiscoverActivity.class);
                if (this.f5284o != null && (this.f5284o.getStatus() == 4 || this.f5284o.getStatus() == 1)) {
                    intent.putExtra(ClubDiscoverActivity.f5017a, false);
                }
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: b */
    private void m6585b(int i) {
        getAsyncTaskQueue().a(new ClubRankActivity$1(this, i), new Integer[0]);
    }

    /* renamed from: a */
    private void m6581a() {
        if (TextUtils.isEmpty(this.f5284o.getLogo())) {
            this.f5275f.setImageResource(C1373R.drawable.ic_avatar_club);
        } else {
            Picasso.with(this).load(this.f5284o.getLogo()).fit().error((int) C1373R.drawable.ic_avatar_club).placeholder((int) C1373R.drawable.ic_avatar_club).centerCrop().into(this.f5275f);
        }
        if (!TextUtils.isEmpty(this.f5284o.getName())) {
            this.f5276g.setText(this.f5284o.getName());
        }
        if (C1849a.b(this)) {
            this.f5277h.setText(Math.round(this.f5284o.getMilestone() / 1000.0d) + "" + getResources().getText(C1373R.string.kilometre));
        } else {
            this.f5277h.setText(Math.round(C1849a.a(this.f5284o.getMilestone() / 1000.0d)) + "" + getResources().getText(C1373R.string.str_mileage_unit_mile));
        }
        this.f5278i.setText(this.f5284o.getRank() + "");
    }

    /* renamed from: b */
    private void m6584b() {
        if (!TextUtils.isEmpty(this.f5284o.getName())) {
            this.f5276g.setText(getResources().getText(C1373R.string.did_not_join_club));
        }
        this.f5277h.setText(HelpFormatter.DEFAULT_OPT_PREFIX);
        this.f5278i.setText(HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    /* renamed from: a */
    public void m6588a(int i) {
        FragmentTransaction beginTransaction = this.f5280k.beginTransaction();
        m6589a(beginTransaction);
        Bundle bundle = new Bundle();
        switch (i) {
            case 1:
                this.f5271b.setSelected(true);
                this.f5272c.setSelected(false);
                this.f5273d.setSelected(false);
                if (this.f5281l == null) {
                    this.f5281l = new ClubRankFrag();
                    bundle.putInt("club_rank_type", 1);
                    this.f5281l.setArguments(bundle);
                    beginTransaction.add((int) C1373R.id.frag_container, this.f5281l);
                    break;
                }
                beginTransaction.show(this.f5281l);
                break;
            case 2:
                this.f5273d.setSelected(true);
                this.f5272c.setSelected(false);
                this.f5271b.setSelected(false);
                if (this.f5283n == null) {
                    this.f5283n = new ClubRankFrag();
                    bundle.putInt("club_rank_type", 0);
                    this.f5283n.setArguments(bundle);
                    beginTransaction.add((int) C1373R.id.frag_container, this.f5283n);
                    break;
                }
                beginTransaction.show(this.f5283n);
                break;
            case 3:
                this.f5273d.setSelected(false);
                this.f5272c.setSelected(true);
                this.f5271b.setSelected(false);
                if (this.f5282m == null) {
                    this.f5282m = new ClubRankFrag();
                    bundle.putInt("club_rank_type", 2);
                    this.f5282m.setArguments(bundle);
                    beginTransaction.add((int) C1373R.id.frag_container, this.f5282m);
                    break;
                }
                beginTransaction.show(this.f5282m);
                break;
        }
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: a */
    public void m6589a(FragmentTransaction fragmentTransaction) {
        if (this.f5281l != null) {
            fragmentTransaction.hide(this.f5281l);
        }
        if (this.f5283n != null) {
            fragmentTransaction.hide(this.f5283n);
        }
    }
}
