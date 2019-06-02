package com.beastbikes.android.modules.shop.ui;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.framework.ui.android.lib.list.BaseListAdapter;

@SuppressLint({"InflateParams"})
/* compiled from: BikeShopListAdapter */
/* renamed from: com.beastbikes.android.modules.shop.ui.a */
public class C2339a extends BaseListAdapter<BikeShopListDTO> {
    /* renamed from: a */
    private String f11120a;

    public C2339a(Handler handler, AbsListView absListView, String str) {
        super(handler, absListView);
        this.f11120a = str;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1432b c1432b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.bike_shop_listitem, null);
            c1432b = new C1432b(view, this.f11120a);
        } else {
            c1432b = (C1432b) view.getTag();
        }
        c1432b.a((BikeShopListDTO) getItem(i));
        return view;
    }

    protected void recycleView(View view) {
    }
}
