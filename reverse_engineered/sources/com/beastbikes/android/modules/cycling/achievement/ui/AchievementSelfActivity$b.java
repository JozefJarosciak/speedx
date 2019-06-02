package com.beastbikes.android.modules.cycling.achievement.ui;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementListDto;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

class AchievementSelfActivity$b extends ViewHolder<AchievementListDto> {
    /* renamed from: a */
    final /* synthetic */ AchievementSelfActivity f8470a;
    /* renamed from: b */
    private TextView f8471b;
    /* renamed from: c */
    private TextView f8472c;

    public /* synthetic */ void bind(Object obj) {
        m9795a((AchievementListDto) obj);
    }

    AchievementSelfActivity$b(AchievementSelfActivity achievementSelfActivity, View view) {
        this.f8470a = achievementSelfActivity;
        super(view);
        this.f8471b = (TextView) view.findViewById(C1373R.id.user_achievement_item_name_tv);
        this.f8472c = (TextView) view.findViewById(C1373R.id.user_achievement_item_count_tv);
    }

    /* renamed from: a */
    public void m9795a(AchievementListDto achievementListDto) {
        if (achievementListDto != null) {
            this.f8471b.setText(achievementListDto.getmStageName());
            this.f8472c.setText("x" + String.valueOf(achievementListDto.getmAchievementCount()));
        }
    }
}
