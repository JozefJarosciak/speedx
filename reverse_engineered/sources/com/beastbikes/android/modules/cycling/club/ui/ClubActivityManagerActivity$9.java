package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;

class ClubActivityManagerActivity$9 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubActivityManagerActivity f9487a;

    ClubActivityManagerActivity$9(ClubActivityManagerActivity clubActivityManagerActivity) {
        this.f9487a = clubActivityManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10710a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10711a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10710a(Void... voidArr) {
        if (TextUtils.isEmpty(ClubActivityManagerActivity.a(this.f9487a).getActId())) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(ClubActivityManagerActivity.b(this.f9487a).m10565d(ClubActivityManagerActivity.a(this.f9487a).getActId()));
    }

    /* renamed from: a */
    protected void m10711a(Boolean bool) {
    }
}
