package com.beastbikes.android.modules.cycling.achievement.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementListDto;
import java.util.List;

class AchievementSelfActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ AchievementSelfActivity f8468a;
    /* renamed from: b */
    private List<AchievementListDto> f8469b;

    AchievementSelfActivity$a(AchievementSelfActivity achievementSelfActivity, List<AchievementListDto> list) {
        this.f8468a = achievementSelfActivity;
        this.f8469b = list;
    }

    public int getCount() {
        return this.f8469b.size();
    }

    public Object getItem(int i) {
        return this.f8469b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        AchievementSelfActivity$b achievementSelfActivity$b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.user_achievement_list_item, null);
            AchievementSelfActivity$b achievementSelfActivity$b2 = new AchievementSelfActivity$b(this.f8468a, view);
            view.setTag(achievementSelfActivity$b2);
            achievementSelfActivity$b = achievementSelfActivity$b2;
        } else {
            achievementSelfActivity$b = (AchievementSelfActivity$b) view.getTag();
        }
        achievementSelfActivity$b.m9795a((AchievementListDto) getItem(i));
        return view;
    }
}
