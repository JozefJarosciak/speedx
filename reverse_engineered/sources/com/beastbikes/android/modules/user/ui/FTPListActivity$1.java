package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.train.dto.FtpDTO;
import java.util.ArrayList;
import java.util.Collections;

class FTPListActivity$1 extends AsyncTask<Void, Void, ArrayList<FtpDTO>> {
    /* renamed from: a */
    final /* synthetic */ FTPListActivity f11571a;

    FTPListActivity$1(FTPListActivity fTPListActivity) {
        this.f11571a = fTPListActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12419a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12420a((ArrayList) obj);
    }

    /* renamed from: a */
    protected ArrayList<FtpDTO> m12419a(Void... voidArr) {
        return FTPListActivity.a(this.f11571a).m12012b();
    }

    /* renamed from: a */
    protected void m12420a(ArrayList<FtpDTO> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            FTPListActivity.b(this.f11571a).clear();
            FTPListActivity.b(this.f11571a).addAll(arrayList);
            Collections.sort(FTPListActivity.b(this.f11571a), FTPListActivity.c(this.f11571a));
            FTPListActivity.d(this.f11571a).notifyDataSetChanged();
        }
    }
}
