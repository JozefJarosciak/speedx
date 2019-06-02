package com.beastbikes.android.modules.cycling.achievement.p063a;

import android.app.Activity;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementListDto;
import com.beastbikes.android.modules.cycling.achievement.dto.AchievementStageDto;
import com.beastbikes.android.modules.cycling.achievement.dto.UserAchievementDto;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AchievementManager */
/* renamed from: com.beastbikes.android.modules.cycling.achievement.a.a */
public class C1893a extends C1397a {
    /* renamed from: a */
    private C1396b f8456a;

    public C1893a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f8456a = (C1396b) new C1772d(activity).m9399a(C1396b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public UserAchievementDto m9779a(String str) {
        try {
            JSONObject a = this.f8456a.a(str);
            if (a == null || !a.has("code") || a.optInt("code", -1) != 0) {
                return null;
            }
            JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null) {
                return null;
            }
            UserAchievementDto userAchievementDto = new UserAchievementDto();
            userAchievementDto.setmAchievementCount(optJSONObject.optInt("achievement_count"));
            userAchievementDto.setmStageCount(optJSONObject.optInt("leg_count"));
            JSONArray optJSONArray = optJSONObject.optJSONArray("achievements");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                List arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    if (optJSONObject2 != null) {
                        AchievementListDto achievementListDto = new AchievementListDto();
                        achievementListDto.setmAchievementCount(optJSONObject2.optInt("achievement_count"));
                        achievementListDto.setmStageName(optJSONObject2.optString("name"));
                        achievementListDto.setmStageId(optJSONObject2.optLong("leg_id"));
                        arrayList.add(achievementListDto);
                    }
                }
                userAchievementDto.setmAchievements(arrayList);
            }
            return userAchievementDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public List<AchievementStageDto> m9780a(long j, String str) {
        try {
            JSONObject a = this.f8456a.a(j, str);
            if (a == null || !a.has("code") || a.optInt("code", -1) != 0) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<AchievementStageDto> arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    AchievementStageDto achievementStageDto = new AchievementStageDto();
                    achievementStageDto.setmName(optJSONObject.optString("name"));
                    achievementStageDto.setmTimeCost(optJSONObject.optDouble("time"));
                    Date h = C2555d.m12820h(optJSONObject.optString("date"));
                    if (h != null) {
                        achievementStageDto.setDate(h.getTime());
                    }
                    achievementStageDto.setRank(optJSONObject.optInt("achievement"));
                    achievementStageDto.setActivityId(optJSONObject.optString("sport_route_id"));
                    arrayList.add(achievementStageDto);
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
