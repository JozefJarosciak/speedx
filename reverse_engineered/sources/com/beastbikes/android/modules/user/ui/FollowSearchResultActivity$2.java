package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import java.util.List;

class FollowSearchResultActivity$2 extends AsyncTask<Void, Void, List<FriendDTO>> {
    /* renamed from: a */
    final /* synthetic */ C1802i f11678a;
    /* renamed from: b */
    final /* synthetic */ String f11679b;
    /* renamed from: c */
    final /* synthetic */ String f11680c;
    /* renamed from: d */
    final /* synthetic */ FollowSearchResultActivity f11681d;

    FollowSearchResultActivity$2(FollowSearchResultActivity followSearchResultActivity, C1802i c1802i, String str, String str2) {
        this.f11681d = followSearchResultActivity;
        this.f11678a = c1802i;
        this.f11679b = str;
        this.f11680c = str2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12476a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12477a((List) obj);
    }

    protected void onPreExecute() {
        if (this.f11678a != null) {
            this.f11678a.show();
        }
    }

    /* renamed from: a */
    protected List<FriendDTO> m12476a(Void... voidArr) {
        return FollowSearchResultActivity.a(this.f11681d).m12125a(4, this.f11679b, this.f11680c, null);
    }

    /* renamed from: a */
    protected void m12477a(List<FriendDTO> list) {
        if (this.f11678a != null) {
            this.f11678a.dismiss();
        }
        FollowSearchResultActivity.a(this.f11681d, list);
    }
}
