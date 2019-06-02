package com.beastbikes.android.modules.cycling.ranking.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.android.widget.C2638d.C2077a;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/* compiled from: RankRecyclerViewAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.a */
public class C2183a extends C2077a {
    /* renamed from: a */
    private Context f10243a;
    /* renamed from: b */
    private C2534b f10244b;

    /* compiled from: RankRecyclerViewAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.a$a */
    public class C2180a extends ViewHolder {
        /* renamed from: a */
        public CircleImageView f10218a;
        /* renamed from: b */
        public TextView f10219b;
        /* renamed from: c */
        public TextView f10220c;
        /* renamed from: d */
        public TextView f10221d;
        /* renamed from: e */
        public TextView f10222e;
        /* renamed from: f */
        public TextView f10223f;
        /* renamed from: g */
        public TextView f10224g;
        /* renamed from: h */
        public TextView f10225h;
        /* renamed from: i */
        public TextView f10226i;
        /* renamed from: j */
        final /* synthetic */ C2183a f10227j;

        public C2180a(C2183a c2183a, View view) {
            this.f10227j = c2183a;
            super(view);
            this.f10218a = (CircleImageView) view.findViewById(C1373R.id.avater);
            this.f10219b = (TextView) view.findViewById(C1373R.id.nickname);
            this.f10220c = (TextView) view.findViewById(C1373R.id.cityname);
            this.f10221d = (TextView) view.findViewById(C1373R.id.clubname);
            this.f10222e = (TextView) view.findViewById(C1373R.id.distance);
            this.f10223f = (TextView) view.findViewById(C1373R.id.distanceunit);
            this.f10224g = (TextView) view.findViewById(C1373R.id.rank);
            this.f10225h = (TextView) view.findViewById(C1373R.id.rankunit);
            this.f10226i = (TextView) view.findViewById(C1373R.id.rank_desc);
        }
    }

    /* compiled from: RankRecyclerViewAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.a$b */
    class C2181b extends ViewHolder {
        /* renamed from: a */
        public TextView f10228a;
        /* renamed from: b */
        public TextView f10229b;
        /* renamed from: c */
        final /* synthetic */ C2183a f10230c;
        /* renamed from: d */
        private TextView f10231d;
        /* renamed from: e */
        private CircleImageView f10232e;
        /* renamed from: f */
        private TextView f10233f;
        /* renamed from: g */
        private TextView f10234g;
        /* renamed from: h */
        private TextView f10235h;
        /* renamed from: i */
        private View f10236i;
        /* renamed from: j */
        private View f10237j;
        /* renamed from: k */
        private RelativeLayout f10238k;
        /* renamed from: l */
        private View f10239l;

        public C2181b(C2183a c2183a, View view) {
            this.f10230c = c2183a;
            super(view);
            this.f10239l = view;
            this.f10228a = (TextView) view.findViewById(C1373R.id.nickname);
            this.f10229b = (TextView) view.findViewById(C1373R.id.cityName);
            this.f10231d = (TextView) view.findViewById(C1373R.id.clubname);
            this.f10232e = (CircleImageView) view.findViewById(C1373R.id.ranking_fragment_list_item_avatar);
            this.f10233f = (TextView) view.findViewById(C1373R.id.distanceValue);
            this.f10234g = (TextView) view.findViewById(C1373R.id.distanceUnit);
            this.f10235h = (TextView) view.findViewById(C1373R.id.ranktv);
            this.f10236i = view.findViewById(C1373R.id.shortSplitLine);
            this.f10237j = view.findViewById(C1373R.id.longSplitLine);
            this.f10238k = (RelativeLayout) view.findViewById(C1373R.id.rankingNum);
        }
    }

    public C2183a(Context context, C2534b c2534b) {
        this.f10243a = context;
        this.f10244b = c2534b;
    }

    /* renamed from: b */
    public ViewHolder mo3419b() {
        return new C2180a(this, LayoutInflater.from(this.f10243a).inflate(C1373R.layout.rankheadview, null, false));
    }

    /* renamed from: a */
    public ViewHolder mo3371a() {
        return new C2181b(this, LayoutInflater.from(this.f10243a).inflate(C1373R.layout.item_rank, null, false));
    }

    /* renamed from: a */
    public void mo3372a(ViewHolder viewHolder, Object obj, final int i, boolean z) {
        RankDTO rankDTO;
        CharSequence city;
        double rankDistance;
        if (viewHolder instanceof C2181b) {
            final C2181b c2181b = (C2181b) viewHolder;
            rankDTO = (RankDTO) obj;
            c2181b.f10228a.setText(C2570p.m12883a(rankDTO.getNickname(), rankDTO.getRemarks()));
            city = rankDTO.getCity();
            if (TextUtils.isEmpty(city) || city.equals("null")) {
                c2181b.f10229b.setVisibility(8);
            } else {
                c2181b.f10229b.setVisibility(0);
                c2181b.f10229b.setText(city);
            }
            city = rankDTO.getClubName();
            if (TextUtils.isEmpty(city) || city.equals("null")) {
                c2181b.f10231d.setText("");
            } else {
                c2181b.f10231d.setText(city);
            }
            rankDistance = rankDTO.getRankDistance() / 1000.0d;
            if (!C1849a.m9645b(this.f10243a)) {
                c2181b.f10234g.setText(this.f10243a.getResources().getString(C1373R.string.str_mileage_unit_mile));
                rankDistance = C1849a.m9638a(rankDistance);
            }
            c2181b.f10233f.setText(String.format("%.1f", new Object[]{Double.valueOf(rankDistance)}));
            c2181b.f10235h.setText((i + 1) + "");
            if (z) {
                c2181b.f10237j.setVisibility(0);
                c2181b.f10236i.setVisibility(4);
            } else {
                c2181b.f10237j.setVisibility(4);
                c2181b.f10236i.setVisibility(0);
            }
            switch (i + 1) {
                case 1:
                    c2181b.f10238k.setBackgroundResource(C1373R.drawable.rankno1);
                    break;
                case 2:
                    c2181b.f10238k.setBackgroundResource(C1373R.drawable.rankno2);
                    break;
                case 3:
                    c2181b.f10238k.setBackgroundResource(C1373R.drawable.rankno3);
                    break;
                default:
                    c2181b.f10238k.setBackgroundResource(C1373R.drawable.transparent);
                    break;
            }
            if (TextUtils.isEmpty(rankDTO.getAvatarUrl())) {
                Picasso.with(this.f10243a).load(C1373R.drawable.ic_avatar).fit().centerCrop().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).into(c2181b.f10232e);
            } else {
                Picasso.with(this.f10243a).load(rankDTO.getAvatarUrl()).fit().centerCrop().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).into(c2181b.f10232e);
            }
            c2181b.f10239l.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C2183a f10217c;

                public void onClick(View view) {
                    this.f10217c.f10244b.mo3520a(c2181b, i);
                }
            });
        } else if (obj != null) {
            rankDTO = (RankDTO) obj;
            C2180a c2180a = (C2180a) viewHolder;
            rankDistance = rankDTO.getMilestone() / 1000.0d;
            if (!C1849a.m9645b(this.f10243a)) {
                c2180a.f10223f.setText(this.f10243a.getResources().getString(C1373R.string.str_mileage_unit_mile));
                rankDistance = C1849a.m9638a(rankDistance);
            }
            c2180a.f10222e.setText(String.format("%.1f", new Object[]{Double.valueOf(rankDistance)}));
            c2180a.f10224g.setText(rankDTO.getOrdinal() + "");
            int ordinal = rankDTO.getOrdinal();
            if (C1849a.m9647c() || (ordinal >= 10 && ordinal <= 20)) {
                c2180a.f10225h.setText("th");
            } else if (ordinal % 10 == 1) {
                c2180a.f10225h.setText("st");
            } else if (ordinal % 10 == 2) {
                c2180a.f10225h.setText("nd");
            } else if (ordinal % 10 == 3) {
                c2180a.f10225h.setText("rd");
            } else {
                c2180a.f10225h.setText("th");
            }
            if (rankDistance <= 0.0d) {
                c2180a.f10224g.setVisibility(4);
                c2180a.f10225h.setVisibility(4);
                c2180a.f10226i.setVisibility(0);
            } else {
                c2180a.f10224g.setVisibility(0);
                c2180a.f10225h.setVisibility(0);
                c2180a.f10226i.setVisibility(4);
            }
            c2180a.f10219b.setText(rankDTO.getNickname());
            c2180a.f10221d.setText(rankDTO.getClubName());
            city = rankDTO.getCity();
            if (!(TextUtils.isEmpty(city) || city.equals("null"))) {
                c2180a.f10220c.setText(city);
            }
            if (TextUtils.isEmpty(rankDTO.getAvatarUrl())) {
                Picasso.with(this.f10243a).load(C1373R.drawable.ic_avatar).fit().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).centerCrop().into(c2180a.f10218a);
            } else {
                Picasso.with(this.f10243a).load(rankDTO.getAvatarUrl()).fit().placeholder(C1373R.drawable.ic_avatar).error(C1373R.drawable.ic_avatar).centerCrop().into(c2180a.f10218a);
            }
        }
    }
}
