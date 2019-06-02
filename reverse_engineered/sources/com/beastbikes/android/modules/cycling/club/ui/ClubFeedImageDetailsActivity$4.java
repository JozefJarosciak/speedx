package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubFeedImageDetailsActivity$4 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    C1802i f9612a = null;
    /* renamed from: b */
    final /* synthetic */ int f9613b;
    /* renamed from: c */
    final /* synthetic */ ClubFeedImageDetailsActivity f9614c;

    ClubFeedImageDetailsActivity$4(ClubFeedImageDetailsActivity clubFeedImageDetailsActivity, int i) {
        this.f9614c = clubFeedImageDetailsActivity;
        this.f9613b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10783a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10784a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.f9612a = new C1802i(this.f9614c, this.f9614c.getString(C1373R.string.loading_msg), true);
        this.f9612a.show();
    }

    /* renamed from: a */
    protected Boolean m10783a(String... strArr) {
        return Boolean.valueOf(ClubFeedImageDetailsActivity.c(this.f9614c).m10593c(this.f9613b));
    }

    /* renamed from: a */
    protected void m10784a(Boolean bool) {
        if (this.f9612a != null) {
            this.f9612a.dismiss();
        }
        if (bool.booleanValue()) {
            ClubFeedImageDetailsActivity.d(this.f9614c).f9622a.remove(ClubFeedImageDetailsActivity.b(this.f9614c));
            ClubFeedImageDetailsActivity.d(this.f9614c).notifyDataSetChanged();
            ClubFeedImageDetailsActivity.a(this.f9614c, ClubFeedImageDetailsActivity.e(this.f9614c));
            if (ClubFeedImageDetailsActivity.d(this.f9614c).f9622a.size() == 0) {
                this.f9614c.finish();
                return;
            }
            if (ClubFeedImageDetailsActivity.b(this.f9614c) >= 0 && ClubFeedImageDetailsActivity.d(this.f9614c).f9622a.size() > 0) {
                ClubFeedImageDetailsActivity.f(this.f9614c).setCurrentItem(ClubFeedImageDetailsActivity.b(this.f9614c), true);
            }
            ClubFeedImageDetailsActivity.g(this.f9614c).remove(ClubFeedImageDetailsActivity.b(this.f9614c));
            ClubFeedImageDetailsActivity.b(this.f9614c, ClubFeedImageDetailsActivity.b(this.f9614c));
            return;
        }
        Toasts.show(this.f9614c, this.f9614c.getString(C1373R.string.delete_err));
    }
}
