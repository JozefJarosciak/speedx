package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903259)
public class ClubMemberRankActivity extends SessionFragmentActivity implements OnItemClickListener {
    @C1458a(a = 2131756331)
    /* renamed from: a */
    private ListView f5189a;
    /* renamed from: b */
    private ClubManager f5190b;
    /* renamed from: c */
    private ClubMemberRankActivity$a f5191c;
    /* renamed from: d */
    private List<RankDTO> f5192d = new ArrayList();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5190b = new ClubManager(this);
        this.f5191c = new ClubMemberRankActivity$a(this, this.f5192d);
        this.f5189a.setAdapter(this.f5191c);
        this.f5189a.setOnItemClickListener(this);
        C2580w.a(this, "查看成员排行", null);
        Intent intent = getIntent();
        if (intent != null) {
            m6521a(intent.getStringExtra("club_id"));
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        RankDTO rankDTO = (RankDTO) this.f5192d.get(i);
        if (rankDTO != null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user_id", rankDTO.getUserId());
            intent.putExtra("avatar", rankDTO.getAvatarUrl());
            intent.putExtra("city", rankDTO.getCity());
            intent.putExtra("remarks", rankDTO.getRemarks());
            startActivity(intent);
        }
    }

    /* renamed from: a */
    private void m6521a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new ClubMemberRankActivity$1(this), new String[]{str});
        }
    }
}
