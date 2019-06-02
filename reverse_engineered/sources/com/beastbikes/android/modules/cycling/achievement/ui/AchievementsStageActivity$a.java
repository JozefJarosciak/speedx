package com.beastbikes.android.modules.cycling.achievement.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementStageDto;
import java.util.List;

class AchievementsStageActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ AchievementsStageActivity f8473a;
    /* renamed from: b */
    private List<AchievementStageDto> f8474b;

    AchievementsStageActivity$a(AchievementsStageActivity achievementsStageActivity, List<AchievementStageDto> list) {
        this.f8473a = achievementsStageActivity;
        this.f8474b = list;
    }

    public int getCount() {
        return this.f8474b.size();
    }

    public Object getItem(int i) {
        return this.f8474b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        AchievementsStageActivity$b achievementsStageActivity$b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.user_achievement_stage_list_item, null);
            AchievementsStageActivity$b achievementsStageActivity$b2 = new AchievementsStageActivity$b(this.f8473a, view);
            view.setTag(achievementsStageActivity$b2);
            achievementsStageActivity$b = achievementsStageActivity$b2;
        } else {
            achievementsStageActivity$b = (AchievementsStageActivity$b) view.getTag();
        }
        achievementsStageActivity$b.m9796a((AchievementStageDto) getItem(i));
        return view;
    }
}
