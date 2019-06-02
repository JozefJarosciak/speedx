package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

final class FollowSearchResultActivity$b extends ViewHolder {
    /* renamed from: a */
    final /* synthetic */ FollowSearchResultActivity f11709a;
    /* renamed from: b */
    private ImageView f11710b;
    /* renamed from: c */
    private TextView f11711c;
    /* renamed from: d */
    private TextView f11712d;
    /* renamed from: e */
    private TextView f11713e;
    /* renamed from: f */
    private TextView f11714f;
    /* renamed from: g */
    private View f11715g;

    protected FollowSearchResultActivity$b(FollowSearchResultActivity followSearchResultActivity, View view) {
        this.f11709a = followSearchResultActivity;
        super(view);
        this.f11715g = view;
        this.f11710b = (ImageView) view.findViewById(C1373R.id.follow_search_result_item_avatar);
        this.f11711c = (TextView) view.findViewById(C1373R.id.follow_search_result_item_name);
        this.f11712d = (TextView) view.findViewById(C1373R.id.follow_search_result_item_status);
        this.f11713e = (TextView) view.findViewById(C1373R.id.follow_search_result_item_value);
        this.f11714f = (TextView) view.findViewById(C1373R.id.follow_search_result_item_club_name);
    }
}
