package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import java.util.List;

class MedalsActivity$1 extends AsyncTask<String, Void, List<MedalDTO>> {
    /* renamed from: a */
    final /* synthetic */ int f11722a;
    /* renamed from: b */
    final /* synthetic */ MedalsActivity f11723b;

    MedalsActivity$1(MedalsActivity medalsActivity, int i) {
        this.f11723b = medalsActivity;
        this.f11722a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12500a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12501a((List) obj);
    }

    /* renamed from: a */
    protected List<MedalDTO> m12500a(String... strArr) {
        try {
            return MedalsActivity.b(this.f11723b).m12124a(this.f11722a, 1, 1000, MedalsActivity.a(this.f11723b));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m12501a(List<MedalDTO> list) {
        if (list == null || list.isEmpty()) {
            if (this.f11722a == 0) {
                MedalsActivity.c(this.f11723b).setVisibility(0);
            }
        } else if (this.f11722a == 0) {
            MedalsActivity.c(this.f11723b).setVisibility(4);
            MedalsActivity.d(this.f11723b).addAll(list);
            MedalsActivity.e(this.f11723b).notifyDataSetChanged();
        } else if (this.f11722a == 1) {
            MedalsActivity.f(this.f11723b).addAll(list);
            MedalsActivity.g(this.f11723b).notifyDataSetChanged();
        } else {
            MedalsActivity.h(this.f11723b).clear();
            MedalsActivity.h(this.f11723b).addAll(list);
            MedalsActivity.i(this.f11723b);
        }
    }
}
