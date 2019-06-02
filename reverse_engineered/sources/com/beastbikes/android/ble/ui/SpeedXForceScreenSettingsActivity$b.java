package com.beastbikes.android.ble.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dto.SpeedXForceScreenPageDTO;
import com.beastbikes.android.ble.ui.p099b.C1746b;
import com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingPreviewLayout;
import com.beastbikes.android.widget.p081b.C1701d;
import java.util.ArrayList;
import java.util.Collections;

class SpeedXForceScreenSettingsActivity$b extends Adapter<C1702a> implements C1701d {
    /* renamed from: a */
    final /* synthetic */ SpeedXForceScreenSettingsActivity f7760a;
    /* renamed from: b */
    private ArrayList<SpeedXForceScreenPageDTO> f7761b;
    /* renamed from: c */
    private C1746b f7762c;

    /* renamed from: com.beastbikes.android.ble.ui.SpeedXForceScreenSettingsActivity$b$a */
    class C1702a extends ViewHolder implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ SpeedXForceScreenSettingsActivity$b f7757a;
        /* renamed from: b */
        private CyclingPreviewLayout f7758b;
        /* renamed from: c */
        private RelativeLayout f7759c;

        C1702a(SpeedXForceScreenSettingsActivity$b speedXForceScreenSettingsActivity$b, View view) {
            this.f7757a = speedXForceScreenSettingsActivity$b;
            super(view);
            this.f7758b = (CyclingPreviewLayout) view.findViewById(C1373R.id.cycling_preview_layout_speedx_force_screen_thumb);
            this.f7758b.m10489a();
            this.f7759c = (RelativeLayout) view.findViewById(C1373R.id.rela_speedx_force_screen_checked);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (this.f7757a.f7762c != null) {
                int adapterPosition = getAdapterPosition();
                if (((SpeedXForceScreenPageDTO) this.f7757a.f7761b.get(adapterPosition)).getPagePositions() == null) {
                    this.f7757a.f7762c.m9311a(adapterPosition);
                } else {
                    this.f7757a.f7762c.m9312b(adapterPosition);
                }
            }
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9231a((C1702a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9229a(viewGroup, i);
    }

    SpeedXForceScreenSettingsActivity$b(SpeedXForceScreenSettingsActivity speedXForceScreenSettingsActivity, ArrayList<SpeedXForceScreenPageDTO> arrayList) {
        this.f7760a = speedXForceScreenSettingsActivity;
        this.f7761b = arrayList;
    }

    /* renamed from: a */
    public C1702a m9229a(ViewGroup viewGroup, int i) {
        return new C1702a(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_speedx_force_screen_thumb, viewGroup, false));
    }

    /* renamed from: a */
    public void m9231a(C1702a c1702a, int i) {
        SpeedXForceScreenPageDTO speedXForceScreenPageDTO = (SpeedXForceScreenPageDTO) this.f7761b.get(i);
        if (speedXForceScreenPageDTO != null) {
            if (speedXForceScreenPageDTO.isChecked()) {
                SpeedXForceScreenSettingsActivity.a(this.f7760a, i);
            }
            c1702a.f7759c.setVisibility(speedXForceScreenPageDTO.isChecked() ? 0 : 8);
            if (speedXForceScreenPageDTO.getPagePositions() != null) {
                c1702a.f7758b.m10490a(speedXForceScreenPageDTO.getPagePositions());
                c1702a.f7758b.setBackgroundColor(-13421773);
                return;
            }
            c1702a.f7758b.m10490a(null);
            c1702a.f7758b.setBackgroundResource(C1373R.drawable.ic_speedx_force_screen_add);
        }
    }

    public int getItemCount() {
        return this.f7761b.size();
    }

    /* renamed from: a */
    public boolean mo3217a(int i, int i2) {
        if (((SpeedXForceScreenPageDTO) this.f7761b.get(i)).getPagePositions() == null) {
            return false;
        }
        if (((SpeedXForceScreenPageDTO) this.f7761b.get(i2)).getPagePositions() == null) {
            return false;
        }
        Collections.swap(this.f7761b, i, i2);
        notifyItemMoved(i, i2);
        notifyItemChanged(i);
        notifyItemChanged(i2);
        return true;
    }

    /* renamed from: a */
    public void mo3216a(int i) {
    }

    /* renamed from: a */
    void m9232a(C1746b c1746b) {
        this.f7762c = c1746b;
    }
}
