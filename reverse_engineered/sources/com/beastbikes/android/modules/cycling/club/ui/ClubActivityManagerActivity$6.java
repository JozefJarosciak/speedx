package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;

class ClubActivityManagerActivity$6 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubActivityManagerActivity f9482a;

    ClubActivityManagerActivity$6(ClubActivityManagerActivity clubActivityManagerActivity) {
        this.f9482a = clubActivityManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10708a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10709a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10708a(Void... voidArr) {
        return Boolean.valueOf(ClubActivityManagerActivity.b(this.f9482a).m10563b(ClubActivityManagerActivity.a(this.f9482a).getActId()));
    }

    /* renamed from: a */
    protected void m10709a(Boolean bool) {
        this.f9482a.finish();
    }
}
