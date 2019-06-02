package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubPhotoDTO;

class ClubFeedImageDetailsActivity$5 extends AsyncTask<Integer, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ int f9615a;
    /* renamed from: b */
    final /* synthetic */ ClubFeedImageDetailsActivity f9616b;

    ClubFeedImageDetailsActivity$5(ClubFeedImageDetailsActivity clubFeedImageDetailsActivity, int i) {
        this.f9616b = clubFeedImageDetailsActivity;
        this.f9615a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10785a((Integer[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10786a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10785a(Integer... numArr) {
        int i = 0;
        if (this.f9615a < 0 || this.f9615a >= ClubFeedImageDetailsActivity.g(this.f9616b).size()) {
            return Boolean.valueOf(false);
        }
        ClubPhotoDTO clubPhotoDTO = (ClubPhotoDTO) ClubFeedImageDetailsActivity.g(this.f9616b).get(this.f9615a);
        if (clubPhotoDTO == null) {
            return Boolean.valueOf(false);
        }
        if (clubPhotoDTO.isHasLiked()) {
            i = 1;
        }
        return Boolean.valueOf(ClubFeedImageDetailsActivity.c(this.f9616b).m10590b(clubPhotoDTO.getPhotoId(), i));
    }

    /* renamed from: a */
    protected void m10786a(Boolean bool) {
        if (bool.booleanValue() && this.f9615a >= 0 && this.f9615a < ClubFeedImageDetailsActivity.g(this.f9616b).size()) {
            ClubPhotoDTO clubPhotoDTO = (ClubPhotoDTO) ClubFeedImageDetailsActivity.g(this.f9616b).get(this.f9615a);
            if (clubPhotoDTO != null) {
                int i;
                int likeNum = clubPhotoDTO.getLikeNum();
                if (clubPhotoDTO.isHasLiked()) {
                    ClubFeedImageDetailsActivity.h(this.f9616b).setSelected(false);
                    likeNum--;
                    clubPhotoDTO.setHasLiked(false);
                    clubPhotoDTO.setLikeNum(likeNum);
                    i = likeNum;
                } else {
                    ClubFeedImageDetailsActivity.h(this.f9616b).setSelected(true);
                    likeNum++;
                    ClubFeedImageDetailsActivity.i(this.f9616b).setText(String.valueOf(likeNum));
                    clubPhotoDTO.setHasLiked(true);
                    clubPhotoDTO.setLikeNum(likeNum);
                    i = likeNum;
                }
                if (i <= 0) {
                    ClubFeedImageDetailsActivity.i(this.f9616b).setText("");
                } else {
                    ClubFeedImageDetailsActivity.i(this.f9616b).setText(String.valueOf(i));
                }
            }
        }
    }
}
