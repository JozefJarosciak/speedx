package com.beastbikes.android.modules.cycling.club.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class ClubActivityManagerActivity$7 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ Dialog f9483a;
    /* renamed from: b */
    final /* synthetic */ ClubActivityManagerActivity f9484b;

    ClubActivityManagerActivity$7(ClubActivityManagerActivity clubActivityManagerActivity, Dialog dialog) {
        this.f9484b = clubActivityManagerActivity;
        this.f9483a = dialog;
    }

    public void onClick(View view) {
        if (this.f9483a != null) {
            this.f9483a.dismiss();
        }
    }
}
