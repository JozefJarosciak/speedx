package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.beastbikes.android.modules.cycling.club.dto.ApplyDTO;
import com.beastbikes.android.modules.user.ui.ProfileActivity;

class ApplyManagerActivity$1 implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ ApplyManagerActivity f9401a;

    ApplyManagerActivity$1(ApplyManagerActivity applyManagerActivity) {
        this.f9401a = applyManagerActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ApplyDTO applyDTO = (ApplyDTO) adapterView.getItemAtPosition(i);
        if (applyDTO != null) {
            Intent intent = new Intent();
            intent.setClass(this.f9401a, ProfileActivity.class);
            intent.putExtra("user_id", applyDTO.getUserId());
            intent.putExtra("avatar", applyDTO.getAvatarUrl());
            intent.putExtra("nick_name", applyDTO.getNickname());
            intent.putExtra("remarks", applyDTO.getRemarks());
            this.f9401a.startActivity(intent);
        }
    }
}
