package com.beastbikes.android.modules.cycling.achievement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementListDto;
import com.beastbikes.android.modules.cycling.achievement.dto.UserAchievementDto;
import com.beastbikes.android.modules.cycling.achievement.p112b.C1895a;
import com.beastbikes.android.modules.cycling.achievement.p113c.C1898a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@C1459b(a = 2130903071)
public class AchievementSelfActivity extends SessionFragmentActivity implements OnItemClickListener, C1898a {
    @C1458a(a = 2131755324)
    /* renamed from: a */
    private TextView f4481a;
    @C1458a(a = 2131755325)
    /* renamed from: b */
    private TextView f4482b;
    @C1458a(a = 2131755326)
    /* renamed from: c */
    private ListView f4483c;
    /* renamed from: d */
    private AchievementSelfActivity$a f4484d;
    /* renamed from: e */
    private List<AchievementListDto> f4485e = new ArrayList();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f4481a.setFocusable(true);
        this.f4481a.setFocusableInTouchMode(true);
        this.f4481a.requestFocus();
        this.f4484d = new AchievementSelfActivity$a(this, this.f4485e);
        this.f4483c.setAdapter(this.f4484d);
        this.f4483c.setOnItemClickListener(this);
        new C1895a(this).a(m5331p());
    }

    /* renamed from: a */
    public SessionFragmentActivity m5848a() {
        return this;
    }

    /* renamed from: a */
    public void m5849a(UserAchievementDto userAchievementDto) {
        if (userAchievementDto != null) {
            this.f4482b.setText(String.valueOf(userAchievementDto.getmAchievementCount()));
            this.f4481a.setText(String.valueOf(userAchievementDto.getmStageCount()));
            Collection collection = userAchievementDto.getmAchievements();
            if (collection != null && collection.size() > 0) {
                this.f4485e.clear();
                this.f4485e.addAll(collection);
                this.f4484d.notifyDataSetChanged();
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AchievementListDto achievementListDto = (AchievementListDto) adapterView.getItemAtPosition(i);
        if (achievementListDto != null) {
            Intent intent = new Intent(this, AchievementsStageActivity.class);
            intent.putExtra("stage_id", achievementListDto.getmStageId());
            intent.putExtra("stage_name", achievementListDto.getmStageName());
            intent.putExtra("user_id", m5331p());
            startActivity(intent);
        }
    }
}
