package com.beastbikes.android.modules.cycling.achievement.dto;

import java.io.Serializable;

public class AchievementListDto implements Serializable {
    private int mAchievementCount;
    private long mStageId;
    private String mStageName;

    public String getmStageName() {
        return this.mStageName;
    }

    public void setmStageName(String str) {
        this.mStageName = str;
    }

    public int getmAchievementCount() {
        return this.mAchievementCount;
    }

    public void setmAchievementCount(int i) {
        this.mAchievementCount = i;
    }

    public long getmStageId() {
        return this.mStageId;
    }

    public void setmStageId(long j) {
        this.mStageId = j;
    }
}
