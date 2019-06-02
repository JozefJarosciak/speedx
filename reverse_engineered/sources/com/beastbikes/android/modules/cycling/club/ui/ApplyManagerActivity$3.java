package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;

class ApplyManagerActivity$3 extends AsyncTask<Void, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ String f9403a;
    /* renamed from: b */
    final /* synthetic */ ApplyManagerActivity f9404b;

    ApplyManagerActivity$3(ApplyManagerActivity applyManagerActivity, String str) {
        this.f9404b = applyManagerActivity;
        this.f9403a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10655a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10656a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10655a(Void... voidArr) {
        ClubInfoCompact clubInfoCompact = null;
        if (ApplyManagerActivity.a(this.f9404b) != null) {
            try {
                clubInfoCompact = ApplyManagerActivity.a(this.f9404b).m10533a(this.f9403a);
            } catch (BusinessException e) {
            }
        }
        return clubInfoCompact;
    }

    /* renamed from: a */
    protected void m10656a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            ApplyManagerActivity.a(this.f9404b, clubInfoCompact.getMembers());
            ApplyManagerActivity.b(this.f9404b, clubInfoCompact.getMaxMembers());
        }
    }
}
