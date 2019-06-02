package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

public class MedalsActivity$a extends ViewHolder {
    /* renamed from: a */
    public final View f11726a;
    /* renamed from: b */
    public final TextView f11727b;
    /* renamed from: c */
    public final ImageView f11728c;

    public MedalsActivity$a(View view) {
        super(view);
        this.f11726a = view;
        this.f11727b = (TextView) view.findViewById(C1373R.id.medal_item_medal_name);
        this.f11728c = (ImageView) view.findViewById(C1373R.id.medal_item_medal_icon);
    }
}
