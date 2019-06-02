package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import java.util.ArrayList;

class ClubImageDetailsActivity$1 extends AsyncTask<String, Void, ClubFeedComment> {
    /* renamed from: a */
    final /* synthetic */ String f9695a;
    /* renamed from: b */
    final /* synthetic */ int f9696b;
    /* renamed from: c */
    final /* synthetic */ ClubImageDetailsActivity f9697c;

    ClubImageDetailsActivity$1(ClubImageDetailsActivity clubImageDetailsActivity, String str, int i) {
        this.f9697c = clubImageDetailsActivity;
        this.f9695a = str;
        this.f9696b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10816a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10817a((ClubFeedComment) obj);
    }

    /* renamed from: a */
    protected ClubFeedComment m10816a(String... strArr) {
        return ClubImageDetailsActivity.b(this.f9697c).m10584b(ClubImageDetailsActivity.a(this.f9697c).getPhotoId(), this.f9695a, this.f9696b);
    }

    /* renamed from: a */
    protected void m10817a(ClubFeedComment clubFeedComment) {
        if (clubFeedComment != null) {
            if (ClubImageDetailsActivity.a(this.f9697c).getCommentList() == null) {
                ClubImageDetailsActivity.a(this.f9697c).setCommentList(new ArrayList());
            }
            ClubImageDetailsActivity.a(this.f9697c).getCommentList().add(clubFeedComment);
            ClubImageDetailsActivity.c(this.f9697c).m11013a(ClubImageDetailsActivity.a(this.f9697c));
            ClubImageDetailsActivity.a(this.f9697c, ClubImageDetailsActivity.a(this.f9697c).getPhotoId());
        }
    }
}
