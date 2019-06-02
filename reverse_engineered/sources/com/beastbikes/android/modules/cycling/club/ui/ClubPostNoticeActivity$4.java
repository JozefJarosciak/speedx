package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubPostNoticeActivity$4 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f9789a;
    /* renamed from: b */
    final /* synthetic */ ClubPostNoticeActivity f9790b;

    ClubPostNoticeActivity$4(ClubPostNoticeActivity clubPostNoticeActivity, String str) {
        this.f9790b = clubPostNoticeActivity;
        this.f9789a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10862a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10863a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10862a(String... strArr) {
        try {
            return Boolean.valueOf(ClubPostNoticeActivity.b(this.f9790b).m10551d(strArr[0]));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10863a(Boolean bool) {
        if (ClubPostNoticeActivity.c(this.f9790b) != null && ClubPostNoticeActivity.c(this.f9790b).isShowing()) {
            ClubPostNoticeActivity.c(this.f9790b).dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f9790b, (int) C1373R.string.club_info_club_post_notice_success);
            Intent intent = this.f9790b.getIntent();
            intent.putExtra("notice", ClubPostNoticeActivity.a(this.f9790b).getText().toString());
            this.f9790b.setResult(-1, intent);
            ClubPostNoticeActivity.f5260a = this.f9789a;
            this.f9790b.finish();
            return;
        }
        Toasts.show(this.f9790b, (int) C1373R.string.club_info_club_post_notice_error);
    }
}
