package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.C2063b;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityUser;
import com.squareup.picasso.Picasso;

class ClubActivityManagerActivity$11 extends AsyncTask<Void, Void, C2063b> {
    /* renamed from: a */
    final /* synthetic */ ClubActivityManagerActivity f9472a;

    ClubActivityManagerActivity$11(ClubActivityManagerActivity clubActivityManagerActivity) {
        this.f9472a = clubActivityManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10700a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10701a((C2063b) obj);
    }

    /* renamed from: a */
    protected C2063b m10700a(Void... voidArr) {
        if (TextUtils.isEmpty(ClubActivityManagerActivity.a(this.f9472a).getActId())) {
            return null;
        }
        return ClubActivityManagerActivity.b(this.f9472a).m10562b(ClubActivityManagerActivity.a(this.f9472a).getActId(), ClubActivityManagerActivity.g(this.f9472a), ClubActivityManagerActivity.h(this.f9472a));
    }

    /* renamed from: a */
    protected void m10701a(C2063b c2063b) {
        if (c2063b != null) {
            ClubActivityManagerActivity.a(this.f9472a, c2063b.m10625a());
            if (ClubActivityManagerActivity.i(this.f9472a) != null && ClubActivityManagerActivity.i(this.f9472a).size() > 0) {
                ClubActivityManagerActivity.j(this.f9472a).setText(ClubActivityManagerActivity.i(this.f9472a).size() + this.f9472a.getResources().getString(C1373R.string.person));
                int i = 0;
                while (i < ClubActivityManagerActivity.i(this.f9472a).size() && i != 6) {
                    if (TextUtils.isEmpty(((ClubActivityUser) ClubActivityManagerActivity.i(this.f9472a).get(i)).getAvatar())) {
                        ((ImageView) ClubActivityManagerActivity.k(this.f9472a).get(i)).setImageResource(C1373R.drawable.ic_avatar);
                    } else {
                        Picasso.with(this.f9472a).load(((ClubActivityUser) ClubActivityManagerActivity.i(this.f9472a).get(i)).getAvatar()).fit().centerCrop().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).into((ImageView) ClubActivityManagerActivity.k(this.f9472a).get(i));
                    }
                    i++;
                }
            }
        }
    }
}
