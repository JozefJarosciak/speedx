package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;

class ClubCreateActivity$7 extends AsyncTask<String, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ String f9546a;
    /* renamed from: b */
    final /* synthetic */ String f9547b;
    /* renamed from: c */
    final /* synthetic */ String f9548c;
    /* renamed from: d */
    final /* synthetic */ String f9549d;
    /* renamed from: e */
    final /* synthetic */ String f9550e;
    /* renamed from: f */
    final /* synthetic */ String f9551f;
    /* renamed from: g */
    final /* synthetic */ String f9552g;
    /* renamed from: h */
    final /* synthetic */ String f9553h;
    /* renamed from: i */
    final /* synthetic */ String f9554i;
    /* renamed from: j */
    final /* synthetic */ int f9555j;
    /* renamed from: k */
    final /* synthetic */ double f9556k;
    /* renamed from: l */
    final /* synthetic */ double f9557l;
    /* renamed from: m */
    final /* synthetic */ ClubCreateActivity f9558m;

    ClubCreateActivity$7(ClubCreateActivity clubCreateActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, double d, double d2) {
        this.f9558m = clubCreateActivity;
        this.f9546a = str;
        this.f9547b = str2;
        this.f9548c = str3;
        this.f9549d = str4;
        this.f9550e = str5;
        this.f9551f = str6;
        this.f9552g = str7;
        this.f9553h = str8;
        this.f9554i = str9;
        this.f9555j = i;
        this.f9556k = d;
        this.f9557l = d2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10728a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10729a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10728a(String... strArr) {
        try {
            return ClubCreateActivity.g(this.f9558m).m10534a(this.f9546a, this.f9547b, this.f9548c, this.f9549d, this.f9550e, this.f9551f, this.f9552g, this.f9553h, this.f9554i, this.f9555j, this.f9556k, this.f9557l);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10729a(ClubInfoCompact clubInfoCompact) {
        if (this.f9558m.f4985a != null) {
            this.f9558m.f4985a.dismiss();
        }
        if (clubInfoCompact != null) {
            this.f9558m.finish();
        }
    }
}
