package com.beastbikes.android.ble.ui;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

class SpeedXForceScreenEditActivity$b extends ViewHolder {
    /* renamed from: a */
    final /* synthetic */ SpeedXForceScreenEditActivity f7745a;
    /* renamed from: b */
    private ImageView f7746b;
    /* renamed from: c */
    private TextView f7747c;
    /* renamed from: d */
    private ImageView f7748d;
    /* renamed from: e */
    private TextView f7749e;
    /* renamed from: f */
    private ImageView f7750f;
    /* renamed from: g */
    private ImageView f7751g;

    SpeedXForceScreenEditActivity$b(SpeedXForceScreenEditActivity speedXForceScreenEditActivity, View view) {
        this.f7745a = speedXForceScreenEditActivity;
        super(view);
        this.f7746b = (ImageView) view.findViewById(C1373R.id.img_speedx_force_screen_edit_delete);
        this.f7747c = (TextView) view.findViewById(C1373R.id.tv_speedx_force_screen_settings_label);
        this.f7748d = (ImageView) view.findViewById(C1373R.id.img_speedx_force_screen_edit_drag);
        this.f7749e = (TextView) view.findViewById(C1373R.id.tv_speedx_force_screen_setting_unit);
        this.f7750f = (ImageView) view.findViewById(C1373R.id.img_speedx_force_screen_selected);
        this.f7751g = (ImageView) view.findViewById(C1373R.id.speedx_force_screen_blue);
    }
}
