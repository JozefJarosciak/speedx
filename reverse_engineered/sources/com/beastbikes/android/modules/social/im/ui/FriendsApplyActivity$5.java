package com.beastbikes.android.modules.social.im.ui;

import android.os.AsyncTask;

class FriendsApplyActivity$5 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ FriendsApplyActivity f11150a;

    FriendsApplyActivity$5(FriendsApplyActivity friendsApplyActivity) {
        this.f11150a = friendsApplyActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11967a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11968a((Boolean) obj);
    }

    protected void onPreExecute() {
        if (FriendsApplyActivity.b(this.f11150a) != null) {
            FriendsApplyActivity.b(this.f11150a).show();
        }
    }

    /* renamed from: a */
    protected Boolean m11967a(Void... voidArr) {
        return Boolean.valueOf(FriendsApplyActivity.c(this.f11150a).m11951a());
    }

    /* renamed from: a */
    protected void m11968a(Boolean bool) {
        if (FriendsApplyActivity.b(this.f11150a) != null) {
            FriendsApplyActivity.b(this.f11150a).dismiss();
        }
        if (bool.booleanValue()) {
            FriendsApplyActivity.d(this.f11150a).clear();
            FriendsApplyActivity.e(this.f11150a).notifyDataSetChanged();
            FriendsApplyActivity.g(this.f11150a).setVisibility(8);
            FriendsApplyActivity.i(this.f11150a).setVisibility(0);
        }
    }
}
