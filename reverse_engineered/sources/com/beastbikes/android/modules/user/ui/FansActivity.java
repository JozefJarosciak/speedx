package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.C2638d;
import com.beastbikes.android.widget.C2638d.C2631b;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;
import rx.subscriptions.CompositeSubscription;

@C1459b(a = 2130903351)
public class FansActivity extends SessionFragmentActivity implements C2534b, C2631b {
    @C1458a(a = 2131756583)
    /* renamed from: a */
    private LinearLayout f6359a;
    @C1458a(a = 2131756582)
    /* renamed from: b */
    private TextView f6360b;
    /* renamed from: c */
    private C2389c f6361c;
    /* renamed from: d */
    private C2638d f6362d;
    /* renamed from: e */
    private List<FriendDTO> f6363e = new ArrayList();
    /* renamed from: f */
    private int f6364f = 1;
    /* renamed from: g */
    private CompositeSubscription f6365g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6361c = new C2389c(this);
        this.f6362d = new C2638d(this, this.f6359a, this.f6363e, 2);
        this.f6362d.setRecyclerCallBack(this);
        this.f6362d.setAdapter(new FansActivity$a(this, this, this));
        this.f6364f = 1;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getObjectId().equals(m5331p())) {
                this.f6360b.setText(C1373R.string.activity_fans_empty_tip);
            } else {
                this.f6360b.setText(C1373R.string.activity_other_friend_fans_empty_label);
            }
            m7568c();
            this.f6365g = new CompositeSubscription();
            this.f6365g.add(C2574s.a().a(ProfileFragment$a.class).a(new FansActivity$1(this)));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f6365g.clear();
    }

    /* renamed from: a */
    public void m7574a() {
        this.f6364f = 1;
        this.f6362d.setCanLoadMore(true);
        this.f6362d.setHasFooter(true);
        m7568c();
    }

    public void a_() {
        m7568c();
    }

    /* renamed from: a */
    public void m7575a(ViewHolder viewHolder, int i) {
        if (this.f6363e != null && this.f6363e.size() > 0) {
            FriendDTO friendDTO = (FriendDTO) this.f6363e.get(i);
            if (friendDTO != null) {
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("user_id", friendDTO.getFriendId());
                intent.putExtra("nick_name", friendDTO.getNickname());
                intent.putExtra("remarks", friendDTO.getRemarks());
                intent.putExtra("avatar", friendDTO.getAvatar());
                super.startActivity(intent);
            }
        }
    }

    /* renamed from: b */
    public void m7576b(ViewHolder viewHolder, int i) {
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: c */
    private void m7568c() {
        getAsyncTaskQueue().a(new FansActivity$2(this), new String[0]);
    }

    /* renamed from: a */
    private void m7563a(String str, int i) {
        getAsyncTaskQueue().a(new FansActivity$3(this, str, i), new String[0]);
    }

    /* renamed from: b */
    private void m7566b(String str, int i) {
        getAsyncTaskQueue().a(new FansActivity$4(this, str, i), new String[0]);
    }

    /* renamed from: c */
    private void m7570c(String str, int i) {
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.msg_unfollow_prompt_dialog);
        c2621c.b(C1373R.string.cancel, new FansActivity$5(this, c2621c));
        c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new FansActivity$6(this, str, i, c2621c));
        c2621c.a();
    }
}
