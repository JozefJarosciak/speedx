package com.beastbikes.android.modules.user.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.user.dto.ActivityDTO;

class CyclingRecordActivity$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ActivityDTO f11558a;
    /* renamed from: b */
    final /* synthetic */ CyclingRecordActivity f11559b;

    CyclingRecordActivity$3(CyclingRecordActivity cyclingRecordActivity, ActivityDTO activityDTO) {
        this.f11559b = cyclingRecordActivity;
        this.f11558a = activityDTO;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (CyclingRecordActivity.d(this.f11559b) == null) {
            CyclingRecordActivity.a(this.f11559b, new C1802i(this.f11559b, this.f11559b.getString(C1373R.string.club_info_waiting), true));
        }
        CyclingRecordActivity.d(this.f11559b).show();
        CyclingRecordActivity.j().info("用户确定删除数据, activityId = " + this.f11558a.getActivityIdentifier());
        CyclingRecordActivity.b(this.f11559b).m12168a(this.f11558a);
    }
}
