package com.beastbikes.android.modules.cycling.club.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.ui.ThumbsListActivity.C1412b;
import java.util.List;

class ThumbsListActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ ThumbsListActivity f9836a;
    /* renamed from: b */
    private List<ClubUser> f9837b;

    public ThumbsListActivity$a(ThumbsListActivity thumbsListActivity, List<ClubUser> list) {
        this.f9836a = thumbsListActivity;
        this.f9837b = list;
    }

    public int getCount() {
        return this.f9837b.size();
    }

    public Object getItem(int i) {
        return this.f9837b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1412b c1412b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_thumbs, null);
            c1412b = new C1412b(this.f9836a, view);
        } else {
            c1412b = (C1412b) view.getTag();
        }
        c1412b.a((ClubUser) this.f9837b.get(i));
        if (i == this.f9837b.size() - 1) {
            c1412b.f5319a.setVisibility(8);
            c1412b.f5320b.setVisibility(0);
        }
        return view;
    }
}
