package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p141b.C2319a;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;

/* compiled from: OfflineMapManagerAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.d */
public class C2318d extends C2315a<C2322a> {
    /* renamed from: d */
    private Context f11040d;
    /* renamed from: e */
    private MKOfflineMap f11041e;
    /* renamed from: f */
    private C2319a f11042f;

    public C2318d(Context context, MKOfflineMap mKOfflineMap, C2319a c2319a) {
        super(context);
        this.f11040d = context;
        this.f11041e = mKOfflineMap;
        this.f11042f = c2319a;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        d$a d_a;
        if (view == null) {
            view = this.c.inflate(C1373R.layout.activity_offline_map_item_manager, null);
            d$a d_a2 = new d$a(this, view);
            view.setTag(d_a2);
            d_a = d_a2;
        } else {
            d_a = (d$a) view.getTag();
        }
        d_a.a((C2322a) getItem(i));
        return view;
    }
}
