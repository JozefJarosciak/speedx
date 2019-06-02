package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

final class FollowActivity$d extends ViewHolder {
    /* renamed from: a */
    final /* synthetic */ FollowActivity f11671a;
    /* renamed from: b */
    private ImageView f11672b;
    /* renamed from: c */
    private TextView f11673c;
    /* renamed from: d */
    private TextView f11674d;
    /* renamed from: e */
    private View f11675e;

    protected FollowActivity$d(FollowActivity followActivity, View view) {
        this.f11671a = followActivity;
        super(view);
        this.f11675e = view;
        this.f11672b = (ImageView) view.findViewById(C1373R.id.fans_and_follow_item_avatar);
        this.f11673c = (TextView) view.findViewById(C1373R.id.fans_and_follow_item_name);
        this.f11674d = (TextView) view.findViewById(C1373R.id.fans_and_follow_item_status);
    }
}
