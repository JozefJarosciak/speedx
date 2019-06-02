package com.beastbikes.android.update.ui;

import android.os.AsyncTask;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.update.p079a.C2547a;
import com.beastbikes.android.update.p162b.C2548a;

class VersionUpdateActivity$1 extends AsyncTask<Void, Void, C2548a> {
    /* renamed from: a */
    final /* synthetic */ VersionUpdateActivity f12030a;

    VersionUpdateActivity$1(VersionUpdateActivity versionUpdateActivity) {
        this.f12030a = versionUpdateActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12750a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12751a((C2548a) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        VersionUpdateActivity.a(this.f12030a, new C1802i(this.f12030a, "", true));
        VersionUpdateActivity.a(this.f12030a).show();
    }

    /* renamed from: a */
    protected C2548a m12750a(Void... voidArr) {
        return new C2547a(this.f12030a).m12743a();
    }

    /* renamed from: a */
    protected void m12751a(C2548a c2548a) {
        super.onPostExecute(c2548a);
        if (!this.f12030a.isFinishing() && VersionUpdateActivity.a(this.f12030a).isShowing()) {
            VersionUpdateActivity.a(this.f12030a).dismiss();
        }
        if (c2548a != null) {
            VersionUpdateActivity.a(this.f12030a, c2548a);
            this.f12030a.f6804d.setText(c2548a.m12749f());
            this.f12030a.f6801a.setText(c2548a.m12745b());
            this.f12030a.f6803c.setOnClickListener(this.f12030a);
            this.f12030a.f6802b.setText(c2548a.m12744a());
        }
    }
}
