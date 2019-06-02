package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "新成员排名")
@C1459b(a = 2130903153)
public class MemberRankingActivity extends SessionFragmentActivity implements OnClickListener {
    /* renamed from: a */
    public static String f5304a;
    @C1458a(a = 2131755546)
    /* renamed from: b */
    private TextView f5305b;
    @C1458a(a = 2131755548)
    /* renamed from: c */
    private TextView f5306c;
    @C1458a(a = 2131755545)
    /* renamed from: d */
    private RelativeLayout f5307d;
    /* renamed from: e */
    private FragmentManager f5308e;
    /* renamed from: f */
    private MonthMemberRankFrag f5309f;
    /* renamed from: g */
    private TotalMemberRankFrag f5310g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        Intent intent = getIntent();
        if (intent != null) {
            f5304a = intent.getStringExtra("club_id");
        }
        this.f5308e = getSupportFragmentManager();
        this.f5305b.setSelected(true);
        m6608a(1);
        this.f5305b.setOnClickListener(this);
        this.f5306c.setOnClickListener(this);
        this.f5307d.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_back:
                finish();
                return;
            case C1373R.id.tv_club_rank_month_rank_list:
                m6608a(1);
                return;
            case C1373R.id.tv_club_rank_total_rank_list:
                m6608a(2);
                return;
            default:
                return;
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    public void m6608a(int i) {
        FragmentTransaction beginTransaction = this.f5308e.beginTransaction();
        m6609a(beginTransaction);
        switch (i) {
            case 1:
                this.f5305b.setSelected(true);
                this.f5306c.setSelected(false);
                if (this.f5309f == null) {
                    this.f5309f = new MonthMemberRankFrag();
                    beginTransaction.add((int) C1373R.id.frag_container, this.f5309f);
                    break;
                }
                beginTransaction.show(this.f5309f);
                break;
            case 2:
                this.f5306c.setSelected(true);
                this.f5305b.setSelected(false);
                if (this.f5310g == null) {
                    this.f5310g = new TotalMemberRankFrag();
                    beginTransaction.add((int) C1373R.id.frag_container, this.f5310g);
                    break;
                }
                beginTransaction.show(this.f5310g);
                break;
        }
        beginTransaction.commit();
    }

    /* renamed from: a */
    public void m6609a(FragmentTransaction fragmentTransaction) {
        if (this.f5309f != null) {
            fragmentTransaction.hide(this.f5309f);
        }
        if (this.f5310g != null) {
            fragmentTransaction.hide(this.f5310g);
        }
    }
}
