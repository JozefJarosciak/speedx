package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.stage.dto.C2264c;
import com.beastbikes.android.utils.C2555d;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* compiled from: StageScoreListAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.ui.d */
public class C2284d extends Adapter<C2283b> {
    /* renamed from: a */
    private Context f10833a;
    /* renamed from: b */
    private ArrayList<C2264c> f10834b = new ArrayList();
    /* renamed from: c */
    private DecimalFormat f10835c = new DecimalFormat("#.#");
    /* renamed from: d */
    private C2282a f10836d;

    /* compiled from: StageScoreListAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.d$a */
    interface C2282a {
        /* renamed from: a */
        void m11638a(C2264c c2264c);
    }

    /* compiled from: StageScoreListAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.d$b */
    class C2283b extends ViewHolder {
        /* renamed from: a */
        TextView f10828a;
        /* renamed from: b */
        TextView f10829b;
        /* renamed from: c */
        ImageView f10830c;
        /* renamed from: d */
        View f10831d;
        /* renamed from: e */
        final /* synthetic */ C2284d f10832e;

        public C2283b(C2284d c2284d, View view) {
            this.f10832e = c2284d;
            super(view);
            this.f10828a = (TextView) view.findViewById(C1373R.id.tv_stage_score_item_name);
            this.f10829b = (TextView) view.findViewById(C1373R.id.tv_stage_score_item_content);
            this.f10830c = (ImageView) view.findViewById(C1373R.id.img_stage_score_item_rank);
            this.f10831d = view.findViewById(C1373R.id.view_divider);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m11642a((C2283b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m11640a(viewGroup, i);
    }

    public C2284d(Context context) {
        this.f10833a = context;
    }

    /* renamed from: a */
    public C2283b m11640a(ViewGroup viewGroup, int i) {
        return new C2283b(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_stage_score_item, viewGroup, false));
    }

    /* renamed from: a */
    public void m11642a(C2283b c2283b, int i) {
        final C2264c c2264c = (C2264c) this.f10834b.get(i);
        if (c2264c != null) {
            c2283b.f10828a.setText(c2264c.m11607d());
            StringBuilder stringBuilder = new StringBuilder();
            if (C1849a.m9645b(this.f10833a)) {
                stringBuilder.append(this.f10835c.format(c2264c.m11605b() / 1000.0d));
                stringBuilder.append("km - ");
                stringBuilder.append(C2555d.m12802b((long) c2264c.m11608e()));
                stringBuilder.append(" - ");
                stringBuilder.append(this.f10835c.format(c2264c.m11606c())).append("'mph");
            } else {
                stringBuilder.append(this.f10835c.format(C1849a.m9638a(c2264c.m11605b() / 1000.0d)));
                stringBuilder.append("mi - ");
                stringBuilder.append(C2555d.m12802b((long) c2264c.m11608e()));
                stringBuilder.append(" - ");
                stringBuilder.append(this.f10835c.format(c2264c.m11606c())).append("'mph");
            }
            c2283b.f10829b.setText(stringBuilder);
            if (c2264c.m11604a() == 3) {
                c2283b.f10830c.setImageResource(C1373R.drawable.ic_section_achievement_type_1);
            } else if (c2264c.m11604a() == 2) {
                c2283b.f10830c.setImageResource(C1373R.drawable.ic_section_achievement_type_2);
            } else if (c2264c.m11604a() == 1) {
                c2283b.f10830c.setImageResource(C1373R.drawable.ic_section_achievement_type_default);
            }
            if (i == this.f10834b.size() - 1) {
                c2283b.f10831d.setVisibility(8);
            } else {
                c2283b.f10831d.setVisibility(0);
            }
            c2283b.itemView.setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ C2284d f10827b;

                public void onClick(View view) {
                    if (this.f10827b.f10836d != null) {
                        this.f10827b.f10836d.m11638a(c2264c);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.f10834b.size();
    }

    /* renamed from: a */
    public void m11643a(ArrayList<C2264c> arrayList) {
        this.f10834b.clear();
        this.f10834b.addAll(arrayList);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m11641a(C2282a c2282a) {
        this.f10836d = c2282a;
    }
}
