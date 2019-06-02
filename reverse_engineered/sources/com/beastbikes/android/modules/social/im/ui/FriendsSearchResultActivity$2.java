package com.beastbikes.android.modules.social.im.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;

class FriendsSearchResultActivity$2 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11158a;
    /* renamed from: b */
    final /* synthetic */ String f11159b;
    /* renamed from: c */
    final /* synthetic */ FriendDTO f11160c;
    /* renamed from: d */
    final /* synthetic */ FriendsSearchResultActivity f11161d;

    FriendsSearchResultActivity$2(FriendsSearchResultActivity friendsSearchResultActivity, String str, String str2, FriendDTO friendDTO) {
        this.f11161d = friendsSearchResultActivity;
        this.f11158a = str;
        this.f11159b = str2;
        this.f11160c = friendDTO;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11971a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11972a((Boolean) obj);
    }

    protected void onPreExecute() {
        if (FriendsSearchResultActivity.a(this.f11161d) != null) {
            FriendsSearchResultActivity.a(this.f11161d).show();
        }
    }

    /* renamed from: a */
    protected Boolean m11971a(String... strArr) {
        return Boolean.valueOf(FriendsSearchResultActivity.b(this.f11161d).m11953a(this.f11158a, this.f11159b));
    }

    /* renamed from: a */
    protected void m11972a(Boolean bool) {
        if (FriendsSearchResultActivity.a(this.f11161d) != null) {
            FriendsSearchResultActivity.a(this.f11161d).dismiss();
        }
        if (bool.booleanValue()) {
            this.f11160c.setStatus(2);
            FriendsSearchResultActivity.g(this.f11161d).notifyDataSetChanged();
        }
    }
}
