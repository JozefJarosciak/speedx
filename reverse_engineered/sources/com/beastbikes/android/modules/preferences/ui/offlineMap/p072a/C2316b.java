package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p141b.C2319a;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;
import com.beastbikes.framework.android.p088g.C2801d;
import java.util.List;

/* compiled from: OfflineExpandableListAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.b */
public class C2316b extends BaseExpandableListAdapter {
    /* renamed from: a */
    protected LayoutInflater f11027a;
    /* renamed from: b */
    private Context f11028b;
    /* renamed from: c */
    private MKOfflineMap f11029c;
    /* renamed from: d */
    private C2319a f11030d;
    /* renamed from: e */
    private List<C2322a> f11031e;
    /* renamed from: f */
    private List<List<C2322a>> f11032f;

    public C2316b(Context context, MKOfflineMap mKOfflineMap, C2319a c2319a) {
        this.f11028b = context;
        this.f11029c = mKOfflineMap;
        this.f11030d = c2319a;
        this.f11027a = LayoutInflater.from(context);
    }

    @SuppressLint({"InflateParams"})
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        b$b b_b;
        if (view == null) {
            view = this.f11027a.inflate(C1373R.layout.activity_offline_map_item_province, null);
            b$b b_b2 = new b$b(this, view);
            view.setTag(b_b2);
            b_b = b_b2;
        } else {
            b_b = (b$b) view.getTag();
        }
        if (z) {
            b$b.a(b_b).setCompoundDrawablesWithIntrinsicBounds(null, null, this.f11028b.getResources().getDrawable(C1373R.drawable.ic_arrow_up), null);
        } else {
            b$b.a(b_b).setCompoundDrawablesWithIntrinsicBounds(null, null, this.f11028b.getResources().getDrawable(C1373R.drawable.ic_arrow_down), null);
        }
        C2322a c2322a = (C2322a) getGroup(i);
        if (!(b_b == null || c2322a == null)) {
            b_b.a(c2322a);
        }
        return view;
    }

    @SuppressLint({"InflateParams"})
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        b$a b_a;
        if (view == null) {
            view = this.f11027a.inflate(C1373R.layout.activity_offline_map_item_province_child, null);
            view.setPadding(C2801d.m13756a(this.f11028b, 12.0f), 0, 0, 0);
            b$a b_a2 = new b$a(this, view);
            view.setTag(b_a2);
            b_a = b_a2;
        } else {
            b_a = (b$a) view.getTag();
        }
        b_a.a((C2322a) getChild(i, i2));
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public int getGroupCount() {
        if (this.f11031e != null) {
            return this.f11031e.size();
        }
        return 0;
    }

    public int getChildrenCount(int i) {
        if (this.f11032f != null && i >= 0 && i < this.f11032f.size()) {
            List list = (List) this.f11032f.get(i);
            if (list != null) {
                return list.size();
            }
        }
        return 0;
    }

    public Object getGroup(int i) {
        if (this.f11031e == null || i < 0 || i >= this.f11031e.size()) {
            return null;
        }
        return this.f11031e.get(i);
    }

    public Object getChild(int i, int i2) {
        List list = (List) this.f11032f.get(i);
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return list.get(i2);
    }

    public long getGroupId(int i) {
        return 0;
    }

    public long getChildId(int i, int i2) {
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    /* renamed from: a */
    public void m11855a(List<C2322a> list, List<List<C2322a>> list2) {
        this.f11031e = list;
        this.f11032f = list2;
        notifyDataSetChanged();
    }
}
