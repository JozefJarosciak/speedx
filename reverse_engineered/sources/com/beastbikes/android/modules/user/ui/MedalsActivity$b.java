package com.beastbikes.android.modules.user.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import com.beastbikes.android.widget.p081b.C2534b;
import com.squareup.picasso.Picasso;
import java.util.List;

final class MedalsActivity$b extends Adapter<MedalsActivity$a> {
    /* renamed from: a */
    final /* synthetic */ MedalsActivity f11737a;
    /* renamed from: b */
    private final int f11738b = -1;
    /* renamed from: c */
    private final List<MedalDTO> f11739c;
    /* renamed from: d */
    private final C2534b f11740d;
    /* renamed from: e */
    private boolean f11741e;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m12505a((MedalsActivity$a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m12504a(viewGroup, i);
    }

    public MedalsActivity$b(MedalsActivity medalsActivity, C2534b c2534b, List<MedalDTO> list, boolean z) {
        this.f11737a = medalsActivity;
        this.f11739c = list;
        this.f11740d = c2534b;
        this.f11741e = z;
    }

    /* renamed from: a */
    public MedalsActivity$a m12504a(ViewGroup viewGroup, int i) {
        if (i == -1) {
            return new MedalsActivity$a(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.medal_item_empty_view, viewGroup, false));
        }
        return new MedalsActivity$a(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.medal_item_view, viewGroup, false));
    }

    /* renamed from: a */
    public void m12505a(final MedalsActivity$a medalsActivity$a, final int i) {
        if (!this.f11741e || i >= 3) {
            MedalDTO medalDTO;
            if (!this.f11741e || i < 3 || i > getItemCount()) {
                medalDTO = (MedalDTO) this.f11739c.get(i);
            } else {
                medalDTO = (MedalDTO) this.f11739c.get(i - 3);
            }
            if (medalDTO != null) {
                medalsActivity$a.f11727b.setText(medalDTO.getName());
                Object unLightUrl = medalDTO.getUnLightUrl();
                if (this.f11741e) {
                    unLightUrl = medalDTO.getLightUrl();
                }
                if (!TextUtils.isEmpty(unLightUrl)) {
                    Picasso.with(this.f11737a).load(unLightUrl).fit().centerCrop().into(medalsActivity$a.f11728c);
                }
                medalsActivity$a.f11726a.setOnClickListener(new OnClickListener(this) {
                    /* renamed from: d */
                    final /* synthetic */ MedalsActivity$b f11732d;

                    public void onClick(View view) {
                        MedalsActivity.a(this.f11732d.f11737a, medalDTO);
                        this.f11732d.f11740d.mo3520a(medalsActivity$a, this.f11732d.f11741e ? i - 3 : i);
                    }
                });
                medalsActivity$a.f11726a.setOnLongClickListener(new OnLongClickListener(this) {
                    /* renamed from: d */
                    final /* synthetic */ MedalsActivity$b f11736d;

                    public boolean onLongClick(View view) {
                        MedalsActivity.a(this.f11736d.f11737a, medalDTO);
                        this.f11736d.f11740d.mo3521b(medalsActivity$a, this.f11736d.f11741e ? i - 3 : i);
                        return true;
                    }
                });
            }
        }
    }

    public int getItemCount() {
        if (this.f11741e) {
            return this.f11739c.size() + 3;
        }
        return this.f11739c.size();
    }

    public int getItemViewType(int i) {
        if (!this.f11741e || i >= 3) {
            return super.getItemViewType(i);
        }
        return -1;
    }
}
