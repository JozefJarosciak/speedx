package com.beastbikes.android.modules.cycling.club.ui;

import com.beastbikes.android.modules.p062c.C1882d;

class ClubActivityReleaseActivity$2 implements C1882d {
    /* renamed from: a */
    final /* synthetic */ String f9501a;
    /* renamed from: b */
    final /* synthetic */ String f9502b;
    /* renamed from: c */
    final /* synthetic */ String f9503c;
    /* renamed from: d */
    final /* synthetic */ String f9504d;
    /* renamed from: e */
    final /* synthetic */ String f9505e;
    /* renamed from: f */
    final /* synthetic */ String f9506f;
    /* renamed from: g */
    final /* synthetic */ String f9507g;
    /* renamed from: h */
    final /* synthetic */ String f9508h;
    /* renamed from: i */
    final /* synthetic */ String f9509i;
    /* renamed from: j */
    final /* synthetic */ int f9510j;
    /* renamed from: k */
    final /* synthetic */ int f9511k;
    /* renamed from: l */
    final /* synthetic */ ClubActivityReleaseActivity f9512l;

    ClubActivityReleaseActivity$2(ClubActivityReleaseActivity clubActivityReleaseActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, int i2) {
        this.f9512l = clubActivityReleaseActivity;
        this.f9501a = str;
        this.f9502b = str2;
        this.f9503c = str3;
        this.f9504d = str4;
        this.f9505e = str5;
        this.f9506f = str6;
        this.f9507g = str7;
        this.f9508h = str8;
        this.f9509i = str9;
        this.f9510j = i;
        this.f9511k = i2;
    }

    /* renamed from: a */
    public void mo3362a(String str) {
        ClubActivityReleaseActivity.a(this.f9512l, this.f9501a, this.f9502b, this.f9503c, this.f9504d, this.f9505e, this.f9506f, this.f9507g, this.f9508h, this.f9509i, this.f9510j, this.f9511k, str);
    }

    /* renamed from: a */
    public void mo3361a() {
        ClubActivityReleaseActivity.b(this.f9512l).setClickable(true);
        if (ClubActivityReleaseActivity.a(this.f9512l) != null) {
            ClubActivityReleaseActivity.a(this.f9512l).cancel();
        }
    }
}
