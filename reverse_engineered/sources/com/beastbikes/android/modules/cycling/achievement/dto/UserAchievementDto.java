package com.beastbikes.android.modules.cycling.achievement.dto;

import java.io.Serializable;
import java.util.List;

public class UserAchievementDto implements Serializable {
    private int mAchievementCount;
    private List<AchievementListDto> mAchievements;
    private int mStageCount;

    public int getmAchievementCount() {
        return this.mAchievementCount;
    }

    public void setmAchievementCount(int i) {
        this.mAchievementCount = i;
    }

    public int getmStageCount() {
        return this.mStageCount;
    }

    public void setmStageCount(int i) {
        this.mStageCount = i;
    }

    public List<AchievementListDto> getmAchievements() {
        return this.mAchievements;
    }

    public void setmAchievements(List<AchievementListDto> list) {
        this.mAchievements = list;
    }
}
