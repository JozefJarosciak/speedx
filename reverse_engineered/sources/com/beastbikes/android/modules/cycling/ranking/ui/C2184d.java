package com.beastbikes.android.modules.cycling.ranking.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.ranking.dto.C2173a;
import com.beastbikes.framework.android.p056e.C1376d;
import java.util.List;

/* compiled from: RankingAdapter2 */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.d */
public final class C2184d extends BaseAdapter {
    /* renamed from: a */
    private final C1376d f10245a;
    /* renamed from: b */
    private final List<C2173a> f10246b;
    /* renamed from: c */
    private boolean f10247c = true;
    /* renamed from: d */
    private String f10248d;

    public C2184d(Context context, C1376d c1376d, List<C2173a> list, String str) {
        this.f10245a = c1376d;
        this.f10246b = list;
        if (!C1849a.m9645b(context)) {
            this.f10247c = false;
        }
        this.f10248d = str;
    }

    public int getCount() {
        return this.f10246b.size();
    }

    public Object getItem(int i) {
        return this.f10246b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1417c c1417c;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.ranking_fragment_list_item2, null);
            c1417c = new C1417c(this.f10245a, view, this.f10247c);
        } else {
            c1417c = (C1417c) view.getTag();
        }
        C2173a c2173a = (C2173a) this.f10246b.get(i);
        c2173a.m11136a(i + 1);
        c1417c.a(c2173a);
        if (c2173a.m11137b().equals(this.f10248d)) {
            c1417c.f5422a.setBackgroundResource(C1373R.color.discover_color1);
        } else {
            c1417c.f5422a.setBackgroundResource(C1373R.color.discover_color3);
        }
        return view;
    }
}
