package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubActivityInfoBrowserActivity;
import com.squareup.picasso.Picasso;

/* compiled from: FeedItemActivity */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.a */
public class C2112a extends C2111b<ClubFeedActivity> {
    /* renamed from: r */
    private TextView f9884r;
    /* renamed from: s */
    private TextView f9885s;
    /* renamed from: t */
    private TextView f9886t;
    /* renamed from: u */
    private ImageView f9887u;
    /* renamed from: v */
    private Context f9888v;

    public C2112a(Context context, View view, AVUser aVUser) {
        super(context, view, aVUser);
        this.f9888v = context;
    }

    /* renamed from: a */
    public void mo3413a() {
        this.f9884r = (TextView) findViewById(C1373R.id.title);
        this.f9885s = (TextView) findViewById(C1373R.id.date);
        this.f9886t = (TextView) findViewById(C1373R.id.place);
        this.f9887u = (ImageView) findViewById(C1373R.id.image);
        setOnClickListener(this);
    }

    /* renamed from: a */
    public void m10962a(ClubFeedActivity clubFeedActivity) {
        m10960b((Object) clubFeedActivity);
        if (clubFeedActivity != null) {
            this.f9884r.setText(clubFeedActivity.getTitle());
            this.f9885s.setText(clubFeedActivity.getStartDate());
            this.f9886t.setText(clubFeedActivity.getRouteName());
            if (!TextUtils.isEmpty(clubFeedActivity.getRouteImage())) {
                Picasso.with(this.f9888v).load(clubFeedActivity.getRouteImage()).fit().centerCrop().into(this.f9887u);
            }
        }
    }

    public View getView() {
        return LayoutInflater.from(getContext()).inflate(C1373R.layout.clubfeed_item_activity, this);
    }

    protected void onClick(View view, ClubFeedActivity clubFeedActivity) {
        if (view == this && clubFeedActivity != null) {
            String actId = clubFeedActivity.getActId();
            Intent intent = new Intent(getContext(), ClubActivityInfoBrowserActivity.class);
            intent.setData(Uri.parse(ClubActivityInfoBrowserActivity.m10682a(clubFeedActivity.getActId(), getContext())));
            intent.putExtra("activity_type", 1);
            intent.putExtra("activity_id", actId);
            getContext().startActivity(intent);
        }
    }
}
