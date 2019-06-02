package com.beastbikes.android.modules.cycling.club.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class ClubActivityManagerActivity$8 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ Dialog f9485a;
    /* renamed from: b */
    final /* synthetic */ ClubActivityManagerActivity f9486b;

    ClubActivityManagerActivity$8(ClubActivityManagerActivity clubActivityManagerActivity, Dialog dialog) {
        this.f9486b = clubActivityManagerActivity;
        this.f9485a = dialog;
    }

    public void onClick(View view) {
        ClubActivityManagerActivity.d(this.f9486b);
        this.f9485a.dismiss();
    }
}
