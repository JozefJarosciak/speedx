package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.sections.dto.C2220a;
import java.util.Collection;

class RecordSegmentActivity$1 extends AsyncTask<Void, Void, C2220a> {
    /* renamed from: a */
    final /* synthetic */ RecordSegmentActivity f10572a;

    RecordSegmentActivity$1(RecordSegmentActivity recordSegmentActivity) {
        this.f10572a = recordSegmentActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11443a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11444a((C2220a) obj);
    }

    /* renamed from: a */
    protected C2220a m11443a(Void... voidArr) {
        try {
            return RecordSegmentActivity.b(this.f10572a).m11400a(RecordSegmentActivity.a(this.f10572a));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11444a(C2220a c2220a) {
        RecordSegmentActivity.c(this.f10572a).m13150a();
        RecordSegmentActivity.c(this.f10572a).setRefreshEnable(false);
        if (c2220a != null && !c2220a.m11405a()) {
            Collection b = c2220a.m11406b();
            if (b == null || b.size() == 0) {
                RecordSegmentActivity.d(this.f10572a).setText(this.f10572a.getResources().getText(C1373R.string.no_pass_any_section));
                return;
            }
            RecordSegmentActivity.e(this.f10572a).setVisibility(8);
            RecordSegmentActivity.f(this.f10572a).addAll(b);
            RecordSegmentActivity.c(this.f10572a).m13153b();
        }
    }
}
