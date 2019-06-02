package com.beastbikes.android.modules.cycling.stage.ui;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.text.DecimalFormat;

class StagesCollectionFrag$a extends ViewHolder<StageDTO> {
    /* renamed from: a */
    final /* synthetic */ StagesCollectionFrag f10772a;
    /* renamed from: b */
    private TextView f10773b;
    /* renamed from: c */
    private TextView f10774c;
    /* renamed from: d */
    private TextView f10775d;
    /* renamed from: e */
    private View f10776e;
    /* renamed from: f */
    private DecimalFormat f10777f = new DecimalFormat("#.#");

    public /* synthetic */ void bind(Object obj) {
        m11618a((StageDTO) obj);
    }

    public /* synthetic */ void bind(Object obj, boolean z) {
        m11619a((StageDTO) obj, z);
    }

    StagesCollectionFrag$a(StagesCollectionFrag stagesCollectionFrag, View view) {
        this.f10772a = stagesCollectionFrag;
        super(view);
        this.f10773b = (TextView) view.findViewById(C1373R.id.stage_collection_item_name_tv);
        this.f10774c = (TextView) view.findViewById(C1373R.id.stage_collection_item_data_tv);
        this.f10775d = (TextView) view.findViewById(C1373R.id.stage_collection_item_count_tv);
        this.f10776e = view.findViewById(C1373R.id.view_divider);
    }

    /* renamed from: a */
    public void m11618a(StageDTO stageDTO) {
        if (stageDTO != null) {
            CharSequence charSequence;
            this.f10773b.setText(stageDTO.getStageName());
            this.f10775d.setText(String.format(this.f10772a.getString(C1373R.string.str_segment_people_join), new Object[]{Integer.valueOf(stageDTO.getParticipateNum())}));
            if (C1849a.m9645b(this.f10772a.getActivity())) {
                charSequence = this.f10777f.format(stageDTO.getDistance() / 1000.0d) + "km - " + this.f10777f.format(stageDTO.getElevationDiff()) + "m - " + stageDTO.getSlope() + "%";
            } else {
                charSequence = this.f10777f.format(C1849a.m9638a(stageDTO.getDistance() / 1000.0d)) + "mi - " + this.f10777f.format(C1849a.m9646c(stageDTO.getElevationDiff())) + "ft - " + stageDTO.getSlope() + "%";
            }
            this.f10774c.setText(charSequence);
        }
    }

    /* renamed from: a */
    public void m11619a(StageDTO stageDTO, boolean z) {
        if (stageDTO != null) {
            CharSequence charSequence;
            this.f10773b.setText(stageDTO.getStageName());
            this.f10775d.setText(String.format(this.f10772a.getString(C1373R.string.str_segment_people_join), new Object[]{Integer.valueOf(stageDTO.getParticipateNum())}));
            if (C1849a.m9645b(this.f10772a.getActivity())) {
                charSequence = this.f10777f.format(stageDTO.getDistance() / 1000.0d) + "km - " + this.f10777f.format(stageDTO.getElevationDiff()) + "m - " + stageDTO.getSlope() + "%";
            } else {
                charSequence = this.f10777f.format(C1849a.m9638a(stageDTO.getDistance() / 1000.0d)) + "mi - " + this.f10777f.format(C1849a.m9646c(stageDTO.getElevationDiff())) + "ft - " + stageDTO.getSlope() + "%";
            }
            this.f10774c.setText(charSequence);
            if (z) {
                this.f10776e.setVisibility(8);
            } else {
                this.f10776e.setVisibility(0);
            }
        }
    }
}
