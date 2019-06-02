package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.framework.business.BusinessException;
import java.util.Locale;

class GridExploreActivity$2 extends AsyncTask<String, Void, UserDetailDTO> {
    /* renamed from: a */
    final /* synthetic */ GridExploreActivity f11718a;

    GridExploreActivity$2(GridExploreActivity gridExploreActivity) {
        this.f11718a = gridExploreActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12495a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12496a((UserDetailDTO) obj);
    }

    /* renamed from: a */
    protected UserDetailDTO m12495a(String... strArr) {
        try {
            return new C2389c(this.f11718a).m12138d(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m12496a(UserDetailDTO userDetailDTO) {
        if (userDetailDTO != null) {
            if (userDetailDTO.getTotalElapsedTime() <= 0) {
                GridExploreActivity.h(this.f11718a).setText("0");
                return;
            }
            float totalElapsedTime = (((float) userDetailDTO.getTotalElapsedTime()) * 1.0f) / 3600.0f;
            GridExploreActivity.h(this.f11718a).setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Float.valueOf(totalElapsedTime)}));
        }
    }
}
