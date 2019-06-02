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
import com.alipay.sdk.cons.C0844a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.stage.dto.StageRankDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/* compiled from: StageRankAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.ui.c */
public class C2280c extends Adapter<C2278a> {
    /* renamed from: a */
    private Context f10822a;
    /* renamed from: b */
    private ArrayList<StageRankDTO> f10823b = new ArrayList();
    /* renamed from: c */
    private C2279b f10824c;
    /* renamed from: d */
    private boolean f10825d;

    /* compiled from: StageRankAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.c$1 */
    class C22761 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2280c f10811a;

        C22761(C2280c c2280c) {
            this.f10811a = c2280c;
        }

        public void onClick(View view) {
            if (this.f10811a.f10824c != null) {
                this.f10811a.f10824c.m11630a(null);
            }
        }
    }

    /* compiled from: StageRankAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.c$a */
    static class C2278a extends ViewHolder {
        /* renamed from: a */
        TextView f10814a;
        /* renamed from: b */
        CircleImageView f10815b;
        /* renamed from: c */
        ImageView f10816c;
        /* renamed from: d */
        TextView f10817d;
        /* renamed from: e */
        TextView f10818e;
        /* renamed from: f */
        TextView f10819f;
        /* renamed from: g */
        TextView f10820g;
        /* renamed from: h */
        TextView f10821h;

        public C2278a(View view, int i) {
            super(view);
            if (i == 1) {
                this.f10815b = (CircleImageView) view.findViewById(C1373R.id.img_segment_rank_first_avatar);
                this.f10816c = (ImageView) view.findViewById(C1373R.id.img_segment_rank_first);
                this.f10817d = (TextView) view.findViewById(C1373R.id.tv_profile_score_rank_num);
                this.f10818e = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_user_name);
                this.f10819f = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_cycling_date);
                this.f10820g = (TextView) view.findViewById(C1373R.id.tv_segment_rank_first_cycling_time);
            } else if (i == 3) {
                this.f10821h = (TextView) view.findViewById(C1373R.id.tv_stage_rank_more);
            } else {
                this.f10814a = (TextView) view.findViewById(C1373R.id.tv_segment_rank_num);
                this.f10815b = (CircleImageView) view.findViewById(C1373R.id.img_segment_rank_avatar);
                this.f10818e = (TextView) view.findViewById(C1373R.id.tv_segment_rank_user_name);
                this.f10819f = (TextView) view.findViewById(C1373R.id.tv_segment_rank_date);
                this.f10820g = (TextView) view.findViewById(C1373R.id.tv_segment_rank_time);
            }
        }
    }

    /* compiled from: StageRankAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.c$b */
    public interface C2279b {
        /* renamed from: a */
        void m11630a(StageRankDTO stageRankDTO);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m11634a((C2278a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m11632a(viewGroup, i);
    }

    public C2280c(Context context, boolean z) {
        this.f10822a = context;
        this.f10825d = z;
    }

    /* renamed from: a */
    public C2278a m11632a(ViewGroup viewGroup, int i) {
        View inflate;
        if (i == 1) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_segment_rank_first, viewGroup, false);
        } else if (i == 3) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.layout_stage_rank_bottom_more, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_segment_rank, viewGroup, false);
        }
        return new C2278a(inflate, i);
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        if (this.f10825d && i == this.f10823b.size()) {
            return 3;
        }
        return 2;
    }

    /* renamed from: a */
    public void m11634a(C2278a c2278a, int i) {
        if (getItemViewType(i) == 3) {
            c2278a.f10821h.setOnClickListener(new C22761(this));
            return;
        }
        final StageRankDTO stageRankDTO = (StageRankDTO) this.f10823b.get(i);
        if (i == 0) {
            c2278a.f10817d.setText(C0844a.f2048d);
            if (!TextUtils.isEmpty(stageRankDTO.getPrize())) {
                Picasso.with(this.f10822a).load(stageRankDTO.getPrize()).fit().error(C1373R.drawable.ic_score_rank_first).placeholder(C1373R.drawable.ic_score_rank_first).into(c2278a.f10816c);
            }
        } else if (i == 1) {
            c2278a.f10814a.setText("");
            c2278a.f10814a.setBackgroundResource(C1373R.drawable.ic_rank_second);
        } else if (i == 2) {
            c2278a.f10814a.setText("");
            c2278a.f10814a.setBackgroundResource(C1373R.drawable.ic_rank_third);
        } else {
            c2278a.f10814a.setText(String.valueOf(i + 1));
            c2278a.f10814a.setBackgroundResource(0);
        }
        if (!TextUtils.isEmpty(stageRankDTO.getAvatar())) {
            Picasso.with(this.f10822a).load(stageRankDTO.getAvatar()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).into(c2278a.f10815b);
        }
        c2278a.f10818e.setText(stageRankDTO.getNickname());
        c2278a.f10819f.setText(C2555d.m12798a(stageRankDTO.getTime()));
        c2278a.f10820g.setText(C2555d.m12802b(stageRankDTO.getDuration()));
        c2278a.itemView.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2280c f10813b;

            public void onClick(View view) {
                if (this.f10813b.f10824c != null) {
                    this.f10813b.f10824c.m11630a(stageRankDTO);
                }
            }
        });
    }

    public int getItemCount() {
        if (this.f10823b.size() <= 0 || !this.f10825d) {
            return this.f10823b.size();
        }
        return this.f10823b.size() + 1;
    }

    /* renamed from: a */
    public void m11636a(ArrayList<StageRankDTO> arrayList) {
        this.f10823b.clear();
        if (arrayList.size() > 10) {
            for (int i = 0; i < 10; i++) {
                this.f10823b.add(arrayList.get(i));
            }
        } else {
            this.f10823b.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m11633a() {
        this.f10823b.clear();
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m11637a(ArrayList<StageRankDTO> arrayList, boolean z) {
        if (!z) {
            this.f10823b.clear();
        }
        this.f10823b.addAll(arrayList);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m11635a(C2279b c2279b) {
        this.f10824c = c2279b;
    }
}
