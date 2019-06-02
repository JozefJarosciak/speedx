package com.beastbikes.android.modules.cycling.activity.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.NumberTextView;

public class CyclingTargetSettingActivity$a extends ViewHolder {
    /* renamed from: a */
    public final TextView f8696a;
    /* renamed from: b */
    public final TextView f8697b;
    /* renamed from: c */
    public final ImageView f8698c;
    /* renamed from: d */
    public final NumberTextView f8699d;
    /* renamed from: e */
    public final View f8700e;

    public CyclingTargetSettingActivity$a(View view) {
        super(view);
        this.f8700e = view;
        this.f8696a = (TextView) view.findViewById(C1373R.id.cycling_target_config_item_title);
        this.f8697b = (TextView) view.findViewById(C1373R.id.cycling_target_config_item_subtitle);
        this.f8698c = (ImageView) view.findViewById(C1373R.id.cycling_target_config_item_check);
        this.f8699d = (NumberTextView) view.findViewById(C1373R.id.cycling_target_config_item_distance);
    }
}
