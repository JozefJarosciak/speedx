package com.beastbikes.android.modules.cycling.club.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubRankBean;
import com.beastbikes.android.widget.PullRefreshListView;
import com.beastbikes.android.widget.PullRefreshListView.C2597b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903527)
public class ClubRankFrag extends SessionFragment implements C2597b {
    @C1458a(a = 2131757201)
    /* renamed from: a */
    private PullRefreshListView f5287a;
    @C1458a(a = 2131757202)
    /* renamed from: b */
    private ImageView f5288b;
    /* renamed from: c */
    private ClubManager f5289c;
    /* renamed from: d */
    private ClubRankActivity$a f5290d;
    /* renamed from: e */
    private List<ClubRankBean> f5291e = new ArrayList();
    /* renamed from: f */
    private int f5292f = 0;
    /* renamed from: g */
    private int f5293g = 1;
    /* renamed from: h */
    private int f5294h = 50;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f5289c = new ClubManager(activity);
        this.f5290d = new ClubRankActivity$a(getActivity(), this.f5291e);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5287a.setPullRefreshEnable(false);
        this.f5287a.setListViewListener(this);
        this.f5287a.setOnItemClickListener(new ClubRankFrag$1(this));
        this.f5287a.setAdapter(this.f5290d);
        this.f5287a.b(C1373R.color.discover_color3);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f5292f = arguments.getInt("club_rank_type");
        }
        m6593c();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* renamed from: a */
    public void m6599a() {
        m6593c();
    }

    public void b_() {
    }

    /* renamed from: c */
    private void m6593c() {
        getAsyncTaskQueue().a(new ClubRankFrag$2(this), new Void[0]);
    }
}
