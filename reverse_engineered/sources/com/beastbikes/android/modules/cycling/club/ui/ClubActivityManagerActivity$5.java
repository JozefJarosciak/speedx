package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;

class ClubActivityManagerActivity$5 extends AsyncTask<Void, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ String f9480a;
    /* renamed from: b */
    final /* synthetic */ ClubActivityManagerActivity f9481b;

    ClubActivityManagerActivity$5(ClubActivityManagerActivity clubActivityManagerActivity, String str) {
        this.f9481b = clubActivityManagerActivity;
        this.f9480a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10706a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10707a((Integer) obj);
    }

    /* renamed from: a */
    protected Integer m10706a(Void... voidArr) {
        return Integer.valueOf(ClubActivityManagerActivity.b(this.f9481b).m10557a(this.f9480a, ClubActivityManagerActivity.a(this.f9481b).getActId()));
    }

    /* renamed from: a */
    protected void m10707a(Integer num) {
        if (num.intValue() != -1) {
            ClubActivityManagerActivity.c(this.f9481b).setText("(" + ClubActivityManagerActivity.a(this.f9481b).getSignInCount() + "/" + ClubActivityManagerActivity.a(this.f9481b).getMembers() + ")");
        }
    }
}
