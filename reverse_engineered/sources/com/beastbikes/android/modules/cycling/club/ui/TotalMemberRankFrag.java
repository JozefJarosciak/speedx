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
public class TotalMemberRankFrag extends SessionFragment implements C2597b {
    @C1458a(a = 2131757201)
    /* renamed from: a */
    private PullRefreshListView f5336a;
    @C1458a(a = 2131757202)
    /* renamed from: b */
    private ImageView f5337b;
    /* renamed from: c */
    private ClubManager f5338c;
    /* renamed from: d */
    private C2184d f5339d;
    /* renamed from: e */
    private List<C2173a> f5340e = new ArrayList();
    /* renamed from: f */
    private int f5341f = 1;
    /* renamed from: g */
    private int f5342g = 50;
    /* renamed from: h */
    private String f5343h = "";

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        BeastBikes beastBikes = (BeastBikes) activity.getApplication();
        this.f5338c = new ClubManager(activity);
        this.f5339d = new C2184d(getActivity(), this, this.f5340e, b());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5336a.setPullRefreshEnable(false);
        this.f5336a.setListViewListener(this);
        this.f5336a.setOnItemClickListener(new TotalMemberRankFrag$1(this));
        this.f5336a.setAdapter(this.f5339d);
        this.f5336a.b(C1373R.color.discover_color3);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f5343h = MemberRankingActivity.f5304a;
        m6637c();
    }

    /* renamed from: a */
    public void m6643a() {
        m6637c();
    }

    public void b_() {
    }

    /* renamed from: c */
    private void m6637c() {
        getAsyncTaskQueue().a(new TotalMemberRankFrag$2(this), new Void[0]);
    }
}
