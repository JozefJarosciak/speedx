package com.beastbikes.android.modules.cycling.club.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.C2067f;
import com.beastbikes.android.modules.cycling.club.ui.ClubHistoryNoticeActivity.C1408b;
import java.util.List;

class ClubHistoryNoticeActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ ClubHistoryNoticeActivity f9693a;
    /* renamed from: b */
    private List<C2067f> f9694b;

    public ClubHistoryNoticeActivity$a(ClubHistoryNoticeActivity clubHistoryNoticeActivity, List<C2067f> list) {
        this.f9693a = clubHistoryNoticeActivity;
        this.f9694b = list;
    }

    public int getCount() {
        return this.f9694b.size();
    }

    public Object getItem(int i) {
        return this.f9694b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1408b c1408b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_history_notice, null);
            c1408b = new C1408b(this.f9693a, view);
        } else {
            c1408b = (C1408b) view.getTag();
        }
        c1408b.a((C2067f) this.f9694b.get(i));
        return view;
    }
}
