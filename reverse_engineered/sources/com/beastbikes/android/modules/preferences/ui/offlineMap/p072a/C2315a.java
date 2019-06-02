package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import java.util.List;

/* compiled from: ArrayListAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.a */
public abstract class C2315a<T> extends BaseAdapter {
    /* renamed from: a */
    protected Context f11024a;
    /* renamed from: b */
    protected List<T> f11025b;
    /* renamed from: c */
    protected LayoutInflater f11026c;

    public C2315a(Context context) {
        this.f11024a = context;
        this.f11026c = LayoutInflater.from(context);
    }

    public int getCount() {
        if (this.f11025b == null) {
            return 0;
        }
        return this.f11025b.size();
    }

    public Object getItem(int i) {
        if (this.f11025b == null || i < 0 || i > this.f11025b.size() - 1) {
            return null;
        }
        return this.f11025b.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    /* renamed from: a */
    public void mo3478a(List<T> list) {
        this.f11025b = list;
        notifyDataSetChanged();
    }
}
