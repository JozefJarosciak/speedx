package com.beastbikes.android.modules.cycling.club.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.ranking.dto.C2173a;
import com.beastbikes.android.modules.cycling.ranking.ui.C2184d;
import com.beastbikes.android.widget.PullRefreshListView;
import com.beastbikes.android.widget.PullRefreshListView.C2597b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903527)
public class MonthMemberRankFrag extends SessionFragment implements C2597b {
    @C1458a(a = 2131757201)
    /* renamed from: a */
    private PullRefreshListView f5311a;
    @C1458a(a = 2131757202)
    /* renamed from: b */
    private ImageView f5312b;
    /* renamed from: c */
    private ClubManager f5313c;
    /* renamed from: d */
    private C2184d f5314d;
    /* renamed from: e */
    private List<C2173a> f5315e = new ArrayList();
    /* renamed from: f */
    private int f5316f = 1;
    /* renamed from: g */
    private int f5317g = 50;
    /* renamed from: h */
    private String f5318h = "";

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        BeastBikes beastBikes = (BeastBikes) activity.getApplication();
        this.f5313c = new ClubManager(activity);
        this.f5314d = new C2184d(getActivity(), this, this.f5315e, b());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5311a.setPullRefreshEnable(false);
        this.f5311a.setListViewListener(this);
        this.f5311a.setOnItemClickListener(new MonthMemberRankFrag$1(this));
        this.f5311a.setAdapter(this.f5314d);
        this.f5311a.b(C1373R.color.discover_color3);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f5318h = MemberRankingActivity.f5304a;
        m6613c();
    }

    /* renamed from: a */
    public void m6619a() {
        m6613c();
    }

    public void b_() {
    }

    /* renamed from: c */
    private void m6613c() {
        getAsyncTaskQueue().a(new MonthMemberRankFrag$2(this), new Void[0]);
    }
}
