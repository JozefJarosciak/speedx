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
public class YearlyRankFragment extends SessionFragment implements C1371a, C2534b, C2631b {
    @C1458a(a = 2131755444)
    /* renamed from: a */
    private LinearLayout f5399a;
    /* renamed from: b */
    private List<RankDTO> f5400b;
    /* renamed from: c */
    private C2638d f5401c;
    /* renamed from: d */
    private C2183a f5402d;
    /* renamed from: e */
    private C2172b f5403e;
    /* renamed from: f */
    private int f5404f;
    /* renamed from: g */
    private int f5405g = 50;
    /* renamed from: h */
    private int f5406h = 3;
    /* renamed from: i */
    private RankDTO f5407i = new RankDTO();
    /* renamed from: j */
    private boolean f5408j = true;
    /* renamed from: k */
    private String f5409k = "";
    /* renamed from: l */
    private CompositeSubscription f5410l;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5409k = (String) getArguments().get("beast.rank_geo");
        this.f5400b = new ArrayList();
        this.f5403e = new C2172b(getActivity());
        this.f5402d = new C2183a(getActivity(), this);
        this.f5401c = new C2638d(getActivity(), this.f5399a, this.f5400b, 1);
        this.f5401c.setRecyclerCallBack(this);
        this.f5401c.setAdapter(this.f5402d);
        this.f5404f = 1;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f5407i.setNickname(currentUser.getDisplayName());
            this.f5407i.setCity(currentUser.getCity());
            this.f5407i.setClubName(currentUser.getClubName());
            this.f5407i.setAvatarUrl(currentUser.getAvatar());
            this.f5401c.setHeaderDate(this.f5407i);
        }
        m6720d();
        m6717c();
        this.f5410l = new CompositeSubscription();
        this.f5410l.add(C2574s.a().a(ProfileFragment$a.class).a(new YearlyRankFragment$1(this)));
        this.f5410l.add(C2574s.a().a(C1563a.class).a(new YearlyRankFragment$2(this)));
    }

    public void onDestroy() {
        super.onDestroy();
        this.f5410l.clear();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /* renamed from: a */
    public void m6727a() {
        m6717c();
        this.f5404f = 1;
        this.f5401c.setCanLoadMore(true);
        this.f5408j = true;
        this.f5401c.setHasFooter(true);
        m6720d();
    }

    public void a_() {
        if (this.f5400b.size() < 200) {
            this.f5404f++;
            this.f5401c.setHasFooter(true);
            m6720d();
        }
    }

    /* renamed from: a */
    public void m6728a(ViewHolder viewHolder, int i) {
        if (this.f5400b != null && this.f5400b.size() > 0) {
            RankDTO rankDTO = (RankDTO) this.f5400b.get(i);
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
    public void m6729b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: c */
    private void m6717c() {
        getAsyncTaskQueue().a(new YearlyRankFragment$3(this), new Void[0]);
    }

    /* renamed from: d */
    private void m6720d() {
        getAsyncTaskQueue().a(new YearlyRankFragment$4(this), new Void[0]);
    }
}
