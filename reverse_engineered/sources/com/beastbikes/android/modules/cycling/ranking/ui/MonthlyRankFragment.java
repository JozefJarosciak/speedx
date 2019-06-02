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
import com.beastbikes.android.widget.C2638d.C2077a;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;
import rx.subscriptions.CompositeSubscription;

@C1459b(a = 2130903374)
public class MonthlyRankFragment extends SessionFragment implements C1371a, C2534b, C2631b {
    @C1458a(a = 2131755444)
    /* renamed from: a */
    private LinearLayout f5355a;
    /* renamed from: b */
    private List<RankDTO> f5356b;
    /* renamed from: c */
    private String f5357c = "";
    /* renamed from: d */
    private C2638d f5358d;
    /* renamed from: e */
    private C2172b f5359e;
    /* renamed from: f */
    private int f5360f;
    /* renamed from: g */
    private int f5361g = 50;
    /* renamed from: h */
    private int f5362h = 1;
    /* renamed from: i */
    private RankDTO f5363i = new RankDTO();
    /* renamed from: j */
    private boolean f5364j = true;
    /* renamed from: k */
    private CompositeSubscription f5365k;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5357c = (String) getArguments().get("beast.rank_geo");
        this.f5356b = new ArrayList();
        this.f5359e = new C2172b(getActivity());
        C2077a c2183a = new C2183a(getActivity(), this);
        this.f5358d = new C2638d(getActivity(), this.f5355a, this.f5356b, 1);
        this.f5358d.setRecyclerCallBack(this);
        this.f5358d.setAdapter(c2183a);
        this.f5360f = 1;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f5363i.setNickname(currentUser.getDisplayName());
            this.f5363i.setCity(currentUser.getCity());
            this.f5363i.setClubName(currentUser.getClubName());
            this.f5363i.setAvatarUrl(currentUser.getAvatar());
            this.f5358d.setHeaderDate(this.f5363i);
        }
        m6656d();
        m6653c();
        this.f5365k = new CompositeSubscription();
        this.f5365k.add(C2574s.a().a(ProfileFragment$a.class).a(new MonthlyRankFragment$1(this)));
        this.f5365k.add(C2574s.a().a(C1563a.class).a(new MonthlyRankFragment$2(this)));
    }

    public void onDestroy() {
        super.onDestroy();
        this.f5365k.clear();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /* renamed from: a */
    public void m6663a() {
        m6653c();
        this.f5360f = 1;
        this.f5358d.setCanLoadMore(true);
        this.f5364j = true;
        this.f5358d.setHasFooter(true);
        m6656d();
    }

    public void a_() {
        if (this.f5356b.size() < 200) {
            this.f5360f++;
            this.f5358d.setHasFooter(true);
            m6656d();
        }
    }

    /* renamed from: a */
    public void m6664a(ViewHolder viewHolder, int i) {
        if (this.f5356b != null && this.f5356b.size() > 0) {
            RankDTO rankDTO = (RankDTO) this.f5356b.get(i);
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
    public void m6665b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: c */
    private void m6653c() {
        getAsyncTaskQueue().a(new MonthlyRankFragment$3(this), new Void[0]);
    }

    /* renamed from: d */
    private void m6656d() {
        getAsyncTaskQueue().a(new MonthlyRankFragment$4(this), new Void[0]);
    }
}
