package com.beastbikes.android.modules.cycling.club.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.C2066e;
import com.beastbikes.android.modules.cycling.club.ui.ClubMsgActivity.C1410b;
import java.util.List;

class ClubMsgActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ ClubMsgActivity f9775a;
    /* renamed from: b */
    private List<C2066e> f9776b;

    public ClubMsgActivity$a(ClubMsgActivity clubMsgActivity, List<C2066e> list) {
        this.f9775a = clubMsgActivity;
        this.f9776b = list;
    }

    public int getCount() {
        return this.f9776b.size();
    }

    public Object getItem(int i) {
        return this.f9776b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1410b c1410b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_club_msg, null);
            c1410b = new C1410b(this.f9775a, view);
        } else {
            c1410b = (C1410b) view.getTag();
        }
        c1410b.a((C2066e) this.f9776b.get(i));
        return view;
    }
}
