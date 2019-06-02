package com.beastbikes.android.modules.cycling.ranking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.LinearLayout;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.ui.C1563a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.cycling.ranking.p067a.C2172b;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.modules.user.ui.ProfileFragment$a;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;
import rx.subscriptions.CompositeSubscription;

@C1459b(a = 2130903374)
public class WeeklyRankFragment extends SessionFragment implements C1371a, C2534b, C2631b {
    @C1458a(a = 2131755444)
    /* renamed from: a */
    private LinearLayout f5387a;
    /* renamed from: b */
    private List<RankDTO> f5388b;
    /* renamed from: c */
    private C2638d f5389c;
    /* renamed from: d */
    private C2183a f5390d;
    /* renamed from: e */
    private C2172b f5391e;
    /* renamed from: f */
    private RankDTO f5392f = new RankDTO();
    /* renamed from: g */
    private int f5393g;
    /* renamed from: h */
    private int f5394h = 50;
    /* renamed from: i */
    private int f5395i = 2;
    /* renamed from: j */
    private boolean f5396j = true;
    /* renamed from: k */
    private String f5397k = "";
    /* renamed from: l */
    private CompositeSubscription f5398l;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5397k = (String) getArguments().get("beast.rank_geo");
        this.f5388b = new ArrayList();
        this.f5391e = new C2172b(getActivity());
        this.f5390d = new C2183a(getActivity(), this);
        this.f5389c = new C2638d(getActivity(), this.f5387a, this.f5388b, 1);
        this.f5389c.setRecyclerCallBack(this);
        this.f5389c.setAdapter(this.f5390d);
        this.f5393g = 1;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f5392f.setNickname(currentUser.getDisplayName());
            this.f5392f.setCity(currentUser.getCity());
            this.f5392f.setClubName(currentUser.getClubName());
            this.f5392f.setAvatarUrl(currentUser.getAvatar());
            this.f5389c.setHeaderDate(this.f5392f);
        }
        this.f5398l = new CompositeSubscription();
        this.f5398l.add(C2574s.a().a(ProfileFragment$a.class).a(new WeeklyRankFragment$1(this)));
        this.f5398l.add(C2574s.a().a(C1563a.class).a(new WeeklyRankFragment$2(this)));
        m6703d();
        m6700c();
    }

    public void onDestroy() {
        super.onDestroy();
        this.f5398l.clear();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /* renamed from: a */
    public void m6710a() {
        m6700c();
        this.f5393g = 1;
        this.f5389c.setCanLoadMore(true);
        this.f5396j = true;
        this.f5389c.setHasFooter(true);
        m6703d();
    }

    public void a_() {
        if (this.f5388b.size() < 200) {
            this.f5393g++;
            this.f5389c.setHasFooter(true);
            m6703d();
        }
    }

    /* renamed from: a */
    public void m6711a(ViewHolder viewHolder, int i) {
        if (this.f5388b != null && this.f5388b.size() > 0) {
            RankDTO rankDTO = (RankDTO) this.f5388b.get(i);
            if (rankDTO != null) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("user_id", rankDTO.getUserId());
                intent.putExtra("nick_name", rankDTO.getNickname());
                intent.putExtra("avatar", rankDTO.getAvatarUrl());
                intent.putExtra("remarks", rankDTO.getRemarks());
                getActivity().startActivity(intent);
            }
        }
    }

    /* renamed from: b */
    public void m6712b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: c */
    private void m6700c() {
        getAsyncTaskQueue().a(new WeeklyRankFragment$3(this), new Void[0]);
    }

    /* renamed from: d */
    private void m6703d() {
        getAsyncTaskQueue().a(new WeeklyRankFragment$4(this), new Void[0]);
    }
}
