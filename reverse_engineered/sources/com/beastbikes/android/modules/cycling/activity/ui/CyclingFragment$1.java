package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.activity.dto.MyGoalInfoDTO;

class CyclingFragment$1 extends AsyncTask<Void, Void, MyGoalInfoDTO> {
    /* renamed from: a */
    final /* synthetic */ CyclingFragment f8653a;

    CyclingFragment$1(CyclingFragment cyclingFragment) {
        this.f8653a = cyclingFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9944a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9945a((MyGoalInfoDTO) obj);
    }

    /* renamed from: a */
    protected MyGoalInfoDTO m9944a(Void... voidArr) {
        return CyclingFragment.a(this.f8653a).m9902b();
    }

    /* renamed from: a */
    protected void m9945a(MyGoalInfoDTO myGoalInfoDTO) {
        if (myGoalInfoDTO != null) {
            if (CyclingFragment.b(this.f8653a) == null || CyclingFragment.b(this.f8653a).getMyGoal() != myGoalInfoDTO.getMyGoal() || CyclingFragment.b(this.f8653a).getCurGoal() != myGoalInfoDTO.getCurGoal()) {
                CyclingFragment.a(this.f8653a, myGoalInfoDTO);
                CyclingFragment.c(this.f8653a);
            }
        }
    }
}
