package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import java.util.List;

class CyclingRecordActivity$4 extends AsyncTask<String, Void, List<LocalActivity>> {
    /* renamed from: a */
    final /* synthetic */ String f11560a;
    /* renamed from: b */
    final /* synthetic */ String f11561b;
    /* renamed from: c */
    final /* synthetic */ CyclingRecordActivity f11562c;

    CyclingRecordActivity$4(CyclingRecordActivity cyclingRecordActivity, String str, String str2) {
        this.f11562c = cyclingRecordActivity;
        this.f11560a = str;
        this.f11561b = str2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12377a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12378a((List) obj);
    }

    /* renamed from: a */
    protected List<LocalActivity> m12377a(String... strArr) {
        return new C1651c(this.f11562c).m8884a(CyclingRecordActivity.e(this.f11562c), this.f11560a);
    }

    /* renamed from: a */
    protected void m12378a(List<LocalActivity> list) {
        if (list == null || list.size() < 1) {
            CyclingRecordActivity.f(this.f11562c).setVisibility(8);
            return;
        }
        CyclingRecordActivity.f(this.f11562c).setOnClickListener(this.f11562c);
        CyclingRecordActivity.f(this.f11562c).setVisibility(0);
        CyclingRecordActivity.g(this.f11562c).setText(String.format(this.f11562c.getString(C1373R.string.msg_ble_unsync), new Object[]{this.f11561b}));
    }
}
