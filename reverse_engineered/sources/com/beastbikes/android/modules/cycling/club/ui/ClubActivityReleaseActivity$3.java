package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityInfo;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubActivityReleaseActivity$3 extends AsyncTask<Object, Object, ClubActivityInfo> {
    /* renamed from: a */
    final /* synthetic */ String f9513a;
    /* renamed from: b */
    final /* synthetic */ String f9514b;
    /* renamed from: c */
    final /* synthetic */ String f9515c;
    /* renamed from: d */
    final /* synthetic */ String f9516d;
    /* renamed from: e */
    final /* synthetic */ String f9517e;
    /* renamed from: f */
    final /* synthetic */ String f9518f;
    /* renamed from: g */
    final /* synthetic */ String f9519g;
    /* renamed from: h */
    final /* synthetic */ String f9520h;
    /* renamed from: i */
    final /* synthetic */ String f9521i;
    /* renamed from: j */
    final /* synthetic */ String f9522j;
    /* renamed from: k */
    final /* synthetic */ int f9523k;
    /* renamed from: l */
    final /* synthetic */ int f9524l;
    /* renamed from: m */
    final /* synthetic */ String f9525m;
    /* renamed from: n */
    final /* synthetic */ String f9526n;
    /* renamed from: o */
    final /* synthetic */ ClubActivityReleaseActivity f9527o;

    ClubActivityReleaseActivity$3(ClubActivityReleaseActivity clubActivityReleaseActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, int i2, String str11, String str12) {
        this.f9527o = clubActivityReleaseActivity;
        this.f9513a = str;
        this.f9514b = str2;
        this.f9515c = str3;
        this.f9516d = str4;
        this.f9517e = str5;
        this.f9518f = str6;
        this.f9519g = str7;
        this.f9520h = str8;
        this.f9521i = str9;
        this.f9522j = str10;
        this.f9523k = i;
        this.f9524l = i2;
        this.f9525m = str11;
        this.f9526n = str12;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10718a(objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10719a((ClubActivityInfo) obj);
    }

    /* renamed from: a */
    protected ClubActivityInfo m10718a(Object... objArr) {
        if (this.f9527o.getIntent().hasExtra("club_activity_manage_tag") && this.f9527o.getIntent().getIntExtra("club_activity_manage_tag", -1) == 2) {
            return ClubActivityReleaseActivity.d(this.f9527o).m10560a(ClubActivityReleaseActivity.c(this.f9527o).getActId(), this.f9513a, this.f9514b, this.f9515c, this.f9516d, this.f9517e, this.f9518f, this.f9519g, this.f9520h, this.f9521i, this.f9522j, this.f9523k, this.f9524l, this.f9525m, this.f9526n);
        }
        return ClubActivityReleaseActivity.d(this.f9527o).m10559a(this.f9513a, this.f9514b, this.f9515c, this.f9516d, this.f9517e, this.f9518f, this.f9519g, this.f9520h, this.f9521i, this.f9522j, this.f9523k, this.f9524l, this.f9525m, this.f9526n);
    }

    /* renamed from: a */
    protected void m10719a(ClubActivityInfo clubActivityInfo) {
        super.onPostExecute(clubActivityInfo);
        ClubActivityReleaseActivity.b(this.f9527o).setClickable(true);
        if (ClubActivityReleaseActivity.a(this.f9527o) != null) {
            ClubActivityReleaseActivity.a(this.f9527o).cancel();
        }
        if (clubActivityInfo != null) {
            if (!this.f9527o.getIntent().hasExtra("club_activity_manage_tag")) {
                Editor edit = ClubActivityReleaseActivity.e(this.f9527o).edit();
                edit.clear();
                edit.commit();
            }
            if (clubActivityInfo != null) {
                Uri parse = Uri.parse(ClubActivityInfoBrowserActivity.m10682a(clubActivityInfo.getActId(), this.f9527o));
                Intent intent = new Intent(this.f9527o, ClubActivityInfoBrowserActivity.class);
                intent.setData(parse);
                intent.putExtra("activity_type", 1);
                intent.putExtra("activity_id", clubActivityInfo.getActId());
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setPackage(this.f9527o.getPackageName());
                this.f9527o.startActivity(intent);
                this.f9527o.setResult(-1, this.f9527o.getIntent());
                this.f9527o.finish();
                return;
            }
            return;
        }
        Toasts.showOnUiThread(this.f9527o, this.f9527o.getString(C1373R.string.network_not_awesome));
    }
}
