package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import java.util.List;

class FollowSearchResultActivity$1 extends AsyncTask<Void, Void, List<FriendDTO>> {
    /* renamed from: a */
    final /* synthetic */ C1802i f11676a;
    /* renamed from: b */
    final /* synthetic */ FollowSearchResultActivity f11677b;

    FollowSearchResultActivity$1(FollowSearchResultActivity followSearchResultActivity, C1802i c1802i) {
        this.f11677b = followSearchResultActivity;
        this.f11676a = c1802i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12474a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12475a((List) obj);
    }

    protected void onPreExecute() {
        if (this.f11676a != null) {
            this.f11676a.show();
        }
    }

    /* renamed from: a */
    protected List<FriendDTO> m12474a(Void... voidArr) {
        try {
            return this.f11677b.c();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m12475a(List<FriendDTO> list) {
        if (this.f11676a != null) {
            this.f11676a.dismiss();
        }
        FollowSearchResultActivity.a(this.f11677b, list);
    }
}
