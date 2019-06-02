package com.beastbikes.android.modules.cycling.achievement.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementStageDto;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.text.SimpleDateFormat;

class AchievementsStageActivity$b extends ViewHolder<AchievementStageDto> {
    /* renamed from: a */
    final /* synthetic */ AchievementsStageActivity f8475a;
    /* renamed from: b */
    private TextView f8476b;
    /* renamed from: c */
    private TextView f8477c;
    /* renamed from: d */
    private ImageView f8478d;
    /* renamed from: e */
    private TextView f8479e;
    /* renamed from: f */
    private SimpleDateFormat f8480f = new SimpleDateFormat("yyyy.MM.dd");

    public /* synthetic */ void bind(Object obj) {
        m9796a((AchievementStageDto) obj);
    }

    AchievementsStageActivity$b(AchievementsStageActivity achievementsStageActivity, View view) {
        this.f8475a = achievementsStageActivity;
        super(view);
        this.f8476b = (TextView) view.findViewById(C1373R.id.achievement_item_title_tv);
        this.f8477c = (TextView) view.findViewById(C1373R.id.achievement_item_date_tv);
        this.f8478d = (ImageView) view.findViewById(C1373R.id.achievement_item_rank_iv);
        this.f8479e = (TextView) view.findViewById(C1373R.id.achievement_item_time_tv);
    }

    /* renamed from: a */
    public void m9796a(AchievementStageDto achievementStageDto) {
        long j = 0;
        if (achievementStageDto != null) {
            long j2;
            long j3;
            this.f8476b.setText(achievementStageDto.getmName());
            this.f8477c.setText(this.f8480f.format(Long.valueOf(achievementStageDto.getDate())));
            long j4 = (long) achievementStageDto.getmTimeCost();
            if (j4 > 0) {
                j2 = j4 / 3600;
                j3 = (j4 % 3600) / 60;
                j = (j4 % 3600) % 60;
            } else {
                j3 = 0;
                j2 = 0;
            }
            this.f8479e.setText(String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j)}));
            switch (achievementStageDto.getRank()) {
                case 1:
                    this.f8478d.setImageResource(C1373R.drawable.ic_section_achievement_type_default);
                    return;
                case 2:
                    this.f8478d.setImageResource(C1373R.drawable.ic_section_achievement_type_2);
                    return;
                case 3:
                    this.f8478d.setImageResource(C1373R.drawable.ic_section_achievement_type_1);
                    return;
                default:
                    return;
            }
        }
    }
}
