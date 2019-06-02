package com.beastbikes.android.modules.shop.ui;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.dto.BikeShopTagInfoDto;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;

/* compiled from: BikeShopListViewHolder */
/* renamed from: com.beastbikes.android.modules.shop.ui.b */
public final class C1432b extends ViewHolder<BikeShopListDTO> {
    @C1458a(a = 2131755364)
    /* renamed from: a */
    public TextView f6105a;
    @C1458a(a = 2131755363)
    /* renamed from: b */
    public TextView f6106b;
    @C1458a(a = 2131755365)
    /* renamed from: c */
    public TextView f6107c;
    @C1458a(a = 2131756247)
    /* renamed from: d */
    public TextView f6108d;
    @C1458a(a = 2131756246)
    /* renamed from: e */
    private TextView f6109e;
    @C1458a(a = 2131756248)
    /* renamed from: f */
    private TextView f6110f;
    @C1458a(a = 2131755386)
    /* renamed from: g */
    private TextView f6111g;
    @C1458a(a = 2131756244)
    /* renamed from: h */
    private ImageView f6112h;
    @C1458a(a = 2131756245)
    /* renamed from: i */
    private TextView f6113i;
    @C1458a(a = 2131755366)
    /* renamed from: j */
    private TextView f6114j;
    /* renamed from: k */
    private String f6115k;
    /* renamed from: l */
    private DecimalFormat f6116l = new DecimalFormat("#.#");

    public /* synthetic */ void bind(Object obj) {
        m7277a((BikeShopListDTO) obj);
    }

    public C1432b(View view, String str) {
        super(view);
        this.f6115k = str;
    }

    /* renamed from: a */
    public void m7277a(BikeShopListDTO bikeShopListDTO) {
        double d = 0.0d;
        boolean z = false;
        if (bikeShopListDTO != null) {
            CharSequence charSequence;
            int i;
            this.f6109e.setText(bikeShopListDTO.getName());
            double distance = bikeShopListDTO.getDistance();
            if (distance >= 0.0d) {
                d = distance;
            }
            boolean b = C1849a.b(getContext());
            String str;
            Object obj;
            if (d < 1000.0d) {
                str = Math.round(d) + " m";
                if (!b) {
                    charSequence = Math.round(C1849a.c(d)) + " ft";
                }
                obj = str;
            } else {
                str = this.f6116l.format(d / 1000.0d) + " km";
                if (!b) {
                    charSequence = this.f6116l.format(C1849a.a(d / 1000.0d)) + " mi";
                }
                obj = str;
            }
            this.f6110f.setText(charSequence);
            this.f6111g.setText(bikeShopListDTO.getProvince() + "  " + bikeShopListDTO.getCity());
            if (TextUtils.isEmpty(bikeShopListDTO.getBikeShopImg())) {
                this.f6112h.setImageResource(C1373R.drawable.ic_avatar_club);
            } else {
                Picasso.with(getContext()).load(bikeShopListDTO.getBikeShopImg()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar_club).placeholder((int) C1373R.drawable.ic_avatar_club).into(this.f6112h);
            }
            BikeShopTagInfoDto tagInfo = bikeShopListDTO.getTagInfo();
            if (tagInfo != null) {
                TextView textView = this.f6114j;
                if (tagInfo.isRent()) {
                    i = 0;
                } else {
                    i = 8;
                }
                textView.setVisibility(i);
                textView = this.f6105a;
                if (tagInfo.isCare()) {
                    i = 0;
                } else {
                    i = 8;
                }
                textView.setVisibility(i);
                textView = this.f6106b;
                if (tagInfo.isFix()) {
                    i = 0;
                } else {
                    i = 8;
                }
                textView.setVisibility(i);
                textView = this.f6107c;
                if (tagInfo.isOfficeExperience()) {
                    i = 0;
                } else {
                    i = 8;
                }
                textView.setVisibility(i);
            }
            switch (bikeShopListDTO.getLevel()) {
                case 0:
                    this.f6113i.setVisibility(8);
                    break;
                case 1:
                    this.f6113i.setVisibility(0);
                    this.f6113i.setBackgroundResource(C1373R.drawable.bg_bike_shop_service_providers);
                    this.f6113i.setText(C1373R.string.str_bicycle_shop_service_level);
                    this.f6113i.setTextColor(Color.parseColor("#713500"));
                    break;
                case 2:
                    this.f6113i.setVisibility(0);
                    this.f6113i.setBackgroundResource(C1373R.drawable.bg_bike_shop_dealers);
                    this.f6113i.setText(C1373R.string.str_bicycle_shop_dealer_level);
                    this.f6113i.setTextColor(Color.parseColor("#ffffff"));
                    break;
            }
            if (this.f6115k.equals("mine")) {
                this.f6108d.setVisibility(0);
                this.f6110f.setVisibility(8);
                TextView textView2 = this.f6108d;
                if (bikeShopListDTO.getStatus() == 0) {
                    z = true;
                }
                textView2.setSelected(z);
                TextView textView3 = this.f6108d;
                if (bikeShopListDTO.getStatus() == 0) {
                    i = C1373R.string.bike_shop_applying;
                } else {
                    i = C1373R.string.bike_shop_unpass;
                }
                textView3.setText(i);
                return;
            }
            this.f6108d.setVisibility(8);
            this.f6110f.setVisibility(0);
        }
    }
}
