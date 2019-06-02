package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.p074a.C2341a;
import java.util.List;

class FollowSearchResultActivity$3 extends AsyncTask<String, Void, List<FriendDTO>> {
    /* renamed from: a */
    final /* synthetic */ C1802i f11682a;
    /* renamed from: b */
    final /* synthetic */ String f11683b;
    /* renamed from: c */
    final /* synthetic */ FollowSearchResultActivity f11684c;

    FollowSearchResultActivity$3(FollowSearchResultActivity followSearchResultActivity, C1802i c1802i, String str) {
        this.f11684c = followSearchResultActivity;
        this.f11682a = c1802i;
        this.f11683b = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12478a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12479a((List) obj);
    }

    protected void onPreExecute() {
        if (this.f11682a != null) {
            this.f11682a.show();
        }
    }

    /* renamed from: a */
    protected List<FriendDTO> m12478a(String... strArr) {
        return new C2341a(this.f11684c).m11949a(this.f11683b, FollowSearchResultActivity.b(this.f11684c), 20);
    }

    /* renamed from: a */
    protected void m12479a(List<FriendDTO> list) {
        if (this.f11682a != null) {
            this.f11682a.dismiss();
        }
        FollowSearchResultActivity.a(this.f11684c, list);
    }
}
