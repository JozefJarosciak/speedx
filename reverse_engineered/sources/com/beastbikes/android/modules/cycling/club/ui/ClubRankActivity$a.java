package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubRankBean;
import com.beastbikes.android.modules.cycling.club.ui.ClubRankActivity.C1411b;
import java.util.List;

public class ClubRankActivity$a extends BaseAdapter {
    /* renamed from: a */
    private List<ClubRankBean> f9793a;
    /* renamed from: b */
    private Context f9794b;

    public ClubRankActivity$a(Context context, List<ClubRankBean> list) {
        this.f9794b = context;
        this.f9793a = list;
    }

    public int getCount() {
        return this.f9793a.size();
    }

    public Object getItem(int i) {
        return this.f9793a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1411b c1411b;
        if (view == null) {
            view = LayoutInflater.from(this.f9794b).inflate(C1373R.layout.universal_rank_item, null);
            C1411b c1411b2 = new C1411b(view);
            view.setTag(c1411b2);
            c1411b = c1411b2;
        } else {
            c1411b = (C1411b) view.getTag();
        }
        ClubRankBean clubRankBean = (ClubRankBean) this.f9793a.get(i);
        clubRankBean.setOrdinal(i + 1);
        c1411b.a(clubRankBean);
        return view;
    }
}
