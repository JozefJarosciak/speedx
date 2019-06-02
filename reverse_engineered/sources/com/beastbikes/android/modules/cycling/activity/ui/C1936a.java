package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.dto.PreviewDto;
import com.beastbikes.android.widget.NumberTextView;
import com.beastbikes.android.widget.convenientbanner.p116b.C1935b;

/* compiled from: CyclingDataViewHolder */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.a */
public class C1936a implements OnClickListener, C1935b<PreviewDto> {
    /* renamed from: a */
    public NumberTextView f8720a;
    /* renamed from: b */
    public NumberTextView f8721b;
    /* renamed from: c */
    private Context f8722c;
    /* renamed from: d */
    private TextView f8723d;
    /* renamed from: e */
    private TextView f8724e;
    /* renamed from: f */
    private LinearLayout f8725f;
    /* renamed from: g */
    private LinearLayout f8726g;
    /* renamed from: h */
    private View f8727h;
    /* renamed from: i */
    private int f8728i;

    /* renamed from: a */
    public View mo3293a(Context context) {
        this.f8722c = context;
        View inflate = LayoutInflater.from(context).inflate(C1373R.layout.cycling_fragment_viewpager_data, null);
        this.f8725f = (LinearLayout) inflate.findViewById(C1373R.id.cycling_fragment_viewpager_data_1);
        this.f8726g = (LinearLayout) inflate.findViewById(C1373R.id.cycling_fragment_viewpager_data_2);
        this.f8723d = (TextView) inflate.findViewById(C1373R.id.cycling_fragment_viewpager_data_1_label);
        this.f8724e = (TextView) inflate.findViewById(C1373R.id.cycling_fragment_viewpager_data_2_label);
        this.f8720a = (NumberTextView) inflate.findViewById(C1373R.id.cycling_fragment_viewpager_data_1_value);
        this.f8721b = (NumberTextView) inflate.findViewById(C1373R.id.cycling_fragment_viewpager_data_2_value);
        this.f8727h = inflate.findViewById(C1373R.id.cycling_fragment_viewpager_data_line);
        return inflate;
    }

    /* renamed from: a */
    public void m9979a(Context context, int i, PreviewDto previewDto) {
        if (previewDto != null) {
            this.f8728i = i;
            CharSequence label1 = previewDto.getLabel1();
            if (TextUtils.isEmpty(label1)) {
                this.f8725f.setVisibility(8);
            } else {
                this.f8725f.setVisibility(0);
                this.f8723d.setText(label1);
            }
            CharSequence value1 = previewDto.getValue1();
            if (TextUtils.isEmpty(value1)) {
                this.f8725f.setVisibility(8);
            } else {
                this.f8725f.setVisibility(0);
                this.f8720a.setText(value1);
            }
            value1 = previewDto.getLabel2();
            if (TextUtils.isEmpty(value1)) {
                this.f8726g.setVisibility(8);
            } else {
                this.f8726g.setVisibility(0);
                this.f8724e.setText(value1);
            }
            CharSequence value2 = previewDto.getValue2();
            if (TextUtils.isEmpty(value2)) {
                this.f8726g.setVisibility(8);
            } else {
                this.f8726g.setVisibility(0);
                this.f8721b.setText(value2);
            }
            if (TextUtils.isEmpty(label1) || TextUtils.isEmpty(value1)) {
                this.f8727h.setVisibility(8);
            }
            this.f8725f.setOnClickListener(this);
            this.f8726g.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cycling_fragment_viewpager_data_1:
            case C1373R.id.cycling_fragment_viewpager_data_2:
                Intent intent = new Intent(this.f8722c, CyclingSettingPageActivity.class);
                intent.putExtra("position", this.f8728i);
                this.f8722c.startActivity(intent);
                return;
            default:
                return;
        }
    }
}
