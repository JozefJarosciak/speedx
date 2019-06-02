package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.SearchRegionDTO;
import com.beastbikes.android.modules.user.ui.p160c.C2524a;

class SearchRegionActivity$b extends ViewHolder implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ SearchRegionActivity f11775a;
    /* renamed from: b */
    private C2524a f11776b;
    /* renamed from: c */
    private TextView f11777c;

    SearchRegionActivity$b(SearchRegionActivity searchRegionActivity, View view, C2524a c2524a) {
        this.f11775a = searchRegionActivity;
        super(view);
        this.f11776b = c2524a;
        this.f11777c = (TextView) view.findViewById(C1373R.id.tv_search_region_item);
        view.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.f11776b != null) {
            this.f11776b.m12671a(view, (SearchRegionDTO) view.getTag());
        }
    }
}
