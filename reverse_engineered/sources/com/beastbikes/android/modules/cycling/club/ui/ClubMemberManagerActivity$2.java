package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.widget.C2621c;

class ClubMemberManagerActivity$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f9725a;
    /* renamed from: b */
    final /* synthetic */ Intent f9726b;
    /* renamed from: c */
    final /* synthetic */ RankDTO f9727c;
    /* renamed from: d */
    final /* synthetic */ ClubMemberManagerActivity f9728d;

    ClubMemberManagerActivity$2(ClubMemberManagerActivity clubMemberManagerActivity, C2621c c2621c, Intent intent, RankDTO rankDTO) {
        this.f9728d = clubMemberManagerActivity;
        this.f9725a = c2621c;
        this.f9726b = intent;
        this.f9727c = rankDTO;
    }

    public void onClick(View view) {
        this.f9725a.m13069b();
        this.f9726b.putExtra("club_member", this.f9727c.getUserId());
        this.f9726b.putExtra("is_quit", ClubMemberManagerActivity.a(this.f9728d));
        this.f9726b.putExtra("club_member_name", this.f9727c.getNickname());
        this.f9728d.setResult(-1, this.f9726b);
        this.f9728d.finish();
    }
}
