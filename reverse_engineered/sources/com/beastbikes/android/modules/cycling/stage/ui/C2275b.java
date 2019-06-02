package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.stage.dto.C2262a;
import com.beastbikes.android.utils.C2555d;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* compiled from: StageProfileScoreAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.ui.b */
public class C2275b extends Adapter<ViewHolder> {
    /* renamed from: a */
    private C2273b f10808a;
    /* renamed from: b */
    private Context f10809b;
    /* renamed from: c */
    private ArrayList<C2262a> f10810c = new ArrayList();

    /* compiled from: StageProfileScoreAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.b$a */
    class C2272a extends ViewHolder {
        /* renamed from: a */
        TextView f10793a;
        /* renamed from: b */
        ImageView f10794b;
        /* renamed from: c */
        TextView f10795c;
        /* renamed from: d */
        TextView f10796d;
        /* renamed from: e */
        TextView f10797e;
        /* renamed from: f */
        ImageView f10798f;
        /* renamed from: g */
        TextView f10799g;
        /* renamed from: h */
        View f10800h;
        /* renamed from: i */
        TextView f10801i;
        /* renamed from: j */
        TextView f10802j;
        /* renamed from: k */
        final /* synthetic */ C2275b f10803k;

        public C2272a(C2275b c2275b, View view) {
            this.f10803k = c2275b;
            super(view);
            this.f10793a = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_user_name);
            this.f10794b = (ImageView) view.findViewById(C1373R.id.img_segment_rank_first_avatar);
            this.f10795c = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_cycling_time);
            this.f10796d = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_cycling_date);
            this.f10797e = (TextView) view.findViewById(C1373R.id.tv_profile_score_rank_num);
            this.f10798f = (ImageView) view.findViewById(C1373R.id.img_segment_rank_first);
            this.f10799g = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_desc);
            this.f10800h = view.findViewById(C1373R.id.view_temp);
            this.f10801i = (TextView) view.findViewById(C1373R.id.tv_all_achievement);
            this.f10802j = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_user_name_sub);
        }
    }

    /* compiled from: StageProfileScoreAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.b$b */
    interface C2273b {
        /* renamed from: a */
        void m11626a(C2262a c2262a);
    }

    /* compiled from: StageProfileScoreAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.b$c */
    class C2274c extends ViewHolder {
        /* renamed from: a */
        TextView f10804a;
        /* renamed from: b */
        ImageView f10805b;
        /* renamed from: c */
        TextView f10806c;
        /* renamed from: d */
        final /* synthetic */ C2275b f10807d;

        public C2274c(C2275b c2275b, View view) {
            this.f10807d = c2275b;
            super(view);
            this.f10804a = (TextView) view.findViewById(C1373R.id.tv_profile_score_item_date);
            this.f10805b = (ImageView) view.findViewById(C1373R.id.img_profile_score_item_beast);
            this.f10806c = (TextView) view.findViewById(C1373R.id.tv_profile_score_item_time);
        }
    }

    public C2275b(Context context) {
        this.f10809b = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new C2272a(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_segment_rank_first, viewGroup, false));
        }
        return new C2274c(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_profile_score, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final C2262a c2262a = (C2262a) this.f10810c.get(i);
        if (getItemViewType(i) == 1) {
            C2272a c2272a = (C2272a) viewHolder;
            c2272a.f10793a.setText(c2262a.m11591a());
            c2272a.f10797e.setText(String.valueOf(c2262a.m11598h()));
            if (!TextUtils.isEmpty(c2262a.m11592b())) {
                Picasso.with(this.f10809b).load(c2262a.m11592b()).error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).fit().into(c2272a.f10794b);
            }
            c2272a.f10802j.setText("");
            c2272a.f10799g.setText(String.format(this.f10809b.getResources().getString(C1373R.string.str_segment_rank_percent), new Object[]{Integer.valueOf(c2262a.m11597g())}));
            if (c2262a.m11598h() == 1) {
                if (!TextUtils.isEmpty(c2262a.m11595e())) {
                    Picasso.with(this.f10809b).load(c2262a.m11595e()).error(C1373R.drawable.ic_score_rank_first).placeholder(C1373R.drawable.ic_score_rank_first).fit().into(c2272a.f10798f);
                }
                c2272a.f10799g.setText(C1373R.string.str_segment_champion);
                c2272a.f10802j.setText(C1373R.string.str_segment_jersey_leader);
            } else if (c2262a.m11598h() == 2) {
                c2272a.f10798f.setImageResource(C1373R.drawable.ic_score_rank_second);
            } else if (c2262a.m11598h() == 3) {
                c2272a.f10798f.setImageResource(C1373R.drawable.ic_score_rank_third);
            } else {
                c2272a.f10798f.setImageResource(C1373R.drawable.ic_score_rank_under_forth);
            }
            if (c2262a.m11598h() > 1 && c2262a.m11598h() < 11) {
                c2272a.f10802j.setText(C1373R.string.str_segments_static_vc_cell_achievement_rank_second_title);
            }
            c2272a.f10796d.setText(C2555d.m12798a(c2262a.m11593c()));
            c2272a.f10795c.setText(C2555d.m12802b(c2262a.m11594d()));
            c2272a.f10800h.setVisibility(8);
            c2272a.f10801i.setVisibility(0);
        } else {
            C2274c c2274c = (C2274c) viewHolder;
            c2274c.f10804a.setText(C2555d.m12798a(c2262a.m11593c()));
            c2274c.f10806c.setText(C2555d.m12802b(c2262a.m11594d()));
            if (c2262a.m11596f()) {
                c2274c.f10805b.setVisibility(0);
            } else {
                c2274c.f10805b.setVisibility(8);
            }
        }
        viewHolder.itemView.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2275b f10792b;

            public void onClick(View view) {
                if (this.f10792b.f10808a != null) {
                    this.f10792b.f10808a.m11626a(c2262a);
                }
            }
        });
    }

    public int getItemCount() {
        return this.f10810c.size();
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        return 2;
    }

    /* renamed from: a */
    public void m11629a(ArrayList<C2262a> arrayList) {
        this.f10810c.clear();
        this.f10810c.addAll(arrayList);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m11628a(C2273b c2273b) {
        this.f10808a = c2273b;
    }
}
