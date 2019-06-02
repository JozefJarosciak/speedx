package com.beastbikes.android.modules.cycling.achievement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementStageDto;
import com.beastbikes.android.modules.cycling.achievement.p112b.C1897b;
import com.beastbikes.android.modules.cycling.achievement.p113c.C1899b;
import com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903072)
public class AchievementsStageActivity extends SessionFragmentActivity implements OnItemClickListener, C1899b {
    @C1458a(a = 2131755328)
    /* renamed from: a */
    private ListView f4486a;
    /* renamed from: b */
    private AchievementsStageActivity$a f4487b;
    /* renamed from: c */
    private List<AchievementStageDto> f4488c = new ArrayList();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        long longExtra = getIntent().getLongExtra("stage_id", 0);
        CharSequence stringExtra = getIntent().getStringExtra("stage_name");
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(stringExtra);
        }
        this.f4487b = new AchievementsStageActivity$a(this, this.f4488c);
        this.f4486a.setAdapter(this.f4487b);
        this.f4486a.setOnItemClickListener(this);
        new C1897b(this).a(longExtra, m5331p());
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AchievementStageDto achievementStageDto = (AchievementStageDto) adapterView.getItemAtPosition(i);
        if (achievementStageDto != null) {
            Intent intent = new Intent(this, CyclingCompletedActivity.class);
            intent.putExtra("sport_identify", achievementStageDto.getActivityId());
            startActivity(intent);
        }
    }

    /* renamed from: a */
    public SessionFragmentActivity m5850a() {
        return this;
    }

    /* renamed from: a */
    public void m5851a(List<AchievementStageDto> list) {
        if (list != null && list.size() > 0) {
            this.f4488c.clear();
            this.f4488c.addAll(list);
            this.f4487b.notifyDataSetChanged();
        }
    }
}
