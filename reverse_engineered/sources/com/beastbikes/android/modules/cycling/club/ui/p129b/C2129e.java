package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.ui.p130a.C2106a;
import com.beastbikes.android.modules.cycling.club.ui.widget.MyViewPager;
import com.beastbikes.android.modules.shop.ui.BikeShopDetailActivity;
import com.beastbikes.android.widget.PageIndicator;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout;
import com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.StickyListHeadersListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: FeedItemHeader */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.e */
public class C2129e extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    protected PageIndicator f9949a;
    /* renamed from: b */
    private MyViewPager f9950b;
    /* renamed from: c */
    private TextView f9951c;
    /* renamed from: d */
    private TextView f9952d;
    /* renamed from: e */
    private TextView f9953e;
    /* renamed from: f */
    private ClubInfoCompact f9954f;
    /* renamed from: g */
    private C2106a f9955g;
    /* renamed from: h */
    private Context f9956h;
    /* renamed from: i */
    private ImageView f9957i;
    /* renamed from: j */
    private LinearLayout f9958j;
    /* renamed from: k */
    private Logger f9959k = LoggerFactory.getLogger(C2129e.class);

    public C2129e(Context context) {
        super(context);
        this.f9956h = context;
        m10991a();
    }

    @SuppressLint({"WrongViewCast"})
    /* renamed from: a */
    private void m10991a() {
        LayoutInflater.from(getContext()).inflate(C1373R.layout.clubfeed_header, this);
        this.f9950b = (MyViewPager) findViewById(C1373R.id.pager_banner);
        this.f9949a = (PageIndicator) findViewById(C1373R.id.indicator);
        MyViewPager myViewPager = this.f9950b;
        PagerAdapter c2106a = new C2106a(this.f9954f, getContext());
        this.f9955g = c2106a;
        myViewPager.setAdapter(c2106a);
        this.f9950b.setCurrentItem(0);
        this.f9949a.setViewPager(this.f9950b);
        this.f9952d = (TextView) findViewById(C1373R.id.club_info_total_distance);
        this.f9953e = (TextView) findViewById(C1373R.id.club_info_member_count);
        this.f9951c = (TextView) findViewById(C1373R.id.club_info_location);
        this.f9957i = (ImageView) findViewById(C1373R.id.club_feed_info_type);
        this.f9958j = (LinearLayout) findViewById(C1373R.id.club_feed_info_type_ll);
    }

    /* renamed from: a */
    public void m10992a(ClubInfoCompact clubInfoCompact) {
        this.f9954f = clubInfoCompact;
        this.f9951c.setText(clubInfoCompact.getCity());
        if (C1849a.m9645b(this.f9956h)) {
            this.f9952d.setText(Math.round(clubInfoCompact.getMilestone() / 1000.0d) + "" + getResources().getText(C1373R.string.kilometre));
        } else {
            this.f9952d.setText(Math.round(C1849a.m9638a(clubInfoCompact.getMilestone() / 1000.0d)) + "" + getResources().getText(C1373R.string.str_mileage_unit_mile));
        }
        this.f9953e.setText(clubInfoCompact.getMembers() + "" + getResources().getText(C1373R.string.person));
        this.f9955g.m10929a(clubInfoCompact);
        if (C1849a.m9641a()) {
            switch (clubInfoCompact.getType()) {
                case 0:
                    this.f9957i.setImageResource(0);
                    return;
                case 1:
                    this.f9957i.setImageResource(C1373R.drawable.ic_club_type_shop);
                    this.f9958j.setOnClickListener(this);
                    return;
                case 2:
                    this.f9957i.setImageResource(C1373R.drawable.ic_club_type_school);
                    this.f9958j.setOnClickListener(this);
                    return;
                default:
                    return;
            }
        }
        this.f9957i.setVisibility(4);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.f9956h, BikeShopDetailActivity.class);
        intent.putExtra("bike_shop_id", this.f9954f.getLinkTo());
        this.f9956h.startActivity(intent);
    }

    /* renamed from: a */
    public void m10993a(SwipeRefreshAndLoadLayout swipeRefreshAndLoadLayout, StickyListHeadersListView stickyListHeadersListView) {
        if (this.f9950b != null) {
            this.f9950b.m11036a(swipeRefreshAndLoadLayout, stickyListHeadersListView);
        }
    }
}
