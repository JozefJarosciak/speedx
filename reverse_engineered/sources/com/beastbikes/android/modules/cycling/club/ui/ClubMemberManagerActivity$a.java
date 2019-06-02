package com.beastbikes.android.modules.cycling.club.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.ui.ClubMemberManagerActivity.C1409b;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import java.util.List;

final class ClubMemberManagerActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ ClubMemberManagerActivity f9730a;
    /* renamed from: b */
    private ClubManager f9731b;
    /* renamed from: c */
    private List<RankDTO> f9732c;

    public ClubMemberManagerActivity$a(ClubMemberManagerActivity clubMemberManagerActivity, List<RankDTO> list, ClubManager clubManager) {
        this.f9730a = clubMemberManagerActivity;
        this.f9732c = list;
        this.f9731b = clubManager;
    }

    public int getCount() {
        return this.f9732c.size();
    }

    public Object getItem(int i) {
        return this.f9732c.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        C1409b c1409b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.club_member_list_item, null);
            c1409b = new C1409b(this.f9730a, view, this.f9731b);
        } else {
            c1409b = (C1409b) view.getTag();
        }
        c1409b.a((RankDTO) this.f9732c.get(i));
        if (i == this.f9732c.size() - 1) {
            c1409b.f5160a.setVisibility(8);
            c1409b.f5161b.setVisibility(0);
        } else {
            c1409b.f5160a.setVisibility(0);
            c1409b.f5161b.setVisibility(8);
        }
        return view;
    }
}
