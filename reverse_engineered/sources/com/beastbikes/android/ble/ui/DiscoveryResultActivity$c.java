package com.beastbikes.android.ble.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.ui.DiscoveryResultActivity.C1391b;
import java.util.ArrayList;
import java.util.List;

final class DiscoveryResultActivity$c extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ DiscoveryResultActivity f7593a;
    /* renamed from: b */
    private List<C1614a> f7594b = new ArrayList();

    public DiscoveryResultActivity$c(DiscoveryResultActivity discoveryResultActivity, List<C1614a> list) {
        this.f7593a = discoveryResultActivity;
        this.f7594b = list;
    }

    public int getCount() {
        return this.f7594b.size();
    }

    public Object getItem(int i) {
        return this.f7594b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1391b c1391b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.speed_force_device_item, null);
            c1391b = new C1391b(this.f7593a, view);
        } else {
            c1391b = (C1391b) view.getTag();
        }
        c1391b.a((C1614a) this.f7594b.get(i));
        return view;
    }
}
