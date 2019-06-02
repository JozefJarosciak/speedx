package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.club.dto.C2062a;

class ClubActivityManagerActivity$10 extends AsyncTask<Void, Void, C2062a> {
    /* renamed from: a */
    final /* synthetic */ ClubActivityManagerActivity f9471a;

    ClubActivityManagerActivity$10(ClubActivityManagerActivity clubActivityManagerActivity) {
        this.f9471a = clubActivityManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10698a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10699a((C2062a) obj);
    }

    /* renamed from: a */
    protected C2062a m10698a(Void... voidArr) {
        if (TextUtils.isEmpty(ClubActivityManagerActivity.a(this.f9471a).getActId())) {
            return null;
        }
        return ClubActivityManagerActivity.b(this.f9471a).m10564c(ClubActivityManagerActivity.a(this.f9471a).getActId());
    }

    /* renamed from: a */
    protected void m10699a(C2062a c2062a) {
        if (c2062a != null) {
            ClubActivityManagerActivity.e(this.f9471a).setText(c2062a.m10621a() + "");
            ClubActivityManagerActivity.f(this.f9471a).setText(c2062a.m10623b() + "");
        }
    }
}
