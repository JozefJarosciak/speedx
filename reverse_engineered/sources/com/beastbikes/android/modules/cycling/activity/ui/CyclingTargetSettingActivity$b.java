package com.beastbikes.android.modules.cycling.activity.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.dto.GoalConfigDTO;
import com.beastbikes.android.widget.p081b.C2534b;
import java.util.List;

final class CyclingTargetSettingActivity$b extends Adapter<CyclingTargetSettingActivity$a> {
    /* renamed from: a */
    final /* synthetic */ CyclingTargetSettingActivity f8707a;
    /* renamed from: b */
    private final List<GoalConfigDTO> f8708b;
    /* renamed from: c */
    private final C2534b f8709c;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9968a((CyclingTargetSettingActivity$a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9967a(viewGroup, i);
    }

    public CyclingTargetSettingActivity$b(CyclingTargetSettingActivity cyclingTargetSettingActivity, C2534b c2534b, List<GoalConfigDTO> list) {
        this.f8707a = cyclingTargetSettingActivity;
        this.f8708b = list;
        this.f8709c = c2534b;
    }

    /* renamed from: a */
    public CyclingTargetSettingActivity$a m9967a(ViewGroup viewGroup, int i) {
        return new CyclingTargetSettingActivity$a(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.cycling_target_config_item, viewGroup, false));
    }

    /* renamed from: a */
    public void m9968a(final CyclingTargetSettingActivity$a cyclingTargetSettingActivity$a, final int i) {
        GoalConfigDTO goalConfigDTO = (GoalConfigDTO) this.f8708b.get(i);
        if (goalConfigDTO != null) {
            cyclingTargetSettingActivity$a.f8696a.setText(goalConfigDTO.getTitle());
            if (TextUtils.isEmpty(goalConfigDTO.getSubTitle())) {
                cyclingTargetSettingActivity$a.f8697b.setVisibility(8);
            } else {
                cyclingTargetSettingActivity$a.f8697b.setVisibility(0);
                cyclingTargetSettingActivity$a.f8697b.setText(goalConfigDTO.getSubTitle());
            }
            double distance = goalConfigDTO.getDistance() / 1000.0d;
            if (C1849a.m9645b(this.f8707a)) {
                cyclingTargetSettingActivity$a.f8699d.setText(String.format("%.0f", new Object[]{Double.valueOf(distance)}) + CyclingTargetSettingActivity.e(this.f8707a));
            } else {
                cyclingTargetSettingActivity$a.f8699d.setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.m9638a(distance))}) + CyclingTargetSettingActivity.e(this.f8707a));
            }
            cyclingTargetSettingActivity$a.f8700e.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ CyclingTargetSettingActivity$b f8703c;

                public void onClick(View view) {
                    this.f8703c.f8709c.mo3520a(cyclingTargetSettingActivity$a, i);
                }
            });
            cyclingTargetSettingActivity$a.f8700e.setOnLongClickListener(new OnLongClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ CyclingTargetSettingActivity$b f8706c;

                public boolean onLongClick(View view) {
                    this.f8706c.f8709c.mo3521b(cyclingTargetSettingActivity$a, i);
                    return true;
                }
            });
            if (goalConfigDTO.isChecked()) {
                cyclingTargetSettingActivity$a.f8698c.setVisibility(0);
            } else {
                cyclingTargetSettingActivity$a.f8698c.setVisibility(4);
            }
        }
    }

    public int getItemCount() {
        return this.f8708b.size();
    }
}
