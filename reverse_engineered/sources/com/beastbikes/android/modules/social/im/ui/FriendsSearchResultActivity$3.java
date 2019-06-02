package com.beastbikes.android.modules.social.im.ui;

import android.os.AsyncTask;

class FriendsSearchResultActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ int f11162a;
    /* renamed from: b */
    final /* synthetic */ FriendsSearchResultActivity f11163b;

    FriendsSearchResultActivity$3(FriendsSearchResultActivity friendsSearchResultActivity, int i) {
        this.f11163b = friendsSearchResultActivity;
        this.f11162a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11973a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11974a((Boolean) obj);
    }

    protected void onPreExecute() {
        if (FriendsSearchResultActivity.a(this.f11163b) != null) {
            FriendsSearchResultActivity.a(this.f11163b).show();
        }
    }

    /* renamed from: a */
    protected Boolean m11973a(String... strArr) {
        return Boolean.valueOf(FriendsSearchResultActivity.b(this.f11163b).m11952a(this.f11162a, 0));
    }

    /* renamed from: a */
    protected void m11974a(Boolean bool) {
        if (FriendsSearchResultActivity.a(this.f11163b) != null) {
            FriendsSearchResultActivity.a(this.f11163b).dismiss();
        }
        if (bool.booleanValue() && FriendsSearchResultActivity.i(this.f11163b) != null) {
            FriendsSearchResultActivity.i(this.f11163b).setStatus(3);
            FriendsSearchResultActivity.g(this.f11163b).notifyDataSetChanged();
        }
    }
}
