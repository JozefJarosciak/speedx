package com.beastbikes.android.modules.cycling.club.ui;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;

@SuppressLint({"InflateParams"})
/* compiled from: ClubSearchAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.a */
public class C2110a extends BaseListAdapter<ClubInfoCompact> {
    public C2110a(Handler handler, AbsListView absListView) {
        super(handler, absListView);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1413b c1413b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.club_list_item, null);
            c1413b = new C1413b(view);
        } else {
            c1413b = (C1413b) view.getTag();
        }
        c1413b.a((ClubInfoCompact) getItem(i));
        if (i == getCount() - 1) {
            c1413b.f5344a.setVisibility(0);
            c1413b.f5345b.setVisibility(4);
        }
        return view;
    }

    protected void recycleView(View view) {
    }
}
