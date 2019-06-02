package com.beastbikes.android.modules.cycling.ranking.ui;

import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/* compiled from: RankViewHolder */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.ui.b */
public final class C1416b extends ViewHolder<RankDTO> {
    /* renamed from: a */
    private View f5411a;
    @C1458a(a = 2131757339)
    /* renamed from: b */
    private TextView f5412b;
    @C1458a(a = 2131756762)
    /* renamed from: c */
    private CircleImageView f5413c;
    @C1458a(a = 2131757340)
    /* renamed from: d */
    private TextView f5414d;
    @C1458a(a = 2131757341)
    /* renamed from: e */
    private TextView f5415e;
    @C1458a(a = 2131757342)
    /* renamed from: f */
    private TextView f5416f;
    @C1458a(a = 2131757343)
    /* renamed from: g */
    private TextView f5417g;
    @C1458a(a = 2131757344)
    /* renamed from: h */
    private TextView f5418h;
    @C1458a(a = 2131757346)
    /* renamed from: i */
    private TextView f5419i;
    @C1458a(a = 2131757345)
    /* renamed from: j */
    private TextView f5420j;
    /* renamed from: k */
    private boolean f5421k = true;

    public /* synthetic */ void bind(Object obj) {
        m6730a((RankDTO) obj);
    }

    public C1416b(View view, boolean z) {
        super(view);
        this.f5411a = view;
        this.f5421k = z;
    }

    /* renamed from: a */
    public void m6730a(RankDTO rankDTO) {
        if (rankDTO != null) {
            switch (rankDTO.getOrdinal()) {
                case 1:
                    this.f5412b.setTextColor(getContext().getResources().getColor(C1373R.color.ranking_fragment_ordinal_default));
                    this.f5412b.setBackgroundResource(C1373R.drawable.ordinal_bg_1);
                    break;
                case 2:
                    this.f5412b.setTextColor(getContext().getResources().getColor(C1373R.color.ranking_fragment_ordinal_default));
                    this.f5412b.setBackgroundResource(C1373R.drawable.ordinal_bg_2);
                    break;
                case 3:
                    this.f5412b.setTextColor(getContext().getResources().getColor(C1373R.color.ranking_fragment_ordinal_default));
                    this.f5412b.setBackgroundResource(C1373R.drawable.ordinal_bg_3);
                    break;
                default:
                    this.f5412b.setTextColor(getContext().getResources().getColor(C1373R.color.ranking_fragment_ordinal_other));
                    this.f5412b.setBackgroundResource(17170445);
                    break;
            }
            String avatarUrl = rankDTO.getAvatarUrl();
            if (TextUtils.isEmpty(avatarUrl)) {
                this.f5413c.setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(getContext()).load(avatarUrl).fit().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).centerCrop().into(this.f5413c);
            }
            this.f5412b.setText(String.valueOf(rankDTO.getOrdinal()));
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser == null || !rankDTO.getUserId().equals(currentUser.getObjectId())) {
                this.f5411a.setBackgroundResource(C1373R.color.universal_rank_item_color_1);
            } else {
                this.f5411a.setBackgroundResource(C1373R.color.universal_rank_item_color_2);
            }
            avatarUrl = rankDTO.getNickname();
            if (TextUtils.isEmpty(avatarUrl)) {
                avatarUrl = rankDTO.getUsername();
            }
            if (avatarUrl == null) {
                avatarUrl = "";
            }
            this.f5414d.setText(C2570p.a(avatarUrl, rankDTO.getRemarks()));
            CharSequence province = rankDTO.getProvince();
            if (TextUtils.isEmpty(province) || province.equals("null") || province.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                this.f5416f.setText("");
            } else {
                this.f5416f.setText(province);
            }
            province = rankDTO.getCity();
            if (TextUtils.isEmpty(province) || province.equals("null") || province.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                this.f5417g.setText("");
            } else {
                this.f5417g.setText(province);
            }
            avatarUrl = rankDTO.getDistrict();
            if (TextUtils.isEmpty(avatarUrl) || avatarUrl.equals("null") || avatarUrl.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                this.f5418h.setText("");
            } else {
                this.f5418h.setText(rankDTO.getDistrict());
            }
            double totalDistance = rankDTO.getTotalDistance();
            switch (rankDTO.getRankType()) {
                case 0:
                    totalDistance = rankDTO.getTotalDistance();
                    break;
                case 1:
                    totalDistance = rankDTO.getMonthlyDistance();
                    break;
                case 2:
                    totalDistance = rankDTO.getWeeklyDistance();
                    break;
            }
            if (totalDistance < 0.0d) {
                totalDistance = 0.0d;
            }
            if (this.f5421k) {
                this.f5419i.setText(String.format("%.0f", new Object[]{Double.valueOf(totalDistance / 1000.0d)}));
            } else {
                this.f5419i.setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.a(totalDistance / 1000.0d))}));
                this.f5420j.setText("mi");
            }
            if (rankDTO.getLevel() == 128) {
                this.f5415e.setVisibility(0);
            } else {
                this.f5415e.setVisibility(8);
            }
        }
    }
}
