package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

class FTPListActivity$c extends ViewHolder {
    /* renamed from: a */
    private TextView f11579a;
    /* renamed from: b */
    private TextView f11580b;
    /* renamed from: c */
    private View f11581c;

    FTPListActivity$c(View view) {
        super(view);
        this.f11581c = view;
        this.f11579a = (TextView) view.findViewById(C1373R.id.item_ftp_view_date);
        this.f11580b = (TextView) view.findViewById(C1373R.id.item_ftp_view_value);
    }
}
