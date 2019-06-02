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
public class TotallyRankFragment extends SessionFragment implements C1371a, C2534b, C2631b {
    @C1458a(a = 2131755444)
    /* renamed from: a */
    private LinearLayout f5375a;
    /* renamed from: b */
    private List<RankDTO> f5376b;
    /* renamed from: c */
    private C2638d f5377c;
    /* renamed from: d */
    private C2183a f5378d;
    /* renamed from: e */
    private C2172b f5379e;
    /* renamed from: f */
    private int f5380f;
    /* renamed from: g */
    private int f5381g = 50;
    /* renamed from: h */
    private int f5382h = 0;
    /* renamed from: i */
    private RankDTO f5383i = new RankDTO();
    /* renamed from: j */
    private boolean f5384j = true;
    /* renamed from: k */
    private String f5385k = "";
    /* renamed from: l */
    private CompositeSubscription f5386l;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f5385k = (String) getArguments().get("beast.rank_geo");
        this.f5376b = new ArrayList();
        this.f5379e = new C2172b(getActivity());
        this.f5378d = new C2183a(getActivity(), this);
        this.f5377c = new C2638d(getActivity(), this.f5375a, this.f5376b, 1);
        this.f5377c.setRecyclerCallBack(this);
        this.f5377c.setAdapter(this.f5378d);
        this.f5380f = 1;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f5383i.setNickname(currentUser.getDisplayName());
            this.f5383i.setCity(currentUser.getCity());
            this.f5383i.setClubName(currentUser.getClubName());
            this.f5383i.setAvatarUrl(currentUser.getAvatar());
            this.f5377c.setHeaderDate(this.f5383i);
        }
        m6686d();
        m6683c();
        this.f5386l = new CompositeSubscription();
        this.f5386l.add(C2574s.a().a(ProfileFragment$a.class).a(new TotallyRankFragment$1(this)));
        this.f5386l.add(C2574s.a().a(C1563a.class).a(new TotallyRankFragment$2(this)));
    }

    public void onDestroy() {
        super.onDestroy();
        this.f5386l.clear();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /* renamed from: a */
    public void m6693a() {
        m6683c();
        this.f5380f = 1;
        this.f5377c.setCanLoadMore(true);
        this.f5384j = true;
        this.f5377c.setHasFooter(true);
        m6686d();
    }

    public void a_() {
        if (this.f5376b.size() < 200) {
            this.f5380f++;
            this.f5377c.setHasFooter(true);
            m6686d();
        }
    }

    /* renamed from: a */
    public void m6694a(ViewHolder viewHolder, int i) {
        if (this.f5376b != null && this.f5376b.size() > 0) {
            RankDTO rankDTO = (RankDTO) this.f5376b.get(i);
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
    public void m6695b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: c */
    private void m6683c() {
        getAsyncTaskQueue().a(new TotallyRankFragment$3(this), new Void[0]);
    }

    /* renamed from: d */
    private void m6686d() {
        getAsyncTaskQueue().a(new TotallyRankFragment$4(this), new Void[0]);
    }
}
