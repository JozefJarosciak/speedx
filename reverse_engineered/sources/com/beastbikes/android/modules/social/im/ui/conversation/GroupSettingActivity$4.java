package com.beastbikes.android.modules.social.im.ui.conversation;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class GroupSettingActivity$4 extends AsyncTask<String, Void, List<RankDTO>> {
    /* renamed from: a */
    final /* synthetic */ GroupSettingActivity f11183a;

    GroupSettingActivity$4(GroupSettingActivity groupSettingActivity) {
        this.f11183a = groupSettingActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11986a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11987a((List) obj);
    }

    /* renamed from: a */
    protected List<RankDTO> m11986a(String... strArr) {
        try {
            return new ClubManager(this.f11183a).m10540a(strArr[0], 0, 1, 1000);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11987a(List<RankDTO> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 1; i < list.size(); i++) {
                GroupSettingActivity.d(this.f11183a).add((RankDTO) list.get(i));
            }
            GroupSettingActivity.e(this.f11183a).notifyDataSetChanged();
        }
    }
}
