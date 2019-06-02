package com.beastbikes.android.modules.cycling.club.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ApplyDTO;
import com.beastbikes.android.modules.cycling.club.ui.ApplyManagerActivity.C1405b;
import java.util.List;

final class ApplyManagerActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ ApplyManagerActivity f9405a;
    /* renamed from: b */
    private List<ApplyDTO> f9406b;
    /* renamed from: c */
    private ClubManager f9407c;

    public ApplyManagerActivity$a(ApplyManagerActivity applyManagerActivity, List<ApplyDTO> list, ClubManager clubManager) {
        this.f9405a = applyManagerActivity;
        this.f9406b = list;
        this.f9407c = clubManager;
    }

    public int getCount() {
        return this.f9406b.size();
    }

    public Object getItem(int i) {
        return this.f9406b.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        C1405b c1405b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.apply_list_item_black, null);
            c1405b = new C1405b(view, this.f9407c);
        } else {
            c1405b = (C1405b) view.getTag();
        }
        c1405b.a((ApplyDTO) this.f9406b.get(i));
        if (i == this.f9406b.size() - 1) {
            c1405b.f4794a.setVisibility(0);
        }
        return view;
    }
}
