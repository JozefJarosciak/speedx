package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.cycling.ranking.ui.C1416b;
import java.util.List;

final class ClubMemberRankActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ ClubMemberRankActivity f9740a;
    /* renamed from: b */
    private final List<RankDTO> f9741b;
    /* renamed from: c */
    private boolean f9742c = true;

    public ClubMemberRankActivity$a(ClubMemberRankActivity clubMemberRankActivity, List<RankDTO> list) {
        this.f9740a = clubMemberRankActivity;
        this.f9741b = list;
        if (!C1849a.m9645b((Context) clubMemberRankActivity)) {
            this.f9742c = false;
        }
    }

    public int getCount() {
        return this.f9741b.size();
    }

    public Object getItem(int i) {
        return this.f9741b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1416b c1416b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.ranking_fragment_list_item, null);
            C1416b c1416b2 = new C1416b(view, this.f9742c);
            view.setTag(c1416b2);
            c1416b = c1416b2;
        } else {
            c1416b = (C1416b) view.getTag();
        }
        c1416b.a((RankDTO) this.f9741b.get(i));
        return view;
    }
}
