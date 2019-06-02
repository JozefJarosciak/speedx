package com.beastbikes.android.modules.cycling.club.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/* compiled from: ClubSectionChildViewHolder */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b */
public final class C1413b extends ViewHolder<ClubInfoCompact> {
    @C1458a(a = 2131756322)
    /* renamed from: a */
    public View f5344a;
    @C1458a(a = 2131756321)
    /* renamed from: b */
    public View f5345b;
    @C1458a(a = 2131756317)
    /* renamed from: c */
    public ImageView f5346c;
    @C1458a(a = 2131756316)
    /* renamed from: d */
    private TextView f5347d;
    @C1458a(a = 2131756320)
    /* renamed from: e */
    private TextView f5348e;
    @C1458a(a = 2131756318)
    /* renamed from: f */
    private TextView f5349f;
    @C1458a(a = 2131756315)
    /* renamed from: g */
    private CircleImageView f5350g;
    @C1458a(a = 2131756319)
    /* renamed from: h */
    private TextView f5351h;

    public /* synthetic */ void bind(Object obj) {
        m6644a((ClubInfoCompact) obj);
    }

    public C1413b(View view) {
        super(view);
    }

    /* renamed from: a */
    public void m6644a(ClubInfoCompact clubInfoCompact) {
        double d = 0.0d;
        if (clubInfoCompact != null) {
            CharSequence charSequence;
            this.f5347d.setText(clubInfoCompact.getName());
            this.f5348e.setText(clubInfoCompact.getCity());
            double milestone = clubInfoCompact.getMilestone();
            if (milestone >= 0.0d) {
                d = milestone;
            }
            String str = getContext().getString(C1373R.string.club_info_total_distance) + Math.round(d / 1000.0d) + " km";
            if (C1849a.b(getContext())) {
                Object obj = str;
            } else {
                charSequence = getContext().getString(C1373R.string.club_info_total_distance) + Math.round(C1849a.a(d / 1000.0d)) + " mi";
            }
            this.f5351h.setText(charSequence);
            this.f5349f.setText(getContext().getString(C1373R.string.club_info_item_member_rank_desc) + ":" + clubInfoCompact.getMembers());
            if (TextUtils.isEmpty(clubInfoCompact.getLogo())) {
                this.f5350g.setImageResource(C1373R.drawable.ic_avatar_club);
            } else {
                Picasso.with(getContext()).load(clubInfoCompact.getLogo()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar_club).placeholder((int) C1373R.drawable.ic_avatar_club).into(this.f5350g);
            }
            if (C1849a.a()) {
                this.f5346c.setVisibility(0);
                if (clubInfoCompact.getType() == 0) {
                    this.f5346c.setBackgroundResource(0);
                    return;
                } else if (clubInfoCompact.getType() == 1) {
                    this.f5346c.setImageResource(C1373R.drawable.ic_club_bicycle_shop);
                    return;
                } else if (clubInfoCompact.getType() == 2) {
                    this.f5346c.setImageResource(C1373R.drawable.ic_club_school);
                    return;
                } else {
                    return;
                }
            }
            this.f5346c.setVisibility(4);
        }
    }
}
