package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityListDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.widget.C2638d.C2077a;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

class ClubActivityListFragment$a extends C2077a {
    /* renamed from: a */
    final /* synthetic */ ClubActivityListFragment f9468a;
    /* renamed from: b */
    private Context f9469b;
    /* renamed from: c */
    private C2534b f9470c;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.ClubActivityListFragment$a$a */
    class C2079a extends ViewHolder {
        /* renamed from: a */
        final /* synthetic */ ClubActivityListFragment$a f9458a;
        /* renamed from: b */
        private TextView f9459b;
        /* renamed from: c */
        private TextView f9460c;
        /* renamed from: d */
        private CircleImageView f9461d;
        /* renamed from: e */
        private TextView f9462e;
        /* renamed from: f */
        private TextView f9463f;
        /* renamed from: g */
        private TextView f9464g;
        /* renamed from: h */
        private ImageView f9465h;
        /* renamed from: i */
        private TextView f9466i;
        /* renamed from: j */
        private View f9467j;

        public C2079a(ClubActivityListFragment$a clubActivityListFragment$a, View view) {
            this.f9458a = clubActivityListFragment$a;
            super(view);
            this.f9467j = view;
            this.f9459b = (TextView) view.findViewById(C1373R.id.layout_club_activity_list_item_manage);
            this.f9460c = (TextView) view.findViewById(C1373R.id.layout_club_activity_list_item_title);
            this.f9461d = (CircleImageView) view.findViewById(C1373R.id.layout_club_activity_list_item_avater);
            this.f9462e = (TextView) view.findViewById(C1373R.id.layout_club_activity_list_item_nickname);
            this.f9463f = (TextView) view.findViewById(C1373R.id.layout_club_activity_list_item_location);
            this.f9464g = (TextView) view.findViewById(C1373R.id.layout_club_activity_list_item_time);
            this.f9465h = (ImageView) view.findViewById(C1373R.id.layout_club_activity_list_item_cover);
            this.f9466i = (TextView) view.findViewById(C1373R.id.layout_club_activity_list_item_status);
        }
    }

    public ClubActivityListFragment$a(ClubActivityListFragment clubActivityListFragment, Context context, C2534b c2534b) {
        this.f9468a = clubActivityListFragment;
        this.f9469b = context;
        this.f9470c = c2534b;
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        View inflate = LayoutInflater.from(this.f9469b).inflate(C1373R.layout.layout_club_activity_list_item, null);
        inflate.setLayoutParams(new LayoutParams(ClubActivityListFragment.h(this.f9468a).widthPixels, (int) (((double) ClubActivityListFragment.h(this.f9468a).widthPixels) / ClubActivityListFragment.i(this.f9468a))));
        return new C2079a(this, inflate);
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, final int i, boolean z) {
        if (viewHolder instanceof C2079a) {
            final C2079a c2079a = (C2079a) viewHolder;
            ClubActivityListDTO clubActivityListDTO = (ClubActivityListDTO) obj;
            c2079a.f9460c.setText(clubActivityListDTO.getTitle());
            if (clubActivityListDTO.isManager()) {
                c2079a.f9459b.setVisibility(0);
            } else {
                c2079a.f9459b.setVisibility(8);
            }
            c2079a.f9462e.setText(C2570p.m12883a(clubActivityListDTO.getNickname(), clubActivityListDTO.getRemarks()));
            c2079a.f9464g.setText(m10695a(clubActivityListDTO.getStartDate()) + " － " + m10695a(clubActivityListDTO.getEndDate()));
            c2079a.f9463f.setText(this.f9468a.getResources().getString(C1373R.string.activity_club_release_activities_activity_place) + "：" + clubActivityListDTO.getMobPlace());
            switch (clubActivityListDTO.getApplyStatus()) {
                case 0:
                    if (!clubActivityListDTO.isJoined()) {
                        c2079a.f9466i.setBackgroundResource(C1373R.drawable.bg_layout_club_activity_list_item_status_joining);
                        c2079a.f9466i.setText(this.f9468a.getResources().getString(C1373R.string.club_activity_joining));
                        break;
                    }
                    c2079a.f9466i.setBackgroundResource(C1373R.drawable.bg_layout_club_activity_list_item_status_joined);
                    c2079a.f9466i.setText(this.f9468a.getResources().getString(C1373R.string.club_activity_has_joined));
                    break;
                case 2:
                    c2079a.f9466i.setText(this.f9468a.getResources().getString(C1373R.string.club_activity_has_ended));
                    c2079a.f9466i.setBackgroundResource(C1373R.drawable.bg_layout_club_activity_list_item_status_cancel);
                    break;
                case 3:
                    c2079a.f9466i.setText(this.f9468a.getResources().getString(C1373R.string.club_activity_has_cancel));
                    c2079a.f9466i.setBackgroundResource(C1373R.drawable.bg_layout_club_activity_list_item_status_cancel);
                    break;
            }
            c2079a.f9467j.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ ClubActivityListFragment$a f9457c;

                public void onClick(View view) {
                    this.f9457c.f9470c.mo3520a(c2079a, i);
                }
            });
            if (TextUtils.isEmpty(clubActivityListDTO.getAvatarImage())) {
                Picasso.with(this.f9469b).load(C1373R.drawable.ic_avatar).fit().centerCrop().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).into(c2079a.f9461d);
            } else {
                Picasso.with(this.f9469b).load(clubActivityListDTO.getAvatarImage() + "?imageView2/1/w/120/h/120").fit().centerCrop().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).into(c2079a.f9461d);
            }
            if (TextUtils.isEmpty(clubActivityListDTO.getCover())) {
                Picasso.with(this.f9469b).load(C1373R.drawable.bg_layout_club_activity_list_item).fit().centerCrop().placeholder(C1373R.drawable.bg_layout_club_activity_list_item).error(C1373R.drawable.bg_layout_club_activity_list_item).into(c2079a.f9465h);
            } else {
                Picasso.with(this.f9469b).load(clubActivityListDTO.getCover()).fit().centerCrop().placeholder(C1373R.drawable.bg_layout_club_activity_list_item).error(C1373R.drawable.bg_layout_club_activity_list_item).into(c2079a.f9465h);
            }
        }
    }

    /* renamed from: a */
    private String m10695a(String str) {
        long b = C2555d.m12800b(str);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(Long.valueOf(b));
    }
}
