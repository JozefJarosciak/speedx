package com.beastbikes.android.modules.preferences.ui.offlineMap.p072a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p141b.C2319a;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;
import java.util.List;

/* compiled from: OfflineMapHotCitiesAdapter */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.a.c */
public class C2317c extends C2315a<C2322a> {
    /* renamed from: d */
    private Context f11033d;
    /* renamed from: e */
    private MKOfflineMap f11034e;
    /* renamed from: f */
    private C2319a f11035f;

    public C2317c(Context context, MKOfflineMap mKOfflineMap, C2319a c2319a) {
        super(context);
        this.f11033d = context;
        this.f11034e = mKOfflineMap;
        this.f11035f = c2319a;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        c$a c_a;
        if (view == null) {
            view = LayoutInflater.from(this.f11033d).inflate(C1373R.layout.activity_offline_map_item_province_child, null);
            view.setBackgroundColor(Color.parseColor("#ffffff"));
            c$a c_a2 = new c$a(this, view);
            view.setTag(c_a2);
            c_a = c_a2;
        } else {
            c_a = (c$a) view.getTag();
        }
        c_a.a((C2322a) getItem(i));
        return view;
    }

    /* renamed from: a */
    public void mo3478a(List<C2322a> list) {
        super.mo3478a(list);
    }
}
