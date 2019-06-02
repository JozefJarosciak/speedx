package com.beastbikes.android.modules.cycling.club.ui;

import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.C2168b.C2080a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubActivityReleaseActivity$1 implements C2080a {
    /* renamed from: a */
    final /* synthetic */ String f9488a;
    /* renamed from: b */
    final /* synthetic */ String f9489b;
    /* renamed from: c */
    final /* synthetic */ String f9490c;
    /* renamed from: d */
    final /* synthetic */ String f9491d;
    /* renamed from: e */
    final /* synthetic */ String f9492e;
    /* renamed from: f */
    final /* synthetic */ String f9493f;
    /* renamed from: g */
    final /* synthetic */ String f9494g;
    /* renamed from: h */
    final /* synthetic */ String f9495h;
    /* renamed from: i */
    final /* synthetic */ String f9496i;
    /* renamed from: j */
    final /* synthetic */ int f9497j;
    /* renamed from: k */
    final /* synthetic */ int f9498k;
    /* renamed from: l */
    final /* synthetic */ String f9499l;
    /* renamed from: m */
    final /* synthetic */ ClubActivityReleaseActivity f9500m;

    ClubActivityReleaseActivity$1(ClubActivityReleaseActivity clubActivityReleaseActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, int i2, String str10) {
        this.f9500m = clubActivityReleaseActivity;
        this.f9488a = str;
        this.f9489b = str2;
        this.f9490c = str3;
        this.f9491d = str4;
        this.f9492e = str5;
        this.f9493f = str6;
        this.f9494g = str7;
        this.f9495h = str8;
        this.f9496i = str9;
        this.f9497j = i;
        this.f9498k = i2;
        this.f9499l = str10;
    }

    /* renamed from: a */
    public void mo3374a(String str) {
        C2580w.m12905a(this.f9500m, "", "click_club_release_activity");
        ClubActivityReleaseActivity.a(this.f9500m, this.f9488a, "", this.f9489b, this.f9490c, this.f9491d, this.f9492e, this.f9493f, this.f9494g, this.f9495h, this.f9496i, this.f9497j, this.f9498k, str, this.f9499l);
    }

    /* renamed from: a */
    public void mo3373a() {
        if (ClubActivityReleaseActivity.a(this.f9500m) != null) {
            ClubActivityReleaseActivity.a(this.f9500m).cancel();
        }
        ClubActivityReleaseActivity.b(this.f9500m).setClickable(true);
        Toasts.showOnUiThread(this.f9500m, this.f9500m.getString(C1373R.string.network_not_awesome));
    }
}
