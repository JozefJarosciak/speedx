package com.beastbikes.android.modules.cycling.stage.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* compiled from: StageListAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.stage.ui.a */
public class C2270a extends Adapter<C2269b> {
    /* renamed from: a */
    private C2268a f10787a;
    /* renamed from: b */
    private Context f10788b;
    /* renamed from: c */
    private ArrayList<StageDTO> f10789c = new ArrayList();
    /* renamed from: d */
    private DecimalFormat f10790d = new DecimalFormat("#.#");

    /* compiled from: StageListAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.a$a */
    interface C2268a {
        /* renamed from: a */
        void m11620a(StageDTO stageDTO);
    }

    /* compiled from: StageListAdapter */
    /* renamed from: com.beastbikes.android.modules.cycling.stage.ui.a$b */
    class C2269b extends ViewHolder {
        /* renamed from: a */
        TextView f10782a;
        /* renamed from: b */
        TextView f10783b;
        /* renamed from: c */
        TextView f10784c;
        /* renamed from: d */
        View f10785d;
        /* renamed from: e */
        final /* synthetic */ C2270a f10786e;

        public C2269b(C2270a c2270a, View view) {
            this.f10786e = c2270a;
            super(view);
            this.f10782a = (TextView) view.findViewById(C1373R.id.stage_collection_item_name_tv);
            this.f10784c = (TextView) view.findViewById(C1373R.id.stage_collection_item_count_tv);
            this.f10783b = (TextView) view.findViewById(C1373R.id.stage_collection_item_data_tv);
            this.f10785d = view.findViewById(C1373R.id.view_divider);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m11624a((C2269b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m11622a(viewGroup, i);
    }

    public C2270a(Context context) {
        this.f10788b = context;
    }

    /* renamed from: a */
    public C2269b m11622a(ViewGroup viewGroup, int i) {
        return new C2269b(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.fragment_stage_collection_list_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.f10789c.size();
    }

    /* renamed from: a */
    public void m11624a(C2269b c2269b, int i) {
        final StageDTO stageDTO = (StageDTO) this.f10789c.get(i);
        if (stageDTO != null) {
            CharSequence charSequence;
            c2269b.f10782a.setText(stageDTO.getStageName());
            c2269b.f10784c.setText(String.format(this.f10788b.getString(C1373R.string.str_segment_people_join), new Object[]{Integer.valueOf(stageDTO.getParticipateNum())}));
            if (C1849a.m9645b(this.f10788b)) {
                charSequence = this.f10790d.format(stageDTO.getDistance() / 1000.0d) + "km - " + this.f10790d.format(stageDTO.getElevationDiff()) + "m - " + this.f10790d.format(stageDTO.getSlope()) + "%";
            } else {
                charSequence = this.f10790d.format(C1849a.m9638a(stageDTO.getDistance() / 1000.0d)) + "mi - " + this.f10790d.format(C1849a.m9646c(stageDTO.getElevationDiff())) + "ft - " + this.f10790d.format(stageDTO.getSlope()) + "%";
            }
            c2269b.f10783b.setText(charSequence);
            c2269b.itemView.setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ C2270a f10781b;

                public void onClick(View view) {
                    if (this.f10781b.f10787a != null) {
                        this.f10781b.f10787a.m11620a(stageDTO);
                    }
                }
            });
            if (i == this.f10789c.size() - 1) {
                c2269b.f10785d.setVisibility(8);
            } else {
                c2269b.f10785d.setVisibility(0);
            }
        }
    }

    /* renamed from: a */
    public void m11625a(ArrayList<StageDTO> arrayList) {
        this.f10789c.clear();
        this.f10789c.addAll(arrayList);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m11623a(C2268a c2268a) {
        this.f10787a = c2268a;
    }
}
