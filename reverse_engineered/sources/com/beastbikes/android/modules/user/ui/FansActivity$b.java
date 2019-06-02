package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

final class FansActivity$b extends ViewHolder {
    /* renamed from: a */
    final /* synthetic */ FansActivity f11612a;
    /* renamed from: b */
    private ImageView f11613b;
    /* renamed from: c */
    private TextView f11614c;
    /* renamed from: d */
    private TextView f11615d;
    /* renamed from: e */
    private View f11616e;

    protected FansActivity$b(FansActivity fansActivity, View view) {
        this.f11612a = fansActivity;
        super(view);
        this.f11616e = view;
        this.f11613b = (ImageView) view.findViewById(C1373R.id.fans_and_follow_item_avatar);
        this.f11614c = (TextView) view.findViewById(C1373R.id.fans_and_follow_item_name);
        this.f11615d = (TextView) view.findViewById(C1373R.id.fans_and_follow_item_status);
    }
}
