package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityInfo;

class ClubActivityManagerActivity$3 extends AsyncTask<String, Void, ClubActivityInfo> {
    /* renamed from: a */
    final /* synthetic */ Intent f9476a;
    /* renamed from: b */
    final /* synthetic */ ClubActivityManagerActivity f9477b;

    ClubActivityManagerActivity$3(ClubActivityManagerActivity clubActivityManagerActivity, Intent intent) {
        this.f9477b = clubActivityManagerActivity;
        this.f9476a = intent;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10704a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10705a((ClubActivityInfo) obj);
    }

    protected void onPreExecute() {
        if (ClubActivityManagerActivity.l(this.f9477b) != null) {
            ClubActivityManagerActivity.l(this.f9477b).show();
        }
    }

    /* renamed from: a */
    protected ClubActivityInfo m10704a(String... strArr) {
        try {
            return ClubActivityManagerActivity.b(this.f9477b).m10558a(ClubActivityManagerActivity.a(this.f9477b).getActId());
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10705a(ClubActivityInfo clubActivityInfo) {
        if (!(this.f9477b.isFinishing() || ClubActivityManagerActivity.l(this.f9477b) == null)) {
            ClubActivityManagerActivity.l(this.f9477b).dismiss();
        }
        if (clubActivityInfo != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("ACT_INFO", clubActivityInfo);
            this.f9476a.putExtras(bundle);
            this.f9477b.startActivityForResult(this.f9476a, 13);
        }
    }
}
